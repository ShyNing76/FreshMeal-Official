<%-- 
    Document   : menu-info
    Created on : Jun 19, 2024, 1:58:53 PM
    Author     : ADMIN
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="dto.Recipe_Product"%>
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
        <link rel="stylesheet" href="view/Assets/css/menu-info.css" />

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

        <title>Menu-info</title>
    </head>
    <body>
        <%@include file="../../common/header.jsp" %>
        <c:set var="product" value="${requestScope.Product}"/>
        <section class="hidden">
            <div class="menu-info">
                <div class="menu-info-box">
                    <div class="menu-info-content">
                        <h1>${product.productName}</h1>
                        <c:set var="listOfCategoryOfProduct" value="${product.getListOfCategory()}"/>
                        <c:set var="listOfCategory" value="${requestScope.ListOfCategory}"/>
                        <div class="menu-tags-contain">
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
                        </div>
                        <div class="menu-info-icon">
                            <div><i class="fa-regular fa-clock"></i>${product.cookingTime} Min</div>
                            <div class="menu-info-price">
                                <i class="fa-solid fa-money-bill"></i><v:Currency_Format  price="${product.price}"/>đ
                            </div>
                        </div>
                        <div class="menu-info-describe">
                            <p>
                                ${product.description}
                            </p>
                        </div>
                        <%                            String mealType = (String) request.getAttribute("MEALTYPE");
                            String quantity = (String) request.getAttribute("QUANTITY");
                        %>
                        <%
                            if (mealType != null && quantity != null) {
                        %>      
                        <form action="DispatchServlet" method="POST">
                            <input type="hidden" name="productID" value="${product.productID}"/>
                            <div class="menu-section-end">
                                <div class="menu-info-selection">
                                    <h3>Type</h3>
                                    <div class="button-selection">                                        
                                        <button id="meal-kit" type="text"><%=mealType%></button>
                                        <input type="hidden" name="selectedMealType" value="<%=mealType%>"/>
                                    </div>
                                </div>

                                <div class="menu-info-quantity">
                                    <h3>Quantity</h3>
                                    <button id="decrement" class="button-quantity">
                                        <svg
                                            enable-background="new 0 0 10 10"
                                            viewBox="0 0 10 10"
                                            x="0"
                                            y="0"
                                            class="svg-icon"
                                            >
                                        <polygon
                                            points="4.5 4.5 3.5 4.5 0 4.5 0 5.5 3.5 5.5 4.5 5.5 10 5.5 10 4.5"
                                            ></polygon>
                                        </svg>
                                    </button>
                                    <input
                                        type="text"
                                        role="spinbutton"
                                        value="<%=Integer.parseInt(quantity)%>"
                                        name="txtQuantity"
                                        class="button-quantity input-quantity"
                                        />
                                    <button id="increment" class="button-quantity">
                                        <svg
                                            enable-background="new 0 0 10 10"
                                            viewBox="0 0 10 10"
                                            x="0"
                                            y="0"
                                            class="svg-icon icon-plus-sign"
                                            >
                                        <polygon
                                            points="10 4.5 5.5 4.5 5.5 0 4.5 0 4.5 4.5 0 4.5 0 5.5 4.5 5.5 4.5 10 5.5 10 5.5 5.5 10 5.5"
                                            ></polygon>
                                        </svg>
                                    </button>
                                </div>
                                <button class="button dark-btn" type="submit" name="btAction" value="AddToCart">
                                    Add To Cart <i class="fa-solid fa-cart-shopping cart-i"></i>
                                </button>
                            </div>
                        </form>
                        <%    } else {
                        %>
                        <form action="DispatchServlet">
                            <input type="hidden" name="productID" value="${product.productID}"/>
                            <div class="menu-section-end">
                                <div class="menu-info-selection">
                                    <h3>Type</h3>
                                    <div class="button-selection">                                        
                                        <button id="meal-kit" type="button" onclick="setMealType('Meal Kits')">Meal Kits</button>
                                        <button id="prepare-meal" type="button" onclick="setMealType('Prepared Meals')">Prepared Meals</button>
                                        <input type="hidden" name="selectedMealType" id="selectedMealType"/>
                                    </div>
                                </div>
                                <p>${requestScope.ERROR}</p>

                                <div class="menu-info-quantity">
                                    <h3>Quantity</h3>
                                    <button id="decrement" class="button-quantity">
                                        <svg
                                            enable-background="new 0 0 10 10"
                                            viewBox="0 0 10 10"
                                            x="0"
                                            y="0"
                                            class="svg-icon"
                                            >
                                        <polygon
                                            points="4.5 4.5 3.5 4.5 0 4.5 0 5.5 3.5 5.5 4.5 5.5 10 5.5 10 4.5"
                                            ></polygon>
                                        </svg>
                                    </button>
                                    <input
                                        type="text"
                                        role="spinbutton"
                                        value="1"
                                        name="txtQuantity"
                                        class="button-quantity input-quantity"
                                        />
                                    <button id="increment" class="button-quantity">
                                        <svg
                                            enable-background="new 0 0 10 10"
                                            viewBox="0 0 10 10"
                                            x="0"
                                            y="0"
                                            class="svg-icon icon-plus-sign"
                                            >
                                        <polygon
                                            points="10 4.5 5.5 4.5 5.5 0 4.5 0 4.5 4.5 0 4.5 0 5.5 4.5 5.5 4.5 10 5.5 10 5.5 5.5 10 5.5"
                                            ></polygon>
                                        </svg>
                                    </button>
                                </div>
                                <button class="button dark-btn" type="submit" name="btAction" value="AddToCart">
                                    Add To Cart <i class="fa-solid fa-cart-shopping cart-i"></i>
                                </button>
                            </div>
                        </form>
                        <%
                            }
                        %>
                    </div>

                    <div class="menu-info-img">
                        <img src="${product.image}" alt="" />
                    </div>
                </div>
            </div>
        </section>


        <section class="hidden">
            <div class="ingredient">
                <div class="menu-ingredient">
                    <div class="menu-ingredient-img">
                        <img src="view/Assets/Images/ingredient.jpg" alt="" />
                    </div>
                    <div class="menu-ingredient-info">
                        <h3>Fresh Ingredient</h3>
                        <ul>
                            <c:set var="ListOfIngredient" value="${requestScope.ListOfIngredient}"/>
                            <c:set var="ListOfIngredientInProduct" value="${requestScope.ListOfIngredientInProduct}"/>
                            <c:if test="${ListOfIngredient != null}">
                                <c:forEach var="i" items="${ListOfIngredient}">
                                    <c:if test="${ListOfIngredientInProduct != null}">
                                        <c:forEach var="bi" items="${ListOfIngredientInProduct}">
                                            <c:if test="${i.ingredientID == bi.ingredientID}">
                                                <li>
                                                    <span class="ingredient-quatity">${bi.quantity} ${bi.unit}</span>
                                                    <span class="ingredient-name">${i.name}</span>
                                                </li>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </div>
        </section>

        <section class="hidden">
            <div class="instruction">
                <div class="instruction-main">
                    <div class="instruction-header">
                        <h2>Intructions Guide</h2>
                    </div>
                    <div class="instruction-step">
                        <%                            ArrayList<Recipe_Product> ListOfRecipe = (ArrayList) request.getAttribute("ListOfRecipe");
                            int count = 1;
                            if (ListOfRecipe != null) {
                                for (Recipe_Product rp : ListOfRecipe) {
                        %>

                        <div class="step">
                            <div class="step-img">
                                <img src="view/Assets/Images/step/Step-<%= rp.getStep()%>.png" alt="" />
                            </div>
                            <div class="step-content">
                                <div class="step-circle">
                                    <span class="number-step"><%=count++%></span>
                                </div>
                                <div class="step-text">
                                    <p>
                                        <%= rp.getInstruction()%>
                                    </p>
                                </div>
                            </div>
                        </div>
                        <%    }
                            }
                        %>
                    </div>
                </div>
            </div>
        </section>


        <section class="hidden">
            <div class="menu-like">
                <div class="menu-like-header">
                    <h3>You might also like...</h3>
                </div>
                <div class="swiper food-slider">
                    <div class="swiper-wrapper">
                        <c:set var="ListOfProductInAllWeeklyPlan" value="${requestScope.ListOfProductInAllWeeklyPlan}"/>
                        <c:if test="${ListOfProductInAllWeeklyPlan != null}">
                            <c:forEach var="p" items="${ListOfProductInAllWeeklyPlan}">
                                <div class="swiper-slide menu-box">
                                    <a href="DispatchServlet?btAction=MenuInfo&productId=${p.productID}">
                                        <img src="${p.image}" alt="" />
                                        <div class="menu-text">
                                            <div class="menu-tags-contain">
                                                <c:set var="listOfCategoryOfProduct" value="${p.getListOfCategory()}"/>
                                                <c:set var="listOfCategory" value="${requestScope.ListOfCategory}"/>
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
                                            </div>
                                            <h2>${p.getProductName()}</h2>
                                            <p>
                                                ${p.description}
                                            </p>
                                        </div>
                                        <div class="menu-min-price">
                                            <div class="menu-min">
                                                <i class="fa-regular fa-clock menu-min-icon"></i>
                                                <small> ${p.getCookingTime()} Min </small>
                                            </div>
                                            <small class="menu-price"><v:Currency_Format  price="${p.price}"/>đ </small>
                                        </div>
                                    </a>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                </div>
                <div class="swiper-button-next"></div>
                <div class="swiper-button-prev"></div>
            </div>
        </div>
    </section>

    <%@include file="../../common/footer.jsp" %>

    <!-- Swiper -->
    <script src="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.js"></script>

    <!-- Main -->
    <script src="view/Assets/Js/swiper-api.js"></script>
    <script src="view/Assets/Js/memu-info.js"></script>
    <script src="view/Assets/Js/index.js"></script>
</body>
</html>

