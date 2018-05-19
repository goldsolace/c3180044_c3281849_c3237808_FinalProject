package itserviceportal.controller;

/**
 * Enumeration of JSP pages to store url constants for easy maintainability
 *
 * @author Brice Purton, Jonothan Williams, Wajdi Yournes
 * @version 1.0
 * @since 19-05-2018
 */

public enum Jsp {
	INDEX("/WEB-INF/view/index.jsp"),
	LOGIN("/WEB-INF/view/index.jsp"),
	USERPORTAL("/WEB-INF/view/user/userPortal.jsp"),
	REPORT("/WEB-INF/view/user/reportIssue.jsp"),
	USERTICKET("/WEB-INF/view/user/userTicket.jsp"),
	USERTICKETLIST("/WEB-INF/view/user/userTicketList.jsp"),
	USERKNOWLEDGEBASE("/WEB-INF/view/user/knowledgeBase.jsp"),
	USERARTICLE("/WEB-INF/view/user/article.jsp"),
	STAFFPORTAL("/WEB-INF/view/staff/staffPortal.jsp"),
	STAFFTICKET("/WEB-INF/view/user/userTicket.jsp"),
	STAFFTICKETLIST("/WEB-INF/view/user/userTicketList.jsp"),
	STAFFKNOWLEDGEBASE("/WEB-INF/view/user/knowledgeBase.jsp"),
	STAFFARTICLE("/WEB-INF/view/user/article.jsp"),
	FORBIDDEN("/WEB-INF/view/error403.jsp"),
	NOTFOUND("/WEB-INF/view/error404.jsp"),
	SERVERERROR("/WEB-INF/view/error500.jsp"),
	EXCEPTION("/WEB-INF/view/errorException.jsp");

	private String url;

	// Private constructor to prevent new instances.
	private Jsp(String url) { this.url = url; }

	public String url() { return url; }
}