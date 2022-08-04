<%-- 
    Document   : displayOrderItem
    Created on : Feb 12, 2022, 8:52:59 PM
    Author     : Dell
--%>

<%@page import="entity.OrderItems"%>
<%@page import="java.util.Vector"%>
<%@page import="model.DAOOrderItem"%>
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
           //DAOOrderItem dao = new DAOOrderItem();
           Vector<OrderItems> vector = (Vector<OrderItems>)request.getAttribute("orderItemList");
           String titleTable = (String)request.getAttribute("titleTable");
        %>
         <table border="1">
             <caption><%=titleTable%></caption>
            <thead>
                <tr>
                    <th>order_id</th>
                    <th>item_id</th>
                    <th>product_id</th>
                    <th>quantity</th>
                    <th>list_price</th>
                    <th>discount</th>
                    <th>update</th>
                    <th>delete</th>
                </tr>
            </thead>
            <tbody>
                <% for (OrderItems oI : vector) { %>
                <tr>
                    <td><%=oI.getOrderID() %></td>
                    <td><%=oI.getItemID() %></td>
                    <td><%=oI.getProductID() %></td>
                    <td><%=oI.getQuantity() %></td>
                    <td><%=oI.getListPrice() %></td>
                    <td><%=oI.getDiscount() %></td>
                    <td><a href="ControllerOrderItem?s=updateOrderItem&orderID=<%=oI.getOrderID() %> &itemID= <%=oI.getItemID() %> ">update</a></td>
                    <td><a href="ControllerOrderItem?s=deleteOrderItem&orderID=<%=oI.getOrderID() %> &itemID= <%=oI.getItemID() %> ">delete</a></td>
                </tr>
                <%}%>
            </tbody>
        </table>
    </body>
</html>
