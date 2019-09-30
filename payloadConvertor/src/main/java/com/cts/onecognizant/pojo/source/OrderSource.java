package com.cts.onecognizant.pojo.source;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class OrderSource {
	@SerializedName("deliveryId")
	private String deliveryId;
	@SerializedName("orders")
	private List<Order> orders;
	public String getDeliveryId() {
		return deliveryId;
	}
	public void setDeliveryId(String deliveryId) {
		this.deliveryId = deliveryId;
	}
	
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	@Override
	public String toString() {
		return "OrderSource [deliveryId=" + deliveryId + ", orders=" + orders + "]";
	}
	
	public OrderSource(String deliveryId, List<Order> orders) {
		super();
		this.deliveryId = deliveryId;
		this.orders = orders;
	}
	public OrderSource() {
		
	}
	
}
