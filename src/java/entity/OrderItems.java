/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Dell
 */
public class OrderItems {
    private int orderID;
    private int itemID;
    private int productID;
    private int quantity;
    private double listPrice;
    private double discount;

    public OrderItems() {
    }

    public OrderItems(int orderID, int itemID, int productID, int quantity, double listPrice, double discount) {
        this.orderID = orderID;
        this.itemID = itemID;
        this.productID = productID;
        this.quantity = quantity;
        this.listPrice = listPrice;
        this.discount = discount;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getListPrice() {
        return listPrice;
    }

    public void setListPrice(double listPrice) {
        this.listPrice = listPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "OrderItems{" + "orderID=" + orderID + ", itemID=" + itemID + ", productID=" + productID + ", quantity=" + quantity + ", listPrice=" + listPrice + ", discount=" + discount + '}';
    }
    
    
}
