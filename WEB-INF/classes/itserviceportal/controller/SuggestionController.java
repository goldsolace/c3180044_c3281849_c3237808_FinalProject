package itserviceportal.controller;

import itserviceportal.model.beans.*;
import itserviceportal.model.datalayer.*;

import java.util.*;
import java.io.*;
import java.util.regex.Pattern;
import java.lang.NumberFormatException;

import javax.servlet.http.*;
import javax.servlet.*;

/**
 * SuggestionController
 *
 * @author Brice Purton, Jonothan Williams, Wajdi Yournes
 * @version 1.0
 * @since 19-05-2018
 */

public class SuggestionController extends HttpServlet {

	/**
	 * Display Knowledge Base
	 *
	 * @param request a http servlet request 
	 * @param response a http servlet response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		// Check if user is allowed to access Suggestion
		String referer = request.getHeader("referer");
		if (referer == null) {
			response.sendRedirect("ServicePortal");
			return;
		} else {
			// Get servlet name user requested suggestion from
			if (referer.endsWith("/")) {
				referer = referer.substring(0, referer.length()-1);
			}
			if (referer.indexOf('?') > -1) {
				referer = referer.substring(referer.lastIndexOf("/", referer.indexOf('?'))+1);
			} else {
				referer = referer.substring(referer.lastIndexOf("/")+1);
			}
			if (!referer.equals("Report")) {
				response.sendRedirect("ServicePortal");
				return;
			}
		}

		// Get user
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		// Get search term
		String term = request.getParameter("term");
		if (term != null && !term.isEmpty()) {
			// Get List of all suggested articles
			List<SupportTicket> suggestedArticles = getSuggestions(term);
			// Attach suggestions to the request to be forwarded to the jsp
			request.setAttribute("suggestedArticles", suggestedArticles);
		} else {
			response.sendRedirect("ServicePortal");
			return;
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Jsp.SUGGESTEDARTICLES.url());
		dispatcher.forward(request, response);
	}

	/**
	 * Do Stuff
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

		// Get sort criteria
		String categorySelect = request.getParameter("categorySelect");
		String orderSelect = request.getParameter("orderSelect");

		// If no sort criteria send to portal with error
		if (categorySelect == null || orderSelect == null) {
			session.setAttribute("errorMessage", "Sorry! We could not sort articles.");
			response.sendRedirect("ServicePortal");
			return;
		}
/*
		// Get List of all knowledge base articles matching criteria
		List<SupportTicket> knowledgeBase = getKnowledgeBase(user, categorySelect, orderSelect);

		// If tickets is null send back to portal with error message
		if (knowledgeBase == null) {
			session.setAttribute("errorMessage", "Sorry! Knowledge Base are unavailable.");
			response.sendRedirect("ServicePortal");
			return;
		
		// If no tickets display error message
		} else if (knowledgeBase.isEmpty()) {
			session.setAttribute("errorMessage", "There are no articles to display.");
		}

		// Attach tickets to the request to be forwarded to the jsp
		request.setAttribute("knowledgeBase", knowledgeBase);*/

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Jsp.KNOWLEDGEBASE.url());
		dispatcher.forward(request, response);
	}

	/**
	 * Get List of all similar Articles(Support Tickets) in the knowledge base
	 */
	public ArrayList<SupportTicket> getSuggestions(String term) {
		try {
			//Calling the Ticket Data Access to retrieve suggestions from the database
			TicketDataAccess ticketDAL = new TicketDataAccess();
			ArrayList<SupportTicket> suggestions = ticketDAL.getSuggestedArticles(term);
			return suggestions;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

