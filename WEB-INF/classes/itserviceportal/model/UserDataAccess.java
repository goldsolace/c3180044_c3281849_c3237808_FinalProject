package itserviceportal.model;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.sql.*;
import java.sql.*;
import javax.naming.InitialContext;

public class UserDataAccess {

    public UserDataAccess() {}

    public UserBean loginUser(String username, String password) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement selectUser = null;
        ResultSet results = null;
        String query = "SELECT * FROM tbl_User WHERE Email = ? AND Password = ?";
        UserBean user = null;
        try 
        {
            //Getting the DB connection, performing the query and getting the results
            dbConnection = getConnectToDB();
            selectUser = dbConnection.prepareStatement(query);
            selectUser.setString(1, username);
            selectUser.setString(2, password);
            results = selectUser.executeQuery();

            //Loop through the result set
            while (results.next())
            {
                //Create a user from the results
                user = new UserBean();
                int id = results.getInt("UserID");
                String email = results.getString("Email");
                String firstName = results.getString("FirstName");
                String lastName = results.getString("LastName");
                String contactNumber = results.getString("ContactNum");
                String role = results.getString("Role");
                
                user.setUserID(id);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setContactNumber(contactNumber);
                user.setRole(role);
            }
            results.close();
            return user;
        }
        //If any errors occurred close all connections and return null 
        catch (Exception e) 
        {
            if(dbConnection != null)
            {
                dbConnection.close();
            }
            if(selectUser != null)
            {
                selectUser.close();
            }
            if(results != null)
            {
                results.close();
            }
            return null;
        }
    }

    private Connection getConnectToDB() {
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
}