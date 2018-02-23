package com.hwc.framework.modules.controller;

import com.hwc.base.api.Response;
import com.hwc.framework.common.Constant;
import com.hwc.framework.modules.bo.FarmNotifyDataBo;
import com.hwc.framework.modules.bo.FarmNotifyDataResultBo;
import com.hwc.framework.modules.bo.NotifyBo;
import com.hwc.framework.modules.bo.NotifyParamsBo;
import com.hwc.framework.modules.model.CLBorrow;
import com.hwc.framework.modules.service.BorrowRepayService;
import com.hwc.framework.modules.service.BorrowService;
import com.hwc.framework.utils.Des;
import com.hwc.framework.utils.GsonUtil;
import com.hwc.framework.utils.ParamUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**新华金典放款通知接口 成功或失败
 * Created by jzl on 2017/12/25.
 */ 
@RestController
@RequestMapping(value = "/api/p2p", method = RequestMethod.POST)
public class LoanNotifyController {
    private static Logger logger = LoggerFactory.getLogger(LoanNotifyController.class);
    @Autowired
    @Qualifier("taskExecutor")
    private ThreadPoolTaskExecutor taskExecutor;
    public void setTaskExecutor(ThreadPoolTaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    @Autowired
    private BorrowService borrowService;
    @Autowired
    private BorrowRepayService borrowRepayService;

    @ResponseBody
    @RequestMapping("/notify")
    public Response loanNotify(@RequestBody String params) {
        logger.info("放款通知接口收到的原生参数:params={}", params);
        if (ParamUtil.isEmpty(params)) {
            logger.info("放款通知接口参数为空");
            return Response.fail("参数为空");
        }

        String decypt = "";
        try {
            decypt = Des.decode(Des.secretKey, params);
        } catch (Exception e) {
            logger.error("放款通知接口解密出现异常,e:{}", e);
            return Response.fail("解密异常");
        }
        if (ParamUtil.isEmpty(decypt)) {
            logger.info("放款通知接口解密后的串为空，返回");
            return Response.fail("解密后为空");
        }
        NotifyParamsBo paramsBo = GsonUtil.GsonToBean(decypt, NotifyParamsBo.class);
        String code = paramsBo.getCode();
        String message = paramsBo.getMessage();
        FarmNotifyDataBo dataBo = paramsBo.getData();
        logger.info("放款通知接口收到的解密后的参数:decypt:{}", decypt);
        //1.放款成功修改表，增加还款记录，可分期
        //2.放款失败修改表
        if (!"0".equals(code)) {
            logger.info("打款通知接口收到的code为：{},失败，直接返回", code);
            return Response.success();
        }

        if (ParamUtil.isEmpty(dataBo)) {
            logger.info("放款通知接口，转化实体为空，返回");
            return Response.fail("参数转化失败");
        }

        switch (dataBo.getNotifyType()) {
            case 1:
                //Response response = borrowService.handleFarmNotify(dataBo.getResult());
                Response response = handleFarmNotify(dataBo.getResult());
                return response;
            case 2:
                return Response.success();
            default:
                return Response.fail("无此还款类型");
        }

    }

    /**
     * 开启多线程
     * @param resultList
     * @return
     */
    public Response handleFarmNotify(List<FarmNotifyDataResultBo> resultList) {
        for (FarmNotifyDataResultBo resultBo : resultList) {
            Future<Boolean> future = taskExecutor
                    .submit(new FarmTaskCallable(borrowService, resultBo));
            boolean result;
            try {
                //线程执行超过5分钟将会自动超时，抛出异常
                result = future.get(5 * 60 * 1000, TimeUnit.MILLISECONDS);
                if (result) {
                    logger.info("此线程任务执行完毕--orderNo-{}", resultBo.getThirdTransaction());
                }
            } catch (TimeoutException e) {
                logger.error("orderNo {} 线程任务执行超过5分钟Timeout，准备取消--{}", resultBo.getThirdTransaction(),
                        e.getCause());
                //参数true代表强制取消未完成的线程
                boolean cancelRes = future.cancel(true);
                logger.error("线程任务Timeout取消结果--{}", cancelRes);
            } catch (InterruptedException e) {
                logger.error("orderNo {} 线程任务执行超过5分钟Interrupted，准备取消--{}", resultBo.getThirdTransaction(),
                        e.getMessage());
                boolean cancelRes = future.cancel(true);
                logger.error("线程任务Interrupted取消结果--{}", cancelRes);
            } catch (ExecutionException e) {
                logger.error("orderNo {} 线程任务执行超过5分钟Execution，准备取消--{}", resultBo.getThirdTransaction(),
                        e.getMessage());
                boolean cancelRes = future.cancel(true);
                logger.error("线程任务Execution取消结果-{}", cancelRes);
            }
        }
        return null;
    }

    /**
     * 线程主体
     */
    public class FarmTaskCallable implements Callable {
        private BorrowService borrowService;
        private FarmNotifyDataResultBo resultBo;

        public FarmTaskCallable(BorrowService borrowService, FarmNotifyDataResultBo resultBo) {
            this.borrowService = borrowService;
            this.resultBo = resultBo;
        }

        @Override
        public Object call() throws Exception {
            CLBorrow borrow = borrowService.findBorrowByOrderNo(resultBo.getThirdTransaction());
            if (ParamUtil.isEmpty(borrow)) {
                logger.info("放款通知接口根据订单号查询无此订单！orderNo:{}", resultBo.getThirdTransaction());
                return false;
            }

            if (Arrays.asList(Constant.BORROW_STATE_ALREADY_LOAN).contains(borrow.getState())) {
                logger.info("放款通知接口查询到该笔订单已有明确状态，orderNo:{},state:{}", resultBo.getThirdTransaction(), borrow.getState());
                return false;
            }
            Response response = borrowService.handleFarmNotify(resultBo);
            if (!ParamUtil.isEmpty(response) && response.getSuccess()) {
                return true;
            }else {
                return false;
            }
        }
    }
}
