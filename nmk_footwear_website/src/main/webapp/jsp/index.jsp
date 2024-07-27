<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	// Set attribute to control display of special section in the header
	request.setAttribute("showSpecialSection", true);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>NMK Footwear</title>

	<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <%@ include file="header.jsp"%>
    
    <!-- Categories -->
    <section class="categories">
        <div class="small-container">
            <div class="row">
                <div class="col-3">
                    <img src="../images/category-1.jpg" alt="">
                </div>
                <div class="col-3">
                    <img src="../images/category-2.jpg" alt="">
                </div>
                <div class="col-3">
                    <img src="../images/category-3.jpg" alt="">
                </div>
            </div>
        </div>
    </section>


    <section class="products">
        <!-- Featured Products -->
        <div class="small-container">
            <h2 class="title">Featured Products</h2>
            <hr> <br>
            <div class="row">
                <div class="col-4">
                    <img src="../images/product-7.jpg" alt="">
                    <h4>Product name</h4>
                    <p>price</p>
                </div>
                <div class="col-4">
                    <img src="../images/product-7.jpg" alt="">
                    <h4>Product name</h4>
                    <p>price</p>
                </div>
                <div class="col-4">
                    <img src="../images/product-7.jpg" alt="">
                    <h4>Product name</h4>
                    <p>price</p>
                </div>
                <div class="col-4">
                    <img src="../images/product-7.jpg" alt="">
                    <h4>Product name</h4>
                    <p>price</p>
                </div>
            </div>

            <!-- Latest Products -->
            <h2 class="title">Latest Products</h2>
            <hr> <br>
            <div class="row">
                <div class="col-4">
                    <img src="../images/product-6.jpg" alt="">
                    <h4>Product name</h4>
                    <p>price</p>
                </div>
                <div class="col-4">
                    <img src="../images/product-6.jpg" alt="">
                    <h4>Product name</h4>
                    <p>price</p>
                </div>
                <div class="col-4">
                    <img src="../images/product-6.jpg" alt="">
                    <h4>Product name</h4>
                    <p>price</p>
                </div>
                <div class="col-4">
                    <img src="../images/product-6.jpg" alt="">
                    <h4>Product name</h4>
                    <p>price</p>
                </div>
                <div class="col-4">
                    <img src="../images/product-6.jpg" alt="">
                    <h4>Product name</h4>
                    <p>price</p>
                </div>
                <div class="col-4">
                    <img src="../images/product-6.jpg" alt="">
                    <h4>Product name</h4>
                    <p>price</p>
                </div>
                <div class="col-4">
                    <img src="../images/product-6.jpg" alt="">
                    <h4>Product name</h4>
                    <p>price</p>
                </div>
                <div class="col-4">
                    <img src="../images/product-6.jpg" alt="">
                    <h4>Product name</h4>
                    <p>price</p>
                </div>
            </div>
        </div>

        <!----------------------- brands --------------------->
        <section class="brands">
            <div class="small-container">
                <div class="row">
                    <div class="col-5">
                        <img src="../images/logo-godrej.png" alt="">
                    </div>
                    <div class="col-5">
                        <img src="../images/logo-oppo.png" alt="">
                    </div>
                    <div class="col-5">
                        <img src="../images/logo-coca-cola.png" alt="">
                    </div>
                    <div class="col-5">
                        <img src="../images/logo-paypal.png" alt="">
                    </div>
                    <div class="col-5">
                        <img src="../images/logo-philips.png" alt="">
                    </div>
                </div>
            </div>
        </section>
    </section>
    
    <%@ include file="footer.jsp"%>
    
</body>
</html>