/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin.management.product;

import dao.CategoryDAO;
import dao.IngredientDAO;
import dao.ProductDAO;
import dto.Category;
import dto.Ingredient;
import dto.Product;
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
public class ManageProductServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String ADMIN_MANAGE_PRODUCT_PAGE = "view/jsp/admin/menu-admin.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ADMIN_MANAGE_PRODUCT_PAGE;

        try {
            ProductDAO pd = new ProductDAO();
            CategoryDAO cd = new CategoryDAO();
            IngredientDAO id = new IngredientDAO();

            String statusParam = request.getParameter("status");
            String searchParam = request.getParameter("search");

            Integer status = null;
            if (statusParam != null && !statusParam.isEmpty()) {
                try {
                    status = Integer.parseInt(statusParam);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    // Handle invalid status input
                }
            }

            ArrayList<Product> listOfAllProduct = pd.getProductByFilter(status, searchParam);
            Map<Integer, String> categoryMap = cd.getCategoryMap();
            ArrayList<Category> listOfCategory = cd.getCategory();
            Map<Integer, String> ingredientMap = id.getIngredientMap();
            ArrayList<Ingredient> listIngredient = id.getIngredient();

            if (listOfAllProduct != null) {
                request.setAttribute("listOfProduct", listOfAllProduct);
                request.setAttribute("CategoryMap", categoryMap);
                request.setAttribute("ListOfCategory", listOfCategory);
                request.setAttribute("IngredientMap", ingredientMap);
                request.setAttribute("ListIngredient", listIngredient);
            } else {
                request.setAttribute("Admin_Manage_Product_Page_Error", "Cannot find any products!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("Admin_Manage_Product_Page_Error", "Error processing request: " + e.getMessage());
        } finally {
            // Check if response is committed before forwarding
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
