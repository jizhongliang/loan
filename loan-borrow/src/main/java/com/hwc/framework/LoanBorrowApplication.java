package com.hwc.framework;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan(basePackages = "com.hwc.framework.modules.dao")
@SpringBootApplication
@ServletComponentScan
@EnableDiscoveryClient
public class LoanBorrowApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(LoanBorrowApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(LoanBorrowApplication.class);
    }
}
