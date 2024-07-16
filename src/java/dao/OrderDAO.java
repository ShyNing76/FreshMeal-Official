/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Order;
import dto.Order_Detail;
import dto.Product;
import dto.User;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBContext;

/**
 *
 * @author ADMIN
 */
public class OrderDAO implements Serializable {

    private final String GET_ALL_ORDER = "SELECT [OrderID], [AddressID], [OrderDate], [ShipDate], [DeliveryID], [Discount], [Tax], [TotalPrice], [Status], [PaymentMethod], [UserID]\n"
            + "FROM [dbo].[tb_Order]\n"
            + "ORDER BY [OrderDate] DESC";
    private final String GET_ALL_ORDER_BY_USERNAME = "SELECT o.[OrderID], o.[AddressID], o.[OrderDate], o.[ShipDate], o.[DeliveryID], o.[Discount], o.[Tax], o.[TotalPrice], o.[Status], o.[PaymentMethod], o.UserID\n"
            + "FROM [dbo].[tb_Order] o INNER JOIN [dbo].[tb_User] u ON o.UserID = u.UserID\n"
            + "WHERE u.[UserName] = ?";
    private final String GET_PENDING_ORDER_BY_USERNAME = "SELECT o.[OrderID], o.[AddressID], o.[OrderDate], o.[ShipDate], o.[DeliveryID], o.[Discount], o.[Tax], o.[TotalPrice], o.[Status], o.[PaymentMethod], o.UserID\n"
            + "FROM [dbo].[tb_Order] o INNER JOIN [dbo].[tb_User] u ON o.UserID = u.UserID\n"
            + "WHERE u.[UserName] = ? AND o.Status = 'Pending'";
    private final String GET_APPROVE_ORDER_BY_USERNAME = "SELECT o.[OrderID], o.[AddressID], o.[OrderDate], o.[ShipDate], o.[DeliveryID], o.[Discount], o.[Tax], o.[TotalPrice], o.[Status], o.[PaymentMethod], o.UserID\n"
            + "FROM [dbo].[tb_Order] o INNER JOIN [dbo].[tb_User] u ON o.UserID = u.UserID\n"
            + "WHERE u.[UserName] = ? AND o.Status = 'Approve'";
    private final String GET_DELIVERY_ORDER_BY_USERNAME = "SELECT o.[OrderID], o.[AddressID], o.[OrderDate], o.[ShipDate], o.[DeliveryID], o.[Discount], o.[Tax], o.[TotalPrice], o.[Status], o.[PaymentMethod], o.UserID\n"
            + "FROM [dbo].[tb_Order] o INNER JOIN [dbo].[tb_User] u ON o.UserID = u.UserID\n"
            + "WHERE u.[UserName] = ? AND o.Status = 'Delivery'";
    private final String GET_FINSIH_ORDER_BY_USERNAME = "SELECT o.[OrderID], o.[AddressID], o.[OrderDate], o.[ShipDate], o.[DeliveryID], o.[Discount], o.[Tax], o.[TotalPrice], o.[Status], o.[PaymentMethod], o.UserID\n"
            + "FROM [dbo].[tb_Order] o INNER JOIN [dbo].[tb_User] u ON o.UserID = u.UserID\n"
            + "WHERE u.[UserName] = ? AND o.Status = 'Finish'";
    private final String GET_CANCEL_ORDER_BY_USERNAME = "SELECT o.[OrderID], o.[AddressID], o.[OrderDate], o.[ShipDate], o.[DeliveryID], o.[Discount], o.[Tax], o.[TotalPrice], o.[Status], o.[PaymentMethod], o.UserID\n"
            + "FROM [dbo].[tb_Order] o INNER JOIN [dbo].[tb_User] u ON o.UserID = u.UserID\n"
            + "WHERE u.[UserName] = ? AND o.Status = 'Cancel'";
    private final String GET_REFURN_ORDER_BY_USERNAME = "SELECT o.[OrderID], o.[AddressID], o.[OrderDate], o.[ShipDate], o.[DeliveryID], o.[Discount], o.[Tax], o.[TotalPrice], o.[Status], o.[PaymentMethod], o.UserID\n"
            + "FROM [dbo].[tb_Order] o INNER JOIN [dbo].[tb_User] u ON o.UserID = u.UserID\n"
            + "WHERE u.[UserName] = ? AND (o.Status = 'refund' OR o.Status = 'Waiting For Accept')";
    private final String GET_ORDER_BY_ORDER_ID = "SELECT [OrderID],[AddressID],[OrderDate],[ShipDate],[DeliveryID],[Discount],[Tax],[TotalPrice],[Status],[PaymentMethod],[UserID]\n"
            + "FROM [dbo].[tb_Order]\n"
            + "WHERE [OrderID] = ?";
    private final String GET_ORDER_BY_USER_ORDER_ID = "SELECT [OrderID],[AddressID],[OrderDate],[ShipDate],[DeliveryID],[Discount],[Tax],[TotalPrice],[Status],[PaymentMethod],[UserID]\n"
            + "FROM [dbo].[tb_Order]\n"
            + "WHERE [OrderID] = ? AND [UserID] = ?";
    private final String GET_ORDER_DETAIL_BY_ORDER = "SELECT [Order_Detail_ID],[ProductID],[OrderID],[Price],[Quantity],[MealType]\n"
            + "FROM [dbo].[tb_Order_Detail]\n"
            + "WHERE [OrderID] = ?";
    private final String INSERT_ORDER = "INSERT INTO [dbo].[tb_Order] ([AddressID],[DeliveryID],[Discount],[Tax],[TotalPrice],[PaymentMethod],[UserID]) VALUES (?,?,?,?,?,?,?)";
    private final String UPDATE_STATUS_ORDER = "UPDATE tb_Order\n"
            + "SET Status = ?\n"
            + "WHERE OrderID = ?";
    private final String UPDATE_STATUS_ORDER_ADMIN = "UPDATE tb_Order\n"
            + "SET Status = ?, ShipDate = ?\n"
            + "WHERE OrderID = ?";
    private final String GET_THE_LASTED_ORDER = "SELECT TOP 1 [OrderID]\n"
            + "FROM [dbo].[tb_Order]\n"
            + "ORDER BY [OrderID] DESC";
    private final String INSERT_ORDER_DETAIL = "INSERT INTO [dbo].[tb_Order_Detail] ([ProductID],[OrderID],[Price],[Quantity],[MealType]) VALUES (?,?,?,?,?)";

