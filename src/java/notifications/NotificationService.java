/**
 * This class represents a notification service responsible for registering observers,
 * removing observers, and notifying observers about updates in retailer inventory.
 */
package notifications;

import dataaccesslayer.DataSource;
import transferobjects.RetailerInventoryDTO;
import transferobjects.UserDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a notification service responsible for registering observers,
 * removing observers, and notifying observers about updates in retailer inventory.
 */
public class NotificationService implements Subject{
    private final List<Observer> observers = new ArrayList<>();
    String itemName;
    String itemCategory;


    /**
     * Registers an observer with the specified phone number.
     *
     * @param observer the observer to be registered
     * @param phone    the phone number of the user associated with the observer
     */
    @Override
    public void registerObserver(Observer observer, String phone) {
        observer.setUserPreferences(phone);
        observers.add(observer);

    }

    /**
     * Removes an observer from the list of registered observers.
     *
     * @param observer the observer to be removed
     */
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);

    }


    /**
     * Notifies all registered observers about updates in retailer inventory.
     *
     * @param retailerInventory the retailer inventory DTO object containing the updated information
     * @param connection        the database connection
     */
    @Override
    public void notifyObservers(RetailerInventoryDTO retailerInventory, Connection connection) {
        int itemId = retailerInventory.getItemId();

        // Retrieve the item category
        String inventoryItemQuery = "SELECT " +
                "category " + // Select only the category of the item
                "FROM item " +
                "WHERE id = " + itemId; // Filter by the specified ID

        try (Statement stmt = connection.createStatement();
             ResultSet rs2 = stmt.executeQuery(inventoryItemQuery)) {

            if (rs2.next()) {
                itemCategory = rs2.getString("category"); // Retrieve the category directly
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        String inventoryItemQuery2 = "SELECT " +
                "name " + // Select only the name of the item
                "FROM item " +
                "WHERE id = "+itemId; // Filter by the specified ID

        try (Statement stmt = connection.createStatement();
             ResultSet rs1 = stmt.executeQuery(inventoryItemQuery2)) {

            while (rs1.next()) {
                itemName = rs1.getString("name"); // Retrieve the name directly
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String userQuery = "SELECT " +
                "u.name AS user_name, " +
                "u.mail AS user_email, " +
                "u.phone AS user_phone, " +
                "u.subscribeToPhone, " + // Include subscribeToPhone column
                "u.subscribeToEmail " + // Include subscribeToEmail column
                "FROM " +
                "users u " +
                "WHERE " +
                "u.preference = '" + itemCategory + "' " + // Use itemCategory variable
                "AND (u.subscribeToPhone = 1 OR u.subscribeToEmail = 1)";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(userQuery)) {

            while (rs.next()) {
                String userName = rs.getString("user_name");
                String userEmail = rs.getString("user_email");
                String userPhone = rs.getString("user_phone");
                Boolean subscribeToPhone = rs.getBoolean("subscribeToPhone");
                Boolean subscribeToMail = rs.getBoolean("subscribeToEmail");

                // Print the information
                if (subscribeToPhone){
                    System.out.println("The item "+itemName+ " has been notified to the user : "+userName+" at the phone number "+userPhone);
                }
                if (subscribeToMail){
                    System.out.println("The item "+itemName+ " has been notified to the user : "+userName+" at the Mail "+userEmail);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
