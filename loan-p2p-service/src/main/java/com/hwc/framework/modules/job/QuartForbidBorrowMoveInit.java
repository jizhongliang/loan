package com.hwc.framework.modules.job;

import com.hwc.framework.modules.domain.BorrowerUserinfo;
import com.hwc.framework.modules.model.ClQuartzInfo;
import com.hwc.framework.modules.model.ClQuartzLog;
import com.hwc.framework.modules.service.BorrowerUserinfoService;
import com.hwc.framework.modules.service.QuartzInfoService;
import com.hwc.framework.modules.service.QuartzLogService;
import com.hwc.framework.utils.SpringContextHolder;
import org.apache.commons.beanutils.BeanUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 定时任务--借款人信息同步
 * 
 * @author pippo
 *
 */
@Component
@Lazy(value = false)
public class QuartForbidBorrowMoveInit implements Job {

	private static final Logger logger = LoggerFactory.getLogger(QuartForbidBorrowMoveInit.class);

	public String synBorrowerUserinfo() {
		logger.info("借款人信息同步--开始...");
		BorrowerUserinfoService borrowerUserinfoService = SpringContextHolder.getBean(BorrowerUserinfoService.class);
		ClQuartzInfo info = borrowerUserinfoService.synBorrowerUserinfo();
		logger.info("借款人信息同步--完成...");
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
		ClQuartzInfo quartzInfo = quartzInfoService.findByCode("doQuartForbidBorrowMoveInit");
		Map<String, Object> qiData = new HashMap<>();
		qiData.put("id", quartzInfo.getId());

		ClQuartzLog quartzLog = new ClQuartzLog();
		quartzLog.setQuartzId(quartzInfo.getId());
		quartzLog.setStartTime(new Date());
		try {
			String remark = synBorrowerUserinfo();
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
