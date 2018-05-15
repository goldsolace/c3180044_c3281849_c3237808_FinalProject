<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/view/header.jsp"/>

<c:import url="/WEB-INF/view/messages.jsp"/>

<div class="text-center">
	<h1 class="text-center"><strong>Report Issue</strong></h1>
</div>

<form class="form-report" method="POST" action="Report">
	<div class="form-group">
		<label for="title">Title</label>
		<input name="title" type="test" class="form-control" id="title" placeholder="Title">
	</div>

	<div class="form-group">
		<label for="inputCategory">Category</label>
		<select name="category" id="category" class="form-control">
			<option value="software">Software</option>
			<option value="hardware">Hardware</option>
			<option value="network">Network</option>
			<option value="account">Account</option>
			<option value="email">Email</option>
		</select>
	</div>

	<div class="form-group">
		<label for="details">Problem Details</label>
		<textarea name="details" class="form-control" id="details" rows="3" placeholder="Details..."></textarea>
	</div>

	<button class="btn btn-lg btn-danger btn-block" type="submit">Report</button>
</form>

<c:import url="/WEB-INF/view/footer.jsp"/>