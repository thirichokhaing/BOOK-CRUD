package com.example.bookcrud.model.orderDto;

import java.sql.Date;

public class OrderDetailsDto {
	
	private int detailId;
	private int orderId;
	private String title;
	private double price;
	private String authorName;
	private Date orderDate;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getDetailId() {
		return detailId;
	}
	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}
	
	
	

	public OrderDetailsDto(int orderId, Date orderDate) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
	}
	public OrderDetailsDto(String title, double price, String authorName, Date orderDate) {
		super();
		this.title = title;
		this.price = price;
		this.authorName = authorName;
		this.orderDate = orderDate;
		
	}
	
	
	
	

}
