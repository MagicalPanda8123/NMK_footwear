<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<header>
	<div class="container">
		<div class="navbar">
			<div class="logo">
				<img src="<c:url value='images/logo.png'/>" alt="logo image">
			</div>
			<nav>
				<ul>
					<li><a href="home">Home</a></li>
					<li><a href="products">Products</a></li>
					<li><a href="contact">Contact</a></li>

					<!-- Check if the user is logged in -->
					<c:choose>
						<c:when test="${sessionScope.user != null}">
							<!-- User is logged in -->
							<li><a href="profile">My Profile</a></li>
							<li><a href="orders">My Orders</a></li>
							<li><a href="logout">Logout</a></li>
						</c:when>

						<c:otherwise>
							<!-- User is not logged in -->
							<li><a href="account">Account</a></li>
						</c:otherwise>
					</c:choose>

				</ul>
			</nav>
			<a href="cart"> <img id="cart-symbol"
				src="<c:url value='images/cart.png'/>" alt="cart image" width="30px"
				height="30px">
			</a>
		</div>

		<c:if test="${requestScope.showSpecialSection}">
			<div class="row">
				<div class="col-2">
					<h1>
						Give Your Workout <br> A New Style !
					</h1>
					<p>
						Success isn't always about greatness. It's about consistency. <br>
						Consistent hard work gains success. Greatness will come.
					</p>
					<a href="home" class="btn">Expore Now</a>
				</div>
				<div class="col-2">
					<img src="images/image1.png">
				</div>
			</div>
		</c:if>
	</div>
</header>