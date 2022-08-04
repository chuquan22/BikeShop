/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Bill;
import entity.Customers;
import entity.HistoryManage;
import entity.Order;
import entity.Products;
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
import model.DAOCustomers;
import model.DAOHistory;
import model.DAOOrder;
import model.DAOOrderItem;
import model.DAOProducts;

/**
 *
 * @author Dell
 */
@WebServlet(name = "ControllerAdmin", urlPatterns = {"/ControllerAdmin"})
public class ControllerAdmin extends HttpServlet {

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

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            if (service == null) {
                response.sendRedirect("admin.jsp");
            }
            if (service.equals("productManager")) {
                DAOProducts dao = new DAOProducts();
                Vector<Products> vector = dao.ListAll("select * from products");
                // get data from model

                RequestDispatcher dispath = request.getRequestDispatcher("admin.jsp");
                // pre data for jsp
                request.setAttribute("productList", vector);

                //run
                dispath.forward(request, response);

            }
            if (service.equals("customerManager")) {
                DAOCustomers dao = new DAOCustomers();
                Vector<Customers> vector = dao.ListAll("select * from customers");
                // get data from model

                RequestDispatcher dispath = request.getRequestDispatcher("admin.jsp");
                // pre data for jsp
                request.setAttribute("customerList", vector);

                //run
                dispath.forward(request, response);
            }
            if (service.equals("orderManager")) {
                DAOOrderItem dao = new DAOOrderItem();
                double total = 0;
                Vector<Bill> vector = new Vector();
                ResultSet rs = dao.getData("select * from orders o left join customers c on o.customer_id = c.customer_id \n"
                        + "order by o.order_id ");
                while (rs.next()) {
                    int bill_id = rs.getInt("order_id");
                    String customer_name = rs.getString("first_name") + " " + rs.getString("last_name");
                    String date = rs.getString("order_date");
                    ResultSet rs1 = dao.getData("select sum(oi.list_price*oi.quantity) from order_items oi where oi.order_id =" + bill_id);
                    if (rs1.next()) {
                        total = rs1.getDouble(1);
                    }
                    int status = rs.getInt("order_status");
                    vector.add(new Bill(bill_id, customer_name, date, total, status));
                }
                ResultSet rs1 = dao.getData("select distinct order_status from orders order by order_status");
                Vector<Order> vector1 = new Vector();
                while (rs1.next()) {
                    vector1.add(new Order(rs1.getInt(1)));
                    request.setAttribute("vectorOrder", vector1);
                }
                int numberNew = 0;
                int numberProcess = 0;
                int numberDone = 0;
                int numberNull = 0;
                for (Bill o : vector) {
                    if(o.getStatus()==1){
                        numberNew++;
                    }else if(o.getStatus()==2){
                        numberProcess++;
                    }else if(o.getStatus()==3){
                        numberDone++;
                    }else{
                        numberNull++;
                    }
                }
                request.setAttribute("new", numberNew);
                request.setAttribute("process", numberProcess);
                request.setAttribute("done", numberDone);
                request.setAttribute("null1", numberNull);
                
                RequestDispatcher dispath = request.getRequestDispatcher("admin.jsp");
                // pre data for jsp
                request.setAttribute("Bill", vector);
                request.setAttribute("rsOrderStatus", rs1);

                //run
                dispath.forward(request, response);
            }
            if (service.equals("searchByOrderStatus")) {
                String orderStatus = request.getParameter("orderStatus");
                DAOOrderItem dao = new DAOOrderItem();
                double total = 0;
                Vector<Bill> vector = new Vector();
                ResultSet rs = dao.getData("select * from orders o left join customers c on o.customer_id = c.customer_id where o.order_status = " + orderStatus + " \n"
                        + "order by o.order_id ");
                while (rs.next()) {
                    int bill_id = rs.getInt("order_id");
                    String customer_name = rs.getString("first_name") + " " + rs.getString("last_name");
                    String date = rs.getString("order_date");
                    ResultSet rs1 = dao.getData("select sum(oi.list_price*oi.quantity) from order_items oi where oi.order_id =" + bill_id);
                    if (rs1.next()) {
                        total = rs1.getDouble(1);
                    }
                    int status = rs.getInt("order_status");
                    vector.add(new Bill(bill_id, customer_name, date, total, status));
                }

                RequestDispatcher dispath = request.getRequestDispatcher("admin.jsp");
                // pre data for jsp
                request.setAttribute("Bill", vector);

                //run
                dispath.forward(request, response);
            }
            if (service.equals("history")) {
                DAOHistory dao1 = new DAOHistory();
                Vector<HistoryManage> vector = dao1.ListAll("select * from HistoryManager");
                request.setAttribute("history", vector);
                request.getRequestDispatcher("HistoryManager.jsp").forward(request, response);
            }

            if (service.equals("logout")) {
                HttpSession session = request.getSession();
                session.invalidate();
                response.sendRedirect("ControllerShop");
            }
            if (service.equals("deletOrder")) {
                int id = Integer.parseInt(request.getParameter("id"));
                String Oid = request.getParameter("id");
                DAOOrderItem dao4 = new DAOOrderItem();
                int m = dao4.removeOrderItem(Oid);
                DAOOrder dao3 = new DAOOrder();
                int n = dao3.removeOrder(id);
                if (n > 0 && m > 0) {
                    request.getRequestDispatcher("ControllerAdmin?s=orderManager").forward(request, response);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
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
