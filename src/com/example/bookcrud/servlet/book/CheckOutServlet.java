package com.example.bookcrud.servlet.book;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.bookcrud.dao.BookOrderDao;
import com.example.bookcrud.model.bookDto.Book;
import com.example.bookcrud.model.orderDto.BookOrder;
import com.example.bookcrud.model.orderDto.OrderDetails;

@WebServlet("/books/checkout")
public class CheckOutServlet extends HttpServlet{

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
		doPost(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	Date currentDate = new Date();
	java.sql.Date orderDate = new java.sql.Date(currentDate.getTime());
	BookOrder order = new BookOrder(0, orderDate);
	

	if(order != null) {
		orderDao.insertOrder(order);
	}
	
	HttpSession session = req.getSession();
	List<Book> books = (List<Book>) session.getAttribute("cart");
	
	if(order != null && books != null && !books.isEmpty()) {
		if(order.getId() <= 0) {
			throw new IllegalStateException("order Id cannot be retrived");
		}
		for(Book book: books) {
			System.out.println("orderId -----" + order.getId());
			OrderDetails orderDetails = new OrderDetails(0,order.getId(), book.getId());
			
			orderDao.insertBookToOrder(orderDetails);
			try {
				session.setAttribute("orderDetails", orderDao.findOrderdetailsByOrderId(order.getId()));
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
	}
	
	
	resp.sendRedirect("check-out.jsp");
	}

}
