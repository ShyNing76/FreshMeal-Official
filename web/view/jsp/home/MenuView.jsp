<%-- 
    Document   : MenuView
    Created on : Jun 5, 2024, 2:12:12 PM
    Author     : ADMIN
--%>

<%@page import="utils.Format"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Date"%>
<%@page import="dto.Weekly_Plan"%>
<%@page import="java.util.List"%>
<%@page import="dto.Category"%>
<%@page import="java.util.ArrayList"%>
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
        <link rel="stylesheet" href="view/Assets/css/menu.css" />

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

        <title>Menu - Fresh Meal</title>
    </head>
    <body>
        <%@include file="../../common/header.jsp" %>
        <c:set var="WeeklyPlanID" value="${requestScope.WeeklyPlanID}"/>
        <c:set var="CategoryName" value="${requestScope.CategoryName}"/>
        <section class="hidden">
            <div class="header-contain">
                <div class="header-menu">
                    <h1>Explore our meal delivery menu</h1>
                </div> 
                <div class="weekly-nav">
                    <%                        String[] monthNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

                        ArrayList<Weekly_Plan> listOfWeeklyPlan = (ArrayList<Weekly_Plan>) request.getAttribute("ListOfWeeklyPlan");

                        if (listOfWeeklyPlan != null) {
                            for (Weekly_Plan w : listOfWeeklyPlan) {
                                Date startDate = w.getStartDate();
                                Date endDate = w.getEndDate();

                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                String startDateString = sdf.format(startDate);
                                String endDateString = sdf.format(endDate);

                                String[] startDateParts = startDateString.split("/");
                                String[] endDateParts = endDateString.split("/");

                                String startDay = startDateParts[0];
                                int startMonthNumber = Integer.parseInt(startDateParts[1]) - 1;
                                String endDay = endDateParts[0];
                    %>
                    <a href="DispatchServlet?btAction=WeeklyPlanProduct&weeklyPlanId=<%= w.getWeekly_Plan_ID()%>" class="weekly-choose">
                        <small><%= monthNames[startMonthNumber]%></small>
                        <span><%= startDay%> - <%= endDay%></span>
                    </a>
                    <%
                            }
                        }
                    %>
                </div>


            </div>
        </section>

        <section class="hidden">
            <div class="menu-contain">
                <div class="menu-category">
                    <c:set var="listOfCategory" value="${requestScope.ListOfCategory}"/>
                    <c:if test="${listOfCategory != null}">
                        <div class="menu-option">
                            <a href="DispatchServlet?btAction=WeeklyPlanProduct&weeklyPlanId=${WeeklyPlanID}">
                                <img src="view/Assets/Images/food category/All.png" alt=""/>
                            </a>
                            <h4>All</h4>
                        </div>
                        <c:forEach var="c" items="${listOfCategory}">
                            <div class="menu-option">
                                <a href="DispatchServlet?btAction=CategoryProduct&categoryName=${c.getCategoryName()}&weeklyPlanId=${WeeklyPlanID}">
                                    <img src="${c.image}" alt=""/>
                                </a>
                                <h4>${c.getCategoryName()}</h4>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
        </section>

        <section class="hidden">
            <div class="menu-flex">
                <div class="menu-filter">
                    <div class="menu-filter-block">
                        <h1><i class="fa-regular fa-clock header-i"></i>Time Consumed</h1>
                        <div class="menu-filter-container">
                            <div class="filter-option">
                                <a href="DispatchServlet?btAction=TimeSelection&startTime=10&endTime=20&categoryName=${CategoryName}&weeklyPlanId=${WeeklyPlanID}" class="filter-link" data-group="time" data-type="10-20">
                                    <span class="radio-circle"></span> 10 min
                                    <i class="fa-solid fa-arrow-right form-i"></i> 20 min
                                </a>
                            </div>
                            <div class="filter-option">
                                <a href="DispatchServlet?btAction=TimeSelection&startTime=20&endTime=30&categoryName=${CategoryName}&weeklyPlanId=${WeeklyPlanID}" class="filter-link" data-group="time" data-type="20-30">
                                    <span class="radio-circle"></span> 20 min
                                    <i class="fa-solid fa-arrow-right form-i"></i> 30 min
                                </a>
                            </div>
                            <div class="filter-option">
                                <a href="DispatchServlet?btAction=TopTimeSelection&categoryName=${CategoryName}&weeklyPlanId=${WeeklyPlanID}" class="filter-link" data-group="time" data-type="30">
                                    <span class="radio-circle"></span> 30 min
                                    <i class="fa-solid fa-up-long form-i"></i>
                                </a>
                            </div>
                        </div>
                    </div>

                    <div class="menu-filter-block">
                        <h1><i class="fa-solid fa-money-bill header-i"></i>Price</h1>
                        <div class="menu-filter-container">
                            <div class="filter-option">
                                <a href="DispatchServlet?btAction=PriceSelection&startPrice=20000&endPrice=30000&categoryName=${CategoryName}&weeklyPlanId=${WeeklyPlanID}" class="filter-link" data-group="price" data-type="20-30">
                                    <span class="radio-circle"></span>20,000đ
                                    <i class="fa-solid fa-arrow-right form-i"></i> 30,000đ
                                </a>
                            </div>
                            <div class="filter-option">
                                <a href="DispatchServlet?btAction=PriceSelection&startPrice=30000&endPrice=40000&categoryName=${CategoryName}&weeklyPlanId=${WeeklyPlanID}" class="filter-link" data-group="price" data-type="30-40">
                                    <span class="radio-circle"></span>30,000đ
                                    <i class="fa-solid fa-arrow-right form-i"></i> 40,000đ
                                </a>
                            </div>
                            <div class="filter-option">
                                <a href="DispatchServlet?btAction=PriceSelection&startPrice=40000&endPrice=50000&categoryName=${CategoryName}&weeklyPlanId=${WeeklyPlanID}" class="filter-link" data-group="price" data-type="40-50">
                                    <span class="radio-circle"></span>40,000đ
                                    <i class="fa-solid fa-arrow-right form-i"></i> 50,000đ
                                </a>
                            </div>
                            <div class="filter-option">
                                <a href="DispatchServlet?btAction=TopPriceSelection&categoryName=${CategoryName}&weeklyPlanId=${WeeklyPlanID}" class="filter-link" data-group="price" data-type="50">
                                    <span class="radio-circle"></span> 50,000
                                    <i class="fa-solid fa-up-long form-i"></i>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="menu-main">
                    <div class="search-container">
                        <form action="DispatchServlet">
                            <input type="hidden" name="weeklyPlanID" value="${WeeklyPlanID}"/>
                            <div class="search-main">
                                <div class="search-main-box">
                                    <div class="form-group">
                                        <div class="search-input">
                                            <input
                                                type="search"
                                                name="txtSearch"
                                                class="search-input-box"
                                                placeholder="Search Here"
                                                value="${requestScope.txtSearch}"
                                                />
                                            <div class="search-button-box">
                                                <button type="submit" name="btAction" value="Search" class="search-button">
                                                    <i class="fa fa-search"></i>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="menu-section">
                        <c:if test="${requestScope.Error == null}">
                            <c:set var="ListOfFilterCategoryProduct" value="${requestScope.ListOfFilterCategoryProduct}"/>
                            <c:set var="ListOfTopPriceProduct" value="${requestScope.ListOfTopPriceProduct}"/>
                            <c:set var="ListOfTopTimeProduct" value="${requestScope.ListOfTopTimeProduct}"/>
                            <c:set var="ListOfPriceSelectionProduct" value="${requestScope.ListOfPriceSelectionProduct}"/>
                            <c:set var="ListOfTimeSelectionProduct" value="${requestScope.ListOfTimeSelectionProduct}"/>
                            <c:set var="ListOf6ProductInWeeklyPlan" value="${requestScope.ListOf6ProductInWeeklyPlan}"/>
                            <c:set var="ListOfAllProductInWeeklyPlan" value="${requestScope.ListOfAllProductInWeeklyPlan}"/>
                            <c:set var="ListOfFilterCategoryAndWeeklyPlanProduct" value="${requestScope.ListOfFilterCategoryAndWeeklyPlanProduct}"/>
                            <c:set var="ListOfProductFounded" value="${requestScope.ListOfProductFounded}"/>
                            <c:if test="${ListOf6ProductInWeeklyPlan != null}">
                                <c:forEach var="p" items="${ListOf6ProductInWeeklyPlan}" >
                                    <div class="menu-box">
                                        <a href="DispatchServlet?btAction=MenuInfo&productId=${p.productID}">
                                            <img src="${p.image}" alt="" />
                                            <div class="menu-text">
                                                <div class="menu-tags-contain">
                                                    <c:set var="listOfCategoryOfProduct" value="${p.getListOfCategory()}"/>
                                                    <c:set var="listOfCategory" value="${requestScope.ListOfCategory}"/>
                                                    <c:if test="${listOfCategoryOfProduct != null}">
                                                        <c:forEach var="categoryOfProduct" items="${listOfCategoryOfProduct}">
                                                            <c:if test="${listOfCategory != null}">
                                                                <c:forEach var="category" items="${listOfCategory}">
                                                                    <c:if test="${categoryOfProduct.categoryID == category.categoryID}">
                                                                        <span class="menu-tags">
                                                                            <i class="fa-solid fa-tag"></i>
                                                                            <small>${category.categoryName}</small>
                                                                        </span>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </c:if>
                                                        </c:forEach>
                                                    </c:if>                 
                                                </div>
                                                <h2>${p.getProductName()}</h2>
                                                <p>
                                                    ${p.description}
                                                </p>
                                            </div>
                                            <div class="menu-min-price">
                                                <div class="menu-min">
                                                    <i class="fa-regular fa-clock menu-min-icon"></i>
                                                    <small> ${p.getCookingTime()} min </small>
                                                </div>
                                                <small class="menu-price"><v:Currency_Format  price="${p.price}"/>đ </small>
                                            </div>
                                        </a>
                                    </div>
                                </c:forEach>
                            </c:if>
                            <c:if test="${ListOfAllProductInWeeklyPlan != null}">
                                <c:forEach var="p" items="${ListOfAllProductInWeeklyPlan}" >
                                    <div class="menu-box">
                                        <a href="DispatchServlet?btAction=MenuInfo&productId=${p.productID}">
                                            <img src="${p.image}" alt="" />
                                            <div class="menu-text">
                                                <div class="menu-tags-contain">
                                                    <c:set var="listOfCategoryOfProduct" value="${p.getListOfCategory()}"/>
                                                    <c:set var="listOfCategory" value="${requestScope.ListOfCategory}"/>
                                                    <c:if test="${listOfCategoryOfProduct != null}">
                                                        <c:forEach var="categoryOfProduct" items="${listOfCategoryOfProduct}">
                                                            <c:if test="${listOfCategory != null}">
                                                                <c:forEach var="category" items="${listOfCategory}">
                                                                    <c:if test="${categoryOfProduct.categoryID == category.categoryID}">
                                                                        <span class="menu-tags">
                                                                            <i class="fa-solid fa-tag"></i>
                                                                            <small>${category.categoryName}</small>
                                                                        </span>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </c:if>
                                                        </c:forEach>
                                                    </c:if>                 
                                                </div>
                                                <h2>${p.getProductName()}</h2>
                                                <p>
                                                    ${p.description}
                                                </p>
                                            </div>
                                            <div class="menu-min-price">
                                                <div class="menu-min">
                                                    <i class="fa-regular fa-clock menu-min-icon"></i>
                                                    <small> ${p.getCookingTime()} min </small>
                                                </div>
                                                <small class="menu-price"><v:Currency_Format  price="${p.price}"/>đ </small>
                                            </div>
                                        </a>
                                    </div>
                                </c:forEach>
                            </c:if>
                            <c:if test="${ListOfProductFounded != null}">
                                <c:forEach var="p" items="${ListOfProductFounded}" >
                                    <div class="menu-box">
                                        <a href="DispatchServlet?btAction=MenuInfo&productId=${p.productID}">
                                            <img src="${p.image}" alt="" />
                                            <div class="menu-text">
                                                <div class="menu-tags-contain">
                                                    <c:set var="listOfCategoryOfProduct" value="${p.getListOfCategory()}"/>
                                                    <c:set var="listOfCategory" value="${requestScope.ListOfCategory}"/>
                                                    <c:if test="${listOfCategoryOfProduct != null}">
                                                        <c:forEach var="categoryOfProduct" items="${listOfCategoryOfProduct}">
                                                            <c:if test="${listOfCategory != null}">
                                                                <c:forEach var="category" items="${listOfCategory}">
                                                                    <c:if test="${categoryOfProduct.categoryID == category.categoryID}">
                                                                        <span class="menu-tags">
                                                                            <i class="fa-solid fa-tag"></i>
                                                                            <small>${category.categoryName}</small>
                                                                        </span>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </c:if>
                                                        </c:forEach>
                                                    </c:if>                 
                                                </div>
                                                <h2>${p.getProductName()}</h2>
                                                <p>
                                                    ${p.description}
                                                </p>
                                            </div>
                                            <div class="menu-min-price">
                                                <div class="menu-min">
                                                    <i class="fa-regular fa-clock menu-min-icon"></i>
                                                    <small> ${p.getCookingTime()} min </small>
                                                </div>
                                                <small class="menu-price"><v:Currency_Format  price="${p.price}"/>đ </small>
                                            </div>
                                        </a>
                                    </div>
                                </c:forEach>
                            </c:if>
                            <c:if test="${ListOfFilterCategoryProduct != null}">
                                <c:forEach var="p" items="${ListOfFilterCategoryProduct}" >
                                    <div class="menu-box">
                                        <a href="DispatchServlet?btAction=MenuInfo&productId=${p.productID}">
                                            <img src="${p.image}" alt="" />
                                            <div class="menu-text">
                                                <div class="menu-tags-contain">
                                                    <c:set var="listOfCategoryOfProduct" value="${p.getListOfCategory()}"/>
                                                    <c:set var="listOfCategory" value="${requestScope.ListOfCategory}"/>
                                                    <c:if test="${listOfCategoryOfProduct != null}">
                                                        <c:forEach var="categoryOfProduct" items="${listOfCategoryOfProduct}">
                                                            <c:if test="${listOfCategory != null}">
                                                                <c:forEach var="category" items="${listOfCategory}">
                                                                    <c:if test="${categoryOfProduct.categoryID == category.categoryID}">
                                                                        <span class="menu-tags">
                                                                            <i class="fa-solid fa-tag"></i>
                                                                            <small>${category.categoryName}</small>
                                                                        </span>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </c:if>
                                                        </c:forEach>
                                                    </c:if>                 
                                                </div>
                                                <h2>${p.getProductName()}</h2>
                                                <p>
                                                    ${p.description}
                                                </p>
                                            </div>
                                            <div class="menu-min-price">
                                                <div class="menu-min">
                                                    <i class="fa-regular fa-clock menu-min-icon"></i>
                                                    <small> ${p.getCookingTime()} min </small>
                                                </div>
                                                <small class="menu-price"><v:Currency_Format  price="${p.price}"/>đ </small>
                                            </div>
                                        </a>
                                    </div>
                                </c:forEach>
                            </c:if>
                            <c:if test="${ListOfTopPriceProduct != null}">
                                <c:forEach var="p" items="${ListOfTopPriceProduct}" >
                                    <div class="menu-box">
                                        <a href="DispatchServlet?btAction=MenuInfo&productId=${p.productID}">
                                            <img src="${p.image}" alt="" />
                                            <div class="menu-text">
                                                <div class="menu-tags-contain">
                                                    <c:set var="listOfCategoryOfProduct" value="${p.getListOfCategory()}"/>
                                                    <c:set var="listOfCategory" value="${requestScope.ListOfCategory}"/>
                                                    <c:if test="${listOfCategoryOfProduct != null}">
                                                        <c:forEach var="categoryOfProduct" items="${listOfCategoryOfProduct}">
                                                            <c:if test="${listOfCategory != null}">
                                                                <c:forEach var="category" items="${listOfCategory}">
                                                                    <c:if test="${categoryOfProduct.categoryID == category.categoryID}">
                                                                        <span class="menu-tags">
                                                                            <i class="fa-solid fa-tag"></i>
                                                                            <small>${category.categoryName}</small>
                                                                        </span>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </c:if>
                                                        </c:forEach>
                                                    </c:if>                 
                                                </div>
                                                <h2>${p.getProductName()}</h2>
                                                <p>
                                                    ${p.description}
                                                </p>
                                            </div>
                                            <div class="menu-min-price">
                                                <div class="menu-min">
                                                    <i class="fa-regular fa-clock menu-min-icon"></i>
                                                    <small> ${p.getCookingTime()} min </small>
                                                </div>
                                                <small class="menu-price"><v:Currency_Format  price="${p.price}"/>đ </small>
                                            </div>
                                        </a>
                                    </div>
                                </c:forEach>
                            </c:if>
                            <c:if test="${ListOfTopTimeProduct != null}">
                                <c:forEach var="p" items="${ListOfTopTimeProduct}" >
                                    <div class="menu-box">
                                        <a href="DispatchServlet?btAction=MenuInfo&productId=${p.productID}">
                                            <img src="${p.image}" alt="" />
                                            <div class="menu-text">
                                                <div class="menu-tags-contain">
                                                    <c:set var="listOfCategoryOfProduct" value="${p.getListOfCategory()}"/>
                                                    <c:set var="listOfCategory" value="${requestScope.ListOfCategory}"/>
                                                    <c:if test="${listOfCategoryOfProduct != null}">
                                                        <c:forEach var="categoryOfProduct" items="${listOfCategoryOfProduct}">
                                                            <c:if test="${listOfCategory != null}">
                                                                <c:forEach var="category" items="${listOfCategory}">
                                                                    <c:if test="${categoryOfProduct.categoryID == category.categoryID}">
                                                                        <span class="menu-tags">
                                                                            <i class="fa-solid fa-tag"></i>
                                                                            <small>${category.categoryName}</small>
                                                                        </span>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </c:if>
                                                        </c:forEach>
                                                    </c:if>                                             
                                                </div>
                                                <h2>${p.getProductName()}</h2>
                                                <p>
                                                    ${p.description}
                                                </p>
                                            </div>
                                            <div class="menu-min-price">
                                                <div class="menu-min">
                                                    <i class="fa-regular fa-clock menu-min-icon"></i>
                                                    <small> ${p.getCookingTime()} min </small>
                                                </div>
                                                <small class="menu-price"><v:Currency_Format  price="${p.price}"/>đ </small>
                                            </div>
                                        </a>
                                    </div>
                                </c:forEach>
                            </c:if>
                            <c:if test="${ListOfPriceSelectionProduct != null}">
                                <c:forEach var="p" items="${ListOfPriceSelectionProduct}">
                                    <div class="menu-box">
                                        <a href="DispatchServlet?btAction=MenuInfo&productId=${p.productID}">
                                            <img src="${p.image}" alt="" />
                                            <div class="menu-text">
                                                <div class="menu-tags-contain">
                                                    <c:set var="listOfCategoryOfProduct" value="${p.getListOfCategory()}"/>
                                                    <c:set var="listOfCategory" value="${requestScope.ListOfCategory}"/>
                                                    <c:if test="${listOfCategoryOfProduct != null}">
                                                        <c:forEach var="categoryOfProduct" items="${listOfCategoryOfProduct}">
                                                            <c:if test="${listOfCategory != null}">
                                                                <c:forEach var="category" items="${listOfCategory}">
                                                                    <c:if test="${categoryOfProduct.categoryID == category.categoryID}">
                                                                        <span class="menu-tags">
                                                                            <i class="fa-solid fa-tag"></i>
                                                                            <small>${category.categoryName}</small>
                                                                        </span>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </c:if>
                                                        </c:forEach>
                                                    </c:if>                 
                                                </div>
                                                <h2>${p.getProductName()}</h2>
                                                <p>
                                                    ${p.description}
                                                </p>
                                            </div>
                                            <div class="menu-min-price">
                                                <div class="menu-min">
                                                    <i class="fa-regular fa-clock menu-min-icon"></i>
                                                    <small> ${p.getCookingTime()} min </small>
                                                </div>
                                                <small class="menu-price"><v:Currency_Format  price="${p.price}"/>đ </small>
                                            </div>
                                        </a>
                                    </div>
                                </c:forEach>
                            </c:if>
                            <c:if test="${ListOfTimeSelectionProduct != null}">
                                <c:forEach var="p" items="${ListOfTimeSelectionProduct}" >
                                    <div class="menu-box">
                                        <a href="DispatchServlet?btAction=MenuInfo&productId=${p.productID}">
                                            <img src="${p.image}" alt="" />
                                            <div class="menu-text">
                                                <div class="menu-tags-contain">
                                                    <c:set var="listOfCategoryOfProduct" value="${p.getListOfCategory()}"/>
                                                    <c:set var="listOfCategory" value="${requestScope.ListOfCategory}"/>
                                                    <c:if test="${listOfCategoryOfProduct != null}">
                                                        <c:forEach var="categoryOfProduct" items="${listOfCategoryOfProduct}">
                                                            <c:if test="${listOfCategory != null}">
                                                                <c:forEach var="category" items="${listOfCategory}">
                                                                    <c:if test="${categoryOfProduct.categoryID == category.categoryID}">
                                                                        <span class="menu-tags">
                                                                            <i class="fa-solid fa-tag"></i>
                                                                            <small>${category.categoryName}</small>
                                                                        </span>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </c:if>
                                                        </c:forEach>
                                                    </c:if>                                     
                                                </div>
                                                <h2>${p.getProductName()}</h2>
                                                <p>
                                                    ${p.description}
                                                </p>
                                            </div>
                                            <div class="menu-min-price">
                                                <div class="menu-min">
                                                    <i class="fa-regular fa-clock menu-min-icon"></i>
                                                    <small> ${p.getCookingTime()} min </small>
                                                </div>
                                                <small class="menu-price"><v:Currency_Format  price="${p.price}"/>đ </small>
                                            </div>
                                        </a>
                                    </div>
                                </c:forEach>
                            </c:if>

                            <c:if test="${ListOfFilterCategoryAndWeeklyPlanProduct != null}">
                                <c:forEach var="p" items="${ListOfFilterCategoryAndWeeklyPlanProduct}" >
                                    <div class="menu-box">
                                        <a href="DispatchServlet?btAction=MenuInfo&productId=${p.productID}">
                                            <img src="${p.image}" alt="" />
                                            <div class="menu-text">
                                                <div class="menu-tags-contain">
                                                    <c:set var="listOfCategoryOfProduct" value="${p.getListOfCategory()}"/>
                                                    <c:set var="listOfCategory" value="${requestScope.ListOfCategory}"/>
                                                    <c:if test="${listOfCategoryOfProduct != null}">
                                                        <c:forEach var="categoryOfProduct" items="${listOfCategoryOfProduct}">
                                                            <c:if test="${listOfCategory != null}">
                                                                <c:forEach var="category" items="${listOfCategory}">
                                                                    <c:if test="${categoryOfProduct.categoryID == category.categoryID}">
                                                                        <span class="menu-tags">
                                                                            <i class="fa-solid fa-tag"></i>
                                                                            <small>${category.categoryName}</small>
                                                                        </span>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </c:if>
                                                        </c:forEach>
                                                    </c:if>                 
                                                </div>
                                                <h2>${p.getProductName()}</h2>
                                                <p>
                                                    ${p.description}
                                                </p>
                                            </div>
                                            <div class="menu-min-price">
                                                <div class="menu-min">
                                                    <i class="fa-regular fa-clock menu-min-icon"></i>
                                                    <small> ${p.getCookingTime()} min </small>
                                                </div>
                                                <small class="menu-price"><v:Currency_Format  price="${p.price}"/>đ </small>
                                            </div>
                                        </a>
                                    </div>
                                </c:forEach>
                            </c:if>
                        </div>
                    </div>
            </section>
            <c:set var="action" value="${requestScope.Action}"/>
            <c:if test="${action == null}">
                <section class="hidden">
                    <div class="menu-plan-start">
                        <form action="DispatchServlet?btAction=Menu" method="POST">
                            <input type="hidden" name="txtMoreProductID" value="${WeeklyPlanID}"/>
                            <button type="submit" name="action" value="showMoreMenu" class="button dark-btn large-btn">Show More Menu</button>
                        </form>
                        <p>
                            Food provide essensial nutriels for overall health and
                            well-being.
                        </p>
                    </div>
                </section>
            </c:if>
        </c:if>

        <c:if test="${requestScope.Error != null}">
            <p>${requestScope.Error}</p>
        </c:if>
        <%@include file="../../common/footer.jsp" %>

        <script src="view/Assets/Js/menu.js"></script>
        <script src="view/Assets/Js/index.js"></script>
    </body>
</html>

