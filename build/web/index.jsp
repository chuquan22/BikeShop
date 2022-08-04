<%-- 
    Document   : index
    Created on : Mar 2, 2022, 5:02:42 PM
    Author     : Dell
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="entity.Products"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Shop QQ</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
    </head>

    <body>
        <%
            Vector<Products> vector = (Vector<Products>) request.getAttribute("productList");
            ResultSet rs = (ResultSet) request.getAttribute("rsCategory");
            String userName = (String) request.getAttribute("nameLogin");
            String fullName = (String) request.getAttribute("fullName");
            Integer cusID = (Integer) request.getAttribute("CusID");
            
        %>
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container px-4 px-lg-2">
                <a class="navbar-brand" href="#!" style="border: 1px solid black; padding: 10px; border-radius: 100%; background-color: blue; color: white">QQ Shop</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-2">
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="ControllerShop">Home</a></li>

                    </ul>
                    <form class="d-flex mx-auto" action="ControllerShop" method="post">
                        <input type="hidden" name="s" value="search">
                        <input  class="form-control me-2" type="search" placeholder="Search" name="search" aria-label="Search">
                        <button class="btn btn-outline-success" type="submit">Search</button>
                    </form>

                    <form class="d-flex" action="ControllerCart">
                        <button class="btn btn-outline-dark" type="submit">
                            <i class="bi-cart-fill me-1"></i>
                            Cart
                            <span style="color: #0b5ed7">${quantity}</span>
                        </button>
                    </form>
                    <%if (userName == null) {%>
                    <nav class="navbar navbar-light bg-light">
                        <form class="container-fluid justify-content-start" action="login.jsp">
                            <button class="btn btn-outline-success ms-lg-4" type="submit">Login</button>
                        </form>

                    </nav>
                    <form class="justify-content-start" action="ControllerCustomer">
                        <input type="hidden" name="s" value="register">
                        <button class="btn btn-outline-success" type="submit">Register</button>
                    </form>

                    <%} else {%>
                    <nav class="d-flex mx-auto my-auto">
                        <h5>RollNumber: <%=cusID%></h5>
                    </nav>
                    <nav class="d-flex mx-auto my-auto">
                        <h5>FullName: <%=fullName%></h5>
                    </nav>
                    <nav class="d-flex mx-auto my-auto">
                        <h5>Welcome, <%=userName%></h5>
                    </nav>
                    <nav class="navbar">
                        <form class="container-fluid justify-content-start" action="ControllerShop">
                            <input type="hidden" name="s" value="logout">
                            <button class="btn btn-outline-success me-2" type="submit">LogOut</button>
                        </form>
                    </nav>
                    <%}%>
                </div>
            </div>
        </nav>
        <!-- Header-->
        <header class="py-5" style="background-color: rgb(11, 122, 98)">
            <div class="container px-4 px-lg-5 my-5">
                <div class="text-center text-white">
                    <h1 class="display-4 fw-bolder">Shop QQ</h1>
                </div>
            </div>
        </header>

        <div>
            <div class="row" class="d-flex my-auto">
                <div class="col-md-3" style="border-right: 1px solid black">
                    <h3 style="text-align: center">Menu</h3>
                    <div class="list-group" style="margin-top: 20%; margin-bottom: 20%">
                        <a href="ControllerProduct" class="list-group-item list-group-item-action active" aria-current="true">
                            All Category
                        </a>
                        <%while (rs.next()) {%>
                        <a class="list-group-item list-group-item-action" href="ControllerShop?s=category&categoryName=<%=rs.getString(1)%>"><%=rs.getString(1)%></a>
                        <%}%>
                    </div>
                    <p style="text-align: center"> Search By Price <br>
                        <form class="d-flex" action="ControllerShop" method="post">
                        <input type="hidden" name="s" value="searchByPrice">
                        <input style="margin-left: 2%;max-width: 100px" class="form-control " type="search" placeholder="Price from" name="priceFrom" aria-label="Search">
                        <input style="margin-left: 2%;margin-right: 2%;max-width: 100px" class="form-control " type="search" placeholder="Price to" name="priceTo" aria-label="Search">
                        <button class="btn btn-outline-success" type="submit">Search Price</button>
                        </form>
                    </p>
                </div>
                <div class="col-md-9">
                    <h3 style="text-align: center">Product</h3>
                    <section class="py-5">
                        <div class="container px-4 px-lg-5 mt-3">
                            <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                                <% for (Products p : vector) {%>
                                <div class="col mb-5">
                                    <div class="card h-100">
                                        <!-- Product details-->
                                        <div class="card-body p-4">
                                            <div class="text-center">
                                                <!-- Product name-->
                                                <h5 class="fw-bolder"><%=p.getProductName()%></h5>
                                                <p><%=p.getBrandName()%></p>
                                                <p><%=p.getCategoryName()%></p><br>
                                                <p>$<%=p.getListPrice()%></p>

                                            </div>
                                        </div>
                                        <!-- Product actions-->
                                        <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                            <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="ControllerCart?s=addToCart&productID=<%=p.getProductID()%>">Add To Cart</a></div>
                                        </div>
                                    </div>
                                </div>
                                <%}%>       
                                </section>
                            </div>
                        </div>
                </div>

                <!-- Footer-->
                <footer class="py-5 bg-dark">
                    <div class="container">
                        <p class="m-0 text-center text-white">Project Individua PRJ301</p>
                    </div>
                </footer>
                <!-- Bootstrap core JS-->
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
                <!-- Core theme JS-->
                <script src="js/scripts.js"></script>
                </body>

                </html>
