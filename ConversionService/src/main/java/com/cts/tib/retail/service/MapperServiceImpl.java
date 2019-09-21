package com.cts.tib.retail.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cts.tib.retail.util.MapperUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Builder;

/*
 * Calls Util methods for conversion
 */
@Service
public class MapperServiceImpl implements MapperService {

	@Override
	public void convertToCSV(String user ,File file) {
		// TODO Auto-generated method stub
		MapperUtil.convertJsonToCSV(user, file);
	}

	
}
