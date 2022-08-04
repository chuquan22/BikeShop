<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : Transaction history
    Created on : Mar 16, 2022, 4:40:45 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1 style="text-align: center">Transaction History</h1>
        <h3>Customer Information</h3>
        <p>Customer Name: ${name} <br> 
           Phone: ${phone} ; Email: ${email} <br>
           Address: ${address}
        </p>
        <h3>Order Information</h3>
        <table border="1">
            <thead>
                <tr>
                    <th>Time</th>
                    <th>Order ID</th>
                    <th>Store Name</th>
                    <th>Product Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Total</th>
                    <th>Order Status</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="o" items="${order}">
                <tr>
                    <td>${o.o.orderDate}</td>
                    <td>${o.o.orderID}</td>
                    <td>${o.s.storeName}</td>
                    <td>${o.p.productName}</td>
                    <td>${o.oi.quantity}</td>
                    <td>${o.oi.listPrice}</td>
                    <td>${o.oi.quantity * o.oi.listPrice}</td>
                    <c:if test="${o.o.orderStatus==1}">
                        <td>New</td>
                    </c:if>
                    <c:if test="${o.o.orderStatus==2}">
                        <td>Process</td>
                    </c:if>
                    <c:if test="${o.o.orderStatus==3}">
                        <td>Done</td>
                    </c:if>
                    <c:if test="${o.o.orderStatus==4}">
                        <td>Cancel</td>
                    </c:if>
                </tr>
                </c:forEach>
            </tbody>
        </table>
        
    </body>
</html>
