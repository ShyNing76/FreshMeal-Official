<%-- 
    Document   : customer-admin
    Created on : Jun 29, 2024, 10:16:12 PM
    Author     : Admin
--%>

<%@page import="dto.Address"%>
<%@page import="dto.User_Address"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
        <!-- DataTables CSS -->
        <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
        <!-- SweetAlert2 -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.12.0/dist/sweetalert2.min.css"/>

        <style>
            .select2-container--default .select2-selection--single {
                background-color: #fff;
                border: 1px solid #aaa;
                border-radius: 4px;
                height: 41px;
            }
        </style>
    </head>
    <body class="hold-transition sidebar-mini layout-fixed">
        <%
            ArrayList<User> listOfUser = (ArrayList<User>) request.getAttribute("ListOfUser");
            Map<Integer, String> roleMap = (Map<Integer, String>) request.getAttribute("RoleMap");
            Map<Integer, Address> addressMap = (Map<Integer, Address>) request.getAttribute("AddressMap");
        %>

        <div class="wrapper">
            <!-- Main Header -->
            <%@include file="../../common/header-admin.jsp" %>

            <!-- Sidebar -->
            <%@include file="../../common/sidebar-admin.jsp" %>

            <!-- Content Wrapper -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <div class="content-header">
                    <div class="container-fluid">
                        <div class="row mb-2">
                            <div class="col-sm-6">
                                <h1 class="m-0">Customers</h1>
                            </div>
                            <!-- /.col -->
                            <div class="col-sm-6">
                                <ol class="breadcrumb float-sm-right">
                                    <li class="breadcrumb-item"><a href="DispatchServlet?btAction=welcomeAdmin">Home</a></li>
                                    <li class="breadcrumb-item active">Customers</li>
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
                                        <h3 class="card-title">Customer List</h3>
                                    </div>
                                    <!-- /.card-header -->
                                    <div class="card-body">
                                        <form action="DispatchServlet" method="get" id="filterForm">
                                            <div class="row">
                                                <input type="hidden" name="btAction" value="user"/>

                                                <div class="col-md-2">
                                                    <button
                                                        type="button"
                                                        class="btn btn-primary mb-2"
                                                        data-toggle="modal"
                                                        data-target="#addUserModal"
                                                        >
                                                        Add New User
                                                    </button>
                                                </div>

                                                <div class="col-md-3">
                                                    <select class="form-control" id="statusSelect" name="status">
                                                        <option value="">Status</option>
                                                        <option value="0">Block</option>
                                                        <option value="1">Unblock</option>
                                                    </select>
                                                </div>

                                                <div class="col-md-3">
                                                    <select class="form-control" id="roleSelect" name="role">
                                                        <option value="">Role</option>
                                                        <option value="1">Customer</option>
                                                        <option value="2">Admin</option>
                                                    </select>
                                                </div>


                                                <div class="col-md-4">
                                                    <div class="input-group">
                                                        <input
                                                            type="text"
                                                            class="form-control"
                                                            placeholder="Search By Name"
                                                            id="searchInput"
                                                            name="search"
                                                            />
                                                        <div class="input-group-append">
                                                            <button class="btn btn-primary" type="button" onclick="document.getElementById('filterForm').submit()">
                                                                <i class="fas fa-search"></i>
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>

                                            </div>
                                        </form>

                                        <table
                                            id="productTable"
                                            class="table table-bordered table-striped"
                                            >
                                            <thead>
                                                <tr>
                                                    <th>No</th>
                                                    <th>Name</th>
                                                    <th>Email</th>
                                                    <th>Phone</th>
                                                    <th>Role</th>
                                                    <th>Status</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    if (listOfUser != null) {
                                                        int count = 0;
                                                        for (User user : listOfUser) {
                                                            count++;
                                                %>
                                                <tr>
                                                    <td><%= count %></td>
                                                    <td><%= user.getFirstName()%> <%= user.getLastName()%></td>
                                                    <td><%= user.getEmail()%></td>
                                                    <td><%= user.getPhone()%></td>
                                                    <% if (roleMap != null) {%>
                                                    <td><%= roleMap.get(user.getRoleID())%></td>
                                                    <%
                                                        }
                                                    %>
                                                    <td><%= user.getStatus() == 1 ? "Unblock" : "Block"%></td>
                                                    <td>
                                                        <button
                                                            type="button"
                                                            class="btn btn-success btn-sm"
                                                            data-toggle="modal"
                                                            data-target="#detailUsertModal-<%= user.getUserID()%>"
                                                            >
                                                            <i class="fas fa-eye"></i> Details
                                                        </button>
                                                        <button
                                                            type="button"
                                                            class="btn btn-info btn-sm"
                                                            data-toggle="modal"
                                                            data-target="#updateUserModal-<%= user.getUserID()%>"
                                                            >
                                                            <i class="fas fa-pencil-alt"></i> Update Role
                                                        </button>
                                                        <form action="DispatchServlet" method="post" style="display:inline;" class="statusForm" data-name="<%= user.getFirstName() + " " + user.getLastName()%>">
                                                            <input type="hidden" name="btAction" value="update_status_user"/>
                                                            <input type="hidden" name="userId" value="<%= user.getUserID()%>">
                                                            <input type="hidden" name="action" value="<%= user.getStatus() == 1 ? 0 : 1%>">
                                                            <button type="submit" class="btn btn-<%= user.getStatus() == 1 ? "danger" : "warning"%> btn-sm statusBtn">
                                                                <i class="fa-solid fa-lock"></i> <%= user.getStatus() == 1 ? "Block" : "Unblock"%>
                                                            </button>
                                                        </form>
                                                    </td>
                                                </tr>
                                                <%
                                                    }
                                                } else {
                                                %>
                                                <tr>
                                                    <td colspan="7">
                                                        No User Found
                                                    </td> 
                                                </tr>
                                                <%
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


                <!-- Add User Modal -->
                <div
                    class="modal fade"
                    id="addUserModal"
                    tabindex="-1"
                    role="dialog"
                    aria-labelledby="addUserModal"
                    aria-hidden="true"
                    >
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="addUserModal">Add New User</h5>
                                <button
                                    type="button"
                                    class="close"
                                    data-dismiss="modal"
                                    aria-label="Close"
                                    >
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <form action="DispatchServlet" id="addForm" role="form" method="post" accept-charset="UTF-8" data-name="User">
                                <input type="hidden" name="btAction" value="insert_user"/>
                                <div class="modal-body">
                                    <div class="row">
                                        <input type="hidden" name="btAction" value="insert_user"/>

                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label for="firstName">First Name</label>
                                                <input
                                                    type="text"
                                                    class="form-control"
                                                    placeholder="Enter First Name"
                                                    name="firstName"
                                                    required
                                                    />
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label for="lastName">Last Name</label>
                                                <input
                                                    type="text"
                                                    class="form-control"
                                                    placeholder="Enter Last Name"
                                                    name="lastName"
                                                    required
                                                    />
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="emailUser">Email</label>
                                        <input
                                            type="text"
                                            class="form-control"
                                            placeholder="Enter Email"
                                            name="emailUser"
                                            required
                                            />
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label for="userName">User Name</label>
                                                <input
                                                    type="text"
                                                    class="form-control"
                                                    placeholder="Enter User Name"
                                                    name="userName"
                                                    required
                                                    />
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label for="passwordUser">Password</label>
                                                <input
                                                    type="text"
                                                    class="form-control"
                                                    placeholder="Enter Password"
                                                    name="passwordUser"
                                                    required
                                                    />
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label for="dateOfBirth">Date Of Birth</label>
                                                <input type="date" name="dateOfBirth" class="form-control" />
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label for="gender">Gender</label>
                                                <select
                                                    class="form-control select2"
                                                    data-placeholder="Select Gender"
                                                    style="width: 100%"
                                                    name="genderUser"
                                                    >
                                                    <option value=""></option>
                                                    <option value="Male">Male</option>
                                                    <option value="Female">Female</option>
                                                    <option value="Others">Others</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-sm-6">
                                            <div class="form-group">
                                                <label for="phone">Phone</label>
                                                <input
                                                    type="tel"
                                                    class="form-control"
                                                    placeholder="Enter Phone"
                                                    name="Phone"
                                                    required
                                                    />
                                            </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <div class="form-group">
                                                <label for="productTime">Role</label>
                                                <select
                                                    class="form-control select2"
                                                    data-placeholder="Select Role"
                                                    style="width: 100%"
                                                    name="roleUser"
                                                    required
                                                    >
                                                    <option value=""></option>
                                                    <option value="1">Customer</option>
                                                    <option value="2">Admin</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label>Address</label>
                                        <textarea
                                            class="form-control"
                                            rows="3"
                                            name="addressUser"
                                            placeholder="Enter Address"
                                            ></textarea>
                                    </div>

                                    <div class="form-group">
                                        <label>District</label>
                                        <select class="form-control select2" data-placeholder="Select District"  name="selectedDistrict">
                                            <option value="">Select District</option>
                                            <option>Thành Phố Thủ Đức</option>
                                            <option>Quận 1</option>
                                            <option>Quận 2</option>
                                            <option>Quận 3</option>
                                            <option>Quận 4</option>
                                            <option>Quận 5</option>
                                            <option>Quận 6</option>
                                            <option>Quận 7</option>
                                            <option>Quận 8</option>
                                            <option>Quận 9</option>
                                            <option>Quận 10</option>
                                            <option>Quận 11</option>
                                            <option>Quận 12</option>
                                            <option>Quận Bình Tân</option>
                                            <option>Quận Bình Thạnh</option>
                                            <option>Quận Gò Vấp</option>
                                            <option>Quận Phú Nhuận</option>
                                            <option>Quận Tân Bình</option>
                                            <option>Quận Tân Phú</option>
                                            <option>Huyện Bình Chánh</option>
                                            <option>Huyện Cần Giờ</option>
                                            <option>Huyện Củ Chi</option>
                                            <option>Huyện Hóc Môn</option>
                                            <option>Huyện Nhà Bè</option>
                                        </select>
                                    </div>

                                    <div class="form-group">
                                        <label for="productImage">User Image</label>
                                        <input
                                            type="file"
                                            class="form-control-file"
                                            name="userImage"
                                            />
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button
                                        type="button"
                                        class="btn btn-secondary"
                                        data-dismiss="modal"
                                        >
                                        Close
                                    </button>
                                    <button id="addBtn" type="submit" class="btn btn-primary">
                                        Add New Product
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <!-- Modal for user details -->
                <%
                    if (listOfUser != null) {
                        for (User user2 : listOfUser) {
                %>
                <div
                    class="modal fade"
                    id="detailUsertModal-<%= user2.getUserID()%>"
                    tabindex="-1"
                    role="dialog"
                    aria-labelledby="detailUserLabel"
                    aria-hidden="true"
                    >
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">User Details</h5>
                                <button
                                    type="button"
                                    class="close"
                                    data-dismiss="modal"
                                    aria-label="Close"
                                    >
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-md-2">
                                            <img
                                                src="<%=user2.getImage() != null ? user2.getImage() : "view/Assets/Images/user/default.jpg"%>"
                                                alt=""
                                                style="width: 100%; border-radius: 50%"
                                                />
                                        </div>
                                        <div class="col-md-6 d-flex align-items-center" >
                                            User Image
                                        </div>
                                    </div>
                                </div>

                                <p><strong>Full Name:</strong> <%= user2.getFirstName()%> <%= user2.getLastName()%></p>

                                <p><strong>Email:</strong> <%= user2.getEmail()%></p>

                                <p><strong>User Name:</strong> <%= user2.getUserName()%></p>

                                <p><strong>Date Of Birth:</strong> <%= user2.getDateOfBirth()%></p>

                                <p><strong>Gender:</strong> <%= user2.getGender()%></p>

                                <p><strong>Phone:</strong> <%= user2.getPhone()%></p>

                                <% if (roleMap != null) {%>
                                <p><strong>Role:</strong> <%= roleMap.get(user2.getRoleID())%></p>
                                <%
                                    }
                                %>

                                <p><strong>Address: </strong></p>
                                <ul>

                                    <%
                                        for (User_Address address : user2.getListOfAddress()) {
                                            Address addressObj = addressMap.get(address.getAddressID());
                                    %>
                                    <li><%= addressObj.getAddress()%>, <%= addressObj.getDistrict()%>, <%= addressObj.getCity()%></li>
                                        <%
                                            }
                                        %>

                                </ul>
                            </div>
                            <div class="modal-footer">
                                <button
                                    type="button"
                                    class="btn btn-secondary"
                                    data-dismiss="modal"
                                    >
                                    Close
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <%
                        }
                    }

                %>

                <!-- Update User Modal -->
                <%                    if (listOfUser != null) {
                        for (User user3 : listOfUser) {
                %>
                <div
                    class="modal fade"
                    id="updateUserModal-<%= user3.getUserID()%>"
                    tabindex="-1"
                    role="dialog"
                    aria-labelledby="updateUserModal"
                    aria-hidden="true"
                    >
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" >Update Roles User</h5>
                                <button
                                    type="button"
                                    class="close"
                                    data-dismiss="modal"
                                    aria-label="Close"
                                    >
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <form role="form" action="DispatchServlet" method="post" class="updateForm" data-name="Role">
                                <input type="hidden" name="btAction" value="update_user"/>

                                <div class="modal-body">
                                    <div class="form-group">
                                        <input type="hidden" name="userId" value="<%= user3.getUserID()%>"/>
                                        <label for="userRole">Role</label>
                                        <select
                                            class="form-control select2"
                                            data-placeholder="Select Role"
                                            style="width: 100%"
                                            name="userRole"
                                            required
                                            >
                                            <option value=""></option>
                                            <option value="1">Customer</option>
                                            <option value="2">Admin</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button
                                        type="button"
                                        class="btn btn-secondary"
                                        data-dismiss="modal"
                                        >
                                        Close
                                    </button>
                                    <button type="submit" class="btn btn-primary updateBtn">
                                        Update Role
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <%
                        }
                    }

                %>

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
                                                                    $(".select2").select2({
                                                                        placeholder: "Select Gender",
                                                                        width: "100%",
                                                                    });
                                                                });

                                                                $('#productTable').DataTable({
                                                                    "paging": true,
                                                                    "searching": false,
                                                                    "lengthChange": false, // Disable the length change dropdown
                                                                    "info": false, // Disable the table information display
                                                                    "pageLength": 10 // Set default number of rows per page
                                                                });
        </script>
    </body>
</html>

