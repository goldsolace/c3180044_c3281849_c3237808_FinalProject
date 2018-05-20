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

	// Display the Report Page
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Jsp.REPORT.url());
		dispatcher.forward(request, response);
	}

    //
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Get user
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		// Ticket category, description and title
		String title = request.getParameter("title");
		String category = request.getParameter("category");
		String description = request.getParameter("description");
		
		// Check for form category
		if (category == null)
		{
			session.setAttribute("errorMessage", "Sorry, incorrect issue submitted. Please try again.");
			response.sendRedirect("Report");
			return;
		}

		// Map of questions and responses
		Map<String, String> issueDetails = new HashMap<String, String>();
		
		// Lists of questions and responses
		List<String> questions = new ArrayList<String>();
		List<String> responses = new ArrayList<String>();
				
		if (category.equals("NETWORK"))
		{
			issueDetails = getNetworkDetails(request);
			
		}
		else if (category.equals("SOFTWARE"))
		{
			String device = request.getParameter("device");
			String software = request.getParameter("software");
			String install = request.getParameter("install");
			String run = request.getParameter("run");
			String version = request.getParameter("version");
			String anotherDevice = request.getParameter("anotherDevice");
			
			questions.add("My Device:");
			questions.add("The software im trying to use:");
			questions.add("I can install the software:");
			questions.add("I can run the software:");
			questions.add("What software version are you running on:");
			questions.add("Have you tried to run the software on another computer?");
			
			responses.add(device);
			responses.add(software);
			responses.add(install);
			responses.add(run);
			responses.add(version);
			responses.add(anotherDevice);
		}
		else if (category.equals("HARDWARE"))
		{
			String device = request.getParameter("device");
			String location = request.getParameter("location");
			String access = request.getParameter("access");
			String damaged = request.getParameter("damaged");
			String power = request.getParameter("power");
			String error = request.getParameter("error");
			
			questions.add("Device im trying to use:");
			questions.add("My Location:");
			questions.add("I can access the device with my account login:");
			questions.add("Is the device damaged:");
			questions.add("Does the device power on?");
			questions.add("If error message is displayed, what is the message?");
			
			responses.add(device);
			responses.add(location);
			responses.add(access);
			responses.add(damaged);
			responses.add(power);
			responses.add(error);
			
		}
		else if (category.equals("EMAIL"))
		{
			String setup = request.getParameter("setup");
			String signin = request.getParameter("signIn");
			String reset = request.getParameter("reset");
			String sendAndReceive = request.getParameter("sendAndReceive");
			String internet = request.getParameter("internet");
			
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
			String activate = request.getParameter("activate");
			String university = request.getParameter("university");
			String error = request.getParameter("error");
			String reset = request.getParameter("reset");
			String system = request.getParameter("system");
			
			questions.add("I have activated my account:");
			questions.add("I can log into a university computer:");
			questions.add("If error message is displayed, what is the message?");
			questions.add("I have tried resetting my password:");
			questions.add("University system im trying to access:");
			
			responses.add(activate);
			responses.add(university);
			responses.add(error);
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

	/**
	 * Get Network Details 
	 */
	public Map<String, String> getNetworkDetails(HttpServletRequest request) {
		HashMap<String, String> details = new HashMap<String, String>();

		// Put Question, Answer into details map
		details.put("My Device:", request.getParameter("device"));
		details.put("My Location:", request.getParameter("location"));
		details.put("My Internet Browser is:", request.getParameter("browser"));
		details.put("I am trying to connect to the following website:", request.getParameter("website"));
		details.put("I am able to access internal websites:", request.getParameter("access"));
		details.put("I have tried using an alternate internet browser:", request.getParameter("alternate"));
		details.put("I have tried restarting my device:", request.getParameter("restart"));
		details.put("Can you access the website on another device?", request.getParameter("anotherDevice"));

		return details;
	}
}