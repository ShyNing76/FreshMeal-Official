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
import dto.Weekly_Plan_Product;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import utils.DBContext;

/**
 *
 * @author ADMIN
 */
public class Weekly_Plan_ProductDAO implements Serializable {

    private final String GET_TOP_6_PRODUCT_IN_WEEKLY_PLAN_BY_ID = "SELECT TOP 6 p.ProductID, p.ProductName, p.Image, p.Price, p.Title, p.Description, p.CookingTime, p.Status\n"
            + "FROM [dbo].[tb_Weekly_Plan_Product] wpp INNER JOIN [dbo].[tb_Weekly_Plan] wp ON wp.Weekly_Plan_ID = wpp.Weekly_Plan_ID\n"
            + "                                        INNER JOIN [dbo].[tb_Product] p ON wpp.ProductID = p.ProductID\n"
            + "WHERE wp.Weekly_Plan_ID = ? AND wp.Status = 1 AND p.Status = 1";
    private final String GET_PRODUCT_IN_WEEKLY_PLAN_BY_ID = "SELECT p.ProductID, p.ProductName, p.Image, p.Price, p.Title, p.Description, p.CookingTime, p.Status\n"
            + "FROM [dbo].[tb_Weekly_Plan_Product] wpp INNER JOIN [dbo].[tb_Weekly_Plan] wp ON wp.Weekly_Plan_ID = wpp.Weekly_Plan_ID\n"
            + "                                        INNER JOIN [dbo].[tb_Product] p ON wpp.ProductID = p.ProductID\n"
            + "WHERE wp.Weekly_Plan_ID = ? AND wp.Status = 1 AND p.Status = 1";
    private final String GET_PRODUCT_IN_WEEKLY_PLAN_BY_NAME = "SELECT TOP 6 [ProductID],[ProductName],[Image],[Price],[Title],[Description],[CookingTime],[Status]\n"
            + "FROM [dbo].[tb_Product]\n"
            + "WHERE [ProductName] LIKE ? AND [ProductID] IN (SELECT DISTINCT p.ProductID\n"
            + "FROM [dbo].[tb_Weekly_Plan_Product] wpp INNER JOIN [dbo].[tb_Weekly_Plan] wp ON wp.Weekly_Plan_ID = wpp.Weekly_Plan_ID\n"
            + "                                        INNER JOIN [dbo].[tb_Product] p ON wpp.ProductID = p.ProductID\n"
            + "WHERE wp.Status = 1 AND wp.Weekly_Plan_ID = ?)";
    private final String GET_PRODUCT_IN_WEEKLY_PLAN = "SELECT [ProductID],[ProductName],[Image],[Price],[Title],[Description],[CookingTime],[Status]\n"
            + "FROM [dbo].[tb_Product]\n"
            + "WHERE [ProductID] IN (SELECT DISTINCT p.ProductID\n"
            + "FROM [dbo].[tb_Weekly_Plan_Product] wpp INNER JOIN [dbo].[tb_Weekly_Plan] wp ON wp.Weekly_Plan_ID = wpp.Weekly_Plan_ID\n"
            + "INNER JOIN [dbo].[tb_Product] p ON wpp.ProductID = p.ProductID\n"
            + "WHERE wp.Status = 1)";
    private final String GET_PRODUCT_IN_WEEKLY_PLAN_BY_ID_AND_CATEGORY = "SELECT p.ProductID, p.ProductName, p.Image, p.Price, p.Title, p.Description, p.CookingTime, p.Status\n"
            + "FROM [dbo].[tb_Weekly_Plan_Product] wpp INNER JOIN [dbo].[tb_Weekly_Plan] wp ON wp.Weekly_Plan_ID = wpp.Weekly_Plan_ID\n"
            + "                                        INNER JOIN [dbo].[tb_Product] p ON wpp.ProductID = p.ProductID\n"
            + "                                        INNER JOIN [dbo].[tb_Product_Category] pc ON p.ProductID = pc.ProductID\n"
            + "                                        INNER JOIN [dbo].[tb_Category] c ON pc.CategoryID = c.CategoryID\n"
            + "WHERE wp.Weekly_Plan_ID = ? AND c.CategoryName LIKE ? AND p.Status = 1 AND wp.Status = 1";
    private final String GET_BOXINGREDIENT_BY_PRODUCT = "SELECT [BoxIngredient_ID],[ProductID],[IngredientID],[Quantity],[Unit]\n"
            + "FROM [dbo].[tb_BoxIngredient]\n"
            + "WHERE [ProductID] = ?";
    private final String GET_CATEGORY_BY_PRODUCT = "SELECT [Product_Category_ID],[ProductID],[CategoryID]\n"
            + "FROM [dbo].[tb_Product_Category]\n"
            + "WHERE [ProductID] = ?";
    private final String GET_RECIPE_BY_PRODUCT = "SELECT [RecipeProductID],[ProductID],[Step],[Instruction]\n"
            + "FROM tb_Recipe_Product\n"
            + "WHERE ProductID = ?";
    private final String INSERT_PRODUCT_WEEKLY_PLAN = "INSERT INTO [dbo].[tb_Weekly_Plan_Product] ([Weekly_Plan_ID],[ProductID]) VALUES (?,?)";
    private final String GET_ID_BY_PRODUCT_AND_WEEKLY = "SELECT Weekly_Plan_Product_ID\n"
            + "FROM tb_Weekly_Plan_Product\n"
            + "WHERE Weekly_Plan_ID = ? AND ProductID = ?";
    private final String GET_PRODUCT_ID_IN_WEEKLY_PLAN = "SELECT Weekly_Plan_Product_ID, Weekly_Plan_ID, ProductID, Status\n"
            + "FROM tb_Weekly_Plan_Product";
    private final String UPDATE_STATUS_WEEKLY_PLAN_PRODUCT = "UPDATE tb_Weekly_Plan_Product\n"
            + "SET [Status] = ?\n"
            + "WHERE [Weekly_Plan_Product_ID] = ?";
    private final String GET_WEEKLY_PLAN_PRODUCT_BY_ID = "SELECT Weekly_Plan_ID, ProductID, Status\n"
            + "FROM tb_Weekly_Plan_Product\n"
            + "WHERE Weekly_Plan_Product_ID = ?";

