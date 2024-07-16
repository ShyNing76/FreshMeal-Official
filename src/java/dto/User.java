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
public class User implements Serializable {

    private int userID;
    private String firstName;
    private String lastName;
    private String userName;
    private Date dateOfBirth;
    private String gender;
    private String email;
    private String phone;
    private String password;
    private String image;
    private int roleID;
    private int status;
    private ArrayList<User_Address> listOfAddress;
    private int orderCount;

    public User() {
        listOfAddress = new ArrayList<>();
    }

    public User(int userID, String firstName, String lastName, String email, String phone) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public User(int userID, String firstName, String lastName, String image, int orderCount) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
        this.orderCount = orderCount;
    }

    public User(int userID, String firstName, String lastName, String userName, Date dateOfBirth, String gender, String email, String phone, String password, String image, int roleID, int status, ArrayList<User_Address> listOfAddress) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.image = image;
        this.roleID = roleID;
        this.status = status;
        this.listOfAddress = listOfAddress;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<User_Address> getListOfAddress() {
        return listOfAddress;
    }

    public void setListOfAddress(ArrayList<User_Address> listOfAddress) {
        this.listOfAddress = listOfAddress;
    }

    public void addAddress(User_Address address) {
        listOfAddress.add(address);
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

}
