package itserviceportal.model;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.sql.*;

import itserviceportal.model.SupportTicket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;

public class TicketDataAccess extends DataAccessLayer{

    public TicketDataAccess() {
        super();
    }

    public List<SupportTicket> getAllTicketsFromDB() {
        String query = "SELECT * FROM vw_SupportTickets";
        List<SupportTicket> ticketsList = new ArrayList<>();

        try
        {
            //Getting the DB connection, performing the query and getting the results
            statement = dbConnection.prepareStatement(query);

            results = statement.executeQuery();

            //Loop through the results set
            while (results.next())
            {
                //Create the support ticket and get the values from the results
                SupportTicket ticket = new SupportTicket();
                int id = results.getInt("TicketID");
                /*String title = results.getString("Title");
                String desc = results.getString("Descrip");
                String state = results.getString("TicketState");
                Date reportedOn = results.getTimestamp("ReportedOn");
                Date resolvedOn = results.getTimestamp("ResolvedOn");*/
                
            }
            return null;
        }
        catch(Exception e)
        {
            return null;
        }
    }

}