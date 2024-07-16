/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Weekly_Plan;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import utils.DBContext;

/**
 *
 * @author ADMIN
 */
public class Weekly_PlanDAO implements Serializable {

    private final String GET_WEEKLY_PLAN_IS_ACTIVE = "SELECT [Weekly_Plan_ID],[StartDate],[EndDate],[Status]\n"
            + "FROM [dbo].[tb_Weekly_Plan]\n"
            + "WHERE [Status] = 1\n"
            + "ORDER by [StartDate]";
    private final String GET_THE_FIRST_WEEKLY_PLAN_IS_ACTIVE = "SELECT top 1 [Weekly_Plan_ID],[StartDate],[EndDate],[Status]\n"
            + "FROM [dbo].[tb_Weekly_Plan]\n"
            + "WHERE [Status] = 1\n"
            + "ORDER BY [StartDate]";
    private final String GET_ALL_WEEKLY_PLAN = "SELECT [Weekly_Plan_ID],[StartDate],[EndDate],[Status]\n"
            + "FROM [dbo].[tb_Weekly_Plan]";
    private final String INSERT_PRODUCT_TO_WEEKLY_PLAN = "INSERT INTO [dbo].[tb_Weekly_Plan] ([StartDate],[EndDate],[Status]) VALUES (?,?,?)";
    private final String INSERT_WEEKLY_PLAN = "INSERT INTO tb_Weekly_Plan (StartDate, EndDate, Status) values (?,?,1)";
    private final String INSERT_PRODUCT_IN_WEEKLY = "INSERT INTO tb_Weekly_Plan_Product (Weekly_Plan_ID, ProductID, Status) values (?,?,1)";
    private final String UPDATE_STATUS_WEEKLY_PLAN = "UPDATE [dbo].[tb_Weekly_Plan]\n"
            + "SET [Status] = ? \n"
            + "WHERE [Weekly_Plan_ID] = ?";
    private final String GET_WEEKLY_PLAN_ID = "SELECT DISTINCT Weekly_Plan_ID FROM tb_Weekly_Plan_Product";
    private final String GET_WEEKLY_PLAN_ID_BY_DAY_PICK = "SELECT Weekly_Plan_ID\n"
            + "FROM tb_Weekly_Plan \n"
            + "WHERE ? BETWEEN StartDate AND EndDate";
    private final String GET_START_END_DATE_BY_ID = "SELECT startDate, endDate \n"
            + "FROM tb_Weekly_Plan \n"
            + "WHERE Weekly_Plan_ID = ?";
    private final String UPDATE_WEEKLY_PLAN = "UPDATE tb_Weekly_Plan \n"
            + "SET StartDate = ? , EndDate = ?\n"
            + "WHERE Weekly_Plan_ID = ?";
    private final String GET_WEEKLY_PLAN_BY_STATUS = "SELECT [Weekly_Plan_ID],[StartDate],[EndDate],[Status]\n"
            + "FROM [dbo].[tb_Weekly_Plan]\n"
            + "WHERE Status = ?";

