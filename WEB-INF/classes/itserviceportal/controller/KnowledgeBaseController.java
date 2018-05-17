package itserviceportal.controller;

import itserviceportal.model.*;

import java.util.*;
import java.io.*;
import java.util.regex.Pattern;
import java.lang.NumberFormatException;

import javax.servlet.http.*;
import javax.servlet.*;

/**
 * KnowledgeBaseController
 *
 * @author Brice Purton, Jonothan Williams, Wajdi Yournes
 * @lastModified: 14-05-2018
 */

public class KnowledgeBaseController extends HttpServlet {

	/**
	 * Redirects get request to doPost method
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
		User user = (User)session.getAttribute("User");

		// Get List of Knowledge Base articles
		List<SupportTicket> knowledgeBase = getKnowledgeBase(user, "all", "all");

		// If knowledgeBase is null send back to portal with error message
		if (knowledgeBase == null) {
			session.setAttribute("errorMessage", "Invalid Request");
			response.sendRedirect("ServicePortal");
			return;
		
		// If knowledgeBase empty display error message
		} else if (knowledgeBase.isEmpty()) {
			session.setAttribute("successMessage", "Issue has been reported.");
			session.setAttribute("errorMessage", "Knowledge Base is empty!");
			session.setAttribute("infoMessage", "Tickets automatically sent to staff.");
			session.setAttribute("warningMessage", "NOTICE: System Maintenance at 12:00pm-3:00pm Friday 18/05/2018.");
		}

		// Attach knowledgeBase to the request to be forwarded to the jsp
		request.setAttribute("knowledgeBase", knowledgeBase);

		// Send user to the correct jsp based on role
		if (user.getRole() == Role.USER) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Jsp.USERKNOWLEDGEBASE.url());
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Jsp.STAFFKNOWLEDGEBASE.url());
			dispatcher.forward(request, response);
		}
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
			// Do stuff
	}

	/**
	 * Get List of all Support Tickets the user is allowed to view
	 */
	public List<SupportTicket> getKnowledgeBase(User user, String categorySelect, String stateSelect) {
		// Something like this
		// categorySelect = "all", "new", "inProgress", "completed", "resolved"
		// stateSelect = "all", "software", "hardware", "network", "account", "email"
		// Returns an arraylist of SupportTickets
		// Can return null for invalid or empty list if no tickets the user can view

		// UserDataAccess userDAL = new UserDataAccess();
		// if (user.getRole() == Role.STAFF) {
		// 	return userDAL.getAllSupportTickets(user, categorySelect, stateSelect);
		// } else {
		// 	return userDAL.getSupportTickets(user, categorySelect, stateSelect);
		// }
		
		return new ArrayList<SupportTicket>();
	}
}

