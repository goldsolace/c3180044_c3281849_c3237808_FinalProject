<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="context" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>IT Services Portal</title>
	<link href="${context}/css/bootstrap.min.css" rel="stylesheet">
	<link href="${context}/css/css/fontawesome-all.css" rel="stylesheet">
	<link href="${context}/css/style.css" rel="stylesheet">
</head>
<body>
	<a class="nounderline" href="ServicePortal">
		<div class="jumbotron page-title">
			<h1>IT Services Portal</h1>
		</div>
	</a>
	<%-- Include nav bar on every page --%>
	<c:import url="/WEB-INF/view/includes/nav.jsp"/>
	<div class="container">
	