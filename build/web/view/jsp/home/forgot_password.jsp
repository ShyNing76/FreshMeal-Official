<%-- 
    Document   : forgot_password
    Created on : Jul 8, 2024, 3:38:36 AM
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
        <link rel="stylesheet" href="view/Assets/css/passwordchange.css" />
        <link rel="stylesheet" href="view/Assets/css/sidemenu.css">
        <link rel="stylesheet" href="view/Assets/css/profile.css">

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
                <a href="DispatchServlet"><img class="logo" src="view/Assets/Images/logo.png" alt="" /></a>
            </header>
            <c:set var="user" value="${requestScope.user}"/>
            <main>
                <div class="container-user">
                    <!-- Profile user -->
                    <div style="display: contents">
                        <div class="main-content main-password">
                            <div class="main-content-header">
                                <h1>Forgot Password</h1>
                            </div>
                            <div class="main-infomation">
                                <div class="info-user">
                                    <c:if test="${user == null}">
                                        <p>${requestScope.EMAILERROR}</p>
                                        <form action="DispatchServlet" method="POST">
                                            <table class="table-info">
                                                <tr>
                                                    <td class="content-left">
                                                        <label for="">Email</label>
                                                    </td>
                                                    <td class="content-right">
                                                        <div>
                                                            <div class="content-edit-contain">
                                                                <input
                                                                    type="email"
                                                                    placeholder="Enter your email"
                                                                    class="input-edit"
                                                                    name="txtEmail"
                                                                    required
                                                                    />
                                                                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="eye-icon eye-close hidden-icon">
                                                                <path stroke-linecap="round" stroke-linejoin="round" d="M3.98 8.223A10.477 10.477 0 0 0 1.934 12C3.226 16.338 7.244 19.5 12 19.5c.993 0 1.953-.138 2.863-.395M6.228 6.228A10.451 10.451 0 0 1 12 4.5c4.756 0 8.773 3.162 10.065 7.498a10.522 10.522 0 0 1-4.293 5.774M6.228 6.228 3 3m3.228 3.228 3.65 3.65m7.894 7.894L21 21m-3.228-3.228-3.65-3.65m0 0a3 3 0 1 0-4.243-4.243m4.242 4.242L9.88 9.88" />
                                                                </svg>    
                                                                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="eye-icon eye-open ">
                                                                <path stroke-linecap="round" stroke-linejoin="round" d="M2.036 12.322a1.012 1.012 0 0 1 0-.639C3.423 7.51 7.36 4.5 12 4.5c4.638 0 8.573 3.007 9.963 7.178.07.207.07.431 0 .639C20.577 16.49 16.64 19.5 12 19.5c-4.638 0-8.573-3.007-9.963-7.178Z" />
                                                                <path stroke-linecap="round" stroke-linejoin="round" d="M15 12a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z" />
                                                                </svg>                                                                                    
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </table>
                                            <button class="button dark-btn" type="submit" name="btAction" value="ForgotPassword">Enter</button>
                                        </form>
                                    </c:if>
                                    <c:if test="${user != null}">
                                        <p>${requestScope.ANNOUNCEMENT}</p>
                                        <form action="DispatchServlet" method="POST">
                                            <input type="hidden" name="txtUserEmail" value="${user.email}"/>
                                            <!--<img src="${user.image}" alt="user-img"/>--> 
                                            <p>${user.email}</p>
                                            <p>${user.firstName}</p>
                                            <p>${user.lastName}</p>
                                            <table class="table-info">
                                                <tr>
                                                    <td class="content-left">
                                                        <label for="">New Password</label>
                                                    </td>
                                                    <td class="content-right">
                                                        <div>
                                                            <div class="content-edit-contain">
                                                                <input
                                                                    type="password"
                                                                    placeholder=""
                                                                    class="input-edit"
                                                                    name="txtNewPassword"
                                                                    required
                                                                    />
                                                                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="eye-icon  eye-close hidden-icon">
                                                                <path stroke-linecap="round" stroke-linejoin="round" d="M3.98 8.223A10.477 10.477 0 0 0 1.934 12C3.226 16.338 7.244 19.5 12 19.5c.993 0 1.953-.138 2.863-.395M6.228 6.228A10.451 10.451 0 0 1 12 4.5c4.756 0 8.773 3.162 10.065 7.498a10.522 10.522 0 0 1-4.293 5.774M6.228 6.228 3 3m3.228 3.228 3.65 3.65m7.894 7.894L21 21m-3.228-3.228-3.65-3.65m0 0a3 3 0 1 0-4.243-4.243m4.242 4.242L9.88 9.88" />
                                                                </svg>   
                                                                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="eye-icon eye-open ">
                                                                <path stroke-linecap="round" stroke-linejoin="round" d="M2.036 12.322a1.012 1.012 0 0 1 0-.639C3.423 7.51 7.36 4.5 12 4.5c4.638 0 8.573 3.007 9.963 7.178.07.207.07.431 0 .639C20.577 16.49 16.64 19.5 12 19.5c-4.638 0-8.573-3.007-9.963-7.178Z" />
                                                                <path stroke-linecap="round" stroke-linejoin="round" d="M15 12a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z" />
                                                                </svg>                                                         
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="content-left">
                                                        <label for="">Confirm New Password</label>
                                                    </td>
                                                    <td class="content-right">
                                                        <div>
                                                            <div class="content-edit-contain">
                                                                <input
                                                                    type="password"
                                                                    placeholder=""
                                                                    class="input-edit"
                                                                    name="txtConfirmNewPassword"
                                                                    required
                                                                    />
                                                                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="eye-icon  eye-close hidden-icon">
                                                                <path stroke-linecap="round" stroke-linejoin="round" d="M3.98 8.223A10.477 10.477 0 0 0 1.934 12C3.226 16.338 7.244 19.5 12 19.5c.993 0 1.953-.138 2.863-.395M6.228 6.228A10.451 10.451 0 0 1 12 4.5c4.756 0 8.773 3.162 10.065 7.498a10.522 10.522 0 0 1-4.293 5.774M6.228 6.228 3 3m3.228 3.228 3.65 3.65m7.894 7.894L21 21m-3.228-3.228-3.65-3.65m0 0a3 3 0 1 0-4.243-4.243m4.242 4.242L9.88 9.88" />
                                                                </svg>  
                                                                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="eye-icon eye-open">
                                                                <path stroke-linecap="round" stroke-linejoin="round" d="M2.036 12.322a1.012 1.012 0 0 1 0-.639C3.423 7.51 7.36 4.5 12 4.5c4.638 0 8.573 3.007 9.963 7.178.07.207.07.431 0 .639C20.577 16.49 16.64 19.5 12 19.5c-4.638 0-8.573-3.007-9.963-7.178Z" />
                                                                <path stroke-linecap="round" stroke-linejoin="round" d="M15 12a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z" />
                                                                </svg>                                                          
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </table>
                                            <p>${requestScope.PasswordError}</p>
                                            <p>${requestScope.msgConfirmPasswordError}</p>
                                            <button class="button dark-btn" type="submit" name="btAction" value="ForgotPassword">Enter</button>
                                        </form>         
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
            <%@include file="../../common/footer.jsp" %>
        </div>
        <script src="view/Assets/Js/password-onoff.js"></script>
        <!-- <script src="Js/index.js"></script> -->
    </body>
</html>
