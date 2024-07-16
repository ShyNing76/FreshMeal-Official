<%-- 
    Document   : my_address
    Created on : Jul 1, 2024, 9:14:37 PM
    Author     : ADMIN
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="dto.Address"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/WEB-INF/tlds/mytags.tld" prefix="p" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <link rel="icon" href="view/Assets/Images/logo.png" />

        <!-- Css -->
        <link rel="stylesheet" href="view/Assets/css/style.css" />
        <link rel="stylesheet" href="view/Assets/css/address.css" />
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
                <div class="container-user">
                    <!-- side-user -->
                    <p:Profile_SideBar_Tag userID="${user.userID}"/>
                    <!-- Profile user -->
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

                                <div class="address-content">
                                    <div class="address-group">
                                        <!-- Address Item -->
                                        <c:set var="ListOfAddressOfUser" value="${requestScope.ListOfAddressOfUser}"/>
                                        <c:if test="${ListOfAddressOfUser != null}">
                                            <c:forEach var="address" items="${ListOfAddressOfUser}">
                                                <div class="address-contain">
                                                    <div class="address-main">
                                                        <div role="heading" class="address-heading-section">
                                                            <div class="address-user-contain">
                                                                <span class="name-address-section">
                                                                    <div class="name-address">${address.district}</div>
                                                                </span>
                                                                <div class="separate-line"></div>
                                                                <div role="row" class="phone-address-user">
                                                                    ${address.city}
                                                                </div>
                                                            </div>
                                                            <div class="update-delete-address">
                                                                <button class="update-address-btn update-button">Update</button>

                                                                <form action="DispatchServlet" method="POST">
                                                                    <input type="hidden" name="addressID" value="${address.addressID}"/>
                                                                    <input type="hidden" name="currentPage" value="DispatchServlet?btAction=MyAddress"/>
                                                                    <button class="delete-address-btn" name="btAction" value="deleteMyAddress">Delete</button>
                                                                </form>
                                                            </div>
                                                        </div>
                                                        <div role="heading" class="address-heading-section">
                                                            <div class="address-user-contain">
                                                                <div class="address-user">
                                                                    <div role="row" class="address-text">
                                                                        ${address.address}
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- Form update -->
                                                <aside id="updateAddressForm" class="form-container" role="dialog" tabindex="0" >
                                                    <div class="form-address">
                                                        <div class="form-address-item">
                                                            <div class="form-address-text">Update Address</div>
                                                            <form action="DispatchServlet" method="POST">
                                                                <input type="hidden" name="addressID" value="${address.addressID}"/>
                                                                <div class="inside-form">
                                                                    <div class="form-main-content">
                                                                        <div class="form-main-item">
                                                                            <div class="form-main name-item">
                                                                                <div class="form-main-contain">
                                                                                    <select name="selectedDistrict">
                                                                                        <option value="0">${address.district}</option>
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
                                                                                            >${address.address}</textarea>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-button">
                                                                        <button id="updateReturnBtn" class="button">Return</button>
                                                                        <div class="seperate-box"></div>
                                                                        <button type="submit" name="btAction" value="UpdateMyAddress" class="button dark-btn">Finish</button>
                                                                    </div>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                    <div class="background-form"></div>
                                                </aside>
                                            </c:forEach>
                                        </c:if>
                                    </div>
                                </div>
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
                            <input type="hidden" name="currentPage" value="DispatchServlet?btAction=MyAddress"/>
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



            <%@include file="../../common/footer.jsp" %>
            <!-- <script src="Js/index.js"></script> -->
            <script src="view/Assets/Js/address.js"></script>
    </body>
</html>

