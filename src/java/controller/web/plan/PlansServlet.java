/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web.plan;

import dao.Personal_PlanDAO;
import dao.ProductDAO;
import dto.Personal_Plan;
import dto.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class PlansServlet extends HttpServlet {

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
            String monthParam = request.getParameter("month");
            String yearParam = request.getParameter("year");

            Calendar calendar = Calendar.getInstance();
            int currentMonth = (monthParam == null) ? calendar.get(Calendar.MONTH) : Integer.parseInt(monthParam);
            int currentYear = (yearParam == null) ? calendar.get(Calendar.YEAR) : Integer.parseInt(yearParam);
            int today = calendar.get(Calendar.DAY_OF_MONTH);

            calendar.set(currentYear, currentMonth, 1);

            int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY;
            int totalDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

            List<Integer> days = new ArrayList<>();

            calendar.add(Calendar.MONTH, -1);
            int daysInPrevMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            for (int i = firstDayOfWeek - 1; i >= 0; i--) {
                days.add(daysInPrevMonth - i);
            }

            calendar.add(Calendar.MONTH, 1);
            for (int i = 1; i <= totalDays; i++) {
                days.add(i);
            }

            int remainingDays = 42 - days.size();
            for (int i = 1; i <= remainingDays; i++) {
                days.add(i);
            }

            Personal_PlanDAO ppd = new Personal_PlanDAO();
            ProductDAO pd = new ProductDAO();

            ArrayList<Personal_Plan> planForMonth = ppd.getPlansForMonth(currentMonth + 1, currentYear);

            Map<String, ArrayList<Personal_Plan>> plansByDate = new HashMap<>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (Personal_Plan plan : planForMonth) {
                String dateStr = sdf.format(plan.getDayPick());
                ArrayList<Personal_Plan> plansForDate = plansByDate.get(dateStr);
                if (plansForDate == null) {
                    plansForDate = new ArrayList<>();
                    plansByDate.put(dateStr, plansForDate);
                }
                plansForDate.add(plan);
            }

            Map<Integer, String> mealTypeMap = ppd.getMealTypeMap();
            Map<Integer, Product> productMap = pd.getProductOrderMap();

            request.setAttribute("days", days);
            request.setAttribute("currentMonth", currentMonth);
            request.setAttribute("currentYear", currentYear);
            request.setAttribute("totalDays", totalDays);
            request.setAttribute("firstDayOfWeek", firstDayOfWeek);
            request.setAttribute("today", today);
            request.setAttribute("plansByDate", plansByDate);
            request.setAttribute("MealTypeMap", mealTypeMap);
            request.setAttribute("ProductMap", productMap);
            request.getRequestDispatcher("view/jsp/home/plan.jsp").forward(request, response);
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
