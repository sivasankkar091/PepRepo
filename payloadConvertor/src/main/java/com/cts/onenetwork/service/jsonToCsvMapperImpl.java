package com.cts.onenetwork.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.onenetwork.interfaces.conversioninterfaces.JsonToCsvMapperServiceInterface;
import com.cts.onenetwork.util.MapperUtil;

@Service
public class jsonToCsvMapperImpl implements JsonToCsvMapperServiceInterface {
	private static Logger logger = LoggerFactory.getLogger(jsonToCsvMapperImpl.class);
	@Autowired
	MapperUtil mapperUtil;

	@Override
	public String jsonToCsvMapper(String srcObject) {
		return null;
	}

}
