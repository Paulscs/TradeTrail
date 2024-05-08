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
<%@ page import="com.google.gson.Gson" %>

<%@ page import="model.UserDAO" %>
<% UserDAO userDAO = (UserDAO) request.getAttribute("userDAO"); %>


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
<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"
	crossorigin="anonymous"></script>
<!-- Google fonts-->
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
	rel="stylesheet" type="text/css" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/index-styles.css" rel="stylesheet" />
	<style>
		.portfolio-item {
			border: 1px solid #ccc;
			border-radius: 5px;
			padding: 20px;
			text-align: center;
			height: 500px;
		}
		.product-desc {
			max-height: calc(1.2em * 3);
			overflow: hidden;
			text-overflow: ellipsis;
			display: -webkit-box;
			-webkit-line-clamp: 2;
			-webkit-box-orient: vertical;
		}
		.portfolio-item img {
			height: 200px; /* Adjust this value as needed */
			width: auto; /* Maintain aspect ratio */
			display: block; /* Ensure the image is a block element */
			margin: 0 auto;
		}
		.product-content{
			text-align: center;
		}
		.product-dropdown{
			padding-top: 125px;
		}
		.dropdown-button-filter{
			display: block;
			margin: auto;
		}

	</style>
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
	<!-- Dropdown menu for category filter -->
	<div class="container product-dropdown">
		<select id="categoryFilter" class="dropdown-button-filter">
			<option value="all">All Categories</option>
			<% List<String> categories = (List<String>) request.getAttribute("categories"); %>
			<% if (categories != null && !categories.isEmpty()) { %>
			<% for (String category : categories) { %>
			<option value="<%= category %>"><%= category %></option>
			<% } %>
			<% } %>
		</select>
	</div>

	<%-- Section to Showcase Products --%>
	<section class="page-section portfolio" id="products">
		<div class="container">
			<!-- Section Heading -->
			<h2 class="page-section-heading text-center text-uppercase text-secondary mb-0">Products</h2>
			<!-- Divider -->
			<div class="divider-custom">
				<div class="divider-custom-line"></div>
				<div class="divider-custom-icon">
					<i class="fas fa-star"></i>
				</div>
				<div class="divider-custom-line"></div>
			</div>
			<!-- Product Grid -->
			<div class="row justify-content-center">
				<% List<Item> items = (List<Item>) request.getAttribute("items"); %>
				<% if (items != null && !items.isEmpty()) { %>
				<% for (Item item : items) { %>
				<div class="col-md-6 col-lg-4 mb-5" data-category="<%= item.getCategory() %>">
					<div class="portfolio-item mx-auto">
						<!-- Display User Name -->
						<h4 class="product-content">Posted by: <%= userDAO.getUsernameById(item.getUserId()) %></h4>
						<!-- Product Image -->
						<img class="img-fluid" src="<%= item.getImageUrl() %>" alt="<%= item.getProductName() %>" />
						<!-- Product Title -->
						<h3 class="product-content"><%= item.getProductName() %></h3>
						<!-- Product Description -->
						<p class="product-content product-desc"><%= item.getDescription() %></p>
						<!-- Button to View More Details -->
						<a class="btn btn-primary product-content" href="product-details.jsp?id=<%= item.getItemId() %>">Contact Seller</a>
					</div>
				</div>
				<% } %>
				<% } else { %>
				<p>No items available</p>
				<% } %>
			</div>
		</div>
	</section>




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


	<script>
		// handle filter change event
		document.getElementById("categoryFilter").addEventListener("change", function() {
			var selectedCategory = this.value;
			if (selectedCategory === "all") {
				// if "all categories" is selected, display all items
				showAllItems();
			} else {
				filterItems(selectedCategory);
			}
		});

		// function to filter items based on category
		function filterItems(category) {
			var items = document.querySelectorAll(".portfolio-item");
			items.forEach(function(item) {
				var itemCategory = item.dataset.category;
				if (itemCategory === category) {
					item.style.display = "block";
				} else {
					item.style.display = "none";
				}
			});
		}

		// function to show all items
		function showAllItems() {
			var items = document.querySelectorAll(".portfolio-item");
			items.forEach(function(item) {
				item.style.display = "block";
			});
		}
	</script>

	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>
</body>
</html>
