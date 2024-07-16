<%-- 
    Document   : how
    Created on : Jun 27, 2024, 8:30:06 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <link rel="icon" href="view/Assets/Images/logo.png" />

        <!-- Css -->
        <link rel="stylesheet" href="view/Assets/css/style.css" />
        <link rel="stylesheet" href="view/Assets/css/how.css" />

        <!-- Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Outfit:wght@100..900&display=swap"
            rel="stylesheet"
            />

        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Poetsen+One&display=swap"
            rel="stylesheet"
            />

        <!-- Logo fonts -->
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
            />

        <title>How it Works - Fresh Meal</title>
    </head>
    <body>
        <%@include file="../../common/header.jsp" %>

        <section class="hidden">
            <div class="header-how">
                <h1>How It Works</h1>
            </div>
        </section>

        <div class="container-how">
            <section class="hidden">
                <div class="content-how">
                    <img src="view/Assets/Images/how-1.webp" alt="" />
                    <div class="text">
                        <h1>Pick your meals</h1>
                        <p>
                            Choose your meals from our diverse weekly menu, including low carb
                            & veggie options!
                        </p>
                        <a href="DispatchServlet" class="button dark-btn">Get Started</a>
                    </div>
                </div>
            </section>

            <section class="hidden">
                <div class="content-how">
                    <div class="text">
                        <h1>We do the work</h1>
                        <p>
                            Our team of chefs prepare the fresh ingredients - no more
                            chopping, measuring, or sink full of dishes!
                        </p>
                        <a href="DispatchServlet" class="button dark-btn">Get Started</a>
                    </div>
                    <img src="view/Assets/Images/how-2.jpg" alt="" />
                </div>
            </section>

            <section class="hidden">
                <div class="content-how">
                    <img src="view/Assets/Images/how-3.jpg" alt="" />
                    <div class="text">
                        <h1>Receive your delivery</h1>
                        <p>
                            Enjoy your hand picked, quality, delicious, flavorful meal in
                            15-minutes or less.
                        </p>
                        <a href="DispatchServlet" class="button dark-btn">Get Started</a>
                    </div>
                </div>
            </section>

            <section class="hidden">
                <div class="content-how">
                    <div class="text">
                        <h1>Dinner in 15-min</h1>
                        <p>
                            Our prepared meal kits make cooking fast, so you have time to
                            unwind and be with family.
                        </p>
                        <a href="DispatchServlet" class="button dark-btn">Get Started</a>
                    </div>
                    <img src="view/Assets/Images/how-4.webp" alt="" />
                </div>
            </section>
        </div>

        <section class="hidden">
            <div class="faq">
                <h1>Frequently Asked Questions</h1>
                <ul class="accordion">
                    <li>
                        <input type="radio" name="accordion" id="first" />
                        <label for="first">Are there any commitments?</label>
                        <div class="content">
                            <p>You may cancel or skip at any time.</p>
                        </div>
                    </li>

                    <li>
                        <input type="radio" name="accordion" id="second" />
                        <label for="second">How much does it cost?</label>
                        <div class="content">
                            <p>
                                Our meals are a flat rate per serving, and starting at 150K VND
                                per serving.
                            </p>
                        </div>
                    </li>

                    <li>
                        <input type="radio" name="accordion" id="third" />
                        <label for="third">Can I choose my own meals?</label>
                        <div class="content">
                            <p>
                                Absolutely — in fact we hope you do! Or if you prefer, we can
                                automatically assign meals for your weekly deliveries based on
                                your taste preference.
                            </p>
                        </div>
                    </li>

                    <li>
                        <input type="radio" name="accordion" id="fourth" />
                        <label for="fourth">Does Fresh Meal ship to my area?</label>
                        <div class="content">
                            <p>
                                We deliver at 2 major centers in Vietnam: Ho Chi Minh City and
                                Hanoi
                            </p>
                        </div>
                    </li>

                    <li>
                        <input type="radio" name="accordion" id="fifth" />
                        <label for="fifth"
                               >What if I have food allergies or intolerances?</label
                        >
                        <div class="content">
                            <p>
                                Your meals can be prepared dairy-free, nut-free, or wheat-free
                                by withholding certain ingredients that are packed separately in
                                the dinner kit. A full list of ingredients can be found on our
                                website and recipe cards.
                            </p>
                        </div>
                    </li>

                    <li>
                        <input type="radio" name="accordion" id="sixth" />
                        <label for="sixth"
                               >What if I don’t want a delivery every week?</label
                        >
                        <div class="content">
                            <p>
                                Of course, Fresh Meal is here to fit your schedule. We make it
                                easy to skip or modify any delivery.
                            </p>
                        </div>
                    </li>
                </ul>
            </div>
        </section>

        <%@include file="../../common/footer.jsp" %>

        <script src="view/Assets/Js/index.js"></script>
    </body>
</html>

