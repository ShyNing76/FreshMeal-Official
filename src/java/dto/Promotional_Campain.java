/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author ADMIN
 */
public class Promotional_Campain implements Serializable {

    private int promotional_Campain_ID;
    private String name;
    private String description;
    private double discountPrice;
    private Date startDate;
    private Date endDate;
    private int status;

    public Promotional_Campain() {
    }

    public Promotional_Campain(int promotional_Campain_ID, String name, String description, double discountPrice, Date startDate, Date endDate, int status) {
        this.promotional_Campain_ID = promotional_Campain_ID;
        this.name = name;
        this.description = description;
        this.discountPrice = discountPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public int getPromotional_Campain_ID() {
        return promotional_Campain_ID;
    }

    public void setPromotional_Campain_ID(int promotional_Campain_ID) {
        this.promotional_Campain_ID = promotional_Campain_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
