/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Customers;
import entity.orderDetail;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DAOOrder;

/**
 *
 * @author Dell
 */
@WebServlet(name = "TransactionHistory", urlPatterns = {"/Transaction"})
public class TransactionHistory extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            Customers cus = (Customers) session.getAttribute("userName");
            if (cus == null) {
                response.sendRedirect("login.jsp");
            } else {
                DAOOrder dao = new DAOOrder();
                Vector<orderDetail> vector = dao.ListDetail("select * from orders o \n"
                        + "join order_items oi on o.order_id = oi.order_id \n"
                        + "join customers c on c.customer_id = o.customer_id\n"
                        + "join products p on p.product_id = oi.product_id\n"
                        + "join stores s on s.store_id = o.store_id\n"
                        + "where o.customer_id = " + cus.getCustomerID());

                request.setAttribute("name", cus.getFirstName() + " " + cus.getLastName());
                request.setAttribute("phone", cus.getPhone());
                request.setAttribute("email", cus.getEmail());
                request.setAttribute("address", cus.getStreet() + ", " + cus.getCity() + ", " + cus.getState());

                request.setAttribute("order", vector);
                request.getRequestDispatcher("Transaction.jsp").forward(request, response);
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
