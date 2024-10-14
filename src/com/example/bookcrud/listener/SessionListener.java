package com.example.bookcrud.listener;

import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener{
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
	 System.out.println("session created at  "+ new Date());
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		 System.out.println("session destroyed at  "+ new Date());
		
	}

}
