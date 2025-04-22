package dataaccesslayer;

import java.sql.SQLException;
import java.util.List;
import transferobjects.UserDTO;

/**
 * UserDAO interface for UserDAOImpl
 */
public interface UserDAO {

    /**
     * Method to insert user
     * @param user UserDTO object
     * @throws SQLException throws SQLException
     */
    public void insertUser(UserDTO user) throws SQLException;

    /**
     * Method to get user by id
     * @param userId int object
     * @return UserDTO object
     */
    public UserDTO getUserById(int userId);

    /**
     * Method to get all users
     * @return List of UserDTO objects
     */
    public List<UserDTO> getAllUsers();

    /**
     * Method to update user
     * @param user UserDTO object
     */
    public void updateUser(UserDTO user);

    /**
     * Method to delete user
     * @param userId int object
     */
    public void deleteUser(int userId);
}