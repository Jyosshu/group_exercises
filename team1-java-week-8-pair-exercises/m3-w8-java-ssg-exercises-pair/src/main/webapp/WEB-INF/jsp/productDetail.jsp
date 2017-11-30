<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="common/header.jsp"  %>

<section class="centeredPanel">
	<h1>Solar System Geek Gift Shop</h1>
	
		<div class="content-container">
			<div class="image-container">
				<c:url var="imagePath" value="/img/${product.imageName}" />
				 <img src="${imagePath}" /> 
			</div>
			<div class="info-container">
				
				<c:out value="${product.name}" /> 
				<c:out value="${product.price}" />
				<p> <c:out value="${product.description}" /> </p>
			</div>
			<div class="form-container">
				<c:url var="storeFormLink" value="/shoppingCart/addToCart" />
				<form method="POST" action="${storeFormLink}">
					<input type="hidden" name="productId" value="${product.id}" />
					<label for="quantity">Quantity to buy</label>
					<input type="number" name="quantity" id="quantity" min="1"/>
					
					<input type="submit" value="Add to Cart" />
				</form>
			</div>
		</div>
</section>

<%@include file="common/footer.jsp"  %>