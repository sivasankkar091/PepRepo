package com.cts.tib.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cts.tib.util.PayloadReceiverConstants;

@SpringBootApplication(scanBasePackages=PayloadReceiverConstants.BASE_PACKAGE)
public class PayloadReceiverApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayloadReceiverApplication.class, args);
	}

}
