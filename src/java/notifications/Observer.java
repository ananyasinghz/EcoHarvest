package notifications;

import transferobjects.RetailerInventoryDTO;
import transferobjects.UserDTO;

import java.sql.Connection;

/**
 * Observer interface for NotificationObserver
 */
public interface Observer {

    /**
     * Method to update observer
     * @param retailerInventory RetailerInventoryDTO object
     * @param connection Connection object
     */
    void update(RetailerInventoryDTO retailerInventory, Connection connection);

    /**
     * Method to set user preferences
     * @param phone String object
     */
    void setUserPreferences(String phone);
}
