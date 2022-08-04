/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Customers;
import entity.Products;
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
import javax.servlet.http.HttpSession;
import model.DAOCustomers;
import model.DAOProducts;

/**
 *
 * @author Dell
 */
@WebServlet(name = "ControllerShop", urlPatterns = {"/ControllerShop"})
public class ControllerShop extends HttpServlet {

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
            service = "displayShop";
        }
        HttpSession session = request.getSession();
        DAOProducts dao = new DAOProducts();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            if (service.equals("search")) {
                String search = request.getParameter("search");
                ResultSet rs = dao.getData("select distinct category_name from products");
                Vector<Products> vector = dao.ListAll("select * from products where product_name like '%" + search + "%'");

                java.util.Enumeration em = session.getAttributeNames();

                while (em.hasMoreElements()) {
                    String key = em.nextElement().toString();
                    if (key.equals("userName")) {
                        Customers cus = (Customers) session.getAttribute(key);
                        request.setAttribute("nameLogin", cus.getUserName());
                        request.setAttribute("fullName", cus.getFirstName() + cus.getLastName());
                        request.setAttribute("CusID", cus.getCustomerID());
                    }
                }

                RequestDispatcher dispath = request.getRequestDispatcher("index.jsp");
                // pre data for jsp
                request.setAttribute("productList", vector);
                request.setAttribute("rsCategory", rs);

                //run
                dispath.forward(request, response);
            }
            if (service.equals("searchByPrice")) {
                int quantity = 0;
                String priceFrom = request.getParameter("priceFrom");
                String priceTo = request.getParameter("priceTo");

                ResultSet rs = dao.getData("select distinct category_name from products");
                int Pricefrom = Integer.parseInt(priceFrom);
                int Priceto = Integer.parseInt(priceTo);

                Vector<Products> vector = dao.searchByPrice(Pricefrom, Priceto);
                java.util.Enumeration em = session.getAttributeNames();

                while (em.hasMoreElements()) {
                    String key = em.nextElement().toString();
                    if (key.equals("userName")) {
                        Customers cus = (Customers) session.getAttribute(key);
                        request.setAttribute("nameLogin", cus.getUserName());
                        request.setAttribute("fullName", cus.getFirstName() + cus.getLastName());
                        request.setAttribute("CusID", cus.getCustomerID());
                    }else{
                        quantity++;
                    }
                }
                RequestDispatcher dispath = request.getRequestDispatcher("index.jsp");
                request.setAttribute("productList", vector);
                request.setAttribute("rsCategory", rs);
                request.setAttribute("quantity", quantity);
                dispath.forward(request, response);
            }
            if (service.equals("category")) {
                int quantity = 0;
                String category = request.getParameter("categoryName");
                ResultSet rs = dao.getData("select distinct category_name from products");
                Vector<Products> vector = dao.ListAll("select * from products where category_name = '" + category + "'");
                RequestDispatcher dispath = request.getRequestDispatcher("index.jsp");

                java.util.Enumeration em = session.getAttributeNames();

                while (em.hasMoreElements()) {
                    String key = em.nextElement().toString();
                    if (key.equals("userName")) {
                        Customers cus = (Customers) session.getAttribute(key);
                        request.setAttribute("nameLogin", cus.getUserName());
                        request.setAttribute("fullName", cus.getFirstName() + cus.getLastName());
                        request.setAttribute("CusID", cus.getCustomerID());
                    }else{
                        quantity++;
                    }
                }
                // pre data for jsp
                request.setAttribute("productList", vector);
                request.setAttribute("rsCategory", rs);
                request.setAttribute("quantity", quantity);
                //run
                dispath.forward(request, response);
            }
            if (service.equals("displayShop")) {
                 int quantity = 0;
                java.util.Enumeration em = session.getAttributeNames();

                while (em.hasMoreElements()) {
                    String key = em.nextElement().toString();
                    if (key.equals("userName")) {
                        Customers cus = (Customers) session.getAttribute(key);
                        request.setAttribute("nameLogin", cus.getUserName());
                        request.setAttribute("fullName", cus.getFirstName() + cus.getLastName());
                        request.setAttribute("CusID", cus.getCustomerID());
                    }else{
                        quantity++;
                    }
                }

                Vector<Products> vector = dao.ListAll("select * from products");
                ResultSet rs = dao.getData("select distinct category_name from products");
                // get data from model

                RequestDispatcher dispath = request.getRequestDispatcher("index.jsp");
                // pre data for jsp
                request.setAttribute("productList", vector);
                request.setAttribute("rsCategory", rs);
                request.setAttribute("quantity", quantity);
                // request.setAttribute("cartNumber", quantity);
                //run
                dispath.forward(request, response);
            }
            if (service.equals("showOrder")) {
                Customers cus = (Customers) session.getAttribute("userName");
                if (cus == null) {
                    response.sendRedirect("login.jsp");
                } else {
                    
                    request.setAttribute("Customer", cus);
                    ResultSet rs1 = dao.getData("select Distinct state from customers");
                    ResultSet rs2 = dao.getData("select Distinct city from customers");
                    ResultSet rs3 = dao.getData("select Distinct zip_code from customers");

                    request.setAttribute("rsState", rs1);
                    request.setAttribute("rscity", rs2);
                    request.setAttribute("rsZipCode", rs3);
                    RequestDispatcher dispath = request.getRequestDispatcher("showOrder.jsp");
                    dispath.forward(request, response);
                }
            }
            if (service.equals("updateCustomer")) {
                DAOCustomers dao1 = new DAOCustomers();
                String id = request.getParameter("id");
                String fName = request.getParameter("fName");
                String lName = request.getParameter("lName");
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                String street = request.getParameter("street");
                String city = request.getParameter("city");
                String state = request.getParameter("state");
                String zipCode = request.getParameter("zipCode");

                java.util.Enumeration em = session.getAttributeNames();
                while (em.hasMoreElements()) {
                    String key = em.nextElement().toString();
                    if (key.equals("userName")) {
                        Customers cus = (Customers) session.getAttribute(key);
                        Customers customer = new Customers(cus.getCustomerID(), fName, lName, phone, email, street, city, state, zipCode);
                        int n = dao1.updateCustomer(customer);
                        request.setAttribute("Customer", customer);
                    }
                }

                ResultSet rs1 = dao.getData("select Distinct state from customers");
                ResultSet rs2 = dao.getData("select Distinct city from customers");
                ResultSet rs3 = dao.getData("select Distinct zip_code from customers");

                request.setAttribute("rsState", rs1);
                request.setAttribute("rscity", rs2);
                request.setAttribute("rsZipCode", rs3);
                RequestDispatcher dispath = request.getRequestDispatcher("showOrder.jsp");
                dispath.forward(request, response);
            }
            if (service.equals("logout")) {
                java.util.Enumeration em = session.getAttributeNames();
                int check = 0;
                while (em.hasMoreElements()) {
                    String key = em.nextElement().toString();
                    if (!key.equals("userName")) {
                        check = 1;
                    }
                }
                if (check == 0) {
                    session.invalidate();
                    response.sendRedirect("ControllerShop");
                } else {
                    response.sendRedirect("ControllerCart?s=addOrderInDB");
                }
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
