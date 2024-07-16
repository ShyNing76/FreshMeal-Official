/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web.login;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.UserDAO;
import dto.Email;
import dto.User;
import dto.UserGoogle;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import utils.EncodePassword;
import utils.IConstants;

/**
 *
 * @author ADMIN
 */
public class RegisterServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String SIGNUP_PAGE = "view/jsp/home/signup.jsp";
    private final String LOGIN_PAGE = "view/jsp/home/login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            UserDAO udao = new UserDAO();
            if (request.getParameter("btAction") != null) {
                String userName = request.getParameter("txtUserName");
                String email = request.getParameter("txtEmail");
                String password = request.getParameter("txtPassword");
                String confirmpassword = request.getParameter("txtConfirmPassword");

                if (!confirmpassword.equals(password)) {
                    String confirmPasswordError = "Confirm Password Not Match With Password!";
                    request.setAttribute("msgConfirmPasswordError", confirmPasswordError);
                    request.getRequestDispatcher(SIGNUP_PAGE).forward(request, response);
                } else {
                    password = EncodePassword.toSHA1(password);
                    User userEmail = udao.checkUserEmail(email);
                    User userUserName = udao.checkUserName(userName);
                    if (userEmail != null || userUserName != null) {
                        String error = "The Account With UserName Or Email Already Exist!";
                        request.setAttribute("msgerror", error);
                        request.getRequestDispatcher(SIGNUP_PAGE).forward(request, response);
                    } else {
                        int rs = udao.insertUser(userName, email, password);
                        if (rs >= 1) {
                            Email emailGG = new Email();
                            emailGG.sendEmail("FRESH MEAL - Thông báo đăng ký tài khoản thành công", "Xác nhận đăng ký thành công tài khoản.", email);
                            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
                        } else {
                            request.setAttribute("msgerror", "ERROR!");
                            request.getRequestDispatcher(SIGNUP_PAGE).forward(request, response);
                        }
                    }
                }
            } else {
                String code = request.getParameter("code");
                String accessToken = getToken(code);
                UserGoogle userGoogle = getUserInfo(accessToken);
                if (userGoogle != null) {
                    User user = udao.getUserByEmail(userGoogle.getEmail());
                    if (user != null) {
                        String error = "The Account Already Exist!";
                        request.setAttribute("msgerror", error);
                        request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
                    } else {
                        request.setAttribute("UserGoogle", userGoogle);
                        request.getRequestDispatcher(SIGNUP_PAGE).forward(request, response);
                    }
                } else {

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getToken(String code) throws ClientProtocolException, IOException {
        // call api to get token
        String response = Request.Post(IConstants.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", IConstants.GOOGLE_CLIENT_ID)
                        .add("client_secret", IConstants.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", IConstants.GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", IConstants.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static UserGoogle getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = IConstants.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();

        UserGoogle googlePojo = new Gson().fromJson(response, UserGoogle.class);

        return googlePojo;
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
