package com.cts.tib.sftpadapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

@SpringBootApplication(scanBasePackages="com.cts.tib")
@IntegrationComponentScan(basePackages="com.cts.tib")
@EnableIntegration
@EnableRedisRepositories(basePackages = "com.cts.tib")
public class SftpAdapterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SftpAdapterApplication.class, args);
	}

}
