package com.hwc.framework.modules.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hwc.base.sdk.core.Client;
import com.hwc.framework.modules.dao.ClOverdueFineMapper;
import com.hwc.framework.modules.model.*;
import com.hwc.loan.sdk.borrow.request.BaofuRepayRequest;
import com.hwc.loan.sdk.borrow.response.BaofuRepayResponse;
import com.hwc.loan.sdk.user.request.UserBaseInfoGetOneRequest;
import com.hwc.loan.sdk.user.request.UserUpdateStateRequest;
import com.hwc.loan.sdk.user.request.UserValidateTradePwdRequest;
import com.hwc.loan.sdk.user.response.UserBaseInfoGetOneResponse;
import com.hwc.loan.sdk.user.response.UserUpdateStateResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.hwc.base.api.Response;
import com.hwc.framework.common.Constant;
import com.hwc.framework.modules.bo.RepayDataResultBo;
import com.hwc.framework.modules.bo.RepayQueryDataBo;
import com.hwc.framework.modules.bo.RepayQueryDataResultBo;
import com.hwc.framework.modules.bo.RepayQueryResponseBo;
import com.hwc.framework.modules.bo.request.RepayQueryParamsRequestBo;
import com.hwc.framework.modules.bo.request.RepayQueryRequestBo;
import com.hwc.framework.modules.dao.CLBorrowMapper;
import com.hwc.framework.modules.dao.CLBorrowRepayMapper;
import com.hwc.framework.modules.dao.SmsTplMapper;
import com.hwc.framework.modules.domain.DebitBean;
import com.hwc.framework.modules.domain.EarlyRepayBean;
import com.hwc.framework.modules.domain.PreRepayInterestAndBreakFine;
import com.hwc.framework.modules.service.ArcCreditService;
import com.hwc.framework.modules.service.ArcSysConfigService;
import com.hwc.framework.modules.service.BorrowRepayLogService;
import com.hwc.framework.modules.service.BorrowRepayService;
import com.hwc.framework.modules.service.BorrowRepayThirdService;
import com.hwc.framework.modules.service.BorrowService;
import com.hwc.framework.modules.service.BorrowThirdService;
import com.hwc.framework.utils.DateUtil;
import com.hwc.framework.utils.Des;
import com.hwc.framework.utils.GsonUtil;
import com.hwc.framework.utils.HttpClientUtils;
import com.hwc.framework.utils.JSONObjectUtil;
import com.hwc.framework.utils.ParamUtil;
import com.hwc.framework.utils.RSAUtil;

import cn.freesoft.utils.FsUtils;
import net.sf.json.JSONObject;

/**
 * Created by jzl on 2017/12/25.
 */
@Service
public class BorrowRepayServiceImpl implements BorrowRepayService {
	private static Logger logger = LoggerFactory.getLogger(BorrowRepayServiceImpl.class);
	@Autowired
	private CLBorrowRepayMapper borrowRepayMapper;
	@Autowired
	private CLBorrowMapper borrowMapper;
	@Autowired
	private ClOverdueFineMapper overdueFineMapper;
	@Autowired
	private BorrowService borrowService;
	@Autowired
	private BorrowRepayService borrowRepayService;
	@Autowired
	private BorrowRepayLogService repayLogService;
	@Autowired
	private BorrowRepayThirdService repayThirdService;
	@Autowired
	private BorrowThirdService borrowThirdService;
	@Autowired
	private ArcCreditService arcCreditService;

	@Autowired
	private SmsTplMapper smsTplMapper;
	@Autowired
	private RestTemplate restTemplete;
	@Autowired
	RestTemplate borrowTemplate;

	@Value("${expire.day}")
	private Integer days;
	@Value("${query.repay.url}")
	private String QUERY_REPAY_URL;
	@Value("${overdue.rate}")
	private Double overdueRate;

	@Autowired
	private Client userClient;

	@Autowired
	private Client borrowClient;
	/**
	 * 还款计划
	 * 
	 * @param borrowModel
	 */
	@Override
	public void handleBorrowRepayPlan(CLBorrow borrowModel) {
		// amount,rate periods,time_limit
		/**
		 * 1.利率为日利率，可在config表里配
		 * configService.getConfigDefault("credit_default_rate", "0.05");
		 * 2.若分期，time_limit/periods,为一个还款阶段 3.利息计算 （本金-已还款金额）* 本期天数 * 日利率
		 */
		if (ParamUtil.isEmpty(borrowModel.getPeriods()) || 1 == borrowModel.getPeriods()) {
			// 不分期
			CLBorrowRepay repay = fillBorrowRepayByBorrow(borrowModel);
			borrowRepayMapper.insertSelective(repay);
		} else {
			// 分期
			handlePeriodsRepayPlan(borrowModel);
		}
	}

	/****************************************************************************/

