package com.hwc.framework.modules.job;

import com.hwc.framework.modules.model.ClQuartzInfo;
import com.hwc.framework.modules.model.ClQuartzLog;
import com.hwc.framework.modules.service.BorrowRepayService;
import com.hwc.framework.modules.service.QuartzInfoService;
import com.hwc.framework.modules.service.QuartzLogService;
import com.hwc.framework.utils.SpringContextHolder;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 定时任务--到期通知
 * 
 * @author pippo
 *
 */
@Component
@Lazy(value = false)
public class QuartExpireRepayPlan implements Job {

	private static final Logger logger = LoggerFactory.getLogger(QuartExpireRepayPlan.class);
	
	public String getSoonExpireRepayPlan() {
		logger.info("到期通知--开始...");
		BorrowRepayService borrowRepayService = SpringContextHolder.getBean(BorrowRepayService.class);
		ClQuartzInfo info = borrowRepayService.getSoonExpireRepayPlan();
		logger.info("到期通知--完成...");
		String quartzRemark = "执行成功"+info.getSucceed()+"次,失败"+info.getFail()+"次";
		logger.info(quartzRemark);
		return quartzRemark;

	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		QuartzInfoService quartzInfoService = SpringContextHolder
				.getBean(QuartzInfoService.class);
		QuartzLogService quartzLogService = SpringContextHolder
				.getBean(QuartzLogService.class);
		// 查询当前任务信息
		ClQuartzInfo quartzInfo = quartzInfoService.findByCode("Expire");
		Map<String, Object> qiData = new HashMap<>();
		qiData.put("id", quartzInfo.getId());

		ClQuartzLog quartzLog = new ClQuartzLog();
		quartzLog.setQuartzId(quartzInfo.getId());
		quartzLog.setStartTime(new Date());
		try {
			String remark = getSoonExpireRepayPlan();
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
