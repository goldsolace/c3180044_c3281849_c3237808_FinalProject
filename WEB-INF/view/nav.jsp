<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- if user --%>

<div class="navbar navbar-expand-lg navbar-light" role="navigation">
	<div class="container">
		<span class="navbar-brand"><strong><c:out value="${sessionScope.User.firstName} ${sessionScope.User.lastName}"/></strong></span>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target=".navbar-collapse">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="navbar-collapse collapse justify-content-end">
			<ul class="navbar-nav">
				<li class="nav-item">
					<a class="nav-link" href="#"><strong>Knowledge Base</strong></a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#"><strong>Support Tickets</strong></a>
				</li>

				<%-- if not admin --%>

				<li class="nav-item">
					<a class="nav-link" href="#"><strong>Report Issue</strong></a>
				</li>
				
				<li class="nav-item">
					<a class="nav-link" href="./Logout">Log Out</a>
				</li>
			</ul>
		</div>
	</div>	
</div>