	/**
	 * 处理分期
	 * 
	 * @param borrowModel
	 */
	private void handlePeriodsRepayPlan(CLBorrow borrowModel) {
		int counts = borrowModel.getPeriods();
		BigDecimal subAmount = BigDecimal.ZERO;
		for (int i = 1; i <= counts; i++) {
			CLBorrowRepay repay = new CLBorrowRepay();
			repay.setUserId(borrowModel.getUserId());
			repay.setBorrowId(borrowModel.getId());
			BigDecimal realAmount = caculatePrinciple(borrowModel.getAmount(), counts);
			repay.setRealAmount(realAmount);
			/**
			 * 如果分期等额本金之和小于借款本金，则第一期本金=总本金-后几期本金之和
			 */
			if (borrowModel.getAmount().compareTo(realAmount.multiply(new BigDecimal(counts))) > 0) {
				if (i == 1) {
					repay.setRealAmount(
							borrowModel.getAmount().subtract(realAmount.multiply(new BigDecimal(counts - 1))));
				}
			}
			subAmount = subAmount.add(realAmount);
			repay.setRate(borrowModel.getRate());
			// repay.setRealAmountBalance(caculateAmountBalance(borrowModel.getAmount(),
			// counts, i));
			repay.setRealAmountBalance(borrowModel.getAmount().subtract(subAmount));
			repay.setSeq(i);
			repay.setState("20"); // 未还款
			repay.setCreateTime(new Date());
			// 得出应还款日期
			// repay.setRepayTime(DateUtil.getSomeDayNight(new Date(),
			// (days/counts) * i));
			Date repayDate = DateUtil.getDayEndTime(FsUtils.getInstanceOfDay(FsUtils.addMonth(new Date(), i)));
			repay.setRepayTime(repayDate);
			// 计算分期利息
			// BigDecimal interest =
			// caculatePeriodsInterest(borrowModel.getAmount(),
			// borrowModel.getRate(), days, counts, i);
			Date lastRepayDate = FsUtils.getInstanceOfDay(FsUtils.addMonth(new Date(), i - 1));
			int useDays = Math.abs(DateUtil.daysBetween(lastRepayDate, repayDate));
			BigDecimal interest = borrowModel.getAmount().multiply(borrowModel.getRate())
					.multiply(new BigDecimal(useDays)).setScale(2, BigDecimal.ROUND_HALF_UP);
			repay.setInterest(interest);
			borrowRepayMapper.insertSelective(repay);
		}

	}

	/**
	 * 计算分期利息 返回本期利息
	 * 
	 * @param amount
	 * @param rate
	 * @param timeLimit
	 * @param counts
	 * @param seq
	 * @return
	 */
	private BigDecimal caculatePeriodsInterest(BigDecimal amount, BigDecimal rate, int timeLimit, int counts, int seq) {
		BigDecimal averagePrinciple = caculatePrinciple(amount, counts);
		BigDecimal interestPrinciple = amount.subtract(averagePrinciple.multiply(new BigDecimal(seq - 1)));
		BigDecimal interest = interestPrinciple.multiply(rate.divide(new BigDecimal(100)))
				.multiply(new BigDecimal(timeLimit / counts));
		return interest;
	}

