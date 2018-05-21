<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix ="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="itserviceportal.model.beans.*" %>

<div class="dropdown">
	<button class="btn btn-notif dropdown-toggle" type="button" id="notifications" data-toggle="dropdown">
		<span class="far fa-bell h5"></span>
		<span class="fas fa-circle notif-dot"></span>
	</button>
	<div id="menu-notif" class="dropdown-menu m-0 p-0 dropdown-menu-left" aria-labelledby="notifications">
		<form class="form-notif" method="POST" action="Notification">
			<input name="ticketID" type="hidden" value="1">
			<button class="btn btn-block btn-light btn-danger text-left word-wrap py-0 text-notif" type="submit">
				<span>
					<strong>Support Ticket 1</strong> has been commented on.
				</span>
				<br>
				<small class="text-muted">15 minutes ago</small>
			</button>
		</form>
		<form class="form-notif" method="POST" action="Notification">
			<input name="ticketID" type="hidden" value="328">
			<button class="btn btn-block btn-light btn-danger text-left word-wrap py-0 text-notif" type="submit">
				<span>
					<strong>Support Ticket 328</strong> has been added to the knowledge base.
				</span>
				<br>
				<small class="text-muted"">5 hours ago.</small>
			</button>
		</form>
		<form class="form-notif" method="POST" action="Notification">
			<input name="ticketID" type="hidden" value="16">
			<button class="btn btn-block btn-light btn-danger text-left word-wrap py-0 text-notif" type="submit">
				<span>
					Solution submitted to <strong>Support Ticket 16</strong>.
				</span>
				<br>
				<small class="text-muted"">6:17pm 18/07/2018</small>
			</button>
		</form>
	</div>
</div>