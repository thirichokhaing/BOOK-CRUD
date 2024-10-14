package com.example.bookcrud.model.bookDto;

public class Book{
	
	private int id;
	private String title;
	private double price;
	private int author_id;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public int getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}
	public Book(int id, String title, Double price, int author_id) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.author_id = author_id;
	}
	public Book(String title) {
		super();
		this.title = title;
	}
	


	
	

}
