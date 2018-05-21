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

	private int notificationID;
	private String text;
	private Date date;
	private int userID;
	private int ticketID;

	public Notification() {
	}

	public Notification(int notificationID, String text, Date date, int userID, int ticketID) {
		this.notificationID = notificationID;
		this.text = text;
		this.date = date;
		this.userID = userID;
		this.ticketID = ticketID;
	}

	public int getNotificationID() { return notificationID; }
	public void setNotificationID(int notificationID) { this.notificationID = notificationID; }

	public String getText() { return text; }
	public void setText(String text) { this.text = text; }

	public Date getDate() { return date; }
	public void setDate(Date date) { this.date = date; }

	public int getUserID() { return userID; }
	public void setuserID(int userID) { this.userID = userID; }

	public int getTicketID() { return ticketID; }
	public void setTicketID(int ticketID) { this.ticketID = ticketID; }
}
