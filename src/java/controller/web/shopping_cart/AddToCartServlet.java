/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web.shopping_cart;

import dao.ProductDAO;
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
public class AddToCartServlet extends HttpServlet {

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
            String productID = request.getParameter("productID");
            String txtquantity = request.getParameter("txtQuantity");
            String selectedMealType = request.getParameter("selectedMealType");
            if (selectedMealType == null || selectedMealType.isEmpty()) {
                request.setAttribute("ERROR", "Choose the type please!");
                request.getRequestDispatcher("DispatchServlet?btAction=MenuInfo&productId=" + productID).forward(request, response);
            } else {
                HttpSession session = request.getSession();
                int quantity = Integer.parseInt(txtquantity.trim());
                ProductDAO pdao = new ProductDAO();
                Product product = pdao.getProductByID(Integer.parseInt(productID.trim()));
                if (product != null) {
                    HashMap<Product, Integer> cart = (HashMap<Product, Integer>) session.getAttribute("CART");
                    if (cart == null) {
                        cart = new HashMap<>();
                        cart.put(product, quantity);
                    } else {
                        boolean found = false;
                        for (Product p : cart.keySet()) {
                            if (p.getProductID() == Integer.parseInt(productID)) {
                                //sửa quantity
                                found = true;
                                int oldQuantity = cart.get(p);
                                quantity = quantity + oldQuantity;
                                p.setMealType(selectedMealType);
                                cart.put(p, quantity);
                            }
                        }
                        if (!found) {
                            cart.put(product, quantity);
                        }
                    }
                    product.setMealType(selectedMealType);
                    session.setAttribute("CART", cart);
                    request.getRequestDispatcher(url).forward(request, response);
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
