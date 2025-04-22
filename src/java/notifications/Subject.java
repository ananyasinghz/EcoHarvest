package notifications;

import transferobjects.RetailerInventoryDTO;
import transferobjects.UserDTO;

import java.sql.Connection;

/**
 * Observer interface for NotificationObserver
 */
public interface Subject {

    /**
     * Method to register observer
     * @param observer Observer object
     * @param phone String object
     */
    void registerObserver(Observer observer, String phone);

    /**
     * Method to remove observer
     * @param observer Observer object
     */
    void removeObserver(Observer observer);

    /**
     * Method to notify observers
     * @param retailerInventory RetailerInventoryDTO object
     * @param connection Connection object
     */
    void notifyObservers(RetailerInventoryDTO retailerInventory, Connection connection);
}