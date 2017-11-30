<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <c:set var="pageTitle" value="Recipe List View"/>
    
    <%@ include file="common/header.jspf" %>
    
    <section id="main-content">
    
    <div class="container">
    	<c:forEach items="${recipes}" var="recipe">
    		<div class="recipe-container">
    			<table class="tile">
    				<tr>
    					<td>
    						<a href="recipeDetails?recipeId=${recipe.recipeId}" ><img src="img/recipe${recipe.recipeId}.jpg" class="tile-profile"/></a>
    					</td>
    				</tr>
    				<tr>
    					<td class="tile-name">
    						<a href="recipeDetails?recipeId=${recipe.recipeId}" ><c:out value="${recipe.name}"/></a>
    					</td>
    				</tr>
    				<tr class="tile-shaded">
    					<td>
    						<div class="test-div">
    						<div class="tile-rating">
    							<fmt:formatNumber maxFractionDigits="0" value="${recipe.averageRating}" var="formattedRating"/>
								<img src="img/${formattedRating}-star.png" class="tile-rating"/>
							</div>
							<div class="tile-ingredients">
								<c:out value="${recipe.ingredients.size()}"/> ingredients
							</div>
							</div>
    					</td>
    				</tr>			
    			</table>
    		</div>
		</c:forEach>
    </div>

       <!-- Use the request attribute "recipes" (List<Recipe>) -->

    </section>
</body>
</html>