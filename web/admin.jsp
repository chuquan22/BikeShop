<%-- 
    Document   : admin
    Created on : Mar 2, 2022, 8:17:20 AM
    Author     : Dell
--%>


<%@page import="java.sql.ResultSet"%>
<%@page import="entity.Bill"%>
<%@page import="entity.OrderItems"%>
<%@page import="entity.staffs"%>
<%@page import="entity.orderDetail"%>
<%@page import="entity.Order"%>
<%@page import="entity.Customers"%>
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
        <title>Admin Controller</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
    </head>

    <body>
        <%
            String fullName = null;
            int rollNumber = 0;
            double sum = 0;
            java.util.Enumeration em = session.getAttributeNames();
            while (em.hasMoreElements()) {
                String key = em.nextElement().toString();
                if (key.equals("admin")) {
                    staffs staff = (staffs) session.getAttribute(key);
                    fullName = staff.getFirstName() + staff.getLastName();
                    rollNumber = staff.getStaffID();
                }
            }
            ResultSet rsOrderStatus = (ResultSet) request.getAttribute("rsOrderStatus");
            Vector<Order> vector1 = (Vector<Order>) request.getAttribute("vectorOrder");
            Vector<Products> vector = (Vector<Products>) request.getAttribute("productList");
            Vector<Customers> vectorCus = (Vector<Customers>) request.getAttribute("customerList");
            String status[] = {"New", "Process", "Done", "Cancel"};
            Vector<Bill> vectorBill = (Vector<Bill>) request.getAttribute("Bill");
            String message = (String) request.getAttribute("message");
        %>
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="#!" style="border: 1px solid blue; padding: 20px; border-radius: 100%; background-color: blue; color: white">Shop QQ</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-5">
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="#!">Home</a></li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Controller</a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="#!">View Shop</a></li>
                                <li><a class="dropdown-item" href="ControllerStore">View Store</a></li>
                                <li><a class="dropdown-item" href="ControllerStocks">Controller Stock</a></li>
                                <li>
                                    <hr class="dropdown-divider" />
                                </li>
                                <li><a class="dropdown-item" style="color: #0c63e4" href="ControllerAdmin?s=history">History Manager</a></li>
                            </ul>
                        </li>
                    </ul>

                    <nav class="d-flex my-auto mx-auto">
                        <h6>RollNumber: <%=rollNumber%></h6>
                    </nav>

                    <nav class="d-flex my-auto mx-auto">
                        <h6>FullName: <%=fullName%></h6>
                    </nav>

                    <nav class="d-flex my-auto mx-auto">
                        <h6>Welcome, admin</h6>
                    </nav>

                    <nav class="navbar">
                        <form class="container-fluid justify-content-start" action="ControllerAdmin">
                            <input type="hidden" name="s" value="logout">
                            <button class="btn btn-outline-success me-2" type="submit">LogOut</button>
                        </form>
                    </nav>
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
        <!-- Section-->

        <div>
            <div class="row" class="d-flex my-auto">
                <div class="col-md-2" style="border-right: 1px solid black">
                    <h3 style="text-align: center">Menu</h3>
                    <div class="list-group" style="margin-top: 20%; margin-bottom: 20%">
                        <a href="#" class="list-group-item list-group-item-action active" aria-current="true">
                            MANAGER
                        </a>
                        <a href="ControllerAdmin?s=productManager" class="list-group-item list-group-item-action">Product Manager</a>
                        <a href="ControllerAdmin?s=customerManager" class="list-group-item list-group-item-action">Customer Manager</a>
                        <a href="ControllerAdmin?s=orderManager" class="list-group-item list-group-item-action">Bill Manager</a>

                    </div>
                </div>
                <div class="col-md-10">
                    <h3 style="text-align: center">Controller</h3>

                    <%if (vector != null) {%>
                    <p><button style="margin-left: 2%" type="button" class="btn btn-outline-success"><a style="text-decoration: none; color: blue" href="insertProduct.jsp">Add New Product</a></button></p>
                    <p>
                    <form class="d-flex" action="ControllerProduct" method="post">
                        <input type="hidden" name="s" value="searchByID">
                        <input style="margin-left: 2%;max-width: 200px" class="form-control me-2" type="search" placeholder="Order ID" name="search" aria-label="Search">
                        <button class="btn btn-outline-success" type="submit">Search ID</button>
                    </form>
                    </p>
                    <p>
                    <form class="d-flex" action="ControllerProduct" method="post">
                        <input type="hidden" name="s" value="searchByName">
                        <input style="margin-left: 2%;max-width: 200px" class="form-control me-2" type="search" placeholder="Customer Name" name="search" aria-label="Search">
                        <button class="btn btn-outline-success" type="submit">Search Name</button>
                    </form>
                    </p>
                    <p>
                    <form class="d-flex" action="ControllerProduct" method="post">
                        <input type="hidden" name="s" value="searchByPrice">
                        <input style="margin-left: 2%;max-width: 150px" class="form-control " type="search" placeholder="Price from" name="priceFrom" aria-label="Search">
                        <input style="margin-left: 1%;max-width: 150px" class="form-control " type="search" placeholder="Price to" name="priceTo" aria-label="Search">
                        <button class="btn btn-outline-success" type="submit">Search Price</button>
                    </form>
                    </p>

                    <%if (message != null) {%>
                    <p style="color: red; text-align: center"><%=message%></p>
                    <%}%>
                    <table class="table table-striped table-bordered border-dark" style="margin-left: 2%">
                        <thead>
                            <tr>
                                <th scope="col">product_id</th>
                                <th scope="col">product_name</th>
                                <th scope="col">model_year</th>
                                <th scope="col">list_price</th>
                                <th scope="col">brand_name</th>
                                <th scope="col">category_name</th>
                                <th scope="col">update</th>
                                <th scope="col">delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Products p : vector) {%>
                            <tr>
                                <th scope="row"><%=p.getProductID()%></th>
                                <td><%=p.getProductName()%></td>
                                <td><%=p.getModelYear()%></td>
                                <td><%=p.getListPrice()%></td>
                                <td><%=p.getBrandName()%></td>
                                <td><%=p.getCategoryName()%></td>
                                <td> <button type="button" class="btn btn-success"><a style="text-decoration: none; color: white" href="ControllerProduct?s=updateProduct&productID=<%=p.getProductID()%>">Update</a> </button></td>
                                <td><button type="button" class="btn btn-danger"><a style="text-decoration: none; color: white" href="ControllerProduct?s=deleteProduct&productID=<%=p.getProductID()%>" >Delete</a></button></td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>
                    <%} else if (vectorCus != null) {%>
                    <p><button style="margin-left: 2%" type="button" class="btn btn-outline-success"><a style="text-decoration: none; color: blue" href="ControllerCustomer?s=register">Create New Customer</a></button></p>
                    <p>
                    <form class="d-flex" action="ControllerCustomer" method="post">
                        <input type="hidden" name="s" value="searchByID">
                        <input style="margin-left: 2%;max-width: 200px" class="form-control me-2" type="search" placeholder="Customer ID" name="search" aria-label="Search">
                        <button class="btn btn-outline-success" type="submit">Search ID</button>
                    </form>
                    </p>
                    <p>
                    <form class="d-flex" action="ControllerCustomer" method="post">
                        <input type="hidden" name="s" value="searchByName">
                        <input style="margin-left: 2%;max-width: 200px" class="form-control me-2" type="search" placeholder="Customer Name" name="search" aria-label="Search">
                        <button class="btn btn-outline-success" type="submit">Search Name</button>
                    </form>
                    </p>

                    <%if (message != null) {%>
                    <p style="color: red; text-align: center"><%=message%></p>
                    <%}%>
                    <table class="table table-striped table-bordered border-dark" >
                        <thead>
                            <tr>
                                <th scope="col">customer_id</th>
                                <th scope="col">first_name</th>
                                <th scope="col">last_name</th>
                                <th scope="col">phone</th>
                                <th scope="col">email</th>
                                <th scope="col">street</th>
                                <th scope="col">city</th>
                                <th scope="col">state</th>
                                <th scope="col">zip_code</th>
                                <th scope="col">update</th>
                                <th scope="col">delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Customers c : vectorCus) {%>
                            <tr>
                                <td scope="row"><%=c.getCustomerID()%></td>
                                <td><%=c.getFirstName()%></td>
                                <td><%=c.getLastName()%></td>
                                <td><%=c.getPhone()%></td>
                                <td><%=c.getEmail()%></td>
                                <td><%=c.getStreet()%></td>
                                <td><%=c.getCity()%></td>
                                <td><%=c.getState()%></td>
                                <td><%=c.getZipCode()%></td>
                                <td> <button type="button" class="btn btn-success"><a style="text-decoration: none; color: white" href="ControllerCustomer?s=updateCustomer&customerID=<%=c.getCustomerID()%>">Update</a> </button></td>
                                <td><button type="button" class="btn btn-danger"><a style="text-decoration: none; color: white" href="ControllerCustomer?s=deleteCustomer&customerID=<%=c.getCustomerID()%>" >Delete</a></button></td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>
                    <%} else if (vectorBill != null) {%>

                    <p>
                    <form class="d-flex" action="ControllerOrder" method="post">
                        <input type="hidden" name="s" value="searchByID">
                        <input style="margin-left: 2%;max-width: 200px" class="form-control me-2" type="search" placeholder="Order ID" name="search" aria-label="Search">
                        <button class="btn btn-outline-success" type="submit">Search ID</button>
                    </form>
                    </p>
                    <p>
                    <form class="d-flex" action="ControllerOrder" method="post">
                        <input type="hidden" name="s" value="searchByName">
                        <input style="margin-left: 2%;max-width: 200px" class="form-control me-2" type="search" placeholder="Customer Name" name="search" aria-label="Search">
                        <button class="btn btn-outline-success" type="submit">Search Name</button>
                    </form>
                    </p>

                    <form action="ControllerAdmin" method="Post">
                        <input type="hidden" name="s" value="searchByOrderStatus">
                        <select name="orderStatus">
                            <option value="1">New</option>
                            <option value="2">Process</option>
                            <option value="3">Done</option>
                            <option value="4">Null</option>
                        </select>
                        <input type="submit" name="submit" value="search">
                    </form>
                    
                    <table class="table table-striped table-bordered border-dark">
                        <thead>
                            <tr>
                                <th scope="col">Bill ID</th>
                                <th scope="col">Customer Name</th>
                                <th scope="col">Date</th>
                                <th scope="col">Total</th>
                                <th scope="col">Order Status</th>
                                <th scope="col">view</th>
                            </tr>
                        </thead>
                        <tbody>

                            <% for (Bill b : vectorBill) {%>
                        <form action="ControllerOrder" method="post">
                            <input type="hidden" name="s" value="updateStatus">
                            <input type="hidden" name="orderID" value="<%=b.getBillID()%>">
                            <tr>
                                <td scope="row"><%=b.getBillID()%></td>
                                <td><%=b.getCustomerName()%></td>
                                <td><%=b.getDate()%></td>
                                <td><%=b.getTotal()%></td>
                                <!--<td><select name="orderStatus" onchange="this.form.submit()">
                                <%while (rsOrderStatus.next()) {%>
                                <option value="<%=rsOrderStatus.getInt(1)%>" <%=(rsOrderStatus.getInt(1) == b.getStatus() ? "selected" : "")%>><%=status[rsOrderStatus.getInt(1) - 1]%></option>
                                <%}%>
                            </select></td> -->
                                <td><select name="orderStatus" onchange="this.form.submit()">
                                        <%for (Order o : vector1) {%>
                                        <option value="<%=o.getOrderStatus()%>" <%=(o.getOrderStatus() == b.getStatus() ? "selected" : "")%>> <%=status[o.getOrderStatus() - 1]%></option>
                                        <%}%>
                                    </select></td>


                                <td><button type="button" class="btn btn-primary"><a style="text-decoration: none; color: white" href="ControllerOrder?s=orderDetail&orderID=<%=b.getBillID()%>">detail</a></button></td>
                                <!--<%if (b.getStatus() == 4) {%>
                                <td><a href="ControllerAdmin?s=deletOrder&id=<%=b.getBillID()%>">delete</a></td>
                                <%} else {%>
                                <td></td>
                                <%}%>-->
                            </tr>
                        </form>
                        <%}%>

                        </tbody>
                    </table>
                    <%}%>
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