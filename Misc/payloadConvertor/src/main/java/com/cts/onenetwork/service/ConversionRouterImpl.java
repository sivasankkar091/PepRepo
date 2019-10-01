package com.cts.onenetwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.onenetwork.interfaces.conversioninterfaces.ConversionRouterInterface;
import com.cts.onenetwork.interfaces.conversioninterfaces.JsonToCsvMapperServiceInterface;
import com.cts.onenetwork.interfaces.conversioninterfaces.XmlToCsvMapperServiceInterface;
import com.cts.onenetwork.util.ConversionConstants;

@Service
public class ConversionRouterImpl implements ConversionRouterInterface {
	@Autowired
	JsonToCsvMapperServiceInterface jsonMapperServiceIntfc;

	@Autowired
	XmlToCsvMapperServiceInterface xmlToCsvMapperIntfc;

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
