package itserviceportal.model;
import java.io.*;
import java.util.Date;
import java.util.ArrayList;
import javax.sql.*;
import java.sql.*;
import javax.naming.InitialContext;

public class TicketDataAccess extends DataAccessLayer{

    public TicketDataAccess() {
        super();
    }

    public ArrayList<SupportTicket> getAllTicketsFromDB(User user, String categorySelect, String stateSelect, boolean knowledgeBase, String orderBy) throws SQLException {
        String query;
        if(orderBy.equals("newest"))
            query = "SELECT * FROM vw_SupportTickets WHERE CreatedByUserID LIKE ? AND CategoryName LIKE ? AND TicketState LIKE ? AND IsKnowledgeBase LIKE ? ORDER BY ReportedOn DESC";
        else
            query = "SELECT * FROM vw_SupportTickets WHERE CreatedByUserID LIKE ? AND CategoryName LIKE ? AND TicketState LIKE ? AND IsKnowledgeBase LIKE ? ORDER BY ReportedOn ASC";
    
        ArrayList<SupportTicket> ticketsList = new ArrayList<>();

        try
        {
            //Getting the DB connection, performing the query and getting the results
            statement = dbConnection.prepareStatement(query);
            System.out.println("Prepared statement");
            String[] filterValues = buildQueryStringValuesGetAllTickets(user, categorySelect, stateSelect, knowledgeBase, orderBy);
            System.out.println("Got filter values:");
            for(int i = 0; i < filterValues.length; i++)
            {
                System.out.println(filterValues[i]);
            }
            statement.setString(1, filterValues[0]);
            statement.setString(2, filterValues[1]);
            statement.setString(3, filterValues[2]);
            statement.setString(4, filterValues[3]);
            System.out.println("set statement values");
            System.out.println(statement.toString());
            results = statement.executeQuery();
            //Loop through the results set
            while (results.next())
            {

                //Create the support ticket and get the values from the results
                int id = results.getInt("TicketID");
                String title = results.getString("Title");
                String desc = results.getString("Descrip");
                String state = results.getString("TicketState");
                Date reportedOn = results.getDate("ReportedOn");
                Date resolvedOn = results.getDate("ResolvedOn");
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
                CommentDataAccess commentDAL = new CommentDataAccess();
                ArrayList<Comment> comments = commentDAL.getAllCommentsForTicket(id);


                //Creating the support ticket from the values retrieved from the query
                SupportTicket ticket = new SupportTicket(id, catName, state, title, desc, reportedOn, createdByUser, resolvedOn, resolvedByUser, isKnowledgeBase, resolutionDetails, comments);

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
}