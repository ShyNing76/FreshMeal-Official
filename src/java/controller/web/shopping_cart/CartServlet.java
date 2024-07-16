/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web.shopping_cart;

import dto.Product;
import java.io.IOException;
import java.io.PrintWriter;
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
public class CartServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String CART_PAGE = "view/jsp/home/cart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try {
            String discount = (String) request.getAttribute("DISCOUNT");
            String promoCode = (String) request.getAttribute("PromoCode");
            HttpSession session = request.getSession();
            HashMap<Product, Integer> cart = (HashMap<Product, Integer>) session.getAttribute("CART");
            if (cart != null) {
                if (discount != null && promoCode != null) {
                    int subTotalPrice = 0;
                    int tax = 10000;
                    int totalPrice = 0;
                    for (Product p : cart.keySet()) {
                        subTotalPrice = subTotalPrice + p.getPrice() * cart.get(p);
                    }
                    int discountPrice = (subTotalPrice * Integer.parseInt(discount)) / 100;
                    totalPrice = subTotalPrice - discountPrice + tax;
                    if (totalPrice < 0) {
                        totalPrice = 0;
                    }
                    request.setAttribute("SUBTOTALPRICE", subTotalPrice);
                    request.setAttribute("DISCOUNT", discountPrice);
                    request.setAttribute("TAX", tax);
                    request.setAttribute("TOTALPRICE", totalPrice);
                    request.getRequestDispatcher(CART_PAGE).forward(request, response);
                } else {
                    int subTotalPrice = 0;
                    int tax = 10000;
                    int totalPrice = 0;
                    for (Product p : cart.keySet()) {
                        subTotalPrice = subTotalPrice + p.getPrice() * cart.get(p);
                    }
                    totalPrice = subTotalPrice + tax;
                    request.setAttribute("SUBTOTALPRICE", subTotalPrice);
                    request.setAttribute("TAX", tax);
                    request.setAttribute("TOTALPRICE", totalPrice);
                    request.getRequestDispatcher(CART_PAGE).forward(request, response);
                }
            } else {
                request.getRequestDispatcher(CART_PAGE).forward(request, response);
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
