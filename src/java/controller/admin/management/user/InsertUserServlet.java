/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin.management.user;

import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class InsertUserServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            request.setCharacterEncoding("UTF-8");

            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String emailUser = request.getParameter("emailUser");
            String userName = request.getParameter("userName");
            String passwordUser = request.getParameter("passwordUser");
            String dateOfBirth = request.getParameter("dateOfBirth");
            String genderUser = request.getParameter("genderUser");
            String phone = request.getParameter("Phone");
            String role = request.getParameter("roleUser");
            String imageName = request.getParameter("userImage");
            String addressUser = request.getParameter("addressUser");
            String districtUser = request.getParameter("selectedDistrict");

            // Path for the image
            String image = "view/Assets/Images/user/" + imageName;

            SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = null;
            Date sqlDate = null;

            if (dateOfBirth != null && !dateOfBirth.trim().isEmpty()) {
                try {
                    utilDate = sdfInput.parse(dateOfBirth);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            if (utilDate != null) {
                sqlDate = new Date(utilDate.getTime());
            }

            int roleInt;
            try {
                roleInt = Integer.parseInt(role);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                request.setAttribute("error", "Invalid role format.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }

            UserDAO userDAO = new UserDAO();
            try {
                boolean isSuccess = userDAO.insertUserWithAddress(firstName, lastName, userName, sqlDate, genderUser, emailUser, phone, passwordUser, image, roleInt, addressUser, districtUser);
                if (isSuccess) {
                    request.getRequestDispatcher("ManageUserServlet").forward(request, response);
                } else {
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
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
