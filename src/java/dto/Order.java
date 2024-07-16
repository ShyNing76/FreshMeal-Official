/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class Order implements Serializable {

    private int orderID;
    private int addressID;
    private Date orderDate;
    private Date shipDate;
    private int deliveryID;
    private int discount;
    private int tax;
    private int totalPrice;
    private String status;
    private String paymentMethod;
    private int userID;
    private ArrayList<Order_Detail> orderDetail;

    public Order() {
        orderDate = new Date(System.currentTimeMillis());
        orderDetail = new ArrayList<>();
    }

    public Order(int orderID, int addressID, Date orderDate, Date shipDate, int deliveryID, int discount, int tax, int totalPrice, String status, String paymentMethod, int userID, ArrayList<Order_Detail> orderDetail) {
        this.orderID = orderID;
        this.addressID = addressID;
        this.orderDate = orderDate;
        this.shipDate = shipDate;
        this.deliveryID = deliveryID;
        this.discount = discount;
        this.tax = tax;
        this.totalPrice = totalPrice;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.userID = userID;
        this.orderDetail = orderDetail;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public int getDeliveryID() {
        return deliveryID;
    }

    public void setDeliveryID(int deliveryID) {
        this.deliveryID = deliveryID;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public ArrayList<Order_Detail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(ArrayList<Order_Detail> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public void addOrderDetail(Order_Detail detail) {
        orderDetail.add(detail);
    }
}
