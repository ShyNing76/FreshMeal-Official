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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import utils.DBContext;

/**
 *
 * @author ADMIN
 */
public class ProductDAO implements Serializable {

    private final String GET_ALL_PRODUCT = "SELECT [ProductID],[ProductName],[Image],[Price],[Title],[Description],[CookingTime],[Status]\n"
            + "FROM [dbo].[tb_Product]";
    private final String GET_PRODUCT_BY_NAME = "SELECT [ProductID],[ProductName],[Image],[Price],[Title],[Description],[CookingTime],[Status]\n"
            + "FROM [dbo].[tb_Product]\n"
            + "WHERE [ProductName] LIKE ?";
    private final String GET_PRODUCT_BY_ID = "SELECT [ProductID],[ProductName],[Image],[Price],[Title],[Description],[CookingTime],[Status]\n"
            + "FROM [dbo].[tb_Product]\n"
            + "WHERE [ProductID] = ?";
    private final String GET_PRODUCT_BY_DATE_IN_WEEKLY_PLAN = "SELECT [ProductID],[ProductName],[Image],[Price],[Title],[Description],[CookingTime],[Status]\n"
            + "FROM [dbo].[tb_Product]\n"
            + "WHERE [ProductID] IN (SELECT DISTINCT p.ProductID\n"
            + "FROM [dbo].[tb_Weekly_Plan_Product] wpp INNER JOIN [dbo].[tb_Weekly_Plan] wp ON wp.Weekly_Plan_ID = wpp.Weekly_Plan_ID\n"
            + "                                        INNER JOIN [dbo].[tb_Product] p ON wpp.ProductID = p.ProductID\n"
            + "WHERE wp.Status = 1 AND wp.StartDate <= ? AND wp.EndDate >= ?)";
    private final String INSERT_PRODUCT = "INSERT INTO [dbo].[tb_Product] ([ProductName],[Image],[Price],[Description],[CookingTime],[Status]) VALUES (?,?,?,?,?,1)";
    private final String UPDATE_STATUS_PRODUCT = "UPDATE [dbo].[tb_Product]\n"
            + "SET [Status] = ? \n"
            + "WHERE [ProductID] = ?";
    private final String GET_BOXINGREDIENT_BY_PRODUCT = "SELECT [BoxIngredient_ID],[ProductID],[IngredientID],[Quantity],[Unit]\n"
            + "FROM [dbo].[tb_BoxIngredient]\n"
            + "WHERE [ProductID] = ?";
    private final String GET_CATEGORY_BY_PRODUCT = "SELECT [Product_Category_ID],[ProductID],[CategoryID]\n"
            + "FROM [dbo].[tb_Product_Category]\n"
            + "WHERE [ProductID] = ?";
    private final String GET_RECIPE_BY_PRODUCT = "SELECT [RecipeProductID],[ProductID],[Step],[Instruction]\n"
            + "FROM tb_Recipe_Product\n"
            + "WHERE ProductID = ?";
    private final String GET_PRODUCT_BY_CATEGORY_WITH_ALL_TIME_SELECTION = "SELECT p.ProductID, p.ProductName, p.Image, p.Price, p.Title, p.Description, p.CookingTime, p.Status\n"
            + "FROM [dbo].[tb_Product_Category] pc INNER JOIN [dbo].[tb_Product] p ON pc.ProductID = p.ProductID\n"
            + "                                    INNER JOIN [dbo].[tb_Category] c ON pc.CategoryID = c.CategoryID\n"
            + "WHERE c.CategoryName = ? AND p.CookingTime >= 10 AND p.Status = '1' AND c.Status = '1' AND p.ProductID IN (SELECT p.[ProductID]\n"
            + "FROM [dbo].[tb_Weekly_Plan_Product] wpp INNER JOIN [dbo].[tb_Product] p ON wpp.ProductID = p.ProductID\n"
            + "                                        INNER JOIN [dbo].[tb_Weekly_Plan] wp ON wpp.Weekly_Plan_ID = wp.Weekly_Plan_ID\n"
            + "WHERE wp.Weekly_Plan_ID = ? AND p.Status = 1 AND wp.Status = 1)";
    private final String GET_PRODUCT_WITH_ALL_TIME_SELECTION = "SELECT p.ProductID, p.ProductName, p.Image, p.Price, p.Title, p.Description, p.CookingTime, p.Status\n"
            + "FROM [dbo].[tb_Product_Category] pc INNER JOIN [dbo].[tb_Product] p ON pc.ProductID = p.ProductID\n"
            + "                                    INNER JOIN [dbo].[tb_Category] c ON pc.CategoryID = c.CategoryID\n"
            + "WHERE p.CookingTime >= 10 AND p.Status = '1' AND c.Status = '1' AND p.ProductID IN (SELECT p.[ProductID]\n"
            + "FROM [dbo].[tb_Weekly_Plan_Product] wpp INNER JOIN [dbo].[tb_Product] p ON wpp.ProductID = p.ProductID\n"
            + "                                        INNER JOIN [dbo].[tb_Weekly_Plan] wp ON wpp.Weekly_Plan_ID = wp.Weekly_Plan_ID\n"
            + "WHERE wp.Weekly_Plan_ID = ? AND p.Status = 1 AND wp.Status = 1)";
    private final String GET_PRODUCT_BY_CATEGORY_WITH_ALL_PRICE_SELECTION = "SELECT p.ProductID, p.ProductName, p.Image, p.Price, p.Title, p.Description, p.CookingTime, p.Status\n"
            + "FROM [dbo].[tb_Product_Category] pc INNER JOIN [dbo].[tb_Product] p ON pc.ProductID = p.ProductID\n"
            + "                                    INNER JOIN [dbo].[tb_Category] c ON pc.CategoryID = c.CategoryID\n"
            + "WHERE c.CategoryName = ? AND p.Price >= 20 AND p.Status = '1' AND c.Status = '1' AND p.ProductID IN (SELECT p.[ProductID]\n"
            + "FROM [dbo].[tb_Weekly_Plan_Product] wpp INNER JOIN [dbo].[tb_Product] p ON wpp.ProductID = p.ProductID\n"
            + "                                        INNER JOIN [dbo].[tb_Weekly_Plan] wp ON wpp.Weekly_Plan_ID = wp.Weekly_Plan_ID\n"
            + "WHERE wp.Weekly_Plan_ID = ? AND p.Status = 1 AND wp.Status = 1)";
    private final String GET_PRODUCT_WITH_ALL_PRICE_SELECTION = "SELECT p.ProductID, p.ProductName, p.Image, p.Price, p.Title, p.Description, p.CookingTime, p.Status\n"
            + "FROM [dbo].[tb_Product_Category] pc INNER JOIN [dbo].[tb_Product] p ON pc.ProductID = p.ProductID\n"
            + "                                    INNER JOIN [dbo].[tb_Category] c ON pc.CategoryID = c.CategoryID\n"
            + "WHERE p.Price >= 20 AND p.Status = '1' AND c.Status = '1' AND p.ProductID IN (SELECT p.[ProductID]\n"
            + "FROM [dbo].[tb_Weekly_Plan_Product] wpp INNER JOIN [dbo].[tb_Product] p ON wpp.ProductID = p.ProductID\n"
            + "                                        INNER JOIN [dbo].[tb_Weekly_Plan] wp ON wpp.Weekly_Plan_ID = wp.Weekly_Plan_ID\n"
            + "WHERE wp.Weekly_Plan_ID = ? AND p.Status = 1 AND wp.Status = 1)";
    private final String GET_PRODUCT_BY_CATEGORY_WITH_PRICE_SELECTION = "SELECT p.ProductID, p.ProductName, p.Image, p.Price, p.Title, p.Description, p.CookingTime, p.Status\n"
            + "FROM [dbo].[tb_Product_Category] pc INNER JOIN [dbo].[tb_Product] p ON pc.ProductID = p.ProductID\n"
            + "					   INNER JOIN [dbo].[tb_Category] c ON pc.CategoryID = c.CategoryID\n"
            + "WHERE c.CategoryName = ? AND p.Price BETWEEN ? AND ? AND p.Status = '1' AND c.Status = '1' AND p.ProductID IN (SELECT p.[ProductID]\n"
            + "FROM [dbo].[tb_Weekly_Plan_Product] wpp INNER JOIN [dbo].[tb_Product] p ON wpp.ProductID = p.ProductID\n"
            + "					       INNER JOIN [dbo].[tb_Weekly_Plan] wp ON wpp.Weekly_Plan_ID = wp.Weekly_Plan_ID\n"
            + "WHERE wp.Weekly_Plan_ID = ? AND p.Status = 1 AND wp.Status = 1)";
    private final String GET_PRODUCT_WITH_PRICE_SELECTION = "SELECT p.ProductID, p.ProductName, p.Image, p.Price, p.Title, p.Description, p.CookingTime, p.Status\n"
            + "FROM [dbo].[tb_Weekly_Plan_Product] wpp INNER JOIN [dbo].[tb_Product] p ON wpp.ProductID = p.ProductID\n"
            + "                                        INNER JOIN [dbo].[tb_Weekly_Plan] wp ON wpp.Weekly_Plan_ID = wp.Weekly_Plan_ID\n"
            + "WHERE p.Price BETWEEN ? AND ?  AND wp.Weekly_Plan_ID = ? AND p.Status = '1'";
    private final String GET_PRODUCT_BY_CATEGORY_WITH_TIME_SELECTION = "SELECT p.ProductID, p.ProductName, p.Image, p.Price, p.Title, p.Description, p.CookingTime, p.Status\n"
            + "FROM [dbo].[tb_Product_Category] pc INNER JOIN [dbo].[tb_Product] p ON pc.ProductID = p.ProductID\n"
            + "					   INNER JOIN [dbo].[tb_Category] c ON pc.CategoryID = c.CategoryID\n"
            + "WHERE c.CategoryName LIKE ? AND p.CookingTime BETWEEN ? AND ? AND p.Status = '1' AND c.Status = '1' AND p.ProductID IN (SELECT p.[ProductID]\n"
            + "FROM [dbo].[tb_Weekly_Plan_Product] wpp INNER JOIN [dbo].[tb_Product] p ON wpp.ProductID = p.ProductID\n"
            + "					       INNER JOIN [dbo].[tb_Weekly_Plan] wp ON wpp.Weekly_Plan_ID = wp.Weekly_Plan_ID\n"
            + "WHERE p.Status = 1 AND wp.Status = 1)";
    private final String GET_PRODUCT_WITH_TIME_SELECTION = "SELECT p.ProductID, p.ProductName, p.Image, p.Price, p.Title, p.Description, p.CookingTime, p.Status\n"
            + "FROM [dbo].[tb_Weekly_Plan_Product] wpp INNER JOIN [dbo].[tb_Product] p ON wpp.ProductID = p.ProductID\n"
            + "                                        INNER JOIN [dbo].[tb_Weekly_Plan] wp ON wpp.Weekly_Plan_ID = wp.Weekly_Plan_ID\n"
            + "WHERE p.CookingTime BETWEEN ? AND ? AND wp.Weekly_Plan_ID = ? AND p.Status = '1'";
    private final String GET_PRODUCT_BY_CATEGORY_WITH_TOP_PRICE = "SELECT p.ProductID, p.ProductName, p.Image, p.Price, p.Title, p.Description, p.CookingTime, p.Status\n"
            + "FROM [dbo].[tb_Product_Category] pc INNER JOIN [dbo].[tb_Product] p ON pc.ProductID = p.ProductID\n"
            + "					   INNER JOIN [dbo].[tb_Category] c ON pc.CategoryID = c.CategoryID\n"
            + "WHERE c.CategoryName = ? AND p.Price >= 50000 AND p.Status = '1' AND p.ProductID IN (SELECT p.[ProductID]\n"
            + "FROM [dbo].[tb_Weekly_Plan_Product] wpp INNER JOIN [dbo].[tb_Product] p ON wpp.ProductID = p.ProductID\n"
            + "					       INNER JOIN [dbo].[tb_Weekly_Plan] wp ON wpp.Weekly_Plan_ID = wp.Weekly_Plan_ID\n"
            + "WHERE wp.Weekly_Plan_ID = ? AND p.Status = 1 AND wp.Status = 1)";
    private final String GET_PRODUCT_WITH_TOP_PRICE = "SELECT p.ProductID, p.ProductName, p.Image, p.Price, p.Title, p.Description, p.CookingTime, p.Status\n"
            + "FROM [dbo].[tb_Weekly_Plan_Product] wpp INNER JOIN [dbo].[tb_Product] p ON wpp.ProductID = p.ProductID\n"
            + "										INNER JOIN [dbo].[tb_Weekly_Plan] wp ON wpp.Weekly_Plan_ID = wp.Weekly_Plan_ID\n"
            + "WHERE p.Price >= 50000 AND wp.Weekly_Plan_ID = ? AND p.Status = '1'";
    private final String GET_PRODUCT_BY_CATEGORY_WITH_TOP_TIME = "SELECT p.ProductID, p.ProductName, p.Image, p.Price, p.Title, p.Description, p.CookingTime, p.Status\n"
            + "FROM [dbo].[tb_Product_Category] pc INNER JOIN [dbo].[tb_Product] p ON pc.ProductID = p.ProductID\n"
            + "                                    INNER JOIN [dbo].[tb_Category] c ON pc.CategoryID = c.CategoryID\n"
            + "WHERE c.CategoryName LIKE ? AND p.CookingTime >= 30 AND p.Status = '1' AND c.Status = '1' AND p.ProductID IN (SELECT p.[ProductID]\n"
            + "FROM [dbo].[tb_Weekly_Plan_Product] wpp INNER JOIN [dbo].[tb_Product] p ON wpp.ProductID = p.ProductID\n"
            + "                                        INNER JOIN [dbo].[tb_Weekly_Plan] wp ON wpp.Weekly_Plan_ID = wp.Weekly_Plan_ID\n"
            + "WHERE p.Status = 1 AND wp.Status = 1)";
    private final String GET_PRODUCT_WITH_TOP_TIME = "SELECT p.ProductID, p.ProductName, p.Image, p.Price, p.Title, p.Description, p.CookingTime, p.Status\n"
            + "FROM [dbo].[tb_Weekly_Plan_Product] wpp INNER JOIN [dbo].[tb_Product] p ON wpp.ProductID = p.ProductID\n"
            + "										INNER JOIN [dbo].[tb_Weekly_Plan] wp ON wpp.Weekly_Plan_ID = wp.Weekly_Plan_ID\n"
            + "WHERE p.CookingTime >= 30 AND wp.Weekly_Plan_ID = ? AND p.Status = '1'";
    private final String GET_PRODUCT_MAP = "SELECT ProductID, ProductName\n"
            + "FROM tb_Product";
    private final String GET_PRODUCT_ORDER_MAP = "SELECT ProductID, ProductName, Image\n"
            + "FROM tb_Product";
    private final String INSERT_PRODUCT_INGREDIENT = "INSERT INTO tb_BoxIngredient(ProductID, IngredientID, Quantity, Unit) values (?,?,?,?)";
    private final String INSERT_PRODUCT_CATEGORY = "INSERT INTO tb_Product_Category(ProductID, CategoryID) VALUES (?,?)";
    private final String INSERT_PRODUCT_RECIPE = "INSERT INTO tb_Recipe_Product(ProductID, Step, Instruction) values (?,?,?)";
//    private final String GET_PRODUCT_BY_CATEGORY_WITH_SELECTION = "SELECT p.ProductID, p.ProductName, p.Image, p.Price, p.Title, p.Description, p.CookingTime, p.Status\n"
//            + "FROM [dbo].[tb_Product_Category] pc INNER JOIN [dbo].[tb_Product] p ON pc.ProductID = p.ProductID\n"
//            + "					   INNER JOIN [dbo].[tb_Category] c ON pc.CategoryID = c.CategoryID\n"
//            + "WHERE c.CategoryName = ? AND p.CookingTime BETWEEN ? AND ? AND p.Price BETWEEN ? AND ? AND p.Status = '1' AND c.Status = '1' AND p.ProductID IN (SELECT p.[ProductID]\n"
//            + "FROM [dbo].[tb_Weekly_Plan_Product] wpp INNER JOIN [dbo].[tb_Product] p ON wpp.ProductID = p.ProductID\n"
//            + "					       INNER JOIN [dbo].[tb_Weekly_Plan] wp ON wpp.Weekly_Plan_ID = wp.Weekly_Plan_ID\n"
//            + "WHERE p.Status = 1 AND wp.Status = 1)";

