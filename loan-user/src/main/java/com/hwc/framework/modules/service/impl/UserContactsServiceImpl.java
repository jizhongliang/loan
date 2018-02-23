package com.hwc.framework.modules.service.impl;

import cn.freesoft.utils.FsUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.hwc.base.api.Response;
import com.hwc.common.aliyun.IHwcCache;
import com.hwc.framework.modules.domain.DUserContacts;
import com.hwc.framework.modules.domain.DUserMessage;
import com.hwc.framework.modules.service.UserContactsService;
import com.hwc.framework.modules.utils.HttpUtil;
import com.hwc.framework.modules.utils.PathUtil;
import com.hwc.mybatis.core.AbstractService;
import com.hwc.mybatis.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 2017/10/23.
 */
@Service
public class UserContactsServiceImpl implements UserContactsService {

    @Autowired
    private IHwcCache iHwcCache;

    @Override
    public Response getContactsList(Long userId, int currentPage, int pageSize) {
        if (FsUtils.strsEmpty(userId)){
            return Response.fail("参数错误");
        }
        Page<DUserContacts> page = new Page<DUserContacts>();
        try {
            String key = "contacts:" + userId;
            String ctx = iHwcCache.get(key);
            if (FsUtils.strsEmpty(ctx)) {
                String fullPath = PathUtil.getFullResPath("contacts/" + userId + ".json");
                ctx = HttpUtil.doGet(fullPath);
                if (FsUtils.strsEmpty(ctx) || ctx.startsWith("<?xml")) {
                    ctx = "";
                }

                if (FsUtils.strsNotEmpty(ctx)) {
                    Long expire = FsUtils.getDaySpan(new Date());
                    iHwcCache.set(key, expire, ctx);
                }
            }
            if (FsUtils.strsNotEmpty(ctx)) {
                JSONArray array = JSON.parseArray(ctx);
                page.setTotal(array.size());
                int start = (currentPage - 1) * pageSize;
                if (start < array.size()) {
                    int max = start + pageSize;
                    if (max >= array.size()) {
                        max = array.size() - 1;
                    }
                    for (int i = start; i < max; i++) {
                        DUserContacts contacts = new DUserContacts();
                        contacts.setName(JSON.parseObject(JSON.toJSONString(array.get(i))).getString("name"));
                        contacts.setPhone(JSON.parseObject(JSON.toJSONString(array.get(i))).getString("phone"));
                        contacts.setUserId(userId);
                        page.add(contacts);
                    }
                }
            }

        } catch (Exception ex) {
            FsUtils.log_error(ex);
        }
        return Response.success(page.getResult());
    }

    @Override
    public Response getMessageList(Long userId, int currentPage, int pageSize) {
        if (FsUtils.strsEmpty(userId)){
            return Response.fail("参数错误");
        }
        Page<DUserMessage> page = new Page<DUserMessage>();
        try {
            String key = "message:" + userId;
            String ctx = iHwcCache.get(key);
            if (FsUtils.strsEmpty(ctx)) {
                String fullPath = PathUtil.getFullResPath("message/" + userId + ".json");
                ctx = HttpUtil.doGet(fullPath);
                if (FsUtils.strsEmpty(ctx) || ctx.startsWith("<?xml")) {
                    ctx = "";
                }

                if (FsUtils.strsNotEmpty(ctx)) {
                    Long expire = FsUtils.getDaySpan(new Date());
                    iHwcCache.set(key, expire, ctx);
                }
            }
            if (FsUtils.strsNotEmpty(ctx)) {
                JSONArray array = JSON.parseArray(ctx);
                page.setTotal(array.size());
                int start = (currentPage - 1) * pageSize;
                if (start < array.size()) {
                    int max = start + pageSize;
                    if (max >= array.size()) {
                        max = array.size() - 1;
                    }
                    for (int i = start; i < max; i++) {
                        DUserMessage message = new DUserMessage();
                        message.setUserId(userId);
                        message.setName(JSON.parseObject(JSON.toJSONString(array.get(i))).getString("name"));
                        message.setPhone(JSON.parseObject(JSON.toJSONString(array.get(i))).getString("phone"));
                        message.setCtx(JSON.parseObject(JSON.toJSONString(array.get(i))).getString("message"));
                        message.setTime(JSON.parseObject(JSON.toJSONString(array.get(i))).getDate("time"));
                        message.setType(JSON.parseObject(JSON.toJSONString(array.get(i))).getString("type"));
                        page.add(message);
                    }
                }
            }

        } catch (Exception ex) {
            FsUtils.log_error(ex);
        }
        return Response.success(page.getResult());
    }
}
