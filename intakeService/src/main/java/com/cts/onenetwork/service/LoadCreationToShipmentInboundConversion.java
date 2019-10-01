package com.cts.onenetwork.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cts.onenetwork.interfaces.TransformerInterface;
import com.cts.onenetwork.models.destination.DestinationModel;
import com.cts.onenetwork.models.source.SourceModel;
import com.cts.onenetwork.util.IntakeServiceConstants;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class LoadCreationToShipmentInboundConversion implements TransformerInterface {
	public static Logger logger = LoggerFactory.getLogger(LoadCreationToShipmentInboundConversion.class);

	@Override
	public String transform(String srcObject) {

		try {
			XmlMapper xmlMapper = new XmlMapper();

			// Should have source Model defined to parse xml
			SourceModel source = xmlMapper.readValue(srcObject, SourceModel.class);
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
			return output;
		} catch (IOException e) {
			logger.error("Error in parsing xml", e);
			return IntakeServiceConstants.IO_EXCEPTION;
		}
	}

}
