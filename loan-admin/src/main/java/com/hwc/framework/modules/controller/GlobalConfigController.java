package com.hwc.framework.modules.controller;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.model.ArcSysConfig;
import com.hwc.framework.modules.service.ArcSysConfigService;
import com.hwc.framework.modules.utils.ParamUtil;
import com.hwc.framework.modules.vo.GlobalConfigDataVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**获取全局配置接口
 * Created by jzl on 2018/1/10.
 */
@RestController
@RequestMapping("/api/admin")
public class GlobalConfigController {
    private static Logger logger = LoggerFactory.getLogger(GlobalConfigController.class);

    @Autowired
    private ArcSysConfigService configService;


    @ResponseBody
    @RequestMapping(value = "/get_global_config", method = RequestMethod.POST)
    public Response getGlobalConfig() {
        logger.info("访问全局配置请求接口");

        GlobalConfigDataVo vo = new GlobalConfigDataVo();
        //放还款渠道
        ArcSysConfig channelConfig = configService.getSysConfigByCode("pay_channel");
        if (!ParamUtil.isEmpty(channelConfig)) {
            vo.setPayChannel(channelConfig.getValue());
        }
        logger.info("全局配置接口返回：pay_channel:{}", channelConfig.getValue());
        return Response.success(vo);
    }
}
