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
	USERPORTAL("/WEB-INF/view/user/userPortal.jsp"),
	REPORT("/WEB-INF/view/user/reportIssue.jsp"),
	USERTICKET("/WEB-INF/view/user/userPortal.jsp"),
	USERTICKETLIST("/WEB-INF/view/user/userTicketList.jsp"),
	USERKNOWLEDGEBASE("/WEB-INF/view/user/userPortal.jsp"),
	USERARTICLE("/WEB-INF/view/user/userPortal.jsp"),
	STAFFPORTAL("/WEB-INF/view/staff/staffPortal.jsp"),
	STAFFTICKET("/WEB-INF/view/user/userTicket.jsp"),
	STAFFTICKETLIST("/WEB-INF/view/user/userTicketList.jsp"),
	STAFFKNOWLEDGEBASE("/WEB-INF/view/user/staffPortal.jsp"),
	STAFFARTICLE("/WEB-INF/view/user/staffPortal.jsp"),
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