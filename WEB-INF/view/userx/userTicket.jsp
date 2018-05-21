<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="date" uri="http://localhost:8080/c3180044_c3281849_c3237808_FinalProject/taglib/date" %>
<%@ page import="itserviceportal.model.beans.*" %>

<c:import url="/WEB-INF/view/includes/header.jsp"/>

<%-- Only display if there is ticket --%>
<c:if test="${not empty supportTicket}">

	<ul class="list-group my-2">
		<li class="list-group-item text-dark py-3">
			<div class="d-flex justify-content-between">
				<%-- Display TicketID --%>
				<h5>Ticket <c:out value="${supportTicket.ticketID}"/></h5>
				<h5 class="d-flex justify-content-between">

					<%-- Add class based on ticket state to add different colours --%>
					<c:choose>
						<c:when test="${supportTicket.state == State.NEW}">
							<c:set var="stateClass" value="badge-danger"/>
						</c:when>
						<c:when test="${supportTicket.state == State.INPROGRESS}">
							<c:set var="stateClass" value="badge-progress"/>
						</c:when>
						<c:when test="${supportTicket.state == State.COMPLETED}">
							<c:set var="stateClass" value="badge-primary"/>
						</c:when>
						<c:when test="${supportTicket.state == State.RESOLVED}">
							<c:set var="stateClass" value="badge-success"/>
						</c:when>
					</c:choose>

					<%-- Display State --%>
					<span class="state mx-1 badge ${stateClass}">
						<span class="mx-1 fas fa-tasks"></span>
						<c:out value="${supportTicket.state.str}"/>
					</span>
					<%-- Display Category --%>
					<span class="mx-1 badge badge-secondary">
						<span class="mx-1 fas fa-tag"></span>
						<c:out value="${supportTicket.category.str}"/>
					</span>
				</h5>
			</div>

			<%-- Display Title --%>
			<h3 class="mb-1 text-left"><c:out value="${supportTicket.title}"/></h3>
			<div class="d-flex justify-content-between">
				<p class="mr-2 mb-0">
					Reported
					<span class=" fas fa-user"></span>
					<%-- Display User who reported the ticket's name --%>
					<c:out value="${supportTicket.reportedBy.firstName} ${supportTicket.reportedBy.lastName}"/>
					<span class="mx-1 fas fa-calendar-alt hidden-sm"></span>
					<%-- Display date it was reported on using custom date format taglib --%>
					<date:format date="${supportTicket.reportedOn}" />
				</p>
				<c:if test="${not empty supportTicket.resolvedOn}">
					<p class="mr-2 mb-0">
						Resolved
						<span class=" fas fa-user"></span>
						<%-- Display User who resolved the ticket's name --%>
						<c:out value="${supportTicket.resolvedBy.firstName} ${supportTicket.resolvedBy.lastName}"/>
						<span class="mx-1 fas fa-calendar-alt hidden-sm"></span>
						<%-- Display date it was reported on using custom date format taglib --%>
						<date:format date="${supportTicket.resolvedOn}" />
					</p>
				</c:if>
			</div>
		</li>
		<c:if test="${not empty supportTicket.issueDetails}">
			<li class="list-group-item">
				<%-- Iterate through issueDetails list --%>
				<c:forEach var="issueDetail" items="${supportTicket.issueDetails}">
					<h5 class="mb-1"><c:out value="${issueDetail.question}"/></h5>
					<p><c:out value="${issueDetail.response}"/></p>
				</c:forEach>
			</li>
		</c:if>
		<li class="list-group-item">
			<h5 class="mb-1">Description</h5>
			<%-- Display description --%>
			<p><c:out value="${supportTicket.description}"/></p>
		</li>
		<form class="">
			<c:if test="${not empty supportTicket.resolutionDetails}">
				<li class="list-group-item">
					<h5 class="mb-1">Resolution Details</h5>
					<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
				</li>
			</c:if>
			<c:if test="${empty supportTicket.resolutionDetails}">
				<li class="list-group-item">
					<div class="form-group">
						<label class="h3" for="solution"><span class="mx-1 far fa-edit"></span>Solution</label>
						<textarea name="solution" type="text" class="form-control" id="soltuion" rows="3" placeholder="Solution..."></textarea>
					</div>
				</li>
			</c:if>
			<li class="list-group-item text-dark py-3">
				<div class="text-center">
					<h2 class="mb-1">Actions</h2>

					<%-- Decide which actions should be available to user --%>

					<%-- For user --%>
					<c:if test="${supportTicket.state == State.NEW || supportTicket.state == State.INPROGRESS}">
						<button name="setCompleted" class="btn btn-lg btn-primary m-1" type="submit">Set Completed</button>
					</c:if>

					<c:if test="${supportTicket.state == State.COMPLETED}">
						<button name="acceptSolution" class="btn btn-lg btn-success m-1" type="submit">Accept Solution</button>
					</c:if>

					<c:if test="${supportTicket.state == State.COMPLETED}">
						<button name="rejectSolution" class="btn btn-lg btn-danger m-1" type="submit">Reject Solution</button>
					</c:if>

					<%-- For staff --%>
					<c:if test="${supportTicket.state == State.NEW}">
						<button name="startWork" class="btn btn-lg btn-progress m-1" type="submit">Start Work</button>
					</c:if>

					<c:if test="${supportTicket.state == State.INPROGRESS}">
						<button name="submitSolution" class="btn btn-lg btn-primary m-1" type="submit">Submit Solution</button>
					</c:if>

					<c:if test="${supportTicket.state == State.COMPLETED || supportTicket.state == State.RESOLVED}">
						<button name="addKnowledge" class="btn btn-lg btn-success m-1" type="submit">Add Knowledge</button>
					</c:if>

					<c:if test="${supportTicket.knowledgeBase}">
						<button name="removeKnowledge" class="btn btn-lg btn-danger m-1" type="submit">Remove Knowledge</button>
					</c:if>
				</div>
			</li>
		</form>
	</ul>

	<ul class="list-group my-2 mb-5">
		<h3 class="text-center m-3">Discussion</span></h3>

		<%-- Iterate through comments list --%>
		<c:forEach var="comment" items="${supportTicket.comments}">
			<%@ include file="/WEB-INF/view/includes/comment.jsp" %>
		</c:forEach>

		<%-- Allow commenting unless resolved --%>
		<c:if test="${supportTicket.state != State.RESOLVED}">
			<li class="list-group-item">
				<form class="my-2 my-lg-0">
					<div class="form-group">
						<label class="h5" for="solution">Comment<span class="mx-1 far fa-comment"></span></label>
						<textarea name="commentText" class="form-control" id="exampleFormControlTextarea1" rows="3" placeholder="Comment text..."></textarea>
					</div>

					<button class="btn btn-success my-2 my-sm-0 m-2 float-right" type="submit">Post</button>
				</form>
			</li>
		</c:if>
	</ul>

</c:if>

<c:import url="/WEB-INF/view/includes/footer.jsp"/>

<%-- Prevent caching to prevent issue like a user logging out then pressing back
	 still being able to see a restricted page --%>

<%
HttpServletResponse httpResponse = (HttpServletResponse)response;
httpResponse.setHeader("Cache-Control","no-cache, no-store, must-revalidate"); 
response.addHeader("Cache-Control", "post-check=0, pre-check=0");
httpResponse.setHeader("Pragma","no-cache"); 
httpResponse.setDateHeader ("Expires", 0); 
%>