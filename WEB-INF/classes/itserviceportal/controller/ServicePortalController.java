package itserviceportal.controller;
import java.io.*;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.http.*;
import itserviceportal.model.*;

/**
 * ServicePortalController
 *
 * @author Brice Purton, Jonothan Williams, Wajdi Yournes
 * @lastModified: 14-05-2018
 */


public class ServicePortalController extends HttpServlet{

	// Display the Portal Page
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Get user from session
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("User");
		
		if(user.getRole() == Role.USER)
		{
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Jsp.USERPORTAL.url());
			dispatcher.forward(request, response);
		}
		else
		{
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Jsp.STAFFPORTAL.url());
			dispatcher.forward(request, response);
		}
	}


    //No POST, perform GET if POST OCCURS
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}