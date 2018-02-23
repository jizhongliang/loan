package com.hwc.framework.modules.controller.mana;

import com.github.pagehelper.PageHelper;
import com.hwc.base.api.IdRequest;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.DCloanUserModel;
import com.hwc.framework.modules.domain.DUserAuthModel;
import com.hwc.framework.modules.domain.DUserBaseInfo;
import com.hwc.framework.modules.domain.request.UserAuthModelListRequest;
import com.hwc.framework.modules.domain.request.UserContactsListRequest;
import com.hwc.framework.modules.domain.request.UserModelListRequest;
import com.hwc.framework.modules.service.ClUserAuthService;
import com.hwc.framework.modules.service.ClUserBaseInfoService;
import com.hwc.framework.modules.service.CloanUserService;
import com.hwc.framework.modules.service.UserContactsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2017/10/23.
 */
@RestController
@RequestMapping("/mana/user")
public class UserManaController {
    private static final Logger logger = LoggerFactory.getLogger(UserManaController.class);

    @Autowired
    private CloanUserService cloanUserService;

    @Autowired
    private ClUserBaseInfoService clUserBaseInfoService;

    @Autowired
    private ClUserAuthService clUserAuthService;

    @Autowired
    private UserContactsService userContactsService;


    @PostMapping("/listPage")
    public Response listPage(@RequestBody UserModelListRequest request) {
        PageHelper.startPage(request.getPage(), request.getPageSize());
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("loginName",request.getPhone());
        map.put("idNo",request.getIdNo());
        map.put("cat",request.getCat());
        map.put("registerClient",request.getRegisterClient());
        map.put("realName",request.getRealName());
        map.put("channelId",request.getChannelId());
        map.put("beginTime",request.getBeginTime());
        map.put("endTime",request.getEndTime());
        map.put("state",request.getState());
        List<DCloanUserModel>  list = cloanUserService.listModelPage(map);
        return Response.success(list);
    }

    // 加入、解除黑名单
    @PostMapping("/updateState")
    public Response addBlacklist(@RequestBody DUserBaseInfo dUserBaseInfo) {
        Response response = clUserBaseInfoService.updateUserState(dUserBaseInfo);
        return response;
    }

    // 查看用户通讯录
    @PostMapping("/contacts/list")
    public Response contactsList(@RequestBody UserContactsListRequest request) {
        PageHelper.startPage(request.getPage(), request.getPageSize());
        Response response = userContactsService.getContactsList(request.getUserId(),request.getPage(), request.getPageSize());
        return response;
    }


    // 查看短信
    @PostMapping("/message/list")
    public Response messageList(@RequestBody UserContactsListRequest request) {
        PageHelper.startPage(request.getPage(), request.getPageSize());
        Response response = userContactsService.getMessageList(request.getUserId(),request.getPage(), request.getPageSize());
        return response;
    }

    // 查看用户认证信息

    // 查看用户人行征信

    @PostMapping("/authListPage")
    public Response authListPage(@RequestBody UserAuthModelListRequest request) {
        PageHelper.startPage(request.getPage(), request.getPageSize());
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("loginName",request.getPhone());
        map.put("idNo",request.getIdNo());
        map.put("cat",request.getCat());
        map.put("realName",request.getRealName());
        map.put("idState",request.getIdState());
        map.put("bankCardState",request.getBankCardState());
        map.put("contactState",request.getContactState());
        map.put("workInfoState",request.getWorkInfoState());
        List<DUserAuthModel>  list = clUserAuthService.listModelPage(map);
        return Response.success(list);
    }

    // 查看用户详情
    @PostMapping("/getOneDetail")
    public Response detail(@RequestBody IdRequest<Long> request) {
        Response response = this.clUserBaseInfoService.getUserDetail(request.getId());
        return response;
    }
}
