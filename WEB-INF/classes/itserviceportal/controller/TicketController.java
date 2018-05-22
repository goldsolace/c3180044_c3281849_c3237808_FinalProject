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

		try
		{
			TicketDataAccess ticketDAL = new TicketDataAccess();
			ticketDAL.updateTicketStateToInProgress(ticketID);

			session.setAttribute("warningMessage", "User has been notified that you've started work on Support Ticket " + ticketID + ".");
 
			// Get the ticket ID
			int reportedUserID = -1;
			try {
				// Notify User
				reportedUserID = Integer.parseInt(request.getParameter("reportedBy"));
				NotificationDataAccess notificationDAL = new NotificationDataAccess();
				notificationDAL.setNotification(action, reportedUserID, ticketID);
				SessionListener.updateActiveUserNotifications(reportedUserID);
			} catch (NumberFormatException e) {
			}
		}
		catch (SQLException e)
		{
			session.setAttribute("errorMessage", "Sorry! An error occured while trying to update the ticket state. Please try again");
			response.sendRedirect("Ticket?ticketID=" + ticketID);
		}
		
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

		//Update the ticket to completed status
		try
		{
			TicketDataAccess ticketDAL = new TicketDataAccess();
			ticketDAL.updateTicketStateToComplete(ticketID, resolutionDetails, user.getUserID());

			session.setAttribute("successMessage", "Solution has been successfully submitted and user has been notified.");
 
			// Get the ticket ID
			int reportedUserID = -1;
			try {
				// Notify User
				reportedUserID = Integer.parseInt(request.getParameter("reportedBy"));
				NotificationDataAccess notificationDAL = new NotificationDataAccess();
				notificationDAL.setNotification(action, reportedUserID, ticketID);
				SessionListener.updateActiveUserNotifications(reportedUserID);
			} catch (NumberFormatException e) {
			}
		}
		catch (SQLException e)
		{
			session.setAttribute("errorMessage", "Sorry! An error occured while trying to update the ticket state. Please try again");
			response.sendRedirect("Ticket?ticketID=" + ticketID);
		}
		
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

		//Update the ticket to resolved status
		try
		{
			TicketDataAccess ticketDAL = new TicketDataAccess();
			ticketDAL.updateTicketStateToAccepted(ticketID);

			session.setAttribute("successMessage", "Support ticket " + ticketID + " has been resolved and closed. We're glad we could help!");
		}
		catch (SQLException e)
		{
			session.setAttribute("errorMessage", "Sorry! An error occured while trying to update the ticket state. Please try again");
			response.sendRedirect("Ticket?ticketID=" + ticketID);

		}
		
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

		//Update the ticket to in progress status. When user rejects solution status reverts back to inprogress
		//And all resolution details such as ResolvedOn, ResolvedByUserID and ResolutionDetails are set back to NULL
		try
		{
			TicketDataAccess ticketDAL = new TicketDataAccess();
			ticketDAL.updateTicketStateToRejected(ticketID);

			session.setAttribute("infoMessage", "Solution has been rejected and your support ticket has been set back to In Progress.");
		}
		catch (SQLException e)
		{
			session.setAttribute("errorMessage", "Sorry! An error occured while trying to update the ticket state. Please try again");
			response.sendRedirect("Ticket?ticketID=" + ticketID);
		}
		
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

		//Add the ticket to the knowledge base by setting IsKnowledgeBase = 1
		try
		{
			TicketDataAccess ticketDAL = new TicketDataAccess();
			ticketDAL.AddOrRemoveFromKnowledgeBase(ticketID, true);

			session.setAttribute("successMessage", "Support Ticket has been added to Knowledge Base.");
 
			// Get the ticket ID
			int reportedUserID = -1;
			try {
				// Notify User
				reportedUserID = Integer.parseInt(request.getParameter("reportedBy"));
				NotificationDataAccess notificationDAL = new NotificationDataAccess();
				notificationDAL.setNotification(action, reportedUserID, ticketID);
				SessionListener.updateActiveUserNotifications(reportedUserID);
			} catch (NumberFormatException e) {
			}
		}
		catch (SQLException e)
		{
			session.setAttribute("errorMessage", "Sorry! An error occured while trying to update the ticket state. Please try again");
			response.sendRedirect("Ticket?ticketID=" + ticketID);
		}
		
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

		//Add the ticket to the knowledge base by setting IsKnowledgeBase = 0
		try
		{
			String backToList = request.getParameter("redirection");
			TicketDataAccess ticketDAL = new TicketDataAccess();
			ticketDAL.AddOrRemoveFromKnowledgeBase(ticketID, false);

			session.setAttribute("successMessage", "Support Ticket has been removed from Knowledge Base.");
 
			// Get the ticket ID
			int reportedUserID = -1;
			try {
				// Notify User
				reportedUserID = Integer.parseInt(request.getParameter("reportedBy"));
				NotificationDataAccess notificationDAL = new NotificationDataAccess();
				notificationDAL.setNotification(action, reportedUserID, ticketID);
				SessionListener.updateActiveUserNotifications(reportedUserID);
			} catch (NumberFormatException e) {
			}

			//If redirection is not null, then we want to go back to the knowledge base list
			//because a staff member removed a knowledge base article from inside the article and
			//not the ticket list
			if(backToList != null)
			{
				response.sendRedirect("KnowledgeBase");
				return;
			}
		}
		catch (SQLException e)
		{
			session.setAttribute("errorMessage", "Sorry! An error occured while trying to update the ticket state. Please try again");
			response.sendRedirect("Ticket?ticketID=" + ticketID);
		}
		
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
		try {
 			TicketDataAccess ticketDAL = new TicketDataAccess();
 			ticketDAL.addComment(ticketID, commentText, user.getUserID());
			session.setAttribute("successMessage", "Comment has been posted.");
 
			// If Staff action then notify user
 			if (user.getRole() == Role.STAFF) {
				// Get the ticket ID
				int reportedUserID = -1;
				try {
					reportedUserID = Integer.parseInt(request.getParameter("reportedBy"));
					NotificationDataAccess notificationDAL = new NotificationDataAccess();
					notificationDAL.setNotification(action, reportedUserID, ticketID);
					SessionListener.updateActiveUserNotifications(reportedUserID);
					session.setAttribute("successMessage", "Comment has been posted and user has been notified.");
				} catch (NumberFormatException e) {
				}
			}
		} catch (SQLException e) {
			session.setAttribute("errorMessage", "Sorry! an error occured while trying to add a comment, please try again.");
			doGet(request, response);
			return;
		}
		
		// Display updated ticket
		doGet(request, response);
	}
}

