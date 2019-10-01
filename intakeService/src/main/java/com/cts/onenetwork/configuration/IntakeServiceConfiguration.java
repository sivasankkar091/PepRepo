package com.cts.onenetwork.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class IntakeServiceConfiguration {

	@Value("${connectorservice.endpoint.url}")
	private String connectorServiceEndpoint;

	@Value("$({spring.security.user.name})")
	private String basicAuthUserName;

	@Value("$(spring.security.user.password)")
	private String basicAuthPassword;

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

	public String getConnectorServiceEndpoint() {
		return connectorServiceEndpoint;
	}

	public void setConnectorServiceEndpoint(String connectorServiceEndpoint) {
		this.connectorServiceEndpoint = connectorServiceEndpoint;
	}

}
