package itserviceportal.model.beans;

import java.io.Serializable;
import java.util.*;

/**
 * Notification bean
 *
 * @author Brice Purton, Jonothan Williams, Wajdi Yournes
 * @version 1.0
 * @since 22-05-2018
 */

public class Notification implements Serializable {

	public enum Action {
		STARTWORK, SUBMITSOLUTION, ADDKOWLEDGE, REMOVEKNOWLEDGE, COMMENT
	}

	private int notificationID;
	private Action action;
	private Date date;
	private int userID;
	private int ticketID;

	public Notification() {
	}

	public Notification(int notificationID, Action action, Date date, int userID, int ticketID) {
		this.notificationID = notificationID;
		this.action = action;
		this.date = date;
		this.userID = userID;
		this.ticketID = ticketID;
	}

	public Notification(int notificationID, String action, Date date, int userID, int ticketID) {
		this.notificationID = notificationID;
		setAction(action);
		this.date = date;
		this.userID = userID;
		this.ticketID = ticketID;
	}

	public int getNotificationID() { return notificationID; }
	public void setNotificationID(int notificationID) { this.notificationID = notificationID; }

	public Action getAction() { return action; }
	public void setAction(Action action) { this.action = action; }

	// For interfacing with data access layer
	public String getActionStr() {
		switch (action) {
			case STARTWORK : return "startWork";
			case SUBMITSOLUTION : return "submitSolution";
			case ADDKOWLEDGE : return "addKnowledge";
			case REMOVEKNOWLEDGE : return "removeKnowledge";
			case COMMENT : return "comment";
			default : return "";
		}
	}
	public void setAction(String action) {
		switch (action) {
			case "startWork" : this.action = Action.STARTWORK; break;
			case "submitSolution" : this.action = Action.SUBMITSOLUTION; break;
			case "addKnowledge" : this.action = Action.ADDKOWLEDGE; break;
			case "removeKnowledge" : this.action = Action.REMOVEKNOWLEDGE; break;
			case "comment" : this.action = Action.COMMENT; break;
			default : return;
		}
	}

	public Date getDate() { return date; }
	public void setDate(Date date) { this.date = date; }

	public int getUserID() { return userID; }
	public void setuserID(int userID) { this.userID = userID; }

	public int getTicketID() { return ticketID; }
	public void setTicketID(int ticketID) { this.ticketID = ticketID; }
}
