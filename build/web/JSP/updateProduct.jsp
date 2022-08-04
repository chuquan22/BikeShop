<%-- 
    Document   : updateProduct
    Created on : Feb 18, 2022, 9:23:30 PM
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
            ResultSet rsProduct = (ResultSet) request.getAttribute("rsProduct");
            ResultSet rsBrand = (ResultSet) request.getAttribute("rsBrand");
            ResultSet rsCategory = (ResultSet) request.getAttribute("rsCategory");
        %>
        <%if (rsProduct.next()) {%>
        <form action="ControllerProduct" method="Post">
            <p><input type="hidden" name="s" value="updateProduct"></p>
            <h3>FORM UPDATE PRODUCTS INFORMATIONS</h3>
            <table>
                <tr>
                    <td><label for="id">ProductID</label></td>
                    <td><input type="number" name="id" id="id" value="<%=rsProduct.getInt(1)%>"></td>
                </tr>
                <tr>
                    <td><label for="pName">productName</label></td>
                    <td><input type="text" name="pName" id="pName" value="<%=rsProduct.getString(2)%>"></td>
                </tr>
                <tr>
                    <td><label for="modelYear">modelYear</label></td>
                    <td><input type="number" name="modelYear" id="modelYear" value="<%=rsProduct.getString(3)%>"></td>
                </tr>
                <tr>
                    <td><label for="listPrice">listPrice</label></td>
                    <td><input type="number" name="listPrice" id="listPrice" value="<%=rsProduct.getString(4)%>"></td>
                </tr>
                <tr>
                    <td>brandName</td>
                    <td><select name="brandName">
                            <%while (rsBrand.next()) {%>
                            <option value="<%=rsBrand.getString(1)%>" <%=(rsBrand.getString(1).equals(rsProduct.getString(5)) ? "selected" : "")%>><%=rsBrand.getString(1)%></option>
                            <%}%>
                        </select></td>
                </tr>
                <tr>
                    <td>categoryName</td>
                    <td><select name="categoryName">
                            <%while (rsCategory.next()) {%>
                            <option value="<%=rsCategory.getString(1)%>" <%=(rsCategory.getString(1).equals(rsProduct.getString(6)) ? "selected" : "")%>><%=rsCategory.getString(1)%></option>
                            <%}%>
                        </select></td>
                </tr>

            </table>
            <input type="submit" name="submit">
            <input type="reset" name="reset">
        </form>
        <%}%>
    </body>
</html>
