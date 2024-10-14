package com.example.bookcrud.exception.handler;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.ServletException;

public class ErrorHandler {

    public static void handle(HttpServletRequest req, HttpServletResponse resp, String message, Exception e)
            throws ServletException, IOException {
        req.setAttribute("errorMessage", message + e.getMessage());
        RequestDispatcher dispatcher = req.getRequestDispatcher("error-page.jsp");
        dispatcher.forward(req, resp);
    }
}
