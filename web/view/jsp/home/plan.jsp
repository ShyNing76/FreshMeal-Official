<%-- 
    Document   : plan.jsp
    Created on : Jun 13, 2024, 5:04:05 PM
    Author     : Admin
--%>

<%@page import="dto.Weekly_Plan_Product"%>
<%@page import="java.util.List"%>
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


<%
    String[] monthNames = {"January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"};

    List<Integer> days = (List<Integer>) request.getAttribute("days");
    int currentMonth = (int) request.getAttribute("currentMonth");
    int currentYear = (int) request.getAttribute("currentYear");
    int totalDays = (int) request.getAttribute("totalDays");
    int firstDayOfWeek = (int) request.getAttribute("firstDayOfWeek");
    int today = (int) request.getAttribute("today");
    Map<String, List<Personal_Plan>> plansByDate = (Map<String, List<Personal_Plan>>) request.getAttribute("plansByDate");
    Map<Integer, String> mealTypeMap = (Map<Integer, String>) request.getAttribute("MealTypeMap");
    Map<Integer, Product> productMap = (Map<Integer, Product>) request.getAttribute("ProductMap");

    User userLogin = (User) session.getAttribute("account");

%>

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

            <section class="hidden">
                <div id="planner-calendar" class="planner">
                    <div class="calendar-nav">
                        <h2 id="month-year"><%= monthNames[currentMonth]%> <%= currentYear%></h2>
                        <div id="calendar-controls">
                            <a href="DispatchServlet?btAction=Plans&month=<%= currentMonth - 1%>&year=<%= currentYear%>"><i class="fa-solid fa-backward control-backward"> </i></a>
                            <div class="calendar-text" id="calendar-text"><%= monthNames[currentMonth]%></div>
                            <a href="DispatchServlet?btAction=Plans&month=<%= currentMonth + 1%>&year=<%= currentYear%>"><i class="fa-solid fa-forward control-forward"> </i></a>
                        </div>
                        <div class="calendar-plan-text">
                            <h4>Personal Plans</h4>
                        </div>
                    </div>

                    <div class="calendar-contain">
                        <div class="weekdays">
                            <div class="weekday">Sun</div>
                            <div class="weekday">Mon</div>
                            <div class="weekday">Tue</div>
                            <div class="weekday">Wed</div>
                            <div class="weekday">Thu</div>
                            <div class="weekday">Fri</div>
                            <div class="weekday">Sat</div>
                        </div>

                        <div id="calendar" class="calendar-grid">
                            <%
                                for (int i = 0; i < days.size(); i++) {
                                    int day = days.get(i);
                                    String dateStr = String.format("%d-%02d-%02d", currentYear, currentMonth + 1, day);
                                    boolean isToday = (day == today && currentMonth == Calendar.getInstance().get(Calendar.MONTH) && currentYear == Calendar.getInstance().get(Calendar.YEAR));

                                    if (i < firstDayOfWeek) {
                            %>
                            <div class="calendar-day prev-month"><%= day%></div>
                            <%
                            } else if (i >= (firstDayOfWeek + totalDays)) {
                            %>
                            <div class="calendar-day next-month"><%= day%></div>
                            <%
                            } else {
                            %>
                            <a class="calendar-day " href="DispatchServlet?btAction=personalPlan&selectedDate=<%= dateStr%>">
                                <div>
                                    <span class="<%= isToday ? "today" : ""%>"><%= day%></span>
                                    <%
                                        if (plansByDate != null && plansByDate.containsKey(dateStr)) {
                                    %>
                                    <div class="plan-event-main">
                                        <%
                                            for (Personal_Plan plan : plansByDate.get(dateStr)) {
                                                if (plan.getUserID() == userLogin.getUserID()) {
                                                    ArrayList<Weekly_Plan_Product> listProductInWeekly = plan.getListOfProduct();
                                                    if (listProductInWeekly != null) {
                                                        for (Weekly_Plan_Product productInWeek : listProductInWeekly) {
                                                            Product product = productMap.get(productInWeek.getProductID());
                                        %>
                                        <li class="plan-card" style="background: rgb(255, 255, 255)">
                                            <img
                                                src="<%= product.getImage()%>"
                                                alt="plan thumbnail"
                                                class="plan-thumbnail"
                                                />
                                            <div class="plan-card-content">
                                                <div class="flex plan-card-header">
                                                    <h4 class="Truncate" style="color: rgb(0, 0, 0)">
                                                        <%= product.getProductName()%>
                                                    </h4>
                                                </div>
                                                <div class="flex plan-meal" style="align-items: center"><i class="fa-solid fa-utensils"></i><%= mealTypeMap.get(plan.getMealID())%></div>
                                            </div>
                                        </li>
                                        <%              }
                                                    }
                                                }
                                            }
                                        %>
                                    </div>


                                    <%
                                        }
                                    %>
                                </div>
                            </a>
                            <%
                                    }
                                }
                            %>
                        </div>
                    </div>
                </div>
            </section>


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


        </div>

        <%@include file="../../common/footer.jsp" %>


        <!-- Swiper -->
        <script src="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.js"></script>

        <!-- Main -->
        <script src="view/Assets/Js/swiper-api.js"></script>
        <script src="view/Assets/Js/memu-info.js"></script>
        <script src="view/Assets/Js/index.js"></script>
        <script src="view/Assets/Js/plan.js"></script>
    </body>
</html>