/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Ingredient;
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
public class IngredientDAO implements Serializable {

    private final String GET_INGREDIENT = "SELECT [IngredientID],[Name]\n"
            + "FROM [dbo].[tb_Ingredient]";
    private final String SEARCH_INGREDIENT = "SELECT [IngredientID],[Name]\n"
            + "FROM [dbo].[tb_Ingredient]\n"
            + "WHERE Name LIKE ?";
    private final String GET_INGREDIENT_BY_NAME = "SELECT IngredientID, Name\n"
            + "FROM tb_Ingredient\n"
            + "WHERE Name = ?";
    private final String INSERT_INGREDIENT = "INSERT INTO [dbo].[tb_Ingredient] ([Name]) VALUES (?)";

    public ArrayList<Ingredient> getIngredient() {
        ArrayList<Ingredient> listOfIngredient = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(GET_INGREDIENT);
                if (rs != null) {
                    while (rs.next()) {
                        Ingredient i = new Ingredient(rs.getInt("IngredientID"), rs.getString("Name"));
                        listOfIngredient.add(i);
                    }
                }
            }
        } catch (Exception e) {
        }
        return listOfIngredient;
    }

    public Map<Integer, String> getIngredientMap() {
        Map<Integer, String> ingredientMap = new HashMap<>();
        Connection cn = null;

        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                Statement st1 = cn.createStatement();
                ResultSet rs1 = st1.executeQuery(GET_INGREDIENT);
                if (rs1 != null) {
                    while (rs1.next()) {
                        int ingredientId = rs1.getInt("IngredientID");
                        String ingredientName = rs1.getString("Name");
                        ingredientMap.put(ingredientId, ingredientName);
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

        return ingredientMap;
    }

    public int insertIngredient(String Name) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(INSERT_INGREDIENT);
                pst.setString(1, Name);
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

    public ArrayList<Ingredient> searchIngredient(String search) {
        ArrayList<Ingredient> listOfIngredient = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(SEARCH_INGREDIENT);
                pst.setString(1, "%" + search + "%");
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        Ingredient i = new Ingredient(rs.getInt("IngredientID"), rs.getString("Name"));
                        listOfIngredient.add(i);
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

        return listOfIngredient;
    }

    public Ingredient getIngredientByName(String nameIngredient) {
        Ingredient ingredient = null;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_INGREDIENT_BY_NAME);
                pst.setString(1, nameIngredient);
                ResultSet rs = pst.executeQuery();

                if (rs != null && rs.next()) {
                    ingredient = new Ingredient(rs.getInt("IngredientID"), rs.getString("Name"));
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
        return ingredient;
    }
}
