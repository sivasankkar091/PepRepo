package com.cts.onecognizant.pojo.source;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {

	private String orderId;
	private int quantity;
	private String storeName;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Order() {

	}

	public Order(String orderId, int quantity, String storeName) {
		super();
		this.orderId = orderId;
		this.quantity = quantity;
		this.storeName = storeName;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", quantity=" + quantity + ", storeName=" + storeName + "]";
	}

}
