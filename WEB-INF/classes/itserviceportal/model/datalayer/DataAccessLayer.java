package itserviceportal.model.datalayer;
import itserviceportal.model.beans.*;
import javax.sql.*;
import java.sql.*;
import javax.naming.InitialContext;

/**
 * DataAccessLayer.java
 * The database access class. Is a Super class which has the following sub classes:
 * CommentDataAccess.java, IssueDetailDataAccess.java, NotificationDataAccess.java, 
 * TicketDataAccess.java and UserDataAccess.java
 *
 * @author Brice Purton, Jonathan Williams, Wajdi Yournes
 * @version 1.0
 * @since 19-05-2018
 */

public class DataAccessLayer {

    protected Connection dbConnection;      //The connection to the MySQL database
    protected PreparedStatement statement;  //A perpared statement object which is used to prepare querys/updates/insert
    protected ResultSet results;            //The result set object for processing data obtained from a SELECT query


  /**
   * Class constructor, calling a method to get the connection from the context.xml
   */
    public DataAccessLayer() {
        dbConnection = getConnectionToDB();
        statement = null;
        results = null;
    }




  /**
   * This method gets the MySQL database connection from the resource found in the context.xml
   * 
   * @return Connection object if resource is found successfully
   * @return null if exception occurs
   */
    public Connection getConnectionToDB() {
        try
        {
            //Lookup the resource in the context.xml by name and get the connection from the data source
            InitialContext context = new InitialContext();
            DataSource ds = (DataSource)context.lookup("java:/comp/env/ServicePortalDB");
            return ds.getConnection();
        }
        catch (Exception e)
        {
            return null;
        }
    }



  /**
   * This method closes all open connections to the MySQL database.
   * If the member varaibles are not null, close the connections
   * 
   * @throws SQLException
   */
    protected void closeConnections() throws SQLException {
        if(dbConnection != null)
            dbConnection.close();
        else if (statement != null)
            statement.close();
        else if(results != null)
            statement.close();
    }
}

