package com.example.bookcrud.servlet.book;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.bookcrud.dao.AuthorDao;
import com.example.bookcrud.dao.BookDao;
import com.example.bookcrud.exception.CustomBookException;
import com.example.bookcrud.exception.handler.ErrorHandler;
import com.example.bookcrud.model.Author;
import com.example.bookcrud.model.bookDto.Book;

@WebServlet("/books/updating-book")
public class UpdatingBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BookDao bookDao;
	private AuthorDao authorDao;

	@Override
	public void init() throws ServletException {
		bookDao = new BookDao();
		authorDao = new AuthorDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Integer id = Integer.parseInt(request.getParameter("id"));
		Book book;
		try {
			book = bookDao.findBookById(id);

			List<Author> authors = authorDao.findAllAuthors();
			if(authors != null) {
				request.setAttribute("authors", authors);
			}
			if(book != null) {
				request.setAttribute("existingBook", book);
			}else {
				System.out.println("no book found");
			}
		} catch (CustomBookException e) {
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("../book-error-page.jsp").forward(request, response);
//			ErrorHandler.handle(request, response, "cannot find Book with id" + id, e);
		}
		catch (SQLException e) {
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("../book-error-page.jsp").forward(request, response);
//			ErrorHandler.handle(request, response, "Database error::", e);
		}

		
		request.getRequestDispatcher("edit-form.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			String title = request.getParameter("title");
			Double price = Double.parseDouble(request.getParameter("price"));
			Integer authorId = Integer.parseInt(request.getParameter("authorId"));

			Book book = new Book(id, title, price , authorId);
			System.out.println(book);
			bookDao.updateBook(book);
			response.sendRedirect("book-list");
		} catch (CustomBookException e) {
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("../book-error-page.jsp").forward(request, response);
//			ErrorHandler.handle(request, response, "data update error", e);
		}
		catch (SQLException e) {
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("../book-error-page.jsp").forward(request, response);
//			ErrorHandler.handle(request, response, "Database error::", e);
		}

	}

}
