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
public class Products {
    private int productID;
    private String productName;
    private int modelYear;
    private double listPrice;
    private String brandName;
    private String categoryName;

    public Products() {
    }

    public Products(int productID, String productName, int modelYear, double listPrice, String brandName, String categoryName) {
        this.productID = productID;
        this.productName = productName;
        this.modelYear = modelYear;
        this.listPrice = listPrice;
        this.brandName = brandName;
        this.categoryName = categoryName;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public double getListPrice() {
        return listPrice;
    }

    public void setListPrice(double listPrice) {
        this.listPrice = listPrice;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Products{" + "productID=" + productID + ", productName=" + productName + ", modelYear=" + modelYear + ", listPrice=" + listPrice + ", brandName=" + brandName + ", categoryName=" + categoryName + '}';
    }

    
    
    
}
