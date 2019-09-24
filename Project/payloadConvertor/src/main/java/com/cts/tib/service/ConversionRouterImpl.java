package com.cts.tib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.tib.intfc.ConversionRouterInterface;
import com.cts.tib.intfc.JsonToCsvMapperServiceInterface;
import com.cts.tib.intfc.XmlToCsvMapperInterface;
import com.cts.tib.util.ConversionConstants;

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
