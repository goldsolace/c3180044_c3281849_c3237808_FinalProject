<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/view/includes/header.jsp"/>

<div class="form-container">
	<form class="form-login" method="POST" action="Login">
		<label for="email" class="sr-only">Email address</label>
		<input type="email" id="email" name="email" class="form-control" placeholder="Email address" required autofocus>
		<label for="password" class="sr-only">Password</label>
		<input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
		<button class="btn btn-lg btn-primary btn-block" type="submit">Log in</button>
	</form>
</div>

<c:import url="/WEB-INF/view/includes/footer.jsp"/>