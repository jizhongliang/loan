package com.hwc.framework.common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by   on 2017/11/29.
 */
public class BeanUtil implements ApplicationContextAware {
    private static final Logger logger = LoggerFactory.getLogger(BeanUtil.class);

    private static ApplicationContext ctx = null;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(BeanUtil.ctx == null){
            BeanUtil.ctx  = applicationContext;
        }
    }

    public static ApplicationContext getApplicationContext() {
        return ctx;
    }

    public BeanUtil() {
    }

    public static <T> T getBean(String name) {
        return (T) getApplicationContext().getBean(name);
    }

    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    //通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }

}