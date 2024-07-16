<%-- 
    Document   : checkout2
    Created on : Jul 2, 2024, 7:39:29 PM
    Author     : ADMIN
--%>

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
        <link rel="stylesheet" href="view/Assets/css/checkout.css" />
        <link rel="stylesheet" href="view/Assets/css/address.css" />

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

        <style>
            .checkout-container {
                display: flex;
                justify-content: center;
                align-items: center;
                padding: 2rem;
            }

            .method-container {
                background-color: #fff;
                padding: 1.5rem;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                width: 100%;
                max-width: 600px;
            }

            .stardust-radio {
                position: relative;
                width: 100%;
            }

            .stardust-radio input[type="radio"] {
                display: none; 
            }

            .stardust-radio-content {
                width: 100%;
                display: flex;
                align-items: center;
                justify-content: space-between;
                border: 1px solid #ccc;
                border-radius: 8px;
            }

            .method-main-content {
                display: flex;
                justify-content: space-between;
                align-items: center;
                width: 100%;
            }

            .price-method {
                font-size: 1.2rem;
                color: #81d34a;
                font-weight: bold;
            }

            .name-method {
                font-size: 1rem;
                color: #333;
                margin-left: 1rem;
            }

            .logo-method img {
                max-width: 40px;
                height: auto;
            }

            .method-button {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-top: 1.5rem;
            }

        </style>

        <title>CheckOut - Fresh Meal</title>
    </head>
    <body>
        <div class="wrapper">
            <div class="step">
                <a href="DispatchServlet"><img src="view/Assets/Images/logo.png" alt="" /></a>

                <ul class="step-list">
                    <li class="step-item">
                        <span class="progress-count">1</span>
                        <span class="progress-label">Address</span>
                    </li>

                    <li class="step-item current-item">
                        <span class="progress-count">2</span>
                        <span class="progress-label">Shipping Method</span>
                    </li>

                    <li class="step-item">
                        <span class="progress-count">3</span>
                        <span class="progress-label">Payment</span>
                    </li>

                    <li class="step-item">
                        <span class="progress-count">4</span>
                        <span class="progress-label">Confirm Order</span>
                    </li>

                    <li class="step-item">
                        <span class="progress-count">5</span>
                        <span class="progress-label">Finish</span>
                    </li>
                </ul>
            </div>
            <main>
                <c:set var="ListOfDelivery" value="${requestScope.ListOfDelivery}"/>
                <form action="DispatchServlet" method="POST">
                    <div class="checkout-container">
                        <div class="method-container">
                            <div class="method-header">
                                <h2>Shipping Method</h2>
                            </div>
                            <p>${requestScope.ERROR}</p>
                            <div class="method-contain">
                                <c:set var="selectedShippingMethod" value="${requestScope.SELECTEDSHIPPINGMETHOD}"/>
                                <c:if test="${ListOfDelivery != null}">
                                    <c:forEach var="d" items="${ListOfDelivery}">
                                        <div class="method-item">
                                            <div class="stardust-radio" role="radio">
                                                <input id="Shipping-${d.deliveryID}" type="radio" name="selectedShippingMethod" value="${d.deliveryID}" <c:if test="${selectedShippingMethod == d.deliveryID}">checked=""</c:if>/> 
                                                <label for="Shipping-${d.deliveryID}" style="width: 100%">
                                                    <div class="stardust-radio-content" style="padding: 10px">
                                                        <div class="method-main-content">
                                                            <div class="price-method"><v:Currency_Format  price="${d.deliveryPrice}"/>đ</div>
                                                            <div class="name-method">${d.deliveryMethod}</div>
                                                            <div class="logo-method">
                                                                <img src="${d.deliveryIcon}" alt="" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                </label>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:if>
                                <div class="method-button">
                                    <input type="hidden" name="SUBTOTALPRICE" value="${requestScope.SUBTOTALPRICE}"/>
                                    <input type="hidden" name="DISCOUNT" value="${requestScope.DISCOUNT}"/>
                                    <input type="hidden" name="TAX" value="${requestScope.TAX}"/>
                                    <input type="hidden" name="TOTALPRICE" value="${requestScope.TOTALPRICE}"/>
                                    <input type="hidden" name="selectedAddress" value="${requestScope.SELECTEDADDRESS}"/>
                                    <button type="submit" name="btAction" value="Checkout1" class="button">Previous</button>
                                    <div class="seperate-box"></div>
                                    <button type="submit" name="btAction" value="Checkout3" class="button dark-btn">Continue</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </main>

        </div>
        <%@include file="../../common/footer.jsp" %>



        <script src="view/Assets/Js/method.js"></script>
    </body>
</html>

