<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All products | NMK</title>

    <link rel="stylesheet" href="css/style.css">

    <script>
        document.addEventListener('DOMContentLoaded', function () {
            const action = document.getElementsByName('action');
            console.log(action);

        });
    </script>

</head>

<body>
    <%@ include file="header.jsp" %>

        <!--- cart items details --------------------->
        <div class="small-container">
            <h1>My cart</h1>
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

                        <c:forEach var="item" items="${sessionScope.cart.cartItems}" varStatus="status">
                            <tr>
                                <form action="cart" method="post">
                                    <td>${status.index + 1}</td>
                                    <td>
                                    	${item.productVariant.product.name} <br>
                                        &#40;${item.productVariant.color} - ${item.productVariant.size}&#41;
                                    </td>
                                    <td>
                                    	<input type="number" name="quantity" value="${item.quantity}" min="1" max="10">
                                        <input type="hidden" name="productVariantId" value="${item.productVariant.productVariantId}">
                                        <input class="update-btn" type="submit" name="action" value="update">
                                        <input class="delete-btn" type="submit" name="action" value="delete">
                                    </td>
                                    <td>${item.price}</td>
                                    <td>${item.subtotal}</td>
                                </form>
                            </tr>
                        </c:forEach>

                        <tr>
                            <th colspan="4" style="text-align: right;">Total :</th>
                            <th>&#36; ${sessionScope.cart.totalPrice}</th>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="row">
                <div class="col-1" style="display: flex; align-items: center; justify-content: space-between">
                    <a href="products" style="text-decoration: underline; margin-right: 20px">Continue
                        shopping... </a>
                    <a class="btn" href="checkout"> Check out</a>
                </div>
            </div>


        </div>

        <%@ include file="footer.jsp" %>

</body>

</html>