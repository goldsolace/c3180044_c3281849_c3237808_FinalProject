<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix ="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="customtags" uri="http://localhost:8080/c3180044_c3281849_c3237808_FinalProject/taglib/customtags" %>
<%@ page import="itserviceportal.model.beans.*" %>


<div class="dropdown">
	<c:choose>
		<c:when test="${not empty notifications}">
			<button class="btn btn-notif dropdown-toggle" type="button" id="notifications" data-toggle="dropdown">
				<span class="far fa-bell h5"></span>
				<span class="fas fa-circle notif-dot"></span>
			</button>
			<div id="menu-notif" class="dropdown-menu m-0 p-0 dropdown-menu-left" aria-labelledby="notifications">
				<%-- Iterate through notifications list --%>
				<c:forEach var="notification" items="${notifications}">
					<form class="form-notif form-inline" method="POST" action="Notification?notificationID=${notification.notificationID}">
						<input name="ticketID" type="hidden" value="${notification.ticketID}">
						<div class="container-fluid btn-group nopadding">
							<button class="btn btn-block btn-light text-left word-wrap py-0 text-notif" type="submit">
								<span>
									<customtags:notif notification="${notification}"/>
								</span>
								<br>
								<small class="text-muted"><customtags:date date="${notification.date}"/></small>
							</button>
							<a class="btn btn-dismiss py-0 d-flex align-items-center" href="Notification?action=dismiss&notificationID=${notification.notificationID}"><span class="fas fa-times align-middle"></span></a>
						</div>
					</form>
				</c:forEach>
				<%--
				<form class="form-notif form-inline" method="POST" action="Notification">
					<input name="ticketID" type="hidden" value="1">
					<div class="container-fluid btn-group nopadding">
						<button class="btn btn-block btn-light text-left word-wrap py-0 text-notif" type="submit">
							<span>
								<strong>Support Ticket 1</strong> has been commented on.
							</span>
							<br>
							<small class="text-muted">15 minutes ago</small>
							
						</button>
						<a class="btn btn-dismiss py-0 d-flex align-items-center" href="Notification?action=dismiss&ticketID=${notification.ticketID}"><span class="fas fa-times align-middle"></span></a>
					</div>
				
				</form>
				<form class="form-notif" method="POST" action="Notification">
					<input name="ticketID" type="hidden" value="328">
					<div class="container-fluid btn-group nopadding">
						<button class="btn btn-block btn-light text-left word-wrap py-0 text-notif" type="submit">
							<span>
								<strong>Support Ticket 328</strong> has been added to the knowledge base.
							</span>
							<br>
							<small class="text-muted"">5 hours ago.</small>
						</button>
						<a class="btn btn-dismiss py-0 d-flex align-items-center" href="Notification?action='dismiss'&ticketID=${notification.ticketID}"><span class="fas fa-times align-middle"></span></a>
					</div>
					</button>
				</form>
				<form class="form-notif" method="POST" action="Notification">
					<input name="ticketID" type="hidden" value="16">
					<div class="container-fluid btn-group nopadding">
						<button class="btn btn-block btn-light text-left word-wrap py-0 text-notif" type="submit">
							<span>
								Solution submitted to <strong>Support Ticket 16</strong>.
							</span>
							<br>
							<small class="text-muted"">6:17pm 18/07/2018</small>
						</button>
						<a class="btn btn-dismiss py-0 d-flex align-items-center" href="Notification?action='dismiss'&ticketID=${notification.ticketID}"><span class="fas fa-times align-middle"></span></a>
					</div>
				</form>--%>
			</div>
		</c:when>
		<c:otherwise>
			<button class="btn btn-notif dropdown-toggle" type="button" id="notifications" data-toggle="dropdown" disabled>
				<span class="far fa-bell h5"></span>
			</button>
		</c:otherwise>
	</c:choose>
</div>