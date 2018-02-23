package com.hwc.framework.modules.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.model.ClQuartzInfo;
import com.hwc.framework.modules.model.QuartzInfoModel;
import com.hwc.framework.modules.service.QuartzInfoService;
import com.hwc.framework.utils.QuartzManager;
import com.hwc.framework.utils.StringUtils;
import com.hwc.loan.sdk.quzrtz.request.QuartzAddOneRequest;
import com.hwc.loan.sdk.quzrtz.request.QuartzDisabledRequest;
import com.hwc.loan.sdk.quzrtz.request.QuartzExecuteRequest;
import com.hwc.loan.sdk.quzrtz.request.QuartzListPageRequest;
import com.hwc.loan.sdk.quzrtz.request.QuartzLoadRequest;
import com.hwc.loan.sdk.quzrtz.request.QuartzRunRequest;
import com.hwc.loan.sdk.quzrtz.request.QuartzUpdateRequest;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/p2p/manager/quartz")
@Api(tags = "定时任务管理")
public class QuartzInfoController {

    private static final Logger logger = Logger.getLogger(QuartzInfoController.class);

    @Autowired
    private QuartzInfoService   quartzInfoService;

    /**
     * 定时任务列表
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "page")
    public Response page(@RequestBody QuartzListPageRequest request) throws Exception {

        Map<String, Object> searchMap = new HashMap<String, Object>();

        Page<QuartzInfoModel> page = quartzInfoService.page(searchMap, request.getPage(),
            request.getPageSize());
        return Response.success(page);
    }

    /**
     * 添加定时任务
     * @throws Exception
     */
    @RequestMapping(value = "addition")
    public Response addition(@RequestBody QuartzAddOneRequest request) throws Exception {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("code", request.getCode());
        ClQuartzInfo qzInfo = quartzInfoService.findSelective(paramMap);

        if (!QuartzManager.isValidExpression(request.getCode(), request.getCycle())) {
            return Response.fail("cron配置不正确");
        }

        boolean flag = false;
        if (qzInfo == null) {
            qzInfo = new ClQuartzInfo();
            qzInfo.setName(request.getName());
            qzInfo.setCode(request.getCode());
            qzInfo.setCycle(request.getCycle());
            qzInfo.setClassName(request.getClassName());
            qzInfo.setSucceed(0);
            qzInfo.setFail(0);
            qzInfo.setState(QuartzInfoModel.STATE_DISABLE);
            qzInfo.setCreateTime(new Date());
            flag = quartzInfoService.save(qzInfo);
            if (flag) {
                return Response.success();
            }
        } else {
            return Response.fail("任务已存在,请勿重复添加");
        }

        return Response.fail();
    }

    /**
     * 启动定时任务
     * @throws Exception
     */
    @RequestMapping(value = "execute")
    public Response execute(@RequestBody QuartzExecuteRequest request) {
        logger.info("【启动任务】开始...");

        boolean flag = true;
        Object cl = null;

        ClQuartzInfo quartzInfo = quartzInfoService.getById(request.getId());
        if (null == quartzInfo || StringUtils.isBlank(quartzInfo.getClassName())) {
            flag = false;
        }

        // 任务执行类实例化
        if (flag) {
            try {
                cl = Class.forName(quartzInfo.getClassName()).newInstance();
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                logger.info("定时任务启动异常...", e);
                flag = false;
            }
        }

        if (flag) {
            // 任务添加
            QuartzManager.addJob(quartzInfo.getCode(), cl.getClass(), quartzInfo.getCycle());

            // 数据库状态更新
            Map<String, Object> data = new HashMap<>();
            data.put("id", quartzInfo.getId());
            data.put("state", QuartzInfoModel.STATE_ENABLE);
            flag = quartzInfoService.update(data);
        }

        logger.info("【启动任务】结束...");
        if (flag) {
            return Response.success();
        }
        return Response.fail();
    }

