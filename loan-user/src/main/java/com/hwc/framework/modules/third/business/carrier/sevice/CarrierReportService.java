package com.hwc.framework.modules.third.business.carrier.sevice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hwc.framework.modules.third.business.carrier.api.ReportClient;
import com.hwc.framework.modules.third.business.carrier.billitem.CarrierReportTask;
import com.hwc.framework.modules.third.business.carrier.entity.report.ReportEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * Created by zengdongping on 17/1/3.
 */
@Service
public class CarrierReportService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarrierReportService.class);
    
    private static ObjectMapper objectMapper= new ObjectMapper();

    @Autowired
    private ReportClient reportClient;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    public void fetchReport(final CarrierReportTask task) {
        // 这里交给线程池处理，防止下面的业务处理时间太长，导致超时。
        // 超时会导致魔蝎数据进行重试，会收到重复的回调请求
        taskExecutor.execute(
                new Runnable() {
                    @Override
                    public void run() {

                        try {
                        	if(task.isResult()){
                        		String reportData = reportClient.getReportBasic(task.getMobile(), task.getTaskId());
                                saveReportData(task,reportData);
                        	}else{
                        		 LOGGER.error("carrier report result is false . task:{},message:{}", task.getTaskId(),task.getMessage());
                        	}
                            

                        } catch (Exception e) {
                            LOGGER.error("fetchBill failed. task:{}", task.getTaskId(), e);
                        }

                    }
                }


        );
    }

    private void saveReportData(CarrierReportTask task, String reportData) {
        ReportEntity reportEntity = new ReportEntity();
        try {

            reportEntity.setUserId(task.getUserId());
            reportEntity.setMobile(task.getMobile());
            reportEntity.setTaskId(task.getTaskId());
            reportEntity.setReportData(reportData);


//            reportDataRepository.saveReportData(reportEntity);
//            catch (JsonProcessingException e) {
//                LOGGER.error("模型转换字符串失败");
//                e.printStackTrace();
//            }
        } catch (Exception e) {
            LOGGER.error("saveReportData failed. taskId:{}", task.getTaskId(), e);
        }
    }

}
