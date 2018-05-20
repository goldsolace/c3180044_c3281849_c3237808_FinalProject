<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="date" uri="http://localhost:8080/c3180044_c3281849_c3237808_FinalProject/taglib/date" %>
<%@ page import="itserviceportal.model.*" %>
 
<c:if test="${not empty comment}">
	<li class="list-group-item">
		<div class="d-flex justify-content-between">
			<h6>
				<c:if test="${comment.createdBy.role == Role.STAFF}">
					<strong class="mr-1 text-primary">IT STAFF</strong>
				</c:if>
				<c:out value="${comment.createdBy.firstName} ${comment.createdBy.lastName}"/>
			</h6>
			<small class="text-muted"><date:format date="${comment.createdOn}" /></small>
		</div>
		<p><c:out value="${comment.commentText}"/></p>
	</li>
</c:if> 