package com.cts.onenetwork.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfigurations {
	
	@Primary
	@Bean("Internal")
	RestTemplate restTemplte(){
		 return new RestTemplate();
	}
	
	@Bean("External")
	RestTemplate restTeamplteExternal(){
		 return new RestTemplate();
	}
}
