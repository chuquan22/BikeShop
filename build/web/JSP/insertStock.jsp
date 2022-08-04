<%-- 
    Document   : insertStore
    Created on : Mar 6, 2022, 1:48:18 PM
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
            ResultSet rsStore = (ResultSet) request.getAttribute("rsStore");
            ResultSet rsProduct = (ResultSet) request.getAttribute("rsProduct");
        %>
        <form action="ControllerStocks" method="Post">
            <p><input type="hidden" name="s" value="insertStock"></p>
            <h3>FORM UPDATE STORE INFORMATIONS</h3>
            <table>
                <tr>
                    <td>Store ID</td>
                    <td><select name="sid" id="sid">
                            <%while (rsStore.next()) {%>
                            <option value="<%=rsStore.getInt(1)%>"><%=rsStore.getString(2)%></option>
                            <%}%>
                        </select></td>
                </tr>

                <tr>
                    <td>Product ID</td>
                    <td><select name="pid" id="pid">
                            <%while (rsProduct.next()) {%>
                            <option value="<%=rsProduct.getInt(1)%>"><%=rsProduct.getString(2)%></option>
                            <%}%>
                        </select></td>
                </tr>

                <tr>
                    <td><label for="quantity">quantity</label></td>
                    <td><input type="number" name="quantity" id="quantity" value=""></td>
                </tr>

            </table>
            <input type="submit" name="submit">
            <input type="reset" name="reset">
        </form>
    </body>
</html>
