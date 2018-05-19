package itserviceportal.model;

public enum Category {
    NETWORK("Network"),
    SOFTWARE("Software"),
    HARDWARE("Hardware"),
    ACCOUNT("Account"),
    EMAIL("Email");

	private String str;
	private Category(String str) { this.str = str; }
	public String getStr() { return str; }
}