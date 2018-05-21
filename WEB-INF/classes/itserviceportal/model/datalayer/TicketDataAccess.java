package itserviceportal.model.datalayer;

import itserviceportal.model.beans.*;
import java.io.*;
import java.util.*;
import javax.sql.*;
import java.util.Date;

import java.sql.*;
import javax.naming.InitialContext;

public class TicketDataAccess extends DataAccessLayer{

	public TicketDataAccess() {
		super();
	}

	public void newTicket(User users, String category, String title, String description, Map<String, String> issueDetails) throws SQLException
	{
		
		//String Query
		String query = "INSERT INTO tbl_SupportTicket (Title, Descrip, ReportedOn, CreatedByUserID, CategoryID) VALUES (?, ?, ?, ?, ?)";
		
		try 
		{
			
		//Prepare Statement
		statement = dbConnection.prepareStatement(query);
		
		//Set Statement
		statement.setString(1, title);
		statement.setString(2, description);
		statement.setTimestamp(3, new Timestamp(new Date().getTime()));
		statement.setInt(4, users.getUserID());
		statement.setInt(5, getCategoryID(category));
		
		//Execute Statement, adding ticket to DB
		statement.executeUpdate();
		statement.close();
		
		//Creating statement to retrieve ticketID
		Statement statement = dbConnection.createStatement();
		query = "SELECT LAST_INSERT_ID()";
		ResultSet rs = statement.executeQuery(query);

		rs.next();
		int ticketID = rs.getInt("LAST_INSERT_ID()");
		closeConnections();
		
		//Add IssueDetails for ticket to DB
		IssueDetailDataAccess issueDetailDAL = new IssueDetailDataAccess();
		issueDetailDAL.newIssueDetails(ticketID, issueDetails);
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("EXCEPTION CAUGHT: TicketDataAccess -- newTicket");
			closeConnections();
		}
			
	}

	/**
	 * Gets all the tickets from the database matching the passed in filter parameters
	 *
	 * @param user the user getting the list of tickets
	 * @param categorySelect the category filter select (All, Network, Hardware etc)
	 * @param stateSelect The state filter selected (Completed, New etc)
	 * @param knowledgeBase value outlining to get all tickets or only knowledge base (true = knowledge base only, false = all tickets)
	 * @param orderBy the order by filter selected (newest or oldest)
	 * @throws SQLException
	 * @return ArrayList<SupportTicket>
	 */
	public ArrayList<SupportTicket> getAllTicketsFromDB(User user, String categorySelect, String stateSelect, boolean knowledgeBase, String orderBy) throws SQLException {
		
		//The list of tickets that will be returned
		ArrayList<SupportTicket> ticketsList = new ArrayList<>();
		
		String query;
		if(orderBy.equals("newest"))
			query = "SELECT * FROM vw_SupportTickets WHERE CreatedByUserID LIKE ? AND CategoryName LIKE ? AND TicketState LIKE ? AND IsKnowledgeBase LIKE ? ORDER BY ReportedOn DESC";
		else
			query = "SELECT * FROM vw_SupportTickets WHERE CreatedByUserID LIKE ? AND CategoryName LIKE ? AND TicketState LIKE ? AND IsKnowledgeBase LIKE ? ORDER BY ReportedOn ASC";

		try
		{
			//Getting the DB connection, performing the query and getting the results
			statement = dbConnection.prepareStatement(query);
			String[] filterValues = buildQueryStringValuesGetAllTickets(user, categorySelect, stateSelect, knowledgeBase, orderBy);

			//Prepare the query parameters
			statement.setString(1, filterValues[0]);
			statement.setString(2, filterValues[1]);
			statement.setString(3, filterValues[2]);
			statement.setString(4, filterValues[3]);

			//Execute the query and get the results
			results = statement.executeQuery();

			//Loop through the results set
			while (results.next())
			{
				SupportTicket ticket = supportTicketFactory(results);
				if(ticket != null)
					ticketsList.add(ticket);
			}
			closeConnections();
			return ticketsList;
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION CAUGHT: TicketDataAccess -- getAllTicketsFromDB()");
			closeConnections();
			return null;
		}
	}


	/**
	 * Gets all the tickets from the database matching the passed in filter parameters
	 *
	 * @param user the user getting the list of tickets
	 * @param categorySelect the category filter select (All, Network, Hardware etc)
	 * @param stateSelect The state filter selected (Completed, New etc)
	 * @param knowledgeBase value outlining to get all tickets or only knowledge base (true = knowledge base only, false = all tickets)
	 * @param orderBy the order by filter selected (newest or oldest)
	 * @throws SQLException
	 * @return SupportTicket object if successful, NULL if exception occured
	 */
	public SupportTicket getTicketByIDFromDB(int id) throws SQLException {
		
		SupportTicket ticket = null;
		String query = "SELECT * FROM vw_SupportTickets WHERE TicketID = ?;";

		try
		{
			//Getting the DB connection, performing the query and getting the results
			statement = dbConnection.prepareStatement(query);

			//Prepare the query parameters
			statement.setInt(1, id);

			//Execute the query and get the results
			results = statement.executeQuery();

			//Get the row and create the ticket object
			results.next();
			ticket = supportTicketFactory(results);
				
			closeConnections();
			return ticket;
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION CAUGHT: TicketDataAccess -- getAllTicketsFromDB()");
			closeConnections();
			return null;
		}
	}


