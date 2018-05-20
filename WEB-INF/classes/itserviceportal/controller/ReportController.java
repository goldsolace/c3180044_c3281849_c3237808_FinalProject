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
		
		//Fill hashmap with issue details
		if (category.equals("NETWORK"))
		{
			issueDetails = getNetworkDetails(request);
		}
		else if (category.equals("SOFTWARE"))
		{
			issueDetails = getSoftwareDetails(request);
		}
		else if (category.equals("HARDWARE"))
		{
			issueDetails = getHardwareDetails(request);
		}
		else if (category.equals("EMAIL"))
		{
			issueDetails = getEmailDetails(request);
		}
		else if (category.equals("ACCOUNT"))
		{
			issueDetails = getAccountDetails(request);
		}
		else
		{
			session.setAttribute("errorMessage", "Sorry, incorrect issue submitted. Please try again.");
			response.sendRedirect("Report");
			return;
		}
		
		//Send all form info to TicketDataAccess
		TicketDataAccess TDA = new TicketDataAccess();
		
		try
		{
		TDA.newTicket(user, category, title, description, issueDetails);
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION CAUGHT: ReportController -- newTicket");
		}
		
		
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
	
	/**
	 * Get Software Details 
	 */
	public Map<String, String> getSoftwareDetails(HttpServletRequest request) {
		HashMap<String, String> details = new HashMap<String, String>();
		
		details.put("My Device:", request.getParameter("device"));
		details.put("The software im trying to use:", request.getParameter("software"));
		details.put("I can install the software:", request.getParameter("install"));
		details.put("I can run the software:", request.getParameter("run"));
		details.put("What software version are you running on:", request.getParameter("version"));
		details.put("Have you tried to run the software on another computer?", request.getParameter("anotherDevice"));
		
		return details;
	}
	
	/**
	 * Get Hardware Details 
	 */
	public Map<String, String> getHardwareDetails(HttpServletRequest request) {
		HashMap<String, String> details = new HashMap<String, String>();
		
		details.put("Device im trying to use:", request.getParameter("device"));
		details.put("My Location:", request.getParameter("location"));
		details.put("I can access the device with my account login:", request.getParameter("access"));
		details.put("Is the device damaged:", request.getParameter("damaged"));
		details.put("Does the device power on?", request.getParameter("power"));
		details.put("If error message is displayed, what is the message?", request.getParameter("error"));
		
		return details;
	}
	
	/**
	 * Get Email Details 
	 */
	public Map<String, String> getEmailDetails(HttpServletRequest request) {
		HashMap<String, String> details = new HashMap<String, String>();
		
		details.put("I have setup my email:", request.getParameter("setup"));
		details.put("I can sign in:", request.getParameter("signIn"));
		details.put("Ive tried resetting my password:", request.getParameter("reset"));
		details.put("I can send and receive emails:", request.getParameter("sendAndReceive"));
		details.put("I have confirmed my internet connection:", request.getParameter("internet"));
		
		return details;
	}
	
	/**
	 * Get Account Details 
	 */
	public Map<String, String> getAccountDetails(HttpServletRequest request) {
		HashMap<String, String> details = new HashMap<String, String>();
		
		details.put("I have activated my account:", request.getParameter("activate"));
		details.put("I can log into a university computer:", request.getParameter("university"));
		details.put("If error message is displayed, what is the message?", request.getParameter("error"));
		details.put("I have tried resetting my password:", request.getParameter("reset"));
		details.put("University system im trying to access:", request.getParameter("system"));
		
		return details;
	}
}