package com.cts.onenetwork.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.onenetwork.interfaces.conversioninterfaces.JsonToCsvMapperServiceInterface;

@Component
public class MapperUtil {
	private static Logger log = LoggerFactory.getLogger(MapperUtil.class);
	@Autowired
	static JsonToCsvMapperServiceInterface convert;

	public static String convertJsonToCSV(String srcObject) {
		return convert.jsonToCsvMapper(srcObject);
	}

}
