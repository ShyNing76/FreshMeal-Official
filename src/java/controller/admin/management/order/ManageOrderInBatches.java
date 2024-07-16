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
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class ManageOrderInBatches extends HttpServlet {

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
                String orderDate = request.getParameter("orderdate");

                SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date utilDate = null;
                Date sqlDate = null;

                if (orderDate != null) {
                    try {
                        utilDate = sdfInput.parse(orderDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                if (utilDate != null) {
                    sqlDate = new Date(utilDate.getTime());
                }

                OrderDAO odao = new OrderDAO();
                AddressDAO adao = new AddressDAO();
                UserDAO udao = new UserDAO();
                DeliveryDAO ddao = new DeliveryDAO();
                ProductDAO pdao = new ProductDAO();

                ArrayList<Order> listOfOrder = odao.getOrderByOrderDate(sqlDate);
                Map<Integer, User> userMap = udao.getUserMap();
                Map<Integer, Delivery> deliveryMap = ddao.getDeliveryMap();
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

                ArrayList<String> listDistrict = new ArrayList<>();
                listDistrict.add("Thành Phố Thủ Đức");
                listDistrict.add("Quận 1");
                listDistrict.add("Quận 2");
                listDistrict.add("Quận 3");
                listDistrict.add("Quận 4");
                listDistrict.add("Quận 5");
                listDistrict.add("Quận 6");
                listDistrict.add("Quận 7");
                listDistrict.add("Quận 8");
                listDistrict.add("Quận 9");
                listDistrict.add("Quận 10");
                listDistrict.add("Quận 11");
                listDistrict.add("Quận 12");
                listDistrict.add("Quận Bình Tân");
                listDistrict.add("Quận Bình Thạnh");
                listDistrict.add("Quận Gò Vấp");
                listDistrict.add("Quận Phú Nhuận");
                listDistrict.add("Quận Tân Bình");
                listDistrict.add("Quận Tân Phú");
                listDistrict.add("Huyện Bình Chánh");
                listDistrict.add("Huyện Cần Giờ");
                listDistrict.add("Huyện Củ Chi");
                listDistrict.add("Huyện Hóc Môn");
                listDistrict.add("Huyện Nhà Bè");

                if (listOfOrder != null) {
                    request.setAttribute("ListOfOrder", listOfOrder);
                    request.setAttribute("UserMap", userMap);
                    request.setAttribute("AddressMap", addressMap);
                    request.setAttribute("DeliveryMap", deliveryMap);
                    request.setAttribute("statusMap", statusMap);
                    request.setAttribute("ProductOrderMap", productOrderMap);
                    request.setAttribute("ListDistrict", listDistrict);
                    request.setAttribute("OrderDate", orderDate);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (!response.isCommitted()) {
                    request.getRequestDispatcher("view/jsp/admin/OrderInBatches.jsp").forward(request, response);
                }
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
