<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <c:set var="pageTitle" value="Recipe - detail"/>

<%@ include file="common/header.jspf" %>

 <section id="main-content">
 
 	<div class="detail-main-container">
 		<div class="detail-image">
 			<c:url var="detailRecipeImage" value="/img/recipe${recipe.recipeId}.jpg" />
 			<img src="${detailRecipeImage}" id="foodImage"/>
 		</div>
 		<div class="detailSummary">
 			<h3><c:out value="${recipe.name}"/></h3>
 				<p> <c:out value="${recipe.recipeType}"/> </p>
 				<p> <strong>Cook Time </strong> <c:out value="${recipe.cookTimeInMinutes}"/> minutes </p>
 				<p>  <c:out value="${recipe.description}" /> </p>	
 		</div>
 	</div>
 		<div class="detailIngredients">
 			<p> <strong>Ingredients</strong> </p>
 			
 			<c:forEach items="${recipe.ingredients}" var="ingredient">
 				<ul>
 					<li> <c:out value="${ingredient.quantity}"/>
 					  <c:out value="${ingredient.name}"/>
 					 </li>
 				</ul>
 			</c:forEach>
 		</div>
 		<div class="detail-preparation">
 			<p> <strong> Preparation</strong> </p>
	 			<ol>
 					<c:forEach items="${recipe.preparationSteps}" var="prep">
 						<li> <c:out value="${prep}"/> </li>
 					</c:forEach>
 				</ol>
 		</div>
 	
 
 </section>
</body>
</html>
