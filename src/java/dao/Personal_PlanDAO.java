/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Personal_Plan;
import dto.Weekly_Plan_Product;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import utils.DBContext;

/**
 *
 * @author ADMIN
 */
public class Personal_PlanDAO implements Serializable {

    private final String GET_PERSONAL_PLAN = "SELECT Personal_Plan_ID, Weekly_Plan_Product_ID, DayPick, MealId, Type, Quantity, UserID\n"
            + "FROM tb_Personal_Plan";
    private final String GET_PERSONAL_PLAN_WITH_DAY = "SELECT Personal_Plan_ID, Weekly_Plan_Product_ID, DayPick, MealId, Type, Quantity, UserID\n"
            + "FROM tb_Personal_Plan\n"
            + "WHERE DayPick = ?";
    private final String INSERT_PERSONAL_PLAN = "INSERT tb_Personal_Plan(Weekly_Plan_Product_ID,DayPick,MealId,Type,Quantity,UserID) values (?,?,?,?,?,?)";
    private final String REMOVE_PERSONAL_PLAN = "DELETE FROM tb_Personal_Plan\n"
            + "WHERE Personal_Plan_ID = ?";
    private final String UPDATE_STATUS_PERSONAL_PLAN = "UPDATE [dbo].[tb_Personal_Plan]\n"
            + "SET [Status] = ? \n"
            + "WHERE [Personal_Plan_ID] = ?";
    private final String GET_PRODUCT_FROM_WEEKLY_PLAN = "SELECT Weekly_Plan_Product_ID, Weekly_Plan_ID, ProductID,Status\n"
            + "FROM tb_Weekly_Plan_Product\n"
            + "WHERE Weekly_Plan_Product_ID = ?";
    private final String UPDATE_PERSONAL_PLAN = "UPDATE tb_Personal_Plan\n"
            + "SET Type = ?, Quantity = ?\n"
            + "WHERE Personal_Plan_ID = ?";
    private final String GET_PLANS_FOR_MONTH = "SELECT Personal_Plan_ID, Weekly_Plan_Product_ID, DayPick, MealID, Quantity, UserID, Type\n"
            + "FROM tb_Personal_Plan\n"
            + "WHERE MONTH(DayPick) = ? AND YEAR(DayPick) = ?";
    private final String GET_ALL_MEAL = "SELECT MealID, MeadName\n" +
"FROM tb_Meal_Type";

    public ArrayList<Personal_Plan> getPersonal_Plan() {
        ArrayList<Personal_Plan> listOfPersonalPlan = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                Statement st1 = cn.createStatement();
                ResultSet rs1 = st1.executeQuery(GET_PERSONAL_PLAN);
                if (rs1 != null) {
                    while (rs1.next()) {
                        Personal_Plan pp = new Personal_Plan();
                        pp.setPersonal_Plan_ID(rs1.getInt("Personal_Plan_ID"));
                        pp.setWeekly_Plan_Product_ID(rs1.getInt("Weekly_Plan_Product_ID"));
                        pp.setDayPick(rs1.getDate("DayPick"));
                        pp.setMealID(rs1.getInt("MealId"));
                        pp.setType(rs1.getString("Type"));
                        pp.setQuantity(rs1.getInt("Quantity"));
                        pp.setUserID(rs1.getInt("UserID"));

                        // Use a set to track added Weekly_Plan_Product_ID
                        Set<Integer> addedProducts = new HashSet<>();

                        PreparedStatement pst1 = cn.prepareStatement(GET_PRODUCT_FROM_WEEKLY_PLAN);
                        pst1.setInt(1, pp.getWeekly_Plan_Product_ID());
                        ResultSet rs2 = pst1.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                int weeklyPlanProductID = rs2.getInt("Weekly_Plan_Product_ID");
                                if (!addedProducts.contains(weeklyPlanProductID)) {
                                    Weekly_Plan_Product wpp = new Weekly_Plan_Product(weeklyPlanProductID, rs2.getInt("Weekly_Plan_ID"), rs2.getInt("ProductID"), rs2.getInt("Status"));
                                    pp.addProductToList(wpp);
                                    addedProducts.add(weeklyPlanProductID);
                                }
                            }
                        }
                        listOfPersonalPlan.add(pp);
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
        return listOfPersonalPlan;
    }

