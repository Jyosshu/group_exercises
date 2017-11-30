<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="common/header.jsp"  %>

<section class="centeredPanel">
	<h1>Solar System Geek Gift Shop</h1>
		
	<c:forEach items="${products}" var="product">
		<div class="content-container">
			<div class="image-container">
				<c:url var="detailLink" value="/shoppingCart/productDetail/${product.id}" />
				<c:url var="imagePath" value="/img/${product.imageName}" />
				<a href="${detailLink}"> <img src="${imagePath}" /> </a>
			</div>
			<div class="info-container">
				<c:url var="detailLink" value="/shoppingCart/productDetail/${product.id}" />
				<a href="${detailLink}"> <c:out value="${product.name}" /> </a>
				<c:out value="${product.price}" />
			</div>
		</div>
	</c:forEach>
		
</section>
	
	<%@include file="common/footer.jsp"  %>