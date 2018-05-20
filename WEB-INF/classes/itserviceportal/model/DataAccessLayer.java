package itserviceportal.model;
import javax.sql.*;
import java.sql.*;
import javax.naming.InitialContext;

public class DataAccessLayer {

    protected Connection dbConnection;
    protected PreparedStatement statement;
    protected ResultSet results;

    public DataAccessLayer() {
        dbConnection = getConnectionToDB();
        statement = null;
        results = null;
    }

    public Connection getConnectionToDB() {
        try
        {
            InitialContext context = new InitialContext();
            DataSource ds = (DataSource)context.lookup("java:/comp/env/ServicePortalDB");
            return ds.getConnection();
        }
        catch (Exception e)
        {
            return null;
        }
    }

    protected void closeConnections() throws SQLException {
        if(dbConnection != null)
            dbConnection.close();
        else if (statement != null)
            statement.close();
        else if(results != null)
            statement.close();
    }
}

