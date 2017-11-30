<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="common/header.jsp"  %>

<section class="centeredPanel">

	<h2>Alien Weight Calculator</h2>
	
	<c:url var="alienWeightUrl" value="/alienWeightResult" />
	<form method="GET" action="${alienWeightUrl}">
		<div>
			<label for="planet">Choose a planet</label>
			<select name="planet" id="planet">
			<%-- <c:forEach items="${alienWeight.weightResult.planetWeightMultiplier}" var="planetName">
				<option value="${planetName.key}"><c:out value="${planetName.key}" /></option>
			</c:forEach> --%>
				<option value="mercury">Mercury</option>
				<option value="venus">Venus</option>
				<option value="mars">Mars</option>
				<option value="jupiter">Jupiter</option>
				<option value="saturn">Saturn</option>
				<option value="uranus">Uranus</option>
				<option value="neptune">Neptune</option>			
			</select>
		</div>
		<div>
			<label for="weight">Enter your Earth weight</label>
			<input type="number" name="userWeight" id="weight" placeholder="Weight in pounds" />
		</div>
		<div>
			<label></label>
			<input type="submit" value="Calculate Weight" />
		</div>
	</form>
</section>
<%@include file="common/footer.jsp"  %>