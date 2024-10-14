package com.example.bookcrud.model.orderDto;

public class OrderDetails {
	private int id;
	private int orderId;
	private int bookId;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public OrderDetails(int id, int orderId, int bookId) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.bookId = bookId;
	}
	
	
}
