<%-- 
    Document   : register
    Created on : Mar 2, 2022, 5:52:59 PM
    Author     : Dell
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <link href="css/register.css" rel="stylesheet" />
        <title> Register form </title>

    </head>

    <body>
        <%
            ResultSet rsSate = (ResultSet) request.getAttribute("rsState");
            ResultSet rsCity = (ResultSet) request.getAttribute("rscity");
            ResultSet rsZipCode = (ResultSet) request.getAttribute("rsZipCode");
            String message = (String) request.getAttribute("message");
        %>

        <form action="ControllerCustomer" method="post" class="actionForm">
            <p><input type="hidden" name="s" value="insertCustomer"></p>
            <h2>Create new account</h2><br>

            <input type="text" placeholder="UserName" name="userName"/><br><br>

            <input type="password" placeholder="PassWord" name="passWord"/><br><br>

            <input type="password" placeholder="re-enter password" name="checkPass"/><br><br>

            <input type="text" placeholder="First Name" name="fName"/><br><br>

            <input type="text" placeholder="Last Name" name="lName"/><br><br>

            <input type="text" placeholder="Phone" name="phone"/><br><br>

            <input type="email" placeholder="Email" name="email"/><br><br>

            <input type="text" placeholder="Street" name="street"/><br><br>

            City <select name="city">
                <%while (rsCity.next()) {%>
                <option value="<%=rsCity.getString(1)%>"><%=rsCity.getString(1)%></option>
                <%}%>
            </select>

            State <select name="state">
                <%while (rsSate.next()) {%>
                <option value="<%=rsSate.getString(1)%>"><%=rsSate.getString(1)%></option>
                <%}%>
            </select>
            Zip Code <select name="zipCode">
                <%while (rsZipCode.next()) {%>
                <option value="<%=rsZipCode.getInt(1)%>"><%=rsZipCode.getInt(1)%></option>
                <%}%>
            </select><br><br>
            <input style="margin-left: 40%; background-color: blue; color: white; padding: 10px" type="submit" name="submit" value="register">
            <%if(message!=null){%>
            <p style="color: red; margin-left: 20%"><%=message%></p>
            <%}%>
        </form>
            
    </body>

</html>

