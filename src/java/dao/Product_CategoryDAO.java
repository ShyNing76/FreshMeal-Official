/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.BoxIngredient;
import dto.Product;
import dto.Product_Category;
import dto.Recipe_Product;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import utils.DBContext;

/**
 *
 * @author ADMIN
 */
public class Product_CategoryDAO implements Serializable {

    private final String GET_PRODUCT_BY_CATEGORY_NAME = "SELECT p.ProductID, p.ProductName, p.Image, p.Price, p.Quantity, p.Title, p.Description, p.CookingTime, p.RecipeProductID, p.Status\n"
            + "FROM [dbo].[tb_Product_Category] pc INNER JOIN [dbo].[tb_Product] p ON pc.ProductID = p.ProductID\n"
            + "									INNER JOIN [dbo].[tb_Category] c ON pc.CategoryID = c.CategoryID\n"
            + "WHERE c.CategoryName LIKE ? AND p.Status = '1' AND c.Status = '1' AND p.ProductID IN (SELECT p.[ProductID]\n"
            + "FROM [dbo].[tb_Weekly_Plan_Product] wpp INNER JOIN [dbo].[tb_Product] p ON wpp.ProductID = p.ProductID\n"
            + "										INNER JOIN [dbo].[tb_Weekly_Plan] wp ON wpp.Weekly_Plan_ID = wp.Weekly_Plan_ID\n"
            + "WHERE p.Status = 1 AND wp.Status = 1)";
    private final String GET_TOP_6_PRODUCT_BY_CATEGORY_NAME = "SELECT TOP 6 p.ProductID, p.ProductName, p.Image, p.Price, p.Quantity, p.Title, p.Description, p.CookingTime, p.RecipeProductID, p.Status\n"
            + "FROM [dbo].[tb_Product_Category] pc INNER JOIN [dbo].[tb_Product] p ON pc.ProductID = p.ProductID\n"
            + "									INNER JOIN [dbo].[tb_Category] c ON pc.CategoryID = c.CategoryID\n"
            + "WHERE c.CategoryName LIKE ? AND p.Status = '1' AND c.Status = '1' AND p.ProductID IN (SELECT p.[ProductID]\n"
            + "FROM [dbo].[tb_Weekly_Plan_Product] wpp INNER JOIN [dbo].[tb_Product] p ON wpp.ProductID = p.ProductID\n"
            + "										INNER JOIN [dbo].[tb_Weekly_Plan] wp ON wpp.Weekly_Plan_ID = wp.Weekly_Plan_ID\n"
            + "WHERE p.Status = 1 AND wp.Status = 1)";
    private final String GET_BOXINGREDIENT_BY_PRODUCT = "SELECT [BoxIngredient_ID],[ProductID],[IngredientID],[Quantity],[Unit]\n"
            + "FROM [dbo].[tb_BoxIngredient]\n"
            + "WHERE [ProductID] = ?";
    private final String GET_CATEGORY_BY_PRODUCT = "SELECT [Product_Category_ID],[ProductID],[CategoryID]\n"
            + "FROM [dbo].[tb_Product_Category]\n"
            + "WHERE [ProductID] = ?";
    private final String GET_RECIPE_BY_PRODUCT = "SELECT [RecipeProductID],[ProductID],[Step],[Instruction],[Image]\n"
            + "FROM tb_Recipe_Product\n"
            + "WHERE ProductID = ?";
    private final String INSERT_CATEGORY_PRODUCT = "INSERT INTO tb_Product_Category(ProductID, CategoryID) VALUES (?,?)";

    public ArrayList<Product> getTop6ProductByCategoryName(String categoryName) {
        ArrayList<Product> listOfProductInCategory = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst1 = cn.prepareStatement(GET_TOP_6_PRODUCT_BY_CATEGORY_NAME);
                pst1.setString(1, categoryName);
                ResultSet rs1 = pst1.executeQuery();
                if (rs1 != null) {
                    while (rs1.next()) {
                        Product p = new Product();
                        p.setProductID(rs1.getInt("ProductID"));
                        p.setProductName(rs1.getString("ProductName"));
                        p.setImage(rs1.getString("Image"));
                        p.setPrice(rs1.getInt("Price"));
                        p.setDescription(rs1.getString("Description"));
                        p.setCookingTime(rs1.getString("CookingTime"));
                        p.setStatus(rs1.getInt("Status"));
                        PreparedStatement pst2 = cn.prepareStatement(GET_BOXINGREDIENT_BY_PRODUCT);
                        pst2.setInt(1, p.getProductID());
                        ResultSet rs2 = pst2.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                BoxIngredient ingredient = new BoxIngredient(rs2.getInt("BoxIngredient_ID"), p.getProductID(),
                                        rs2.getInt("IngredientID"), rs2.getString("Quantity"), rs2.getString("Unit"));
                                p.addBoxIngredient(ingredient);
                            }
                        }

                        PreparedStatement pst3 = cn.prepareStatement(GET_CATEGORY_BY_PRODUCT);
                        pst3.setInt(1, p.getProductID());
                        ResultSet rs3 = pst3.executeQuery();
                        if (rs3 != null) {
                            while (rs3.next()) {
                                Product_Category pc = new Product_Category(rs3.getInt("Product_Category_ID"), p.getProductID(),
                                        rs3.getInt("CategoryID"));
                                p.addCategory(pc);
                            }
                        }

                        PreparedStatement pst4 = cn.prepareStatement(GET_RECIPE_BY_PRODUCT);
                        pst4.setInt(1, p.getProductID());
                        ResultSet rs4 = pst4.executeQuery();
                        if (rs4 != null) {
                            while (rs4.next()) {
                                Recipe_Product rp = new Recipe_Product(rs4.getInt("RecipeProductID"), rs4.getInt("ProductID"), rs4.getInt("Step"), rs4.getString("Instruction"));
                                p.addRecipe(rp);
                            }
                        }
                        listOfProductInCategory.add(p);
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
        return listOfProductInCategory;
    }

    public int insertCategoryProduct(int productId, int categoryId) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(INSERT_CATEGORY_PRODUCT);
                pst.setInt(1, productId);
                pst.setInt(2, categoryId);
                rs = pst.executeUpdate();
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
        return rs;
    }
}
