package itserviceportal.controller;
import java.io.*;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.http.*;
import itserviceportal.model.*;

public class LoginController extends HttpServlet{

	//Display the Login Page
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Check to see if the user is currently already inside the session
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("User");
		
		//If the user is not null then the user has already logged in, direct to the portal
		if(user != null)
		{
			response.sendRedirect("ServicePortal");
		}

		//The user is not logged in so display the login page
		else
		{
			RequestDispatcher requestDispatcher; 
			requestDispatcher = request.getRequestDispatcher(Paths.INDEX.url());
			requestDispatcher.forward(request, response);
		}	
	}


    //Log the user into the IT Service Portal
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Get the data posted by the user
		String username = request.getParameter("inputEmail");
		String password = request.getParameter("inputPassword");

		//Calling the Data access layer to get the user from the database
		try
		{
			UserDataAccess userDAL = new UserDataAccess();
			User user = userDAL.loginUser(username, password);
		
			//If the user is null then the user did not login correctly with a valid account
			if(user == null)
			{
				request.setAttribute("errorMessage", "Sorry, that username and password combination does not exist. Please try again.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Paths.INDEX.url());
				dispatcher.forward(request, response);
			}
			//Otherwise, the user successfully logged in
			else
			{
				//Set the user object into the session and redirect to the ServicePortal
				HttpSession session = request.getSession();
				session.setAttribute("User", user);
				response.sendRedirect("ServicePortal");
			}
		}

		//If any error occured display an error message to the user
		catch (SQLException e)
		{
			request.setAttribute("errorMessage", "Sorry, Something went wrong while trying to log you in. Please try again.");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Paths.INDEX.url());
			dispatcher.forward(request, response);
		} 
	}
}