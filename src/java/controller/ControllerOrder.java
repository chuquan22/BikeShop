/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.HistoryManage;
import entity.Order;
import entity.orderDetail;
import entity.staffs;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DAOHistory;
import model.DAOOrder;

/**
 *
 * @author Dell
 */
@WebServlet(name = "ControllerOrder", urlPatterns = {"/ControllerOrder"})
public class ControllerOrder extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String service = request.getParameter("s");
        if (service == null) {
            //service = "displayAllOrder";
            service = "displayOrderDetail";
        }
        DAOOrder dao = new DAOOrder();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            if (service.equals("displayAllOrder")) {
//                out.print("<table border=\"1\">\n"
//                        + "            <caption>list of orders</caption>\n"
//                        + "            <thead>\n"
//                        + "                <tr>\n"
//                        + "                    <th>order_id</th>\n"
//                        + "                    <th>customer_id</th>\n"
//                        + "                    <th>order_status</th>\n"
//                        + "                    <th>order_date</th>\n"
//                        + "                    <th>required_date</th>\n"
//                        + "                    <th>shipped_date</th>\n"
//                        + "                    <th>store_id</th>\n"
//                        + "                    <th>staff_id</th>\n"
//                        + "                    <th>update</th>\n"
//                        + "                    <th>delete</th>\n"
//                        + "                </tr>\n"
//                        + "            </thead>\n"
//                        + "            <tbody>");
                Vector<Order> vector = dao.ListAll("select * from orders");
//                for (Order o : vector) {
//                    out.print("<tr>\n"
//                            + "                    <td>" + o.getOrderID() + "</td>\n"
//                            + "                    <td>" + o.getCustomerID() + "</td>\n"
//                            + "                    <td>" + o.getOrderStatus() + "</td>\n"
//                            + "                    <td>" + o.getOrderDate() + "</td>\n"
//                            + "                    <td>" + o.getRequiredDate() + "</td>\n"
//                            + "                    <td>" + o.getShippedDate() + "</td>\n"
//                            + "                    <td>" + o.getStoreID() + "</td>\n"
//                            + "                    <td>" + o.getStaffID() + "</td>\n"
//                            + "                    <td><a href=\"ControllerOrder?s=updateOrder&orderID=" + o.getOrderID() + "\">update</a></td>\n"
//                            + "                    <td><a href=\"ControllerOrder?s=deleteOrder&orderID=" + o.getOrderID() + "\">delete</a></td>\n"
//                            + "                </tr>");
//                }
//                out.print("</tbody>\n"
//                        + "        </table>");

                String titlepage = "Order manager";
                String titleTable = "Order List";
                RequestDispatcher dispath = request.getRequestDispatcher("/JSP/displayOrder.jsp");

                request.setAttribute("orderList", vector);
                request.setAttribute("titlepage", titlepage);
                request.setAttribute("titleTable", titleTable);
                dispath.forward(request, response);
            }
            if (service.equals("updateOrder")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String orderID = request.getParameter("orderID");
                    ResultSet rs = dao.getData("select * from orders where order_id=" + orderID);
                    ResultSet rs1 = dao.getData("select * from customers");
                    ResultSet rs2 = dao.getData("select * from stores");
                    ResultSet rs3 = dao.getData("select * from staffs");
                    ResultSet rs4 = dao.getData("select Distinct order_status from orders");

                    RequestDispatcher dispath = request.getRequestDispatcher("/JSP/updateOrder.jsp");

                    request.setAttribute("rsOrder", rs);
                    request.setAttribute("rsCus", rs1);
                    request.setAttribute("rsStore", rs2);
                    request.setAttribute("rsStaff", rs3);
                    request.setAttribute("rsOrders", rs4);

                    dispath.forward(request, response);
                } else {
                    String orderID = request.getParameter("id");
                    String CustomerID = request.getParameter("CustomerID");
                    String OrderStatus = request.getParameter("OrderStatus");
                    String orderDate = request.getParameter("orderDate");
                    String requiredDate = request.getParameter("requiredDate");
                    String shippedDate = request.getParameter("shippedDate");
                    String storeID = request.getParameter("storeID");
                    String staffID = request.getParameter("staffID");

                    if (orderID == null || orderID.equals("")) {
                        out.print("id null");
                    } else if (CustomerID == null || orderID.equals("")) {
                        out.print("CustomerID null");
                    } else if (OrderStatus == null || OrderStatus.equals("")) {
                        out.print("OrderStatus null");
                    } else if (storeID == null || storeID.equals("")) {
                        out.print("storeID null");
                    } else if (staffID == null || staffID.equals("")) {
                        out.print("staffID null");
                    }

                    int orderIDNumber = Integer.parseInt(orderID);
                    int cusNumber = Integer.parseInt(CustomerID);
                    int storeNumber = Integer.parseInt(storeID);
                    int staffNumber = Integer.parseInt(staffID);
                    int orderNumber = Integer.parseInt(OrderStatus);

                    int n = dao.updateOrder(new Order(orderIDNumber, cusNumber, orderNumber, orderDate, requiredDate, shippedDate, storeNumber, staffNumber));
                    response.sendRedirect("ControllerOrder");
                }
            }

