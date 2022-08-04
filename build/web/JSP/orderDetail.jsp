<%-- 
    Document   : orderDetail
    Created on : Feb 16, 2022, 8:08:20 AM
    Author     : Dell
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="entity.orderDetail"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Detail</title>
    </head>
    <body>
        <%
            String status[] = {"New", "Process", "Done", "Null"};
            Vector<orderDetail> vector = (Vector<orderDetail>) request.getAttribute("vector");
            float total = 0;
            for (orderDetail elem : vector) {
                total += (elem.getOi().getQuantity() * elem.getOi().getListPrice());
            }
            String cusName = (String) request.getAttribute("cusName");
            String storeName = (String) request.getAttribute("storeName");
            int oID = (Integer) request.getAttribute("oID");
            String oStatus = (String) request.getAttribute("oStatus");
            ResultSet rsOrderStatus = (ResultSet) request.getAttribute("rsOrderStatus");
            ResultSet rsOrder = (ResultSet) request.getAttribute("rsOrder");
            String oDate = (String) request.getAttribute("oDate");

        %>

        <h3>OrderDetail:</h3>
        <p>Customer Name: <%=cusName%> ;  Store Name: <%=storeName%></p>
        <p>Order ID:<%=oID%> ;  Order Date: <%=oDate%></p>

        <%if (rsOrder.next()) {%>
        <form action="ControllerOrder" method="POST">
            <input type="hidden" name="s" value="updateStatus">
            <input type="hidden" name="orderID" value="<%=oID%>">
            Order Status <select name="orderStatus">
                <%while (rsOrderStatus.next()) {%>
                <option value="<%=rsOrderStatus.getInt(1)%>" <%=(rsOrderStatus.getString(1).equals(rsOrder.getString(3)) ? "selected" : "")%>><%=status[rsOrderStatus.getInt(1) - 1]%></option>
                <%}%>
            </select>
            <input type="submit" name="submit" value="save">
        </form>
        <%}%>
    </select>
    <table border="1">
        <h3>Detail:</h3>
        <thead>
            <tr>
                <th>ProductID</th>
                <th>ProductName</th>
                <th>Quantity</th>
                <th>OrderItem.ListPrice</th>
                <th>Total=Quantity*ListPrice</th>


            </tr>
        </thead>
        <tbody>
            <% for (orderDetail od : vector) {%>
            <tr>
                <td><%=od.getP().getProductID()%></td>
                <td><%=od.getP().getProductName()%></td>
                <td><%=od.getOi().getQuantity()%></td>
                <td><%=od.getOi().getListPrice()%></td>
                <td><%=(od.getOi().getQuantity() * od.getOi().getListPrice())%></td>

            </tr>
            <%}%>
        </tbody>
    </table>
    <p style="margin-left: 10%">Grand Total = <%=total%></p>

</body>
</html>
