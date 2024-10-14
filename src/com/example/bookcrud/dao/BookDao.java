package com.example.bookcrud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import com.example.bookcrud.db.DatabaseConnection;
import com.example.bookcrud.exception.CustomBookException;
import com.example.bookcrud.model.bookDto.Book;



public class BookDao {
	

	private static final String INSERT_BOOK = "insert into book (title,price,author_id) values (?,?,?)";
	private static final String FIND_ALL = "select * from book";
	private static final String UPDATE_BOOK = "update book SET title=?, price=?, author_id=? where id=?";
	private static final String DELETE = "delete from book where id=?";
	private static final String FIND_BY_ID = "select * from book where id=?";
	
	 private Connection getConnection() throws SQLException {
	        return DatabaseConnection.getInstance().getConnection();
	    }
	
	public void insertBook(Book book) throws SQLException {
		if(book.getTitle() == null || book.getTitle().isEmpty()) {
			throw new CustomBookException("Book Title cannot be null or empty!!");
		}
		
		Double price = book.getPrice();
		
		if(price == null || Double.isNaN(book.getPrice()) ) {
			throw new CustomBookException("price cannot be null or NaN!!");
		} 
		try(Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement(INSERT_BOOK)) {
			
			stmt.setString(1, book.getTitle());
			stmt.setDouble(2, book.getPrice());
			stmt.setInt(3, book.getAuthor_id());

			stmt.executeUpdate();

		} catch (SQLException e) {
		throw new CustomBookException("book insertion failed", e.getCause());
		}
	}

	public List<Book> findAllBooks()throws SQLException {
		List<Book> books = new ArrayList<>();

		try (Connection conn =getConnection(); PreparedStatement stmt = conn.prepareStatement(FIND_ALL)) {

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				Double price = rs.getDouble("price");
				int authorId = rs.getInt("author_id");
				books.add(new Book(id, title, price,authorId));
			}
		}
		catch (SQLException e) {
			throw new CustomBookException("cannot find all book list", e.getCause());
		}
		return books;
	}
	
	public void updateBook(Book book)throws SQLException {
		try(Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(UPDATE_BOOK)){
			stmt.setString(1, book.getTitle());
			stmt.setDouble(2, book.getPrice());
			stmt.setInt(3, book.getAuthor_id());
			stmt.setInt(4, book.getId());
			
			stmt.executeUpdate();
			
		}catch (SQLException e) {
			throw new CustomBookException("cannot find all book list", e.getCause());
		}
	}
	
	public void deleteBook(int id)throws SQLException {
	
		
		try(Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(DELETE)){
			stmt.setInt(1, id);
			stmt.executeUpdate();
			
		}catch (SQLException e) {
			throw new CustomBookException("cannot find all book list", e.getCause());
					}
	}
	
	public Book findBookById(int id) throws SQLException{
		Book book = null;
		try(Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(FIND_BY_ID)){
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int bookId = rs.getInt("id");
				String title = rs.getString("title");
				Double price = rs.getDouble("price");
				int authorId = rs.getInt("author_id");
				book= new Book(bookId, title,price, authorId);
				
			}
			
		}catch (SQLException e) {
			throw new CustomBookException("cannot find book with id "+ id);
		}
		return book;
	}
}
