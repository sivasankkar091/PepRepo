package com.cts.onenetwork.service;

import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cts.onenetwork.interfaces.TransformerInterface;
import com.cts.onenetwork.models.destination.DestinationModel;
import com.cts.onenetwork.models.source.SourceModel;
import com.cts.onenetwork.util.IntakeServiceConstants;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SalesOrderToOneNetworkSalesOrderConversion implements TransformerInterface {
	public static Logger logger = LoggerFactory.getLogger(SalesOrderToOneNetworkSalesOrderConversion.class);

	@Override
	public String transform(String srcObject) {
		try {
			Gson gson = new Gson();
			Type type = new TypeToken<SourceModel>() {
			}.getType();

			// Should have source Model defined to parse json
			SourceModel source = gson.fromJson(srcObject, type);
			/*
			 * //Mapper Implementation to convert from Source to
			 * DestinationFormat
			 */
			// should have a destinationModel in the destination expecting
			// format which will to converted to csv format
			DestinationModel destination = new DestinationModel();
			CsvMapper csvMapper = new CsvMapper();
			CsvSchema schema = csvMapper.schemaFor(DestinationModel.class);
			schema = schema.withColumnSeparator(',');
			schema = schema.withUseHeader(true);
			ObjectWriter objwriter = csvMapper.writer(schema);
			String output = objwriter.writeValueAsString(destination);
			logger.info("csvOutput is" + output);
			return null;
		} catch (Exception e) {
			logger.error(IntakeServiceConstants.JSON_PARSING_EXCEPTION, e);
			return IntakeServiceConstants.JSON_PARSING_EXCEPTION;
		}

	}

}
