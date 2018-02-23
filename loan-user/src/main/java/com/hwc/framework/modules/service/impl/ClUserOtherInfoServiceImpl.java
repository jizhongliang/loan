package com.hwc.framework.modules.service.impl;

import cn.freesoft.utils.FsUtils;
import com.hwc.framework.modules.dao.ClUserOtherInfoMapper;
import com.hwc.framework.modules.domain.DUserOtherInfo;
import com.hwc.framework.modules.model.ClUserOtherInfo;
import com.hwc.framework.modules.service.ClUserOtherInfoService;
import com.hwc.mybatis.core.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 2017/10/23.
 */
@Service
public class ClUserOtherInfoServiceImpl extends AbstractService<ClUserOtherInfoMapper, ClUserOtherInfo> implements ClUserOtherInfoService {
    private static final Logger logger = LoggerFactory.getLogger(ClUserOtherInfoServiceImpl.class);


    @Override
    public boolean updateUserOtherInfo(DUserOtherInfo dUserOtherInfo) {
        if (FsUtils.strsEmpty(dUserOtherInfo.getAccnt(), dUserOtherInfo.getUserId(), dUserOtherInfo.getCat())){
            return false;
        }
        if (FsUtils.strsEmpty(dUserOtherInfo.getId())){
            ClUserOtherInfo update = new ClUserOtherInfo();
            update.setAccnt(dUserOtherInfo.getAccnt());
            update.setUserId(dUserOtherInfo.getUserId());
            update.setUnionid(dUserOtherInfo.getUnionid());
            update.setCat(dUserOtherInfo.getCat());
            update.setCreateTime(new Date());
            update.setUpdateTime(new Date());
            int re = this.mapper.insert(update);
            if (re > 0){
                return true;
            }else {
                return false;
            }
        }else {
            ClUserOtherInfo update = new ClUserOtherInfo();
            update.setId(dUserOtherInfo.getId());
            update.setAccnt(dUserOtherInfo.getAccnt());
            update.setUserId(dUserOtherInfo.getUserId());
            update.setUnionid(dUserOtherInfo.getUnionid());
            update.setCat(dUserOtherInfo.getCat());
            update.setUpdateTime(new Date());
            this.update(update);
            return true;
        }

    }

    @Override
    public DUserOtherInfo getUserOtherInfoByOpenid(String openid) {
        if (FsUtils.strsEmpty(openid)){
            return null;
        }
        ClUserOtherInfo select = new ClUserOtherInfo();
        select.setAccnt(openid);
        List<ClUserOtherInfo> list = this.mapper.select(select);
        if (list != null && list.size() >0){
            if (list.size() > 1){
                DUserOtherInfo dUserOtherInfo = new DUserOtherInfo();
                return dUserOtherInfo;
            }
            return convertToDUserOtherInfo(list.get(0));
        }else {
            return null;
        }
    }

    @Override
    public DUserOtherInfo getUserOtherInfoByUserId(Long userId) {
        if (FsUtils.strsEmpty(userId)){
            return null;
        }
        ClUserOtherInfo select  = new ClUserOtherInfo();
        select.setUserId(userId);
        ClUserOtherInfo clUserOtherInfo = this.mapper.selectOne(select);
        if (clUserOtherInfo != null){
            return convertToDUserOtherInfo(clUserOtherInfo);
        }else {
            return null;
        }
    }

    private DUserOtherInfo convertToDUserOtherInfo(ClUserOtherInfo clUserOtherInfo){
        DUserOtherInfo dUserOtherInfo = new DUserOtherInfo();
        dUserOtherInfo.setId(clUserOtherInfo.getId());
        dUserOtherInfo.setAccnt(clUserOtherInfo.getAccnt());
        dUserOtherInfo.setUserId(clUserOtherInfo.getUserId());
        dUserOtherInfo.setUnionid(clUserOtherInfo.getUnionid());
        dUserOtherInfo.setCat(clUserOtherInfo.getCat());
        dUserOtherInfo.setCreateTime(clUserOtherInfo.getCreateTime());
        dUserOtherInfo.setUpdateTime(clUserOtherInfo.getUpdateTime());
        return dUserOtherInfo;
    }
}
