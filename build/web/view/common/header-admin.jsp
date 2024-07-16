<%-- 
    Document   : header-admin
    Created on : Jul 11, 2024, 12:38:33 AM
    Author     : Admin
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="main-header navbar navbar-expand navbar-white navbar-light">
    <!-- Left navbar links -->
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" data-widget="pushmenu" href="#"
               ><i class="fas fa-bars"></i
                ></a>
        </li>
        <li class="nav-item d-none d-sm-inline-block">
            <a href="DispatchServlet?btAction=welcomeAdmin" class="nav-link">Home</a>
        </li>
    </ul>

    <!-- Right navbar links -->
    <ul class="navbar-nav ml-auto">
        <!-- User Account Menu -->
        <li class="nav-item dropdown">
            <a class="nav-link" data-toggle="dropdown" href="#">
                <i class="fas fa-user"></i>
            </a>
            <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
                <div class="dropdown-divider"></div>
                <a href="DispatchServlet?btAction=Logout" class="dropdown-item">
                    <i class="fas fa-sign-out-alt mr-2"></i> Sign out
                </a>
            </div>
        </li>
    </ul>
</nav>
