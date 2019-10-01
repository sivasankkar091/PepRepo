+package com.cts.onenetwork.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.onenetwork.configuration.PayloadConvertorConfiguration;
import com.cts.onenetwork.interfaces.conversioninterfaces.ConversionRouterInterface;
import com.cts.onenetwork.util.ConversionConstants;

@RestController
@RequestMapping("/onenetwork")
public class PayloadConvertorController {
	private static Logger log = LoggerFactory.getLogger(PayloadConvertorController.class);
	@Autowired
	ConversionRouterInterface conversionRouterIntfc;

	@Autowired
	PayloadConvertorConfiguration payloadConvertorConfiguration;

	@PostMapping(value = "/order/payloadconverter", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> orderPayloadConverter(@RequestBody String source,
			@RequestHeader("Content-Type") String contentType) {
		try {
			
			return ResponseEntity.ok(ConversionConstants.SUCCESS_RESPONSE);

		} catch (Exception exception) {
			log.error(ConversionConstants.IO_EXCEPTION, exception);
			return ResponseEntity.ok(ConversionConstants.BAD_REQUEST);
		}

	}
	@PostMapping(value = "/carrier/payloadconverter", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> carrierPayloadConverter(@RequestBody String source,
			@RequestHeader("Content-Type") String contentType) {
		try {
			
			return ResponseEntity.ok(ConversionConstants.SUCCESS_RESPONSE);

		} catch (Exception exception) {
			log.error(ConversionConstants.IO_EXCEPTION, exception);
			return ResponseEntity.ok(ConversionConstants.BAD_REQUEST);
		}

	}
	@PostMapping(value = "/tripstatus/payloadconverter", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> tripStatusPayloadConverter(@RequestBody String source,
			@RequestHeader("Content-Type") String contentType) {
		try {
			
			return ResponseEntity.ok(ConversionConstants.SUCCESS_RESPONSE);

		} catch (Exception exception) {
			log.error(ConversionConstants.IO_EXCEPTION, exception);
			return ResponseEntity.ok(ConversionConstants.BAD_REQUEST);
		}

	}
	@PostMapping(value = "/tender/payloadconverter", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> tenderPayloadConverter(@RequestBody String source,
			@RequestHeader("Content-Type") String contentType) {
		try {
			
			return ResponseEntity.ok(ConversionConstants.SUCCESS_RESPONSE);

		} catch (Exception exception) {
			log.error(ConversionConstants.IO_EXCEPTION, exception);
			return ResponseEntity.ok(ConversionConstants.BAD_REQUEST);
		}

	}
	@PostMapping(value = "/loadcreation/payloadConverter", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> loadCreationPayloadConverter(@RequestBody String source,
			@RequestHeader("Content-Type") String contentType) {
		try {
			
			return ResponseEntity.ok(ConversionConstants.SUCCESS_RESPONSE);

		} catch (Exception exception) {
			log.error(ConversionConstants.IO_EXCEPTION, exception);
			return ResponseEntity.ok(ConversionConstants.BAD_REQUEST);
		}

	}
	@PostMapping(value = "/delivery/payloadConverter", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> deliveryPayloadConverter(@RequestBody String source,
			@RequestHeader("Content-Type") String contentType) {
		try {
			
			return ResponseEntity.ok(ConversionConstants.SUCCESS_RESPONSE);

		} catch (Exception exception) {
			log.error(ConversionConstants.IO_EXCEPTION, exception);
			return ResponseEntity.ok(ConversionConstants.BAD_REQUEST);
		}

	}
	@PostMapping(value = "/dt/payloadConverter", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> transportationDocumentPayloadConverter(@RequestBody String source,
			@RequestHeader("Content-Type") String contentType) {
		try {
			
			return ResponseEntity.ok(ConversionConstants.SUCCESS_RESPONSE);

		} catch (Exception exception) {
			log.error(ConversionConstants.IO_EXCEPTION, exception);
			return ResponseEntity.ok(ConversionConstants.BAD_REQUEST);
		}

	}

}
