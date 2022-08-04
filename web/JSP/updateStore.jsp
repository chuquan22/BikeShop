<%-- 
    Document   : updateStore
    Created on : Feb 18, 2022, 11:33:01 PM
    Author     : Dell
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Store</title>
    </head>
    <body>
        <%
            ResultSet rsStore = (ResultSet) request.getAttribute("rsStore");
            ResultSet rsZipCode = (ResultSet) request.getAttribute("rsZipCode");
            ResultSet rsAllStore = (ResultSet) request.getAttribute("rsAllStore");
            ResultSet rsCity = (ResultSet) request.getAttribute("rsCity");
        %>
        <%if (rsStore.next()) {%>
        <form action="ControllerStore" method="Post">
            <p><input type="hidden" name="s" value="updateStore"></p>
            <h3>FORM ADD STORE INFORMATIONS</h3>
            <table>
                <tr>
                    <td><label for="id">StoreID</label></td>
                    <td><input type="number" name="id" id="id" value="<%=rsStore.getInt(1)%>"></td>
                </tr>
                <tr>
                    <td><label for="sName">StoreName</label></td>
                    <td><input type="text" name="sName" id="sName" value="<%=rsStore.getString(2)%>"></td>
                </tr>
                <tr>
                    <td><label for="phone">phone</label></td>
                    <td><input type="number" name="phone" id="phone" value="<%=rsStore.getString(3)%>"></td>
                </tr>
                <tr>
                    <td><label for="email">email</label></td>
                    <td><input type="text" name="email" id="email" value="<%=rsStore.getString(4)%>"></td>
                </tr>
                <tr>
                    <td><label for="street">street</label></td>
                    <td><input type="text" name="street" id="street" value="<%=rsStore.getString(5)%>"></td>
                </tr>
                <tr>
                    <td>city</td>
                    <td><select name="city">
                            <%while (rsCity.next()) {%>
                            <option value="<%=rsCity.getString(1)%>" <%=(rsCity.getString(1).equals(rsStore.getString(6)) ? "selected" : "")%>><%=rsCity.getString(1)%></option>
                            <%}%>
                    </td>
                </tr>
                <tr>
                    <td>state</td>
                    <td><select name="state">
                            <%while (rsAllStore.next()) {%>
                            <option value="<%=rsAllStore.getString(7)%>" <%=(rsAllStore.getString(7).equals(rsStore.getString(7)) ? "selected" : "")%>><%=rsAllStore.getString(7)%></option>
                            <%}%>
                        </select></td>
                </tr>
                <tr>
                    <td>Zip Code</td>
                    <td><select name="zipCode">
                            <%while (rsZipCode.next()) {%>
                            <option value="<%=rsZipCode.getInt(1)%>" <%=(rsZipCode.getString(1).equals(rsStore.getString(8)) ? "selected" : "")%>><%=rsZipCode.getString(1)%></option>
                            <%}%>
                        </select></td>
                </tr>

            </table>
            <input type="submit" name="submit">
        </form>
        <%}%>
    </body>
</html>
