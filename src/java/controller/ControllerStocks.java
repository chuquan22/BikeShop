/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.stocks;
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
import model.DAOStock;

/**
 *
 * @author Dell
 */
@WebServlet(name = "ControllerStocks", urlPatterns = {"/ControllerStocks"})
public class ControllerStocks extends HttpServlet {

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
            service = "displayAllStock";
        }
        DAOStock dao = new DAOStock();
        try (PrintWriter out = response.getWriter()) {
            if (service.equals("insertStock")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    ResultSet rs1 = dao.getData("select * from stores");
                    ResultSet rs2 = dao.getData("select * from products");

                    RequestDispatcher dispath = request.getRequestDispatcher("/JSP/insertStock.jsp");

                    request.setAttribute("rsStore", rs1);
                    request.setAttribute("rsProduct", rs2);

                    dispath.forward(request, response);
                } else {
                    String StoreID = request.getParameter("sid");
                    String ProductID = request.getParameter("pid");
                    String Quantity = request.getParameter("quantity");

                    int sIDNumber = Integer.parseInt(StoreID);
                    int pIDNumber = Integer.parseInt(ProductID);
                    int QuantityNumber = Integer.parseInt(Quantity);

                    int n = dao.addStock(new stocks(sIDNumber, pIDNumber, QuantityNumber));
                    response.sendRedirect("ControllerStocks");
                }
            }
            if (service.equals("displayAllStock")) {
                Vector<stocks> vector = dao.ListAll("select * from stocks");
                String titlepage = "Stock manager";
                String titleTable = "Stock List";
                RequestDispatcher dispath = request.getRequestDispatcher("/JSP/displayStock.jsp");
                request.setAttribute("stockList", vector);
                request.setAttribute("titlepage", titlepage);
                request.setAttribute("titleTable", titleTable);
                dispath.forward(request, response);
            }
            if (service.equals("updateStock")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String storeID = request.getParameter("storeID");
                    String productID = request.getParameter("productID");
                    ResultSet rs = dao.getData("select * from stocks where store_id=" + storeID + " and product_id=" + productID);
                    ResultSet rs1 = dao.getData("select * from stores");
                    ResultSet rs2 = dao.getData("select * from products");

                    RequestDispatcher dispath = request.getRequestDispatcher("/JSP/updateStock.jsp");

                    request.setAttribute("rsStock", rs);
                    request.setAttribute("rsStore", rs1);
                    request.setAttribute("rsProduct", rs2);

                    dispath.forward(request, response);
                } else {
                    String StoreID = request.getParameter("sid");
                    String ProductID = request.getParameter("pid");
                    String Quantity = request.getParameter("quantity");

                    int sIDNumber = Integer.parseInt(StoreID);
                    int pIDNumber = Integer.parseInt(ProductID);
                    int QuantityNumber = Integer.parseInt(Quantity);

                    int n = dao.updateStock(new stocks(sIDNumber, pIDNumber, QuantityNumber));
                    response.sendRedirect("ControllerStocks");
                }
            }
            if (service.equals("deleteStock")) {
                String storeID = request.getParameter("storeID");
                String productID = request.getParameter("productID");
                int n = dao.removeStock(storeID, productID);
                response.sendRedirect("ControllerStocks");
            }
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ControllerStocks</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ControllerStocks at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
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
