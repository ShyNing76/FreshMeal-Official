/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web.plan;

import dao.CategoryDAO;
import dao.Personal_PlanDAO;
import dao.ProductDAO;
import dao.Weekly_PlanDAO;
import dto.Personal_Plan;
import dto.Product;
import dto.Weekly_Plan_Product;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class PersonalPlanServlet extends HttpServlet {

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
        try {
            Personal_PlanDAO ppdao = new Personal_PlanDAO();
            ProductDAO pdao = new ProductDAO();
            Weekly_PlanDAO wpdao = new Weekly_PlanDAO();
            CategoryDAO cdao = new CategoryDAO();

            ArrayList<Integer> listProductWeekPlan = new ArrayList<>();
            ArrayList<Product> listProductItem = new ArrayList<>();
            ArrayList<Personal_Plan> listPlan = new ArrayList<>();
            Map<Integer, String> categoryMap = cdao.getCategoryMap();

            String selectedDateParam = request.getParameter("selectedDate");
            SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdfOutput = new SimpleDateFormat("dd-MM-yyyy");
            Date selectedDate = null;

            if (selectedDateParam != null && !selectedDateParam.isEmpty()) {
                try {
                    selectedDate = new Date(sdfInput.parse(selectedDateParam).getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            if (selectedDate != null) {

                ArrayList<Integer> productWeekPlan = pdao.getProductIdForDayPick(selectedDate);

                if (productWeekPlan != null && !productWeekPlan.isEmpty()) {
                    for (Integer productID : productWeekPlan) {
                        if (!listProductWeekPlan.contains(productID)) {
                            listProductWeekPlan.add(productID);

                            // Lấy thông tin sản phẩm từ Database
                            Product product = pdao.getProductByID(productID);

                            // Kiểm tra sản phẩm và đảm bảo không thêm sản phẩm trùng lặp vào listProductItem
                            if (product != null && !listProductItem.contains(product)) {
                                listProductItem.add(product);
                            }
                        }
                    }
                }
            }

            request.setAttribute("listproductItem", listProductItem);
            request.setAttribute("listProductId", listProductWeekPlan);

            listPlan = ppdao.getPersonalPlanForDate(selectedDate);

            if (listPlan != null && !listPlan.isEmpty()) {
                ArrayList<Product> listProduct = new ArrayList<>();
                Set<Integer> addedProductIDs = new HashSet<>(); // Sử dụng Set để theo dõi các productID đã được thêm vào

                for (Personal_Plan personal_Plan : listPlan) {
                    for (Weekly_Plan_Product weekly_Plan_Product : personal_Plan.getListOfProduct()) {
                        int productID = weekly_Plan_Product.getProductID();
                        if (!addedProductIDs.contains(productID)) {
                            Product product = pdao.getProductByID(productID);
                            if (product != null) {
                                listProduct.add(product);
                                addedProductIDs.add(productID); // Thêm productID vào Set
                            }
                        }
                    }
                }

                request.setAttribute("personalPlans", listPlan);
                request.setAttribute("listProducts", listProduct);
            }

            String formattedDate = sdfOutput.format(selectedDate);
            request.setAttribute("selectedDate", formattedDate);
            request.setAttribute("categoryMap", categoryMap);
            request.getRequestDispatcher("view/jsp/home/PersonalPlan.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("view/jsp/home/error.jsp").forward(request, response);
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
