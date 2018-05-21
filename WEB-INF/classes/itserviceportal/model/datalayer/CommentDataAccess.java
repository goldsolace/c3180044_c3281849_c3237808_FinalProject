package itserviceportal.model.datalayer;

import itserviceportal.model.beans.*;
import java.io.*;
import java.util.Date;
import java.util.ArrayList;
import javax.sql.*;
import java.sql.*;
import javax.naming.InitialContext;

public class CommentDataAccess extends DataAccessLayer{

	public CommentDataAccess() {
		super();
	}

	public ArrayList<Comment> getAllCommentsForTicket(int ticketID) throws SQLException {

		ArrayList<Comment> comments = new ArrayList<>();
		String query = "SELECT * FROM vw_Comments WHERE TicketID = ?";

		try
		{
			//Getting the DB connection, performing the query and getting the results
			statement = dbConnection.prepareStatement(query);
			statement.setString(1, Integer.toString(ticketID));
			results = statement.executeQuery();

			//Loop through the result set
			while(results.next())
			{
				//Getting the column values from the view
				int id = results.getInt("CommentID");
				String text = results.getString("CommentText");
				Date date = results.getDate("CommentDate");
				int userID = results.getInt("UserID");
				String fName = results.getString("FirstName");
				String lName = results.getString("LastName");
				String email = results.getString("Email");
				String num = results.getString("ContactNum");
				String role = results.getString("UserRole");

				//Create the user from the values
				User user = new User(userID, email, fName, lName, num, role);

				//Create the comment from the values
				Comment comment = new Comment(id, text, date, user);

				//Add the comment to the list of comments
				comments.add(comment);
			}
			closeConnections();
			return comments;
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION CAUGHT: CommentDataAccess -- getAllCommentsForTicket()");
			closeConnections();
			return null;
		}
	}

}