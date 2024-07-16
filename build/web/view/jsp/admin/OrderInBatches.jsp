<%-- 
    Document   : OrderInBatches
    Created on : Jul 9, 2024, 12:56:41 PM
    Author     : Admin
--%>

<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="dto.Address"%>
<%@page import="dto.Product"%>
<%@page import="dto.Delivery"%>
<%@page import="dto.User"%>
<%@page import="dto.Order_Detail"%>
<%@page import="java.util.Map"%>
<%@page import="dto.Order"%>
<%@page import="java.util.ArrayList"%>
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
        <link rel="stylesheet" href="view/Assets/css/select2.min.css" />
        <link rel="stylesheet" href="view/Assets/css/checkout.css">
        <!-- DataTables CSS -->
        <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
        <!-- SweetAlert2 -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.12.0/dist/sweetalert2.min.css"/>

        <style>
            @media (min-width: 992px) {
                .modal-lg {
                    max-width: 600px;
                }

                .modal-xl{
                    max-width: 1000px;
                }

                .checkout-container {
                    display: flex;
                    justify-content: center;
                    padding: 0;
                }
            }
        </style>

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
                                <h1>Orders</h1>
                            </div>
                            <div class="col-sm-6">
                                <ol class="breadcrumb float-sm-right">
                                    <li class="breadcrumb-item"><a href="DispatchServlet?btAction=welcomeAdmin">Home</a></li>
                                    <li class="breadcrumb-item active">Orders In Batches</li>
                                </ol>
                            </div>
                        </div>
                    </div>
                </section>

                <section class="content">
                    <div class="container-fluid">

                        <!-- Danh sách đơn hàng -->
                        <%
                            ArrayList<Order> listOrder = (ArrayList<Order>) request.getAttribute("ListOfOrder");
                            Map<Integer, User> UserMap = (Map<Integer, User>) request.getAttribute("UserMap");
                            Map<Integer, Delivery> DeliveryMap = (Map<Integer, Delivery>) request.getAttribute("DeliveryMap");
                            Map<String, String> statusMap = (Map<String, String>) request.getAttribute("statusMap");
                            Map<Integer, Address> addressMap = (Map<Integer, Address>) request.getAttribute("AddressMap");
                            Map<Integer, Product> productOrderMap = (Map<Integer, Product>) request.getAttribute("ProductOrderMap");
                            ArrayList<String> listDistrict = (ArrayList<String>) request.getAttribute("ListDistrict");
                            String orderDate = (String) request.getAttribute("OrderDate");
                        %>

                        <div class="row">
                            <div class="col-12">
                                <div class="card">
                                    <div class="card-header">
                                        <h3 class="card-title">Order In <%= orderDate%></h3>
                                    </div>
                                    <!-- /.card-header -->
                                    <div class="card-body">
                                        <!-- Button trigger modal -->
                                        <div class="row">
                                            <div class="col-md-5">
                                                <a
                                                    type="button"
                                                    class="btn btn-primary mb-2"
                                                    href="DispatchServlet?btAction=order"
                                                    >
                                                    Show All Orders
                                                </a>
                                            </div>

                                            <div class="col-md-4"></div>

                                            <div class="col-md-3">
                                                <form id="dateForm" action="DispatchServlet" method="get">
                                                    <input type="hidden" name="btAction" value="order_in_batches"/>
                                                    <input class="form-control" value="<%= orderDate%>" type="date" name="orderdate" onchange="document.getElementById('dateForm').submit()"/>
                                                </form>
                                            </div>

                                        </div>

                                        <%
                                            if (listOrder != null && !listOrder.isEmpty()) {
                                                for (String districtName : listDistrict) {
                                                    Set<Integer> processOrderIDs = new HashSet<>(); // Set to keep track of processed orders for this district
                                                    boolean districtHasOrders = false; // Flag to check if the district has any orders

                                                    // Check if there are any orders for this district
                                                    for (Order orderMain : listOrder) {
                                                        Address addressMain = addressMap.get(orderMain.getAddressID());
                                                        if (addressMain.getDistrict().equalsIgnoreCase(districtName)) {
                                                            districtHasOrders = true;
                                                            break;
                                                        }
                                                    }

                                                    if (districtHasOrders) { // Only display the table if there are orders for this district
%>
                                        <!-- Table -->

                                        <div class="row" style="padding: 15px 0px 5px">
                                            <div class="col d-flex align-items-center">
                                                <i class="fa-solid fa-location-dot" style="color: #28a745"></i>
                                                <h5 class="mb-0 ml-2"><%= districtName%></h5>
                                            </div>
                                        </div>

                                        <table class="table table-bordered table-striped orderTable">
                                            <thead>
                                                <tr>
                                                    <th>ID Order</th>
                                                    <th>Customer</th>
                                                    <th>Quantity</th>
                                                    <th>Total Price</th>
                                                    <th>Status</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    for (Order order : listOrder) {
                                                        if (!processOrderIDs.contains(order.getOrderID())) {
                                                            int totalQuantity = 0;
                                                            User user = UserMap.get(order.getUserID());
                                                            Address address = addressMap.get(order.getAddressID());
                                                            if (address.getDistrict().equalsIgnoreCase(districtName)) {
                                                                for (Order_Detail orderDetails : order.getOrderDetail()) {
                                                                    totalQuantity += orderDetails.getQuantity();
                                                                }
                                                                processOrderIDs.add(order.getOrderID()); // Mark this order as processed
                                                %>
                                                <tr>
                                                    <td><%= order.getOrderID()%></td>
                                                    <td><%= user.getFirstName()%> <%= user.getLastName()%></td>
                                                    <td>x<%= totalQuantity%></td>
                                                    <td><v:Currency_Format  price="<%= order.getTotalPrice()%>"/>đ</td>
                                                    <td><span class="badge <%= statusMap.get(order.getStatus())%>"><%= order.getStatus()%></span></td>
                                                    <td>
                                                        <button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#orderDetailsModal-<%= order.getOrderID()%>">
                                                            <i class="fas fa-eye"></i> Details
                                                        </button>
                                                    </td>
                                                </tr>
                                                <%
                                                            }
                                                        }
                                                    }
                                                %>
                                            </tbody>
                                        </table>
                                        <%          }
                                            }
                                        } else {
                                        %>
                                        <p>No Order In <%= orderDate%></p>
                                        <%
                                            }
                                        %>

                                    </div>
                                    <!-- /.card-body -->
                                </div>
                                <!-- /.card -->
                            </div>
                            <!-- /.col -->
                        </div>

                        <!-- Chi tiết đơn hàng -->
                        <%
                            if (listOrder != null) {
                                for (Order order : listOrder) {
                                    int subtotal = 0;
                                    User user = UserMap.get(order.getUserID());
                                    Delivery delivery = DeliveryMap.get(order.getDeliveryID());
                                    Address address = addressMap.get(order.getAddressID());
                        %>
                        <div class="modal fade" id="orderDetailsModal-<%= order.getOrderID()%>" tabindex="-1" role="dialog" aria-labelledby="orderDetailsLabel" aria-hidden="true">
                            <div class="modal-dialog modal-lg" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="orderDetailsLabel">Invoice</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="container-fluid">
                                            <div class="checkout-container">
                                                <div class="method-container">
                                                    <div class="method-header">
                                                        <div class="header-content-back">
                                                            <h2>Order</h2>
                                                        </div>
                                                        <div class="header-discription">
                                                            <small class="order-id">Order Id: <span class="order-data">#<%= order.getOrderID()%></span></small>
                                                            <div class="separate-line"></div>
                                                            <small class="order-date"
                                                                   >Order date: <span class="order-data"><%= order.getOrderDate()%></span></small
                                                            >

                                                            <div class="separate-line"></div>
                                                            <small class="order-date">
                                                                Ship date: <span class="order-data">
                                                                    <%
                                                                        if (order.getShipDate() != null) {
                                                                    %>
                                                                    <%= order.getShipDate()%>
                                                                    <%
                                                                    } else {
                                                                    %>
                                                                    ...
                                                                    <%
                                                                        }
                                                                    %>
                                                                </span>
                                                            </small>

                                                        </div>
                                                    </div>

                                                    <div class="method-contain">
                                                        <div class="order-main">
                                                            <%
                                                                for (Order_Detail orderDetails : order.getOrderDetail()) {
                                                                    Product product = productOrderMap.get(orderDetails.getProductID());
                                                                    subtotal += (orderDetails.getPrice() * orderDetails.getQuantity());
                                                            %>
                                                            <div class="order-items">

                                                                <div class="item-container">
                                                                    <div class="img-order-item">
                                                                        <img src="<%= product.getImage()%>" alt="<%= product.getProductName()%>" />
                                                                    </div>
                                                                    <div class="side-content">
                                                                        <div class="main-order-content">
                                                                            <div class="order-name-item"><%= product.getProductName()%></div>
                                                                            <div class="order-price-item"><v:Currency_Format  price="<%= orderDetails.getPrice() * orderDetails.getQuantity()%>"/>đ</div>
                                                                        </div>
                                                                        <div class="discription-order-content">
                                                                            <small class="order-type-item"><%= orderDetails.getMealType()%></small>
                                                                            <small class="order-quantity-item">Qty: <%= orderDetails.getQuantity()%></small>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <%
                                                                }
                                                            %>
                                                        </div>

                                                        <div class="order-body">
                                                            <div class="order-payment-item">
                                                                <div class="header-item">Payment</div>
                                                                <div class="payment-content">
                                                                    <i class="fa-solid fa-dolly"></i>
                                                                    <div class="payment-text"><%= order.getPaymentMethod()%></div>
                                                                </div>
                                                            </div>
                                                            <div class="order-address-item">
                                                                <div class="header-item">Delivery</div>
                                                                <div class="order-address">
                                                                    <div class="subitem-content">
                                                                        <small class="subitem-header">Address</small>
                                                                        <p>
                                                                            <%= address.getAddress()%>, <%= address.getDistrict()%>, <%= address.getCity()%>
                                                                        </p>
                                                                        <p><%= user.getPhone()%></p>
                                                                    </div>
                                                                    <div class="subitem-content">
                                                                        <small class="subitem-header">Delivery Method</small>
                                                                        <p><%= delivery.getDeliveryMethod()%></p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <div class="order-sumary">
                                                            <div class="img-sumary">
                                                                <div class="img-sumary-contain">
                                                                    <img src="view/Assets/Images/logo.png" alt="" />
                                                                </div>
                                                            </div>

                                                            <div class="order-sumary-contain">
                                                                <div class="sub-sumary">
                                                                    <div class="header-item">Order Sumary</div>
                                                                    <div class="sub-sumary-contain">
                                                                        <div class="sumary-subtotal">
                                                                            <div class="subtotal-text">Sub Total</div>
                                                                            <div class="subtotal-price"><v:Currency_Format  price="<%= subtotal%>"/>đ</div>
                                                                        </div>
                                                                        <div class="sumary-discription">
                                                                            <small class="sumary-subtext">Discount</small>
                                                                            <small class="sumary-price">- <v:Currency_Format  price="<%= order.getDiscount()%>"/>đ</small>
                                                                        </div>
                                                                        <div class="sumary-discription">
                                                                            <small class="sumary-subtext">Delivery</small>
                                                                            <small class="sumary-price">+ <v:Currency_Format  price="<%= delivery.getDeliveryPrice()%>"/>đ</small>
                                                                        </div>
                                                                        <div class="sumary-discription">
                                                                            <small class="sumary-subtext">Tax</small>
                                                                            <small class="sumary-price">+ <v:Currency_Format  price="<%= order.getTax()%>"/>đ</small>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="order-sumary-total">
                                                                    <div class="sumary-total-text">Total</div>
                                                                    <div class="total-price"><v:Currency_Format  price="<%= order.getTotalPrice()%>"/>đ</div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>


                                                </div>
                                            </div>

                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <%
                                }
                            }
                        %>


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
        <!-- Select 2 -->
        <script src="view/Assets/Js/select2.min.js"></script>
        <!-- Bootstrap 4 -->
        <script src="view/Assets/Js/bootstrap.bundle.min.js"></script>
        <!-- AdminLTE App -->
        <script src="view/Assets/Js/adminlte.min.js"></script>
        <!--DataTable-->
        <script src="view/Assets/Js/jquery.dataTables.min.js"></script>
        <!-- Sweatalert2 -->
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.12.0/dist/sweetalert2.all.min.js"></script>
        <!--AdminAlert-->
        <script src="view/Assets/Js/admin-alert.js"></script>

        <script>
                                                        $(document).ready(function () {
                                                            $('.orderTable').DataTable({
                                                                "paging": true,
                                                                "searching": false,
                                                                "lengthChange": false, // Disable the length change dropdown
                                                                "info": false, // Disable the table information display
                                                                "pageLength": 5, // Set default number of rows per page
                                                                "ordering": false
                                                            });
                                                        });
        </script>
    </body>
</html>


