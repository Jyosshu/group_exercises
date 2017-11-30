<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@include file="common/header.jsp"  %>

<section class="centeredPanel">
	
	<div>
	
	<c:url var="planetImageUrl" value="/img/${alienWeight.planet}.jpg" />
	<img class="planet-smaller-img" src="${planetImageUrl}" alt="${alienWeight.planet}"/>	
	
	<h2>If you weigh <c:out value="${alienWeight.userWeight}" /> lbs on planet Earth,
	then you weigh <c:out value="${alienWeight.alienWeight}" /> <span class="title-case"><c:out value="${alienWeight.planet}" /></span> lbs.</h2>
	
	</div>
	
</section>
	
	<%@include file="common/footer.jsp"  %>