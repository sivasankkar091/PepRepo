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

import com.cts.tib.intfc.ConversionRouterInterface;
import com.cts.tib.util.ConversionConstants;

@RestController
public class ConversionServiceController {
	private static Logger log = LoggerFactory.getLogger(ConversionServiceController.class);
	@Autowired
	ConversionRouterInterface conversionRouterIntfc;
	
	@Value("${tranferEndpoint}")
	private String endpontUrl;

	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/payloadConverter", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> payloadConverter(@RequestBody String source,
			@RequestHeader("Content-Type") String contentType) {
		try {

			String output = conversionRouterIntfc.conversionRouter(source, contentType);
			RestTemplate template = new RestTemplate();
			Map<String, Object> requestMap = new HashMap<>();
			String url = UriComponentsBuilder.fromHttpUrl(endpontUrl).buildAndExpand(requestMap).encode().toUri()
					.toString();
			Map<String, String> requestBody = new HashMap<>();
			requestBody.put("output", output);
			log.info("Output before request" + output);
			HttpEntity<Map> requestEntity = new HttpEntity<>(requestBody);
			template.exchange(url, HttpMethod.POST, requestEntity, String.class);
			return ResponseEntity.ok(ConversionConstants.SUCCESS_RESPONSE);

		} catch (Exception exception) {
			log.error(ConversionConstants.IO_EXCEPTION, exception);
			return ResponseEntity.ok(ConversionConstants.BAD_REQUEST);
		}

	}

}
