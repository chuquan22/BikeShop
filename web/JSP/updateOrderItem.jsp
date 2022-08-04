<%-- 
    Document   : updateOrderItem
    Created on : Feb 19, 2022, 9:09:58 AM
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
            ResultSet rsorderItem = (ResultSet) request.getAttribute("rsorderItem");
            ResultSet rsOrder = (ResultSet) request.getAttribute("rsOrder");
            ResultSet rsProduct = (ResultSet) request.getAttribute("rsProduct");
            ResultSet rsItem = (ResultSet) request.getAttribute("rsItem");
        %>
        <%if (rsorderItem.next()) {%>
        <form action="ControllerOrderItem" method="POST">
            <p><input type="hidden" name="s" value="updateOrderItem"></p>
            <table>
                
                    <tr>
                        <td>orderID</td>
                        <td><select name="orderID">
                            <%while (rsOrder.next()) {%>
                            <option value="<%=rsOrder.getInt(1)%>" <%=(rsOrder.getString(1).equals(rsorderItem.getString(1)) ? "selected" : "")%>><%=rsOrder.getString(1)%></option>
                            <%}%>
                        </select></td>
                    </tr>
                    <tr>
                        <td>itemID</td>
                        <td><select name="itemID">
                        <%while (rsItem.next()) {%>
                            <option value="<%=rsItem.getInt(1)%>" <%=(rsItem.getString(1).equals(rsorderItem.getString(2)) ? "selected" : "")%>><%=rsItem.getString(1)%></option>
                            <%}%>
                            </select></td>
                    </tr>
                    <tr>
                        <td>productID</td>
                        <td><select name="productID">
                            <%while (rsProduct.next()) {%>
                            <option value="<%=rsProduct.getInt(1)%>" <%=(rsProduct.getString(1).equals(rsorderItem.getString(3)) ? "selected" : "")%>><%=rsProduct.getString(2)%></option>
                            <%}%>
                        </select></td>
                    </tr>
                    <tr>
                        <td><label for="quantity">quantity</label></td>
                        <td><input type="number"  name="quantity" id="quantity" value="<%=rsorderItem.getInt(4)%>"></td>
                    </tr>
                    <tr>
                        <td><label for="ListPrice">ListPrice</label></td>
                        <td><input type="number"  name="ListPrice" id="ListPrice" step="0.01" value="<%=rsorderItem.getDouble(5)%>"></td>
                    </tr>
                    <tr>
                        <td><label for="Discount">Discount</label></td>
                        <td><input type="number"  name="Discount" id="Discount" step="0.01" value="<%=rsorderItem.getDouble(6)%>"></td>
                    </tr>
                    
            </table>
            <input type="submit" name="submit" >
            <input type="reset" name="reset" >
        </form>
        <%}%>
    </body>
</html>
