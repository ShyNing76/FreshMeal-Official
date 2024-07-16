/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.BoxIngredient;
import dto.Order_Detail;
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
public class Order_DetailDAO implements Serializable {

    private final String GET_PRODUCT_BY_USER_AND_ORDER_STATUS = "SELECT p.[ProductID],p.[ProductName],p.[Image],p.[Price],p.[Title],p.[Description],p.[CookingTime],p.[Status], od.Quantity, od.MealType\n"
            + "FROM [dbo].[tb_Order_Detail] od INNER JOIN [dbo].[tb_Order] o ON od.OrderID = o.OrderID\n"
            + "								INNER JOIN [dbo].[tb_Product] p ON od.ProductID = p.ProductID\n"
            + "								INNER JOIN [dbo].[tb_User] u ON o.UserID = u.UserID\n"
            + "WHERE u.UserName = ? AND o.Status LIKE ?";
    private final String GET_PRODUCT_BY_USER = "SELECT p.[ProductID],p.[ProductName],p.[Image],p.[Price],p.[Title],p.[Description],p.[CookingTime],p.[Status], od.Quantity, od.MealType\n"
            + "FROM [dbo].[tb_Order_Detail] od INNER JOIN [dbo].[tb_Order] o ON od.OrderID = o.OrderID\n"
            + "								INNER JOIN [dbo].[tb_Product] p ON od.ProductID = p.ProductID\n"
            + "								INNER JOIN [dbo].[tb_User] u ON o.UserID = u.UserID\n"
            + "WHERE u.UserName = ?";
    private final String GET_ORDER_DETAIL_BY_USER = "SELECT od.Order_Detail_ID, od.ProductID, od.OrderID, od.Price, od.Quantity, od.MealType\n"
            + "FROM [dbo].[tb_Order_Detail] od INNER JOIN [dbo].[tb_Order] o ON od.OrderID = o.OrderID\n"
            + "                                INNER JOIN [dbo].[tb_Product] p ON od.ProductID = p.ProductID\n"
            + "                                INNER JOIN [dbo].[tb_User] u ON o.UserID = u.UserID\n"
            + "WHERE u.UserName = ?";
    private final String GET_BOXINGREDIENT_BY_PRODUCT = "SELECT [BoxIngredient_ID],[ProductID],[IngredientID],[Quantity],[Unit]\n"
            + "FROM [dbo].[tb_BoxIngredient]\n"
            + "WHERE [ProductID] = ?";
    private final String GET_CATEGORY_BY_PRODUCT = "SELECT [Product_Category_ID],[ProductID],[CategoryID]\n"
            + "FROM [dbo].[tb_Product_Category]\n"
            + "WHERE [ProductID] = ?";
    private final String GET_RECIPE_BY_PRODUCT = "SELECT [RecipeProductID],[ProductID],[Step],[Instruction]\n"
            + "FROM tb_Recipe_Product\n"
            + "WHERE ProductID = ?";

    public ArrayList<Order_Detail> getOrderDetailByUserName(String userName) {
        ArrayList<Order_Detail> listOfOrderDetail = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_ORDER_DETAIL_BY_USER);
                pst.setString(1, "%" + userName + "%");
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        Order_Detail orderDetail = new Order_Detail(rs.getInt("Order_Detail_ID"), rs.getInt("ProductID"), rs.getInt("OrderID"), rs.getInt("Price"), rs.getInt("Quantity"), rs.getString("MealType"));
                        listOfOrderDetail.add(orderDetail);
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
        return listOfOrderDetail;
    }

    public ArrayList<Product> getProductByuserNameInOrder(String userName) {
        ArrayList<Product> listOfProduct = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_PRODUCT_BY_USER);
                pst.setString(1, userName);
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
                        p.setMealType(rs1.getString("MealType"));
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

    public ArrayList<Product> getProductByOrder(String userName, String status) {
        ArrayList<Product> listOfProduct = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_PRODUCT_BY_USER_AND_ORDER_STATUS);
                pst.setString(1, "%" + userName + "%");
                pst.setString(2, "%" + status + "%");
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
