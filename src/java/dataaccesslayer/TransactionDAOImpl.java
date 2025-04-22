package dataaccesslayer;

import transferobjects.TransactionDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * TransactionDAOImpl class for TransactionDAO
 */
public class TransactionDAOImpl implements TransactionDAO {

    Connection con;

    /**
     * Constructor for TransactionDAOImpl
     * @param con Connection object
     */
    public TransactionDAOImpl(Connection con) {
        this.con = con;
    }

    /**
     * Method to insert transaction
     * @param transaction TransactionDTO object
     * @throws SQLException throws SQLException
     */
    @Override
    public void insertTransaction(TransactionDTO transaction) throws SQLException {
        String sql = "INSERT INTO transaction (transaction_id, userInventory_id, users_id, quantity) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, transaction.getTransactionId());
            pstmt.setInt(2, transaction.getUserInventoryId());
            pstmt.setInt(3, transaction.getUsersId());
            pstmt.setInt(4, transaction.getQuantity());
            pstmt.executeUpdate();
        }
    }

    /**
     * Method to get transaction by id
     * @param transactionId String object
     * @return TransactionDTO object
     */
    @Override
    public TransactionDTO getTransactionById(String transactionId) {
        String sql = "SELECT * FROM transaction WHERE transaction_id = ?";
        TransactionDTO transaction = null;
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, transactionId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                transaction = new TransactionDTO();
                transaction.setTransactionId(rs.getString("transaction_id"));
                transaction.setUserInventoryId(rs.getInt("userInventory_id"));
                transaction.setUsersId(rs.getInt("users_id"));
                transaction.setQuantity(rs.getInt("quantity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transaction;
    }

    /**
     * Method to get all transactions
     * @return List of TransactionDTO objects
     */
    @Override
    public List<TransactionDTO> getAllTransactions() {
        List<TransactionDTO> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transaction";
        try (PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                TransactionDTO transaction = new TransactionDTO();
                transaction.setTransactionId(rs.getString("transaction_id"));
                transaction.setUserInventoryId(rs.getInt("userInventory_id"));
                transaction.setUsersId(rs.getInt("users_id"));
                transaction.setQuantity(rs.getInt("quantity"));
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    /**
     * Method to update transaction
     * @param transaction TransactionDTO object
     */
    @Override
    public void updateTransaction(TransactionDTO transaction) {
        String sql = "UPDATE transaction SET userInventory_id = ?, users_id = ?, quantity = ? WHERE transaction_id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, transaction.getUserInventoryId());
            pstmt.setInt(2, transaction.getUsersId());
            pstmt.setInt(3, transaction.getQuantity());
            pstmt.setString(4, transaction.getTransactionId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to delete transaction
     * @param transactionId String object
     */
    @Override
    public void deleteTransaction(String transactionId) {
        String sql = "DELETE FROM transaction WHERE transaction_id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, transactionId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
