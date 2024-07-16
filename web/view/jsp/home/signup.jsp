<%-- 
    Document   : signup
    Created on : Jun 19, 2024, 1:58:20 PM
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
        <c:set var="userGoogle" value="${requestScope.UserGoogle}"/>
        <div>
            <nav>
                <a href="DispatchServlet"><img class="logo" src="view/Assets/Images/logo.png" alt="" /></a>
                <h1>Sign Up</h1>
            </nav>
        </div>

        <div class="container">
            <div class="logo-signup">
                <img src="view/Assets/Images/logo-light.png" alt="" />
            </div>
            <div class="signup">
                <div class="form-box">
                    <h1>Sign Up</h1>
                    <form action="DispatchServlet" method="post">
                        <c:if test="${userGoogle == null}">
                            <p>${requestScope.msgConfirmPasswordError}</p>
                            <p>${requestScope.msgerror}</p>
                            <div class="input-group">
                                <div class="input-field" id="nameField">
                                    <i class="fa-solid fa-user"></i>
                                    <input type="text" name="txtUserName" placeholder="UserName" required />
                                </div>

                                <div class="input-field">
                                    <i class="fa-solid fa-envelope"></i>
                                    <input type="email" name="txtEmail" placeholder="Email" required />
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

                                <div class="input-field" id="confirmField">
                                    <i class="fa-solid fa-lock"></i>
                                    <input class="input-edit" type="password" name="txtConfirmPassword" placeholder="Confirm Password" required />
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
                                <button class="button dark-btn" type="sumit" name="btAction" value="Signup">Sign Up</button>
                            </div>

                            <div class="option">
                                <div class="line"></div>
                                <span>OR</span>
                                <div class="line"></div>
                            </div>
                            <!--Google Login-->
                            <div>
                                <a class="login-with-google-btn" href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile&redirect_uri=http://localhost:8084/F.R.E.S.H_Meal/RegisterServlet&response_type=code&client_id=872543102355-17qf7tkojpn71agmn6i8c58ge7vk4ns1.apps.googleusercontent.com&approval_prompt=force">Sign Up With Google</a>
                            </div>
                            <div class="account">
                                <p class="already">You already have account ?</p>
                                <a  href="DispatchServlet?btAction=loginPage"> Login </a>
                            </div>
                        </c:if>
                        <c:if test="${userGoogle != null}">
                            <div class="input-group">
                                <img src="${userGoogle.picture}"/>
                                <p>${userGoogle.name}</p>
                                <p>${userGoogle.email}</p>
                                <input type="hidden" name="txtUserName" value="${userGoogle.name}"/>
                                <input type="hidden" name="txtEmail" value="${userGoogle.email}"/>
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

                                <div class="input-field" id="confirmField">
                                    <i class="fa-solid fa-lock"></i>
                                    <input class="input-edit" type="password" name="txtConfirmPassword" placeholder="Confirm Password" required />
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
                                <button class="button dark-btn" type="sumit" name="btAction" value="Signup">Sign Up</button>
                            </div>
                        </c:if>

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
        <script src="view/Assets/Js/register.js"></script>
    </body>
</html>
