package com.rabbit.rabbitListener.intfc;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface OrderChannelIntfc {

	String order_channel = "OrderEventChannel";

	@Input(OrderChannelIntfc.order_channel)
	public SubscribableChannel orderMsgHandler();
}
