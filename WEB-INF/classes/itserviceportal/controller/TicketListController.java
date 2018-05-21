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
 * @version 1.0
 * @since 19-05-2018
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
		User user = (User) session.getAttribute("user");

		long start = System.nanoTime();
		// Get List of all Support Tickets the user is allowed to view
		ArrayList<SupportTicket> tickets = getTickets(user, "all", "all", false, "newest");
		long end = System.nanoTime();
		System.out.println("Took: " + ((end - start) / 1000000) + "ms");

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

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Jsp.TICKETLIST.url());
		dispatcher.forward(request, response);
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
		User user = (User) session.getAttribute("user");

		// Get sort criteria
		String categorySelect = request.getParameter("categorySelect");
		String stateSelect = request.getParameter("stateSelect");
		String orderSelect = request.getParameter("orderSelect");
		System.out.println(categorySelect);

		// If no sort criteria send to portal with error
		if (categorySelect == null || stateSelect == null || orderSelect == null) {
			session.setAttribute("errorMessage", "Could not sort tickets");
			response.sendRedirect("ServicePortal");
			return;
		}

		long start = System.nanoTime();
		// Get List of Support Tickets the user is allowed to view matching criteria
		List<SupportTicket> tickets = getTickets(user, categorySelect, stateSelect, false, orderSelect);
		long end = System.nanoTime();
		System.out.println("Took: " + ((end - start) / 1000000) + "ms");


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

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Jsp.TICKETLIST.url());
		dispatcher.forward(request, response);
	}


	/**
	 * Get List of all Support Tickets the user is allowed to view
	 */
	public ArrayList<SupportTicket> getTickets(User user, String categorySelect, String stateSelect, boolean knowledgeBase, String orderBy) {
		try
		{
			//Calling the Ticket Data Access to retrieve all the tickets from the database
			TicketDataAccess ticketDAL = new TicketDataAccess();
			ArrayList<SupportTicket> ticketList = ticketDAL.getAllTicketsFromDB(user, stateSelect, categorySelect, false, orderBy);

			return ticketList;
		}
		catch (Exception e)
		{
			return null;
		}
	}
}

