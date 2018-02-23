package com.hwc.framework.modules.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hwc.base.api.Response;
import com.hwc.base.exception.ServiceException;
import com.hwc.framework.common.BorrowStateConstant;
import com.hwc.framework.common.NidGenerator;
import com.hwc.framework.common.PayConstant;
import com.hwc.framework.common.RepayStateConstant;
import com.hwc.framework.modules.dao.ClBorrowRepayMapper;
import com.hwc.framework.modules.domain.ChargeDataDomain;
import com.hwc.framework.modules.domain.ChargeQueryDomain;
import com.hwc.framework.modules.domain.ChargeRespBean;
import com.hwc.framework.modules.domain.DebitBean;
import com.hwc.framework.modules.domain.EarlyRepayBean;
import com.hwc.framework.modules.domain.LianlianResponse;
import com.hwc.framework.modules.domain.PayQueryDomain;
import com.hwc.framework.modules.domain.PayRespBean;
import com.hwc.framework.modules.domain.PreRepayBean;
import com.hwc.framework.modules.model.ClBankCard;
import com.hwc.framework.modules.model.ClBorrow;
import com.hwc.framework.modules.model.ClBorrowRepay;
import com.hwc.framework.modules.model.ClBorrowRepayLog;
import com.hwc.framework.modules.model.ClPayLog;
import com.hwc.framework.modules.service.ArcCreditService;
import com.hwc.framework.modules.service.ArcSysConfigService;
import com.hwc.framework.modules.service.ChargeGatewayService;
import com.hwc.framework.modules.service.ChargeService;
import com.hwc.framework.modules.service.ClBankCardService;
import com.hwc.framework.modules.service.ClBorrowRepayLogService;
import com.hwc.framework.modules.service.ClBorrowRepayService;
import com.hwc.framework.modules.service.ClBorrowService;
import com.hwc.framework.modules.service.ClPayLogService;
import com.hwc.framework.modules.service.PayGatewayService;
import com.hwc.framework.modules.service.PayService;
import com.hwc.framework.modules.third.BorrowNotifyService;
import com.hwc.framework.modules.third.UserService;
import com.hwc.loan.sdk.user.domain.DCloanUserDomain;

import cn.freesoft.utils.FsUtils;

/**
 * Created by on 2017/11/1.
 */
@Service
public class ChargeServiceImpl implements ChargeService {

	org.slf4j.Logger logger = LoggerFactory.getLogger(ChargeServiceImpl.class);
	@Autowired
	private ChargeGatewayService chargeGatewayService;

	@Autowired
	private UserService userService;
	@Autowired
	private ClBorrowService clBorrowService;

	@Autowired
	private ClBankCardService cardService;
	@Autowired
	private ClBorrowRepayService repayService;

	@Autowired
	private ClPayLogService payLogService;

	@Autowired
	private ArcSysConfigService configService;
	@Autowired
	private ArcCreditService arcCreditService;
	@Autowired
	private BorrowNotifyService borrowNotifyService;
	@Autowired
	private PayService payService;
	@Autowired
	private PayGatewayService payGatewayService;
	@Autowired
	private ClBorrowService borrowService;
	@Autowired
	private ClBorrowRepayLogService clBorrowRepayLogService;
	@Autowired
	private ClBorrowRepayMapper clBorrowRepayMapper;

	/**
	 * 主动还款
	 *
	 * @param req
	 * @return
	 */
	public Response repayment(DebitBean req) {
		// Long uid= RequestUtils.getUserId();
		ClBorrowRepay repay = repayService.findById(req.getRepayId());
		// checkPwd(repay.getUserId(), req.getTradePassword());
		if (repay.getState().equals(RepayStateConstant.NU_PAY)) {
			Response<ChargeRespBean> response = repayment(repay);
			if (response.getSuccess()) {
				if ("O".equals(response.getData().getState())) {
					Response res = repayService.repaymentSuccessCallback(repay.getBorrowId(), response.getData());
					// TODO
				} else {
					if (FsUtils.strsNotEmpty(response.getMessage())) {
						return Response.fail(response.getMessage());
					} else
						return Response.fail(response.getData().getRemark());
				}
			}
			return response;
		} else {
			return Response.fail("请勿重复支付");
		}
	}

