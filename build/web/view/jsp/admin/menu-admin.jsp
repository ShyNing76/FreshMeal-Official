<%-- 
    Document   : menu-admin
    Created on : Jun 29, 2024, 10:16:42 PM
    Author     : Admin
--%>

<%@page import="dto.Ingredient"%>
<%@page import="dto.BoxIngredient"%>
<%@page import="dto.Recipe_Product"%>
<%@page import="java.util.Map"%>
<%@page import="dto.Product_Category"%>
<%@page import="dto.Category"%>
<%@page import="dto.Product"%>
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
        <!-- DataTables CSS -->
        <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
        <!-- SweetAlert2 -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.12.0/dist/sweetalert2.min.css"/>
    </head>
    <body class="hold-transition sidebar-mini layout-fixed">
        <div class="wrapper">
            <%
                ArrayList<Product> listProduct = (ArrayList<Product>) request.getAttribute("listOfProduct");
                Map<Integer, String> categoryMap = (Map<Integer, String>) request.getAttribute("CategoryMap");
                ArrayList<Category> listCategory = (ArrayList<Category>) request.getAttribute("ListOfCategory");
                Map<Integer, String> ingredientMap = (Map<Integer, String>) request.getAttribute("IngredientMap");
                ArrayList<Ingredient> listIngredient = (ArrayList<Ingredient>) request.getAttribute("ListIngredient");
            %>
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
                                <h1 class="m-0">Products Management</h1>
                            </div>
                            <!-- /.col -->
                            <div class="col-sm-6">
                                <ol class="breadcrumb float-sm-right">
                                    <li class="breadcrumb-item"><a href="DispatchServlet?btAction=welcomeAdmin">Home</a></li>
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
                                        <h3 class="card-title">Products List</h3>
                                    </div>
                                    <!-- /.card-header -->
                                    <div class="card-body">
                                        <!-- Button trigger modal -->
                                        <form action="DispatchServlet" method="get" id="productForm">
                                            <input type="hidden" name="btAction" value="product"/>
                                            <div class="row">
                                                <div class="col-md-5">
                                                    <button
                                                        type="button"
                                                        class="btn btn-primary mb-2"
                                                        data-toggle="modal"
                                                        data-target="#addProductModal"
                                                        >
                                                        Add New Product
                                                    </button>
                                                </div>

                                                <div class="col-md-3">
                                                    <select class="form-control" name="status" onchange="document.getElementById('productForm').submit()">
                                                        <option value="">Status</option>
                                                        <option value="0">Block</option>
                                                        <option value="1">Unblock</option>
                                                    </select>
                                                </div>

                                                <div class="col-md-4">
                                                    <div class="input-group">
                                                        <input
                                                            type="text"
                                                            class="form-control"
                                                            placeholder="Search Menu Product"
                                                            name="search"
                                                            />
                                                        <div class="input-group-append">
                                                            <button class="btn btn-primary" type="button" onclick="document.getElementById('productForm').submit()">
                                                                <i class="fas fa-search"></i>
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>


                                        <!-- Table -->
                                        <table
                                            id="productTable"
                                            class="table table-bordered table-striped"
                                            >
                                            <thead>
                                                <tr>
                                                    <th>ID</th>
                                                    <th>Product Name</th>
                                                    <th>Category</th>
                                                    <th>Price</th>
                                                    <th>Cooking Time</th>
                                                    <th>Status</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    if (listProduct != null) {
                                                        for (Product product : listProduct) {
                                                %>
                                                <tr>
                                                    <td><%= product.getProductID()%></td>
                                                    <td><%= product.getProductName()%></td>
                                                    <td>
                                                        <%
                                                            for (Product_Category category : product.getListOfCategory()) {
                                                        %>
                                                        <span class="badge badge-success"><%= categoryMap.get(category.getCategoryID())%></span>
                                                        <%
                                                            }
                                                        %>
                                                    </td>
                                                    <td><v:Currency_Format  price="<%= product.getPrice()%>"/></td>
                                                    <td><%= product.getCookingTime()%></td>
                                                    <td><%= product.getStatus() == 1 ? "Unblock" : "Block"%></td>
                                                    <td>
                                                        <button
                                                            type="button"
                                                            class="btn btn-success btn-sm"
                                                            data-toggle="modal"
                                                            data-target="#detailProductModal-<%= product.getProductID()%>"
                                                            >
                                                            <i class="fas fa-eye"></i> Details
                                                        </button>
                                                        <button
                                                            type="button"
                                                            class="btn btn-info btn-sm"
                                                            data-toggle="modal"
                                                            data-target="#updateProductModal-<%= product.getProductID()%>"
                                                            >
                                                            <i class="fas fa-pencil-alt"></i> Update
                                                        </button>
                                                        <form action="DispatchServlet" method="post" style="display:inline;" class="statusForm" data-name="<%= product.getProductName()%>">
                                                            <input type="hidden" name="btAction" value="update_status_product"/>
                                                            <input type="hidden" name="productId" value="<%= product.getProductID()%>">
                                                            <input type="hidden" name="action" value="<%= product.getStatus() == 1 ? 0 : 1%>">
                                                            <button type="submit" class="btn btn-<%= product.getStatus() == 1 ? "danger" : "warning"%> btn-sm statusBtn">
                                                                <i class="fa-solid fa-lock"></i> <%= product.getStatus() == 1 ? "Block" : "Unblock"%>
                                                            </button>
                                                        </form>
                                                    </td>
                                                </tr>
                                                <%      }
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
                    id="addProductModal"
                    tabindex="-1"
                    role="dialog"
                    aria-labelledby="addProductLabel"
                    aria-hidden="true"
                    >
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="addProductLabel">
                                    Add New Product
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
                            <form id="addForm" role="form" action="DispatchServlet" method="post" accept-charset="UTF-8" data-name="Product">
                                <input type="hidden" name="btAction" value="insert_product"/>

                                <div class="modal-body">
                                    <div class="form-group">
                                        <label for="productName">Product Name</label>
                                        <input
                                            type="text"
                                            class="form-control"
                                            id="productName"
                                            name="productName"
                                            placeholder="Enter Product Name"
                                            />
                                    </div>
                                    <div class="form-group">
                                        <label for="productCategory">Category</label>
                                        <select
                                            class="form-control select2"
                                            multiple="multiple"
                                            data-placeholder="Choose Category"
                                            style="width: 100%"
                                            name="productCategory[]"
                                            >
                                            <option value="">Choose Category</option>
                                            <% if (listCategory != null) {
                                                    for (Category category : listCategory) {%>
                                            <option value="<%= category.getCategoryID()%>"><%= category.getCategoryName()%></option>
                                            <% }
                                                }%>
                                        </select>
                                    </div>

                                    <div class="row">
                                        <div class="col-sm-6">
                                            <div class="form-group">
                                                <label for="productPrice">Price</label>
                                                <input
                                                    type="text"
                                                    class="form-control"
                                                    id="productPrice"
                                                    name="productPrice"
                                                    placeholder="Enter Price"
                                                    />
                                            </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <div class="form-group">
                                                <label for="productTime">Cooking Time</label>
                                                <input
                                                    type="text"
                                                    class="form-control"
                                                    id="productTime"
                                                    name="productTime"
                                                    placeholder="Enter Cooking Time"
                                                    />
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label>Product Description</label>
                                        <textarea
                                            class="form-control"
                                            rows="3"
                                            name="productDescription"
                                            placeholder="Enter Product Description"
                                            ></textarea>
                                    </div>

                                    <div class="form-group">
                                        <label for="productImage">Food Image</label>
                                        <input
                                            type="file"
                                            class="form-control-file"
                                            id="productImage"
                                            name="productImage"
                                            />
                                    </div>

                                    <div class="form-group">
                                        <label>Recipe Instructions</label>
                                        <div id="recipeContainerAdd">
                                            <div class="form-group">
                                                <div class="row">
                                                    <div class="col-md-2">
                                                        <input type="number" class="form-control" placeholder="Step 1" name="step[]" value="1" readonly />
                                                    </div>
                                                    <div class="col-md-9">
                                                        <input type="text" class="form-control" placeholder="Instruction for Step 1" name="instruction[]" />
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <button type="button" class="btn btn-success btn-add-step" data-target="#recipeContainerAdd"><i class="fas fa-plus"></i> Add Step</button>
                                    </div>

                                    <div class="form-group">
                                        <label>Ingredients</label>
                                        <div id="ingredientContainerAdd">
                                            <div class="form-group">
                                                <div class="row">
                                                    <div class="col-md-5">
                                                        <select
                                                            class="form-control"
                                                            style="width: 100%"
                                                            name="ingredientId[]"
                                                            >
                                                            <option value="">Ingredient</option>
                                                            <%
                                                                if (listIngredient != null) {
                                                                    for (Ingredient ingredient : listIngredient) {
                                                            %>
                                                            <option value="<%= ingredient.getIngredientID()%>"> <%= ingredient.getName()%> </option>
                                                            <%
                                                                    }
                                                                }
                                                            %>
                                                        </select>
                                                    </div>
                                                    <div class="col-md-3">
                                                        <input type="text" class="form-control" placeholder="Quantity" name="ingredientQuantity[]" />
                                                    </div>
                                                    <div class="col-md-3">
                                                        <input type="text" class="form-control" placeholder="Unit" name="ingredientUnit[]" />
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <button type="button" class="btn btn-success btn-add-ingredient" data-target="#ingredientContainerAdd"><i class="fas fa-plus"></i> Add Ingredient</button>
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


                <!-- Modal for product details -->
                <%                    if (listProduct != null) {
                        for (Product product : listProduct) {
                %>
                <div
                    class="modal fade"
                    id="detailProductModal-<%= product.getProductID()%>"
                    tabindex="-1"
                    role="dialog"
                    aria-labelledby="detailProductLabel"
                    aria-hidden="true"
                    >
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="detailOrderLabel">
                                    Product Details
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
                            <div class="modal-body">
                                <div class="form-group text-center" >
                                    <img
                                        src="<%= product.getImage()%>"
                                        alt=""
                                        class="d-block mx-auto"
                                        style="width: 60%; text-align: center"
                                        />
                                </div>

                                <p><strong>Product Name:</strong> <%= product.getProductName()%></p>

                                <p><strong>Category:</strong>
                                    <%
                                        for (Product_Category category : product.getListOfCategory()) {
                                    %>
                                    <span class="badge badge-success"><%= categoryMap.get(category.getCategoryID())%></span>
                                    <%
                                        }
                                    %>
                                </p>

                                <div class="row">
                                    <div class="col-md-6"><p><strong>Price:</strong> <v:Currency_Format  price="<%= product.getPrice()%>"/>đ</p></div>
                                    <div class="col-md-6"><p><strong>Cooking Time:</strong> <%= product.getCookingTime()%> Min</p></div>
                                </div>

                                <p>
                                    <strong>Product Discription:</strong>
                                    <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#productDiscription-<%= product.getProductID()%>" aria-expanded="false" aria-controls="recipeInstruction-<%= product.getProductID()%>">
                                        <span style="color: #28a745">Details</span>
                                        <svg style="width: 20px; color: #28a745" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-6">
                                        <path stroke-linecap="round" stroke-linejoin="round" d="m11.25 11.25.041-.02a.75.75 0 0 1 1.063.852l-.708 2.836a.75.75 0 0 0 1.063.853l.041-.021M21 12a9 9 0 1 1-18 0 9 9 0 0 1 18 0Zm-9-3.75h.008v.008H12V8.25Z" />
                                        </svg>
                                    </button>
                                </p>
                                <div id="productDiscription-<%= product.getProductID()%>" class="collapse">
                                    <p class="text-justify">
                                        <%= product.getDescription()%>
                                    </p>
                                </div>

                                <p>
                                    <strong>Recipe Instruction:</strong>
                                    <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#recipeInstruction-<%= product.getProductID()%>" aria-expanded="false" aria-controls="recipeInstruction-<%= product.getProductID()%>">
                                        <span style="color: #28a745">Details</span>
                                        <svg style="width: 20px; color: #28a745" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-6">
                                        <path stroke-linecap="round" stroke-linejoin="round" d="m11.25 11.25.041-.02a.75.75 0 0 1 1.063.852l-.708 2.836a.75.75 0 0 0 1.063.853l.041-.021M21 12a9 9 0 1 1-18 0 9 9 0 0 1 18 0Zm-9-3.75h.008v.008H12V8.25Z" />
                                        </svg>
                                    </button>
                                </p>
                                <div id="recipeInstruction-<%= product.getProductID()%>" class="collapse">
                                    <ul>
                                        <% for (Recipe_Product recipe : product.getListOfRecipe()) {%>
                                        <li><strong>Bước <%= recipe.getStep()%>:</strong> <%= recipe.getInstruction()%></li>
                                            <% }%>
                                    </ul>
                                </div>

                                <p>
                                    <strong>Ingredient:</strong>
                                    <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#ingredient-<%= product.getProductID()%>" aria-expanded="false" aria-controls="ingredient-<%= product.getProductID()%>">
                                        <span style="color: #28a745">Details</span>
                                        <svg style="width: 20px; color: #28a745" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-6">
                                        <path stroke-linecap="round" stroke-linejoin="round" d="m11.25 11.25.041-.02a.75.75 0 0 1 1.063.852l-.708 2.836a.75.75 0 0 0 1.063.853l.041-.021M21 12a9 9 0 1 1-18 0 9 9 0 0 1 18 0Zm-9-3.75h.008v.008H12V8.25Z" />
                                        </svg>
                                    </button>
                                </p>
                                <div id="ingredient-<%= product.getProductID()%>" class="collapse">
                                    <ol>
                                        <% for (BoxIngredient ingredient : product.getListIngredient()) {%>
                                        <li><%= ingredientMap.get(ingredient.getIngredientID())%>: <%= ingredient.getQuantity()%> <%= ingredient.getUnit()%> </li>
                                            <% } %>
                                    </ol>
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
                            </div>
                        </div>
                    </div>
                </div>
                <%}
                    }
                %>

                <!-- Update Product Modal -->
                <%
                    if (listProduct != null) {
                        for (Product product : listProduct) {
                %>
                <div
                    class="modal fade"
                    id="updateProductModal-<%= product.getProductID()%>"
                    tabindex="-1"
                    role="dialog"
                    aria-labelledby="updateProductLabel"
                    aria-hidden="true"
                    >
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="updateProductLabel">
                                    Update Product
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
                            <form role="form" action="DispatchServlet" method="post" class="updateForm" data-name="Product">
                                <input type="hidden" name="btAction" value="update_product"/>

                                <div class="modal-body">
                                    <input type="hidden" name="productID" value="<%= product.getProductID()%>" />
                                    <div class="form-group">
                                        <label for="productName">Product Name</label>
                                        <input
                                            type="text"
                                            class="form-control"
                                            id="productName"
                                            name="productName"
                                            value="<%= product.getProductName()%>"
                                            placeholder="Enter Product Name"
                                            />
                                    </div>
                                    <div class="form-group">
                                        <label for="productCategory">Category</label>
                                        <select
                                            class="form-control select2"
                                            multiple="multiple"
                                            data-placeholder="Choose Category"
                                            style="width: 100%"
                                            name="productCategory[]"
                                            >
                                            <option value="">Choose Category</option>
                                            <%
                                                if (listCategory != null) {
                                                    for (Category category : listCategory) {
                                                        boolean selected = false;
                                                        for (Product_Category productCategory : product.getListOfCategory()) {
                                                            if (productCategory.getCategoryID() == category.getCategoryID()) {
                                                                selected = true;
                                                                break;
                                                            }
                                                        }
                                            %>
                                            <option value="<%= category.getCategoryID()%>" <%= selected ? "selected" : ""%>><%= category.getCategoryName()%></option>
                                            <%
                                                    }
                                                }
                                            %>
                                        </select>
                                    </div>

                                    <div class="row">
                                        <div class="col-sm-6">
                                            <div class="form-group">
                                                <label for="productPrice">Price</label>
                                                <input
                                                    type="text"
                                                    class="form-control"
                                                    id="productPrice"
                                                    name="productPrice"
                                                    value="<%= product.getPrice()%>"
                                                    placeholder="Enter Price"
                                                    />
                                            </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <div class="form-group">
                                                <label for="productTime">Cooking Time</label>
                                                <input
                                                    type="text"
                                                    class="form-control"
                                                    id="productTime"
                                                    name="productTime"
                                                    value="<%= product.getCookingTime()%>"
                                                    placeholder="Enter Cooking Time"
                                                    />
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label>Product Description</label>
                                        <textarea
                                            class="form-control"
                                            rows="3"
                                            name="productDescription"
                                            placeholder="Enter Product Description"
                                            ><%= product.getDescription()%></textarea>
                                    </div>

                                    <div class="form-group">
                                        <label for="productImage">Food Image</label>
                                        <input
                                            type="file"
                                            class="form-control-file"
                                            id="productImage"
                                            value="<%= product.getImage()%>"
                                            name="productImage"
                                            />
                                    </div>

                                    <div class="form-group">
                                        <label>Recipe Instructions</label>
                                        <div id="recipeContainerUpdate-<%= product.getProductID()%>">
                                            <%
                                                for (Recipe_Product recipe : product.getListOfRecipe()) {
                                            %>
                                            <div class="form-group">
                                                <div class="row">
                                                    <div class="col-md-2">
                                                        <input type="number" class="form-control" placeholder="Step <%= recipe.getStep()%>" name="step[]" value="<%= recipe.getStep()%>" readonly />
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control" placeholder="Instruction for Step <%= recipe.getStep()%>" name="instruction[]" value="<%= recipe.getInstruction()%>" />
                                                    </div>
                                                </div>
                                            </div>
                                            <%
                                                }
                                            %>
                                        </div>
                                        <button type="button" class="btn btn-success btn-add-step" data-target="#recipeContainerUpdate-<%= product.getProductID()%>"><i class="fas fa-plus"></i> Add Step</button>
                                    </div>

                                    <div class="form-group">
                                        <label>Ingredients</label>
                                        <div id="ingredientContainerUpdate-<%= product.getProductID()%>">
                                            <%
                                                for (BoxIngredient ingredient : product.getListIngredient()) {
                                            %>
                                            <div class="form-group">
                                                <div class="row">
                                                    <div class="col-md-4">
                                                        <select
                                                            class="form-control"
                                                            style="width: 100%"
                                                            name="ingredientId[]"
                                                            >
                                                            <option value="">Ingredient</option>
                                                            <option selected value="<%= ingredient.getIngredientID()%>"><%= ingredientMap.get(ingredient.getIngredientID())%></option>
                                                            <%
                                                                if (listIngredient != null) {
                                                                    for (Ingredient ingredientDetails : listIngredient) {
                                                            %>
                                                            <option value="<%= ingredientDetails.getIngredientID()%>"> <%= ingredientDetails.getName()%> </option>
                                                            <%
                                                                    }
                                                                }
                                                            %>
                                                        </select>
                                                    </div>
                                                    <div class="col-md-3">
                                                        <input type="text" class="form-control" placeholder="Quantity" name="ingredientQuantity[]" value="<%= ingredient.getQuantity()%>"/>
                                                    </div>
                                                    <div class="col-md-3">
                                                        <input type="text" class="form-control" placeholder="Unit" name="ingredientUnit[]" value="<%= ingredient.getUnit()%>" />
                                                    </div>
                                                    <div class="col-md-1">
                                                        <button type="button" class="btn btn-danger btn-remove-ingredient"><i class="fas fa-minus"></i></button>
                                                    </div>
                                                </div>
                                            </div>
                                            <%
                                                }
                                            %>
                                        </div>
                                        <button type="button" class="btn btn-success btn-add-ingredient" data-target="#ingredientContainerUpdate-<%= product.getProductID()%>"><i class="fas fa-plus"></i> Add Ingredient</button>
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
                                        Update Product
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <%}
                    }
                %>

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
        <!--adminJs-->
        <script src="view/Assets/Js/admin.js"></script>
        <!-- Sweatalert2 -->
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.12.0/dist/sweetalert2.all.min.js"></script>
        <!--AdminAlert-->
        <script src="view/Assets/Js/admin-alert.js"></script>

        <script>
                                                                $(document).ready(function () {
                                                                    $(".select2").select2({
                                                                        placeholder: "Choose Category",
                                                                        width: "100%",
                                                                        multiple: true,
                                                                    });

                                                                    $('#productTable').DataTable({
                                                                        "paging": true,
                                                                        "searching": false,
                                                                        "lengthChange": false, // Disable the length change dropdown
                                                                        "info": false, // Disable the table information display
                                                                        "pageLength": 10 // Set default number of rows per page
                                                                    });
                                                                });
        </script>

        <script type="text/javascript">
            var listIngredient = [
            <% if (listIngredient != null && !listIngredient.isEmpty()) {
                    for (int i = 0; i < listIngredient.size(); i++) {
                        Ingredient ingredient = listIngredient.get(i);
            %>
            {
            id: "<%= ingredient.getIngredientID()%>",
                    name: "<%= ingredient.getName()%>"
            }
            <% if (i < listIngredient.size() - 1) { %>,<% } %>
            <% }
                }%>
            ];
        </script>





    </body>
</html>
