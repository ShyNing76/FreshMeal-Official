/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web.shopping_cart;

import dao.AddressDAO;
import dto.Address;
import dto.Product;
import dto.User;
import java.io.IOException;
import java.util.ArrayList;
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
public class Checkout1Servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String CHECKOUT_PAGE_1 = "view/jsp/home/checkout1.jsp";
    private final String CART_PAGE = "DispatchServlet?btAction=CartPage";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String subTotalPrice = request.getParameter("SUBTOTALPRICE");
            String discountPrice = request.getParameter("DISCOUNT");
            String tax = request.getParameter("TAX");
            String totalPrice = request.getParameter("TOTALPRICE");
            String selectedAddress = request.getParameter("selectedAddress");
            AddressDAO adao = new AddressDAO();
            HttpSession session = request.getSession();
            HashMap<Product, Integer> cart = (HashMap<Product, Integer>) session.getAttribute("CART");
            if (cart != null) {
                User user = (User) session.getAttribute("account");
                ArrayList<Address> listOfAddressOfUser = adao.getAddressByUser(user.getUserName());
                if (listOfAddressOfUser != null) {
                    if (selectedAddress != null) {
                        request.setAttribute("SELECTEDADDRESS", selectedAddress);
                    }
                    request.setAttribute("ListOfAddressOfUser", listOfAddressOfUser);
                } else {
                    request.setAttribute("Web_Address_Profile_Page_Error", "Cann't Find Any Address Of User Name: " + user.getUserName());
                }
                if (cart.isEmpty()) {
                    request.setAttribute("Error", "There are no products to checkout!");
                    request.getRequestDispatcher(CART_PAGE).forward(request, response);
                }
                request.setAttribute("SUBTOTALPRICE", subTotalPrice);
                request.setAttribute("DISCOUNT", discountPrice);
                request.setAttribute("TAX", tax);
                request.setAttribute("TOTALPRICE", totalPrice);
                request.getRequestDispatcher(CHECKOUT_PAGE_1).forward(request, response);
            } else {
                response.sendRedirect(CART_PAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
