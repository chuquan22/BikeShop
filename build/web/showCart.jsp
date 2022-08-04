<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : showCart
    Created on : Feb 24, 2022, 3:21:44 PM
    Author     : Dell
--%>

<%@page import="entity.Customers"%>
<%@page import="entity.Cart"%>
<%@page import="entity.orderDetail"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
        <title>Shopping Cart</title>
    </head>
    <body style="background: #b4cccc;">
        

        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <c:choose>
                <c:when test="${userName==null}">
                    <h5 style="margin-left: 40%">You are not logged in</h5>
                </c:when>
                <c:otherwise>
                    <nav class="d-flex mx-auto my-auto">
                <h5>RollNumber: ${number}</h5>
            </nav>
            <nav class="d-flex mx-auto my-auto">
                <h5>FullName: ${name}</h5>
            </nav>
            <nav class="d-flex mx-auto my-auto">
                <h5>Welcome, ${userName}</h5>
            </nav>
                </c:otherwise>
            </c:choose>
        </nav>
        <br>

        <h5 style="text-align: center"> Shopping Cart </h5>
        <p style="text-align: center; color: white">
            <button type="button" class="btn btn-info"><a style="text-decoration: none; color: white" href="ControllerShop">List Product</a></button> /
            <button type="button" class="btn btn-secondary"><a style="text-decoration: none; color: black" href="ControllerShop?s=showOrder">Check Out</a></button>
        </p>


        <div class="container" style="background-color: white">
            <table class=" table table-striped">

                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Name</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">Price</th>
                        <th scope="col">Total</th>
                        <th scope="col">Remove</th>

                    </tr>
                </thead>
                <tbody>

                    <%
                        java.util.Enumeration em = session.getAttributeNames();
                        float sum = 0;
                        while (em.hasMoreElements()) {
                            String key = em.nextElement().toString();
                            if (!key.equals("userName")) {
                                Cart cart = (Cart) session.getAttribute(key);
                                double total = cart.getQuantity() * cart.getPrice();
                                sum += total;
                    %>
                <form action="UpdateQuantityController" method="post">
                        <input type="hidden" name="productID" value="<%=cart.getP().getProductID()%>">
                        <tr>
                            <td scope="row"><%=cart.getP().getProductID()%></td>
                            <td><%=cart.getP().getProductName()%></td>
                            <td><input style="max-width: 70px" type="number" onchange="this.from.submit()" name="quantity" value="<%=cart.getQuantity()%>"</td>
                            <td><%=cart.getPrice()%></td>
                            <td><%=total%></td>
                            <td><button type="button" class="btn btn-danger"><a onclick="alert('<%=cart.getP().getProductName()%> was removed in cart')" style="text-decoration: none; color: white" href="ControllerCart?s=remove&productID=<%=cart.getP().getProductID()%>">Remove</a></button></td>
                        </tr>
                </form>
                <%}%>
                <%}%>
                <tr class="table-primary">
                    <td> <button class="btn btn-success"><a href="Transaction" style="text-decoration: none; color: white">Transaction history</a></button></td>
                    <td></td>
                    <td></td>
                    <td>Total</td>
                    <td><%=sum%></td>
                    <td><button type="button" class="btn btn-danger"><a onclick="alert(' remove all product in cart')" style="text-decoration: none; color: white" href="ControllerCart?s=removeAll">Remove All</a></button></td>
                </tr>
                </form>
                </tbody>
            </table>
        </div>

    </body>
</html>
