/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web.plan;

import dao.Personal_PlanDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class UpdatePlanServlet extends HttpServlet {

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
            String typePlan = request.getParameter("selectedmealtypeupdate");
            int quantityMeal = Integer.parseInt(request.getParameter("quantitymeal"));
            int personalPlanId = Integer.parseInt(request.getParameter("personalplanid"));
            String dayPick = request.getParameter("selectedDate");
            
            
            SimpleDateFormat sdfInput = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date utilDate = null;
            Date sqlDate = null;

            if (dayPick != null) {
                try {
                    utilDate = sdfInput.parse(dayPick);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            
            if (utilDate != null) {
                sqlDate = new Date(utilDate.getTime());
            }
            
            Personal_PlanDAO d = new Personal_PlanDAO();
            int rs = d.updatePersonalPlan(typePlan, quantityMeal, personalPlanId);
            if (rs >= 1) {
                request.getRequestDispatcher("PersonalPlanServlet?selectedDate="+sqlDate).forward(request, response);
            }
            else{
                request.getRequestDispatcher("view/jsp/home/error.jsp").forward(request, response);
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
