/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import entity.staffs;
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
public class DAOStaff extends ConnectDB{
    public int addStaff(staffs st){
        int n=0;
        String sql = "INSERT INTO [dbo].[staffs]([staff_id],[first_name],"
                + "[last_name],[email],[phone],[active],[store_id],[manager_id])\n" +
                "     VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //set value for ?
            // index start 1
            pre.setInt(1, st.getStaffID()); //1 ~ customerID
            pre.setString(2, st.getFirstName()); // 2 ~ firstname
            pre.setString(3, st.getLastName());
            pre.setString(4, st.getEmail());
            pre.setString(5, st.getPhone());
            pre.setInt(6, st.getActive());
            pre.setInt(7, st.getStoreID());
            pre.setInt(8, st.getManagerID());
            // run
            n= pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public int updateStaff(staffs st){
        int n=0;
        String sql = "update staffs set [first_name]=?,"
                + "[last_name]=?,[email]=?,[phone]=?,"
                + "[active]=?,[store_id]=?,[manager_id]=? where [staff_id]=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //set value for ?
            // index start 1
            pre.setString(1, st.getFirstName()); // 2 ~ firstname
            pre.setString(2, st.getLastName());
            pre.setString(3, st.getEmail());
            pre.setString(4, st.getPhone());
            pre.setInt(5, st.getActive());
            pre.setInt(6, st.getStoreID());
            pre.setInt(7, st.getManagerID());
            pre.setInt(8, st.getStaffID()); 
            // run
            n= pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public int removeStaff(String id){
        int n=0;
        String sql = "delete from staffs where [staff_id]='"+id+"'";
        // check foreign key costain
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public Vector<staffs> ListAll(String sql){
        Vector<staffs> vector = new Vector();
//        String sql = "select * from staffs";
        try {
            ResultSet rs = getData(sql);
            while(rs.next()){
                int staff_id = rs.getInt(1);
                String first_name = rs.getString(2);
                String last_name = rs.getString(3);
                String email = rs.getString(4);
                String phone = rs.getString(5);
                int active = rs.getInt(6);
                int store_id = rs.getInt(7);
                int manager_id = rs.getInt(8);
                
                staffs st = new staffs(staff_id, first_name, last_name, email, phone, active, store_id, manager_id);
               vector.add(st);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public staffs getStaffByUserName(String userName){
        ResultSet rs = getData("select * from staffs where username = '"+userName+"'");
        staffs s = new staffs();
        try {
            if(rs.next()){
                s.setStaffID(rs.getInt(1));
                s.setFirstName(rs.getString(2));
                s.setLastName(rs.getString(3));
                s.setEmail(rs.getString(4));
                s.setPhone(rs.getString(5));
                s.setActive(rs.getInt(6));
                s.setStoreID(rs.getInt(7));
                s.setManagerID(rs.getInt(8));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStaff.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    public static void main(String[] args) {
        DAOStaff dao = new DAOStaff();
//        int n = dao.addStaff(new staffs(12, "demo", "demo", "demo@gmail.com", "1234567", 1, 2, 3));
////
//        if (n > 0) {
//            System.out.println("inserted");
//        }
        //dao.ListAll();
    }
    }

