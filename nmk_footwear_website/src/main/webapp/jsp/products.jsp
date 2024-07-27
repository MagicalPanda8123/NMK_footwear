<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All products | NMK</title>

    <link rel="stylesheet" href="<c:url value='css/style.css'/>">
</head>
<body>
    <jsp:include page="header.jsp">
    	<jsp:param name="showSpecialSection" value="false" />
	</jsp:include>


    <!-- All Products -->
    <div class="small-container">
        <h2 class="title">All Products</h2>

        <div class="filter-bar">
           <form id="filter-form" action="" method="get">
                <div class="row">
                    <div class="col-2">
                        <label for="price-filter">Price sorting &#36;</label> <br>
                        <select name="price-filter" id="price-filter">
                            <option value="default">Default sorting</option>
                            <option value="asc">&#8593; Sort by price (ascending)</option>
                            <option value="desc">&#8595; Sort by price (descending)</option>
                        </select>
                    </div>
                    <div class="col-2">
                        <label for="type-filter">Type</label>
                        <select name="type-filter" id="type-filter">
                            <option value="all">All</option>
                            <option value="sneaker">Sneaker</option>
                            <option value="slides">Slides</option>
                        </select>
                    </div>
                   
                </div>
                <div class="row">
                    <div class="col-2">
                        <label for="brand-filter">Brand</label> <br>
                        <select name="brand-filter" id="brand-filter">
                            <option value="all">All</option>
                            <option value="nike">Nike</option>
                            <option value="jordan">Jordan</option>
                            <option value="adidas">Adidas</option>
                            <option value="new balance">New Balance</option>
                        </select>
                    </div>
                    <div class="col-2">
                        <label for="gender-filter">Gender</label> <br>
                        <input type="radio" name="gender" value="all" id="all" checked> all
                        <input type="radio" name="gender" value="male" id="male"> male &#9794;
                        <input type="radio" name="gender" value="female" id="female"> female &#9792;
                    </div>
                </div>
                
                <div class="row">
                    <div class="col-2">
                        <label for="search">Search</label> <br>
                        <input type="text" name="search" id="search">
                    </div>
                    <div class="col-2">
                        <button type="submit">Search</button>
                    </div>
                </div>
                
           </form>
        </div>

        <hr> <br>
        
        <!-- Products Display -->
        <c:forEach var="product" items="${products}" varStatus="status">
		    <c:if test="${status.index % 4 == 0}">
		        <div class="row">
		    </c:if>
		
			<!-- Get the first variant image. --> 
		    <c:forEach var="variant" items="${product.variants}" begin="0" end="0">
		        <div class="col-4">
		        	<a href='<c:url value='product-detail?productId=${product.productId}' />'>
		               <img src="<c:url value='${variant.imageURL}'/>" alt="${product.name}">
		               <h4>${product.name}</h4>
		               <p>${variant.price}</p>
		        	</a>
	            </div>
	        </c:forEach>
		
		    <c:if test="${status.index % 4 == 3 || status.last}">
		        </div>
		    </c:if>
		</c:forEach>

		<!-- Pagination control -->
        <div class="page-btn">
        
        	<!-- Back to top button -->
        	<c:if test="${currentPage > 1 }">
	            <span>
	            	<a href="?page=1"> &#8606; </a>
	            </span>
        	</c:if>
        	
        	<!-- Previous button -->
        	<c:if test="${currentPage > 1 }">
	            <span>
	            	<a href="?page=${currentPage - 1}"> &#8592; </a>
	            </span>
        	</c:if>
        	
        	<!-- Page numbers -->
        	<c:forEach var="i" begin="1" end="${totalPageCount}" varStatus="status">
	            <!-- Display only 5 pages at a time -->
		    	<c:choose>
		            <c:when test="${status.index >= currentPage - 3 && status.index <= currentPage + 1}">
		                <c:if test="${i == currentPage}">
		                    <span class="active">${i}</span>
		                </c:if>
		                <c:if test="${i != currentPage}">
		                    <span>
		                    	<a href="?page=${i}">${i}</a>
		                    </span>
		                </c:if>
		            </c:when>
		        </c:choose>
        	</c:forEach>
        	
        	<!-- Next button -->
        	<c:if test="${currentPage < totalPageCount}">
	            <span>
	            	<a href="?page=${currentPage + 1}"> &#8594; </a>
	            </span>
        	</c:if>
        	
        	<!-- Last page button -->
        	<c:if test="${currentPage < totalPageCount}">
	            <span>
	            	<a href="?page=${totalPageCount}"> &#8608; </a>
	            </span>
        	</c:if>
        	
			<!-- 
			<span>1</span>
            <span>2</span>
            <span>3</span>
            <span>4</span>
			 -->
            
        </div>
        
    </div>

    <%@ include file="footer.jsp" %>
</body>
</html>