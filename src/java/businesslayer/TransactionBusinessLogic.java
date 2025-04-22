package businesslayer;

import dataaccesslayer.TransactionDAO;
import dataaccesslayer.TransactionDAOImpl;
import transferobjects.TransactionDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Business logic for the Transaction table.
 */
public class TransactionBusinessLogic {

    private TransactionDAO transactionDao;

    /**
     * Constructs a TransactionBusinessLogic object, initializing the DAO with the provided database connection.
     *
     * @param con The database connection to be used by the DAO.
     */
    public TransactionBusinessLogic(Connection con) {
        this.transactionDao = new TransactionDAOImpl(con);
    }

    /**
     * Adds a new transaction.
     *
     * @param transaction The transaction to add.
     * @throws SQLException If an error occurs during the database operation.
     */
    public void addTransaction(TransactionDTO transaction) throws SQLException {
        transactionDao.insertTransaction(transaction);
    }

    /**
     * Retrieves a transaction by its ID.
     *
     * @param transactionId The ID of the transaction to retrieve.
     * @return The transaction with the specified ID, or null if not found.
     */
    public TransactionDTO getTransactionById(String transactionId) {
        return transactionDao.getTransactionById(transactionId);
    }

    /**
     * Retrieves all transactions.
     *
     * @return A list of all transactions.
     */
    public List<TransactionDTO> getAllTransactions() {
        return transactionDao.getAllTransactions();
    }

    /**
     * Updates an existing transaction.
     *
     * @param transaction The transaction with updated information.
     * @throws SQLException If an error occurs during the database operation.
     */
    public void updateTransaction(TransactionDTO transaction) throws SQLException {
        transactionDao.updateTransaction(transaction);
    }

    /**
     * Deletes a transaction by its ID.
     *
     * @param transactionId The ID of the transaction to delete.
     * @throws SQLException If an error occurs during the database operation.
     */
    public void deleteTransaction(String transactionId) throws SQLException {
        transactionDao.deleteTransaction(transactionId);
    }
}
