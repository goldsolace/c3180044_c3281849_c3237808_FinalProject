<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix ="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="itserviceportal.model.Role" %>

<%-- Only display nav bar if a user logged in --%>
<c:if test="${not empty User}">

	<div id="navBar" class="navbar navbar-expand-lg navbar-light" role="navigation">
		<div class="container">

			<%-- Display user's name --%>
			<span class="navbar-brand"><strong>
				<a class="nounderline text-dark" href="ServicePortal">
					<c:out value="${fn:escapeXml(sessionScope.User.firstName)} ${fn:escapeXml(sessionScope.User.lastName)}"/></strong>
				</a>
			</span>

			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="navbar-collapse collapse justify-content-end">
				<ul class="navbar-nav float-right text-right">
					<li class="nav-item">
						<a class="nav-link" href="ServicePortal"><strong>Portal</strong></a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="KnowledgeBase"><strong>Knowledge Base</strong></a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="TicketList"><strong>Support Tickets</strong></a>
					</li>

					<%-- Only display non-staff user --%>
					<c:if test="${User.role == Role.USER}">
						<li class="nav-item">
							<a class="nav-link" href="Report"><strong>Report Issue</strong></a>
						</li>
					</c:if>

					<li class="nav-item">
						<a class="nav-link" href="Logout">Log Out</a>
					</li>
				</ul>
			</div>
		</div>	
	</div>

</c:if>

<div class="container pt-2">
	<jsp:include page="/WEB-INF/view/messages.jsp"/>
</div>