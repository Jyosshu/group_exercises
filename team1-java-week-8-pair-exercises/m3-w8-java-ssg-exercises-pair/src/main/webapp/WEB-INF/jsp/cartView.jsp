<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="common/header.jsp"  %>

<section class="centeredPanel">
	<h1>Solar System Shopping Cart</h1>

	<table>
		<tr>
			<th>&nbsp;</th>
			<th>Name</th>
			<th>Price</th>
			<th>Qty</th>
			<th>Total</th>
		</tr>
		<c:forEach var="entry" items="${productList}">
			<c:set var="product" value="${entry.key}"/>
			<c:set var="quantity" value= "${entry.value }"/>
			<tr>
				<td><c:url value="/img/${product.imageName}" var ="image"/>
					<img src="${image}"/></td>
				<td><c:out value="${product.name}" /></td>
				<td><c:out value="${product.price}" /></td>
				<td><c:out value="${quantity}" /></td>
				<td><c:out value="${product.price * quantity}" /></td>
			</tr>
		</c:forEach>
			
	</table>


</section>

<%@include file="common/footer.jsp"  %>