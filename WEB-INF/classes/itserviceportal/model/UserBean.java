package itserviceportal.model;
import java.lang.*;

public class UserBean {
    private int userID;
    private String email;
    private String firstName;
    private String lastName;
    private String contactNumber;
    private Role role;


    public UserBean() {
        userID = 0;
        email = "";
        firstName = "";
        lastName = "";
        contactNumber = "";
        role = Role.USER;
    }

    //GETTERS
    public int getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public Role getRole() {
        return role;
    }

    //SETTERS
    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setRole(String role) {
        if(role.equalsIgnoreCase("staff"))
        {
            this.role = Role.STAFF;
        }
        else{
            this.role = Role.USER;
        }
    }
}