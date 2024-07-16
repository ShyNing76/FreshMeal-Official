/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.BoxIngredient;
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
public class BoxIngredientDAO implements Serializable {

    private final String GET_BOXINGREDIENT = "SELECT [BoxIngredient_ID],[ProductID],[IngredientID],[Quantity],[Unit]\n"
            + "FROM [dbo].[tb_BoxIngredient]";
    private final String GET_BOXINGREDIENT_BY_PRODUCT = "SELECT b.BoxIngredient_ID, b.ProductID, b.IngredientID, b.Quantity, b.Unit\n"
            + "FROM [dbo].[tb_BoxIngredient] b\n"
            + "WHERE b.ProductID = ?";
    private final String INSERT_BOXINGREDIENT = "INSERT INTO [dbo].[tb_BoxIngredient] ([ProductID],[IngredientID],[Quantity],[Unit]) VALUES (?,?,?,?)";

    public ArrayList<BoxIngredient> getBoxIngredient() {
        ArrayList<BoxIngredient> listOfBoxIngredient = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(GET_BOXINGREDIENT);
                if (rs != null) {
                    while (rs.next()) {
                        BoxIngredient bi = new BoxIngredient(rs.getInt("BoxIngredient_ID"), rs.getInt("ProductID"), rs.getInt("IngredientID"), rs.getString("Quantity"), rs.getString("Unit"));
                        listOfBoxIngredient.add(bi);
                    }
                }
            }
        } catch (Exception e) {
        }
        return listOfBoxIngredient;
    }

    public ArrayList<BoxIngredient> getBoxIngredientByProduct(int productID) {
        ArrayList<BoxIngredient> listOfBoxIngredient = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_BOXINGREDIENT_BY_PRODUCT);
                pst.setInt(1, productID);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        BoxIngredient bi = new BoxIngredient(rs.getInt("BoxIngredient_ID"), rs.getInt("ProductID"), rs.getInt("IngredientID"), rs.getString("Quantity"), rs.getString("Unit"));
                        listOfBoxIngredient.add(bi);
                    }
                }
            }
        } catch (Exception e) {
        }
        return listOfBoxIngredient;
    }

    public int insertBoxIngredientIntoProduct(int ProductID, int IngredientID, int Quantity, String Unit) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(INSERT_BOXINGREDIENT);
                pst.setInt(1, ProductID);
                pst.setInt(2, IngredientID);
                pst.setInt(3, Quantity);
                pst.setString(4, Unit);
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
