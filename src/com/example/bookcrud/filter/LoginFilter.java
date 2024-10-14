package com.example.bookcrud.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/books/*,/order-list"})
public class LoginFilter implements Filter {
	
    

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		System.out.println("call login Filter");
		
//		HttpSession session = httpRequest.getSession();
//
//		if (session == null || session.getAttribute("username") == null || session.getAttribute("password") == null) {
//			httpResponse.sendRedirect("index.html");
//		}else{
//			chain.doFilter(request, response);
//		}
		
		String role = null;
	
		Cookie[] cookies = httpRequest.getCookies();
		if(cookies != null){
			for(Cookie cookie : cookies){
				if(cookie.getName().equals("role")) {
					role = cookie.getValue();
					System.out.println(role);
				}
				
			}
		}
		
		if(role == null){
			httpResponse.sendRedirect("index.html");
		}else {
			chain.doFilter(request, response);
		}
		
	}
		
	

}
