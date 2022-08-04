<%-- 
    Document   : DemoJSP
    Created on : Feb 11, 2022, 7:54:10 AM
    Author     : Dell
--%>

<%@page import="java.util.Date"%>
<%@page import="entity.staffs"%>
<%@page import="java.util.Vector"%>
<%@page import="model.DAOStaff"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <!--script-->
        <%
            // code here
            int a = 100;
            DAOStaff dao = new DAOStaff();
            Vector<staffs> vector = dao.ListAll("select * from staffs");
            out.println("<h2>a=" + a + "</h2>");
        %>
        <!--express-->
        <h3 style="color:red"><%=a*12%></h3>
        <% for (int i=10;i<=100;i+=10) {
        %>
        <hr width="<%=i%>" />
        <% } %>
        <% for (int i = 10;i<=100;i += 10) {
            
                 out.print("<hr width=" + i + "/>");
            }%>
        <!--declare-->
        <% 
            int min=0;
        %>
        
        <%! Date d = new Date();%>
        
        <%!
            public String getName(String name){
                  return name;
            }
        %>
        
    </body>
</html>