    //Admin
    private final String GET_REVENUE_MAP = "SELECT TOP 10 ShipDate, TotalPrice\n"
            + "FROM tb_Order\n"
            + "WHERE ShipDate IS NOT NULL\n"
            + "ORDER BY ShipDate DESC";
    private final String GET_ORDER_MAP = "SELECT TOP 10 OrderDate, COUNT(OrderID) AS OrderCount \n"
            + "FROM tb_Order\n"
            + "WHERE OrderDate IS NOT NULL\n"
            + "GROUP BY OrderDate\n"
            + "ORDER BY OrderDate DESC";
    private final String GET_TOP_5_NEWEST_ORDER = "SELECT TOP 5 [OrderID], [AddressID], [OrderDate], [ShipDate], [DeliveryID], [Discount], [Tax], [TotalPrice], [Status], [PaymentMethod], [UserID]\n"
            + "FROM [dbo].[tb_Order]\n"
            + "ORDER BY [OrderDate] DESC";
    private final String GET_PRODUCT_FOR_SELLING = "SELECT ProductID, ProductName, Price, Image\n"
            + "FROM tb_Product\n"
            + "WHERE ProductID = ?";
    private final String GET_TOTAL_SOLD = "SELECT TOP 5 ProductID, SUM(Quantity) AS TotalSold\n"
            + "FROM tb_Order_Detail\n"
            + "GROUP BY ProductID\n"
            + "ORDER BY TotalSold DESC";
    private final String GET_TOP_CUSTOMER_IDS = "SELECT TOP 5 UserID, COUNT(OrderID) AS OrderCount\n"
            + "FROM tb_Order\n"
            + "GROUP BY UserID\n"
            + "ORDER BY OrderCount DESC";
    private final String GET_CUSTOMER_INFO = "SELECT UserID, FirstName, LastName, Image\n"
            + "FROM tb_User\n"
            + "WHERE UserID = ?";
    private final String GET_ORDER_BY_ORDER_DATE = "SELECT OrderID, AddressID, OrderDate, ShipDate, DeliveryID, Discount, Tax, TotalPrice, Status, PaymentMethod, UserID\n"
            + "FROM tb_Order\n"
            + "WHERE OrderDate = ?";

