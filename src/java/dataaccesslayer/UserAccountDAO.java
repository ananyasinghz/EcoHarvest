package dataaccesslayer;

import java.sql.SQLException;
import java.util.List;
import transferobjects.UserAccountDTO;

/**
 * UserAccountDAO interface for UserAccountDAOImpl
 */
public interface UserAccountDAO {

    /**
     * Method to insert user account
     * @param userAccount UserAccountDTO object
     * @throws SQLException throws SQLException
     */
    void insertUserAccount(UserAccountDTO userAccount) throws SQLException;

    /**
     * Method to get user account by id
     * @param accountId int object
     * @return UserAccountDTO object
     */
    UserAccountDTO getUserAccountById(int accountId);

    /**
     * Method to get all user accounts
     * @return List of UserAccountDTO objects
     */
    List<UserAccountDTO> getAllUserAccounts();

    /**
     * Method to update user account
     * @param userAccount UserAccountDTO object
     */
    void updateUserAccount(UserAccountDTO userAccount);

    /**
     * Method to delete user account
     * @param accountId int object
     */
    void deleteUserAccount(int accountId);
}
