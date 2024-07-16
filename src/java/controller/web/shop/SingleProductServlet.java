/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web.shop;

import dao.BoxIngredientDAO;
import dao.CategoryDAO;
import dao.IngredientDAO;
import dao.ProductDAO;
import dao.RecipeProductDAO;
import dao.Weekly_Plan_ProductDAO;
import dto.BoxIngredient;
import dto.Category;
import dto.Ingredient;
import dto.Product;
import dto.Recipe_Product;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class SingleProductServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String SINGLE_PRODUCT_PAGE = "view/jsp/home/menu_info.jsp";
    private final String MENU_PAGE = "view/jsp/home/MenuView.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = SINGLE_PRODUCT_PAGE;
        try {
            String productID = request.getParameter("productId");
            String mealType = request.getParameter("mealType");
            String quantity = request.getParameter("quantity");
            ProductDAO pdao = new ProductDAO();
            CategoryDAO cdao = new CategoryDAO();
            BoxIngredientDAO bidao = new BoxIngredientDAO();
            Weekly_Plan_ProductDAO wppdao = new Weekly_Plan_ProductDAO();
            IngredientDAO idao = new IngredientDAO();
            RecipeProductDAO rpdao = new RecipeProductDAO();
            Product product = pdao.getProductByID(Integer.parseInt(productID));
            ArrayList<Category> listOfCategory = cdao.getCategory();
            ArrayList<Product> listOfProductInAllWeeklyPlan = wppdao.getProductInWeekly_Plan();
            ArrayList<Ingredient> listOfIngredient = idao.getIngredient();
            ArrayList<BoxIngredient> listOfIngredientInProduct = bidao.getBoxIngredientByProduct(Integer.parseInt(productID));
            ArrayList<Recipe_Product> listOfRecipe = rpdao.getRecipeById(Integer.parseInt(productID));
            if (listOfCategory != null) {
                request.setAttribute("ListOfCategory", listOfCategory);
            }
            if (listOfProductInAllWeeklyPlan != null) {
                request.setAttribute("ListOfProductInAllWeeklyPlan", listOfProductInAllWeeklyPlan);
            } else {
                request.setAttribute("Web_Product_In_Weekly_Plan_Error", "Cann't Find Product In Weekly Plan!");
            }
            if (listOfIngredient != null) {
                request.setAttribute("ListOfIngredient", listOfIngredient);
            } else {

            }
            if (listOfIngredientInProduct != null) {
                request.setAttribute("ListOfIngredientInProduct", listOfIngredientInProduct);
            } else {

            }
            if (listOfRecipe != null) {
                request.setAttribute("ListOfRecipe", listOfRecipe);
            } else {

            }
            if (product != null) {
                request.setAttribute("Product", product);
                if (mealType != null && quantity != null) {
                    request.setAttribute("MEALTYPE", mealType);
                    request.setAttribute("QUANTITY", quantity);
                }
            } else {
                request.setAttribute("SingleProductPageError", "Error When Accessing The Page!");
                url = MENU_PAGE;
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
