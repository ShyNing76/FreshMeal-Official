/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Delivery;
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
public class DeliveryDAO implements Serializable {

    private final String GET_ALL_DELIVERY_ACTIVE = "SELECT [DeliveryID],[DeliveryMethod],[DeliveryPrice],[DeliveryIcon],[Status]\n"
            + "FROM [dbo].[tb_Delivery]\n"
            + "WHERE [Status] = 1";
    private final String GET_DELIVERY_ACTIVE_BY_ID = "SELECT [DeliveryID],[DeliveryMethod],[DeliveryPrice],[DeliveryIcon],[Status]\n"
            + "FROM [dbo].[tb_Delivery]\n"
            + "WHERE [Status] = 1 AND [DeliveryID] = ?";
    private final String GET_DELIVERY_ACTIVE_BY_ORDER_ID = "SELECT d.[DeliveryID],d.[DeliveryMethod],d.[DeliveryPrice],d.[DeliveryIcon],d.[Status]\n"
            + "FROM [dbo].[tb_Delivery] d INNER JOIN [dbo].[tb_Order] o ON d.DeliveryID = o.DeliveryID\n"
            + "WHERE d.[Status] = 1 AND o.OrderID = ?";

    public ArrayList<Delivery> getAllDeliveryActive() {
        ArrayList<Delivery> listOfDelivery = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(GET_ALL_DELIVERY_ACTIVE);
                if (rs != null) {
                    while (rs.next()) {
                        Delivery delivery = new Delivery(rs.getInt("DeliveryID"), rs.getString("DeliveryMethod"), rs.getInt("DeliveryPrice"), rs.getString("DeliveryIcon"), rs.getInt("Status"));
                        listOfDelivery.add(delivery);
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
        return listOfDelivery;
    }

    public Delivery getAllDeliveryActiveByID(int DeliveryID) {
        Delivery delivery = null;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_DELIVERY_ACTIVE_BY_ID);
                pst.setInt(1, DeliveryID);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        delivery = new Delivery(rs.getInt("DeliveryID"), rs.getString("DeliveryMethod"), rs.getInt("DeliveryPrice"), rs.getString("DeliveryIcon"), rs.getInt("Status"));
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
        return delivery;
    }
    
    public Delivery getAllDeliveryActiveByOrderID(int orderID) {
        Delivery delivery = null;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_DELIVERY_ACTIVE_BY_ORDER_ID);
                pst.setInt(1, orderID);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        delivery = new Delivery(rs.getInt("DeliveryID"), rs.getString("DeliveryMethod"), rs.getInt("DeliveryPrice"), rs.getString("DeliveryIcon"), rs.getInt("Status"));
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
        return delivery;
    }

    public Map<Integer, Delivery> getDeliveryMap() {
        Map<Integer, Delivery> deliveryMap = new HashMap<>();
        Connection cn = null;

        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                String sql = "SELECT DeliveryID, DeliveryMethod, DeliveryPrice\n"
                        + "FROM tb_Delivery";
                PreparedStatement ps = cn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int deliveryID = rs.getInt("DeliveryID");
                    String deliveryMethod = rs.getString("DeliveryMethod");
                    int deliveryPrice = rs.getInt("DeliveryPrice");
                    Delivery delivery = new Delivery(deliveryID, deliveryMethod, deliveryPrice);
                    deliveryMap.put(deliveryID, delivery);
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

        return deliveryMap;
    }
}
