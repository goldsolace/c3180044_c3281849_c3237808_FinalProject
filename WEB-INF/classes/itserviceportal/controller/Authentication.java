package itserviceportal.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.util.Set;

public class Authentication implements Filter {
	private Map<String,String> permissions = new HashMap<String,String>();
	public final static String ALL = "ALL";
	public final static String USER = "USER";
	public final static String USERX = "USER_EXCLUSIVE";
	public final static String STAFF = "STAFF";
	public final static String NONE = "NO_ACCESS";

	public String security(String command){
		return grant.getOrDefault(command,ALL);
	}

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

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		SecurityConfiguration securityConfiguration = SecurityConfiguration.getInstance();

		HttpServletRequest httpServletRequest = ((HttpServletRequest) request);
		HttpServletResponse httpServletResponse = ((HttpServletResponse)response);
		HttpSession session = httpServletRequest.getSession();

		String uri = httpServletRequest.getRequestURI();
		if(uri.endsWith("/")) {
			uri = uri.substring(0, uri.length()-1);
		}
		String page = uri.substring(uri.lastIndexOf("/")+1);

		String permission = grant.getOrDefault(page, ALL);

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
				chain.doFilter(request, response);
				return;
		}
		httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	}

	@Override
	public void destroy() {
	}

	public boolean isUserLoggedIn(HttpSession session) {
		return session.getAttribute("user") != null;
	}
	public boolean isAdminLoggedIn(HttpSession session) {
		User user = (User) session.getAttribute("user");
		return user != null && user.getRole() == Role.STAFF;
	}
}
