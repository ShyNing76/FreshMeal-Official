/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin.management.order;

import dao.OrderDAO;
import dao.UserDAO;
import dto.Email;
import dto.Order;
import dto.User;
import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class UpdateOrderServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String ADMIN_MANAGE_ORDER_PAGE = "ManageOrderServlet";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ADMIN_MANAGE_ORDER_PAGE;
        try {
            String orderID = request.getParameter("orderid");
            String userID = request.getParameter("userID");
            String orderStatus = request.getParameter("status");
            LocalDate shipDate = null;

            if ("Finish".equalsIgnoreCase(orderStatus)) {
                shipDate = LocalDate.now();
            }

            OrderDAO odao = new OrderDAO();
            Order order = odao.getOrderByID(Integer.parseInt(orderID.trim()));
            UserDAO udao = new UserDAO();
            User user = udao.getUserByID(Integer.parseInt(userID));
            if (order != null) {
                int rs = odao.updateStatusOrderAdmin(orderStatus.trim(), Integer.parseInt(orderID.trim()), shipDate);
                if (rs >= 1) {
                    Email email = new Email();
                    if (orderStatus.equals("Approve")) {
                        email.sendEmail(email.subjectApproveOrder(), "<p>Xin chào " + user.getFirstName() + user.getLastName() + ",</p>\n"
                                + "<p>Đơn hàng của bạn đã được phê duyệt thành công. Chúng tôi sẽ sớm tiến hành giao hàng cho bạn.</p>\n"
                                + "<p>Cảm ơn bạn đã tin tưởng và sử dụng dịch vụ của chúng tôi.</p>\n"
                                + "<p>Trân trọng,</p>\n"
                                + "<p>FRESH MEAL</p>", user.getEmail());
                    } else if (orderStatus.equals("Delivery")) {
                        email.sendEmail(email.subjectDeliveryOrder(), "<p>Xin chào " + user.getFirstName() + user.getLastName() + ",</p>\n"
                                + "<p>Đơn hàng của bạn đang được giao và sẽ đến nơi trong khoảng thời gian sớm nhất. Vui lòng kiểm tra thông tin giao hàng để biết thêm chi tiết.</p>\n"
                                + "<p>Cảm ơn bạn đã lựa chọn dịch vụ của chúng tôi.</p>\n"
                                + "<p>Trân trọng,</p>\n"
                                + "<p>FRESH MEAL</p>", user.getEmail());
                    } else if (orderStatus.equals("Finish")) {
                        email.sendEmail(email.subjectFinishOrder(), "<p>Xin chào " + user.getFirstName() + user.getLastName() + ",</p>\n"
                                + "<p>Đơn hàng của bạn đã được giao thành công. Cảm ơn bạn đã mua sắm tại FRESH MEAL.</p>\n"
                                + "<p>Chúng tôi hy vọng sẽ được phục vụ bạn trong những lần mua sắm tiếp theo.</p>\n"
                                + "<p>Trân trọng,</p>\n"
                                + "<p>FRESH MEAL</p>", user.getEmail());
                    } else if (orderStatus.equals("Return/Refund")) {
                        email.sendEmail(email.subjectFinishRefurnOrder(), "<p>Xin chào " + user.getFirstName() + user.getLastName() + ",</p>\n"
                                + "<p>Chúng tôi thông báo rằng yêu cầu trả lại đơn hàng của bạn đã được chấp nhận. Đơn hàng của bạn sẽ được xử lý và chúng tôi sẽ thông báo cho bạn khi quá trình hoàn tất.</p>\n"
                                + "<p>Nếu bạn có bất kỳ câu hỏi nào, vui lòng liên hệ với bộ phận chăm sóc khách hàng của chúng tôi.</p>\n"
                                + "<p>Trân trọng,</p>\n"
                                + "<p>FRESH MEAL</p>", user.getEmail());
                    }
                    request.getRequestDispatcher(url).forward(request, response);
                } else {
                    request.setAttribute("ERROR", "Cann't Change Order Status");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
