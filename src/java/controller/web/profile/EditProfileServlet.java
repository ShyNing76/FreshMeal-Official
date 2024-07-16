/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web.profile;

import dao.UserDAO;
import dto.User;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
public class EditProfileServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String currentPage = request.getParameter("txtCurrentPage");
            String userID = request.getParameter("txtUserId");
            String firstName = request.getParameter("txtFirstName");
            String lastName = request.getParameter("txtLastName");
            String dateOfBirth = request.getParameter("txtDate");
            String gender = request.getParameter("txtGender");
            String phone = request.getParameter("txtPhone");
            UserDAO udao = new UserDAO();
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("account");
            if (user != null) {
                int rs = udao.updateInforUser(Integer.parseInt(userID), firstName, lastName, Date.valueOf(dateOfBirth), gender, phone);
                if (rs >= 1) {
                    // refresh lại session user vì mới update
                    user = udao.Login(user.getEmail(), user.getPassword());
                    session.setAttribute("account", user);
                    request.setAttribute("STATUS", "Update Information Successfully!");
                } else {
                    request.setAttribute("Error", "Update Information Failed!");
                }
            }
            response.sendRedirect(currentPage);
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
