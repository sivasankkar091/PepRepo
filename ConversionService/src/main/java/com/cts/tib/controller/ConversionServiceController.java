package com.cts.tib.controller;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.tib.intfc.ConversionRouterIntfc;

@RestController
public class ConversionServiceController {
	private static Logger log = LoggerFactory.getLogger(ConversionServiceController.class);
	@Autowired
	ConversionRouterIntfc conversionRouterIntfc;

	@PostMapping(value = "/convertToCSV/{fileName}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> convertOrder(@PathVariable("fileName") String fileName, @RequestBody String source,
			@RequestHeader("Content-Type") String contentType) {
		try {
			File file = new File(fileName + ".csv");
			boolean fileCreated = file.createNewFile();
			if (fileCreated) {
				conversionRouterIntfc.conversionRouter(source, file, contentType);
			} else {
				log.info("file creation failed");
			}
			return ResponseEntity.ok("Success");

		} catch (Exception exception) {
			return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
		}

	}

}
