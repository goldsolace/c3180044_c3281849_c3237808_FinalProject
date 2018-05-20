package itserviceportal.model;

public enum State {
    NEW("New"),
    INPROGRESS("In Progress"),
    COMPLETED("Completed"),
    RESOLVED("Resolved");

	private String str;
	private State(String str) { this.str = str; }
	public String getStr() { return str; }
}