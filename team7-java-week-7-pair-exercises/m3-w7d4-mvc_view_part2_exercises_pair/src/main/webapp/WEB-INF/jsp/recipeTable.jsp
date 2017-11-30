<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="pageTitle" value="Recipe Table View"/>

<%@ include file="common/header.jspf" %>
    <section id="main-content">
    <!-- Use the request attribute "recipes" (List<Recipe>) -->
    
		<h2>Recipes</h2>
		<table id="recipeTable">
			<tr>
				<th>&nbsp;</th>
					<c:forEach items="${recipes}" var= "recipe">
						<td> <a href="recipeDetails?recipeId=${recipe.recipeId}" > <img src="img/recipe${recipe.recipeId}.jpg" class="profile"/> </a> </td>
					</c:forEach>
			</tr>	
			
			<tr class="shaded">
				<th>Name</th>
					<c:forEach items="${recipes}" var= "recipe">
						<td> <a href="recipeDetails?recipeId=${recipe.recipeId}" ><c:out value="${recipe.name}"/> </a></td>
					</c:forEach>
			</tr>	
			
			<tr>	
				<th>Type</th>
					<c:forEach items="${recipes}" var= "recipe">
						<td> <c:out value="${recipe.recipeType}"/> </td>
					</c:forEach>
			</tr>			
			
			<tr class="shaded">	
				<th>Cook Time</th>
					<c:forEach items="${recipes}" var= "recipe">
						<td> <c:out value="${recipe.cookTimeInMinutes}"/> min</td>
					</c:forEach>
			</tr>		
				
			<tr>	
				<th> Ingredients </th>
					<c:forEach items="${recipes}" var= "recipe">
						<td> <c:out value="${recipe.ingredients.size()}"/> ingredients</td>
					</c:forEach>
			</tr>	
			
			<tr class="shaded">	
				<th>Rating</th>
					<c:forEach items="${recipes}" var= "recipe">
						<td>
							<fmt:formatNumber maxFractionDigits="0" value="${recipe.averageRating}" var="formattedRating"/>
						
							<img src="img/${formattedRating}-star.png" class="rating"/>
							
						</td>
					</c:forEach>
			</tr>	
		</table>
		 
    </section>
</body>
</html>
