<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/view/header.jsp"/>

<div class="form-container">
	<form class="form-signin" method="POST" action="Login">
		<label for="inputEmail" class="sr-only">Email address</label>
		<input type="email" id="inputEmail" name="inputEmail" class="form-control" placeholder="Email address" required autofocus>
		<label for="inputPassword" class="sr-only">Password</label>
		<input type="password" id="inputPassword" name="inputPassword" class="form-control" placeholder="Password" required>
		<button class="btn btn-lg btn-primary btn-block" type="submit">Log in</button>
	</form>
</div>

<c:import url="/WEB-INF/view/footer.jsp"/>