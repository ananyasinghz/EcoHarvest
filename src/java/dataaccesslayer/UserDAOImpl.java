package dataaccesslayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import transferobjects.UserDTO;

/**
 * UserDAOImpl class for UserDAO
 */
public class UserDAOImpl implements UserDAO {
    private Connection con;
    List<UserDTO> users = new ArrayList<>();

    /**
     * Constructor for UserDAOImpl
     * @param con Connection object
     */
    public UserDAOImpl(Connection con) {
        this.con = con;
    }

    /**
     * Method to insert user
     * @param user UserDTO object
     * @throws SQLException throws SQLException
     */
    @Override
    public void insertUser(UserDTO user) throws SQLException {
        String sql = "INSERT INTO users (id, name, password, subscribeToPhone, subscribeToEmail, location, role, phone, mail, preference) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, user.getId());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getPassword());
            pstmt.setBoolean(4, user.getSubscribeToPhone());
            pstmt.setBoolean(5, user.getSubscribeToEmail());
            pstmt.setString(6, user.getLocation());
            pstmt.setString(7, user.getRole());
            pstmt.setString(8, user.getPhone());
            pstmt.setString(9, user.getMail());
            pstmt.setString(10, user.getPreference());
            pstmt.executeUpdate();
            System.out.println("User inserted successfully");
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception SQLIntegrityConstraintViolation){
            System.out.println("User already in the database. Failed to insert.");
        }
    }

    /**
     * Method to get user by id
     * @param userId int object
     * @return UserDTO object
     */
    @Override
    public UserDTO getUserById(int userId) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    UserDTO user = new UserDTO();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setPassword(rs.getString("password"));
                    user.setSubscribeToPhone(rs.getBoolean("subscribeToPhone"));
                    user.setSubscribeToEmail(rs.getBoolean("subscribeToEmail"));
                    user.setLocation(rs.getString("location"));
                    user.setRole(rs.getString("role"));
                    user.setPhone(rs.getString("phone"));
                    user.setMail(rs.getString("mail"));
                    user.setPreference(rs.getString("preference"));
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method to get all users
     * @return List of UserDTO objects
     */
    @Override
    public List<UserDTO> getAllUsers() {

        String sql = "SELECT * FROM users";
        try (PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                UserDTO user = new UserDTO();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setSubscribeToPhone(rs.getBoolean("subscribeToPhone"));
                user.setSubscribeToEmail(rs.getBoolean("subscribeToEmail"));
                user.setLocation(rs.getString("location"));
                user.setRole(rs.getString("role"));
                user.setPhone(rs.getString("phone"));
                user.setMail(rs.getString("mail"));
                user.setPreference(rs.getString("preference"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * Method to update user
     * @param user UserDTO object
     */
    @Override
    public void updateUser(UserDTO user) {
        String sql = "UPDATE users SET name = ?, password = ?, subscribeToPhone = ?, subscribeToEmail = ?, location = ?, role = ?, phone = ?, mail = ?, preference = ? WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getPassword());
            pstmt.setBoolean(3, user.getSubscribeToPhone());
            pstmt.setBoolean(4, user.getSubscribeToEmail());
            pstmt.setString(5, user.getLocation());
            pstmt.setString(6, user.getRole());
            pstmt.setString(7, user.getPhone());
            pstmt.setString(8, user.getMail());
            pstmt.setString(9, user.getPreference());
            pstmt.setInt(10, user.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to delete user
     * @param userId int object
     */
    @Override
    public void deleteUser(int userId) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
