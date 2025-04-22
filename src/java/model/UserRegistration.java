package model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * UserRegistration is a model class that handles the registration of a user.
 * @author aaronthomp
 */
public class UserRegistration {

    /**
     * Used to redirect user on from the Home page to either login page or register page
     * @param action the action to perform (the button clicked)
     * @return the page to redirect to
     */
    public String welcomePageRedirect(String action){
        if (action.equals("Log In")) {
            return "views/Signin.jsp";
        } else if (action.equals("Sign Up")) {
            return "views/register/role_selection.jsp";
        } else {
            throw new IllegalArgumentException("Error: Unexpected outcome");
            // if we have a different action, we can redirect to an error page
        }
    }

    /**
     * Used to redirect user based on role to the appropriate registration page
     * @param userType the role of the user
     * @return the page to redirect to
     */
    public String registrationType(String userType){
        return switch (userType) {
            case "Customer" -> "views/register/Customer_registration.jsp";
            case "Retailer" -> "views/register/Retailer_registration.jsp";
            case "Charity" -> "views/register/Charity_registration.jsp";
            default -> throw new IllegalArgumentException("Error: Unexpected user type");
        };
    }

    /**
     * Used to redirect user based on role to the appropriate registration error page
     * @param userType the role of the user
     * @return the page to redirect to
     */
    public String registrationErrorRedirect(String userType){
        return switch (userType) {
            case "Customer" -> "views/register/Customer_registration_error.jsp";
            case "Retailer" -> "views/register/Retailer_registration_error.jsp";
            case "Charity" -> "views/register/Charity_registration_error.jsp";
            default -> throw new IllegalArgumentException("Error: Unexpected user type");
        };
    }

    /**
     * checks to see if the user already exists
     * @param name takes in the name of the user
     * @param con takes in the db connection
     * @return true if the user exists, false otherwise
     */
    public boolean userExists(String name, Connection con) {
        String sql = "SELECT COUNT(*) FROM users WHERE name = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * checks to see if the phone number
     * @param phone the phone number to validate
     * @return true if the phone number is invalid, false otherwise
     */
    public boolean phoneValidation(String phone) {
        return !phone.matches("\\d{10}");
    }

}
