/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin.management.product;

import dao.ProductDAO;
import dto.BoxIngredient;
import dto.Product;
import dto.Recipe_Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class InsertProductServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            request.setCharacterEncoding("UTF-8");

            String productName = request.getParameter("productName");
            String[] productCategory = request.getParameterValues("productCategory[]");
            String productPrice = request.getParameter("productPrice");
            String productDescription = request.getParameter("productDescription");
            String productCookingTime = request.getParameter("productTime");
            String productImageName = request.getParameter("productImage");

            //xu ly path anh
            String productImage = "view/Assets/Images/food/" + productImageName;

            // Get recipe steps
            String[] steps = request.getParameterValues("step[]");
            String[] instructions = request.getParameterValues("instruction[]");

            // Get ingredients
            String[] ingredientIds = request.getParameterValues("ingredientId[]");
            String[] ingredientQuantities = request.getParameterValues("ingredientQuantity[]");
            String[] ingredientUnits = request.getParameterValues("ingredientUnit[]");

            // Convert category IDs to ArrayList<Integer>
            ArrayList<Integer> categoryList = new ArrayList<>();
            if (productCategory != null) {
                for (String categoryId : productCategory) {
                    try {
                        categoryList.add(Integer.parseInt(categoryId.trim()));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }

            // Create recipe list
            ArrayList<Recipe_Product> recipeList = new ArrayList<>();
            if (steps != null && instructions != null) {
                for (int i = 0; i < steps.length && i < instructions.length; i++) {
                    try {
                        int stepNumber = Integer.parseInt(steps[i].trim());
                        String instruction = instructions[i].trim();
                        recipeList.add(new Recipe_Product(stepNumber, instruction));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }

            // Create ingredient list
            ArrayList<BoxIngredient> ingredientList = new ArrayList<>();
            if (ingredientIds != null && ingredientQuantities != null && ingredientUnits != null) {
                for (int i = 0; i < ingredientIds.length && i < ingredientQuantities.length && i < ingredientUnits.length; i++) {
                    try {
                        int ingredientId = Integer.parseInt(ingredientIds[i].trim());
                        String ingredientQuantity = ingredientQuantities[i].trim();
                        String ingredientUnit = ingredientUnits[i].trim();
                        ingredientList.add(new BoxIngredient(ingredientId, ingredientQuantity, ingredientUnit));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }

            // Check if product name already exists
            ProductDAO pdao = new ProductDAO();
            ArrayList<Product> listOfProduct = pdao.getProductByName(productName);
            if (!listOfProduct.isEmpty()) {
                request.setAttribute("Admin_Manage_Product_Page_Error", "Product name already exists!");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }

            // Insert new product
            boolean success = pdao.insertProductDAO(productName, productDescription, Integer.parseInt(productPrice),
                    Integer.parseInt(productCookingTime), productImage, ingredientList, categoryList, recipeList);

            if (success) {
                // Redirect to manage product page
                response.sendRedirect("ManageProductServlet");
            } else {
                request.setAttribute("Admin_Manage_Product_Page_Error", "Failed to insert product!");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("Admin_Manage_Product_Page_Error", "Error processing request: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } finally {
            if (out != null) {
                out.close();
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
