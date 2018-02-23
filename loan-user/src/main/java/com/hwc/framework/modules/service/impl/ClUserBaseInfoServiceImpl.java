package com.hwc.framework.modules.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hwc.base.api.Response;
import com.hwc.base.exception.ServiceException;
import com.hwc.common.aliyun.ons.producer.HwcOnsProducer;
import com.hwc.framework.common.Constant;
import com.hwc.framework.modules.dao.ClUserBaseInfoMapper;
import com.hwc.framework.modules.domain.DBankCard;
import com.hwc.framework.modules.domain.DUser;
import com.hwc.framework.modules.domain.DUserAuth;
import com.hwc.framework.modules.domain.DUserAuthData;
import com.hwc.framework.modules.domain.DUserBaseInfo;
import com.hwc.framework.modules.domain.DUserEmerContacts;
import com.hwc.framework.modules.domain.DUserOtherInfo;
import com.hwc.framework.modules.domain.DWContacts;
import com.hwc.framework.modules.face.LinkfaceHs2Request;
import com.hwc.framework.modules.face.LinkfaceHs2Request_FileUrl;
import com.hwc.framework.modules.model.ClUserBaseInfo;
import com.hwc.framework.modules.model.ClUserCardCreditLog;
import com.hwc.framework.modules.model.ManagerUserModel;
import com.hwc.framework.modules.service.ArcSysConfigService;
import com.hwc.framework.modules.service.ClBankCardService;
import com.hwc.framework.modules.service.ClUserAuthDataService;
import com.hwc.framework.modules.service.ClUserAuthService;
import com.hwc.framework.modules.service.ClUserBaseInfoService;
import com.hwc.framework.modules.service.ClUserBlackLogService;
import com.hwc.framework.modules.service.ClUserCardCreditLogService;
import com.hwc.framework.modules.service.ClUserEmerContactsService;
import com.hwc.framework.modules.service.ClUserOtherInfoService;
import com.hwc.framework.modules.service.ClUserService;
import com.hwc.framework.modules.service.ClWContactsService;
import com.hwc.framework.modules.utils.PathUtil;
import com.hwc.loan.sdk.user.domain.DCloanUserDomain;
import com.hwc.mybatis.core.AbstractService;

import cn.freesoft.utils.FsUtils;
import cn.freesoft.utils.IDCardUtils;
import credit.Header;
import net.dongliu.requests.RawResponse;
import net.dongliu.requests.Requests;

