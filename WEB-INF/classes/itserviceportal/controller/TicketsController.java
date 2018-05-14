package itserviceportal.controller;

import itserviceportal.model.*;

import java.util.*;
import java.io.*;
import java.util.regex.Pattern;
import java.lang.NumberFormatException;

import javax.servlet.http.*;
import javax.servlet.*;

/**
 * 
 *
 * @author Brice Purton
 * @studentID 3180044
 * @lastModified: 14-05-2018
 */

public class TicketsController extends HttpServlet {

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

		//UserDataAccess userDAL = new UserDataAccess();
		//List<Tickets> tickets = userDAL.getTickets(user);
		List<SupportTicket> supportTickets = new ArrayList<SupportTicket>();

		if (supportTickets == null) {
			request.setAttribute("errorMessage", "No tickets to view");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("ServicePortal");
			dispatcher.forward(request, response);
			return;
		}
		request.setAttribute("supportTickets", supportTickets);

		if(user.getRole() == Role.USER) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Paths.USERTICKETLIST.url());
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Paths.STAFFTICKETLIST.url());
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
			// Probably sort
	}
}
