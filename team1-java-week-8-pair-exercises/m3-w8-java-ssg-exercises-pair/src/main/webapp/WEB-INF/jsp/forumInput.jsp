<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="common/header.jsp"  %>

<section class="centeredPanel">
	<h1>New Geek Post</h1>
	
	<c:url var="forumInputUrl" value="/forumInput" />
	<form method="POST" action="${forumInputUrl}">
		<div>
			<label for="username">Username</label> <input type="text"
				name="username" id="username" placeholder="Username" />
		</div>
		<div>
			<label for="subject">Subject</label> <input type="text"
				name="subject" id="subject" placeholder="Subject" />
		</div>
		<div>
			<label for="message">Message</label> <input type="text"
				name="message" id="message" placeholder="Message" />
		</div>
		<div>
			<label></label> <input type="submit" value="Submit!" />

		</div>
	</form>
</section>
<%@include file="common/footer.jsp"  %>