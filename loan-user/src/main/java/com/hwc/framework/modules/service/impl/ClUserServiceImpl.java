package com.hwc.framework.modules.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hwc.base.api.Response;
import com.hwc.base.sdk.core.Client;
import com.hwc.framework.common.Constant;
import com.hwc.framework.common.UserType;
import com.hwc.framework.modules.dao.ArcSysConfigMapper;
import com.hwc.framework.modules.dao.ClUserMapper;
import com.hwc.framework.modules.domain.DContactsModel;
import com.hwc.framework.modules.domain.DSms;
import com.hwc.framework.modules.domain.DUser;
import com.hwc.framework.modules.domain.DUserAuth;
import com.hwc.framework.modules.domain.DUserBaseInfo;
import com.hwc.framework.modules.domain.DUserModel;
import com.hwc.framework.modules.domain.DUserOtherInfo;
import com.hwc.framework.modules.domain.DWContacts;
import com.hwc.framework.modules.domain.response.DLoginResponse;
import com.hwc.framework.modules.model.ArcSysConfig;
import com.hwc.framework.modules.model.ClUser;
import com.hwc.framework.modules.model.ClUserBaseInfo;
import com.hwc.framework.modules.service.ClUserAuthService;
import com.hwc.framework.modules.service.ClUserBaseInfoService;
import com.hwc.framework.modules.service.ClUserOtherInfoService;
import com.hwc.framework.modules.service.ClUserService;
import com.hwc.framework.modules.service.ClWContactsService;
import com.hwc.framework.modules.third.SmsService;
import com.hwc.framework.modules.utils.Base64;
import com.hwc.framework.modules.utils.HttpUtil;
import com.hwc.framework.modules.utils.OSSUtils;
import com.hwc.framework.modules.utils.PathUtil;
import com.hwc.loan.sdk.borrow.request.CreditGenQuotaRequest;
import com.hwc.loan.sdk.borrow.request.RateRequest;
import com.hwc.loan.sdk.borrow.response.CreditGenQuotaResponse;
import com.hwc.loan.sdk.borrow.response.RateResponse;
import com.hwc.loan.sdk.user.response.UserContextResponse;
import com.hwc.mybatis.core.AbstractService;
import com.hwc.web.core.security.token.TokenManage;

import cn.freesoft.utils.FsUtils;

/**
 * 2017/10/23.
 */
