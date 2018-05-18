package itserviceportal.controller;

import java.io.*;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.http.*;
import itserviceportal.model.*;

/**
 * TimeoutController
 *
 * @author Brice Purton, Jonothan Williams, Wajdi Yournes
 * @lastModified: 18-05-2018
 */

public class TimeoutController extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Check to see if the user is currently already inside the session
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("User");
		
		// If the user is not null then the user has already logged in, direct to the portal
		if(user != null) {
			response.sendRedirect("ServicePortal");
		} else {
			String errorMessage = request.getParameter("errorMessage");

			if(errorMessage != null) {
				session.setAttribute("errorMessage", errorMessage);
				
			}
			response.sendRedirect(request.getContextPath() + "/");
		}
	}
}