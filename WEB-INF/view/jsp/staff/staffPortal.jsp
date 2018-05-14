<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../header.jsp"/>

	<div class="row">
		<h1 class="text-center portal-heading"><strong>Staff Portal</strong></h1>
		<div class="col-md-6">
			<div class="well">
				<h2 class="text-center">Knowledge Base</h2>
				<br/>
				<a class="btn btn-info btn-lg btn-block" href="#">View Knowledge Base Articles</a>
			</div>
		</div>
		<div class="col-md-6">
			<div class="well">
				<h2 class="text-center">Support Tickets</h2>
				<br/>
				<a class="btn btn-success btn-lg btn-block" href="#">View all support tickets<br/></a>
			</div>
		</div>
	</div>

<c:import url="../footer.jsp"/>