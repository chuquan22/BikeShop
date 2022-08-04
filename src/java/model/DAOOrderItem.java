/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import entity.OrderItems;
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
public class DAOOrderItem extends ConnectDB{
    public int addOrderItem(OrderItems oItem){
        int n=0;
        String sql = "INSERT INTO [dbo].[order_items]([order_id],[item_id],"
                + "[product_id],[quantity],[list_price],[discount])\n" +
                " VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //set value for ?
            // index start 1
            pre.setInt(1, oItem.getOrderID()); //1 ~ customerID
            pre.setInt(2, oItem.getItemID()); // 2 ~ firstname
            pre.setInt(3, oItem.getProductID());
            pre.setInt(4, oItem.getQuantity());
            pre.setDouble(5, oItem.getListPrice());
            pre.setDouble(6, oItem.getDiscount());
            // run
            n= pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public int updateOrderItem(OrderItems oItem){
        int n=0;
        String sql = "update order_items set [item_id]=?,"
                + "[product_id]=?,[quantity]=?,[list_price]=?,[discount]=? where [order_id]=? ";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //set value for ?
            // index start 1
            pre.setInt(1, oItem.getItemID()); // 2 ~ firstname
            pre.setInt(2, oItem.getProductID());
            pre.setInt(3, oItem.getQuantity());
            pre.setDouble(4, oItem.getListPrice());
            pre.setDouble(5, oItem.getDiscount());
            pre.setInt(6, oItem.getOrderID());
            // run
            n= pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public int removeOrderItem(String Oid){
        int n=0;
        String sql = "delete from order_items where [order_id]='"+Oid+"'";
        // check foreign key costain
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public Vector<OrderItems> ListAll(String sql){
        Vector<OrderItems> vector = new Vector();
        try {
            ResultSet rs = getData(sql);
            while(rs.next()){
                int order_id = rs.getInt(1);
                int item_id = rs.getInt(2);
                int product_id = rs.getInt(3);
                int quantity = rs.getInt(4);
                double list_price = rs.getDouble(5);
                double discount = rs.getDouble(6);
                
                OrderItems orItem = new OrderItems(order_id, item_id, product_id, quantity, list_price, discount);
                vector.add(orItem);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    


    public static void main(String[] args) {
//        DAOOrderItem dao = new DAOOrderItem();
//        int n = dao.addOrderItem(new OrderItems(1616, 2, 125, 2, 2021.99, 0.10));
//        //dao.ListAll();
//        if (n > 0) {
//            System.out.println("inserted");
//        }
    }
    }