	/**
	 * Creates a user object from the result set data.
	 *
	 * @param results the result set obtained from the database
	 * @return User
	 */
	private User getCreatedByUserFromResults(ResultSet results) throws SQLException {
		int id = results.getInt("CreatedByUserID");
		String email = results.getString("CreatedByEmail");
		String fName = results.getString("CreatedByFName");
		String lName = results.getString("CreatedByLName");
		String contactNum = results.getString("CreatedByContactNum");
		String role = results.getString("CreatedByRole");
		User user = new User(id, email, fName, lName, contactNum, role);
		return user;
	}


	/**
	 * Creates a user object from the result set data.
	 *
	 * @param results the result set obtained from the database
	 * @return User
	 */
	private User getResolvedByUserFromResults(ResultSet results) throws SQLException {
		int id = results.getInt("ResolvedByUserID");
		String email = results.getString("ResolvedByEmail");
		String fName = results.getString("ResolvedByFName");
		String lName = results.getString("ResolvedByLName");
		String contactNum = results.getString("ResolvedByContactNum");
		String role = results.getString("ResolvedByRole");
		User user = new User(id, email, fName, lName, contactNum, role);
		return user;
	}


	/**
	 * Makes the query string values which will be passed into the select statement in order to perform filtering.
	 *
	 * @param user the user getting the list of tickets
	 * @param categorySelect the category filter select (All, Network, Hardware etc)
	 * @param stateSelect The state filter selected (Completed, New etc)
	 * @param knowledgeBase value outlining to get all tickets or only knowledge base (true = knowledge base only, false = all tickets)
	 * @param orderBy the order by filter selected (newest or oldest)
	 * @return String[] values will be an SQL wildcard '%' if matching "ALL" criteria for the filter, otherwise, the value passed in will remain
	 */
	private String[] buildQueryStringValuesGetAllTickets(User user, String categorySelect, String stateSelect, boolean knowledgeBase, String orderBy) {
		String[] queryValues = new String[4];

		//If the role is just a user, they can only view the tickets they have made
		if(user.getRole() == Role.USER)
			queryValues[0] = Integer.toString(user.getUserID());

		//Otherwise, the user is staff member so display everything
		else
			queryValues[0] = "%";


		//If the state selected is all, use a wildcard to find everything
		if(stateSelect.equals("all"))
			queryValues[1] = "%";
		else
			queryValues[1] = stateSelect;



		//If the categorySelected is "all" then use a wildcard to find everything
		if(categorySelect.equals("all"))
			queryValues[2] = "%";
		//Otherwise, use the valids selected
		else
			queryValues[2] = categorySelect;


		//if not knowledge base find everything, otherwise, find only knowledgebase tickets
		if(!knowledgeBase)
			queryValues[3] = "%";
		else
			queryValues[3] = "1";

		return queryValues;
	}


	/**
	 * Creates a SupportTicket object from the result set passed in.
	 *
	 * @param results the results set obtained from the database
	 * @return SupportTicket if created successfully, NULL if exception occured
	 */
	private SupportTicket supportTicketFactory(ResultSet results) {
		try
		{
			//Create the support ticket and get the values from the results
			int id = results.getInt("TicketID");
			String title = results.getString("Title");
			String desc = results.getString("Descrip");
			String state = results.getString("TicketState");
			Date reportedOn = null;
			Timestamp ts = results.getTimestamp("ReportedOn");
			if (ts != null) {
				reportedOn = new Date(ts.getTime());
			}
			Date resolvedOn = null;
			ts = results.getTimestamp("ResolvedOn");
			if (ts != null) {
				resolvedOn = new Date(ts.getTime());
			}
			Boolean isKnowledgeBase = results.getBoolean("IsKnowledgeBase");
			String resolutionDetails = results.getString("ResolutionDetails");
			int catID = results.getInt("CategoryID");
			String catName = results.getString("CategoryName");

			//Getting the created by user values from the query and creating the user object
			User createdByUser = getCreatedByUserFromResults(results);

			//Getting the resolved by user values from the query
			User resolvedByUser = null;

			//If the resolvedOn date is not null then there is a resolved by user information
			if(resolvedOn != null)
				resolvedByUser = getResolvedByUserFromResults(results);

			//Calling the comments data access to get all the comments for this ticket
			IssueDetailDataAccess issueDetailDAL = new IssueDetailDataAccess();
			ArrayList<IssueDetail> issueDetails = issueDetailDAL.getAllIssueDetailsForTicket(id);

			//Calling the comments data access to get all the comments for this ticket
			CommentDataAccess commentDAL = new CommentDataAccess();
			ArrayList<Comment> comments = commentDAL.getAllCommentsForTicket(id);

			//Creating the support ticket from the values retrieved from the query
			SupportTicket ticket = new SupportTicket(id, catName, state, title, desc, reportedOn, createdByUser, resolvedOn, resolvedByUser, isKnowledgeBase, resolutionDetails, issueDetails, comments);

			return ticket;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public int getCategoryID(String category)
	{
		switch (category) { 
			case "network": return 1;
			case "software": return 2;
			case "hardware": return 3;
			case "email": return 4;
			case "account": return 5;
			default: return 0;
		}
	}
}