<%-- 
    Document   : login
    Created on : Jun 19, 2024, 1:57:58 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="icon" href="view/Assets/Images/logo.png" />

        <!-- Css -->
        <link rel="stylesheet" href="view/Assets/css/style.css" />
        <link rel="stylesheet" href="view/Assets/css/loginstyle.css" />
        <link rel="stylesheet" href="view/Assets/css/passwordchange.css">

        <!-- Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Outfit:wght@100..900&display=swap"
            rel="stylesheet"
            />

        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Poetsen+One&display=swap"
            rel="stylesheet"
            />

        <!-- Logo fonts -->
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
            />

        <title>Login - Fresh Meal</title>
    </head>
    <body>
        <div>
            <nav>
                <a href="DispatchServlet"><img class="logo" src="view/Assets/Images/logo.png" alt="" /></a>
                <h1>Login</h1>
            </nav>
        </div>
        <div class="container">
            <div class="logo-signup">
                <img src="view/Assets/Images/logo-light.png" alt="" />
            </div>
            <div class="signup">
                <div class="form-box">
                    <h1>Login</h1>
                            <p>${requestScope.msgerror}</p>

                    <form action="DispatchServlet" method="post">
                        <div class="input-group">
                            <div class="input-field">
                                <i class="fa-solid fa-envelope"></i>
                                <input type="text" name="txtUserName_email" placeholder="Email" required />
                            </div>

                            <div class="input-field">
                                <i class="fa-solid fa-lock"></i>
                                <input class="input-edit" type="password" name="txtPassword" placeholder="Password" required />
                                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="eye-icon eye-close hidden-icon">
                                <path stroke-linecap="round" stroke-linejoin="round" d="M3.98 8.223A10.477 10.477 0 0 0 1.934 12C3.226 16.338 7.244 19.5 12 19.5c.993 0 1.953-.138 2.863-.395M6.228 6.228A10.451 10.451 0 0 1 12 4.5c4.756 0 8.773 3.162 10.065 7.498a10.522 10.522 0 0 1-4.293 5.774M6.228 6.228 3 3m3.228 3.228 3.65 3.65m7.894 7.894L21 21m-3.228-3.228-3.65-3.65m0 0a3 3 0 1 0-4.243-4.243m4.242 4.242L9.88 9.88" />
                                </svg>    
                                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="eye-icon eye-open ">
                                <path stroke-linecap="round" stroke-linejoin="round" d="M2.036 12.322a1.012 1.012 0 0 1 0-.639C3.423 7.51 7.36 4.5 12 4.5c4.638 0 8.573 3.007 9.963 7.178.07.207.07.431 0 .639C20.577 16.49 16.64 19.5 12 19.5c-4.638 0-8.573-3.007-9.963-7.178Z" />
                                <path stroke-linecap="round" stroke-linejoin="round" d="M15 12a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z" />
                                </svg>  
                            </div>
                        </div>
                        <div class="btn-field">
                            <button class="button dark-btn" type="submit" name="btAction" value="Login">Login</button>
                        </div>

                        <div class="option">
                            <div class="line"></div>
                            <span>OR</span>
                            <div class="line"></div>
                        </div>

                        <div class="account">
                            <p class="already">New to FreshMeal ?</p>
                            <a  href="DispatchServlet?btAction=SignupPage" > Sign Up</a>
                        </div>
                        <div class="account">
                            <a  href="DispatchServlet?btAction=ForgotPasswordPage"> Forgot Password</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <div class="follow">
            <h4>FOLLOW US</h4>
            <div class="icon">
                <i class="fa-brands fa-instagram"></i>
                <i class="fa-brands fa-tiktok"></i>
                <i class="fa-brands fa-facebook"></i>
                <i class="fa-brands fa-github"></i>
                <i class="fa-brands fa-youtube"></i>
            </div>
        </div>

        <%@include file="../../common/footer.jsp" %>

        <script src="view/Assets/Js/password-onoff.js"></script>
    </body>
</html>
