package businesslayer;

import dataaccesslayer.UserAccountDAO;
import dataaccesslayer.UserAccountDAOImpl;
import transferobjects.UserAccountDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Business logic for the UserAccount table.
 */
public class UserAccountBusinessLogic {

    private UserAccountDAO userAccountDao;

    /**
     * Constructs a UserAccountBusinessLogic object, initializing the DAO with the given database connection.
     *
     * @param con The database connection to be used by the DAO.
     */
    public UserAccountBusinessLogic(Connection con) {
        this.userAccountDao = new UserAccountDAOImpl(con);
    }

    /**
     * Adds a new user account.
     *
     * @param userAccount The user account to add.
     * @throws SQLException If an error occurs during the database operation.
     */
    public void addUserAccount(UserAccountDTO userAccount) throws SQLException {
        userAccountDao.insertUserAccount(userAccount);
    }

    /**
     * Retrieves a user account by its ID.
     *
     * @param accountId The ID of the user account to retrieve.
     * @return The user account with the specified ID, or null if not found.
     */
    public UserAccountDTO getUserAccountById(int accountId) {
        return userAccountDao.getUserAccountById(accountId);
    }

    /**
     * Retrieves all user accounts.
     *
     * @return A list of all user accounts.
     */
    public List<UserAccountDTO> getAllUserAccounts() {
        return userAccountDao.getAllUserAccounts();
    }

    /**
     * Updates an existing user account.
     *
     * @param userAccount The user account with updated information.
     * @throws SQLException If an error occurs during the database operation.
     */
    public void updateUserAccount(UserAccountDTO userAccount) throws SQLException {
        userAccountDao.updateUserAccount(userAccount);
    }

    /**
     * Deletes a user account by its ID.
     *
     * @param accountId The ID of the user account to delete.
     * @throws SQLException If an error occurs during the database operation.
     */
    public void deleteUserAccount(int accountId) throws SQLException {
        userAccountDao.deleteUserAccount(accountId);
    }
}
