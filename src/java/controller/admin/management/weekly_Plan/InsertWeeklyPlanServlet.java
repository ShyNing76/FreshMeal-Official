/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin.management.weekly_Plan;

import dao.Weekly_PlanDAO;
import dto.Weekly_Plan;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class InsertWeeklyPlanServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String startdateParam = request.getParameter("startdate");
            String enddateParam = request.getParameter("enddate");
            String[] productIds = request.getParameterValues("products");

            Date startDate = Date.valueOf(startdateParam);
            Date endDate = Date.valueOf(enddateParam);
            ArrayList<Integer> productList = new ArrayList<>();

            if (productIds != null) {
                for (String productId : productIds) {
                    try {
                        productList.add(Integer.parseInt(productId.trim()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            try {
                Weekly_PlanDAO wpd = new Weekly_PlanDAO();
                boolean success = wpd.insertWeeklyPlan(startDate, endDate, productList);

                if (success) {
                    request.getRequestDispatcher("ManageWeeklyPlanServlet").forward(request, response);
                } else {
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.getRequestDispatcher("error.jsp").forward(request, response);
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
