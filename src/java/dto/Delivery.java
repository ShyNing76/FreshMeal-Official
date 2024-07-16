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
public class Delivery implements Serializable {

    private int deliveryID;
    private String deliveryMethod;
    private int deliveryPrice;
    private String deliveryIcon;
    private int status;

    public Delivery() {
    }

    public Delivery(int deliveryID, String deliveryMethod, int deliveryPrice) {
        this.deliveryID = deliveryID;
        this.deliveryMethod = deliveryMethod;
        this.deliveryPrice = deliveryPrice;
    }

    public Delivery(int deliveryID, String deliveryMethod, int deliveryPrice, String deliveryIcon, int status) {
        this.deliveryID = deliveryID;
        this.deliveryMethod = deliveryMethod;
        this.deliveryPrice = deliveryPrice;
        this.deliveryIcon = deliveryIcon;
        this.status = status;
    }

    public int getDeliveryID() {
        return deliveryID;
    }

    public void setDeliveryID(int deliveryID) {
        this.deliveryID = deliveryID;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public int getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(int deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public String getDeliveryIcon() {
        return deliveryIcon;
    }

    public void setDeliveryIcon(String deliveryIcon) {
        this.deliveryIcon = deliveryIcon;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
