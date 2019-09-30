package com.cts.onecognizant.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.onecognizant.interfaces.JsonToCsvMapperServiceInterface;

@Component
public class MapperUtil {
	private static Logger log = LoggerFactory.getLogger(MapperUtil.class);
	@Autowired
	JsonToCsvMapperServiceInterface convert;

	public String convertJsonToCSV(String srcObject) {
		return convert.jsonToCsvMapper(srcObject);
	}

}
