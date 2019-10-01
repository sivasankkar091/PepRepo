package com.cts.onenetwork.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.cts.onenetwork.configuration.IntakeServiceConfiguration;
import com.cts.onenetwork.service.CarrierToMovementTrackingConversion;
import com.cts.onenetwork.service.DeliveryToShipmentInboundConversion;
import com.cts.onenetwork.service.LoadCreationToShipmentInboundConversion;
import com.cts.onenetwork.service.PurchaseOrderToOneNetworkPurchaseOrderConversion;
import com.cts.onenetwork.service.SalesOrderToOneNetworkSalesOrderConversion;
import com.cts.onenetwork.service.StockTransferToEnhancedDeploymentOrderConversion;
import com.cts.onenetwork.service.TenderToShipmentTrackingConversion;
import com.cts.onenetwork.service.TransportationDocumentToShipmentInboundConversion;
import com.cts.onenetwork.service.TripStatusToTripStatusCompletedConversion;
import com.cts.onenetwork.service.TripStatusToTripStatusUpdateConverion;
import com.cts.onenetwork.util.IntakeServiceConstants;

@RestController
@RequestMapping("/onenetwork")
public class IntakeServiceController {
	private static Logger log = LoggerFactory.getLogger(IntakeServiceController.class);

	@Autowired
	IntakeServiceConfiguration payloadConvertorConfiguration;

	@Autowired
	CarrierToMovementTrackingConversion carrierToMovementTrackingConversion;
	@Autowired
	DeliveryToShipmentInboundConversion deliveryToShipmentInboundConversion;
	@Autowired
	LoadCreationToShipmentInboundConversion loadCreationToShipmentInboundConversion;
	@Autowired
	PurchaseOrderToOneNetworkPurchaseOrderConversion purchaseOrderToOneNetworkPurchaseOrderConversion;
	@Autowired
	SalesOrderToOneNetworkSalesOrderConversion salesOrderToOneNetworkSalesOrderConversion;
	@Autowired
	StockTransferToEnhancedDeploymentOrderConversion stockTransferToEnhancedDeploymentOrderConversion;
	@Autowired
	TenderToShipmentTrackingConversion tenderToShipmentTrackingConversion;
	@Autowired
	TransportationDocumentToShipmentInboundConversion transportationDocumentToShipmentInboundConversion;
	@Autowired
	TripStatusToTripStatusCompletedConversion tripStatusToTripStatusCompletedConversion;
	@Autowired
	TripStatusToTripStatusUpdateConverion tripStatusToTripStatusUpdateConverion;
	@Autowired
	IntakeServiceConfiguration intakeServiceConfig;

