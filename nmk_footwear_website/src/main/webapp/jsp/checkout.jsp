<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>All products | NMK</title>

<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<%@ include file="header.jsp"%>

	<!--- cart items details --------------------->
	<div class="small-container">
		<h1>Order summary</h1>
		<div class="row">
			<div class="col-1">
				<table id="cart-table">
					<tr>
						<th>no.</th>
						<th>Product</th>
						<th>Quantity</th>
						<th>Price</th>
						<th>Subtotal</th>
					</tr>
					<c:forEach var="item" items="${sessionScope.cart.cartItems}"
						varStatus="status">
						<tr>
							<td>${status.index + 1}</td>
							<td>${item.productVariant.product.name}<br>
								&#40;${item.productVariant.color} - ${item.productVariant.size}
								&#41;
							</td>
							<td>${item.quantity}</td>
							<td>${item.price}</td>
							<td>${item.subtotal}</td>
						</tr>
					</c:forEach>

					<tr>
						<td colspan="5"></td>
					</tr>

					<tr>
						<th colspan="4" style="text-align: right;">Total amount</th>
						<td>&#36; ${sessionScope.cart.totalPrice }</td>
					</tr>
					<tr>
						<th colspan="4" style="text-align: right;"><label
							for="promotion-code">Promotion code</label> <br>
							<form action="promotion-processing" method="post"
								style="display: inline;">
								<input type="text" name="promotionCode" id="promotion-code">
								<input type="hidden" name="totalAmount"
									value="${sessionScope.cart.totalPrice}" /> <input type="submit"
									value="Apply">
							</form> <c:if test="${not empty promotionError}">
								<br>
								<span style="color: red; font-size: 200">${promotionError}</span>
							</c:if> <c:choose>
								<c:when test="${not empty promotionError}">
									<br>
									<span style="color: red; font-size: 200">${promotionError}</span>
								</c:when>
								<c:when test="${not empty promotion}">
									<br>
									<span style="color: red; font-size: 200">${sessionScope.promotion.code}
										applied</span>
								</c:when>
							</c:choose></th>
						<c:if test="${not empty discountedAmount}">
							<td><span style="color: red"> - &#36;
									${discountedAmount} <br> &#40;
									${sessionScope.promotion.discountPercentage} &#37; off &#41;
							</span></td>
						</c:if>
					</tr>

					<tr>
						<th colspan="4" style="text-align: right;">Final total amount
						</th>
						<c:choose>
							<c:when test="${not empty finalAmount}">
								<td>&#36; ${sessionScope.finalAmount}</td>
							</c:when>
							<c:otherwise>
								<td>&#36; ${sessionScope.cart.totalPrice}</td>
							</c:otherwise>
						</c:choose>
					</tr>
				</table>
			</div>
		</div>

		<div class="row">
			<div class="col-1">
				<h2>Delivery information</h2>
				<form id="delivery-info-form" action="order" method="POST">
					<div class="row">
						<div class="col-1">
							<h3>Recipient</h3>
						</div>
					</div>

					<div class="row">
						<div class="col-2">
							<label for="name">Name</label>
						</div>
						<div class="col-2">
							<input type="text" id="name" name="name" required> <br>
						</div>
					</div>
					<div class="row">
						<div class="col-2">
							<label for="phone">Phone number</label>
						</div>
						<div class="col-2">
							<input type="text" id="phone" name="phone" required>
						</div>
					</div>

					<div class="row">
						<div class="col-1">
							<h3>Delivery address</h3>
						</div>
					</div>

					<div class="row">
						<div class="col-2">
							<label for="street">Street (including number)</label>
						</div>
						<div class="col-2">
							<input type="text" id="street" name="street" required> <br>

						</div>
					</div>
					<div class="row">
						<div class="col-2">
							<label for="city">City</label>
						</div>
						<div class="col-2">
							<input type="text" id="city" name="city" required>

						</div>
					</div>
					<div class="row">
						<div class="col-2">
							<label for="state">State</label>
						</div>
						<div class="col-2">
							<input type="text" id="state" name="state" required>
						</div>
					</div>
					<div class="row">
						<div class="col-2">
							<label for="zip">Zip code</label>
						</div>
						<div class="col-2">
							<input type="text" id="zip" name="zip" required>
						</div>
					</div>

					<div class="row">
						<div class="col-1">
							<h3>Payment information</h3>
						</div>
					</div>

					<div class="row">
						<div class="col-2">
							<label for="payment-method">Payment method</label>
						</div>
						<div class="col-2">
							<select name="payment-method" id="payment-method" required>
								<option value="CREDIT_CARD">Credit card</option>
								<option value="BANK_TRANSFER">Bank transfer</option>
								<option value="CASH_ON_DELIVERY">COD</option>
							</select>
						</div>
					</div>

					<div class="row">
						<div class="col-1">

							<input class="btn" type="submit" value="Place order">
						</div>
					</div>
				</form>
			</div>
		</div>
		<a href="cart"
			style="text-decoration: underline; margin-bottom: 40px; display: inline-block">
			Return to cart page </a>
	</div>


	<%@ include file="footer.jsp"%>

</body>
</html>