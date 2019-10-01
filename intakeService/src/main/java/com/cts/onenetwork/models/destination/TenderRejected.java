package com.pepsico.filesender.model;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
@JsonFormat(shape = JsonFormat.Shape.STRING, pattern= "yyyy-MM-dd'T'HH:mm:ssZ")
@Getter
@Setter
@SuppressWarnings("unused")
public class TenderRejected implements Serializable {
	
	private static final long serialVersionUID = 4311396412016984944L;
	private String movementNumber;
	private Date evtDate;
	private String evtType;
	private String evtMessage;
	private String evtLocation;
	private String country;
	private String street1;
	private String street2;
	private String street3;
	private String state;
	private String zip;
	private String city;
	private String reasonCode;
	private String splc;
	private double currentPositionLatitude;
	private double currentPositionLongitude;
	private String transportationControllingEnterpriseName;
	private String transportationControllingOrganizationName;
	private String currentCarrierEnterpriseName;
	private String currentCarrierOrganizationName;
	private double insideTemperature;
	private double outsideTemperature;
    private String eventAttributes;
}