@Service
public class ClUserServiceImpl extends AbstractService<ClUserMapper, ClUser>
                               implements ClUserService {
    private static final Logger    logger      = LoggerFactory.getLogger(ClUserServiceImpl.class);

    private static final double    BORROW_RATE = 0.05;

    @Autowired
    private TokenManage            tokenManage;

    @Autowired
    private ClUserBaseInfoService  clUserBaseInfoService;

    @Autowired
    private ClUserAuthService      clUserAuthService;

    @Autowired
    private ClWContactsService     clWContactsService;

    @Autowired
    private ClUserOtherInfoService clUserOtherInfoService;

    @Autowired
    private SmsService             smsService;

    @Autowired
    private Client                 borrowClient;

    @Autowired
    private OSSUtils               ossUtils;

    @Autowired
    @Qualifier("smsClient")
    private Client                 smsClient;

    @Override
    @Transactional
    public Response registerUser(DUser request) {
        if (FsUtils.strsEmpty(request.getVcode(), request.getLoginName(), request.getLoginPwd(),
            request.getRegisterClient())) {
            return Response.fail("参数有误");
        }
        if (!FsUtils.isMobileNum(request.getLoginName())) {
            return Response.fail("账号必须为手机号");
        }
        // 检验验证码
        DSms dSms = new DSms();
        dSms.setType("register");
        dSms.setPhone(request.getLoginName());
        dSms.setCode(request.getVcode());
        Response response = this.smsService.verifyCode(dSms);
        if (!response.getSuccess()) {
            return Response.fail(response.getMessage());
        }

        // 检验手机号是否存在
        if (!FsUtils.strsEmpty(request.getLoginName())) {
            ClUser clUser = findUserByParameter(request.getLoginName(), "");
            if (clUser != null) {
                return Response.fail("该手机号码已被注册");
            }
        }
         Response res=this.queryIsRegister(request.getLoginName());
        logger.info("res----------------------------------->res==="+res);
        if("不可注册,该用户提示手机号已注册".equals(res.getMessage())){
        	return Response.fail("不可注册,该用户提示手机号已注册");
        }else if("不可注册,该用户私人手机号已注册".equals(res.getMessage())){
        	return Response.fail("不可注册,该用户私人手机号已注册");
        }
        // 邀请码是否存在
        if (!FsUtils.strsEmpty(request.getInvitationCode())) {
            ClUser clUser = findUserByParameter("", request.getInvitationCode());
            if (clUser == null) {
                return Response.fail("邀请人不存在");
            }
        }

        // 验证是否是白名单用户
        request.setCat("0");
        BigDecimal total = new BigDecimal("0");

        DWContacts dwContacts = clWContactsService
            .getWContactsWithTipsPhone(request.getLoginName());
        if (dwContacts == null) {
            dwContacts = clWContactsService.getWContactsWithPhone(request.getLoginName());
        }
        if (dwContacts != null) {
            if (dwContacts.getIsCredit().endsWith("T")) {
                request.setCat("10");
            }
            if (dwContacts.getIsDy().endsWith("T")) {
                request.setCat("20");
            }
            total = dwContacts.getBorrowQuota();
            //            total = FsUtils.mulNumber(dwContacts.getBorrowQuota(), 10000);
        }

        // 注册
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        request.setInvitationCode(randomInvitationCode(6));
        request.setUuid(uuid);
        request.setLoginPwd(DigestUtils.md5Hex(request.getLoginPwd()));
        request.setRegistTime(new Date());
        ClUser clUser = convertToClUser(request);
        this.mapper.insert(clUser);

        // 初始化用户额度
        this.initCreditUserBorrowQuota(clUser.getId(), total);

        // cl_user_base_info
        DUserBaseInfo dUserBaseInfo = new DUserBaseInfo();
        dUserBaseInfo.setUserId(clUser.getId());
        dUserBaseInfo.setPhone(request.getLoginName());
        dUserBaseInfo.setCreateTime(request.getRegistTime());
        dUserBaseInfo.setState("20");
        clUserBaseInfoService.insertUserBaseInfo(dUserBaseInfo);

        // cl_user_auth
        DUserAuth dUserAuth = new DUserAuth();
        dUserAuth.setUserId(clUser.getId());
        dUserAuth.setIdState("10");
        dUserAuth.setContactState("10");
        dUserAuth.setBankCardState("10");
        dUserAuth.setWorkInfoState("10");
        dUserAuth.setOtherInfoState("10");
        dUserAuth.setCreditState("10");
        dUserAuth.setPhoneState("10");
        clUserAuthService.updateUserAuth(dUserAuth);

        Map<String, Object> map = new HashMap<>();
        map.put("id", clUser.getId());
        map.put("cat", clUser.getCat());
        return Response.success(map);
    }

    @Override
    @Transactional
    public Response wxRegisterUser(DUser dUser) {
        if (FsUtils.strsEmpty(dUser.getVcode(), dUser.getLoginName(), dUser.getLoginPwd(),
            dUser.getOpenid())) {
            return Response.fail("参数有误");
        }
        if (!FsUtils.isMobileNum(dUser.getLoginName())) {
            return Response.fail("账号必须为手机号");
        }

        // 检验验证码
        Response response = verifySms(dUser.getLoginName(), "register", dUser.getVcode());
        if (!response.getSuccess()) {
            return Response.fail(response.getMessage());
        }

        //检验手机号是否存在
        if (!FsUtils.strsEmpty(dUser.getLoginName())) {
            ClUser clUser = findUserByParameter(dUser.getLoginName(), "");
            if (clUser != null) {
                return Response.fail("该手机号码已注册, 请直接登录");
            }
        }
        Response res=this.queryIsRegister(dUser.getLoginName());
        logger.info("res----------------------------------->res==="+res);
        if("不可注册,该用户提示手机号已注册".equals(res.getMessage())){
        	return Response.fail("不可注册,该用户提示手机号已注册");
        }else if("不可注册,该用户私人手机号已注册".equals(res.getMessage())){
        	return Response.fail("不可注册,该用户私人手机号已注册");
        }
        // 邀请码是否存在
        if (!FsUtils.strsEmpty(dUser.getInvitationCode())) {
            ClUser clUser = findUserByParameter("", dUser.getInvitationCode());
            if (clUser != null) {
                return Response.fail("邀请人不存在");

            }
        }

        // 验证是否是白名单用户
        dUser.setCat("0");
        BigDecimal total = new BigDecimal("0");
        DWContacts dwContacts = clWContactsService.getWContactsWithTipsPhone(dUser.getLoginName());
        if (dwContacts == null) {
            dwContacts = clWContactsService.getWContactsWithPhone(dUser.getLoginName());
        }
        if (dwContacts != null) {
            if (dwContacts.getIsCredit().endsWith("T")) {
                dUser.setCat("10");
                total = dwContacts.getBorrowQuota();
            }
            if (dwContacts.getIsDy().endsWith("T")) {
                dUser.setCat("20");
            }
            total = dwContacts.getBorrowQuota();
            //            total = FsUtils.mulNumber(dwContacts.getBorrowQuota(), 10000);
        }

        // 注册
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        dUser.setInvitationCode(randomInvitationCode(6));
        dUser.setUuid(uuid);
        dUser.setLoginPwd(DigestUtils.md5Hex(dUser.getLoginPwd()));
        dUser.setRegistTime(new Date());
        dUser.setRegisterClient("wx");
        ClUser clUser = convertToClUser(dUser);
        this.mapper.insert(clUser);

        // 初始化用户借款额度
        this.initCreditUserBorrowQuota(clUser.getId(), total);

        // cl_user_base_info
        DUserBaseInfo dUserBaseInfo = new DUserBaseInfo();
        dUserBaseInfo.setUserId(clUser.getId());
        dUserBaseInfo.setPhone(dUser.getLoginName());
        dUserBaseInfo.setCreateTime(dUser.getRegistTime());
        dUserBaseInfo.setState("20");
        clUserBaseInfoService.insertUserBaseInfo(dUserBaseInfo);

        // cl_user_auth
        DUserAuth dUserAuth = new DUserAuth();
        dUserAuth.setUserId(clUser.getId());
        dUserAuth.setIdState("10");
        dUserAuth.setContactState("10");
        dUserAuth.setBankCardState("10");
        dUserAuth.setWorkInfoState("10");
        dUserAuth.setOtherInfoState("10");
        dUserAuth.setCreditState("10");
        dUserAuth.setPhoneState("10");
        clUserAuthService.updateUserAuth(dUserAuth);

        // save DUserOtherInfo
        DUserOtherInfo dUserOtherInfo = new DUserOtherInfo();
        dUserOtherInfo.setCat("WX");
        dUserOtherInfo.setAccnt(dUser.getOpenid());
        dUserOtherInfo.setUnionid(dUser.getUnionid());
        dUserOtherInfo.setUserId(clUser.getId());
        clUserOtherInfoService.updateUserOtherInfo(dUserOtherInfo);

        Map<String, Object> map = new HashMap<>();
        map.put("id", clUser.getId());
        map.put("cat", clUser.getCat());
        return Response.success(map);
    }

    @Override
    public Response loginUser(DUser request) {
        if (FsUtils.strsEmpty(request.getLoginName(), request.getLoginPwd())) {
            return Response.fail("账号或密码不能为空");
        }
        if (!FsUtils.isMobileNum(request.getLoginName())) {
            return Response.fail("账号必须为手机号");
        }
        String loginPwd = DigestUtils.md5Hex(request.getLoginPwd());
        ClUser select = new ClUser();
        select.setLoginName(request.getLoginName());
        ClUser user = this.mapper.selectOne(select);
        if (user == null) {
            return Response.fail("账号不存在");
        }
        if (!loginPwd.equals(user.getLoginPwd())) {
            return Response.fail("账号或密码错误");
        }
        request.setId(user.getId());
        request.setCat(user.getCat());
        String generate = createToken(request);
        String borrowRate = "0.05";
        RateRequest rateRequest = new RateRequest();
        rateRequest.setId(user.getId());
        RateResponse rateResponse = borrowClient.invoke(rateRequest);
        if (rateResponse.getSuccess()) {
            borrowRate = rateResponse.getData().getRates();
        }
        DLoginResponse resp = new DLoginResponse();
        resp.setToken(generate);
        resp.setCat(user.getCat());
        resp.setUserId(user.getId());
        resp.setBorrowRate(borrowRate);
        return Response.success(resp);
    }

    @Override
    public Response wxCheckAccount(DUser request) {
        if (FsUtils.strsEmpty(request.getOpenid())) {
            return Response.fail("参数有误");
        }
        //
        DUserOtherInfo dUserOtherInfo = this.clUserOtherInfoService
            .getUserOtherInfoByOpenid(request.getOpenid());
        if (dUserOtherInfo == null) {
            return Response.fail("该账号未注册，请先注册");
        } else {
            return Response.success();
        }
    }

    @Override
    public double getRateByPhone(String phone) {
        DWContacts dwContacts = clWContactsService.getWContactsWithPhone(phone);
        double borrowRate = BORROW_RATE;
        if (dwContacts != null && dwContacts.getBorrowRate() != null) {
            borrowRate = dwContacts.getBorrowRate().doubleValue();
        }
        return borrowRate;
    }

    @Override
    public double getRateByUserId(Long userId) {
        DUser user = this.getUserById(userId);
        double borrowRate = BORROW_RATE;
        if (user != null && user.getCat().equals(UserType.CREDIT.getType())) {
            borrowRate = getRateByPhone(user.getLoginName());
        }
        return borrowRate;
    }

    @Override
    public Response wxLoginUser(DUser request) {
        if (FsUtils.strsEmpty(request.getOpenid())) {
            return Response.fail("参数有误");
        }
        //
        DUserOtherInfo dUserOtherInfo = this.clUserOtherInfoService
            .getUserOtherInfoByOpenid(request.getOpenid());
        if (dUserOtherInfo == null) {
            return Response.fail("该微信号未绑定用户，请先登录绑定用户");
        }
        if (FsUtils.strsEmpty(dUserOtherInfo.getUserId())) {
            return Response.fail("账号异常，该微信号绑定了多个账号");
        }

        ClUser clUser = this.mapper.selectByPrimaryKey(dUserOtherInfo.getUserId());
        if (clUser != null) {
            DUser dUser = convertToDUser(clUser);

            Map<String, Object> param = new HashMap<String, Object>();
            param.put("loginName", dUser.getLoginName());
            param.put("userId", dUser.getId());
            param.put("cat", dUser.getCat());
            DLoginResponse resp = new DLoginResponse();
            resp.setCat(dUser.getCat());
            resp.setUserId(dUser.getId());
            resp.setLoginName(dUser.getLoginName());
            logger.info("isLogin,userId=>" + dUser.getId() + ",data=>" + dUser.getIsLogin());
            resp.setIsLogin(dUser.getIsLogin());
            return Response.success(resp);
        } else {
            return Response.fail("绑定的用户不存在");
        }

    }

    @Override
    public Response wxBindLogin(DUser request) {
        if (FsUtils.strsEmpty(request.getOpenid(), request.getLoginName(), request.getLoginPwd())) {
            return Response.fail("参数有误");
        }
        if (!FsUtils.isMobileNum(request.getLoginName())) {
            return Response.fail("账号必须为手机号");
        }
        DUser select = this.getUserByLoginName(request.getLoginName());
        if (select == null) {
            return Response.fail("该手机号未注册").setCode(Constant.USER_EMPTY);
        }

        // 检测该账号有无被绑定
        DUserOtherInfo dUserOtherInfo = this.clUserOtherInfoService
            .getUserOtherInfoByUserId(select.getId());
        logger.info("dUserOtherInfo------------------------>" + (dUserOtherInfo == null));
        DUserOtherInfo dUserOtherInfos = this.clUserOtherInfoService
            .getUserOtherInfoByOpenid(request.getOpenid());
        logger.info("dUserOtherInfos------------------------>" + (dUserOtherInfos == null));
        if (dUserOtherInfo != null && !dUserOtherInfo.getAccnt().equals(request.getOpenid())) {
            return Response.fail("您无法登录，该账号已绑定其他微信号");
        }
        if (dUserOtherInfos != null) {
            logger
                .info("dUserOtherInfos------------------------>" + dUserOtherInfos.getUserId()
                      + "******************>" + dUserOtherInfos.getUserId().equals(select.getId()));
        }

        if (dUserOtherInfos != null && !dUserOtherInfos.getUserId().equals(select.getId())) {
            return Response.fail("您无法登录，该微信号已绑定其他账号");
        }
        if (select.getLoginPwd().equals(DigestUtils.md5Hex(request.getLoginPwd()))) {
            // 绑定
            if (dUserOtherInfo == null) {
                DUserOtherInfo otherInfoUpdate = new DUserOtherInfo();
                otherInfoUpdate.setCat("WX");
                otherInfoUpdate.setAccnt(request.getOpenid());
                otherInfoUpdate.setUnionid(request.getUnionid());
                otherInfoUpdate.setUserId(select.getId());
                clUserOtherInfoService.updateUserOtherInfo(otherInfoUpdate);
            }

            String generate = createToken(select);

            DLoginResponse resp = new DLoginResponse();
            resp.setToken(generate);
            resp.setCat(select.getCat());
            resp.setUserId(select.getId());
            this.updateIsLogin(select.getId(), "1");
            return Response.success(resp);
        } else {
            return Response.fail("账号或密码错误");
        }
    }

    /**
     * 创建用户token
     *
     * @param user
     * @return
     */
    private String createToken(DUser user) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("loginName", user.getLoginName());
        param.put("cat", user.getCat());
        param.put("userId", user.getId());
        return tokenManage.generate(param);
    }

    @Override
    public Response vCodeLoginUser(DUser dUser) {
        if (FsUtils.strsEmpty(dUser.getLoginName(), dUser.getVcode())) {
            return Response.fail("参数有误");
        }

        // 校对短信验证码
        Response response = verifySms(dUser.getLoginName(), "login", dUser.getVcode());
        if (!response.getSuccess()) {
            return Response.fail(response.getMessage());
        }

        ClUser select = new ClUser();
        select.setLoginName(dUser.getLoginName());
        ClUser user = this.mapper.selectOne(select);
        if (user == null) {
            return Response.fail("账号不存在");
        }

        String generate = createToken(dUser);

        DLoginResponse resp = new DLoginResponse();
        resp.setToken(generate);
        resp.setCat(user.getCat());
        resp.setUserId(user.getId());
        return Response.success(resp);
    }

    @Override
    public Response forgetPwd(DUser request) {
        if (FsUtils.strsEmpty(request.getLoginName(), request.getLoginPwd(), request.getVcode())) {
            return Response.fail("参数错误");
        }
        // 校对验证码
        Response response = verifySms(request.getLoginName(), "findReg", request.getVcode());
        if (!response.getSuccess()) {
            return Response.fail(response.getMessage());
        }

        //
        ClUser updatePwd = this.findUserByParameter(request.getLoginName(), "");
        if (updatePwd == null) {
            return Response.fail("该用户不存在");
        } else {
            String loginPwd = request.getLoginPwd();
            String newPwd = DigestUtils.md5Hex(request.getLoginPwd());
            if (loginPwd.equals(newPwd)) {
                return Response.fail("新密码和原密码不能相同");
            }
            ClUser update = new ClUser();
            update.setId(updatePwd.getId());
            update.setLoginPwd(newPwd);
            update.setLoginpwdModifyTime(new Date());
            this.update(update);
            this.updateIsLogin(updatePwd.getId(), "0");
            return Response.success("密码重置成功");
        }
    }

    @Override
    public Response updatePwd(DUser request) {
        if (FsUtils.strsEmpty(request.getId(), request.getOldPwd(), request.getLoginPwd())) {
            return Response.fail("参数错误");
        }
        DUser dUser = this.getUserById(request.getId());
        if (dUser != null) {
            String oldPwd = DigestUtils.md5Hex(request.getOldPwd());
            String loginPwd = dUser.getLoginPwd();
            String newPwd = DigestUtils.md5Hex(request.getLoginPwd());
            if (oldPwd.equals(loginPwd)) {
                if (loginPwd.equals(newPwd)) {
                    return Response.fail("新密码和原密码不能相同");
                }
                ClUser update = new ClUser();
                update.setId(dUser.getId());
                update.setLoginPwd(newPwd);
                update.setLoginpwdModifyTime(new Date());
                this.update(update);
                return Response.success("密码修改成功");
            } else {
                return Response.fail("原密码错误");
            }
        } else {
            return Response.fail("用户不存在");
        }
    }

    @Override
    public Response getUserCat(Long userId) {
        if (FsUtils.strsEmpty(userId)) {
            return Response.fail("参数错误");
        }
        ClUser clUser = this.mapper.selectByPrimaryKey(userId);
        if (clUser != null) {
            DUser dUser = new DUser();
            dUser.setCat(clUser.getCat());
            dUser.setId(clUser.getId());
            return Response.success(dUser);
        } else {
            return Response.fail("该用户不存在");
        }
    }

    @Override
    public Response updateCat(DUser request) {
        if (FsUtils.strsEmpty(request.getId(), request.getCat())) {
            return Response.fail("参数错误");
        }
        ClUser clUser = new ClUser();
        clUser.setId(request.getId());
        ClUser userOne = this.mapper.selectOne(clUser);
        if (userOne != null) {
            if (FsUtils.strsEmpty(userOne.getCat()) || userOne.getCat().equals("0")) {
                ClUser update = new ClUser();
                update.setCat(request.getCat());
                update.setId(userOne.getId());
                this.update(update);
                return Response.success();
            } else {
                return Response.fail("已经选择好类型的用户，不能修改");
            }
        }
        return Response.fail("用户不存在");
    }

    @Override
    public Response setTradePwd(DUser request) {
        if (FsUtils.strsEmpty(request.getId(), request.getTradePwd())) {
            return Response.fail("参数错误");
        }
        ClUser clUser = this.mapper.selectByPrimaryKey(request.getId());
        if (!FsUtils.strsEmpty(clUser.getTradePwd())) {
            return Response.fail("交易密码已设置,不能重复设置");
        }
        ClUser update = new ClUser();
        update.setId(request.getId());
        update.setTradePwd(DigestUtils.md5Hex(request.getTradePwd()));
        update.setTradepwdModifyTime(new Date());
        this.update(update);
        return Response.success("交易密码设置成功");
    }

    @Override
    public Response changeTradePwd(DUser request) {
        if (FsUtils.strsEmpty(request.getId(), request.getTradePwd(), request.getOldPwd())) {
            return Response.fail("参数错误");
        }
        DUser clUser = this.getUserById(request.getId());
        if (FsUtils.strsEmpty(clUser.getTradePwd())) {
            return Response.fail("请先设置初始交易密码");
        }
        if (!clUser.getTradePwd().equals(DigestUtils.md5Hex(request.getOldPwd()))) {
            return Response.fail("原交易密码错误");
        }
        if (clUser.getTradePwd().equals(DigestUtils.md5Hex(request.getTradePwd()))) {
            return Response.fail("新交易密码不能与旧密码相同");
        }
        ClUser update = new ClUser();
        update.setId(request.getId());
        update.setTradePwd(DigestUtils.md5Hex(request.getTradePwd()));
        update.setTradepwdModifyTime(new Date());
        this.update(update);
        return Response.success("交易密码修改成功");
    }

    @Override
    public Response changeDLPwd(DUser request) {
        if (FsUtils.strsEmpty(request.getId(), request.getLoginPwd(), request.getOldPwd())) {
            return Response.fail("参数错误");
        }
        DUser clUser = this.getUserById(request.getId());
        if (FsUtils.strsEmpty(clUser.getLoginPwd())) {
            return Response.fail("请先设置初始登录密码");
        }
        if (!clUser.getLoginPwd().equals(DigestUtils.md5Hex(request.getOldPwd()))) {
            return Response.fail("原登录密码错误");
        }
        if (clUser.getLoginPwd().equals(DigestUtils.md5Hex(request.getLoginPwd()))) {
            return Response.fail("新登录密码不能与旧密码相同");
        }
        ClUser update = new ClUser();
        update.setId(request.getId());
        update.setLoginPwd(DigestUtils.md5Hex(request.getLoginPwd()));
        update.setLoginpwdModifyTime(new Date());
        this.update(update);
        this.updateIsLogin(request.getId(), "0");
        return Response.success("登录密码修改成功");
    }

    @Override
    public Response validateTradePwd(DUser request) {
        if (FsUtils.strsEmpty(request.getId(), request.getTradePwd())) {
            return Response.fail("参数错误");
        }
        ClUser select = new ClUser();
        select.setId(request.getId());
        ClUser clUser = this.mapper.selectOne(select);
        if (!clUser.getTradePwd().equals(DigestUtils.md5Hex(request.getTradePwd()))) {
            return Response.fail("交易密码错误");
        }
        return Response.success();
    }

    @Override
    public Response getTradeState(Long userId) {
        if (FsUtils.strsEmpty(userId)) {
            return Response.fail("参数错误");
        }
        // 获取用户详细信息
        DUser dUser = this.getUserById(userId);
        if (dUser != null) {
            if (!FsUtils.strsEmpty(dUser.getTradePwd())) {
                return Response.success();
            }
        }
        return Response.fail();
    }

    @Override
    public Response validateUser(DUserBaseInfo request) {
        if (FsUtils.strsEmpty(request.getUserId(), request.getIdNo(), request.getRealName(),
            request.getPhone(), request.getVCode())) {
            return Response.fail("参数错误");
        }
        // 校对验证码
        Response response = verifySms(request.getPhone(), "findPay", request.getVCode());
        if (!response.getSuccess()) {
            return Response.fail(response.getMessage());
        }

        // 获取用户详细信息
        ClUserBaseInfo select = new ClUserBaseInfo();
        if (!select.getIdNo().equalsIgnoreCase(request.getIdNo())
            || !select.getRealName().equals(request.getRealName())) {
            return Response.fail("身份证或姓名验证不通过");
        } else {
            return Response.success("身份验证通过");
        }
    }

    /**
     * 重置交易密码校验lyf
     */
    @Override
    public Response resetTradePwdPreCheck(DUserModel request) {
        if (FsUtils.strsEmpty(request.getLoginName(), request.getIdNo(), request.getRealName(),
            request.getUserId())) {
            return Response.fail("参数错误");
        }

        ClUser cluser = findUserByParameter(request.getLoginName(), "");
        if (cluser == null) {
            return Response.fail("该手机号未注册为用户");
        }
        if (cluser.getId().longValue() != request.getUserId().longValue()) {
            return Response.fail("和当前登录用户手机号码匹配不一致");
        }
        // 获取用户详细信息
        DUserBaseInfo select = this.clUserBaseInfoService.getUserBaseInfo(cluser.getId());
        if (select == null || FsUtils.strsEmpty(select.getIdNo())
            || FsUtils.strsEmpty(select.getRealName())) {
            return Response.fail("该用户还未实名认证");
        }

        if (!select.getIdNo().equalsIgnoreCase(request.getIdNo())) {
            return Response.fail("身份证验证不通过");
        }

        if (!select.getRealName().equals(request.getRealName())) {
            return Response.fail("姓名验证不通过");
        }
        return Response.success("用户信息验证通过");
    }

    @Autowired
    private ArcSysConfigMapper arcSysConfigMapper;

    @Override
    public Response getCode(Integer id) {
        String code;
        if (id != null && id == 0) {
            code = Constant.APP_TYPE_IOS;
        } else {
            code = Constant.APP_TYPE_ANDROID;
        }
        ArcSysConfig obj = new ArcSysConfig();
        obj.setCode(code);
        obj = arcSysConfigMapper.selectOne(obj);
        int result = (obj != null) ? Integer.parseInt(obj.getValue()) : 0;
        return Response.success(result);
    }

    @Override
    public Response resetTradePwd(DUserModel request) {
        if (FsUtils.strsEmpty(request.getUserId(), request.getIdNo(), request.getRealName(),
            request.getvCode(), request.getTradePwd())) {
            return Response.fail("参数错误");
        }

        ClUser cluser = findUserByParameter(request.getLoginName(), "");
        if (cluser == null) {
            return Response.fail("该手机号未注册为用户");
        }
        if (cluser.getId().longValue() != request.getUserId().longValue()) {
            return Response.fail("手机号填写错误,和当前登录用户手机号匹配不一致");
        }

        // 获取用户详细信息
        DUserBaseInfo select = this.clUserBaseInfoService.getUserBaseInfo(request.getUserId());
        // 校对验证码
        Response response = verifySms(select.getPhone(), "findPay", request.getvCode());
        if (!response.getSuccess()) {
            return Response.fail(response.getMessage());
        }

        if (FsUtils.strsEmpty(select.getIdNo()) || FsUtils.strsEmpty(select.getRealName())) {
            return Response.fail("该用户还未实名认证");
        }

        if (!select.getIdNo().equalsIgnoreCase(request.getIdNo())
            || !select.getRealName().equals(request.getRealName())) {
            return Response.fail("身份证或姓名验证不通过");
        }

        if (DigestUtils.md5Hex(request.getTradePwd()).equals(cluser.getTradePwd())) {
            return Response.fail("新交易密码不能与旧密码相同");
        }

        ClUser resetUser = new ClUser();
        resetUser.setId(request.getUserId());
        resetUser.setTradePwd(DigestUtils.md5Hex(request.getTradePwd()));
        resetUser.setTradepwdModifyTime(new Date());
        this.update(resetUser);
        return Response.success("交易密码重置成功");
    }

    @Override
    public DUser getUserById(Long userId) {
        if (FsUtils.strsEmpty(userId)) {
            return null;
        }
        ClUser clUser = this.mapper.selectByPrimaryKey(userId);
        if (clUser != null) {
            return convertToDUser(clUser);
        } else {
            return null;
        }
    }

    @Override
    public DUser getUserByUuid(String uuid) {
        if (FsUtils.strsEmpty(uuid)) {
            return null;
        }
        ClUser select = new ClUser();
        select.setUuid(uuid);
        ClUser clUser = this.mapper.selectOne(select);
        if (clUser != null) {
            return convertToDUser(clUser);
        } else {
            return null;
        }
    }

    @Override
    public DUser getUserByLoginName(String loginName) {
        if (FsUtils.strsEmpty(loginName) || !FsUtils.isMobileNum(loginName)) {
            return null;
        }
        ClUser select = new ClUser();
        select.setLoginName(loginName);
        ClUser clUser = this.mapper.selectOne(select);
        if (clUser != null) {
            return convertToDUser(clUser);
        } else {
            return null;
        }
    }

    @Override
    public Response isSetTradePwd(DUser request) {
        if (FsUtils.strsEmpty(request.getId())) {
            return Response.fail("参数错误");
        }
        DUser select = this.getUserById(request.getId());
        Map<String, Object> map = new HashMap();
        if (select != null) {
            if (!FsUtils.strsEmpty(select.getTradePwd())) {
                map.put("isSet", true);
            } else {
                map.put("isSet", false);
            }
            return Response.success(map);
        } else {
            return Response.fail("用户不存在");
        }
    }

    @Override
    public Response uploadContacts(DContactsModel contactsModel) {
        if (FsUtils.strsEmpty(contactsModel.getInfo(), contactsModel.getUserId())) {
            return Response.fail("参数错误");
        }
        logger.info("通信录:userId:" + contactsModel.getUserId() + ":" + contactsModel.getInfo());
        try {
            String info = new String(Base64.decode(contactsModel.getInfo()));
            String path = "contacts/" + contactsModel.getUserId() + ".json";
            String fullPath = PathUtil.getFullResPath(path);
            String ctx = HttpUtil.doGet1(fullPath);
            if (FsUtils.strsEmpty(ctx)) {
                ossUtils.upFile(info, "contacts/", contactsModel.getUserId());
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
                ossUtils.upFile(array.toJSONString(), "contacts/", contactsModel.getUserId());
            }
        } catch (Exception ex) {
            FsUtils.log_error(ex);
        }
        return Response.success();
    }

    @Override
    public Response uploadMessage(DContactsModel contactsModel) {
        if (FsUtils.strsEmpty(contactsModel.getInfo(), contactsModel.getUserId())) {
            return Response.fail("参数错误");
        }
        String info = new String(Base64.decode(contactsModel.getInfo()));
        logger.info("短信:userId:" + contactsModel.getUserId() + ":" + contactsModel.getInfo());
        ossUtils.upFile(info, "message/", contactsModel.getUserId());
        return Response.success();
    }

    public void initCreditUserBorrowQuota(Long userId, BigDecimal total) {
        CreditGenQuotaRequest arcCreditGenQuotaRequest = new CreditGenQuotaRequest();
        arcCreditGenQuotaRequest.setTotal(total);
        arcCreditGenQuotaRequest.setUserId(userId);
        CreditGenQuotaResponse response = borrowClient.invoke(arcCreditGenQuotaRequest);
        logger.info("设置用户额度结果,{}", JSON.toJSONString(response));
    }

    public Response verifySms(String phone, String type, String code) {

        //        SmsVerifyCodeRequest smsSendCodeRequest = new SmsVerifyCodeRequest();
        //        smsSendCodeRequest.setPhone(phone);
        //        smsSendCodeRequest.setType(type);
        //        smsSendCodeRequest.setCode(code);
        //        SmsVerifyCodeResponse smsVerifyCodeResponse = smsClient.invoke(smsSendCodeRequest);
        //        return smsVerifyCodeResponse;
        DSms dSms = new DSms();
        dSms.setPhone(phone);
        dSms.setType(type);
        dSms.setCode(code);
        Response response = smsService.verifyCode(dSms);
        return response;
    }

    private ClUser convertToClUser(DUser dUser) {
        ClUser clUser = new ClUser();
        clUser.setId(dUser.getId());
        clUser.setUuid(dUser.getUuid());
        clUser.setLoginName(dUser.getLoginName());
        clUser.setLoginPwd(dUser.getLoginPwd());
        clUser.setInvitationCode(dUser.getInvitationCode());
        clUser.setCat(dUser.getCat());
        clUser.setMktId(dUser.getMktId());
        clUser.setRegistTime(dUser.getRegistTime());
        clUser.setChannelId(dUser.getChannelId());
        clUser.setRegisterClient(dUser.getRegisterClient());
        clUser.setTradePwd(dUser.getTradePwd());
        return clUser;
    }

    private DUser convertToDUser(ClUser clUser) {
        DUser dUser = new DUser();
        dUser.setId(clUser.getId());
        dUser.setUuid(clUser.getUuid());
        dUser.setLoginName(clUser.getLoginName());
        dUser.setLoginPwd(clUser.getLoginPwd());
        dUser.setInvitationCode(clUser.getInvitationCode());
        dUser.setCat(clUser.getCat());
        dUser.setMktId(clUser.getMktId());
        dUser.setRegistTime(clUser.getRegistTime());
        dUser.setChannelId(clUser.getChannelId());
        dUser.setRegisterClient(clUser.getRegisterClient());
        dUser.setTradePwd(clUser.getTradePwd());
        dUser.setIsLogin(clUser.getIsLogin());
        return dUser;
    }

    // 根据参数查询用户
    @Override
    public ClUser findUserByParameter(String loginName, String invitationCode) {
        ClUser select = new ClUser();
        if (!FsUtils.strsEmpty(loginName)) {
            select.setLoginName(loginName);
        }
        if (!FsUtils.strsEmpty(invitationCode)) {
            select.setInvitationCode(invitationCode);
        }
        ClUser user = this.mapper.selectOne(select);
        return user;
    }

    // 生产邀请码
    private String randomInvitationCode(int len) {
        while (true) {
            String str = randomNumAlph(len);
            if (findUserByParameter("", str) == null) {
                return str;
            }
        }
    }

    private static String randomNumAlph(int len) {
        Random random = new Random();

        StringBuilder sb = new StringBuilder();
        byte[][] list = { { 48, 57 }, { 97, 122 }, { 65, 90 } };
        for (int i = 0; i < len; i++) {
            byte[] o = list[random.nextInt(list.length)];
            byte value = (byte) (random.nextInt(o[1] - o[0] + 1) + o[0]);
            sb.append((char) value);
        }
        return sb.toString();
    }

    @Override
    public void updateIsLogin(Long userId, String isLogin) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("isLogin", isLogin);
        mapper.updateIsLogin(map);

    }

    /** 
     * @see com.hwc.framework.modules.service.ClUserService#queryUserData(java.lang.String)
     */
    @Override
    public Response queryUserData(String openId) {
        UserContextResponse userContextResponse = new UserContextResponse();
        DUserOtherInfo dUserOtherInfo = clUserOtherInfoService.getUserOtherInfoByOpenid(openId);
        //用户从未登录时,dUserOtherInfo为空
        if (dUserOtherInfo == null) {
            return Response.success(userContextResponse);
        }
        DUserBaseInfo dUserBaseInfo = clUserBaseInfoService
            .getUserBaseInfo(dUserOtherInfo.getUserId());
        if (dUserBaseInfo == null) {
            return Response.fail("查询用户不存在");
        }
        ClUser clUser = this.mapper.selectByPrimaryKey(dUserOtherInfo.getUserId());
        if (clUser == null) {
            return Response.fail("查询用户不存在");
        }
        DUser duser = new DUser();
        duser.setCat(clUser.getCat());
        duser.setLoginName(clUser.getLoginName());
        duser.setId(clUser.getId());
        userContextResponse.setIdCard(dUserBaseInfo.getIdNo());
        userContextResponse.setName(dUserBaseInfo.getRealName());
        userContextResponse.setOpenId(openId);
        userContextResponse.setUserId(dUserOtherInfo.getUserId());
        userContextResponse.setCat(clUser.getCat());
        userContextResponse.setPhone(clUser.getLoginName());
        userContextResponse.setToken(createToken(duser));
        return Response.success(userContextResponse);
    }

    @Override
    public Response loginout(DUser request) {
        if (FsUtils.strsEmpty(request.getOpenid())) {
            return Response.fail("参数有误");
        }
        //
        DUserOtherInfo dUserOtherInfo = this.clUserOtherInfoService
            .getUserOtherInfoByOpenid(request.getOpenid());
        if (dUserOtherInfo == null) {
            return Response.fail("该微信号未绑定用户，请先登录绑定用户");
        }
        if (FsUtils.strsEmpty(dUserOtherInfo.getUserId())) {
            return Response.fail("账号异常，该微信号绑定了多个账号");
        }

        ClUser clUser = this.mapper.selectByPrimaryKey(dUserOtherInfo.getUserId());
        if (clUser != null) {
            DUser dUser = convertToDUser(clUser);

            Map<String, Object> param = new HashMap<String, Object>();
            param.put("loginName", dUser.getLoginName());
            param.put("userId", dUser.getId());
            param.put("cat", dUser.getCat());
            String generate = createToken(dUser);

            DLoginResponse resp = new DLoginResponse();
            resp.setToken(generate);
            resp.setCat(dUser.getCat());
            resp.setUserId(dUser.getId());
            resp.setLoginName(dUser.getLoginName());
            this.updateIsLogin(dUser.getId(), "0");
            return Response.success(resp);
        } else {
            return Response.fail("绑定的用户不存在");
        }
    }

    @Override
    public BigDecimal getRateByPhoneBig(String phone) {
        DWContacts dwContacts = clWContactsService.getWContactsWithPhone(phone);
        BigDecimal borrowRate = new BigDecimal(BORROW_RATE);
        if (dwContacts != null && dwContacts.getBorrowRate() != null) {
            borrowRate = dwContacts.getBorrowRate();
        }
        return borrowRate;
    }

    @Override
    public BigDecimal getRateByUserIdBig(Long userId) {
        DUser user = this.getUserById(userId);
        BigDecimal borrowRate = new BigDecimal(BORROW_RATE);
        if (user != null && user.getCat().equals(UserType.CREDIT.getType())) {
            borrowRate = getRateByPhoneBig(user.getLoginName());
        }
        return borrowRate;
    }

    @Override
    public Response queryIsRegister(String loginName) {
        Map<String, String> maps = new HashMap<String, String>();
        Map<String, String> mapss = new HashMap<String, String>();
        maps.put("isAvailability", "1");
        mapss.put("isAvailability", "1");
        maps.put("phone", loginName);
        mapss.put("mobile", loginName);
        DWContacts dWContacts = clWContactsService.getByP(maps);
        DWContacts dWContactss = clWContactsService.getByP(mapss);
        if (null == dWContacts) {
            if (null == dWContactss) {
                return Response.success("可注册");
            } else {
                if (null != dWContactss.getPhone() && !"".equals(dWContactss.getPhone())) {
                    ClUser clUser = findUserByParameter(dWContactss.getPhone(), "");
                    if (null == clUser) {
                        return Response.success("可注册");
                    } else {
                        return Response.success("不可注册,该用户提示手机号已注册");
                    }
                } else {
                    return Response.success("可注册");
                }

            }
        } else {
            if (null != dWContacts.getTipsPhone() && !"".equals(dWContacts.getTipsPhone())) {
                ClUser clUser = findUserByParameter(dWContacts.getTipsPhone(), "");
                if (null == clUser) {
                    if (null == dWContactss) {
                        return Response.success("可注册");
                    } else {
                        if (null != dWContactss.getPhone() && !"".equals(dWContactss.getPhone())) {
                            ClUser clUser1 = findUserByParameter(dWContactss.getPhone(), "");
                            if (null == clUser1) {
                                return Response.success("可注册");
                            } else {
                                return Response.success("不可注册,该用户提示手机号已注册");
                            }
                        } else {
                            return Response.success("可注册");
                        }

                    }
                } else {
                    return Response.success("不可注册,该用户私人手机号已注册");
                }
            } else {
                if (null == dWContactss) {
                    return Response.success("可注册");
                } else {
                    if (null != dWContactss.getPhone() && !"".equals(dWContactss.getPhone())) {
                        ClUser clUser = findUserByParameter(dWContactss.getPhone(), "");
                        if (null == clUser) {
                            return Response.success("可注册");
                        } else {
                            return Response.success("不可注册,该用户提示手机号已注册");
                        }
                    } else {
                        return Response.success("可注册");
                    }

                }
            }
        }

    }

}
