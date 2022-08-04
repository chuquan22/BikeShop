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
public class stocks {
    private int storeID;
    private int productID;
    private int quantity;

    public stocks() {
    }

    public stocks(int storeID, int productID, int quantity) {
        this.storeID = storeID;
        this.productID = productID;
        this.quantity = quantity;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
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

    @Override
    public String toString() {
        return "stocks{" + "storeID=" + storeID + ", productID=" + productID + ", quantity=" + quantity + '}';
    }
    
    
}
