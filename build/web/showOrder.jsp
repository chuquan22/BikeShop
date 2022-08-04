<%-- 
    Document   : showOrder
    Created on : Mar 7, 2022, 8:31:59 AM
    Author     : Dell
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="entity.Customers"%>
<%@page import="entity.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Check Order</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
    </head>
    <body>

        <h1 style="text-align: center">Check Out</h1>
        <div class="container">
            <div class="row" class="d-flex my-auto">
                <div class="col-md-6">
                    <h3 style="text-align: center">List Product</h3><br><br>
                    <table class="table table-bordered table-striped" >

                        <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Name</th>
                                <th scope="col">Quantity</th>
                                <th scope="col">Price</th>
                                <th scope="col">Total</th>

                            </tr>
                        </thead>
                        <tbody>
                            <%
                                java.util.Enumeration em = session.getAttributeNames();
                                float sum = 0;
                                while (em.hasMoreElements()) {
                                    String key = em.nextElement().toString();
                                    if (!key.equals("userName")) {
                                        Cart cart = (Cart) session.getAttribute(key);
                                        double total = cart.getQuantity() * cart.getPrice();
                                        sum += total;
                            %>
                            <tr>
                                <td scope="row"><%=cart.getP().getProductID()%></td>
                                <td><%=cart.getP().getProductName()%></td>
                                <td><%=cart.getQuantity()%></td>
                                <td><%=cart.getPrice()%></td>
                                <td><%=total%></td>
                                
                            </tr>
                            <%}%>
                            <%}%>
                        </tbody>
                    </table>
                    <h5>Total : <%=sum%></h5>
                    <p><button type="submit" class="btn btn-success"><a style="text-decoration: none; color: white" href="ControllerShop">Buy More</a> </button></p>        
                </div>

                <div class="col-md-6">
                    <h3 style="text-align: center">Customer Information</h3>
                    <section class="py-5">
                        <%
                            Customers cus = (Customers) request.getAttribute("Customer");
                            ResultSet rsSate = (ResultSet) request.getAttribute("rsState");
                            ResultSet rsCity = (ResultSet) request.getAttribute("rscity");
                            ResultSet rsZipCode = (ResultSet) request.getAttribute("rsZipCode");
                        %>

                        <form action="ControllerShop" method="Post">
                            <input type="hidden" name="s" value="updateCustomer">
                            <table class="table" style="margin-left: 15%">
                                <tr>
                                    <td><label for="fName">firstName</label></td>
                                    <td><input type="text" name="fName" id="fName" value="<%=cus.getFirstName()%>"></td>
                                </tr>
                                <tr>
                                    <td><label for="lName">lastName</label></td>
                                    <td><input type="text" name="lName" id="lName" value="<%=cus.getLastName()%>"></td>
                                </tr>
                                <tr>
                                    <td><label for="phone">phone</label></td>
                                    <td><input type="number" name="phone" id="phone" value="<%=cus.getPhone()%>"></td>
                                </tr>
                                <tr>
                                    <td><label for="email">email</label></td>
                                    <td><input type="email" name="email" id="email" value="<%=cus.getEmail()%>"></td>
                                </tr>
                                <tr>
                                    <td><label for="street">street</label></td>
                                    <td><input type="text" name="street" id="street" value="<%=cus.getStreet()%>"></td>
                                </tr>
                                <tr>
                                    <td>city</td>
                                    <td><select name="city">
                                            <%while (rsCity.next()) {%>
                                            <option value="<%=rsCity.getString(1)%>" <%=(rsCity.getString(1).equals(cus.getCity()) ? "selected" : "")%>><%=rsCity.getString(1)%></option>
                                            <%}%>
                                        </select></td>
                                </tr>
                                <tr>
                                    <td>state</td>
                                    <td><select name="state">
                                            <%while (rsSate.next()) {%>
                                            <option value="<%=rsSate.getString(1)%>" <%=(rsSate.getString(1).equals(cus.getState()) ? "selected" : "")%>><%=rsSate.getString(1)%></option>
                                            <%}%>
                                        </select></td>
                                </tr>
                                <tr>
                                    <td>Zip Code</td>
                                    <td><select name="zipCode">
                                            <%while (rsZipCode.next()) {%>
                                            <option value="<%=rsZipCode.getInt(1)%>" <%=(rsZipCode.getString(1).equals(cus.getZipCode()) ? "selected" : "")%>><%=rsZipCode.getInt(1)%></option>
                                            <%}%>
                                        </select></td>
                                </tr>
                            </table>
                            <input style="margin-left: 45%" type="submit" name="update" value="update">
                            <input type="reset" name="reset">
                        </form>
                    </section>
                </div>
            </div>
            <div class="row"><button class="btn btn-success" style="max-width: 500px; margin-left: 30%; margin-top: 3%"><a style="text-decoration: none; color: white" href="ControllerCart?s=addOrderInDB">Submit</a></button></div>
        </div>
    </body>
</html>
