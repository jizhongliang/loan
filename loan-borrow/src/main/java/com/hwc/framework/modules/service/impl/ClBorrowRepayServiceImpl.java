package com.hwc.framework.modules.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.druid.sql.visitor.functions.If;
import com.hwc.framework.modules.model.*;
import com.hwc.framework.modules.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.hwc.base.api.IdRequest;
import com.hwc.base.api.Response;
import com.hwc.base.exception.ServiceException;
import com.hwc.common.aliyun.IHwcCache;
import com.hwc.framework.common.AverageCapitalUtils;
import com.hwc.framework.common.Constant;
import com.hwc.framework.common.DateUtil;
import com.hwc.framework.common.ParamUtil;
import com.hwc.framework.common.RateUtils;
import com.hwc.framework.common.RepayStateConstant;
import com.hwc.framework.modules.dao.ClBorrowRepayMapper;
import com.hwc.framework.modules.domain.BaseCode;
import com.hwc.framework.modules.domain.BorrowBean;
import com.hwc.framework.modules.domain.BorrowBeanBigDecimal;
import com.hwc.framework.modules.domain.BorrowRepayBean;
import com.hwc.framework.modules.domain.ChargeRespBean;
import com.hwc.framework.modules.domain.PreRepayBean;
import com.hwc.framework.modules.domain.PreRepayInterestAndBreakFine;
import com.hwc.framework.modules.domain.RepayPlanBean;
import com.hwc.framework.modules.domain.RepayPlanBeanBigDecimal;
import com.hwc.framework.modules.domain.RepayQueryRequest;
import com.hwc.framework.modules.model.ClBankCard;
import com.hwc.framework.modules.model.ClBorrow;
import com.hwc.framework.modules.model.ClBorrowRepay;
import com.hwc.framework.modules.model.ClMortgage;
import com.hwc.framework.modules.model.ClPayLog;
import com.hwc.framework.modules.service.ArcBasecodeService;
import com.hwc.framework.modules.service.ArcCreditService;
import com.hwc.framework.modules.service.ArcSysConfigService;
import com.hwc.framework.modules.service.ChargeService;
import com.hwc.framework.modules.service.ClBankCardService;
import com.hwc.framework.modules.service.ClBorrowRepayLogService;
import com.hwc.framework.modules.service.ClBorrowRepayService;
import com.hwc.framework.modules.service.ClBorrowService;
import com.hwc.framework.modules.service.ClMortgageService;
import com.hwc.framework.modules.service.ClPayLogService;
import com.hwc.framework.modules.third.BorrowNotifyService;
import com.hwc.framework.modules.third.UserService;
import com.hwc.mybatis.core.AbstractService;

import cn.freesoft.utils.FsUtils;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

/**
 * 2017/10/31.
 */
