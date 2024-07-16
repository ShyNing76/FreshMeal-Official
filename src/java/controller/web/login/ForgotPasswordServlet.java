/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web.login;

import dao.UserDAO;
import dto.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.EncodePassword;

/**
 *
 * @author ADMIN
 */
public class ForgotPasswordServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String LOGIN_PAGE = "DispatchServlet?btAction=loginPage";
    private final String FORGOT_PASSWORD_PAGE = "view/jsp/home/forgot_password.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String inputEmail = request.getParameter("txtEmail");
            UserDAO udao = new UserDAO();
            if (inputEmail != null) {
                User user = udao.getUserByEmail(inputEmail);
                if (user != null) {
                    request.setAttribute("ANNOUNCEMENT", "Fill In The Information!");
                    request.setAttribute("user", user);
                    request.getRequestDispatcher(FORGOT_PASSWORD_PAGE).forward(request, response);

                } else {
                    request.setAttribute("EMAILERROR", "NOT EXIST - Invalid Email!");
                    request.getRequestDispatcher(FORGOT_PASSWORD_PAGE).forward(request, response);

                }
            } else {
                String newPassword = request.getParameter("txtNewPassword");
                String confirmNewPassword = request.getParameter("txtConfirmNewPassword");
                String email = request.getParameter("txtUserEmail");
                User emailUser = udao.getUserByEmail(email);
                if (newPassword != null && confirmNewPassword != null) {
                    if (!confirmNewPassword.equals(newPassword)) {
                        // quay lại trang điền new password
                        if (emailUser != null) {
                            request.setAttribute("user", emailUser);
                        }
                        request.setAttribute("ANNOUNCEMENT", "Fill In The Information!");
                        request.setAttribute("msgConfirmPasswordError", "Confirm New Password Not Match With New Password!");
                        request.getRequestDispatcher(FORGOT_PASSWORD_PAGE).forward(request, response);
                    } else {
                        newPassword = EncodePassword.toSHA1(newPassword);
                        int rs = udao.updatePasswordUserByEmail(email, newPassword);
                        if (rs >= 1) {
                            response.sendRedirect(LOGIN_PAGE);
                        } else {

                        }
                    }
                }
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
