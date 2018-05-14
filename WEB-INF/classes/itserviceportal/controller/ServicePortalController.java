package itserviceportal.controller;
import java.io.*;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import itserviceportal.model.*;

@WebServlet(urlPatterns = {"/ServicePortal"})
public class ServicePortalController extends HttpServlet{

	//Display the Login Page
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Check to see if the user is currently already inside the session
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute("User");
		
		//If the user is not null then the user has already logged in, direct to the service portal
		if(user != null)
		{
            //If the logged in user is a USER, display the userPortal
            if(user.getRole() == Role.USER)
            {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Paths.USERPORTAL.url());
				dispatcher.forward(request, response);
            }
            else
            {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Paths.STAFFPORTAL.url());
				dispatcher.forward(request, response);
            }

		}

		//The user is not logged in so display the login page
		else
		{
			response.sendRedirect(Paths.INDEX.url());
		}	
	}


    //No POST, perform GET if POST OCCURS
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}