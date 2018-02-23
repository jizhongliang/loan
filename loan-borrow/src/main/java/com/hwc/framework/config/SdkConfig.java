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
import org.springframework.web.client.RestTemplate;

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
        return new RestTemplate();
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

}
