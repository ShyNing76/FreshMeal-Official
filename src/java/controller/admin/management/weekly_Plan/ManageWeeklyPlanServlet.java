/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin.management.weekly_Plan;

import dao.ProductDAO;
import dao.Weekly_PlanDAO;
import dao.Weekly_Plan_ProductDAO;
import dto.Order;
import dto.Product;
import dto.Weekly_Plan;
import dto.Weekly_Plan_Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class ManageWeeklyPlanServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String ADMIN_MANAGE_WEEKLY_PLAN_PAGE = "view/jsp/admin/weeklyplan-admin.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ADMIN_MANAGE_WEEKLY_PLAN_PAGE;
        try {
            /* TODO output your page here. You may use following sample code. */
            Weekly_PlanDAO wpdao = new Weekly_PlanDAO();
            Weekly_Plan_ProductDAO wppdao = new Weekly_Plan_ProductDAO();
            ProductDAO pd = new ProductDAO();

            String statusParam = request.getParameter("status");
            Integer status = (statusParam != null && !statusParam.isEmpty()) ? Integer.parseInt(statusParam) : null;

            ArrayList<Weekly_Plan> listOfWeeklyPlan = (status == null) ? wpdao.getAllWeekly_Plan() : wpdao.getAllWeekly_PlanByStatus(status);
            ArrayList<Weekly_Plan_Product> listOfProductInWeekly = wppdao.getProductInWeekly();
            ArrayList<Product> listProduct = pd.getProduct();
            Map<Integer, String> productMap = pd.getProductMap();
            
            Collections.sort(listOfWeeklyPlan, new Comparator<Weekly_Plan>(){
                @Override
                public int compare(Weekly_Plan o1, Weekly_Plan o2) {
                    return o2.getStartDate().compareTo(o1.getStartDate());
                }
            });

            if (listOfWeeklyPlan != null) {
                request.setAttribute("ListOfWeeklyPlan", listOfWeeklyPlan);
                request.setAttribute("ListOfProductInWeekly", listOfProductInWeekly);
                request.setAttribute("ListProduct", listProduct);
                request.setAttribute("ProductMap", productMap);
            } else {
                request.setAttribute("Admin_Manage_Weekly_Plan_Page_Error", "Cann't Find Any Weekly Plan!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
