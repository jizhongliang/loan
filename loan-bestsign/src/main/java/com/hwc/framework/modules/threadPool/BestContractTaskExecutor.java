package com.hwc.framework.modules.threadPool;

import com.alibaba.fastjson.JSONArray;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.ContractDomian;
import com.hwc.framework.modules.service.BestSignContractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import java.util.concurrent.*;

/**
 * Created by jzl on 2018/1/24.
 */
@Component
public class BestContractTaskExecutor {
    private static Logger logger = LoggerFactory.getLogger(BestContractTaskExecutor.class);
    @Autowired
    @Qualifier("taskExecutor")
    private ThreadPoolTaskExecutor taskExecutor;
    public void setTaskExecutor(ThreadPoolTaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }
    @Autowired
    private BestSignContractService signContractService;


    /**
     * 开启多线程
     * @param
     * @return
     */
    public Response handleBestContract(ContractDomian domian, JSONArray array) {

        for (int i=1; i<=7; i++) {
            domian.setType(String.valueOf(i));
            Future<Boolean> future = taskExecutor
                    .submit(new FarmTaskCallable(signContractService, domian, array));
            boolean result;
            try {
                //线程执行超过5分钟将会自动超时，抛出异常
                result = future.get(5 * 60 * 1000, TimeUnit.MILLISECONDS);
                if (result) {
                    logger.info("此线程任务执行完毕--用户uid:{}信用签第：{} 份合同签署完毕", domian.getUserId(), i);
                }
            } catch (TimeoutException e) {
                logger.error("用户uid:{}信用签第：{} 份合同签署 线程任务执行超过5分钟Timeout，准备取消--{}", domian.getUserId(), i,
                        e.getCause());
                //参数true代表强制取消未完成的线程
                boolean cancelRes = future.cancel(true);
                logger.error("线程任务Timeout取消结果--{}", cancelRes);
            } catch (InterruptedException e) {
                logger.error("用户uid:{}信用签第：{} 份合同签署线程任务执行超过5分钟Interrupted，准备取消--{}", domian.getUserId(), i,
                        e.getMessage());
                boolean cancelRes = future.cancel(true);
                logger.error("线程任务Interrupted取消结果--{}", cancelRes);
            } catch (ExecutionException e) {
                logger.error("用户uid:{}信用签第：{} 份合同签署 线程任务执行超过5分钟Execution，准备取消--{}", domian.getUserId(), i,
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
        private BestSignContractService signContractService;
        private ContractDomian domian;
        private JSONArray array;

        public FarmTaskCallable(BestSignContractService signContractService, ContractDomian domian, JSONArray array) {
            this.signContractService = signContractService;
            this.domian = domian;
            this.array = array;
        }

        @Override
        public Object call() throws Exception {
            signContractService.createContract(domian, array);
            return true;
        }
    }
}
