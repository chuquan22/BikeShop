<%-- 
    Document   : updateOrder
    Created on : Feb 18, 2022, 9:46:34 PM
    Author     : Dell
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            ResultSet rsOrder = (ResultSet) request.getAttribute("rsOrder");
            ResultSet rsCus = (ResultSet) request.getAttribute("rsCus");
            ResultSet rsStore = (ResultSet) request.getAttribute("rsStore");
            ResultSet rsStaff = (ResultSet) request.getAttribute("rsStaff");
             ResultSet rsOrders = (ResultSet) request.getAttribute("rsOrders");
        %>
        
        <%if(rsOrder.next()){%>
        <form action="ControllerOrder" method="POST">
            <p><input type="hidden" name="s" value="updateOrder"></p>
            <table>
                <tr>
                    <td><label for="id">orderID</label></td>
                    <td><input type="number"  name="id" id="id" value="<%=rsOrder.getInt(1)%>"></td>
                </tr>
                <tr>
                    <td>CustomerID</td>
                    <td><select name="CustomerID">
                            <%while (rsCus.next()) {%>
                            <option value="<%=rsCus.getInt(1)%>" <%=(rsCus.getString(1).equals(rsOrder.getString(2)) ? "selected" : "")%>><%=rsCus.getString(2)%></option>
                            <%}%>
                        </select></td>
                </tr>
                <tr>
                    <td>OrderStatus</td>
                    <td><select name="OrderStatus">
                            <%while (rsOrders.next()) {%>
                            <option value="<%=rsOrders.getInt(1)%>" <%=(rsOrders.getString(1).equals(rsOrder.getString(3)) ? "selected" : "")%>><%=rsOrders.getString(1)%></option>
                            <%}%>
                        </select></td>
                </tr>
                <tr>
                    <td><label for="orderDate">orderDate</label></td>
                    <td><input type="date" name="orderDate" id="orderDate" value="<%=rsOrder.getString(4)%>"></td>
                </tr>
                <tr>
                    <td><label for="requiredDate">requiredDate</label></td>
                    <td><input type="date" name="requiredDate" id="requiredDate" value="<%=rsOrder.getString(5)%>"></td>
                </tr>
                <tr>
                    <td><label for="shippedDate">shippedDate</label></td>
                    <td><input type="date" name="shippedDate" id="shippedDate" value="<%=rsOrder.getString(6)%>"></td>
                </tr>
                <tr>
                    <td>storeID</td>
                    <td><select name="storeID">
                            <%while (rsStore.next()) {%>
                            <option value="<%=rsStore.getInt(1)%>" <%=(rsStore.getString(1).equals(rsOrder.getString(7)) ? "selected" : "")%>><%=rsStore.getString(2)%></option>
                            <%}%>
                        </select></td>
                </tr>
                <tr>
                    <td>staffID</td>
                    <td><select name="staffID">
                            <%while (rsStaff.next()) {%>
                            <option value="<%=rsStaff.getInt(1)%>" <%=(rsStaff.getString(1).equals(rsOrder.getString(8)) ? "selected" : "")%>><%=rsStaff.getString(2)%></option>
                            <%}%>
                        </select></td>
                </tr>
            </table>
            <input type="submit" name="submit" value="submit">
            <input type="reset" name="reset" value="reset">
        </form>
        <%}%>
    </body>
</html>
