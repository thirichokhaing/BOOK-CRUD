package com.example.bookcrud.servlet.book;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.bookcrud.dao.BookDao;
import com.example.bookcrud.exception.CustomBookException;
import com.example.bookcrud.exception.handler.ErrorHandler;

@WebServlet("/books/deleting-book")
public class DeletingBookServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BookDao bookDao;

	@Override
	public void init() throws ServletException {
		bookDao = new BookDao();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	try {
		int id = Integer.parseInt(req.getParameter("id"));
		bookDao.deleteBook(id);
		resp.sendRedirect("book-list");
	} catch (CustomBookException e) {
		req.setAttribute("errorMessage", e.getMessage());
		req.getRequestDispatcher("../book-error-page.jsp").forward(req, resp);
//		ErrorHandler.handle(req, resp, "", e);
	}
	catch (SQLException e) {
		req.setAttribute("errorMessage", e.getMessage());
		req.getRequestDispatcher("../book-error-page.jsp").forward(req, resp);
//		ErrorHandler.handle(req, resp, "Database error::", e);
	}
	}

}
