<%-- 
    Document   : displayProduct
    Created on : Feb 12, 2022, 8:29:36 PM
    Author     : Dell
--%>

<%@page import="entity.Customers"%>
<%@page import="entity.Products"%>
<%@page import="java.util.Vector"%>
<%@page import="model.DAOProducts"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%String titlePage = (String)request.getAttribute("titlepage");%>
        <title><%=titlePage%></title>
    </head>
    <body>
       <% 
           Vector<Products> vector = (Vector<Products>)request.getAttribute("productList");
           String titleTable = (String)request.getAttribute("titleTable");
           String userName = null;
           java.util.Enumeration em = session.getAttributeNames();
           while(em.hasMoreElements()){
                String key = em.nextElement().toString();
                if(key.equals("userName")){
                    Customers cus =(Customers) session.getAttribute("userName");
                    userName = cus.getUserName();
                }
           }
           if(userName == null){
        %>
        <p><a href="login.jsp">Đăng nhập</a></p>
        <%} else {%>
        <p>Hello, <%=userName%></p>
        <%}%>
        <H2><a href="showCart.jsp">Show Cart</a></H2>
         <table border="1">
            <caption><%=titleTable%></caption>
            <thead>
                <tr>
                    <th>product_id</th>
                    <th>product_name</th>
                    <th>model_year</th>
                    <th>list_price</th>
                    <th>brand_name</th>
                    <th>category_name</th>
                    <th>Cart</th>
                </tr>
            </thead>
            <tbody>
                <% for (Products p : vector) { %>
                <tr>
                    <td><%=p.getProductID() %></td>
                    <td><%=p.getProductName() %></td>
                    <td><%=p.getModelYear() %></td>
                    <td><%=p.getListPrice() %></td>
                    <td><%=p.getBrandName() %></td>
                    <td><%=p.getCategoryName() %></td>
                    <td><a href="ControllerCart?s=addToCart&productID=<%=p.getProductID()%>">Add To Cart</a></td>
                    
                </tr>
                <%}%>
            </tbody>
        </table>
    </body>
</html>