	@PostMapping(value = "/order/sales", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getSalesOrderFromSap(@RequestBody String payload) {
		try {
			String connectServicePayload = salesOrderToOneNetworkSalesOrderConversion.transform(payload);
			RestTemplate template = new RestTemplate();
			Map<String, Object> requestMap = new HashMap<>();
			String url = UriComponentsBuilder.fromHttpUrl(intakeServiceConfig.getConnectorServiceEndpoint())
					.buildAndExpand(requestMap).encode().toUri().toString();
			Map<String, String> requestBody = new HashMap<>();
			requestBody.put("connectorServicePayload", connectServicePayload);
			HttpHeaders header = new HttpHeaders();
			header.setBasicAuth(intakeServiceConfig.getBasicAuthUserName(), intakeServiceConfig.getBasicAuthPassword());
			HttpEntity<Map> requestEntity = new HttpEntity<>(requestBody, header);
			template.exchange(url, HttpMethod.POST, requestEntity, String.class);
			return ResponseEntity.ok(IntakeServiceConstants.SUCCESS_RESPONSE);

		} catch (Exception exception) {
			log.error(IntakeServiceConstants.IO_EXCEPTION, exception);
			return ResponseEntity.ok(IntakeServiceConstants.BAD_REQUEST);
		}

	}

	@PostMapping(value = "/order/stocktransfer", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getStockTransferOrderFromSap(@RequestBody String payload) {
		try {
			String connectServicePayload = stockTransferToEnhancedDeploymentOrderConversion.transform(payload);
			RestTemplate template = new RestTemplate();
			Map<String, Object> requestMap = new HashMap<>();
			String url = UriComponentsBuilder.fromHttpUrl(intakeServiceConfig.getConnectorServiceEndpoint())
					.buildAndExpand(requestMap).encode().toUri().toString();
			Map<String, String> requestBody = new HashMap<>();
			requestBody.put("connectorServicePayload", connectServicePayload);
			HttpHeaders header = new HttpHeaders();
			header.setBasicAuth(intakeServiceConfig.getBasicAuthUserName(), intakeServiceConfig.getBasicAuthPassword());
			HttpEntity<Map> requestEntity = new HttpEntity<>(requestBody, header);
			template.exchange(url, HttpMethod.POST, requestEntity, String.class);
			return ResponseEntity.ok(IntakeServiceConstants.SUCCESS_RESPONSE);

		} catch (Exception exception) {
			log.error(IntakeServiceConstants.IO_EXCEPTION, exception);
			return ResponseEntity.ok(IntakeServiceConstants.BAD_REQUEST);
		}

	}

	@PostMapping(value = "/order/purchase", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getPurchaseOrderFromSap(@RequestBody String payload) {
		try {
			String connectServicePayload = purchaseOrderToOneNetworkPurchaseOrderConversion.transform(payload);
			RestTemplate template = new RestTemplate();
			Map<String, Object> requestMap = new HashMap<>();
			String url = UriComponentsBuilder.fromHttpUrl(intakeServiceConfig.getConnectorServiceEndpoint())
					.buildAndExpand(requestMap).encode().toUri().toString();
			Map<String, String> requestBody = new HashMap<>();
			requestBody.put("connectorServicePayload", connectServicePayload);
			HttpHeaders header = new HttpHeaders();
			header.setBasicAuth(intakeServiceConfig.getBasicAuthUserName(), intakeServiceConfig.getBasicAuthPassword());
			HttpEntity<Map> requestEntity = new HttpEntity<>(requestBody, header);
			template.exchange(url, HttpMethod.POST, requestEntity, String.class);
			return ResponseEntity.ok(IntakeServiceConstants.SUCCESS_RESPONSE);

		} catch (Exception exception) {
			log.error(IntakeServiceConstants.IO_EXCEPTION, exception);
			return ResponseEntity.ok(IntakeServiceConstants.BAD_REQUEST);
		}

	}

	@PostMapping(value = "/carrier/getLocation", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getlocationFromGeoTab(@RequestBody String payload) {
		try {
			String connectServicePayload = carrierToMovementTrackingConversion.transform(payload);
			RestTemplate template = new RestTemplate();
			Map<String, Object> requestMap = new HashMap<>();
			String url = UriComponentsBuilder.fromHttpUrl(intakeServiceConfig.getConnectorServiceEndpoint())
					.buildAndExpand(requestMap).encode().toUri().toString();
			Map<String, String> requestBody = new HashMap<>();
			requestBody.put("connectorServicePayload", connectServicePayload);
			HttpHeaders header = new HttpHeaders();
			header.setBasicAuth(intakeServiceConfig.getBasicAuthUserName(), intakeServiceConfig.getBasicAuthPassword());
			HttpEntity<Map> requestEntity = new HttpEntity<>(requestBody, header);
			template.exchange(url, HttpMethod.POST, requestEntity, String.class);
			return ResponseEntity.ok(IntakeServiceConstants.SUCCESS_RESPONSE);

		} catch (Exception exception) {
			log.error(IntakeServiceConstants.IO_EXCEPTION, exception);
			return ResponseEntity.ok(IntakeServiceConstants.BAD_REQUEST);
		}

	}

	/*
	 * @PostMapping(value = "/carrier/updatelocation", consumes = {
	 * MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	 * public ResponseEntity<?> updatelocationFromGeoTab(@RequestBody String
	 * source,
	 * 
	 * @RequestHeader("Content-Type") String contentType) { try {
	 * 
	 * return ResponseEntity.ok(ConversionConstants.SUCCESS_RESPONSE);
	 * 
	 * } catch (Exception exception) {
	 * log.error(ConversionConstants.IO_EXCEPTION, exception); return
	 * ResponseEntity.ok(ConversionConstants.BAD_REQUEST); }
	 * 
	 * }
	 */
	@PostMapping(value = "/tripstatus/update", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getTripStatusUpdateFromSap(@RequestBody String payload) {
		try {
			String connectServicePayload = tripStatusToTripStatusUpdateConverion.transform(payload);
			RestTemplate template = new RestTemplate();
			Map<String, Object> requestMap = new HashMap<>();
			String url = UriComponentsBuilder.fromHttpUrl(intakeServiceConfig.getConnectorServiceEndpoint())
					.buildAndExpand(requestMap).encode().toUri().toString();
			Map<String, String> requestBody = new HashMap<>();
			requestBody.put("connectorServicePayload", connectServicePayload);
			HttpHeaders header = new HttpHeaders();
			header.setBasicAuth(intakeServiceConfig.getBasicAuthUserName(), intakeServiceConfig.getBasicAuthPassword());
			HttpEntity<Map> requestEntity = new HttpEntity<>(requestBody, header);
			template.exchange(url, HttpMethod.POST, requestEntity, String.class);
			return ResponseEntity.ok(IntakeServiceConstants.SUCCESS_RESPONSE);

		} catch (Exception exception) {
			log.error(IntakeServiceConstants.IO_EXCEPTION, exception);
			return ResponseEntity.ok(IntakeServiceConstants.BAD_REQUEST);
		}

	}

	@PostMapping(value = "/tripstatus/complete", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getCompletedTripStatusDetailsFromSap(@RequestBody String payload) {
		try {
			String connectServicePayload = tripStatusToTripStatusCompletedConversion.transform(payload);
			RestTemplate template = new RestTemplate();
			Map<String, Object> requestMap = new HashMap<>();
			String url = UriComponentsBuilder.fromHttpUrl(intakeServiceConfig.getConnectorServiceEndpoint())
					.buildAndExpand(requestMap).encode().toUri().toString();
			Map<String, String> requestBody = new HashMap<>();
			requestBody.put("connectorServicePayload", connectServicePayload);
			HttpHeaders header = new HttpHeaders();
			header.setBasicAuth(intakeServiceConfig.getBasicAuthUserName(), intakeServiceConfig.getBasicAuthPassword());
			HttpEntity<Map> requestEntity = new HttpEntity<>(requestBody, header);
			template.exchange(url, HttpMethod.POST, requestEntity, String.class);
			return ResponseEntity.ok(IntakeServiceConstants.SUCCESS_RESPONSE);

		} catch (Exception exception) {
			log.error(IntakeServiceConstants.IO_EXCEPTION, exception);
			return ResponseEntity.ok(IntakeServiceConstants.BAD_REQUEST);
		}

	}

	@PostMapping(value = "/tender", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getTenderDetailsFromJda(@RequestBody String payload) {
		try {
			String connectServicePayload = tenderToShipmentTrackingConversion.transform(payload);
			RestTemplate template = new RestTemplate();
			Map<String, Object> requestMap = new HashMap<>();
			String url = UriComponentsBuilder.fromHttpUrl(intakeServiceConfig.getConnectorServiceEndpoint())
					.buildAndExpand(requestMap).encode().toUri().toString();
			Map<String, String> requestBody = new HashMap<>();
			requestBody.put("connectorServicePayload", connectServicePayload);
			HttpHeaders header = new HttpHeaders();
			header.setBasicAuth(intakeServiceConfig.getBasicAuthUserName(), intakeServiceConfig.getBasicAuthPassword());
			HttpEntity<Map> requestEntity = new HttpEntity<>(requestBody, header);
			template.exchange(url, HttpMethod.POST, requestEntity, String.class);
			return ResponseEntity.ok(IntakeServiceConstants.SUCCESS_RESPONSE);

		} catch (Exception exception) {
			log.error(IntakeServiceConstants.IO_EXCEPTION, exception);
			return ResponseEntity.ok(IntakeServiceConstants.BAD_REQUEST);
		}

	}

	@PostMapping(value = "/loadcreation", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getLoadCreationDetailsFromJda(@RequestBody String payload) {
		try {
			String connectServicePayload = loadCreationToShipmentInboundConversion.transform(payload);
			RestTemplate template = new RestTemplate();
			Map<String, Object> requestMap = new HashMap<>();
			String url = UriComponentsBuilder.fromHttpUrl(intakeServiceConfig.getConnectorServiceEndpoint())
					.buildAndExpand(requestMap).encode().toUri().toString();
			Map<String, String> requestBody = new HashMap<>();
			requestBody.put("connectorServicePayload", connectServicePayload);
			HttpHeaders header = new HttpHeaders();
			header.setBasicAuth(intakeServiceConfig.getBasicAuthUserName(), intakeServiceConfig.getBasicAuthPassword());
			HttpEntity<Map> requestEntity = new HttpEntity<>(requestBody, header);
			template.exchange(url, HttpMethod.POST, requestEntity, String.class);
			return ResponseEntity.ok(IntakeServiceConstants.SUCCESS_RESPONSE);

		} catch (Exception exception) {
			log.error(IntakeServiceConstants.IO_EXCEPTION, exception);
			return ResponseEntity.ok(IntakeServiceConstants.BAD_REQUEST);
		}

	}

	@PostMapping(value = "/delivery", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getDeliveryDetailsFromSap(@RequestBody String payload) {
		try {
			String connectServicePayload = deliveryToShipmentInboundConversion.transform(payload);
			RestTemplate template = new RestTemplate();
			Map<String, Object> requestMap = new HashMap<>();
			String url = UriComponentsBuilder.fromHttpUrl(intakeServiceConfig.getConnectorServiceEndpoint())
					.buildAndExpand(requestMap).encode().toUri().toString();
			Map<String, String> requestBody = new HashMap<>();
			requestBody.put("connectorServicePayload", connectServicePayload);
			HttpHeaders header = new HttpHeaders();
			header.setBasicAuth(intakeServiceConfig.getBasicAuthUserName(), intakeServiceConfig.getBasicAuthPassword());
			HttpEntity<Map> requestEntity = new HttpEntity<>(requestBody, header);
			template.exchange(url, HttpMethod.POST, requestEntity, String.class);
			return ResponseEntity.ok(IntakeServiceConstants.SUCCESS_RESPONSE);

		} catch (Exception exception) {
			log.error(IntakeServiceConstants.IO_EXCEPTION, exception);
			return ResponseEntity.ok(IntakeServiceConstants.BAD_REQUEST);
		}

	}

	@PostMapping(value = "/transportdocumentation", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getTransportationDocumentdetailsFromSap(@RequestBody String payload) {
		try {
			String connectServicePayload = transportationDocumentToShipmentInboundConversion.transform(payload);
			RestTemplate template = new RestTemplate();
			Map<String, Object> requestMap = new HashMap<>();
			String url = UriComponentsBuilder.fromHttpUrl(intakeServiceConfig.getConnectorServiceEndpoint())
					.buildAndExpand(requestMap).encode().toUri().toString();
			Map<String, String> requestBody = new HashMap<>();
			requestBody.put("connectorServicePayload", connectServicePayload);
			HttpHeaders header = new HttpHeaders();
			header.setBasicAuth(intakeServiceConfig.getBasicAuthUserName(), intakeServiceConfig.getBasicAuthPassword());
			HttpEntity<Map> requestEntity = new HttpEntity<>(requestBody, header);
			template.exchange(url, HttpMethod.POST, requestEntity, String.class);
			return ResponseEntity.ok(IntakeServiceConstants.SUCCESS_RESPONSE);

		} catch (Exception exception) {
			log.error(IntakeServiceConstants.IO_EXCEPTION, exception);
			return ResponseEntity.ok(IntakeServiceConstants.BAD_REQUEST);
		}

	}

}
