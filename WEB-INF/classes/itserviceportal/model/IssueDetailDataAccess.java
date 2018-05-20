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
		String query = "INSERT INTO tbl_IssueDetails (QuestionText, Responsetext, TicketID) VALUES (?, ?, ?)";
		
		try{
		
		//Prepare Statement
		statement = dbConnection.prepareStatement(query);

		//Cycle through hashmap, and add to DB
        for (Map.Entry<String, String> entry : issues.entrySet())
		{
			statement.setString(1, entry.getKey());
			statement.setString(2, entry.getValue());
			statement.setInt(3, id);
			statement.executeUpdate();
		}
		closeConnections();
		
		}
		catch(Exception e)
        {
            System.out.println("EXCEPTION CAUGHT: IssueDetailDataAccess -- newIssueDetails");
            closeConnections();
        }
    }

}