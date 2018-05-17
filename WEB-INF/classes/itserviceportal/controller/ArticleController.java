package itserviceportal.controller;

import itserviceportal.model.*;

import java.util.*;
import java.io.*;
import java.util.regex.Pattern;
import java.lang.NumberFormatException;

import javax.servlet.http.*;
import javax.servlet.*;

/**
 * ArticleController
 *
 * @author Brice Purton, Jonothan Williams, Wajdi Yournes
 * @lastModified: 14-05-2018
 */

public class ArticleController extends HttpServlet {

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
		//List<Tickets> tickets = userDAL.getTicket(ticketId);
		SupportTicket article = new SupportTicket();

		if (article == null) {
			session.setAttribute("errorMessage", "Invalid Request");
			response.sendRedirect("ServicePortal");
			return;
		// If no ticket display error message
		} else if (article.getTitle() == null) {
			session.setAttribute("errorMessage", "No ticket to view");
		}

		request.setAttribute("article", article);

		// Send user to the correct jsp based on role
		if (user.getRole() == Role.USER) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Jsp.USERARTICLE.url());
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Jsp.STAFFARTICLE.url());
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
}

