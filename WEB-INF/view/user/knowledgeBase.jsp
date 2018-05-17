<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/view/header.jsp"/>

<div class="my-2">
	<h1 class="text-center"><strong>Knowledge Base</strong></h1>
</div>

<form class="form-sort" method="POST" action="KnowledgeBase">
	<div class="input-group">
		<select name="categorySelect" class="custom-select">
			<option value="all">All Categories</option>
			<option value="software">Software</option>
			<option value="hardware">Hardware</option>
			<option value="network">Network</option>
			<option value="account">Account</option>
			<option value="email">Email</option>
		</select>
		<select name="orderSelect" class="custom-select">
			<option value="newest">Newest Resolved</option>
			<option value="oldest">Oldest Resolved</option>
		</select>
		<div class="input-group-append">
			<button class="btn btn-outline-info" type="submit" >Sort</button>
		</div>
	</div>
</form>


<ul class="list-group my-2 mb-5">

	<a class="nounderline" href="Article?article=1">
		<li class="list-group-item list-group-item-action list-group-item-light text-dark">
			<div class="d-flex justify-content-between">
				<h3 class="mb-1 text-left">How do I change my University Password</h3>
				<h5 class="d-flex justify-content-between">
					<span id="stateBadge" class="mx-1 py-2 badge badge-secondary" >
						<span class="mx-1 fas fa-tag"></span>Software
					</span>
				</h5>
			</div>
			
			<div class="d-flex justify-content-between">
				<p class="mr-2">Resolved: <span class="mx-1 fas fa-user"></span>Brice Purton<span class="mx-1 fas fa-calendar-alt"></span>11:48am 14/05/2018</p>
			</div>
		</li>
	</a>

	<a class="nounderline" href="Article?article=1">
		<li class="list-group-item list-group-item-action list-group-item-light text-dark">
			<div class="d-flex justify-content-between">
				<h5>Article 1</h5>
				<h5 class="d-flex justify-content-between">
					<span id="stateBadge" class="mx-1 badge badge-secondary" >
						<span class="mx-1 fas fa-tag"></span>Software
					</span>
				</h5>
			</div>
			<h3 class="mb-1 text-left">How do I change my University Password</h3>
			<div class="d-flex justify-content-between">
				<p class="mr-2">Resolved: <span class="mx-1 fas fa-user"></span>Brice Purton<span class="mx-1 fas fa-calendar-alt"></span>11:48am 14/05/2018</p>
			</div>
		</li>
	</a>

	<a class="nounderline" href="Article?article=1">
		<li class="list-group-item list-group-item-action list-group-item-light text-dark">
			<div class="d-flex justify-content-between">
				<h5>Article 1</h5>
				<h5 class="d-flex justify-content-between">
					<span id="stateBadge" class="mx-1 badge badge-secondary" >
						<span class="mx-1 fas fa-tag"></span>Software
					</span>
				</h5>
			</div>
			<h3 class="mb-1 text-left">How do I change my University Password</h3>
			<div class="d-flex justify-content-between">
				<p class="mr-2">Resolved: <span class="mx-1 fas fa-user"></span>Brice Purton<span class="mx-1 fas fa-calendar-alt"></span>11:48am 14/05/2018</p>
			</div>
		</li>
	</a>

	<a class="nounderline" href="Article?article=1">
		<li class="list-group-item list-group-item-action list-group-item-light text-dark">
			<div class="d-flex justify-content-between">
				<h5>Article 1</h5>
				<h5 class="d-flex justify-content-between">
					<span id="stateBadge" class="mx-1 badge badge-secondary" >
						<span class="mx-1 fas fa-tag"></span>Software
					</span>
				</h5>
			</div>
			<h3 class="mb-1 text-left">How do I change my University Password</h3>
			<div class="d-flex justify-content-between">
				<p class="mr-2">Resolved: <span class="mx-1 fas fa-user"></span>Brice Purton<span class="mx-1 fas fa-calendar-alt"></span>11:48am 14/05/2018</p>
			</div>
		</li>
	</a>

	<a class="nounderline" href="Article?article=1">
		<li class="list-group-item list-group-item-action list-group-item-light text-dark">
			<div class="d-flex justify-content-between">
				<h5>Article 1</h5>
				<h5 class="d-flex justify-content-between">
					<span id="stateBadge" class="mx-1 badge badge-secondary" >
						<span class="mx-1 fas fa-tag"></span>Software
					</span>
				</h5>
			</div>
			<h3 class="mb-1 text-left">How do I change my University Password</h3>
			<div class="d-flex justify-content-between">
				<p class="mr-2">Resolved: <span class="mx-1 fas fa-user"></span>Brice Purton<span class="mx-1 fas fa-calendar-alt"></span>11:48am 14/05/2018</p>
			</div>
		</li>
	</a>

	<a class="nounderline" href="Article?article=1">
		<li class="list-group-item list-group-item-action list-group-item-light text-dark">
			<div class="d-flex justify-content-between">
				<h5>Article 1</h5>
				<h5 class="d-flex justify-content-between">
					<span id="stateBadge" class="mx-1 badge badge-secondary" >
						<span class="mx-1 fas fa-tag"></span>Software
					</span>
				</h5>
			</div>
			<h3 class="mb-1 text-left">How do I change my University Password</h3>
			<div class="d-flex justify-content-between">
				<p class="mr-2">Resolved: <span class="mx-1 fas fa-user"></span>Brice Purton<span class="mx-1 fas fa-calendar-alt"></span>11:48am 14/05/2018</p>
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