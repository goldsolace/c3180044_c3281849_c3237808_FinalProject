<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="date" uri="http://localhost:8080/c3180044_c3281849_c3237808_FinalProject/taglib/date" %>
<%@ page import="itserviceportal.model.*" %>

<c:import url="/WEB-INF/view/header.jsp"/>

<div class="my-2">
	<h1 class="text-center"><strong>Support Tickets</strong></h1>
</div>

<%-- Only display if their are tickets --%>
<c:if test="${not empty tickets}">

	<form class="form-sort" method="POST" action="TicketList">
		<div class="input-group">
			<select name="categorySelect" class="custom-select">
				<option value="all">All Categories</option>
				<option value="network">Network</option>
				<option value="software">Software</option>
				<option value="hardware">Hardware</option>
				<option value="account">Account</option>
				<option value="email">Email</option>
			</select>
			<select name="stateSelect" class="custom-select">
				<option value="all">All States</option>
				<option value="new">New</option>
				<option value="in progress">In Progress</option>
				<option value="completed">Completed</option>
				<option value="resolved">Resolved</option>
			</select>
			<select name="orderSelect" class="custom-select">
				<option value="newest">Newest Reported</option>
				<option value="oldest">Oldest Reported</option>
			</select>
			<div class="input-group-append">
				<button class="btn btn-outline-info" type="submit" >Sort</button>
			</div>
		</div>
	</form>


	<ul class="list-group my-2 mb-5">

		<%-- Iterate through tickets list --%>
		<c:forEach var="ticket" items="${tickets}" varStatus="ticketIndex">
		
			<%-- Link to Ticket Controller passing ticketID as a parameter --%>
			<a class="nounderline" href="Ticket?ticket=${ticket.ticketID}">
				<li class="list-group-item list-group-item-action text-dark py-3">
					<div class="d-flex justify-content-between">
						<%-- Display TicketID --%>
						<h5>Ticket <c:out value="${ticket.ticketID}"/></h5>
						<h5 class="d-flex justify-content-between">

							<%-- Add class based on ticket state to add different colours --%>
							<c:set var="state" value="${ticket.state}"/>
							<c:choose>
								<c:when test="${ticket.state == State.NEW}">
									<c:set var="stateClass" value="badge-danger"/>
								</c:when>
								<c:when test="${ticket.state == State.INPROGRESS}">
									<c:set var="stateClass" value="badge-progress"/>
								</c:when>
								<c:when test="${ticket.state == State.COMPLETED}">
									<c:set var="stateClass" value="badge-primary"/>
								</c:when>
								<c:when test="${ticket.state == State.RESOLVED}">
									<c:set var="stateClass" value="badge-success"/>
								</c:when>
							</c:choose>
							<span class="state mx-1 badge ${stateClass}">
								<span class="mx-1 fas fa-tasks"></span>
								<c:out value="${ticket.state.str}"/>
							</span>
							<span class="mx-1 badge badge-secondary">
								<span class="mx-1 fas fa-tag"></span>
								<c:out value="${ticket.category.str}"/>
							</span>
						</h5>
					</div>
					<h3 class="mb-1 text-left"><c:out value="${ticket.title}"/></h3>
					<div class="d-flex justify-content-between">
						<p class="mr-2 mb-0">
							Reported
							<span class=" fas fa-user"></span>
							<c:out value="${ticket.reportedBy.firstName} ${ticket.reportedBy.lastName}"/>
							<span class="mx-1 fas fa-calendar-alt hidden-sm"></span>
							<date:format date="${ticket.reportedOn}" />
						</p>
						<c:if test="${not empty ticket.resolvedOn}">
							<p class="mr-2 mb-0">
								Resolved
								<span class=" fas fa-user"></span>
								<c:out value="${ticket.resolvedBy.firstName} ${ticket.resolvedBy.lastName}"/>
								<span class="mx-1 fas fa-calendar-alt hidden-sm"></span>
								<date:format date="${ticket.resolvedOn}" />
							</p>
						</c:if>
					</div>
				</li>
			</a>
		</c:forEach>
	</ul>
</c:if>


<c:import url="/WEB-INF/view/footer.jsp"/>

<%
HttpServletResponse httpResponse = (HttpServletResponse)response;

httpResponse.setHeader("Cache-Control","no-cache, no-store, must-revalidate"); 
response.addHeader("Cache-Control", "post-check=0, pre-check=0");
httpResponse.setHeader("Pragma","no-cache"); 
httpResponse.setDateHeader ("Expires", 0); 
%>