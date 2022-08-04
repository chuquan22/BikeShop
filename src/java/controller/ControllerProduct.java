/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Customers;
import entity.HistoryManage;
import entity.Products;
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
import model.DAOProducts;

/**
 *
 * @author Dell
 */
@WebServlet(name = "ControllerProduct", urlPatterns = {"/ControllerProduct"})
public class ControllerProduct extends HttpServlet {

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
            service = "displayAllProduct";
        }
        DAOProducts dao = new DAOProducts();
        try (PrintWriter out = response.getWriter()) {
            if (service.equals("search")) {
                String search = request.getParameter("search");
                ResultSet rs = dao.getData("select distinct category_name from products");
                Vector<Products> vector = dao.ListAll("select * from products where product_name like '%" + search + "%'");
                RequestDispatcher dispath = request.getRequestDispatcher("index.jsp");
                // pre data for jsp
                request.setAttribute("productList", vector);
                request.setAttribute("rsCategory", rs);

                //run
                dispath.forward(request, response);
            }
            if (service.equals("category")) {
                String category = request.getParameter("categoryName");
                ResultSet rs = dao.getData("select distinct category_name from products");
                Vector<Products> vector = dao.ListAll("select * from products where category_name = '" + category + "'");
                RequestDispatcher dispath = request.getRequestDispatcher("index.jsp");
                // pre data for jsp
                request.setAttribute("productList", vector);
                request.setAttribute("rsCategory", rs);

                //run
                dispath.forward(request, response);
            }
            if (service.equals("insertProduct")) {
                int proID = 0;
                ResultSet rs = dao.getData("select max(product_id) from products");
                if (rs.next()) {
                    proID = rs.getInt(1);
                }

                String pName = request.getParameter("pName");
                String modelYear = request.getParameter("modelYear");
                String listPrice = request.getParameter("listPrice");
                String brandName = request.getParameter("brandName");
                String categoryName = request.getParameter("categoryName");

                if (pName == null || pName.equals("") || modelYear == null || modelYear.equals("") || listPrice == null || listPrice.equals("")) {
                    request.setAttribute("error", "Please enter all data in form");
                    request.getRequestDispatcher("insertProduct.jsp").forward(request, response);
                } else {
                    int yearNumber = Integer.parseInt(modelYear);
                    double priceNumber = Double.parseDouble(listPrice);

                    int n = dao.addProducts(new Products(proID + 1, pName, yearNumber, priceNumber, brandName, categoryName));
                    if (n > 0) {
                        DAOHistory dao1 = new DAOHistory();
                        HttpSession session = request.getSession();
                        java.util.Enumeration em = session.getAttributeNames();
                        while (em.hasMoreElements()) {
                            String key = em.nextElement().toString();
                            if (key.equals("admin")) {
                                staffs staff = (staffs) session.getAttribute(key);
                                String content = "Was insert product "+pName+" in shop";
                                int m = dao1.addHistoryManage(new HistoryManage(staff.getFirstName() + " "+ staff.getLastName(), content));
                            }
                        }
                    }
                    response.sendRedirect("ControllerAdmin?s=productManager");
                }
            }
            if (service.equals("displayAllProduct")) {
                HttpSession session = request.getSession();
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
                Vector<Products> vector = dao.ListAll("select * from products");
                ResultSet rs = dao.getData("select distinct category_name from products");
                // get data from model

                RequestDispatcher dispath = request.getRequestDispatcher("index.jsp");
                // pre data for jsp
                request.setAttribute("productList", vector);
                request.setAttribute("rsCategory", rs);

                //run
                dispath.forward(request, response);
            }
            if (service.equals("updateProduct")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String productID = request.getParameter("productID");
                    ResultSet rs = dao.getData("select * from products where product_id=" + productID);
                    ResultSet rs1 = dao.getData("select Distinct brand_name from products");
                    ResultSet rs2 = dao.getData("select Distinct category_name from products");

                    request.setAttribute("rsProduct", rs);
                    request.setAttribute("rsBrand", rs1);
                    request.setAttribute("rsCategory", rs2);

                    RequestDispatcher dispath = request.getRequestDispatcher("/JSP/updateProduct.jsp");
                    dispath.forward(request, response);

                } else {
                    String id = request.getParameter("id");
                    String pName = request.getParameter("pName");
                    String modelYear = request.getParameter("modelYear");
                    String listPrice = request.getParameter("listPrice");
                    String brandName = request.getParameter("brandName");
                    String categoryName = request.getParameter("categoryName");

                    int idNumber = Integer.parseInt(id);
                    int yearNumber = Integer.parseInt(modelYear);
                    double priceNumber = Double.parseDouble(listPrice);

                    int n = dao.updateProduct(new Products(idNumber, pName, yearNumber, priceNumber, brandName, categoryName));
                    if (n > 0) {
                        DAOHistory dao1 = new DAOHistory();
                        HttpSession session = request.getSession();
                        java.util.Enumeration em = session.getAttributeNames();
                        while (em.hasMoreElements()) {
                            String key = em.nextElement().toString();
                            if (key.equals("admin")) {
                                staffs staff = (staffs) session.getAttribute(key);
                                String content = "Was update product "+pName+" in shop";
                                int m = dao1.addHistoryManage(new HistoryManage(staff.getFirstName() + " "+ staff.getLastName(), content));
                            }
                        }
                    }
                    response.sendRedirect("ControllerAdmin?s=productManager");
                }
            }
            if (service.equals("deleteProduct")) {
                String productID = request.getParameter("productID");
                int n = dao.removeProduct(productID);
                if (n == 0) {
                    request.setAttribute("message", "can't delete this product because product exist in the stock and order");
                } else {
                    request.setAttribute("message", "delete product successfull");
                }
                Vector<Products> vector = dao.ListAll("select * from products");
                // get data from model
                if (n > 0) {
                        DAOHistory dao1 = new DAOHistory();
                        HttpSession session = request.getSession();
                        java.util.Enumeration em = session.getAttributeNames();
                        while (em.hasMoreElements()) {
                            String key = em.nextElement().toString();
                            if (key.equals("admin")) {
                                staffs staff = (staffs) session.getAttribute(key);
                                String content = "Was delete product have id "+productID+" in shop";
                                int m = dao1.addHistoryManage(new HistoryManage(staff.getFirstName() + " "+ staff.getLastName(), content));
                            }
                        }
                    }
                RequestDispatcher dispath = request.getRequestDispatcher("admin.jsp");
                // pre data for jsp
                request.setAttribute("productList", vector);

                //run
                dispath.forward(request, response);
            }
            if (service.equals("searchByID")) {
                String productID = request.getParameter("search");
                Vector<Products> vector = dao.ListAll("select * from products where product_id = " + productID);
                // get data from model

                RequestDispatcher dispath = request.getRequestDispatcher("admin.jsp");
                // pre data for jsp
                request.setAttribute("productList", vector);

                //run
                dispath.forward(request, response);
            }
            if (service.equals("searchByName")) {
                String productName = request.getParameter("search");
                Vector<Products> vector = dao.ListAll("select * from products where product_name like '%" + productName + "%' ");
                // get data from model

                RequestDispatcher dispath = request.getRequestDispatcher("admin.jsp");
                // pre data for jsp
                request.setAttribute("productList", vector);

                //run
                dispath.forward(request, response);
            }
            if (service.equals("searchByPrice")) {
                String priceFrom = request.getParameter("priceFrom");
                String priceTo = request.getParameter("priceTo");

                int Pricefrom = Integer.parseInt(priceFrom);
                int Priceto = Integer.parseInt(priceTo);

                Vector<Products> vector = dao.searchByPrice(Pricefrom, Priceto);
                // get data from model

                RequestDispatcher dispath = request.getRequestDispatcher("admin.jsp");
                // pre data for jsp
                request.setAttribute("productList", vector);

                //run
                dispath.forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControllerProduct.class.getName()).log(Level.SEVERE, null, ex);
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
