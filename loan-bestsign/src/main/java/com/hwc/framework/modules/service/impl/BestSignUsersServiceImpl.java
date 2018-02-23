/**
 * Copyright 杭州惠万村信息技术有限公司. All Rights Reserved.
 */
package com.hwc.framework.modules.service.impl;

import cn.freesoft.utils.FsUtils;
import com.alibaba.fastjson.JSONObject;
import com.hwc.mybatis.core.AbstractService;
import com.hwc.framework.modules.dao.BestSignUsersMapper;
import com.hwc.framework.modules.model.BestSignUsers;
import com.hwc.framework.modules.service.BestSignUsersService;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 * 类说明
 *
 * @author HwcGenerator
 * @version 1.0.0
 * @since 2017/12/18
 */
@Service
public class BestSignUsersServiceImpl extends AbstractService<BestSignUsersMapper, BestSignUsers> implements BestSignUsersService {
    private static final Logger logger = LoggerFactory.getLogger(BestSignUsersServiceImpl.class);

    private BestSignUsers create() {
        BestSignUsers users = new BestSignUsers();
        users.setState("10");
        return users;
    }

    @Override
    public void signUser(JSONObject jsonObject) {
        BestSignUsers users = getUser(jsonObject.getLong("userId"));
        if (FsUtils.strsEmpty(users)) {
            users = create();
        }
        users.setCerttype(jsonObject.getString("certType"));
        users.setCreated(new Date());
        users.setCertId(jsonObject.getString("certId"));
        users.setSignImage(jsonObject.getString("signImage"));
        users.setIdno(jsonObject.getString("idNo"));
        users.setName(jsonObject.getString("realName"));
        users.setUserId(jsonObject.getLong("userId"));
        if (FsUtils.strsEmpty(users.getId())) {
            insert(users);
        } else {
            update(users);
        }
    }

    @Override
    public BestSignUsers getUser(Long userId) {
        BestSignUsers users = new BestSignUsers();
        users.setUserId(userId);
        List<BestSignUsers> signUsers = this.mapper.select(users);
        if (FsUtils.strsEmpty(signUsers) || signUsers.isEmpty()) {
            return null;
        } else {
            return signUsers.get(0);
        }

    }

}
