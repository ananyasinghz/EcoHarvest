package dataaccesslayer;

import java.sql.SQLException;
import java.util.List;
import transferobjects.CharityInventoryDTO;

/**
 * CharityInventoryDAO interface for CharityInventoryDAOImpl
 */
public interface CharityInventoryDAO {

    /**
     * Method to insert charity inventory
     * @param charityInventory CharityInventoryDTO object
     * @throws SQLException throws SQLException
     */
    void insertCharityInventory(CharityInventoryDTO charityInventory) throws SQLException;

    /**
     * Method to get charity inventory by id
     * @param inventoryId int object
     * @return CharityInventoryDTO object
     */
    CharityInventoryDTO getCharityInventoryById(int inventoryId);
    /**
     * Method to get all charity inventories
     * @return List of CharityInventoryDTO objects
     */
    List<CharityInventoryDTO> getAllCharityInventories();
    /**
     * Method to update charity inventory
     * @param charityInventory CharityInventoryDTO object
     */
    void updateCharityInventory(CharityInventoryDTO charityInventory);
    /**
     * Method to delete charity inventory
     * @param inventoryId int object
     */
    void deleteCharityInventory(int inventoryId);
}
