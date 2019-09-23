package com.cts.tib.sftpPollingAdapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

@SpringBootApplication(scanBasePackages = "com.cts.tib")
@EnableIntegration
@IntegrationComponentScan(basePackages = "com.cts.tib")
public class SftpPollingAdapterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SftpPollingAdapterApplication.class, args);
	}

}
