/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class Product implements Serializable{

    private int productID;
    private String productName;
    private String image;
    private int price;
    private String title;
    private String description;
    private String cookingTime;
    private int status;
    private String mealType;
    private ArrayList<BoxIngredient> listIngredient;
    private ArrayList<Product_Category> listOfCategory;
    private ArrayList<Recipe_Product> listOfRecipe;
    private int totalSold;

    public Product() {
        listIngredient = new ArrayList<>();
        listOfCategory = new ArrayList<>();
        listOfRecipe = new ArrayList<>();
    }

    public Product(int productID, String productName, String image) {
        this.productID = productID;
        this.productName = productName;
        this.image = image;
    }

    public Product(int productID, String productName, String image, int price, String title, String description, String cookingTime, int status, ArrayList<BoxIngredient> listIngredient, ArrayList<Product_Category> listOfCategory, ArrayList<Recipe_Product> listOfRecipe) {
        this.productID = productID;
        this.productName = productName;
        this.image = image;
        this.price = price;
        this.title = title;
        this.description = description;
        this.cookingTime = cookingTime;
        this.status = status;
        this.listIngredient = listIngredient;
        this.listOfCategory = listOfCategory;
        this.listOfRecipe = listOfRecipe;
    }

    public Product(int productID, String productName, String image, int price, int totalSold) {
        this.productID = productID;
        this.productName = productName;
        this.image = image;
        this.price = price;
        this.totalSold = totalSold;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(String cookingTime) {
        this.cookingTime = cookingTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<BoxIngredient> getListIngredient() {
        return listIngredient;
    }

    public void setListIngredient(ArrayList<BoxIngredient> listIngredient) {
        this.listIngredient = listIngredient;
    }

    public ArrayList<Product_Category> getListOfCategory() {
        return listOfCategory;
    }

    public void setListOfCategory(ArrayList<Product_Category> listOfCategory) {
        this.listOfCategory = listOfCategory;
    }

    public ArrayList<Recipe_Product> getListOfRecipe() {
        return listOfRecipe;
    }

    public void setListOfRecipe(ArrayList<Recipe_Product> listOfRecipe) {
        this.listOfRecipe = listOfRecipe;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public void addBoxIngredient(BoxIngredient boxIngredient) {
        listIngredient.add(boxIngredient);
    }

    public void addCategory(Product_Category category) {
        listOfCategory.add(category);
    }

    public void addRecipe(Recipe_Product recipe) {
        listOfRecipe.add(recipe);
    }

    public int getTotalSold() {
        return totalSold;
    }

    public void setTotalSold(int totalSold) {
        this.totalSold = totalSold;
    }
}
