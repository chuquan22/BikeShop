/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.OrderItems;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAOOrderItem;

/**
 *
 * @author Dell
 */
@WebServlet(name = "ControllerOrderItem", urlPatterns = {"/ControllerOrderItem"})
public class ControllerOrderItem extends HttpServlet {

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
            service = "displayAllOrderItem";
        }
        DAOOrderItem dao = new DAOOrderItem();
        try (PrintWriter out = response.getWriter()) {
            if (service.equals("displayAllOrderItem")) {
                Vector<OrderItems> vector = dao.ListAll("select * from order_items");
                String titlepage = "Order Item manager";
                String titleTable = "Order Item List";
                RequestDispatcher dispath = request.getRequestDispatcher("/JSP/displayOrderItem.jsp");

                request.setAttribute("orderItemList", vector);
                request.setAttribute("titlepage", titlepage);
                request.setAttribute("titleTable", titleTable);

                dispath.forward(request, response);
            }
            if (service.equals("updateOrderItem")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String orderID = request.getParameter("orderID");
                    String itemID = request.getParameter("itemID");
                    ResultSet rs = dao.getData("select * from order_items where order_id=" + orderID + " and item_id=" + itemID);
                    ResultSet rs1 = dao.getData("select * from orders");
                    ResultSet rs2 = dao.getData("select * from products");
                    ResultSet rs3 = dao.getData("select distinct item_id from order_items");

                    RequestDispatcher dispath = request.getRequestDispatcher("/JSP/updateOrderItem.jsp");

                    request.setAttribute("rsorderItem", rs);
                    request.setAttribute("rsOrder", rs1);
                    request.setAttribute("rsProduct", rs2);
                    request.setAttribute("rsItem", rs3);

                    dispath.forward(request, response);
                } else {
                    String orderID = request.getParameter("orderID");
                    String itemID = request.getParameter("itemID");
                    String productID = request.getParameter("productID");
                    String quantity = request.getParameter("quantity");
                    String listPrice = request.getParameter("ListPrice");
                    String discount = request.getParameter("Discount");

                    int orderNumber = Integer.parseInt(orderID);
                    int itemNumber = Integer.parseInt(itemID);
                    int productNumber = Integer.parseInt(productID);
                    int Quantity = Integer.parseInt(quantity);
                    double ListPrice = Double.parseDouble(listPrice);
                    double Discount = Double.parseDouble(discount);

                    int n = dao.updateOrderItem(new OrderItems(orderNumber, itemNumber, productNumber, Quantity, ListPrice, Discount));
                    response.sendRedirect("ControllerOrderItem");
                }
            }
            if (service.equals("deleteOrderItem")) {
                String orderID = request.getParameter("orderID");
                String itemID = request.getParameter("itemID");
                int n = dao.removeOrderItem(orderID);
                response.sendRedirect("ControllerOrderItem");
            }
            
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
