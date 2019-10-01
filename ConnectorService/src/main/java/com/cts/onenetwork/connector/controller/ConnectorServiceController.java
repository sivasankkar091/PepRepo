package com.cts.onenetwork.connector.controller;

import java.util.HashMap;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.cts.onenetwork.connector.configuration.BasicAuthorization;

@RestController
public class ConnectorServiceController {
	private static Logger logger = Logger.getLogger(ConnectorServiceController.class);

	@Autowired
	BasicAuthorization basicAuth = new BasicAuthorization();

	@PostMapping(path = "/connector", consumes = { org.springframework.http.MediaType.APPLICATION_JSON_VALUE })
	public void connector(@RequestBody HashMap<String, String> sourceMap,
			@RequestHeader("authorization") String authString) {
		if (basicAuth.isAuthendicated(authString)) {
			logger.info("User Authenticated Successfully");
			logger.info("The output of service is" + sourceMap.get("output"));
		}
	}
}
