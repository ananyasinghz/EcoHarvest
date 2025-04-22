package businesslayer;

import dataaccesslayer.RetailerInventoryDAO;
import dataaccesslayer.RetailerInventoryDAOImpl;
import transferobjects.RetailerInventoryDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Business logic for the RetailerInventory table.
 */
public class RetailerInventoryBusinessLogic {

    private RetailerInventoryDAO retailerInventoryDao;

    /**
     * Constructs a RetailerInventoryBusinessLogic object, initializing the DAO with the given database connection.
     *
     * @param con The database connection to be used by the DAO.
     */
    public RetailerInventoryBusinessLogic(Connection con) {
        this.retailerInventoryDao = new RetailerInventoryDAOImpl(con);
    }

    /**
     * Adds a new retailer inventory.
     *
     * @param retailerInventory The retailer inventory to add.
     * @throws SQLException If an error occurs during the database operation.
     */
    public void addRetailerInventory(RetailerInventoryDTO retailerInventory) throws SQLException {
        retailerInventoryDao.insertRetailerInventory(retailerInventory);
    }

    /**
     * Retrieves a retailer inventory by its ID.
     *
     * @param inventoryId The ID of the retailer inventory to retrieve.
     * @return The retailer inventory with the specified ID, or null if not found.
     */
    public RetailerInventoryDTO getRetailerInventoryById(int inventoryId) {
        return retailerInventoryDao.getRetailerInventoryById(inventoryId);
    }

    /**
     * Retrieves all retailer inventories.
     *
     * @return A list of all retailer inventories.
     */
    public List<RetailerInventoryDTO> getAllRetailerInventories() {
        return retailerInventoryDao.getAllRetailerInventories();
    }

    /**
     * Updates an existing retailer inventory.
     *
     * @param retailerInventory The retailer inventory with updated information.
     * @throws SQLException If an error occurs during the database operation.
     */
    public void updateRetailerInventory(RetailerInventoryDTO retailerInventory) throws SQLException {
        retailerInventoryDao.updateRetailerInventory(retailerInventory);
    }

    /**
     * Deletes a retailer inventory by its ID.
     *
     * @param inventoryId The ID of the retailer inventory to delete.
     * @throws SQLException If an error occurs during the database operation.
     */
    public void deleteRetailerInventory(int inventoryId) throws SQLException {
        retailerInventoryDao.deleteRetailerInventory(inventoryId);
    }
}
