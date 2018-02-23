package com.hwc.framework.modules.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.google.common.eventbus.EventBus;
import com.hwc.base.api.IdRequest;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.DCloanUserModel;
import com.hwc.framework.modules.domain.DUser;
import com.hwc.framework.modules.domain.DUserAuth;
import com.hwc.framework.modules.domain.DUserAuthData;
import com.hwc.framework.modules.domain.DUserBaseInfo;
import com.hwc.framework.modules.domain.DUserEmerContacts;
import com.hwc.framework.modules.service.ClUserAuthDataService;
import com.hwc.framework.modules.service.ClUserAuthService;
import com.hwc.framework.modules.service.ClUserBaseInfoService;
import com.hwc.framework.modules.service.ClUserEmerContactsService;
import com.hwc.framework.modules.service.ClUserService;
import com.hwc.framework.modules.service.CloanUserService;
import com.hwc.framework.modules.third.util.EventTypeUtil;
import com.hwc.framework.modules.third.util.JsoupUtile;
import com.hwc.framework.modules.utils.OSSUtils;

import cn.freesoft.utils.FsUtils;

/**
 * 2017/10/26.
 */
@RestController
@RequestMapping("/api/user/auth")
public class ClUserAuthController {
    private static final Logger logger                 = LoggerFactory
        .getLogger(ClUserAuthController.class);

    private static final String HEADER_MOXIE_EVENT     = "X-Moxie-Event";

    private static final String HEADER_MOXIE_TYPE      = "X-Moxie-Type";

    private static final String HEADER_MOXIE_SIGNATURE = "X-Moxie-Signature";

    @Value("${moxie.api.baseUrl}")
    private String              baseUrl;

    @Value("${moxie.apiKey}")
    private String              moxieApiKey;

    @Value("${moxie.signature.secret}")
    private String              secret;

    @Value("${moxie.zhengxin.html}")
    private String              zhengxinHtml;

    @Value("${moxie.carrier.html}")
    private String              carrierHtml;

    @Value("${moxie.backUrl}")
    private String              backUrl;

    @Autowired
    private OSSUtils            ossUtils;

    private static ObjectMapper objectMapper           = new ObjectMapper();

    private EventBus            eventBus;

