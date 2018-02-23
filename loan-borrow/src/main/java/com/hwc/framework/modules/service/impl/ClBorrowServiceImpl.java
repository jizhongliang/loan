package com.hwc.framework.modules.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.hwc.base.api.Response;
import com.hwc.base.exception.ServiceException;
import com.hwc.framework.common.BorrowStateConstant;
import com.hwc.framework.modules.dao.ClBorrowMapper;
import com.hwc.framework.modules.dao.ClBorrowProgressMapper;
import com.hwc.framework.modules.dao.ClPayLogMapper;
import com.hwc.framework.modules.domain.BorrowBean;
import com.hwc.framework.modules.domain.BorrowQueryRequest;
import com.hwc.framework.modules.domain.PayLogBean;
import com.hwc.framework.modules.model.CLWContacts;
import com.hwc.framework.modules.model.ClBorrow;
import com.hwc.framework.modules.model.ClBorrowProgress;
import com.hwc.framework.modules.model.ClPayLog;
import com.hwc.framework.modules.service.ArcCreditService;
import com.hwc.framework.modules.service.CLWContactService;
import com.hwc.framework.modules.service.ClBorrowService;
import com.hwc.framework.modules.service.ClCreditBorrowService;
import com.hwc.framework.modules.service.ClMortgageBorrowService;
import com.hwc.framework.modules.third.UserService;
import com.hwc.loan.sdk.user.domain.DCloanUserDomain;
import com.hwc.mybatis.core.AbstractService;
import com.hwc.mybatis.util.DataObjectConverter;
import com.hwc.mybatis.util.PageUtils;

import cn.freesoft.utils.FsUtils;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

/**
 * 2017/10/30. 借款統一入口
 */
@Service
public class ClBorrowServiceImpl extends AbstractService<ClBorrowMapper, ClBorrow> implements ClBorrowService {
	private static final Logger logger = LoggerFactory.getLogger(ClBorrowServiceImpl.class);
	@Autowired
    private CLWContactService         cLWContactService;
	@Autowired
	private UserService             userService;
	@Autowired
	private ArcCreditService arcCreditService;
	@Autowired
	private ClCreditBorrowService clCreditBorrowService;
	@Autowired
	private ClMortgageBorrowService clMortgageBorrowService;
	@Autowired
	private ClBorrowMapper clBorrowMapper;
	@Autowired
	private ClBorrowProgressMapper clBorrowProgressMapper;
	@Autowired
	private ClPayLogMapper clPayLogMapper;

	// @Autowired
	// private ClPayLogService clPayLogService;

	public ClBorrow getBorrow(Long borrow_id) {
		return this.findById(borrow_id);
	}

	@Override
	public BorrowBean getBorrowBean(Long borrow_id) {
		ClBorrow borrow = getBorrow(borrow_id);
		if (FsUtils.strsEmpty(borrow)) {
			throw new ServiceException("未找到订单记录");
		}
		BorrowBean bean = new BorrowBean();
		bean.setRate(borrow.getRate().doubleValue());
		bean.setRemark(borrow.getRemark());
		bean.setMid(borrow.getMid());
		bean.setUserId(borrow.getUserId());
		bean.setId(borrow.getId());
		bean.setInterest(borrow.getInterest().doubleValue());
		bean.setPeriods(borrow.getPeriods());
		bean.setAddress(borrow.getAddress());
		bean.setScenes(borrow.getScenes());
		bean.setState(borrow.getState());
		bean.setAmount(borrow.getAmount().doubleValue());
		bean.setCreateTime(borrow.getCreateTime());
		bean.setMobile(borrow.getMobile());
		bean.setName(borrow.getName());
		bean.setOrderNo(borrow.getOrderNo());
		return bean;
	}

