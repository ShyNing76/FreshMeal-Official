/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin.management.dashboard;

import dao.OrderDAO;
import dao.ProductDAO;
import dao.UserDAO;
import dto.Order;
import dto.Product;
import dto.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class ManageDashBoardServlet extends HttpServlet {

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
            try {
                OrderDAO odao = new OrderDAO();
                UserDAO udao = new UserDAO();
                ProductDAO pdao = new ProductDAO();

                ArrayList<Order> listOrder = odao.getAllOrder();
                ArrayList<Order> listOrderLastest = odao.getTop5OrderLastest();
                ArrayList<User> listCustomer = udao.getUser();
                ArrayList<Product> listProduct = pdao.getProduct();
                ArrayList<Product> listBestSelling = odao.getBestSellingDishes();
                ArrayList<User> topCustomers = odao.getTopCustomers();
                Map<Date, Integer> revenueData = odao.getRevenueMap();
                Map<Date, Integer> orderData = odao.getOrderMap();
                Map<Integer, User> userMap = udao.getUserMap();
                Map<String, String> statusMap = new HashMap<>();
                statusMap.put("Pending", "badge-warning");
                statusMap.put("Approve", "badge-primary");
                statusMap.put("Delivery", "badge-info");
                statusMap.put("Finish", "badge-success");
                statusMap.put("Cancel", "badge-danger");
                statusMap.put("Waiting For Accept", "badge-warning");
                statusMap.put("Return/Refund", "badge-secondary");

                int revenue = 0;
                int totalCustomer = 0;

//                Revenue
                for (Order order : listOrder) {
                    if (order.getStatus().equalsIgnoreCase("Finish")) {
                        revenue += order.getTotalPrice();
                    }
                }

//                Customer
                for (User user : listCustomer) {
                    if (user.getRoleID() == 1) {
                        totalCustomer++;
                    }
                }

                if (listOrder != null && listCustomer != null && listProduct != null ) {
                    request.setAttribute("ListOrder", listOrder);
                    request.setAttribute("ListOrderLastest", listOrderLastest);
                    request.setAttribute("ListCustomer", listCustomer);
                    request.setAttribute("UserMap", userMap);
                    request.setAttribute("topCustomers", topCustomers);
                    request.setAttribute("ListProduct", listProduct);
                    request.setAttribute("ListBestSelling", listBestSelling);
                    request.setAttribute("Revenue", revenue);
                    request.setAttribute("TotalCustomer", totalCustomer);
                    request.setAttribute("RevenueDate", revenueData);
                    request.setAttribute("OrderDate", orderData);
                    request.setAttribute("StatusMap", statusMap);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                request.getRequestDispatcher("view/jsp/admin/admin.jsp").forward(request, response);
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
