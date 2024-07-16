/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
public class Order_Detail implements Serializable {

    private int order_Detail_ID;
    private int productID;
    private int orderID;
    private int price;
    private int quantity;
    private String mealType;

    public Order_Detail() {
    }

    public Order_Detail(int order_Detail_ID, int productID, int orderID, int price, int quantity, String mealType) {
        this.order_Detail_ID = order_Detail_ID;
        this.productID = productID;
        this.orderID = orderID;
        this.price = price;
        this.quantity = quantity;
        this.mealType = mealType;
    }

    public int getOrder_Detail_ID() {
        return order_Detail_ID;
    }

    public void setOrder_Detail_ID(int order_Detail_ID) {
        this.order_Detail_ID = order_Detail_ID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

}