    @Autowired
    public ClUserAuthController(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Autowired
    private ClUserAuthService         clUserAuthService;

    @Autowired
    private ClUserService             clUserService;

    @Autowired
    private ClUserBaseInfoService     clUserBaseInfoService;

    @Autowired
    private ClUserEmerContactsService clUserEmerContactsService;

    @Autowired
    private ClUserAuthDataService     clUserAuthDataService;

    @Autowired
    private CloanUserService          cloanUserService;

    /**
     *  获取用户验证状态详情
     *
     */
    @PostMapping("/getAuth")
    public Response getAuth(@RequestBody IdRequest<Long> request) {
        Response response = this.clUserAuthService.getUserAuth(request.getId());
        return response;
    }

    /**
     *  获取用户验证状态结果 T/F
     *
     */
    @PostMapping("/getAuthState")
    public Response getAuthState(@RequestBody IdRequest<Long> request) {
        Response response = this.clUserAuthService.getUserAuthState(request.getId());
        return response;
    }

    /**
     *  获取用户工作信息
     *
     */
    @PostMapping("/getWorkInfo")
    public Response getWorkInfo(@RequestBody IdRequest<Long> request) {
        Response response = clUserBaseInfoService.getUserWorkInfo(request.getId());
        return response;
    }

    /**
     *  更新用户工作信息
     *
     */
    @PostMapping("/updateWorkInfo")
    public Response updateWorkInfo(@RequestBody DUserBaseInfo request) {
        Response response = clUserBaseInfoService.updateUserWorkInfo(request);
        return response;
    }

    /**
     *  联系人信息
     *
     */
    @PostMapping("/updateEmerContacts")
    public Response updateEmerContacts(@RequestBody DUserEmerContacts request) {
        Response response = clUserEmerContactsService.updateUserEmerContacts(request);
        return response;
    }

    /**
     *  获取联系人信息
     *
     */
    @PostMapping("/getEmerContacts")
    public Response getEmerContacts(@RequestBody DUserEmerContacts request) {
        List<DUserEmerContacts> list = clUserEmerContactsService
            .findUserEmerContactsByUserId(request.getUserId());
        return Response.success(list);
    }

    /**
     *  更新银行卡认证信息
     *
     */
    @PostMapping("/updateBankCardState")
    public Response updateBankCardState(@RequestBody DUserAuth dUserAuth) {
        Response response = clUserAuthService.updateUserBankCardState(dUserAuth);
        return response;
    }

    /**
     *  实名认证
     *
     */
    @PostMapping("/authentication")
    public Response authentication(@RequestBody DUserBaseInfo dUserBaseInfo) {

        Response response = this.clUserBaseInfoService.authenticationUserBaseInfo(dUserBaseInfo);
        return response;
    }

    /**
     * h5车位实名认证
     * @param dUserBaseInfo
     * @return
     */
    @PostMapping("/parkingAuthentication")
    public Response parkingAuthentication(@RequestBody DUserBaseInfo dUserBaseInfo) {
        Response response = this.clUserBaseInfoService.authenticationParking(dUserBaseInfo);
        return response;
    }

    /**
     * 
     * @param dUserBaseInfo
     * @return
     */
    @PostMapping("/authenticationCw")
    public Response authenticationCw(@RequestBody DUserBaseInfo dUserBaseInfo) {
        Response response = this.clUserBaseInfoService.authenticationCw(dUserBaseInfo);
        return response;
    }

    /**
     *  是否启用人行征信
     *
     */
    @PostMapping("/isUseCredit")
    public Response isUseCredit() {
        Response response = this.clUserBaseInfoService.isUseCredit();
        return response;
    }

    /**
     *  获取人行征信URL
     *
     */
    @PostMapping("/getCreditUrl")
    public Response getCreditUrl(@RequestBody DUser request) {
        String creditUrl = "", url = "";
        if (request != null && !FsUtils.strsEmpty(request.getId())) {
            DUser dUser = this.clUserService.getUserById(request.getId());
            if (dUser != null) {
                //
                try {
                    url = URLEncoder.encode(this.backUrl, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                creditUrl = baseUrl + "/h5/importV3/#/zhengxin?apiKey=" + moxieApiKey + "&userId="
                            + dUser.getUuid() + "&backUrl=" + url;
            } else {
                return Response.fail("用户不存在");
            }
        } else {
            return Response.fail("参数错误");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("creditUrl", creditUrl);
        return Response.success(map);
    }

    @PostMapping("/notifications")
    public void notifications(@RequestBody String body, ServletRequest request,
                              ServletResponse response) {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String eventName = httpServletRequest.getHeader(HEADER_MOXIE_EVENT); // 事件类型：task or bill

        String eventType = httpServletRequest.getHeader(HEADER_MOXIE_TYPE); // 业务类型：email、bank、carrier 等

        String signature = httpServletRequest.getHeader(HEADER_MOXIE_SIGNATURE); // body签名

        logger.info("request body:" + body);

        if (Strings.isNullOrEmpty(eventName)) {
            writeMessage(httpServletResponse, HttpServletResponse.SC_BAD_REQUEST,
                "header not found:" + HEADER_MOXIE_EVENT);
            return;
        }

        if (Strings.isNullOrEmpty(eventType)) {
            writeMessage(httpServletResponse, HttpServletResponse.SC_BAD_REQUEST,
                "header not found:" + HEADER_MOXIE_TYPE);
            return;
        }

        if (Strings.isNullOrEmpty(signature)) {
            writeMessage(httpServletResponse, HttpServletResponse.SC_BAD_REQUEST,
                "header not found:" + HEADER_MOXIE_SIGNATURE);
            return;
        }

        if (Strings.isNullOrEmpty(body)) {
            writeMessage(httpServletResponse, HttpServletResponse.SC_BAD_REQUEST,
                "request body is empty");
            return;
        }

        JSONObject json = JSONObject.parseObject(body);
        String user_id = json.getString("user_id");
        String userId = user_id;
        DUser dUser = clUserService.getUserByUuid(user_id);
        if (dUser != null) {
            userId = dUser.getId().toString();
        }

        // 用户资信报告通知
        if (StringUtils.equals(eventName.toLowerCase(), "apply")) {
            logger.info("用户:" + user_id + "已经申请人行报告:");
            // 通知状态变更为 '认证中'
            this.updateCreditState(user_id, "20");
            // 返回魔蝎，收到回调通知
            writeMessage(httpServletResponse, HttpServletResponse.SC_CREATED, "apply success");
        }

        // 创建任务通知
        if (StringUtils.equals(eventName.toLowerCase(), "task.submit")) {
            logger.info("用户:" + user_id + " 创建任务");
            // 返回魔蝎，收到回调通知
            writeMessage(httpServletResponse, HttpServletResponse.SC_CREATED,
                "task.submit success");
        }

        // 任务授权登录结果通知
        if (StringUtils.equals(eventName.toLowerCase(), "task")) {
            logger.info("用户:" + user_id + " 报告验证码 进行验证");
            try {
                Map<String, ?> map = objectMapper.readValue(body, Map.class);
                if (map.containsKey("result")) {
                    String result = map.get("result").toString();
                    if (StringUtils.equals(result, "false")) {
                        if (map.containsKey("message")) {
                            String message = map.get("message") == null ? "未知异常"
                                : map.get("message").toString();
                            // 返回魔蝎，收到回调通知
                            writeMessage(httpServletResponse, HttpServletResponse.SC_CREATED,
                                "task success");
                            logger.info("task event. result={}, message={}", result, message);
                            return;
                        }
                    }
                }
                writeMessage(httpServletResponse, HttpServletResponse.SC_CREATED, "task success");
            } catch (Exception e) {
                logger.error("body convert to object error", e);
            }
        }

        if (StringUtils.equals(eventName.toLowerCase(), "task.fail")) {
            logger.info("用户:" + user_id + " 任务过程中的失败");
            try {
                Map<String, ?> map = objectMapper.readValue(body, Map.class);
                if (map.containsKey("result") && map.containsKey("message")) {
                    String result = map.get("result").toString();
                    String message = map.get("message") == null ? "未知异常"
                        : map.get("message").toString();
                    if (StringUtils.equals(result, "false")) {
                        writeMessage(httpServletResponse, HttpServletResponse.SC_CREATED,
                            "task.fail success");
                        logger.info("task fail event. result={}, message={}", result, message);
                    }
                }
                this.updatePhoneState(user_id, "10");
                writeMessage(httpServletResponse, HttpServletResponse.SC_CREATED,
                    "task.fail success");
            } catch (Exception e) {
                logger.error("body convert to object error", e);
            }
        }

        logger.info("event name:" + eventName.toLowerCase());
        // 任务完成的通知处理，其中qq联系人的通知为sns，其它的都为bill
        if (StringUtils.equals(eventName.toLowerCase(), "bill")
            || StringUtils.equals(eventName.toLowerCase(), "allbill")
            || StringUtils.equals(eventName.toLowerCase(), "sns")) {
            logger.info("用户:" + user_id + " 任务完成");
            // 认证完成
            this.updateCreditState(user_id, "30");
            // 返回魔蝎，收到回调通知
            writeMessage(httpServletResponse, HttpServletResponse.SC_CREATED, "success");
            try {
                // 调用后续的数据查询接口获取数据
                Object object = objectMapper.readValue(body,
                    EventTypeUtil.getEventType(eventType.toLowerCase()));
                eventBus.post(object);
            } catch (Exception e) {
                logger.error("body convert to object error", e);
            }
        }

        // 用户资信报告通知
        if (StringUtils.equals(eventName.toLowerCase(), "report")) {
            logger.info("用户:" + user_id + " 用户资信报告通知");
            writeMessage(httpServletResponse, HttpServletResponse.SC_CREATED, "success");
            try {
                String message = json.getString("message");
                //
                String htmlUrl = zhengxinHtml + message;
                logger.info("报告地址:" + htmlUrl);
                String htmPat = JsoupUtile.getZhenXingHtml(htmlUrl, ossUtils, userId);
                // 保存
                DUserAuthData dUserAuthData = new DUserAuthData();
                dUserAuthData.setUserId(dUser.getId());
                dUserAuthData.setZhengxinReport(htmPat);
                clUserAuthDataService.updateUserAuthDataForzhengxin(dUserAuthData);
            } catch (Exception e) {
                logger.error("body convert to object error", e);
            }
        }
    }

    /**
     *  获取运营商报告URL
     *
     */
    @PostMapping("/getCarrierUrl")
    public Response getCarrierUrl(@RequestBody DUser request) {
        String carrierUrl = "", params = "", url = "";
        if (request != null && !FsUtils.strsEmpty(request.getId())) {
            DCloanUserModel response = this.cloanUserService.getCloanUserOne(request.getId());
            if (response != null) {
                carrierUrl = baseUrl + "/h5/importV3/index.html#/carrier?apiKey=" + moxieApiKey
                             + "&userId=" + response.getUuid();
                //
                JSONObject param = new JSONObject();
                param.put("phone", response.getPhone());
                param.put("name", response.getRealName());
                param.put("idcard", response.getIdNo());
                //
                try {
                    params = URLEncoder.encode(param.toString(), "UTF-8");
                    url = URLEncoder.encode(this.backUrl, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                //
                carrierUrl = carrierUrl + "&loginParams=" + params + "&backUrl=" + url;
            } else {
                return Response.fail("用户不存在");
            }
        } else {
            return Response.fail("参数错误");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("carrierUrl", carrierUrl);
        return Response.success(map);
    }

    @PostMapping("/notificationsCarrier")
    public void notificationsCarrier(@RequestBody String body, ServletRequest request,
                                     ServletResponse response) {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String eventName = httpServletRequest.getHeader(HEADER_MOXIE_EVENT); // 事件类型：task or bill

        String eventType = httpServletRequest.getHeader(HEADER_MOXIE_TYPE); // 业务类型：email、bank、carrier 等

        String signature = httpServletRequest.getHeader(HEADER_MOXIE_SIGNATURE); // body签名

        logger.info("request body:" + body);

        if (Strings.isNullOrEmpty(eventName)) {
            writeMessage(httpServletResponse, HttpServletResponse.SC_BAD_REQUEST,
                "header not found:" + HEADER_MOXIE_EVENT);
            return;
        }

        if (Strings.isNullOrEmpty(eventType)) {
            writeMessage(httpServletResponse, HttpServletResponse.SC_BAD_REQUEST,
                "header not found:" + HEADER_MOXIE_TYPE);
            return;
        }

        if (Strings.isNullOrEmpty(signature)) {
            writeMessage(httpServletResponse, HttpServletResponse.SC_BAD_REQUEST,
                "header not found:" + HEADER_MOXIE_SIGNATURE);
            return;
        }

        if (Strings.isNullOrEmpty(body)) {
            writeMessage(httpServletResponse, HttpServletResponse.SC_BAD_REQUEST,
                "request body is empty");
            return;
        }

        JSONObject json = JSONObject.parseObject(body);
        String user_id = json.getString("user_id");
        String userId = user_id;
        DUser dUser = clUserService.getUserByUuid(user_id);
        if (dUser != null) {
            userId = dUser.getId().toString();
        }

        // 用户资信报告通知
        if (StringUtils.equals(eventName.toLowerCase(), "apply")) {
            logger.info("用户:" + user_id + "申请运营商 报告:");
            // 通知状态变更为 '认证中'
            //            this.updateState(user_id,"20");
            // 返回魔蝎，收到回调通知
            writeMessage(httpServletResponse, HttpServletResponse.SC_CREATED, "apply success");
        }

        // 创建任务通知
        if (StringUtils.equals(eventName.toLowerCase(), "task.submit")) {
            logger.info("用户:" + user_id + "  运营商 创建任务");
            // 返回魔蝎，收到回调通知
            this.updatePhoneState(user_id, "20");
            writeMessage(httpServletResponse, HttpServletResponse.SC_CREATED,
                "task.submit success");
        }

        // 任务授权登录结果通知
        if (StringUtils.equals(eventName.toLowerCase(), "task")) {
            logger.info("用户:" + user_id + " 运营商 进行验证");
            try {
                Map<String, ?> map = objectMapper.readValue(body, Map.class);
                if (map.containsKey("result")) {
                    String result = map.get("result").toString();
                    if (StringUtils.equals(result, "false")) {
                        if (map.containsKey("message")) {
                            String message = map.get("message") == null ? "未知异常"
                                : map.get("message").toString();
                            // 返回魔蝎，收到回调通知
                            writeMessage(httpServletResponse, HttpServletResponse.SC_CREATED,
                                "task success");
                            logger.info("task event. result={}, message={}", result, message);
                            return;
                        }
                    }
                }
                writeMessage(httpServletResponse, HttpServletResponse.SC_CREATED, "task success");
            } catch (Exception e) {
                logger.error("body convert to object error", e);
            }
        }

        if (StringUtils.equals(eventName.toLowerCase(), "task.fail")) {
            logger.info("用户:" + user_id + " 运营商 任务过程中的失败");
            try {
                Map<String, ?> map = objectMapper.readValue(body, Map.class);
                if (map.containsKey("result") && map.containsKey("message")) {
                    String result = map.get("result").toString();
                    String message = map.get("message") == null ? "未知异常"
                        : map.get("message").toString();
                    if (StringUtils.equals(result, "false")) {
                        writeMessage(httpServletResponse, HttpServletResponse.SC_CREATED,
                            "task.fail success");
                        logger.info("task fail event. result={}, message={}", result, message);
                    }
                }
                this.updatePhoneState(user_id, "10");
                writeMessage(httpServletResponse, HttpServletResponse.SC_CREATED,
                    "task.fail success");
            } catch (Exception e) {
                logger.error("body convert to object error", e);
            }
        }

        logger.info("event name:" + eventName.toLowerCase());
        // 任务完成的通知处理，其中qq联系人的通知为sns，其它的都为bill
        if (StringUtils.equals(eventName.toLowerCase(), "bill")
            || StringUtils.equals(eventName.toLowerCase(), "allbill")
            || StringUtils.equals(eventName.toLowerCase(), "sns")) {
            logger.info("用户:" + user_id + " 运营商 任务完成");
            // 认证完成
            this.updatePhoneState(user_id, "30");
            // 返回魔蝎，收到回调通知
            writeMessage(httpServletResponse, HttpServletResponse.SC_CREATED, "success");
            try {
                // 调用后续的数据查询接口获取数据
                Object object = objectMapper.readValue(body,
                    EventTypeUtil.getEventType(eventType.toLowerCase()));
                eventBus.post(object);
            } catch (Exception e) {
                logger.error("body convert to object error", e);
            }
        }

        // 用户资信报告通知
        if (StringUtils.equals(eventName.toLowerCase(), "report")) {
            logger.info("用户:" + user_id + " 用户运营商报告通知");
            writeMessage(httpServletResponse, HttpServletResponse.SC_CREATED, "success");
            try {
                String message = json.getString("message");
                logger.info("message:" + message);
                String htmlUrl = carrierHtml + message;
                String htmPath = JsoupUtile.getPhoneHtml(htmlUrl, ossUtils, userId);
                // 保存
                DUserAuthData dUserAuthData = new DUserAuthData();
                dUserAuthData.setUserId(dUser.getId());
                dUserAuthData.setPhoneReport(htmPath);
                clUserAuthDataService.updateUserAuthDataForPhone(dUserAuthData);
            } catch (Exception e) {
                logger.error("body convert to object error", e);
            }
        }
    }

    private void updateCreditState(String uuid, String state) {
        DUser dUser = clUserService.getUserByUuid(uuid);
        if (dUser != null) {
            DUserAuth dUserAuth = new DUserAuth();
            dUserAuth.setUserId(dUser.getId());
            dUserAuth.setCreditState(state);
            clUserAuthService.updateUserAuthCreditState(dUserAuth);
        }
    }

    private void updatePhoneState(String uuid, String state) {
        DUser dUser = clUserService.getUserByUuid(uuid);
        if (dUser != null) {
            DUserAuth dUserAuth = new DUserAuth();
            dUserAuth.setUserId(dUser.getId());
            dUserAuth.setPhoneState(state);
            clUserAuthService.updateUserPhoneState(dUserAuth);
        }
    }

    private void writeMessage(HttpServletResponse response, int status, String content) {
        response.setStatus(status);
        try {
            PrintWriter printWriter = response.getWriter();
            printWriter.write(content);
        } catch (IOException ignored) {
        }
    }

    /** 
     * 更新认证项
     */
    @PostMapping("/updateUserState")
    public Response updateUserState(@RequestBody DUserAuth dUserAuth) {
        Response response = clUserAuthService.updateAllAuth(dUserAuth);
        return response;
    }
}
