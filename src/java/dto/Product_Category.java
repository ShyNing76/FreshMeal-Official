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
public class Product_Category implements Serializable {

    private int product_Category_ID;
    private int productID;
    private int categoryID;

    public Product_Category() {
    }

    public Product_Category(int product_Category_ID, int productID, int categoryID) {
        this.product_Category_ID = product_Category_ID;
        this.productID = productID;
        this.categoryID = categoryID;
    }

    public int getProduct_Category_ID() {
        return product_Category_ID;
    }

    public void setProduct_Category_ID(int product_Category_ID) {
        this.product_Category_ID = product_Category_ID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

}
