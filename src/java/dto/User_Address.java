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
public class User_Address implements Serializable {

    private int user_Address_ID;
    private int addressID;
    private int userID;

    public User_Address() {
    }

    public User_Address(int user_Address_ID, int addressID, int userID) {
        this.user_Address_ID = user_Address_ID;
        this.addressID = addressID;
        this.userID = userID;
    }

    public int getUser_Address_ID() {
        return user_Address_ID;
    }

    public void setUser_Address_ID(int user_Address_ID) {
        this.user_Address_ID = user_Address_ID;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

}
