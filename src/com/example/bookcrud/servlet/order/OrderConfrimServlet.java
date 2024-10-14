package com.example.bookcrud.servlet.order;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.bookcrud.dao.BookOrderDao;

@WebServlet("/confirm-order")
public class OrderConfrimServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BookOrderDao orderDao;
	
	@Override
	public void init() throws ServletException {
	orderDao = new BookOrderDao();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id =Integer.parseInt(req.getParameter("id"));
		orderDao.confirmOrderStatus(id);
		resp.sendRedirect("order-list");
	}

}
