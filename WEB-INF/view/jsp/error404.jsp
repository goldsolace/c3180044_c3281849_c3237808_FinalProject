<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang="en" class="max_size">
	<head>
		<title>404 Page Not Found</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() + "/css/style.css" %>" />
	</head>
	<body class="max_size">
		<canvas id="particle_canvas"></canvas>
		<div class="error_page">
			<div class="error_item">
				<h1>404</h1>
				<h2>PAGE NOT FOUND</h2>
				<a id="button" class="error_button" href="<%= request.getContextPath() + "/" %>">BACK TO HOMEPAGE</a>
			</div>
		</div>
	</body>
	<script src="<%= request.getContextPath() + "/js/particles.js" %>"></script>
</html>
