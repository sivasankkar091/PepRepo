package com.rabbit.publish.rabbitPublisher;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.rabbit.publish")
@EnableRabbit
public class RabbitPublisherApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitPublisherApplication.class, args);
	}

}