	/**
	 * 计算剩余本金
	 * 
	 * @param amount
	 *            本金
	 * @param periods
	 *            分几期
	 * @param seq
	 *            第几期
	 * @return
	 */
	private BigDecimal caculateAmountBalance(BigDecimal amount, int periods, int seq) {
		BigDecimal averageAmount = caculatePrinciple(amount, periods);

		return amount.subtract(averageAmount.multiply(new BigDecimal(seq))).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 计算分期等额本金
	 * 
	 * @param amount
	 * @param periods
	 * @return
	 */
	private BigDecimal caculatePrinciple(BigDecimal amount, int periods) {
		return amount.divide(new BigDecimal(periods)).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * @param borrowModel
	 * @return
	 */
	private CLBorrowRepay fillBorrowRepayByBorrow(CLBorrow borrowModel) {
		CLBorrowRepay repay = new CLBorrowRepay();
		repay.setUserId(borrowModel.getUserId());
		repay.setBorrowId(borrowModel.getId());
		repay.setRealAmount(borrowModel.getAmount());
		repay.setRate(borrowModel.getRate());
		repay.setRealAmountBalance(borrowModel.getAmount());
		repay.setSeq(1);
		repay.setState("20"); // 未还款
		repay.setCreateTime(new Date());
		repay.setRepayTime(DateUtil.getSomeDayNight(new Date(), Integer.parseInt(borrowModel.getTimeLimit())));
		BigDecimal interest = caculateInterest(borrowModel.getAmount(), borrowModel.getRate(),
				borrowModel.getTimeLimit());
		repay.setInterest(interest);
		return repay;
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

	/**
	 * 使用宝付还款,请求服务即可
	 * 
	 * @param pwd
	 * @param borrowId
	 * @param repayIds
	 * @param type
	 *            1:当期还款 2:提前还款
	 * @return
	 */
	@Override
	public Response toBaoFuPay(String type, String pwd, String borrowId, String repayIds) {
		switch (type) {
		case "1":
			return payThisSeq(repayIds, pwd);
		case "2":
			return payOtherSeqs(borrowId, pwd);
		default:
			return Response.fail("无此类型");
		}
	}

	/**
	 * 提前还款请求
	 * 
	 * @param borrowId
	 * @param pwd
	 * @return
	 */
	private Response payOtherSeqs(String borrowId, String pwd) {
		String url = "http://loan-borrow/api/pay/preRepayment";
		EarlyRepayBean request = new EarlyRepayBean();
		request.setBorrowId(Long.valueOf(borrowId));
		request.setTradePassword(pwd);
		request.setType(1); // 信用
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<EarlyRepayBean> entity = new HttpEntity<>(request, headers);
		ResponseEntity<Response> responseEntity = borrowTemplate.exchange(url, HttpMethod.POST, entity, Response.class);
		Response response = responseEntity.getBody();
		return response;
	}

	/**
	 * 本期还款请求
	 * 
	 * @param repayIds
	 * @param pwd
	 * @return
	 */
	private Response payThisSeq(String repayIds, String pwd) {
		String url = "http://loan-borrow/api/pay/repayment";
		DebitBean request = new DebitBean();
		request.setRepayId(Long.valueOf(repayIds));
		request.setTradePassword(pwd);
		request.setType(1); // 信用
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<DebitBean> entity = new HttpEntity<>(request, headers);
		ResponseEntity<Response> responseEntity = borrowTemplate.exchange(url, HttpMethod.POST, entity, Response.class);
		Response response = responseEntity.getBody();
		return response;
	}

	// /**
	// * 通知（逾期 到期 ）
	// */
	// @Override
	// public ClQuartzInfo notity() {
	// ClQuartzInfo info = new ClQuartzInfo();
	// Map<String, Object> map = new HashMap<>();
	// Date date = FsUtils.addDate(new Date(), days);
	// //Date dayStartTime = DateUtil.getDayStartTime(date);
	// Date dayEndTime = DateUtil.getDayEndTime(date);
	// //map.put("start", DateUtil.getAllDate(dayStartTime));
	// map.put("end", DateUtil.getAllDate(dayEndTime));
	// List<CLBorrowRepay> AllBorrowRepay =
	// borrowRepayMapper.getUnRepayList(map);
	// if (AllBorrowRepay == null || AllBorrowRepay.isEmpty()) {
	// logger.info("本批次无需要通知记录，计算终止");
	// info.setSucceed(0);
	// info.setFail(0);
	// return info;
	// }
	// Map<Long, Long> map1 = new HashMap<>();
	// int succeed = 0;
	// int fail = 0;
	// for (CLBorrowRepay repay : AllBorrowRepay) {
	// try {
	// //计算相差天数
	// int daysBetween= DateUtil.daysBetween(new Date(), repay.getRepayTime());
	// if (map1.containsKey(repay.getBorrowId())) {
	// continue;
	// }
	// CLBorrow borrow = borrowService.getBorrow(repay.getBorrowId());
	// if (FsUtils.strsEmpty(borrow)){
	// continue;
	// }
	// map1.put(borrow.getId(), borrow.getId());
	// //逾期
	// if(daysBetween>0){
	// boolean overdue = overdue(repay, daysBetween, borrow);
	// if(overdue){
	// succeed++;
	// }
	// }else
	// if(Math.abs(daysBetween)==days||repay.getIsNotityUser().equals("10")){
	// //到期通知
	// String loan = repay.getRealAmount()+"";
	// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// String repayData =sdf.format(repay.getRepayTime());
	// String mobile = borrow.getMobile();
	// SmsTpl selectByPrimaryKey = smsTplMapper.selectByPrimaryKey(11);
	// String typeName = selectByPrimaryKey.getTpl().replace("{$time}", "财位")
	// .replace("{$loan}", loan)
	// .replace("{$time1}",DateUtil.getDate(repay.getRepayTime()));
	// //尊敬的客户,您在{$time}借款{$loan}元,最后还款日期为{$time}，请及时还款！
	// String url =
	// "http://loan-notify/api/gexin/repayExpire?loan="+loan+"&repayData="+repayData+"&mobile="+mobile;
	// String forObject = restTemplete.getForObject(url, String.class);
	// System.out.println(forObject);
	//
	// boolean sendMsg = SmsUtil.sendMsg(mobile, typeName);
	// if(sendMsg){
	// succeed++;
	// CLBorrowRepay newRepay = new CLBorrowRepay();
	// newRepay.setIsNotityUser("20");//已通知标识
	// newRepay.setId(repay.getId());
	// int updateIsNotityUser =
	// borrowRepayMapper.updateByPrimaryKeySelective(newRepay);
	// if(updateIsNotityUser==0){
	// logger.info("到期 通知标识修改失败！");
	// }
	// }else{
	// logger.info("到期通知短信发送失败！");
	// fail++;
	// }
	// }
	// } catch (Exception ex) {
	// logger.error("通知错误:", ex);
	// fail++;
	// }
	// }
	// info.setSucceed(succeed);
	// info.setFail(fail);
	// return info;
	// }
	// /**
	// * 计算逾期金额(剩余所有未还款金额*overdueRate*逾期天数) ， 并修改当期订单
	// * @param repay
	// * @param daysBetween
	// * @param borrow
	// */
	// public boolean overdue(CLBorrowRepay repay,int daysBetween,CLBorrow
	// borrow){
	// boolean state = true;
	// BigDecimal total =
	// repay.getRealAmount().add(repay.getRealAmountBalance());//剩余总金额
	// BigDecimal rate1 = new BigDecimal(Double.toString(overdueRate));//逾期利率
	// BigDecimal overdueFee = total.multiply(rate1).setScale(2,
	// BigDecimal.ROUND_HALF_UP); //日逾期罚金
	// BigDecimal day = new BigDecimal(daysBetween);
	// BigDecimal overdueFeeAll = overdueFee.multiply(day).setScale(2,
	// BigDecimal.ROUND_HALF_UP);//总逾期金额
	// CLBorrowRepay newRepay = new CLBorrowRepay();
	// newRepay.setPenaltyDay(daysBetween);
	// newRepay.setPenaltyAmout(overdueFeeAll);
	// newRepay.setId(repay.getId());
	// //修改逾期数据
	// int updateByPrimaryKeySelective =
	// borrowRepayMapper.updateByPrimaryKeySelective(newRepay);
	// if(updateByPrimaryKeySelective==0){
	// logger.info("修改逾期数据失败");
	// state = false;
	// }
	// //逾期通知 平台 总 当期
	// //尊敬的用户,您在{$platform}借款{$loan}元,还款时间为{$time},现已逾期{$overdueDay}天,逾期罚金为{$amercement}元,请尽快还款!
	// SmsTpl selectByPrimaryKey = smsTplMapper.selectByPrimaryKey(5);
	// String loan = borrow.getAmount().toString();
	// String mobile = borrow.getMobile();
	// String repayData = DateUtil.getDate(repay.getRepayTime());
	// String money = overdueFee.toString();
	// String overdueDay = String.valueOf(daysBetween);
	// String amercement = overdueFeeAll.toString();
	// String typeName = selectByPrimaryKey.getTpl().replace("{$platform}",
	// "财位")
	// .replace("{$loan}", loan)
	// .replace("{$time}", repayData)
	// .replace("{$overdueDay}", overdueDay)
	// .replace("{$amercement}", amercement );
	// String url =
	// "http://loan-notify/api/gexin/overdue?loan="+loan+"&repayData="+repayData+"&mobile="+mobile+"&money="+money;
	// String forObject = restTemplete.getForObject(url, String.class);
	// System.out.println(forObject);
	// //短信通知
	// boolean sendMsg = SmsUtil.sendMsg(mobile, typeName);
	// if(sendMsg){
	// newRepay.setIsNotityUser("20");//已通知标识
	// int updateIsNotityUser =
	// borrowRepayMapper.updateByPrimaryKeySelective(newRepay);
	// if(updateIsNotityUser==0){
	// logger.info("通知状态修改失败");
	// state = false;
	// }
	// }else{
	// state = false;
	// }
	// return state;
	// }

	/**
	 * 计算逾期方法
	 */
	@Override
	public ClQuartzInfo clacOverdue() {
		ClQuartzInfo info = new ClQuartzInfo();
		List<Long> overdueRepays = getOverdueRepays();
		if (overdueRepays == null || overdueRepays.isEmpty()) {
			logger.info("本批次无逾期记录，逾期计算终止");
			info.setSucceed(0);
			info.setFail(0);
			return info;
		}
		int succeed = 0;
		int fail = 0;
		CLBorrowRepay repay;
		CLBorrowRepay repayNext;
		CLBorrowRepay newRepay;
		int days;
		int daysBetween = 0;
		BigDecimal rate1 = new BigDecimal(Double.toString(overdueRate));
		BigDecimal overdueFee;
		BigDecimal overdueFeeAll;
		BigDecimal overdueFeeLast;
		BigDecimal total;
		for (Long borrowId : overdueRepays) {
			try {
				CLBorrow borrow = borrowService.getBorrow(borrowId);
				if (FsUtils.strsEmpty(borrow)) {
					continue;
				}
				if (borrow.getOverdueTime() != null && DateUtil.daysBetween(new Date(), borrow.getOverdueTime()) == 0) {
					logger.info("订单：" + borrow.getId() + "   逾期已计算，此订单逾期计算终止");
					continue;
				}
				Map<String, Object> map = new HashMap<>();
				map.put("borrowId", String.valueOf(borrowId));
				map.put("end", DateUtil.getAllDate(DateUtil.getDayStartTime(new Date())));
				repay = borrowRepayMapper.loadOverdue(map);
				map.put("seq", repay.getSeq() + 1);
				repayNext = borrowRepayMapper.getOverdue(map);
				newRepay = new CLBorrowRepay();
				if (repayNext == null) {
					days = DateUtil.daysBetween(new Date(), repay.getRepayTime());
					newRepay.setId(repay.getId());
					newRepay.setPenaltyDay(days);
					overdueFeeLast = repay.getPenaltyAmout();
				} else {
					// 下期逾期时长
					daysBetween = DateUtil.daysBetween(new Date(), repayNext.getRepayTime());
					if (daysBetween > 0) {
						// 如果当前时间大于下期还款时间，说明已经开始下一期累积逾期，上一期逾期计算停止
						newRepay.setId(repayNext.getId());
						newRepay.setPenaltyDay(daysBetween);
						days = daysBetween;
						overdueFeeLast = new BigDecimal(0);
					} else {
						// 上一期逾期计算
						newRepay.setId(repay.getId());
						days = DateUtil.daysBetween(new Date(), repay.getRepayTime());
						newRepay.setPenaltyDay(days);
						overdueFeeLast = repay.getPenaltyAmout();
					}

				}
				// 查询用户状态，若userBaseinfo里state是20不在白名单，就将用户拉黑
				UserBaseInfoGetOneRequest userBaseInfoGetOneRequest = new UserBaseInfoGetOneRequest();
				userBaseInfoGetOneRequest.setId(borrow.getUserId());
				UserBaseInfoGetOneResponse userBaseInfoGetOneResponse = userClient.invoke(userBaseInfoGetOneRequest);
				if (null != userBaseInfoGetOneResponse.getData()) {
					if ("20".equals(userBaseInfoGetOneResponse.getData().getState())) {
						UserUpdateStateRequest userUpdateStateRequest = new UserUpdateStateRequest();
						userUpdateStateRequest.setId(FsUtils.l(borrow.getUserId()));
						userUpdateStateRequest.setState("40");
						UserUpdateStateResponse userUpdateStateResponse = userClient.invoke(userUpdateStateRequest);

					}
				}

				total = new BigDecimal(0);
				map.put("end", null);
				map.put("seq", null);
				List<CLBorrowRepay> overdueRepay = borrowRepayMapper.getUnRepayList(map);
				for (CLBorrowRepay borrowRepay : overdueRepay) {
					total = total.add(borrowRepay.getRealAmount()).add(borrowRepay.getInterest());
				}
				overdueFee = total.multiply(rate1).setScale(2, BigDecimal.ROUND_UP);
				overdueFeeAll = overdueFee.add(overdueFeeLast);// 总逾期金额
				newRepay.setPenaltyAmout(overdueFeeAll);
				logger.info("订单{}分期{}", borrowId, newRepay.getId());
				logger.info("0-总基数{}", total);
				logger.info("1-罚金利率{}", rate1);
				logger.info("2-总天数{}", days);
				logger.info("3-总罚息{}", overdueFeeAll);
				borrowRepayMapper.updateByPrimaryKeySelective(newRepay);
				if (daysBetween > 0) {
					newRepay = new CLBorrowRepay();
					newRepay.setId(repay.getId());
					newRepay.setOverdue(1);
					borrowRepayMapper.updateByPrimaryKeySelective(newRepay);
				}
				borrow.setOverdueTime(new Date());
				borrowMapper.updateByPrimaryKeySelective(borrow);
				CLOverdueFineLog log = new CLOverdueFineLog();
				log.setCreateTime(new Date());
				log.setBorrowId(borrowId);
				log.setDay(days);
				log.setFine(overdueFee);
				log.setAmount(overdueFeeAll);
				log.setRepayId(newRepay.getId());
				log.setTotal(total);
				log.setRate(rate1);
				overdueFineMapper.insert(log);
				try {
					// 逾期通知 平台 总 当期
					// 尊敬的用户,您在{$platform}借款{$loan}元,还款时间为{$time},现已逾期{$overdueDay}天,逾期罚金为{$amercement}元,请尽快还款!
					SmsTpl selectByPrimaryKey = smsTplMapper.selectByPrimaryKey(5);
					String loan = borrow.getAmount().toString();
					String mobile = borrow.getMobile();
					String repayData = DateUtil.getDate(repay.getRepayTime());
					String money = overdueFee.toString();
					String overdueDay = String.valueOf(daysBetween);
					String amercement = overdueFeeAll.toString();
					String typeName = selectByPrimaryKey.getTpl().replace("{$platform}", "财位").replace("{$loan}", loan)
							.replace("{$time}", repayData).replace("{$overdueDay}", overdueDay)
							.replace("{$amercement}", amercement);
					String url = "http://loan-notify/api/gexin/overdue?loan=" + loan + "&repayData=" + repayData
							+ "&mobile=" + mobile + "&money=" + money;
					restTemplete.getForObject(url, String.class);
				} catch (Throwable throwable) {
					logger.error("逾期通知错误:", throwable);
				}
			} catch (Exception ex) {
				logger.error("计算逾期错误:", ex);
				fail++;
			}
		}
		info.setSucceed(succeed);
		info.setFail(fail);
		return info;
	}

	/**
	 * 快到期提醒 默认提前 days 天
	 *
	 * @return
	 */
	@Override
	public ClQuartzInfo getSoonExpireRepayPlan() {
		ClQuartzInfo info = new ClQuartzInfo();
		List<CLBorrowRepay> repays = getBorrowRepays(1);
		if (repays == null || repays.isEmpty()) {
			logger.info("本批次无到期记录，到期计算终止");
			info.setSucceed(0);
			info.setFail(0);
			return info;
		}
		int succeed = 0;
		int fail = 0;
		for (CLBorrowRepay repay : repays) {
			// 到期通知
			try {
				if (repay.getIsNotityUser().equals("20")) {
					logger.info("分期订单：" + repay.getId() + "  到期通知已通知，此分期订单到期通知停止");
					continue;
				}
				CLBorrow borrow = borrowService.getBorrow(repay.getBorrowId());
				String loan = repay.getRealAmount().toString();
				String repayData = DateUtil.getDate(repay.getRepayTime());
				String mobile = borrow.getMobile();
				SmsTpl selectByPrimaryKey = smsTplMapper.selectByPrimaryKey(11);
				String typeName = selectByPrimaryKey.getTpl().replace("{$time}", "财位").replace("{$loan}", loan)
						.replace("{$time1}", repayData);
				// 尊敬的客户,您在{$time}借款{$loan}元,最后还款日期为{$time}，请及时还款！
				String url = "http://loan-notify/api/gexin/repayExpire?loan=" + loan + "&repayData=" + repayData
						+ "&mobile=" + mobile;
				restTemplete.getForObject(url, String.class);
				// 短信通知
				// boolean sendMsg = SmsUtil.sendMsg(mobile, typeName);
				// if(sendMsg){
				succeed++;
				CLBorrowRepay newRepay = new CLBorrowRepay();
				newRepay.setIsNotityUser("20");// 到期通知标识
				newRepay.setId(repay.getId());
				borrowRepayMapper.updateByPrimaryKeySelective(newRepay);
				// }else{
				// logger.info("到期通知短信发送失败");
				// fail++;
				// }
			} catch (Throwable e) {
				logger.error("到期通知报错", e);
				fail++;
			}
		}
		info.setSucceed(succeed);
		info.setFail(fail);
		return info;
	}

	private List<Long> getOverdueRepays() {
		Date dayEndTime = DateUtil.getDayStartTime(new Date());
		Map<String, Object> map = new HashMap<>();
		map.put("end", DateUtil.getAllDate(dayEndTime));
		return borrowRepayMapper.getOverdueList(map);
	}

	/**
	 * 查询分期订单
	 */
	public List<CLBorrowRepay> getBorrowRepays(int state) {
		Map<String, Object> map = new HashMap<>();
		Date dayEndTime = null;
		List<CLBorrowRepay> overdueRepays;
		switch (state) {

		case 1: // SoonExpireRepayPlan
			Date date = FsUtils.addDate(new Date(), days);
			dayEndTime = DateUtil.getDayEndTime(date);
			map.put("start", DateUtil.getAllDate(new Date()));
			map.put("end", DateUtil.getAllDate(dayEndTime));
			map.put("isNotityUser", "10");
			overdueRepays = borrowRepayMapper.getUnRepayList(map);
			break;
		default:// unknow
			overdueRepays = borrowRepayMapper.getUnRepayList(map);
			break;
		}
		return overdueRepays;
	}

	/**
	 * 查找还款计划集合
	 * 
	 * @param repayIds
	 * @return
	 */
	@Override
	public List<CLBorrowRepay> findBorrowRepayList(String repayIds) {
		List<CLBorrowRepay> repayList = new ArrayList<>();
		for (String repayIdStr : repayIds.split(",")) {
			long repayId = Long.valueOf(repayIdStr);
			CLBorrowRepay repay = borrowRepayMapper.selectByPrimaryKey(repayId);
			if (ParamUtil.isEmpty(repay)) {
				logger.info("查询还款计划订单时，有订单为空，及时返回，repayid:{}", repayId);
				return null;
			}
			repayList.add(repay);
		}
		return repayList;
	}

	/**
	 * 校验未还款状态
	 * 
	 * @param repayList
	 * @return
	 */
	@Override
	public boolean checkBorrowRepayUnRepay(List<CLBorrowRepay> repayList) {
		if (ParamUtil.isEmpty(repayList) || repayList.isEmpty()) {
			logger.info("校验未还款状态收到集合为空");
			return false;
		}

		for (CLBorrowRepay repay : repayList) {
			if (!Constant.BORROW_REPAY_STATE_UNPAY.equals(repay.getState())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 更新还款信息
	 * 
	 * @param resultBoList
	 * @return
	 */
	@Transactional
	@Override
	public boolean updateRepayInfos(List<RepayDataResultBo> resultBoList) {
		/**
		 * 更新与第三方记录 repay_third 更新还款记录 repay_log 更新还款计划表 borrow_repay 更新借款表
		 * borrow
		 */
		for (RepayDataResultBo resultBo : resultBoList) {
			repayLogService.updateRepayLogInfo(resultBo);
			repayThirdService.updateBorrowRepayThirdInfo(resultBo);
			borrowRepayService.updateBorrowRepayInfo(resultBo);
			borrowService.updateBorrowInfo(resultBo);
			// 还款成功，回复额度 status：状态:0成功 1失败
			if (resultBo.getStatus() == 0) {
				// repayId : stages
				CLBorrowRepay borrowRepay = borrowRepayService.findBorrowRepayById(
						ParamUtil.isEmpty(resultBo.getStages()) ? 0 : Long.valueOf(resultBo.getStages()));
				arcCreditService.increaseQuota(borrowRepay.getUserId(), Double.valueOf(resultBo.getRepayAmount()));
			}
		}
		return true;
	}

	/**
	 * 更新还款计划信息
	 * 
	 * @param resultBo
	 */
	@Override
	public void updateBorrowRepayInfo(RepayDataResultBo resultBo) {
		// amount,repay_amount,repay_time,state,
		CLBorrowRepay borrowRepay = new CLBorrowRepay();
		borrowRepay.setId(ParamUtil.isEmpty(resultBo.getStages()) ? 0 : Long.valueOf(resultBo.getStages()));
		borrowRepay.setAmount(ParamUtil.isEmpty(resultBo.getTotalAmount()) ? BigDecimal.ZERO
				: new BigDecimal(resultBo.getTotalAmount()));
		borrowRepay.setInterest(
				ParamUtil.isEmpty(resultBo.getInterest()) ? BigDecimal.ZERO : new BigDecimal(resultBo.getInterest()));
		borrowRepay.setPenaltyAmout(ParamUtil.isEmpty(resultBo.getOverdueFine()) ? BigDecimal.ZERO
				: new BigDecimal(resultBo.getOverdueFine()));
		borrowRepay.setRepayAmount(ParamUtil.isEmpty(resultBo.getTotalAmount()) ? BigDecimal.ZERO
				: new BigDecimal(resultBo.getTotalAmount()));
		borrowRepay.setRepayTime(new Date());
		// 还款状态 10-已还款 20-未还款,30 部分还款，40还款中
		borrowRepay.setState(0 == resultBo.getStatus() ? "10"
				: 1 == resultBo.getStatus() ? "20" : 2 == resultBo.getStatus() ? "40" : "50");
		borrowRepayMapper.updateByPrimaryKeySelective(borrowRepay);
	}

	@Override
	public CLBorrowRepay findBorrowRepayById(Long repayId) {
		return borrowRepayMapper.selectByPrimaryKey(repayId);
	}

	/**
	 * 获取已还款的期数
	 * 
	 * @param
	 * @return
	 */
	@Override
	public int getAlreadyRepayCountsByBorrowId(Long borrowId) {
		return borrowRepayMapper.getAlreadyRepayCountsByBorrowId(borrowId);
	}

	/**
	 * 查询还款中的订单,待查询
	 * 
	 * @return
	 */
	@Override
	public List<CLBorrowRepay> queryNeedQueryOrders() {
		return borrowRepayMapper.queryNeedQueryOrders();
	}

	/**
	 * 查询还款状态
	 * 
	 * @param repay
	 */
	@Override
	@Transactional
	public boolean queryRepay(CLBorrowRepay repay) throws Exception {
		// 打款流水号
		CLBorrowThird borrowThird = borrowThirdService.findOneByBorrowId(repay.getBorrowId());
		if (ParamUtil.isEmpty(borrowThird)) {
			return false;
		}

		// 还款流水号
		CLBorrowRepayThird repayThird = repayThirdService.findOneByBorrowRepayId(repay.getId());
		if (ParamUtil.isEmpty(repayThird)) {
			return false;
		}

		RepayQueryParamsRequestBo paramsRequestBo = new RepayQueryParamsRequestBo();
		paramsRequestBo.setPayMentTransaction(borrowThird.getThirdNo());
		paramsRequestBo.setRepaymentTransaction(repayThird.getThirdRepayNo());
		RepayQueryRequestBo requestBo = new RepayQueryRequestBo();
		requestBo.setSource("caiwei");
		requestBo.setParams(paramsRequestBo);
		requestBo.setSign(RSAUtil.encoderByMd5(JSONObject
				.fromObject(JSONObjectUtil.sortJsonObject(JSONObject.fromObject(paramsRequestBo))).toString()));

		logger.info("需要验签的排序串:{}", JSONObject
				.fromObject(JSONObjectUtil.sortJsonObject(JSONObject.fromObject(paramsRequestBo))).toString());
		logger.info("加密的请求串:{}", Des.encode(Des.secretKey, JSONObject.fromObject(requestBo).toString()));
		String result = HttpClientUtils.postJson(QUERY_REPAY_URL,
				Des.encode(Des.secretKey, JSONObject.fromObject(requestBo).toString()), "UTF-8", true);

		RepayQueryResponseBo responseBo = GsonUtil.GsonToBean(result, RepayQueryResponseBo.class);
		if (!"0".equals(responseBo.getCode())) {
			logger.info("还款查询返回 失败:message:{}", responseBo.getMessage());
			return false;
		}

		// 解密
		String decypt = Des.decode(Des.secretKey, responseBo.getData());
		if (ParamUtil.isEmpty(decypt)) {
			logger.info("还款查询返回解密错误");
			return false;
		}

		RepayQueryDataBo dataBo = GsonUtil.GsonToBean(decypt, RepayQueryDataBo.class);
		for (RepayQueryDataResultBo resultBo : dataBo.getResult()) {
			if (repayThird.getThirdRepayNo().equals(resultBo.getRepaymentTransaction())) {
				/**
				 * 开始更新
				 */
				RepayDataResultBo bo = new RepayDataResultBo();
				bo.setRepaymentTransaction(resultBo.getRepaymentTransaction());
				bo.setPayMentTransaction(resultBo.getPayMentTransaction());
				bo.setStages(String.valueOf(repay.getId()));
				bo.setRemark(resultBo.getRemark());
				bo.setStatus(resultBo.getStatus());
				bo.setRepayAmount(resultBo.getRepayAmount());
				bo.setTotalAmount(resultBo.getTotalAmount());
				bo.setInterest(resultBo.getInterest());
				bo.setOverdueFine(resultBo.getOverdueFine());

				repayThirdService.updateBorrowRepayThirdInfo(bo);
				repayLogService.updateRepayLogInfo(bo);
				borrowRepayService.updateBorrowRepayInfo(bo);
				borrowService.updateBorrowInfo(bo);
				// 回复额度
				if (bo.getStatus() == 0) {
					// repayId : stages
					// CLBorrowRepay borrowRepay =
					// borrowRepayService.findBorrowRepayById(ParamUtil.isEmpty(bo.getStages())
					// ? 0 : Long.valueOf(resultBo.getStages()));
					arcCreditService.increaseQuota(repay.getUserId(), Double.valueOf(bo.getRepayAmount()));
				}
			}
		}
		return true;
	}

	/**
	 * 修改为还款中
	 * 
	 * @param repayList
	 */
	@Override
	public void updateRepayInProgress(List<CLBorrowRepay> repayList) {
		for (CLBorrowRepay repay : repayList) {
			borrowRepayMapper.updateRepayInProgress(repay.getId());
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
	public PreRepayInterestAndBreakFine calcuPreRepayInterestAndBreakFine(CLBorrowRepay repay, CLBorrow borrow) {
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
			// int days = Math.abs(DateUtil.daysBetween(someday, new Date())) +
			// 1;
			// BigDecimal interest =
			// caculateInterest(repay.getRealAmount().add(repay.getRealAmountBalance()),
			// repay.getRate(), String.valueOf(days));
			// 直接取，不算提前
			interestAndBreakFine.setInterest(repay.getInterest());
			interestAndBreakFine.setBreakFine(BigDecimal.ZERO);
		}

		return interestAndBreakFine;
	}

	/**
	 * 组装需要还款的repayIds
	 * 
	 * @param userId
	 * @param type
	 *            1：当期还款 2：提前还款所有剩余的
	 * @return
	 */
	@Override
	public String structureRepayIds(Long userId, int type, Long borrowId) {
		List<CLBorrowRepay> repayList = findBorrowRepaysByUserIdAndBorrowId(userId, borrowId);
		if (ParamUtil.isEmpty(repayList) || repayList.isEmpty()) {
			logger.info("无此用户，或者该用户无待还款订单,uid:{}", userId);
			return null;
		}

		switch (type) {
		case 1:
			logger.info("本次请求为当期还款，uid:{}", userId);
			String repayId = String.valueOf(repayList.get(0).getId());
			return repayId;
		case 2:
			logger.info("本次请求为提前还款，uid:{}", userId);
			String repayIds = handleRepayListToRepayIds(repayList);
			return repayIds;
		default:
			return null;
		}
	}

	/**
	 * 处理repay集合，组装repayIds
	 * 
	 * @param repayList
	 * @return
	 */
	private String handleRepayListToRepayIds(List<CLBorrowRepay> repayList) {
		StringBuilder sb = new StringBuilder();
		for (CLBorrowRepay repay : repayList) {
			sb.append(repay.getId());
			sb.append(",");
		}
		// qu 最后一个逗号
		return sb.substring(0, sb.length() - 1).toString();
	}

	/**
	 * 查询当前用户需要还款的订单
	 * 
	 * @param userId
	 * @return
	 */
	private List<CLBorrowRepay> findBorrowRepaysByUserIdAndBorrowId(Long userId, Long borrowId) {

		return borrowRepayMapper.findBorrowRepaysByUserIdAndBorrowId(userId, borrowId);
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

	@Override
	public List<CLBorrowRepay> queryCanPayOrder(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return borrowRepayMapper.queryCanPayOrder(param);
	}
	
	
	@Override
	public String repay(CLBorrowRepay clBorrowRepay) {
		BaofuRepayRequest request = new BaofuRepayRequest();
		request.setRepayId(clBorrowRepay.getId());
		request.setType(1);
		BaofuRepayResponse response=borrowClient.invoke(request);
		return ""+response;
	}
}
