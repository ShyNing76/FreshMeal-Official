/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin.management.user;

import dao.AddressDAO;
import dao.UserDAO;
import dto.Address;
import dto.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class ManageUserServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String ADMIN_MANAGE_USER_PAGE = "view/jsp/admin/customer-admin.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ADMIN_MANAGE_USER_PAGE;
        try {
            /* TODO output your page here. You may use following sample code. */
            String statusParam = request.getParameter("status");
            String roleParam = request.getParameter("role");
            String searchQuerry = request.getParameter("search");

            Integer status = (statusParam != null && !statusParam.isEmpty()) ? Integer.parseInt(statusParam) : null;
            Integer role = (roleParam != null && !roleParam.isEmpty()) ? Integer.parseInt(roleParam) : null;

            UserDAO udao = new UserDAO();
            AddressDAO adao = new AddressDAO();

            ArrayList<User> listOfUser = udao.getUserAdminPanel(status, role, searchQuerry);
            Map<Integer, String> roleMap = udao.getRoleMap();
            Map<Integer, Address> addressMap = adao.getAddressMap();

            if (listOfUser != null) {
                request.setAttribute("ListOfUser", listOfUser);
                request.setAttribute("RoleMap", roleMap);
                request.setAttribute("AddressMap", addressMap);
            } else {
                request.setAttribute("Admin_Manage_User_Page_Error", "Cann't Find Any Users!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (!response.isCommitted()) {
                request.getRequestDispatcher(url).forward(request, response);
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
