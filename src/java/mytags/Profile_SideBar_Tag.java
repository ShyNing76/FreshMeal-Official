/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytags;

import dao.UserDAO;
import dto.User;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author ADMIN
 */
public class Profile_SideBar_Tag extends SimpleTagSupport {

    private Integer userID;

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();

        try {
            UserDAO udao = new UserDAO();
            User user = udao.getUserByID(userID);
            String userImage = user.getImage();
            if (userImage == null) {
                userImage = "view/Assets/Images/user/default.jpg";
            }
            String sideBar = "<div class=\"side-menu\">\n"
                    + "                        <div class=\"side-img-user\">\n"
                    + "                            <a href=\"DispatchServlet?btAction=Profile\">\n"
                    + "                                <img src=" + userImage + " alt=\"user-img\"/>\n"
                    + "                                <span class=\"user-name\">" + user.getUserName() + "</span>\n"
                    + "                            </a>\n"
                    + "                        </div>\n"
                    + "                        <div class=\"side-user\">\n"
                    + "                            <div class=\"profile-item-header\">\n"
                    + "                                <a href=\"DispatchServlet?btAction=Profile\">\n"
                    + "                                    <i class=\"fa-regular fa-user\"></i>\n"
                    + "                                    <span>My Account</span>\n"
                    + "                                </a>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"profile-item\">\n"
                    + "                                <div>\n"
                    + "                                    <a href=\"DispatchServlet?btAction=Profile\">\n"
                    + "                                        <span>Profile</span>\n"
                    + "                                    </a>\n"
                    + "                                    <a href=\"DispatchServlet?btAction=MyAddress\">\n"
                    + "                                        <span>Address</span>\n"
                    + "                                    </a>\n"
                    + "                                    <a href=\"DispatchServlet?btAction=PasswordChange\">\n"
                    + "                                        <span>Change Password</span>\n"
                    + "                                    </a>\n"
                    + "                                </div>\n"
                    + "                            </div>\n"
                    + "                        </div>\n"
                    + "                        <div class=\"side-user\">\n"
                    + "                            <a href=\"DispatchServlet?btAction=MyOrder\">\n"
                    + "                                <i class=\"fa-regular fa-clipboard\"></i>\n"
                    + "                                <span>My Order</span>\n"
                    + "                            </a>\n"
                    + "                        </div>\n"
                    + "                        <div class=\"side-user\">\n"
                    + "                            <a href=\"#\">\n"
                    + "                                <i class=\"fa-regular fa-comment\"></i>\n"
                    + "                                <span>Comment</span>\n"
                    + "                            </a>\n"
                    + "                        </div>\n"
                    + "                        <div class=\"side-user\">\n"
                    + "                            <a href=\"#\">\n"
                    + "                                <i class=\"fa-regular fa-bell\"></i>\n"
                    + "                                <span>Notification</span>\n"
                    + "                            </a>\n"
                    + "                        </div>\n"
                    + "                    </div>";
            out.print(sideBar);
        } catch (Exception e) {
        }
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
}