    /**
     * 禁用定时任务
     * @throws Exception
     */
    @RequestMapping(value = "delete")
    public Response delete(@RequestBody QuartzDisabledRequest request) throws Exception {
        logger.info("【删除任务】开始...");

        ClQuartzInfo quartzInfo = quartzInfoService.getById(request.getId());
        if (null != quartzInfo && QuartzInfoModel.STATE_ENABLE.equals(quartzInfo.getState())) {
            QuartzManager.removeJob(quartzInfo.getCode());
        }

        Map<String, Object> data = new HashMap<>();
        data.put("id", request.getId());
        data.put("state", QuartzInfoModel.STATE_DISABLE);
        boolean flag = quartzInfoService.update(data);
        logger.info("【删除任务】结束...");
        if (flag) {
            return Response.success();

        }
        return Response.fail();
    }

    /**
     * 修改定时任务
     * @throws Exception
     */
    @RequestMapping(value = "update")
    public Response update(@RequestBody QuartzUpdateRequest request) throws Exception {
        logger.info("【修改任务】开始...");
        ClQuartzInfo quartzInfo = quartzInfoService.getById(request.getId());
        if (!QuartzManager.isValidExpression(quartzInfo.getCode(), request.getCycle())) {
            return Response.fail("cron配置不正确");
        }
        if (null != quartzInfo && QuartzInfoModel.STATE_ENABLE.equals(quartzInfo.getState())) {
            QuartzManager.modifyJobTime(quartzInfo.getCode(), request.getCycle());
        }
        Map<String, Object> data = new HashMap<>();
        data.put("id", request.getId());
        data.put("name", request.getName());
        data.put("cycle", request.getCycle());
        boolean flag = quartzInfoService.update(data);
        logger.info("【修改任务】结束...");
        Map<String, Object> result = new HashMap<String, Object>();
        if (flag) {
            return Response.success();
        }
        return Response.fail();
    }

    /**
     * 关闭所有定时任务 
     * @throws Exception
     */
    @RequestMapping(value = "shutdownJobs")
    public Response shutdownJobs() throws Exception {
        try {
            logger.info("【关闭所有任务】开始...");
            QuartzManager.shutdownJobs();
            logger.info("【关闭所有任务】结束...");
        } catch (Throwable e) {
            return Response.fail();
        }
        return Response.success();
    }

    /**
     * 立即执行一个任务
     * @throws Exception
     */
    @RequestMapping(value = "runJobNow")
    public Response runJobNow(@RequestBody QuartzRunRequest request) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        logger.info("【执行】开始...");
        ClQuartzInfo quartzInfo = quartzInfoService.getById(request.getId());

        if (null == quartzInfo) {
            return Response.fail("任务不存在");
        } else if (QuartzInfoModel.STATE_ENABLE.equals(quartzInfo.getState())) {
            return Response.fail("只能禁用状态下可以立即执行");
        }
        try {
            Object cl = Class.forName(quartzInfo.getClassName()).newInstance();
            QuartzManager.addJob(quartzInfo.getCode(), cl.getClass(), quartzInfo.getCycle());
            // 数据库状态更新
            Map<String, Object> data = new HashMap<>();
            data.put("id", quartzInfo.getId());
            data.put("state", QuartzInfoModel.STATE_ENABLE);
            quartzInfoService.update(data);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            logger.info("定时任务启动异常...", e);
        }

        QuartzManager.startJobNow(quartzInfo.getCode());
        logger.info("【执行任务】结束...");
        return Response.success();
    }

    /**
     * 获取一个任务
     * @throws Exception
     */
    @RequestMapping(value = "load")
    public Response load(@RequestBody QuartzLoadRequest request) throws Exception {
        ClQuartzInfo quartzInfo = quartzInfoService.getById(request.getId());
        if (null == quartzInfo) {
            return Response.fail("任务不存在");
        }
        return Response.success(quartzInfo);
    }

}
