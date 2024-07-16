<%-- 
    Document   : ingredient-admin
    Created on : Jul 5, 2024, 7:16:49 PM
    Author     : Admin
--%>

<%@page import="dto.Ingredient"%>
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
        <link rel="stylesheet" href="view/Assets/css/select2.min.css" />
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
                <!-- Content Header (Page header) -->
                <%  ArrayList<Ingredient> listIngredient = (ArrayList<Ingredient>) request.getAttribute("ListIngredient");
                    String errorInsert = (String) request.getAttribute("errorInsert");
                %>   
                <div class="content-header">
                    <div class="container-fluid">
                        <div class="row mb-2">
                            <div class="col-sm-6">
                                <h1 class="m-0">Ingredient Management</h1>
                            </div>
                            <!-- /.col -->
                            <div class="col-sm-6">
                                <ol class="breadcrumb float-sm-right">
                                    <li class="breadcrumb-item"><a href="#">Home</a></li>
                                    <li class="breadcrumb-item active">Products Management</li>
                                </ol>
                            </div>
                            <!-- /.col -->
                        </div>
                        <!-- /.row -->
                    </div>
                    <!-- /.container-fluid -->
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
                                        <h3 class="card-title">Ingredient List</h3>
                                    </div>
                                    <!-- /.card-header -->
                                    <div class="card-body">
                                        <!-- Button trigger modal -->
                                        <div class="row">
                                            <div class="col-md-5">
                                                <button
                                                    type="button"
                                                    class="btn btn-primary mb-2"
                                                    data-toggle="modal"
                                                    data-target="#addIngredientModal"
                                                    >
                                                    Add New Ingredient
                                                </button>
                                            </div>

                                            <div class="col-md-3" style="color: red">
                                                <%= errorInsert != null ? errorInsert : ""%>
                                            </div>

                                            <div class="col-md-4">
                                                <form action="DispatchServlet" method="get" id="searchForm">
                                                    <input type="hidden" name="btAction" value="ingredient"/>
                                                    <div class="input-group">
                                                        <input
                                                            type="text"
                                                            class="form-control"
                                                            placeholder="Search Ingredient"
                                                            name="search"
                                                            />
                                                        <div class="input-group-append">
                                                            <button class="btn btn-primary" type="button" onclick="document.getElementById('searchForm').submit()">
                                                                <i class="fas fa-search"></i>
                                                            </button>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>

                                        <!-- Table -->
                                        <table
                                            id="productTable"
                                            class="table table-bordered table-striped"
                                            >
                                            <thead>
                                                <tr>
                                                    <th>ID</th>
                                                    <th>Ingredient Name</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%                                                    if (listIngredient != null) {
                                                        for (Ingredient ingredient : listIngredient) {
                                                %>
                                                <tr>
                                                    <td><%= ingredient.getIngredientID()%></td>
                                                    <td><%= ingredient.getName()%></td>
                                                </tr>
                                                <%   }
                                                    }
                                                %>

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
                    </div>
                    <!-- /.container-fluid -->
                </section>
                <!-- /.content -->

                <!-- Add product Modal -->
                <div
                    class="modal fade"
                    id="addIngredientModal"
                    tabindex="-1"
                    role="dialog"
                    aria-labelledby="addIngredientModal"
                    aria-hidden="true"
                    >
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="addProductLabel">
                                    Add New Ingredient
                                </h5>
                                <button
                                    type="button"
                                    class="close"
                                    data-dismiss="modal"
                                    aria-label="Close"
                                    >
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <form role="form" id="addForm" action="DispatchServlet" method="post" accept-charset="UTF-8" data-name="Ingredient">
                                <input type="hidden" name="btAction" value="insert_ingredient"/>
                                <div class="modal-body">
                                    <div class="form-group">
                                        <label for="productName">Ingredient Name</label>
                                        <input
                                            type="text"
                                            class="form-control"
                                            id="productName"
                                            name="ingredientName"
                                            placeholder="Enter Ingredient Name"
                                            />
                                    </div>

                                    <div class="modal-footer">
                                        <button
                                            type="button"
                                            class="btn btn-secondary"
                                            data-dismiss="modal"
                                            >
                                            Close
                                        </button>
                                        <button type="submit" id="addBtn" class="btn btn-primary">
                                            Add New Ingredient
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

            </div>
            <!-- /.content-wrapper -->

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
                                                                    $('#productTable').DataTable({
                                                                        "paging": true,
                                                                        "searching": false,
                                                                        "lengthChange": false, // Disable the length change dropdown
                                                                        "info": false, // Disable the table information display
                                                                        "pageLength": 10 // Set default number of rows per page
                                                                    });
                                                                });
        </script>

    </body>
</html>
