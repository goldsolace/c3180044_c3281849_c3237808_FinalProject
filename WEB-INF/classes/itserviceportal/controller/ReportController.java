package itserviceportal.controller;
import java.io.*;
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
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Paths.REPORT.url());
		dispatcher.forward(request, response);
	}


    //No POST, perform GET if POST OCCURS
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}