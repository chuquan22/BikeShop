/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import model.DAOStaff;

/**
 *
 * @author Dell
 */
@WebServlet(name = "ControllerStaff", urlPatterns = {"/ControllerStaff"})
public class ControllerStaff extends HttpServlet {

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
            service = "displayAllStaff";
        }
        try (PrintWriter out = response.getWriter()) {
            DAOStaff dao = new DAOStaff();
            if (service.equals("insertStaff")) {
                
                String id = request.getParameter("id");
                String fName = request.getParameter("fName");
                String lName = request.getParameter("lName");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                String active = request.getParameter("active");
                String storeID = request.getParameter("storeID");
                String managerID = request.getParameter("managerID");
                
                if (id == null || id.equals("")) {
                    out.println("Staff id is not empty");
                } else if (fName == null || fName.equals("")) {
                    out.println("first name is not empty");
                } else if (lName == null || lName.equals("")) {
                    out.println("last name is not empty");
                } else if (email == null || email.equals("")) {
                    out.println("email is not empty");
                }                
                
                int idNumber = Integer.parseInt(id);
                int managerIDNumber = Integer.parseInt(managerID);
                int storeIDNumber = Integer.parseInt(storeID);

//            out.println("<H2>"+id+"<H2>");
//            out.println("<H2>"+fName+"<H2>");
//            out.println("<H2>"+lName+"<H2>");
//            out.println("<H2>"+phone+"<H2>");
//            out.println("<H2>"+email+"<H2>");
//            out.println("<H2>"+active+"<H2>");
//            out.println("<H2>"+storeID+"<H2>");
//            out.println("<H2>"+managerID+"<H2>");
                int n = dao.addStaff(new staffs(idNumber, fName, lName, email, phone, Integer.parseInt(active), storeIDNumber, managerIDNumber));
                response.sendRedirect("ControllerStaff");
//                if (n > 0) {
//                    out.println("inserted");
//                }
            }
            
            if (service.equals("displayAllStaff")) {
//                out.print("<table border=\"1\">\n"
//                        + "            <caption>list of staff</caption>\n"
//                        + "            <thead>\n"
//                        + "                <tr>\n"
//                        + "                    <th>staff_id</th>\n"
//                        + "                    <th>first_name</th>\n"
//                        + "                    <th>last_name</th>\n"
//                        + "                    <th>email</th>\n"
//                        + "                    <th>phone</th>\n"
//                        + "                    <th>active</th>\n"
//                        + "                    <th>store_id</th>\n"
//                        + "                    <th>manager_id</th>\n"
//                        + "                    <th>update</th>\n"
//                        + "                    <th>delete</th>\n"
//                        + "                </tr>\n"
//                        + "            </thead>\n"
//                        + "            <tbody>");
                Vector<staffs> vector = dao.ListAll("select * from staffs");
                // get data from model
                String titlepage = "Staffs manager";
                String titleTable = "Staff List";
                RequestDispatcher dispath = request.getRequestDispatcher("/JSP/displayStaff.jsp");
                // pre data for jsp
                request.setAttribute("staffList", vector);
                request.setAttribute("titlepage", titlepage);
                request.setAttribute("titleTable", titleTable);

                //run
                dispath.forward(request, response);
//                for (staffs obj : vector) {
//                    out.print("<tr>\n"
//                            + "                    <td>" + obj.getStaffID() + "</td>\n"
//                            + "                    <td>" + obj.getFirstName() + "</td>\n"
//                            + "                    <td>" + obj.getLastName() + "</td>\n"
//                            + "                    <td>" + obj.getEmail() + "</td>\n"
//                            + "                    <td>" + obj.getPhone() + "</td>\n"
//                            + "                    <td>" + obj.getActive() + "</td>\n"
//                            + "                    <td>" + obj.getStoreID() + "</td>\n"
//                            + "                    <td>" + obj.getManagerID() + "</td>\n"
//                            + "                    <td><a href=\"ControllerStaff?s=updateStaff&staffID=" + obj.getStaffID() + "\">update</a></td>\n"
//                            + "                    <td><a href=\"ControllerStaff?s=deleteStaff&staffID=" + obj.getStaffID() + "\">delete</a></td>\n"
//                            + "                </tr>");
//                }
//                out.print(" </tbody>\n"
//                        + "        </table>");
            }
            if (service.equals("updateStaff")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    // step1 get data
                    String staffID = request.getParameter("staffID");
                    ResultSet rs = dao.getData("select * from staffs where staff_id=" + staffID);
                    ResultSet rs1 = dao.getData("select * from stores");
                    ResultSet rs2 = dao.getData("select staff_id, (first_name +' '+ last_name) from staffs s where s.staff_id in \n"
                            + "(select manager_id from staffs )");
                    request.setAttribute("rsStaff", rs);
                    request.setAttribute("rsStore", rs1);
                    request.setAttribute("rsManager", rs2);
                    dispath(request, response, "/JSP/updateStaff.jsp");

                    // if (rs.next()) {
//                        out.print("<form action=\"ControllerStaff\" method=\"Post\">\n"
//                                + "       <!-- <form action=\"ControllerStaff?s=insertStaff\" method=\"Post\"> -->\n"
//                                + "            \n"
//                                + "            <p><input type=\"hidden\" name=\"s\" value=\"updateStaff\"></p>\n"
//                                + "            <h3>FORM UPDATE STAFF INFORMATIONS</h3>\n"
//                                + "            <table>\n"
//                                + "                <tr>\n"
//                                + "                    <td><label for=\"id\">StaffID</label></td>\n"
//                                + "                    <td><input type=\"number\" name=\"id\" id=\"id\" value=\"" + rs.getString(1) + "\"></td>\n"
//                                + "                </tr>\n"
//                                + "                <tr>\n"
//                                + "                    <td><label for=\"fName\">firstName</label></td>\n"
//                                + "                    <td><input type=\"text\" name=\"fName\" id=\"fName\" value=\"" + rs.getString(2) + "\"></td>\n"
//                                + "                </tr>\n"
//                                + "                <tr>\n"
//                                + "                    <td><label for=\"lName\">lastName</label></td>\n"
//                                + "                    <td><input type=\"text\" name=\"lName\" id=\"lName\" value=\"" + rs.getString(3) + "\"></td>\n"
//                                + "                </tr>\n"
//                                + "                <tr>\n"
//                                + "                    <td><label for=\"email\">email</label></td>\n"
//                                + "                    <td><input type=\"email\" name=\"email\" id=\"email\" value=\"" + rs.getString(4) + "\"></td>\n"
//                                + "                </tr>\n"
//                                + "                <tr>\n"
//                                + "                    <td><label for=\"phone\">phone</label></td>\n"
//                                + "                    <td><input type=\"number\" name=\"phone\" id=\"phone\" value=\"" + rs.getString(5) + "\"></td>\n"
//                                + "                </tr>\n"
//                                + "                <tr>\n"
//                                + "                    <td>active</td>\n"
//                                + "                    <td>\n"
//                                + "                        <input type=\"radio\"  name=\"active\" value=\"1\"" + (rs.getInt(6) == 1 ? "Checked" : "") + ">Active\n"
//                                + "                        <input type=\"radio\"  name=\"active\" value=\"0\"" + (rs.getInt(6) == 0 ? "Checked" : "") + ">Deactive\n"
//                                + "                    </td>\n"
//                                + "                </tr>\n"
//                                + "                <tr>\n"
//                                + "                    <td>Store ID</td>\n"
//                                + "                    <td><select name=\"storeID\" >\n");
//                                ResultSet rs1=dao.getData("select * from stores");
//                                while(rs1.next()){
//                                    out.print("<option value=\""+rs1.getInt(1)+"\""+(rs.getString(7).equals(rs1.getString(1)) ? "selected=": "")+">"+rs1.getString(2)+"</option>");
//                                }
//                                out.print("                        </select></td>\n"
//                                + "                </tr>\n"
//                                + "                <tr>\n"
//                                + "                    <td>ManagerID</td>\n"
//                                + "                    <td><select name=\"managerID\" >\n");
//                                ResultSet rs2 = dao.getData("select * from staffs");
//                                while(rs2.next()){
//                                    out.print("<option value=\""+rs2.getInt(1)+"\""+(rs.getString(8).equals(rs2.getString(1)) ? "selected=": "")+">"+rs2.getString(2)+"</option>");
//                                }
//                                out.print("                        </select></td>\n"
//                                + "                </tr>\n"
//                                + "            </table>\n"
//                                + "            <input type=\"submit\" name=\"submit\" value=\"submit\">\n"
//                                + "            <input type=\"reset\" value=\"reset\">\n"
//                                + "        </form>");
//                    }
                } else {
                    // step2: update
                    String id = request.getParameter("id");
                    String fName = request.getParameter("fName");
                    String lName = request.getParameter("lName");
                    String email = request.getParameter("email");
                    String phone = request.getParameter("phone");
                    String active = request.getParameter("active");
                    String storeID = request.getParameter("storeID");
                    String managerID = request.getParameter("managerID");
                    
                    int idNumber = Integer.parseInt(id);
                    int phoneNumber = Integer.parseInt(phone);
                    int managerIDNumber = Integer.parseInt(managerID);
                    int storeIDNumber = Integer.parseInt(storeID);
                    int activeNumber = Integer.parseInt(active);
                    
                    int n = dao.updateStaff(new staffs(idNumber, fName, lName, email, phone, activeNumber, storeIDNumber, managerIDNumber));
                    
                    response.sendRedirect("ControllerStaff");
                }
                
            }
            if (service.equals("deleteStaff")) {
                String staffID = request.getParameter("staffID");
                int n = dao.removeStaff(staffID);
                response.sendRedirect("ControllerStaff");
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }

    public void dispath(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        RequestDispatcher dispath = request.getRequestDispatcher(page);
        dispath.forward(request, response);
    }


    /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ControllerStaff</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ControllerStaff at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
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
