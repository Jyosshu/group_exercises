<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="common/header.jsp"  %>

<section class="centeredPanel">
	<h2>Alien Travel Calculator</h2>
	
	<c:url var="alienTravelUrl" value="/alienTravelResult" />
	<form method="GET" action="${alienTravelUrl}">
	
		<div>
			<label for="planet">Choose a planet</label>
			<select name="planet" id="planet">
				<option value="Mercury">Mercury</option>
				<option value="Venus">Venus</option>
				<option value="Mars">Mars</option>
				<option value="Jupiter">Jupiter</option>
				<option value="Saturn">Saturn</option>
				<option value="Uranus">Uranus</option>
				<option value="Neptune">Neptune</option>
			</select>
		</div>
		<div>
			<label for="modeTrans">Choose a mode transportation</label>
			<select name="modeTrans" id="modeTrans">
				<option value="Walking">Walking</option>
				<option value="Car">Car</option>
				<option value="Bullet Train">Bullet Train</option>
				<option value="Boeing 747">Boeing 747</option>
				<option value="Concorde">Concorde</option>
			</select>
		</div>
		<div>
			<label for="age">Enter your Earth age</label>
			<input type="number" name="age" id="age" placeholder="Between 1 and 100" />
		</div>
		<div>
			
			<input type="submit" value="Calculate Age"/>
		</div>
	
	</form>
	</section>
	
	
	<%@include file="common/footer.jsp"  %>