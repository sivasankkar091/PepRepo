package com.cts.tib.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.cts.tib.intfc.ConversionRouterIntfc;
import com.cts.tib.restTemplateConfig.RestTemplateConfig;

@RestController
public class ConversionServiceController {
	private static Logger log = LoggerFactory.getLogger(ConversionServiceController.class);
	@Autowired
	ConversionRouterIntfc conversionRouterIntfc;

	@Value("${sftp.tranferEndpoint}")
	private String sftpEndpontUrl;

	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/convertToCSV", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> convertOrder(@RequestBody String source,
			@RequestHeader("Content-Type") String contentType) {
		try {

			String output=conversionRouterIntfc.conversionRouter(source,contentType);
			RestTemplate template = RestTemplateConfig.getTemplate();
			Map<String, Object> requestMap = new HashMap<>();
			String url = UriComponentsBuilder.fromHttpUrl(sftpEndpontUrl).buildAndExpand(requestMap).encode().toUri().toString();
			Map<String, String> requestBody = new HashMap<>();
			requestBody.put("csvOutput", output);
			log.info("Output before request" + output);
			HttpEntity<Map> requestEntity = new HttpEntity<>(requestBody);
			template.exchange(url, HttpMethod.POST, requestEntity, String.class);
			return ResponseEntity.ok("success");

		} catch (Exception exception) {
			log.error("Error in writing", exception);
			return ResponseEntity.ok("BadRequest");
		}

	}

}
