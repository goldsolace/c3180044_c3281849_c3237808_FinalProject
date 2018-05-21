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
 * ArticleController
 *
 * @author Brice Purton, Jonothan Williams, Wajdi Yournes
 * @version 1.0
 * @since 19-05-2018
 */

public class ArticleController extends HttpServlet {

	/**
	 * Do stuff
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

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Jsp.ARTICLE.url());
		dispatcher.forward(request, response);
	}

	/**
	 * Do stuff
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

