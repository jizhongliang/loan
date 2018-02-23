/**
 * Copyright (c) 2018 All Rights Reserved.
 */
package com.hwc.framework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * @author jinlilong
 * @version $Id: LinkFaceConfig.java, v 0.1 2018年2月1日 上午10:10:47 jinlilong Exp $
 */
@Component
public class LinkFaceConfig {

    /**  */
    @Value("${linkface.apikey}")
    private String apikey;

    /**  */
    @Value("${linkface.secretkey}")
    private String secretkey;

    /**  */
    @Value("${linkface.host1}")
    private String h5Host;

    /**  */
    @Value("${linkface.host2}")
    private String appHost;

    /**  身份正面照解析url*/
    @Value("${linkface.ocr.idcard.host}")
    private String ocrHost;

    /**
     * Getter method for property <tt>apikey</tt>.
     * 
     * @return property value of apikey
     */
    public String getApikey() {
        return apikey;
    }

    /**
     * Setter method for property <tt>apikey</tt>.
     * 
     * @param apikey value to be assigned to property apikey
     */
    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    /**
     * Getter method for property <tt>secretkey</tt>.
     * 
     * @return property value of secretkey
     */
    public String getSecretkey() {
        return secretkey;
    }

    /**
     * Setter method for property <tt>secretkey</tt>.
     * 
     * @param secretkey value to be assigned to property secretkey
     */
    public void setSecretkey(String secretkey) {
        this.secretkey = secretkey;
    }

    /**
     * Getter method for property <tt>h5Host</tt>.
     * 
     * @return property value of h5Host
     */
    public String getH5Host() {
        return h5Host;
    }

    /**
     * Setter method for property <tt>h5Host</tt>.
     * 
     * @param h5Host value to be assigned to property h5Host
     */
    public void setH5Host(String h5Host) {
        this.h5Host = h5Host;
    }

    /**
     * Getter method for property <tt>appHost</tt>.
     * 
     * @return property value of appHost
     */
    public String getAppHost() {
        return appHost;
    }

    /**
     * Setter method for property <tt>appHost</tt>.
     * 
     * @param appHost value to be assigned to property appHost
     */
    public void setAppHost(String appHost) {
        this.appHost = appHost;
    }

    /**
     * Getter method for property <tt>ocrHost</tt>.
     * 
     * @return property value of ocrHost
     */
    public String getOcrHost() {
        return ocrHost;
    }

    /**
     * Setter method for property <tt>ocrHost</tt>.
     * 
     * @param ocrHost value to be assigned to property ocrHost
     */
    public void setOcrHost(String ocrHost) {
        this.ocrHost = ocrHost;
    }

}
