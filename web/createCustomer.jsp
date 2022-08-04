<%-- 
    Document   : createCustomer
    Created on : Mar 7, 2022, 1:16:56 AM
    Author     : Dell
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/reset.css">
        <link rel="stylesheet" href="css/app.css">
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css">
        <title>Form login</title>
    </head>

    <body>
        <%
            ResultSet rsSate = (ResultSet) request.getAttribute("rsState");
            ResultSet rsCity = (ResultSet) request.getAttribute("rscity");
            ResultSet rsZipCode = (ResultSet) request.getAttribute("rsZipCode");
            String error = (String) request.getAttribute("error");
        %>
        <div id="wrapper" style="color: white">
            <form action="ControllerCustomer" id="form-login" method="POST">
                <p><input type="hidden" name="s" value="insertCustomer"></p>
                <h1 class="form-heading">Form Create Account</h1>
                <div class="form-group">
                    <i class="far fa-user"></i>
                    <input type="text" class="form-input" name="userName" placeholder="User Name">
                </div>
                <div class="form-group">
                    <i class="fas fa-key"></i>
                    <input type="password" class="form-input" name="passWord" placeholder="Pass Word">
                    <div id="eye">
                        <i class="far fa-eye"></i>
                    </div>
                </div>
                <div class="form-group">
                    <i class="fas fa-key"></i>
                    <input type="password" class="form-input" name="checkPass" placeholder="re-Pass Word">
                    <div id="eye">
                        <i class="far fa-eye"></i>
                    </div>
                </div>
                <div class="form-group">
                    <i class="far fa-user"></i>
                    <input type="text" class="form-input" name="fName" placeholder="First Name">
                </div>
                <div class="form-group">
                    <i class="far fa-user"></i>
                    <input type="text" class="form-input" name="lName" placeholder="Last Name">
                </div>
                <div class="form-group">
                    <i class="fa-solid fa-phone"></i>
                    <input type="text" class="form-input" name="phone" placeholder="phone">
                </div>
                <div class="form-group">
                    <i class="fa-solid fa-at"></i>
                    <input type="email" class="form-input" name="email" placeholder="Email">
                </div>
                <div class="form-group">
                    <i class="fa-solid fa-road"></i>
                    <input type="text" class="form-input" name="street" placeholder="Street">
                </div>
                <div class="form-group">
                    City : <select name="city">
                        <%while (rsCity.next()) {%>
                        <option value="<%=rsCity.getString(1)%>"><%=rsCity.getString(1)%></option>
                        <%}%>
                    </select>
                </div>
                <div class="form-group">
                    State : <select name="state">
                        <%while (rsSate.next()) {%>
                        <option value="<%=rsSate.getString(1)%>"><%=rsSate.getString(1)%></option>
                        <%}%>
                    </select>
                </div>
                <div class="form-group">
                    Zip Code :<select name="zipCode">
                        <%while (rsZipCode.next()) {%>
                        <option value="<%=rsZipCode.getInt(1)%>"><%=rsZipCode.getInt(1)%></option>
                        <%}%>                    
                    </select>
                </div>  
                <%if (error != null) {%>
                <p style="color: red; text-align: center"><%=error%></p>
                <%}%>
                <input type="submit" value="Create" class="form-submit">
                <p style="color: white">You have account ? <a style="color: blue" href="login.jsp">Login Now</a></p>
                
            </form>

        </div>

    </body>
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="js/app.js"></script>

</html>