	public boolean checkPwd(Long userId, String pwd) {
		Response response = userService.checkPwd(userId, pwd);
		if (!response.getSuccess()) {
			throw new ServiceException("交易密码不正确");
		}
		return true;
	}

	/**
	 * 还款
	 *
	 * @param repay
	 * @return
	 */
	public Response repayment(ClBorrowRepay repay) {
		ChargeDataDomain domain = new ChargeDataDomain();
		// checkPwd(repay.getUserId(), req.getTradePassword());
		domain.setService_name("repayment");
		ClPayLog payLog = payLogService.getPayLogByRepayId(repay.getId());
		if (FsUtils.strsNotEmpty(payLog)) {
			// 待支付
			if (payLog.getState().equals("10")) {
				domain.setOrder_no(payLog.getOrderNo());
			} else if (payLog.getState().equals("40")) {
				return Response.success("支付成功");
			}
		}
		DCloanUserDomain userInfo = userService.getUserInfo(repay.getUserId());
		domain.setId_no(userInfo.getIdNo());
		if (FsUtils.strsEmpty(domain.getOrder_no()))
			domain.setOrder_no(NidGenerator.getPayOrderNo());
		String switchStr = configService.getConfigDefault("pay_switch", "dev");
		// 测试环境
		 if (switchStr.equals("dev")) { domain.setAmount(0.01); } else {
		domain.setAmount(repay.getTotalAmount());
		 }
		domain.setName(userInfo.getRealName());
		domain.setUser_id(repay.getUserId());
		domain.setBorrow_id(repay.getBorrowId());
		ClBankCard card = cardService.getBankCard(repay.getUserId());
		domain.setTrade_date(FsUtils.formatDateTime(new Date(), "yyyyMMddHHmmss"));
		domain.setBank_code(card.getBankCode());
		domain.setBank_name(card.getBank());
		domain.setBank_no(card.getCardNo());
		domain.setMobile(userInfo.getPhone());
		domain.setRepay_id(repay.getId() + "");
		domain.setAgree_no(card.getAgreeNo());
		domain.setChannel(card.getPayCode());
		payLogService.autoDebit(repay, card, domain.getOrder_no());
		Response<ChargeRespBean> response = chargeGatewayService.doCharge(domain);
		logger.info("charge response:{}", JSONObject.toJSONString(response));
		payLogService.updatChargeLogState(domain.getOrder_no(), response);
		if (response.getSuccess()) {
			response.getData().setBank_card_no(card.getCardNo());
		}

		return response;
	}

