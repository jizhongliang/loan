/**
 * Copyright (c) 2018 All Rights Reserved.
 */
package com.hwc.framework.modules.service.impl;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.ServiceException;
import com.hwc.base.api.Response;
import com.hwc.common.aliyun.ons.producer.HwcOnsProducer;
import com.hwc.framework.modules.convertor.UserAuthConvertor;
import com.hwc.framework.modules.dao.ClUserBaseInfoMapper;
import com.hwc.framework.modules.domain.DUser;
import com.hwc.framework.modules.domain.DUserAuth;
import com.hwc.framework.modules.domain.DUserBaseInfo;
import com.hwc.framework.modules.model.ClUserBaseInfo;
import com.hwc.framework.modules.service.ClUserAuthService;
import com.hwc.framework.modules.service.ClUserCardCreditLogService;
import com.hwc.framework.modules.service.ClUserService;
import com.hwc.framework.modules.service.UserAuthService;
import com.hwc.framework.modules.third.IdCardClient;
import com.hwc.loan.sdk.enums.UserAuthStateEnums;
import com.hwc.mybatis.core.AbstractService;

import cn.freesoft.utils.FsUtils;

/**
 * 
 * @author jinlilong
 * @version $Id: UserAuthServiceImpl.java, v 0.1 2018年2月1日 上午10:04:02 jinlilong Exp $
 */
@Service
public class UserAuthServiceImpl extends AbstractService<ClUserBaseInfoMapper, ClUserBaseInfo>
                                 implements UserAuthService {

    /**  */
    @Autowired
    private IdCardClient               idCardClient;

    /**  */
    @Autowired
    private ClUserCardCreditLogService clUserCardCreditLogService;

    /**  */
    @Autowired
    private ClUserService              clUserService;

    /**  */
    @Autowired
    private ClUserAuthService          clUserAuthService;

    @Autowired
    @Qualifier("smsProducer")
    private HwcOnsProducer             producer;

    /**  */
    private static final Logger        logger = LoggerFactory.getLogger(UserAuthServiceImpl.class);

    /** 
     * @see com.hwc.framework.modules.service.UserAuthService#authentication(com.hwc.framework.modules.domain.DUserBaseInfo)
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    @Transactional
    public Response<String> authentication(DUserBaseInfo dUserBaseInfo) {
        logger.info("微信实名认证传参=>" + JSONObject.toJSONString(dUserBaseInfo));
        //校验数据
        UserAuthConvertor.validateAuthenticationData(dUserBaseInfo);
        //解析正面照
        JSONObject jsonObject = idCardClient.getidCardInfo(dUserBaseInfo.getFrontImg());
        //校验解析结果和用户输入的姓名身份证是否一致。
        UserAuthConvertor.validateUserIdenInputData(dUserBaseInfo, jsonObject);
        //校验反面照是否正确
        idCardClient.validateCardBackInfo(dUserBaseInfo.getBackImg());

        //校验是否已认证
        ClUserBaseInfo info = validateIsCanAuthentication(dUserBaseInfo.getIdNo(),
            dUserBaseInfo.getUserId());

        //校验认证次数是否超过限制
        Response response = clUserCardCreditLogService.isCanCredit(info.getUserId());
        if (!response.getSuccess()) {
            return Response.fail(response.getMessage());
        }
        DUser dUser = clUserService.getUserById(dUserBaseInfo.getUserId());
        if (dUser == null) {
            throw new ServiceException("用户不存在");
        }
        //设置baseinfo
        UserAuthConvertor.buildDUserBaseInfo(info, dUser, dUserBaseInfo, jsonObject);
        this.update(info);
        //更新userAuth为已认证
        updateUserAuth(dUser.getId());

        //发送队列消息
        Properties properties = new Properties();
        properties.setProperty("userinfo", JSON.toJSONString(info));
        producer.sendJson("user_auth", "user_id:" + info.getUserId(), properties, info);
        return Response.success("实名认证成功");
    }

    /**
     * 校验用户身份证账号是否已认证
     */
    public ClUserBaseInfo validateIsCanAuthentication(String idNo, Long userId) {
        ClUserBaseInfo selectIdNo = new ClUserBaseInfo();
        selectIdNo.setIdNo(idNo);
        ClUserBaseInfo info = this.mapper.selectOne(selectIdNo);
        if (info != null) {
            throw new ServiceException("该身份证号已认证，无法重复认证");
        }
        ClUserBaseInfo selectUserId = new ClUserBaseInfo();
        selectUserId.setUserId(userId);
        info = this.mapper.selectOne(selectUserId);
        if (!FsUtils.strsEmpty(info.getIdNo())) {
            throw new ServiceException("您已完成认证认证，无法重复认证");
        }
        return info;
    }

    /**
     * 更新用户实名认证状态
     * @param userId
     */
    public void updateUserAuth(Long userId) {
        DUserAuth dUserAuth = new DUserAuth();
        dUserAuth.setIdState(UserAuthStateEnums.ID_STATE_ALREADY_AUTH.getCode());
        dUserAuth.setUserId(userId);
        clUserAuthService.updateUserIdCardCreditState(dUserAuth);
    }

}
