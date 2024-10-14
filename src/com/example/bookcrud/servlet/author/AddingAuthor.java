package com.example.bookcrud.servlet.author;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.bookcrud.dao.AuthorDao;
import com.example.bookcrud.exception.CustomAuthorException;
import com.example.bookcrud.exception.handler.ErrorHandler;
import com.example.bookcrud.model.Author;

@WebServlet("/authors/adding-author")
public class AddingAuthor extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AuthorDao authorDao;

	@Override
	public void init() throws ServletException {
		System.out.println("Author Dao initialized");
		authorDao = new AuthorDao();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("author form get method");
		req.getRequestDispatcher("author-form.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("author form post method");
		
		try {
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			Author author = new Author(0, name, email);
	        authorDao.insertAuthor(author);
	        System.out.println("Author inserted successfully");
	        resp.sendRedirect(req.getServletContext().getContextPath() + "/books/book-list");
	     
	    } catch (CustomAuthorException e) {
//	     ErrorHandler.handle(req, resp, "data insertion fail ", e);
	     req.setAttribute("errorMessage", e.getMessage());
	     System.out.println(req.getServletContext().getContextPath());
	  		req.getRequestDispatcher("../author-error-page.jsp").forward(req, resp);
	    } catch (SQLException e) {
//	    	  ErrorHandler.handle(req, resp, "database error ", e);
	    	  req.setAttribute("errorMessage", e.getMessage());
	    	
	    		req.getRequestDispatcher("../author-error-page.jsp").forward(req, resp);
		}
	
	
		
		
		
	}

}
