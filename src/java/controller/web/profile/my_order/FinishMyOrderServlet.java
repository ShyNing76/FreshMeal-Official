/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web.profile.my_order;

import dao.OrderDAO;
import dao.Order_DetailDAO;
import dao.ProductDAO;
import dto.Order;
import dto.Product;
import dto.User;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
public class FinishMyOrderServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String PROFILE_MY_ORDER_PAGE = "view/jsp/home/my_order.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            OrderDAO odao = new OrderDAO();
            ProductDAO pdao = new ProductDAO();
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("account");
            ArrayList<Order> listOfOrderOfUser = odao.getFinishOrderByUserName(user.getUserName());
            ArrayList<Product> listOfProduct = pdao.getProduct();
            if (listOfOrderOfUser != null) {
                request.setAttribute("ListOfOrderOfUser", listOfOrderOfUser);
            } else {
                request.setAttribute("Web_Order_Profile_Page_Error", "Cann't Find Any Order Of User Name: " + user.getUserName());
            }
            if (listOfProduct != null) {
                request.setAttribute("ListOfProductOfOrder", listOfProduct);
            } else {
                request.setAttribute("Web_Order_Profile_Page_Error", "Cann't Find Any Order Of User Name: " + user.getUserName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(PROFILE_MY_ORDER_PAGE).forward(request, response);
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
