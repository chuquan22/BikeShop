<%-- 
    Document   : updateOrderStatus
    Created on : Feb 22, 2022, 3:43:30 PM
    Author     : Dell
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            ResultSet rsOrderStatus = (ResultSet) request.getAttribute("rsOrderStatus");
            ResultSet rsOrder = (ResultSet) request.getAttribute("rsOrder");
            String status[] = {"New","Process","Done","Updating..."};
        %>
         <%if (rsOrder.next()) {%>
        <form action="ControllerOrder" method="POST">
            <p><input type="hidden" name="s" value="updateStatus"></p>
            <table>
                
                    <tr>
                        <td><label for="orderID">order ID</label></td>
                        <td><input type="number"  name="orderID" id="orderID" value="<%=rsOrder.getInt(1)%>"></td>
                    </tr>
                    <tr>
                        <td>order Status</td>
                        <td><select name="orderStatus">
                        <%while (rsOrderStatus.next()) {%>
                            <option value="<%=rsOrderStatus.getInt(1)%>" <%=(rsOrderStatus.getString(1).equals(rsOrder.getString(3)) ? "selected" : "")%>><%=status[rsOrderStatus.getInt(1)-1]%></option>
                            <%}%>
                            </select></td>
                    </tr>
            </table>
            <input type="submit" name="submit" >
            <input type="reset" name="reset" >
        </form>
        <%}%>
    </body>
</html>
