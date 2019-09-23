package com.rabbit.rabbitListener.rabbitListener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.rabbit.rabbitListener")
public class RabbitListenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitListenerApplication.class, args);
	}

}
