/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web.profile.my_address;

import dao.AddressDAO;
import dto.Address;
import dto.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
public class UpdateMyAddress extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String PROFILE_MY_ADDRESS_PAGE = "DispatchServlet?btAction=MyAddress";
    private static final Logger LOGGER = Logger.getLogger(UpdateMyAddress.class.getName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = PROFILE_MY_ADDRESS_PAGE;
        try {
            String addressID = request.getParameter("addressID");
            String addressDetail = request.getParameter("txtdetailAddress");
            String district = request.getParameter("selectedDistrict");
            LOGGER.log(Level.INFO, "Received parameters - addressID: {0}, addressDetail: {1}, district: {2}", new Object[]{addressID, addressDetail, district});

            AddressDAO adao = new AddressDAO();
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("account");

            if (user != null) {
                LOGGER.log(Level.INFO, "User {0} is updating address", user.getUserName());

                int rs = adao.updateAddress(addressDetail, district, Integer.parseInt(addressID));

                if (rs >= 1) {
                    LOGGER.log(Level.INFO, "Address updated successfully for user {0}", user.getUserName());
                    response.sendRedirect(url);
                } else {
                    LOGGER.log(Level.WARNING, "Failed to update address for user {0}", user.getUserName());
                    // Handle the failure case (e.g., set an error message and forward to an error page)
                }
            } else {
                LOGGER.log(Level.WARNING, "User session is null");
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Exception occurred while updating address", e);
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
