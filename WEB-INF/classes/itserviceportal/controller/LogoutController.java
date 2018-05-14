package itserviceportal.controller;
import java.io.*;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import itserviceportal.model.*;

@WebServlet(urlPatterns = {"/Logout"})
public class LogoutController extends HttpServlet{

	//Log the user out and display the login page
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Check to see if the user is currently already inside the session
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute("User");
		
		//If the user is not null then perform the logout
		if(user != null)
		{
			session.invalidate();
			request.setAttribute("successMessage", "You've been logged out!");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Paths.INDEX.url());
			dispatcher.forward(request, response);
		}

		//The user is not logged in so display the login page
		else
		{
			request.setAttribute("errorMessage", "No user logged in!");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Paths.INDEX.url());
			dispatcher.forward(request, response);
		}	
	}


    //there is no POST for this controller. Perform GET
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
	}
}