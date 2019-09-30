package com.cts.onenetwork.controller;

import java.util.HashMap;

import org.jboss.logging.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayloadReceiverController {
	private static Logger log = Logger.getLogger(PayloadReceiverController.class);
	
	@PostMapping(path = "/payloadReceiver")
	public void destinationReceiver(@RequestBody HashMap<String,String> sourceMap) {
		log.info("The output of service is"+sourceMap.get("output"));
	}
}
