/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import entity.stocks;
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
public class DAOStock extends ConnectDB{
    public int addStock(stocks st){
        int n=0;
        String sql = "INSERT INTO [dbo].[stocks]([store_id]"
                + " ,[product_id],[quantity])"
                + "VALUES(?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //set value for ?
            // index start 1
            pre.setInt(1, st.getStoreID()); //1 ~ customerID
            pre.setInt(2, st.getProductID());
            pre.setInt(3, st.getQuantity());
            // run
            n= pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public int updateStock(stocks st){
        int n=0;
        String sql = "update stocks set [quantity]=? where [store_id]=? and [product_id]=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //set value for ?
            // index start 1
             pre.setInt(1, st.getQuantity());
            pre.setInt(2, st.getStoreID()); //1 ~ customerID
            pre.setInt(3, st.getProductID());
           
            // run
            n= pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public int removeStock(String Sid, String Pid){
        int n=0;
        String sql = "delete from stocks where [store_id]='"+Sid+"' and [product_id]='"+Pid+"'";
        // check foreign key costain
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public Vector<stocks> ListAll(String sql){
        Vector<stocks> vector = new Vector();
        try {
            ResultSet rs = getData(sql);
            while(rs.next()){
                int store_id = rs.getInt(1);
                int product_id = rs.getInt(2);
                int quantity = rs.getInt(3);
                stocks st = new stocks(store_id, product_id, quantity);
                vector.add(st);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public static void main(String[] args) {
        DAOStock dao = new DAOStock();
//        int n = dao.addStock(new stocks(4, 201, 10));
//
//        if (n > 0) {
//            System.out.println("inserted");
//        }
       // dao.ListAll();
    }
}
