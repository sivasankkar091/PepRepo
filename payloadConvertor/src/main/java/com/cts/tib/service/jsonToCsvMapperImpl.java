package com.cts.tib.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.tib.intfc.JsonToCsvMapperServiceInterface;
import com.cts.tib.util.ConversionConstants;
import com.cts.tib.util.MapperUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Builder;

@Service
public class jsonToCsvMapperImpl implements JsonToCsvMapperServiceInterface {
	private static Logger log = LoggerFactory.getLogger(jsonToCsvMapperImpl.class);
	@Autowired
	MapperUtil mapperUtil;

	@Override
	public String jsonToCsvMapper(String srcObject) {
		try {
			JsonNode jsonTree = new ObjectMapper().readTree(srcObject);
			Builder csvSchemaBuilder = CsvSchema.builder();
			JsonNode firstObject = jsonTree.elements().next();
			firstObject.fieldNames().forEachRemaining(fieldName -> {
				csvSchemaBuilder.addColumn(fieldName);
			});
			CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();
			CsvMapper csvMapper = new CsvMapper();
			String output=csvMapper.writerFor(JsonNode.class).with(csvSchema).writeValueAsString(jsonTree);
			log.info("output is"+ output);
			return output;

		} catch (JsonProcessingException e) {
			log.error(ConversionConstants.JSON_PARSING_EXCEPTION, e);
			return null;
		} catch (IOException e) {
			log.error(ConversionConstants.IO_EXCEPTION, e);
			return null;
		}
	}

}
