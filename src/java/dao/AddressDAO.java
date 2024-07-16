/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Address;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBContext;

/**
 *
 * @author ADMIN
 */
public class AddressDAO implements Serializable {

    private final String GET_ADDRESS = "SELECT [AddressID], [Address], [District], [City]\n"
            + "FROM [dbo].[tb_Address]";
    private final String GET_ADDRESS_BY_ID = "SELECT a.[AddressID], a.[Address], a.[District], a.[City]\n"
            + "FROM [dbo].[tb_User_Address] ua INNER JOIN [dbo].[tb_Address] a ON ua.AddressID = a.AddressID\n"
            + "                                INNER JOIN [dbo].[tb_User] u ON ua.UserID = u.UserID\n"
            + "WHERE a.[AddressID] = ?";
    private final String INSERT_ADDRESS = "INSERT INTO [dbo].[tb_Address] ([Address],[District]) VALUES (?,?)";
    private final String INSERT_USER_ADDRESS = "INSERT INTO [dbo].[tb_User_Address] ([AddressID],[UserID]) VALUES (?,?)";
    private final String UPDATE_ADDRESS = "UPDATE [dbo].[tb_Address]\n"
            + "SET [Address] = ?, [District] = ?\n"
            + "WHERE [AddressID] = ?";
    private final String DELETE_USER_ADDRESS = "DELETE \n"
            + "FROM [dbo].[tb_User_Address]\n"
            + "WHERE [AddressID] = ?";
    private final String DELETE_ADDRESS = "DELETE \n"
            + "FROM [dbo].[tb_Address]\n"
            + "WHERE [AddressID] = ?";
    private final String GET_ADDRESS_BY_USER_NAME = "SELECT a.AddressID, a.Address, a.District, a.City\n"
            + "FROM [dbo].[tb_User_Address] ua INNER JOIN [dbo].[tb_User] u ON ua.UserID = u.UserID\n"
            + "                                INNER JOIN [dbo].[tb_Address] a ON ua.AddressID = a.AddressID\n"
            + "WHERE u.[UserName] = ?";
    private final String GET_ADDRESS_BY_ORDER_ID = "SELECT a.AddressID, a.Address, a.District, a.City\n"
            + "FROM [dbo].[tb_Order] o INNER JOIN [dbo].[tb_Address] a ON o.AddressID = a.AddressID\n"
            + "WHERE o.OrderID = ?";
    private final String GET_THE_NEW_ADDRESS = "SELECT TOP 1 [AddressID]\n"
            + "FROM [dbo].[tb_Address]\n"
            + "ORDER BY [AddressID] DESC";
    private final String INSERT_ADDRESS_BY_USER = "INSERT tb_User_Address(UserID, AddressID) Values (?,?)";

    public ArrayList<Address> getAddress() {
        ArrayList<Address> listOfAddress = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(GET_ADDRESS);
                if (rs != null) {
                    while (rs.next()) {
                        Address a = new Address(rs.getInt("AddressID"), rs.getString("Address"), rs.getString("District"), rs.getString("City"));
                        listOfAddress.add(a);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOfAddress;
    }

    public Address getAddressByID(int addressID) {
        Address address = null;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_ADDRESS_BY_ID);
                pst.setInt(1, addressID);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        address = new Address(rs.getInt("AddressID"), rs.getString("Address"), rs.getString("District"), rs.getString("City"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return address;
    }
    
    public Address getAddressByOrderID(int orderID) {
        Address address = null;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_ADDRESS_BY_ORDER_ID);
                pst.setInt(1, orderID);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        address = new Address(rs.getInt("AddressID"), rs.getString("Address"), rs.getString("District"), rs.getString("City"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return address;
    }

    public Address getTheNewAddress() {
        Address address = null;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(GET_THE_NEW_ADDRESS);
                if (rs != null) {
                    while (rs.next()) {
                        address = new Address(rs.getInt("AddressID"), rs.getString("Address"), rs.getString("District"), rs.getString("City"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return address;
    }

    public ArrayList<Address> getAddressByUser(String userName) {
        ArrayList<Address> listOfAddress = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_ADDRESS_BY_USER_NAME);
                pst.setString(1, userName);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        Address a = new Address(rs.getInt("AddressID"), rs.getString("Address"), rs.getString("District"), rs.getString("City"));
                        listOfAddress.add(a);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOfAddress;
    }

    public int insertUserAddress(String Address, String District, int userID) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                cn.setAutoCommit(false);
                PreparedStatement pst = cn.prepareStatement(INSERT_ADDRESS);
                pst.setString(1, Address);
                pst.setString(2, District);
                rs = pst.executeUpdate();
                pst = cn.prepareStatement(GET_THE_NEW_ADDRESS);
                ResultSet table = pst.executeQuery();
                if (table != null && table.next()) {
                    pst = cn.prepareStatement(INSERT_USER_ADDRESS);
                    pst.setInt(1, table.getInt("AddressID"));
                    pst.setInt(2, userID);
                    rs = pst.executeUpdate();
                    cn.commit();
                }
                cn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            if (cn != null) {
                try {
                    cn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public int updateAddress(String Address, String district, int addressID) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(UPDATE_ADDRESS);
                pst.setString(1, Address);
                pst.setString(2, district);
                pst.setInt(3, addressID);
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

    public int deleteAddress(int addressID) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                cn.setAutoCommit(false);
                PreparedStatement pst = cn.prepareStatement(GET_ADDRESS_BY_ID);
                pst.setInt(1, addressID);
                ResultSet table = pst.executeQuery();
                if (table != null && table.next()) {
                    pst = cn.prepareStatement(DELETE_USER_ADDRESS);
                    pst.setInt(1, table.getInt("AddressID"));
                    rs = pst.executeUpdate();
                    cn.commit();
                }
                cn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            if (cn != null) {
                try {
                    cn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public Map<Integer, Address> getAddressMap() {
        Map<Integer, Address> addressMap = new HashMap<>();
        Connection cn = null;

        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                Statement st1 = cn.createStatement();
                ResultSet rs1 = st1.executeQuery(GET_ADDRESS);
                if (rs1 != null) {
                    while (rs1.next()) {
                        int addressId = rs1.getInt("AddressID");
                        String address = rs1.getString("Address");
                        String district = rs1.getString("District");
                        String city = rs1.getString("City");
                        Address addressObj = new Address(addressId, address, district, city);
                        addressMap.put(addressId, addressObj);
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

        return addressMap;
    }
    
    public int insertAddress(String Address, String District, String City) {
        int addressid = 0;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(INSERT_ADDRESS, PreparedStatement.RETURN_GENERATED_KEYS);
                pst.setString(1, Address);
                pst.setString(2, District);
                pst.setString(3, City);
                int rs = pst.executeUpdate();

                if (rs == 0) {
                    throw new SQLException("Inserting address failed, no rows affected.");
                } else {
                    try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            addressid = generatedKeys.getInt(1);
                        } else {
                            throw new SQLException("Inserting address failed, no ID obtained.");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
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
        return addressid;
    }
    
    public int insertAddressByUser(int userId, int addressId) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(INSERT_ADDRESS_BY_USER);
                pst.setInt(1, userId);
                pst.setInt(2, addressId);
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
