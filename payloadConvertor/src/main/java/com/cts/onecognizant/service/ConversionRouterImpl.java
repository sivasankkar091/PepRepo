package com.cts.onecognizant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.onecognizant.interfaces.ConversionRouterInterface;
import com.cts.onecognizant.interfaces.JsonToCsvMapperServiceInterface;
import com.cts.onecognizant.interfaces.XmlToCsvMapperInterface;
import com.cts.onecognizant.util.ConversionConstants;

@Service
public class ConversionRouterImpl implements ConversionRouterInterface {
	@Autowired
	JsonToCsvMapperServiceInterface jsonMapperServiceIntfc;

	@Autowired
	XmlToCsvMapperInterface xmlToCsvMapperIntfc;

	@Override
	public String conversionRouter(String srcObject, String contentType) {

		if (ConversionConstants.XML_CONTENT_TYPE.equalsIgnoreCase(contentType)) {
			String output=xmlToCsvMapperIntfc.xmlToCsvMapper(srcObject);
			return output;
		}
		if (ConversionConstants.JSON_CONTENT_TYPE.equalsIgnoreCase(contentType)) {
			String output=jsonMapperServiceIntfc.jsonToCsvMapper(srcObject);
			return output;
		}
		return null;
	}

}
