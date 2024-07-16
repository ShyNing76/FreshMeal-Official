/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web.shopping_cart;

import dto.Product;
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
public class EditCartServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String CART_PAGE = "DispatchServlet?btAction=CartPage";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = CART_PAGE;
        try {
            String removeProductID = request.getParameter("txtRemoveProductId");
            String btAction = request.getParameter("btAction");
            HttpSession session = request.getSession();
            HashMap<Product, Integer> cart = (HashMap<Product, Integer>) session.getAttribute("CART");
            if (cart != null) {
                Product product = null;
                for (Product p : cart.keySet()) {
                    if (p.getProductID() == Integer.parseInt(removeProductID)) {
                        product = p;
                        break;
                    }
                }
                if (btAction.equalsIgnoreCase("DeleteItemOfCart")) {
                    //loại bỏ product khỏi giỏ hàng
                    if (product != null) {
                        cart.remove(product);
                        //save cart vao session
                        session.setAttribute("CART", cart);
                    }
                } else {
                    // update quantity cho product
                    String newQuantity = request.getParameter("txtQuantity");
                    cart.put(product, Integer.parseInt(newQuantity.trim()));
                    //save cart vao session
                    session.setAttribute("CART", cart);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
