package com.cts.onecognizant.service;

import java.lang.reflect.Type;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.onecognizant.interfaces.JsonToCsvMapperServiceInterface;
import com.cts.onecognizant.pojo.source.OrderSource;
import com.cts.onecognizant.util.ConversionConstants;
import com.cts.onecognizant.util.MapperUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class jsonToCsvMapperImpl implements JsonToCsvMapperServiceInterface {
	private static Logger log = LoggerFactory.getLogger(jsonToCsvMapperImpl.class);
	@Autowired
	MapperUtil mapperUtil;

	@Override
	public String jsonToCsvMapper(String srcObject) {
		try {
			Gson gson = new Gson();
			Type type = new TypeToken<List<OrderSource>>() {
			}.getType();
			String output="sameer";
/*			List<OrderSource> orderList = gson.fromJson(srcObject, type);
			List<OrderDestination> destinationList = new ArrayList<>();
			JMapper<OrderDestination, OrderSource> mapper = new JMapper<>(OrderDestination.class, OrderSource.class);
			orderList.stream().filter(ord -> ord != null).forEach(ord -> {
				destinationList.add(mapper.getDestination(ord));
			});
			CsvMapper csvMapper = new CsvMapper();
			CsvSchema schema = csvMapper.schemaFor(OrderDestination.class);
			schema = schema.withColumnSeparator(',');
			schema = schema.withUseHeader(true);
			ObjectWriter objwriter = csvMapper.writer(schema);

			String output = objwriter.writeValueAsString(destinationList);*/
			log.info("csvOutput is" + output);
			return null;
		} catch (Exception e) {
			log.error(ConversionConstants.JSON_PARSING_EXCEPTION, e);
			return ConversionConstants.JSON_PARSING_EXCEPTION;
		}
	}

}
