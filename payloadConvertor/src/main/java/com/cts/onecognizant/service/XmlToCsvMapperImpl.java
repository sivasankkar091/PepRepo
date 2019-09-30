package com.cts.onecognizant.service;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.onecognizant.interfaces.XmlToCsvMapperInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Builder;

@Service
public class XmlToCsvMapperImpl implements XmlToCsvMapperInterface {
	private static Logger log = LoggerFactory.getLogger(XmlToCsvMapperImpl.class);
	@Autowired
	XmlToCsvMapperInterface convertor;

	@Override
	public String xmlToCsvMapper(String srcObject) {
		try {
			JSONObject obj = XML.toJSONObject(srcObject);
			/*this.normalizeObject(obj);
			JSONArray array = new JSONArray();
			array.put(obj);*/
			String jsonStr = obj.toString(4);
			JsonNode jsonTree = new ObjectMapper().readTree(jsonStr);
			Builder csvSchemaBuilder = CsvSchema.builder();
			JsonNode firstObject = jsonTree.elements().next();
			firstObject.fieldNames().forEachRemaining(fieldName -> {
				csvSchemaBuilder.addColumn(fieldName);
			});
			CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();
			CsvMapper csvMapper = new CsvMapper();
			String output=csvMapper.writerFor(JsonNode.class).with(csvSchema).writeValueAsString(jsonTree);
			return output;

		} catch (JsonProcessingException e) {
			log.error("Error in parsingJson", e);
			return null;
		} catch (IOException e) {
			log.error("Error in writing to file", e);
			return null;
		}
	}

	/*private JSONObject normalizeObject(JSONObject obj) {
		for (String key : JSONObject.getNames(obj)) {
			JSONObject subtree = obj.optJSONObject(key);
			if (subtree != null) {
				normalizeObject(subtree);
			}
		}
		return obj;
	}*/

}
