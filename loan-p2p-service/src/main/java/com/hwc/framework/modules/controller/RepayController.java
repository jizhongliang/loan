package com.hwc.framework.modules.controller;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.bo.RepayDataBo;
import com.hwc.framework.modules.bo.RepayDataResultBo;
import com.hwc.framework.modules.bo.RepayResponseBo;
import com.hwc.framework.modules.bo.dto.request.RepayRequestDto;
import com.hwc.framework.modules.bo.request.RepayParamsOverduesBo;
import com.hwc.framework.modules.bo.request.RepayParamsRequestBo;
import com.hwc.framework.modules.bo.request.RepayRequestBo;
import com.hwc.framework.modules.domain.PreRepayInterestAndBreakFine;
import com.hwc.framework.modules.model.ArcSysConfig;
import com.hwc.framework.modules.model.CLBorrow;
import com.hwc.framework.modules.model.CLBorrowRepay;
import com.hwc.framework.modules.model.CLBorrowThird;
import com.hwc.framework.modules.service.*;
import com.hwc.framework.modules.third.UserService;
import com.hwc.framework.utils.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**还款接口
 * Created by jzl on 2017/12/27.
 */
@RestController
@RequestMapping("/api/p2p")
public class RepayController {
    private static Logger logger = LoggerFactory.getLogger(RepayController.class);
    @Autowired
    private BorrowRepayService borrowRepayService;
    @Autowired
    private BorrowThirdService borrowThirdService;
    @Autowired
    private BorrowService borrowService;
    @Autowired
    private BorrowRepayLogService repayLogService;
    @Autowired
    private BorrowRepayThirdService repayThirdService;
    @Autowired
    private UserService userService;
    @Autowired
    private ArcSysConfigService configService;




    @Value("${repayment.url}")
    private String REPAYMENT_URL;


