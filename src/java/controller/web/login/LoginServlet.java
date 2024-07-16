/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web.login;

import dao.UserDAO;
import dto.Email;
import dto.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.EncodePassword;

/**
 *
 * @author ADMIN
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String ADMIN_PAGE = "DispatchServlet?btAction=welcomeAdmin";
    private final String WELCOME = "DispatchServlet";
    private final String LOGIN_PAGE = "view/jsp/home/login.jsp";
    private final String FORM_PAGE = "DispatchServlet?btAction=Form";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            UserDAO udao = new UserDAO();
            String userName_email = request.getParameter("txtUserName_email");
            String password = request.getParameter("txtPassword");
            password = EncodePassword.toSHA1(password);
            User user = udao.Login(userName_email, password);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("account", user);
                if (user.getRoleID() == 2) {
                    response.sendRedirect(ADMIN_PAGE);
                } else {
                    if (user.getPhone() != null && !user.getPhone().isEmpty()) {
                        response.sendRedirect(WELCOME);
                    } else {
                        response.sendRedirect(FORM_PAGE);
                    }
                }
            } else {
                String error = "Invalid username or password!";
                request.setAttribute("msgerror", error);
                request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
            }

        } catch (Exception e) {
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
