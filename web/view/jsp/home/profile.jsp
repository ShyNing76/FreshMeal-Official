<%-- 
    Document   : profile
    Created on : Jun 21, 2024, 10:56:56 AM
    Author     : ADMIN
--%>

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
        <link rel="stylesheet" href="view/Assets/css/profile.css" />
        <link rel="stylesheet" href="view/Assets/css/sidemenu.css">

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
                    <div style="display: contents">
                        <div class="main-content">
                            <div class="main-content-header">
                                <h1>My Profile</h1>
                                <p>Manage profile information for account security</p>
                            </div>
                            <div class="main-infomation">
                                <div class="info-user">
                                    <form action="">
                                        <table class="table-info">
                                            <tr>
                                                <td class="content-left">
                                                    <label for="">UserName</label>
                                                </td>
                                                <td class="content-right">
                                                    <div class="content-contain">
                                                        <div>${user.userName}</div>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="content-left">
                                                    <label for="">FirstName</label>
                                                </td>
                                                <td class="content-right">
                                                    <div class="content-contain">
                                                        <div>${user.firstName}</div>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="content-left">
                                                    <label for="">LastName</label>
                                                </td>
                                                <td class="content-right">
                                                    <div class="content-contain">
                                                        <div>${user.lastName}</div>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="content-left">
                                                    <label for="">Email</label>
                                                </td>
                                                <td class="content-right">
                                                    <div class="content-contain">
                                                        <div>${user.email}</div>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="content-left">
                                                    <label for="">Phone</label>
                                                </td>
                                                <td class="content-right">
                                                    <div class="content-contain">
                                                        <div>${user.phone}</div>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="content-left">
                                                    <label for="">Gender</label>
                                                </td>
                                                <td class="content-right">
                                                    <div class="content-contain">
                                                        <div>${user.gender}</div>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="content-left">
                                                    <label for="">DateOfBirth</label>
                                                </td>
                                                <td class="content-right">
                                                    <div class="content-contain">
                                                        <div>${user.dateOfBirth}</div>
                                                    </div>
                                                </td>
                                            </tr>
                                        </table>
                                    </form>
                                    <a href="DispatchServlet?btAction=EditProfile" class="button dark-btn">Edit information</a>
                                </div>
                                <form id="imageUploadForm" action="DispatchServlet" method="POST">
                                    <input type="hidden" name="btAction" value="UpdateImageProfile"/>
                                    <input type="hidden" name="txtCurrentPage" value="DispatchServlet?btAction=Profile"/>
                                    <div class="user-img-contain">
                                        <div class="img-contain">
                                            <div class="user-img">
                                                <c:if test="${user.image != null}">
                                                    <img src="${user.image}" alt="user-img" />
                                                </c:if>
                                                <c:if test="${user.image == null}">
                                                    <img src="<%= user.getImage() != null ? user.getImage() : "view/Assets/Images/user/default.jpg" %>" alt="user-img" />
                                                </c:if>
                                            </div>
                                            <input
                                                class="input-user-img"
                                                id="input-file"
                                                type="file"
                                                name="txtImage"
                                                accept=".jpg,.jpeg,.png"
                                                onchange="handleFileSelect(event)"
                                                />
                                            <label for="input-file" class="button dark-btn">Update Image</label>
                                            <div class="user-img-discription">
                                                <div class="discription-text">Maximum file size is 1 MB</div>
                                                <div class="discription-text">Format: .JPEG, .PNG</div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
            <%@include file="../../common/footer.jsp" %>
        </div>
        <script src="view/Assets/Js/profile.js"></script>
    </body>
</html>

