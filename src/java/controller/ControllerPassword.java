/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAOCustomers;

/**
 *
 * @author Dell
 */
@WebServlet(name = "ControllerPassword", urlPatterns = {"/ControllerPassword"})
public class ControllerPassword extends HttpServlet {

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
            DAOCustomers dao = new DAOCustomers();
            String userName = request.getParameter("userName");
            String OldpassWord = request.getParameter("OldpassWord");
            String NewpassWord = request.getParameter("NewpassWord");
            String ReNewpassWord = request.getParameter("ReNewpassWord");

            ResultSet rs = dao.getData("select customer_id from customers where user_name = '" + userName + "' and password = '" + OldpassWord + "'");

            if (userName == null || OldpassWord == null || userName.equals("") || OldpassWord.equals("")
                    || NewpassWord == null || NewpassWord.equals("") || ReNewpassWord == null || ReNewpassWord.equals("")) {
                String error = "UserName, PassWord can not empty";
                request.setAttribute("error", error);
                request.getRequestDispatcher("ChangePassword.jsp").forward(request, response);
            } else if (OldpassWord.equals(NewpassWord)) {
                String error = "PassWord and New PassWord can not equal";
                request.setAttribute("error", error);
                request.getRequestDispatcher("ChangePassword.jsp").forward(request, response);
            } else if (!NewpassWord.equals(ReNewpassWord)) {
                String error = "New PassWord and Re New PassWord must equal";
                request.setAttribute("error", error);
                request.getRequestDispatcher("ChangePassword.jsp").forward(request, response);
            } else if (rs.next()) {
                int n = dao.changePassword(NewpassWord, rs.getInt(1));
                if (n > 0) {
                    request.setAttribute("changePass", "Change password successfull!");
                    request.getRequestDispatcher("ChangePassword.jsp").forward(request, response);
                }
            } else {
                String error = "UserName or Password incorrect";
                request.setAttribute("error", error);
                request.getRequestDispatcher("ChangePassword.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControllerPassword.class.getName()).log(Level.SEVERE, null, ex);
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