    @ResponseBody
    @RequestMapping(value = "/repay", method = RequestMethod.POST)
    public Response repay(@RequestBody RepayRequestDto requestDto) {

        if (ParamUtil.isEmpty(requestDto)) {
            logger.info("还款接口请求实体为空");
            return Response.fail("请求实体为空");
        }
        String userId = requestDto.getUserId();
        String borrowId = requestDto.getBorrowId();
        String type = requestDto.getType();
        String pwd = requestDto.getTradePassword();
        logger.info("还款接口收到的参数:userId:{},borrowId:{},type:{},tradePassword:{}", userId, borrowId, type, pwd);
        try {
            if (ParamUtil.isEmpty(userId) || ParamUtil.isEmpty(type) || ParamUtil.isEmpty(borrowId)) {
                logger.info("参数为空，返回");
                return Response.fail("参数为空，拒绝访问");
            }

            //交易密码
            Response response = userService.checkPwd(Long.valueOf(userId), pwd);
            if (200 != response.getCode()) {
                logger.info("交易密码错误，userId:{},pwd:{}", userId, pwd);
                return response;
            }

            String repayIds = borrowRepayService.structureRepayIds(Long.valueOf(userId), Integer.parseInt(type), Long.valueOf(borrowId));

            //做开关判断
            boolean isBaofu = isBaoFuOperate();
            if (isBaofu) {
                logger.info("通过宝付直接扣款支付");
                Response response1 = borrowRepayService.toBaoFuPay(type, pwd, borrowId, repayIds);
                logger.info("宝付扣款成功");
                return response1;
            }

            /**
             * 1、校验参数
             * 2、校验还款订单
             *      不为空
             *      未还款
             * 3、开始还款请求
             * 4、生成还款记录
             * 5、生成与第三方记录入库
             * 6、根据返回信息
             *      更新与第三方记录    repay_third
             *      更新还款记录       repay_log
             *      更新还款计划表     borrow_repay
             *      更新借款表        borrow
             */
            logger.info("还款接口收到的请求参数:repayIds:{}", repayIds);
            if (ParamUtil.isEmpty(repayIds)) {
                logger.info("该用户无还款订单，uid:{}", userId);
                return Response.fail("无还款订单");
            }

            List<CLBorrowRepay> repayList = borrowRepayService.findBorrowRepayList(repayIds);
            if (ParamUtil.isEmpty(repayList) || repayList.isEmpty()) {
                logger.info("还款接口，还款订单列表为空");
                return Response.fail("还款订单为空");
            }

            boolean res = borrowRepayService.checkBorrowRepayUnRepay(repayList);
            if (!res) {
                logger.info("还款接口，订单已还款,repayList:{}", repayList);
                return Response.fail("订单已还款");
            }

            CLBorrow borrow = borrowService.getBorrow(repayList.get(0).getBorrowId());
            if (ParamUtil.isEmpty(borrow)) {
                return Response.fail("借款订单为空");
            }

            CLBorrowThird borrowThird = borrowThirdService.getBorrowThirdByOrderNo(borrow.getOrderNo());
            if (ParamUtil.isEmpty(borrowThird)) {
                return Response.fail("与第三方放款订单为空");
            }

            //3 begin
            //这里要计算 提前还款的利息 重新计算
            List<RepayParamsRequestBo> paramsList = fillParamsRequestBo(repayList, borrowThird, borrow);
            logger.info("排序前的data信息:{}", JSONArray.fromObject(paramsList).toString());
            logger.info("排序后的data信息:{}", JSONArray.fromObject(JSONObjectUtil.sortJsonArray(JSONArray.fromObject(paramsList))).toString());
            RepayRequestBo repayRequestBo = fillRepayRequestBo(paramsList);
            logger.info("未加密的信息:{}", JSONArray.fromObject(repayRequestBo).toString());
            logger.info("sign={}", repayRequestBo.getSign());
            String result = "";
            try {
                logger.info("还款请求参数：{}", Des.encode(Des.secretKey, JSONObject.fromObject(repayRequestBo).toString()));
                result = HttpClientUtils.postJson(REPAYMENT_URL, Des.encode(Des.secretKey, JSONObject.fromObject(repayRequestBo).toString()), "UTF-8", true);
            }catch (Exception e) {
                logger.error("还款接口请求第三方超时,paramList:{}", paramsList);
            }
            //4
            repayLogService.generateRepayLogs(repayList);
            //5
            repayThirdService.generateRepayThird(borrow, repayList);

            //解密
            if (ParamUtil.isEmpty(result)) {
                logger.info("请求超时，无返回结果");
                borrowRepayService.updateRepayInProgress(repayList);
                return Response.fail("系统异常");
            }

            //返回串只是对data加密
            RepayResponseBo responseBo = GsonUtil.GsonToBean(result, RepayResponseBo.class);
            if (ParamUtil.isEmpty(responseBo)) {
                logger.info("与第三方交互出现错误");
                borrowRepayService.updateRepayInProgress(repayList);
                return Response.fail("与第三方交互错误");
            }

            if (!"0".equals(responseBo.getCode())) {
                logger.info("还款失败！");
                //清理数据 如果还款订单不是还款中，那就清理掉记录表里相关的数据
                repayLogService.deleteRepayLogByRepayId(repayList);
                repayThirdService.deleteRepayThirdByRepayId(repayList);
                return Response.fail("还款失败");
            }

            String decypt = "";
            try {
                decypt = Des.decode(Des.secretKey, responseBo.getData());
            }catch (Exception e) {
                logger.error("还款接口解密出现异常，e:{}", e);
                borrowRepayService.updateRepayInProgress(repayList);
                return Response.fail("解密错误");
            }

            RepayDataBo dataBo = GsonUtil.GsonToBean(decypt, RepayDataBo.class);
            if (ParamUtil.isEmpty(dataBo)) {
                logger.info("与第三方解密后获取业务参数实体为空");
                borrowRepayService.updateRepayInProgress(repayList);
                return Response.fail("业务实体为空");
            }

            List<RepayDataResultBo> resultBoList = dataBo.getResult();
            logger.info("结果集为：{}", JSONArray.fromObject(resultBoList).toString());
            //6
            boolean updateRes = borrowRepayService.updateRepayInfos(resultBoList);
            logger.info("本次还款操作成功，list:{}", resultBoList);
            return Response.success();

        }catch (Exception e) {
            logger.error("还款接口出现异常,e:{}", e);
            return Response.fail("系统异常");
        }

    }


