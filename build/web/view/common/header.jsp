<%-- 
    Document   : header
    Created on : Jun 24, 2024, 4:08:59 PM
    Author     : ADMIN
--%>

<%@page import="dto.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div>
    <%
        // kiem tra trang thai dang nhap
        User user = (User) session.getAttribute("account");
    %>
    <nav>
        <a href="DispatchServlet"><img class="logo" src="view/Assets/Images/logo.png" alt="" /></a>
        <ul>
            <li>
                <a class="nav-menu" href="DispatchServlet?btAction=Plans"><span>Plans</span></a>
            </li>
            <li>
                <a class="nav-menu" href="DispatchServlet?btAction=How"><span>How it Works</span></a>
            </li>
            <li>
                <a class="nav-menu" href="DispatchServlet?btAction=Menu"><span>Menu</span></a>
            </li>
            <%                if (user != null) {
            %>            
            <li>
                <a href="DispatchServlet?btAction=CartPage"><i class="fa-solid fa-basket-shopping icon-nav"></i></a>
            </li>
            <li>
                <div class="img-user">
                    <img src="<%= user.getImage() != null ? user.getImage() : "view/Assets/Images/user/default.jpg" %>" alt="user-img">
                </div>
                <div class="dropdown-menu">
                    <ul>
                        <div class="arrow-drop"></div>
                        <a href="DispatchServlet?btAction=Profile"><li>Profile</li></a>
                        <a href="DispatchServlet?btAction=MyOrder"><li>Order</li></a>
                        <a href="DispatchServlet?btAction=Logout"><li>Logout</li></a>
                    </ul>
                </div>
            </li>
            <%            } else {
            %>
            <li>
                <a href="DispatchServlet?btAction=loginPage" id="loginBtn" class="button">Login</a>
            </li>
            <li>
                <a href="DispatchServlet?btAction=SignupPage" id="signupBtn" class="button dark-btn">Sign Up</a>
            </li>
            <%
                }
            %>
        </ul>
    </nav>
</div>
