package dataaccesslayer;

import transferobjects.UserAccountDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * UserAccountDAOImpl class for UserAccountDAO
 */
public class UserAccountDAOImpl implements UserAccountDAO {

    Connection con;

    /**
     * Constructor for UserAccountDAOImpl
     * @param con Connection object
     */
    public UserAccountDAOImpl(Connection con) {
        this.con = con;
        System.out.println("DEBUG - Connection received: " + con);
    }

    /**
     * Method to insert user account
     * @param userAccount UserAccountDTO object
     * @throws SQLException throws SQLException
     */
    @Override
    public void insertUserAccount(UserAccountDTO userAccount) throws SQLException {
        String sql = "INSERT INTO userAccount (balance, users_id) VALUES (?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setDouble(1, userAccount.getBalance());
            pstmt.setInt(2, userAccount.getUsersId());
            pstmt.executeUpdate();
            System.out.println("DEBUG - Inserted user account for user_id: " + userAccount.getUsersId());
        }
    }

    /**
     * Method to get user account by id
     * @param accountId int object
     * @return UserAccountDTO object
     */
    @Override
    public UserAccountDTO getUserAccountById(int accountId) {
        String sql = "SELECT * FROM userAccount WHERE users_id = ?";
        System.out.println("DEBUG - Executing query: " + sql + " with ID: " + accountId);
        UserAccountDTO account = null;
        
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, accountId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                // Create new account instance first
                account = new UserAccountDTO();
                account.setId(rs.getInt("id"));
                account.setBalance(rs.getDouble("balance"));
                account.setUsersId(rs.getInt("users_id"));
                System.out.println("DEBUG - Found account: ID=" + account.getId() + ", Balance=" + account.getBalance());
            } else {
                System.out.println("DEBUG - No account found for user ID: " + accountId);
            }
        } catch (SQLException e) {
            System.out.println("DEBUG - SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
        return account;
    }

    /**
     * Method to get all user accounts
     * @return List of UserAccountDTO objects
     */
    @Override
    public List<UserAccountDTO> getAllUserAccounts() {
        List<UserAccountDTO> accounts = new ArrayList<>();
        String sql = "SELECT * FROM userAccount";
        try (PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                UserAccountDTO account = new UserAccountDTO();
                account.setId(rs.getInt("id"));
                account.setBalance(rs.getDouble("balance"));
                account.setUsersId(rs.getInt("users_id"));
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    /**
     * Method to update user account
     * @param userAccount UserAccountDTO object
     */
    @Override
    public void updateUserAccount(UserAccountDTO userAccount) {
        String sql = "UPDATE userAccount SET balance = ?, users_id = ? WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setDouble(1, userAccount.getBalance());
            pstmt.setInt(2, userAccount.getUsersId());
            pstmt.setInt(3, userAccount.getId());
            pstmt.executeUpdate();
            System.out.println("DEBUG - Updated account with ID=" + userAccount.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to delete user account
     * @param accountId int object
     */
    @Override
    public void deleteUserAccount(int accountId) {
        String sql = "DELETE FROM userAccount WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, accountId);
            pstmt.executeUpdate();
            System.out.println("DEBUG - Deleted account with ID=" + accountId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
