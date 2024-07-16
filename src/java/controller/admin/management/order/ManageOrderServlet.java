/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin.management.order;

import dao.AddressDAO;
import dao.DeliveryDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import dao.UserDAO;
import dto.Address;
import dto.Delivery;
import dto.Order;
import dto.Product;
import dto.User;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
public class ManageOrderServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String ADMIN_MANAGE_ORDER_PAGE = "view/jsp/admin/order-admin.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ADMIN_MANAGE_ORDER_PAGE;
        try {
            OrderDAO odao = new OrderDAO();
            AddressDAO adao = new AddressDAO();
            UserDAO udao = new UserDAO();
            DeliveryDAO ddao = new DeliveryDAO();
            ProductDAO pdao = new ProductDAO();

            String status = request.getParameter("status-search");
            String orderDate = request.getParameter("orderDate");
            String customerContact = request.getParameter("customerContact");

            SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = null;
            Date sqlDate = null;

            if (orderDate != null && !orderDate.isEmpty()) {
                try {
                    utilDate = sdfInput.parse(orderDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            if (utilDate != null) {
                sqlDate = new Date(utilDate.getTime());
            }

            ArrayList<Order> listOfOrder = odao.getAllOrderFilter(status, sqlDate, customerContact);
            Map<Integer, User> userMap = udao.getUserMap();
            Map<Integer, Delivery> deliveryMap = ddao.getDeliveryMap();

            Collections.sort(listOfOrder, new Comparator<Order>() {
                @Override
                public int compare(Order o1, Order o2) {
                    int dateComparison = o2.getOrderDate().compareTo(o1.getOrderDate()); // Sắp xếp giảm dần theo orderDate
                    if (dateComparison == 0) {
                        return Integer.compare(o2.getOrderID(), o1.getOrderID()); // Nếu orderDate bằng nhau, sắp xếp giảm dần theo orderId
                    }
                    return dateComparison;
                }
            });
            
            Map<Integer, Product> productOrderMap = pdao.getProductOrderMap();
            Map<Integer, Address> addressMap = adao.getAddressMap();
            Map<String, String> statusMap = new HashMap<>();
            statusMap.put("Pending", "badge-warning");
            statusMap.put("Approve", "badge-primary");
            statusMap.put("Delivery", "badge-info");
            statusMap.put("Finish", "badge-success");
            statusMap.put("Cancel", "badge-danger");
            statusMap.put("Waiting For Accept", "badge-warning");
            statusMap.put("Return/Refund", "badge-secondary");

            if (listOfOrder != null) {
                request.setAttribute("ListOfOrder", listOfOrder);
                request.setAttribute("UserMap", userMap);
                request.setAttribute("AddressMap", addressMap);
                request.setAttribute("DeliveryMap", deliveryMap);
                request.setAttribute("statusMap", statusMap);
                request.setAttribute("ProductOrderMap", productOrderMap);
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