@Service
public class ClBorrowRepayServiceImpl extends AbstractService<ClBorrowRepayMapper, ClBorrowRepay>
		implements ClBorrowRepayService {
	private static final Logger logger = LoggerFactory.getLogger(ClBorrowRepayServiceImpl.class);
	@Autowired
	private IHwcCache cache;

	@Autowired
	private ClBorrowService borrowService;
	@Autowired
	private ClBorrowRepayMapper clBorrowRepayMapper;

	@Autowired
	private ClBankCardService bankCardService;

	@Autowired
	private ArcCreditService arcCreditService;

	@Autowired
	private ArcBasecodeService basecodeService;

	@Autowired
	private ArcSysConfigService sysConfigService;
	@Autowired
	private ClBorrowRepayLogService borrowRepayLogService;

	@Autowired
	private ClMortgageService mortgageService;
	@Autowired
	private BorrowNotifyService notifyService;
	@Autowired
	private ChargeService chargeService;

	@Autowired
	private UserService userService;
	@Autowired
	private ClPayLogService clPayLogService;
	@Autowired
	private CLWContactService clwContactService;

	public void createRepayPlan(ClBorrow borrow) {
		List<JSONObject> jsonObjects = AverageCapitalUtils.getPerMonthPrincipalInterest(
				borrow.getAmount().doubleValue(), new Date(), borrow.getRate().doubleValue(), borrow.getPeriods());
		for (JSONObject jsonObject : jsonObjects) {
			doSavePlan(jsonObject, borrow.getUserId(), borrow.getId(), borrow.getRate());
		}
	}

	public List<RepayPlanBean> getRepayPlans(Long userId) {
		List<ClBorrow> borrows = borrowService.getBorrowLoanSuccess(userId);
		List<RepayPlanBean> beans = new ArrayList<>();
		for (ClBorrow borrow : borrows) {
			RepayPlanBean planBean = getRepayPlan(borrow.getId());
			if (!ParamUtil.isEmpty(planBean)) {
				beans.add(planBean);
			}
		}
		return beans;
	}

	/**
	 * 计算利息
	 *
	 * @param amount
	 * @param periods
	 * @return
	 */
	public Double calcInterest(Double amount, Integer periods, Double rate) {
		String key = getRepayPlanKey(amount, rate, periods) + ":calcInterest";
		if (cache.exists(key)) {
			return FsUtils.d(cache.get(key));
		}
		BorrowBean bean = new BorrowBean();
		bean.setAmount(amount);
		bean.setRate(rate);
		bean.setPeriods(periods);
		RepayPlanBean plans = getTempRepayPlan(bean);
		Double interest = 0D;
		for (BorrowRepayBean repayBean : plans.getPlans()) {
			interest = FsUtils.addDouble(interest, repayBean.getInterest().doubleValue());
		}
		cache.set(key, interest + "");
		return interest;
	}

	private String getRepayPlanKey(Double amount, Double rate, int periods) {
		int day = FsUtils.i(FsUtils.formatDateTime(new Date(), "dd"));
		String nday = FsUtils.formatDateTime(new Date(), "MM");
		if (day <= 28) {
			nday = nday + 28;
		} else {
			day = FsUtils.i(FsUtils.formatDateTime(new Date(), "MMdd"));
		}

		String key = "repayPlan:date:" + day + ":amount:" + amount + ":rate:" + rate + ":periods:" + periods;
		return key;
	}

	@Override
	public Response getTempRepayPlanList(BorrowBeanBigDecimal borrow) {
		List<RepayPlanBeanBigDecimal> beans = new ArrayList<>();
		List<BaseCode> codes = basecodeService.getBasecodeList("mortgage_periods");
		Long userId = borrow.getUserId();
		ClMortgage mortgage = mortgageService.getMortgage(userId);
		if (FsUtils.strsEmpty(mortgage)) {
			borrow.setRate(userService.getBorrowRateByUserIdBigDecimal(userId));
		} else {
			borrow.setRate(mortgage.getRealRate());
		}
		Date expireDate = mortgage.getExpireDate();
		for (BaseCode code : codes) {
			int periods = FsUtils.i(code.getCode());
			// 获取在范围内的分期数
			if (FsUtils.compare_Day(expireDate, FsUtils.addMonth(new Date(), periods)) >= 0) {
				borrow.setPeriods(periods);
				beans.add(getTempRepayPlanBig(borrow));
			}
		}
		return Response.success(beans);
	}

	/**
	 * 只计算第一个还款日的金额和时间
	 *
	 * @param borrow
	 * @return
	 */
	@Override
	public BorrowRepayBean calcFirstRepay(BorrowBean borrow) {
		if (FsUtils.strsEmpty(borrow.getAmount(), borrow.getPeriods(), borrow.getRate())) {
			throw new ServiceException("借款数据不能为空");
		}
		Double mod = borrow.getAmount() % 1000D;
		if (!mod.equals(0D)) {
			throw new ServiceException("借款金额必须为1000的整数倍！");
		}
		// ArcCredit credit =
		// arcCreditService.getCreditByUserid(borrow.getUserId());
		// if (FsUtils.strsNotEmpty(credit)) {
		// Double left = FsUtils.subDouble(credit.getUnuse().doubleValue(),
		// borrow.getAmount());
		// if (left < 0) {
		// throw new ServiceException("可借款额度不能超过" +
		// FsUtils.roundDouble(credit.getUnuse().doubleValue(), 2));
		// }
		// }
		String key = getRepayPlanKey(borrow.getAmount(), borrow.getRate(), borrow.getPeriods()) + ":calcFirstRepay";
		if (cache.exists(key)) {
			return cache.get(key, BorrowRepayBean.class);
		}
		double principal = AverageCapitalUtils.getPerMonthPrincipal(borrow.getAmount(), borrow.getPeriods());
		Date now = FsUtils.getInstanceOfDay(new Date());
		Double days = FsUtils.getDays(now, FsUtils.addMonth(now, 1));
		Double interest = FsUtils.mulDouble(borrow.getAmount(),
				FsUtils.mulDouble(days, RateUtils.getRate(borrow.getRate())));
		BorrowRepayBean bean = new BorrowRepayBean();
		bean.setRepayDate(FsUtils.addMonth(new Date(), 1));
		bean.setInterest(new BigDecimal(interest).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		bean.setRealAmount(new BigDecimal(principal).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		bean.setAmount(new BigDecimal(FsUtils.roundCeilingDouble(FsUtils.addDouble(interest, principal), 2))
				.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		Long expire = FsUtils.getDaySpan(new Date());
		cache.set(key, expire, bean);
		return bean;
	}

	@Override
	public Response<BorrowRepayBean> getBorrowRepayDetail(IdRequest<Long> request) {
		ClBorrowRepay repay = this.mapper.selectByPrimaryKey(request.getId());
		if (FsUtils.strsNotEmpty(repay) && repay.getState().equals(RepayStateConstant.NU_PAY)) {
			ClBorrow borrow = borrowService.getBorrow(repay.getBorrowId());
			int day = FsUtils.compare_Day(borrow.getLoanTime(), new Date());
			if (day == 0) {
				return Response.fail("当日申请次日才可归还哦!");
			}

			BorrowRepayBean bean = new BorrowRepayBean();

			bean.setRealAmount(repay.getRealAmount().doubleValue());
			bean.setId(repay.getId());
			bean.setInterest(repay.getInterest().doubleValue());
			PreRepayInterestAndBreakFine interestAndBreakFine = calcuPreRepayInterestAndBreakFine(repay, borrow);
			// bean.setInterest(interestAndBreakFine.getInterest().doubleValue());
			bean.setPenaltyAmout(repay.getPenaltyAmout().doubleValue());
			bean.setBorrowId(repay.getBorrowId());
			bean.setPenaltyDay(repay.getPenaltyDay());
			// 计算应还金额 本金+利息+逾期费用
			bean.setAmount(FsUtils.addDouble(bean.getInterest(),
					FsUtils.addDouble(bean.getRealAmount(), bean.getPenaltyAmout())));
			ClBankCard card = bankCardService.getBankCard(repay.getUserId());
			if (FsUtils.strsNotEmpty(card)) {
				bean.setCardno(card.getBank() + " 尾号"
						+ card.getCardNo().substring(card.getCardNo().length() - 4, card.getCardNo().length()));
			}
			return Response.success(bean);
		} else {
			return Response.fail("未找到还款记录");
		}

	}

	@Override
	public Response<RepayPlanBean> genCreditRepayPlan(BorrowBeanBigDecimal borrow) {
		// 获取信用的白名单
	/*	List<CLWContacts> clwContactsList = clwContactService.getAllContactsByPhone(borrow.getMobile());
		if (!ParamUtil.isEmpty(clwContactsList) && clwContactsList.size() > 0) {
			borrow.setRate(clwContactsList.get(0).getBorrowRate());
		}*/

		Map<String,String> map=borrowService.findRate(borrow.getUserId());
		BigDecimal bigDecimal =new BigDecimal(map.get("rate"));
		bigDecimal= bigDecimal.setScale(4, BigDecimal.ROUND_DOWN);
		borrow.setRate(bigDecimal);
		return Response.success(getTempRepayPlanBig(borrow));
	}


	/**
	 * 根据订单 获取临时的还款计划
	 *
	 * @param borrow
	 * @return
	 */
	public RepayPlanBean getTempRepayPlan(BorrowBean borrow) {
		// Date date=new Date();
		if (FsUtils.strsEmpty(borrow.getAmount(), borrow.getRate(), borrow.getPeriods())) {
			throw new ServiceException("参数不能为空");
		}
		String key = getRepayPlanKey(borrow.getAmount(), borrow.getRate(), borrow.getPeriods());
		if (cache.exists(key)) {
			return cache.get(key, RepayPlanBean.class);
		}
		List<JSONObject> jsonObjects = AverageCapitalUtils.getPerMonthPrincipalInterest(
				borrow.getAmount().doubleValue(), new Date(), borrow.getRate().doubleValue(), borrow.getPeriods());
		List<BorrowRepayBean> plans = new ArrayList<>();
		RepayPlanBean planBean = new RepayPlanBean();
		ClBorrow borrowBo = borrowService.getBorrow(borrow.getId());
		if (borrowBo != null) {
			planBean.setBorrow_date(borrowBo.getLoanTime());
		}
		planBean.setAmount(new BigDecimal(borrow.getAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		planBean.setRate(String.valueOf(borrow.getRate()));
		planBean.setPeriods(jsonObjects.size());
		planBean.setEnd_repay_date(new Date());
		planBean.setFirst_repay_date(new Date());
		planBean.setUnRepay_periods(borrow.getPeriods());
		planBean.setRepayAmount(this.mapper.clacRepayAmount(borrow.getId()));
		for (JSONObject jsonObject : jsonObjects) {
			Date date = jsonObject.getDate("date");
			if (1 == jsonObject.getIntValue("seq")) {
				planBean.setFirst_repay_amount(new BigDecimal(FsUtils.roundDouble(jsonObject.getDouble("dayRes"), 2))
						.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				planBean.setCurrent_repay_amount(new BigDecimal(FsUtils.roundDouble(jsonObject.getDouble("dayRes"), 2))
						.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				planBean.setCurrent_repay_date(date);
			}
			if (FsUtils.compare_Day(planBean.getEnd_repay_date(), date) <= 0) {
				planBean.setEnd_repay_date(date);
			}
			planBean.setInterest(new BigDecimal(
					FsUtils.roundDouble(FsUtils.addDouble(planBean.getInterest(), jsonObject.getDouble("interest")), 2))
							.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			BorrowRepayBean repay = new BorrowRepayBean();
			repay.setRepayDate(date);
			repay.setAmount(new BigDecimal(FsUtils.roundDouble(
					FsUtils.addDouble(jsonObject.getDouble("monthPri"), jsonObject.getDouble("interest")), 2))
							.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			repay.setInterest(new BigDecimal(FsUtils.roundDouble(jsonObject.getDouble("interest"), 2))
					.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			repay.setRealAmount(new BigDecimal(FsUtils.roundDouble(jsonObject.getDouble("monthPri"), 2))
					.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			repay.setRealAmountBalance(new BigDecimal(FsUtils.roundDouble(jsonObject.getDouble("balance"), 2))
					.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			plans.add(repay);
		}
		planBean.setPlans(plans);
		planBean.setTotal_amount(
				new BigDecimal(FsUtils.roundDouble(FsUtils.addDouble(planBean.getAmount(), planBean.getInterest()), 2))
						.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		planBean.setUnRepay_amount(
				new BigDecimal(planBean.getTotal_amount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		// Date endDate =
		// FsUtils.addDate(FsUtils.date(FsUtils.getLastDayOfMonth(new Date())),
		// -3);
		Long expire = FsUtils.getDateSpan(new Date(), FsUtils.addDate(new Date(), 7));
		// if (FsUtils.compare_Day(endDate, new Date()) >= 0) {
		// expire = FsUtils.getDateSpan(endDate, new Date());
		// }
		cache.set(key, expire, planBean);
		return planBean;
	}

	private ClBorrowRepay doSavePlan(JSONObject jsonObject, Long userId, Long borrow_id, BigDecimal rate) {
		ClBorrowRepay repay = new ClBorrowRepay();
		repay.setUserId(userId);
		repay.setBorrowId(borrow_id);
		repay.setCreateTime(new Date());
		repay.setState(RepayStateConstant.NU_PAY);
		repay.setSeq(jsonObject.getInteger("seq"));
		repay.setAmount(FsUtils.roundDouble(jsonObject.getBigDecimal("dayRes"), 2));
		repay.setInterest(FsUtils.roundDouble(jsonObject.getBigDecimal("interest"), 2));
		repay.setRate(rate);
		repay.setRepayTime(FsUtils.date(FsUtils.formatDate(jsonObject.getDate("date")) + " 23:23:59"));
		repay.setRealAmount(FsUtils.roundDouble(jsonObject.getBigDecimal("monthPri"), 2));
		repay.setRealAmountBalance(jsonObject.getBigDecimal("balance"));
		repay.setPenaltyAmout(FsUtils.bigdec(0));
		repay.setRepayAmount(FsUtils.bigdec(0));
		repay.setPenaltyDay(0);
		this.insert(repay);
		return repay;
	}

	public ClBorrowRepay getRepayPlanLately(Long borrowId) {
		ClBorrowRepay repay = mapper.getLatelyRepayPlan(borrowId);
		return repay;
	}

	private List<ClBorrowRepay> getBorrowRepay(Long borrowId) {
		Condition condition = new Condition(ClBorrowRepay.class);
		Example.Criteria criteria = condition.createCriteria();
		criteria.andEqualTo("borrowId", borrowId);
		condition.setOrderByClause("seq");
		return this.mapper.selectByCondition(condition);

	}

	public RepayPlanBean getRepayPlan(Long borrowId) {
		List<ClBorrowRepay> repays = getBorrowRepay(borrowId);
		List<BorrowRepayBean> beans = new ArrayList<>();
		ClBorrow borrow = borrowService.getBorrow(borrowId);
		List<CLWContacts> clwContactsList = clwContactService.getAllContactsByPhone(borrow.getMobile());
		CLWContacts clwContacts = null;
		if (!ParamUtil.isEmpty(clwContactsList) && clwContactsList.size() > 0) {
			clwContacts = clwContactsList.get(0);
		}

		RepayPlanBean planBean = new RepayPlanBean();
		// 校验是否都已还款,如果是，就不展示
		int successCount = 0;
		for (ClBorrowRepay repay : repays) {
			if (repay.getState().equals(RepayStateConstant.PAIED)) {
				successCount++;
			}
			if (successCount == repays.size()) {
				// 返回空即可
				logger.info("已全部还款，borrowId:{}", borrowId);
				return null;
			}
		}

		if (FsUtils.strsNotEmpty(borrow)) {
			planBean.setBorrow_date(borrow.getLoanTime());
		}
		planBean.setRepayAmount(this.mapper.clacRepayAmount(borrowId));
		planBean.setEnd_repay_date(new Date());
		planBean.setFirst_repay_date(FsUtils.addMonth(new Date(), 2));
		planBean.setBorrowId(borrowId);
		planBean.setPeriods(borrow.getPeriods());
		planBean.setAmount(borrow.getAmount().doubleValue());
		for (ClBorrowRepay repay : repays) {
			if (repay.getState().equals(RepayStateConstant.PRE_PAY)) {
				continue;
			}
			if (FsUtils.compare_Day(planBean.getFirst_repay_date(), repay.getRepayTime()) >= 0) {
				planBean.setFirst_repay_date(borrow.getCreateTime());
				planBean.setFirst_repay_amount(repay.getTotalAmount());
			}
			if (FsUtils.compare_Day(planBean.getEnd_repay_date(), repay.getRepayTime()) <= 0) {
				planBean.setEnd_repay_date(repay.getRepayTime());
			}
			if (repay.getState().equals(RepayStateConstant.PAIED)) {
				planBean.setHasRepay_periods(planBean.getHasRepay_periods() + 1);
			} else {
				if (FsUtils.strsEmpty(planBean.getCurrent_repay_amount()) || 0D == planBean.getCurrent_repay_amount()) {
					// 当期应还款金额
					BigDecimal current = repay.getRealAmount().add(repay.getInterest().add(repay.getPenaltyAmout()));
					planBean.setCurrent_repay_amount(
							(ParamUtil.isEmpty(current) ? BigDecimal.ZERO.setScale(2) : current).doubleValue());
					planBean.setCurrent_repay_date(repay.getRepayTime());
					planBean.setCurrent_repay_id(repay.getId());
					planBean.setFirst_repay_id(repay.getId());
				}
				// 未还款期数
				planBean.setUnRepay_periods(planBean.getUnRepay_periods() + 1);
				// 未还款本金
				planBean.setUnRepay_amount(
						FsUtils.addDouble(planBean.getUnRepay_amount(), repay.getAmount().doubleValue()));
			}
			// TODO: 2018/1/8 利息
			planBean.setInterest(repay.getInterest().doubleValue());
			planBean.setRate(ParamUtil.isEmpty(clwContacts) ? String.valueOf(borrow.getRate())
					: String.valueOf(clwContacts.getBorrowRate()));
			BorrowRepayBean bean = new BorrowRepayBean();
			bean.setId(repay.getId());
			bean.setRate(ParamUtil.isEmpty(clwContacts) ? String.valueOf(borrow.getRate())
					: String.valueOf(clwContacts.getBorrowRate()));
			// 利息
			bean.setInterest(repay.getInterest().doubleValue());
			// 应还款金额 +逾期费用
			bean.setAmount(FsUtils.addNumber(repay.getAmount(), repay.getPenaltyAmout()).doubleValue());
			bean.setBorrowId(repay.getBorrowId());
			bean.setSeq(repay.getSeq());
			bean.setState(repay.getState());
			bean.setRepayDate(repay.getRepayTime());
			bean.setId(repay.getId());
			bean.setPenaltyAmout(repay.getPenaltyAmout().doubleValue());
			bean.setPenaltyDay(repay.getPenaltyDay());
			bean.setRealAmountBalance(repay.getRealAmountBalance().doubleValue());
			bean.setRealAmount(repay.getRealAmount().doubleValue());
			bean.setRepayAmount(repay.getRepayAmount().doubleValue());
			ClBankCard card = bankCardService.getBankCard(repay.getUserId());
			if (FsUtils.strsNotEmpty(card)) {
				planBean.setBankCardNo(card.getBank() + " 尾号"
						+ card.getCardNo().substring(card.getCardNo().length() - 4, card.getCardNo().length()));
			}
			beans.add(bean);
		}
		planBean.setPlans(beans);
		planBean.setTotal_amount(FsUtils.addDouble(planBean.getAmount(), planBean.getInterest()));
		// 如果全还完，则返回下列参数初始化
		if (FsUtils.strsEmpty(planBean.getCurrent_repay_amount()) || 0D == planBean.getCurrent_repay_amount()) {
			// 当期应还款金额
			ClBorrowRepay lastRepay = repays.get(repays.size() - 1);
			planBean.setCurrent_repay_amount(BigDecimal.ZERO.setScale(2).doubleValue());
			planBean.setCurrent_repay_date(lastRepay.getRepayTime());
			planBean.setCurrent_repay_id(lastRepay.getId());
			planBean.setFirst_repay_id(lastRepay.getId());
		}
		return planBean;
	}

	/**
	 * 还款更新还款计划
	 */
	public ClBorrowRepay repayment(Long id, Double amount) {
		ClBorrowRepay repay = mapper.selectByPrimaryKey(id);
		Double shouldPay = repay.getTotalAmount();
		ClBorrowRepay newRepay = new ClBorrowRepay();
		newRepay.setId(id);
		newRepay.setRepayAmount(FsUtils.bigdec(amount));
		// if (shouldPay.equals(amount)) {
		newRepay.setState(RepayStateConstant.PAIED);
		// } ad
		// newRepay.setRepayTime(new Date());
		this.update(newRepay);
		return mapper.selectByPrimaryKey(id);

	}

	/**
	 * 计算逾期
	 */
	public void overdue(Long id, Double rate) {
		ClBorrowRepay repay = mapper.selectByPrimaryKey(id);

		Double total = FsUtils.addDouble(repay.getRealAmount().doubleValue(),
				repay.getRealAmountBalance().doubleValue());
		// total = FsUtils.addDouble(total, repay.getInterest().doubleValue());
		Double overdueFee = FsUtils.mulDouble(total, RateUtils.getRate(rate));
		Date overdueDate = FsUtils.addDate(FsUtils.getInstanceOfDay(repay.getRepayTime()),
				repay.getPenaltyDay().intValue());
		if (FsUtils.compare_Day(overdueDate, new Date()) < 0) {
			ClBorrowRepay newRepay = new ClBorrowRepay();
			newRepay.setPenaltyDay(repay.getPenaltyDay().intValue() + 1);
			newRepay.setPenaltyAmout(FsUtils.addNumber(newRepay.getPenaltyAmout(), overdueFee));
			newRepay.setId(id);
			this.update(newRepay);
			// 逾期通知
			notifyService.borrowOverdueNotify(repay);
		}
	}

	public Response repaymentSuccessCallback(Long borrow_id, ChargeRespBean bean) {
		ClBorrowRepay repay = null;
		if (FsUtils.strsNotEmpty(bean.getRepay_id())) {
			repay = mapper.selectByPrimaryKey(FsUtils.l(bean.getRepay_id()));
		}
		if (FsUtils.strsEmpty(repay)) {
			repay = getRepayPlanLately(borrow_id);
			if (FsUtils.strsEmpty(repay)) {
				logger.info("提前还款未找到还款记录{}", borrow_id);
			}
		}

		if (FsUtils.strsNotEmpty(repay)) {
			repayment(repay.getId(), bean.getAmount());
			Map<String, Object> params = new HashMap<>();
			params.put("serialNumber", bean.getOrder_no());
			params.put("repayAccount", bean.getBank_card_no());
			params.put("repayWay", "10");
			borrowRepayLogService.doSave(repay, params);
			// 更新额度
			ClBorrow borrow = borrowService.getBorrow(borrow_id);
			/*
			 * if ("D".equals(borrow.getBorrowType())) {
			 * arcCreditService.increaseQuota(borrow.getUserId(),
			 * repay.getRealAmount().doubleValue()); }
			 */
			// 全部完成还款后
			if (isLastRepayPlan(repay)) {
				borrowService.repaymentSuccessCallback(borrow_id);
				/*
				 * if (borrow.getBorrowType().equals("X")) { //更新额度 信用类
				 * 完成还款后才更新额度 arcCreditService.increaseQuota(borrow.getUserId(),
				 * borrow.getAmount().doubleValue()); }
				 */
			}
			// 还款成功推送TODO
			try {
				logger.info("还款成功推送>>" + JSONObject.toJSONString(repay));
				notifyService.repaySuccess(repay);
			} catch (Exception e) {
				logger.info("还款成功推送出错");
			}

			arcCreditService.increaseQuota(borrow.getUserId(), repay.getRealAmount().doubleValue());

		}
		return Response.success();
	}

	public Response preRepaymentSuccessCallback(Long borrow_id, PreRepayBean preRepayBean, ChargeRespBean bean) {
		/*
		 * ClBorrowRepay repay = null; if
		 * (FsUtils.strsNotEmpty(bean.getRepay_id())) { repay =
		 * mapper.selectByPrimaryKey(FsUtils.l(bean.getRepay_id())); } if
		 * (FsUtils.strsEmpty(repay)) { repay = getRepayPlanLately(borrow_id);
		 * if (FsUtils.strsEmpty(repay)) { logger.info("提前还款未找到还款记录{}",
		 * borrow_id); } }
		 */
		ClBorrowRepay repay = new ClBorrowRepay();
		repay.setBorrowId(borrow_id);
		repay.setUserId(preRepayBean.getUser_id());
		repay.setAmount(new BigDecimal(preRepayBean.getTotal()));
		repay.setRepayTime(new Date());
		repay.setPenaltyDay(0);
		if (FsUtils.strsNotEmpty(repay)) {
			// repayment(repay.getId(), bean.getAmount());
			Map<String, Object> params = new HashMap<>();
			params.put("serialNumber", bean.getOrder_no());
			params.put("repayAccount", bean.getBank_card_no());
			params.put("repayWay", "10");
			borrowRepayLogService.doSave(repay, params);

		}
		return Response.success();
	}

	private boolean isLastRepayPlan(ClBorrowRepay repay) {
		ClBorrowRepay max = mapper.getMaxUnRepayPlan(repay.getBorrowId());
		if (FsUtils.strsNotEmpty(max) && max.getId().equals(repay.getId())) {
			return true;
		} else
			return false;
	}

	@Override
	public Response prePaymentCheck(Long borrowId) {
		ClBorrow borrow = borrowService.getBorrow(borrowId);
		int day = FsUtils.compare_Day(borrow.getLoanTime(), new Date());
		if (day == 0) {
			return Response.fail("当日申请次日才可归还哦!");
		}
		return Response.success();
	}

	/**
	 * 提前还款
	 *
	 * @param borrowId
	 * @return
	 */
	public Response getPrepaymentAmount(Long borrowId) {
		Map<String, Object> map = new HashMap<>();
		Date date = DateUtil.getDayStartTime(new Date());
		map.put("date", date);
		map.put("borrowId", borrowId);
		int count = mapper.countOverdue(map);
		if (count > 0) {
			return Response.fail("您已逾期需先完成本期还款才可提前还款!");
		}
		ClBorrowRepay repay = mapper.clacUnRepayAmount(borrowId);
		// 计算剩下未还额度
		Double total_amount = FsUtils.addDouble(repay.getPenaltyAmout().doubleValue(),
				repay.getRealAmount().doubleValue());
		// total_amount=FsUtils.addDouble(repay.getPenaltyAmout().doubleValue(),total_amount);
		ClBorrowRepay hasRepay = mapper.getLatelyHasRepayPlan(borrowId);
		ClBorrow borrow = borrowService.getBorrow(borrowId);
		int day;
		if (FsUtils.strsNotEmpty(hasRepay)) {
			if (DateUtil.daysBetween(new Date(), hasRepay.getRepayTime()) < 0) {
				day = 0;
			} else {
				day = Math.abs(DateUtil.daysBetween(new Date(), hasRepay.getRepayTime()));
			}
		} else {
			day = Math.abs(DateUtil.daysBetween(new Date(), borrow.getLoanTime()));
			if (day == 0) {
				return Response.fail("当日申请次日才可归还哦!");
			}
		}
		BigDecimal total = new BigDecimal(total_amount * day * 0.01);
		BigDecimal rate = borrow.getRate();
		Double total_repay_amount = total.multiply(rate).setScale(2, BigDecimal.ROUND_UP).doubleValue();
		// Double total_repay_amount =
		// FsUtils.roundUpDouble(FsUtils.mulDouble(total_amount,
		// FsUtils.mulDouble(borrow.getRate().doubleValue() * 0.01, day)), 2);
		// if (total_repay_amount.doubleValue() == 0.0) {
		// total_repay_amount = 0.01;
		// }
		PreRepayBean bean = new PreRepayBean();
		bean.setBorrow_id(borrowId);
		bean.setRate(borrow.getRate().doubleValue());
		bean.setAmount(total_amount);
		bean.setInterest(total_repay_amount);
		bean.setTotal(FsUtils.addDouble(bean.getAmount(), bean.getInterest()));
		ClBankCard card = bankCardService.getBankCard(repay.getUserId());
		bean.setBank_card_name(card.getBank());
		bean.setBank_card_no(card.getBank() + ""
				+ card.getCardNo().substring(card.getCardNo().length() - 4, card.getCardNo().length()));
		bean.setUser_id(borrow.getUserId());
		bean.setRepayAmount(mapper.clacRepayAmount(borrowId));
		return Response.success(bean);
	}

	public Response comfigPreRepayment(Long borrow_id, PreRepayBean preRepayBean, ChargeRespBean bean) {
		List<ClBorrowRepay> repays = getBorrowRepay(borrow_id);
		List<ClPayLog> payLogs = clPayLogService.getList(borrow_id);
		for (int i = 0; i < repays.size(); i++) {
			if (null != payLogs && payLogs.size() > 0) {
				if (i < payLogs.size()) {
					if (RepayStateConstant.NU_PAY.equals(repays.get(i).getState())) {
						repays.get(i).setState(RepayStateConstant.PAIED);
						repays.get(i).setInterest(repays.get(i).getAmount().subtract(repays.get(i).getRealAmount()));
						repays.get(i).setRepayAmount(payLogs.get(i).getAmount());
						logger.info("comfigPreRepayment,borrowId=>" + borrow_id + ",data=>"
								+ JSONObject.toJSONString(repays.get(i)));
						update(repays.get(i));
					} else {
						continue;
					}
				} else {
					if (RepayStateConstant.NU_PAY.equals(repays.get(i).getState())) {
						repays.get(i).setState(RepayStateConstant.PAIED);
						repays.get(i).setInterest(repays.get(i).getAmount().subtract(repays.get(i).getRealAmount()));
						repays.get(i).setRepayAmount(new BigDecimal(0));
						logger.info("comfigPreRepayment,borrowId=>" + borrow_id + ",data=>"
								+ JSONObject.toJSONString(repays.get(i)));
						update(repays.get(i));
					} else {
						continue;
					}
				}
			} else {
				if (RepayStateConstant.NU_PAY.equals(repays.get(i).getState())) {
					repays.get(i).setState(RepayStateConstant.PAIED);
					repays.get(i).setInterest(repays.get(i).getAmount().subtract(repays.get(i).getRealAmount()));
					repays.get(i).setRepayAmount(new BigDecimal(0));
					logger.info("comfigPreRepayment,borrowId=>" + borrow_id + ",data=>"
							+ JSONObject.toJSONString(repays.get(i)));
					update(repays.get(i));
				} else {
					continue;
				}
			}
		}
		return preRepaymentSuccessCallback(borrow_id, preRepayBean, bean);

	}

	public static void main(String[] args) {
		/*Map<String, Object> map=new HashMap<>();
		map.put("name", 0.0001);

		System.out.println( new BigDecimal((double)map.get("name")));*/
		BigDecimal bigDecimal =new BigDecimal("0.005");
		bigDecimal= bigDecimal.setScale(4, BigDecimal.ROUND_DOWN); //小数位 直接舍去

		System.out.println(bigDecimal);
	}


	@Override
	public Response repayList(RepayQueryRequest request) {
		PageHelper.startPage(request.getPage(), request.getPageSize());
		Map<String, Object> map = new HashMap<>();
		if (FsUtils.strsNotEmpty(request.getStart()))
			map.put("start", request.getStart());
		if (FsUtils.strsNotEmpty(request.getEnd()))
			map.put("end", FsUtils.formatDate(FsUtils.addDate(request.getEnd(), 1)));
		if (FsUtils.strsNotEmpty(request.getName()))
			map.put("name", request.getName());
		if (FsUtils.strsNotEmpty(request.getMobile()))
			map.put("mobile", request.getMobile());
		if (FsUtils.strsNotEmpty(request.getOrderNo()))
			map.put("orderNo", request.getOrderNo());
		if (FsUtils.strsNotEmpty(request.getState())) {
			map.put("state", request.getState());
		}
		map.put("borrow_type", request.getBorrow_type());
		List<BorrowRepayBean> beans = mapper.getRepayList(map);
		return Response.success(beans);
	}

	/**
	 * 获取逾期还款计划
	 *
	 * @param
	 * @return
	 */

	public List<ClBorrowRepay> getBorrowRepayOverdue(Date expireDate) {

		Map<String, Object> map = new HashMap<>();
		map.put("end", FsUtils.formatDate(expireDate));
		List<ClBorrowRepay> repays = mapper.getUnRepayList(map);

		return repays;
	}

	/**
	 * 快到期提醒 默认提前 days 天
	 *
	 * @return
	 */
	@Override
	public List<ClBorrowRepay> getSoonExpireRepayPlan(int days) {
		Map<String, Object> map = new HashMap<>();
		// String days = sysConfigService.getConfigDefault("repay_expire_day",
		// "2");
		Date date = FsUtils.addDate(new Date(), days);
		map.put("end", FsUtils.formatDate(date));
		List<ClBorrowRepay> repays = mapper.getUnRepayList(map);
		return repays;
	}

	/**
	 * 计算逾期
	 */
	@Override
	public void clacOverdue() {
		List<ClBorrowRepay> repays = getBorrowRepayOverdue(new Date());
		Map<Long, Long> map1 = new HashMap<>();
		for (ClBorrowRepay repay : repays) {
			try {
				if (map1.containsKey(repay.getBorrowId())) {
					continue;
				}
				ClBorrow borrow = borrowService.getBorrow(repay.getBorrowId());
				if (FsUtils.strsEmpty(borrow))
					continue;
				map1.put(borrow.getId(), borrow.getId());
				String overdue = sysConfigService.getConfigDefault("overdue_rate", "0.1");
				double overdueRate = FsUtils.d(overdue);
				overdue(repay.getId(), overdueRate);

			} catch (Exception ex) {
				logger.error("计算逾期错误:", ex);
			}
		}
	}

	/**
	 * 批量扣款
	 */
	@Override
	public void batchDebitRepay() {
		List<ClBorrowRepay> repays = getBorrowRepayOverdue(new Date());
		Map<Long, Long> map1 = new HashMap<>();
		for (ClBorrowRepay repay : repays) {
			try {
				if (map1.containsKey(repay.getBorrowId())) {
					continue;
				}
				ClBorrow borrow = borrowService.getBorrow(repay.getBorrowId());
				if (FsUtils.strsEmpty(borrow))
					continue;
				map1.put(borrow.getId(), borrow.getId());
				// DebitBean bean = new DebitBean();
				// bean.setRepayId(repay.getId());
				chargeService.repayment(repay);
				// overdue(repay.getId(), borrow.getRate().doubleValue());

			} catch (Exception ex) {
				logger.error("自动扣款失败:", ex);
			}
		}
	}

	/**
	 * 计算违约金 用于提前还款
	 * 
	 * @param interest
	 * @param timeLimit
	 * @return
	 */
	private BigDecimal calcuBreakFine(BigDecimal interest, int timeLimit) {
		// 6个月(含)以下，按20%，6个月以上，按15%
		if (Constant.BREAK_FINE_TIME_LIMIT >= timeLimit) {
			return interest.multiply(new BigDecimal(Constant.BREAK_FINE_LESS_THEN_TIME_LIMIT)).setScale(2,
					BigDecimal.ROUND_HALF_UP);
		} else {
			return interest.multiply(new BigDecimal(Constant.BREAK_FINE_MORE_THEN_TIME_LIMIT)).setScale(2,
					BigDecimal.ROUND_HALF_UP);
		}
	}

	/**
	 * 计算单笔还款 利息和违约金
	 * 
	 * @param repay
	 * @param borrow
	 * @return
	 */
	@Override
	public PreRepayInterestAndBreakFine calcuPreRepayInterestAndBreakFine(ClBorrowRepay repay, ClBorrow borrow) {
		// 应还款时间 - 分期周期 > 当前时间 即为提前还款
		if (ParamUtil.isEmpty(repay) || ParamUtil.isEmpty(borrow)) {
			return null;
		}
		PreRepayInterestAndBreakFine interestAndBreakFine = new PreRepayInterestAndBreakFine();

		// 分期不分期一块算
		if (ParamUtil.isEmpty(borrow.getPeriods())) {
			borrow.setPeriods(1);
		}
		// someDay = 应还款时间 - 分期周期
		Date someday = DateUtil.getSomeDay(repay.getRepayTime(),
				-(Integer.parseInt(borrow.getTimeLimit()) / borrow.getPeriods()));
		if (DateUtil.compareDate(someday, new Date()) > 0) {
			// 算违约 提前还款
			interestAndBreakFine.setInterest(BigDecimal.ZERO); // 利息
			// 违约金
			interestAndBreakFine
					.setBreakFine(calcuBreakFine(repay.getInterest(), Integer.parseInt(borrow.getTimeLimit())));
		} else {
			// 不算违约，正常利息
			// interestAndBreakFine.setInterest(repay.getInterest());
			// 利息重新计算，有一天算一天的
			int days = Math.abs(DateUtil.daysBetween(someday, new Date())) + 1;
			// BigDecimal interest =
			// caculateInterest(repay.getRealAmount().add(repay.getRealAmountBalance()),
			// repay.getRate(), String.valueOf(days));
			interestAndBreakFine.setInterest(repay.getInterest());
			interestAndBreakFine.setBreakFine(BigDecimal.ZERO);
		}

		return interestAndBreakFine;
	}

	/**
	 * 计算利息 （本金-已还款金额）* 本期天数 * 日利率
	 * 
	 * @param amount
	 * @param rate
	 * @param timeLimit
	 * @return
	 */
	public BigDecimal caculateInterest(BigDecimal amount, BigDecimal rate, String timeLimit) {
		BigDecimal _rate = rate.divide(new BigDecimal(100));
		return amount.multiply(_rate).multiply(new BigDecimal(timeLimit)).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	@Override
	public List<ClBorrowRepay> getList(Long borrow_id) {

		return clBorrowRepayMapper.getList(borrow_id);
	}

	/**
	 * new zq
	 */
	public RepayPlanBeanBigDecimal getTempRepayPlanBig(BorrowBeanBigDecimal borrow) {
		// Date date=new Date();
		if (FsUtils.strsEmpty(borrow.getAmount(), borrow.getRate(), borrow.getPeriods())) {
			throw new ServiceException("参数不能为空");
		}
		String key = getRepayPlanKeyBigDecimal(borrow.getAmount(), borrow.getRate(), borrow.getPeriods());
		/*
		 * if (cache.exists(key)) { return cache.get(key, RepayPlanBean.class);
		 * }
		 */
		List<JSONObject> jsonObjects = AverageCapitalUtils.getPerMonthPrincipalInterest(
				borrow.getAmount().doubleValue(), new Date(), borrow.getRate().doubleValue(), borrow.getPeriods());
		List<BorrowRepayBean> plans = new ArrayList<>();
		RepayPlanBeanBigDecimal planBean = new RepayPlanBeanBigDecimal();
		ClBorrow borrowBo = borrowService.getBorrow(borrow.getId());
		if (borrowBo != null) {
			planBean.setBorrow_date(borrowBo.getLoanTime());
		}
		planBean.setAmount(new BigDecimal(borrow.getAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		planBean.setRate(borrow.getRate());
		planBean.setPeriods(jsonObjects.size());
		planBean.setEnd_repay_date(new Date());
		planBean.setFirst_repay_date(new Date());
		planBean.setUnRepay_periods(borrow.getPeriods());
		planBean.setRepayAmount(this.mapper.clacRepayAmount(borrow.getId()));
		for (JSONObject jsonObject : jsonObjects) {
			Date date = jsonObject.getDate("date");
			if (1 == jsonObject.getIntValue("seq")) {
				planBean.setFirst_repay_amount(new BigDecimal(FsUtils.roundDouble(jsonObject.getDouble("dayRes"), 2))
						.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				planBean.setCurrent_repay_amount(new BigDecimal(FsUtils.roundDouble(jsonObject.getDouble("dayRes"), 2))
						.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				planBean.setCurrent_repay_date(date);
			}
			if (FsUtils.compare_Day(planBean.getEnd_repay_date(), date) <= 0) {
				planBean.setEnd_repay_date(date);
			}
			planBean.setInterest(new BigDecimal(
					FsUtils.roundDouble(FsUtils.addDouble(planBean.getInterest(), jsonObject.getDouble("interest")), 2))
							.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			BorrowRepayBean repay = new BorrowRepayBean();
			repay.setRepayDate(date);
			repay.setAmount(new BigDecimal(FsUtils.roundDouble(
					FsUtils.addDouble(jsonObject.getDouble("monthPri"), jsonObject.getDouble("interest")), 2))
							.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			repay.setInterest(new BigDecimal(FsUtils.roundDouble(jsonObject.getDouble("interest"), 2))
					.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			repay.setRealAmount(new BigDecimal(FsUtils.roundDouble(jsonObject.getDouble("monthPri"), 2))
					.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			repay.setRealAmountBalance(new BigDecimal(FsUtils.roundDouble(jsonObject.getDouble("balance"), 2))
					.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			plans.add(repay);
		}
		planBean.setPlans(plans);
		planBean.setTotal_amount(
				new BigDecimal(FsUtils.roundDouble(FsUtils.addDouble(planBean.getAmount(), planBean.getInterest()), 2))
						.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		planBean.setUnRepay_amount(
				new BigDecimal(planBean.getTotal_amount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		// Date endDate =
		// FsUtils.addDate(FsUtils.date(FsUtils.getLastDayOfMonth(new Date())),
		// -3);
		Long expire = FsUtils.getDateSpan(new Date(), FsUtils.addDate(new Date(), 7));
		// if (FsUtils.compare_Day(endDate, new Date()) >= 0) {
		// expire = FsUtils.getDateSpan(endDate, new Date());
		// }
		cache.set(key, expire, planBean);
		return planBean;
	}


	private String getRepayPlanKeyBigDecimal(Double amount, BigDecimal rate, int periods) {
		int day = FsUtils.i(FsUtils.formatDateTime(new Date(), "dd"));
		String nday = FsUtils.formatDateTime(new Date(), "MM");
		if (day <= 28) {
			nday = nday + 28;
		} else {
			day = FsUtils.i(FsUtils.formatDateTime(new Date(), "MMdd"));
		}

		String key = "repayPlan:date:" + day + ":amount:" + amount + ":rate:" + rate + ":periods:" + periods;
		return key;
	}

	/** 
	 * 
	 * 
	 */
	private RepayPlanBeanBigDecimal getRepayPlanBigDecimal(Long borrowId) {
		List<ClBorrowRepay> repays = getBorrowRepay(borrowId);
		List<BorrowRepayBean> beans = new ArrayList<>();
		ClBorrow borrow = borrowService.getBorrow(borrowId);
		List<CLWContacts> clwContactsList = clwContactService.getAllContactsByPhone(borrow.getMobile());
		CLWContacts clwContacts = null;
		if (!ParamUtil.isEmpty(clwContactsList) && clwContactsList.size() > 0) {
			clwContacts = clwContactsList.get(0);
		}

		RepayPlanBeanBigDecimal planBean = new RepayPlanBeanBigDecimal();
		// 校验是否都已还款,如果是，就不展示
		int successCount = 0;
		for (ClBorrowRepay repay : repays) {
			if (repay.getState().equals(RepayStateConstant.PAIED)) {
				successCount++;
			}
			if (successCount == repays.size()) {
				// 返回空即可
				logger.info("已全部还款，borrowId:{}", borrowId);
				return null;
			}
		}

		if (FsUtils.strsNotEmpty(borrow)) {
			planBean.setBorrow_date(borrow.getLoanTime());
		}
		planBean.setRepayAmount(this.mapper.clacRepayAmount(borrowId));
		planBean.setEnd_repay_date(new Date());
		planBean.setFirst_repay_date(FsUtils.addMonth(new Date(), 2));
		planBean.setBorrowId(borrowId);
		planBean.setPeriods(borrow.getPeriods());
		planBean.setAmount(borrow.getAmount().doubleValue());
		for (ClBorrowRepay repay : repays) {
			if (repay.getState().equals(RepayStateConstant.PRE_PAY)) {
				continue;
			}
			if (FsUtils.compare_Day(planBean.getFirst_repay_date(), repay.getRepayTime()) >= 0) {
				planBean.setFirst_repay_date(borrow.getCreateTime());
				planBean.setFirst_repay_amount(repay.getTotalAmount());
			}
			if (FsUtils.compare_Day(planBean.getEnd_repay_date(), repay.getRepayTime()) <= 0) {
				planBean.setEnd_repay_date(repay.getRepayTime());
			}
			if (repay.getState().equals(RepayStateConstant.PAIED)) {
				planBean.setHasRepay_periods(planBean.getHasRepay_periods() + 1);
			} else {
				if (FsUtils.strsEmpty(planBean.getCurrent_repay_amount()) || 0D == planBean.getCurrent_repay_amount()) {
					// 当期应还款金额
					BigDecimal current = repay.getRealAmount().add(repay.getInterest().add(repay.getPenaltyAmout()));
					planBean.setCurrent_repay_amount(
							(ParamUtil.isEmpty(current) ? BigDecimal.ZERO.setScale(2) : current).doubleValue());
					planBean.setCurrent_repay_date(repay.getRepayTime());
					planBean.setCurrent_repay_id(repay.getId());
					planBean.setFirst_repay_id(repay.getId());
				}
				// 未还款期数
				planBean.setUnRepay_periods(planBean.getUnRepay_periods() + 1);
				// 未还款本金
				planBean.setUnRepay_amount(
						FsUtils.addDouble(planBean.getUnRepay_amount(), repay.getAmount().doubleValue()));
			}
			// TODO: 2018/1/8 利息
			planBean.setInterest(repay.getInterest().doubleValue());
			planBean.setRate(new BigDecimal(ParamUtil.isEmpty(clwContacts) ? String.valueOf(borrow.getRate())
					: String.valueOf(clwContacts.getBorrowRate())));
			BorrowRepayBean bean = new BorrowRepayBean();
			bean.setId(repay.getId());
			bean.setRate(ParamUtil.isEmpty(clwContacts) ? String.valueOf(borrow.getRate())
					: String.valueOf(clwContacts.getBorrowRate()));
			// 利息
			bean.setInterest(repay.getInterest().doubleValue());
			// 应还款金额 +逾期费用
			bean.setAmount(FsUtils.addNumber(repay.getAmount(), repay.getPenaltyAmout()).doubleValue());
			bean.setBorrowId(repay.getBorrowId());
			bean.setSeq(repay.getSeq());
			bean.setState(repay.getState());
			bean.setRepayDate(repay.getRepayTime());
			bean.setId(repay.getId());
			bean.setPenaltyAmout(repay.getPenaltyAmout().doubleValue());
			bean.setPenaltyDay(repay.getPenaltyDay());
			bean.setRealAmountBalance(repay.getRealAmountBalance().doubleValue());
			bean.setRealAmount(repay.getRealAmount().doubleValue());
			bean.setRepayAmount(repay.getRepayAmount().doubleValue());
			ClBankCard card = bankCardService.getBankCard(repay.getUserId());
			if (FsUtils.strsNotEmpty(card)) {
				planBean.setBankCardNo(card.getBank() + " 尾号"
						+ card.getCardNo().substring(card.getCardNo().length() - 4, card.getCardNo().length()));
			}
			beans.add(bean);
		}
		planBean.setPlans(beans);
		planBean.setTotal_amount(FsUtils.addDouble(planBean.getAmount(), planBean.getInterest()));
		// 如果全还完，则返回下列参数初始化
		if (FsUtils.strsEmpty(planBean.getCurrent_repay_amount()) || 0D == planBean.getCurrent_repay_amount()) {
			// 当期应还款金额
			ClBorrowRepay lastRepay = repays.get(repays.size() - 1);
			planBean.setCurrent_repay_amount(BigDecimal.ZERO.setScale(2).doubleValue());
			planBean.setCurrent_repay_date(lastRepay.getRepayTime());
			planBean.setCurrent_repay_id(lastRepay.getId());
			planBean.setFirst_repay_id(lastRepay.getId());
		}
		return planBean;
	}

	@Override
	public List<RepayPlanBeanBigDecimal> getRepayPlanBig(Long userId) {
		List<ClBorrow> borrows = borrowService.getBorrowLoanSuccess(userId);
		List<RepayPlanBeanBigDecimal> beans = new ArrayList<>();
		for (ClBorrow borrow : borrows) {
			RepayPlanBeanBigDecimal repayPlanBeanBigDecimal = getRepayPlanBigDecimal(borrow.getId());
			if (!ParamUtil.isEmpty(repayPlanBeanBigDecimal)) {
				beans.add(repayPlanBeanBigDecimal);
			}
		}
		return beans;
	}
	
}
