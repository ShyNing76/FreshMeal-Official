<%-- 
    Document   : profile_edit
    Created on : Jun 21, 2024, 10:57:47 AM
    Author     : ADMIN
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Date"%>
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
                    <c:if test="${user.dateOfBirth == null}">
                        <div style="display: contents">
                            <div class="main-content">
                                <div class="main-content-header">
                                    <h1>My Profile</h1>
                                    <p>Manage profile information for account security</p>
                                </div>
                                <div class="main-infomation">
                                    <div class="info-user">
                                        <form action="DispatchServlet" action="POST">
                                            <input type="hidden" name="txtUserId" value="${user.userID}"/>
                                            <input type="hidden" name="txtCurrentPage" value="DispatchServlet?btAction=Profile"/>
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
                                                        <div>
                                                            <div class="content-edit-contain">
                                                                <input
                                                                    type="text"
                                                                    name="txtFirstName"
                                                                    placeholder=""
                                                                    class="input-edit"
                                                                    value="${user.firstName}"
                                                                    />
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="content-left">
                                                        <label for="">LastName</label>
                                                    </td>
                                                    <td class="content-right">
                                                        <div>
                                                            <div class="content-edit-contain">
                                                                <input
                                                                    type="text"
                                                                    name="txtLastName"
                                                                    placeholder=""
                                                                    class="input-edit"
                                                                    value="${user.lastName}"
                                                                    />
                                                            </div>
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
                                                        <div>
                                                            <div class="content-edit-contain">
                                                                <input
                                                                    type="text"
                                                                    name="txtPhone"
                                                                    placeholder=""
                                                                    class="input-edit"
                                                                    value="${user.phone}"
                                                                    required=""
                                                                    />
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="content-left">
                                                        <label for="">Gender</label>
                                                    </td>
                                                    <td class="content-right">
                                                        <div>
                                                            <div class="radio-edit-contain">
                                                                <div
                                                                    class="stardust-radio-group"
                                                                    role="radiogroup"
                                                                    >
                                                                    <input type="radio" name="txtGender" value="Male" <c:if test="${user.gender == 'Male'}">checked=""</c:if>/>
                                                                        <div class="stardust-radio-content">
                                                                            <div class="stardust-radio-label">Male</div>
                                                                        </div>
                                                                        <input type="radio" name="txtGender" value="Female" <c:if test="${user.gender == 'Female'}">checked=""</c:if>/>
                                                                        <div class="stardust-radio-content">
                                                                            <div class="stardust-radio-label">
                                                                                Female
                                                                            </div>
                                                                        </div>
                                                                        <input type="radio" name="txtGender" value="Others" <c:if test="${user.gender == 'Others'}">checked=""</c:if>/>
                                                                        <div class="stardust-radio-content">
                                                                            <div class="stardust-radio-label">
                                                                                Others
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="content-left">
                                                            <label for="">DateOfBirth</label>
                                                        </td>
                                                        <td class="content-right">
                                                            <div class="date-edit-contain">
                                                                <div class="date-item-user" id="day" style="position: relative">
                                                                    <input type="date" name="txtDate" value="${user.dateOfBirth}" required=""/>
                                                            </div>

                                                        </div>
                                                    </td>
                                                </tr>
                                            </table>
                                            <button class="button dark-btn" type="sumit" name="btAction" value="SaveEditProfile">Save</button>
                                        </form>
                                    </div>

                                    <form id="imageUploadForm" action="DispatchServlet" method="POST">
                                        <input type="hidden" name="btAction" value="UpdateImageProfile"/>
                                        <input type="hidden" name="txtCurrentPage" value="DispatchServlet?btAction=EditProfile"/>
                                        <div class="user-img-contain">
                                            <div class="img-contain">
                                                <div class="user-img">
                                                    <c:if test="${user.image != null}">
                                                        <img src="${user.image}" alt="user-img" />
                                                    </c:if>
                                                    <c:if test="${user.image == null}">
                                                        <img src="view/Assets/Images/user/default.jpg" alt="user-img" />
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
                    </c:if>
                    <c:if test="${user.dateOfBirth != null}">
                        <div style="display: contents">
                            <div class="main-content">
                                <div class="main-content-header">
                                    <h1>My Profile</h1>
                                    <p>Manage profile information for account security</p>
                                </div>
                                <div class="main-infomation">
                                    <div class="info-user">
                                        <form action="DispatchServlet" action="POST">
                                            <input type="hidden" name="txtUserId" value="${user.userID}"/>
                                            <input type="hidden" name="txtCurrentPage" value="DispatchServlet?btAction=Profile"/>
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
                                                        <div>
                                                            <div class="content-edit-contain">
                                                                <input
                                                                    type="text"
                                                                    name="txtFirstName"
                                                                    placeholder=""
                                                                    class="input-edit"
                                                                    value="${user.firstName}"
                                                                    />
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="content-left">
                                                        <label for="">LastName</label>
                                                    </td>
                                                    <td class="content-right">
                                                        <div>
                                                            <div class="content-edit-contain">
                                                                <input
                                                                    type="text"
                                                                    name="txtLastName"
                                                                    placeholder=""
                                                                    class="input-edit"
                                                                    value="${user.lastName}"
                                                                    />
                                                            </div>
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
                                                        <div>
                                                            <div class="content-edit-contain">
                                                                <input
                                                                    type="text"
                                                                    name="txtPhone"
                                                                    placeholder=""
                                                                    class="input-edit"
                                                                    value="${user.phone}"
                                                                    />
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="content-left">
                                                        <label for="">Gender</label>
                                                    </td>
                                                    <td class="content-right">
                                                        <div>
                                                            <div class="radio-edit-contain">
                                                                <div
                                                                    class="stardust-radio-group"
                                                                    role="radiogroup"
                                                                    >
                                                                    <input type="radio" name="txtGender" value="Male" <c:if test="${user.gender == 'Male'}">checked=""</c:if>/>
                                                                        <div class="stardust-radio-content">
                                                                            <div class="stardust-radio-label">Male</div>
                                                                        </div>

                                                                        <input type="radio" name="txtGender" value="Female" <c:if test="${user.gender == 'Female'}">checked=""</c:if>/>
                                                                        <div class="stardust-radio-content">
                                                                            <div class="stardust-radio-label">
                                                                                Female
                                                                            </div>
                                                                        </div>

                                                                        <input type="radio" name="txtGender" value="Others" <c:if test="${user.gender == 'Others'}">checked=""</c:if>/>
                                                                        <div class="stardust-radio-content">
                                                                            <div class="stardust-radio-label">
                                                                                Others
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="content-left">
                                                            <label for="">DateOfBirth</label>
                                                        </td>
                                                        <td class="content-right">
                                                            <div class="date-edit-contain">
                                                                <div class="date-item-user" id="day" style="position: relative">
                                                                    <input type="date" name="txtDate" value="${user.dateOfBirth}" required=""/>
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </table>
                                            <button class="button dark-btn" type="sumit" name="btAction" value="SaveEditProfile">Save</button>
                                        </form>
                                    </div>
                                    <form id="imageUploadForm" action="DispatchServlet" method="POST">
                                        <input type="hidden" name="btAction" value="UpdateImageProfile"/>
                                        <input type="hidden" name="txtCurrentPage" value="DispatchServlet?btAction=EditProfile"/>
                                        <div class="user-img-contain">
                                            <div class="img-contain">
                                                <div class="user-img">
                                                    <c:if test="${user.image != null}">
                                                        <img src="${user.image}" alt="user-img" />
                                                    </c:if>
                                                    <c:if test="${user.image == null}">
                                                        <img src="view/Assets/Images/user/default.jpg" alt="user-img" />
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
                    </c:if>
                </div>
            </main>
            <%@include file="../../common/footer.jsp" %>
            <script src="view/Assets/Js/profile.js"></script>
    </body>
</html>

