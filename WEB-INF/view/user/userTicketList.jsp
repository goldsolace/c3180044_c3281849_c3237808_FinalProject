<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/view/header.jsp"/>

<form class="form-sort" method="POST" action="TicketList">
	<div class="input-group">
		<select name="categorySelect" class="custom-select">
			<option value="all">All Categories</option>
			<option value="software">Software</option>
			<option value="hardware">Hardware</option>
			<option value="network">Network</option>
			<option value="account">Account</option>
			<option value="email">Email</option>
		</select>
		<select name="stateSelect" class="custom-select">
			<option value="all">All States</option>
			<option value="new">New</option>
			<option value="inProgress">In Progress</option>
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


<ul class="list-group my-2">

	<a class="nounderline" href="Ticket?ticket=1">
		<li class="list-group-item list-group-item-action list-group-item-light text-dark">
			<div class="d-flex justify-content-between">
				<h5>Ticket 1</h5>
				<h5 class="d-flex justify-content-between">
					<span id="stateBadge" class="mx-1 badge bg-danger text-light" >
						<span class="mx-1 fas fa-tasks"></span>New
					</span>
					<span id="stateBadge" class="mx-1 badge badge-secondary" >
						<span class="mx-1 fas fa-tag"></span>Software
					</span>
				</h5>
			</div>
			<h3 class="mb-1 text-left">How do I change my University Password</h3>
			<div class="d-flex justify-content-between">
				<p class="mr-2"><span class="mx-1 fas fa-user"></span>Billy Jones<span class="mx-1 fas fa-calendar-alt"></span>5:13pm 13/05/2018</p>
				<p class="mr-2"><span class="mx-1 fas fa-user"></span>Brice Purton<span class="mx-1 fas fa-calendar-alt"></span>11:48am 14/05/2018</p>
			</div>
		</li>
	</a>

	<a class="nounderline" href="Ticket?ticket=2">
		<li class="list-group-item list-group-item-action list-group-item-light text-dark">
			<div class="d-flex justify-content-between">
				<h5>Ticket 2</h5>
				<h5 class="d-flex justify-content-between">
					<span id="stateBadge" class="mx-1 badge bg-success text-light" >
						<span class="mx-1 fas fa-tasks"></span>Resolved
					</span>
					<span id="stateBadge" class="mx-1 badge badge-secondary" >
						<span class="mx-1 fas fa-tag"></span>Software
					</span>
				</h5>
			</div>
			<h3 class="mb-1 text-left">How do I change my University Password</h3>
			<div class="d-flex justify-content-between">
				<p class="mr-2"><span class="mx-1 fas fa-user"></span>Billy Jones<span class="mx-1 fas fa-calendar-alt"></span>5:13pm 13/05/2018</p>
				<p class="mr-2"><span class="mx-1 fas fa-user"></span>Brice Purton<span class="mx-1 fas fa-calendar-alt"></span>11:48am 14/05/2018</p>
			</div>
		</li>
	</a>

	<a class="nounderline" href="Ticket?ticket=3">
		<li class="list-group-item list-group-item-action list-group-item-light text-dark">
			<div class="d-flex justify-content-between">
				<h5>Ticket 3</h5>
				<h5 class="d-flex justify-content-between">
					<span id="stateBadge" class="mx-1 badge bg-primary text-light" >
						<span class="mx-1 fas fa-tasks"></span>Completed
					</span>
					<span id="stateBadge" class="mx-1 badge badge-secondary" >
						<span class="mx-1 fas fa-tag"></span>Software
					</span>
				</h5>
			</div>
			<h3 class="mb-1 text-left">How do I change my University Password</h3>
			<div class="d-flex justify-content-between">
				<p class="mr-2"><span class="mx-1 fas fa-user"></span>Billy Jones<span class="mx-1 fas fa-calendar-alt"></span>5:13pm 13/05/2018</p>
				<p class="mr-2"><span class="mx-1 fas fa-user"></span>Brice Purton<span class="mx-1 fas fa-calendar-alt"></span>11:48am 14/05/2018</p>
			</div>
		</li>
	</a>

	<a class="nounderline" href="Ticket?ticket=4">
		<li class="list-group-item list-group-item-action list-group-item-light text-dark">
			<div class="d-flex justify-content-between">
				<h5>Ticket 4</h5>
				<h5 class="d-flex justify-content-between">
					<span id="stateBadge" class="mx-1 badge bg-progress text-light" >
						<span class="mx-1 fas fa-tasks"></span>In Progress
					</span>
					<span id="stateBadge" class="mx-1 badge badge-secondary" >
						<span class="mx-1 fas fa-tag"></span>Software
					</span>
				</h5>
			</div>
			<h3 class="mb-1 text-left">How do I change my University Password</h3>
			<div class="d-flex justify-content-between">
				<p class="mr-2"><span class="mx-1 fas fa-user"></span>Billy Jones<span class="mx-1 fas fa-calendar-alt"></span>5:13pm 13/05/2018</p>
				<p class="mr-2"><span class="mx-1 fas fa-user"></span>Brice Purton<span class="mx-1 fas fa-calendar-alt"></span>11:48am 14/05/2018</p>
			</div>
		</li>
	</a>

</ul>

<c:import url="/WEB-INF/view/footer.jsp"/>

<%
HttpServletResponse httpResponse = (HttpServletResponse)response;

httpResponse.setHeader("Cache-Control","no-cache, no-store, must-revalidate"); 
response.addHeader("Cache-Control", "post-check=0, pre-check=0");
httpResponse.setHeader("Pragma","no-cache"); 
httpResponse.setDateHeader ("Expires", 0); 
%>