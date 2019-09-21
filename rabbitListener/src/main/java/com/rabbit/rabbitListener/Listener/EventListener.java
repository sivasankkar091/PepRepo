package com.rabbit.rabbitListener.Listener;

import org.jboss.logging.Logger;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.rabbit.rabbitListener.bean.OrderBean;
import com.rabbit.rabbitListener.intfc.OrderChannelIntfc;

@EnableBinding(OrderChannelIntfc.class)
public class EventListener {
	private static Logger log = Logger.getLogger(EventListener.class);
	@StreamListener(target = OrderChannelIntfc.order_channel)
	public void msglistener(OrderBean msg) {
		log.info("Message is" + msg.getOrderId());
	}
}
