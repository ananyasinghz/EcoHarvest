package transferobjects;

/**
 * This class is to create a data transfer object for the transaction table in the database
 */
public class TransactionDTO {
    private String transactionId;
    private int userInventoryId;
    private int usersId;
    private int quantity;

    public String getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    public int getUserInventoryId() {
        return userInventoryId;
    }
    public void setUserInventoryId(int userInventoryId) {
        this.userInventoryId = userInventoryId;
    }
    public int getUsersId() {
        return usersId;
    }
    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
