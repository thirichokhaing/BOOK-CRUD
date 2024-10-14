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
import com.example.bookcrud.model.Author;
import com.example.bookcrud.model.bookDto.Book;
import com.example.bookcrud.exception.CustomBookException;
import com.example.bookcrud.exception.handler.ErrorHandler;

@WebServlet("/books/adding-book")
public class AddingBookServlet extends HttpServlet {

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
		List<Author> authors = authorDao.findAllAuthors();
		req.setAttribute("authors", authors);
		req.getRequestDispatcher("book-form.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String name = req.getParameter("title");
			Double price = Double.parseDouble(req.getParameter("price"));
			int authorId = Integer.parseInt(req.getParameter("authorId"));
			Book book = new Book(0, name, price, authorId);
			bookDao.insertBook(book);
			resp.sendRedirect(req.getServletContext().getContextPath() + "/books/book-list");

		} catch (NumberFormatException e) {
//			ErrorHandler.handle(req, resp, "price cannot be null or NaN  ", e);
			req.setAttribute("errorMessage", e.getMessage());
			req.getRequestDispatcher("../book-error-page.jsp").forward(req, resp);
		} catch (CustomBookException e) {
			req.setAttribute("errorMessage", e.getMessage());
			req.getRequestDispatcher("../book-error-page.jsp").forward(req, resp);
//			ErrorHandler.handle(req, resp, "data insertion error", e);
		} catch (SQLException e) {
			
			req.setAttribute("errorMessage", e.getMessage());
			req.getRequestDispatcher("../book-error-page.jsp").forward(req, resp);
//			ErrorHandler.handle(req, resp, "Database error::", e);
		}

	}

}
