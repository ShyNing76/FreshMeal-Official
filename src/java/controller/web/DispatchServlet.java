/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class DispatchServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //trang khách hàng
    private final String LOGIN_PAGE = "loginPage";
    private final String LOGIN_PAGE_WEB = "view/jsp/home/login.jsp";
    private final String LOGIN = "Login";
    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String WELCOME_HOME = "Welcome";
    private final String WELCOME_PAGE = "view/jsp/home/home.jsp";
    private final String FORM = "Form";
    private final String FORM_PAGE = "view/jsp/home/user_information_form.jsp";
    private final String SIGNUP = "Signup";
    private final String SIGNUP_CONTROLLER = "RegisterServlet";
    private final String SIGNUP_PAGE = "SignupPage";
    private final String SIGNUP_PAGE_WEB = "view/jsp/home/signup.jsp";
    private final String FORGOT_PASSWORD_PAGE = "ForgotPasswordPage";
    private final String FORGOT_PASSWORD_PAGE_WEB = "view/jsp/home/forgot_password.jsp";
    private final String FORGOT_PASSWORD = "ForgotPassword";
    private final String FORGOT_PASSWORD_CONTROLLER = "ForgotPasswordServlet";
    private final String LOGOUT = "Logout";
    private final String LOGOUT_CONTROLLER = "LogoutServlet";

    //Trang Plans
    private final String PLANS = "Plans";
    private final String PLANS_CONTROLLER = "PlansServlet";
    private final String PERSONAL_PLANS = "personalPlan";
    private final String PERSONAL_PLANS_CONTROLLER = "PersonalPlanServlet";
    private final String CREATE_PERSONAL_PLAN = "createPersonalPlan";
    private final String CREATE_PERSONAL_PLAN_CONTROLLER = "CreatePersonalPlanServlet";
    private final String UPDATE_PERSONAL_PLAN = "updatePersonalPlan";
    private final String UPDATE_PERSONAL_PLAN_CONTROLLER = "UpdatePlanServlet";
    private final String REMOVE_PERSONAL_PLAN = "removePersonalPlan";
    private final String REMOVE_PERSONAL_PLAN_CONTROLLER = "RemovePlanServlet";

    //Trang How it works
    private final String HOW = "How";
    private final String HOW_PAGE = "view/jsp/home/how.jsp";

    //Trang Menu
    private final String MENU = "Menu";
    private final String MENU_CONTROLLER = "MenuServlet";
    private final String MENU_INFO = "MenuInfo";
    private final String MENU_INFO_CONTROLLER = "SingleProductServlet";
    private final String MENU_SEARCH = "Search";
    private final String MENU_SEARCH_CONTROLLER = "SearchServlet";
    private final String PRODUCT_CATEGORY_MENU = "CategoryProduct";
    private final String PRODUCT_CATEGORY_MENU_CONTROLLER = "CategorySelectionServlet";
    private final String WEEKLY_PLAN_PRODUCT_MENU = "WeeklyPlanProduct";
    private final String WEEKLY_PLAN_PRODUCT_MENU_CONTROLLER = "WeeklyPlanSelectionServlet";
    private final String TIME_SELECTION_MENU = "TimeSelection";
    private final String TIME_SELECTION_MENU_CONTROLLER = "TimeSelectionServlet";
    private final String PRICE_SELECTION_MENU = "PriceSelection";
    private final String PRICE_SELECTION_MENU_CONTROLLER = "PriceSelectionServlet";
    private final String TOP_TIME_SELECTION_MENU = "TopTimeSelection";
    private final String TOP_TIME_SELECTION_MENU_CONTROLLER = "TopTimeSelectionServlet";
    private final String TOP_PRICE_SELECTION_MENU = "TopPriceSelection";
    private final String TOP_PRICE_SELECTION_MENU_CONTROLLER = "TopPriceSelectionServlet";

    //Trang Cart
    private final String CART_PAGE = "CartPage";
    private final String CART_PAGE_WEB = "CartServlet";
    private final String ADD_TO_CART = "AddToCart";
    private final String ADD_TO_CART_CONTROLLER = "AddToCartServlet";
    private final String DELETE_ITEM_OF_CART = "DeleteItemOfCart";
    private final String UPDATE_ITEM_OF_CART = "UpdateItemOfCart";
    private final String EDIT_CART_CONTROLLER = "EditCartServlet";
    private final String CHECKOUT1 = "Checkout1";
    private final String CHECKOUT1_CONTROLLER = "Checkout1Servlet";
    private final String CHECKOUT2 = "Checkout2";
    private final String CHECKOUT2_CONTROLLER = "Checkout2Servlet";
    private final String CHECKOUT3 = "Checkout3";
    private final String CHECKOUT3_CONTROLLER = "Checkout3Servlet";
    private final String CHECKOUT4 = "Checkout4";
    private final String CHECKOUT4_CONTROLLER = "Checkout4Servlet";
    private final String CHECKOUT5 = "Checkout5";
    private final String CHECKOUT5_CONTROLLER = "Checkout5Servlet";
    private final String PROMOCODE = "PromoCode";
    private final String PROMOCODE_CONTROLLER = "PromoCodeServlet";

    //Trang Profile
    private final String PROFILE = "Profile";
    private final String PROFILE_CONTROLLER = "ProfileServlet";
    private final String SAVE_EDIT_PROFILE = "SaveEditProfile";
    private final String SAVE_EDIT_PROFILE_CONTROLLER = "EditProfileServlet";
    private final String EDIT_PROFILE = "EditProfile";
    private final String EDIT_PROFILE_PAGE = "view/jsp/home/profile_edit.jsp";
    private final String PASSWORD_CHANGE = "PasswordChange";
    private final String PASSWORD_CHANGE_PAGE = "view/jsp/home/password_change.jsp";
    private final String SAVE_CHANGE_PASSWORD = "SaveChangePassword";
    private final String SAVE_CHANGE_PASSWORD_CONTROLLER = "ChangePasswordServlet";
    private final String UPDATE_IMAGE_FILE_PROFILE = "UpdateImageProfile";
    private final String UPDATE_IMAGE_FILE_PROFILE_CONTROLLER = "UpdateImageProfileServlet";

    //Trang Profile-Order
    private final String MY_ORDER = "MyOrder";
    private final String MY_ORDER_CONTROLLER = "MyOrderServlet";
    private final String MY_PENDING_ORDER = "PendingOrder";
    private final String MY_PENDING_ORDER_CONTROLLER = "PendingMyOrderServlet";
    private final String MY_APPROVE_ORDER = "ApproveOrder";
    private final String MY_APPROVE_ORDER_CONTROLLER = "ApproveMyOrderServlet";
    private final String MY_DELIVERY_ORDER = "DeliveryOrder";
    private final String MY_DELIVERY_ORDER_CONTROLLER = "DeliveryMyOrderServlet";
    private final String MY_FINISH_ORDER = "FinishOrder";
    private final String MY_FINISH_ORDER_CONTROLLER = "FinishMyOrderServlet";
    private final String MY_CANCEL_ORDER = "CancelOrder";
    private final String MY_CANCEL_ORDER_CONTROLLER = "CancelMyOrderServlet";
    private final String MY_REFURN_ORDER = "RefurnOrder";
    private final String MY_REFURN_ORDER_CONTROLLER = "RefurnMyOrderServlet";
    private final String REQUEST_FOR_CANCEL_MY_ORDER = "RequestForCancelOrder";
    private final String REQUEST_FOR_CANCEL_MY_ORDER_CONTROLLER = "RequestForCancelOrderServlet";
    private final String REQUEST_FOR_REFURN_MY_ORDER = "RequestForRefurnOrder";
    private final String REQUEST_FOR_REFURN_MY_ORDER_CONTROLLER = "RequestForRefurnOrderServlet";
    private final String MY_ORDER_DETAIL = "MyOrderDetail";
    private final String MY_ORDER_DETAIL_CONTROLLER = "MyOrderDetailServlet";

    //Trang Profile-Address
    private final String MY_ADDRESS = "MyAddress";
    private final String MY_ADDRESS_CONTROLLER = "MyAddressServlet";
    private final String ADD_NEW_MY_ADDRESS = "addNewMyAddress";
    private final String ADD_MY_ADDRESS_CONTROLLER = "AddMyAddressServlet";
    private final String DELETE_MY_ADDRESS = "deleteMyAddress";
    private final String DELETE_MY_ADDRESS_CONTROLLER = "DeleteMyAddressServlet";
    private final String UPDATE_MY_ADDRESS = "UpdateMyAddress";
    private final String UPDATE_MY_ADDRESS_CONTROLLER = "UpdateMyAddress";

    //trang admin
    private final String WELCOME_ADMIN = "ManageDashBoardServlet";
    private final String INGREDIENT = "ManageIngredientServlet";
    private final String INSERT_INGREDIENT = "InsertIngredientServlet";
    private final String ORDER = "ManageOrderServlet";
    private final String ORDER_IN_BATCHES = "ManageOrderInBatches";
    private final String UPDATE_ORDER = "UpdateOrderServlet";
    private final String PRODUCT = "ManageProductServlet";
    private final String INSERT_PRODUCT = "InsertProductServlet";
    private final String UPDATE_PRODUCT = "UpdateProductServlet";
    private final String UPDATE_STATUS_PRODUCT = "UpdateStatusProductServlet";
    private final String USER = "ManageUserServlet";
    private final String INSERT_USER = "InsertUserServlet";
    private final String UPDATE_USER = "UpdateUserServlet";
    private final String UPDATE_STATUS_USER = "UpdateStatusUserServlet";
    private final String WEEKLYPLAN = "ManageWeeklyPlanServlet";
    private final String INSERT_WEEKLYPLAN = "InsertWeeklyPlanServlet";
    private final String UPDATE_STATUS_PRODUCT_IN_WEEKLYPLAN = "UpdateStatusProductInWeeklyServlet";
    private final String UPDATE_STATUS_WEEKLYPLAN = "UpdateStatusWeeklyPlanServlet";
    private final String UPDATE_WEEKLYPLAN = "UpdateWeeklyPlanServlet";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String ac = request.getParameter("btAction");
            String url = "";
            if (ac == null) {
                ac = WELCOME_HOME;
            }
            switch (ac) {
                //trang khách hàng
                case WELCOME_HOME:
                    url = WELCOME_PAGE;
                    break;
                case SIGNUP_PAGE:
                    url = SIGNUP_PAGE_WEB;
                    break;
                case SIGNUP:
                    url = SIGNUP_CONTROLLER;
                    break;
                case LOGIN_PAGE:
                    url = LOGIN_PAGE_WEB;
                    break;
                case LOGIN:
                    url = LOGIN_CONTROLLER;
                    break;
                case FORGOT_PASSWORD_PAGE:
                    url = FORGOT_PASSWORD_PAGE_WEB;
                    break;
                case FORGOT_PASSWORD:
                    url = FORGOT_PASSWORD_CONTROLLER;
                    break;
                case LOGOUT:
                    url = LOGOUT_CONTROLLER;
                    break;
                case FORM:
                    url = FORM_PAGE;
                    break;

                //Trang Cart
                case CART_PAGE:
                    url = CART_PAGE_WEB;
                    break;
                case ADD_TO_CART:
                    url = ADD_TO_CART_CONTROLLER;
                    break;
                case DELETE_ITEM_OF_CART:
                    url = EDIT_CART_CONTROLLER;
                    break;
                case UPDATE_ITEM_OF_CART:
                    url = EDIT_CART_CONTROLLER;
                    break;
                case CHECKOUT1:
                    url = CHECKOUT1_CONTROLLER;
                    break;
                case CHECKOUT2:
                    url = CHECKOUT2_CONTROLLER;
                    break;
                case CHECKOUT3:
                    url = CHECKOUT3_CONTROLLER;
                    break;
                case CHECKOUT4:
                    url = CHECKOUT4_CONTROLLER;
                    break;
                case CHECKOUT5:
                    url = CHECKOUT5_CONTROLLER;
                    break;
                case PROMOCODE:
                    url = PROMOCODE_CONTROLLER;
                    break;

                //Trang Plans
                case PLANS:
                    url = PLANS_CONTROLLER;
                    break;
                case PERSONAL_PLANS:
                    url = PERSONAL_PLANS_CONTROLLER;
                    break;
                case CREATE_PERSONAL_PLAN:
                    url = CREATE_PERSONAL_PLAN_CONTROLLER;
                    break;
                case UPDATE_PERSONAL_PLAN:
                    url = UPDATE_PERSONAL_PLAN_CONTROLLER;
                    break;
                case REMOVE_PERSONAL_PLAN:
                    url = REMOVE_PERSONAL_PLAN_CONTROLLER;
                    break;

                //Trang Menu
                case MENU:
                    url = MENU_CONTROLLER;
                    break;
                case HOW:
                    url = HOW_PAGE;
                    break;
                case MENU_INFO:
                    url = MENU_INFO_CONTROLLER;
                    break;
                case MENU_SEARCH:
                    url = MENU_SEARCH_CONTROLLER;
                    break;
                case PRODUCT_CATEGORY_MENU:
                    url = PRODUCT_CATEGORY_MENU_CONTROLLER;
                    break;
                case WEEKLY_PLAN_PRODUCT_MENU:
                    url = WEEKLY_PLAN_PRODUCT_MENU_CONTROLLER;
                    break;
                case TIME_SELECTION_MENU:
                    url = TIME_SELECTION_MENU_CONTROLLER;
                    break;
                case PRICE_SELECTION_MENU:
                    url = PRICE_SELECTION_MENU_CONTROLLER;
                    break;
                case TOP_TIME_SELECTION_MENU:
                    url = TOP_TIME_SELECTION_MENU_CONTROLLER;
                    break;
                case TOP_PRICE_SELECTION_MENU:
                    url = TOP_PRICE_SELECTION_MENU_CONTROLLER;
                    break;

                // trang Profile
                case PROFILE:
                    url = PROFILE_CONTROLLER;
                    break;
                case PASSWORD_CHANGE:
                    url = PASSWORD_CHANGE_PAGE;
                    break;
                case SAVE_EDIT_PROFILE:
                    url = SAVE_EDIT_PROFILE_CONTROLLER;
                    break;
                case EDIT_PROFILE:
                    url = EDIT_PROFILE_PAGE;
                    break;
                case SAVE_CHANGE_PASSWORD:
                    url = SAVE_CHANGE_PASSWORD_CONTROLLER;
                    break;
                case UPDATE_IMAGE_FILE_PROFILE:
                    url = UPDATE_IMAGE_FILE_PROFILE_CONTROLLER;
                    break;

                // trang Profile-Order
                case MY_ORDER:
                    url = MY_ORDER_CONTROLLER;
                    break;
                case MY_PENDING_ORDER:
                    url = MY_PENDING_ORDER_CONTROLLER;
                    break;
                case MY_APPROVE_ORDER:
                    url = MY_APPROVE_ORDER_CONTROLLER;
                    break;
                case MY_DELIVERY_ORDER:
                    url = MY_DELIVERY_ORDER_CONTROLLER;
                    break;
                case MY_FINISH_ORDER:
                    url = MY_FINISH_ORDER_CONTROLLER;
                    break;
                case MY_CANCEL_ORDER:
                    url = MY_CANCEL_ORDER_CONTROLLER;
                    break;
                case MY_REFURN_ORDER:
                    url = MY_REFURN_ORDER_CONTROLLER;
                    break;
                case REQUEST_FOR_CANCEL_MY_ORDER:
                    url = REQUEST_FOR_CANCEL_MY_ORDER_CONTROLLER;
                    break;
                case REQUEST_FOR_REFURN_MY_ORDER:
                    url = REQUEST_FOR_REFURN_MY_ORDER_CONTROLLER;
                    break;
                case MY_ORDER_DETAIL:
                    url = MY_ORDER_DETAIL_CONTROLLER;
                    break;

                //trang Profile-Address
                case MY_ADDRESS:
                    url = MY_ADDRESS_CONTROLLER;
                    break;
                case ADD_NEW_MY_ADDRESS:
                    url = ADD_MY_ADDRESS_CONTROLLER;
                    break;
                case UPDATE_MY_ADDRESS:
                    url = UPDATE_MY_ADDRESS_CONTROLLER;
                    break;
                case DELETE_MY_ADDRESS:
                    url = DELETE_MY_ADDRESS_CONTROLLER;
                    break;

                //trang Admin
                case "welcomeAdmin":
                    url = WELCOME_ADMIN;
                    break;
                case "ingredient":
                    url = INGREDIENT;
                    break;
                case "insert_ingredient":
                    url = INSERT_INGREDIENT;
                    break;
                case "order":
                    url = ORDER;
                    break;
                case "order_in_batches":
                    url = ORDER_IN_BATCHES;
                    break;
                case "update_order":
                    url = UPDATE_ORDER;
                    break;
                case "product":
                    url = PRODUCT;
                    break;
                case "insert_product":
                    url = INSERT_PRODUCT;
                    break;
                case "update_product":
                    url = UPDATE_PRODUCT;
                    break;
                case "update_status_product":
                    url = UPDATE_STATUS_PRODUCT;
                    break;
                case "user":
                    url = USER;
                    break;
                case "insert_user":
                    url = INSERT_USER;
                    break;
                case "update_user":
                    url = UPDATE_USER;
                    break;
                case "update_status_user":
                    url = UPDATE_STATUS_USER;
                    break;
                case "weeklyplan":
                    url = WEEKLYPLAN;
                    break;
                case "insert_weeklyplan":
                    url = INSERT_WEEKLYPLAN;
                    break;
                case "upspw":
                    url = UPDATE_STATUS_PRODUCT_IN_WEEKLYPLAN;
                    break;
                case "usw":
                    url = UPDATE_STATUS_WEEKLYPLAN;
                    break;
                case "update_weeklyplan":
                    url = UPDATE_WEEKLYPLAN;
                    break;
                default:
                    // If action is unknown, redirect to a default or error page
                    url = WELCOME_PAGE;
                    break;
            }
            request.getRequestDispatcher(url).forward(request, response);
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
