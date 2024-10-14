package com.example.bookcrud.servlet.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout-servlet")
public class LogoutServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie loginCookie = null;
		Cookie sessionCookie = null;
		Cookie[] cookies = req.getCookies();
		
		HttpSession session = req.getSession(false);
		
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("role")) {
					loginCookie = cookie;
					
				}
				if(cookie.getName().equals("JSESSIONID")) {
					sessionCookie = cookie;
				}
			}
		}

		if(session != null) {
			session.invalidate();
			
		}
		if(loginCookie != null) {
			loginCookie.setMaxAge(0);
			resp.addCookie(loginCookie);
		}
		if(sessionCookie != null) {
			sessionCookie.setMaxAge(0);
			resp.addCookie(sessionCookie);
		}
		
		
		resp.sendRedirect(req.getContextPath());
	}

}
