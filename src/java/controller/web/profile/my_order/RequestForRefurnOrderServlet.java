/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web.profile.my_order;

import dao.OrderDAO;
import dto.Email;
import dto.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
public class RequestForRefurnOrderServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String PROFILE_MY_ORDER_PAGE = "view/jsp/home/my_order.jsp";
    private final String PROFILE_MY_REFURN_ORDER_PAGE = "DispatchServlet?btAction=MyOrder";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String orderID = request.getParameter("orderID");
            OrderDAO odao = new OrderDAO();
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("account");
            if (user != null) {
                int rs = odao.updateStatusOrder("Waiting For Accept", Integer.parseInt(orderID));
                if (rs >= 1) {
                    Email email = new Email();
                    email.sendEmail(email.subjectRefurnOrder(), "<p>Xin chào " + user.getFirstName() + user.getLastName() + ",</p>\n"
                            + "<p>Chúng tôi đã nhận được yêu cầu trả lại đơn hàng của bạn. Đơn hàng sẽ được xử lý và chúng tôi sẽ thông báo cho bạn khi quá trình hoàn tất.</p>\n"
                            + "<p>Nếu bạn có bất kỳ câu hỏi nào, vui lòng liên hệ với bộ phận chăm sóc khách hàng của chúng tôi.</p>\n"
                            + "<p>Trân trọng,</p>\n"
                            + "<p>FRESH MEAL</p>", user.getEmail());
                    response.sendRedirect(PROFILE_MY_REFURN_ORDER_PAGE);
                } else {
                    request.getRequestDispatcher(PROFILE_MY_ORDER_PAGE).forward(request, response);
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
