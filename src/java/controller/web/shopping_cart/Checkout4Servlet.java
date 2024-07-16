/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web.shopping_cart;

import dao.AddressDAO;
import dao.DeliveryDAO;
import dto.Address;
import dto.Delivery;
import dto.Product;
import dto.User;
import java.io.IOException;
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
public class Checkout4Servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String CHECKOUT_PAGE_4 = "view/jsp/home/checkout4.jsp";
    private final String CHECKOUT_PAGE_3 = "DispatchServlet?btAction=Checkout3";
    private final String CHECKOUT_PAGE_2 = "DispatchServlet?btAction=Checkout2";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        try {
            String subTotalPrice = request.getParameter("SUBTOTALPRICE");
            String discountPrice = request.getParameter("DISCOUNT");
            String tax = request.getParameter("TAX");
            String totalPrice = request.getParameter("TOTALPRICE");
            String selectedAddress = request.getParameter("selectedAddress");
            String selectedShippingMethod = request.getParameter("selectedShippingMethod");
            String selectedPaymentMethod = request.getParameter("selectedPaymentMethod");
            AddressDAO adao = new AddressDAO();
            DeliveryDAO ddao = new DeliveryDAO();
            Delivery delivery = ddao.getAllDeliveryActiveByID(Integer.parseInt(selectedShippingMethod));

            if (selectedShippingMethod != null) {
                request.setAttribute("SELECTEDSHIPPINGMETHOD", selectedShippingMethod);
            } else {
                request.setAttribute("ERROR", "Choose The Shipping Method!");
                request.getRequestDispatcher(CHECKOUT_PAGE_2).forward(request, response);
            }

            if (selectedPaymentMethod != null) {
                request.setAttribute("SELECTEDPAYMENTMETHOD", selectedPaymentMethod);
            } else {
                request.setAttribute("ERROR", "Choose The Shipping Method!");
                request.getRequestDispatcher(CHECKOUT_PAGE_3).forward(request, response);
            }
            Address address = adao.getAddressByID(Integer.parseInt(selectedAddress));
            if (address != null) {
                request.setAttribute("ADDRESS", address);
            }

            if (delivery != null) {
                request.setAttribute("DELIVERY", delivery);
            }
            int totalPriceCheckOut = Integer.parseInt(totalPrice) + delivery.getDeliveryPrice();

            request.setAttribute("SUBTOTALPRICE", subTotalPrice);
            if (!discountPrice.isEmpty()) {
                request.setAttribute("DISCOUNT", discountPrice);
            }
            request.setAttribute("TAX", tax);
            request.setAttribute("DELIVERYPRICE", delivery.getDeliveryPrice());
            request.setAttribute("TOTALPRICE", totalPriceCheckOut);
            request.getRequestDispatcher(CHECKOUT_PAGE_4).forward(request, response);
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
