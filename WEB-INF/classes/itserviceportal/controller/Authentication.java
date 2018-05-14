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
		permissions.put("/",ALL);
		permissions.put("login.jsp",ALL);
		permissions.put("403.jsp",ALL);
		permissions.put("404.jsp",ALL);
		permissions.put("500.jsp",ALL);
		permissions.put("userPortal.jsp",USERX);
		permissions.put("reportIssue.jsp",USERX);
		permissions.put("viewTickets.jsp",USER);
		permissions.put("supportTicket.jsp",USER);
		permissions.put("knowledgebase.jsp",USER);
		permissions.put("article.jsp",USER);
		permissions.put("staffPortal.jsp",STAFF);
		permissions.put("header.jsp",NONE);
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
				chain.doFilter(request,response);
				httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
				return;
			case USER:
				if (isUserLoggedIn(session)) {
					chain.doFilter(request, response);
					return;
				} else {
					httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
					return;
				}
			case USERX:
				if (isUserLoggedIn(session) && !isStaffLoggedIn(session)) {
					chain.doFilter(request, response);
					return;
				} else {
					httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
					return;
				}
			case STAFF:
				if (isStaffLoggedIn(session)) {
					chain.doFilter(request, response);
					return;
				} else {
					httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
					return;
				}
			default:
				httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
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
		return session.getAttribute("user") != null;
	}

	/**
	 * Check if a staff user is logged in
	 *
	 * @param session a http session object
	 * @return boolean true ifstaff logged in
	 */ 
	public boolean isStaffLoggedIn(HttpSession session) {
		UserBean user = (UserBean) session.getAttribute("user");
		return user != null && user.getRole() == Role.STAFF;
	}
}
