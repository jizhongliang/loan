package com.hwc.framework.modules.service.impl;

import cn.freesoft.utils.FsUtils;
import cn.freesoft.utils.IDCardUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hwc.base.api.Response;
import com.hwc.base.exception.ServiceException;
import com.hwc.common.aliyun.ons.producer.HwcOnsProducer;
import com.hwc.framework.common.Constant;
import com.hwc.framework.modules.dao.ClUserBaseInfoMapper;
import com.hwc.framework.modules.dao.ClUserBlackLogMapper;
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
import com.hwc.framework.modules.model.ClUserBlackLog;
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
import credit.Header;
import net.dongliu.requests.RawResponse;
import net.dongliu.requests.Requests;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Service
public class ClUserBlackLogServiceImpl extends AbstractService<ClUserBlackLogMapper, ClUserBlackLog>
                                       implements ClUserBlackLogService {
    private static final Logger        logger = LoggerFactory
        .getLogger(ClUserBlackLogServiceImpl.class);




    @Override
    public void insert(Long userId, String state) {
        ClUserBlackLog clUserBlackLog =new ClUserBlackLog();
        clUserBlackLog.setUserId(userId);
        clUserBlackLog.setState(state);
        clUserBlackLog.setCreateTime(new Date());
        this.mapper.insert(clUserBlackLog);
    }
}
