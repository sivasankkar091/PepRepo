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
import com.cts.onenetwork.models.source.SourceModel;
import com.cts.onenetwork.models.source.SourceModel1;
import com.cts.onenetwork.models.source.SourceModel2;
import com.cts.onenetwork.models.source.SourceModel3;
import com.cts.onenetwork.models.source.SourceModel4;
import com.cts.onenetwork.models.source.SourceModel5;
import com.cts.onenetwork.models.source.SourceModel6;
import com.cts.onenetwork.models.source.SourceModel7;
import com.cts.onenetwork.models.source.SourceModel8;
import com.cts.onenetwork.models.source.SourceModel9;
import com.cts.onenetwork.service.geotab.CarrierToMovementTrackingConversion;
import com.cts.onenetwork.service.jda.LoadCreationToShipmentInboundConversion;
import com.cts.onenetwork.service.jda.TenderToShipmentTrackingConversion;
import com.cts.onenetwork.service.sap.DeliveryToShipmentInboundConversion;
import com.cts.onenetwork.service.sap.PurchaseOrderToOneNetworkPurchaseOrderConversion;
import com.cts.onenetwork.service.sap.SalesOrderToOneNetworkSalesOrderConversion;
import com.cts.onenetwork.service.sap.StockTransferToEnhancedDeploymentOrderConversion;
import com.cts.onenetwork.service.sap.TransportationDocumentToShipmentInboundConversion;
import com.cts.onenetwork.service.sap.TripStatusToTripStatusCompletedConversion;
import com.cts.onenetwork.service.sap.TripStatusToTripStatusUpdateConverion;
import com.cts.onenetwork.util.IntakeServiceConstants;

@RestController
@RequestMapping("/onenetwork")
public class IntakeServiceController {
	private static Logger log = LoggerFactory.getLogger(IntakeServiceController.class);
	@Autowired
	private CarrierToMovementTrackingConversion carrierToMovementTrackingConversion;
	@Autowired
	private DeliveryToShipmentInboundConversion deliveryToShipmentInboundConversion;
	@Autowired
	private LoadCreationToShipmentInboundConversion loadCreationToShipmentInboundConversion;
	@Autowired
	private PurchaseOrderToOneNetworkPurchaseOrderConversion purchaseOrderToOneNetworkPurchaseOrderConversion;
	@Autowired
	private SalesOrderToOneNetworkSalesOrderConversion salesOrderToOneNetworkSalesOrderConversion;
	@Autowired
	private StockTransferToEnhancedDeploymentOrderConversion stockTransferToEnhancedDeploymentOrderConversion;
	@Autowired
	private TenderToShipmentTrackingConversion tenderToShipmentTrackingConversion;
	@Autowired
	private TransportationDocumentToShipmentInboundConversion transportationDocumentToShipmentInboundConversion;
	@Autowired
	private TripStatusToTripStatusCompletedConversion tripStatusToTripStatusCompletedConversion;
	@Autowired
	private TripStatusToTripStatusUpdateConverion tripStatusToTripStatusUpdateConverion;
	@Autowired
	private IntakeServiceConfiguration intakeServiceConfig;

