<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@include file="common/header.jsp"  %>

<section class="centeredPanel">
	
	<div>
	<c:url var="planetImageUrl" value="/img/${calculator.planet}.jpg" />
	<img class="planet-smaller-img" src="${planetImageUrl}" alt="${calculator.planet}"/>
	
	<h2>If you are <c:out value="${calculator.age}" /> years old on planet Earth,
	then you are <c:out value="${calculator.alienAge}" /> <span class="title-case"><c:out value="${calculator.planet}" /></span> years old.</h2>
	</div>
	
</section>
	
	<%@include file="common/footer.jsp"  %>