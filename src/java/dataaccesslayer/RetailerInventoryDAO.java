package dataaccesslayer;

import java.sql.SQLException;
import java.util.List;
import transferobjects.RetailerInventoryDTO;

/**
 * RetailerInventoryDAO interface for RetailerInventoryDAOImpl
 */
public interface RetailerInventoryDAO {

    /**
     * Method to insert retailer inventory
     * @param retailerInventory RetailerInventoryDTO object
     * @throws SQLException throws SQLException
     */
    void insertRetailerInventory(RetailerInventoryDTO retailerInventory) throws SQLException;

    /**
     * Method to get retailer inventory by id
     * @param inventoryId int object
     * @return RetailerInventoryDTO object
     */
    RetailerInventoryDTO getRetailerInventoryById(int inventoryId);

    /**
     * Method to get all retailer inventories
     * @return List of RetailerInventoryDTO objects
     */
    List<RetailerInventoryDTO> getAllRetailerInventories();

    /**
     * Method to update retailer inventory
     * @param retailerInventory RetailerInventoryDTO object
     */
    void updateRetailerInventory(RetailerInventoryDTO retailerInventory);

    /**
     * Method to delete retailer inventory
     * @param inventoryId int object
     */
    void deleteRetailerInventory(int inventoryId);
}
