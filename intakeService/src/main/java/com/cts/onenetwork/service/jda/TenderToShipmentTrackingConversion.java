package com.cts.onenetwork.service.jda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cts.onenetwork.interfaces.TransformerInterface;
import com.cts.onenetwork.models.destination.DestinationModel6;
import com.cts.onenetwork.models.source.SourceModel6;
import com.cts.onenetwork.util.ConvertorUtil;
import com.cts.onenetwork.util.IntakeServiceConstants;

public class TenderToShipmentTrackingConversion implements TransformerInterface {
	public static Logger logger = LoggerFactory.getLogger(TenderToShipmentTrackingConversion.class);

	@Autowired
	ConvertorUtil convertorUtil;
	@Override
	public String transform(Object srcObject) {

		try {
			SourceModel6 source = (SourceModel6) srcObject;
			/*
			 * //Mapper Implementation to convert from Source to
			 * DestinationFormat
			 */
			// should have a destinationModel in the destination expecting
			// format which will to converted to csv format
			DestinationModel6 destination = new DestinationModel6();
			String output = convertorUtil.convertToCsvFormat(destination);
			logger.info("csvOutput is" + output);
			return output;
		} catch (Exception e) {
			logger.error("Error in parsing xml", e);
			return IntakeServiceConstants.IO_EXCEPTION;
		}

	}

}
