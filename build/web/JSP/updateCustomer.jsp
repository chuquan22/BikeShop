<%-- 
    Document   : updateCustomer
    Created on : Feb 18, 2022, 8:58:10 AM
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
            ResultSet rsCus = (ResultSet) request.getAttribute("rsCus");
            ResultSet rsSate = (ResultSet) request.getAttribute("rsState");
            ResultSet rsCity = (ResultSet) request.getAttribute("rscity");
            ResultSet rsZipCode = (ResultSet) request.getAttribute("rsZipCode");
        %>
        <%if (rsCus.next()) {%>
        <form action="ControllerCustomer" method="Post">
            <p><input type="hidden" name="s" value="updateCustomer"></p>
            <h3>FORM UPDATE CUSTOMER INFORMATIONS</h3>
            <table>
                <tr>
                    <td><label for="id">CustomerID</label></td>
                    <td><input type="number" name="id" id="id" value="<%=rsCus.getInt(1)%>" ></td>
                </tr>
                <tr>
                    <td><label for="fName">firstName</label></td>
                    <td><input type="text" name="fName" id="fName" value="<%=rsCus.getString(2)%>"></td>
                </tr>
                <tr>
                    <td><label for="lName">lastName</label></td>
                    <td><input type="text" name="lName" id="lName" value="<%=rsCus.getString(3)%>"></td>
                </tr>
                <tr>
                    <td><label for="phone">phone</label></td>
                    <td><input type="number" name="phone" id="phone" value="<%=rsCus.getString(4)%>"></td>
                </tr>
                <tr>
                    <td><label for="email">email</label></td>
                    <td><input type="email" name="email" id="email" value="<%=rsCus.getString(5)%>"></td>
                </tr>
                <tr>
                    <td><label for="street">street</label></td>
                    <td><input type="text" name="street" id="street" value="<%=rsCus.getString(6)%>"></td>
                </tr>
                <tr>
                    <td>city</td>
                    <td><select name="city">
                            <%while (rsCity.next()) {%>
                            <option value="<%=rsCity.getString(1)%>" <%=(rsCity.getString(1).equals(rsCus.getString(7)) ? "selected" : "")%>><%=rsCity.getString(1)%></option>
                            <%}%>
                        </select></td>
                </tr>
                <tr>
                    <td>state</td>
                    <td><select name="state">
                            <%while (rsSate.next()) {%>
                            <option value="<%=rsSate.getString(1)%>" <%=(rsSate.getString(1).equals(rsCus.getString(8)) ? "selected" : "")%>><%=rsSate.getString(1)%></option>
                            <%}%>
                        </select></td>
                </tr>
                <tr>
                    <td>Zip Code</td>
                    <td><select name="zipCode">
                            <%while (rsZipCode.next()) {%>
                            <option value="<%=rsZipCode.getInt(1)%>" <%=(rsZipCode.getInt(1) == rsCus.getInt(9) ? "selected" : "")%>><%=rsZipCode.getInt(1)%></option>
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