    public ArrayList<Product> getProduct() {
        ArrayList<Product> listOfProduct = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                Statement st1 = cn.createStatement();
                ResultSet rs1 = st1.executeQuery(GET_ALL_PRODUCT);
                if (rs1 != null) {
                    while (rs1.next()) {
                        Product p = new Product();
                        p.setProductID(rs1.getInt("ProductID"));
                        p.setProductName(rs1.getString("ProductName"));
                        p.setImage(rs1.getString("Image"));
                        p.setPrice(rs1.getInt("Price"));
                        p.setTitle(rs1.getString("Title"));
                        p.setDescription(rs1.getString("Description"));
                        p.setCookingTime(rs1.getString("CookingTime"));
                        p.setStatus(rs1.getInt("Status"));
                        PreparedStatement pst1 = cn.prepareStatement(GET_BOXINGREDIENT_BY_PRODUCT);
                        pst1.setInt(1, p.getProductID());
                        ResultSet rs2 = pst1.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                BoxIngredient ingredient = new BoxIngredient(rs2.getInt("BoxIngredient_ID"), p.getProductID(),
                                        rs2.getInt("IngredientID"), rs2.getString("Quantity"), rs2.getString("Unit"));
                                p.addBoxIngredient(ingredient);
                            }
                        }
                        PreparedStatement pst2 = cn.prepareStatement(GET_CATEGORY_BY_PRODUCT);
                        pst2.setInt(1, p.getProductID());
                        ResultSet rs3 = pst2.executeQuery();
                        if (rs3 != null) {
                            while (rs3.next()) {
                                Product_Category pc = new Product_Category(rs3.getInt("Product_Category_ID"), p.getProductID(),
                                        rs3.getInt("CategoryID"));
                                p.addCategory(pc);
                            }
                        }
                        PreparedStatement pst3 = cn.prepareStatement(GET_RECIPE_BY_PRODUCT);
                        pst3.setInt(1, p.getProductID());
                        ResultSet rs4 = pst3.executeQuery();
                        if (rs4 != null) {
                            while (rs4.next()) {
                                Recipe_Product rp = new Recipe_Product(rs4.getInt("RecipeProductID"), rs4.getInt("ProductID"), rs4.getInt("Step"), rs4.getString("Instruction"));
                                p.addRecipe(rp);
                            }
                        }
                        listOfProduct.add(p);
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
        return listOfProduct;
    }

