<%-- 
    Document   : home
    Created on : Jun 24, 2024, 4:04:01 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <link rel="icon" href="view/Assets/Images/logo.png" />

        <!-- Css -->
        <link rel="stylesheet" href="view/Assets/css/style.css" />

        <!-- Fonts -->
        <!-- Outfit -->
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Outfit:wght@100..900&display=swap"
            rel="stylesheet"
            />
        <!-- Roboto -->
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap"
            rel="stylesheet"
            />

        <!-- Logo fonts -->
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
            />

        <title>Fresh Meal</title>
    </head>
    <body>
        <%@include file="../../common/header.jsp" %>

        <section class="hidden">
            <div class="hero">
                <img src="view/Assets/Images/hero.jpg" alt="" />
                <div class="content-header">
                    <h1>All fresh and delicious meal delivered to you</h1>
                    <a class="button dark-btn large-btn" href="DispatchServlet?btAction=Menu">Get Started</a>
                </div>
            </div>
        </section>

        <section class="hidden">
            <div class="meal">
                <div class="meal-content">
                    <h1>Pick Your <span>Meals</span></h1>
                    <h3>
                        Start with either a Meal Kit or Fresh,
                        <br />
                        Prepared Meal plan and then customize your weekly delivery.
                    </h3>
                    <p>
                        It's easy peasy. Get exactly what you want—meal kits, prepared
                        meals, or both—every time. <br />
                        And don't worry, we've got lots of Less Prep, high-protein and
                        gluten-free meals.
                    </p>
                </div>
                <div class="meal-menu">
                    <div class="meal-kit">
                        <img src="view/Assets/Images/Meal-Kit.png" alt="meal-kit" />
                        <h4>Meal Kits</h4>
                        <p>
                            We'll send you everything you need to slice, dice, <br />
                            and sauté your way to mouthwatering meals.
                        </p>
                    </div>
                    <div class="meal-prepare">
                        <img src="view/Assets/Images/Prepare-meal.png" alt="meal-prepare" />
                        <h4>Fresh, Prepared Meals</h4>
                        <p>
                            Heat 'em up—they're ready in less than <br />
                            5 minutes—and dig in!
                        </p>
                    </div>
                </div>

                <div class="meal-btn">
                    <a class="button dark-btn large-btn" href="DispatchServlet?btAction=Plans">Customize Your Plan</a>
                </div>
            </div>
        </section>

        <section class="hidden">
            <div class="landscape">
                <img src="view/Assets/Images/landscape1.png" alt="" />
            </div>
        </section>

        <section class="hidden">
            <div class="menu">
                <div class="menu-content">
                    <h2>CHOOSE FROM</h2>
                    <h3>80+ weekly options</h3>
                </div>

                <div class="menu-separate">
                    <div class="menu-contain">
                        <img src="view/Assets/Images/food/food1.jpg" alt="" />
                        <h4>CRAFT</h4>
                        <p>Sườn Chua Ngọt</p>
                    </div>

                    <div class="menu-contain">
                        <img src="view/Assets/Images/food/food2.jpg" alt="" />
                        <h4>PREMIUM</h4>
                        <p>Tôm Hùm Nướng</p>
                    </div>

                    <div class="menu-contain">
                        <img src="view/Assets/Images/food/food3.jpg" alt="" />
                        <h4>FAMILY FRIENDLY</h4>
                        <p>Lẩu Thái</p>
                    </div>

                    <div class="menu-contain">
                        <img src="view/Assets/Images/food/food4.jpg" alt="" />
                        <h4>FAST & EASY</h4>
                        <p>Kho Quẹt</p>
                    </div>

                    <div class="menu-contain">
                        <img src="view/Assets/Images/food/food5.jpg" alt="" />
                        <h4>READY TO COOK</h4>
                        <p>Bò xào hành tây</p>
                    </div>
                </div>

                <div class="menu-separate">
                    <div class="menu-contain">
                        <img src="view/Assets/Images/food/food6.jpg" alt="" />
                        <h4>WELLNESS</h4>
                        <p>Đậu hũ sốt cà</p>
                    </div>

                    <div class="menu-contain">
                        <img src="view/Assets/Images/food/food7.png" alt="" />
                        <h4>PREPARED & READY</h4>
                        <p>Cơm Chiên Dứa</p>
                    </div>

                    <div class="menu-contain">
                        <img src="view/Assets/Images/food/food8.jpg" alt="" />
                        <h4>BREAKFAST</h4>
                        <p>Phở</p>
                    </div>

                    <div class="menu-contain">
                        <img src="view/Assets/Images/food/food9.jpg" alt="" />
                        <h4>VEGETARIAN</h4>
                        <p>Chả Giò Chay</p>
                    </div>

                    <div class="menu-contain">
                        <img src="view/Assets/Images/food/food10.jpg" alt="" />
                        <h4>DESSERTS</h4>
                        <p>Gỏi đu đủ</p>
                    </div>
                </div>

                <a href="DispatchServlet?btAction=Menu" class="button dark-btn large-btn">Browse our menus</a>
            </div>
        </section>

        <section class="hidden">
            <div class="guide">
                <div class="guide-flex">
                    <img src="view/Assets/Images/guide-1.svg" alt="" />
                    <div class="content-guide">
                        <h4>Choose your meals</h4>
                        <p>
                            We keep dinner interesting. From top-rated favorites and
                            health-conscious options to Premium dishes and more, variety is
                            always on the menu.
                        </p>
                    </div>
                </div>
                <div class="guide-flex">
                    <div class="content-guide">
                        <h4>Unpack your box</h4>
                        <p>
                            We guarantee the freshness of all our ingredients and deliver them
                            in an insulated box right to your door.
                        </p>
                    </div>
                    <img src="view/Assets/Images/guide-2.svg" alt="" />
                </div>
                <div class="guide-flex">
                    <img src="view/Assets/Images/guide-3.svg" alt="" />
                    <div class="content-guide">
                        <h4>NEW! Cook, create, enjoy</h4>
                        <p>
                            Follow our easy step-by-step recipes to learn new skills, try new
                            tastes, and make your family amazing meals.
                        </p>
                    </div>
                </div>
            </div>
        </section>

        <section class="hidden">
            <div class="plan">
                <img src="view/Assets/Images/plan.png" alt="" />
                <h2>STARTING AT 150K VND PER SERVING</h2>
                <p>No commitment. Skipping or canceling meals is easy.</p>
                <a href="DispatchServlet?btAction=Plans" class="button dark-btn large-btn">Choose Your Plan</a>
            </div>
        </section>
        <section class="hidden">
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
        </section>
        <%@include file="../../common/footer.jsp" %>
        <script src="view/Assets/Js/index.js"></script>
    </body>
</html>
