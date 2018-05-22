package itserviceportal.controller;

import itserviceportal.model.beans.*;
import itserviceportal.model.datalayer.*;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * SessionListener
 *
 * @author Brice Purton, Jonothan Williams, Wajdi Yournes
 * @version 1.0
 * @since 19-05-2018
 */

public final class SessionListener implements HttpSessionListener, HttpSessionAttributeListener  {

	// Map to store active user sessions
	private static HashMap<Integer, HttpSession> activeUserSessions = new HashMap<Integer, HttpSession>();

	// Utility method to print out all active sessions
	private static void printActiveUserSessions() {
		activeUserSessions.forEach((key,value) -> System.out.println(key+", "+value));
	}

	/**
	 * Check if a user is active in a session
	 * 
	 * @param userID
	 * @return true if active
	 */
	public static boolean isUserActive(int userID) {
		return activeUserSessions.containsKey(userID);
	}

	/**
	 * Return active user's session
	 * 
	 * @param userID
	 * @return user's session
	 */
	public static HttpSession getActiveUserSession(int userID) {
		return activeUserSessions.get(userID);
	}

	/**
	 * Reload an active user's notifcations into their session
	 * 
	 * @param userID
	 */
	public static void updateActiveUserNotifications(User user) {
		try {
			if (user.getRole() == Role.USER && isUserActive(user.getUserID())) {
				System.out.println("update");
				HttpSession session = activeUserSessions.get(user.getUserID());
				NotificationDataAccess notificationDAL = new NotificationDataAccess();
				List<Notification> notifications = notificationDAL.getNotifications(user.getUserID());
				// for (int i = 0; i < notifications.size(); i++){
    //     			System.out.println(notifications.get(i));
    // 			}
				session.setAttribute("notifications", notifications);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		// Set session to timeout after 15 minutes
		event.getSession().setMaxInactiveInterval(15 * 60);
	}

	/**
	 * Do stuff when session is destroyed
	 * 
	 * @param event The HttpSessionBindingEvent event
	 */
	@Override
	public final void sessionDestroyed(HttpSessionEvent event) {
		// Get game stored in session. Null if no game attribute
		User user = (User) event.getSession().getAttribute("user");
		if (user != null) {
			// Do stuff
		}
		// Remove from active session map
		activeUserSessions.remove(user.getUserID());
	}

	/**
	 * Add userID and session to activeUserSessions map if user attribute added to a session
	 *
	 * @param event The HttpSessionBindingEvent event
	 */
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		// User logs in
		if (event.getName().equals("user")) {
			User user = (User) event.getValue();
			activeUserSessions.put(user.getUserID(), event.getSession());
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		// User logs out
		if (event.getName().equals("user")) {
			User user = (User) event.getValue();
			activeUserSessions.remove(user.getUserID());
		}

	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
	}
}