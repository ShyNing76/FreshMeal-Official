<%-- 
    Document   : cart
    Created on : Jun 27, 2024, 1:07:21 AM
    Author     : ADMIN
--%>

<%@page import="java.util.HashMap"%>
<%@page import="dto.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/WEB-INF/tlds/mytags.tld" prefix="v" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="icon" href="view/Assets/Images/logo.png" />

        <!-- Css -->
        <link rel="stylesheet" href="view/Assets/css/style.css" />
        <link rel="stylesheet" href="view/Assets/css/cart.css">
        <link rel="stylesheet" href="view/Assets/css/menu-info.css">

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

        <title>Fresh Meal</title>

    </head>
    <body>
        <%@include file="../../common/header.jsp" %>
        <%            HashMap<Product, Integer> cart = (HashMap<Product, Integer>) session.getAttribute("CART");
            if (cart != null) {
        %>
        <main>
            <div class="cart-container">
                <div class="cart">
                    <div class="cart-items">
                        <div class="cart-items-title">
                            <p>Items</p>
                            <p>Title</p>
                            <p>Type</p>
                            <p>Price</p>
                            <p>Quantity</p>
                            <p>Remove</p>
                        </div>
                        <br>
                        <hr>

                        <%        for (Product product : cart.keySet()) {
                        %>        
                        <div class="cart-box">
                            <div class="cart-items-title cart-items-item">
                                <img src="<%= product.getImage() %>" alt="">
                                <p class="cart-title"><%= product.getProductName()%></p>
                                <p><%=product.getMealType()%></p>
                                <p><v:Currency_Format  price="<%= product.getPrice()%>"/>đ</p>
                                <form action="DispatchServlet" method="POST" class="update-quantity-form">
                                    <input type="hidden" name="txtRemoveProductId" value="<%= product.getProductID()%>"/>
                                    <input type="hidden" name="btAction" value="UpdateItemOfCart"/>
                                    <p class="quantity-cart">
                                        <button class="button-quantity decrement" >
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
                                            value="<%= cart.get(product)%>"
                                            name="txtQuantity"
                                            class="button-quantity input-quantity"
                                            readonly=""
                                            />
                                        <button class="button-quantity increment">
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
                                    </p>
                                </form>

                                <form action="DispatchServlet" method="POST">
                                    <input type="hidden" name="txtRemoveProductId" value="<%= product.getProductID()%>"/>
                                    <button type="submit" name="btAction" value="DeleteItemOfCart" class="remove-btn"><i class="fa-solid fa-trash-can"></i></button>
                                </form>
                            </div>
                            <hr>
                        </div>
                        <%}%>

                        <div class="cart-bottom">
                            <div class="cart-total">
                                <h2>Cart Totals</h2>
                                <div>
                                    <div class="cart-total-details">
                                        <p>Subtotal</p>
                                        <p><v:Currency_Format  price="${requestScope.SUBTOTALPRICE}"/>đ</p>
                                    </div>
                                    <hr>
                                    <c:if test="${requestScope.DISCOUNT != null}">
                                        <div class="cart-total-details">
                                            <p>Discount (-10%)</p>
                                            <p><v:Currency_Format  price="${requestScope.DISCOUNT}"/>đ</p>
                                        </div>
                                        <hr>
                                    </c:if>

                                    <div class="cart-total-details">
                                        <p>Tax</p>
                                        <% if (!cart.isEmpty()) {%>
                                        <p><v:Currency_Format  price="${requestScope.TAX}"/>đ</p>
                                        <%   } else {%>
                                        <p>0đ</p>
                                        <%}
                                        %>  
                                    </div>
                                    <hr>                                    
                                    <div class="cart-total-details">
                                        <b>Total:</b>
                                        <% if (!cart.isEmpty()) {%>
                                        <b><v:Currency_Format  price="${requestScope.TOTALPRICE}"/>đ</b>
                                        <%   } else { %>
                                        <p>0đ</p>
                                        <%}
                                        %>
                                    </div>
                                </div>
                                <form action="DispatchServlet" method="POST">
                                    <input type="hidden" name="SUBTOTALPRICE" value="${requestScope.SUBTOTALPRICE}"/>
                                    <input type="hidden" name="DISCOUNT" value="${requestScope.DISCOUNT}"/>
                                    <input type="hidden" name="TAX" value="${requestScope.TAX}"/>
                                    <input type="hidden" name="TOTALPRICE" value="${requestScope.TOTALPRICE}"/>
                                    <button type="submit" name="btAction" value="Checkout1" class="button dark-btn checkoutbtn">PROCEED TO CHECKOUT</button>
                                    <p>${requestScope.Error}</p>
                                </form>

                            </div>
                            <div class="cart-promocode">
                                <form action="DispatchServlet" method="POST" accept-charset="UTF-8">
                                    <div>
                                        <p>If you have a promo code, Enter it here</p>
                                        <div class="cart-promocode-input">
                                            <input type="text" placeholder="Promo code" name="txtPromoCode" value="${requestScope.PromoCode}">
                                            <button type="submit" name="btAction" value="PromoCode">Submit</button>
                                        </div>
                                    </div>
                                </form>
                                <p>${requestScope.InvalidCode}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <%    } else {
        %>
        <main>
            <div class="cart-container">
                <div class="cart">
                    <div class="cart-items">
                        <img src="view/Assets/Images/cart/emptycart1.png" alt=""/>
                        <br>
                        <hr>
                        <div class="cart-bottom">
                            <div class="cart-total">
                                <h2>Cart Totals</h2>
                                <div>
                                    <div class="cart-total-details">
                                        <p>Subtotal</p>
                                        <p>0đ</p>
                                    </div>
                                    <hr>
                                    <div class="cart-total-details">
                                        <p>Tax</p>
                                        <p>0đ</p>
                                    </div>
                                    <hr>
                                    <div class="cart-total-details">
                                        <b>Total:</b>
                                        <b>0đ</b>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <%    }
        %>
        <script src="view/Assets/Js/cart.js"></script>
    </body>
</html>
