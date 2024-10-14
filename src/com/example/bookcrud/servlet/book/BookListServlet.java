package com.example.bookcrud.servlet.book;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.bookcrud.dao.AuthorDao;
import com.example.bookcrud.dao.BookDao;
import com.example.bookcrud.exception.CustomBookException;
import com.example.bookcrud.exception.handler.ErrorHandler;
import com.example.bookcrud.model.Author;
import com.example.bookcrud.model.bookDto.Book;


@WebServlet("/books/book-list")
public class BookListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BookDao bookDao;
	private AuthorDao authorDao;


	@Override
	public void init() throws ServletException {
		bookDao = new BookDao();
		authorDao = new AuthorDao();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
			List<Book> books = bookDao.findAllBooks();
			List<Author> authors = authorDao.findAllAuthors();
			req.setAttribute("authors", authors);
			req.setAttribute("books", books);
			req.getRequestDispatcher("book-list.jsp").forward(req, resp);

		} 
		catch (CustomBookException e) {
//		ErrorHandler.handle(req, resp, "Error Fetching data", e);
		req.setAttribute("errorMessage", e.getMessage());
		req.getRequestDispatcher("../book-error-page.jsp").forward(req, resp);
		}catch (SQLException e) {
//			ErrorHandler.handle(req, resp, "Database error::", e);
			req.setAttribute("errorMessage", e.getMessage());
			req.getRequestDispatcher("../book-error-page.jsp").forward(req, resp);
		}
	}
	
	

}
