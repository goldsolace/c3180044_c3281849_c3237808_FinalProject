package itserviceportal.model;

import java.io.Serializable;
import java.util.*;


import itserviceportal.model.Category;

/**
 * SupportTicket Bean
 *
 * @author Brice Purton, Jonothan Williams, Wajdi Yournes
 * @version 1.0
 * @since 19-05-2018
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
	private ArrayList<Comment> comments;

	public SupportTicket() {
	}

	public SupportTicket(int ticketID, Category category, State state, String title, String description, Date reportedOn,
		User reportedBy, Date resolvedOn, User resolvedBy, boolean knowledgeBase, String resolutionDetails, ArrayList<Comment> comments) {
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
		this.comments = comments;
	}

	public SupportTicket(int ticketID, String category, String state, String title, String description, Date reportedOn,
		User reportedBy, Date resolvedOn, User resolvedBy, boolean knowledgeBase, String resolutionDetails, ArrayList<Comment> comments) {
		this.ticketID = ticketID;
		setCategory(category);
		setState(state);
		this.title = title;
		this.description = description;
		this.reportedOn = reportedOn;
		this.reportedBy = reportedBy;
		this.resolvedOn = resolvedOn;
		this.resolvedBy = resolvedBy;
		this.knowledgeBase = knowledgeBase;
		this.resolutionDetails = resolutionDetails;
		this.comments = comments;
	}

	public int getTicketID() { return ticketID; }
	public void setTicketID(int ticketID) { this.ticketID = ticketID; }

	public Category getCategory() { return category; }
	public void setCategory(Category category) { this.category = category; }
	public void setCategory(String category) {
		if(category.equalsIgnoreCase("network"))
			this.category = Category.NETWORK;
		else if(category.equalsIgnoreCase("software"))
			this.category = Category.SOFTWARE;
		else if(category.equalsIgnoreCase("hardware"))
			this.category = Category.HARDWARE;
		else if(category.equalsIgnoreCase("account"))
			this.category = Category.ACCOUNT;
		else
			this.category = Category.EMAIL;
	}

	public State getState() { return state; }
	public void setState(State state) { this.state = state; }
	public void setState(String state) {
		if(state.equalsIgnoreCase("new"))
			this.state = State.NEW;

		else if(state.equalsIgnoreCase("in progress"))
			this.state = State.INPROGRESS;

		else if(state.equalsIgnoreCase("completed"))
			this.state = State.COMPLETED;

		else
			this.state = State.RESOLVED;
	}

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
	public void setResolvedBy(User resolvedBy) { this.resolvedBy = resolvedBy; }

	public boolean isKnowledgeBase() { return knowledgeBase; }
	public void setKnowledgeBase(boolean knowledgeBase) { this.knowledgeBase = knowledgeBase; }

	public String getResolutionDetails() { return resolutionDetails; }
	public void setResolutionDetails(String resolutionDetails) { this.resolutionDetails = resolutionDetails; }

	public ArrayList<Comment> getComments() { return comments; }
	public void setComments(ArrayList<Comment> comments) { this.comments = comments; }
}
