/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Customers;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class DAOCustomers extends ConnectDB {

    public int addCustomers(Customers cus) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[customers]\n"
                + "           ([customer_id]\n"
                + "           ,[first_name]\n"
                + "           ,[last_name]\n"
                + "           ,[phone]\n"
                + "           ,[email]\n"
                + "           ,[street]\n"
                + "           ,[city]\n"
                + "           ,[state]\n"
                + "           ,[zip_code]\n"
                + "           ,[user_name]\n"
                + "           ,[password])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //set value for ?
            // index start 1
            pre.setInt(1, cus.getCustomerID());
            pre.setString(2, cus.getFirstName()); // 2 ~ firstname
            pre.setString(3, cus.getLastName());
            pre.setString(4, cus.getPhone());
            pre.setString(5, cus.getEmail());
            pre.setString(6, cus.getStreet());
            pre.setString(7, cus.getCity());
            pre.setString(8, cus.getState());
            pre.setString(9, cus.getZipCode());
            pre.setString(10, cus.getUserName());
            pre.setString(11, cus.getPassword());
            // run
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int updateCustomer(Customers cus) {
        int n = 0;
        String sql = "update Customers set "
                + "[first_name]=?,[last_name]=?,[phone]=?,"
                + "[email]=?,[street]=?,[city]=?,[state]=?,[zip_code]=? "
                + "where [customer_id]=? ";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //set value for ?
            // index start 1
            pre.setString(1, cus.getFirstName()); // 2~ first_name
            pre.setString(2, cus.getLastName());
            pre.setString(3, cus.getPhone());
            pre.setString(4, cus.getEmail());
            pre.setString(5, cus.getStreet());
            pre.setString(6, cus.getCity());
            pre.setString(7, cus.getState());
            pre.setString(8, cus.getZipCode());
            pre.setInt(9, cus.getCustomerID()); //1
            // run
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;

    }

    public int changePassword(String password, int id) {
        int n = 0;
        String sql = "update Customers set [password]=? where [customer_id]=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //set value for ?
            // index start 1
            pre.setString(1, password);
            pre.setInt(2, id);
            // run
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int removeCustomer(String id) {
        int n = 0;
        String sql = "delete from Customers where [customer_id]='" + id + "'";
        // check foreign key costain
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Vector<Customers> searchCity(String city) {
        Vector<Customers> vector = new Vector<Customers>();
        String sql = "select * from customers where city='" + city + "'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int cus_id = rs.getInt(1);
                String firt_name = rs.getString(2);
                String last_name = rs.getString(3);
                String phone = rs.getString(4);
                String email = rs.getString(5);
                String street = rs.getString(6);
                city = rs.getString(7);
                String state = rs.getString(8);
                String zip_code = rs.getString(9);
                Customers cus = new Customers(cus_id, firt_name, last_name, phone, email, street, city, state, zip_code);
                vector.add(cus);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Customers> ListAll(String sql) {
        Vector<Customers> vector = new Vector();
        try {
            //        Statement satement = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
//            Statement state = conn.createStatement(
//                    ResultSet.TYPE_SCROLL_SENSITIVE,
//                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = getData(sql);
            while (rs.next()) {
                int cusID = rs.getInt(1);
                String cusFirst = rs.getString(2);
                String cusLast = rs.getString(3);
                String cusPhone = rs.getString(4);
                String cusEmail = rs.getString(5);
                String cusStreet = rs.getString(6);
                String cusCity = rs.getString(7);
                String cusState = rs.getString(8);
                String cusZip_Code = rs.getString(9);

                Customers cus = new Customers(cusID, cusFirst, cusLast, cusPhone, cusEmail, cusStreet,
                        cusCity, cusState, cusZip_Code);
                vector.add(cus);
            }
//        state.execute(sql); // create, drop, alter
//        TYPE_FORWARD_ONLY: default, duyet cac ban ghi tu tren xuong
//        TYPE_SCROLL_SENSITIVE: thread safe
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Customers getCusbyUserName(String userName) {
        ResultSet rs = getData("select * from customers where username = '" + userName + "'");
        Customers cus = new Customers();
        try {
            if (rs.next()) {
                cus.setCustomerID(rs.getInt(1));
                cus.setFirstName(rs.getString(2));
                cus.setLastName(rs.getString(3));
                cus.setPhone(rs.getString(4));
                cus.setEmail(rs.getString(5));
                cus.setStreet(rs.getString(6));
                cus.setCity(rs.getString(7));
                cus.setState(rs.getString(8));
                cus.setZipCode(rs.getString(9));
                cus.setUserName(rs.getString(10));
                cus.setPassword(rs.getString(11));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cus;
    }

    public boolean checkUserNameExist(String userName) {
        boolean check = false;
        ResultSet rs1 = getData("select * from customers where user_name = '"+userName+"'");
        try {
            if (rs1.next()) {
                check = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return check;
    }

    public static void main(String[] args) {
        DAOCustomers dao = new DAOCustomers();
//        int n = dao.addCustomers(new Customers(501, "demo", "demo", "123456789", "demo@gmail.com", "demo", "demo", "HN", "12345"));
//        int n=dao.changePhone("0984739845", 501);
        //int n= dao.updateCustomer(new Customers(501, "abc", "abc", "00001", "demo1@gmail.com", "demo1", "demo2", "HN", "23456"));
        //dao.listAll();
//       Vector<Customers> vec=dao.searchCity("demo2");
//       for(Customers cus:vec){
//           System.out.println(cus);
//       }
//        if (n > 0) {
//            System.out.println("inserted");
//        }
        
    }
}
