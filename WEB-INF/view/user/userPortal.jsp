<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/view/header.jsp"/>

<div class="px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
	<h1 class="text-center"><strong>User Portal</strong></h1>
</div>

<div class="row">
	<div class="col-md-4">
		<div class="card-deck mb-3 text-center">
			<div class="card mb-4 box-shadow">
				<div class="card-header">
					<h4 class="my-0 font-weight-normal">Knowledge Base</h4>
				</div>
				<div class="card-body">
					<a href="KnowledgeBase" class="btn btn-lg btn-block btn-primary">Search our knowledge base<br/> and find answers</a>
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-4">
		<div class="card-deck mb-3 text-center">
			<div class="card mb-4 box-shadow">
				<div class="card-header">
					<h4 class="my-0 font-weight-normal">Support Tickets</h4>
				</div>
				<div class="card-body">
					<a href="TicketList" class="btn btn-lg btn-block btn-success">View your support tickets</a>
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-4">
		<div class="card-deck mb-3 text-center">
			<div class="card mb-4 box-shadow">
				<div class="card-header">
					<h4 class="my-0 font-weight-normal">Report Issue</h4>
				</div>
				<div class="card-body">
					<a href="Report" class="btn btn-lg btn-block btn-danger">Experiencing a problem?<br/> Report it here</a>
				</div>
			</div>
		</div>
	</div>
</div>

<c:import url="/WEB-INF/view/footer.jsp"/>

<%
HttpServletResponse httpResponse = (HttpServletResponse)response;

httpResponse.setHeader("Cache-Control","no-cache, no-store, must-revalidate"); 
response.addHeader("Cache-Control", "post-check=0, pre-check=0");
httpResponse.setHeader("Pragma","no-cache"); 
httpResponse.setDateHeader ("Expires", 0); 
// if (session.getAttribute("User") == null ) {
// 	response.sendRedirect("/");
// 	return;
// }
%>