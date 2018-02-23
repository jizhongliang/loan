package com.hwc.framework.modules.service.impl;

import cn.freesoft.utils.FsUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.github.pagehelper.PageHelper;
import com.hwc.base.api.IdRequest;
import com.hwc.base.api.Request;
import com.hwc.base.api.Response;
import com.hwc.common.aliyun.IHwcCache;
import com.hwc.framework.modules.dao.ArcSysConfigMapper;
import com.hwc.framework.modules.domain.*;
import com.hwc.framework.modules.model.ArcSysConfig;
import com.hwc.framework.modules.service.ArcSysConfigService;
import com.hwc.mybatis.core.AbstractService;
import net.dongliu.requests.Requests;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * 2017/11/01.
 */
@Service
public class ArcSysConfigServiceImpl extends AbstractService<ArcSysConfigMapper, ArcSysConfig> implements ArcSysConfigService {
    private static final Logger logger = LoggerFactory.getLogger(ArcSysConfigServiceImpl.class);

    @Autowired
    private IHwcCache cache;
    private String key = "data:sysconfig";

    public String getConfig(String code) {
        try {
            if (cache.hexists(key, code)) {
                return FsUtils.s(cache.hget(key, code));
            } else {
                ArcSysConfig config = getConfigDb(code);
                if (FsUtils.strsNotEmpty(config)) {
                    cache.hset(key, code, config.getValue());
                    return config.getValue();
                }
                return "";
            }
        } catch (Exception ex) {
            logger.error("获取 sysconfig error ：", ex);
        }
        return "";
    }

    public String getConfigDefault(String code, String def) {
        String ret = getConfig(code);
        if (FsUtils.strsEmpty(ret))
            return def;
        else
            return ret;
    }

    private ArcSysConfig getConfigDb(String code) {
        ArcSysConfig config = new ArcSysConfig();
        config.setCode(code);
        config.setState("1");
        List<ArcSysConfig> configs = this.mapper.select(config);
        if (FsUtils.strsEmpty(configs) || configs.isEmpty()) {
            return null;
        } else {
            return configs.get(0);
        }
    }

    @Override
    public Response updateConfig(SysConfig config) {
        if (FsUtils.strsEmpty(config.getId())) {
            return Response.fail("未找到该记录");
        }
        ArcSysConfig arcSysConfig = this.findById(config.getId());
        if (FsUtils.strsEmpty(arcSysConfig)) {
            return Response.fail("未找到该记录");
        }
        ArcSysConfig c = new ArcSysConfig();
        c.setValue(config.getValue());
        c.setType(config.getType());
        c.setState(config.getState());
        c.setRemark(config.getRemark());
        c.setName(config.getName());
        c.setId(arcSysConfig.getId());
        this.update(c);
        clearCache(config.getCode());
        return Response.success();
    }


    @Override
    public Response insertConfig(SysConfig config) {
        ArcSysConfig arcSysConfig = getConfigDb(config.getCode());
        if (FsUtils.strsEmpty(arcSysConfig)) {
            ArcSysConfig db = new ArcSysConfig();
            db.setCode(config.getCode());
            db.setName(config.getName());
            db.setRemark(config.getRemark());
            db.setState("1");
            db.setType(config.getType());
            db.setValue(config.getValue());
            insert(db);
            cache.hset(key, config.getCode(), config.getValue());
            return Response.success();
        } else {
            return Response.fail("该记录已经存在，请勿重复插入");
        }
    }

    @Override
    public Response deleteConfig(Long id) {
        ArcSysConfig arcSysConfig = this.findById(id);
        if (FsUtils.strsNotEmpty(arcSysConfig)) {
            this.deleteById(id);
            clearCache(arcSysConfig.getCode());
        }
        return Response.success();

    }

    private void clearCache(String code) {
        cache.hdel(key, code);
    }


