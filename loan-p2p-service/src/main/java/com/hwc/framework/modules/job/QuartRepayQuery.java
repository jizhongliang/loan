package com.hwc.framework.modules.job;

import com.hwc.framework.modules.model.CLBorrowRepay;
import com.hwc.framework.modules.model.ClQuartzInfo;
import com.hwc.framework.modules.model.ClQuartzLog;
import com.hwc.framework.modules.service.BorrowRepayService;
import com.hwc.framework.modules.service.QuartzInfoService;
import com.hwc.framework.modules.service.QuartzLogService;
import com.hwc.framework.utils.ParamUtil;
import com.hwc.framework.utils.SpringContextHolder;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**还款查询,定时任务
 * Created by jzl on 2017/12/28.
 */
@Component
@Lazy(value = false)
public class QuartRepayQuery implements Job {

    private static Logger logger = LoggerFactory.getLogger(QuartRepayQuery.class);


    /**
     * 逻辑主体
     * @return
     */
    public String repayQuery(){
        try {
            logger.info("开始收集还款中的分期订单");
            ThreadPoolTaskExecutor taskExecutor = SpringContextHolder.getBean("taskExecutor");
            BorrowRepayService borrowRepayService = SpringContextHolder.getBean(BorrowRepayService.class);
            List<CLBorrowRepay> repayList = borrowRepayService.queryNeedQueryOrders();
            logger.info("查询出需要查询的分期还款订单数量:{}", repayList.size());
            if (ParamUtil.isEmpty(repayList) || repayList.isEmpty()) {
                logger.info("需要查询的还款订单数量为0");
                return "执行成功" + 0 + "次,失败" + 0 + "次";
            }
            int succeed = 0;
            int failed = 0;
            for (CLBorrowRepay repay : repayList) {
                Future<Boolean> future = taskExecutor.submit(new RepayQueryTaskCallable(borrowRepayService, repay));
                succeed++;
                boolean result;
                try {
                    //线程执行超过5分钟将会自动超时，抛出异常
                    result = future.get(5 * 60 * 1000, TimeUnit.MILLISECONDS);
                    if (result) {
                        logger.info("此线程任务执行完毕---repayId:{}", repay.getId());
                    }
                } catch (TimeoutException e) {
                    logger.error("repayId {} 线程任务执行超过5分钟Timeout，准备取消--{}", repay.getId(),
                            e.getCause());
                    //参数true代表强制取消未完成的线程
                    boolean cancelRes = future.cancel(true);
                    failed++;
                    logger.error("线程任务Timeout取消结果--{}", cancelRes);
                } catch (InterruptedException e) {
                    logger.error("repayId {} 线程任务执行超过5分钟Interrupted，准备取消--{}",repay.getId(),
                            e.getMessage());
                    boolean cancelRes = future.cancel(true);
                    failed++;
                    logger.error("线程任务Interrupted取消结果--{}", cancelRes);
                } catch (ExecutionException e) {
                    logger.error("repayId {} 线程任务执行超过5分钟Execution，准备取消--{}",repay.getId(),
                            e.getMessage());
                    boolean cancelRes = future.cancel(true);
                    failed++;
                    logger.error("线程任务Execution取消结果-{}", cancelRes);
                }
            }
            return "执行成功" + succeed + "次,失败" + failed + "次";
        }catch (Exception e) {
            logger.error("查询还款线程池出现异常,e:{}", e);
            return "执行成功" + 0 + "次,失败" + 1 + "次";
        }
    }


    /**
     * 线程任务体
     */
    public class RepayQueryTaskCallable implements Callable {

        private BorrowRepayService borrowRepayService;
        private CLBorrowRepay repay;
        public RepayQueryTaskCallable(BorrowRepayService borrowRepayService, CLBorrowRepay repay) {
            this.borrowRepayService = borrowRepayService;
            this.repay = repay;
        }

        @Override
        public Boolean call() throws Exception {
            logger.info("开始查询还款repayId:{}", repay.getId());
            boolean res = borrowRepayService.queryRepay(repay);
            return res;
        }
    }

    /**
     * 定时开始
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        QuartzInfoService quartzInfoService = SpringContextHolder
                .getBean(QuartzInfoService.class);
        QuartzLogService quartzLogService = SpringContextHolder
                .getBean(QuartzLogService.class);
        // 查询当前任务信息
        ClQuartzInfo quartzInfo = quartzInfoService.findByCode("doQuartRepayQuery");
        Map<String, Object> qiData = new HashMap<>();
        qiData.put("id", quartzInfo.getId());

        ClQuartzLog quartzLog = new ClQuartzLog();
        quartzLog.setQuartzId(quartzInfo.getId());
        quartzLog.setStartTime(new Date());
        try {
            String remark = repayQuery();
            quartzLog.setTime(Math.toIntExact(System.currentTimeMillis() - quartzLog.getStartTime().getTime()));
            quartzLog.setResult("10");
            quartzLog.setRemark(remark);
            qiData.put("succeed", quartzInfo.getSucceed() + 1);
        } catch (Exception e) {
            quartzLog.setResult("20");
            qiData.put("fail", quartzInfo.getFail() + 1);
            logger.error(e.getMessage(), e);
        } finally {
            logger.info("保存定时任务日志");
            quartzLogService.save(quartzLog);
            quartzInfoService.update(qiData);
        }
    }
}
