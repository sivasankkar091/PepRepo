package com.cts.tib.service;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.tib.intfc.JsonToCsvMapperServiceIntfc;
import com.cts.tib.util.MapperUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Builder;

@Service
public class jsonToCsvMapperImpl implements JsonToCsvMapperServiceIntfc {
	private static Logger log = LoggerFactory.getLogger(jsonToCsvMapperImpl.class);
	@Autowired
	MapperUtil mapperUtil;

	@Override
	public File jsonToCsvMapper(String srcObject, File file) {
		try {
			JsonNode jsonTree = new ObjectMapper().readTree(srcObject);
			Builder csvSchemaBuilder = CsvSchema.builder();
			JsonNode firstObject = jsonTree.elements().next();
			firstObject.fieldNames().forEachRemaining(fieldName -> {
				csvSchemaBuilder.addColumn(fieldName);
			});
			CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();
			CsvMapper csvMapper = new CsvMapper();
			csvMapper.writerFor(JsonNode.class).with(csvSchema).writeValue(file, jsonTree);
			return file;

		} catch (JsonProcessingException e) {
			log.error("Error in parsingJson", e);
			return file;
		} catch (IOException e) {
			log.error("Error in writing to file", e);
			return file;
		}
	}

}
