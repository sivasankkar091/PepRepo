package com.cts.onenetwork.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.onenetwork.interfaces.conversioninterfaces.XmlToCsvMapperServiceInterface;

@Service
public class XmlToCsvMapperImpl implements XmlToCsvMapperServiceInterface {
	private static Logger logger = LoggerFactory.getLogger(XmlToCsvMapperImpl.class);
	@Autowired
	XmlToCsvMapperServiceInterface convertor;

	@Override
	public String xmlToCsvMapper(String srcObject) {
		return null;

	}

}
