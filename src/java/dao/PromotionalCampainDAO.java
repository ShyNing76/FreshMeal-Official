/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Promotional_Campain;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import utils.DBContext;

/**
 *
 * @author ADMIN
 */
public class PromotionalCampainDAO implements Serializable {

    private final String GET_PROMOTIONAL_CAMPAIN = "SELECT [Promotional_Campain_ID],[Name],[Description],[DiscountPrice],[StartDate],[EndDate],[Status]\n"
            + "FROM [dbo].[tb_Promotional_Campain]";
    private final String INSERT_PROMOTIONAL_CAMPAIN = "INSERT INTO [dbo].[tb_Promotional_Campain] ([Name],[Description],[DiscountPrice],[StartDate],[EndDate],[Status]) VALUES (?,?,?,?,?,?)";
    private final String UPDATE_STATUS_PROMOTIONAL_CAMPAIN = "UPDATE [dbo].[tb_Promotional_Campain]\n"
            + "SET [Status] = ? \n"
            + "WHERE [Promotional_Campain_ID] = ?";

    public ArrayList<Promotional_Campain> getPromotionalCampain() {
        ArrayList<Promotional_Campain> listOfPromotionalCampain = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(GET_PROMOTIONAL_CAMPAIN);
                if (rs != null) {
                    while (rs.next()) {
                        Promotional_Campain pc = new Promotional_Campain(rs.getInt("Promotional_Campain_ID"), rs.getString("Name"),
                                rs.getString("Description"), rs.getDouble("DiscountPrice"), rs.getDate("StartDate"),
                                rs.getDate("EndDate"), rs.getInt("Status"));
                        listOfPromotionalCampain.add(pc);
                    }
                }
            }
        } catch (Exception e) {
        }
        return listOfPromotionalCampain;
    }

    public int insertPromotionalCampain(String Name, String Description, double DiscountPrice, Date StartDate, Date EndDate, int Status) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(INSERT_PROMOTIONAL_CAMPAIN);
                pst.setString(1, Name);
                pst.setString(2, Description);
                pst.setDouble(3, DiscountPrice);
                pst.setDate(4, StartDate);
                pst.setDate(5, EndDate);
                pst.setInt(6, Status);
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

    public int updateStatusPromotionalCampin(int status, int Promotional_Campain_ID) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(UPDATE_STATUS_PROMOTIONAL_CAMPAIN);
                pst.setInt(1, status);
                pst.setInt(2, Promotional_Campain_ID);
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
