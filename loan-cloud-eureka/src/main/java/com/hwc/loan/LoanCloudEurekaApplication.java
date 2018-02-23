package com.hwc.loan;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class LoanCloudEurekaApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(LoanCloudEurekaApplication.class)
				.web(true).run(args);
	}
}
