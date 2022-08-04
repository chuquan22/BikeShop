/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.staffs;
import entity.stores;
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
public class DAOStore extends ConnectDB{
    public int addStore(stores st){
        int n=0;
        String sql = "INSERT INTO [dbo].[stores]([store_id],"
                + "[store_name],[phone],[email],[street],"
                + "[city],[state],[zip_code])\n" +
"     VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //set value for ?
            // index start 1
            pre.setInt(1, st.getStoreID()); 
            pre.setString(2, st.getStoreName()); 
            pre.setString(3, st.getPhone());
            pre.setString(4, st.getEmail());
            pre.setString(5, st.getStreet());
            pre.setString(6, st.getCity());
            pre.setString(7, st.getState());
            pre.setString(8, st.getZipCode());
            // run
            n= pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public int updateStore(stores st){
        int n=0;
        String sql = "update stores set [store_name]=?,"
                + "[phone]=?,[email]=?,[street]=?,"
                + "[city]=?,[state]=?,[zip_code]=? where [store_id]=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //set value for ?
            // index start 1
            pre.setString(1, st.getStoreName()); // 2 ~ firstname
            pre.setString(2, st.getPhone());
            pre.setString(3, st.getEmail());
            pre.setString(4, st.getStreet());
            pre.setString(5, st.getCity());
            pre.setString(6, st.getState());
            pre.setString(7, st.getZipCode());
            pre.setInt(8, st.getStoreID());
            // run
            n= pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public int removeStore(String id){
        int n=0;
        String sql = "delete from stores where [store_id]='"+id+"'";
        // check foreign key costain
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public Vector<stores> ListAll(String sql){
        Vector<stores> vector = new Vector();
        try {
            ResultSet rs = getData(sql);
            while(rs.next()){
                int store_id = rs.getInt(1);
                String store_name = rs.getString(2);
                String phone = rs.getString(3);
                String email = rs.getString(4);
                String street = rs.getString(5);
                String city = rs.getString(6);
                String state = rs.getString(7);
                String zip_code = rs.getString(8);
                
                stores st = new stores(store_id, store_name, phone, email, street, city, state, zip_code);
                vector.add(st);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public static void main(String[] args) {
        DAOStore dao = new DAOStore();
//        int n = dao.addStore(new stores(4, "demo", "123456789", "demo@gmail.com", "demo", "demo", "HN", "12345"));
//
//        if (n > 0) {
//            System.out.println("inserted");
//        }
        //dao.ListAll();
    }
    }

