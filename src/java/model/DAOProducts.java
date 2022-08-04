/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import entity.Cart;
import entity.Customers;
import entity.Order;
import entity.OrderItems;
import entity.Products;
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
public class DAOProducts extends ConnectDB{
    public int addProducts(Products pro){
        int n=0;
        String sql = "INSERT INTO [dbo].[products]([product_id],"
                + "[product_name],[model_year],[list_price],"
                + "[brand_name],[category_name]) "
                + "VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //set value for ?
            // index start 1
            pre.setInt(1, pro.getProductID()); //1 ~ ProductID
            pre.setString(2, pro.getProductName()); // 2 ~ ProductName
            pre.setInt(3, pro.getModelYear());
            pre.setDouble(4, pro.getListPrice());
            pre.setString(5, pro.getBrandName());
            pre.setString(6, pro.getCategoryName());
            // run
            n= pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public int updateProduct(Products pro){
        int n=0;
        String sql = "update products set [product_name]=?,"
                + "[model_year]=?,[list_price]=?,"
                + "[brand_name]=?,[category_name]=? where [product_id]=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //set value for ?
            // index start 1
            pre.setString(1, pro.getProductName()); // 2 ~ ProductName
            pre.setInt(2, pro.getModelYear());
            pre.setDouble(3, pro.getListPrice());
            pre.setString(4, pro.getBrandName());
            pre.setString(5, pro.getCategoryName());
            pre.setInt(6, pro.getProductID());
            // run
            n= pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public int removeProduct(String id){
        int n=0;
        String sql = "delete from products where [product_id] = "+id+"";
        // check foreign key costain
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public Vector<Products> searchByName(String Pname){
        Vector<Products> vector = new Vector();
        String sql = "select * from products where product_name like '%"+Pname+"%'";
        ResultSet rs = getData(sql);
        try {
            while(rs.next()){
                
                int product_id = rs.getInt(1);
                String product_name = rs.getString(2);
                int model_year = rs.getInt(3);
                double list_price = rs.getDouble(4);
                String brand_name = rs.getString(5);
                String category_name = rs.getString(6);
                Products pro = new Products(product_id, product_name, model_year, list_price, brand_name, category_name);
                vector.add(pro);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public Vector<Products> searchByPrice(int priceX, int priceY){
        Vector<Products> vector = new Vector();
        String sql = "select * from products where list_price between "+priceX+" and "+priceY+"";
        ResultSet rs = getData(sql);
        try {
            while(rs.next()){
                int product_id = rs.getInt(1);
                String product_name = rs.getString(2);
                int model_year = rs.getInt(3);
                double list_price = rs.getDouble(4);
                String brand_name = rs.getString(5);
                String category_name = rs.getString(6);
                Products pro = new Products(product_id, product_name, model_year, list_price, brand_name, category_name);
                vector.add(pro);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public Vector<Products> ListAll(String sql){
        Vector<Products> vector = new Vector();
        try {
            ResultSet rs = getData(sql);
            while(rs.next()){
                int product_id = rs.getInt(1);
                String product_name = rs.getString(2);
                int model_year = rs.getInt(3);
                double list_price = rs.getDouble(4);
                String brand_name = rs.getString(5);
                String category_name = rs.getString(6);
                
                
                Products pro = new Products(product_id, product_name, model_year, list_price, brand_name, category_name);
                vector.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public void joinTable(){
        String sql = "select * from customers c\n" +
"inner join orders o on c.customer_id = o.customer_id\n" +
"inner join order_items oi on o.order_id = oi.order_id\n" +
"inner join products p on oi.product_id = p.product_id\n" +
"inner join staffs s on s.staff_id = o.staff_id";
        try {
            ResultSet rs = getData(sql);
            while(rs.next()){
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
                double list_price1 = rs.getDouble("list_price");
                String brand_name = rs.getString("brand_name");
                String category_name = rs.getString("category_name");
                Products pro = new Products(pID, product_name, model_year, list_price1, brand_name, category_name);
                
                int staff_id1 = rs.getInt("staff_id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                int active = rs.getInt("active");
                int store_id1 = rs.getInt("store_id");
                int manager_id = rs.getInt("manager_id");
                staffs st = new staffs(staff_id, first_name, last_name, email, phone, store_id, store_id, manager_id);
                
                
                System.out.print(cus);
                System.out.print(ord);
                System.out.print(orItem);
                System.out.print(pro);
                System.out.print(st);
                System.out.println("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void main(String[] args) {
        DAOProducts dao = new DAOProducts();
//        int n = dao.addProducts(new Products(201, "demo", 2020, 10000, "demo", "Electric Bikes"));
//        int n=dao.updateProduct(new Products(201, "quan", 2020, 10000, "quan", "quanvip"));
//        if (n > 0) {
//            System.out.println("inserted");
//        }
//        Vector<Products> vec = dao.searchByName("quan");
//        for (Products products : vec) {
//            System.out.println(products);
//        }
       //dao.ListAll();
//        dao.ListAll();
        
    }
    }