	/**
	 * 放款后回调更新订单状态
	 *
	 * @param borrow_id
	 */
	public void loanCallback(Long borrow_id, String state) {
		ClBorrow borrow = this.mapper.selectByPrimaryKey(borrow_id);
		if (FsUtils.strsNotEmpty(borrow)) {
			if (borrow.getState().equals(BorrowStateConstant.STATE_PASS)
					|| borrow.getState().equals(BorrowStateConstant.STATE_REPAY_FAIL)) {
				if (state.equals(BorrowStateConstant.STATE_REPAY))
					updateBorrowState(borrow_id, BorrowStateConstant.STATE_REPAY);
				else {
					updateBorrowState(borrow_id, BorrowStateConstant.STATE_REPAY_FAIL);
				}
			} else {
				throw new ServiceException("订单状态不正确,已经放款款请注意:" + borrow_id);
			}
		} else {
			throw new ServiceException("未找到订单:" + borrow_id);
		}
	}

	/**
	 * 订单全部还完后，才会更新订单的状态
	 *
	 * @param borrow_id
	 */
	public void repaymentSuccessCallback(Long borrow_id) {
		ClBorrow borrow = this.mapper.selectByPrimaryKey(borrow_id);
		if (FsUtils.strsNotEmpty(borrow)) {
			if (borrow.getState().equals(BorrowStateConstant.STATE_REPAY)
					|| borrow.getState().equals(BorrowStateConstant.STATE_REMISSION_FINISH)) {
				updateBorrowState(borrow_id, BorrowStateConstant.STATE_FINISH);
			} else {
				throw new ServiceException("订单状态不正确,已经扣款请注意:" + borrow_id);
			}
		} else {
			throw new ServiceException("未找到订单:" + borrow_id);
		}
	}

	/**
	 * 逾期更新订单状态
	 *
	 * @param borrow_id
	 */
	@Override
	public void overdue(Long borrow_id) {

		ClBorrow borrow = this.mapper.selectByPrimaryKey(borrow_id);
		if (FsUtils.strsNotEmpty(borrow) && BorrowStateConstant.STATE_REPAY.equals(borrow.getState())) {
			updateBorrowState(borrow_id, BorrowStateConstant.STATE_DELAY);
			// 冻结额度
			arcCreditService.freezeQuota(borrow.getUserId());
		}

	}

	/**
	 * 坏账
	 *
	 * @param borrow_id
	 */
	public void badDebt(Long borrow_id) {
		updateBorrowState(borrow_id, BorrowStateConstant.STATE_BAD);
	}

	private void updateBorrowState(Long borrowId, String state) {
		ClBorrow borrow = this.mapper.selectByPrimaryKey(borrowId);
		if (FsUtils.strsNotEmpty(borrow)) {
			ClBorrow borrow1 = new ClBorrow();
			borrow1.setId(borrowId);
			if (state.equals(BorrowStateConstant.STATE_REPAY)) {
				borrow1.setLoanTime(new Date());
			}
			borrow1.setState(state);
			this.update(borrow1);

		} else {
			throw new ServiceException("未找到订单:" + borrowId);
		}
	}

	public List<ClBorrow> getBorrowLoanSuccess(Long userId) {
		Map<String, Object> map = new HashMap();
		map.put("userId", userId);
		List<String> list = new ArrayList<>();
		list.add(BorrowStateConstant.STATE_REPAY);
		list.add(BorrowStateConstant.STATE_DELAY);
		map.put("state", list);
		return borrowList(map);

	}

