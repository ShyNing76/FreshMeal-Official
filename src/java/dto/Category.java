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
public class Category implements Serializable {

    private int categoryID;
    private String categoryName;
    private String image;
    private int status;
    private ArrayList<Product_Category> listOfProduct;

    public Category() {
        listOfProduct = new ArrayList<>();
    }

    public Category(int categoryID, String categoryName, String image, int status, ArrayList<Product_Category> listOfProduct) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.image = image;
        this.status = status;
        this.listOfProduct = listOfProduct;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public ArrayList<Product_Category> getListOfProduct() {
        return listOfProduct;
    }

    public void setListOfProduct(ArrayList<Product_Category> listOfProduct) {
        this.listOfProduct = listOfProduct;
    }

    public void addProduct(Product_Category pc) {
        listOfProduct.add(pc);
    }

}