    public ArrayList<Weekly_Plan> getAllWeekly_Plan() {
        ArrayList<Weekly_Plan> listOfWeekly_Plan = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(GET_ALL_WEEKLY_PLAN);
                if (rs != null) {
                    while (rs.next()) {
                        Weekly_Plan wp = new Weekly_Plan(rs.getInt("Weekly_Plan_ID"), rs.getDate("StartDate"), rs.getDate("EndDate"), rs.getInt("Status"));
                        listOfWeekly_Plan.add(wp);
                    }
                }
            }
        } catch (Exception e) {
        }
        return listOfWeekly_Plan;
    }

    public ArrayList<Weekly_Plan> getWeekly_PlanIsActive() {
        ArrayList<Weekly_Plan> listOfWeekly_Plan = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(GET_WEEKLY_PLAN_IS_ACTIVE);
                if (rs != null) {
                    while (rs.next()) {
                        Weekly_Plan wp = new Weekly_Plan(rs.getInt("Weekly_Plan_ID"), rs.getDate("StartDate"), rs.getDate("EndDate"), rs.getInt("Status"));
                        listOfWeekly_Plan.add(wp);
                    }
                }
            }
        } catch (Exception e) {
        }
        return listOfWeekly_Plan;
    }

    public Weekly_Plan getTheFirstWeekly_PlanIsActive() {
        Weekly_Plan weekly_Plan = null;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(GET_THE_FIRST_WEEKLY_PLAN_IS_ACTIVE);
                if (rs != null) {
                    while (rs.next()) {
                        weekly_Plan = new Weekly_Plan(rs.getInt("Weekly_Plan_ID"), rs.getDate("StartDate"), rs.getDate("EndDate"), rs.getInt("Status"));

                    }
                }
            }
        } catch (Exception e) {
        }
        return weekly_Plan;
    }

    public int insertProductToWeekly_Plan(int Weekly_Plan_ID, String Name, int ProductID, Date StartDate, Date EndDate, int Status) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(INSERT_PRODUCT_TO_WEEKLY_PLAN);
                pst.setString(1, Name);
                pst.setInt(2, ProductID);
                pst.setDate(3, StartDate);
                pst.setDate(4, EndDate);
                pst.setInt(5, Status);
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

    public int updateStatusWeekly_Plan(int status, int Weekly_Plan_ID) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(UPDATE_STATUS_WEEKLY_PLAN);
                pst.setInt(1, status);
                pst.setInt(2, Weekly_Plan_ID);
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

    public Weekly_Plan getDateWeeklyPlanByID(int weeklyPlanID) {
        Weekly_Plan weekly_Plan = null;

        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                pst = cn.prepareStatement(GET_START_END_DATE_BY_ID);
                pst.setInt(1, weeklyPlanID);

                rs = pst.executeQuery();
                if (rs.next()) {
                    Date startDate = rs.getDate("startDate");
                    Date endDate = rs.getDate("endDate");

                    weekly_Plan = new Weekly_Plan();
                    weekly_Plan.setStartDate(startDate);
                    weekly_Plan.setEndDate(endDate);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return weekly_Plan;
    }

    public int getWeeklyPlanIdByDayPick(Date daypick) {
        int weeklyPlanId = 0;

        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_WEEKLY_PLAN_ID_BY_DAY_PICK);
                pst.setDate(1, daypick);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    weeklyPlanId = rs.getInt("Weekly_Plan_ID");
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

        return weeklyPlanId;
    }

    public ArrayList<Integer> getWeeklyPlanId() {
        ArrayList<Integer> weeklyPlanIDs = new ArrayList<>();

        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(GET_WEEKLY_PLAN_ID);
                if (rs != null) {
                    while (rs.next()) {
                        int weeklyPlanID = rs.getInt("Weekly_Plan_ID");
                        weeklyPlanIDs.add(weeklyPlanID);
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

        return weeklyPlanIDs;
    }

    public boolean insertWeeklyPlan(Date startDate, Date endDate, ArrayList<Integer> listProduct) {

        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean hasInsert = false;

        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                pst = cn.prepareStatement(INSERT_WEEKLY_PLAN, PreparedStatement.RETURN_GENERATED_KEYS);
                pst.setDate(1, startDate);
                pst.setDate(2, endDate);
                int affectedRows = pst.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Creating weekly plan failed, no rows affected.");
                }

                int weeklyPlanId;
                rs = pst.getGeneratedKeys();
                if (rs.next()) {
                    weeklyPlanId = rs.getInt(1);
                } else {
                    throw new SQLException("Creating weekly plan failed, no ID obtained.");
                }

                pst = cn.prepareStatement(INSERT_PRODUCT_IN_WEEKLY);
                for (Integer productid : listProduct) {
                    pst.setInt(1, weeklyPlanId);
                    pst.setInt(2, productid);
                    pst.addBatch();
                }
                pst.executeBatch();

                hasInsert = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            hasInsert = false;
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                    pst.close();
                    rs.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return hasInsert;
    }

    public boolean updateWeeklyPlan(int weeklyPlanId, Date startDate, Date endDate, ArrayList<Integer> listProduct) {

        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean hasUpdate = false;

        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                pst = cn.prepareStatement(UPDATE_WEEKLY_PLAN);
                pst.setDate(1, startDate);
                pst.setDate(2, endDate);
                pst.setInt(3, weeklyPlanId);
                int affectedRows = pst.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Creating weekly plan failed, no rows affected.");
                }

                pst = cn.prepareStatement(INSERT_PRODUCT_IN_WEEKLY);
                for (Integer productid : listProduct) {
                    pst.setInt(1, weeklyPlanId);
                    pst.setInt(2, productid);
                    pst.addBatch();
                }
                pst.executeBatch();

                hasUpdate = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            hasUpdate = false;
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                    pst.close();
                    rs.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return hasUpdate;
    }

    public ArrayList<Weekly_Plan> getAllWeekly_PlanByStatus(int status) {
        ArrayList<Weekly_Plan> listOfWeekly_Plan = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_WEEKLY_PLAN_BY_STATUS);
                pst.setInt(1, status);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        Weekly_Plan wp = new Weekly_Plan(rs.getInt("Weekly_Plan_ID"), rs.getDate("StartDate"), rs.getDate("EndDate"), rs.getInt("Status"));
                        listOfWeekly_Plan.add(wp);
                    }
                }
            }
        } catch (Exception e) {
        }
        return listOfWeekly_Plan;
    }
}