	@PostMapping(value = "/order/sales", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getSalesOrderFromSap(@RequestBody SourceModel payload) {
		try {
			String connectServicePayload = salesOrderToOneNetworkSalesOrderConversion.transform(payload);
			RestTemplate template = new RestTemplate();
			String url = UriComponentsBuilder.fromHttpUrl(intakeServiceConfig.getConnectorServiceEndpoint())
					.buildAndExpand().encode().toUri().toString();
			HttpHeaders header = new HttpHeaders();
			header.setBasicAuth(intakeServiceConfig.getBasicAuthUserName(), intakeServiceConfig.getBasicAuthPassword());
			HttpEntity<String> requestEntity = new HttpEntity<>(connectServicePayload, header);
			template.exchange(url, HttpMethod.POST, requestEntity, String.class);
			return ResponseEntity.ok(IntakeServiceConstants.SUCCESS_RESPONSE);

		} catch (Exception exception) {
			log.error(IntakeServiceConstants.IO_EXCEPTION, exception);
			return ResponseEntity.ok(IntakeServiceConstants.BAD_REQUEST);
		}

	}

	@PostMapping(value = "/order/stocktransfer", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getStockTransferOrderFromSap(@RequestBody SourceModel1 payload) {
		try {
			String connectServicePayload = stockTransferToEnhancedDeploymentOrderConversion.transform(payload);
			RestTemplate template = new RestTemplate();
			String url = UriComponentsBuilder.fromHttpUrl(intakeServiceConfig.getConnectorServiceEndpoint())
					.buildAndExpand().encode().toUri().toString();
			HttpHeaders header = new HttpHeaders();
			header.setBasicAuth(intakeServiceConfig.getBasicAuthUserName(), intakeServiceConfig.getBasicAuthPassword());
			HttpEntity<String> requestEntity = new HttpEntity<>(connectServicePayload, header);
			template.exchange(url, HttpMethod.POST, requestEntity, String.class);
			return ResponseEntity.ok(IntakeServiceConstants.SUCCESS_RESPONSE);

		} catch (Exception exception) {
			log.error(IntakeServiceConstants.IO_EXCEPTION, exception);
			return ResponseEntity.ok(IntakeServiceConstants.BAD_REQUEST);
		}

	}

	@PostMapping(value = "/order/purchase", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getPurchaseOrderFromSap(@RequestBody SourceModel2 payload) {
		try {
			String connectServicePayload = purchaseOrderToOneNetworkPurchaseOrderConversion.transform(payload);
			RestTemplate template = new RestTemplate();
			String url = UriComponentsBuilder.fromHttpUrl(intakeServiceConfig.getConnectorServiceEndpoint())
					.buildAndExpand().encode().toUri().toString();
			HttpHeaders header = new HttpHeaders();
			header.setBasicAuth(intakeServiceConfig.getBasicAuthUserName(), intakeServiceConfig.getBasicAuthPassword());
			HttpEntity<String> requestEntity = new HttpEntity<>(connectServicePayload, header);
			template.exchange(url, HttpMethod.POST, requestEntity, String.class);
			return ResponseEntity.ok(IntakeServiceConstants.SUCCESS_RESPONSE);

		} catch (Exception exception) {
			log.error(IntakeServiceConstants.IO_EXCEPTION, exception);
			return ResponseEntity.ok(IntakeServiceConstants.BAD_REQUEST);
		}

	}

	@PostMapping(value = "/carrier/getLocation", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getlocationFromGeoTab(@RequestBody SourceModel3 payload) {
		try {
			String connectServicePayload = carrierToMovementTrackingConversion.transform(payload);
			RestTemplate template = new RestTemplate();
			String url = UriComponentsBuilder.fromHttpUrl(intakeServiceConfig.getConnectorServiceEndpoint())
					.buildAndExpand().encode().toUri().toString();
			HttpHeaders header = new HttpHeaders();
			header.setBasicAuth(intakeServiceConfig.getBasicAuthUserName(), intakeServiceConfig.getBasicAuthPassword());
			HttpEntity<String> requestEntity = new HttpEntity<>(connectServicePayload, header);
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
	public ResponseEntity<?> getTripStatusUpdateFromSap(@RequestBody SourceModel4 payload) {
		try {
			String connectServicePayload = tripStatusToTripStatusUpdateConverion.transform(payload);
			RestTemplate template = new RestTemplate();
			String url = UriComponentsBuilder.fromHttpUrl(intakeServiceConfig.getConnectorServiceEndpoint())
					.buildAndExpand().encode().toUri().toString();
			HttpHeaders header = new HttpHeaders();
			header.setBasicAuth(intakeServiceConfig.getBasicAuthUserName(), intakeServiceConfig.getBasicAuthPassword());
			HttpEntity<String> requestEntity = new HttpEntity<>(connectServicePayload, header);
			template.exchange(url, HttpMethod.POST, requestEntity, String.class);
			return ResponseEntity.ok(IntakeServiceConstants.SUCCESS_RESPONSE);

		} catch (Exception exception) {
			log.error(IntakeServiceConstants.IO_EXCEPTION, exception);
			return ResponseEntity.ok(IntakeServiceConstants.BAD_REQUEST);
		}

	}

	@PostMapping(value = "/tripstatus/complete", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getCompletedTripStatusDetailsFromSap(@RequestBody SourceModel5 payload) {
		try {
			String connectServicePayload = tripStatusToTripStatusCompletedConversion.transform(payload);
			RestTemplate template = new RestTemplate();
			String url = UriComponentsBuilder.fromHttpUrl(intakeServiceConfig.getConnectorServiceEndpoint())
					.buildAndExpand().encode().toUri().toString();
			HttpHeaders header = new HttpHeaders();
			header.setBasicAuth(intakeServiceConfig.getBasicAuthUserName(), intakeServiceConfig.getBasicAuthPassword());
			HttpEntity<String> requestEntity = new HttpEntity<>(connectServicePayload, header);
			template.exchange(url, HttpMethod.POST, requestEntity, String.class);
			return ResponseEntity.ok(IntakeServiceConstants.SUCCESS_RESPONSE);

		} catch (Exception exception) {
			log.error(IntakeServiceConstants.IO_EXCEPTION, exception);
			return ResponseEntity.ok(IntakeServiceConstants.BAD_REQUEST);
		}

	}

	@PostMapping(value = "/tender", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getTenderDetailsFromJda(@RequestBody SourceModel6 payload) {
		try {
			String connectServicePayload = tenderToShipmentTrackingConversion.transform(payload);
			RestTemplate template = new RestTemplate();
			String url = UriComponentsBuilder.fromHttpUrl(intakeServiceConfig.getConnectorServiceEndpoint())
					.buildAndExpand().encode().toUri().toString();
			HttpHeaders header = new HttpHeaders();
			header.setBasicAuth(intakeServiceConfig.getBasicAuthUserName(), intakeServiceConfig.getBasicAuthPassword());
			HttpEntity<String> requestEntity = new HttpEntity<>(connectServicePayload, header);
			template.exchange(url, HttpMethod.POST, requestEntity, String.class);
			return ResponseEntity.ok(IntakeServiceConstants.SUCCESS_RESPONSE);

		} catch (Exception exception) {
			log.error(IntakeServiceConstants.IO_EXCEPTION, exception);
			return ResponseEntity.ok(IntakeServiceConstants.BAD_REQUEST);
		}

	}

	@PostMapping(value = "/loadcreation", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getLoadCreationDetailsFromJda(@RequestBody SourceModel7 payload) {
		try {
			String connectServicePayload = loadCreationToShipmentInboundConversion.transform(payload);
			RestTemplate template = new RestTemplate();
			String url = UriComponentsBuilder.fromHttpUrl(intakeServiceConfig.getConnectorServiceEndpoint())
					.buildAndExpand().encode().toUri().toString();
			HttpHeaders header = new HttpHeaders();
			header.setBasicAuth(intakeServiceConfig.getBasicAuthUserName(), intakeServiceConfig.getBasicAuthPassword());
			HttpEntity<String> requestEntity = new HttpEntity<>(connectServicePayload, header);
			template.exchange(url, HttpMethod.POST, requestEntity, String.class);
			return ResponseEntity.ok(IntakeServiceConstants.SUCCESS_RESPONSE);

		} catch (Exception exception) {
			log.error(IntakeServiceConstants.IO_EXCEPTION, exception);
			return ResponseEntity.ok(IntakeServiceConstants.BAD_REQUEST);
		}

	}

	@PostMapping(value = "/delivery", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getDeliveryDetailsFromSap(@RequestBody SourceModel8 payload) {
		try {
			String connectServicePayload = deliveryToShipmentInboundConversion.transform(payload);
			RestTemplate template = new RestTemplate();
			String url = UriComponentsBuilder.fromHttpUrl(intakeServiceConfig.getConnectorServiceEndpoint())
					.buildAndExpand().encode().toUri().toString();
			HttpHeaders header = new HttpHeaders();
			header.setBasicAuth(intakeServiceConfig.getBasicAuthUserName(), intakeServiceConfig.getBasicAuthPassword());
			HttpEntity<String> requestEntity = new HttpEntity<>(connectServicePayload, header);
			template.exchange(url, HttpMethod.POST, requestEntity, String.class);
			return ResponseEntity.ok(IntakeServiceConstants.SUCCESS_RESPONSE);

		} catch (Exception exception) {
			log.error(IntakeServiceConstants.IO_EXCEPTION, exception);
			return ResponseEntity.ok(IntakeServiceConstants.BAD_REQUEST);
		}

	}

	@PostMapping(value = "/transportdocumentation", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getTransportationDocumentdetailsFromSap(@RequestBody SourceModel9 payload) {
		try {
			String connectServicePayload = transportationDocumentToShipmentInboundConversion.transform(payload);
			RestTemplate template = new RestTemplate();
			String url = UriComponentsBuilder.fromHttpUrl(intakeServiceConfig.getConnectorServiceEndpoint())
					.buildAndExpand().encode().toUri().toString();
			Map<String, String> requestBody = new HashMap<>();
			requestBody.put("connectorServicePayload", connectServicePayload);
			HttpHeaders header = new HttpHeaders();
			header.setBasicAuth(intakeServiceConfig.getBasicAuthUserName(), intakeServiceConfig.getBasicAuthPassword());
			HttpEntity<String> requestEntity = new HttpEntity<>(connectServicePayload, header);
			template.exchange(url, HttpMethod.POST, requestEntity, String.class);
			return ResponseEntity.ok(IntakeServiceConstants.SUCCESS_RESPONSE);

		} catch (Exception exception) {
			log.error(IntakeServiceConstants.IO_EXCEPTION, exception);
			return ResponseEntity.ok(IntakeServiceConstants.BAD_REQUEST);
		}

	}

}
