package itserviceportal.controller;

import itserviceportal.model.*;

import java.util.*;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The Authentication class is a web filter that manages user permissions.
 * 
 * @author Brice Purton
 * @studentID 3180044
 * @lastModified: 25-04-2018
 */

public class Authentication implements Filter {
	private Map<String,String> permissions = new HashMap<String,String>();
	// Constants
	public final static String ALL = "ALL";
	public final static String USER = "USER";
	public final static String USERX = "USER_EXCLUSIVE";
	public final static String STAFF = "STAFF";
	public final static String NONE = "NO_ACCESS";

	/**
	 * This method initialises the filter specifying user page access
	 * by adding them to the permissions map.
	 *
	 * @param filterConfig
	 * @throws ServletException
	 */ 
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// Servlets
		permissions.put("/",ALL);
		permissions.put("Login",ALL);
		permissions.put("Logout",USER);
		permissions.put("ServicePortal",USER);
		permissions.put("UserPortal",USERX);
		permissions.put("StaffPortal",STAFF);
		permissions.put("Report",USERX);
		permissions.put("UserPortal",USERX);
		permissions.put("TicketList",USER);
		permissions.put("Ticket",USER);
		permissions.put("KnowledgeBase",USER);
		permissions.put("Article",USER);
		// JSPs
		permissions.put("login.jsp",ALL);
		permissions.put("userPortal.jsp",USERX);
		permissions.put("staffPortal.jsp",STAFF);
		permissions.put("reportIssue.jsp",USERX);
		permissions.put("userTicketList.jsp",USERX);
		permissions.put("userticket.jsp",USERX);
		permissions.put("staffTicketList.jsp",STAFF);
		permissions.put("staffticket.jsp",STAFF);
		permissions.put("knowledgebase.jsp",USER);
		permissions.put("article.jsp",USER);
		permissions.put("comment.jsp",USER);
		permissions.put("header.jsp",NONE);
		permissions.put("403.jsp",ALL);
		permissions.put("404.jsp",ALL);
		permissions.put("500.jsp",ALL);
	}

	/**
	 * This method authenticates the user and 
	 *
	 * @param request a http servlet request 
	 * @param response a http servlet response
	 * @throws ServletException
	 * @throws IOException
	 */ 
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpServletRequest = ((HttpServletRequest) request);
		HttpServletResponse httpServletResponse = ((HttpServletResponse)response);
		HttpSession session = httpServletRequest.getSession();
		RequestDispatcher requestDispatcher;

		String uri = httpServletRequest.getRequestURI();

		if(uri.endsWith("/")) {
			uri = uri.substring(0, uri.length()-1);
		}
		String page = uri.substring(uri.lastIndexOf("/")+1);

		String permission = permissions.getOrDefault(page, ALL);

		switch (permission) {
			case ALL:
				chain.doFilter(request, response);
				return;
			case NONE:
				httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/");
				return;
			case USER:
				if (isUserLoggedIn(session)) {
					chain.doFilter(request, response);
					return;
				} else {
					httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/");
					return;
				}
			case USERX:
				if (isUserLoggedIn(session) && !isStaffLoggedIn(session)) {
					chain.doFilter(request, response);
					return;
				} else {
					httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/");
					return;
				}
			case STAFF:
				if (isStaffLoggedIn(session)) {
					chain.doFilter(request, response);
					return;
				} else {
					httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/");
					return;
				}
			default:
				chain.doFilter(request, response);
				return;
		}
	}

	@Override
	public void destroy() {
	}

	/**
	 * Check if a user is logged in
	 *
	 * @param session a http session object
	 * @return boolean true if user logged in
	 */ 
	public boolean isUserLoggedIn(HttpSession session) {
		return session.getAttribute("User") != null;
	}

	/**
	 * Check if a staff user is logged in
	 *
	 * @param session a http session object
	 * @return boolean true ifstaff logged in
	 */ 
	public boolean isStaffLoggedIn(HttpSession session) {
		User user = (User) session.getAttribute("User");
		return user != null && user.getRole() == Role.STAFF;
	}
}
