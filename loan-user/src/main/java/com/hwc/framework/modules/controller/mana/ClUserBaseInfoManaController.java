package com.hwc.framework.modules.controller.mana;

import cn.freesoft.utils.FsUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.service.ClUserBaseInfoService;
import com.hwc.framework.modules.utils.Base64;
import com.hwc.framework.modules.utils.HttpUtil;
import com.hwc.framework.modules.utils.OSSUtils;
import com.hwc.framework.modules.utils.PathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 2017/10/23.
 */
@RestController
@RequestMapping("/mana/userBaseInfo")
public class ClUserBaseInfoManaController {
    private static final Logger logger = LoggerFactory.getLogger(ClUserBaseInfoManaController.class);

    @Autowired
    private ClUserBaseInfoService clUserBaseInfoService;

    @PostMapping("/contacts")
    public Response listPage(@RequestParam(value = "info", required = true) String encodedInfo,
                             @RequestParam(value = "userId", required = true) String userId) {
        logger.info("通信录:userId:" + userId + ":" + encodedInfo);
        try {
            OSSUtils ossUtils = new OSSUtils();
            String info = new String(Base64.decode(encodedInfo));
            //logger.info("通信录:userId:" + userId + ":" + encodedInfo);
            String path = "contacts/" + userId + ".json";
            String fullPath = PathUtil.getFullResPath(path);
            String ctx = HttpUtil.doGet1(fullPath);
            if (FsUtils.strsEmpty(ctx)) {
                ossUtils.upFile(info, "contacts/", userId);
            } else {
                JSONArray array = JSONObject.parseArray(ctx);
                Map<String, JSONObject> map = new HashMap<>();
                for (Object o : array) {
                    JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(o));
                    map.put(jsonObject.getString("phone"), jsonObject);
                }
                JSONArray infoArray = JSON.parseArray(info);
                for (Object obj : infoArray) {
                    JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(obj));
                    if (!map.containsKey(jsonObject.getString("phone"))) {
                        array.add(jsonObject);
                    }
                }
                ossUtils.upFile(array.toJSONString(), "contacts/", userId);
            }
        } catch (Exception ex) {
            FsUtils.log_error(ex);
        }
        return Response.success();
    }

}
