package itserviceportal.controller;
import java.io.*;
import java.util.*;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.http.*;
import itserviceportal.model.*;

/**
 * ReportController
 *
 * @author Brice Purton, Jonothan Williams, Wajdi Yournes
 * @lastModified: 14-05-2018
 */

public class ReportController extends HttpServlet{

	//Display the Login Page
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Jsp.REPORT.url());
		dispatcher.forward(request, response);
	}


    //
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Get user
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("User");
		
		//Ticket category, description and title
		String category = request.getParameter("Category");
		String description = request.getParameter("Description");
		String title = request.getParameter("Title");
		
		//Check for form category
		if (category == null)
		{
			session.setAttribute("errorMessage", "Sorry, incorrect issue submitted. Please try again.");
			response.sendRedirect("Report");
			return;
		}
		
		//Lists of questions and responses
		List<String> questions = new ArrayList<String>();
		List<String> responses = new ArrayList<String>();
				
		if (category.equals("NETWORK"))
		{
			String device = request.getParameter("Device");
			String location = request.getParameter("Location");
			String browser = request.getParameter("Browser");
			String website = request.getParameter("Website");
			String access = request.getParameter("Access");
			String alternate = request.getParameter("Alternate");
			String restart = request.getParameter("Restart");
			String isp = request.getParameter("ISP");
			
			questions.add("My Device:")
			questions.add("My Location:");
			questions.add("My Internet Browser is:");
			questions.add("I am trying to connect to the following website:");
			questions.add("I am able to access internal websites:");
			questions.add("I have tried using an alternate internet browser:");
			questions.add("I have tried restarting my device:");
			questions.add("Who is your ISP:");

			responses.add(device);
			responses.add(location);
			responses.add(browser);
			responses.add(website);
			responses.add(access);
			responses.add(alternate);
			responses.add(restart);
			responses.add(isp);
			
		}
		else if (category.equals("SOFTWARE"))
		{
			String device = request.getParameter("Device");
			String software = request.getParameter("Software");
			String install = request.getParameter("Install");
			String run = request.getParameter("Run");
			String version = request.getParameter("Version");
			
			questions.add("My Device:");
			questions.add("The software im trying to use:");
			questions.add("I can install the software:");
			questions.add("I can run the software:");
			questions.add("What software version are you running on:");
			
			responses.add(device);
			responses.add(software);
			responses.add(install);
			responses.add(run);
			responses.add(version);
		}
		else if (category.equals("HARDWARE"))
		{
			String device = request.getParameter("Device");
			String location = request.getParameter("Location");
			String access = request.getParameter("Access");
			String damaged = request.getParameter("Damaged");
			
			questions.add("Device im trying to use:");
			questions.add("My Location:");
			questions.add("I can access the device with my account login:");
			questions.add("Is the device damaged:");
			
			responses.add(device);
			responses.add(location);
			responses.add(access);
			responses.add(damaged);
			
		}
		else if (category.equals("EMAIL"))
		{
			String setup = request.getParameter("Setup");
			String signin = request.getParameter("Signin");
			String reset = request.getParameter("Reset");
			String sendAndReceive = request.getParameter("SendAndReceive");
			String internet = request.getParameter("Internet");
			
			questions.add("I have setup my email:");
			questions.add("I can sign in:");
			questions.add("Ive tried resetting my password:");
			questions.add("I can send and receive emails:");
			questions.add("I have confirmed my internet connection:");
			
			responses.add(setup);
			responses.add(signin);
			responses.add(reset);
			responses.add(sendAndReceive);
			responses.add(internet);
			
		}
		else if (category.equals("ACCOUNT"))
		{
			String activate = request.getParameter("Activate");
			String reset = request.getParameter("Reset");
			String system = request.getParameter("System");
			
			questions.add("I have activated my account:");
			questions.add("I have tried resetting my password:");
			questions.add("University system im trying to access:");
			
			responses.add(activate);
			responses.add(reset);
			responses.add(system);
			
		}
		else
		{
			session.setAttribute("errorMessage", "Sorry, incorrect issue submitted. Please try again.");
			response.sendRedirect("Report");
			return;
		}
		
		//Send all form info to TicketDataAccess
		TicketDataAccess TDA = new TicketDataAccess();
		TDA.newTicket(user, category, title, description, questions, responses);

		//Successful form
		session.setAttribute("successMessage", "Your issue has been reported!");
		response.sendRedirect("ServicePortal");
	}
}