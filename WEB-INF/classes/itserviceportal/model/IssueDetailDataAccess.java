package itserviceportal.model;
import java.io.*;
import java.util.*;
import javax.sql.*;
import java.sql.*;
import javax.naming.InitialContext;

public class IssueDetailDataAccess extends DataAccessLayer{

	public IssueDetailDataAccess() {
		super();
	}

	public void newIssueDetails(int id, Map<String, String> issues) throws SQLException {

		//Prepare Query
		String query = "INSERT INTO tbl_IssueDetails (QuestionText, ResponseText, TicketID) VALUES (?, ?, ?)";
		
		try {
			//Prepare Statement
			statement = dbConnection.prepareStatement(query);

			//Cycle through hashmap, and add to DB
			for (Map.Entry<String, String> entry : issues.entrySet()) {
				statement.setString(1, entry.getKey());
				statement.setString(2, entry.getValue());
				statement.setInt(3, id);
				statement.executeUpdate();
			}
			closeConnections();
		
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION CAUGHT: IssueDetailDataAccess -- newIssueDetails");
			closeConnections();
		}
	}

	public ArrayList<IssueDetail> getAllIssueDetailsForTicket(int ticketID) throws SQLException {

		ArrayList<IssueDetail> issueDetails = new ArrayList<>();
		String query = "SELECT * FROM tbl_IssueDetails WHERE TicketID = ?";

		try {
			//Getting the DB connection, performing the query and getting the results
			statement = dbConnection.prepareStatement(query);
			statement.setString(1, Integer.toString(ticketID));
			results = statement.executeQuery();

			//Loop through the result set
			while (results.next()) {
				//Getting the column values from the table
				int id = results.getInt("IssueDetailsID");
				String question = results.getString("QuestionText");
				String response = results.getString("ResponseText");

				// Create the issueDetail from the values
				IssueDetail issueDetail = new IssueDetail(id, question, response);

				//Add the issueDetail to the list of issueDetails
				issueDetails.add(issueDetail);
			}
			closeConnections();
			return issueDetails;
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION CAUGHT: IssueDetailDataAccess -- getAllIssueDetailsForTicket()");
			closeConnections();
			return null;
		}
	}

}