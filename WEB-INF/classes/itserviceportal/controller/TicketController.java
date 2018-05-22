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
		SupportTicket supportTicket = getTicket(ticketID, user);

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

		Enumeration<String> params = request.getParameterNames(); 
		while(params.hasMoreElements()){
			String paramName = params.nextElement();
		}

		HttpSession session = request.getSession();

		String action = request.getParameter("action");
		if (action == null) {
			session.setAttribute("errorMessage", "Sorry! Request is invalid");
			response.sendRedirect("ServicePortal");
			return;
		}

		//Fill hashmap with issue details
		switch (action) {
			case "startWork": StartWork(action, request, response); break;
			case "submitSolution": SubmitSolution(action, request, response); break;
			case "acceptSolution": AcceptSolution(action, request, response); break;
			case "rejectSolution": RejectSolution(action, request, response); break;
			case "addKnowledge": AddKnowledge(action, request, response); break;
			case "removeKnowledge": RemoveKnowledge(action, request, response); break;
			case "comment": Comment(action, request, response); break;
			default:
				session.setAttribute("errorMessage", "Sorry! Request is invalid");
				response.sendRedirect("ServicePortal");
				return;
		}
	}

	/**
	 * Get Support Ticket
	 */
	public SupportTicket getTicket(int ticketID, User user) {
		try {
			// Calling the Ticket Data Access to retrieve the ticket from the database
			TicketDataAccess ticketDAL = new TicketDataAccess();
			SupportTicket supportTicket = ticketDAL.getTicketByIDFromDB(ticketID, user, false);
			return supportTicket;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Start Work
	 */
	public void StartWork(String action, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get user
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if (user.getRole() != Role.STAFF) {
			session.setAttribute("errorMessage", "Sorry! Request is invalid");
			response.sendRedirect("ServicePortal");
			return;
		}

		// Get the ticket ID
		int ticketID = -1;
		try {
			ticketID = Integer.parseInt(request.getParameter("ticketID"));
		} catch (NumberFormatException e) {
			session.setAttribute("errorMessage", "Sorry! The ticket does not exist.");
			response.sendRedirect("ServicePortal");
			return;
		}

		///////////////////////////
		// Update Ticket Methods //
		///////////////////////////
		
		// Display updated ticket
		doGet(request, response);
	}

	/**
	 * Submit Solution
	 */
	public void SubmitSolution(String action, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get user
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		// Validate user role
		if (user.getRole() != Role.STAFF) {
			session.setAttribute("errorMessage", "Sorry! Request is invalid");
			response.sendRedirect("ServicePortal");
			return;
		}

		// Get the ticket ID
		int ticketID = -1;
		try {
			ticketID = Integer.parseInt(request.getParameter("ticketID"));
		} catch (NumberFormatException e) {
			session.setAttribute("errorMessage", "Sorry! The ticket does not exist.");
			response.sendRedirect("ServicePortal");
			return;
		}

		String resolutionDetails = request.getParameter("solution");
		if (resolutionDetails == null || resolutionDetails.isEmpty()) {
			session.setAttribute("errorMessage", "Sorry! Solution can't be empty");
			doGet(request, response);
			return;
		}

		///////////////////////////
		// Update Ticket Methods //
		///////////////////////////
		
		// Display updated ticket
		doGet(request, response);
	}

	/**
	 * Accept Solution
	 */
	public void AcceptSolution(String action, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get user
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		// Validate user role
		if (user.getRole() != Role.USER) {
			session.setAttribute("errorMessage", "Sorry! Request is invalid");
			response.sendRedirect("ServicePortal");
			return;
		}

		// Get the ticket ID
		int ticketID = -1;
		try {
			ticketID = Integer.parseInt(request.getParameter("ticketID"));
		} catch (NumberFormatException e) {
			session.setAttribute("errorMessage", "Sorry! The ticket does not exist.");
			response.sendRedirect("ServicePortal");
			return;
		}

		///////////////////////////
		// Update Ticket Methods //
		///////////////////////////
		
		// Display updated ticket
		doGet(request, response);
	}

	/**
	 * Reject Solution
	 */
	public void RejectSolution(String action, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get user
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		// Validate user role
		if (user.getRole() != Role.USER) {
			session.setAttribute("errorMessage", "Sorry! Request is invalid");
			response.sendRedirect("ServicePortal");
			return;
		}

		// Get the ticket ID
		int ticketID = -1;
		try {
			ticketID = Integer.parseInt(request.getParameter("ticketID"));
		} catch (NumberFormatException e) {
			session.setAttribute("errorMessage", "Sorry! The ticket does not exist.");
			response.sendRedirect("ServicePortal");
			return;
		}

		///////////////////////////
		// Update Ticket Methods //
		///////////////////////////
		
		// Display updated ticket
		doGet(request, response);
	}

	/**
	 * Add Knowledge
	 */
	public void AddKnowledge(String action, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get user
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		// Validate user role
		if (user.getRole() != Role.STAFF) {
			session.setAttribute("errorMessage", "Sorry! Request is invalid");
			response.sendRedirect("ServicePortal");
			return;
		}

		// Get the ticket ID
		int ticketID = -1;
		try {
			ticketID = Integer.parseInt(request.getParameter("ticketID"));
		} catch (NumberFormatException e) {
			session.setAttribute("errorMessage", "Sorry! The ticket does not exist.");
			response.sendRedirect("ServicePortal");
			return;
		}

		///////////////////////////
		// Update Ticket Methods //
		///////////////////////////
		
		// Display updated ticket
		doGet(request, response);
	}

	/**
	 * Remove Knowledge
	 */
	public void RemoveKnowledge(String action, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get user
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		// Validate user role
		if (user.getRole() != Role.STAFF) {
			session.setAttribute("errorMessage", "Sorry! Request is invalid");
			response.sendRedirect("ServicePortal");
			return;
		}

		// Get the ticket ID
		int ticketID = -1;
		try {
			ticketID = Integer.parseInt(request.getParameter("ticketID"));
		} catch (NumberFormatException e) {
			session.setAttribute("errorMessage", "Sorry! The ticket does not exist.");
			response.sendRedirect("ServicePortal");
			return;
		}

		///////////////////////////
		// Update Ticket Methods //
		///////////////////////////
		
		// Display updated ticket
		doGet(request, response);
	}

	/**
	 * Comment
	 */
	public void Comment(String action, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get user
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		// Get the ticket ID
		int ticketID = -1;
		try {
			ticketID = Integer.parseInt(request.getParameter("ticketID"));
		} catch (NumberFormatException e) {
			session.setAttribute("errorMessage", "Sorry! The ticket does not exist.");
			response.sendRedirect("ServicePortal");
			return;
		}

		String commentText = request.getParameter("commentText");
		if (commentText == null || commentText.isEmpty()) {
			session.setAttribute("errorMessage", "Sorry! Comment can't be empty");
			doGet(request, response);
			return;
		}
			
		// Add the comment to the ticket
		try
		{
			TicketDataAccess ticketDAL = new TicketDataAccess();
			ticketDAL.addComment(ticketID, comment);

			if (user.getRole() == Role.STAFF) {
				System.out.println("Create notif");
				NotificationDataAccess notificationDAL = new NotificationDataAccess();
				notificationDAL.setNotification(action, user.getUserID(), ticketID);
				System.out.println("Set notif");
				SessionListener.updateActiveUserNotifications(user);
			}
			session.setAttribute("notifications", notifications);
		}
		catch (SQLException e)
		{
			session.setAttribute("errorMessage", "Sorry! an error occured while trying to add a comment, please try again.");
			doGet(request, response);
			return;
		}
		
		// Display updated ticket
		doGet(request, response);
	}
}

