package itserviceportal.controller;

import itserviceportal.model.*;
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

		//Get the ticket ID passed in as a URL param
		int ticketID = -1;
		try
		{
			ticketID = Integer.parseInt(request.getParameter("ticket"));
		}
		//The URL did not contain a valid int value. Display error
		catch (NumberFormatException e)
		{
			System.out.println("EXCEPTION CAUGHT: TicketController -- NumberFormatException thrown while trying to parse ticketid");
			session.setAttribute("errorMessage", "Invalid Request");
			response.sendRedirect("ServicePortal");
			return;
		}
		
		//Get the support ticket by id
		SupportTicket supportTicket;
		TicketDataAccess ticketDAL = new TicketDataAccess();
		try
		{
			supportTicket = ticketDAL.getTicketByIDFromDB(ticketID);
		}
		catch (SQLException e)
		{
			supportTicket = null;
		}

		if (supportTicket == null) {
			session.setAttribute("errorMessage", "Invalid Request");
			response.sendRedirect("ServicePortal");
			return;
		// If no ticket display error message
		} else if (supportTicket.getTitle() == null) {
			session.setAttribute("errorMessage", "No ticket to view");
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
	 * Get List of all Support Tickets the user is allowed to view
	 */
	public SupportTicket createTempTicket(int day, int hour, int minute, State state, Category net) {
		SupportTicket ticket = new SupportTicket();
		ticket.setTicketID(56);
		User user2 = new User();
		user2.setUserID(1);
		user2.setFirstName("Joe");
		user2.setLastName("West");
		ticket.setReportedBy(user2);
		ticket.setResolvedBy(user2);

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(gc.YEAR, 2018);
		gc.set(gc.DAY_OF_YEAR, day);
		gc.set(gc.HOUR_OF_DAY, hour);
		gc.set(gc.MINUTE, minute);
		Date d = gc.getTime();
		ticket.setReportedOn(d);
		ticket.setResolvedOn(d);
		ticket.setTitle("Can't connect to uni wifi");
		ticket.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.");
		ticket.setState(state);
		ticket.setCategory(net);

		return ticket;
	}
}

