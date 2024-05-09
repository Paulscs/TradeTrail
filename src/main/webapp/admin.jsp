<%@ page import="model.Item" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="java.util.function.Predicate" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="java.util.stream.Stream" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="model.UserDAO" %>
<%@ page import="model.ItemDAO" %>
<%@ page import="com.google.gson.Gson" %>
<% UserDAO userDAO = (UserDAO) request.getAttribute("userDAO"); %>
<% ItemDAO itemDAO = (ItemDAO) request.getAttribute("itemDAO"); %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>TradeTrail</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js" crossorigin="anonymous"></script>
<!-- Google fonts-->
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
<link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/index-styles.css" rel="stylesheet" />

	<!-- Bootstrap CSS -->
	<link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
	<!-- Bootstrap datatable css -->
	<link href="https://cdn.datatables.net/2.0.7/css/dataTables.bootstrap5.css" rel="stylesheet">
</head>
<body id="page-top">
	<!-- Navigation-->
	<nav
		class="navbar navbar-expand-lg bg-secondary text-uppercase fixed-top"
		id="mainNav">
		<div class="container">
			<a class="navbar-brand" href="#page-top">TradeTrail</a>
			<button
				class="navbar-toggler text-uppercase font-weight-bold bg-primary text-white rounded"
				type="button" data-bs-toggle="collapse"
				data-bs-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				Menu <i class="fas fa-bars"></i>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ms-auto">
					<% if ((boolean)session.getAttribute("admin")) { %>
					<li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="admin">Admin</a></li>
					<% } %>
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="products">Market</a></li>
					<li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="sell">Sell</a></li>
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="#favorites">Favorites</a></li>
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded logout-link" href="logout">Logout</a></li>
					<li class="nav-item mx-0 mx-lg-1 bg-danger"><a
							class="nav-link py-3 px-0 px-lg-3 rounded" href="logout"><%= session.getAttribute("name") %></a></li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- Datatable-->
	<table id="producttable" class="table table-striped" style="width:100%; margin-top: 60px;">
		<thead>
		<tr>
			<th>ID</th>
			<th>Title</th>
			<th>Category ID</th>
			<th>Category Name</th>
			<th>Description</th>
			<th>Price</th>
			<th>Actions</th>
		</tr>
		</thead>
		<tbody>
		<!-- loop through product data to fill up the table rows -->
		<%
			Object products2 = request.getAttribute("products2");
			if (products2 instanceof List) {
				List<Item> productList = (List<Item>) products2;
				for (Item item : productList) {
		%>
		<tr>
			<td><%= item.getItemId() %></td>
			<td><%= item.getProductName() %></td>
			<td><%= item.getCategoryId() %></td>
			<td><%= item.getDescription() %></td>
			<td><%= item.getPrice() %></td>
			<td>
				<button class="btn btn-danger delete-btn" data-id="<%= item.getItemId() %>" onclick="deleteItem(<%= item.getItemId() %>)">Delete</button>
				<button class="btn btn-primary edit-btn" data-id="<%= item.getItemId() %>">Edit</button>
			</td>
		</tr>
		<%
				}
			} else {
				out.println("No products retrieved in JSP");
			}
		%>
		</tbody>
	</table>
	</div>
	<!-- Hidden form for editing an item -->
	<form id="editForm" style="display: none;">
		<input type="hidden" id="editItemId" name="itemId">
		<div class="mb-3">
			<label for="editTitle" class="form-label">Title</label>
			<input type="text" class="form-control" id="editTitle" name="title">
		</div>
		<div class="mb-3">
			<label for="editCategory" class="form-label">Category</label>
			<input type="text" class="form-control" id="editCategory" name="category">
		</div>
		<div class="mb-3">
			<label for="editDescription" class="form-label">Description</label>
			<textarea class="form-control" id="editDescription" name="description"></textarea>
		</div>
		<div class="mb-3">
			<label for="editPrice" class="form-label">Price</label>
			<input type="text" class="form-control" id="editPrice" name="price">
		</div>
		<button type="button" class="btn btn-primary" id="saveEdit">Save Changes</button>
	</form>
	<!-- Footer-->
	<footer class="footer text-center">
		<div class="container">
			<div class="row">
				<!-- Footer About Text-->
				<div class="col-lg-12">
					<h4 class="text-uppercase mb-4">Paul Calderon</h4>
					<p class="lead mb-0">
						J2EE Online Marketplace
						by <a href="https://github.com/Paulscs"> Me</a> .
					</p>
				</div>
			</div>
		</div>
	</footer>
	<!-- Copyright Section-->
	<div class="copyright py-4 text-center text-white">
		<div class="container">
			<a href="https://github.com/Paulscs"> Paul Calderon</a>
		</div>
	</div>

	<!-- jquery with a hopefully functioning cdn-->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<!-- Bootstrap core JS-->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Bootstrap datable-->
	<script defer src="https://cdn.datatables.net/2.0.7/js/dataTables.js"></script>
	<!-- Bootstrap datable integration-->
	<script defer src="https://cdn.datatables.net/2.0.7/js/dataTables.bootstrap5.js"></script>
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>
	<script>
		$(document).ready(function () {
			new DataTable('#producttable');
		});
	</script>
	<script>
		function deleteItem(itemId) {
			fetch('/admin', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/x-www-form-urlencoded',
				},
				body: 'itemId=' + encodeURIComponent(itemId),
			})
					.then(response => {
						if (response.ok) {
							//refresh datatable
							location.reload();
						} else {
							console.error('Failed to delete item');
						}
					})
					.catch(error => {
						console.error('Error:', error);
					});
		}
	</script>

	<script>
		$(document).ready(function () {

			$('.edit-btn').click(function () {
				var itemId = $(this).data('id');

				$.ajax({
					type: 'GET',
					url: '/updateItem',
					data: {itemId: itemId},
					success: function (response) {
						$('#editItemId').val(response.itemId);
						$('#editTitle').val(response.productName);
						$('#editCategory').val(response.categoryId); 
						$('#editDescription').val(response.description);
						$('#editPrice').val(response.price);

						// Show the edit form
						$('#editForm').show();
					},
					error: function () {
						alert('Failed to fetch item details');
					}
				});
			});

			$('#saveEdit').click(function () {
				var formData = $('#editForm').serialize();

				$.ajax({
					type: 'POST',
					url: '/updateItem',
					data: formData,
					success: function (response) {
						console.log('Item updated successfully');
					},
					error: function () {
						alert('Failed to update item');
					}
				});
			});
		});
	</script>

</body>
</html>
