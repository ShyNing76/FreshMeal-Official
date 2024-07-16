<%-- 
    Document   : admin
    Created on : Jun 29, 2024, 10:15:34 PM
    Author     : Admin
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="dto.Order_Detail"%>
<%@page import="dto.Order"%>
<%@page import="dto.Product"%>
<%@page import="dto.User"%>
<%@page import="java.util.Map"%>
<%@page import="java.sql.Date"%>
<%@page import="java.sql.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/mytags.tld" prefix="v" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>FreshMeal Admin Panel</title>

        <link rel="icon" href="view/Assets/Images/logo.png" />

        <!-- Tell the browser to be responsive to screen width -->
        <meta
            content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
            name="viewport"
            />
        <!-- Font Awesome -->
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
            />
        <!-- Theme style -->
        <link rel="stylesheet" href="view/Assets/css/adminlte.min.css" />
    </head>
    <body class="hold-transition sidebar-mini layout-fixed">
        <div class="wrapper">
            <!-- Main Header -->
            <%@include file="../../common/header-admin.jsp" %>

            <!-- Sidebar -->
            <%@include file="../../common/sidebar-admin.jsp" %>


            <!-- Content Wrapper -->
            <div class="content-wrapper">
                <section class="content-header">
                    <div class="container-fluid">
                        <div class="row mb-2">
                            <div class="col-sm-6">
                                <h1>Dashboard</h1>
                            </div>
                            <div class="col-sm-6">
                                <ol class="breadcrumb float-sm-right">
                                    <li class="breadcrumb-item"><a href="DispatchServlet?btAction=welcomeAdmin">Home</a></li>
                                    <li class="breadcrumb-item active">Dashboard</li>
                                </ol>
                            </div>
                        </div>
                    </div><!-- /.container-fluid -->
                </section>

                <section class="content">
                    <%
                        ArrayList<Order> listOrder = (ArrayList<Order>) request.getAttribute("ListOrder");
                        ArrayList<Order> listOrderLastest = (ArrayList<Order>) request.getAttribute("ListOrderLastest");
                        ArrayList<User> listCustomer = (ArrayList<User>) request.getAttribute("ListCustomer");
                        ArrayList<User> topCustomers = (ArrayList<User>) request.getAttribute("topCustomers");
                        ArrayList<Product> listProduct = (ArrayList<Product>) request.getAttribute("ListProduct");
                        ArrayList<Product> listBestSelling = (ArrayList<Product>) request.getAttribute("ListBestSelling");
                        Integer revenue = (Integer) request.getAttribute("Revenue");
                        Integer totalCustomer = (Integer) request.getAttribute("TotalCustomer");
                        Map<Date, Integer> revenueData = (Map<Date, Integer>) request.getAttribute("RevenueDate");
                        Map<Date, Integer> orderData = (Map<Date, Integer>) request.getAttribute("OrderDate");
                        Map<Integer, User> userMap = (Map<Integer, User>) request.getAttribute("UserMap");
                        Map<String, String> statusMap = (Map<String, String>) request.getAttribute("StatusMap");
                    %>
                    <div class="container-fluid">
                        <!-- Tổng quan -->
                        <%                            if (listOrder != null && listCustomer != null && listProduct != null) {
                        %>
                        <div class="row">
                            <div class="col-lg-3 col-6">
                                <div class="small-box bg-info">
                                    <div class="inner">
                                        <h3><%= listOrder.size()%></h3>
                                        <p>Total Orders</p>
                                    </div>
                                    <div class="icon">
                                        <i class="ion fa-solid fa-bag-shopping"></i>
                                    </div>
                                    <a href="DispatchServlet?btAction=order" class="small-box-footer">Details <i class="fas fa-arrow-circle-right"></i></a>
                                </div>
                            </div>
                            <div class="col-lg-3 col-6">
                                <div class="small-box bg-success">
                                    <div class="inner">
                                        <h3><v:Currency_Format  price="<%= revenue %>"/>đ</h3>
                                        <p>Total Revenue</p>
                                    </div>
                                    <div class="icon">
                                        <i class="ion fa-solid fa-money-bills"></i>
                                    </div>
                                    <a href="DispatchServlet?btAction=order" class="small-box-footer">Details <i class="fas fa-arrow-circle-right"></i></a>
                                </div>
                            </div>
                            <div class="col-lg-3 col-6">
                                <div class="small-box bg-warning">
                                    <div class="inner">
                                        <h3><%= totalCustomer%></h3>
                                        <p>Total Customer</p>
                                    </div>
                                    <div class="icon">
                                        <i class="ion fa-solid fa-user"></i>
                                    </div>
                                    <a href="DispatchServlet?btAction=user" class="small-box-footer">Details <i class="fas fa-arrow-circle-right"></i></a>
                                </div>
                            </div>
                            <div class="col-lg-3 col-6">
                                <div class="small-box bg-danger">
                                    <div class="inner">
                                        <h3><%= listProduct.size()%></h3>
                                        <p>Total Product</p>
                                    </div>
                                    <div class="icon">
                                        <i class="ion fa-solid fa-bowl-food"></i>
                                    </div>
                                    <a href="DispatchServlet?btAction=product" class="small-box-footer">Details <i class="fas fa-arrow-circle-right"></i></a>
                                </div>
                            </div>
                        </div>
                        <%
                            }
                        %>


                        <!-- Biểu đồ -->
                        <div class="row">
                            <div class="col-md-6">
                                <div class="card">
                                    <div class="card-header">
                                        <h3 class="card-title">Revenue Chart</h3>
                                        <div class="card-tools">
                                            <button type="button" class="btn btn-tool" data-card-widget="collapse">
                                                <i class="fas fa-minus"></i>
                                            </button>
                                            <button type="button" class="btn btn-tool" data-card-widget="remove">
                                                <i class="fas fa-times"></i>
                                            </button>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <div class="chart">
                                            <canvas id="revenueChart" style="height: 230px; min-height: 230px;"></canvas>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="card">
                                    <div class="card-header">
                                        <h3 class="card-title">Orders Chart</h3>
                                        <div class="card-tools">
                                            <button type="button" class="btn btn-tool" data-card-widget="collapse">
                                                <i class="fas fa-minus"></i>
                                            </button>
                                            <button type="button" class="btn btn-tool" data-card-widget="remove">
                                                <i class="fas fa-times"></i>
                                            </button>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <div class="chart">
                                            <canvas id="orderChart" style="height: 230px; min-height: 230px;"></canvas>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Đơn hàng mới nhất -->
                        <div class="row">
                            <div class="col-md-12">
                                <div class="card">
                                    <div class="card-header border-transparent">
                                        <h3 class="card-title">Latest orders</h3>
                                        <div class="card-tools">
                                            <button type="button" class="btn btn-tool" data-card-widget="collapse">
                                                <i class="fas fa-minus"></i>
                                            </button>
                                            <button type="button" class="btn btn-tool" data-card-widget="remove">
                                                <i class="fas fa-times"></i>
                                            </button>
                                        </div>
                                    </div>
                                    <div class="card-body p-0">
                                        <div class="table-responsive">
                                            <table class="table m-0">
                                                <thead>
                                                    <tr>
                                                        <th>ID Order</th>
                                                        <th>Customer</th>
                                                        <th>Quantity</th>
                                                        <th>Total Price</th>
                                                        <th>Status</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <%
                                                        if (listOrderLastest != null) {
                                                            for (Order order : listOrderLastest) {
                                                                int totalQuantity = 0;
                                                                int totalPrice = 0;
                                                                User user = userMap.get(order.getUserID());
                                                                for (Order_Detail orderDetails : order.getOrderDetail()) {
                                                                    totalQuantity += orderDetails.getQuantity();
                                                                }
                                                    %>
                                                    <tr>
                                                        <td><%= order.getOrderID()%></td>
                                                        <td><%= user.getFirstName()%> <%= user.getLastName()%></td>
                                                        <td>x<%= totalQuantity%></td>
                                                        <td><v:Currency_Format  price="<%= order.getTotalPrice()%>"/>đ</td>
                                                        <td><span class="badge <%= statusMap.get(order.getStatus())%>"><%= order.getStatus()%></span></td>
                                                    </tr>
                                                    <%
                                                            }
                                                        }
                                                    %>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="card-footer clearfix">
                                        <a href="DispatchServlet?btAction=order" class="btn btn-sm btn-secondary float-right">Show All Orders</a>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Món ăn bán chạy -->
                        <div class="row">
                            <%
                                if (listBestSelling != null) {
                            %>
                            <div class="col-md-6">
                                <div class="card">
                                    <div class="card-header">
                                        <h3 class="card-title">Best-selling dish</h3>
                                        <div class="card-tools">
                                            <button type="button" class="btn btn-tool" data-card-widget="collapse">
                                                <i class="fas fa-minus"></i>
                                            </button>
                                            <button type="button" class="btn btn-tool" data-card-widget="remove">
                                                <i class="fas fa-times"></i>
                                            </button>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <ul class="products-list product-list-in-card pl-2 pr-2">
                                            <%
                                                for (Product product : listBestSelling) {
                                            %>
                                            <li class="item">
                                                <div class="product-img" >
                                                    <img src="<%= product.getImage()%>" alt="Product Image" >
                                                </div>
                                                <div class="product-info">
                                                    <div  class="product-title"><%= product.getProductName()%>
                                                        <span class="badge badge-warning float-right"><v:Currency_Format  price="<%= product.getPrice()%>"/>đ</span></div>
                                                    <span class="product-description">
                                                        <%= product.getTotalSold()%> orders sold
                                                    </span>
                                                </div>
                                            </li>
                                            <%
                                                }
                                            %>
                                        </ul>
                                    </div>
                                    <div class="card-footer text-center">
                                        <a href="DispatchServlet?btAction=product" class="uppercase">Show all menu</a>
                                    </div>
                                </div>
                            </div>
                            <%                                }
                            %>


                            <%
                                if (topCustomers != null) {
                            %>
                            <div class="col-md-6">
                                <div class="card">
                                    <div class="card-header">
                                        <h3 class="card-title">Customer Order Most list</h3>
                                        <div class="card-tools">
                                            <button type="button" class="btn btn-tool" data-card-widget="collapse">
                                                <i class="fas fa-minus"></i>
                                            </button>
                                            <button type="button" class="btn btn-tool" data-card-widget="remove">
                                                <i class="fas fa-times"></i>
                                            </button>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <ul class="products-list product-list-in-card pl-2 pr-2">
                                            <%
                                                for (User user : topCustomers) {
                                            %>
                                            <li class="item">
                                                <div class="product-img">
                                                    <img src="<%= user.getImage() != null ? user.getImage() : "view/Assets/Images/user/default.jpg" %>" alt="User Image" class="img-size-50">
                                                </div>
                                                <div class="product-info">
                                                    <div class="product-title"><%= user.getFirstName() %> <%= user.getLastName() %>
                                                        <span class="badge badge-info float-right"><%= user.getOrderCount() %> orders</span></div>
                                                </div>
                                            </li>
                                            <%
                                                }
                                            %>
                                        </ul>
                                    </div>
                                    <div class="card-footer text-center">
                                        <a href="DispatchServlet?btAction=user" class="uppercase">Show All Customer</a>
                                    </div>
                                </div>
                            </div>
                            <%
                                }
                            %>


                        </div>

                    </div>
                </section>
            </div>


            <!-- Footer -->
            <%@include file="../../common/footer-admin.jsp" %>

            <!-- Control Sidebar -->
            <aside class="control-sidebar control-sidebar-dark">
                <!-- Add Control Sidebar content here -->
            </aside>
            <div class="control-sidebar-bg"></div>
        </div>


        <!-- jQuery -->
        <script src="view/Assets/Js/jquery.min.js"></script>
        <!-- Bootstrap 4 -->
        <script src="view/Assets/Js/bootstrap.bundle.min.js"></script>
        <!-- AdminLTE App -->
        <script src="view/Assets/Js/adminlte.min.js"></script>

        <script src="view/Assets/Js/Chart.js"></script>

        <%
            StringBuilder revenueLabels = new StringBuilder();
            StringBuilder revenueValues = new StringBuilder();
            StringBuilder orderLabels = new StringBuilder();
            StringBuilder orderValues = new StringBuilder();

            for (Map.Entry<Date, Integer> entry : revenueData.entrySet()) {
                revenueLabels.append("'").append(entry.getKey()).append("',");
                revenueValues.append(entry.getValue()).append(",");
            }

            for (Map.Entry<Date, Integer> entry : orderData.entrySet()) {
                orderLabels.append("'").append(entry.getKey()).append("',");
                orderValues.append(entry.getValue()).append(",");
            }

            if (revenueLabels.length() > 0) {
                revenueLabels.setLength(revenueLabels.length() - 1); // Xóa dấu phẩy cuối cùng
                revenueValues.setLength(revenueValues.length() - 1); // Xóa dấu phẩy cuối cùng
            }

            if (orderLabels.length() > 0) {
                orderLabels.setLength(orderLabels.length() - 1); // Xóa dấu phẩy cuối cùng
                orderValues.setLength(orderValues.length() - 1); // Xóa dấu phẩy cuối cùng
            }
        %>

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                // Revenue Chart
                var revenueCtx = document.getElementById('revenueChart').getContext('2d');
                var revenueData = {
                    labels: [<%= revenueLabels.toString()%>],
                    datasets: [{
                            label: 'Revenue',
                            data: [<%= revenueValues.toString()%>],
                            backgroundColor: 'rgba(75, 192, 192, 0.2)',
                            borderColor: 'rgba(75, 192, 192, 1)',
                            borderWidth: 1
                        }]
                };

                var revenueChart = new Chart(revenueCtx, {
                    type: 'line',
                    data: revenueData,
                    options: {
                        scales: {
                            y: {
                                beginAtZero: true
                            }
                        }
                    }
                });

                // Orders Chart
                var orderCtx = document.getElementById('orderChart').getContext('2d');
                var orderData = {
                    labels: [<%= orderLabels.toString()%>],
                    datasets: [{
                            label: 'Orders',
                            data: [<%= orderValues.toString()%>],
                            backgroundColor: 'rgba(153, 102, 255, 0.2)',
                            borderColor: 'rgba(153, 102, 255, 1)',
                            borderWidth: 1
                        }]
                };

                var orderChart = new Chart(orderCtx, {
                    type: 'line',
                    data: orderData,
                    options: {
                        scales: {
                            y: {
                                beginAtZero: true
                            }
                        }
                    }
                });
            });
        </script>
    </body>
</html>

