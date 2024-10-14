
<%@page import="com.example.bookcrud.model.Author"%>
<%@page import="com.example.bookcrud.dao.AuthorDao"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Set"%>
<%@page import="com.example.bookcrud.model.bookDto.Book"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book List</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
</head>
<body>
<div class="container mt-5">
<div class="row my-5">
				
					<div class="offset-1 col-md-5">
						<a href="checkout" class="btn btn-outline-primary w-50">CheckOut</a>
					</div>
					<div class="col-md-6">
						<a href="book-list" class="btn btn-outline-primary w-50">Continue Shopping</a>
					</div>
				
		</div>
</div>
	<div class="container my-5">
	
		<div class="row d-flex justify-content-center ">
			<div class="col-md-6">
				<div class="card text-center">
					<div class="card-header">
						<h3 class="text-center text-primary">Your List</h3>
					</div>
					<div class="card-body text-center">
						<table class="table table-bordered text-primary">
							<thead>
								<tr>
									<th>Id</th>
									<th>Title</th>
									<th>Author</th>
									<th>Price</th>
								</tr>
							</thead>
							<tbody>
								<%
									List<Book> cart = (List<Book>) session.getAttribute("cart");
								AuthorDao authorDao = new AuthorDao();
								Double total = 0.0;

								if (cart != null) {
									for (Book book : cart) {
										Author author = authorDao.findAuthorById(book.getAuthor_id());
										total += book.getPrice();
								%>
								<tr>
									<td><%=book.getId()%></td>
									<td><%=book.getTitle()%></td>
									<td><%=author.getName()%></td>
									<td><%=book.getPrice()%></td>
								</tr>
								<%
									}
								} else {
									out.print("No books available.");
								}
								%>
							</tbody>
						</table>
					</div>
					<div class="cart-footer">
						<%
							if (total != 0) {
						%>
						<p class="text-end text-primary">
							Total Price ::
							<%=total%>$
						</p>
						<%
							}
						%>
					</div>
				</div>
			</div>
		</div>
		
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
		integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
		integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
		crossorigin="anonymous"></script>
</body>
</html>
