/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Recipe_Product;
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
public class RecipeProductDAO implements Serializable {

    private final String GET_RECIPE = "SELECT [RecipeProductID],[ProductID],[Step],[Instruction],[Image]\n"
            + "FROM tb_Recipe_Product";
    private final String GET_RECIPE_BY_PRODUCT_ID = "SELECT [RecipeProductID],[ProductID],[Step],[Instruction]\n"
            + "FROM tb_Recipe_Product\n"
            + "WHERE ProductID = ?";
    private final String INSERT_RECIPE = "INSERT INTO [dbo].[tb_Recipe_Product] ([Name],[StepDescription],[ListImage]) VALUES(?,?,?)";

    public ArrayList<Recipe_Product> getRecipe() {
        ArrayList<Recipe_Product> listOfRecipe = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(GET_RECIPE);
                if (rs != null) {
                    while (rs.next()) {
                        Recipe_Product rp = new Recipe_Product(rs.getInt("RecipeProductID"), rs.getInt("ProductID"), rs.getInt("Step"), rs.getString("Instruction"));
                        listOfRecipe.add(rp);
                    }
                }
            }
        } catch (Exception e) {
        }
        return listOfRecipe;
    }

    public ArrayList<Recipe_Product> getRecipeById(int productId) {
        ArrayList<Recipe_Product> listOfRecipe = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_RECIPE_BY_PRODUCT_ID);
                pst.setInt(1, productId);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        Recipe_Product rp = new Recipe_Product(rs.getInt("RecipeProductID"), rs.getInt("ProductID"), rs.getInt("Step"), rs.getString("Instruction"));
                        listOfRecipe.add(rp);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return listOfRecipe;
        }
    }

    public int insertRecipe(String Name, String StepDescription, String ListImage) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(INSERT_RECIPE);
                pst.setString(1, Name);
                pst.setString(2, StepDescription);
                pst.setString(3, ListImage);
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
