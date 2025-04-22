package transferobjects;

/**
 * CharityInventoryDTO is a data transfer object to hold the data for the CharityInventory table.
 */
public class CharityInventoryDTO {
    private int id;
    private int usersId;
    private String quantity;
    private int userInventoryId;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUsersId() {
        return usersId;
    }
    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }
    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    public int getUserInventoryId() {
        return userInventoryId;
    }
    public void setUserInventoryId(int userInventoryId) {
        this.userInventoryId = userInventoryId;
    }
}

