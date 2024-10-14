package com.example.bookcrud.servlet.order;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.bookcrud.dao.BookOrderDao;
import com.example.bookcrud.model.bookDto.Book;
import com.example.bookcrud.model.bookDto.Order;
import com.example.bookcrud.model.orderDto.OrderDetailsDto;

@WebServlet("/order-list")
public class OrderListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private BookOrderDao orderDao;

    @Override
    public void init() throws ServletException {
        orderDao = new BookOrderDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orderList;
        HttpSession session = req.getSession();
		try {
			orderList = orderDao.findAllOrders();

	        if (orderList != null && !orderList.isEmpty()) {
	            session.setAttribute("orderList", orderList);
	        } 
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
    


        req.getRequestDispatcher("orderList.jsp").forward(req, resp);
    }
}
