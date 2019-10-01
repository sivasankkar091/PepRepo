package com.cts.onenetwork.connector;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class ConnectorServiceTest<T> {

	public ConnectorServiceTest() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test
	public void test() {
		RestTemplate template = new RestTemplate();
		Map<String, Object> requestMap = new HashMap<>();
		String url = UriComponentsBuilder.fromHttpUrl("http://localhost:8082/connector").buildAndExpand(requestMap)
				.encode().toUri().toString();
		Map<String, String> requestBody = new HashMap<>();
		requestBody.put("csvOutput", "{}");
		HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth("pepsi", "pepsi");
		HttpEntity<Map> requestEntity = new HttpEntity<>(requestBody, headers);
		ResponseEntity<String> responseEntity=template.exchange(url, HttpMethod.POST, requestEntity, String.class);
		assertEquals(ResponseEntity.ok("success").getStatusCode(),responseEntity.getStatusCode());
	}
}
