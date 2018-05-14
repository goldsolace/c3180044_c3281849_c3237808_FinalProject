<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/view/header.jsp"/>

<c:import url="/WEB-INF/view/messages.jsp"/>

<div class="row">
	<h1 class="text-center portal-heading"><strong>User Portal</strong></h1>
	<div class="col-md-4">
		<div class="well">
			<h2 class="text-center">Knowledge Base</h2>
			<br/>
			<a class="btn btn-info btn-lg btn-block" href="#">Search our knowledge base<br/> and find answers</a>
		</div>
	</div>
	<div class="col-md-4">
		<div class="well">
			<h2 class="text-center">Support Tickets</h2>
			<br/>
			<a class="btn btn-success btn-lg btn-block" href="#">View your support tickets<br/></a>
		</div>
	</div>
	<div class="col-md-4">
		<div class="well">
			<h2 class="text-center">Report Issue</h2>
			<br/>
			<a class="btn btn-danger btn-lg btn-block" href="#">Experiencing a problem?<br/> Report it here</a>
		</div>
	</div>
</div>

<c:import url="/WEB-INF/view/footer.jsp"/>