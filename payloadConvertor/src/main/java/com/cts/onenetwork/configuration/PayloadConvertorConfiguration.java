package com.cts.onenetwork.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class PayloadConvertorConfiguration {
	
	@Value("${tranferEndpoint}")
	private String payloadReceiverEndpoint;
	
	@Value("$({spring.security.user.name})")
	private String basicAuthUserName;
	
	public String getBasicAuthPassword() {
		return basicAuthPassword;
	}

	public void setBasicAuthPassword(String basicAuthPassword) {
		this.basicAuthPassword = basicAuthPassword;
	}

	public String getBasicAuthUserName() {
		return basicAuthUserName;
	}

	public void setBasicAuthUserName(String basicAuthUserName) {
		this.basicAuthUserName = basicAuthUserName;
	}

	@Value("$(spring.security.user.password)")
	private String basicAuthPassword;

	public String getPayloadReceiverEndpoint() {
		return payloadReceiverEndpoint;
	}

	public void setPayloadReceiverEndpoint(String payloadReceiverEndpoint) {
		this.payloadReceiverEndpoint = payloadReceiverEndpoint;
	}
	
}
