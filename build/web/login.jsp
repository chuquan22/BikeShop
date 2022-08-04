<%-- 
    Document   : login
    Created on : Feb 28, 2022, 8:06:49 AM
    Author     : Dell
--%>

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
            String error = (String)request.getAttribute("error");
        %>
        <div id="wrapper">
            <form action="ControllerLogin" id="form-login" method="POST">
                <h1 class="form-heading">Form Login Shop</h1>
                <div class="form-group">
                    <i class="far fa-user"></i>
                    <input type="text" class="form-input" name="userName" placeholder="Tên đăng nhập">
                </div>
                <div class="form-group">
                    <i class="fas fa-key"></i>
                    <input type="password" class="form-input" name="passWord" placeholder="Mật khẩu">
                    <div id="eye">
                        <i class="far fa-eye"></i>
                    </div>
                </div>
                <input type="submit" value="login" class="form-submit">
                <p style="color: white">You don't have account ? <a href="ControllerCustomer?s=register">Create Account</a></p>
                <p><a style="color: blue" href="ChangePassword.jsp">Change Password</a></p>
                <%if (error != null) {%>
                <p style="color: red"><%=error%></p>
                <%}%>
            </form>

        </div>

    </body>
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="js/app.js"></script>

</html>