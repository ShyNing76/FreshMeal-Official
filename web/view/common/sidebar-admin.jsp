<%-- 
    Document   : sidebar-admin
    Created on : Jul 11, 2024, 12:40:00 AM
    Author     : Admin
--%>

<%@page import="dto.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<aside class="main-sidebar sidebar-dark-primary elevation-4">
    <%
        User admin = (User) session.getAttribute("account");
    %>
    <!-- Brand Logo -->
    <a href="DispatchServlet?btAction=welcomeAdmin" class="brand-link">
        <img src="<%= admin.getImage() != null ? admin.getImage() : "view/Assets/Images/user/default.jpg" %>" alt="FreshMeal Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
        <span class="brand-text font-weight-light">FreshMeal Admin</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
        <!-- Sidebar user panel (optional) -->
        <div class="user-panel mt-3 pb-3 mb-3 d-flex">
            <div class="image">
                <img src="<%= admin.getImage() != null ? admin.getImage() : "view/Assets/Images/user/default.jpg" %>" class="img-circle elevation-2" alt="User Image">
            </div>
            <div class="info">
                <a href="DispatchServlet?btAction=welcomeAdmin" class="d-block">Admin</a>
            </div>
        </div>

        <!-- Sidebar Menu -->
        <nav class="mt-2">
            <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
                <li class="nav-item">
                    <a href="DispatchServlet?btAction=welcomeAdmin" class="nav-link">
                        <i class="nav-icon fas fa-tachometer-alt"></i>
                        <p>
                            Dashboard
                        </p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="DispatchServlet?btAction=order" class="nav-link">
                        <i class="nav-icon fas fa-cutlery"></i>
                        <p>
                            Orders
                        </p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="DispatchServlet?btAction=ingredient" class="nav-link">
                        <i class="nav-icon fa-solid fa-carrot"></i>
                        <p>
                            Ingredient
                        </p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="DispatchServlet?btAction=product" class="nav-link">
                        <i class="nav-icon fas fa-book"></i>
                        <p>
                            Product Menu
                        </p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="DispatchServlet?btAction=weeklyplan" class="nav-link">
                        <i class="nav-icon fa-solid fa-calendar-week"></i>
                        <p>
                            Weekly Plan
                        </p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="DispatchServlet?btAction=user" class="nav-link">
                        <i class="nav-icon fas fa-users"></i>
                        <p>
                            Customers
                        </p>
                    </a>
                </li>
            </ul>
        </nav>
        <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
</aside>

