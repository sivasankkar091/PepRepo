package com.cts.onenetwork.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class ConvertorUtil {
	public static Logger logger = LoggerFactory.getLogger(ConvertorUtil.class);

	public String convertToCsvFormat(Object obj) {

		try {
			CsvMapper csvMapper = new CsvMapper();
			CsvSchema schema = csvMapper.schemaFor(Object.class);
			schema = schema.withColumnSeparator(',');
			schema = schema.withUseHeader(true);
			ObjectWriter objwriter = csvMapper.writer(schema);
			String output = objwriter.writeValueAsString(obj);
			return output;
		} catch (JsonProcessingException e) {
			logger.error(IntakeServiceConstants.JSON_PARSING_EXCEPTION, e);
			return IntakeServiceConstants.JSON_PARSING_EXCEPTION;
		}
	}
}
