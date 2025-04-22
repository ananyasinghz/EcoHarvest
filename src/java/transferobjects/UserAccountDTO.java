package transferobjects;

/**
 * This class is to create a data transfer object for the user_account table in the database
 */
public class UserAccountDTO {
    private int id;
    private double balance;
    private int usersId;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public int getUsersId() {
        return usersId;
    }
    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }
}
