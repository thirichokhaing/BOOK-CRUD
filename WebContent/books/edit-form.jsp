<%@page import="com.example.bookcrud.model.Author"%>
<%@page import="java.util.List"%>
<%@page import="com.example.bookcrud.dao.AuthorDao"%>
<%@page import="com.example.bookcrud.model.bookDto.Book"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book-Edit-Form</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
</head>
<body>
	<%
		Book book = (Book) request.getAttribute("existingBook");

	if (book != null) {
		Integer id = book.getId();
		String title = book.getTitle();
		int authorId = book.getAuthor_id();
		double price = book.getPrice();
	%>
	<div class="container">
	<h3 class="my-3 text-primary text-center">Book Edit Form</h3>
		<div class="row d-flex justify-content-center">
			<div class="col-md-6">
				<div class="card">
					<div class="card-body">
					<form action="updating-book" method="post">
						<input type="hidden" name="id" value="<%=id%>">
						<div class="mb-2">
							<label id="title" class="form-label">Title:</label> 
							<input id="title" name="title" class="form-control" value="<%=title%>" type="text" />
						</div>
						<div class="mb-2">
							<label class="form-label">Author :</label>
							<select class="form-select" name="authorId">
								<%
							
								List<Author> authors = (List<Author>)request.getAttribute("authors");
								if (authors != null) {
									for (Author author : authors) {
								%>
								<option value="<%=author.getId()%>"><%=author.getName()%></option>
								<%
									}
								} else {
								%>
								<option>No author available</option>
								<%
									}
								%>
							</select>
						</div>
						<div class="mb-2">
							<label for="price" class="form-label" >Price:</label>
							 <input id="price" name="price" class="form-control" value="<%=price%>" type="number" step="0.01" />
						</div>
					
						 <input type="submit" class="btn btn-primary w-100" value="Submit" />
					</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%
		} else {
	%>
	<p>No book found</p>
	<%
		}
	%>

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