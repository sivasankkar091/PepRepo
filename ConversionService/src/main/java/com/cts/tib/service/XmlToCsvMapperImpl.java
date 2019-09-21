package com.cts.tib.service;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.tib.intfc.XmlToCsvMapperIntfc;

@Service
public class XmlToCsvMapperImpl implements XmlToCsvMapperIntfc {
	private static Logger log = LoggerFactory.getLogger(XmlToCsvMapperImpl.class);
	@Autowired
	XmlToCsvMapperIntfc convertor;

	@Override
	public File xmlToCsvMapper(String srcObject, File file) {
		//Implementation
		return file;
	}

}