	/**
	 * 提前还款
	 *
	 * @param bean1
	 * @return
	 */
	public Response preRepayment(EarlyRepayBean bean1) {

		Response<PreRepayBean> response = repayService.getPrepaymentAmount(bean1.getBorrowId());
		// 校验上面的返回结果，及时返回，虽然在计算提前还款金额的接口里已判断

		logger.info("提前还款查询还款数据,borrowId=>" + bean1.getBorrowId() + ",data=>" + JSONObject.toJSONString(bean1));
		PreRepayBean bean = response.getData();
		checkPwd(bean.getUser_id(), bean1.getTradePassword());
		ChargeDataDomain domain = new ChargeDataDomain();
		domain.setService_name("pre_repayment");
		DCloanUserDomain userInfo = userService.getUserInfo(bean.getUser_id());
		domain.setId_no(userInfo.getIdNo());
		domain.setOrder_no(NidGenerator.getPayOrderNo());
		String switchStr = configService.getConfigDefault("pay_switch", "dev");
		// 测试环境
		  if (switchStr.equals("dev")) { domain.setAmount(0.01); } else {
		domain.setAmount(bean.getTotal());
		 }
		domain.setName(userInfo.getRealName());
		domain.setUser_id(bean.getUser_id());
		domain.setBorrow_id(bean.getBorrow_id());
		domain.setTrade_date(FsUtils.formatDateTime(new Date(), "yyyyMMddHHmmss"));
		ClBankCard card = cardService.getBankCard(bean.getUser_id());
		domain.setBank_code(card.getBankCode());
		domain.setBank_name(card.getBank());
		domain.setBank_no(card.getCardNo());
		domain.setMobile(userInfo.getPhone());
		domain.setRepay_id(bean1.getBorrowId() + "");
		domain.setAgree_no(card.getAgreeNo());
		domain.setChannel(card.getPayCode());
		ClBorrowRepay repay = new ClBorrowRepay();
		repay.setBorrowId(bean.getBorrow_id());
		repay.setAmount(FsUtils.bigdec(bean.getTotal()));
		repay.setUserId(bean.getUser_id());
		payLogService.autoDebit(repay, card, domain.getOrder_no());
		Response<ChargeRespBean> res = chargeGatewayService.doCharge(domain);
		// String rs =
		// "{\"code\":200,\"data\":{\"amount\":0.01,\"key\":\"charge_201712280110001523198013_O\",\"order_no\":\"201712280110001523198013\",\"orig_trans_id\":\"0198808314\",\"pay_channel\":\"baofu\",\"remark\":\"交易成功\",\"repay_id\":\"7\",\"state\":\"O\"},\"message\":\"success\",\"success\":true}";
		// Response res1 = JSONObject.parseObject(rs, Response.class);
		// logger.info("charge response:{}", JSONObject.toJSONString(res));
		//
		// Response<ChargeRespBean> res =
		// Response.success(JSON.parseObject(JSON.toJSONString(res1.getData()),ChargeRespBean.class
		// ));
		payLogService.updatChargeLogState(domain.getOrder_no(), res);
		if (res.getSuccess()) {
			res.getData().setBank_card_no(card.getCardNo());
			// 回复额度
			ChargeRespBean respBean = (ChargeRespBean) res.getData();
			if ("O".equals(respBean.getState())) {
				// 还款成功，回复额度
				arcCreditService.increaseQuota(bean.getUser_id(), bean.getAmount().doubleValue());
				logger.info("还款成功，回复额度成功，uid:{},amount:{}", bean.getUser_id(), bean.getAmount());
				clBorrowService.updates(bean1.getBorrowId(), "40");
				// 更新borrow_repay为已还款
				repayService.comfigPreRepayment(bean1.getBorrowId(), bean, res.getData());
				// 还款成功推送TODO
				try {
					logger.info("还款成功推送>>" + JSONObject.toJSONString(repay));
					borrowNotifyService.repaySuccess(repay);
				} catch (Exception e) {
					logger.error("还款成功推送出错");
				}

			}
		}
		return res;
	}

	@Override
	public LianlianResponse repepaymentCallback(String resp) {
		return null;
	}

