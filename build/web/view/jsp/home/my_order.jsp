<%-- 
    Document   : my_order
    Created on : Jul 1, 2024, 8:16:36 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/WEB-INF/tlds/mytags.tld" prefix="p" %>
<%@taglib uri="/WEB-INF/tlds/mytags.tld" prefix="v" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <link rel="icon" href="view/Assets/Images/logo.png" />

        <!-- Css -->
        <link rel="stylesheet" href="view/Assets/css/style.css" />
        <link rel="stylesheet" href="view/Assets/css/order.css" />
        <link rel="stylesheet" href="view/Assets/css/sidemenu.css" />

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

        <title>Profile - FreshMeal</title>
    </head>
    <body>
        <div class="wrapper">
            <header>
                <%@include file="../../common/header.jsp" %>
            </header>
            <c:set var="user" value="${sessionScope.account}"/>
            <main>
                <c:set var="ListOfOrderOfUser" value="${requestScope.ListOfOrderOfUser}"/>
                <c:set var="ListOfProductOfOrder" value="${requestScope.ListOfProductOfOrder}"/>
                <div class="container-user">
                    <!-- side-user -->
                    <p:Profile_SideBar_Tag userID="${user.userID}"/>
                    <!-- order user -->
                    <div class="side-order">
                        <div style="display: contents">
                            <div class="order-contain">
                                <section class="progress-order" aria-role="tablist">
                                    <a class="progress-aria progress-choose" title="all" href="DispatchServlet?btAction=MyOrder"
                                       ><span class="progress-text">All</span></a
                                    ><a class="progress-aria" title="Pending" href="DispatchServlet?btAction=PendingOrder"
                                        ><span class="progress-text">Pending</span></a
                                    ><a class="progress-aria" title="Delivery" href="DispatchServlet?btAction=ApproveOrder"
                                        ><span class="progress-text">Approve</span></a
                                    ><a class="progress-aria" title="Waiting" href="DispatchServlet?btAction=DeliveryOrder"
                                        ><span class="progress-text">Delivery</span></a
                                    ><a class="progress-aria" title="Finish" href="DispatchServlet?btAction=FinishOrder"
                                        ><span class="progress-text">Finish</span></a
                                    ><a class="progress-aria" title="Cancel" href="DispatchServlet?btAction=CancelOrder"
                                        ><span class="progress-text">Cancel</span></a
                                    ><a class="progress-aria" title="Return/Refund" href="DispatchServlet?btAction=RefurnOrder"
                                        ><span class="progress-text">Return/Refurn</span></a>
                                </section>

                                <main>
                                    <c:if test="${ListOfOrderOfUser != null}">
                                        <c:if test="${ListOfProductOfOrder != null}">
                                            <c:forEach var="order" items="${ListOfOrderOfUser}">
                                                <a href="DispatchServlet?btAction=MyOrderDetail&OrderID=${order.orderID}">
                                                    <div>
                                                        <div class="order-box">
                                                            <div>
                                                                <div class="order-main">
                                                                    <section>
                                                                        <div class="order-header">
                                                                            <div class="progress-order-header" style="display: flex; justify-content: space-between; width: 100%;">
                                                                                <div style="color: #6eb83d;">Order #${order.orderID}</div>
                                                                                <div class="progress-header-text" tabindex="0">
                                                                                    ${order.status}
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </section>
                                                                    <c:forEach var="orderDetail" items="${order.orderDetail}">
                                                                        <c:if test="${orderDetail.orderID == order.orderID}">
                                                                            <c:forEach var="product" items="${ListOfProductOfOrder}">
                                                                                <c:if test="${orderDetail.productID == product.productID}">
                                                                                    <section>
                                                                                        <div>
                                                                                            <div class="order-detail-contain">
                                                                                                <div class="order-detail">
                                                                                                    <div>
                                                                                                        <section>
                                                                                                            <div class="order-detail-content">
                                                                                                                <div class="order-detail-text">
                                                                                                                    <img
                                                                                                                        src="${product.image}"
                                                                                                                        alt=""
                                                                                                                        class="order-img"
                                                                                                                        />
                                                                                                                    <div class="order-discription">
                                                                                                                        <div>
                                                                                                                            <div
                                                                                                                                class="order-discription-text"
                                                                                                                                >
                                                                                                                                <span class="discription-text"
                                                                                                                                      >${product.productName}</span
                                                                                                                                >
                                                                                                                            </div>
                                                                                                                        </div>
                                                                                                                        <div>
                                                                                                                            <div class="order-type">
                                                                                                                                Type:
                                                                                                                                <div
                                                                                                                                    class="order-tags-contain"
                                                                                                                                    >
                                                                                                                                    <span class="order-tags">
                                                                                                                                        <i
                                                                                                                                            class="fa-solid fa-box"
                                                                                                                                            ></i>
                                                                                                                                        <small>${orderDetail.mealType}</small>
                                                                                                                                    </span>
                                                                                                                                </div>
                                                                                                                            </div>
                                                                                                                            <div class="order-quantity">
                                                                                                                                Quantity: <b>x${orderDetail.quantity}</b>
                                                                                                                            </div>

                                                                                                                        </div>
                                                                                                                    </div>
                                                                                                                </div>
                                                                                                                <div class="order-detail-price">
                                                                                                                    <div class="order-price">
                                                                                                                        <span class="price"><v:Currency_Format  price="${product.price}"/>đ</span>
                                                                                                                    </div>
                                                                                                                </div>
                                                                                                            </div>
                                                                                                        </section>
                                                                                                    </div>
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>
                                                                                    </section>
                                                                                </c:if>
                                                                            </c:forEach>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </div>
                                                            </div>
                                                            <div class="seperate-box">
                                                                <div class="line line-left"></div>
                                                                <div class="line line-right"></div>
                                                            </div>
                                                            <div class="order-final-price">
                                                                <div class="order-price-contain">
                                                                    <svg
                                                                        xmlns="http://www.w3.org/2000/svg"
                                                                        fill="none"
                                                                        viewBox="0 0 24 24"
                                                                        stroke-width="1.5"
                                                                        stroke="currentColor"
                                                                        class="icon-dollar"
                                                                        >
                                                                    <path
                                                                        stroke-linecap="round"
                                                                        stroke-linejoin="round"
                                                                        d="M12 6v12m-3-2.818.879.659c1.171.879 3.07.879 4.242 0 1.172-.879 1.172-2.303 0-3.182C13.536 12.219 12.768 12 12 12c-.725 0-1.45-.22-2.003-.659-1.106-.879-1.106-2.303 0-3.182s2.9-.879 4.006 0l.415.33M21 12a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z"
                                                                        />
                                                                    </svg>
                                                                    <label class="order-price-text">Order Total: </label>
                                                                    <div class="final-price"><v:Currency_Format  price="${order.totalPrice}"/>đ</div>
                                                                </div>
                                                            </div>
                                                            <c:if test="${order.status == 'Pending' || order.status == 'Approve'}">
                                                                <form action="DispatchServlet" method="POST">
                                                                    <input type="hidden" name="orderID" value="${order.orderID}"/>
                                                                    <div class="order-option">
                                                                        <section class="option-section">
                                                                            <div class="seperate-button"></div>
                                                                            <button class="button" type="submit" name="btAction" value="RequestForCancelOrder">
                                                                                Cancel
                                                                            </button>
                                                                        </section>
                                                                    </div>
                                                                </form>
                                                            </c:if>

                                                            <c:if test="${order.status == 'Finish'}">
                                                                <form action="DispatchServlet" method="POST">
                                                                    <input type="hidden" name="orderID" value="${order.orderID}"/>
                                                                    <div class="order-option">
                                                                        <section class="option-section">
                                                                            <button class="button dark-btn">Rate</button>
                                                                            <div class="seperate-button"></div>
                                                                            <button class="button" type="submit" name="btAction" value="RequestForRefurnOrder">
                                                                                Refurn/Return
                                                                            </button>
                                                                        </section>
                                                                    </div>
                                                                </form>
                                                            </c:if>

                                                        </div>
                                                    </div>
                                                </a>
                                            </c:forEach>
                                        </c:if>
                                    </c:if>
                                </main>
                            </div>
                        </div>
                    </div>
                </div>
            </main>

            <%@include file="../../common/footer.jsp" %>

            <script src="view/Assets/Js/order.js"></script>
            <!-- <script src="Js/index.js"></script> -->
    </body>
</html>

