package itserviceportal.controller;

import java.util.*;
import itserviceportal.model.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * SessionListener
 * 
 * @author Brice Purton
 * @lastModified: 18-05-2018
 */

public final class SessionListener implements HttpSessionListener, HttpSessionAttributeListener  {

	// Map to store active sessions
	private static HashMap<String, String> activeSessions = new HashMap<String, String>();

	// Utility method to print out all activeSessions
	private static void printActiveSessions() {
		activeSessions.forEach((key,value) -> System.out.println(key+", "+value));
	}

	/**
	 * Check if a user is active in a session
	 * 
	 * @param userID
	 * @return true if active
	 */
	public static boolean isActive(String userID) {
		// Iterate through map entries
		for (Map.Entry<String,String> entries : activeSessions.entrySet()) {
			// If stored username matches username return in use (true)
		 	if (entries.getValue().equals(userID)) {
				return true;
			}
		}
		// Not found
		return false;
	}

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		// Set session to timeout after 15 minutes
		event.getSession().setMaxInactiveInterval(15 * 60);
	}

	/**
	 * Saves game when session is destroyed
	 * 
	 * @param event The HttpSessionBindingEvent event
	 */
	@Override
	public final void sessionDestroyed(HttpSessionEvent event) {
		// Get game stored in session. Null if no game attribute
		User user = (User) event.getSession().getAttribute("User");
		if (user != null) {
			// Do stuff
		}
		// Remove from active session map
		activeSessions.remove(event.getSession().getId());
	}

	/**
	 * Add session Id and username if game attribute added to a session
	 *
	 * @param event The HttpSessionBindingEvent event
	 */
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		if (event.getName().equals("User")) {
			User user = (User) event.getValue();
			activeSessions.put(event.getSession().getId(), String.valueOf(user.getUserID()));
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
	}
}