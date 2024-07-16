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
public class BoxIngredient implements Serializable {

    private int boxIngredient_ID;
    private int productID;
    private int ingredientID;
    private String quantity;
    private String unit;

    public BoxIngredient() {
    }

    public BoxIngredient(int boxIngredient_ID, int productID, int ingredientID, String quantity, String unit) {
        this.boxIngredient_ID = boxIngredient_ID;
        this.productID = productID;
        this.ingredientID = ingredientID;
        this.quantity = quantity;
        this.unit = unit;
    }

    public BoxIngredient(int ingredientID, String quantity, String unit) {
        this.ingredientID = ingredientID;
        this.quantity = quantity;
        this.unit = unit;
    }

    public int getBoxIngredient_ID() {
        return boxIngredient_ID;
    }

    public void setBoxIngredient_ID(int boxIngredient_ID) {
        this.boxIngredient_ID = boxIngredient_ID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getIngredientID() {
        return ingredientID;
    }

    public void setIngredientID(int ingredientID) {
        this.ingredientID = ingredientID;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}