//            if (service.equals("deleteOrder")) {
//                String orderID = request.getParameter("orderID");
//                int n = dao.removeOrder(orderID);
//                response.sendRedirect("ControllerOrder");
//            }

            if (service.equals("orderDetail")) {
                String orderID = request.getParameter("orderID");
                
                ResultSet rs1 = dao.getData("select distinct order_status from orders order by order_status");
                ResultSet rs2 = dao.getData("select * from orders where order_id = "+ orderID);

                ResultSet rs = dao.getData("select * from orders o \n"
                        + " inner join customers c on o.customer_id = c.customer_id \n"
                        + " inner join stores s on o.store_id = s.store_id\n"
                        + " inner join order_items oi on o.order_id = oi.order_id\n"
                        + " inner join products p on p.product_id = oi.product_id\n"
                        + " where o.order_id = " + orderID);

                Vector<orderDetail> vector = dao.ListDetail("select * from orders o \n"
                        + " inner join customers c on o.customer_id = c.customer_id \n"
                        + " inner join stores s on o.store_id = s.store_id\n"
                        + " inner join order_items oi on o.order_id = oi.order_id\n"
                        + " inner join products p on p.product_id = oi.product_id\n"
                        + " where o.order_id = " + orderID + "\n"
                        + "order by p.product_id");

                request.setAttribute("vector", vector);
                request.setAttribute("rsOrderStatus", rs1);
                request.setAttribute("rsOrder", rs2);
                try {
                    if (rs.next()) {
                        String cusFirst = rs.getString("first_name");
                        String cusLast = rs.getString("last_name");
                        String storeName = rs.getString("store_name");
                        int order_id = rs.getInt("order_id");
                        String oStatus = OrderStatus(rs.getInt("order_status"));
                        String order_date = rs.getString("order_date");

                        request.setAttribute("cusName", cusFirst + " " + cusLast);
                        request.setAttribute("storeName", storeName);
                        request.setAttribute("oID", order_id);
                        request.setAttribute("oStatus", oStatus);
                        request.setAttribute("oDate", order_date);

                        RequestDispatcher dispath = request.getRequestDispatcher("/JSP/orderDetail.jsp");
                        dispath.forward(request, response);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (service.equals("displayOrderDetail")) {
                Vector<orderDetail> vector = dao.OrderList("select * from orders o \n"
                        + " inner join customers c on o.customer_id = c.customer_id \n"
                        + " inner join stores s on o.store_id = s.store_id");

                ResultSet rs = dao.getData("select distinct order_status from orders order by order_status");
                Vector<Integer> vt = new Vector();

                while (rs.next()) {
                    vt.add(rs.getInt(1));

                }

                String titlepage = "Order manager";
                String titleTable = "Order List";
                RequestDispatcher dispath = request.getRequestDispatcher("/JSP/displayOrderDetail.jsp");

                request.setAttribute("vector", vector);
                request.setAttribute("OrderStatus", vt);
                request.setAttribute("titlepage", titlepage);
                request.setAttribute("titleTable", titleTable);
                dispath.forward(request, response);
            }
            if (service.equals("updateStatus")) {
                String orderID = request.getParameter("orderID");
                String orderStatus = request.getParameter("orderStatus");

                int n = dao.updateOrderStatus(Integer.parseInt(orderStatus), Integer.parseInt(orderID));
                if (n > 0) {
                        DAOHistory dao1 = new DAOHistory();
                        HttpSession session = request.getSession();
                        java.util.Enumeration em = session.getAttributeNames();
                        while (em.hasMoreElements()) {
                            String key = em.nextElement().toString();
                            if (key.equals("admin")) {
                                staffs staff = (staffs) session.getAttribute(key);
                                String content = "Was update status of Bill have id "+orderID+"";
                                int m = dao1.addHistoryManage(new HistoryManage(staff.getFirstName() + " "+ staff.getLastName(), content));
                            }
                        }
                    }
                response.sendRedirect("ControllerAdmin?s=orderManager");

            }
            if(service.equals("searchByID")){
                String orderID = request.getParameter("search");
                Vector<orderDetail> vector = dao.OrderList("select * from orders o \n"
                        + " inner join customers c on o.customer_id = c.customer_id \n"
                        + " inner join stores s on o.store_id = s.store_id where o.order_id = "+orderID);
                // get data from model
                
                RequestDispatcher dispath = request.getRequestDispatcher("admin.jsp");
                // pre data for jsp
                request.setAttribute("orderDetail", vector);

                //run
                dispath.forward(request, response);
            }
            if(service.equals("searchByName")){
                String cusName = request.getParameter("search");
                Vector<orderDetail> vector = dao.OrderList("select * from orders o \n"
                        + " inner join customers c on o.customer_id = c.customer_id \n"
                        + " inner join stores s on o.store_id = s.store_id where c.first_name like '%"+cusName+"%' or c.last_name like '%"+cusName+"%'");
                // get data from model
                
                RequestDispatcher dispath = request.getRequestDispatcher("admin.jsp");
                // pre data for jsp
                request.setAttribute("orderDetail", vector);

                //run
                dispath.forward(request, response);
            }
            

        } catch (SQLException ex) {
            Logger.getLogger(ControllerOrder.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String OrderStatus(int order_status) {
        if (order_status == 1) {
            return "New";
        } else if (order_status == 2) {
            return "Process";
        } else if (order_status == 3) {
            return "Done";
        } else {
            return "Cencal";
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
