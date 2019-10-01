package com.cts.onenetwork.service.sap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cts.onenetwork.interfaces.TransformerInterface;
import com.cts.onenetwork.models.destination.DestinationModel2;
import com.cts.onenetwork.models.source.SourceModel2;
import com.cts.onenetwork.util.ConvertorUtil;
import com.cts.onenetwork.util.IntakeServiceConstants;

public class PurchaseOrderToOneNetworkPurchaseOrderConversion implements TransformerInterface {
	public static Logger logger = LoggerFactory.getLogger(PurchaseOrderToOneNetworkPurchaseOrderConversion.class);
	@Autowired
	ConvertorUtil convertorUtil;

	@Override
	public String transform(Object srcObject) {

		try {
			SourceModel2 source = (SourceModel2) srcObject;
			/*
			 * //Mapper Implementation to convert from Source to
			 * DestinationFormat
			 */
			// should have a destinationModel in the destination expecting
			// format which will to converted to csv format
			DestinationModel2 destination = new DestinationModel2();
			String output = convertorUtil.convertToCsvFormat(destination);
			logger.info("csvOutput is" + output);
			return output;
		} catch (Exception e) {
			logger.error(IntakeServiceConstants.JSON_PARSING_EXCEPTION, e);
			return IntakeServiceConstants.JSON_PARSING_EXCEPTION;
		}

	}

}
