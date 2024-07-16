<%-- 
    Document   : checkout4
    Created on : Jul 2, 2024, 7:58:11 PM
    Author     : ADMIN
--%>

<%@page import="java.util.HashMap"%>
<%@page import="dto.Product"%>
<%@page import="dto.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
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

                    <li class="step-item">
                        <span class="progress-count">2</span>
                        <span class="progress-label">Shipping Method</span>
                    </li>

                    <li class="step-item">
                        <span class="progress-count">3</span>
                        <span class="progress-label">Payment</span>
                    </li>

                    <li class="step-item current-item">
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
                <c:set var="user" value="${sessionScope.account}"/>
                <c:set var="address" value="${requestScope.ADDRESS}"/>
                <c:set var="delivery" value="${requestScope.DELIVERY}"/>
                <c:set var="subTotalPrice" value="${requestScope.SUBTOTALPRICE}"/>
                <c:set var="discount" value="${requestScope.DISCOUNT}"/>
                <c:set var="tax" value="${requestScope.TAX}"/>
                <c:set var="totalPrice" value="${requestScope.TOTALPRICE}"/>
                <c:set var="paymentMethod" value="${requestScope.SELECTEDPAYMENTMETHOD}"/>
                <%            HashMap<Product, Integer> cart = (HashMap<Product, Integer>) session.getAttribute("CART");
                    if (cart != null) {
                %>
                <div class="checkout-container">
                    <div class="method-container">
                        <div class="method-header">
                            <div class="header-content-back">
                                <h2>Order</h2>
                            </div>
                        </div>
                        <div class="method-contain">
                            <div class="order-main">                                    
                                <%                                        for (Product product : cart.keySet()) {
                                %>
                                <div class="order-items">
                                    <div class="item-container">
                                        <div class="img-order-item">
                                            <img src="<%= product.getImage()%>" alt="" />
                                        </div>
                                        <div class="side-content">
                                            <div class="main-order-content">
                                                <div class="order-name-item"><%= product.getProductName()%></div>
                                                <div class="order-price-item"> <v:Currency_Format  price="<%= product.getPrice()%>"/>đ</div>
                                            </div>
                                            <div class="discription-order-content">
                                                <small class="order-type-item"><%=product.getMealType()%></small>
                                                <small class="order-quantity-item">Quantity: <%= cart.get(product)%></small>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%
                                    }
                                %>

                            </div>

                            <div class="order-body">
                                <div class="order-payment-item">
                                    <div class="header-item">Payment</div>
                                    <div class="payment-content">
                                        <i class="fa-solid fa-dolly"></i>
                                        <div class="payment-text">${requestScope.SELECTEDPAYMENTMETHOD}</div>
                                    </div>
                                </div>                                       

                                <div class="order-address-item">
                                    <div class="header-item">Delivery</div>
                                    <div class="order-address">
                                        <div class="subitem-content">
                                            <small class="subitem-header">Address</small>
                                            <p>
                                                ${address.address}
                                            </p>
                                        </div>
                                        <div class="subitem-content">
                                            <small class="subitem-header">Phone</small>
                                            <p>${user.phone}</p>
                                        </div>
                                        <div class="subitem-content">
                                            <small class="subitem-header">Delivery Method</small>
                                            <p>${delivery.deliveryMethod}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="order-sumary">
                                <div class="img-sumary">
                                    <div class="img-sumary-contain">
                                        <img src="view/Assets/Images/logo.png" alt="" />
                                    </div>
                                </div>

                                <div class="order-sumary-contain">
                                    <div class="sub-sumary">
                                        <div class="header-item">Order Sumary</div>
                                        <div class="sub-sumary-contain">
                                            <div class="sumary-subtotal">
                                                <div class="subtotal-text">Sub Total</div>
                                                <div class="subtotal-price"><v:Currency_Format  price="${requestScope.SUBTOTALPRICE}"/>đ</div>
                                            </div>
                                            <div class="sumary-discription">
                                                <small class="sumary-subtext">Discount</small>
                                                <c:if test="${requestScope.DISCOUNT != null}">
                                                    <small class="sumary-price">- <v:Currency_Format  price="${requestScope.DISCOUNT}"/>đ</small>
                                                </c:if>
                                                <c:if test="${requestScope.DISCOUNT == null}">
                                                    <small class="sumary-price">0 đ</small>
                                                </c:if>
                                            </div>
                                            <div class="sumary-discription">
                                                <small class="sumary-subtext">Delivery</small>
                                                <small class="sumary-price"><v:Currency_Format  price="${requestScope.DELIVERYPRICE}"/>đ</small>
                                            </div>
                                            <div class="sumary-discription">
                                                <small class="sumary-subtext">Tax</small>
                                                <small class="sumary-price">10,000đ</small>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="order-sumary-total">
                                        <div class="sumary-total-text">Total</div>
                                        <div class="total-price"><v:Currency_Format  price="${requestScope.TOTALPRICE}"/>đ</div>
                                    </div>
                                </div>
                            </div>
                            <div class="method-button">
                                <!--Order-->
                                <form action="DispatchServlet" method="POST">
                                    <input type="hidden" name="addressID" value="${address.addressID}"/>
                                    <input type="hidden" name="deliveryID" value="${delivery.deliveryID}"/>
                                    <input type="hidden" name="discount" value="${discount}"/>
                                    <input type="hidden" name="tax" value="10000"/>
                                    <input type="hidden" name="totalPrice" value="${totalPrice}"/>
                                    <input type="hidden" name="paymentMethod" value="${paymentMethod}"/>
                                    <button type="submit" name="btAction" value="Checkout5" class="button dark-btn">
                                        Submit
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <%
                    }
                %>
            </main>
        </div>
        <%@include file="../../common/footer.jsp" %>


        <script src="view/Assets/Js/method.js"></script>
        <script src="view/Assets/Js/popup.js"></script>
    </body>
</html>

