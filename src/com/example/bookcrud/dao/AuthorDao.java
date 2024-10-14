package com.example.bookcrud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.bookcrud.db.DatabaseConnection;
import com.example.bookcrud.exception.CustomAuthorException;
import com.example.bookcrud.exception.CustomBookException;
import com.example.bookcrud.model.Author;
import com.example.bookcrud.model.bookDto.Book;

public class AuthorDao {

	private final String INSERT_AUTHOR = "insert into author (name,email) values (?, ?)";
	private final String FIND_ALL_AUTHOR = "select * from author";
	private final String FIND_AUTHOR_BY_ID = "select * from author where id = ?";
	private final String UPDATE_AUTHOR = "update author set name=?, email = ? where id = ?";
	private final String DELETE_AUTHOR = "delete from author wher id = ?";

	private Connection getConnection() throws SQLException {
		return DatabaseConnection.getInstance().getConnection();
	}

	public void insertAuthor(Author author) throws SQLException {
		if(author.getName() == null || author.getName().isEmpty()) {
			throw new CustomAuthorException("Name cannot be null or empty!");
		}
		if(author.getEmail() == null || author.getEmail().isEmpty()) {
			throw new CustomAuthorException("Email cannot be null or empty!");
		}
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(INSERT_AUTHOR)) {
			stmt.setString(1, author.getName());
			stmt.setString(2, author.getEmail());

			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new CustomAuthorException("Author Creation failed", e.getCause());
		}
	}

	public List<Author> findAllAuthors() {
		List<Author> authors = new ArrayList<>();
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(FIND_ALL_AUTHOR)) {
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Author author = new Author(id, name, email);
				authors.add(author);

			}
		} catch (SQLException e) {
			throw new CustomAuthorException("Cannot find Author List", e.getCause());
		}

		return authors;

	}

	public Author findAuthorById(int id) {
		Author author = null;
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(FIND_AUTHOR_BY_ID)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int authorId = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				author = new Author(authorId, name, email);

			}
		} catch (SQLException e) {
			throw new CustomAuthorException("Cannot find author with id " + id , e.getCause());
		}
		return author;
	}

	public void deleteAuthor(int id) {
		Author existingAuthor = findAuthorById(id);
		if(existingAuthor == null) {
			throw new CustomBookException("Cannot find author with id " + id);
		}
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(DELETE_AUTHOR)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new CustomAuthorException("Cannot delete author Id " + id, e.getCause());
		}
	}

	public void updateAuthor(Author author) {
		Author existingAuthor = findAuthorById(author.getId());
		if(existingAuthor == null) {
			throw new CustomAuthorException("Cannot find author with id " + author.getId());
		}
		
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(UPDATE_AUTHOR)) {
			stmt.setString(1, author.getName());
			stmt.setString(2, author.getEmail());
			stmt.setInt(3, author.getId());

			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new CustomAuthorException("Cannot update author Id " + author.getId(), e.getCause());
		}
	}

}
