package com.cts.onecognizant.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.cts.onecognizant.configuration.PayloadConvertorConfiguration;
import com.cts.onecognizant.interfaces.ConversionRouterInterface;
import com.cts.onecognizant.util.ConversionConstants;

@RestController
public class PayloadConvertorController {
	private static Logger log = LoggerFactory.getLogger(PayloadConvertorController.class);
	@Autowired
	ConversionRouterInterface conversionRouterIntfc;

	@Autowired
	PayloadConvertorConfiguration payloadConvertorConfiguration;

	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/payloadConverter", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> payloadConverter(@RequestBody String source,
			@RequestHeader("Content-Type") String contentType) {
		try {

			String output = conversionRouterIntfc.conversionRouter(source, contentType);
			RestTemplate template = new RestTemplate();
			Map<String, Object> requestMap = new HashMap<>();
			String url = UriComponentsBuilder.fromHttpUrl(payloadConvertorConfiguration.getPayloadReceiverEndpoint())
					.buildAndExpand(requestMap).encode().toUri().toString();
			Map<String, String> requestBody = new HashMap<>();
			requestBody.put("output", output);
			HttpHeaders headers = new HttpHeaders();
			headers.setBasicAuth(payloadConvertorConfiguration.getBasicAuthUserName(),
					payloadConvertorConfiguration.getBasicAuthPassword());
			HttpEntity<Map> requestEntity = new HttpEntity<>(requestBody, headers);
			template.exchange(url, HttpMethod.POST, requestEntity, String.class);
			return ResponseEntity.ok(ConversionConstants.SUCCESS_RESPONSE);

		} catch (Exception exception) {
			log.error(ConversionConstants.IO_EXCEPTION, exception);
			return ResponseEntity.ok(ConversionConstants.BAD_REQUEST);
		}

	}

}
