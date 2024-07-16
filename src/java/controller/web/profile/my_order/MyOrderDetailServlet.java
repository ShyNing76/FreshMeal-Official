/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web.profile.my_order;

import dao.AddressDAO;
import dao.DeliveryDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import dto.Address;
import dto.Delivery;
import dto.Order;
import dto.Order_Detail;
import dto.Product;
import dto.User;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
public class MyOrderDetailServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String MY_ORDER_DETAIL_PAGE = "view/jsp/home/my_order_detail.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");
        try {
            String orderID = request.getParameter("OrderID");
            OrderDAO odao = new OrderDAO();
            ProductDAO pdao = new ProductDAO();
            AddressDAO adao = new AddressDAO();
            DeliveryDAO ddao = new DeliveryDAO();
            Delivery delivery = ddao.getAllDeliveryActiveByOrderID(Integer.parseInt(orderID));
            Order orderOfUser = odao.getOrderByUserOrderID(Integer.parseInt(orderID), user.getUserID());
            ArrayList<Product> listOfProduct = pdao.getProduct();
            Address addressOfOrder = adao.getAddressByOrderID(Integer.parseInt(orderID));
            if (orderOfUser != null) {
                request.setAttribute("OrderOfUser", orderOfUser);
            } else {
                request.setAttribute("ERROR", "Cann't find your Order");
            }
            if (delivery != null) {
                request.setAttribute("DeliveryOfUser", delivery);
            } else {
                request.setAttribute("ERROR", "Cann't find your Order");
            }
            if (addressOfOrder != null) {
                request.setAttribute("AddressOfUser", addressOfOrder);
            } else {
                request.setAttribute("ERROR", "Cann't find your Order");
            }
            if (listOfProduct != null) {
                request.setAttribute("ListOfProductOfOrder", listOfProduct);
            } else {
                String errorMessage = "Can't Find Any Product In Orders Of User Name: " + user.getUserName();
                request.setAttribute("Web_Order_Profile_Page_Error", errorMessage);
            }
            
            int subTotal = 0;
            for (Order_Detail order_Detail : orderOfUser.getOrderDetail()) {
                subTotal = subTotal + order_Detail.getPrice() * order_Detail.getQuantity();
            }
            request.setAttribute("SUBTOTALPRICE", subTotal);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(MY_ORDER_DETAIL_PAGE).forward(request, response);
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
