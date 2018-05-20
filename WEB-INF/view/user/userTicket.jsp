<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/view/header.jsp"/>

<%-- Only display if there is ticket --%>
<c:if test="${not empty ticket}">

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

							<%-- Display State --%>
							<span class="state mx-1 badge ${stateClass}">
								<span class="mx-1 fas fa-tasks"></span>
								<c:out value="${ticket.state.str}"/>
							</span>
							<%-- Display Category --%>
							<span class="mx-1 badge badge-secondary">
								<span class="mx-1 fas fa-tag"></span>
								<c:out value="${ticket.category.str}"/>
							</span>
						</h5>
					</div>
					<%-- Display Title --%>
					<h3 class="mb-1 text-left"><c:out value="${ticket.title}"/></h3>
					<div class="d-flex justify-content-between">
						<p class="mr-2 mb-0">
							Reported
							<span class=" fas fa-user"></span>
							<%-- Display User who reported the ticket's name --%>
							<c:out value="${ticket.reportedBy.firstName} ${ticket.reportedBy.lastName}"/>
							<span class="mx-1 fas fa-calendar-alt hidden-sm"></span>
							<%-- Display date it was reported on using custom date format taglib --%>
							<date:format date="${ticket.reportedOn}" />
						</p>
						<c:if test="${not empty ticket.resolvedOn}">
							<p class="mr-2 mb-0">
								Resolved
								<span class=" fas fa-user"></span>
								<%-- Display User who resolved the ticket's name --%>
								<c:out value="${ticket.resolvedBy.firstName} ${ticket.resolvedBy.lastName}"/>
								<span class="mx-1 fas fa-calendar-alt hidden-sm"></span>
								<%-- Display date it was reported on using custom date format taglib --%>
								<date:format date="${ticket.resolvedOn}" />
							</p>
						</c:if>
					</div>
				</li>
			</a>
		</c:forEach>
	</ul>
</c:if>

<ul class="list-group my-2">
	<li class="list-group-item text-dark py-3">
		<div class="d-flex justify-content-between">
			<h5>Ticket 1</h5>
			<h5 class="d-flex justify-content-between">
				<span class="state mx-1 badge">
					<span class="mx-1 fas fa-tasks"></span>New
				</span>
				<span class="mx-1 badge badge-secondary" >
					<span class="mx-1 fas fa-tag"></span>Software
				</span>
			</h5>
		</div>
		<h3 class="mb-1 text-left">How do I change my University Password</h3>
		<div class="d-flex justify-content-between">
			<p class="mr-2">Reported: <span class="mx-1 fas fa-user"></span>Billy Jones<span class="mx-1 fas fa-calendar-alt"></span>5:13pm 13/05/2018</p>
			<p class="mr-2">Resolved: <span class="mx-1 fas fa-user"></span>Brice Purton<span class="mx-1 fas fa-calendar-alt"></span>11:48am 14/05/2018</p>
		</div>
	</li>
	<li class="list-group-item">
		<h5 class="mb-1">Question 1</h5>
		<p>Answer1</p>
		<h5 class="mb-1">Question 2</h5>
		<p>Answer2</p>
		<h5 class="mb-1">Question 3</h5>
		<p>Answer3</p>
		<h5 class="mb-1">Question 4</h5>
		<p>Answer4</p>
	</li>
	<li class="list-group-item">
		<h5 class="mb-1">Description</h5>
		<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
	</li>
	<form class="">
		<li class="list-group-item">
			<h5 class="mb-1">Resolution Details</h5>
			<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
		</li>
		<li class="list-group-item">
			<div class="form-group">
				<label class="h3" for="solution"><span class="mx-1 far fa-edit"></span>Solution</label>
				<textarea name="solution" type="text" class="form-control" id="soltuion" rows="3" placeholder="Solution..."></textarea>
			</div>
		</li>
		<li class="list-group-item text-dark py-3">
			<div class="text-center">
				<h2 class="mb-1">Actions</h2>
				<button class="btn btn-lg btn-progress m-1" type="submit">Start Work</button>
				<button class="btn btn-lg btn-primary m-1" type="submit">Set Completed</button>
				<button class="btn btn-lg btn-primary m-1" type="submit">Submit Solution</button>
				<button class="btn btn-lg btn-success m-1" type="submit">Accept Solution</button>
				<button class="btn btn-lg btn-danger m-1" type="submit">Reject Solution</button>
				<button class="btn btn-lg btn-success m-1" type="submit">Add Knowledge</button>
				<button class="btn btn-lg btn-danger m-1" type="submit">Remove Knowledge</button>
			</div>
		</li>
	</form>
</ul>

<ul class="list-group my-2 mb-5">
	<h3 class="text-center m-3">Discussion</span></h3>

	<li class="list-group-item">
		<div class="d-flex justify-content-between">
			<h6>Brice Purton</h6>
			<small>9:37am 14/05/2018</small>
		</div>
		<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
		<div class="list-group-item ml-5">
			<div class="d-flex justify-content-between">
				<h6><strong class="mr-1 text-primary">IT STAFF</strong>Jono Williams</h6>
				<small>9:42am 14/05/2018</small>
			</div>
			<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
		</div>
	</li>

	<li class="list-group-item flex-column align-items-start">
		<div class="d-flex justify-content-between">
			<h6><strong class="mr-1 text-primary">IT STAFF</strong>Jono Williams</h6>
			<small>9:42am 14/05/2018</small>
		</div>
		<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
	</li>

	<li class="list-group-item flex-column align-items-start">
		<div class="d-flex mb-1 justify-content-between">
			<h6>Wajdi Younes</h6>
			<small>12:01pm 14/05/2018</small>
		</div>
		<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
		<div class="list-group-item ml-5">
			<div class="d-flex mb-1 justify-content-between">
				<h6><strong class="mr-1 text-primary">IT STAFF</strong>Jono Williams</h6>
				<small>9:42am 14/05/2018</small>
			</div>
			<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
		</div>
		<div class="list-group-item ml-5">
			<div class="d-flex mb-1 justify-content-between">
				<h6>Wajdi Younes</h6>
				<small>9:42am 14/05/2018</small>
			</div>
			<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
			<div class="list-group-item ml-5">
				<div class="d-flex mb-1 justify-content-between">
					<h6><strong class="mr-1 text-primary">IT STAFF</strong>Jono Williams</h6>
					<small>9:42am 14/05/2018</small>
				</div>
				<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
			</div>
		</div>
	</li>

	<li class="list-group-item">
		<form class="my-2 my-lg-0">
			<div class="form-group">
				<label class="h5" for="solution">Reply<span class="mx-1 far fa-comment"></span></label>
				<textarea class="form-control" id="exampleFormControlTextarea1" rows="3" placeholder="Comment text..."></textarea>
			</div>

			<button class="btn btn-success my-2 my-sm-0 m-2 float-right" type="submit">Post</button>
		</form>
	</li>
</ul>


<c:import url="/WEB-INF/view/footer.jsp"/>

<%
HttpServletResponse httpResponse = (HttpServletResponse)response;

httpResponse.setHeader("Cache-Control","no-cache, no-store, must-revalidate"); 
response.addHeader("Cache-Control", "post-check=0, pre-check=0");
httpResponse.setHeader("Pragma","no-cache"); 
httpResponse.setDateHeader ("Expires", 0); 
%>