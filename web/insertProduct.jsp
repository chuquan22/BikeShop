<%-- 
    Document   : insertProduct
    Created on : Mar 4, 2022, 8:45:04 AM
    Author     : Dell
--%>

<%@page import="model.DAOProducts"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Create New Product</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />

    </head>
    <body>
        <%
            DAOProducts dao = new DAOProducts();
            ResultSet rsBrand = dao.getData("select Distinct brand_name from products");
            ResultSet rsCategory = dao.getData("select Distinct category_name from products");
            String error = (String) request.getAttribute("error");
        %>
        <div> 
            <form action="ControllerProduct" method="Post" style="margin-left: 30%; margin-top: 5%; max-width: 500px">
                <p><input type="hidden" name="s" value="insertProduct"></p>
                <h4 style="text-align: center">FORM ADD PRODUCTS INFORMATIONS</h4><br><br>
                <table class="table table-striped">

                    <tr>
                        <td><label for="pName">productName</label></td>
                        <td><input type="text" name="pName" id="pName"></td>
                    </tr>
                    <tr>
                        <td><label for="modelYear">modelYear</label></td>
                        <td><input type="number" name="modelYear" id="modelYear"></td>
                    </tr>
                    <tr>
                        <td><label for="listPrice">listPrice</label></td>
                        <td><input type="number" name="listPrice" id="listPrice" step="0.1"></td>
                    </tr>
                    <tr>
                        <td>brandName</td>
                        <td><select name="brandName">
                                <%while (rsBrand.next()) {%>
                                <option value="<%=rsBrand.getString(1)%>"><%=rsBrand.getString(1)%></option>
                                <%}%>
                            </select></td>
                    </tr>
                    <tr>
                        <td>categoryName</td>
                        <td><select name="categoryName">
                                <%while (rsCategory.next()) {%>
                                <option value="<%=rsCategory.getString(1)%>"><%=rsCategory.getString(1)%></option>
                                <%}%>
                            </select></td>
                    </tr>

                </table>
                <input style="margin-left: 35%" type="submit" name="submit">
                <input type="reset" name="reset">
                <%if (error != null) {%>
                <p style="margin-left: 30%; color: red"><%=error%></p>
                <%}%>
            </form>

        </div>
    </body>
</html>
