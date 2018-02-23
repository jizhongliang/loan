package com.hwc.framework.modules.service.impl;

import cn.freesoft.utils.FsUtils;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.dao.CloanUserMapper;
import com.hwc.framework.modules.domain.DCloanUserModel;
import com.hwc.framework.modules.model.CloanUserModel;
import com.hwc.framework.modules.service.CloanUserService;
import com.hwc.mybatis.core.AbstractService;
import com.hwc.mybatis.util.PageUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 2017/10/23.
 */
@Service
public class CloanUserServiceImpl extends AbstractService<CloanUserMapper, CloanUserModel> implements CloanUserService {
    private static final Logger logger = LoggerFactory.getLogger(CloanUserServiceImpl.class);


    @Override
    public List<DCloanUserModel> listModelPage(Map<String, Object> params) {
        List<DCloanUserModel> responseList = null;
        List<CloanUserModel>  cloanUserList = this.mapper.listModelPage(params);
        if (cloanUserList != null){
            responseList = new ArrayList<DCloanUserModel>();
            for (int i=0; i<cloanUserList.size(); i++){
                DCloanUserModel dCloanUserModel = new DCloanUserModel();
                covertToDSysUser(dCloanUserModel,cloanUserList.get(i));
                responseList.add(dCloanUserModel);
            }
        }
        List<DCloanUserModel> list = PageUtils.convert(cloanUserList,responseList);
        return  list;
    }

    @Override
    public Response getCloanUserById(Long userId) {
        if (FsUtils.strsEmpty(userId)){
            return Response.fail();
        }
        CloanUserModel cloanUserModel = this.mapper.getModelById(userId);
        if (cloanUserModel == null){
            return Response.fail("用户不存在");
        }else {
            DCloanUserModel dCloanUserModel = new DCloanUserModel();
            covertToDSysUser(dCloanUserModel,cloanUserModel);
            return Response.success(dCloanUserModel);
        }
    }

    @Override
    public DCloanUserModel getCloanUserOne(Long userId) {
        if (FsUtils.strsEmpty(userId)){
            return null;
        }
        CloanUserModel cloanUserModel = this.mapper.getModelById(userId);
        if (cloanUserModel != null){
            DCloanUserModel dCloanUserModel = new DCloanUserModel();
            covertToDSysUser(dCloanUserModel,cloanUserModel);
            return dCloanUserModel;
        }
        return null;
    }


    public void covertToDSysUser (DCloanUserModel dCloanUserModel, CloanUserModel cloanUserModel){
        try {
            BeanUtils.copyProperties(dCloanUserModel,cloanUserModel);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
