<%-- 
    Document   : displayStore
    Created on : Feb 12, 2022, 7:11:41 PM
    Author     : Dell
--%>

<%@page import="entity.stores"%>
<%@page import="java.util.Vector"%>
<%@page import="model.DAOStore"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Favicon-->
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
           //DAOStore dao = new DAOStore();
           Vector<stores> vector = (Vector<stores>)request.getAttribute("storeList");
           String titleTable = (String)request.getAttribute("titleTable");
        %>
        <div>
        <h3 style="text-align: center"><%=titleTable%></h3>
        <p><a href="ControllerStore?s=insertStore">Create new Store</a></p>
         <table class="table table-primary border table-bordered border-dark">
             
            <thead>
                <tr>
                    <th scope="col">store_id</th>
                    <th scope="col">store_name</th>
                    <th scope="col">phone</th>
                    <th scope="col">email</th>
                    <th scope="col">street</th>
                    <th scope="col">city</th>
                    <th scope="col">state</th>
                    <th scope="col">zip_code</th>
                    <th scope="col">update</th>
                    <th scope="col">delete</th>
                </tr>
            </thead>
            <tbody>
                <% for (stores s : vector) { %>
                <tr>
                    <td scope="row"><%=s.getStoreID() %></td>
                    <td><%=s.getStoreName() %></td>
                    <td><%=s.getPhone() %></td>
                    <td><%=s.getEmail() %></td>
                    <td><%=s.getStreet() %></td>
                    <td><%=s.getCity() %></td>
                    <td><%=s.getState() %></td>
                    <td><%=s.getZipCode() %></td>
                    <td><a href="ControllerStore?s=updateStore&storeID=<%=s.getStoreID() %>">update</a></td>
                    <td><a href="ControllerStore?s=deleteStore&storeID=<%=s.getStoreID() %>">delete</a></td>
                </tr>
                <%}%>
            </tbody>
        </table>
        </div>
    </body>
</html>
