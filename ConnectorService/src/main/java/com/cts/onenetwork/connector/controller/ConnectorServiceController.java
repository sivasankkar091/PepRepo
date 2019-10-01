package com.cts.onenetwork.connector.controller;

import java.util.HashMap;

import org.jboss.logging.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConnectorServiceController {
	private static Logger logger = Logger.getLogger(ConnectorServiceController.class);

	@PostMapping(path = "/transfertoonenetwork", consumes = {
			org.springframework.http.MediaType.APPLICATION_JSON_VALUE })
	public void transferToOneNetwork(@RequestBody String input) {

	}
}
