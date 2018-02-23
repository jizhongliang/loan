package com.hwc.framework;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ImportResource;

@MapperScan(basePackages = "com.hwc.framework.modules.dao")
@SpringBootApplication
@EnableDiscoveryClient
@ImportResource("classpath:/spring/spring.xml")
public class LoanBestsignApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(LoanBestsignApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(LoanBestsignApplication.class);
    }
}
