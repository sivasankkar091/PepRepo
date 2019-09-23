package com.cts.tib.controller;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
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

	@PostMapping(value = "/convertToCSV/{fileName}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> convertOrder(@PathVariable("fileName") String fileName, @RequestBody String source,
			@RequestHeader("Content-Type") String contentType) {
		try {
			File file = new File(fileName + ".csv");
			file.createNewFile();
			conversionRouterIntfc.conversionRouter(source, file, contentType);
			FileInputStream inputStream = new FileInputStream(file);
			MultipartFile inputFile = new MockMultipartFile("file", file.getName(), "text/csv", inputStream);
			RestTemplate template = RestTemplateConfig.getTemplate();
			Map<String, Object> requestMap = new HashMap<>();
			requestMap.put("fileName", fileName);
			URI uri = UriComponentsBuilder.fromHttpUrl(sftpEndpontUrl).buildAndExpand(requestMap).encode().toUri();
			MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
			ContentDisposition contentDisposition = ContentDisposition.builder("form-data").name("file")
					.filename(fileName).build();
			map.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
			HttpEntity<byte[]> fileEntity = new HttpEntity<>(inputFile.getBytes(), map);
			MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
			requestBody.add("file", fileEntity);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);
			HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
			template.exchange(uri, HttpMethod.POST, requestEntity, String.class);
			return ResponseEntity.ok("success");

		} catch (Exception exception) {
			log.error("Error in writing", exception);
			return ResponseEntity.ok("BadRequest");
		}

	}

}
