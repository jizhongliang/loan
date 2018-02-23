package com.hwc.framework;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.client.RestTemplate;

import com.hwc.framework.modules.job.QuartRepayQuery;

@MapperScan(basePackages = "com.hwc.framework.modules.dao")
@SpringBootApplication
@EnableDiscoveryClient
@ImportResource("classpath:/spring/spring.xml")
public class P2PApplication extends SpringBootServletInitializer {
    @Autowired
    private QuartRepayQuery scheduleTasks;

    public static void main(String[] args) {

        SpringApplication.run(P2PApplication.class, args);
    }
    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(P2PApplication.class);
    }

}
