package com.hwc.framework.modules.service.impl;

import cn.freesoft.utils.FsUtils;
import com.alibaba.fastjson.JSONObject;
import com.hwc.base.api.Response;
import com.hwc.base.api.ResponseCode;
import com.hwc.framework.modules.dao.SysUserMapper;
import com.hwc.framework.modules.domain.DSysUser;
import com.hwc.framework.modules.model.SysUser;
import com.hwc.framework.modules.service.SysUserService;
import com.hwc.mybatis.core.AbstractService;
import com.hwc.mybatis.util.PageUtils;
import com.hwc.web.core.security.token.TokenManage;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2017/09/22.
 */
@Service
public class SysUserServiceImpl extends AbstractService<SysUserMapper, SysUser> implements SysUserService {


    @Autowired
    private TokenManage tokenManage;

    @Override
    public Response getLoginUser(String userName, String password) {
        if (FsUtils.strsEmpty(userName, password)) {
            return Response.fail("账号或密码不能为空");
        }
        SysUser login = new SysUser();
        login.setAccount(userName);
        login.setPassword(password);

        SysUser sysUser = this.mapper.selectOne(login);
        if (sysUser != null) {
            if (FsUtils.strsEmpty(sysUser.getRoleid())) {
                return Response.fail("该用户不支持登录");
            }
            DSysUser response = new DSysUser();
            covertToLoginDSysUser(sysUser, response);

            Map<String, Object> param = new HashMap<String, Object>();
            param.put("loginName", response.getAccount());
            param.put("cat", "manage");
            param.put("userId", response.getId());
            String token = tokenManage.generate(param);
            response.setToken(token);

            return Response.success(response);
        } else {
            return Response.fail("账号或密码错误");
        }
    }

    @Override
    public Response updateUser(DSysUser dSysUser) {
        /*if (FsUtils.strsEmpty(dSysUser.getId())) {
            return Response.fail(ResponseCode.MISSING_PARAMETER);
        }*/
        SysUser sysUser = new SysUser();
        covertToSysUser(sysUser, dSysUser);
        if (!FsUtils.strsEmpty(dSysUser.getId())) {
            this.mapper.updateByPrimaryKeySelective(sysUser);
        } else {
            Map<String, String> map = new HashMap<String, String>();
            map.put("account", dSysUser.getAccount());
            DSysUser user = getOneUserByMap(map);
            if (user == null) {
                sysUser.setStatus(1);
                sysUser.setPassword(DigestUtils.md5Hex("123456"));
                sysUser.setCreatetime(new Date());
                this.mapper.insert(sysUser);
            } else {
                return Response.fail("该账号已存在");
            }
        }
        return Response.success();
    }

    @Override
    public Response deleteUser(Integer userId) {
        if (FsUtils.strsEmpty(userId)) {
            return Response.fail(ResponseCode.MISSING_PARAMETER);
        }
        DSysUser getDSysUser = getOneUser(userId);
        if (getDSysUser.getRoleid().equals("1")) {
            return Response.fail("无法删除系统管理员");
        }
        this.mapper.deleteByPrimaryKey(userId);
        return Response.success();
    }

    @Override
    public DSysUser getOneUser(Integer userId) {
        DSysUser dSysUser = new DSysUser();
        SysUser sysUser = this.mapper.selectByPrimaryKey(userId);
        covertToDSysUser(dSysUser, sysUser);
        return dSysUser;
    }

    @Override
    public List<DSysUser> getListUserPage(Map<String, String> param) {
        List<DSysUser> responseList = null;
        List<SysUser> userList = this.mapper.listUserPage(param);
        if (userList != null) {
            responseList = new ArrayList<DSysUser>();
            for (int i = 0; i < userList.size(); i++) {
                DSysUser dSysUser = new DSysUser();
                covertToDSysUser(dSysUser, userList.get(i));
                responseList.add(dSysUser);
            }
        }
        List<DSysUser> list = PageUtils.convert(userList, responseList);
        return list;
    }

    @Override
    public DSysUser getOneUserByMap(Map<String, String> param) {
        DSysUser dSysUser = new DSysUser();
        SysUser user = this.mapper.selectOneBySelective(param);
        if (user == null) {
            return null;
        }
        covertToDSysUser(dSysUser, user);
        return dSysUser;
    }

    public void covertToDSysUser(DSysUser dSysUser, SysUser user) {
        try {
            BeanUtils.copyProperties(dSysUser, user);
            dSysUser.setPassword(null);
            dSysUser.setSalt(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void covertToSysUser(SysUser user, DSysUser dSysUser) {
        try {
            BeanUtils.copyProperties(user, dSysUser);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void covertToLoginDSysUser(SysUser sysUser, DSysUser response) {
        response.setAccount(sysUser.getAccount());
        response.setAvatar(sysUser.getAvatar());
        response.setBirthday(sysUser.getBirthday());
        response.setCreatetime(sysUser.getCreatetime());
        response.setEmail(sysUser.getEmail());
        response.setId(sysUser.getId());
        response.setName(sysUser.getName());
        response.setPhone(sysUser.getPhone());
        response.setRoleid(sysUser.getRoleid());
        response.setSex(sysUser.getSex());
        response.setStatus(sysUser.getStatus());
        response.setVersion(sysUser.getVersion());
    }
}
