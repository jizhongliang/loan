package com.hwc.framework.config;

import com.hwc.base.sdk.core.Client;
import com.hwc.base.sdk.core.ClientConfig;
import com.hwc.base.sdk.core.SdkConnectFactory;
import com.hwc.web.core.rest.RestTemplateConnectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by  on 2017/11/6.
 */
@Configuration
public class SdkConfig {
    @Autowired
    private Environment env;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

    @Bean
    public SdkConnectFactory sdkConnectFactory() {
        RestTemplateConnectFactory connectFactory = new RestTemplateConnectFactory(restTemplate());
        return connectFactory;
    }

    @Bean("userClient")
    public Client userClient() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setHost(env.getProperty("remote.module.user"));
        Client client = new Client(clientConfig);
        client.setConnectFactory(sdkConnectFactory());
        return client;
    }

    @Bean("borrowClient")
    public Client borrowClient() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setHost(env.getProperty("remote.module.borrow"));
        Client client = new Client(clientConfig);
        client.setConnectFactory(sdkConnectFactory());
        return client;
    }
}
