package itserviceportal.controller;

/**
 * Enumeration of Paths to store url constants for easy maintainability
 * 
 * @author Brice Purton
 * @studentID 3180044
 * @lastModified: 14-05-2018
 */

public enum Paths {
	// ENUMS
	INDEX("/WEB-INF/view/index.jsp"),
	LOGIN("/WEB-INF/view/index.jsp"),
	USERPORTAL("/WEB-INF/view/user/portal.jsp"),
	USERTICKET("/WEB-INF/view/user/portal.jsp"),
	USERTICKETLIST("/WEB-INF/view/user/ticketList.jsp"),
	USERKNOWLEDGEBASE("/WEB-INF/view/user/portal.jsp"),
	USERARTICLE("/WEB-INF/view/user/portal.jsp"),
	STAFFPORTAL("/WEB-INF/view/staff/portal.jsp"),
	STAFFTICKET("/WEB-INF/view/staff/portal.jsp"),
	STAFFTICKETLIST("/WEB-INF/view/staff/portal.jsp"),
	STAFFKNOWLEDGEBASE("/WEB-INF/view/user/portal.jsp"),
	STAFFARTICLE("/WEB-INF/view/user/portal.jsp"),
	FORBIDDEN("/WEB-INF/view/error403.jsp"),
	NOTFOUND("/WEB-INF/view/error404.jsp"),
	SERVERERROR("/WEB-INF/view/error500.jsp"),
	EXCEPTION("/WEB-INF/view/errorException.jsp");

	// Instance variables
	private String url;

	/**
	 * Private constructor to prevent further instances.
	 * 
	 * @param url path to be set
	 */
	private Paths(String url) {
		this.url = url;
	}

	/**
	 * Returns the uri path.
	 *
	 * @return url
	 */
	public String url() {
		return url;
	}
}