package com.hwc.framework.modules.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.dao.ClUserEmerContactsMapper;
import com.hwc.framework.modules.domain.DUserAuth;
import com.hwc.framework.modules.domain.DUserEmerContacts;
import com.hwc.framework.modules.model.ClUser;
import com.hwc.framework.modules.model.ClUserEmerContacts;
import com.hwc.framework.modules.service.ClUserAuthService;
import com.hwc.framework.modules.service.ClUserEmerContactsService;
import com.hwc.framework.modules.service.ClUserService;
import com.hwc.mybatis.core.AbstractService;

import cn.freesoft.utils.FsUtils;

/**
 * 2017/10/23.
 */
@Service
public class ClUserEmerContactsServiceImpl extends
                                           AbstractService<ClUserEmerContactsMapper, ClUserEmerContacts>
                                           implements ClUserEmerContactsService {
    private static final Logger       logger = LoggerFactory
        .getLogger(ClUserEmerContactsServiceImpl.class);

    @Autowired
    private ClUserEmerContactsService clUserEmerContactsService;

    @Autowired
    private ClUserAuthService         clUserAuthService;

    @Autowired
    private ClUserService             clUserService;

    @Override
    public Response updateUserEmerContacts(DUserEmerContacts request) {
        if (FsUtils.strsEmpty(request.getUserId(), request.getName(), request.getPhone(),
            request.getRelation(), request.getType())) {
            return Response.fail("参数错误");
        }
        // 查询是否已有联系人
        List<DUserEmerContacts> contacts = this.clUserEmerContactsService
            .findUserEmerContactsByUserId(request.getUserId());

        String nameList = FsUtils.replaceEmoji(request.getName());
        String[] names = nameList.split(",");
        String[] phones = request.getPhone().split(",");
        String[] relations = request.getRelation().split(",");
        String[] types = request.getType().split(",");

        // 校对联系人
        for (String name : names) {
            if (FsUtils.strsEmpty(name)) {
                return Response.fail("联系人姓名不能为空");
            }
        }
        // 校对手机号
        for (String phone : phones) {
            phone = phone.replaceAll("[-\\s]+", "");
            int len = phone.length();
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < len; ++i) {
                char codePoint = phone.charAt(i);
                if (FsUtils.isInteger(codePoint + "")) {
                    buffer.append(codePoint);
                }
            }
            if (!FsUtils.isMobileNum(buffer.toString())) {
                return Response.fail("请填写正确的手机号");
            }
        }

        //校验联系人电话号码
        String msg = checkUserPhone(phones, request.getUserId());
        if (!msg.equals("")) {
            return Response.fail(msg);
        }

        int count = 0;
        if (contacts.size() == 0) {
            for (int i = 0; i < names.length; i++) {
                ClUserEmerContacts info = new ClUserEmerContacts();
                info.setName(names[i]);
                info.setPhone(phones[i]);
                info.setRelation(relations[i]);
                info.setType(types[i]);
                info.setUserId(request.getUserId());
                count = this.mapper.insert(info);
            }
            // 更新 UserAuth
            DUserAuth dUserAuth = new DUserAuth();
            dUserAuth.setContactState("30");
            dUserAuth.setUserId(request.getUserId());
            this.clUserAuthService.updateUserContactState(dUserAuth);
        } else {
            for (int i = 0; i < names.length; i++) {
                if (contacts.get(i).getType().equals(types[i])) {
                    DUserEmerContacts dUserEmerContacts = contacts.get(i);
                    ClUserEmerContacts info = new ClUserEmerContacts();
                    info.setId(dUserEmerContacts.getId());
                    info.setName(names[i]);
                    info.setPhone(phones[i]);
                    info.setRelation(relations[i]);
                    info.setType(types[i]);
                    count = this.mapper.updateByPrimaryKeySelective(info);
                } else {
                    int j = i == 0 ? 1 : 0;
                    DUserEmerContacts dUserEmerContacts = contacts.get(i);
                    ClUserEmerContacts info = new ClUserEmerContacts();
                    info.setId(dUserEmerContacts.getId());
                    info.setName(names[j]);
                    info.setPhone(phones[j]);
                    info.setRelation(relations[j]);
                    info.setType(types[j]);
                    count = this.mapper.updateByPrimaryKeySelective(info);
                }
            }
        }
        if (count > 0) {
            // 更新 UserAuth
            DUserAuth dUserAuth = new DUserAuth();
            dUserAuth.setContactState("30");
            dUserAuth.setUserId(request.getUserId());
            this.clUserAuthService.updateUserContactState(dUserAuth);
        }
        // 更新设备信息

        return Response.success();
    }

    @Override
    public List<DUserEmerContacts> findUserEmerContactsByUserId(Long userId) {
        if (FsUtils.strsEmpty(userId)) {
            return null;
        }
        List<DUserEmerContacts> emerContacts = new ArrayList<DUserEmerContacts>();
        ClUserEmerContacts select = new ClUserEmerContacts();
        select.setUserId(userId);
        List<ClUserEmerContacts> list = this.mapper.select(select);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                emerContacts.add(convertToDUserEmerContacts(list.get(i)));
            }
        }
        return emerContacts;
    }

    private DUserEmerContacts convertToDUserEmerContacts(ClUserEmerContacts clUserEmerContacts) {
        DUserEmerContacts dUserEmerContacts = new DUserEmerContacts();
        dUserEmerContacts.setId(clUserEmerContacts.getId());
        dUserEmerContacts.setName(clUserEmerContacts.getName());
        dUserEmerContacts.setPhone(clUserEmerContacts.getPhone());
        dUserEmerContacts.setRelation(clUserEmerContacts.getRelation());
        dUserEmerContacts.setType(clUserEmerContacts.getType());
        dUserEmerContacts.setUserId(clUserEmerContacts.getUserId());
        return dUserEmerContacts;
    }

    /**
     * 校验联系人号码不能和当前登录用户号码一致
     */
    private String checkUserPhone(String[] phones, Long userId) {
        ClUser clUser = clUserService.findById(userId);
        if (clUser != null) {
            Set<String> set = new HashSet<String>();
            for (String phone : phones) {
                if (clUser.getLoginName().equals(phone)) {
                    return "联系人手机号不能和登录手机号相同";
                }
                set.add(phone);
            }
            if (set.size() != phones.length) {
                return "联系人手机号码不能相同";
            }
        }
        return "";
    }
}
