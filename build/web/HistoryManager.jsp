<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : HistoryManager
    Created on : Mar 17, 2022, 8:35:55 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Manager</title>
    </head>
    <body>
        <h1 style="text-align: center">History Manager</h1>
    
        <table border="1">
            <thead>
                <tr>
                    <th>Manage Time</th>
                    <th>Staff Name</th>
                    <th>Manage Content</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="m" items="${history}">
                <tr>
                    <td>${m.time}</td>
                    <td>${m.name}</td>
                    <td>${m.content}</td>
                </tr>
                </c:forEach>
            </tbody>
        </table>

    
    </body>
</html>
