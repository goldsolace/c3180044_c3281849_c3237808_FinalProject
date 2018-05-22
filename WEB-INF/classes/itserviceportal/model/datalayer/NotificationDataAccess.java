package itserviceportal.model.datalayer;

import itserviceportal.model.beans.*;
import java.io.*;
import java.util.Date;
import java.util.ArrayList;
import javax.sql.*;
import java.sql.*;
import javax.naming.InitialContext;

public class NotificationDataAccess extends DataAccessLayer {

	private final static int MAX_NOTIFICATIONS = 5;

	public NotificationDataAccess() {
		super();
	}

	public ArrayList<Notification> getNotifications(int userID) throws SQLException {

		ArrayList<Notification> notifications = new ArrayList<>();
		String query = "SELECT * FROM tbl_Notifications WHERE userID = ? ORDER BY NotificationDate ASC";

		try {
			// Getting the DB connection, performing the query and getting the results
			statement = dbConnection.prepareStatement(query);
			statement.setInt(1, userID);
			results = statement.executeQuery();

			// Iterate results to get list of notifications
			while(results.next()) {
				int notificationID = results.getInt("NotificationID");
				String notificationAction = results.getString("NotificationAction");
				Date notificationDate = results.getDate("NotificationDate");
				int ticketID = results.getInt("TicketID");
				Notification notification = new Notification(notificationID, notificationAction, notificationDate, userID, ticketID);
				notifications.add(notification);
			}
			return notifications;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			closeConnections();	
		}
	}

	public void setNotification(String action, int userID, int ticketID) throws SQLException {

		// Insert new notification
		String query = "INSERT INTO tbl_Notifications (NotificationAction, NotificationDate, UserID, TicketID) VALUES (?, ?, ?, ?)";
		// Delete any notifations that match userID if userID has more than MAX_NOTIFICATIONS by oldest first
		String queryLimitNotifications = "DELETE FROM tbl_Notification WHERE userID = ? AND NotificationID NOT IN (SELECT NotificationID FROM (SELECT NotificationID FROM tbl_Notification WHERE userID = ? ORDER BY NotificationDate DESC LIMIT ? ) AS n)";
		
		try {
			// Insert new notification 
			statement = dbConnection.prepareStatement(query);
			statement.setString(1, action);
			statement.setDate(2, new java.sql.Date(new Date().getTime()));
			statement.setInt(3, userID);
			statement.setInt(4, ticketID);
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

	// TODO =====
	public void dismissNotification(int userID, int notificationID) throws SQLException {

		// Insert new notification
		String query = "INSERT INTO tbl_Notifications (NotificationAction, NotificationDate, UserID, TicketID) VALUES (?, ?, ?, ?)";
		// Delete any notifations that match userID if userID has more than MAX_NOTIFICATIONS by oldest first
		String queryLimitNotifications = "DELETE FROM tbl_Notification WHERE userID = ? AND NotificationID NOT IN (SELECT NotificationID FROM (SELECT NotificationID FROM tbl_Notification WHERE userID = ? ORDER BY NotificationDate DESC LIMIT ? ) AS n)";
		
		try {
			// Insert new notification 
			statement = dbConnection.prepareStatement(query);
			statement.setInt(3, userID);
			statement.setInt(4, notificationID);
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
}