	/**
	 * 宝付还款加查询
	 */
	@Override
	public Response baofuRepayment(DebitBean req) {
		// Long uid= RequestUtils.getUserId();
		logger.info("baofu Req>>" + req);
		ClBorrowRepay repay = repayService.findById(req.getRepayId());
		if (repay.getState().equals(RepayStateConstant.NU_PAY)) {
			// 查询cl_pay_log
			ClPayLog payLog = payLogService.getLastRepayLog(repay.getBorrowId());
			if (payLog != null) {
				if ("10".equals(payLog.getState()) && "20".equals(payLog.getScenes())
						&& "20".equals(payLog.getType())) {
					// 宝付查询
					ChargeQueryDomain domain = new ChargeQueryDomain();
					// 流水号
					domain.setOrder_no("S" + System.currentTimeMillis() + FsUtils.randomNumeric(3));
					// 订单号
					domain.setTrans_serial_no(payLog.getOrderNo());
					// 原交易时间
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
					domain.setTrade_date(sdf.format(payLog.getPayReqTime()));
					//
					domain.setBorrow_id(payLog.getBorrowId());
					domain.setUser_id(payLog.getUserId());
					logger.info("宝付定时扣款明文》》" + domain);
					Response reponse = chargeGatewayService.doChargeQuery(domain);
					logger.info("宝付定时扣款返回》》" + reponse);
					if (reponse.getSuccess()) {
						// 成功
						ChargeRespBean dataJson = (ChargeRespBean)reponse.getData();
						if (dataJson != null) {
							if (dataJson.getState().equals("O")) {
								// S表示交易成功 修改payLog 数据
								payLog.setState("40");
								payLog.setRemark("还款成功");
								payLog.setUpdateTime(new Date());
								payLogService.update(payLog);
								// 新增cl_borrow_repay_log
								ClBorrowRepayLog clborrowRepayLog = new ClBorrowRepayLog();
								clborrowRepayLog.setRepayId(repay.getId());
								clborrowRepayLog.setUserId(payLog.getUserId());
								clborrowRepayLog.setBorrowId(payLog.getBorrowId());
								clborrowRepayLog.setAmount(payLog.getAmount());
								clborrowRepayLog.setPenaltyDay(repay.getPenaltyDay());
								clborrowRepayLog.setPenaltyAmout(repay.getPenaltyAmout());
								clborrowRepayLog.setRepayWay("10");
								clborrowRepayLog.setRepayAccount(payLog.getBank());
								clborrowRepayLog.setSerialNumber(dataJson.getOrder_no());
								clborrowRepayLog.setPayTime(new Date());
								clborrowRepayLog.setRepayTime(new Date());
								clborrowRepayLog.setCreateTime(new Date());
								clBorrowRepayLogService.insert(clborrowRepayLog);
								// 修改cl_borrow_repay
								repay.setState("10");
								repay.setRepayTime(new Date());
								repayService.update(repay);
								// 如果这是最后一条还款成功，则修改cl_borrow
								if (isLastRepayPlan(repay)) {
									borrowService.repaymentSuccessCallback(repay.getBorrowId());
								}
								// 返还额度
								arcCreditService.increaseQuota(repay.getUserId(), repay.getRealAmount().doubleValue());
								return Response.success("支付成功");
							} else if (dataJson.getState().equals("F")) {
								// F交易失败
								payLog.setState("50");
								payLog.setUpdateTime(new Date());
								payLogService.update(payLog);
								return Response.fail("支付失败");
							} else if (dataJson.getState().equals("I")) {
								return Response.fail("支付中");
							} else if (dataJson.getState().equals("FF")) {
								// FF交易失败
								payLog.setState("50");
								payLog.setUpdateTime(new Date());
								payLogService.update(payLog);
								return Response.fail("支付失败");
							} else {
								try {
									throw new Exception("宝付定时返回其他状态》》》" + reponse);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					} else {
						return Response.fail("交易失败，无需查询类");
					}
				} else if ("40".equals(payLog.getState())) {
					return Response.fail("已经扣款");
				} else {
					// 宝付扣款
					return baofuRepayment(repay);
				}
			} else {
				// 宝付扣款
				return baofuRepayment(repay);
			}
		} else {
			return Response.fail("请勿重复支付");
		}
		return null;
	}

	private boolean isLastRepayPlan(ClBorrowRepay repay) {
		ClBorrowRepay max = clBorrowRepayMapper.getMaxUnRepayPlan(repay.getBorrowId());
		if (FsUtils.strsNotEmpty(max) && max.getId().equals(repay.getId())) {
			return true;
		} else
			return false;
	}

	public Response baofuRepayment(ClBorrowRepay repay) {
		Response<ChargeRespBean> response = repayment(repay);
		if (response.getSuccess()) {
			if ("O".equals(response.getData().getState())) {
				Response res = repayService.repaymentSuccessCallback(repay.getBorrowId(), response.getData());
				// TODO
			} else {
				if (FsUtils.strsNotEmpty(response.getMessage())) {
					return Response.fail(response.getMessage());
				} else
					return Response.fail(response.getData().getRemark());
			}
		}
		return response;
	}

}
