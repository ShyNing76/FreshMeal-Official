/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.User;
import dto.User_Address;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import utils.DBContext;

/**
 *
 * @author ADMIN
 */
public class UserDAO implements Serializable {

    private final String LOGIN = "SELECT [UserID],[FirstName],[LastName],[UserName],[DateOfBirth],[Gender],[Email],[Phone],[Password],[Image],[RoleID],[Status]\n"
            + "FROM [dbo].[tb_User]\n"
            + "WHERE ([UserName] = ? OR [Email] = ?) AND [Password] = ?";
    private final String GET_USER = "SELECT [UserID],[FirstName],[LastName],[UserName],[DateOfBirth],[Gender],[Email],[Phone],[Password],[Image],[RoleID],[Status]\n"
            + "FROM [dbo].[tb_User]";
    private final String GET_USER_BY_Name = "SELECT [UserID],[FirstName],[LastName],[UserName],[DateOfBirth],[Gender],[Email],[Phone],[Password],[Image],[RoleID],[Status]\n"
            + "FROM [dbo].[tb_User]\n"
            + "WHERE [UserName] = ?";
    private final String GET_USER_BY_ID = "SELECT [UserID],[FirstName],[LastName],[UserName],[DateOfBirth],[Gender],[Email],[Phone],[Password],[Image],[RoleID],[Status]\n"
            + "FROM [dbo].[tb_User]\n"
            + "WHERE [UserID] = ?";
    private final String GET_USER_BY_EMAIL = "SELECT [UserID],[FirstName],[LastName],[UserName],[DateOfBirth],[Gender],[Email],[Phone],[Password],[Image],[RoleID],[Status]\n"
            + "FROM [dbo].[tb_User]\n"
            + "WHERE [Email] = ?";
    private final String CHECK_USER_NAME = "SELECT [UserID],[FirstName],[LastName],[UserName],[DateOfBirth],[Gender],[Email],[Phone],[Password],[Image],[RoleID],[Status]\n"
            + "FROM [dbo].[tb_User]\n"
            + "WHERE [UserName] = ?";
    private final String CHECK_USER_EMAIL = "SELECT [UserID],[FirstName],[LastName],[UserName],[DateOfBirth],[Gender],[Email],[Phone],[Password],[Image],[RoleID],[Status]\n"
            + "FROM [dbo].[tb_User]\n"
            + "WHERE [Email] = ?";
    private final String INSERT_USER = "INSERT INTO [dbo].[tb_User] ([UserName],[Email],[Password]) VALUES (?,?,?)";
    private final String INSERT_USER_INFOMATION = "INSERT tb_User(FirstName, LastName, UserName, DateOfBirth, Gender, Email, Phone, Password, Image, RoleID, Status) values (?,?,?,?,?,?,?,?,?,?,1);";
    private final String UPDATE_STATUS_USER = "UPDATE [dbo].[tb_User]\n"
            + "SET [Status] = ? "
            + "WHERE [UserID] = ?";
    private final String UPDATE_INFORMATION_USER = "UPDATE [dbo].[tb_User]\n"
            + "SET [FirstName] = ?, [LastName] = ?, [Phone] = ?, [Gender] = ?, [DateOfBirth] = ?\n"
            + "WHERE [UserID] = ? And [Status] = '1'";
    private final String UPDATE_PASSWORD_USER_BY_USER_ID = "UPDATE [dbo].[tb_User]\n"
            + "SET [Password] = ?\n"
            + "WHERE [UserID] = ? And [Status] = '1'";
    private final String UPDATE_PASSWORD_USER_BY_EMAIL = "UPDATE [dbo].[tb_User]\n"
            + "SET [Password] = ?\n"
            + "WHERE [Email] = ? And [Status] = '1'";
    private final String UPDATE_IMAGE_USER_BY_USER_ID = "UPDATE [dbo].[tb_User]\n"
            + "SET [Image] = ?\n"
            + "WHERE [UserID] = ? And [Status] = '1'";
    private final String UPDATE_ROLE_USER = "UPDATE [dbo].[tb_User]\n"
            + "SET [RoleID] = ?\n"
            + "WHERE [UserID] = ?";
//    private final String GET_COMMENT_BY_USER = "SELECT [CommentID], [ProductID], [UserID], [text], [Status]\n"
//            + "FROM [dbo].[tb_Comment]\n"
//            + "WHERE [UserID] = ?";
    private final String GET_ADDRESS_BY_USER = "SELECT [User_Address_ID], [AddressID], [UserID]\n"
            + "FROM [dbo].[tb_User_Address]\n"
            + "WHERE [UserID] = ?";
    private final String GET_ROLE_NAME = "SELECT RoleID, Name\n"
            + "FROM tb_Role\n";

//    public static void main(String[] args) {
//        // Assuming you have a class named DBContext with a static method makeConnection()
//        UserDAO d = new UserDAO();
//        ArrayList<User> users = d.getUser();
//        for (User user : users) {
//            System.out.println("User ID: " + user.getUserID());
//            System.out.println("First Name: " + user.getFirstName());
//            System.out.println("Last Name: " + user.getLastName());
//            System.out.println("User Name: " + user.getUserName());
//            System.out.println("Date of Birth: " + user.getDateOfBirth());
//            System.out.println("Gender: " + user.getGender());
//            System.out.println("Email: " + user.getEmail());
//            System.out.println("Phone: " + user.getPhone());
//            System.out.println("Password: " + user.getPassword());
//            System.out.println("Image: " + user.getImage());
//            System.out.println("Role ID: " + user.getRoleID());
//            System.out.println("Status: " + user.getStatus());
//            System.out.println("Addresses: ");
//            for (User_Address address : user.getListOfAddress()) {
//                System.out.println("    User Address ID: " + address.getUser_Address_ID());
//                System.out.println("    Address ID: " + address.getAddressID());
//                System.out.println("    User ID: " + address.getUserID());
//            }
//            System.out.println();
//        }
//    }
    public ArrayList<User> getUser() {
        ArrayList<User> listOfUser = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                Statement st1 = cn.createStatement();
                ResultSet rs1 = st1.executeQuery(GET_USER);
                if (rs1 != null) {
                    while (rs1.next()) {
                        User u = new User();
                        u.setUserID(rs1.getInt("UserID"));
                        u.setFirstName(rs1.getString("FirstName"));
                        u.setLastName(rs1.getString("LastName"));
                        u.setUserName(rs1.getString("UserName"));
                        u.setDateOfBirth(rs1.getDate("DateOfBirth"));
                        u.setGender(rs1.getString("Gender"));
                        u.setEmail(rs1.getString("Email"));
                        u.setPhone(rs1.getString("Phone"));
                        u.setPassword(rs1.getString("Password"));
                        u.setImage(rs1.getString("Image"));
                        u.setRoleID(rs1.getInt("RoleID"));
                        u.setStatus(rs1.getInt("Status"));
                        PreparedStatement pst = cn.prepareStatement(GET_ADDRESS_BY_USER);
                        pst.setInt(1, u.getUserID());
                        ResultSet rs2 = pst.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                User_Address ud = new User_Address(rs2.getInt("User_Address_ID"), rs2.getInt("AddressID"), u.getUserID());
                                u.addAddress(ud);
                            }
                        }
                        listOfUser.add(u);
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
        return listOfUser;
    }