@Service
public class ClUserBaseInfoServiceImpl extends AbstractService<ClUserBaseInfoMapper, ClUserBaseInfo>
                                       implements ClUserBaseInfoService {
    private static final Logger        logger = LoggerFactory
        .getLogger(ClUserBaseInfoServiceImpl.class);

    @Autowired
    private ClUserService              clUserService;

    @Autowired
    private ClUserAuthService          clUserAuthService;

    @Autowired
    private ClUserOtherInfoService     clUserOtherInfoService;

    @Autowired
    private ClBankCardService          clBankCardService;

    @Autowired
    private ClUserEmerContactsService  clUserEmerContactsService;

    @Autowired
    private ClUserCardCreditLogService clUserCardCreditLogService;

    @Autowired
    private ClUserAuthDataService      clUserAuthDataService;

    @Autowired
    private ClWContactsService         clWContactsService;

    @Value("${linkface.apikey}")
    private String                     linkFace_apikey;

    @Value("${linkface.secretkey}")
    private String                     linkFace_secretkey;

    @Value("${linkface.host1}")
    private String                     linkFace_host1;

    @Value("${linkface.host2}")
    private String                     linkFace_host2;

    @Value("${oss.endpoint}")
    private String                     ossEndpoint;
    @Autowired
    @Qualifier("smsProducer")
    private HwcOnsProducer             producer;

    @Autowired
    private ArcSysConfigService        arcSysConfigService;
    @Autowired
    private ClUserBlackLogService      clUserBlackLogService;

    @Override
    public Response authenticationUserBaseInfo(DUserBaseInfo request) {
        if (!FsUtils.strsEmpty(request.getUserId())) {
            try {
                Response response = this.linkface(request.getLivingImg(), request.getFrontImg(),
                    request.getBackImg(), request.getOcrImg(), request.getRealName(),
                    request.getIdNo(), request.getLiveAddr(), request.getLiveDetailAddr(),
                    request.getUserId(), request.getLiveCoordinate(), request.getChannelCode(),
                    request.getNational(), request.getSignatureImg());
                return response;
            } catch (Exception e) {
                return Response.fail(e.getMessage());
            }

        } else {
            return Response.fail("参数错误");
        }
    }

    @Override
    public Response updateUserBaseInfo(DUserBaseInfo dUserBaseInfo) {
        logger.info("updateUserBaseInfo", JSONObject.toJSON(dUserBaseInfo));
        if (FsUtils.strsEmpty(dUserBaseInfo.getPhone(), dUserBaseInfo.getUserId())) {
            return Response.fail("参数错误");
        }
        if (!FsUtils.strsEmpty(dUserBaseInfo.getId())) {
            //
            this.mapper.updateByPrimaryKeySelective(convertToClUser(dUserBaseInfo));

        } else {
            //
            this.mapper.insert(convertToClUser(dUserBaseInfo));
        }
        return Response.success();
    }

    /** 
     * @see com.hwc.framework.modules.service.ClUserBaseInfoService#insertUserBaseInfo(com.hwc.framework.modules.domain.DUserBaseInfo)
     */
    @Override
    public Response insertUserBaseInfo(DUserBaseInfo dUserBaseInfo) {
        this.mapper.insert(convertToClUser(dUserBaseInfo));
        return Response.success();
    }

    @Override
    public DUserBaseInfo getUserBaseInfo(Long userId) {
        if (FsUtils.strsEmpty(userId)) {
            return null;
        }
        ClUserBaseInfo select = new ClUserBaseInfo();
        select.setUserId(userId);
        ClUserBaseInfo clUserBaseInfo = this.mapper.selectOne(select);
        if (clUserBaseInfo != null) {
            return convertToDUserBaseInfo(clUserBaseInfo);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Response updateUserState(DUserBaseInfo request) {
        if (FsUtils.strsEmpty(request.getId(), request.getState())) {
            return Response.fail("参数错误");
        }
        ClUserBaseInfo select = new ClUserBaseInfo();
        select.setUserId(request.getId());
        ClUserBaseInfo clUserBaseInfo = this.mapper.selectOne(select);
        if (clUserBaseInfo != null) {
            ClUserBaseInfo update = new ClUserBaseInfo();
            update.setId(clUserBaseInfo.getId());
            update.setUserId(request.getId());
            update.setState(request.getState());
            this.mapper.updateByPrimaryKeySelective(update);
            clUserBlackLogService.insert(request.getId(), request.getState());
            return Response.success();
        } else {
            return Response.fail("用户不存在");
        }

    }

    @Override
    public Response getUserBaseInfoApp(Long userId) {
        if (FsUtils.strsEmpty(userId)) {
            return Response.fail("参数错误");
        }
        ClUserBaseInfo select = new ClUserBaseInfo();
        select.setUserId(userId);
        ClUserBaseInfo clUserBaseInfo = this.mapper.selectOne(select);
        if (clUserBaseInfo != null) {
            if (clUserBaseInfo.getLivingImg() != null) {
                clUserBaseInfo.setLivingImg(PathUtil.getFullResPath(clUserBaseInfo.getLivingImg()));
                clUserBaseInfo.setFrontImg(PathUtil.getFullResPath(clUserBaseInfo.getFrontImg()));
                clUserBaseInfo.setBackImg(PathUtil.getFullResPath(clUserBaseInfo.getBackImg()));
                clUserBaseInfo.setOcrImg(PathUtil.getFullResPath(clUserBaseInfo.getOcrImg()));
            }
            DUserBaseInfo dUserBaseInfo = convertToDUserBaseInfo(clUserBaseInfo);
            //
            DWContacts dwContacts = this.clWContactsService
                .getWContactsWithPhone(dUserBaseInfo.getPhone());
            if (dwContacts != null) {
                dUserBaseInfo.setWhiteList(true);
            } else {
                dUserBaseInfo.setWhiteList(false);
            }
            return Response.success(dUserBaseInfo);
        } else {
            return Response.fail("用户不存在");
        }
    }

    @Override
    public Response updateUserWorkInfo(DUserBaseInfo request) {
        if (FsUtils.strsEmpty(request.getUserId(), request.getCompanyName(),
            request.getCompanyAddr(), request.getSalary())) {
            return Response.fail("参数错误");
        }
        ClUserBaseInfo select = new ClUserBaseInfo();
        select.setUserId(request.getUserId());
        ClUserBaseInfo findUserBaseInfo = this.mapper.selectOne(select);
        if (findUserBaseInfo != null) {
            ClUserBaseInfo update = new ClUserBaseInfo();
            update.setUserId(request.getUserId());
            update.setCompanyName(request.getCompanyName());
            update.setCompanyAddr(request.getCompanyAddr());
            update.setSalary(request.getSalary());
            update.setId(findUserBaseInfo.getId());
            this.update(update);

            // 更新认证状态
            DUserAuth dUserAuth = new DUserAuth();
            dUserAuth.setUserId(findUserBaseInfo.getId());
            dUserAuth.setWorkInfoState("30");
            this.clUserAuthService.updateUserWorkInfoState(dUserAuth);
            return Response.success();
        } else {
            return Response.fail("用户不存在");
        }
    }

    @Override
    public Response getByPhone(DCloanUserDomain request) {
        if (FsUtils.strsEmpty(request.getPhone())) {
            return Response.fail("参数错误");
        }
        ClUserBaseInfo select = new ClUserBaseInfo();
        select.setPhone(request.getPhone());
        ClUserBaseInfo findUserBaseInfo = this.mapper.selectOne(select);
        logger.info("findUserBaseInfo----------------->" + (null == findUserBaseInfo)
                    + "idno-------->=" + findUserBaseInfo.getIdNo());
        if (findUserBaseInfo != null) {

            return Response.success(findUserBaseInfo);
        } else {
            return Response.fail("用户不存在");
        }
    }

    /** 
     * @see com.hwc.framework.modules.service.ClUserBaseInfoService#getByIdNo(com.hwc.loan.sdk.user.domain.DCloanUserDomain)
     */
    @Override
    public Response getByIdNo(DCloanUserDomain request) {
        if (FsUtils.strsEmpty(request.getIdNo())) {
            return Response.fail("参数错误");
        }
        ClUserBaseInfo select = new ClUserBaseInfo();
        select.setIdNo(request.getIdNo());
        ClUserBaseInfo findUserBaseInfo = this.mapper.selectOne(select);
        return Response.success(findUserBaseInfo);
    }

    @Override
    public Response getUserWorkInfo(Long userId) {
        if (FsUtils.strsEmpty(userId)) {
            return Response.fail("参数错误");
        }
        ClUserBaseInfo select = new ClUserBaseInfo();
        select.setUserId(userId);
        ClUserBaseInfo findUserBaseInfo = this.mapper.selectOne(select);
        if (findUserBaseInfo != null) {
            DUserBaseInfo dUserBaseInfo = new DUserBaseInfo();
            Map<String, Object> map = new HashMap<>();
            map.put("userId", findUserBaseInfo.getUserId());
            map.put("companyName", findUserBaseInfo.getCompanyName());
            map.put("companyAddr", findUserBaseInfo.getCompanyAddr());
            map.put("salary", findUserBaseInfo.getSalary());
            //            dUserBaseInfo.setUserId(findUserBaseInfo.getUserId());
            //            dUserBaseInfo.setCompanyName(findUserBaseInfo.getCompanyName());
            //            dUserBaseInfo.setCompanyAddr(findUserBaseInfo.getCompanyAddr());
            //            dUserBaseInfo.setSalary(findUserBaseInfo.getSalary());
            return Response.success(map);
        } else {
            return Response.fail("用户不存在");
        }

    }

    @Override
    public Response getUserDetail(Long userId) {
        if (FsUtils.strsEmpty(userId)) {
            return Response.fail("参数错误");
        }
        HashMap<String, Object> map = new HashMap<String, Object>();
        // 基本信息
        ManagerUserModel model = this.mapper.getBaseModelByUserId(userId);

        String userCat = model.getCat();
        if (!FsUtils.strsEmpty(userCat)) {
            if (userCat.equals("10")) {
                userCat = "信用分期";
            } else if (userCat.equals("20")) {
                userCat = "车位分期";
            } else {
                userCat = "";
            }
        }
        model.setCat(userCat);

        // 实名图片
        model.setLivingImg(
            model.getLivingImg() != null ? PathUtil.getFullResPath(model.getLivingImg()) : "");
        model.setFrontImg(
            model.getFrontImg() != null ? PathUtil.getFullResPath(model.getFrontImg()) : "");
        model.setBackImg(
            model.getBackImg() != null ? PathUtil.getFullResPath(model.getBackImg()) : "");
        model
            .setOcrImg(model.getOcrImg() != null ? PathUtil.getFullResPath(model.getOcrImg()) : "");

        // 银行卡信息
        DBankCard dBankCard = this.clBankCardService.getBankCardByUserId(userId);
        if (dBankCard != null) {
            model.setBank(dBankCard.getBank());
            model.setBankPhone(dBankCard.getPhone());
            model.setCardNo(dBankCard.getCardNo());
        }
        // 注册渠道

        // 推广渠道

        map.put("userbase", model);

        // 人行 & 运营商
        DUserAuthData dUserAuthData = clUserAuthDataService.getUserAuthData(userId);
        if (dUserAuthData != null) {
            dUserAuthData.setZhengxinReport(FsUtils.strsNotEmpty(dUserAuthData.getZhengxinReport())
                ? PathUtil.getFullResPath(dUserAuthData.getZhengxinReport()) : "");
            dUserAuthData.setPhoneReport(FsUtils.strsNotEmpty(dUserAuthData.getPhoneReport())
                ? PathUtil.getFullResPath(dUserAuthData.getPhoneReport()) : "");
        }
        map.put("userAuthData", dUserAuthData);

        // 联系人信息
        List<DUserEmerContacts> userEmerList = this.clUserEmerContactsService
            .findUserEmerContactsByUserId(userId);
        if (userEmerList != null) {
            map.put("userEmerList", userEmerList);
        }

        // 认证信息
        DUserAuth dUserAuth = this.clUserAuthService.getUserAuthById(userId);
        if (dUserAuth != null) {
            map.put("userAuth", dUserAuth);
        }

        // 微信（其他）账号信息
        DUserOtherInfo dUserOtherInfo = this.clUserOtherInfoService
            .getUserOtherInfoByUserId(userId);
        if (dUserOtherInfo != null) {
            map.put("userOtherInfo", dUserOtherInfo);
        }
        System.out.println(JSONObject.toJSONString(map));

        return Response.success(map);
    }

    @Override
    public Response isUseCredit() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        String user_credit = arcSysConfigService.getConfigDefault(Constant.USE_CREDIT, "");
        if (!FsUtils.strsEmpty(user_credit) && user_credit.equals("T")) {
            map.put("isUse", "T");
        } else {
            map.put("isUse", "F");
        }
        return Response.success(map);
    }

    /**
     * @param livingImg  自拍
     * @param frontImg   正面
     * @param backImg    背面
     * @param ocrImg     证上照片
     * @param realName   名字
     * @param idNo       身份证号
     * @param liveAddr   居住地址
     * @param detailAddr 居住详细地址
     * @throws Exception
     * @description 人证识别
     */
    private Response linkface(String livingImg, String frontImg, String backImg, String ocrImg,
                              String realName, String idNo, String liveAddr, String detailAddr,
                              Long userId, String liveCoordinate, String channelCode,
                              String national, String signImg) throws Exception {
        final String APIKEY = linkFace_apikey;
        final String SECRETKEY = linkFace_secretkey;
        final String LINKFACEHOST1 = linkFace_host1;
        final String LINKFACEHOST2 = linkFace_host2;

        //        if (!IDCardUtils.validateIdCard18(idNo) || !IDCardUtils.validateIdCard15(idNo)){
        //            return Response.fail("请输入正确的身份证号");
        //        }
        Map<String, Object> returnMap = new HashMap<String, Object>();
        logger.info("人证识别传参=》" + realName + idNo);
        JSONObject jsonObject = getidCardInfo(userId, frontImg);
        if (FsUtils.strsNotEmpty(jsonObject)) {
            logger.info("人证识别扫描结果=》" + jsonObject.toJSONString());
            if (realName != null && !realName.equals(jsonObject.getString("name"))) {
                return Response.fail("身份证扫描姓名和输入的姓名不匹配");
            }
            if (idNo != null && !idNo.equals(jsonObject.getString("number"))) {
                return Response.fail("身份证号码和输入的号码不匹配");
            }
            realName = jsonObject.getString("name");
            liveAddr = jsonObject.getString("address");
            national = jsonObject.getString("nation");
            idNo = jsonObject.getString("number");
        }
        if (!validateIdCardBack(userId, backImg)) {
            return Response.fail("身份证反面照片不正确，请更换");
        }

        ClUserBaseInfo selectIdNo = new ClUserBaseInfo();
        selectIdNo.setIdNo(idNo);
        ClUserBaseInfo info = this.mapper.selectOne(selectIdNo);
        if (info == null) {
            ClUserBaseInfo selectUserId = new ClUserBaseInfo();
            selectUserId.setUserId(userId);
            info = this.mapper.selectOne(selectUserId);
        } else {
            if (!info.getState().equals("20")) {
                return Response.fail("不支持黑名单用户进行实名认证");
            }
        }

        Double id_card_credit_pass_rate = FsUtils
            .d(arcSysConfigService.getConfigDefault("id_card_credit_pass_rate", ""));

        if (info != null && info.getUserId().longValue() == userId.longValue()
            && (FsUtils.strsEmpty(info.getIdNo()) || info.getIdNo().equals(idNo))) {
            // 判断用户是否超出认证次数
            Response response = clUserCardCreditLogService.isCanCredit(info.getUserId());
            if (!response.getSuccess()) {
                // 超过次数
                return Response.fail(response.getMessage());
            } else {
                // 根据userId获取user表里面的login_name并存入baseinfo表里面的phone字段
                DUser dUser = clUserService.getUserById(userId);
                info.setPhone(dUser.getLoginName());
                info.setRealName(realName);
                if (IDCardUtils.getSex(idNo).equals("M")) {
                    info.setSex("男");
                } else {
                    info.setSex("女");
                }
                info.setAge(IDCardUtils.getAge(idNo));
                info.setIdNo(idNo);
                info.setLiveDetailAddr(detailAddr);

                info.setLiveAddr(liveAddr);
                info.setLiveCoordinate(liveCoordinate);
                info.setNational(national);
                info.setSignatureImg(signImg);
                DUserAuth userAuth = clUserAuthService.getUserAuthById(userId);

                double match = 0.0;
                if (livingImg != null && 30 != Integer.parseInt(userAuth.getIdState())) {
                    //&& ocrImg != null
                    logger.info("用户" + dUser.getLoginName() + "完善个人信息，进入人证识别比对");

                    info.setLivingImg(livingImg);
                    info.setFrontImg(frontImg);
                    info.setBackImg(backImg);
                    info.setOcrImg(ocrImg);

                    long timestamp = new Date().getTime();
                    Header header = new Header(APIKEY, timestamp);
                    LinkfaceHs2Request linkfaceRequest = null;
                    LinkfaceHs2Request_FileUrl linkfaceHs2Request_fileUrl = null;
                    String result = "";
                    ClUserCardCreditLog log = new ClUserCardCreditLog();
                    // 读取远程文件结束
                    if (!channelCode.equalsIgnoreCase("H5")) {
                        linkfaceRequest = new LinkfaceHs2Request(LINKFACEHOST1, header);
                        linkfaceRequest.setLivingImg(PathUtil.getFullResPath(livingImg));
                        linkfaceRequest.setOcrImg(PathUtil.getFullResPath(ocrImg));
                        linkfaceRequest.setName(realName);
                        linkfaceRequest.setIdCard(idNo);
                        linkfaceRequest.setApisecret(SECRETKEY);
                        result = linkfaceRequest.request();
                        log.setReqParams(JSONObject.toJSONString(linkfaceRequest));
                    } else {
                        linkfaceHs2Request_fileUrl = new LinkfaceHs2Request_FileUrl(LINKFACEHOST2,
                            header);
                        linkfaceHs2Request_fileUrl.setLivingImg(PathUtil.getFullResPath(livingImg));
                        linkfaceHs2Request_fileUrl.setApisecret(SECRETKEY);
                        result = linkfaceHs2Request_fileUrl.request();
                        log.setReqParams(JSONObject.toJSONString(linkfaceHs2Request_fileUrl));
                    }

                    logger.info("用户" + dUser.getLoginName() + "完善个人信息，进行人证识别比对，result为:" + result);
                    log.setCreateTime(new Date());
                    log.setUserId(info.getUserId());
                    log.setReturnParams(result);

                    JSONObject resultJson = JSONObject.parseObject(result);
                    if (null != resultJson && !FsUtils.strsEmpty(resultJson.getString("status"))
                        && "ok".equals(resultJson.getString("status").toLowerCase())) {
                        String score = "0";
                        if (!channelCode.equalsIgnoreCase("H5")) {
                            score = resultJson.getString("confidence");
                        } else {
                            score = resultJson.getString("verify_score");
                        }
                        match = Double.valueOf(score);
                        log.setResult(resultJson.getString("status"));
                        log.setConfidence(String.valueOf(match));
                    } else {
                        logger.info(
                            "用户" + dUser.getLoginName() + "人证识别比对失败:" + resultJson.get("status"));
                        return Response.fail("人脸识别与身份证不符，请重试");
                    }
                    clUserCardCreditLogService.insert(log);

                    logger.info("用户" + dUser.getLoginName() + "完善个人信息，进行人证识别比对，比对值为:" + match);
                } else if (30 == Integer.parseInt(userAuth.getIdState())) {
                    match = id_card_credit_pass_rate;
                }

                logger.info("用户" + dUser.getLoginName() + "完善个人信息，人证识别比对最终值为：" + match);

                if (match >= id_card_credit_pass_rate) {
                    this.update(info);
                    DUserAuth dUserAuth = new DUserAuth();
                    dUserAuth.setIdState("30");
                    dUserAuth.setUserId(userId);
                    clUserAuthService.updateUserIdCardCreditState(dUserAuth);
                    Properties properties = new Properties();
                    properties.setProperty("userinfo", JSON.toJSONString(info));
                    producer.sendJson("user_auth", "user_id:" + info.getUserId(), properties, info);
                } else {
                    // 信息校验失败,返回前台处理
                    return Response.fail("人脸识别验证失败");
                }
            }
            return Response.success("认证成功");
        } else {
            return Response.fail("处理失败，身份信息已存在");
        }

    }

    private ClUserBaseInfo convertToClUser(DUserBaseInfo dUserBaseInfo) {
        ClUserBaseInfo clUserBaseInfo = new ClUserBaseInfo();
        clUserBaseInfo.setId(dUserBaseInfo.getId());
        clUserBaseInfo.setIdNo(dUserBaseInfo.getIdNo());
        clUserBaseInfo.setIdAddr(dUserBaseInfo.getIdAddr());
        clUserBaseInfo.setAge(dUserBaseInfo.getAge());
        clUserBaseInfo.setBackImg(dUserBaseInfo.getBackImg());
        clUserBaseInfo.setBlackReason(dUserBaseInfo.getBlackReason());
        clUserBaseInfo.setCompanyAddr(dUserBaseInfo.getCompanyAddr());
        clUserBaseInfo.setCompanyDetailAddr(dUserBaseInfo.getCompanyDetailAddr());
        clUserBaseInfo.setCompanyCoordinate(dUserBaseInfo.getCompanyCoordinate());
        clUserBaseInfo.setCompanyName(dUserBaseInfo.getCompanyName());
        clUserBaseInfo.setCompanyPhone(dUserBaseInfo.getCompanyPhone());
        clUserBaseInfo.setCreateTime(dUserBaseInfo.getCreateTime());
        clUserBaseInfo.setEducation(dUserBaseInfo.getEducation());
        clUserBaseInfo.setSignatureImg(dUserBaseInfo.getSignatureImg());
        clUserBaseInfo.setFrontImg(dUserBaseInfo.getFrontImg());
        clUserBaseInfo.setLiveAddr(dUserBaseInfo.getLiveAddr());
        clUserBaseInfo.setLiveCoordinate(dUserBaseInfo.getLiveCoordinate());
        clUserBaseInfo.setLiveDetailAddr(dUserBaseInfo.getLiveDetailAddr());
        clUserBaseInfo.setLiveTime(dUserBaseInfo.getLiveTime());
        clUserBaseInfo.setLivingImg(dUserBaseInfo.getLivingImg());
        clUserBaseInfo.setMarryState(dUserBaseInfo.getMarryState());
        clUserBaseInfo.setNational(dUserBaseInfo.getNational());
        clUserBaseInfo.setOcrImg(dUserBaseInfo.getOcrImg());
        clUserBaseInfo.setPhone(dUserBaseInfo.getPhone());
        clUserBaseInfo.setPhoneServerPwd(dUserBaseInfo.getPhoneServerPwd());
        clUserBaseInfo.setRealName(dUserBaseInfo.getRealName());
        clUserBaseInfo.setRegisterAddr(clUserBaseInfo.getRegisterAddr());
        clUserBaseInfo.setRegisterCoordinate(clUserBaseInfo.getCompanyCoordinate());
        clUserBaseInfo.setSalary(dUserBaseInfo.getSalary());
        clUserBaseInfo.setSex(dUserBaseInfo.getSex());
        clUserBaseInfo.setState(dUserBaseInfo.getState());
        clUserBaseInfo.setUpdateTime(dUserBaseInfo.getUpdateTime());
        clUserBaseInfo.setUserId(dUserBaseInfo.getUserId());
        clUserBaseInfo.setWorkingImg(dUserBaseInfo.getWorkingImg());
        return clUserBaseInfo;
    }

    private DUserBaseInfo convertToDUserBaseInfo(ClUserBaseInfo clUserBaseInfo) {
        DUserBaseInfo dUserBaseInfo = new DUserBaseInfo();
        dUserBaseInfo.setId(clUserBaseInfo.getId());
        dUserBaseInfo.setIdNo(clUserBaseInfo.getIdNo());
        dUserBaseInfo.setIdAddr(clUserBaseInfo.getIdAddr());
        dUserBaseInfo.setAge(clUserBaseInfo.getAge());
        dUserBaseInfo.setBackImg(clUserBaseInfo.getBackImg());
        dUserBaseInfo.setBlackReason(clUserBaseInfo.getBlackReason());
        dUserBaseInfo.setCompanyAddr(clUserBaseInfo.getCompanyAddr());
        dUserBaseInfo.setCompanyDetailAddr(clUserBaseInfo.getCompanyDetailAddr());
        dUserBaseInfo.setCompanyCoordinate(clUserBaseInfo.getCompanyCoordinate());
        dUserBaseInfo.setCompanyName(clUserBaseInfo.getCompanyName());
        dUserBaseInfo.setCompanyPhone(clUserBaseInfo.getCompanyPhone());
        dUserBaseInfo.setCreateTime(clUserBaseInfo.getCreateTime());
        dUserBaseInfo.setEducation(clUserBaseInfo.getEducation());
        dUserBaseInfo.setFrontImg(clUserBaseInfo.getFrontImg());
        dUserBaseInfo.setSignatureImg(clUserBaseInfo.getSignatureImg());
        dUserBaseInfo.setLiveAddr(clUserBaseInfo.getLiveAddr());
        dUserBaseInfo.setLiveCoordinate(clUserBaseInfo.getLiveCoordinate());
        dUserBaseInfo.setLiveDetailAddr(clUserBaseInfo.getLiveDetailAddr());
        dUserBaseInfo.setLiveTime(clUserBaseInfo.getLiveTime());
        dUserBaseInfo.setLivingImg(clUserBaseInfo.getLivingImg());
        dUserBaseInfo.setMarryState(clUserBaseInfo.getMarryState());
        dUserBaseInfo.setNational(clUserBaseInfo.getNational());
        dUserBaseInfo.setOcrImg(clUserBaseInfo.getOcrImg());
        dUserBaseInfo.setPhone(clUserBaseInfo.getPhone());
        dUserBaseInfo.setPhoneServerPwd(clUserBaseInfo.getPhoneServerPwd());
        dUserBaseInfo.setRealName(clUserBaseInfo.getRealName());
        dUserBaseInfo.setRegisterAddr(clUserBaseInfo.getRegisterAddr());
        dUserBaseInfo.setRegisterCoordinate(clUserBaseInfo.getCompanyCoordinate());
        dUserBaseInfo.setSalary(clUserBaseInfo.getSalary());
        dUserBaseInfo.setSex(clUserBaseInfo.getSex());
        dUserBaseInfo.setState(clUserBaseInfo.getState());
        dUserBaseInfo.setUpdateTime(clUserBaseInfo.getUpdateTime());
        dUserBaseInfo.setUserId(clUserBaseInfo.getUserId());
        dUserBaseInfo.setWorkingImg(clUserBaseInfo.getWorkingImg());
        return dUserBaseInfo;
    }

    private JSONObject getidCardInfo(Long userId, String iamge) {
        Map<String, String> query = new HashMap<>();
        query.put("api_id", linkFace_apikey);
        query.put("api_secret", linkFace_secretkey);
        String url = "http://caiwei.oss-cn-hangzhou.aliyuncs.com/" + iamge
                     + "?x-oss-process=image/resize,w_500";
        query.put("url", url);
        RawResponse send = Requests.post("https://cloudapi.linkface.cn/ocr/idcard").params(query)
            .send();
        JSONObject jsonObject = send.readToJson(JSONObject.class);
        String status = jsonObject.getString("status");
        if ("ok".equalsIgnoreCase(status)) {
            String side = jsonObject.getString("side");
            if ("front".equalsIgnoreCase(side)) {
                JSONObject info = jsonObject.getJSONObject("info");
                if (FsUtils.strsNotEmpty(info)) {
                    return info;
                }
            } else {
                throw new ServiceException("身份证正面照片不正确，请重新上传");
            }
        }
        throw new ServiceException("身份证正面照片不正确，请重新上传");

    }

    private boolean validateIdCardBack(Long userId, String iamge) {
        Map<String, String> query = new HashMap<>();
        query.put("api_id", linkFace_apikey);
        query.put("api_secret", linkFace_secretkey);
        String url = "http://caiwei.oss-cn-hangzhou.aliyuncs.com/" + iamge
                     + "?x-oss-process=image/resize,w_500";
        query.put("url", url);
        RawResponse send = Requests.post("https://cloudapi.linkface.cn/ocr/idcard").params(query)
            .send();
        JSONObject jsonObject = send.readToJson(JSONObject.class);
        String status = jsonObject.getString("status");
        if ("ok".equalsIgnoreCase(status)) {
            String side = jsonObject.getString("side");
            if ("back".equalsIgnoreCase(side)) {
                return true;
            } else {
                throw new ServiceException("身份证反面照片不正确，请更换");
            }
        }
        return false;

    }

    /** 
     * @see com.hwc.framework.modules.service.ClUserBaseInfoService#authenticationParking(com.hwc.framework.modules.domain.DUserBaseInfo)
     */
    @Override
    public Response authenticationParking(DUserBaseInfo request) {
        if (!FsUtils.strsEmpty(request.getUserId())) {
            try {
                Response response = this.parkingLinkface(request.getFrontImg(),
                    request.getBackImg(), request.getUserId());
                return response;
            } catch (Exception e) {
                return Response.fail(e.getMessage());
            }

        } else {
            return Response.fail("参数错误");
        }
    }

    /**
     * 车位用户身份认证
     * @param frontImg
     * @param backImg
     * @param userId
     * @return
     * @throws Exception
     */
    private Response parkingLinkface(String frontImg, String backImg,
                                     Long userId) throws Exception {

        //校验用户是否已实名认证
        DUserAuth userAuth = clUserAuthService.getUserAuthById(userId);
        if (userAuth.getIdState().equals("30")) {
            return Response.fail("恁已经实名认证,无需再次认证");
        }
        //正面照校验并取值
        ClUserBaseInfo clUserBaseInfo = validateIdCardFront(userId, frontImg);
        if (clUserBaseInfo == null) {
            return Response.fail("身份证正面照不正确，请重新上传");
        }
        //反面照校验
        if (!validateIdCardBack(userId, backImg)) {
            return Response.fail("身份证背面照不正确，请重新上传");
        }

        ClUserBaseInfo request = new ClUserBaseInfo();
        request.setIdNo(clUserBaseInfo.getIdNo());
        ClUserBaseInfo info = this.mapper.selectOne(request);
        if (info == null) {
            ClUserBaseInfo selectUserId = new ClUserBaseInfo();
            selectUserId.setUserId(userId);
            info = this.mapper.selectOne(selectUserId);
        } else {
            if (info.getUserId().longValue() != userId.longValue()) {
                return Response.fail("此身份证账号已经认证");
            }

            if (!info.getState().equals("20")) {
                return Response.fail("黑名单用户无法进行实名认证");
            }
        }

        // 根据userId获取user表里面的login_name并存入baseinfo表里面的phone字段
        DUser dUser = clUserService.getUserById(userId);
        info.setPhone(dUser.getLoginName());
        info.setRealName(clUserBaseInfo.getRealName());
        if (IDCardUtils.getSex(clUserBaseInfo.getIdNo()).equals("M")) {
            info.setSex("男");
        } else {
            info.setSex("女");
        }
        info.setAge(IDCardUtils.getAge(clUserBaseInfo.getIdNo()));
        info.setIdNo(clUserBaseInfo.getIdNo());
        info.setLiveAddr(clUserBaseInfo.getLiveAddr());
        info.setNational(clUserBaseInfo.getNational());
        info.setFrontImg(frontImg);
        info.setBackImg(backImg);
        this.update(info);
        DUserAuth dUserAuth = new DUserAuth();
        dUserAuth.setIdState("30");
        dUserAuth.setUserId(userId);
        clUserAuthService.updateUserIdCardCreditState(dUserAuth);
        Properties properties = new Properties();
        properties.setProperty("userinfo", JSON.toJSONString(info));
        producer.sendJson("user_auth", "user_id:" + info.getUserId(), properties, info);
        return Response.success(clUserBaseInfo);
    }

    /**
     * 正面照校验并返回相关数据
     * @param userId
     * @param frontImg
     * @return
     */
    public ClUserBaseInfo validateIdCardFront(Long userId, String frontImg) {
        ClUserBaseInfo clUserBaseInfo = null;
        JSONObject jsonObject = getidCardInfo(userId, frontImg);
        if (FsUtils.strsNotEmpty(jsonObject)) {
            logger.info("人证识别扫描结果=》" + jsonObject.toJSONString());
            clUserBaseInfo = new ClUserBaseInfo();
            clUserBaseInfo.setRealName(jsonObject.getString("name"));
            clUserBaseInfo.setLiveAddr(jsonObject.getString("address"));
            clUserBaseInfo.setNational(jsonObject.getString("nation"));
            clUserBaseInfo.setIdNo(jsonObject.getString("number"));
        }
        return clUserBaseInfo;

    }

    /** 
     * @see com.hwc.framework.modules.service.ClUserBaseInfoService#updateUserBaseInfoByUserId(com.hwc.framework.modules.domain.DUserBaseInfo)
     */
    @Override
    public Response updateUserBaseInfoByUserId(DUserBaseInfo dUserBaseInfo) {
        if (dUserBaseInfo.getUserId() == null) {
            return Response.fail("参数错误");
        }
        DUserBaseInfo dinfo = getUserBaseInfo(dUserBaseInfo.getUserId());
        if (dinfo == null) {
            return Response.fail("查询用户不存在");
        }
        dinfo.setIdNo(dUserBaseInfo.getIdNo());
        dinfo.setRealName(dUserBaseInfo.getRealName());
        updateUserBaseInfo(dinfo);
        return Response.success("更新成功");
    }

    @Override
    public Response updateUserBaseInfoByUserIdCW(DUserBaseInfo dUserBaseInfo) {
        logger.info("updateUserBaseInfoByUserIdCW,user_id=>" + dUserBaseInfo.getUserId() + ",data=>"
                    + JSONObject.toJSONString(dUserBaseInfo));
        if (dUserBaseInfo.getUserId() == null) {
            return Response.fail("参数错误");
        }
        DUserBaseInfo dinfo = getUserBaseInfo(dUserBaseInfo.getUserId());
        if (dinfo == null) {
            return Response.fail("查询用户不存在");
        }
        dinfo.setIdNo(dUserBaseInfo.getIdNo());
        dinfo.setRealName(dUserBaseInfo.getRealName());
        dinfo.setPhone(dUserBaseInfo.getPhone());
        dinfo.setAge(IDCardUtils.getAge(dUserBaseInfo.getIdNo()));
        if (IDCardUtils.getSex(dUserBaseInfo.getIdNo()).equals("M")) {
            dinfo.setSex("男");
        } else {
            dinfo.setSex("女");
        }
        updateUserBaseInfo(dinfo);
        return Response.success("更新成功");
    }

    /** 
     * @see com.hwc.framework.modules.service.ClUserBaseInfoService#authenticationCw(com.hwc.framework.modules.domain.DUserBaseInfo)
     */
    @Override
    public Response authenticationCw(DUserBaseInfo dUserBaseInfo) {
        logger.info("authenticationCw," + JSONObject.toJSONString(dUserBaseInfo));
        ClUserBaseInfo request = new ClUserBaseInfo();
        request.setIdNo(dUserBaseInfo.getIdNo());
        ClUserBaseInfo info = this.mapper.selectOne(request);
        Properties properties = new Properties();
        properties.setProperty("userinfo", JSON.toJSONString(info));
        producer.sendJson("user_auth", "user_id:" + info.getUserId(), properties, info);
        return Response.success();
    }

}
