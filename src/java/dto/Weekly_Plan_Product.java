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
public class Weekly_Plan_Product implements Serializable {

    private int weekly_Plan_Product_ID;
    private int weekly_Plan_ID;
    private int productID;
    private int status;

    public Weekly_Plan_Product() {
    }

    public Weekly_Plan_Product(int weekly_Plan_Product_ID, int weekly_Plan_ID, int productID, int status) {
        this.weekly_Plan_Product_ID = weekly_Plan_Product_ID;
        this.weekly_Plan_ID = weekly_Plan_ID;
        this.productID = productID;
        this.status = status;
    }

    public int getWeekly_Plan_Product_ID() {
        return weekly_Plan_Product_ID;
    }

    public void setWeekly_Plan_Product_ID(int weekly_Plan_Product_ID) {
        this.weekly_Plan_Product_ID = weekly_Plan_Product_ID;
    }

    public int getWeekly_Plan_ID() {
        return weekly_Plan_ID;
    }

    public void setWeekly_Plan_ID(int weekly_Plan_ID) {
        this.weekly_Plan_ID = weekly_Plan_ID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