    /***************************************************************************************/

    private boolean isBaoFuOperate() {
        //放还款渠道
        ArcSysConfig channelConfig = configService.getSysConfigByCode("pay_channel");
        if (ParamUtil.isEmpty(channelConfig)) {
            return false;
        }

        //10:baofu 20:xinhuajindian
        if ("10".equals(channelConfig.getValue())) {
            return true;
        }else {
            return false;
        }

    }


    private RepayRequestBo fillRepayRequestBo(List<RepayParamsRequestBo> paramsList) {
        RepayRequestBo requestBo = new RepayRequestBo();
        requestBo.setSource("caiwei");
        requestBo.setParams(paramsList);
        requestBo.setSign(RSAUtil.encoderByMd5(JSONArray.fromObject(JSONObjectUtil.sortJsonArray(JSONArray.fromObject(paramsList))).toString()));
        return requestBo;
    }

    public List<RepayParamsRequestBo> fillParamsRequestBo(List<CLBorrowRepay> repayList, CLBorrowThird borrowThird, CLBorrow borrow) {
        List<RepayParamsRequestBo> boList = new ArrayList<>();
        for (CLBorrowRepay repay : repayList) {
            RepayParamsRequestBo bo = new RepayParamsRequestBo();
            bo.setPayMentTransaction(borrowThird.getThirdNo());
            bo.setRepayAmount(repay.getRealAmount().toString());    //本金
            //提前还款 利息重新计算
            PreRepayInterestAndBreakFine interestAndBreakFine = borrowRepayService.calcuPreRepayInterestAndBreakFine(repay, borrow);
            bo.setInterest(interestAndBreakFine.getInterest().toString()); //利息
            //违约金先不用

            BigDecimal totalAmount = repay.getRealAmount().add(interestAndBreakFine.getInterest().add(interestAndBreakFine.getBreakFine()).add(repay.getPenaltyAmout()));
            bo.setTotalAmount(totalAmount.toString());  //总
            bo.setStages(String.valueOf(repay.getId()));
            bo.setIsLastStages(repay.getSeq() == borrow.getPeriods() ? 1 : 0);//是否为最后一期还款：1是 0否
            bo.setFine(String.valueOf(repay.getPenaltyAmout()));
            //增加逾期详情实体集合
            if (DateUtil.compareDate(repay.getRepayTime(), new Date()) < 0) {
                bo.setIsOverdue(1);
                List<RepayParamsOverduesBo> overduesBoList = new ArrayList<>();
                RepayParamsOverduesBo overduesBo = new RepayParamsOverduesBo();
                overduesBo.setExpiryDate(repay.getRepayTime());     //还款日期
                overduesBo.setOverdueAmount(repay.getRealAmount()); //逾期本金
                overduesBo.setOverdueFine(repay.getPenaltyAmout()); //逾期罚金
                overduesBo.setOverdueStages(String.valueOf(repay.getId()));//期数
                overduesBo.setOverdueInterest(borrowRepayService.caculateInterest(repay.getRealAmount().add(repay.getRealAmountBalance()), repay.getRate(), String.valueOf(Math.abs(DateUtil.daysBetween(repay.getRepayTime(), new Date())) + 1)));
                overduesBoList.add(overduesBo);
                bo.setOverdues(overduesBoList);
            }else {
                bo.setIsOverdue(0);
                bo.setOverdues(new ArrayList<>());
            }
            boList.add(bo);
        }
        return boList;
    }
}