    @Override
    public Response<OssConfig> getOssConfig(IdRequest<String> request) {
        OssConfig config = new OssConfig();
        config.setAccesskeyid(getConfigDefault("oss_accesskeyid", ""));
        config.setAccesskeysecret(getConfigDefault("oss_accesskeysecret", ""));
        config.setBucketname(getConfigDefault("oss_bucketname", ""));
        config.setEndpoint(getConfigDefault("oss_endpoint", ""));
        String path = getConfigDefault("oss_path", "");
        if (FsUtils.strsEmpty(path)) {
            config.setFolder("data/image/");
        } else {
            JSONObject object = JSON.parseObject(path);
            if (object.containsKey(request.getId())) {
                config.setFolder(object.getString(request.getId()));
            } else {
                config.setFolder("temp/" + request.getId() + "/");
            }
        }
        return Response.success(config);
    }

    @Override
    public Response getSysConfig(ConfigQueryRequest request) {
        List<SysConfig> configs = new ArrayList<>();
        Condition condition = new Condition(ArcSysConfig.class);
        Example.Criteria c = condition.createCriteria();
        if (FsUtils.strsEmpty(request.getType())) {
            return Response.success(configs);
        }
        if (FsUtils.strsNotEmpty(request.getCode())) {
            c.andEqualTo("code", request.getCode());
        }
        if (FsUtils.strsNotEmpty(request.getName())) {
            c.andLike("name", request.getName().replace(" ", "") + "%");
        }
        if (FsUtils.strsNotEmpty(request.getType())) {
            c.andEqualTo("type", request.getType());
        }
        c.andEqualTo("state", "1");
        condition.setOrderByClause("seq");
        PageHelper.startPage(request.getPage(), request.getPageSize());
        List<ArcSysConfig> lists = mapper.selectByCondition(condition);
        for (ArcSysConfig list : lists) {
            SysConfig config = new SysConfig();
            config.setRemark(list.getRemark());
            config.setId(list.getId());
            config.setCode(list.getCode());
            config.setName(list.getName());
            config.setState(list.getState());
            config.setType(list.getType());
            config.setValue(list.getValue());
            configs.add(config);
        }
        return Response.success(configs);
    }

    @Override
    public Response getAppH5(HelpBean bean) {
        String json = "";
        if ("C".equalsIgnoreCase(bean.getType())) {
            json = Requests.get("http://caiwei.oss-cn-hangzhou.aliyuncs.com/agreement/credit_agreement.json").send().readToText();
        } else {
            json = Requests.get("http://caiwei.oss-cn-hangzhou.aliyuncs.com/agreement/credit_agreement.json").charset(Charset.forName("utf-8")).send().readToText();
        }
        logger.info("json:{}", json);
        if (FsUtils.strsNotEmpty(json)) {
            JSONObject jsonObject = JSONObject.parseObject(json);
            H5UrlConfig config = new H5UrlConfig();
            config.setAbout(jsonObject.getString("about"));
            config.setLogin(jsonObject.getString("login"));
            config.setRegister(jsonObject.getString("register"));

            //config.setCreditBorrow("http://www.baidu.com");
            //config.setCreditRepay("http://www.baidu.com");
            // config.setCreditRreRepay("http://www.baidu.com");


            // config.setMortgageBorrow("http://www.baidu.com");
            // config.setMortgageRepay("http://www.baidu.com");
            // config.setMortgageRreRepay("http://www.baidu.com");

            config.setProblem(jsonObject.getString("problem"));
            config.setNotes(jsonObject.getString("notes"));

            return Response.success(config);

        }
        return Response.fail("未找些协议");

    }

    /**
     * 获取实体
     * @param code
     * @return
     */
    @Override
    public ArcSysConfig getSysConfigByCode(String code) {
        ArcSysConfig config = new ArcSysConfig();
        config.setCode(code);
        config.setState("1");
        List<ArcSysConfig> configs = this.mapper.select(config);
        if (FsUtils.strsEmpty(configs) || configs.isEmpty()) {
            return null;
        } else {
            return configs.get(0);
        }
    }
}
