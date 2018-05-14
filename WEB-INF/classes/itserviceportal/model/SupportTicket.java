package itserviceportal.model;

import java.io.Serializable;
import java.util.*;

/**
 * Ticket Bean
 * 
 * @author Brice Purton
 * @studentID 3180044
 * @lastModified: 14-05-2018
 */

public class SupportTicket implements Serializable {

	private int ticketID;
	private Category category;
	private State state;
	private String title;
	private String description;
	private Date reportedOn;
	private User reportedBy;
	private Date resolvedOn;
	private User resolvedBy;
	private boolean knowledgeBase;
	private String resolutionDetails;
	private List<Comment> comments;

	public SupportTicket() {
	}

	public SupportTicket(int ticketID, Category category, State state, String title, String description, Date reportedOn,
		User reportedBy, Date resolvedOn, User resolvedBy, boolean knowledgeBase, String resolutionDetails, List<Comment> comments) {
		this.ticketID = ticketID;
		this.category = category;
		this.state = state;
		this.title = title;
		this.description = description;
		this.reportedOn = reportedOn;
		this.reportedBy = reportedBy;
		this.resolvedOn = resolvedOn;
		this.resolvedBy = resolvedBy;
		this.knowledgeBase = knowledgeBase;
		this.resolutionDetails = resolutionDetails;
	}

	public int getTicketID() { return ticketID; }
	public void setTicketID(int ticketID) { this.ticketID = ticketID; }

	public Category getCategory() { return category; }
	public void setCategory(Category category) { this.category = category; }

	public State getState() { return state; }
	public void setState(State state) { this.state = state; }

	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }

	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }

	public Date getReportedOn() { return reportedOn; }
	public void setReportedOn(Date reportedOn) { this.reportedOn = reportedOn; }

	public User getReportedBy() { return reportedBy; }
	public void setReportedBy(User reportedBy) { this.reportedBy = reportedBy; }

	public Date getResolvedOn() { return resolvedOn; }
	public void setResolvedOn(Date resolvedOn) { this.resolvedOn = resolvedOn; }

	public User getResolvedBy() { return resolvedBy; }
	public void setResolvedBy(User reportedBy) { this.resolvedBy = resolvedBy; }

	public boolean isKnowledgeBase() { return knowledgeBase; }
	public void setKnowledgeBase(boolean knowledgeBase) { this.knowledgeBase = knowledgeBase; }

	public String getResolutionDetails() { return resolutionDetails; }
	public void setResolutionDetails(String resolutionDetails) { this.resolutionDetails = resolutionDetails; }

	public List<Comment> getComments() { return comments; }
	public void setComments(List<Comment> comments) { this.comments = comments; }
}
