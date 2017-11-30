<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@include file="common/header.jsp"  %>
	<section class="centeredPanel">
		<c:url var="planetImageUrl" value="/img/${travel.planet}.jpg" />
		<img class="planet-smaller-img" src="${planetImageUrl}" alt="${travel.planet}"/>
	
		<p>
			Traveling by <c:out value="${travel.modeTrans }"/> you will reach 
			<c:out value="${travel.planet}"/> in <c:out value="${travel.yearsToPlanet}"/> years. 
			You will be <c:out value="${travel.yearsToPlanet + travel.age}"/> years old.
		</p>
	</section>
	
<%@include file="common/footer.jsp"  %>