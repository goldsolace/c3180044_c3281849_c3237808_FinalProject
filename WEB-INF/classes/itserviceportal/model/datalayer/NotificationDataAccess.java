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
		String query = "SELECT * FROM tbl_Notifications WHERE userID = ? ORDER BY NotificationDate ASC LIMIT ?";

		try {
			// Getting the DB connection, performing the query and getting the results
			statement = dbConnection.prepareStatement(query);
			statement.setInt(1, userID);
			statement.setInt(2, MAX_NOTIFICATIONS);
			results = statement.executeQuery();

			// Loop through the result set
			while(results.next()) {
				// Getting the column values
				int notificationID = results.getInt("NotificationID");
				String notificationAction = results.getString("NotificationAction");
				Date notificationDate = results.getDate("NotificationDate");
				int ticketID = results.getInt("TicketID");

				// Create the notification from the values
				Notification notification = new Notification(notificationID, Notification.Action.STARTWORK, notificationDate, userID, ticketID);

				// Add the motification to the list of motifications
				notifications.add(notification);
			}
			closeConnections();
			return notifications;
		} catch(Exception e) {
			e.printStackTrace();
			closeConnections();
			return null;
		}
	}

	public void setNotification(String text, int userID, int ticketID) throws SQLException {

		//Prepare Query
		String query = "INSERT INTO tbl_Notifications (NotificationText, NotificationDate, UserID, TicketID) VALUES (?, ?, ?, ?)";
		
		try {
			//Prepare Statement
			statement = dbConnection.prepareStatement(query);

			//Set Statement
			statement.setString(1, text);
			statement.setDate(2, new java.sql.Date(new Date().getTime()));
			statement.setInt(3, userID);
			statement.setInt(4, ticketID);

			//Execute Statement, adding notification to DB
			statement.executeUpdate();

			// Need to add deletion to only keep 5 latest here

			statement.close();

			closeConnections();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION CAUGHT: IssueDetailDataAccess -- newIssueDetails");
			closeConnections();
		}
	}

}