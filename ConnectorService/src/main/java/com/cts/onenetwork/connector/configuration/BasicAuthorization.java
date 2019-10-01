package com.cts.onenetwork.connector.configuration;

import java.io.IOException;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.cts.onenetwork.connector.controller.ConnectorServiceController;

import sun.misc.BASE64Decoder;

@Configuration
@Component
public class BasicAuthorization {

	private static Logger logger = Logger.getLogger(BasicAuthorization.class);

	@Value("${spring.security.user.name}")
	private String basicAuthUserName;

	@Value("${spring.security.user.password}")
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

	/*
	 *  method to validate user authorization
		returns true if user is valid else returns false
	 */
	@SuppressWarnings("restriction")
	public boolean isAuthendicated(String authString) {
		String[] authParts = authString.split("\\s+");
		String authInfo = authParts[1];
		byte[] bytes = null;
		try {
			bytes = new BASE64Decoder().decodeBuffer(authInfo);
		} catch (IOException e) {
			logger.error("Exception occured " + e.getMessage());
		}
		String decodedAuthString = new String(bytes);
		logger.info("decodedAuthString  " + decodedAuthString);
		String validAuthString = getBasicAuthUserName().concat(":").concat(getBasicAuthPassword());
		logger.info("validAuthString  " + validAuthString);
		if (decodedAuthString.equals(validAuthString)) {
			return true;
		}
		return false;

	}

}
