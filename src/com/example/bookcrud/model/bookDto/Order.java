package com.example.bookcrud.model.bookDto;

import java.util.Date;

public class Order {

	private int orderId;
	private Date orderDate;
	private int count;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Order(int orderId, Date orderDate, int count) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.count = count;
	}
	
	
	
}
