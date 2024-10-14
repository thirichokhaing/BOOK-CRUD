package com.example.bookcrud.model.orderDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.bookcrud.model.bookDto.Book;

public class BookOrder {
 
	private int id;
	private Date orderDate;
	
	private List<Book> books = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public BookOrder(int id, Date orderDate) {
		super();
		this.id = id;
		this.orderDate = orderDate;
	}
	
	
}
