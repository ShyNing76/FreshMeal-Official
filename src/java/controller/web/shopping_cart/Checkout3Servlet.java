/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web.shopping_cart;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class Checkout3Servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String CHECKOUT_PAGE_3 = "view/jsp/home/checkout3.jsp";
    private final String CHECKOUT_PAGE_2 = "DispatchServlet?btAction=Checkout2";
    private final String CHECKOUT_PAGE_1 = "DispatchServlet?btAction=Checkout1";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String subTotalPrice = request.getParameter("SUBTOTALPRICE");
            String discountPrice = request.getParameter("DISCOUNT");
            String tax = request.getParameter("TAX");
            String totalPrice = request.getParameter("TOTALPRICE");
            String selectedAddress = request.getParameter("selectedAddress");
            if (selectedAddress != null) {
                request.setAttribute("SELECTEDADDRESS", selectedAddress);
            } else {
                request.setAttribute("ERROR", "Choose The Address!");
                request.getRequestDispatcher(CHECKOUT_PAGE_1).forward(request, response);
            }

            String selectedShippingMethod = request.getParameter("selectedShippingMethod");
            if (selectedShippingMethod != null) {
                request.setAttribute("SELECTEDSHIPPINGMETHOD", selectedShippingMethod);
            } else {
                request.setAttribute("ERROR", "Choose The Shipping Method!");
                request.getRequestDispatcher(CHECKOUT_PAGE_2).forward(request, response);
            }

            request.setAttribute("SUBTOTALPRICE", subTotalPrice);
            request.setAttribute("DISCOUNT", discountPrice);
            request.setAttribute("TAX", tax);
            request.setAttribute("TOTALPRICE", totalPrice);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(CHECKOUT_PAGE_3).forward(request, response);
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
