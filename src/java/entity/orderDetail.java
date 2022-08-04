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
public class orderDetail{
   Customers c;
   Order o;
   OrderItems oi;
   Products p;
   stores s;

    public orderDetail() {
    }

    public orderDetail(Customers c, Order o, OrderItems oi, Products p, stores s) {
        this.c = c;
        this.o = o;
        this.oi = oi;
        this.p = p;
        this.s = s;
    }
    
    public orderDetail(Customers c, Order o, stores s) {
        this.c = c;
        this.o = o;
        this.s = s;
    }

    public orderDetail(OrderItems oi, Products p) {
        this.oi = oi;
        this.p = p;
    }

    public orderDetail(Order o, OrderItems oi, stores s, Products p) {
        this.o = o;
        this.oi = oi;
        this.s = s;
        this.p = p;
    }
    
    public Customers getC() {
        return c;
    }

    public void setC(Customers c) {
        this.c = c;
    }

    public Order getO() {
        return o;
    }

    public void setO(Order o) {
        this.o = o;
    }

    public OrderItems getOi() {
        return oi;
    }

    public void setOi(OrderItems oi) {
        this.oi = oi;
    }

    public Products getP() {
        return p;
    }

    public void setP(Products p) {
        this.p = p;
    }

    public stores getS() {
        return s;
    }

    public void setS(stores s) {
        this.s = s;
    }
    
   
    
    
}
