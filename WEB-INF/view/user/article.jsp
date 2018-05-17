<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/view/header.jsp"/>

<ul class="list-group my-2 mb-5">
	<li class="list-group-item text-dark py-3">
		<h5 class="d-flex justify-content-between float-right">
			<span id="stateBadge" class="mx-1 badge badge-secondary" >
				<span class="mx-1 fas fa-tag"></span>Account
			</span>
		</h5>
		<div class="d-flex justify-content-between">
			<h3 class="mb-1 text-left">How do I change my University Password</h3>
		</div>
		
		<div class="d-flex justify-content-between">
			<p class="mr-2 mb-0">Resolved: <span class="mx-1 fas fa-user"></span>Brice Purton<span class="mx-1 fas fa-calendar-alt"></span>11:48am 14/05/2018</p>
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
	<li class="list-group-item">
		<h5 class="mb-1">Resolution Details</h5>
		<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
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