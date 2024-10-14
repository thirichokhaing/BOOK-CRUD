<%@page import="com.example.bookcrud.model.orderDto.OrderDetailsDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Checkout Page</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
</head>
<body>
	<p class="text-primary mt-5">You order has been confirmed.</p>
	<div class="container my-5">
		<div class="row d-flex justify-content-center ">
			<div class="col-md-6">
				<div class="card text-center">
					<div class="card-header">
						<h3 class="text-center text-primary">Your Book List</h3>
					</div>
					<div class="card-body text-center">
						<table class="table table-bordered text-primary">
							<thead>
								<tr>
									<th>Title</th>
									<th>Author</th>
									<th>Price</th>
								</tr>
							</thead>
							<tbody>
								<%
									List<OrderDetailsDto> orderDetails = (List<OrderDetailsDto>) session.getAttribute("orderDetails");

								Double total = 0.0;

								if (orderDetails != null) {
									for (OrderDetailsDto orderDetail : orderDetails) {

										total += orderDetail.getPrice();
								%>
								<tr>
									<td><%=orderDetail.getTitle()%></td>
									<td><%=orderDetail.getAuthorName()%></td>
									<td><%=orderDetail.getPrice()%></td>

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