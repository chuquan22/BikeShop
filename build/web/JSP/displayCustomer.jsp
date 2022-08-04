<%-- 
    Document   : displayCustomer
    Created on : Feb 11, 2022, 10:37:57 PM
    Author     : Dell
--%>

<%@page import="entity.Customers"%>
<%@page import="java.util.Vector"%>
<%@page import="model.DAOCustomers"%>
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
           //DAOCustomers dao = new DAOCustomers();
          // Vector<Customers> vector = dao.ListAll("select * from customers");
           Vector<Customers> vector = (Vector<Customers>)request.getAttribute("customerList");
           String titleTable = (String)request.getAttribute("titleTable");
        %>
         <table border="1">
            <caption><%=titleTable%></caption>
            <thead>
                <tr>
                    <th>customer_id</th>
                    <th>first_name</th>
                    <th>last_name</th>
                    <th>phone</th>
                    <th>email</th>
                    <th>street</th>
                    <th>city</th>
                    <th>state</th>
                    <th>zip_code</th>
                    <th>update</th>
                    <th>delete</th>
                </tr>
            </thead>
            <tbody>
                <% for (Customers c : vector) { %>
                <tr>
                    <td><%=c.getCustomerID() %></td>
                    <td><%=c.getFirstName() %></td>
                    <td><%=c.getLastName() %></td>
                    <td><%=c.getPhone() %></td>
                    <td><%=c.getEmail() %></td>
                    <td><%=c.getStreet() %></td>
                    <td><%=c.getCity() %></td>
                    <td><%=c.getState() %></td>
                    <td><%=c.getZipCode() %></td>
                    <td><a href="ControllerCustomer?s=updateCustomer&customerID=<%=c.getCustomerID() %>">update</a></td>
                    <td><a href="ControllerCustomer?s=deleteCustomer&customerID=<%=c.getCustomerID() %>">delete</a></td>
                </tr>
                <%}%>
            </tbody>
        </table>
    </body>
</html>