    public User getUserByID(int userID) {
        User u = null;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_USER_BY_ID);
                pst.setInt(1, userID);
                ResultSet rs1 = pst.executeQuery();
                if (rs1 != null) {
                    while (rs1.next()) {
                        u = new User();
                        u.setUserID(rs1.getInt("UserID"));
                        u.setFirstName(rs1.getString("FirstName"));
                        u.setLastName(rs1.getString("LastName"));
                        u.setUserName(rs1.getString("UserName"));
                        u.setDateOfBirth(rs1.getDate("DateOfBirth"));
                        u.setGender(rs1.getString("Gender"));
                        u.setEmail(rs1.getString("Email"));
                        u.setPhone(rs1.getString("Phone"));
                        u.setPassword(rs1.getString("Password"));
                        u.setImage(rs1.getString("Image"));
                        u.setRoleID(rs1.getInt("RoleID"));
                        u.setStatus(rs1.getInt("Status"));
                        PreparedStatement pst1 = cn.prepareStatement(GET_ADDRESS_BY_USER);
                        pst1.setInt(1, u.getUserID());
                        ResultSet rs2 = pst1.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                User_Address ud = new User_Address(rs2.getInt("User_Address_ID"), rs2.getInt("AddressID"), u.getUserID());
                                u.addAddress(ud);
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
        return u;
    }

    public User getUserByName(String userName) {
        User u = null;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(GET_USER_BY_Name);
                pst.setString(1, userName);
                ResultSet rs1 = pst.executeQuery();
                if (rs1 != null) {
                    while (rs1.next()) {
                        u = new User();
                        u.setUserID(rs1.getInt("UserID"));
                        u.setFirstName(rs1.getString("FirstName"));
                        u.setLastName(rs1.getString("LastName"));
                        u.setUserName(rs1.getString("UserName"));
                        u.setDateOfBirth(rs1.getDate("DateOfBirth"));
                        u.setGender(rs1.getString("Gender"));
                        u.setEmail(rs1.getString("Email"));
                        u.setPhone(rs1.getString("Phone"));
                        u.setPassword(rs1.getString("Password"));
                        u.setImage(rs1.getString("Image"));
                        u.setRoleID(rs1.getInt("RoleID"));
                        u.setStatus(rs1.getInt("Status"));
                        PreparedStatement pst1 = cn.prepareStatement(GET_ADDRESS_BY_USER);
                        pst1.setInt(1, u.getUserID());
                        ResultSet rs2 = pst1.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                User_Address ud = new User_Address(rs2.getInt("User_Address_ID"), rs2.getInt("AddressID"), u.getUserID());
                                u.addAddress(ud);
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
        return u;
    }

    public User getUserByEmail(String email) {
        User u = null;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst1 = cn.prepareStatement(GET_USER_BY_EMAIL);
                pst1.setString(1, email);
                ResultSet rs1 = pst1.executeQuery();
                if (rs1 != null) {
                    while (rs1.next()) {
                        u = new User();
                        u.setUserID(rs1.getInt("UserID"));
                        u.setFirstName(rs1.getString("FirstName"));
                        u.setLastName(rs1.getString("LastName"));
                        u.setUserName(rs1.getString("UserName"));
                        u.setDateOfBirth(rs1.getDate("DateOfBirth"));
                        u.setGender(rs1.getString("Gender"));
                        u.setEmail(rs1.getString("Email"));
                        u.setPhone(rs1.getString("Phone"));
                        u.setPassword(rs1.getString("Password"));
                        u.setImage(rs1.getString("Image"));
                        u.setRoleID(rs1.getInt("RoleID"));
                        u.setStatus(rs1.getInt("Status"));
                        PreparedStatement pst = cn.prepareStatement(GET_ADDRESS_BY_USER);
                        pst.setInt(1, u.getUserID());
                        ResultSet rs2 = pst.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                User_Address ud = new User_Address(rs2.getInt("User_Address_ID"), rs2.getInt("AddressID"), u.getUserID());
                                u.addAddress(ud);
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
        return u;
    }

    public Map<Integer, String> getRoleMap() {
        Map<Integer, String> roleMap = new HashMap<>();
        Connection cn = null;

        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                Statement st1 = cn.createStatement();
                ResultSet rs1 = st1.executeQuery(GET_ROLE_NAME);
                if (rs1 != null) {
                    while (rs1.next()) {
                        int roleID = rs1.getInt("RoleID");
                        String roleName = rs1.getString("Name");
                        roleMap.put(roleID, roleName);
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

        return roleMap;
    }

    public int insertUser(String UserName, String Email, String Password) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(INSERT_USER);
                pst.setString(1, UserName);
                pst.setString(2, Email);
                pst.setString(3, Password);
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

    public int insertUserInfomation(String firstName, String lastName, String userName, Date dateOfBirth, String gender, String email, String phone, String password, String image, int roleid) {
        int userid = 0;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(INSERT_USER_INFOMATION, PreparedStatement.RETURN_GENERATED_KEYS);
                pst.setString(1, firstName);
                pst.setString(2, lastName);
                pst.setString(3, userName);
                pst.setDate(4, dateOfBirth);
                pst.setString(5, gender);
                pst.setString(6, email);
                pst.setString(7, phone);
                pst.setString(8, password);
                pst.setString(9, image);
                pst.setInt(10, roleid);
                int rs = pst.executeUpdate();

                if (rs == 0) {
                    throw new SQLException("Inserting user failed, no rows affected.");
                } else {
                    try (ResultSet generateKeys = pst.getGeneratedKeys()) {
                        if (generateKeys.next()) {
                            userid = generateKeys.getInt(1);
                        } else {
                            throw new SQLException("Inserting user failed, no ID obtained.");
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
        return userid;
    }

    public int updateStatusUser(int status, int userID) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(UPDATE_STATUS_USER);
                pst.setInt(1, status);
                pst.setInt(2, userID);
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

    public int updateInforUser(int userID, String FirstName, String LastName, Date DateOfBirth, String Gender, String Phone) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(UPDATE_INFORMATION_USER);
                pst.setString(1, FirstName);
                pst.setString(2, LastName);
                pst.setString(3, Phone);
                pst.setString(4, Gender);
                pst.setDate(5, DateOfBirth);
                pst.setInt(6, userID);
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

    public int updatePasswordUserByUserID(int userID, String Password) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(UPDATE_PASSWORD_USER_BY_USER_ID);
                pst.setString(1, Password);
                pst.setInt(2, userID);
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

    public int updatePasswordUserByEmail(String email, String Password) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(UPDATE_PASSWORD_USER_BY_EMAIL);
                pst.setString(1, Password);
                pst.setString(2, email);
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

    public int updateImageUserByUserID(int userID, String image) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(UPDATE_IMAGE_USER_BY_USER_ID);
                pst.setString(1, image);
                pst.setInt(2, userID);
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

    public int updateRoleUser(int userID, int roleID) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst = cn.prepareStatement(UPDATE_ROLE_USER);
                pst.setInt(1, roleID);
                pst.setInt(2, userID);
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

    public User checkUserName(String userName) {
        User u = null;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst1 = cn.prepareStatement(CHECK_USER_NAME);
                pst1.setString(1, userName);
                ResultSet rs = pst1.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        u = new User();
                        u.setUserID(rs.getInt("UserID"));
                        u.setFirstName(rs.getString("FirstName"));
                        u.setLastName(rs.getString("LastName"));
                        u.setUserName(rs.getString("UserName"));
                        u.setDateOfBirth(rs.getDate("DateOfBirth"));
                        u.setGender(rs.getString("Gender"));
                        u.setEmail(rs.getString("Email"));
                        u.setPhone(rs.getString("Phone"));
                        u.setPassword(rs.getString("Password"));
                        u.setImage(rs.getString("Image"));
                        u.setRoleID(rs.getInt("RoleID"));
                        u.setStatus(rs.getInt("Status"));
                        PreparedStatement pst = cn.prepareStatement(GET_ADDRESS_BY_USER);
                        pst.setInt(1, u.getUserID());
                        ResultSet rs2 = pst.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                User_Address ud = new User_Address(rs2.getInt("User_Address_ID"), rs2.getInt("AddressID"), u.getUserID());
                                u.addAddress(ud);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        return u;
    }

    public User checkUserEmail(String email) {
        User u = null;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst1 = cn.prepareStatement(CHECK_USER_EMAIL);
                pst1.setString(1, email);
                ResultSet rs = pst1.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        u = new User();
                        u.setUserID(rs.getInt("UserID"));
                        u.setFirstName(rs.getString("FirstName"));
                        u.setLastName(rs.getString("LastName"));
                        u.setUserName(rs.getString("UserName"));
                        u.setDateOfBirth(rs.getDate("DateOfBirth"));
                        u.setGender(rs.getString("Gender"));
                        u.setEmail(rs.getString("Email"));
                        u.setPhone(rs.getString("Phone"));
                        u.setPassword(rs.getString("Password"));
                        u.setImage(rs.getString("Image"));
                        u.setRoleID(rs.getInt("RoleID"));
                        u.setStatus(rs.getInt("Status"));
                        PreparedStatement pst = cn.prepareStatement(GET_ADDRESS_BY_USER);
                        pst.setInt(1, u.getUserID());
                        ResultSet rs2 = pst.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                User_Address ud = new User_Address(rs2.getInt("User_Address_ID"), rs2.getInt("AddressID"), u.getUserID());
                                u.addAddress(ud);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        return u;
    }

    public User Login(String email_userName, String password) {
        User u = null;
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                PreparedStatement pst1 = cn.prepareStatement(LOGIN);
                pst1.setString(1, email_userName);
                pst1.setString(2, email_userName);
                pst1.setString(3, password);
                ResultSet rs = pst1.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        u = new User();
                        u.setUserID(rs.getInt("UserID"));
                        u.setFirstName(rs.getString("FirstName"));
                        u.setLastName(rs.getString("LastName"));
                        u.setUserName(rs.getString("UserName"));
                        u.setDateOfBirth(rs.getDate("DateOfBirth"));
                        u.setGender(rs.getString("Gender"));
                        u.setEmail(rs.getString("Email"));
                        u.setPhone(rs.getString("Phone"));
                        u.setPassword(rs.getString("Password"));
                        u.setImage(rs.getString("Image"));
                        u.setRoleID(rs.getInt("RoleID"));
                        u.setStatus(rs.getInt("Status"));
                        PreparedStatement pst = cn.prepareStatement(GET_ADDRESS_BY_USER);
                        pst.setInt(1, u.getUserID());
                        ResultSet rs2 = pst.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                User_Address ud = new User_Address(rs2.getInt("User_Address_ID"), rs2.getInt("AddressID"), u.getUserID());
                                u.addAddress(ud);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        return u;
    }

    public Map<Integer, User> getUserMap() {
        Map<Integer, User> userMap = new HashMap<>();
        Connection cn = null;

        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                String sql = "SELECT UserID, FirstName, LastName, Phone, Email FROM tb_User";
                PreparedStatement ps = cn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int userID = rs.getInt("UserID");
                    String firstName = rs.getString("FirstName");
                    String lastName = rs.getString("LastName");
                    String phone = rs.getString("Phone");
                    String email = rs.getString("Email");
                    User user = new User(userID, firstName, lastName, email, phone);
                    userMap.put(userID, user);
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

        return userMap;
    }

    public boolean insertUserWithAddress(String firstName, String lastName, String userName, Date dateOfBirth, String genderUser, String emailUser, String phone, String passwordUser, String image, int role, String addressUser, String districtUser) throws SQLException, Exception {
        Connection cn = null;

        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                cn.setAutoCommit(false);

                int userId = insertUserInfomation(firstName, lastName, userName, dateOfBirth, genderUser, emailUser, phone, passwordUser, image, role);
                if (userId == 0) {
                    throw new Exception("Failed to insert user information.");
                }

                AddressDAO addressDao = new AddressDAO();
                int addressId = addressDao.insertAddress(addressUser, districtUser, "Thành Phố Hồ Chí Minh");
                if (addressId == 0) {
                    throw new Exception("Failed to insert address.");
                }

                int rs = addressDao.insertAddressByUser(userId, addressId);
                if (rs < 1) {
                    throw new Exception("Failed to link user with address.");
                }

                cn.commit();
                return true;
            }
        } catch (Exception e) {
            if (cn != null) {
                cn.rollback();
            }
            throw e;
        } finally {
            if (cn != null) {
                cn.setAutoCommit(true);
                cn.close();
            }
        }

        return false;
    }

    public ArrayList<User> getUserAdminPanel(Integer status, Integer role, String searchQuery) {
        ArrayList<User> listOfUser = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBContext.makeConnection();
            if (cn != null) {
                // Xây dựng truy vấn SQL dựa trên các tham số
                StringBuilder query = new StringBuilder("SELECT [UserID],[FirstName],[LastName],[UserName],[DateOfBirth],[Gender],[Email],[Phone],[Password],[Image],[RoleID],[Status]\n"
                        + "FROM [dbo].[tb_User]\n"
                        + "WHERE 1=1");
                ArrayList<Object> parameters = new ArrayList<>();

                if (status != null) {
                    query.append(" AND Status = ?");
                    parameters.add(status);
                }

                if (role != null) {
                    query.append(" AND RoleID = ?");
                    parameters.add(role);
                }

                if (searchQuery != null && !searchQuery.isEmpty()) {
                    query.append(" AND (FirstName LIKE ? OR LastName LIKE ? OR UserName LIKE ?)");
                    String searchPattern = "%" + searchQuery + "%";
                    parameters.add(searchPattern);
                    parameters.add(searchPattern);
                    parameters.add(searchPattern);
                }

                PreparedStatement pst1 = cn.prepareStatement(query.toString());
                for (int i = 0; i < parameters.size(); i++) {
                    pst1.setObject(i + 1, parameters.get(i));
                }

                ResultSet rs1 = pst1.executeQuery();
                if (rs1 != null) {
                    while (rs1.next()) {
                        User u = new User();
                        u.setUserID(rs1.getInt("UserID"));
                        u.setFirstName(rs1.getString("FirstName"));
                        u.setLastName(rs1.getString("LastName"));
                        u.setUserName(rs1.getString("UserName"));
                        u.setDateOfBirth(rs1.getDate("DateOfBirth"));
                        u.setGender(rs1.getString("Gender"));
                        u.setEmail(rs1.getString("Email"));
                        u.setPhone(rs1.getString("Phone"));
                        u.setPassword(rs1.getString("Password"));
                        u.setImage(rs1.getString("Image"));
                        u.setRoleID(rs1.getInt("RoleID"));
                        u.setStatus(rs1.getInt("Status"));

                        PreparedStatement pst2 = cn.prepareStatement(GET_ADDRESS_BY_USER);
                        pst2.setInt(1, u.getUserID());
                        ResultSet rs2 = pst2.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                User_Address ud = new User_Address(rs2.getInt("User_Address_ID"), rs2.getInt("AddressID"), u.getUserID());
                                u.addAddress(ud);
                            }
                        }
                        listOfUser.add(u);
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
        return listOfUser;
    }
}
