/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web.profile.my_order;

import dao.OrderDAO;
import dao.ProductDAO;
import dto.Order;
import dto.Product;
import dto.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
public class MyOrderServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");

        try {
            OrderDAO odao = new OrderDAO();
            ProductDAO pdao = new ProductDAO();
            ArrayList<Order> listOfOrderOfUser = odao.getAllOrderByUserName(user.getUserName());
            ArrayList<Product> listOfProduct = pdao.getProduct();

            Collections.sort(listOfOrderOfUser, new Comparator<Order>() {
                @Override
                public int compare(Order o1, Order o2) {
                    int dateComparison = o2.getOrderDate().compareTo(o1.getOrderDate()); // Sắp xếp giảm dần theo orderDate
                    if (dateComparison == 0) {
                        return Integer.compare(o2.getOrderID(), o1.getOrderID()); // Nếu orderDate bằng nhau, sắp xếp giảm dần theo orderId
                    }
                    return dateComparison;
                }
            });

            if (listOfOrderOfUser != null) {
                request.setAttribute("ListOfOrderOfUser", listOfOrderOfUser);
            } else {
                String errorMessage = "Can't Find Any Order Of User Name: " + user.getUserName();
                request.setAttribute("Web_Order_Profile_Page_Error", errorMessage);
            }

            if (listOfProduct != null) {
                request.setAttribute("ListOfProductOfOrder", listOfProduct);
            } else {
                String errorMessage = "Can't Find Any Product In Orders Of User Name: " + user.getUserName();
                request.setAttribute("Web_Order_Profile_Page_Error", errorMessage);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                request.getRequestDispatcher(PROFILE_MY_ORDER_PAGE).forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
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
