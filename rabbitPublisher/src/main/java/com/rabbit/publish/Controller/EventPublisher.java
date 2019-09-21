package com.rabbit.publish.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbit.publish.Intfc.OrderEventPublisherChannel;
import com.rabbit.publish.bean.OrderBean;

@RestController
@EnableBinding(OrderEventPublisherChannel.class)
public class EventPublisher {
	@Autowired
	OrderEventPublisherChannel orderEventPublisherChannel;

	@PostMapping("/publishEvent")
	public String message(@RequestBody String payload) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		OrderBean orderjson = mapper.readValue(payload, OrderBean.class);
		orderEventPublisherChannel.orderEventPublish().send(MessageBuilder.withPayload(orderjson).build());
		return "success";

	}
}
