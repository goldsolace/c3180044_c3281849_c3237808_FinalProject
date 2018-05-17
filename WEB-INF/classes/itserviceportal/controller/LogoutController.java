package itserviceportal.controller;

import java.io.*;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.http.*;
import itserviceportal.model.*;

public class LogoutController extends HttpServlet{

	//Log the user out and display the login page
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Check to see if the user is currently already inside the session
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("User");
		
		//If the user is not null then perform the logout
		if(user != null)
		{
			session.invalidate();
			request.getSession().setAttribute("successMessage", "You've been logged out!");
			response.sendRedirect(request.getContextPath() + "/");
		}

		//The user is not logged in so display the login page
		else
		{
			session.setAttribute("errorMessage", "No user logged in!");
			response.sendRedirect(request.getContextPath() + "/");
		}	
	}


    //there is no POST for this controller. Perform GET
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
	}
}