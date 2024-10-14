package com.example.bookcrud.servlet.auth;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.bookcrud.model.Role;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = (String) req.getParameter("name");
		String password = (String) req.getParameter("password");
		
		if(username != null && password != null) {
			
			if("user123".equals(username) && "userpwd".equals(password)) {
				Cookie userCookie = new Cookie("role", Role.USER.toString());
				resp.addCookie(userCookie);
				resp.sendRedirect(req.getContextPath() + "/books/book-list");
				
			}else if("root".equals(username) && "adminpwd".equals(password)) {
				Cookie userCookie = new Cookie("role", Role.ADMIN.toString());
				resp.addCookie(userCookie);
				resp.sendRedirect(req.getContextPath() + "/order-list");
			}
		}else {
			req.getRequestDispatcher(req.getContextPath()).forward(req, resp);
		}
		

	}

}
