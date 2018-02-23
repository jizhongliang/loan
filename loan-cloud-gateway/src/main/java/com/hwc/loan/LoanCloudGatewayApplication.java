package com.hwc.loan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class LoanCloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoanCloudGatewayApplication.class, args);
    }
}
