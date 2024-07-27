<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All products | NMK</title>

    <link rel="stylesheet" href="<c:url value='css/style.css'/>">
    
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            const variants = JSON.parse('${variantsJson}');
            console.log(variants);

            updateImageAndSizes();
            document.getElementById('color').addEventListener('change', updateImageAndSizes);
            document.getElementById('size').addEventListener('change', updatePriceAndQuantity);
            
            function updateImageAndSizes() {
                const selectedColor = document.getElementById('color').value;
                
                // Filter variants based on selected color
                const availableVariants = variants.filter(variant => variant.color === selectedColor);
                const availableSizes = Array.from(new Set(availableVariants.map(variant => variant.size))); // Unique sizes                
                
                //Remove current sizes
                document.getElementById('size').innerHTML = '';
                
                availableSizes.forEach(size => {
                    const option = document.createElement('option');
                    option.value = size;
                    option.innerText = size;
                    document.getElementById('size').appendChild(option);
                });
                
                // Update image
                document.getElementById('variant-image').src = availableVariants[0].imageURL;
                updatePriceAndQuantity();
            }
            
            function updatePriceAndQuantity() {
            	const selectedColor = document.getElementById('color').value;
                const selectedSize = document.getElementById('size').value;
                
                // Filter variant based on selected colro and size
                const selectedVariant = variants.find(variant => variant.color === selectedColor && variant.size === selectedSize);
                
                // Update price and quantity
                if(selectedVariant) {
                	console.log(selectedVariant);
                	
                	document.getElementById('price').innerText = selectedVariant.price;
                	document.getElementById('in-stock-quantity').innerText = 'This product has ' + selectedVariant.stockQuantity + ' pair(s) left.';
                	document.getElementById('product-variant-id').value = selectedVariant.productVariantId; // Update hidden input with variant ID
                	console.log(selectedVariant.productVariantId);
                }
            }
        });
    </script>
</head>
<body>
	<!----------------------- header -------------------->
	<jsp:include page="header.jsp" />

    <!-- Product details -->
     <div class="small-container">
        <div class="row">
            <div class="col-2">
                <img id="variant-image" src='<c:url value="${product.variants.iterator().next().imageURL}" />' alt="product image">
            </div>
            <div class="col-2">
                <h1 style="margin: 0 0 0 20px; font-size: 45px">Product Details</h1>
                
                <span id="in-stock-quantity">This product has ${product.variants.iterator().next().stockQuantity} pair(s) left.</span>
                <hr style="margin-left: 20px;">
                <form action="cart" method="post">
	                <table>
	                    <tr>
	                        <th>Product name</th>
	                        <td><span id="product-name">${product.name}</span></td>
	                    </tr>
	                    <tr>
	                        <th>Price</th>                 
	                        <td><b id="price">&#36;  ${product.variants.iterator().next().price}</b></td>
	                    </tr>
	                    <tr>
	                        <th>Color</th>
	                        <td>
	                            <select name="color" id="color">
	                                <c:forEach var="variant" items="${product.variants}" varStatus="status">
	                            		<option value="${variant.color}">${variant.color}</option>
	                            	</c:forEach>
	                            </select>
	                        </td>
	                    </tr>
	                    <tr>
	                        <th>Size</th>
	                        <td>
	                            <select name="size" id="size">
	                            </select>
	                        </td>
	                    </tr>
	                    <input type="hidden" id="product-variant-id" name="productVariantId">
	                    <input type="hidden" id="action" name="action" value="add">
	                </table>
	                <button type="submit" id="add-btn" class="btn">Add to cart</button>
				</form>
            </div>
        </div>

        <hr> <br>

        <div class="row" id="product-description">
            <div class="col-1">
            	<h2>Product description</h2>
	            <p>
	                ${product.description}
	            </p>
            </div>
        </div>

        <!-- Related products -->
        <br> <br> <hr> <br>
        <div class="row-related">
            <h2 style="text-align: center; margin: 30px 0px">Related products</h2>
            <a href="products.html">View more...</a>
        </div>
        <div class="row">
            <div class="col-4">
                <img src="../img/product-7.jpg" alt="">
                <h4>Product name</h4>
                <p>price</p>
            </div>
            <div class="col-4">
                <img src="../img/product-7.jpg" alt="">
                <h4>Product name</h4>
                <p>price</p>
            </div>
            <div class="col-4">
                <img src="../img/product-7.jpg" alt="">
                <h4>Product name</h4>
                <p>price</p>
            </div>
            <div class="col-4">
                <img src="../img/product-7.jpg" alt="">
                <h4>Product name</h4>
                <p>price</p>
            </div>
        </div>  
        
     </div>
     
    <!----------------------- footer --------------------->
	<%@include file="footer.jsp" %>
    
</body>
</html>