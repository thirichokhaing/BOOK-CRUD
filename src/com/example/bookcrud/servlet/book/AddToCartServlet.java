package com.example.bookcrud.servlet.book;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.bookcrud.dao.BookDao;
import com.example.bookcrud.exception.CustomBookException;
import com.example.bookcrud.exception.handler.ErrorHandler;
import com.example.bookcrud.model.bookDto.Book;

@WebServlet("/books/add-to-cart")
public class AddToCartServlet extends HttpServlet{

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

		Integer id = Integer.parseInt(req.getParameter("id"));
		Book book = bookDao.findBookById(id);
		HttpSession session = req.getSession();
		
		List<Book> cart = (List<Book>) session.getAttribute("cart");
		if(cart == null) {
			cart = new ArrayList<>();
			
		}
		
		boolean existing = false;
		
		for(Book b: cart) {
			if(b.getId() == book.getId()) {
			
				existing = true;
				break;
			}
		}
		
		if(!existing) {
			cart.add(book);
		}
		
		
		session.setAttribute("cart", cart);
		
		resp.sendRedirect("shopping-cart.jsp");
	} catch (CustomBookException e) {
		req.setAttribute("errorMessage", e.getMessage());
		req.getRequestDispatcher("../book-error-page.jsp").forward(req, resp);
	}catch (SQLException e) {
		req.setAttribute("errorMessage", e.getMessage());
		req.getRequestDispatcher("../book-error-page.jsp").forward(req, resp);
	}
		
	}
}
