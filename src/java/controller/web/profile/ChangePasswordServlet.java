/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web.profile;

import dao.UserDAO;
import dto.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
public class ChangePasswordServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String PROFILE_PAGE = "view/jsp/home/profile.jsp";
    private final String PASSWORD_CHANGE_PAGE = "view/jsp/home/password_change.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String oldPassword = request.getParameter("txtOldPassword");
            String newPassword = request.getParameter("txtNewPassword");
            String confirmNewPassword = request.getParameter("txtConfirmNewPassword");
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("account");
            if (user != null && user.getPassword().equals(oldPassword)) {
                if (!newPassword.equals(confirmNewPassword)) {
                    request.setAttribute("msgConfirmPasswordError", "Confirm New Password Not Match With New Password!");
                    request.getRequestDispatcher(PASSWORD_CHANGE_PAGE).forward(request, response);
                } else {
                    UserDAO udao = new UserDAO();
                    int rs = udao.updatePasswordUserByUserID(user.getUserID(), newPassword);
                    if (rs >= 1) {
                        user = udao.Login(user.getEmail(), newPassword);
                        session.setAttribute("account", user);
                        request.getRequestDispatcher(PROFILE_PAGE).forward(request, response);
                    } else {
                        
                    }
                }
            } else {
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
