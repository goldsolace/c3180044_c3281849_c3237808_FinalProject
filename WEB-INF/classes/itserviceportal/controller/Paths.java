package itserviceportal.model;

public enum Paths {
	INDEX("/WEB-INF/view/index.jsp"),
	LOGIN("/WEB-INF/view/index.jsp"),
	USERPORTAL("/WEB-INF/view/user/portal.jsp"),
	USERTICKET("/WEB-INF/view/user/portal.jsp"),
	USERTICKETLIST("/WEB-INF/view/user/portal.jsp"),
	KNOWLEDGEBASE(""),
	ARTICLE(""),
	STAFFPORTAL("/WEB-INF/view/staff/portal.jsp"),
	403("/WEB-INF/view/error403.jsp"),
	404("/WEB-INF/view/error404.jsp"),
	500("/WEB-INF/view/error500.jsp"),
	EXCEPTION("/WEB-INF/view/errorException.jsp");

	private String url;

	private Paths(String url) {
		this.url = url;
	}

	public String url() {
		return url;
	}
}