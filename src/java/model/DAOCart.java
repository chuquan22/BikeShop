/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Cart;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Dell
 */
public class DAOCart extends ConnectDB {

    public int addCart(Cart cart) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Cart]\n"
                + "           ([product_id]\n"
                + "           ,[product_name]\n"
                + "           ,[quantity]\n"
                + "           ,[price])\n"
                + "     VALUES (?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setInt(1, cart.getP().getProductID()); 
            pre.setString(2, cart.getP().getProductName()); 
            pre.setInt(3, cart.getQuantity());
            pre.setDouble(4, cart.getPrice());
            // run
            n= pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public int removeProduct(String id){
        int n=0;
        String sql = "delete from cart where [product_id] = "+id+"";
        // check foreign key costain
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    public int updateProduct(Cart cart, int oldQuantity){
        int n=0;
        String sql = "update cart set [quantity]=?,"
                + "[price]=? where [product_id]=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //set value for ?
            // index start 1
            pre.setInt(1, cart.getQuantity()+ oldQuantity); // 2 ~ ProductName
            pre.setDouble(2, cart.getPrice());
            pre.setInt(3, cart.getP().getProductID());
            // run
            n= pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    } 
}
