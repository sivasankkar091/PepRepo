package com.cts.tib.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.tib.intfc.ConversionRouterIntfc;
import com.cts.tib.intfc.JsonToCsvMapperServiceIntfc;
import com.cts.tib.intfc.XmlToCsvMapperIntfc;
import com.cts.tib.util.ConversionConstants;

@Service
public class ConversionRouterImpl implements ConversionRouterIntfc {
	@Autowired
	JsonToCsvMapperServiceIntfc jsonMapperServiceIntfc;

	@Autowired
	XmlToCsvMapperIntfc xmlToCsvMapperIntfc;

	@Override
	public File conversionRouter(String srcObject, File file, String contentType) {

		if (ConversionConstants.XML_CONTENT_TYPE.equalsIgnoreCase(contentType)) {
			xmlToCsvMapperIntfc.xmlToCsvMapper(srcObject, file);
		}
		if (ConversionConstants.JSON_CONTENT_TYPE.equalsIgnoreCase(contentType)) {
			jsonMapperServiceIntfc.jsonToCsvMapper(srcObject, file);
		}
		return file;
	}

}
