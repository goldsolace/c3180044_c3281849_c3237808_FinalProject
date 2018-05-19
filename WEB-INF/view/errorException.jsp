<%@ page isErrorPage="true" import="java.io.*" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="context" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en" class="max_size">
	<head>
		<title>500 Server Error</title>
		<link rel="stylesheet" type="text/css" href="${context}/css/style.css" />
	</head>
	<body class="max_size">
		<canvas id="particle_canvas"></canvas>
		<div class="error_page">
			<div class="error_item">
				<h1>500</h1>
				<h2>SERVER ERROR</h2>
				<a id="button" class="error_button error_red" href="${context}/">BACK TO HOMEPAGE</a>
			</div>
		</div>
	</body>
	<script src="${context}/js/particles.js"></script>
</html>
