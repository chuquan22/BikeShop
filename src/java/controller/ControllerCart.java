/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Cart;
import entity.Customers;
import entity.Order;
import entity.OrderItems;
import entity.Products;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DAOOrder;
import model.DAOOrderItem;
import model.DAOProducts;

/**
 *
 * @author Dell
 */
@WebServlet(name = "ControllerCart", urlPatterns = {"/ControllerCart"})
public class ControllerCart extends HttpServlet {

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
        HttpSession session = request.getSession();
        java.util.Enumeration em = session.getAttributeNames();
        if (service == null) {
            service = "showCart";
        }
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            DAOProducts dao = new DAOProducts();
            if (service.equals("addToCart")) {
                String productID = request.getParameter("productID");
                Cart cart = (Cart) session.getAttribute(productID);
                ResultSet rs = dao.getData("select * from products where product_id=" + productID);
                ResultSet rs1 = dao.getData("select distinct list_price from order_items where product_id=" + productID);
                try {
                    if (rs.next()) {
                        Products p = new Products(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getString(5), rs.getString(6));
                        if (cart == null) {
                            Cart c = new Cart();
                            c.setP(p);
                            c.setQuantity(1);
                            if (rs1.next()) {
                                c.setPrice(rs1.getDouble(1));
                            } else {
                                c.setPrice(p.getListPrice());
                            }
                            session.setAttribute(productID, c);
                        } else {
                            cart.setQuantity(cart.getQuantity() + 1);
                            session.setAttribute(productID, cart);
                        }
                    }
                    response.sendRedirect("ControllerShop");

                } catch (SQLException ex) {
                    Logger.getLogger(ControllerProduct.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (service.equals("showCart")) {
                

                while (em.hasMoreElements()) {
                    String key = em.nextElement().toString();
                    if (key.equals("userName")) {
                        Customers cus = (Customers) session.getAttribute(key);
                        int RollNumber = cus.getCustomerID();
                        String fullName = cus.getFirstName() + " " + cus.getLastName();
                        String userName = cus.getUserName();
                        request.setAttribute("number", RollNumber);
                        request.setAttribute("name", fullName);
                        request.setAttribute("userName", userName);
                    }
                }
                String productID = request.getParameter("productID");
                ResultSet rs = dao.getData("select * from products where product_id=" + productID);
                ResultSet rs1 = dao.getData("select distinct list_price from order_items where product_id=" + productID);

                Cart cart = (Cart) session.getAttribute(productID);

                try {
                    if (rs.next()) {
                        Products p = new Products(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getString(5), rs.getString(6));
                        request.setAttribute("Product", p);
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(ControllerProduct.class.getName()).log(Level.SEVERE, null, ex);
                }

                RequestDispatcher dispath = request.getRequestDispatcher("showCart.jsp");
                // pre data for jsp

                request.setAttribute("rsOrderItem", rs1);
                request.setAttribute("productID", productID);
                //run
                dispath.forward(request, response);
            }

            if (service.equals("addOrderInDB")) {
                int oldOrderID = 0;
                int n = 0;
                String date = "2022-03-18";

                ResultSet rs2 = dao.getData("select Max(order_id) from orders");
                if (rs2.next()) {
                    oldOrderID = rs2.getInt(1);
                }

                while (em.hasMoreElements()) {
                    String key = em.nextElement().toString();
                    if (key.equals("userName")) {
                        DAOOrder dao2 = new DAOOrder();
                        Customers cus = (Customers) session.getAttribute(key);
                        n = dao2.addOrder(new Order(oldOrderID + 1, cus.getCustomerID(), 1, date, date, date, 2, 5));
                    }
                }
                if (n == 0) {
                    response.sendRedirect("login.jsp");
                } else {
                    response.sendRedirect("ControllerCart?s=addCartInDB");
                }
            }

            if (service.equals("addCartInDB")) {
                Random rand = new Random();
                int staffid = rand.nextInt(10) + 1;
                double discount = rand.nextDouble() + 0.01;
                String date = "2022-08-12";
                int n = 0;
                int oldOrderID = 0;
                int itemid = 0;

                ResultSet rs = dao.getData("select Max(order_id) from orders");
                if (rs.next()) {
                    oldOrderID = rs.getInt(1);
                }

                while (em.hasMoreElements()) {
                    String key = em.nextElement().toString();
                    if (!key.equals("userName")) {
                        itemid++;
                        Cart cart = (Cart) session.getAttribute(key);
                        DAOOrderItem dao1 = new DAOOrderItem();
                        n = 1;
                        int n1 = dao1.addOrderItem(new OrderItems(oldOrderID, itemid, cart.getP().getProductID(), cart.getQuantity(), cart.getPrice(), discount));
                    }
                }
                if (n == 1) {
                    session.invalidate();
                    response.sendRedirect("checkOut.jsp");
                } else {
                    response.sendRedirect("ControllerCart?s=showCart");
                }

            }
            if (service.equals("remove")) {
                String id = request.getParameter("productID");
                session.removeAttribute(id);
                response.sendRedirect("ControllerCart");
            }
            if (service.equals("removeAll")) {
                while (em.hasMoreElements()) {
                    String key = em.nextElement().toString();
                    if (!key.equals("userName")) {
                        session.removeAttribute(key);
                    }
                }
                response.sendRedirect("ControllerCart");
            }
            if (service.equals("updateQuantity")) {
                String productid = request.getParameter("productID");
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                while (em.hasMoreElements()) {
                    String key = em.nextElement().toString();
                    Cart cart = (Cart) session.getAttribute(productid);
                    if (cart.equals(productid)) {
                        cart.setQuantity(quantity);
                    }
                }
                response.sendRedirect("ControllerCart");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ControllerCart.class.getName()).log(Level.SEVERE, null, ex);
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
