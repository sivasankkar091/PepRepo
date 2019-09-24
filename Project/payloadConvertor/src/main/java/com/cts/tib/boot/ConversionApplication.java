package com.cts.tib.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cts.tib.util.ConversionConstants;

@SpringBootApplication(scanBasePackages = ConversionConstants.BASE_PACKAGE)
public class ConversionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConversionApplication.class, args);
	}

}