	public Response getBorrowBeanList(BorrowQueryRequest bean) {
		if (bean.getPage() > 0 && bean.getPageSize() > 0) {
			PageHelper.startPage(bean.getPage(), bean.getPageSize());
		}
		Map<String, Object> map = new HashMap<>();
		if (FsUtils.strsNotEmpty(bean.getOrderNo()))
			map.put("orderNo", bean.getOrderNo());
		if (FsUtils.strsNotEmpty(bean.getState()))
			map.put("state", bean.getState());
		if (FsUtils.strsNotEmpty(bean.getMobile()))
			map.put("mobile", bean.getMobile());
		if (FsUtils.strsNotEmpty(bean.getUserId())) {
			map.put("userId", bean.getUserId());
		}
		if (FsUtils.strsNotEmpty(bean.getType()))
			map.put("borrowType", bean.getType());
		if (FsUtils.strsNotEmpty(bean.getStart()))
			map.put("createTime#start", FsUtils.formatDate(bean.getStart()));
		if (FsUtils.strsNotEmpty(bean.getEnd()))
			map.put("createTime#end", FsUtils.formatDate(FsUtils.addDate(bean.getEnd(), 1)));
		map.put("name", bean.getName());
		List<ClBorrow> borrows = borrowList(map);
		List<BorrowBean> borrowBeans = PageUtils.convert(borrows, new DataObjectConverter<ClBorrow, BorrowBean>() {
			@Override
			public BorrowBean convert(ClBorrow borrow) {
				BorrowBean b = new BorrowBean();
				b.setUserId(borrow.getUserId());
				b.setAmount(borrow.getAmount().doubleValue());
				b.setRate(borrow.getRate() == null ? 0D : borrow.getRate().doubleValue());
				b.setName(borrow.getName());
				b.setMobile(borrow.getMobile());
				b.setState(borrow.getState());
				b.setPeriods(borrow.getPeriods());
				b.setTimeLimit(FsUtils.i(borrow.getTimeLimit()));
				b.setCreateTime(borrow.getCreateTime());
				b.setId(borrow.getId());
				b.setOrderNo(borrow.getOrderNo());
				b.setRemark(borrow.getRemark());
				b.setInterest(borrow.getInterest().doubleValue());
				if (FsUtils.strsNotEmpty(borrow.getMid()))
					b.setMid(borrow.getMid());
				return b;
			}
		});

		return Response.success(borrowBeans);
	}

	public List<ClBorrow> borrowList(Map<String, Object> map) {

		Condition condition = new Condition(ClBorrow.class);
		Example.Criteria criteria = condition.createCriteria();
		for (String s : map.keySet()) {
			if (s.contains("#start")) {
				criteria.andGreaterThanOrEqualTo(s.replace("#start", ""), map.get(s));
			} else if (s.contains("#end")) {
				criteria.andLessThanOrEqualTo(s.replace("#end", ""), map.get(s));
			} else {
				if (s.equalsIgnoreCase("state")) {
					if (map.get(s) instanceof String) {
						criteria.andEqualTo("state", map.get(s));
						//
					} else if (map.get(s) instanceof List) {
						criteria.andIn("state", (List) map.get(s));
					}

				} else if (FsUtils.strsNotEmpty(map.get(s))) {
					criteria.andEqualTo(s, map.get(s));
				}

			}
		}
		condition.setOrderByClause("create_time desc");
		return this.mapper.selectByCondition(condition);
	}

	/**
	 * 根据userId查询pay_log（如果借款时10分钟前返回，其他返回null）
	 */
	@Override
	public String findBorrowlastTenM(Long userId) {
		logger.info("findBorrowlastTenM > userId" +userId);
		Map<String, Object> param = new HashMap<>();
		param.put("userId", userId);
		ClPayLog clPayLog = this.clPayLogMapper.selectByParam(param);
		if (clPayLog != null) {
			if (clPayLog.getState().equals("40") && clPayLog.getUpdateTime() != null
					&& clPayLog.getType().equals("20")) {
				Calendar nowTime = Calendar.getInstance();
				nowTime.setTime(clPayLog.getUpdateTime());
				nowTime.add(Calendar.MINUTE, 10);
				if (nowTime.getTime().after(new Date())) {
					return "尊敬的用户：恭喜您归还成功！";
				} else {
					return null;
				}
			} else if (clPayLog.getState().equals("40") && clPayLog.getUpdateTime() != null
					&& clPayLog.getType().equals("10")) {
				Calendar nowTime = Calendar.getInstance();
				nowTime.setTime(clPayLog.getUpdateTime());
				nowTime.add(Calendar.MINUTE, 10);
				if (nowTime.getTime().after(new Date())) {
					return "尊敬的用户：恭喜您申请分期成功！";
				} else {
					return null;
				}
			}
		}
		return null;
	}

