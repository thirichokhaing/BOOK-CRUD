<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Author-form</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
</head>
<body>
	<div class="container my-5">
	<h3 class="text-primary text-center">Author Form</h3>
		<div class="row d-flex justify-content-center">
			<div class="col-md-6">
				<div class="card">
					<div class="card-body">
						<form action="adding-author" method="post">
							<div class="mb-3">
								<label id="name" class="form-label">Name:</label> <input
									id="name" class="form-control" name="name" type="text" />
							</div>

							<div class="mb-3">
								<label for="email" class="form-label">Email:</label>
								 <input id="email" name="email" class="form-control" type="email" />
							</div>

							<input type="submit" class="btn btn-primary w-100" value="Submit" />
						</form>
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