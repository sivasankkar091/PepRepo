package com.rabbit.publish.bean;

public class OrderBean {
	private String orderId;
	private String item;
	private int quantity;
	private int unitPrice;
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
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
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
	public OrderBean(String item, String orderId, int quantity, int unitPrice) {
		super();
		this.item = item;
		this.orderId = orderId;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}
	protected OrderBean() {
		
	}
}
