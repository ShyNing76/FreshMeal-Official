/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Category;
import dto.Product_Category;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import utils.DBContext;

/**
 *
 * @author ADMIN
 */
public class CategoryDAO implements Serializable {

    private final String GET_ALL_CATEGORY = "SELECT CategoryID, CategoryName, Image, Status\n"
            + "FROM tb_Category";
    private final String GET_PRODUCT_BY_CATEGORY = "SELECT [Product_Category_ID],[ProductID],[CategoryID]\n"
            + "FROM [dbo].[tb_Product_Category]\n"
            + "WHERE [CategoryID] = ?";
    private final String GET_CATEGORY_BY_PRODUCT = "SELECT c.[CategoryID],c.[CategoryName],c.[Status]\n"
            + "FROM [dbo].[tb_Product_Category] pc INNER JOIN [dbo].[tb_Product] p ON pc.ProductID = p.ProductID\n"
            + "                                    INNER JOIN [dbo].[tb_Category] c ON pc.CategoryID = c.CategoryID\n"
            + "WHERE p.ProductID = ?";

    public ArrayList<Category> getCategory() {
        ArrayList<Category> listOfCategory = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                Statement st1 = cn.createStatement();
                ResultSet rs1 = st1.executeQuery(GET_ALL_CATEGORY);
                if (rs1 != null) {
                    while (rs1.next()) {
                        Category c = new Category();
                        c.setCategoryID(rs1.getInt("CategoryID"));
                        c.setCategoryName(rs1.getString("CategoryName"));
                        c.setImage(rs1.getString("Image"));
                        c.setStatus(rs1.getInt("Status"));
                        PreparedStatement pst = cn.prepareStatement(GET_PRODUCT_BY_CATEGORY);
                        pst.setInt(1, c.getCategoryID());
                        ResultSet rs2 = pst.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                Product_Category pc = new Product_Category(rs2.getInt("Product_Category_ID"),
                                        rs2.getInt("ProductID"),
                                        rs2.getInt("CategoryID"));
                                c.addProduct(pc);
                            }
                        }
                        listOfCategory.add(c);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listOfCategory;
    }

    public ArrayList<Category> getCategoryByProduct(int productID) {
        ArrayList<Category> listOfCategory = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_CATEGORY_BY_PRODUCT);
                pst.setInt(1, productID);
                ResultSet rs1 = pst.executeQuery();
                if (rs1 != null) {
                    while (rs1.next()) {
                        Category c = new Category();
                        c.setCategoryID(rs1.getInt("CategoryID"));
                        c.setCategoryName(rs1.getString("CategoryName"));
                        c.setStatus(rs1.getInt("Status"));
                        PreparedStatement pst2 = cn.prepareStatement(GET_PRODUCT_BY_CATEGORY);
                        pst2.setInt(1, c.getCategoryID());
                        ResultSet rs2 = pst2.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                Product_Category pc = new Product_Category(rs2.getInt("Product_Category_ID"),
                                        rs2.getInt("ProductID"),
                                        rs2.getInt("CategoryID"));
                                c.addProduct(pc);
                            }
                        }
                        listOfCategory.add(c);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listOfCategory;
    }

    public Map<Integer, String> getCategoryMap() {
        Map<Integer, String> categoryMap = new HashMap<>();
        Connection cn = null;

        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                Statement st1 = cn.createStatement();
                ResultSet rs1 = st1.executeQuery(GET_ALL_CATEGORY);
                if (rs1 != null) {
                    while (rs1.next()) {
                        int categoryID = rs1.getInt("CategoryID");
                        String categoryName = rs1.getString("CategoryName");
                        categoryMap.put(categoryID, categoryName);
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return categoryMap;
    }

}
