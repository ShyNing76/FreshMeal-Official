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
public class Meal_Type implements Serializable {

    private int mealID;
    private String mealName;

    public Meal_Type() {
    }

    public Meal_Type(int mealID, String mealName) {
        this.mealID = mealID;
        this.mealName = mealName;
    }

    public int getMealID() {
        return mealID;
    }

    public void setMealID(int mealID) {
        this.mealID = mealID;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

}
