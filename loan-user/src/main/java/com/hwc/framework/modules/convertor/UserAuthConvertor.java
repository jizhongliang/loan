/**
 * Copyright (c) 2018 All Rights Reserved.
 */
package com.hwc.framework.modules.convertor;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.ServiceException;
import com.hwc.framework.modules.domain.DUser;
import com.hwc.framework.modules.domain.DUserBaseInfo;
import com.hwc.framework.modules.model.ClUserBaseInfo;

import cn.freesoft.utils.FsUtils;
import cn.freesoft.utils.IDCardUtils;

/**
 * 
 * @author jinlilong
 * @version $Id: UserAuthConvertor.java, v 0.1 2018年2月1日 上午10:09:12 jinlilong Exp $
 */
public class UserAuthConvertor {

    /**
     * 校验微信端用户实名认证数据
     * @param dUserBaseInfo
     */
    public static void validateAuthenticationData(DUserBaseInfo dUserBaseInfo) {

        if (FsUtils.strsEmpty(dUserBaseInfo.getUserId(), dUserBaseInfo.getIdNo(),
            dUserBaseInfo.getRealName())) {
            throw new ServiceException("参数错误");
        }
        if (FsUtils.strsEmpty(dUserBaseInfo.getFrontImg(), dUserBaseInfo.getBackImg(),
            dUserBaseInfo.getLivingImg())) {
            throw new ServiceException("参数错误");
        }

    }

    /**
     * 校验用户输入的身份证号和扫描结果是否一致
     * @param dUserBaseInfo
     * @param json
     */
    public static void validateUserIdenInputData(DUserBaseInfo dUserBaseInfo,
                                                 JSONObject jsonObject) {
        if (!dUserBaseInfo.getRealName().equalsIgnoreCase(jsonObject.getString("name"))) {
            throw new ServiceException("身份证扫描姓名和输入的姓名不匹配");
        }
        if (!dUserBaseInfo.getIdNo().equalsIgnoreCase(jsonObject.getString("number"))) {
            throw new ServiceException("身份证号码和输入的号码不匹配");
        }
    }

    /**
     * 
     * @param dUserBaseInfo
     * @param jsonObject
     * @return
     */
    public static void buildDUserBaseInfo(ClUserBaseInfo info, DUser dUser,
                                          DUserBaseInfo dUserBaseInfo, JSONObject jsonObject) {
        String idNo = jsonObject.getString("number");
        info.setPhone(dUser.getLoginName());
        info.setRealName(jsonObject.getString("name"));
        if (IDCardUtils.getSex(idNo).equals("M")) {
            info.setSex("男");
        } else {
            info.setSex("女");
        }
        info.setAge(IDCardUtils.getAge(idNo));
        info.setIdNo(idNo);
        info.setNational(jsonObject.getString("nation"));
        info.setLiveAddr(jsonObject.getString("address"));
        info.setFrontImg(dUserBaseInfo.getFrontImg());
        info.setBackImg(dUserBaseInfo.getBackImg());
        info.setLivingImg(dUserBaseInfo.getLivingImg());
        info.setOcrImg(dUserBaseInfo.getOcrImg());
    }

}
