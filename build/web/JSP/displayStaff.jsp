<%-- 
    Document   : displayStaff
    Created on : Feb 11, 2022, 8:33:59 AM
    Author     : Dell
--%>

<%@page import="entity.staffs"%>
<%@page import="java.util.Vector"%>
<%@page import="model.DAOStaff"%>
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
        //DAOStaff dao = new DAOStaff();
          //Vector<staffs> vector = dao.ListAll("select * from staffs");
             Vector<staffs> vector = (Vector<staffs>)request.getAttribute("staffList");
             //String titlePage = (String)request.setAttribute("titlepage");
             String titleTable = (String)request.getAttribute("titleTable");
        %>
          <table border="1">
            <caption><%=titleTable%></caption>
            <thead>
                <tr>
                    <th>staff_id</th>
                    <th>first_name</th>
                    <th>last_name</th>
                    <th>email</th>
                    <th>phone</th>
                    <th>active</th>
                    <th>store_id</th>
                    <th>manager_id</th>
                    <th>update</th>
                    <th>delete</th>
                </tr>
            </thead>
            <tbody>
                <% for (staffs temp : vector) { %>
                <tr>
                    <td><%=temp.getStaffID() %></td>
                    <td><%=temp.getFirstName() %></td>
                    <td><%=temp.getLastName() %></td>
                    <td><%=temp.getEmail() %></td>
                    <td><%=temp.getPhone() %></td>
                    <td><%=temp.getActive() %></td>
                    <td><%=temp.getStoreID() %></td>
                    <td><%=temp.getManagerID() %></td>
                    <td><a href="ControllerStaff?s=updateStaff&staffID=<%=temp.getStaffID()%>">update</a></td>
                    <td><a href="ControllerStaff?s=deleteStaff&staffID=<%=temp.getStaffID()%>">delete</a></td>
                </tr>
                <%}%>
            </tbody>
           </table>
    </body>
</html>
