package com.hwc.framework.modules.service.impl;

import cn.freesoft.utils.FsUtils;
import com.alibaba.fastjson.JSON;
import com.hwc.base.api.Response;
import com.hwc.common.aliyun.IHwcCache;
import com.hwc.framework.modules.dao.ClMortgageImgMapper;
import com.hwc.framework.modules.model.ClMortgageImg;
import com.hwc.framework.modules.service.ArcSysConfigService;
import com.hwc.framework.modules.service.ClMortgageImgService;
import com.hwc.mybatis.core.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.swing.*;
import java.awt.font.ImageGraphicAttribute;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
 * 2017/10/23.
 * 抵押物申請借款
 */
@Service
public class ClMortgageImgServiceImpl extends AbstractService<ClMortgageImgMapper, ClMortgageImg> implements ClMortgageImgService {
    private static final Logger logger = LoggerFactory.getLogger(ClMortgageImgServiceImpl.class);

    @Autowired
    private IHwcCache cache;
    @Autowired
    private ArcSysConfigService sysConfigService;

    private ClMortgageImg create(int seq) {
        ClMortgageImg img = new ClMortgageImg();
        img.setCreated(new Date());
        img.setSeq(seq);
        return img;

    }

    public void uploadImg(String url, Long userId, Long mid, String cat, int seq) {
        ClMortgageImg img = create(seq);
        img.setImgCat(cat);
        img.setUserId(userId);
        img.setImgUrl(url);
        img.setMid(mid);
        insert(img);
        expireCache(cat, mid);
    }

    private void deleteImg(String cat, Long mid) {
        Condition condition = new Condition(ClMortgageImg.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("mid", mid);
        criteria.andEqualTo("imgCat", cat);
        mapper.deleteByCondition(condition);
        expireCache(cat, mid);
    }

    private void expireCache(String cat, Long mid) {
        String key = "mortgageImg:mid:" + mid;
        Set<String> keys = cache.keys(key+"*");
        if (FsUtils.strsNotEmpty(keys)) {
            for (String s : keys) {
                cache.del(s);
            }
        }

    }

    public Response batchUploadImg(List<String> url, Long userid, Long mid, String cat) {
        int i = 1;
        try {
            deleteImg(cat, mid);
            for (String s : url) {
                try {
                    uploadImg(s, userid, mid, cat, i);
                    i++;
                } catch (Exception ex) {
                    logger.error("上传图片出错", ex);
                    return Response.fail("上传图片出错" + ex.getMessage());
                }
            }
            expireCache(cat, mid);
            return Response.success();
        } catch (Exception ex) {
            logger.error("batchUploadImg", ex);
            return Response.fail(ex.getMessage());
        }
    }

    public List<String> getImg(String cat, Long mid) {
        String key = "mortgageImg:mid:" + mid + ":cat:" + cat;
        if (cache.exists(key)) {
            return JSON.parseArray(cache.get(key), String.class);
        } else {
            Condition condition = new Condition(ClMortgageImg.class);
            Example.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("mid", mid);
            criteria.andEqualTo("imgCat", cat);
            condition.setOrderByClause("seq");
            List<ClMortgageImg> imgs = mapper.selectByCondition(condition);
            List<String> imgListStr = new ArrayList<>();
            String prefix = sysConfigService.getConfigDefault("oss_http_prefix", "http://caiwei.oss-cn-hangzhou.aliyuncs.com/");
            if (FsUtils.strsNotEmpty(imgs)) {
                for (ClMortgageImg img : imgs) {
                    if (!img.getImgUrl().toLowerCase().startsWith("http")) {
                        imgListStr.add(prefix + img.getImgUrl());
                    } else {
                        imgListStr.add(img.getImgUrl());
                    }
                }
                Long expire = FsUtils.getDateSpan(new Date(), FsUtils.addDate(new Date(), 3));
                cache.set(key, expire, imgListStr);
            }
            return imgListStr;
        }


    }


}