    public ArrayList<Order> getAllOrder() {
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(GET_ALL_ORDER);
                if (rs != null) {
                    while (rs.next()) {
                        Order ord = new Order();
                        ord.setOrderID(rs.getInt("OrderID"));
                        ord.setAddressID(rs.getInt("AddressID"));
                        ord.setOrderDate(rs.getDate("OrderDate"));
                        ord.setShipDate(rs.getDate("ShipDate"));
                        ord.setDeliveryID(rs.getInt("DeliveryID"));
                        ord.setDiscount(rs.getInt("Discount"));
                        ord.setTax(rs.getInt("Tax"));
                        ord.setTotalPrice(rs.getInt("TotalPrice"));
                        ord.setStatus(rs.getString("Status"));
                        ord.setPaymentMethod(rs.getString("PaymentMethod"));
                        ord.setUserID(rs.getInt("UserID"));
                        PreparedStatement pst2 = cn.prepareStatement(GET_ORDER_DETAIL_BY_ORDER);
                        pst2.setInt(1, ord.getOrderID());
                        ResultSet rs2 = pst2.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                Order_Detail od = new Order_Detail(rs2.getInt("Order_Detail_ID"), rs2.getInt("ProductID"), ord.getOrderID(),
                                        rs2.getInt("Price"), rs2.getInt("Quantity"), rs2.getString("MealType"));
                                ord.addOrderDetail(od);
                            }
                        }
                        list.add(ord);
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
        return list;
    }

