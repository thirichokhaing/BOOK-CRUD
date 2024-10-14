<%@page import="com.example.bookcrud.dao.AuthorDao"%>
<%@page import="com.example.bookcrud.model.Author"%>
<%@page import="com.example.bookcrud.model.bookDto.Book"%>
<%@page import="java.util.List"%>
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
		<div class="d-flex justify-content-between mb-5">
			<div class="col-6">
				<a href="../logout-servlet" class="btn btn-danger">Logout</a>
			</div>
			<div>
				<a href="adding-book" class="btn btn-primary">Add New Book</a>
				<a href="../authors/adding-author" class="btn btn-secondary">Add Author</a>
			</div>
		</div>

	<h3 class="my-3 text-primary text-center">Book List</h3>

	<table class="table table-striped table-bordered">
		<thead>
			<tr>
				<th>Id</th>
				<th>Title</th>
				<th>Author</th>
				<th>Price</th>
				<th>Actions</th>
				<th>Add To Cart</th>
			</tr>
		</thead>
		<tbody>
			<%
				List<Book> books = (List<Book>) request.getAttribute("books");
				List<Author> authors = (List<Author>) request.getAttribute("authors");
				Author existingAuthor = new Author();

			if (books != null) {
				for (Book book : books) {
				if(authors != null){
					for(Author a : authors){
						if(book.getAuthor_id() == a.getId()){
							existingAuthor = a;
						}
					}
				}
			%>
			<tr>
				<td><%=book.getId()%></td>
				<td><%=book.getTitle()%></td>
				<td><%=existingAuthor.getName()%></td>
				<td><%=book.getPrice()%></td>
				<td><a href="updating-book?id=<%=book.getId()%>"
					class="btn btn-warning btn-sm">Update</a> <a
					href="deleting-book?id=<%=book.getId()%>"
					class="btn btn-danger btn-sm">Delete</a></td>
				<td><a href="add-to-cart?id=<%=book.getId()%>"
					class="btn btn-success btn-sm">Add To Cart</a></td>
			</tr>
			<%
				}
			} else {
			%>
			<tr>
				<td colspan="6">No books available</td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>

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