	@Override
	public void updates(Long borrowId, String state) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("borrowId", borrowId);
		map.put("state", state);
		clBorrowMapper.updates(map);
	}

	@Override
	public List<ClBorrow> getByUserId(Long userId, String state, String borrowType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("state", state);
		map.put("borrowType", borrowType);
		return clBorrowMapper.getByUserId(map);
	}

	@Override
	public List<ClBorrow> getByUserIds(Long userId, String borrowType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("borrowType", borrowType);
		return clBorrowMapper.getByUserIds(map);
	}

	@Override
	public ClBorrow findByUserIdDesc(Long userId) {

		return clBorrowMapper.findByUserId(userId);
	}

	@Override
	public Map<String,String> findRate(Long userId) {
		Map<String,String> map=new HashMap<String,String>();
		 logger.info("dCloanUserDomain---------------------------->userId=="+userId);
		   DCloanUserDomain dCloanUserDomain=userService.getUserInfoByUserId(userId);
		   logger.info("dCloanUserDomain---------------------------->=="+dCloanUserDomain.getLoginName());
		   logger.info("dCloanUserDomain---------------------------->=="+(null==dCloanUserDomain));
           String rate="0.05";
           Map<String, String> maps=new HashMap<String, String>();
           Map<String, String> mapss=new HashMap<String, String>();
           maps.put("isAvailability","1");
           mapss.put("isAvailability","1");
           if(null!=dCloanUserDomain){
           	maps.put("phone",dCloanUserDomain.getLoginName());
        	CLWContacts cLWContacts=cLWContactService.getByP(maps);
           	logger.info("cLWContacts---------------------------->"+(cLWContacts==null));
           	if(null!=cLWContacts){
           		rate= cLWContacts.getBorrowRate().toString();
           		logger.info("rate1---------------------------->"+rate);
           	}else{
           		mapss.put("mobile",dCloanUserDomain.getLoginName());
           		CLWContacts cLWContactss=cLWContactService.getByP(mapss);
           		logger.info("cLWContactss---------------------------->=="+(null==cLWContactss));
           		if(null!=cLWContactss){
           			rate= cLWContactss.getBorrowRate().toString();
           			logger.info("rate2---------------------------->"+rate);
           		}
           	}	  	       
           }
           logger.info("rate3---------------------------->"+rate);
           String rates = String.valueOf(new BigDecimal(rate).multiply(new BigDecimal(100)));
           String ratess = String.valueOf(new BigDecimal(rate).multiply(new BigDecimal(10)));
           if(rates.indexOf(".") > 0){  
           	rates = rates.replaceAll("0+?$", "");//去掉多余的0  
           	rates = rates.replaceAll("[.]$", "");//如最后一位是.则去掉  
           }  
           if(ratess.indexOf(".") > 0){  
           	ratess = ratess.replaceAll("0+?$", "");//去掉多余的0  
           	ratess = ratess.replaceAll("[.]$", "");//如最后一位是.则去掉  
           } 
           logger.info("rate4---------------------------->"+rate);
           logger.info("rate5---------------------------->"+rates);
           logger.info("rate6---------------------------->"+ratess);
           map.put("rate", rate);//白名单日费率（0.05%）
           map.put("rates", rates);//白名单日费率（0.05%）*100，1万元每天所需要的利息
           map.put("ratess", ratess);//白名单日费率（0.05%）*10,1000元每天所需要的利息
           return map;
	}
}
