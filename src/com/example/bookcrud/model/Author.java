package com.example.bookcrud.model;

public class Author {
	private int id;
	private String name;
	private String email;
	
	public Author(int id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}
	
	
	public Author() {
		super();
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setTitle(String email) {
		this.email = email;
	}
	
	

}
