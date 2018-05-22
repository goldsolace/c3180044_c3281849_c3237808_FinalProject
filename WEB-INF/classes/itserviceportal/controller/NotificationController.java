package itserviceportal.controller;

import itserviceportal.model.beans.*;
import itserviceportal.model.datalayer.*;
import java.util.*;
import java.io.*;
import java.util.regex.Pattern;
import java.lang.NumberFormatException;
import java.sql.SQLException;

import javax.servlet.http.*;
import javax.servlet.*;

/**
 * NotificationController
 *
 * @author Brice Purton, Jonothan Williams, Wajdi Yournes
 * @version 1.0
 * @since 22-05-2018
 */

public class NotificationController extends HttpServlet {

	/**
	 * Dismisses a notification for a user
	 *
	 * @param request a http servlet request 
	 * @param response a http servlet response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		// Get user
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		// Check valid action
		String action = request.getParameter("action");
		if (action == null) {
			session.setAttribute("errorMessage", "Sorry! Request is invalid.");
			response.sendRedirect("ServicePortal");
			return;
		} else if (action.equals("dismiss")) {

			// Check valid notifcationID
			int notificationID = 0;
			try {
				notificationID = Integer.parseInt(request.getParameter("notificationID"));
			} catch (Exception e) {
				session.setAttribute("errorMessage", "Sorry! Request is invalid.");
				response.sendRedirect("ServicePortal");
				return;
			}

			try {
				// Delete notificaton from database
				NotificationDataAccess notificationDAL = new NotificationDataAccess();
				notificationDAL.dismissNotification(user.getUserID(), notificationID);

				// Remove the notification from the session and the database
				@SuppressWarnings("unchecked")
				List<Notification> notifications = (ArrayList<Notification>) session.getAttribute("notifications");
				if (notifications != null && !notifications.isEmpty()) {
					for(int i=0; i<notifications.size(); i++) {
						Notification n = notifications.get(i);
						if (n.getNotificationID() == notificationID) {
							notifications.remove(i);
							break;
						}
					}
					session.setAttribute("notifications", notifications);
				}
			} catch (Exception e) {
			}
		}

		// Send user back to page they requested to dismiss notification from
		String referer = request.getHeader("referer");
		if (referer == null) {
			response.sendRedirect("ServicePortal");
			return;
		}

		if(referer.endsWith("/")) {
			referer = referer.substring(0, referer.length()-1);
		}
		// Get servlet url-mapping and query string only
		String sendUserBack = referer.substring(referer.lastIndexOf("/", referer.indexOf('?'))+1);
		response.sendRedirect(sendUserBack);
	}

	/**
	 * This method controls the main flow of the game by deciding what to do based on input of the user.
	 *
	 * @param request a http servlet request 
	 * @param response a http servlet response
	 * @throws ServletException
	 * @throws IOException
	 */ 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		// Get user
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		// Check valid notifcationID
		int notificationID = 0;
		try {
			notificationID = Integer.parseInt(request.getParameter("notificationID"));
		} catch (Exception e) {
			session.setAttribute("errorMessage", "Sorry! Request is invalid.");
			response.sendRedirect("ServicePortal");
			return;
		}

		// Check valid ticketID
		int ticketID = 0;
		try {
			ticketID = Integer.parseInt(request.getParameter("ticketID"));
		} catch (Exception e) {
			session.setAttribute("errorMessage", "Sorry! Request is invalid.");
			response.sendRedirect("ServicePortal");
			return;
		}

		try {
			// Delete notificaton from database
			NotificationDataAccess notificationDAL = new NotificationDataAccess();
			notificationDAL.dismissNotification(user.getUserID(), notificationID);

			// Remove the notification from the session and the database
			@SuppressWarnings("unchecked")
			List<Notification> notifications = (ArrayList<Notification>) session.getAttribute("notifications");
			if (notifications != null && !notifications.isEmpty()) {
				for(int i=0; i<notifications.size(); i++) {
					Notification n = notifications.get(i);
					if (n.getNotificationID() == notificationID) {
						notifications.remove(i);
						break;
					}
				}
				session.setAttribute("notifications", notifications);	
				// Send to TicketController to display Ticket
				response.sendRedirect("Ticket?ticketID=" + ticketID);
				return;
			}
		} catch (Exception e) {
		}
		session.setAttribute("errorMessage", "Sorry! Request is invalid.");
		response.sendRedirect("ServicePortal");
	}
}

