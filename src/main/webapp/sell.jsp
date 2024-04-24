<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Sell Product - TradeTrail</title>
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
                <li class="nav-item mx-0 mx-lg-1"><a
                        class="nav-link py-3 px-0 px-lg-3 rounded" href="#market">Market</a></li>
                <li class="nav-item mx-0 mx-lg-1 active"><a
                        class="nav-link py-3 px-0 px-lg-3 rounded" href="sell.jsp">Sell</a></li>
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

<!-- Sell Product Section-->
<section class="page-section" id="sell-product">
    <div class="container">
        <h2 class="text-center text-uppercase text-secondary mb-0">Sell Your Product</h2>
        <div class="divider-custom">
            <div class="divider-custom-line"></div>
            <div class="divider-custom-icon">
                <i class="fas fa-star"></i>
            </div>
            <div class="divider-custom-line"></div>
        </div>
        <!-- Sell Product Form -->
        <div class="row justify-content-center">
            <div class="col-lg-8">
                <form action="addProductServlet" method="post">
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="productName" name="productName" placeholder="Product Name" required>
                        <label for="productName">Product Name</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="description" name="description" placeholder="Description" required>
                        <label for="description">Description</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="price" name="price" placeholder="Price" required>
                        <label for="price">Price</label>
                    </div>
                    <button class="btn btn-primary btn-xl" type="submit">Submit</button>
                </form>
            </div>
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
<!-- Bootstrap core JS-->
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="js/scripts.js"></script>
</body>
</html>
