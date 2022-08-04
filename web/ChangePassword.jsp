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
        <title>Form Change Password</title>
    </head>

    <body>
        <%
            String error = (String)request.getAttribute("error");
        %>
        <div id="wrapper">
            <form action="ControllerPassword" id="form-login" method="POST">
                <h1 class="form-heading">Form Change Password</h1>
                <div class="form-group">
                    <i class="far fa-user"></i>
                    <input type="text" class="form-input" name="userName" placeholder="User Name">
                </div>
                <div class="form-group">
                    <i class="fas fa-key"></i>
                    <input type="password" class="form-input" name="OldpassWord" placeholder="Password">
                    <div id="eye">
                        <i class="far fa-eye"></i>
                    </div>
                </div>
                <div class="form-group">
                    <i class="fas fa-key"></i>
                    <input type="password" class="form-input" name="NewpassWord" placeholder="New Password">
                    <div id="eye">
                        <i class="far fa-eye"></i>
                    </div>
                </div>
                <div class="form-group">
                    <i class="fas fa-key"></i>
                    <input type="password" class="form-input" name="ReNewpassWord" placeholder="Enter the new password">
                    <div id="eye">
                        <i class="far fa-eye"></i>
                    </div>
                </div>
                <input type="submit" value="save" class="form-submit">
                <p><a style="color: blue" href="login.jsp">Login</a></p>
                <%if (error != null) {%>
                <p style="color: red"><%=error%></p>
                <%}%>
                <p style="color: green">${changePass}</p>
            </form>

        </div>

    </body>
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="js/app.js"></script>

</html>