<%-- 
    Document   : updateStaff
    Created on : Feb 18, 2022, 7:57:59 AM
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
         ResultSet rsStaff = (ResultSet)request.getAttribute("rsStaff"),
         rsStore = (ResultSet)request.getAttribute("rsStore"),
         rsManager = (ResultSet)request.getAttribute("rsManager");
        %>
        <% if (rsStaff.next()) { %>
        <form action="ControllerStaff" method="Post">
            <p><input type="hidden" name="s" value="updateStaff"></p>
            <h3>FORM ADD STAFF INFORMATIONS</h3>
            <table>
                <tr>
                    <td><label for="id">StaffID</label></td>
                    <td><input type="text" name="id" id="id" value="<%=rsStaff.getInt(1)%>"></td>
                </tr>
                <tr>
                    <td><label for="fName">firstName</label></td>
                    <td><input type="text" name="fName" id="fName" value="<%=rsStaff.getString(2)%>"></td>
                </tr>
                <tr>
                    <td><label for="lName">lastName</label></td>
                    <td><input type="text" name="lName" id="lName" value="<%=rsStaff.getString(3)%>"></td>
                </tr>
                <tr>
                    <td><label for="email">email</label></td>
                    <td><input type="email" name="email" id="email" value="<%=rsStaff.getString(4)%>"></td>
                </tr>
                <tr>
                    <td><label for="phone">phone</label></td>
                    <td><input type="number" name="phone" id="phone" value="<%=rsStaff.getString(5)%>"></td>
                </tr>
                <tr>
                    <td>active</td>
                    <td>
                        <input type="radio" name="active" value="1" 
                        <%=(rsStaff.getInt(6)==1 ? "Checked":"")%> >Active
                        <input type="radio" name="active" value="0"
                        <%=(rsStaff.getInt(6)==0 ? "Checked":"")%>>Deactive
                    </td>
                </tr>
                <tr>
                    <td>Store ID</td>
                    <td><select name="storeID">
                            <%while(rsStore.next()){%>
                            <option value="<%=rsStore.getInt(1)%>" <%=(rsStaff.getInt(7)==rsStore.getInt(1) ? "selected":"")%>><%=rsStore.getString(2)%></option>
                            <%}%>
                        </select></td>
                </tr>
                <tr>
                    <td>ManagerID</td>
                    <td><select name="managerID">
                           <%while(rsManager.next()){%>
                            <option value="<%=rsManager.getInt(1)%>" <%=(rsStaff.getInt(8)==rsManager.getInt(1) ? "selected":"")%> ><%=rsManager.getString(2)%></option>
                            <%}%>
                            
                        </select></td>
                </tr>
            </table>
            <input type="submit" name="submit">
            <input type="reset" value="reset">
        </form>
        <%}%>
    </body>
</html>
