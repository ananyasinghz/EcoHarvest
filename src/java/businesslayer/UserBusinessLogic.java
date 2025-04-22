package businesslayer;

import dataaccesslayer.UserDAO;
import dataaccesslayer.UserDAOImpl;
import transferobjects.UserDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * This class has the business logic for user management, interacting with the data access layer.
 */
public class UserBusinessLogic {


    private UserDAO userDao;

    /**
     * Constructor that initializes the UserBusinessLogic with a database connection.
     *
     * @param con The database connection to be used by the UserDAO.
     */
    public UserBusinessLogic(Connection con) {
        this.userDao = new UserDAOImpl(con);
    }

    /**
     * Adds a new user to the database.
     *
     * @param user The UserDTO object containing the user's data.
     * @throws SQLException If a database access error occurs or inserting the user fails.
     */
    public void addUser(UserDTO user) throws SQLException {
        userDao.insertUser(user);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return A UserDTO representing the retrieved user. Returns null if no user is found.
     */
    public UserDTO getUserById(int userId) {
        return userDao.getUserById(userId);
    }

    /**
     * Retrieves all users from the database.
     *
     * @return A List of UserDTO objects, each representing a user.
     */
    public List<UserDTO> getAllUsers() {
        return userDao.getAllUsers();
    }

    /**
     * Updates the details of an existing user in the database.
     *
     * @param user The UserDTO object containing the updated data for the user.
     * @throws SQLException If a database access error occurs or updating the user fails.
     */
    public void updateUser(UserDTO user) throws SQLException {
        userDao.updateUser(user);
    }

    /**
     * Deletes a user from the database based on their ID.
     *
     * @param userId The ID of the user to be deleted.
     * @throws SQLException If a database access error occurs or deleting the user fails.
     */
    public void deleteUser(int userId) throws SQLException {
        userDao.deleteUser(userId);
    }
}
