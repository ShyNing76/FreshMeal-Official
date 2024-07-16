<%-- 
    Document   : my_order_detail
    Created on : Jul 14, 2024, 8:15:34 AM
    Author     : ADMIN
--%>

<%@page import="java.util.HashMap"%>
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

        <style>
            .img-sumary-contain {
                transform: translate(-150%, -50%);
            }
        </style>

        <title>My Order Detail - Fresh Meal</title>
    </head>
    <body>
        <%@include file="../../common/header.jsp" %>
        <div class="wrapper">
            <main>
                <c:set var="user" value="${sessionScope.account}"/>
                <c:set var="order" value="${requestScope.OrderOfUser}"/>
                <c:set var="delivery" value="${requestScope.DeliveryOfUser}"/>
                <c:set var="address" value="${requestScope.AddressOfUser}"/>
                <c:set var="listOfProduct" value="${requestScope.ListOfProductOfOrder}"/>

                <div class="checkout-container" style="margin-top: 100px">
                    <div class="method-container">
                        <div class="method-header">
                            <div class="header-content-back">
                                <a href="DispatchServlet?btAction=MyOrder">
                                    <svg
                                        xmlns="http://www.w3.org/2000/svg"
                                        fill="none"
                                        viewBox="0 0 24 24"
                                        stroke-width="1.5"
                                        stroke="currentColor"
                                        class="size-6"
                                        >
                                    <path
                                        stroke-linecap="round"
                                        stroke-linejoin="round"
                                        d="M15.75 19.5 8.25 12l7.5-7.5"
                                        />
                                    </svg>  
                                </a>
                                <h2>Order Invoice</h2>


                            </div>
                            <div>
                                <div class="header-discription">
                                    <small class="order-id">Order Id: <span class="order-data">#${order.orderID}</span></small>
                                    <div class="separate-line"></div>
                                    <small class="order-date"
                                           >Order date: <span class="order-data">${order.orderDate}</span></small
                                    >

                                    <div class="separate-line"></div>
                                    <small class="order-date">
                                        Ship date: <span class="order-data">${order.shipDate != null ? order.shipDate : "..."}</span>
                                    </small>
                                </div>
                            </div>
                        </div>

                        <div class="method-contain">
                            <div class="order-main">          
                                <c:forEach var="orderDetail" items="${order.orderDetail}">
                                    <c:forEach var="product" items="${listOfProduct}">
                                        <c:if test="${orderDetail.productID == product.productID}">
                                            <div class="order-items">
                                                <div class="item-container">
                                                    <div class="img-order-item">
                                                        <img src="${product.image}" alt="" />
                                                    </div>
                                                    <div class="side-content" style="justify-content: space-around">
                                                        <div class="main-order-content">
                                                            <div class="order-name-item">${product.getProductName()}</div>
                                                            <div class="order-price-item"><v:Currency_Format  price="${product.getPrice()}"/>đ</div>
                                                        </div>
                                                        <div class="discription-order-content">
                                                            <small class="order-type-item">${orderDetail.getMealType()}</small>
                                                            <small class="order-quantity-item">Quantity: ${orderDetail.quantity}</small>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </c:forEach>

                            </div>
                            <div class="order-body">
                                <div class="order-payment-item">
                                    <div class="header-item">Payment</div>
                                    <div class="payment-content">
                                        <i class="fa-solid fa-dolly"></i>
                                        <div class="payment-text">${order.paymentMethod}</div>
                                    </div>
                                </div>                                       

                                <div class="order-address-item">
                                    <div class="header-item">Delivery</div>
                                    <div class="order-address">
                                        <div class="subitem-content">
                                            <small class="subitem-header">Address</small>
                                            <p>
                                                ${address.address}, ${address.district}, ${address.city}
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
                                                <small class="sumary-price">- <v:Currency_Format  price="${order.discount}"/>đ</small>
                                            </div>
                                            <div class="sumary-discription">
                                                <small class="sumary-subtext">Delivery</small>
                                                <small class="sumary-price">+ <v:Currency_Format  price="${delivery.deliveryPrice}"/>đ</small>
                                            </div>
                                            <div class="sumary-discription">
                                                <small class="sumary-subtext">Tax</small>
                                                <small class="sumary-price">+ 10,000đ</small>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="order-sumary-total">
                                        <div class="sumary-total-text">Total</div>
                                        <div class="total-price"><v:Currency_Format  price="${order.totalPrice}"/>đ</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </div>
        <%@include file="../../common/footer.jsp" %>

        <script src="view/Assets/Js/method.js"></script>
        <script src="view/Assets/Js/popup.js"></script>
    </body>
</html>
