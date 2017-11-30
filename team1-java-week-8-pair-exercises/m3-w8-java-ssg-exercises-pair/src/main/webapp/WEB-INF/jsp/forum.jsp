<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="common/header.jsp"  %>

<section class="centeredPanel">
	<h1>Solar System Geek Forum</h1>
	
	<p> <c:url var="forumUrl" value="/forumInput" />
		<a href="${forumUrl}"> Post a Message</a>
	</p>
	
	<c:forEach items="${allPosts}" var="post">
		<div class="forum-container">
			<h3 class="forum-post-title"> <c:out value="${post.subject}" /> </h3>
			<c:out value="${post.username}" /> on <c:out value="${post.datePosted}"/>
			<p> <c:out value="${post.message}" /> </p> 
		</div>
	</c:forEach>
	
	</section>
	
	<%@include file="common/footer.jsp"  %>