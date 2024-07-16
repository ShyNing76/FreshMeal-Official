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
public class Personal_Plan implements Serializable {

    private int personal_Plan_ID;
    private int weekly_Plan_Product_ID;
    private Date dayPick;
    private int mealID;
    private String type;
    private int quantity;
    private int userID;
    private ArrayList<Weekly_Plan_Product> listOfProduct;

    public Personal_Plan() {
        personal_Plan_ID = 0;
        weekly_Plan_Product_ID = 0;
        dayPick = new Date(System.currentTimeMillis());
        mealID = 0;
        type = "";
        quantity = 0;
        userID = 0;
        listOfProduct = new ArrayList<>();
    }

    public Personal_Plan(int personal_Plan_ID, int weekly_Plan_Product_ID, Date dayPick, int mealID, String type, int quantity, int userID, ArrayList<Weekly_Plan_Product> listOfProduct) {
        this.personal_Plan_ID = personal_Plan_ID;
        this.weekly_Plan_Product_ID = weekly_Plan_Product_ID;
        this.dayPick = dayPick;
        this.mealID = mealID;
        this.type = type;
        this.quantity = quantity;
        this.userID = userID;
        this.listOfProduct = listOfProduct;
    }

    public int getPersonal_Plan_ID() {
        return personal_Plan_ID;
    }

    public void setPersonal_Plan_ID(int personal_Plan_ID) {
        this.personal_Plan_ID = personal_Plan_ID;
    }

    public int getWeekly_Plan_Product_ID() {
        return weekly_Plan_Product_ID;
    }

    public void setWeekly_Plan_Product_ID(int weekly_Plan_Product_ID) {
        this.weekly_Plan_Product_ID = weekly_Plan_Product_ID;
    }

    public Date getDayPick() {
        return dayPick;
    }

    public void setDayPick(Date dayPick) {
        this.dayPick = dayPick;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getMealID() {
        return mealID;
    }

    public void setMealID(int mealID) {
        this.mealID = mealID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ArrayList<Weekly_Plan_Product> getListOfProduct() {
        return listOfProduct;
    }

    public void setListOfProduct(ArrayList<Weekly_Plan_Product> listOfProduct) {
        this.listOfProduct = listOfProduct;
    }

    public void addProductToList(Weekly_Plan_Product product) {
        listOfProduct.add(product);
    }

//    public ArrayList<Integer> getProductIDsForMeal(int mealIdFront) {
//        ArrayList<Integer> productIDs = new ArrayList<>();
//        for (Weekly_Plan_Product weekly_Plan_Product : listOfProduct) {
//            if (this.mealID == mealIdFront) {
//                productIDs.add(weekly_Plan_Product.getProductID());
//            }
//        }
//        return productIDs;
//    }

}
