package itserviceportal.model;

import java.io.Serializable;
import java.util.*;

/**
 * IssueDetail bean
 *
 * @author Brice Purton, Jonothan Williams, Wajdi Yournes
 * @version 1.0
 * @since 19-05-2018
 */

public class IssueDetail implements Serializable {

	private int issueDetailID;
	private String question;
	private String response;

	public IssueDetail() {
	}
	
	public IssueDetail(int issueDetailID, String question, String response) {
		this.issueDetailID = issueDetailID;
		this.question = question;
		this.response = response;
	}
	
	public int getIssueDetailID() { return issueDetailID; }
	public void setIssueDetailID(int issueDetailID) { this.issueDetailID = issueDetailID; }
	
	public String getQuestion () { return question; }
	public void setQuestion (String question) { this.question = question; }
	
	public String getResponse () { return response; }
	public void setResponse () { this.response = response; }
}
	