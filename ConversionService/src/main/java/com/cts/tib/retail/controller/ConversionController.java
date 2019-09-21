package com.cts.tib.retail.controller;

import java.io.File;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.tib.retail.service.MapperService;
import com.cts.tib.retail.util.ServiceConstant;

@RestController
public class ConversionController {
	private static Logger logger = Logger.getLogger(ConversionController.class);
	@Autowired
	MapperService mapperService;

	@PostMapping(value = "/convertToCSV", headers = "Accept=application/json")
	public String convertOrder(@RequestBody String order) {
		try {
			File file = new File("orderLines.csv");
			mapperService.convertToCSV(order, file);
			logger.info("Successfully converted");
			return ServiceConstant.SUCCESSDESC;

		} catch (Exception exception) {
			logger.error(exception.getMessage());
			return ServiceConstant.ERRORDESC;

		}

	}

}
