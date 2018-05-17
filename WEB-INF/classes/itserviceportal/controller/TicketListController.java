package itserviceportal.controller;

import itserviceportal.model.*;

import java.util.*;
import java.io.*;
import java.util.regex.Pattern;
import java.lang.NumberFormatException;

import javax.servlet.http.*;
import javax.servlet.*;

/**
 * TicketListController
 *
 * @author Brice Purton, Jonothan Williams, Wajdi Yournes
 * @lastModified: 14-05-2018
 */

public class TicketListController extends HttpServlet {

	/**
	 * Display all tickets the user has access to.
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

		// Get List of all Support Tickets the user is allowed to view
		List<SupportTicket> tickets = getTickets(user, "all", "all");

		// If tickets is null send back to portal with error message
		if (tickets == null) {
			session.setAttribute("errorMessage", "Invalid Request");
			response.sendRedirect("ServicePortal");
			return;
		
		// If no tickets display error message
		} else if (tickets.isEmpty()) {
			session.setAttribute("errorMessage", "No tickets");
		}

		// Attach tickets to the request to be forwarded to the jsp
		request.setAttribute("tickets", tickets);

		// Send user to the correct jsp based on role
		if (user.getRole() == Role.USER) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Paths.USERTICKETLIST.url());
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Paths.STAFFTICKETLIST.url());
			dispatcher.forward(request, response);
		}
	}

	/**
	 * Display all tickets the user has access to with sort criteria.
	 *
	 * @param request a http servlet request 
	 * @param response a http servlet response
	 * @throws ServletException
	 * @throws IOException
	 */ 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		// Print params
		Map params = request.getParameterMap();
		Iterator i = params.keySet().iterator();
		while ( i.hasNext() ) {
			String key = (String) i.next();
			String value = ((String[]) params.get( key ))[ 0 ];
			System.out.println(key+" "+value);
		}
		
		// Get user
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("User");

		// Get sort criteria
		String categorySelect = request.getParameter("categorySelect");
		String stateSelect = request.getParameter("stateSelect");
		String orderSelect = request.getParameter("orderSelect");

		// If no sort criteria send to portal with error
		if (categorySelect == null || stateSelect == null || orderSelect == null) {
			session.setAttribute("errorMessage", "Could not sort tickets");
			response.sendRedirect("ServicePortal");
			return;
		}

		// Get List of Support Tickets the user is allowed to view matching criteria
		List<SupportTicket> tickets = getTickets(user, categorySelect, stateSelect);

		// If tickets is null send back to portal with error message
		if (tickets == null) {
			session.setAttribute("errorMessage", "Invalid Request");
			response.sendRedirect("ServicePortal");
			return;
		
		// If no tickets display error message
		} else if (tickets.isEmpty()) {
			session.setAttribute("errorMessage", "No tickets");
		}

		// Sort tickets (default newest to oldest)
		SupportTicketComparator.SortOrder order = orderSelect == "oldest" ? SupportTicketComparator.SortOrder.DESCENDING : SupportTicketComparator.SortOrder.ASCENDING;
		Collections.sort(tickets, new SupportTicketComparator(order));

		// Attach tickets to the request to be forwarded to the jsp
		request.setAttribute("tickets", tickets);

		// Send user to the correct jsp based on role
		if (user.getRole() == Role.USER) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Paths.USERTICKETLIST.url());
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Paths.STAFFTICKETLIST.url());
			dispatcher.forward(request, response);
		}
	}


	/**
	 * Get List of all Support Tickets the user is allowed to view
	 */
	public List<SupportTicket> getTickets(User user, String categorySelect, String stateSelect) {
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

