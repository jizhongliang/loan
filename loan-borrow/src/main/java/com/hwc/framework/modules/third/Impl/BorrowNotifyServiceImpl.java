package com.hwc.framework.modules.third.Impl;

import com.alibaba.fastjson.JSON;
import com.hwc.common.aliyun.ons.producer.HwcOnsProducer;
import com.hwc.framework.config.BorrowConsumerConfig;

import com.hwc.framework.modules.model.ClBorrow;
import com.hwc.framework.modules.model.ClBorrowRepay;
import com.hwc.framework.modules.service.ClBorrowService;
import com.hwc.framework.modules.third.BorrowNotifyService;
import com.hwc.loan.sdk.borrow.domain.DBorrowBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Properties;

/**
 * Created by on 2017/11/23.
 */
@Service
public class BorrowNotifyServiceImpl implements BorrowNotifyService {
	@Autowired
	@Qualifier("borrowProducer")
	private HwcOnsProducer producer;

	@Autowired
	private BorrowConsumerConfig config;

	@Autowired
	private ClBorrowService borrowService;

	/**
	 * 新申请 消息通知 "credit_borrow_apply"
	 *
	 * @param borrow
	 */
	private DBorrowBean getBean(ClBorrow borrow) {
		DBorrowBean bean = new DBorrowBean();
		bean.setMobile(borrow.getMobile());
		bean.setState(borrow.getState());
		bean.setName(borrow.getName());
		bean.setCreateTime(borrow.getCreateTime());
		bean.setAmount(borrow.getAmount().doubleValue());
		bean.setOrderNo(borrow.getOrderNo());
		bean.setPeriods(borrow.getPeriods());
		return bean;
	}

	public void applyBorrowNotify(ClBorrow borrow) {
		Properties properties = new Properties();
		properties.setProperty("borrow", JSON.toJSONString(borrow));
		producer.sendJson(config.getApplyBorrowTag(), "borrow_" + borrow.getId() + "_" + borrow.getState(), properties,
				getBean(borrow));
	}

	/**
	 * 申请被拒绝消息通知 credit_borrow_refuse
	 *
	 * @param borrow
	 */
	public void refuseBorrowNotify(ClBorrow borrow) {
		Properties properties = new Properties();
		properties.setProperty("borrow", JSON.toJSONString(borrow));
		producer.sendJson(config.getRefuseBorrowTag(), "borrow_" + borrow.getId() + "_" + borrow.getState(), properties,
				getBean(borrow));
	}

	/**
	 * 审核通过 credit_borrow_pass
	 *
	 * @param borrow
	 */
	public void borrowPassNotify(ClBorrow borrow) {
		Properties properties = new Properties();
		properties.setProperty("borrow", JSON.toJSONString(borrow));
		producer.sendJson(config.getBorrowPassTag(), "borrow_" + borrow.getId() + "_" + borrow.getState(), properties,
				getBean(borrow));
	}

	/**
	 * 逾期通知 repay_borrow_overdue
	 *
	 * @param repay
	 */
	public void borrowOverdueNotify(ClBorrowRepay repay) {
		ClBorrow borrow = borrowService.getBorrow(repay.getBorrowId());
		Properties properties = new Properties();
		properties.setProperty("borrow", JSON.toJSONString(borrow));
		producer.sendJson(config.getBorrowOverdueTag(), "repay_" + repay.getId() + "_" + repay.getState(), properties,
				repay);
	}

	/**
	 * 快到期提醒 repay_borrow_expire
	 *
	 * @param repay
	 */
	public void repayExpireNotify(ClBorrowRepay repay) {
		ClBorrow borrow = borrowService.getBorrow(repay.getBorrowId());
		Properties properties = new Properties();
		properties.setProperty("borrow", JSON.toJSONString(borrow));
		producer.sendJson(config.getRepayExpireTag(), "repay_" + repay.getId() + "_" + repay.getState(), properties,
				repay);
	}
	/** 
	 * 放款成功提醒
	 */
	@Override
	public void paySuccessNotify(ClBorrow borrow) {
		Properties properties = new Properties();
		properties.setProperty("borrow", JSON.toJSONString(borrow));
		producer.sendJson(config.getPaySuccessTag(), "borrow_" + borrow.getId() + "_" + borrow.getState(), properties,
				getBean(borrow));

	}
	/** 
	 * 还款成功提醒
	 */
	@Override
	public void repaySuccess(ClBorrowRepay repay) {
		ClBorrow borrow = borrowService.getBorrow(repay.getBorrowId());
		Properties properties = new Properties();
		properties.setProperty("borrow", JSON.toJSONString(borrow));
		producer.sendJson(config.getRepaySuccessTag(), "repay_" + repay.getId() + "_" + repay.getState(), properties,
				repay);
		
	}

	// /**
	// * 请求放款成功 等待回调消息
	// *
	// * @param payLog
	// */
	// public void paySuccessNotify(PayRespBean payLog) {
	//
	// }
	//
	// /**
	// * 请求代扣成功
	// *
	// * @param payLog
	// */
	// public void repaySuccessNotify(ChargeRespBean payLog) {
	//
	// }
}
