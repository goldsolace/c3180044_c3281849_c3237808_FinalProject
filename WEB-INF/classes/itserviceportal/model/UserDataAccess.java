package itserviceportal.model;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.sql.*;
import java.sql.*;
import javax.naming.InitialContext;

public class UserDataAccess extends DataAccessLayer{

    public UserDataAccess() {
        super();
    }

    public User loginUser(String username, String password) throws SQLException {

        String query = "SELECT * FROM tbl_User WHERE Email = ? AND UserPassword = ?";
        User user = null;
        try 
        {
            //Getting the DB connection, performing the query and getting the results
            statement = dbConnection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            results = statement.executeQuery();

            //Loop through the result set
            while (results.next())
            {
                //Create a user from the results
                user = new User();
                int id = results.getInt("UserID");
                String email = results.getString("Email");
                String firstName = results.getString("FirstName");
                String lastName = results.getString("LastName");
                String contactNumber = results.getString("ContactNum");
                String role = results.getString("UserRole");
                
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
            if(statement != null)
            {
                statement.close();
            }
            if(results != null)
            {
                results.close();
            }
            return null;
        }
    }
}