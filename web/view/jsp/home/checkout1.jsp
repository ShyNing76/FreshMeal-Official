<%-- 
    Document   : checkout1
    Created on : Jul 2, 2024, 4:32:56 PM
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
            .stardust-radio {
                position: relative;
                cursor: pointer;
                width: 100%;
            }

            .stardust-radio input[type="radio"] {
                display: none; /* Ẩn radio button mặc định */
            }

            .stardust-radio-content {
                display: block;
                width: 100%;
                padding: 1rem;
                border: 1px solid #ccc;
                border-radius: 8px;
                box-sizing: border-box;
                transition: border-color 0.3s ease, background-color 0.3s ease;
            }

            .stardust-radio-content:hover {
                border-color: #888;
            }

            .address-heading-section {
                font-weight: bold;
                font-size: 1.2rem;
                color: #333;
            }

            .address-user-contain {
                display: flex;
                justify-content: space-between;
                align-items: center;
            }


            .address-user .address-text {
                color: #666;
                font-size: 0.9rem;
            }
            
            .address-text{
                min-height: 3vh;
            }

            /* CSS cho form */
            .address-header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 1rem;
            }

            .address-header-text h1 {
                margin: 0;
                font-size: 1.5rem;
            }

            .address-button-header .button {
                display: flex;
                align-items: center;
                justify-content: center;
            }

            .address-group .address-contain {
                margin-bottom: 1rem;
            }

            .button-container {
                display: flex;
                justify-content: flex-end;
                margin-top: 1rem;
            }

        </style>

        <title>CheckOut - Fresh Meal</title>
    </head>
    <body>
        <div class="wrapper">
            <div class="step">
                <a href="DispatchServlet"><img src="view/Assets/Images/logo.png" alt="" /></a>

                <ul class="step-list">
                    <li class="step-item current-item">
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
                <div class="checkout-container">
                    <div class="side-address">
                        <div class="address-main" role="main">
                            <div class="address-item-main">
                                <div class="address-header">
                                    <div class="address-header-text">
                                        <h1>My Address</h1>
                                    </div>
                                    <div class="address-button-header">
                                        <button id="addNewAddressBtn" class="button dark-btn">
                                            <i class="fa-solid fa-plus"></i> Add New Address
                                        </button>
                                    </div>
                                </div>
                                <p>${requestScope.ERROR}</p>
                                <form action="DispatchServlet" method="POST">
                                    <div class="address-content">
                                        <div class="address-group">
                                            <!-- Address Item -->
                                            <c:set var="ListOfAddressOfUser" value="${requestScope.ListOfAddressOfUser}"/>
                                            <c:set var="selectedAddress" value="${requestScope.SELECTEDADDRESS}"/>
                                            <c:if test="${ListOfAddressOfUser != null}">
                                                <c:forEach var="address" items="${ListOfAddressOfUser}">
                                                    <div class="address-contain">
                                                        <div class="address-main">
                                                            <div class="stardust-radio" role="radio">
                                                                <input id="address-${address.addressID}" type="radio" name="selectedAddress" value="${address.addressID}" <c:if test="${selectedAddress == address.addressID}">checked=""</c:if>/> 
                                                                <label for="address-${address.addressID}" style="width: 100%">
                                                                    <div class="stardust-radio-content">
                                                                        <div role="heading" class="address-heading-section">
                                                                            <div class="address-user-contain">
                                                                                <span class="name-address-section">
                                                                                    <div class="name-address">${address.district}</div>
                                                                                </span>
                                                                                <div role="row" class="phone-address-user">
                                                                                    ${address.city}
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <div role="heading" class="address-heading-section">
                                                                            <div class="address-user-contain">
                                                                                <div class="address-user" style="width: 100%">
                                                                                    <div role="row" class="address-text">
                                                                                        ${address.address}
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </c:if>
                                        </div>
                                    </div>
                                    <div class="button-container">
                                        <input type="hidden" name="SUBTOTALPRICE" value="${requestScope.SUBTOTALPRICE}"/>
                                        <input type="hidden" name="DISCOUNT" value="${requestScope.DISCOUNT}"/>
                                        <input type="hidden" name="TAX" value="${requestScope.TAX}"/>
                                        <input type="hidden" name="TOTALPRICE" value="${requestScope.TOTALPRICE}"/>
                                        <button type="submit" name="btAction" value="Checkout2" class="button dark-btn">Continue</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </main>

            <aside id="addressForm" class="form-container" role="dialog" tabindex="0" >
                <div class="form-address">
                    <div class="form-address-item">
                        <div class="form-address-text">New Address</div>
                        <form action="DispatchServlet" action="POST">
                            <input type="hidden" name="currentPage" value="Checkout1Servlet"/>
                            <div class="inside-form">
                                <div class="form-main-content">
                                    <div class="form-main-item">
                                        <div class="form-main name-item">
                                            <div class="form-main-contain">
                                                <select name="selectedDistrict">
                                                    <option value="0">SELECT DISTRICT</option>
                                                    <option>Thành Phố Thủ Đức</option>
                                                    <option>Quận 1</option>
                                                    <option>Quận 2</option>
                                                    <option>Quận 3</option>
                                                    <option>Quận 4</option>
                                                    <option>Quận 5</option>
                                                    <option>Quận 6</option>
                                                    <option>Quận 7</option>
                                                    <option>Quận 8</option>
                                                    <option>Quận 9</option>
                                                    <option>Quận 10</option>
                                                    <option>Quận 11</option>
                                                    <option>Quận 12</option>
                                                    <option>Quận Bình Tân</option>
                                                    <option>Quận Bình Thạnh</option>
                                                    <option>Quận Gò Vấp</option>
                                                    <option>Quận Phú Nhuận</option>
                                                    <option>Quận Tân Bình</option>
                                                    <option>Quận Tân Phú</option>
                                                    <option>Huyện Bình Chánh</option>
                                                    <option>Huyện Cần Giờ</option>
                                                    <option>Huyện Củ Chi</option>
                                                    <option>Huyện Hóc Môn</option>
                                                    <option>Huyện Nhà Bè</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="seperate-box"></div>
                                        <div class="form-main phone-item">
                                            <div class="form-main-contain">
                                                <p>HỒ CHÍ MINH CITY</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-main-item">
                                        <div class="form-main-address">
                                            <div class="address-form-contain">
                                                <div class="form-main-contain address-area-large">
                                                    <textarea
                                                        class="address-main-area"
                                                        rows="4"
                                                        placeholder="Detail Address"
                                                        maxlength="256"
                                                        name="txtdetailAddress"
                                                        ></textarea>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-button">
                                    <button id="addReturnBtn" class="button">Return</button>
                                    <div class="seperate-box"></div>
                                    <button type="submit" name="btAction" value="addNewMyAddress" class="button dark-btn">Finish</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="background-form"></div>
            </aside>
        </div>
        <%@include file="../../common/footer.jsp" %>



        <script src="view/Assets/Js/address.js"></script>
    </body>
</html>

