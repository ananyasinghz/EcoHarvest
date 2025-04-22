package controller;

import businesslayer.UserBusinessLogic;
import dataaccesslayer.DataSource;
import model.DTOBuilder;
import model.UserRegistration;
import transferobjects.UserDTO;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

/**
 * ValidateRegistrationServlet is a controller that handles the validation of user registration.
 */
@WebServlet(name = "ValidateRegistrationServlet", urlPatterns = {"/ValidateRegistrationServlet"})
public class ValidateRegistrationServlet extends HttpServlet {
    String name = null;
    String email;
    String password;
    String phone;
    String location;
    String role;
    String preference;
    Boolean subscribeToPhone;
    Boolean subscribeToEmail;
    //TODO we need to figure out what we're doing for all the registration forms and how we are going to validate them
    DTOBuilder builder = new DTOBuilder();
    DataSource dataSource;
    UserBusinessLogic userBusinessLogic;
    UserRegistration registration = new UserRegistration();

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        dataSource = new DataSource(context);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // We don't need a pre‑existing session here at all:
        String role = request.getParameter("role");
        name = request.getParameter("name");
        email = request.getParameter("email");
        password = request.getParameter("password");
        phone = request.getParameter("phone");
        preference = request.getParameter("preference");
        subscribeToPhone = Boolean.parseBoolean(request.getParameter("subscribeToPhone"));
        subscribeToEmail = Boolean.parseBoolean(request.getParameter("subscribeToMail"));

        if(Objects.equals(role, "Customer") || Objects.equals(role, "Charity")) {
            location = request.getParameter("selectedValue");
        } else{
            location = request.getParameter("location");
        }
        try (Connection connection = dataSource.getConnection()) {
            if (registration.userExists(name, connection)) {
                request.setAttribute("errorMessage", "Username already exists, please choose another username");
                request.getRequestDispatcher(registration.registrationErrorRedirect(role)).forward(request, response);
            }
            else if(registration.phoneValidation(phone) && Objects.equals(role, "Customer"))    {
                request.setAttribute("errorMessage", "Phone number must be 10 digits");
                request.getRequestDispatcher(registration.registrationErrorRedirect(role)).forward(request, response);
            }
            else{
                // Create a new UserDTO object
                UserDTO user = builder.userBuilder(name, password, role, email, phone, location, preference, subscribeToPhone, subscribeToEmail);
                userBusinessLogic = new UserBusinessLogic(connection);
                userBusinessLogic.addUser(user);

                response.sendRedirect("views/Signin.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}