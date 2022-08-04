<%-- 
    Document   : displayOrder
    Created on : Feb 11, 2022, 8:51:42 AM
    Author     : Dell
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="entity.orderDetail"%>
<%@page import="java.util.Vector"%>
<%@page import="model.DAOOrder"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            //DAOOrder dao = new DAOOrder();
            Vector<orderDetail> vector = (Vector<orderDetail>) request.getAttribute("vector");
            String status[] = {"New", "Process", "Done", "Cencal"};
            String titleTable = (String) request.getAttribute("titleTable");
            Vector<Integer> vectorO = (Vector<Integer>) request.getAttribute("OrderStatus");
        %>
        <form action="displayOrderDetail">
            <table border="1">
                <caption><%=titleTable%></caption>
                <thead>
                    <tr>
                        <th>orderID</th>
                        <th>customerName</th>
                        <th>orderDate</th>
                        <th>storeName</th>
                        <th>shippedDate</th>
                        <th>OrderStatus</th>
                        <th>detail</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (orderDetail od : vector) {%>
                    <tr style="text-align: center">
                        <td><%=od.getO().getOrderID()%></td>
                        <td><%=od.getC().getFirstName()%> <%=od.getC().getLastName()%></td>
                        <td><%=od.getO().getOrderDate()%></td>
                        <td><%=od.getS().getStoreName()%></td>
                        <td><%=od.getO().getShippedDate()%></td>
                        <td ><%=status[od.getO().getOrderStatus() - 1]%></td>
                        <td><a href="ControllerOrder?s=orderDetail&orderID=<%=od.getO().getOrderID()%>">detail</a></td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
        </form>
    </body>
</html>
