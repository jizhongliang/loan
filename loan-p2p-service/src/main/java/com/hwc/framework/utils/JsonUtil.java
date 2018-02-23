package com.hwc.framework.utils;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hwc.framework.modules.domain.Response;
import com.hwc.framework.modules.service.impl.BorrowerUserinfoServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Map;

public class JsonUtil {
    private static final Logger logger = LoggerFactory.getLogger(BorrowerUserinfoServiceImpl.class);
    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false); //去掉默认的时间戳格式
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true); //单引号处理
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); //反序列化时，属性不存在的兼容处理
        mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false); //设置不写NULLmap值
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); //空值不序列化
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")); //序列化时，日期的统一格式

    }

    public static <T> T parse(String value,Class<T> clz){
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        try {
            T obj = mapper.readValue(value, clz);
            if(obj instanceof Map){
                return (T) MapUtil.removeEmptyStr((Map<String, Object>) obj);
            }
            return obj;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static String getData(String result){
        try {
            Response res = JSON.parseObject(result,Response.class);
            String data = Des.decode(Des.secretKey,res.getData());
            logger.info("明文结果{}",data);
            return data;
        } catch (Exception e) {
            logger.warn("Json字符串解析报错{}",e.getMessage());
        }
        return null;
    }
}
