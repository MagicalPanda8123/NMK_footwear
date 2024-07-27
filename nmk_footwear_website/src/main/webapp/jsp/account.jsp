<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>All products | NMK</title>

<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%@ include file="header.jsp"%>

	<!-----------------------account page --------------------->
	<div class="account-page">
		<div class="container">
			<div class="row">
				<div class="col-2">
					<img src="images/image1.png" alt="">
				</div>
				<div class="col-2">
					<div class="form-container">
						<div class="form-btn">
							<span onclick="login()">Login</span> <span onclick="register()">Register</span>
							<hr id="Indicator">

							<form id="login-form" action="account" method="post">
								<div>
									<label for="username">Username : </label> <br> 
									<input type="text" name="username" id="username">
								</div>

								<div>
									<label for="password">Password : </label> <br> <input
										type="password" name="password" id="password">
								</div>
								<button style="margin-bottom: 30px;" type="submit" class="btn">Login</button>
								<a id="forgot-password" href="#">Forgot your password ?</a>

								<c:if test="${error != null}">
									<span id="login-message">${error}</span>
								</c:if>
							</form>

							<form id="register-form" action="account" method="post">
								<div>
									<label for="name">Name : </label> <br> <input type="text"
										name="" id="" placeholder="full name">
								</div>
								<div>
									<label for="">Email : </label> <br> <input type="email"
										name="email" id="" placeholder="email">
								</div>

								<label for="dob">Date of birth (day/ month/ year) :</label> <br>
								<div id="dob">
									<select name="day" id="day">
										<option value="">day</option>
										<!--Use script element to generate days of a month-->
									</select> <select name="month" id="month">
										<option value="">month</option>
										<!--Use script element to generate months in a year-->
									</select> <select name="year" id="year">
										<option value="">year</option>
										<!--Use script element to generate years (from 1990 - current year)-->
									</select>
								</div>

								<div>
									<label for="">Phone: </label> <br> <input type="tel"
										name="" id="" placeholder="phone number">
								</div>

								<div>
									<label for="">Username : </label> <br> <input type="text"
										name="" id="" placeholder="username">
								</div>

								<div>
									<label for="">Password : </label> <br> <input
										type="password" name="" id="">
								</div>

								<div>
									<label for="">Confirm password : </label> <br> <input
										type="password" name="" id="">
								</div>

								<button style="margin-bottom: 30px;" type="submit" class="btn">Register</button>

							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<%@ include file="footer.jsp"%>

	<script>
		window.onload = function() {
			let daySelect = document.getElementById('day');
			let monthSelect = document.getElementById('month');
			let yearSelect = document.getElementById('year');

			for (let i = 1; i <= 31; i++) {
				daySelect.innerHTML += `<option value="${i}">${i}</option>`;
			}

			for (let i = 1; i <= 12; i++) {
				monthSelect.innerHTML += `<option value="${i}">${i}</option>`;
			}

			let currentYear = new Date().getFullYear();
			for (let i = currentYear; i >= 1990; i--) {
				yearSelect.innerHTML += `<option value="${i}">${i}</option>`;
			}
		};

		let loginForm = document.getElementById('login-form');
		let registerForm = document.getElementById('register-form');
		let indicator = document.getElementById('Indicator');
		let formContainer = document.querySelector('.form-container');

		function register() {
			registerForm.style.transform = "translateX(-130%)";
			loginForm.style.transform = "translateX(-200%)";
			formContainer.style.height = "1050px";
			indicator.style.transform = "translateX(100px)";
		}

		function login() {
			formContainer.style.height = "600px";
			loginForm.style.left = "5%";
			loginForm.style.transform = "translateX(0px)";
			registerForm.style.transform = "translateX(100%)";
			indicator.style.transform = "translateX(0px)";
		}
	</script>
</body>
</html>