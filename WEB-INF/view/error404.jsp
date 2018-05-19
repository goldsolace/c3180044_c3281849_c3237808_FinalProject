<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>IT Services Portal</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	<div class="jumbotron page-title">
		<h1>IT Services Portal</h1>
	</div>

	<div class="container-fluid error-page-container">
		<canvas id="particle_canvas"></canvas>
		<div class="row error_page">
			<div class="my-5 col-md-12 text-center">
				<h1>404</h1>
				<h2>PAGE NOT FOUND</h2>
				<a href="<c:url value='/' />" class="btn btn-info btn-lg">Take Me Home</a>
			</div>
		</div>
	</div>


	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="<%= request.getContextPath() + "/js/particles.js" %>"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
	<script src="js/validation.js"></script>
</body>
</html>