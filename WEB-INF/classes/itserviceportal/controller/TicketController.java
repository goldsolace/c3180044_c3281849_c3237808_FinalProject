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
 * TicketController
 *
 * @author Brice Purton, Jonothan Williams, Wajdi Yournes
 * @version 1.0
 * @since 19-05-2018
 */

public class TicketController extends HttpServlet {

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
		User user = (User) session.getAttribute("user");

		// Get the ticket ID passed in as a URL param
		int ticketID = -1;
		try
		{
			ticketID = Integer.parseInt(request.getParameter("ticketID"));
		}
		// The URL did not contain a valid int value. Display error
		catch (NumberFormatException e)
		{
			System.out.println("EXCEPTION CAUGHT: TicketController -- NumberFormatException thrown while trying to parse ticketid");
			session.setAttribute("errorMessage", "Sorry! The ticket you've requested does not exist.");
			response.sendRedirect("ServicePortal");
			return;
		}
		
		// Get the support ticket by id
		SupportTicket supportTicket = getTicket(ticketID);

		if (supportTicket == null) {
			session.setAttribute("errorMessage", "Sorry! The ticket you've requested does not exist.");
			response.sendRedirect("ServicePortal");
			return;
		// If no ticket display error message
		} else if (supportTicket.getTitle() == null) {
			session.setAttribute("errorMessage", "Sorry! We could not display that ticket");
		}

		request.setAttribute("supportTicket", supportTicket);

		// Send user to the correct jsp based on role
		if (user.getRole() == Role.USER) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Jsp.USERTICKET.url());
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Jsp.STAFFTICKET.url());
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
			doGet(request, response);
	}

	/**
	 * Get Support Ticket
	 */
	public SupportTicket getTicket(int ticketID) {
		try {
			// Calling the Ticket Data Access to retrieve the ticket from the database
			TicketDataAccess ticketDAL = new TicketDataAccess();
			SupportTicket supportTicket = ticketDAL.getTicketByIDFromDB(ticketID);
			return supportTicket;
		} catch (Exception e) {
			return null;
		}
	}
}

