<%-- 
    Document   : updateStock
    Created on : Feb 19, 2022, 8:19:15 AM
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
            ResultSet rsStock = (ResultSet) request.getAttribute("rsStock");
            ResultSet rsStore = (ResultSet) request.getAttribute("rsStore");
            ResultSet rsProduct = (ResultSet) request.getAttribute("rsProduct");
        %>
        <%if(rsStock.next()){%>
        <form action="ControllerStocks" method="Post">
            <p><input type="hidden" name="s" value="updateStock"></p>
            <h3>FORM UPDATE STORE INFORMATIONS</h3>
            <table>
                <tr>
                    <td>Store ID</td>
                    <td><select name="sid" id="sid">
                            <%while (rsStore.next()) {%>
                            <option value="<%=rsStore.getInt(1)%>" <%=(rsStore.getString(1).equals(rsStock.getString(1)) ? "selected" : "")%>><%=rsStore.getString(2)%></option>
                            <%}%>
                        </select></td>
                </tr>

                <tr>
                    <td>Product ID</td>
                    <td><select name="pid" id="pid">
                            <%while (rsProduct.next()) {%>
                            <option value="<%=rsProduct.getInt(1)%>" <%=(rsProduct.getString(1).equals(rsStock.getString(2)) ? "selected" : "")%>><%=rsProduct.getString(2)%></option>
                            <%}%>
                        </select></td>
                </tr>

                <tr>
                    <td><label for="quantity">quantity</label></td>
                    <td><input type="number" name="quantity" id="quantity" value="<%=rsStock.getInt(3)%>"></td>
                </tr>

            </table>
            <input type="submit" name="submit">
            <input type="reset" name="reset">
        </form>
        <%}%>
    </body>
</html>
