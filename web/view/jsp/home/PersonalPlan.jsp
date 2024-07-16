<%-- 
    Document   : plan.jsp
    Created on : Jun 13, 2024, 5:04:05 PM
    Author     : Admin
--%>

<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@page import="dto.Weekly_Plan_Product"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Map"%>
<%@page import="dto.Product_Category"%>
<%@page import="dto.Category"%>
<%@page import="dto.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.Personal_Plan"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/WEB-INF/tlds/mytags.tld" prefix="v" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <link rel="icon" href="view/Assets/Images/logo.png" />

        <!-- Css -->
        <link rel="stylesheet" href="view/Assets/css/style.css" />
        <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css"
            />
        <link rel="stylesheet" href="view/Assets/css/plan.css" />


        <!-- SweetAlert2 -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.12.0/dist/sweetalert2.min.css"/>

        <!-- Fonts -->
        <!-- Outfit -->
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Outfit:wght@100..900&display=swap"
            rel="stylesheet"
            />
        <!-- Roboto -->
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap"
            rel="stylesheet"
            />

        <!-- Logo fonts -->
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
            />

        <title>Plan - Fresh Meal</title>
    </head>
    <body>
        <div class="wrapper">
            <%@include file="../../common/header.jsp" %>

            <section class="hidden">
                <div class="header-text">
                    <h1>Personalize your Plan</h1>
                    <h4>Choose From 100+ weekly menu options</h4>
                </div>
            </section>



            <!-- Hidden box -->
            <div id="planner-container" class="planner-container"> 
                <div class="planner-choose">
                    <div class="planner-header">
                        <div class="logo-planner-header">
                            <img src="view/Assets/Images/logo.png" alt="" />
                        </div>
                        <div class="daypick-header">
                            <h1> ${selectedDate} </h1>                            
                        </div>
                        <div class="control-section">
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="setting-svg">
                            <path stroke-linecap="round" stroke-linejoin="round" d="M9.594 3.94c.09-.542.56-.94 1.11-.94h2.593c.55 0 1.02.398 1.11.94l.213 1.281c.063.374.313.686.645.87.074.04.147.083.22.127.325.196.72.257 1.075.124l1.217-.456a1.125 1.125 0 0 1 1.37.49l1.296 2.247a1.125 1.125 0 0 1-.26 1.431l-1.003.827c-.293.241-.438.613-.43.992a7.723 7.723 0 0 1 0 .255c-.008.378.137.75.43.991l1.004.827c.424.35.534.955.26 1.43l-1.298 2.247a1.125 1.125 0 0 1-1.369.491l-1.217-.456c-.355-.133-.75-.072-1.076.124a6.47 6.47 0 0 1-.22.128c-.331.183-.581.495-.644.869l-.213 1.281c-.09.543-.56.94-1.11.94h-2.594c-.55 0-1.019-.398-1.11-.94l-.213-1.281c-.062-.374-.312-.686-.644-.87a6.52 6.52 0 0 1-.22-.127c-.325-.196-.72-.257-1.076-.124l-1.217.456a1.125 1.125 0 0 1-1.369-.49l-1.297-2.247a1.125 1.125 0 0 1 .26-1.431l1.004-.827c.292-.24.437-.613.43-.991a6.932 6.932 0 0 1 0-.255c.007-.38-.138-.751-.43-.992l-1.004-.827a1.125 1.125 0 0 1-.26-1.43l1.297-2.247a1.125 1.125 0 0 1 1.37-.491l1.216.456c.356.133.751.072 1.076-.124.072-.044.146-.086.22-.128.332-.183.582-.495.644-.869l.214-1.28Z" />
                            <path stroke-linecap="round" stroke-linejoin="round" d="M15 12a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z" />
                            </svg>
                            <a href="DispatchServlet?btAction=Plans">
                                <svg  xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="exit-svg">
                                <path stroke-linecap="round" stroke-linejoin="round" d="m9.75 9.75 4.5 4.5m0-4.5-4.5 4.5M21 12a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z" />
                                </svg>    
                            </a>            
                        </div>

                    </div>
                    <h4 style="color: red">${requestScope.ERROR}</h4>
                    <div class="planner-main-content">
                        <div class="main-contain">
                            <%                                ArrayList<Personal_Plan> listPlan = (ArrayList<Personal_Plan>) request.getAttribute("personalPlans");
                                ArrayList<Product> listProduct = (ArrayList<Product>) request.getAttribute("listProducts");
                                Map<Integer, String> categoryMap = (Map<Integer, String>) request.getAttribute("categoryMap");
                                ArrayList<Integer> listProductID = (ArrayList<Integer>) request.getAttribute("listProductId");
                                ArrayList<Product> listProductItem = (ArrayList<Product>) request.getAttribute("listproductItem");

                                User userLogin = (User) session.getAttribute("account");

                                int countBreakfastPlan = 0;
                                int countLunchPlan = 0;
                                int countBrunchPlan = 0;
                                int countDinnerPlan = 0;

                                boolean showAddIconBreakfast = true;
                                boolean showAddIconLunch = true;
                                boolean showAddIconBrunch = true;
                                boolean showAddIconDinner = true;

                            %>


                            <div class="planner-choose-box">
                                <div class="description-image">
                                    <img
                                        src="view/Assets/Images/food-plan/Default_Morning_food_1.jpg"
                                        alt=""
                                        />
                                    <h3>Breakfast</h3>
                                </div>
                                <div class="plan-menu-contain">


                                    <div class="plan-choose-box">
                                        <% if (listPlan != null) {
                                                Set<Integer> addedProductIDs = new HashSet<>();
                                                for (Personal_Plan personal_Plan : listPlan) {
                                                    if (personal_Plan.getUserID() == userLogin.getUserID()) {

                                                        int mealID = personal_Plan.getMealID();
                                                        if (mealID == 1) {
                                                            ArrayList<Weekly_Plan_Product> products = personal_Plan.getListOfProduct();
                                                            if (products != null && !products.isEmpty()) {
                                                                for (Weekly_Plan_Product weekly_Plan_Product : products) {
                                                                    int productID = weekly_Plan_Product.getProductID();

                                                                    // Kiểm tra xem productID đã được thêm vào danh sách chưa
                                                                    if (!addedProductIDs.contains(productID)) {
                                                                        addedProductIDs.add(productID); // Thêm productID vào Set để đánh dấu đã thêm

                                                                        // Tìm sản phẩm trong listProduct dựa vào productID
                                                                        Product product = null;
                                                                        for (Product p : listProduct) {
                                                                            if (p.getProductID() == productID) {
                                                                                product = p;
                                                                                break;
                                                                            }
                                                                        }

                                                                        // Nếu tìm thấy sản phẩm, hiển thị thông tin
                                                                        if (product != null) {
                                                                            countBreakfastPlan++;
                                                                            if (countBreakfastPlan >= 2) {
                                                                                showAddIconBreakfast = false;
                                                                            }
                                        %>
                                        <div class="plan-control">
                                            <form action="DispatchServlet" method="POST">
                                                <button type="submit" name="btAction" value="MenuInfo" class="menu-info-btn">
                                                    <input type="hidden" name="productId" value="<%= product.getProductID()%>"/>
                                                    <input type="hidden" name="mealType" value="<%= personal_Plan.getType()%>"/>
                                                    <input type="hidden" name="quantity" value="<%= personal_Plan.getQuantity()%>"/>
                                                    <div class="plan-box-container">
                                                        <div class="img-food-plan">
                                                            <img src="<%= product.getImage()%>" alt="">
                                                        </div>
                                                        <div class="content-food-plan">
                                                            <div class="discription-items">
                                                                <h2><%= product.getProductName()%></h2>
                                                                <div class="plan-time-cost">
                                                                    <i class="fa-solid fa-box"></i>
                                                                    <small><%= personal_Plan.getType()%></small>
                                                                </div>
                                                            </div>
                                                            <div class="discription-items">
                                                                <div class="menu-tags-contain">
                                                                    <%
                                                                        if (categoryMap != null) {
                                                                            for (Product_Category category : product.getListOfCategory()) {
                                                                    %>
                                                                    <span class="plan-tags">
                                                                        <i class="fa-solid fa-tag"></i>
                                                                        <small><%= categoryMap.get(category.getCategoryID())%></small>
                                                                    </span>      
                                                                    <%
                                                                            }
                                                                        }
                                                                    %>
                                                                </div>
                                                                <div class="plan-time-cost">
                                                                    <small>Qty: <%= personal_Plan.getQuantity()%></small>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </button>
                                            </form>
                                            <div class="plan-main-control hidden-box">
                                                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="update-plan">
                                                <path stroke-linecap="round" stroke-linejoin="round" d="m16.862 4.487 1.687-1.688a1.875 1.875 0 1 1 2.652 2.652L10.582 16.07a4.5 4.5 0 0 1-1.897 1.13L6 18l.8-2.685a4.5 4.5 0 0 1 1.13-1.897l8.932-8.931Zm0 0L19.5 7.125M18 14v4.75A2.25 2.25 0 0 1 15.75 21H5.25A2.25 2.25 0 0 1 3 18.75V8.25A2.25 2.25 0 0 1 5.25 6H10" />
                                                </svg>
                                                <form class="delete-plan" action="DispatchServlet" method="post">
                                                    <input type="hidden" name="btAction" value="removePersonalPlan"/>
                                                    <input type="hidden" name="id" value="<%= personal_Plan.getPersonal_Plan_ID()%>" />
                                                    <input type="hidden" name="date" value="${selectedDate}>" />
                                                    <button class="deleteBtn" type="submit">
                                                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-6">
                                                        <path stroke-linecap="round" stroke-linejoin="round" d="m14.74 9-.346 9m-4.788 0L9.26 9m9.968-3.21c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 0 1-2.244 2.077H8.084a2.25 2.25 0 0 1-2.244-2.077L4.772 5.79m14.456 0a48.108 48.108 0 0 0-3.478-.397m-12 .562c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 0 1 3.478-.397m7.5 0v-.916c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 0 0-3.32 0c-1.18.037-2.09 1.022-2.09 2.201v.916m7.5 0a48.667 48.667 0 0 0-7.5 0" />
                                                        </svg> 
                                                    </button>
                                                </form>
                                            </div>

                                            <!--update section-->
                                            <aside
                                                class="update-container"
                                                role="dialog"
                                                tabindex="0"
                                                >
                                                <div class="form-plan">
                                                    <div class="form-plan-update-item">
                                                        <div class="form-plan-text">
                                                            Update Personal Plan
                                                        </div>
                                                        <form class="form-update" action="DispatchServlet" method="post">
                                                            <input type="hidden" name="btAction" value="updatePersonalPlan"/>
                                                            <div class="inside-update-form">
                                                                <div class="form-main-content">
                                                                    <div class="plan-control">
                                                                        <div class="plan-update-box-container">
                                                                            <div class="img-food-plan">
                                                                                <img src="<%= product.getImage()%>" alt="">
                                                                            </div>
                                                                            <div class="content-food-plan">
                                                                                <div class="discription-items">
                                                                                    <h2><%= product.getProductName()%></h2>
                                                                                    <div class="plan-time-cost">
                                                                                        <i class="fa-solid fa-box"></i>
                                                                                        <small><%= personal_Plan.getType()%></small>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="discription-items">
                                                                                    <div class="menu-tags-contain">
                                                                                        <%
                                                                                            if (categoryMap != null) {
                                                                                                for (Product_Category category : product.getListOfCategory()) {
                                                                                        %>
                                                                                        <span class="plan-tags">
                                                                                            <i class="fa-solid fa-tag"></i>
                                                                                            <small><%= categoryMap.get(category.getCategoryID())%></small>
                                                                                        </span>      
                                                                                        <%
                                                                                                }
                                                                                            }
                                                                                        %>
                                                                                    </div>
                                                                                    <div class="plan-time-cost">
                                                                                        <small>Qty: <%= personal_Plan.getQuantity()%></small>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="menu-info-selection">
                                                                        <h3>Type</h3>
                                                                        <div class="button-selection">
                                                                            <button class="updateBtn meal-kit" data-type="MealKit" type="button" >
                                                                                Meal Kits
                                                                            </button>
                                                                            <button class="updateBtn prepare-meal" data-type="PrepareMeal" type="button" >Prepared Meals</button>
                                                                        </div>
                                                                        <input type="hidden" value="<%= personal_Plan.getType()%>" name="selectedmealtypeupdate" class="selectedMealTypeUpdate" />
                                                                    </div>

                                                                    <input type="hidden" value="<%= personal_Plan.getPersonal_Plan_ID()%>" name="personalplanid"/>
                                                                    <input type="hidden" value="${selectedDate}" name="selectedDate"/>

                                                                    <div class="menu-info-quantity">
                                                                        <h3>Quantity</h3>
                                                                        <button class="decrement button-quantity">
                                                                            <svg enable-background="new 0 0 10 10" viewBox="0 0 10 10" x="0" y="0" class="svg-icon">
                                                                            <polygon points="4.5 4.5 3.5 4.5 0 4.5 0 5.5 3.5 5.5 4.5 5.5 10 5.5 10 4.5"></polygon>
                                                                            </svg>
                                                                        </button>
                                                                        <input type="text" name="quantitymeal" role="spinbutton" value="<%= personal_Plan.getQuantity()%>" class="button-quantity input-quantity">
                                                                        <button class="increment button-quantity">
                                                                            <svg enable-background="new 0 0 10 10" viewBox="0 0 10 10" x="0" y="0" class="svg-icon icon-plus-sign">
                                                                            <polygon points="10 4.5 5.5 4.5 5.5 0 4.5 0 4.5 4.5 0 4.5 0 5.5 4.5 5.5 4.5 10 5.5 10 5.5 5.5 10 5.5"></polygon>
                                                                            </svg>
                                                                        </button>
                                                                    </div>
                                                                </div>
                                                                <div class="form-button">
                                                                    <button class="button returnUpdateBtn">Return</button>
                                                                    <div class="seperate-box"></div>
                                                                    <button class="button dark-btn saveUpdateBtn">Save</button>
                                                                </div>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                                <div class="background-form"></div>
                                            </aside>
                                        </div>
                                        <%                          } // Đóng if kiểm tra product
                                                                    } // Đóng for-each listProduct
                                                                } // Đóng if kiểm tra productID chưa được thêm
                                                            } // Đóng for-each weekly_Plan_Product
                                                        } // Đóng if kiểm tra products không null và không rỗng
                                                    }
                                                }// Đóng if kiểm tra mealID == 1
                                            } // Đóng for-each listPlan
                                        %>  

                                        <!-- Khi chưa có plan nào hoặc chưa đủ 2 plan thì hiển thị icon add -->
                                        <% if (showAddIconBreakfast) { %>
                                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="add-svg" data-meal="Breakfast" onclick="setMealId(this)">
                                        <path stroke-linecap="round" stroke-linejoin="round" d="M12 4.5v15m7.5-7.5h-15" />
                                        </svg>
                                        <% } %>
                                    </div>

                                </div>
                            </div>




                            <div class="planner-choose-box">
                                <div class="description-image">
                                    <img
                                        src="view/Assets/Images/food-plan/Default_lunch_food_0.jpg"
                                        alt=""
                                        />
                                    <h3>Lunch</h3>
                                </div>
                                <div class="plan-menu-contain">

                                    <div class="plan-choose-box">
                                        <% if (listPlan != null) {
                                                Set<Integer> addedProductIDs = new HashSet<>();
                                                for (Personal_Plan personal_Plan : listPlan) {
                                                    if (personal_Plan.getUserID() == userLogin.getUserID()) {
                                                        int mealID = personal_Plan.getMealID();
                                                        if (mealID == 2) {
                                                            ArrayList<Weekly_Plan_Product> products = personal_Plan.getListOfProduct();
                                                            if (products != null && !products.isEmpty()) {
                                                                for (Weekly_Plan_Product weekly_Plan_Product : products) {
                                                                    int productID = weekly_Plan_Product.getProductID();

                                                                    // Kiểm tra xem productID đã được thêm vào danh sách chưa
                                                                    if (!addedProductIDs.contains(productID)) {
                                                                        addedProductIDs.add(productID); // Thêm productID vào Set để đánh dấu đã thêm

                                                                        // Tìm sản phẩm trong listProduct dựa vào productID
                                                                        Product product = null;
                                                                        for (Product p : listProduct) {
                                                                            if (p.getProductID() == productID) {
                                                                                product = p;
                                                                                break;
                                                                            }
                                                                        }

                                                                        // Nếu tìm thấy sản phẩm, hiển thị thông tin
                                                                        if (product != null) {
                                                                            countLunchPlan++;
                                                                            if (countLunchPlan >= 2) {
                                                                                showAddIconLunch = false;
                                                                            }
                                        %>
                                        <div class="plan-control">
                                            <form action="DispatchServlet" method="POST">
                                                <button type="submit" name="btAction" value="MenuInfo" class="menu-info-btn">
                                                    <input type="hidden" name="productId" value="<%= product.getProductID()%>"/>
                                                    <input type="hidden" name="mealType" value="<%= personal_Plan.getType()%>"/>
                                                    <input type="hidden" name="quantity" value="<%= personal_Plan.getQuantity()%>"/>
                                                    <div class="plan-box-container">
                                                        <div class="img-food-plan">
                                                            <img src="<%= product.getImage()%>" alt="">
                                                        </div>
                                                        <div class="content-food-plan">
                                                            <div class="discription-items">
                                                                <h2><%= product.getProductName()%></h2>
                                                                <div class="plan-time-cost">
                                                                    <i class="fa-solid fa-box"></i>
                                                                    <small><%= personal_Plan.getType()%></small>
                                                                </div>
                                                            </div>
                                                            <div class="discription-items">
                                                                <div class="menu-tags-contain">
                                                                    <%
                                                                        if (categoryMap != null) {
                                                                            for (Product_Category category : product.getListOfCategory()) {
                                                                    %>
                                                                    <span class="plan-tags">
                                                                        <i class="fa-solid fa-tag"></i>
                                                                        <small><%= categoryMap.get(category.getCategoryID())%></small>
                                                                    </span>      
                                                                    <%
                                                                            }
                                                                        }
                                                                    %>
                                                                </div>
                                                                <div class="plan-time-cost">
                                                                    <small>Qty: <%= personal_Plan.getQuantity()%></small>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </button>
                                            </form>
                                            <div class="plan-main-control hidden-box">
                                                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="update-plan">
                                                <path stroke-linecap="round" stroke-linejoin="round" d="m16.862 4.487 1.687-1.688a1.875 1.875 0 1 1 2.652 2.652L10.582 16.07a4.5 4.5 0 0 1-1.897 1.13L6 18l.8-2.685a4.5 4.5 0 0 1 1.13-1.897l8.932-8.931Zm0 0L19.5 7.125M18 14v4.75A2.25 2.25 0 0 1 15.75 21H5.25A2.25 2.25 0 0 1 3 18.75V8.25A2.25 2.25 0 0 1 5.25 6H10" />
                                                </svg>
                                                <form class="delete-plan" action="DispatchServlet" method="post">
                                                    <input type="hidden" name="btAction" value="removePersonalPlan"/>
                                                    <input type="hidden" name="id" value="<%= personal_Plan.getPersonal_Plan_ID()%>" />
                                                    <input type="hidden" name="date" value="${selectedDate}>" />
                                                    <button class="deleteBtn" type="submit">
                                                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-6">
                                                        <path stroke-linecap="round" stroke-linejoin="round" d="m14.74 9-.346 9m-4.788 0L9.26 9m9.968-3.21c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 0 1-2.244 2.077H8.084a2.25 2.25 0 0 1-2.244-2.077L4.772 5.79m14.456 0a48.108 48.108 0 0 0-3.478-.397m-12 .562c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 0 1 3.478-.397m7.5 0v-.916c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 0 0-3.32 0c-1.18.037-2.09 1.022-2.09 2.201v.916m7.5 0a48.667 48.667 0 0 0-7.5 0" />
                                                        </svg> 
                                                    </button>
                                                </form>
                                            </div>

                                            <!--update section-->
                                            <aside
                                                class="update-container"
                                                role="dialog"
                                                tabindex="0"
                                                >
                                                <div class="form-plan">
                                                    <div class="form-plan-update-item">
                                                        <div class="form-plan-text">
                                                            Update Personal Plan
                                                        </div>
                                                        <form class="form-update" action="DispatchServlet" method="post">
                                                            <input type="hidden" name="btAction" value="updatePersonalPlan"/>
                                                            <div class="inside-update-form">
                                                                <div class="form-main-content">
                                                                    <div class="plan-control">
                                                                        <div class="plan-update-box-container">
                                                                            <div class="img-food-plan">
                                                                                <img src="<%= product.getImage()%>" alt="">
                                                                            </div>
                                                                            <div class="content-food-plan">
                                                                                <div class="discription-items">
                                                                                    <h2><%= product.getProductName()%></h2>
                                                                                    <div class="plan-time-cost">
                                                                                        <i class="fa-solid fa-box"></i>
                                                                                        <small><%= personal_Plan.getType()%></small>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="discription-items">
                                                                                    <div class="menu-tags-contain">
                                                                                        <%
                                                                                            if (categoryMap != null) {
                                                                                                for (Product_Category category : product.getListOfCategory()) {
                                                                                        %>
                                                                                        <span class="plan-tags">
                                                                                            <i class="fa-solid fa-tag"></i>
                                                                                            <small><%= categoryMap.get(category.getCategoryID())%></small>
                                                                                        </span>      
                                                                                        <%
                                                                                                }
                                                                                            }
                                                                                        %>
                                                                                    </div>
                                                                                    <div class="plan-time-cost">
                                                                                        <small>Qty: <%= personal_Plan.getQuantity()%></small>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="menu-info-selection">
                                                                        <h3>Type</h3>
                                                                        <div class="button-selection">
                                                                            <button class="updateBtn meal-kit" data-type="MealKit" type="button" >
                                                                                Meal Kits
                                                                            </button>
                                                                            <button class="updateBtn prepare-meal" data-type="PrepareMeal" type="button" >Prepared Meals</button>
                                                                        </div>
                                                                        <input type="hidden" value="<%= personal_Plan.getType()%>" name="selectedmealtypeupdate" class="selectedMealTypeUpdate" />
                                                                    </div>

                                                                    <input type="hidden" value="<%= personal_Plan.getPersonal_Plan_ID()%>" name="personalplanid"/>
                                                                    <input type="hidden" value="${selectedDate}" name="selectedDate"/>

                                                                    <div class="menu-info-quantity">
                                                                        <h3>Quantity</h3>
                                                                        <button class="decrement button-quantity">
                                                                            <svg enable-background="new 0 0 10 10" viewBox="0 0 10 10" x="0" y="0" class="svg-icon">
                                                                            <polygon points="4.5 4.5 3.5 4.5 0 4.5 0 5.5 3.5 5.5 4.5 5.5 10 5.5 10 4.5"></polygon>
                                                                            </svg>
                                                                        </button>
                                                                        <input type="text" name="quantitymeal" role="spinbutton" value="<%= personal_Plan.getQuantity()%>" class="button-quantity input-quantity">
                                                                        <button class="increment button-quantity">
                                                                            <svg enable-background="new 0 0 10 10" viewBox="0 0 10 10" x="0" y="0" class="svg-icon icon-plus-sign">
                                                                            <polygon points="10 4.5 5.5 4.5 5.5 0 4.5 0 4.5 4.5 0 4.5 0 5.5 4.5 5.5 4.5 10 5.5 10 5.5 5.5 10 5.5"></polygon>
                                                                            </svg>
                                                                        </button>
                                                                    </div>
                                                                </div>
                                                                <div class="form-button">
                                                                    <button class="button returnUpdateBtn">Return</button>
                                                                    <div class="seperate-box"></div>
                                                                    <button class="button dark-btn saveUpdateBtn">Save</button>
                                                                </div>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                                <div class="background-form"></div>
                                            </aside>
                                        </div>
                                        <%                          } // Đóng if kiểm tra product
                                                                    } // Đóng for-each listProduct
                                                                } // Đóng if kiểm tra productID chưa được thêm
                                                            } // Đóng for-each weekly_Plan_Product
                                                        } // Đóng if kiểm tra products không null và không rỗng
                                                    }
                                                }// Đóng if kiểm tra mealID == 2
                                            } // Đóng for-each listPlan
                                        %>  

                                        <!-- Khi chưa có plan nào hoặc chưa đủ 2 plan thì hiển thị icon add -->
                                        <% if (showAddIconLunch) { %>
                                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="add-svg" data-meal="Lunch" onclick="setMealId(this)">
                                        <path stroke-linecap="round" stroke-linejoin="round" d="M12 4.5v15m7.5-7.5h-15" />
                                        </svg>
                                        <% } %>
                                    </div>

                                </div>
                            </div>




                            <div class="planner-choose-box">
                                <div class="description-image">
                                    <img
                                        src="view/Assets/Images/food-plan/Default_brunch_0.jpg"
                                        alt=""
                                        />
                                    <h3>Brunch</h3>
                                </div>
                                <div class="plan-menu-contain">
                                    <div class="plan-choose-box">
                                        <% if (listPlan != null) {
                                                Set<Integer> addedProductIDs = new HashSet<>();
                                                for (Personal_Plan personal_Plan : listPlan) {
                                                    if (personal_Plan.getUserID() == userLogin.getUserID()) {
                                                        int mealID = personal_Plan.getMealID();
                                                        if (mealID == 3) {
                                                            ArrayList<Weekly_Plan_Product> products = personal_Plan.getListOfProduct();
                                                            if (products != null && !products.isEmpty()) {
                                                                for (Weekly_Plan_Product weekly_Plan_Product : products) {
                                                                    int productID = weekly_Plan_Product.getProductID();

                                                                    // Kiểm tra xem productID đã được thêm vào danh sách chưa
                                                                    if (!addedProductIDs.contains(productID)) {
                                                                        addedProductIDs.add(productID); // Thêm productID vào Set để đánh dấu đã thêm

                                                                        // Tìm sản phẩm trong listProduct dựa vào productID
                                                                        Product product = null;
                                                                        for (Product p : listProduct) {
                                                                            if (p.getProductID() == productID) {
                                                                                product = p;
                                                                                break;
                                                                            }
                                                                        }

                                                                        // Nếu tìm thấy sản phẩm, hiển thị thông tin
                                                                        if (product != null) {
                                                                            countBrunchPlan++;
                                                                            if (countBrunchPlan >= 2) {
                                                                                showAddIconBrunch = false;
                                                                            }
                                        %>
                                        <div class="plan-control">
                                            <form action="DispatchServlet" method="POST">
                                                <button type="submit" name="btAction" value="MenuInfo" class="menu-info-btn">
                                                    <input type="hidden" name="productId" value="<%= product.getProductID()%>"/>
                                                    <input type="hidden" name="mealType" value="<%= personal_Plan.getType()%>"/>
                                                    <input type="hidden" name="quantity" value="<%= personal_Plan.getQuantity()%>"/>
                                                    <div class="plan-box-container">
                                                        <div class="img-food-plan">
                                                            <img src="<%= product.getImage()%>" alt="">
                                                        </div>
                                                        <div class="content-food-plan">
                                                            <div class="discription-items">
                                                                <h2><%= product.getProductName()%></h2>
                                                                <div class="plan-time-cost">
                                                                    <i class="fa-solid fa-box"></i>
                                                                    <small><%= personal_Plan.getType()%></small>
                                                                </div>
                                                            </div>
                                                            <div class="discription-items">
                                                                <div class="menu-tags-contain">
                                                                    <%
                                                                        if (categoryMap != null) {
                                                                            for (Product_Category category : product.getListOfCategory()) {
                                                                    %>
                                                                    <span class="plan-tags">
                                                                        <i class="fa-solid fa-tag"></i>
                                                                        <small><%= categoryMap.get(category.getCategoryID())%></small>
                                                                    </span>      
                                                                    <%
                                                                            }
                                                                        }
                                                                    %>
                                                                </div>
                                                                <div class="plan-time-cost">
                                                                    <small>Qty: <%= personal_Plan.getQuantity()%></small>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </button>
                                            </form>
                                            <div class="plan-main-control hidden-box">
                                                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="update-plan">
                                                <path stroke-linecap="round" stroke-linejoin="round" d="m16.862 4.487 1.687-1.688a1.875 1.875 0 1 1 2.652 2.652L10.582 16.07a4.5 4.5 0 0 1-1.897 1.13L6 18l.8-2.685a4.5 4.5 0 0 1 1.13-1.897l8.932-8.931Zm0 0L19.5 7.125M18 14v4.75A2.25 2.25 0 0 1 15.75 21H5.25A2.25 2.25 0 0 1 3 18.75V8.25A2.25 2.25 0 0 1 5.25 6H10" />
                                                </svg>
                                                <form class="delete-plan" action="DispatchServlet" method="post">
                                                    <input type="hidden" name="btAction" value="removePersonalPlan"/>
                                                    <input type="hidden" name="id" value="<%= personal_Plan.getPersonal_Plan_ID()%>" />
                                                    <input type="hidden" name="date" value="${selectedDate}>" />
                                                    <button class="deleteBtn" type="submit">
                                                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-6">
                                                        <path stroke-linecap="round" stroke-linejoin="round" d="m14.74 9-.346 9m-4.788 0L9.26 9m9.968-3.21c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 0 1-2.244 2.077H8.084a2.25 2.25 0 0 1-2.244-2.077L4.772 5.79m14.456 0a48.108 48.108 0 0 0-3.478-.397m-12 .562c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 0 1 3.478-.397m7.5 0v-.916c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 0 0-3.32 0c-1.18.037-2.09 1.022-2.09 2.201v.916m7.5 0a48.667 48.667 0 0 0-7.5 0" />
                                                        </svg> 
                                                    </button> 
                                                </form>
                                            </div>

                                            <!--update section-->
                                            <aside
                                                class="update-container"
                                                role="dialog"
                                                tabindex="0"
                                                >
                                                <div class="form-plan">
                                                    <div class="form-plan-update-item">
                                                        <div class="form-plan-text">
                                                            Update Personal Plan
                                                        </div>
                                                        <form class="form-update" action="DispatchServlet" method="post">
                                                            <input type="hidden" name="btAction" value="updatePersonalPlan"/>
                                                            <div class="inside-update-form">
                                                                <div class="form-main-content">
                                                                    <div class="plan-control">
                                                                        <div class="plan-update-box-container">
                                                                            <div class="img-food-plan">
                                                                                <img src="<%= product.getImage()%>" alt="">
                                                                            </div>
                                                                            <div class="content-food-plan">
                                                                                <div class="discription-items">
                                                                                    <h2><%= product.getProductName()%></h2>
                                                                                    <div class="plan-time-cost">
                                                                                        <i class="fa-solid fa-box"></i>
                                                                                        <small><%= personal_Plan.getType()%></small>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="discription-items">
                                                                                    <div class="menu-tags-contain">
                                                                                        <%
                                                                                            if (categoryMap != null) {
                                                                                                for (Product_Category category : product.getListOfCategory()) {
                                                                                        %>
                                                                                        <span class="plan-tags">
                                                                                            <i class="fa-solid fa-tag"></i>
                                                                                            <small><%= categoryMap.get(category.getCategoryID())%></small>
                                                                                        </span>      
                                                                                        <%
                                                                                                }
                                                                                            }
                                                                                        %>
                                                                                    </div>
                                                                                    <div class="plan-time-cost">
                                                                                        <small>Qty: <%= personal_Plan.getQuantity()%></small>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="menu-info-selection">
                                                                        <h3>Type</h3>
                                                                        <div class="button-selection">
                                                                            <button class="updateBtn meal-kit" data-type="MealKit" type="button" >
                                                                                Meal Kits
                                                                            </button>
                                                                            <button class="updateBtn prepare-meal" data-type="PrepareMeal" type="button" >Prepared Meals</button>
                                                                        </div>
                                                                        <input type="hidden" value="<%= personal_Plan.getType()%>" name="selectedmealtypeupdate" class="selectedMealTypeUpdate" />
                                                                    </div>

                                                                    <input type="hidden" value="<%= personal_Plan.getPersonal_Plan_ID()%>" name="personalplanid"/>
                                                                    <input type="hidden" value="${selectedDate}" name="selectedDate"/>

                                                                    <div class="menu-info-quantity">
                                                                        <h3>Quantity</h3>
                                                                        <button class="decrement button-quantity">
                                                                            <svg enable-background="new 0 0 10 10" viewBox="0 0 10 10" x="0" y="0" class="svg-icon">
                                                                            <polygon points="4.5 4.5 3.5 4.5 0 4.5 0 5.5 3.5 5.5 4.5 5.5 10 5.5 10 4.5"></polygon>
                                                                            </svg>
                                                                        </button>
                                                                        <input type="text" name="quantitymeal" role="spinbutton" value="<%= personal_Plan.getQuantity()%>" class="button-quantity input-quantity">
                                                                        <button class="increment button-quantity">
                                                                            <svg enable-background="new 0 0 10 10" viewBox="0 0 10 10" x="0" y="0" class="svg-icon icon-plus-sign">
                                                                            <polygon points="10 4.5 5.5 4.5 5.5 0 4.5 0 4.5 4.5 0 4.5 0 5.5 4.5 5.5 4.5 10 5.5 10 5.5 5.5 10 5.5"></polygon>
                                                                            </svg>
                                                                        </button>
                                                                    </div>
                                                                </div>
                                                                <div class="form-button">
                                                                    <button class="button returnUpdateBtn">Return</button>
                                                                    <div class="seperate-box"></div>
                                                                    <button class="button dark-btn saveUpdateBtn">Save</button>
                                                                </div>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                                <div class="background-form"></div>
                                            </aside>
                                        </div>
                                        <%                          } // Đóng if kiểm tra product
                                                                    } // Đóng for-each listProduct
                                                                } // Đóng if kiểm tra productID chưa được thêm
                                                            } // Đóng for-each weekly_Plan_Product
                                                        } // Đóng if kiểm tra products không null và không rỗng
                                                    }
                                                }// Đóng if kiểm tra mealID == 1
                                            } // Đóng for-each listPlan
                                        %>  

                                        <!-- Khi chưa có plan nào hoặc chưa đủ 2 plan thì hiển thị icon add -->
                                        <% if (showAddIconBrunch) { %>
                                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="add-svg" data-meal="Brunch" onclick="setMealId(this)">
                                        <path stroke-linecap="round" stroke-linejoin="round" d="M12 4.5v15m7.5-7.5h-15" />
                                        </svg>
                                        <% } %>
                                    </div>
                                </div>
                            </div>




                            <div class="planner-choose-box">
                                <div class="description-image">
                                    <img
                                        src="view/Assets/Images/food-plan/Default_dinner_1.jpg"
                                        alt=""
                                        />
                                    <h3>Dinner</h3>
                                </div>
                                <div class="plan-menu-contain">
                                    <div class="plan-choose-box">
                                        <% if (listPlan != null) {
                                                Set<Integer> addedProductIDs = new HashSet<>();
                                                for (Personal_Plan personal_Plan : listPlan) {
                                                    if (personal_Plan.getUserID() == userLogin.getUserID()) {
                                                        int mealID = personal_Plan.getMealID();
                                                        if (mealID == 4) {
                                                            ArrayList<Weekly_Plan_Product> products = personal_Plan.getListOfProduct();
                                                            if (products != null && !products.isEmpty()) {
                                                                for (Weekly_Plan_Product weekly_Plan_Product : products) {
                                                                    int productID = weekly_Plan_Product.getProductID();

                                                                    // Kiểm tra xem productID đã được thêm vào danh sách chưa
                                                                    if (!addedProductIDs.contains(productID)) {
                                                                        addedProductIDs.add(productID); // Thêm productID vào Set để đánh dấu đã thêm

                                                                        // Tìm sản phẩm trong listProduct dựa vào productID
                                                                        Product product = null;
                                                                        for (Product p : listProduct) {
                                                                            if (p.getProductID() == productID) {
                                                                                product = p;
                                                                                break;
                                                                            }
                                                                        }

                                                                        // Nếu tìm thấy sản phẩm, hiển thị thông tin
                                                                        if (product != null) {
                                                                            countDinnerPlan++;
                                                                            if (countDinnerPlan >= 2) {
                                                                                showAddIconDinner = false;
                                                                            }
                                        %>
                                        <div class="plan-control">
                                            <form action="DispatchServlet" method="POST">
                                                <button type="submit" name="btAction" value="MenuInfo" class="menu-info-btn">
                                                    <input type="hidden" name="productId" value="<%= product.getProductID()%>"/>
                                                    <input type="hidden" name="mealType" value="<%= personal_Plan.getType()%>"/>
                                                    <input type="hidden" name="quantity" value="<%= personal_Plan.getQuantity()%>"/>
                                                    <div class="plan-box-container">
                                                        <div class="img-food-plan">
                                                            <img src="<%= product.getImage()%>" alt="">
                                                        </div>
                                                        <div class="content-food-plan">
                                                            <div class="discription-items">
                                                                <h2><%= product.getProductName()%></h2>
                                                                <div class="plan-time-cost">
                                                                    <i class="fa-solid fa-box"></i>
                                                                    <small><%= personal_Plan.getType()%></small>
                                                                </div>
                                                            </div>
                                                            <div class="discription-items">
                                                                <div class="menu-tags-contain">
                                                                    <%
                                                                        if (categoryMap != null) {
                                                                            for (Product_Category category : product.getListOfCategory()) {
                                                                    %>
                                                                    <span class="plan-tags">
                                                                        <i class="fa-solid fa-tag"></i>
                                                                        <small><%= categoryMap.get(category.getCategoryID())%></small>
                                                                    </span>      
                                                                    <%
                                                                            }
                                                                        }
                                                                    %>
                                                                </div>
                                                                <div class="plan-time-cost">
                                                                    <small>Qty: <%= personal_Plan.getQuantity()%></small>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </button>
                                            </form>
                                            <div class="plan-main-control hidden-box">
                                                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="update-plan">
                                                <path stroke-linecap="round" stroke-linejoin="round" d="m16.862 4.487 1.687-1.688a1.875 1.875 0 1 1 2.652 2.652L10.582 16.07a4.5 4.5 0 0 1-1.897 1.13L6 18l.8-2.685a4.5 4.5 0 0 1 1.13-1.897l8.932-8.931Zm0 0L19.5 7.125M18 14v4.75A2.25 2.25 0 0 1 15.75 21H5.25A2.25 2.25 0 0 1 3 18.75V8.25A2.25 2.25 0 0 1 5.25 6H10" />
                                                </svg>
                                                <form class="delete-plan" action="DispatchServlet" method="post">
                                                    <input type="hidden" name="btAction" value="removePersonalPlan"/>
                                                    <input type="hidden" name="id" value="<%= personal_Plan.getPersonal_Plan_ID()%>" />
                                                    <input type="hidden" name="date" value="${selectedDate}>" />
                                                    <button class="deleteBtn" type="submit">
                                                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-6">
                                                        <path stroke-linecap="round" stroke-linejoin="round" d="m14.74 9-.346 9m-4.788 0L9.26 9m9.968-3.21c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 0 1-2.244 2.077H8.084a2.25 2.25 0 0 1-2.244-2.077L4.772 5.79m14.456 0a48.108 48.108 0 0 0-3.478-.397m-12 .562c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 0 1 3.478-.397m7.5 0v-.916c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 0 0-3.32 0c-1.18.037-2.09 1.022-2.09 2.201v.916m7.5 0a48.667 48.667 0 0 0-7.5 0" />
                                                        </svg> 
                                                    </button>
                                                </form>
                                            </div>

                                            <!--update section-->
                                            <aside
                                                class="update-container"
                                                role="dialog"
                                                tabindex="0"
                                                >
                                                <div class="form-plan">
                                                    <div class="form-plan-update-item">
                                                        <div class="form-plan-text">
                                                            Update Personal Plan
                                                        </div>
                                                        <form class="form-update" action="DispatchServlet" method="post">
                                                            <input type="hidden" name="btAction" value="updatePersonalPlan"/>
                                                            <div class="inside-update-form">
                                                                <div class="form-main-content">
                                                                    <div class="plan-control">
                                                                        <div class="plan-update-box-container">
                                                                            <div class="img-food-plan">
                                                                                <img src="<%= product.getImage()%>" alt="">
                                                                            </div>
                                                                            <div class="content-food-plan">
                                                                                <div class="discription-items">
                                                                                    <h2><%= product.getProductName()%></h2>
                                                                                    <div class="plan-time-cost">
                                                                                        <i class="fa-solid fa-box"></i>
                                                                                        <small><%= personal_Plan.getType()%></small>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="discription-items">
                                                                                    <div class="menu-tags-contain">
                                                                                        <%
                                                                                            if (categoryMap != null) {
                                                                                                for (Product_Category category : product.getListOfCategory()) {
                                                                                        %>
                                                                                        <span class="plan-tags">
                                                                                            <i class="fa-solid fa-tag"></i>
                                                                                            <small><%= categoryMap.get(category.getCategoryID())%></small>
                                                                                        </span>      
                                                                                        <%
                                                                                                }
                                                                                            }
                                                                                        %>
                                                                                    </div>
                                                                                    <div class="plan-time-cost">
                                                                                        <small>Qty: <%= personal_Plan.getQuantity()%></small>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="menu-info-selection">
                                                                        <h3>Type</h3>
                                                                        <div class="button-selection">
                                                                            <button class="updateBtn meal-kit" data-type="MealKit" type="button" >
                                                                                Meal Kits
                                                                            </button>
                                                                            <button class="updateBtn prepare-meal" data-type="PrepareMeal" type="button" >Prepared Meals</button>
                                                                        </div>
                                                                        <input type="hidden" value="<%= personal_Plan.getType()%>" name="selectedmealtypeupdate" class="selectedMealTypeUpdate" />
                                                                    </div>

                                                                    <input type="hidden" value="<%= personal_Plan.getPersonal_Plan_ID()%>" name="personalplanid"/>
                                                                    <input type="hidden" value="${selectedDate}" name="selectedDate"/>

                                                                    <div class="menu-info-quantity">
                                                                        <h3>Quantity</h3>
                                                                        <button class="decrement button-quantity">
                                                                            <svg enable-background="new 0 0 10 10" viewBox="0 0 10 10" x="0" y="0" class="svg-icon">
                                                                            <polygon points="4.5 4.5 3.5 4.5 0 4.5 0 5.5 3.5 5.5 4.5 5.5 10 5.5 10 4.5"></polygon>
                                                                            </svg>
                                                                        </button>
                                                                        <input type="text" name="quantitymeal" role="spinbutton" value="<%= personal_Plan.getQuantity()%>" class="button-quantity input-quantity">
                                                                        <button class="increment button-quantity">
                                                                            <svg enable-background="new 0 0 10 10" viewBox="0 0 10 10" x="0" y="0" class="svg-icon icon-plus-sign">
                                                                            <polygon points="10 4.5 5.5 4.5 5.5 0 4.5 0 4.5 4.5 0 4.5 0 5.5 4.5 5.5 4.5 10 5.5 10 5.5 5.5 10 5.5"></polygon>
                                                                            </svg>
                                                                        </button>
                                                                    </div>
                                                                </div>
                                                                <div class="form-button">
                                                                    <button class="button returnUpdateBtn">Return</button>
                                                                    <div class="seperate-box"></div>
                                                                    <button class="button dark-btn saveUpdateBtn">Save</button>
                                                                </div>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                                <div class="background-form"></div>
                                            </aside>

                                        </div>
                                        <%                          } // Đóng if kiểm tra product
                                                                    } // Đóng for-each listProduct
                                                                } // Đóng if kiểm tra productID chưa được thêm
                                                            } // Đóng for-each weekly_Plan_Product
                                                        } // Đóng if kiểm tra products không null và không rỗng
                                                    }
                                                }// Đóng if kiểm tra mealID == 1
                                            } // Đóng for-each listPlan
                                        %>  

                                        <!-- Khi chưa có plan nào hoặc chưa đủ 2 plan thì hiển thị icon add -->
                                        <% if (showAddIconDinner) { %>
                                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="add-svg" data-meal="Dinner" onclick="setMealId(this)">
                                        <path stroke-linecap="round" stroke-linejoin="round" d="M12 4.5v15m7.5-7.5h-15" />
                                        </svg>
                                        <% } %>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <aside id="planForm" class="form-container" role="dialog" tabindex="0" >
                <div class="form-plan">
                    <div class="form-plan-item">
                        <div class="form-plan-text">Add Personal Plan - <span id="plan-type-text" style="margin-left: 5px; color: #4b9e13;"></span></div>
                        <form id="form-create" action="DispatchServlet" method="post">
                            <input type="hidden" name="btAction" value="createPersonalPlan"/>
                            <div class="inside-form">
                                <div class="form-main-content">                
                                    <div class="swiper food-slider">
                                        <div class="swiper-wrapper">
                                            <%
                                                if (listProductID != null) {
                                                    for (Product productPick : listProductItem) {
                                            %>
                                            <div class="swiper-slide plan-box">
                                                <input type="radio" id="product-<%= productPick.getProductID()%>" name="selectedProduct" value="<%= productPick.getProductID()%>" class="hidden-radio"/>   
                                                <label for="product-<%= productPick.getProductID()%>">
                                                    <img src="<%= productPick.getImage()%>" alt="" />
                                                    <div class="plan-text">
                                                        <div class="menu-tags-contain">
                                                            <% if (categoryMap != null) {
                                                                    for (Product_Category category : productPick.getListOfCategory()) {
                                                            %>
                                                            <span class="plan-tags">
                                                                <i class="fa-solid fa-tag"></i>
                                                                <small><%= categoryMap.get(category.getCategoryID())%></small>
                                                            </span>      
                                                            <%
                                                                    }
                                                                }
                                                            %>
                                                        </div>
                                                        <h2><%= productPick.getProductName()%></h2>
                                                        <p>
                                                            <%= productPick.getDescription()%>
                                                        </p>
                                                    </div>
                                                    <div class="plan-min-price">
                                                        <div class="plan-min">
                                                            <i class="fa-regular fa-clock plan-min-icon"></i>
                                                            <small> <%= productPick.getCookingTime()%> Min</small>
                                                        </div>
                                                        <small class="plan-price"><v:Currency_Format  price="<%= productPick.getPrice()%>"/>đ</small>
                                                    </div>
                                                    <div class="overlay-plan">
                                                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-6">
                                                        <path stroke-linecap="round" stroke-linejoin="round" d="M9 12.75 11.25 15 15 9.75M21 12a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z" />
                                                        </svg>                            
                                                    </div>
                                                </label>
                                            </div>
                                            <%
                                                    }
                                                }
                                            %>


                                        </div>
                                        <div class="swiper-button-next"></div>
                                        <div class="swiper-button-prev"></div>
                                    </div>

                                    <input type="hidden" name="selectedDate" value="${selectedDate}"/>
                                    <input type="hidden" name="selectedMeal" value="" id="mealIdInput"/>

                                    <div class="menu-info-selection">
                                        <h3>Type</h3>
                                        <div class="button-selection">
                                            <button class="meal-kit" type="button" onclick="setMealType('MealKit')">
                                                Meal Kits
                                            </button>
                                            <button class="prepare-meal" type="button" onclick="setMealType('PrepareMeal')">Prepared Meals</button>
                                        </div>
                                        <input type="hidden" value="" name="selectedmealtype" id="selectedMealType" />
                                    </div>

                                    <div class="menu-info-quantity">
                                        <h3>Quantity</h3>
                                        <button class="decrement button-quantity">
                                            <svg enable-background="new 0 0 10 10" viewBox="0 0 10 10" x="0" y="0" class="svg-icon">
                                            <polygon points="4.5 4.5 3.5 4.5 0 4.5 0 5.5 3.5 5.5 4.5 5.5 10 5.5 10 4.5"></polygon>
                                            </svg>
                                        </button>
                                        <input type="text" name="quantitymeal" role="spinbutton" value="1" class="button-quantity input-quantity">
                                        <button class="increment button-quantity">
                                            <svg enable-background="new 0 0 10 10" viewBox="0 0 10 10" x="0" y="0" class="svg-icon icon-plus-sign">
                                            <polygon points="10 4.5 5.5 4.5 5.5 0 4.5 0 4.5 4.5 0 4.5 0 5.5 4.5 5.5 4.5 10 5.5 10 5.5 5.5 10 5.5"></polygon>
                                            </svg>
                                        </button>
                                    </div>

                                    <input type="hidden" name="userLogin" value="<%= userLogin.getUserID()%>"/>
                                </div>
                                <div class="form-button">
                                    <button id="returnBtn" class="button">Return</button>
                                    <div class="seperate-box"></div>
                                    <button id="saveBtn" type="submit" class="button dark-btn">Save
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="background-form"></div>
            </aside>




            <section class="hidden">
                <div class="back-content">
                    <div class="back-content-header">
                        <h1>Enjoy 100+ options each week</h1>
                        <p>for breakfast, lunch, dinner and more.</p>
                    </div>

                    <div class="swiper food-slider">
                        <div class="swiper-wrapper">
                            <div class="swiper-slide box">
                                <div class="img-back-content">
                                    <img src="view/Assets/Images/food/food11.jpg" alt="" />
                                    <div class="overlay"></div>
                                    <div class="text-back-content">
                                        <h1>Rice</h1>
                                        <p>our most popular plan</p>
                                    </div>
                                </div>
                            </div>

                            <div class="swiper-slide box">
                                <div class="img-back-content">
                                    <img src="view/Assets/Images/food/food8.jpg" alt="" />
                                    <div class="overlay"></div>
                                    <div class="text-back-content">
                                        <h1>Noodle</h1>
                                        <p>delight in every slurp</p>
                                    </div>
                                </div>
                            </div>

                            <div class="swiper-slide box">
                                <div class="img-back-content">
                                    <img src="view/Assets/Images/food/food13.jpg" alt="" />
                                    <div class="overlay"></div>
                                    <div class="text-back-content">
                                        <h1>Vietnamese Cake</h1>
                                        <p>sweet traditions in every bite</p>
                                    </div>
                                </div>
                            </div>

                            <div class="swiper-slide box">
                                <div class="img-back-content">
                                    <img src="view/Assets/Images/food/food9.jpg" alt="" />
                                    <div class="overlay"></div>
                                    <div class="text-back-content">
                                        <h1>Rolls</h1>
                                        <p>wrapped in freshness</p>
                                    </div>
                                </div>
                            </div>

                            <div class="swiper-slide box">
                                <div class="img-back-content">
                                    <img src="view/Assets/Images/food/food14.jpg" alt="" />
                                    <div class="overlay"></div>
                                    <div class="text-back-content">
                                        <h1>Veggie</h1>
                                        <p>green and glorious goodness</p>
                                    </div>
                                </div>
                            </div>

                            <div class="swiper-slide box">
                                <div class="img-back-content">
                                    <img src="view/Assets/Images/food/food15.jpg" alt="" />
                                    <div class="overlay"></div>
                                    <div class="text-back-content">
                                        <h1>Foreign Food</h1>
                                        <p>flavors from around the world</p>
                                    </div>
                                </div>
                            </div>

                            <div class="swiper-slide box">
                                <div class="img-back-content">
                                    <img src="view/Assets/Images/food/food3.jpg" alt="" />
                                    <div class="overlay"></div>
                                    <div class="text-back-content">
                                        <h1>Hotpot</h1>
                                        <p>a communal culinary adventure</p>
                                    </div>
                                </div>
                            </div>

                            <div class="swiper-slide box">
                                <div class="img-back-content">
                                    <img src="view/Assets/Images/food/food16.jpg" alt="" />
                                    <div class="overlay"></div>
                                    <div class="text-back-content">
                                        <h1>Deserts</h1>
                                        <p>sweet endings to savor</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="swiper-button-next"></div>
                        <div class="swiper-button-prev"></div>
                    </div>
                </div>
            </section>

            <%@include file="../../common/footer.jsp" %>
        </div>

        <!-- Sweatalert2 -->
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.12.0/dist/sweetalert2.all.min.js"></script>

        <!-- Swiper -->
        <script src="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.js"></script>

        <!-- Main -->
        <script src="view/Assets/Js/swiper-api.js"></script>
        <script src="view/Assets/Js/index.js"></script>
        <script src="view/Assets/Js/plan.js"></script>
    </body>
</html>