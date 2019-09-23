package com.rabbit.publish.Intfc;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface OrderEventPublisherChannel {

	String order_publish_channel = "OrderEventChannel";

	@Output(OrderEventPublisherChannel.order_publish_channel)
	public MessageChannel orderEventPublish();
}
