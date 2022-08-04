<%-- 
    Document   : displayOrder
    Created on : Feb 18, 2022, 10:11:22 PM
    Author     : Dell
--%>

<%@page import="entity.Order"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Manager</title>
    </head>
    <body>
        <% 
           Vector<Order> vector = (Vector<Order>)request.getAttribute("orderList");
        %>
        <p><a href="ControllerOrder?s=displayOrderDetail">Order detail</a>
         <table border="1">
            <caption>list of orders</caption>
            <thead>
                <tr>
                    <th>order_id</th>
                    <th>customer_id</th>
                    <th>order_status</th>
                    <th>order_date</th>
                    <th>required_date</th>
                    <th>shipped_date</th>
                    <th>store_id</th>
                    <th>staff_id</th>
                    <th>update</th>
                    <th>delete</th>
                </tr>
            </thead>
            <tbody>
                <% for (Order o : vector) { %>
                <tr>
                    <td><%=o.getOrderID() %></td>
                    <td><%=o.getCustomerID() %></td>
                    <td><%=o.getOrderStatus() %></td>
                    <td><%=o.getOrderDate() %></td>
                    <td><%=o.getRequiredDate() %></td>
                    <td><%=o.getShippedDate() %></td>
                    <td><%=o.getStoreID() %></td>
                    <td><%=o.getStaffID() %></td>
                    <td><a href="ControllerOrder?s=updateOrder&orderID= <%=o.getOrderID() %>">update</a></td>
                    <td><a href="ControllerOrder?s=deleteOrder&orderID= <%=o.getOrderID() %>">delete</a></td>
                </tr>
                <%}%>
            </tbody>
        </table>
    </body>
</html>
