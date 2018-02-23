package com.hwc.framework.modules.job;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.hwc.framework.config.SdkConfig;
import com.hwc.framework.modules.model.ArcSysConfig;
import com.hwc.framework.modules.model.CLBorrowRepay;
import com.hwc.framework.modules.model.ClQuartzInfo;
import com.hwc.framework.modules.model.ClQuartzLog;
import com.hwc.framework.modules.service.ArcSysConfigService;
import com.hwc.framework.modules.service.BorrowRepayService;
import com.hwc.framework.modules.service.QuartzInfoService;
import com.hwc.framework.modules.service.QuartzLogService;
import com.hwc.framework.utils.SpringContextHolder;
import com.hwc.loan.sdk.borrow.request.BaofuRepayRequest;
import com.hwc.loan.sdk.borrow.response.BaofuRepayResponse;

/**
 * 宝付代扣定时任务
 * 
 * @author
 *
 */
@Component
@Lazy(value = false)
public class QuartzBaofoo implements Job {
	
	
	private static final Logger logger = LoggerFactory.getLogger(QuartzBaofoo.class);

	public String repayment() {

		String quartzRemark = null;
		AtomicInteger succeed = new AtomicInteger(0);
		AtomicInteger fail = new AtomicInteger(0);
		AtomicInteger total = new AtomicInteger(0);

		BorrowRepayService borrowRepayService = SpringContextHolder.getBean(BorrowRepayService.class);
		//SpringContextHolder.getBean(userClient);
		ArcSysConfigService arcSysConfigService=SpringContextHolder.getBean(ArcSysConfigService.class);
		ArcSysConfig config=arcSysConfigService.getSysConfigByCode("baofuUserId");
		Map<String, Object> param=new HashMap<>();
		param.put("clBorrowType", "D");
		param.put("userId", config.getValue());
		logger.info("Baofu params >>"+param);
		// 查询可以代扣的list集合
		List<CLBorrowRepay> list = borrowRepayService.queryCanPayOrder(param); // ???
		logger.info("baofu List >> "+list.size());

		/* 创建一个大小为10的线程池 */
		final int threadCount = 10;
		final int threadTaskCount = list.size() / threadCount + 1;
		ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
		final CountDownLatch latch = new CountDownLatch(threadCount);
		for (int i = 0; i < threadCount; i++) {
			final int index = i;
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					int threadStart = threadTaskCount * index;
					CLBorrowRepay clBorrowRepay = null;
					while (threadStart < Math.min(threadTaskCount * (index + 1), list.size())) {
						try {
							clBorrowRepay = list.get(threadStart++);
							String  response = borrowRepayService.repay(clBorrowRepay);
							JSONObject json=(JSONObject) JSONObject.parse(response);
							logger.info( "宝付扣款》》" +JSONObject.toJSONString(clBorrowRepay));
							logger.info("reponse  >>"+response);
							if (json.getBoolean("success")) {
								logger.info("success  >> 宝付代扣"+clBorrowRepay.getId());
								//logger.info("response >>"+response.toString());
								succeed.incrementAndGet();
								total.incrementAndGet();
							} else {
								logger.info("fail  >>  宝付代扣"+clBorrowRepay.getId());
								//logger.info("response >>"+response.toString());
								fail.incrementAndGet();
								total.incrementAndGet();
							}
						} catch (Exception e) {
							e.printStackTrace();
							fail.incrementAndGet();
							total.incrementAndGet();
						}
					}

					latch.countDown();
				}
			});
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			executorService.shutdown();
		}
		logger.info("自动扣款完成...");
		quartzRemark = "执行总次数" + total.get() + ",成功" + succeed.get() + "次,失败" + fail.get() + "次";
		return quartzRemark;
	}

	// 宝付定时代扣
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		QuartzInfoService quartzInfoService = SpringContextHolder.getBean(QuartzInfoService.class);
		QuartzLogService quartzLogService = SpringContextHolder.getBean(QuartzLogService.class);
		// 查询当前任务信息
		logger.info("QuartzBaofu>>>>>定时任务");
		ClQuartzInfo quartzInfo = quartzInfoService.findByCode("bofuRepayment");
		Map<String, Object> qiData = new HashMap<>();
		qiData.put("id", quartzInfo.getId());

		ClQuartzLog quartzLog = new ClQuartzLog();
		quartzLog.setQuartzId(quartzInfo.getId());
		quartzLog.setStartTime(new Date());
		try {
			String remark = repayment();
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