    public ArrayList<Product> getTop6ProductInWeekly_PlanByID(int weeklyPlanID) {
        ArrayList<Product> listOfProduct = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_TOP_6_PRODUCT_IN_WEEKLY_PLAN_BY_ID);
                pst.setInt(1, weeklyPlanID);
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

    public ArrayList<Product> getAllProductInWeekly_PlanByID(int weeklyPlanID) {
        ArrayList<Product> listOfProduct = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_PRODUCT_IN_WEEKLY_PLAN_BY_ID);
                pst.setInt(1, weeklyPlanID);
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

    public ArrayList<Product> getProductInWeekly_PlanByProductName(String productName, int weeklyPlanID) {
        ArrayList<Product> listOfProduct = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_PRODUCT_IN_WEEKLY_PLAN_BY_NAME);
                pst.setString(1, "%" + productName + "%");
                pst.setInt(2, weeklyPlanID);
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

    public ArrayList<Product> getProductInWeekly_Plan() {
        ArrayList<Product> listOfProduct = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                Statement st = cn.createStatement();
                ResultSet rs1 = st.executeQuery(GET_PRODUCT_IN_WEEKLY_PLAN);
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

    public ArrayList<Product> getProductInWeekly_PlanByDateAndCategory(int weeklyPlanID, String CategoryName) {
        ArrayList<Product> listOfProduct = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_PRODUCT_IN_WEEKLY_PLAN_BY_ID_AND_CATEGORY);
                pst.setInt(1, weeklyPlanID);
                pst.setString(2, "%" + CategoryName + "%");
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

    public int InsertProductIntoWeeklyPlan(int weekly_Plan_ID, int productID) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(INSERT_PRODUCT_WEEKLY_PLAN);
                pst.setInt(1, weekly_Plan_ID);
                pst.setInt(2, productID);
            }
        } catch (Exception e) {
        }
        return rs;
    }

    public int getWeeklyPlanProductId(int weeklyPlanId, int productId) {
        int weeklyPlanProductId = 0;

        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_ID_BY_PRODUCT_AND_WEEKLY);
                pst.setInt(1, weeklyPlanId);
                pst.setInt(2, productId);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    weeklyPlanProductId = rs.getInt("Weekly_Plan_Product_ID");
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

        return weeklyPlanProductId;
    }

    public ArrayList<Weekly_Plan_Product> getProductInWeekly() {
        ArrayList<Weekly_Plan_Product> listOfProduct = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(GET_PRODUCT_ID_IN_WEEKLY_PLAN);
                if (rs != null) {
                    while (rs.next()) {
                        int weeklyPlanProductId = rs.getInt("Weekly_Plan_Product_ID");
                        int weeklyPlanId = rs.getInt("Weekly_Plan_ID");
                        int productId = rs.getInt("ProductID");
                        int status = rs.getInt("Status");

                        Weekly_Plan_Product wpp = new Weekly_Plan_Product(weeklyPlanProductId, weeklyPlanId, productId, status);
                        listOfProduct.add(wpp);
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

    public int updateStatusProductInWeekly_Plan(int status, int Weekly_plan_product_id) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(UPDATE_STATUS_WEEKLY_PLAN_PRODUCT);
                pst.setInt(1, status);
                pst.setInt(2, Weekly_plan_product_id);
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

    public Weekly_Plan_Product getWeeklyPlanProductById(int weekly_plan_product_id) {
        Weekly_Plan_Product wpp = null;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_WEEKLY_PLAN_PRODUCT_BY_ID);
                pst.setInt(1, weekly_plan_product_id);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    wpp = new Weekly_Plan_Product(weekly_plan_product_id, rs.getInt("Weekly_Plan_ID"), rs.getInt("ProductID"), rs.getInt("Status"));
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
        return wpp;
    }

}
