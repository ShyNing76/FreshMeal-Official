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
public class Recipe_Product implements Serializable {

    private int recipeProductID;
    private int productID;
    private int step;
    private String instruction;

    public Recipe_Product() {
    }

    public Recipe_Product(int recipeProductID, int productID, int step, String instruction) {
        this.recipeProductID = recipeProductID;
        this.productID = productID;
        this.step = step;
        this.instruction = instruction;
    }

    public Recipe_Product(int step, String instruction) {
        this.step = step;
        this.instruction = instruction;
    }

    public int getRecipeProductID() {
        return recipeProductID;
    }

    public void setRecipeProductID(int recipeProductID) {
        this.recipeProductID = recipeProductID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

}
