<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/view/header.jsp"/>

<div class="px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
	<h1 class="text-center"><strong>Staff Portal</strong></h1>
</div>

<div class="row">
	<div class="col-md-4 offset-md-2">
		<div class="card-deck mb-3 text-center">
			<div class="card mb-4 box-shadow">
				<div class="card-header">
					<h4 class="my-0 font-weight-normal">Knowledge Base</h4>
				</div>
				<div class="card-body">
					<a href="KnowledgeBase" class="btn btn-lg btn-block btn-primary">View All Articles</a>
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
					<a href="Tickets" class="btn btn-lg btn-block btn-success">View All Tickets</a>
				</div>
			</div>
		</div>
	</div>
</div>

<c:import url="/WEB-INF/view/footer.jsp"/>