package com.hwc.framework.modules.listener;

import cn.freesoft.utils.FsUtils;
import com.hwc.framework.modules.model.ClQuartzInfo;
import com.hwc.framework.modules.model.QuartzInfoModel;
import com.hwc.framework.modules.service.QuartzInfoService;
import com.hwc.framework.utils.QuartzManager;
import com.hwc.framework.utils.SpringContextHolder;
import com.hwc.framework.utils.SpringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by   on 2017/11/22.
 */
@Service
public class QuartzListener implements ApplicationListener<ContextRefreshedEvent> {
    private static Logger logger= Logger.getLogger(QuartzListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("【启动所有任务】开始...");
        try {
            QuartzInfoService quartzInfoService = SpringContextHolder.getBean(QuartzInfoService.class);
            // 查询启用状态的定时任务信息
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("state", QuartzInfoModel.STATE_ENABLE);
            List<ClQuartzInfo> list = quartzInfoService.list(paramMap);

            // 循环添加任务
            for (ClQuartzInfo quartzInfo : list) {
                String clName = quartzInfo.getClassName();
                try{
                    Object cl = Class.forName(clName).newInstance();
                    QuartzManager.addJob(quartzInfo.getCode(), cl.getClass(),quartzInfo.getCycle());
                }catch (ClassNotFoundException e){
                    logger.warn("定时任务类"+clName+"不存在");
                }
            }

            // 启动所有定时任务
            QuartzManager.startJobs();

        } catch (Exception e) {
            logger.error("启动定时任务异常--->" + e.getMessage(), e);
        }
        logger.info("【启动所有任务】结束...");
    }

}