    public ArrayList<Order> getAllOrderByUserName(String userName) {
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_ALL_ORDER_BY_USERNAME);
                pst.setString(1, userName);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        Order ord = new Order();
                        ord.setOrderID(rs.getInt("OrderID"));
                        ord.setAddressID(rs.getInt("AddressID"));
                        ord.setOrderDate(rs.getDate("OrderDate"));
                        ord.setShipDate(rs.getDate("ShipDate"));
                        ord.setDeliveryID(rs.getInt("DeliveryID"));
                        ord.setDiscount(rs.getInt("Discount"));
                        ord.setTax(rs.getInt("Tax"));
                        ord.setTotalPrice(rs.getInt("TotalPrice"));
                        ord.setStatus(rs.getString("Status"));
                        ord.setPaymentMethod(rs.getString("PaymentMethod"));
                        ord.setUserID(rs.getInt("UserID"));
                        PreparedStatement pst2 = cn.prepareStatement(GET_ORDER_DETAIL_BY_ORDER);
                        pst2.setInt(1, ord.getOrderID());
                        ResultSet rs2 = pst2.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                Order_Detail od = new Order_Detail(rs2.getInt("Order_Detail_ID"), rs2.getInt("ProductID"), ord.getOrderID(),
                                        rs2.getInt("Price"), rs2.getInt("Quantity"), rs2.getString("MealType"));
                                ord.addOrderDetail(od);
                            }
                        }
                        list.add(ord);
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
        return list;
    }

    public ArrayList<Order> getPendingOrderByUserName(String userName) {
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_PENDING_ORDER_BY_USERNAME);
                pst.setString(1, userName);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        Order ord = new Order();
                        ord.setOrderID(rs.getInt("OrderID"));
                        ord.setAddressID(rs.getInt("AddressID"));
                        ord.setOrderDate(rs.getDate("OrderDate"));
                        ord.setShipDate(rs.getDate("ShipDate"));
                        ord.setDeliveryID(rs.getInt("DeliveryID"));
                        ord.setDiscount(rs.getInt("Discount"));
                        ord.setTax(rs.getInt("Tax"));
                        ord.setTotalPrice(rs.getInt("TotalPrice"));
                        ord.setStatus(rs.getString("Status"));
                        ord.setPaymentMethod(rs.getString("PaymentMethod"));
                        ord.setUserID(rs.getInt("UserID"));
                        PreparedStatement pst2 = cn.prepareStatement(GET_ORDER_DETAIL_BY_ORDER);
                        pst2.setInt(1, ord.getOrderID());
                        ResultSet rs2 = pst2.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                Order_Detail od = new Order_Detail(rs2.getInt("Order_Detail_ID"), rs2.getInt("ProductID"), ord.getOrderID(),
                                        rs2.getInt("Price"), rs2.getInt("Quantity"), rs2.getString("MealType"));
                                ord.addOrderDetail(od);
                            }
                        }
                        list.add(ord);
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
        return list;
    }

    public ArrayList<Order> getApproveOrderByUserName(String userName) {
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_APPROVE_ORDER_BY_USERNAME);
                pst.setString(1, userName);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        Order ord = new Order();
                        ord.setOrderID(rs.getInt("OrderID"));
                        ord.setAddressID(rs.getInt("AddressID"));
                        ord.setOrderDate(rs.getDate("OrderDate"));
                        ord.setShipDate(rs.getDate("ShipDate"));
                        ord.setDeliveryID(rs.getInt("DeliveryID"));
                        ord.setDiscount(rs.getInt("Discount"));
                        ord.setTax(rs.getInt("Tax"));
                        ord.setTotalPrice(rs.getInt("TotalPrice"));
                        ord.setStatus(rs.getString("Status"));
                        ord.setPaymentMethod(rs.getString("PaymentMethod"));
                        ord.setUserID(rs.getInt("UserID"));
                        PreparedStatement pst2 = cn.prepareStatement(GET_ORDER_DETAIL_BY_ORDER);
                        pst2.setInt(1, ord.getOrderID());
                        ResultSet rs2 = pst2.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                Order_Detail od = new Order_Detail(rs2.getInt("Order_Detail_ID"), rs2.getInt("ProductID"), ord.getOrderID(),
                                        rs2.getInt("Price"), rs2.getInt("Quantity"), rs2.getString("MealType"));
                                ord.addOrderDetail(od);
                            }
                        }
                        list.add(ord);
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
        return list;
    }

    public ArrayList<Order> getDeliveryOrderByUserName(String userName) {
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_DELIVERY_ORDER_BY_USERNAME);
                pst.setString(1, userName);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        Order ord = new Order();
                        ord.setOrderID(rs.getInt("OrderID"));
                        ord.setAddressID(rs.getInt("AddressID"));
                        ord.setOrderDate(rs.getDate("OrderDate"));
                        ord.setShipDate(rs.getDate("ShipDate"));
                        ord.setDeliveryID(rs.getInt("DeliveryID"));
                        ord.setDiscount(rs.getInt("Discount"));
                        ord.setTax(rs.getInt("Tax"));
                        ord.setTotalPrice(rs.getInt("TotalPrice"));
                        ord.setStatus(rs.getString("Status"));
                        ord.setPaymentMethod(rs.getString("PaymentMethod"));
                        ord.setUserID(rs.getInt("UserID"));
                        PreparedStatement pst2 = cn.prepareStatement(GET_ORDER_DETAIL_BY_ORDER);
                        pst2.setInt(1, ord.getOrderID());
                        ResultSet rs2 = pst2.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                Order_Detail od = new Order_Detail(rs2.getInt("Order_Detail_ID"), rs2.getInt("ProductID"), ord.getOrderID(),
                                        rs2.getInt("Price"), rs2.getInt("Quantity"), rs2.getString("MealType"));
                                ord.addOrderDetail(od);
                            }
                        }
                        list.add(ord);
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
        return list;
    }

    public ArrayList<Order> getFinishOrderByUserName(String userName) {
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_FINSIH_ORDER_BY_USERNAME);
                pst.setString(1, userName);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        Order ord = new Order();
                        ord.setOrderID(rs.getInt("OrderID"));
                        ord.setAddressID(rs.getInt("AddressID"));
                        ord.setOrderDate(rs.getDate("OrderDate"));
                        ord.setShipDate(rs.getDate("ShipDate"));
                        ord.setDeliveryID(rs.getInt("DeliveryID"));
                        ord.setDiscount(rs.getInt("Discount"));
                        ord.setTax(rs.getInt("Tax"));
                        ord.setTotalPrice(rs.getInt("TotalPrice"));
                        ord.setStatus(rs.getString("Status"));
                        ord.setPaymentMethod(rs.getString("PaymentMethod"));
                        ord.setUserID(rs.getInt("UserID"));
                        PreparedStatement pst2 = cn.prepareStatement(GET_ORDER_DETAIL_BY_ORDER);
                        pst2.setInt(1, ord.getOrderID());
                        ResultSet rs2 = pst2.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                Order_Detail od = new Order_Detail(rs2.getInt("Order_Detail_ID"), rs2.getInt("ProductID"), ord.getOrderID(),
                                        rs2.getInt("Price"), rs2.getInt("Quantity"), rs2.getString("MealType"));
                                ord.addOrderDetail(od);
                            }
                        }
                        list.add(ord);
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
        return list;
    }

    public ArrayList<Order> getCancelOrderByUserName(String userName) {
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_CANCEL_ORDER_BY_USERNAME);
                pst.setString(1, userName);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        Order ord = new Order();
                        ord.setOrderID(rs.getInt("OrderID"));
                        ord.setAddressID(rs.getInt("AddressID"));
                        ord.setOrderDate(rs.getDate("OrderDate"));
                        ord.setShipDate(rs.getDate("ShipDate"));
                        ord.setDeliveryID(rs.getInt("DeliveryID"));
                        ord.setDiscount(rs.getInt("Discount"));
                        ord.setTax(rs.getInt("Tax"));
                        ord.setTotalPrice(rs.getInt("TotalPrice"));
                        ord.setStatus(rs.getString("Status"));
                        ord.setPaymentMethod(rs.getString("PaymentMethod"));
                        ord.setUserID(rs.getInt("UserID"));
                        PreparedStatement pst2 = cn.prepareStatement(GET_ORDER_DETAIL_BY_ORDER);
                        pst2.setInt(1, ord.getOrderID());
                        ResultSet rs2 = pst2.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                Order_Detail od = new Order_Detail(rs2.getInt("Order_Detail_ID"), rs2.getInt("ProductID"), ord.getOrderID(),
                                        rs2.getInt("Price"), rs2.getInt("Quantity"), rs2.getString("MealType"));
                                ord.addOrderDetail(od);
                            }
                        }
                        list.add(ord);
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
        return list;
    }

    public ArrayList<Order> getRefurnOrderByUserName(String userName) {
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_REFURN_ORDER_BY_USERNAME);
                pst.setString(1, userName);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        Order ord = new Order();
                        ord.setOrderID(rs.getInt("OrderID"));
                        ord.setAddressID(rs.getInt("AddressID"));
                        ord.setOrderDate(rs.getDate("OrderDate"));
                        ord.setShipDate(rs.getDate("ShipDate"));
                        ord.setDeliveryID(rs.getInt("DeliveryID"));
                        ord.setDiscount(rs.getInt("Discount"));
                        ord.setTax(rs.getInt("Tax"));
                        ord.setTotalPrice(rs.getInt("TotalPrice"));
                        ord.setStatus(rs.getString("Status"));
                        ord.setPaymentMethod(rs.getString("PaymentMethod"));
                        ord.setUserID(rs.getInt("UserID"));
                        PreparedStatement pst2 = cn.prepareStatement(GET_ORDER_DETAIL_BY_ORDER);
                        pst2.setInt(1, ord.getOrderID());
                        ResultSet rs2 = pst2.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                Order_Detail od = new Order_Detail(rs2.getInt("Order_Detail_ID"), rs2.getInt("ProductID"), ord.getOrderID(),
                                        rs2.getInt("Price"), rs2.getInt("Quantity"), rs2.getString("MealType"));
                                ord.addOrderDetail(od);
                            }
                        }
                        list.add(ord);
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
        return list;
    }

    public Order getOrderByID(int orderID) {
        Order ord = null;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_ORDER_BY_ORDER_ID);
                pst.setInt(1, orderID);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        ord = new Order();
                        ord.setOrderID(rs.getInt("OrderID"));
                        ord.setAddressID(rs.getInt("AddressID"));
                        ord.setOrderDate(rs.getDate("OrderDate"));
                        ord.setShipDate(rs.getDate("ShipDate"));
                        ord.setDeliveryID(rs.getInt("DeliveryID"));
                        ord.setDiscount(rs.getInt("Discount"));
                        ord.setTax(rs.getInt("Tax"));
                        ord.setTotalPrice(rs.getInt("TotalPrice"));
                        ord.setStatus(rs.getString("Status"));
                        ord.setPaymentMethod(rs.getString("PaymentMethod"));
                        ord.setUserID(rs.getInt("UserID"));
                        PreparedStatement pst2 = cn.prepareStatement(GET_ORDER_DETAIL_BY_ORDER);
                        pst2.setInt(1, ord.getOrderID());
                        ResultSet rs2 = pst2.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                Order_Detail od = new Order_Detail(rs2.getInt("Order_Detail_ID"), rs2.getInt("ProductID"), ord.getOrderID(),
                                        rs2.getInt("Price"), rs2.getInt("Quantity"), rs2.getString("MealType"));
                                ord.addOrderDetail(od);
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
        return ord;
    }
    
    public Order getOrderByUserOrderID(int orderID, int userID) {
        Order ord = null;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_ORDER_BY_USER_ORDER_ID);
                pst.setInt(1, orderID);
                pst.setInt(2, userID);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        ord = new Order();
                        ord.setOrderID(rs.getInt("OrderID"));
                        ord.setAddressID(rs.getInt("AddressID"));
                        ord.setOrderDate(rs.getDate("OrderDate"));
                        ord.setShipDate(rs.getDate("ShipDate"));
                        ord.setDeliveryID(rs.getInt("DeliveryID"));
                        ord.setDiscount(rs.getInt("Discount"));
                        ord.setTax(rs.getInt("Tax"));
                        ord.setTotalPrice(rs.getInt("TotalPrice"));
                        ord.setStatus(rs.getString("Status"));
                        ord.setPaymentMethod(rs.getString("PaymentMethod"));
                        ord.setUserID(rs.getInt("UserID"));
                        PreparedStatement pst2 = cn.prepareStatement(GET_ORDER_DETAIL_BY_ORDER);
                        pst2.setInt(1, ord.getOrderID());
                        ResultSet rs2 = pst2.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                Order_Detail od = new Order_Detail(rs2.getInt("Order_Detail_ID"), rs2.getInt("ProductID"), ord.getOrderID(),
                                        rs2.getInt("Price"), rs2.getInt("Quantity"), rs2.getString("MealType"));
                                ord.addOrderDetail(od);
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
        return ord;
    }

    public int insertOrder(int AddressID, int DeliveryID, int Discount, int Tax, int TotalPrice, String PaymentMethod, int UserID, HashMap<Product, Integer> cart) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                cn.setAutoCommit(false);
                PreparedStatement pst = cn.prepareStatement(INSERT_ORDER);
                pst.setInt(1, AddressID);
                pst.setInt(2, DeliveryID);
                pst.setInt(3, Discount);
                pst.setInt(4, Tax);
                pst.setInt(5, TotalPrice);
                pst.setString(6, PaymentMethod);
                pst.setInt(7, UserID);
                rs = pst.executeUpdate();
                pst = cn.prepareStatement(GET_THE_LASTED_ORDER);
                ResultSet table = pst.executeQuery();
                if (table != null && table.next()) {
                    int OrderID = table.getInt("OrderID");
                    for (Product product : cart.keySet()) {
                        int quantity = cart.get(product);
                        pst = cn.prepareStatement(INSERT_ORDER_DETAIL);
                        pst.setInt(1, product.getProductID());
                        pst.setInt(2, OrderID);
                        pst.setInt(3, product.getPrice());
                        pst.setInt(4, quantity);
                        pst.setString(5, product.getMealType());
                        rs = pst.executeUpdate();
                    }
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

    public int updateStatusOrder(String status, int OrderID) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(UPDATE_STATUS_ORDER);
                pst.setString(1, status);
                pst.setInt(2, OrderID);
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

    public ArrayList<Order> getTop5OrderLastest() {
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(GET_TOP_5_NEWEST_ORDER);
                if (rs != null) {
                    while (rs.next()) {
                        Order ord = new Order();
                        ord.setOrderID(rs.getInt("OrderID"));
                        ord.setAddressID(rs.getInt("AddressID"));
                        ord.setOrderDate(rs.getDate("OrderDate"));
                        ord.setShipDate(rs.getDate("ShipDate"));
                        ord.setDeliveryID(rs.getInt("DeliveryID"));
                        ord.setDiscount(rs.getInt("Discount"));
                        ord.setTax(rs.getInt("Tax"));
                        ord.setTotalPrice(rs.getInt("TotalPrice"));
                        ord.setStatus(rs.getString("Status"));
                        ord.setPaymentMethod(rs.getString("PaymentMethod"));
                        ord.setUserID(rs.getInt("UserID"));
                        PreparedStatement pst2 = cn.prepareStatement(GET_ORDER_DETAIL_BY_ORDER);
                        pst2.setInt(1, ord.getOrderID());
                        ResultSet rs2 = pst2.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                Order_Detail od = new Order_Detail(rs2.getInt("Order_Detail_ID"), rs2.getInt("ProductID"), ord.getOrderID(),
                                        rs2.getInt("Price"), rs2.getInt("Quantity"), rs2.getString("MealType"));
                                ord.addOrderDetail(od);
                            }
                        }
                        list.add(ord);
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
        return list;
    }

    public ArrayList<Product> getBestSellingDishes() {
        Map<Integer, Integer> totalSoldMap = new HashMap<>();
        ArrayList<Product> bestSellingDishes = new ArrayList<>();
        Connection cn = null;

        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_TOTAL_SOLD);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int productId = rs.getInt("ProductID");
                        int totalSold = rs.getInt("TotalSold");
                        totalSoldMap.put(productId, totalSold);
                    }
                }

                for (Integer productid : totalSoldMap.keySet()) {
                    PreparedStatement pst2 = cn.prepareStatement(GET_PRODUCT_FOR_SELLING);
                    pst2.setInt(1, productid);
                    ResultSet rs2 = pst2.executeQuery();
                    if (rs2 != null && rs2.next()) {
                        String productName = rs2.getString("ProductName");
                        int price = rs2.getInt("Price");
                        String image = rs2.getString("Image");
                        int totalSold = totalSoldMap.get(productid);

                        Product product = new Product(productid, productName, image, price, totalSold);
                        bestSellingDishes.add(product);
                    }
                }

                // Sort the list by totalSold in descending order
                Collections.sort(bestSellingDishes, new Comparator<Product>() {
                    @Override
                    public int compare(Product p1, Product p2) {
                        return Integer.compare(p2.getTotalSold(), p1.getTotalSold());
                    }
                });

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

        return bestSellingDishes;
    }

    public ArrayList<User> getTopCustomers() {
        ArrayList<User> topCustomers = new ArrayList<>();
        Map<Integer, Integer> customerOrderCounts = new HashMap<>();
        Connection cn = null;

        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                // Get top customer IDs and their order counts
                PreparedStatement pst = cn.prepareStatement(GET_TOP_CUSTOMER_IDS);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int userId = rs.getInt("UserID");
                        int orderCount = rs.getInt("OrderCount");
                        customerOrderCounts.put(userId, orderCount);
                    }
                }

                // Get customer info for each top customer ID
                for (Map.Entry<Integer, Integer> entry : customerOrderCounts.entrySet()) {
                    int userId = entry.getKey();
                    int orderCount = entry.getValue();

                    PreparedStatement pst2 = cn.prepareStatement(GET_CUSTOMER_INFO);
                    pst2.setInt(1, userId);
                    ResultSet rs2 = pst2.executeQuery();
                    if (rs2 != null && rs2.next()) {
                        String firstName = rs2.getString("FirstName");
                        String lastName = rs2.getString("LastName");
                        String image = rs2.getString("Image");

                        User user = new User(userId, firstName, lastName, image, orderCount);
                        topCustomers.add(user);
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

        return topCustomers;
    }

    public Map<Date, Integer> getRevenueMap() {
        Map<Date, Integer> revenueMap = new TreeMap<>();
        Connection cn = null;

        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement ps = cn.prepareStatement(GET_REVENUE_MAP);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Date shipDate = rs.getDate("ShipDate");
                    int revenue = rs.getInt("TotalPrice");

                    revenueMap.put(shipDate, revenueMap.getOrDefault(shipDate, 0) + revenue);
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

        return revenueMap;
    }

    public Map<Date, Integer> getOrderMap() {
        Map<Date, Integer> orderMap = new TreeMap<>();
        Connection cn = null;

        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement ps = cn.prepareStatement(GET_ORDER_MAP);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Date shipDate = rs.getDate("OrderDate");
                    int orderCount = rs.getInt("OrderCount");

                    orderMap.put(shipDate, orderCount);
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

        return orderMap;
    }

    public ArrayList<Order> getOrderByOrderDate(Date orderDate) {
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_ORDER_BY_ORDER_DATE);
                pst.setDate(1, orderDate);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        Order ord = new Order();
                        ord.setOrderID(rs.getInt("OrderID"));
                        ord.setAddressID(rs.getInt("AddressID"));
                        ord.setOrderDate(rs.getDate("OrderDate"));
                        ord.setShipDate(rs.getDate("ShipDate"));
                        ord.setDeliveryID(rs.getInt("DeliveryID"));
                        ord.setDiscount(rs.getInt("Discount"));
                        ord.setTax(rs.getInt("Tax"));
                        ord.setTotalPrice(rs.getInt("TotalPrice"));
                        ord.setStatus(rs.getString("Status"));
                        ord.setPaymentMethod(rs.getString("PaymentMethod"));
                        ord.setUserID(rs.getInt("UserID"));
                        PreparedStatement pst2 = cn.prepareStatement(GET_ORDER_DETAIL_BY_ORDER);
                        pst2.setInt(1, ord.getOrderID());
                        ResultSet rs2 = pst2.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                Order_Detail od = new Order_Detail(rs2.getInt("Order_Detail_ID"), rs2.getInt("ProductID"), ord.getOrderID(),
                                        rs2.getInt("Price"), rs2.getInt("Quantity"), rs2.getString("MealType"));
                                ord.addOrderDetail(od);
                            }
                        }
                        list.add(ord);
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
        return list;
    }

    public ArrayList<Order> getAllOrderFilter(String status, Date orderDate, String customerContact) {
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                String sql = "SELECT [OrderID], [AddressID], [OrderDate], [ShipDate], [DeliveryID], [Discount], [Tax], [TotalPrice], [Status], [PaymentMethod], [UserID]\n"
                        + "FROM [dbo].[tb_Order]\n"
                        + "WHERE 1 = 1";

                if (status != null && !status.isEmpty()) {
                    sql += " AND Status = ?";
                }
                if (orderDate != null) {
                    sql += " AND OrderDate = ?";
                }

                PreparedStatement pst = cn.prepareStatement(sql);
                int paramIndex = 1;

                if (status != null && !status.isEmpty()) {
                    pst.setString(paramIndex++, status);
                }

                if (orderDate != null) {
                    pst.setDate(paramIndex++, orderDate);
                }

                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        Order ord = new Order();
                        ord.setOrderID(rs.getInt("OrderID"));
                        ord.setAddressID(rs.getInt("AddressID"));
                        ord.setOrderDate(rs.getDate("OrderDate"));
                        ord.setShipDate(rs.getDate("ShipDate"));
                        ord.setDeliveryID(rs.getInt("DeliveryID"));
                        ord.setDiscount(rs.getInt("Discount"));
                        ord.setTax(rs.getInt("Tax"));
                        ord.setTotalPrice(rs.getInt("TotalPrice"));
                        ord.setStatus(rs.getString("Status"));
                        ord.setPaymentMethod(rs.getString("PaymentMethod"));
                        ord.setUserID(rs.getInt("UserID"));
                        PreparedStatement pst2 = cn.prepareStatement(GET_ORDER_DETAIL_BY_ORDER);
                        pst2.setInt(1, ord.getOrderID());
                        ResultSet rs2 = pst2.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                Order_Detail od = new Order_Detail(rs2.getInt("Order_Detail_ID"), rs2.getInt("ProductID"), ord.getOrderID(),
                                        rs2.getInt("Price"), rs2.getInt("Quantity"), rs2.getString("MealType"));
                                ord.addOrderDetail(od);
                            }
                        }
                        if (customerContact != null && !customerContact.isEmpty()) {
                            if (isCustomerContactMatch(rs.getInt("UserID"), customerContact)) {
                                list.add(ord);
                            }
                        } else {
                            list.add(ord);
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
        return list;
    }

    private boolean isCustomerContactMatch(int userID, String customerContact) {
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                String sql = "SELECT*\n"
                        + "FROM tb_User\n"
                        + "WHERE UserID = ? AND (Phone = ? OR Email = ?)";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, userID);
                pst.setString(2, customerContact);
                pst.setString(3, customerContact);

                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public int updateStatusOrderAdmin(String status, int OrderID, LocalDate shipDate) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(UPDATE_STATUS_ORDER_ADMIN);
                pst.setString(1, status);
                if (shipDate != null) {
                    pst.setDate(2, java.sql.Date.valueOf(shipDate));
                } else {
                    pst.setNull(2, java.sql.Types.DATE);
                }
                pst.setInt(3, OrderID);
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
