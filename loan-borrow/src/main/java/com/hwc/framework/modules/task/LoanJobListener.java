package com.hwc.framework.modules.task;

import cn.freesoft.utils.FsUtils;
import com.hwc.framework.common.BeanUtil;
import com.hwc.framework.modules.model.ClQuartzInfo;
import com.hwc.framework.modules.service.ClBorrowRepayService;
import com.hwc.framework.modules.service.ClQuartzInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.auditing.IsNewAwareAuditingHandler;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import sun.reflect.generics.tree.VoidDescriptor;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by   on 2017/11/22.
 */
@Service
public class LoanJobListener implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger logger = LoggerFactory.getLogger(LoanJobListener.class);
    @Autowired
    private TaskScheduler scheduler;

    @Autowired
    private ClBorrowRepayService repayService;
    @Autowired
    private QuartzOverdueing overdueing;
    @Autowired
    private QuartzOverdueRemind remind;
    @Autowired
    private ClQuartzInfoService quartzInfoService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        init();
    }

    //@PostConstruct
    public void init() {

        List<ClQuartzInfo> infos = quartzInfoService.getQuartzService();
        if (FsUtils.strsNotEmpty(infos) && !infos.isEmpty()) {
            for (ClQuartzInfo info : infos) {
                CronTrigger trigger = new CronTrigger(info.getCycle());
                try{
                    final BorrowJob job1 = BeanUtil.getBean(info.getCode());
                    if(job1 != null) {
                        scheduler.schedule(new Runnable() {
                            @Override
                            public void run() {
                                job1.execute();
                            }
                        }, trigger);
                    }
                }catch (Throwable e){
                    logger.warn("定时任务启动失败",e);
                }
            }
        }

//        CronTrigger trigger1 = new CronTrigger("0 9 10 * * ?");
//        scheduler.schedule(new Runnable() {
//            @Override
//            public void run() {
//                overdueing.execute();
//            }
//        }, trigger);
//        scheduler.schedule(new Runnable() {
//            @Override
//            public void run() {
//                remind.execute();
//            }
//        }, trigger1);
    }

}
