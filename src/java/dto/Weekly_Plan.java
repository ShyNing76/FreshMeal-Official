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
public class Weekly_Plan implements Serializable {

    private int weekly_Plan_ID;
    private Date startDate;
    private Date endDate;
    private int status;

    public Weekly_Plan() {
    }

    public Weekly_Plan(int weekly_Plan_ID, Date startDate, Date endDate, int status) {
        this.weekly_Plan_ID = weekly_Plan_ID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public int getWeekly_Plan_ID() {
        return weekly_Plan_ID;
    }

    public void setWeekly_Plan_ID(int weekly_Plan_ID) {
        this.weekly_Plan_ID = weekly_Plan_ID;
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
