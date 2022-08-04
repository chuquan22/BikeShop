/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Customers;
import entity.HistoryManage;
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
import model.DAOCustomers;
import model.DAOHistory;

/**
 *
 * @author Dell
 */
@WebServlet(name = "ControllerCustomer", urlPatterns = {"/ControllerCustomer"})
public class ControllerCustomer extends HttpServlet {

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
            service = "displayAllCustomer";
        }
        DAOCustomers dao = new DAOCustomers();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            if (service.equals("register")) {
                ResultSet rs1 = dao.getData("select Distinct state from customers");
                ResultSet rs2 = dao.getData("select Distinct city from customers");
                ResultSet rs3 = dao.getData("select Distinct zip_code from customers");

                request.setAttribute("rsState", rs1);
                request.setAttribute("rscity", rs2);
                request.setAttribute("rsZipCode", rs3);

                RequestDispatcher dispath = request.getRequestDispatcher("createCustomer.jsp");
                dispath.forward(request, response);
            }
            if (service.equals("insertCustomer")) {

                ResultSet rs4 = dao.getData("select Distinct state from customers");
                ResultSet rs2 = dao.getData("select Distinct city from customers");
                ResultSet rs3 = dao.getData("select Distinct zip_code from customers");
                ResultSet rs5 = dao.getData("select max(customer_id) from customers");
                int oldCusID = 0;

                String fName = request.getParameter("fName");
                String lName = request.getParameter("lName");
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                String street = request.getParameter("street");
                String city = request.getParameter("city");
                String state = request.getParameter("state");
                String zipCode = request.getParameter("zipCode");
                String userName = request.getParameter("userName");
                String passWord = request.getParameter("passWord");
                String checkPass = request.getParameter("checkPass");

                request.setAttribute("rsState", rs4);
                request.setAttribute("rscity", rs2);
                request.setAttribute("rsZipCode", rs3);

                if (rs5.next()) {
                    oldCusID = rs5.getInt(1);
                }

                if (fName == null || fName.equals("") || lName == null || lName.equals("") || email == null || email.equals("")
                        || userName == null || userName.equals("") || passWord == null || passWord.equals("")) {

                    request.setAttribute("error", "Please enter all data");

                    request.getRequestDispatcher("createCustomer.jsp").forward(request, response);
                } else if (!checkPass.equals(passWord)) {
                    request.setAttribute("error", "Passwors and re-password not euqal. Plese enter again");

                    request.getRequestDispatcher("createCustomer.jsp").forward(request, response);
                } else if (dao.checkUserNameExist(userName)) {
                    request.setAttribute("error", "User Name is exsit. Please enter other user name");

                    request.getRequestDispatcher("createCustomer.jsp").forward(request, response);
                } else {
                    int n = dao.addCustomers(new Customers(oldCusID + 1, fName, lName, phone, email, street, city, state, zipCode, userName, passWord));
                    if (n > 0) {
                        DAOHistory dao1 = new DAOHistory();
                        HttpSession session = request.getSession();
                        java.util.Enumeration em = session.getAttributeNames();
                        while (em.hasMoreElements()) {
                            String key = em.nextElement().toString();
                            if (key.equals("admin")) {
                                staffs staff = (staffs) session.getAttribute(key);
                                String content = "Was craete customer " + fName + " " + lName + "";
                                int m = dao1.addHistoryManage(new HistoryManage(staff.getFirstName() + " " + staff.getLastName(), content));
                            }
                        }
                    }
                    HttpSession session = request.getSession();
                    ResultSet rs = dao.getData("select user_name, password from customers where user_name = '" + userName + "' and password = '" + passWord + "'");
                    if (rs.next()) {
                        Customers cus = dao.getCusbyUserName(userName);
                        session.setAttribute("userName", cus);
                        response.sendRedirect("ControllerShop");
                    }
                    //request.getRequestDispatcher("ControllerLogin?").forward(request, response);
                }

            }
            if (service.equals("displayAllCustomer")) {
                Vector<Customers> vector = dao.ListAll("select * from customers");
                // get data from model
                String titlepage = "Customer manager";
                String titleTable = "Customer List";
                RequestDispatcher dispath = request.getRequestDispatcher("/JSP/displayCustomer.jsp");
                // pre data for jsp
                request.setAttribute("customerList", vector);
                request.setAttribute("titlepage", titlepage);
                request.setAttribute("titleTable", titleTable);

                //run
                dispath.forward(request, response);
            }
            if (service.equals("updateCustomer")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String customerID = request.getParameter("customerID");
                    ResultSet rs = dao.getData("select * from customers where customer_id=" + customerID);
                    ResultSet rs1 = dao.getData("select Distinct state from customers");
                    ResultSet rs2 = dao.getData("select Distinct city from customers");
                    ResultSet rs3 = dao.getData("select Distinct zip_code from customers");

                    request.setAttribute("rsCus", rs);
                    request.setAttribute("rsState", rs1);
                    request.setAttribute("rscity", rs2);
                    request.setAttribute("rsZipCode", rs3);
                    RequestDispatcher dispath = request.getRequestDispatcher("/JSP/updateCustomer.jsp");
                    dispath.forward(request, response);
                } else {
                    String id = request.getParameter("id");
                    String fName = request.getParameter("fName");
                    String lName = request.getParameter("lName");
                    String phone = request.getParameter("phone");
                    String email = request.getParameter("email");
                    String street = request.getParameter("street");
                    String city = request.getParameter("city");
                    String state = request.getParameter("state");
                    String zipCode = request.getParameter("zipCode");

                    Customers customer = new Customers(Integer.parseInt(id), fName, lName, phone, email, street, city, state, zipCode);
                    int n = dao.updateCustomer(customer);
                    if (n > 0) {
                        DAOHistory dao1 = new DAOHistory();
                        HttpSession session = request.getSession();
                        java.util.Enumeration em = session.getAttributeNames();
                        while (em.hasMoreElements()) {
                            String key = em.nextElement().toString();
                            if (key.equals("admin")) {
                                staffs staff = (staffs) session.getAttribute(key);
                                String content = "Was update customer " + fName + " " + lName + "";
                                int m = dao1.addHistoryManage(new HistoryManage(staff.getFirstName() + " " + staff.getLastName(), content));
                            }
                        }
                    }
                    response.sendRedirect("ControllerAdmin?s=customerManager");
                }
            }
            if (service.equals("deleteCustomer")) {
                // nếu đã có order thì k xóa đc
                String customerID = request.getParameter("customerID");
                int n = dao.removeCustomer(customerID);
                if (n == 0) {
                    request.setAttribute("message", "can't delete this customer because customer have order");

                } else {
                    request.setAttribute("message", "delete successfull");

                }
                Vector<Customers> vector = dao.ListAll("select * from customers");
                // get data from model

                RequestDispatcher dispath = request.getRequestDispatcher("admin.jsp");
                // pre data for jsp
                request.setAttribute("customerList", vector);
                if (n > 0) {
                    DAOHistory dao1 = new DAOHistory();
                    HttpSession session = request.getSession();
                    java.util.Enumeration em = session.getAttributeNames();
                    while (em.hasMoreElements()) {
                        String key = em.nextElement().toString();
                        if (key.equals("admin")) {
                            staffs staff = (staffs) session.getAttribute(key);
                            String content = "Was delete customer " + customerID + "";
                            int m = dao1.addHistoryManage(new HistoryManage(staff.getFirstName() + " " + staff.getLastName(), content));
                        }
                    }
                }
                //run
                dispath.forward(request, response);

            }
            if (service.equals("customerManager")) {
                Vector<Customers> vector = dao.ListAll("select * from customers");
                // get data from model

                RequestDispatcher dispath = request.getRequestDispatcher("/JSP/displayCustomer.jsp");
                // pre data for jsp
                request.setAttribute("customerList", vector);

                //run
                dispath.forward(request, response);
            }
            if (service.equals("searchByID")) {
                String customerID = request.getParameter("search");
                Vector<Customers> vector = dao.ListAll("select * from customers where customer_id = " + customerID);
                // get data from model

                RequestDispatcher dispath = request.getRequestDispatcher("admin.jsp");
                // pre data for jsp
                request.setAttribute("customerList", vector);

                //run
                dispath.forward(request, response);
            }
            if (service.equals("searchByName")) {
                String cusName = request.getParameter("search");
                Vector<Customers> vector = dao.ListAll("select * from customers where first_name like '%" + cusName + "%' or last_name like '%" + cusName + "%'");
                // get data from model

                RequestDispatcher dispath = request.getRequestDispatcher("admin.jsp");
                // pre data for jsp
                request.setAttribute("customerList", vector);

                //run
                dispath.forward(request, response);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ControllerCustomer.class.getName()).log(Level.SEVERE, null, ex);
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
