package itserviceportal.model.datalayer;
import itserviceportal.model.beans.*;
import java.io.*;
import java.util.Date;
import java.util.ArrayList;
import javax.sql.*;
import java.sql.*;
import javax.naming.InitialContext;


/**
 * NotificationDataAccess.java
 * The database access class which gets a users notifications and adds new notifications to users.
 * Notifications occur when an action is performed on a support ticket and the user who owns the support
 * ticket will be notified about the changes / action.
 * 
 * Inherits from: DataAccessLayer.java
 *
 * @author Brice Purton, Jonathan Williams, Wajdi Yournes
 * @version 1.0
 * @since 19-05-2018
 */


public class NotificationDataAccess extends DataAccessLayer {

	private final static int MAX_NOTIFICATIONS = 5; //The max number of notifications to display at one time


 /**
   * Class constructor, calling the Superclass DataAccessLayer to initalise the database =
   * connection, prepared statement and result set objects.
   */
	public NotificationDataAccess() {
		super();
	}




  /**
   * This method gets all notifications for a specific user.
   * 
   * @param userID The userID of the user to get the notifications for
   * @return ArrayList<Notification> if execution successfull
   * @throws SQLException
   */
	public ArrayList<Notification> getNotifications(int userID) throws SQLException {

		//The list where notifications will be stored
		ArrayList<Notification> notifications = new ArrayList<>();

		//The query which gets all the notifications from a specific user
		String query = "SELECT * FROM tbl_Notification WHERE userID = ? ORDER BY NotificationDate DESC";


		try {
			// Getting the DB connection, performing the query and getting the results
			statement = dbConnection.prepareStatement(query);
			statement.setInt(1, userID);
			results = statement.executeQuery();

			// Iterate results to get list of notifications, create the notification from the data and add to list
			while (results.next()) {
				int notificationID = results.getInt("NotificationID");
				String notificationAction = results.getString("NotificationAction");
				Date notificationDate = new Date(results.getTimestamp("NotificationDate").getTime());
				int ticketID = results.getInt("TicketID");
				Notification notification = new Notification(notificationID, notificationAction, notificationDate, userID, ticketID);
				notifications.add(notification);
			}

			//return the notifications list if processing successful
			return notifications;

		//An exception occured, print the error and return null
		} catch(Exception e) {
			e.printStackTrace();
			return null;

		//Finally, close all open database connections
		} finally {
			closeConnections();	
		}
	}




  /**
   * This method adds a new notification into the database. 
   * 
   * @param action the action performed / the description of the notification, outlining what has been completed / updated
   * @param userID The userID which the notifiction is for.
   * @param ticketID the ticketID of the SupportTicket which has been modified.
   * @throws SQLException
   */
	public void setNotification(String action, int userID, int ticketID) throws SQLException {

		// Insert new notification
		String query = "INSERT INTO tbl_Notification (NotificationAction, NotificationDate, UserID, TicketID) VALUES (?, NOW(), ?, ?)";
		// Delete any notifations that match userID if userID has more than MAX_NOTIFICATIONS by oldest first
		String queryLimitNotifications = "DELETE FROM tbl_Notification WHERE userID = ? AND NotificationID NOT IN (SELECT NotificationID FROM (SELECT NotificationID FROM tbl_Notification WHERE userID = ? ORDER BY NotificationDate DESC LIMIT ? ) AS n)";
		
		try {
			// Insert new notification 
			statement = dbConnection.prepareStatement(query);
			statement.setString(1, action);
			statement.setInt(2, userID);
			statement.setInt(3, ticketID);
			statement.executeUpdate();

			// Keep 5 newest notifications for user
			statement = dbConnection.prepareStatement(queryLimitNotifications);
			statement.setInt(1, userID);
			statement.setInt(2, userID);
			statement.setInt(3, MAX_NOTIFICATIONS);
			statement.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeConnections();
		}
	}




  /**
   * This deletes notifications from the database once they have been read by the user.
   * 
   * @param userID The userID which the notification is assigned to.
   * @param notificationID the ID of the notification which is being deleted.
   * @throws SQLException
   */
	public void dismissNotification(int userID, int notificationID) throws SQLException {

		// Delete notification
		String query = "DELETE FROM tbl_Notification WHERE userID = ? AND  NotificationID = ?";
		
		try {
			statement = dbConnection.prepareStatement(query);
			statement.setInt(1, userID);
			statement.setInt(2, notificationID);
			statement.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeConnections();
		}
	}
}