    public ArrayList<Personal_Plan> getPlansForMonth(int month, int year) {
        ArrayList<Personal_Plan> listOfPersonalPlan = new ArrayList<>();
        Connection cn = null;

        try {
            cn = DBContext.makeConnection();
            PreparedStatement pst = cn.prepareStatement(GET_PLANS_FOR_MONTH);

            pst.setInt(1, month);
            pst.setInt(2, year);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Personal_Plan pp = new Personal_Plan();
                    pp.setPersonal_Plan_ID(rs.getInt("Personal_Plan_ID"));
                    pp.setWeekly_Plan_Product_ID(rs.getInt("Weekly_Plan_Product_ID"));
                    pp.setDayPick(rs.getDate("DayPick"));
                    pp.setMealID(rs.getInt("MealId"));
                    pp.setType(rs.getString("Type"));
                    pp.setQuantity(rs.getInt("Quantity"));
                    pp.setUserID(rs.getInt("UserID"));

                    Set<Integer> addedProducts = new HashSet<>();

                    try (PreparedStatement pst1 = cn.prepareStatement(GET_PRODUCT_FROM_WEEKLY_PLAN)) {
                        pst1.setInt(1, pp.getWeekly_Plan_Product_ID());
                        try (ResultSet rs2 = pst1.executeQuery()) {
                            while (rs2.next()) {
                                int weeklyPlanProductID = rs2.getInt("Weekly_Plan_Product_ID");
                                if (addedProducts.add(weeklyPlanProductID)) {
                                    Weekly_Plan_Product wpp = new Weekly_Plan_Product(
                                            weeklyPlanProductID,
                                            rs2.getInt("Weekly_Plan_ID"),
                                            rs2.getInt("ProductID"),
                                            rs2.getInt("Status")
                                    );
                                    pp.addProductToList(wpp);
                                }
                            }
                        }
                    }

                    listOfPersonalPlan.add(pp);
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

        return listOfPersonalPlan;
    }

    public ArrayList<Personal_Plan> getPersonalPlanForDate(Date selectedDate) {
        ArrayList<Personal_Plan> listOfPersonalPlan = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_PERSONAL_PLAN_WITH_DAY);
                pst.setDate(1, selectedDate);
                ResultSet rs1 = pst.executeQuery();
                if (rs1 != null) {
                    while (rs1.next()) {
                        Personal_Plan pp = new Personal_Plan();
                        pp.setPersonal_Plan_ID(rs1.getInt("Personal_Plan_ID"));
                        pp.setWeekly_Plan_Product_ID(rs1.getInt("Weekly_Plan_Product_ID"));
                        pp.setDayPick(rs1.getDate("DayPick"));
                        pp.setMealID(rs1.getInt("MealId"));
                        pp.setType(rs1.getString("Type"));
                        pp.setQuantity(rs1.getInt("Quantity"));
                        pp.setUserID(rs1.getInt("UserID"));

                        // Use a set to track added Weekly_Plan_Product_IDs
                        Set<Integer> addedProducts = new HashSet<>();

                        PreparedStatement pst1 = cn.prepareStatement(GET_PRODUCT_FROM_WEEKLY_PLAN);
                        pst1.setInt(1, pp.getWeekly_Plan_Product_ID());
                        ResultSet rs2 = pst1.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                int weeklyPlanProductID = rs2.getInt("Weekly_Plan_Product_ID");
                                if (!addedProducts.contains(weeklyPlanProductID)) {
                                    Weekly_Plan_Product wpp = new Weekly_Plan_Product(
                                            weeklyPlanProductID,
                                            rs2.getInt("Weekly_Plan_ID"),
                                            rs2.getInt("ProductID"),
                                            rs2.getInt("Status")
                                    );
                                    pp.addProductToList(wpp);
                                    addedProducts.add(weeklyPlanProductID);
                                }
                            }
                        }
                        listOfPersonalPlan.add(pp);
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
        return listOfPersonalPlan;
    }

    public int insertPersonalPlan(int weeklyPlanProductId, Date dayPick, int mealId, String type, int quantity, int userId) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(INSERT_PERSONAL_PLAN);
                pst.setInt(1, weeklyPlanProductId);
                pst.setDate(2, dayPick);
                pst.setInt(3, mealId);
                pst.setString(4, type);
                pst.setInt(5, quantity);
                pst.setInt(6, userId);
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

    public int removePlanById(int id) {
        int rs = 0;

        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(REMOVE_PERSONAL_PLAN);
                pst.setInt(1, id);
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

    public int updateStatusPersonalPlan(int status, int Personal_Plan_ID) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(UPDATE_STATUS_PERSONAL_PLAN);
                pst.setInt(1, status);
                pst.setInt(2, Personal_Plan_ID);
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

    public int updatePersonalPlan(String type, int quantity, int personal_Plan_ID) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(UPDATE_PERSONAL_PLAN);
                pst.setString(1, type);
                pst.setInt(2, quantity);
                pst.setInt(3, personal_Plan_ID);
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

    public Map<Integer, String> getMealTypeMap() {
        Map<Integer, String> mealTypeMap = new HashMap<>();
        Connection cn = null;

        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                Statement st1 = cn.createStatement();
                ResultSet rs1 = st1.executeQuery(GET_ALL_MEAL);
                if (rs1 != null) {
                    while (rs1.next()) {
                        int mealId = rs1.getInt("MealID");
                        String mealName = rs1.getString("MeadName");
                        mealTypeMap.put(mealId, mealName);
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

        return mealTypeMap;
    }
}
