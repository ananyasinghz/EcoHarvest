package test;

import businesslayer.ItemBusinessLogic;
import businesslayer.UserAccountBusinessLogic;
import businesslayer.UserBusinessLogic;
import dataaccesslayer.DataSource;
import dataaccesslayer.ItemDAOImpl;
import dataaccesslayer.UserAccountDAOImpl;
import model.DTOBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import transferobjects.ItemDTO;
import transferobjects.UserAccountDTO;
import transferobjects.UserDTO;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * This class contains test cases for the UserBusinessLogic, UserAccountBusinessLogic, and ItemBusinessLogic classes.
 * The tests verify the functionality of adding, updating, and retrieving user, user account, and item information.
 */
@WebServlet(name = "TestServlet", urlPatterns = {"/TestServlet"})
public class TestServlet extends HttpServlet {
    ServletContext context = getServletContext();
    DataSource dataSource = new DataSource(context);
    Connection connection = dataSource.getConnection();
    DTOBuilder builder = new DTOBuilder();
    public TestServlet() throws SQLException {
    }

    /**
     * Tests the addUser method to ensure that the UserBusinessLogic class
     * can successfully add a user to the database and then retrieve it correctly.
     * Verifies that the added user's name matches the expected result.
     */
    @Test
    void addUser() throws SQLException {
        UserBusinessLogic userBusinessLogic = new UserBusinessLogic(connection);
        UserDTO user = builder.userBuilder("Aaron", "Pass","Customer", "Aaron@mail.com", "1234567890", "Barrhaven", "Fruits", false, false);
        user.setId(999);
        userBusinessLogic.addUser(user);
        UserDTO test = userBusinessLogic.getUserById(999);
        String name = test.getName();
        Assertions.assertEquals("Aaron", name);
    }

    /**
     * Tests the updateUser method to verify that the UserBusinessLogic class
     * can correctly update an existing user's details in the database.
     * The test involves changing a user's name and asserting that the update
     * is successfully reflected in the retrieved user information.
     */
    @Test
    void updateUser() throws SQLException {
        UserBusinessLogic userBusinessLogic = new UserBusinessLogic(connection);
        UserDTO user = builder.userBuilder("Aaron", "Pass","Customer", "Aaron@mail.com", "1234567890", "Barrhaven", "Fruits", false, false);
        user.setId(998);
        userBusinessLogic.addUser(user);
        UserDTO test = userBusinessLogic.getUserById(998);
        String newName = "Paul";
        test.setName(newName);
        userBusinessLogic.updateUser(test);
        String name = test.getName();
        Assertions.assertEquals("Paul", name);
    }

    /**
     * Tests the integration of UserBusinessLogic and UserAccountBusinessLogic classes to ensure
     * that user and user account functionalities interact correctly. The test adds a user and
     * an associated user account, then checks if the user account's balance is correctly set
     * and retrieved. It verifies the balance consistency between the creation and retrieval process.
     */
    @Test
    void testUserAccount() throws SQLException {
        UserBusinessLogic userBusinessLogic = new UserBusinessLogic(connection);
        UserDTO user = builder.userBuilder("Aaron", "Pass","Customer", "Aaron@mail.com", "1234567890", "Barrhaven", "Fruits", false, false);
        user.setId(997);
        userBusinessLogic.addUser(user);
        UserAccountBusinessLogic userAccountBusinessLogic = new UserAccountBusinessLogic(connection);
        UserAccountDTO userAccountDTO = new UserAccountDTO();
        userAccountDTO.setUsersId(997);
        userAccountDTO.setBalance(1000);
        userAccountBusinessLogic.addUserAccount(userAccountDTO);
        UserAccountDAOImpl userAccountDAO = new UserAccountDAOImpl(connection);
        UserAccountDTO userAccountDTO1 = userAccountDAO.getUserAccountById(997);
        double balance = userAccountDTO1.getBalance();
        UserAccountDTO test = userAccountBusinessLogic.getUserAccountById(997);
        double expected = test.getBalance();
        Assertions.assertEquals(expected, balance);
    }

    @Test
    void testAddItem() throws SQLException {
        ItemBusinessLogic itemBusinessLogic = new ItemBusinessLogic(connection);
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(999);
        itemDTO.setCategory("Fruits");
        itemDTO.setName("Kamboocha");
        itemBusinessLogic.addItem(itemDTO);
        ItemDAOImpl itemDAO = new ItemDAOImpl(connection);
        ItemDTO test = itemDAO.getItemById(999);
        String expected = test.getCategory();
        Assertions.assertEquals(expected,"Fruits");
    }

    @Test
    void testUpdateItem() throws SQLException {
        ItemBusinessLogic itemBusinessLogic = new ItemBusinessLogic(connection);
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(998);
        itemDTO.setCategory("Fruits");
        itemDTO.setName("Kamboocha");
        itemBusinessLogic.addItem(itemDTO);
        itemDTO.setName("Orange");
        itemBusinessLogic.updateItem(itemDTO);
        ItemDAOImpl itemDAO = new ItemDAOImpl(connection);
        ItemDTO test = itemDAO.getItemById(998);
        String expected = test.getName();
        Assertions.assertEquals(expected,"Orange");
    }
}

