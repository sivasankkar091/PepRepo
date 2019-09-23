package com.cts.tib.util;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.tib.intfc.JsonToCsvMapperServiceIntfc;

@Component
public class MapperUtil {
	private static Logger log = LoggerFactory.getLogger(MapperUtil.class);
	@Autowired
	JsonToCsvMapperServiceIntfc convert;

	public File convertJsonToCSV(String srcObject, File file) {
		convert.jsonToCsvMapper(srcObject, file);
		return file;
	}

}