    public ArrayList<Product> getProductByName(String productName) {
        ArrayList<Product> listOfProduct = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_PRODUCT_BY_NAME);
                pst.setString(1, "%" + productName + "%");
                ResultSet rs1 = pst.executeQuery();
                if (rs1 != null) {
                    while (rs1.next()) {
                        Product p = new Product();
                        p.setProductID(rs1.getInt("ProductID"));
                        p.setProductName(rs1.getString("ProductName"));
                        p.setImage(rs1.getString("Image"));
                        p.setPrice(rs1.getInt("Price"));
                        p.setTitle(rs1.getString("Title"));
                        p.setDescription(rs1.getString("Description"));
                        p.setCookingTime(rs1.getString("CookingTime"));
                        p.setStatus(rs1.getInt("Status"));
                        PreparedStatement pst1 = cn.prepareStatement(GET_BOXINGREDIENT_BY_PRODUCT);
                        pst1.setInt(1, p.getProductID());
                        ResultSet rs2 = pst1.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                BoxIngredient ingredient = new BoxIngredient(rs2.getInt("BoxIngredient_ID"), p.getProductID(),
                                        rs2.getInt("IngredientID"), rs2.getString("Quantity"), rs2.getString("Unit"));
                                p.addBoxIngredient(ingredient);
                            }
                        }
                        PreparedStatement pst2 = cn.prepareStatement(GET_CATEGORY_BY_PRODUCT);
                        pst2.setInt(1, p.getProductID());
                        ResultSet rs3 = pst2.executeQuery();
                        if (rs3 != null) {
                            while (rs3.next()) {
                                Product_Category pc = new Product_Category(rs3.getInt("Product_Category_ID"), p.getProductID(),
                                        rs3.getInt("CategoryID"));
                                p.addCategory(pc);
                            }
                        }
                        PreparedStatement pst3 = cn.prepareStatement(GET_RECIPE_BY_PRODUCT);
                        pst3.setInt(1, p.getProductID());
                        ResultSet rs4 = pst3.executeQuery();
                        if (rs4 != null) {
                            while (rs4.next()) {
                                Recipe_Product rp = new Recipe_Product(rs4.getInt("RecipeProductID"), rs4.getInt("ProductID"), rs4.getInt("Step"), rs4.getString("Instruction"));
                                p.addRecipe(rp);
                            }
                        }
                        listOfProduct.add(p);
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
        return listOfProduct;
    }

    public Product getProductByID(int productID) {
        Product p = null;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_PRODUCT_BY_ID);
                pst.setInt(1, productID);
                ResultSet rs1 = pst.executeQuery();
                if (rs1 != null) {
                    while (rs1.next()) {
                        p = new Product();
                        p.setProductID(rs1.getInt("ProductID"));
                        p.setProductName(rs1.getString("ProductName"));
                        p.setImage(rs1.getString("Image"));
                        p.setPrice(rs1.getInt("Price"));
                        p.setTitle(rs1.getString("Title"));
                        p.setDescription(rs1.getString("Description"));
                        p.setCookingTime(rs1.getString("CookingTime"));
                        p.setStatus(rs1.getInt("Status"));
                        PreparedStatement pst1 = cn.prepareStatement(GET_BOXINGREDIENT_BY_PRODUCT);
                        pst1.setInt(1, p.getProductID());
                        ResultSet rs2 = pst1.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                BoxIngredient ingredient = new BoxIngredient(rs2.getInt("BoxIngredient_ID"), p.getProductID(),
                                        rs2.getInt("IngredientID"), rs2.getString("Quantity"), rs2.getString("Unit"));
                                p.addBoxIngredient(ingredient);
                            }
                        }
                        PreparedStatement pst2 = cn.prepareStatement(GET_CATEGORY_BY_PRODUCT);
                        pst2.setInt(1, p.getProductID());
                        ResultSet rs3 = pst2.executeQuery();
                        if (rs3 != null) {
                            while (rs3.next()) {
                                Product_Category pc = new Product_Category(rs3.getInt("Product_Category_ID"), p.getProductID(),
                                        rs3.getInt("CategoryID"));
                                p.addCategory(pc);
                            }
                        }
                        PreparedStatement pst3 = cn.prepareStatement(GET_RECIPE_BY_PRODUCT);
                        pst3.setInt(1, p.getProductID());
                        ResultSet rs4 = pst3.executeQuery();
                        if (rs4 != null) {
                            while (rs4.next()) {
                                Recipe_Product rp = new Recipe_Product(rs4.getInt("RecipeProductID"), rs4.getInt("ProductID"), rs4.getInt("Step"), rs4.getString("Instruction"));
                                p.addRecipe(rp);
                            }
                        }
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
        return p;
    }

    public int insertProduct(String name, String img, int price, String description, int cookingTime) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(INSERT_PRODUCT);
                pst.setString(1, name);
                pst.setString(2, img);
                pst.setInt(3, price);
                pst.setString(4, description);
                pst.setInt(5, cookingTime);
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

    public boolean insertProductDAO(String productName, String productDescription, int productPrice, int cookingTime, String imagePath, ArrayList<BoxIngredient> ingredients, ArrayList<Integer> categories, ArrayList<Recipe_Product> recipes) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean hasInsert = false;

        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                // Chèn sản phẩm mới vào bảng sản phẩm
                cn.setAutoCommit(false);
                pst = cn.prepareStatement(INSERT_PRODUCT, PreparedStatement.RETURN_GENERATED_KEYS);
                pst.setString(1, productName);
                pst.setString(2, imagePath);
                pst.setInt(3, productPrice);
                pst.setString(4, productDescription);
                pst.setInt(5, cookingTime);
                int affectedRows = pst.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Creating product failed, no rows affected.");
                }

                int productId;
                rs = pst.getGeneratedKeys();
                if (rs.next()) {
                    productId = rs.getInt(1);
                } else {
                    throw new SQLException("Creating product failed, no ID obtained.");
                }

                // Chèn các nguyên liệu liên quan đến sản phẩm
                pst = cn.prepareStatement(INSERT_PRODUCT_INGREDIENT);
                for (BoxIngredient ingredient : ingredients) {
                    pst.setInt(1, productId);
                    pst.setInt(2, ingredient.getIngredientID());
                    pst.setString(3, ingredient.getQuantity());
                    pst.setString(4, ingredient.getUnit());
                    pst.addBatch();
                }
                pst.executeBatch();

                pst = cn.prepareStatement(INSERT_PRODUCT_CATEGORY);
                for (Integer categoryid : categories) {
                    pst.setInt(1, productId);
                    pst.setInt(2, categoryid);
                    pst.addBatch();
                }
                pst.executeBatch();

                pst = cn.prepareStatement(INSERT_PRODUCT_RECIPE);
                for (Recipe_Product recipe : recipes) {
                    pst.setInt(1, productId);
                    pst.setInt(2, recipe.getStep());
                    pst.setString(3, recipe.getInstruction());
                    pst.addBatch();
                }
                pst.executeBatch();
                cn.commit();
                hasInsert = true;
                cn.setAutoCommit(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (cn != null) {
                    cn.rollback();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
            hasInsert = false;
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return hasInsert;
    }

    public int updateStatusProduct(int status, int productID) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(UPDATE_STATUS_PRODUCT);
                pst.setInt(1, status);
                pst.setInt(2, productID);
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

    public boolean updateProduct(int productId, String productName, String productDescription, int productPrice, int cookingTime, String imagePath, ArrayList<BoxIngredient> ingredients, ArrayList<Integer> categories, ArrayList<Recipe_Product> recipes) {
        Connection cn = null;
        PreparedStatement pst = null;
        boolean isUpdated = false;

        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                cn.setAutoCommit(false);
                String sqlUp1 = "UPDATE tb_Product\n"
                        + "SET ProductName = ?, Price = ?, CookingTime = ? , Description = ?, Image = ?\n"
                        + "WHERE ProductID = ?";
                // Cập nhật thông tin sản phẩm
                pst = cn.prepareStatement(sqlUp1);
                pst.setString(1, productName);
                pst.setInt(2, productPrice);
                pst.setInt(3, cookingTime);
                pst.setString(4, productDescription);
                pst.setString(5, imagePath);
                pst.setInt(6, productId);
                pst.executeUpdate();

                // Xóa danh mục cũ và thêm danh mục mới
                String sqlDel1 = "DELETE FROM tb_Product_Category\n"
                        + "WHERE ProductID = ?";
                pst = cn.prepareStatement(sqlDel1);
                pst.setInt(1, productId);
                pst.executeUpdate();

                String sqlUp2 = "INSERT INTO tb_Product_Category (ProductID,CategoryID) values (?,?)";
                pst = cn.prepareStatement(sqlUp2);
                for (Integer categoryId : categories) {
                    pst.setInt(1, productId);
                    pst.setInt(2, categoryId);
                    pst.addBatch();
                }
                pst.executeBatch();

                // Xóa các bước cũ và thêm các bước mới
                String sqlDel2 = "DELETE FROM tb_Recipe_Product\n"
                        + "WHERE ProductID = ?";
                pst = cn.prepareStatement(sqlDel2);
                pst.setInt(1, productId);
                pst.executeUpdate();

                String sqlUp3 = "INSERT INTO tb_Recipe_Product(ProductID, Step, Instruction) values (?,?,?)";
                pst = cn.prepareStatement(sqlUp3);
                for (Recipe_Product recipe_Product : recipes) {
                    pst.setInt(1, productId);
                    pst.setInt(2, recipe_Product.getStep());
                    pst.setString(3, recipe_Product.getInstruction());
                    pst.addBatch();
                }
                pst.executeBatch();

                // Xóa các thành phần cũ và thêm các thành phần mới
                String sqlDel3 = "DELETE FROM tb_BoxIngredient\n"
                        + "WHERE ProductID = ?";
                pst = cn.prepareStatement(sqlDel3);
                pst.setInt(1, productId);
                pst.executeUpdate();

                String sqlUp4 = "INSERT INTO tb_BoxIngredient(ProductID, IngredientID,Quantity, Unit) values (?,?,?,?)";
                pst = cn.prepareStatement(sqlUp4);
                for (BoxIngredient ingredient : ingredients) {
                    pst.setInt(1, productId);
                    pst.setInt(2, ingredient.getIngredientID());
                    pst.setString(3, ingredient.getQuantity());
                    pst.setString(4, ingredient.getUnit());
                    pst.addBatch();
                }
                pst.executeBatch();

                cn.commit();
                isUpdated = true;
                cn.setAutoCommit(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (cn != null) {
                    cn.rollback();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
            isUpdated = false;
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return isUpdated;
    }

    public ArrayList<Product> getProductInCategoryWithAllTimeSelection(String categoryName, int weeklyPlanId) {
        ArrayList<Product> listOfProductInCategory = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_PRODUCT_BY_CATEGORY_WITH_ALL_TIME_SELECTION);
                pst.setString(1, categoryName);
                pst.setInt(2, weeklyPlanId);
                ResultSet rs1 = pst.executeQuery();
                if (rs1 != null) {
                    while (rs1.next()) {
                        Product p = new Product();
                        p.setProductID(rs1.getInt("ProductID"));
                        p.setProductName(rs1.getString("ProductName"));
                        p.setImage(rs1.getString("Image"));
                        p.setPrice(rs1.getInt("Price"));
                        p.setTitle(rs1.getString("Title"));
                        p.setDescription(rs1.getString("Description"));
                        p.setCookingTime(rs1.getString("CookingTime"));
                        p.setStatus(rs1.getInt("Status"));
                        PreparedStatement pst1 = cn.prepareStatement(GET_BOXINGREDIENT_BY_PRODUCT);
                        pst1.setInt(1, p.getProductID());
                        ResultSet rs2 = pst1.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                BoxIngredient ingredient = new BoxIngredient(rs2.getInt("BoxIngredient_ID"), p.getProductID(),
                                        rs2.getInt("IngredientID"), rs2.getString("Quantity"), rs2.getString("Unit"));
                                p.addBoxIngredient(ingredient);
                            }
                        }
                        PreparedStatement pst2 = cn.prepareStatement(GET_CATEGORY_BY_PRODUCT);
                        pst2.setInt(1, p.getProductID());
                        ResultSet rs3 = pst2.executeQuery();
                        if (rs3 != null) {
                            while (rs3.next()) {
                                Product_Category pc = new Product_Category(rs3.getInt("Product_Category_ID"), p.getProductID(),
                                        rs3.getInt("CategoryID"));
                                p.addCategory(pc);
                            }
                        }
                        PreparedStatement pst3 = cn.prepareStatement(GET_RECIPE_BY_PRODUCT);
                        pst3.setInt(1, p.getProductID());
                        ResultSet rs4 = pst3.executeQuery();
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

    public ArrayList<Product> getProductWithAllTimeSelection(int weeklyPlanId) {
        ArrayList<Product> listOfProductInCategory = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_PRODUCT_WITH_ALL_TIME_SELECTION);
                pst.setInt(1, weeklyPlanId);
                ResultSet rs1 = pst.executeQuery();
                if (rs1 != null) {
                    while (rs1.next()) {
                        Product p = new Product();
                        p.setProductID(rs1.getInt("ProductID"));
                        p.setProductName(rs1.getString("ProductName"));
                        p.setImage(rs1.getString("Image"));
                        p.setPrice(rs1.getInt("Price"));
                        p.setTitle(rs1.getString("Title"));
                        p.setDescription(rs1.getString("Description"));
                        p.setCookingTime(rs1.getString("CookingTime"));
                        p.setStatus(rs1.getInt("Status"));
                        PreparedStatement pst1 = cn.prepareStatement(GET_BOXINGREDIENT_BY_PRODUCT);
                        pst1.setInt(1, p.getProductID());
                        ResultSet rs2 = pst1.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                BoxIngredient ingredient = new BoxIngredient(rs2.getInt("BoxIngredient_ID"), p.getProductID(),
                                        rs2.getInt("IngredientID"), rs2.getString("Quantity"), rs2.getString("Unit"));
                                p.addBoxIngredient(ingredient);
                            }
                        }
                        PreparedStatement pst2 = cn.prepareStatement(GET_CATEGORY_BY_PRODUCT);
                        pst2.setInt(1, p.getProductID());
                        ResultSet rs3 = pst2.executeQuery();
                        if (rs3 != null) {
                            while (rs3.next()) {
                                Product_Category pc = new Product_Category(rs3.getInt("Product_Category_ID"), p.getProductID(),
                                        rs3.getInt("CategoryID"));
                                p.addCategory(pc);
                            }
                        }
                        PreparedStatement pst3 = cn.prepareStatement(GET_RECIPE_BY_PRODUCT);
                        pst3.setInt(1, p.getProductID());
                        ResultSet rs4 = pst3.executeQuery();
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

    public ArrayList<Product> getProductInCategoryWithAllPriceSelection(String categoryName, int weeklyPlanId) {
        ArrayList<Product> listOfProductInCategory = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_PRODUCT_BY_CATEGORY_WITH_ALL_PRICE_SELECTION);
                pst.setString(1, categoryName);
                pst.setInt(2, weeklyPlanId);
                ResultSet rs1 = pst.executeQuery();
                if (rs1 != null) {
                    while (rs1.next()) {
                        Product p = new Product();
                        p.setProductID(rs1.getInt("ProductID"));
                        p.setProductName(rs1.getString("ProductName"));
                        p.setImage(rs1.getString("Image"));
                        p.setPrice(rs1.getInt("Price"));
                        p.setTitle(rs1.getString("Title"));
                        p.setDescription(rs1.getString("Description"));
                        p.setCookingTime(rs1.getString("CookingTime"));
                        p.setStatus(rs1.getInt("Status"));
                        PreparedStatement pst1 = cn.prepareStatement(GET_BOXINGREDIENT_BY_PRODUCT);
                        pst1.setInt(1, p.getProductID());
                        ResultSet rs2 = pst1.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                BoxIngredient ingredient = new BoxIngredient(rs2.getInt("BoxIngredient_ID"), p.getProductID(),
                                        rs2.getInt("IngredientID"), rs2.getString("Quantity"), rs2.getString("Unit"));
                                p.addBoxIngredient(ingredient);
                            }
                        }
                        PreparedStatement pst2 = cn.prepareStatement(GET_CATEGORY_BY_PRODUCT);
                        pst2.setInt(1, p.getProductID());
                        ResultSet rs3 = pst2.executeQuery();
                        if (rs3 != null) {
                            while (rs3.next()) {
                                Product_Category pc = new Product_Category(rs3.getInt("Product_Category_ID"), p.getProductID(),
                                        rs3.getInt("CategoryID"));
                                p.addCategory(pc);
                            }
                        }
                        PreparedStatement pst3 = cn.prepareStatement(GET_RECIPE_BY_PRODUCT);
                        pst3.setInt(1, p.getProductID());
                        ResultSet rs4 = pst3.executeQuery();
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

    public ArrayList<Product> getProductWithAllPriceSelection(int weeklyPlanId) {
        ArrayList<Product> listOfProductInCategory = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_PRODUCT_WITH_ALL_PRICE_SELECTION);
                pst.setInt(1, weeklyPlanId);
                ResultSet rs1 = pst.executeQuery();
                if (rs1 != null) {
                    while (rs1.next()) {
                        Product p = new Product();
                        p.setProductID(rs1.getInt("ProductID"));
                        p.setProductName(rs1.getString("ProductName"));
                        p.setImage(rs1.getString("Image"));
                        p.setPrice(rs1.getInt("Price"));
                        p.setTitle(rs1.getString("Title"));
                        p.setDescription(rs1.getString("Description"));
                        p.setCookingTime(rs1.getString("CookingTime"));
                        p.setStatus(rs1.getInt("Status"));
                        PreparedStatement pst1 = cn.prepareStatement(GET_BOXINGREDIENT_BY_PRODUCT);
                        pst1.setInt(1, p.getProductID());
                        ResultSet rs2 = pst1.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                BoxIngredient ingredient = new BoxIngredient(rs2.getInt("BoxIngredient_ID"), p.getProductID(),
                                        rs2.getInt("IngredientID"), rs2.getString("Quantity"), rs2.getString("Unit"));
                                p.addBoxIngredient(ingredient);
                            }
                        }
                        PreparedStatement pst2 = cn.prepareStatement(GET_CATEGORY_BY_PRODUCT);
                        pst2.setInt(1, p.getProductID());
                        ResultSet rs3 = pst2.executeQuery();
                        if (rs3 != null) {
                            while (rs3.next()) {
                                Product_Category pc = new Product_Category(rs3.getInt("Product_Category_ID"), p.getProductID(),
                                        rs3.getInt("CategoryID"));
                                p.addCategory(pc);
                            }
                        }
                        PreparedStatement pst3 = cn.prepareStatement(GET_RECIPE_BY_PRODUCT);
                        pst3.setInt(1, p.getProductID());
                        ResultSet rs4 = pst3.executeQuery();
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

    public ArrayList<Product> getProductInCategoryWithPriceSelection(String categoryName, int StartPrice, int EndPrice, int weeklyPlanId) {
        ArrayList<Product> listOfProductInCategory = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_PRODUCT_BY_CATEGORY_WITH_PRICE_SELECTION);
                pst.setString(1, categoryName);
                pst.setInt(2, StartPrice);
                pst.setInt(3, EndPrice);
                pst.setInt(4, weeklyPlanId);
                ResultSet rs1 = pst.executeQuery();
                if (rs1 != null) {
                    while (rs1.next()) {
                        Product p = new Product();
                        p.setProductID(rs1.getInt("ProductID"));
                        p.setProductName(rs1.getString("ProductName"));
                        p.setImage(rs1.getString("Image"));
                        p.setPrice(rs1.getInt("Price"));
                        p.setTitle(rs1.getString("Title"));
                        p.setDescription(rs1.getString("Description"));
                        p.setCookingTime(rs1.getString("CookingTime"));
                        p.setStatus(rs1.getInt("Status"));
                        PreparedStatement pst1 = cn.prepareStatement(GET_BOXINGREDIENT_BY_PRODUCT);
                        pst1.setInt(1, p.getProductID());
                        ResultSet rs2 = pst1.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                BoxIngredient ingredient = new BoxIngredient(rs2.getInt("BoxIngredient_ID"), p.getProductID(),
                                        rs2.getInt("IngredientID"), rs2.getString("Quantity"), rs2.getString("Unit"));
                                p.addBoxIngredient(ingredient);
                            }
                        }
                        PreparedStatement pst2 = cn.prepareStatement(GET_CATEGORY_BY_PRODUCT);
                        pst2.setInt(1, p.getProductID());
                        ResultSet rs3 = pst2.executeQuery();
                        if (rs3 != null) {
                            while (rs3.next()) {
                                Product_Category pc = new Product_Category(rs3.getInt("Product_Category_ID"), p.getProductID(),
                                        rs3.getInt("CategoryID"));
                                p.addCategory(pc);
                            }
                        }
                        PreparedStatement pst3 = cn.prepareStatement(GET_RECIPE_BY_PRODUCT);
                        pst3.setInt(1, p.getProductID());
                        ResultSet rs4 = pst3.executeQuery();
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

    public ArrayList<Product> getProductWithPriceSelection(int StartPrice, int EndPrice, int weekly_Plan_ID) {
        ArrayList<Product> listOfProductInCategory = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_PRODUCT_WITH_PRICE_SELECTION);
                pst.setInt(1, StartPrice);
                pst.setInt(2, EndPrice);
                pst.setInt(3, weekly_Plan_ID);
                ResultSet rs1 = pst.executeQuery();
                if (rs1 != null) {
                    while (rs1.next()) {
                        Product p = new Product();
                        p.setProductID(rs1.getInt("ProductID"));
                        p.setProductName(rs1.getString("ProductName"));
                        p.setImage(rs1.getString("Image"));
                        p.setPrice(rs1.getInt("Price"));
                        p.setTitle(rs1.getString("Title"));
                        p.setDescription(rs1.getString("Description"));
                        p.setCookingTime(rs1.getString("CookingTime"));
                        p.setStatus(rs1.getInt("Status"));
                        PreparedStatement pst1 = cn.prepareStatement(GET_BOXINGREDIENT_BY_PRODUCT);
                        pst1.setInt(1, p.getProductID());
                        ResultSet rs2 = pst1.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                BoxIngredient ingredient = new BoxIngredient(rs2.getInt("BoxIngredient_ID"), p.getProductID(),
                                        rs2.getInt("IngredientID"), rs2.getString("Quantity"), rs2.getString("Unit"));
                                p.addBoxIngredient(ingredient);
                            }
                        }
                        PreparedStatement pst2 = cn.prepareStatement(GET_CATEGORY_BY_PRODUCT);
                        pst2.setInt(1, p.getProductID());
                        ResultSet rs3 = pst2.executeQuery();
                        if (rs3 != null) {
                            while (rs3.next()) {
                                Product_Category pc = new Product_Category(rs3.getInt("Product_Category_ID"), p.getProductID(),
                                        rs3.getInt("CategoryID"));
                                p.addCategory(pc);
                            }
                        }
                        PreparedStatement pst3 = cn.prepareStatement(GET_RECIPE_BY_PRODUCT);
                        pst3.setInt(1, p.getProductID());
                        ResultSet rs4 = pst3.executeQuery();
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

    public ArrayList<Product> getProductByCategoryWithTopPrice(String categoryName, int weekly_Plan_ID) {
        ArrayList<Product> listOfProductInCategory = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_PRODUCT_BY_CATEGORY_WITH_TOP_PRICE);
                pst.setString(1, categoryName);
                pst.setInt(2, weekly_Plan_ID);
                ResultSet rs1 = pst.executeQuery();
                if (rs1 != null) {
                    while (rs1.next()) {
                        Product p = new Product();
                        p.setProductID(rs1.getInt("ProductID"));
                        p.setProductName(rs1.getString("ProductName"));
                        p.setImage(rs1.getString("Image"));
                        p.setPrice(rs1.getInt("Price"));
                        p.setTitle(rs1.getString("Title"));
                        p.setDescription(rs1.getString("Description"));
                        p.setCookingTime(rs1.getString("CookingTime"));
                        p.setStatus(rs1.getInt("Status"));
                        PreparedStatement pst1 = cn.prepareStatement(GET_BOXINGREDIENT_BY_PRODUCT);
                        pst1.setInt(1, p.getProductID());
                        ResultSet rs2 = pst1.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                BoxIngredient ingredient = new BoxIngredient(rs2.getInt("BoxIngredient_ID"), p.getProductID(),
                                        rs2.getInt("IngredientID"), rs2.getString("Quantity"), rs2.getString("Unit"));
                                p.addBoxIngredient(ingredient);
                            }
                        }
                        PreparedStatement pst2 = cn.prepareStatement(GET_CATEGORY_BY_PRODUCT);
                        pst2.setInt(1, p.getProductID());
                        ResultSet rs3 = pst2.executeQuery();
                        if (rs3 != null) {
                            while (rs3.next()) {
                                Product_Category pc = new Product_Category(rs3.getInt("Product_Category_ID"), p.getProductID(),
                                        rs3.getInt("CategoryID"));
                                p.addCategory(pc);
                            }
                        }
                        PreparedStatement pst3 = cn.prepareStatement(GET_RECIPE_BY_PRODUCT);
                        pst3.setInt(1, p.getProductID());
                        ResultSet rs4 = pst3.executeQuery();
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

    public ArrayList<Product> getProductWithTopPrice(int weekly_Plan_ID) {
        ArrayList<Product> listOfProductInCategory = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_PRODUCT_WITH_TOP_PRICE);
                pst.setInt(1, weekly_Plan_ID);
                ResultSet rs1 = pst.executeQuery();
                if (rs1 != null) {
                    while (rs1.next()) {
                        Product p = new Product();
                        p.setProductID(rs1.getInt("ProductID"));
                        p.setProductName(rs1.getString("ProductName"));
                        p.setImage(rs1.getString("Image"));
                        p.setPrice(rs1.getInt("Price"));
                        p.setTitle(rs1.getString("Title"));
                        p.setDescription(rs1.getString("Description"));
                        p.setCookingTime(rs1.getString("CookingTime"));
                        p.setStatus(rs1.getInt("Status"));
                        PreparedStatement pst1 = cn.prepareStatement(GET_BOXINGREDIENT_BY_PRODUCT);
                        pst1.setInt(1, p.getProductID());
                        ResultSet rs2 = pst1.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                BoxIngredient ingredient = new BoxIngredient(rs2.getInt("BoxIngredient_ID"), p.getProductID(),
                                        rs2.getInt("IngredientID"), rs2.getString("Quantity"), rs2.getString("Unit"));
                                p.addBoxIngredient(ingredient);
                            }
                        }
                        PreparedStatement pst2 = cn.prepareStatement(GET_CATEGORY_BY_PRODUCT);
                        pst2.setInt(1, p.getProductID());
                        ResultSet rs3 = pst2.executeQuery();
                        if (rs3 != null) {
                            while (rs3.next()) {
                                Product_Category pc = new Product_Category(rs3.getInt("Product_Category_ID"), p.getProductID(),
                                        rs3.getInt("CategoryID"));
                                p.addCategory(pc);
                            }
                        }
                        PreparedStatement pst3 = cn.prepareStatement(GET_RECIPE_BY_PRODUCT);
                        pst3.setInt(1, p.getProductID());
                        ResultSet rs4 = pst3.executeQuery();
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

    public ArrayList<Product> getProductInCategoryWithTimeSelection(String categoryName, int StartTime, int EndTime) {
        ArrayList<Product> listOfProductInCategory = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_PRODUCT_BY_CATEGORY_WITH_TIME_SELECTION);
                pst.setString(1, "%" + categoryName + "%");
                pst.setInt(2, StartTime);
                pst.setInt(3, EndTime);
                ResultSet rs1 = pst.executeQuery();
                if (rs1 != null) {
                    while (rs1.next()) {
                        Product p = new Product();
                        p.setProductID(rs1.getInt("ProductID"));
                        p.setProductName(rs1.getString("ProductName"));
                        p.setImage(rs1.getString("Image"));
                        p.setPrice(rs1.getInt("Price"));
                        p.setTitle(rs1.getString("Title"));
                        p.setDescription(rs1.getString("Description"));
                        p.setCookingTime(rs1.getString("CookingTime"));
                        p.setStatus(rs1.getInt("Status"));
                        PreparedStatement pst1 = cn.prepareStatement(GET_BOXINGREDIENT_BY_PRODUCT);
                        pst1.setInt(1, p.getProductID());
                        ResultSet rs2 = pst1.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                BoxIngredient ingredient = new BoxIngredient(rs2.getInt("BoxIngredient_ID"), p.getProductID(),
                                        rs2.getInt("IngredientID"), rs2.getString("Quantity"), rs2.getString("Unit"));
                                p.addBoxIngredient(ingredient);
                            }
                        }
                        PreparedStatement pst2 = cn.prepareStatement(GET_CATEGORY_BY_PRODUCT);
                        pst2.setInt(1, p.getProductID());
                        ResultSet rs3 = pst2.executeQuery();
                        if (rs3 != null) {
                            while (rs3.next()) {
                                Product_Category pc = new Product_Category(rs3.getInt("Product_Category_ID"), p.getProductID(),
                                        rs3.getInt("CategoryID"));
                                p.addCategory(pc);
                            }
                        }
                        PreparedStatement pst3 = cn.prepareStatement(GET_RECIPE_BY_PRODUCT);
                        pst3.setInt(1, p.getProductID());
                        ResultSet rs4 = pst3.executeQuery();
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

    public ArrayList<Product> getProductWithTimeSelection(int StartTime, int EndTime, int weekly_Plan_ID) {
        ArrayList<Product> listOfProductInCategory = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_PRODUCT_WITH_TIME_SELECTION);
                pst.setInt(1, StartTime);
                pst.setInt(2, EndTime);
                pst.setInt(3, weekly_Plan_ID);
                ResultSet rs1 = pst.executeQuery();
                if (rs1 != null) {
                    while (rs1.next()) {
                        Product p = new Product();
                        p.setProductID(rs1.getInt("ProductID"));
                        p.setProductName(rs1.getString("ProductName"));
                        p.setImage(rs1.getString("Image"));
                        p.setPrice(rs1.getInt("Price"));
                        p.setTitle(rs1.getString("Title"));
                        p.setDescription(rs1.getString("Description"));
                        p.setCookingTime(rs1.getString("CookingTime"));
                        p.setStatus(rs1.getInt("Status"));
                        PreparedStatement pst1 = cn.prepareStatement(GET_BOXINGREDIENT_BY_PRODUCT);
                        pst1.setInt(1, p.getProductID());
                        ResultSet rs2 = pst1.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                BoxIngredient ingredient = new BoxIngredient(rs2.getInt("BoxIngredient_ID"), p.getProductID(),
                                        rs2.getInt("IngredientID"), rs2.getString("Quantity"), rs2.getString("Unit"));
                                p.addBoxIngredient(ingredient);
                            }
                        }
                        PreparedStatement pst2 = cn.prepareStatement(GET_CATEGORY_BY_PRODUCT);
                        pst2.setInt(1, p.getProductID());
                        ResultSet rs3 = pst2.executeQuery();
                        if (rs3 != null) {
                            while (rs3.next()) {
                                Product_Category pc = new Product_Category(rs3.getInt("Product_Category_ID"), p.getProductID(),
                                        rs3.getInt("CategoryID"));
                                p.addCategory(pc);
                            }
                        }
                        PreparedStatement pst3 = cn.prepareStatement(GET_RECIPE_BY_PRODUCT);
                        pst3.setInt(1, p.getProductID());
                        ResultSet rs4 = pst3.executeQuery();
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

    public ArrayList<Product> getProductInCategoryWithTopTime(String categoryName) {
        ArrayList<Product> listOfProductInCategory = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_PRODUCT_BY_CATEGORY_WITH_TOP_TIME);
                pst.setString(1, categoryName);
                pst.setString(2, categoryName);
                ResultSet rs1 = pst.executeQuery();
                if (rs1 != null) {
                    while (rs1.next()) {
                        Product p = new Product();
                        p.setProductID(rs1.getInt("ProductID"));
                        p.setProductName(rs1.getString("ProductName"));
                        p.setImage(rs1.getString("Image"));
                        p.setPrice(rs1.getInt("Price"));
                        p.setTitle(rs1.getString("Title"));
                        p.setDescription(rs1.getString("Description"));
                        p.setCookingTime(rs1.getString("CookingTime"));
                        p.setStatus(rs1.getInt("Status"));
                        PreparedStatement pst1 = cn.prepareStatement(GET_BOXINGREDIENT_BY_PRODUCT);
                        pst1.setInt(1, p.getProductID());
                        ResultSet rs2 = pst1.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                BoxIngredient ingredient = new BoxIngredient(rs2.getInt("BoxIngredient_ID"), p.getProductID(),
                                        rs2.getInt("IngredientID"), rs2.getString("Quantity"), rs2.getString("Unit"));
                                p.addBoxIngredient(ingredient);
                            }
                        }
                        PreparedStatement pst2 = cn.prepareStatement(GET_CATEGORY_BY_PRODUCT);
                        pst2.setInt(1, p.getProductID());
                        ResultSet rs3 = pst2.executeQuery();
                        if (rs3 != null) {
                            while (rs3.next()) {
                                Product_Category pc = new Product_Category(rs3.getInt("Product_Category_ID"), p.getProductID(),
                                        rs3.getInt("CategoryID"));
                                p.addCategory(pc);
                            }
                        }
                        PreparedStatement pst3 = cn.prepareStatement(GET_RECIPE_BY_PRODUCT);
                        pst3.setInt(1, p.getProductID());
                        ResultSet rs4 = pst3.executeQuery();
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

    public ArrayList<Product> getProductWithTopTime(int weekly_Plan_ID) {
        ArrayList<Product> listOfProductInCategory = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_PRODUCT_WITH_TOP_TIME);
                pst.setInt(1, weekly_Plan_ID);
                ResultSet rs1 = pst.executeQuery();
                if (rs1 != null) {
                    while (rs1.next()) {
                        Product p = new Product();
                        p.setProductID(rs1.getInt("ProductID"));
                        p.setProductName(rs1.getString("ProductName"));
                        p.setImage(rs1.getString("Image"));
                        p.setPrice(rs1.getInt("Price"));
                        p.setTitle(rs1.getString("Title"));
                        p.setDescription(rs1.getString("Description"));
                        p.setCookingTime(rs1.getString("CookingTime"));
                        p.setStatus(rs1.getInt("Status"));
                        PreparedStatement pst1 = cn.prepareStatement(GET_BOXINGREDIENT_BY_PRODUCT);
                        pst1.setInt(1, p.getProductID());
                        ResultSet rs2 = pst1.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                BoxIngredient ingredient = new BoxIngredient(rs2.getInt("BoxIngredient_ID"), p.getProductID(),
                                        rs2.getInt("IngredientID"), rs2.getString("Quantity"), rs2.getString("Unit"));
                                p.addBoxIngredient(ingredient);
                            }
                        }
                        PreparedStatement pst2 = cn.prepareStatement(GET_CATEGORY_BY_PRODUCT);
                        pst2.setInt(1, p.getProductID());
                        ResultSet rs3 = pst2.executeQuery();
                        if (rs3 != null) {
                            while (rs3.next()) {
                                Product_Category pc = new Product_Category(rs3.getInt("Product_Category_ID"), p.getProductID(),
                                        rs3.getInt("CategoryID"));
                                p.addCategory(pc);
                            }
                        }
                        PreparedStatement pst3 = cn.prepareStatement(GET_RECIPE_BY_PRODUCT);
                        pst3.setInt(1, p.getProductID());
                        ResultSet rs4 = pst3.executeQuery();
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

//    public ArrayList<Product> getProductInCategoryWithSelection(String categoryName, int StartTime, int EndTime, int StartPrice, int EndPrice) {
//        ArrayList<Product> listOfProductInCategory = new ArrayList<>();
//        Connection cn = null;
//        try {
//            cn = DBContext.makeConnection();
//            if (cn != null) {
//                PreparedStatement pst = cn.prepareStatement(GET_PRODUCT_BY_CATEGORY_WITH_SELECTION);
//                pst.setString(1, categoryName);
//                pst.setInt(2, StartTime);
//                pst.setInt(3, EndTime);
//                pst.setString(4, categoryName);
//                pst.setInt(5, StartPrice);
//                pst.setInt(6, EndPrice);
//                ResultSet rs1 = pst.executeQuery();
//                if (rs1 != null) {
//                    while (rs1.next()) {
//                        Product p = new Product();
//                        p.setProductID(rs1.getInt("ProductID"));
//                        p.setProductName(rs1.getString("ProductName"));
//                        p.setImage(rs1.getString("Image"));
//                        p.setPrice(rs1.getInt("Price"));
//                        p.setDescription(rs1.getString("Description"));
//                        p.setCookingTime(rs1.getString("CookingTime"));
//                        p.setStatus(rs1.getInt("Status"));
//                        PreparedStatement pst1 = cn.prepareStatement(GET_BOXINGREDIENT_BY_PRODUCT);
//                        pst1.setInt(1, p.getProductID());
//                        ResultSet rs2 = pst1.executeQuery();
//                        if (rs2 != null) {
//                            while (rs2.next()) {
//                                BoxIngredient ingredient = new BoxIngredient(rs2.getInt("BoxIngredient_ID"), p.getProductID(),
//                                        rs2.getInt("IngredientID"), rs2.getString("Quantity"), rs2.getString("Unit"));
//                                p.addBoxIngredient(ingredient);
//                            }
//                        }
//                        PreparedStatement pst2 = cn.prepareStatement(GET_CATEGORY_BY_PRODUCT);
//                        pst2.setInt(1, p.getProductID());
//                        ResultSet rs3 = pst2.executeQuery();
//                        if (rs3 != null) {
//                            while (rs3.next()) {
//                                Product_Category pc = new Product_Category(rs3.getInt("Product_Category_ID"), p.getProductID(),
//                                        rs3.getInt("CategoryID"));
//                                p.addCategory(pc);
//                            }
//                        }
//                        PreparedStatement pst3 = cn.prepareStatement(GET_RECIPE_BY_PRODUCT);
//                        pst3.setInt(1, p.getProductID());
//                        ResultSet rs4 = pst3.executeQuery();
//                        if (rs4 != null) {
//                            while (rs4.next()) {
//                                Recipe_Product rp = new Recipe_Product(rs4.getInt("RecipeProductID"), rs4.getInt("ProductID"), rs4.getString("Instruction"));
//                                p.addRecipe(rp);
//                            }
//                        }
//                        listOfProductInCategory.add(p);
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        } finally {
//            try {
//                if (cn != null) {
//                    cn.close();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return listOfProductInCategory;
//    }
    public ArrayList<Integer> getProductIdForDayPick(Date dayPick) {
        ArrayList<Integer> productIDs = new ArrayList<>();

        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_PRODUCT_BY_DATE_IN_WEEKLY_PLAN);
                pst.setDate(1, dayPick);
                pst.setDate(2, dayPick);
                ResultSet rs1 = pst.executeQuery();
                if (rs1 != null) {
                    while (rs1.next()) {
                        productIDs.add(rs1.getInt("ProductID"));
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
        return productIDs;
    }

    public Map<Integer, String> getProductMap() {
        Map<Integer, String> productMap = new HashMap<>();
        Connection cn = null;

        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                Statement st1 = cn.createStatement();
                ResultSet rs1 = st1.executeQuery(GET_PRODUCT_MAP);
                if (rs1 != null) {
                    while (rs1.next()) {
                        int categoryID = rs1.getInt("ProductID");
                        String categoryName = rs1.getString("ProductName");
                        productMap.put(categoryID, categoryName);
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

        return productMap;
    }

    public Map<Integer, Product> getProductOrderMap() {
        Map<Integer, Product> productOrderMap = new HashMap<>();
        Connection cn = null;

        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                Statement st1 = cn.createStatement();
                ResultSet rs1 = st1.executeQuery(GET_PRODUCT_ORDER_MAP);
                if (rs1 != null) {
                    while (rs1.next()) {
                        int productid = rs1.getInt("ProductID");
                        String productName = rs1.getString("ProductName");
                        String imageProduct = rs1.getString("Image");
                        Product product = new Product(productid, productName, imageProduct);
                        productOrderMap.put(productid, product);
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

        return productOrderMap;
    }

    public ArrayList<Product> getProductByFilter(Integer status, String search) {
        ArrayList<Product> listOfProduct = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                String sql = "SELECT [ProductID], [ProductName], [Image], [Price], [Title], [Description], [CookingTime], [Status] "
                        + "FROM [dbo].[tb_Product] "
                        + "WHERE 1 = 1";

                if (status != null) {
                    sql += " AND Status = ?";
                }

                if (search != null && !search.isEmpty()) {
                    sql += " AND ProductName LIKE ?";
                }

                PreparedStatement pst = cn.prepareStatement(sql);
                int paramIndex = 1;

                if (status != null) {
                    pst.setInt(paramIndex++, status);
                }

                if (search != null && !search.isEmpty()) {
                    pst.setString(paramIndex++, "%" + search + "%");
                }

                ResultSet rs1 = pst.executeQuery();
                if (rs1 != null) {
                    while (rs1.next()) {
                        Product p = new Product();
                        p.setProductID(rs1.getInt("ProductID"));
                        p.setProductName(rs1.getString("ProductName"));
                        p.setImage(rs1.getString("Image"));
                        p.setPrice(rs1.getInt("Price"));
                        p.setTitle(rs1.getString("Title"));
                        p.setDescription(rs1.getString("Description"));
                        p.setCookingTime(rs1.getString("CookingTime"));
                        p.setStatus(rs1.getInt("Status"));
                        PreparedStatement pst1 = cn.prepareStatement(GET_BOXINGREDIENT_BY_PRODUCT);
                        pst1.setInt(1, p.getProductID());
                        ResultSet rs2 = pst1.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                BoxIngredient ingredient = new BoxIngredient(rs2.getInt("BoxIngredient_ID"), p.getProductID(),
                                        rs2.getInt("IngredientID"), rs2.getString("Quantity"), rs2.getString("Unit"));
                                p.addBoxIngredient(ingredient);
                            }
                        }
                        PreparedStatement pst2 = cn.prepareStatement(GET_CATEGORY_BY_PRODUCT);
                        pst2.setInt(1, p.getProductID());
                        ResultSet rs3 = pst2.executeQuery();
                        if (rs3 != null) {
                            while (rs3.next()) {
                                Product_Category pc = new Product_Category(rs3.getInt("Product_Category_ID"), p.getProductID(),
                                        rs3.getInt("CategoryID"));
                                p.addCategory(pc);
                            }
                        }
                        PreparedStatement pst3 = cn.prepareStatement(GET_RECIPE_BY_PRODUCT);
                        pst3.setInt(1, p.getProductID());
                        ResultSet rs4 = pst3.executeQuery();
                        if (rs4 != null) {
                            while (rs4.next()) {
                                Recipe_Product rp = new Recipe_Product(rs4.getInt("RecipeProductID"), rs4.getInt("ProductID"), rs4.getInt("Step"), rs4.getString("Instruction"));
                                p.addRecipe(rp);
                            }
                        }
                        listOfProduct.add(p);
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
        return listOfProduct;
    }
}
