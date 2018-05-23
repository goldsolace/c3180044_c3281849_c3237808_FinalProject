package itserviceportal.controller;

import itserviceportal.model.beans.*;
import itserviceportal.model.datalayer.*;
import java.io.*;
import java.util.*;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.http.*;

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
		switch (category) {
			case "network": issueDetails = getNetworkDetails(request); break;
			case "software": issueDetails = getSoftwareDetails(request); break;
			case "hardware": issueDetails = getHardwareDetails(request); break;
			case "email": issueDetails = getEmailDetails(request); break;
			case "account": issueDetails = getAccountDetails(request); break;
			default:
				session.setAttribute("errorMessage", "Invalid Category. Please try again.");
				response.sendRedirect("Report");
				return;
		}
		
		//Send all form info to TicketDataAccess
		try {
			TicketDataAccess ticketDAL = new TicketDataAccess();
			ticketDAL.newTicket(user, category, title, description, issueDetails);
		} catch (Exception e) {
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
		String device = request.getParameter("networkDevice");
		if (device != null && !device.isEmpty()) {
			details.put("Device", device);
		}
		String location = request.getParameter("networkLocation");
		if (location != null && !location.isEmpty()) {
			details.put("Location", location);
		}
		String browser = request.getParameter("networkBrowser");
		if (browser != null && !browser.isEmpty()) {
			details.put("Browser", browser);
		}
		String website = request.getParameter("networkWebsite");
		if (website != null && !website.isEmpty()) {
			details.put("Website I'm trying to connect to?", website);
		}
		String access = request.getParameter("networkAccess");
		if (access != null && !access.isEmpty()) {
			details.put("I am able to access internal websites?", access);
		}
		String alternate = request.getParameter("networkAlternate");
		if (alternate != null && !alternate.isEmpty()) {
			details.put("I have tried using an alternate internet browser?", alternate);
		}
		String restart = request.getParameter("networkRestart");
		if (restart != null && !restart.isEmpty()) {
			details.put("I have tried restarting my device?", restart);
		}
		String anotherDevice = request.getParameter("networkAnotherDevice");
		if (anotherDevice != null && !anotherDevice.isEmpty()) {
			details.put("I can access the website on another device?", anotherDevice);
		}

		return details;
	}
	
	/**
	 * Get Software Details 
	 */
	public Map<String, String> getSoftwareDetails(HttpServletRequest request) {
		HashMap<String, String> details = new HashMap<String, String>();
		
		String device = request.getParameter("softwareDevice");
		if (device != null && !device.isEmpty()) {
			details.put("Device", device);
		}
		String software = request.getParameter("softwareSoftware");
		if (software != null && !software.isEmpty()) {
			details.put("Software", software);
		}
		String version = request.getParameter("softwareVersion");
		if (version != null && !version.isEmpty()) {
			details.put("Version", version);
		}
		String install = request.getParameter("softwareInstall");
		if (install != null && !install.isEmpty()) {
			details.put("I can install the software?", install);
		}
		String run = request.getParameter("softwareRun");
		if (run != null && !run.isEmpty()) {
			details.put("I can run the software?", run);
		}
		String anotherDevice = request.getParameter("softwareAnotherDevice");
		if (anotherDevice != null && !anotherDevice.isEmpty()) {
			details.put("I have tried to run the software on another computer?", anotherDevice);
		}
		
		return details;
	}
	
	/**
	 * Get Hardware Details 
	 */
	public Map<String, String> getHardwareDetails(HttpServletRequest request) {
		HashMap<String, String> details = new HashMap<String, String>();
		
		String device = request.getParameter("hardwareDevice");
		if (device != null && !device.isEmpty()) {
			details.put("Device", device);
		}
		String location = request.getParameter("hardwareLocation");
		if (location != null && !location.isEmpty()) {
			details.put("Location", location);
		}
		String access = request.getParameter("hardwareAccess");
		if (access != null && !access.isEmpty()) {
			details.put("I can access the device with my account login?", access);
		}
		String damaged = request.getParameter("hardwareDamaged");
		if (damaged != null && !damaged.isEmpty()) {
			details.put("Device is damaged?", damaged);
		}
		String power = request.getParameter("hardwarePower");
		if (power != null && !power.isEmpty()) {
			details.put("Device powers on?", power);
		}
		String error = request.getParameter("hardwareError");
		if (error != null && !error.isEmpty()) {
			details.put("Error message?", error);
		}
		
		return details;
	}
	
	/**
	 * Get Email Details 
	 */
	public Map<String, String> getEmailDetails(HttpServletRequest request) {
		HashMap<String, String> details = new HashMap<String, String>();
		
		String setup = request.getParameter("emailSetup");
		if (setup != null && !setup.isEmpty()) {
			details.put("I have setup my email?", setup);
		}
		String signIn = request.getParameter("emailSignIn");
		if (signIn != null && !signIn.isEmpty()) {
			details.put("I can sign in?", signIn);
		}
		String reset = request.getParameter("emailReset");
		if (reset != null && !reset.isEmpty()) {
			details.put("I've tried resetting my password?", reset);
		}
		String sendReceive = request.getParameter("emailSendReceive");
		if (sendReceive != null && !sendReceive.isEmpty()) {
			details.put("I can send and receive emails?", sendReceive);
		}
		String internet = request.getParameter("emailInternet");
		if (internet != null && !internet.isEmpty()) {
			details.put("Internet connection confirmed??", internet);
		}
		
		return details;
	}
	
	/**
	 * Get Account Details 
	 */
	public Map<String, String> getAccountDetails(HttpServletRequest request) {
		HashMap<String, String> details = new HashMap<String, String>();
		
		String activate = request.getParameter("accountActivate");
		if (activate != null && !activate.isEmpty()) {
			details.put("I have activated my account?", activate);
		}
		String university = request.getParameter("accountUniversity");
		if (university != null && !university.isEmpty()) {
			details.put("I can log into a university computer?", university);
		}
		String system = request.getParameter("accountSystem");
		if (system != null && !system.isEmpty()) {
			details.put("University system im trying to access?", system);
		}

		String reset = request.getParameter("accountReset");
		if (reset != null && !reset.isEmpty()) {
			details.put("I have tried resetting my password?", reset);
		}
		String error = request.getParameter("accountError");
		if (error != null && !error.isEmpty()) {
			details.put("Error message?", error);
		}
		
		return details;
	}
}