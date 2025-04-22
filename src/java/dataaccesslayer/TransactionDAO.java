package dataaccesslayer;

import java.sql.SQLException;
import java.util.List;
import transferobjects.TransactionDTO;

/**
 * TransactionDAO interface for TransactionDAOImpl
 */
public interface TransactionDAO {

    /**
     * Method to insert transaction
     * @param transaction TransactionDTO object
     * @throws SQLException throws SQLException
     */
    void insertTransaction(TransactionDTO transaction) throws SQLException;

    /**
     * Method to get transaction by id
     * @param transactionId String object
     * @return TransactionDTO object
     */
    TransactionDTO getTransactionById(String transactionId);

    /**
     * Method to get all transactions
     * @return List of TransactionDTO objects
     */
    List<TransactionDTO> getAllTransactions();

    /**
     * Method to update transaction
     * @param transaction TransactionDTO object
     */
    void updateTransaction(TransactionDTO transaction);

    /**
     * Method to delete transaction
     * @param transactionId String object
     */
    void deleteTransaction(String transactionId);
}

