<%-- 
    Document   : insertStore
    Created on : Mar 6, 2022, 2:04:34 PM
    Author     : Dell
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert store</title>
    </head>
    <body>
        <%
            ResultSet rsZipCode = (ResultSet) request.getAttribute("rsZipCode");
            ResultSet rsAllStore = (ResultSet) request.getAttribute("rsAllStore");
            ResultSet rsCity = (ResultSet) request.getAttribute("rsCity");
        %>
        <form action="ControllerStore" method="Post">
            <p><input type="hidden" name="s" value="updateStore"></p>
            <h3>FORM ADD STORE INFORMATIONS</h3>
            <table>
                <tr>
                    <td><label for="id">StoreID</label></td>
                    <td><input type="number" name="id" id="id" value=""></td>
                </tr>
                <tr>
                    <td><label for="sName">StoreName</label></td>
                    <td><input type="text" name="sName" id="sName" value=""></td>
                </tr>
                <tr>
                    <td><label for="phone">phone</label></td>
                    <td><input type="number" name="phone" id="phone" value=""></td>
                </tr>
                <tr>
                    <td><label for="email">email</label></td>
                    <td><input type="text" name="email" id="email" value=""></td>
                </tr>
                <tr>
                    <td><label for="street">street</label></td>
                    <td><input type="text" name="street" id="street" value=""></td>
                </tr>
                <tr>
                    <td>city</td>
                    <td><select name="city">
                            <%while (rsCity.next()) {%>
                            <option value="<%=rsCity.getString(1)%>"><%=rsCity.getString(1)%></option>
                            <%}%>
                    </td>
                </tr>
                <tr>
                    <td>state</td>
                    <td><select name="state">
                            <%while (rsAllStore.next()) {%>
                            <option value="<%=rsAllStore.getString(7)%>"><%=rsAllStore.getString(7)%></option>
                            <%}%>
                        </select></td>
                </tr>
                <tr>
                    <td>Zip Code</td>
                    <td><select name="zipCode">
                            <%while (rsZipCode.next()) {%>
                            <option value="<%=rsZipCode.getInt(1)%>"><%=rsZipCode.getString(1)%></option>
                            <%}%>
                        </select></td>
                </tr>

            </table>
            <input type="submit" name="submit">
        </form>
    </body>
</html>
