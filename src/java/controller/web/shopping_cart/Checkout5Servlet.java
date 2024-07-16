/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web.shopping_cart;

import dao.OrderDAO;
import dto.Email;
import dto.Product;
import dto.User;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
public class Checkout5Servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String CHECKOUT_PAGE_5 = "view/jsp/home/checkout5.jsp";
    private final String CHECKOUT_PAGE_4 = "DispatchServlet?btAction=Checkout4";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String addressID = request.getParameter("addressID");
            String deliveryID = request.getParameter("deliveryID");
            String discount = request.getParameter("discount");
            String tax = request.getParameter("tax");
            String totalPrice = request.getParameter("totalPrice");
            String paymentMethod = request.getParameter("paymentMethod");
            OrderDAO odao = new OrderDAO();
            HttpSession session = request.getSession();
            HashMap<Product, Integer> cart = (HashMap<Product, Integer>) session.getAttribute("CART");
            User user = (User) session.getAttribute("account");

            int discountInsert = 0;
            if (discount != null && !discount.isEmpty()) {
                discountInsert = Integer.parseInt(discount);
            }

            if (cart != null && user != null) {
                int rs = odao.insertOrder(Integer.parseInt(addressID), Integer.parseInt(deliveryID), discountInsert, Integer.parseInt(tax), Integer.parseInt(totalPrice), paymentMethod, user.getUserID(), cart);
                if (rs >= 1) {
                    Email email = new Email();
                    email.sendEmail(email.subjectNewOrder(), "<p>Xin chào " + user.getFirstName() + user.getLastName() + ",</p>\n"
                            + "<p>Cảm ơn bạn đã đặt hàng tại FRESH MEAL.</p>\n"
                            + "<p>Cảm ơn bạn đã tin tưởng và sử dụng dịch vụ của chúng tôi.</p>\n"
                            + "<p>Trân trọng,</p>\n"
                            + "<p>FRESH MEAL</p>", user.getEmail());
                    session.removeAttribute("CART");
                    request.getRequestDispatcher(CHECKOUT_PAGE_5).forward(request, response);
                } else {

                    request.getRequestDispatcher(CHECKOUT_PAGE_4).forward(request, response);
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
