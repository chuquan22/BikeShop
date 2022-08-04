/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Customers;
import entity.Order;
import entity.OrderItems;
import entity.Products;
import entity.orderDetail;
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
public class DAOOrder extends ConnectDB {

    public int addOrder(Order ord) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[orders]([order_id],[customer_id],"
                + "[order_status],[order_date],[required_date],"
                + "[shipped_date],[store_id],[staff_id])\n"
                + " VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //set value for ?
            // index start 1
            pre.setInt(1, ord.getOrderID()); //1 ~ customerID
            pre.setInt(2, ord.getCustomerID()); // 2 ~ firstname
            pre.setInt(3, ord.getOrderStatus());
            pre.setString(4, ord.getOrderDate());
            pre.setString(5, ord.getRequiredDate());
            pre.setString(6, ord.getShippedDate());
            pre.setInt(7, ord.getStoreID());
            pre.setInt(8, ord.getStaffID());
            // run
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int updateOrder(Order ord) {
        int n = 0;
        String sql = "update orders set [customer_id]=?,"
                + "[order_status]=?,[order_date]=?,[required_date]=?,"
                + "[shipped_date]=?,[store_id]=?,[staff_id]=? where [order_id]=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, ord.getCustomerID());
            pre.setInt(2, ord.getOrderStatus());
            pre.setString(3, ord.getOrderDate());
            pre.setString(4, ord.getRequiredDate());
            pre.setString(5, ord.getShippedDate());
            pre.setInt(6, ord.getStoreID());
            pre.setInt(7, ord.getStaffID());
            pre.setInt(8, ord.getOrderID());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int removeOrder(int id) {
        int n = 0;
        String sql = "delete from orders where [order_id]=" + id + "";
        // check foreign key costain
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Vector<Order> ListAll(String sql) {
        Vector<Order> vector = new Vector();
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                int oID = rs.getInt(1);
                int cID = rs.getInt(2);
                int oStatus = rs.getInt(3);
                String order_date = rs.getString(4);
                String required_date = rs.getString(5);
                String shipped_date = rs.getString(6);
                int store_id = rs.getInt(7);
                int staff_id = rs.getInt(8);

                Order ord = new Order(oID, cID, oStatus, order_date, required_date, shipped_date, store_id, staff_id);
                vector.add(ord);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

   public Vector<orderDetail> OrderList(String sql) {
        Vector<orderDetail> vector = new Vector();
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                int cusID = rs.getInt("customer_id");
                String cusFirst = rs.getString("first_name");
                String cusLast = rs.getString("last_name");
                String cusPhone = rs.getString("phone");
                String cusEmail = rs.getString("email");
                String cusStreet = rs.getString("street");
                String cusCity = rs.getString("city");
                String cusState = rs.getString("state");
                String cusZip_Code = rs.getString("zip_code");
                Customers cus = new Customers(cusID, cusFirst, cusLast, cusPhone, cusEmail, cusStreet, cusCity, cusState, cusZip_Code);
                 
                int oID = rs.getInt("order_id");
                int cID = rs.getInt("customer_id");
                int oStatus = rs.getInt("order_status");
                String order_date = rs.getString("order_date");
                String required_date = rs.getString("required_date");
                String shipped_date = rs.getString("shipped_date");
                int store_id = rs.getInt("store_id");
                int staff_id = rs.getInt("staff_id");
                Order ord = new Order(oID, cID, oStatus, order_date, required_date, shipped_date, store_id, staff_id); 
                
                
                int store_id1 = rs.getInt("store_id");
                String store_name = rs.getString("store_name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String street = rs.getString("street");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String zip_code = rs.getString("zip_code");
                
                stores st = new stores(store_id1, store_name, phone, email, street, city, state, zip_code);

                orderDetail od = new orderDetail(cus, ord, st);
                vector.add(od);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
   
    public Vector<orderDetail> ListDetail(String sql) {
        Vector<orderDetail> vector = new Vector();
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                int cusID = rs.getInt("customer_id");
                String cusFirst = rs.getString("first_name");
                String cusLast = rs.getString("last_name");
                String cusPhone = rs.getString("phone");
                String cusEmail = rs.getString("email");
                String cusStreet = rs.getString("street");
                String cusCity = rs.getString("city");
                String cusState = rs.getString("state");
                String cusZip_Code = rs.getString("zip_code");
                Customers cus = new Customers(cusID, cusFirst, cusLast, cusPhone, cusEmail, cusStreet, cusCity, cusState, cusZip_Code);
                 
                int oID = rs.getInt("order_id");
                int cID = rs.getInt("customer_id");
                int oStatus = rs.getInt("order_status");
                String order_date = rs.getString("order_date");
                String required_date = rs.getString("required_date");
                String shipped_date = rs.getString("shipped_date");
                int store_id = rs.getInt("store_id");
                int staff_id = rs.getInt("staff_id");
                Order ord = new Order(oID, cID, oStatus, order_date, required_date, shipped_date, store_id, staff_id); 
                
                int order_id = rs.getInt("order_id");
                int item_id = rs.getInt("item_id");
                int product_id = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                double list_price = rs.getDouble("list_price");
                double discount = rs.getDouble("discount");
                OrderItems orItem = new OrderItems(order_id, item_id, product_id, quantity, list_price, discount);
                
                int pID = rs.getInt("product_id");
                String product_name = rs.getString("product_name");
                int model_year = rs.getInt("model_year");
                int list_price1 = rs.getInt("list_price");
                String brand_name = rs.getString("brand_name");
                String category_name = rs.getString("category_name");
                Products pro = new Products(pID, product_name, model_year, list_price1, brand_name, category_name);
                
                int store_id1 = rs.getInt("store_id");
                String store_name = rs.getString("store_name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String street = rs.getString("street");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String zip_code = rs.getString("zip_code");
                
                stores st = new stores(store_id1, store_name, phone, email, street, city, state, zip_code);

                orderDetail od = new orderDetail(cus, ord, orItem, pro, st);
                vector.add(od);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public Vector<orderDetail> Transaction (String sql) {
        Vector<orderDetail> vector = new Vector();
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                
                int oID = rs.getInt("order_id");
                int cID = rs.getInt("customer_id");
                int oStatus = rs.getInt("order_status");
                String order_date = rs.getString("order_date");
                String required_date = rs.getString("required_date");
                String shipped_date = rs.getString("shipped_date");
                int store_id = rs.getInt("store_id");
                int staff_id = rs.getInt("staff_id");
                Order ord = new Order(oID, cID, oStatus, order_date, required_date, shipped_date, store_id, staff_id); 
                
                int order_id = rs.getInt("order_id");
                int item_id = rs.getInt("item_id");
                int product_id = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                double list_price = rs.getDouble("list_price");
                double discount = rs.getDouble("discount");
                OrderItems orItem = new OrderItems(order_id, item_id, product_id, quantity, list_price, discount);
                
                int pID = rs.getInt("product_id");
                String product_name = rs.getString("product_name");
                int model_year = rs.getInt("model_year");
                int list_price1 = rs.getInt("list_price");
                String brand_name = rs.getString("brand_name");
                String category_name = rs.getString("category_name");
                Products pro = new Products(pID, product_name, model_year, list_price1, brand_name, category_name);
                
                int store_id1 = rs.getInt("store_id");
                String store_name = rs.getString("store_name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String street = rs.getString("street");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String zip_code = rs.getString("zip_code");
                stores st = new stores(store_id1, store_name, phone, email, street, city, state, zip_code);

                orderDetail od = new orderDetail(ord, orItem, st, pro);
                vector.add(od);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public int updateOrderStatus(int order_status, int id) {
        int n = 0;
        String sql = "update orders set "
                + "[order_status]=?"
                + " where [order_id]=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, order_status);
            pre.setInt(2, id);
            

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
   

    public static void main(String[] args) {
        DAOOrder dao = new DAOOrder();
//        int n = dao.addOrder(new Order(1616, 200, 4, "12-12-2001", "09-09-2002", "12-08-2002", 3, 6));
//        dao.ListAll();
//        int n=dao.updateOrder(new Order(1616, 200, 4, "12-08-2001", "09-09-2001", "12-08-2001", 3, 10));
//        if (n > 0) {
//            System.out.println("inserted");
//        }
    }
}
