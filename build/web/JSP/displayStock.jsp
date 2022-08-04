<%-- 
    Document   : displayStock
    Created on : Feb 12, 2022, 10:27:17 PM
    Author     : Dell
--%>

<%@page import="entity.stocks"%>
<%@page import="java.util.Vector"%>
<%@page import="model.DAOStock"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%String titlePage = (String)request.getAttribute("titlepage");%>
        <title><%=titlePage%></title>
    </head>
    <body>
        <% 
          /// DAOStock dao = new DAOStock();
           Vector<stocks> vector = (Vector<stocks>)request.getAttribute("stockList");
           String titleTable = (String)request.getAttribute("titleTable");
        %>
        <h5 style="margin-left: 5%"><%=titleTable%></h5>
        <p><a href="ControllerStocks?s=insertStock">Create new stock</a></p>
         <table class="table-striped border table-bordered border-dark">
            
            <thead>
                <tr>
                    <th scope="col">store_id</th>
                    <th scope="col">product_id</th>
                    <th scope="col">quantity</th>
                    <th scope="col">update</th>
                    <th scope="col">delete</th>
                </tr>
            </thead>
            <tbody>
                <% for (stocks st : vector) { %>
                <tr>
                    <td scope="row"><%=st.getStoreID() %></td>
                    <td><%=st.getProductID() %></td>
                    <td><%=st.getQuantity() %></td>
                    <td><a href="ControllerStocks?s=updateStock&storeID=<%=st.getStoreID()%>&productID=<%=st.getProductID()%>" >update</a></td>
                    <td><a href="ControllerStocks?s=deleteStock&storeID=<%=st.getStoreID()%>&productID=<%=st.getProductID()%>" >delete</a></td>
                </tr>
                <%}%>
            </tbody>
        </table>
    </body>
</html>
