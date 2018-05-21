<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="date" uri="http://localhost:8080/c3180044_c3281849_c3237808_FinalProject/taglib/date" %>
<%@ page import="itserviceportal.model.beans.*" %>

<c:import url="/WEB-INF/view/includes/header.jsp"/>

<%-- Only display if there is article --%>
<c:if test="${not empty article}">

	<ul class="list-group my-2 mb-5">
		<li class="list-group-item text-dark py-3 bg-light">
			<h5 class="d-flex justify-content-between float-right">
				<%-- Display Category --%>
				<span class="mx-1 badge badge-secondary">
					<span class="mx-1 fas fa-tag"></span>
					<c:out value="${article.category.str}"/>
				</span>
			</h5>
			<div class="d-flex justify-content-between">
				<%-- Display Title --%>
				<h3 class="mb-1 text-left"><span class="mx-1 fas fa-book text-info"></span><c:out value="${article.title}"/></h3>
			</div>
			
			<div class="d-flex justify-content-between">
				<p class="mr-2 mb-0">
					Resolved
					<span class="fas fa-user-check"></span>
					<%-- Display User who resolved the ticket's name --%>
					<c:out value="${article.resolvedBy.firstName} ${article.resolvedBy.lastName}"/>
					<span class="mx-1 fas fa-calendar-alt hidden-sm"></span>
					<%-- Display date it was reported on using custom date format taglib --%>
					<date:format date="${article.resolvedOn}" />
				</p>
			</div>
		</li>

		<%-- Display category based issue details --%>
		<c:if test="${not empty article.issueDetails}">
			<li class="list-group-item">
				<c:forEach var="issueDetail" items="${article.issueDetails}">
					<h5 class="mb-1"><c:out value="${issueDetail.question}"/></h5>
					<p><c:out value="${issueDetail.response}"/></p>
				</c:forEach>
			</li>
		</c:if>

		<%-- Display description --%>
		<li class="list-group-item">
			<h5 class="mb-1">Description</h5>
			<p><c:out value="${supportTicket.description}"/></p>
		</li>

		<%-- Display resolution details --%>
		<li class="list-group-item">
			<h5 class="mb-1">Resolution Details</h5>
			<p><c:out value="${supportTicket.resolutionDetails}"/></p>
		</li>
	</ul>

</c:if>

<ul class="list-group my-2 mb-5">
	<li class="list-group-item py-3 text-dark bg-light">
		<h5 class="d-flex justify-content-between float-right">
			<span id="stateBadge" class="mx-1 badge badge-secondary" >
				<span class="mx-1 fas fa-tag"></span>Account
			</span>
		</h5>
		<div class="d-flex justify-content-between">
			<h3 class="mb-1 text-left"><span class="mx-1 fas fa-book text-info"></span>How do I change my University Password</h3>
		</div>
		
		<div class="d-flex justify-content-between">
			<p class="mr-2 mb-0">Resolved <span class="mx-1 fas fa-user-check"></span>Brice Purton<span class="mx-1 fas fa-calendar-alt"></span>11:48am 14/05/2018</p>
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
		<h5 class="mb-1">Resolution Details<span class="mx-1 fas fas fa-check text-success"></span></h5>
		<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
	</li>
</ul>

<c:import url="/WEB-INF/view/includes/footer.jsp"/>

<%
HttpServletResponse httpResponse = (HttpServletResponse)response;

httpResponse.setHeader("Cache-Control","no-cache, no-store, must-revalidate"); 
response.addHeader("Cache-Control", "post-check=0, pre-check=0");
httpResponse.setHeader("Pragma","no-cache"); 
httpResponse.setDateHeader ("Expires", 0); 
%>