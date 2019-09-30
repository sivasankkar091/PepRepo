package com.cts.onecognizant.pojo.destination;

import it.avutils.jmapper.annotations.JMap;

public class OrderDestination {

	@JMap("deliveryId")
	private String deliveryNumber;
	@JMap("${order.orderId}")
	private String orderNumber;
	@JMap("{order.storeName}")
	private String shopName;
	@JMap("order.quantity")
	private int orderQuantity;

}
