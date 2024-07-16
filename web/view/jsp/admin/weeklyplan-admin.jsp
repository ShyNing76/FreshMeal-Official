<%-- 
    Document   : weeklyplan-admin
    Created on : Jun 29, 2024, 10:17:44 PM
    Author     : Admin
--%>

<%@page import="java.util.Map"%>
<%@page import="dto.Product"%>
<%@page import="dto.Weekly_Plan_Product"%>
<%@page import="dto.Weekly_Plan"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <link rel="stylesheet" href="view/Assets/css/select2.min.css">
        <!-- DataTables CSS -->
        <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
        <!-- SweetAlert2 -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.12.0/dist/sweetalert2.min.css"/>

    </head>
    <body class="hold-transition sidebar-mini layout-fixed">
        <div class="wrapper">
            <!-- Main Header -->
            <%@include file="../../common/header-admin.jsp" %>

            <!-- Sidebar -->
            <%@include file="../../common/sidebar-admin.jsp" %>

            <!-- Content Wrapper -->
            <div class="content-wrapper">
                <%
                    ArrayList<Weekly_Plan> listWeeklyPlan = (ArrayList<Weekly_Plan>) request.getAttribute("ListOfWeeklyPlan");
                    ArrayList<Weekly_Plan_Product> listOfProductInWeekly = (ArrayList<Weekly_Plan_Product>) request.getAttribute("ListOfProductInWeekly");
                    ArrayList<Product> listProduct = (ArrayList<Product>) request.getAttribute("ListProduct");
                    Map<Integer, String> productMap = (Map<Integer, String>) request.getAttribute("ProductMap");
                %>
                <!-- Content Header (Page header) -->
                <div class="content-header">
                    <div class="container-fluid">
                        <div class="row mb-2">
                            <div class="col-sm-6">
                                <h1 class="m-0">Manage Weekly Plan</h1>
                            </div><!-- /.col -->
                            <div class="col-sm-6">
                                <ol class="breadcrumb float-sm-right">
                                    <li class="breadcrumb-item"><a href="DispatchServlet?btAction=welcomeAdmin">Home</a></li>
                                    <li class="breadcrumb-item active">Manage Weekly Plan</li>
                                </ol>
                            </div><!-- /.col -->
                        </div><!-- /.row -->
                    </div><!-- /.container-fluid -->
                </div>
                <!-- /.content-header -->

                <!-- Main content -->
                <section class="content">
                    <div class="container-fluid">
                        <!-- Small boxes (Stat box) -->
                        <div class="row">
                            <div class="col-12">
                                <div class="card">
                                    <div class="card-header">
                                        <h3 class="card-title">Weekly Plan List</h3>
                                    </div>
                                    <!-- /.card-header -->
                                    <div class="card-body">
                                        <!-- Button trigger modal -->
                                        <div class="row">
                                            <div class="col-md-9">
                                                <button type="button" class="btn btn-primary mb-2" data-toggle="modal" data-target="#addWeeklyPlanModal">
                                                    Add Weekly Plan
                                                </button>
                                            </div>

                                            <div class="col-md-3">
                                                <form action="DispatchServlet" method="get" id="statusForm">
                                                    <input type="hidden" name="btAction" value="weeklyplan"/>
                                                    <select name="status" class="form-control" onchange="document.getElementById('statusForm').submit()">
                                                        <option value="">Status</option>
                                                        <option value="0">Block</option>
                                                        <option value="1">Unblock</option>
                                                    </select>
                                                </form>
                                            </div>
                                        </div>


                                        <!-- Table -->
                                        <table id="productTable" class="table table-bordered table-striped">
                                            <thead>
                                                <tr>
                                                    <th>ID</th>
                                                    <th>Stard Date</th>
                                                    <th>End Date</th>
                                                    <th>Total Product</th>
                                                    <th>Status</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    if (listWeeklyPlan != null) {
                                                        for (Weekly_Plan weeklyPlan : listWeeklyPlan) {
                                                            int totalProduct = 0;

                                                            for (Weekly_Plan_Product weekly_Plan_Product : listOfProductInWeekly) {
                                                                if (weekly_Plan_Product.getWeekly_Plan_ID() == weeklyPlan.getWeekly_Plan_ID() && weekly_Plan_Product.getStatus() == 1) {
                                                                    totalProduct = totalProduct + 1;
                                                                }
                                                            }

                                                %>
                                                <tr>
                                                    <td><%= weeklyPlan.getWeekly_Plan_ID()%></td>
                                                    <td><%= weeklyPlan.getStartDate()%></td>
                                                    <td><%= weeklyPlan.getEndDate()%></td>
                                                    <td><%= totalProduct%></td>
                                                    <td><%= weeklyPlan.getStatus() == 1 ? "Unblock" : "Block"%></td>
                                                    <td>
                                                        <button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#viewWeeklyPlanModal-<%= weeklyPlan.getWeekly_Plan_ID()%>"><i class="fas fa-eye"></i> Details</button>
                                                        <button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#updaeWeeklyPlanModal-<%= weeklyPlan.getWeekly_Plan_ID()%>"><i class="fas fa-pencil-alt"></i> Update</button>
                                                        <form action="DispatchServlet" method="post" style="display:inline;" class="statusForm" data-name="WeeklyPlan #<%= weeklyPlan.getWeekly_Plan_ID()%>">
                                                            <input type="hidden" name="btAction" value="usw"/>
                                                            <input type="hidden" name="weeklyplanid" value="<%= weeklyPlan.getWeekly_Plan_ID()%>">
                                                            <input type="hidden" name="action" value="<%= weeklyPlan.getStatus() == 1 ? 0 : 1%>">
                                                            <button type="submit" class="btn btn-<%= weeklyPlan.getStatus() == 1 ? "danger" : "warning"%> btn-sm statusBtn">
                                                                <i class="fa-solid fa-lock"></i> <%= weeklyPlan.getStatus() == 1 ? "Block" : "Unblock"%>
                                                            </button>
                                                        </form>
                                                    </td>
                                                </tr>
                                                <%
                                                        }
                                                    }
                                                %>
                                                <!-- more rows -->
                                            </tbody>
                                        </table>
                                    </div>
                                    <!-- /.card-body -->
                                </div>
                                <!-- /.card -->
                            </div>
                            <!-- /.col -->
                        </div>
                        <!-- /.row -->
                    </div><!-- /.container-fluid -->
                </section>
                <!-- /.content -->
            </div>
            <!-- /.content-wrapper -->


            <!-- Modal Thêm Weekly Plan -->

            <div class="modal fade" id="addWeeklyPlanModal" tabindex="-1" role="dialog" aria-labelledby="addWeeklyPlanModalLabel" aria-hidden="true"  >
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="addWeeklyPlanModalLabel">Add Weekly Plan</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <form action="DispatchServlet" method="post" role="form" id="addForm" data-name="Weekly Plan">
                            <input type="hidden" name="btAction" value="insert_weeklyplan"/>
                            <div class="modal-body">
                                <div class="form-group">
                                    <label for="startDate">Start Date</label>
                                    <input type="date" name="startdate" class="form-control" id="startDate">
                                </div>
                                <div class="form-group">
                                    <label for="endDate">End Date</label>
                                    <input type="date" name="enddate" class="form-control" id="endDate">
                                </div>
                                <div class="form-group">
                                    <label for="products">List of products</label>
                                    <select class="form-control select2" multiple="multiple" name="products" data-placeholder="Choose Product" style="width: 100%;">
                                        <%
                                            if (listProduct != null) {
                                                for (Product product : listProduct) {
                                        %>
                                        <option value="<%= product.getProductID()%>"><%= product.getProductName()%></option>
                                        <%
                                                }
                                            }
                                        %>
                                    </select>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                <button type="submit" id="addBtn" class="btn btn-primary">Add Weekly Plan</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <!-- /.modal -->


            <!-- Modal Update Weekly Plan -->
            <%
                if (listWeeklyPlan != null) {
                    for (Weekly_Plan weeklyPlan : listWeeklyPlan) {
            %>
            <div class="modal fade" id="updaeWeeklyPlanModal-<%= weeklyPlan.getWeekly_Plan_ID()%>" tabindex="-1" role="dialog" aria-labelledby="updaeWeeklyPlanModal" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="addWeeklyPlanModalLabel">Update Weekly Plan</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>

                        <div class="modal-body">
                            <table id="" class="table table-bordered table-striped">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Name</th>
                                        <th>Status</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        int countProduct = 0;
                                        for (Weekly_Plan_Product weekly_Plan_Product : listOfProductInWeekly) {
                                            if (weekly_Plan_Product.getWeekly_Plan_ID() == weeklyPlan.getWeekly_Plan_ID()) {
                                    %>
                                    <tr>
                                        <td><%= countProduct = countProduct + 1%></td>
                                        <td><%= productMap.get(weekly_Plan_Product.getProductID())%></td>
                                        <td><%= weekly_Plan_Product.getStatus() == 1 ? "Unblock" : "Block"%></td>
                                        <td>
                                            <form action="DispatchServlet" method="post" style="display:inline;" class="statusForm" data-name="<%= productMap.get(weekly_Plan_Product.getProductID())%>" >
                                                <input type="hidden" name="btAction" value="upspw"/>
                                                <input type="hidden" name="weeklyplanproductid" value="<%= weekly_Plan_Product.getWeekly_Plan_Product_ID()%>">
                                                <input type="hidden" name="action" value="<%= weekly_Plan_Product.getStatus() == 1 ? 0 : 1%>">
                                                <button type="submit" class="btn btn-<%= weekly_Plan_Product.getStatus() == 1 ? "danger" : "warning"%> btn-sm statusBtn">
                                                    <i class="fa-solid fa-lock"></i> <%= weekly_Plan_Product.getStatus() == 1 ? "Block" : "Unblock"%>
                                                </button>
                                            </form>
                                        </td>
                                    </tr>
                                    <%
                                            }
                                        }
                                    %>
                                    <!-- more rows -->
                                </tbody>
                            </table>
                        </div>


                        <form action="DispatchServlet" method="post" role="form" class="updateForm" data-name="WeeklyPlan #<%= weeklyPlan.getWeekly_Plan_ID()%>">
                            <input type="hidden" name="btAction" value="update_weeklyplan"/>

                            <div class="modal-body">
                                <div class="form-group">
                                    <label for="startDate">Start Date</label>
                                    <input type="date" value="<%= weeklyPlan.getStartDate()%>" name="startdate" class="form-control" id="startDate">
                                </div>
                                <div class="form-group">
                                    <label for="endDate">End Date</label>
                                    <input type="date"  value="<%= weeklyPlan.getEndDate()%>" name="enddate" class="form-control" id="endDate">
                                </div>
                                <div class="form-group">
                                    <input type="hidden"  value="<%= weeklyPlan.getWeekly_Plan_ID()%>" name = "weeklyplanid" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label for="products">List of products</label>
                                    <select class="form-control select2" multiple="multiple" name="products" data-placeholder="Choose Product" style="width: 100%;">
                                        <%
                                            if (listProduct != null) {
                                                for (Product product : listProduct) {
                                                    boolean isInWeeklyPlan = false;
                                                    for (Weekly_Plan_Product productInWeek : listOfProductInWeekly) {
                                                        if (product.getProductID() == productInWeek.getProductID() && weeklyPlan.getWeekly_Plan_ID() == productInWeek.getWeekly_Plan_ID()) {
                                                            isInWeeklyPlan = true;
                                                            break;
                                                        }
                                                    }
                                                    if (!isInWeeklyPlan) {
                                        %>
                                        <option value="<%= product.getProductID()%>"><%= product.getProductName()%></option>
                                        <%          }
                                                }
                                            }
                                        %>
                                    </select>
                                </div>

                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                <button type="submit" class="btn btn-primary updateBtn">Update Weekly Plan</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <%     }
                }
            %>

            <!-- Modal Xem Chi tiết Weekly Plan -->
            <%                if (listWeeklyPlan != null) {
                    for (Weekly_Plan weeklyPlan : listWeeklyPlan) {
            %>
            <div class="modal fade" id="viewWeeklyPlanModal-<%= weeklyPlan.getWeekly_Plan_ID()%>" tabindex="-1" role="dialog" aria-labelledby="viewWeeklyPlanModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="viewWeeklyPlanModalLabel">Details Weekly Plan</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <p><strong>Start Date:</strong> <%= weeklyPlan.getStartDate()%></p>
                            <p><strong>End Date:</strong> <%= weeklyPlan.getEndDate()%></p>
                            <p><strong>List of products:</strong></p>
                            <ul>
                                <%  if (listOfProductInWeekly != null) {
                                        for (Weekly_Plan_Product weekly_Plan_Product : listOfProductInWeekly) {
                                            if (weeklyPlan.getWeekly_Plan_ID() == weekly_Plan_Product.getWeekly_Plan_ID()) {%>
                                <li><%= productMap.get(weekly_Plan_Product.getProductID())%></li>
                                    <%         }
                                            }
                                        }
                                    %>
                            </ul>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
            <%                    }
                }
            %>
            <!-- /.modal -->


        </div>
        <!-- ./wrapper -->

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
                                                            $('.select2').select2({
                                                                placeholder: "Chọn sản phẩm",
                                                                width: '100%',
                                                                multiple: true
                                                            });
                                                        });

                                                        $('#productTable').DataTable({
                                                            "paging": true,
                                                            "searching": false,
                                                            "lengthChange": false, // Disable the length change dropdown
                                                            "info": false, // Disable the table information display
                                                            "pageLength": 10, // Set default number of rows per page
                                                            "ordering": false
                                                        });
    </script>
</body>
</html>
