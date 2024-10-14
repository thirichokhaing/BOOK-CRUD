<%@page import="com.example.bookcrud.model.bookDto.Order"%>
<%@page import="com.example.bookcrud.model.bookDto.Book"%>
<%@page import="com.example.bookcrud.model.orderDto.OrderDetailsDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order List</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
</head>
<body>
	<div class="container my-5">
	<div class="col-6">
				<a href="logout-servlet" class="btn btn-danger">Logout</a>
			</div>
		<h3 class="text-primary text-center">Order List</h3>
		<div class="container my-5">
			<div class="row d-flex justify-content-center ">
				<div class="col-md-10">
					<div class="card text-center">

						<div class="card-body text-center">
							<table class="table table-bordered text-primary">
								<thead>
									<tr>

										<th>OrderId</th>
										<th>Quantity</th>
										<th>Order Date</th>
										<th>Actions</th>
									</tr>
								</thead>
								<tbody>
									<%
										List<Order> orderList = (List<Order>) session.getAttribute("orderList");
								

									if (orderList != null) {
										for (Order order : orderList) {
											
									%>
									<tr>
										
										<td><%=order.getOrderId()%></td>
										<td><%=order.getCount()%></td>
										<td><%=order.getOrderDate()%></td>
										<td><a class="btn btn-outline-success"
											href="confirm-order?id=<%=order.getOrderId()%>">Confirm</a>
											<a class="btn btn-outline-danger">Cancel</a></td>
											
									</tr>
									<%
										}
									} else {
										out.print("No books available or error retrieving book lists.");
									}
									%>
								</tbody>

							</table>
						</div>
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