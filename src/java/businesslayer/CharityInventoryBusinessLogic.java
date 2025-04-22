package businesslayer;

import dataaccesslayer.CharityInventoryDAO;
import dataaccesslayer.CharityInventoryDAOImpl;
import transferobjects.CharityInventoryDTO;
import java.sql.Connection;
import java.util.List;


/**
 * Business logic for the CharityInventory table.
 */
public class CharityInventoryBusinessLogic {

    private CharityInventoryDAO charityInventoryDao;



    /**
     * Constructs a CharityInventoryBusinessLogic object, initializing the DAO.
     *
     * @param con Database connection
     */
    public CharityInventoryBusinessLogic(Connection con) {
        this.charityInventoryDao = new CharityInventoryDAOImpl(con);

    }

    /**
     * Retrieves a charity inventory by its ID.
     *
     * @param inventoryId The ID of the inventory to retrieve.
     * @return The charity inventory with the specified ID.
     */
    public CharityInventoryDTO getCharityInventoryById(int inventoryId) {
        return charityInventoryDao.getCharityInventoryById(inventoryId);
    }

    /**
     * Retrieves all charity inventories.
     *
     * @return A list of all charity inventories.
     */
    public List<CharityInventoryDTO> getAllCharityInventories() {
        return charityInventoryDao.getAllCharityInventories();
    }

    /**
     * Adds a new charity inventory, after validating it.
     *
     * @param charityInventory : Charity inventory to add
     * @throws Exception If the inventory validation fails.
     */
    public void addCharityInventory(CharityInventoryDTO charityInventory) throws Exception {

        charityInventoryDao.insertCharityInventory(charityInventory);
    }

    /**
     * Updates an existing charity inventory, after validating it.
     *
     * @param charityInventory : Charity inventory to update
     * @throws Exception If the inventory validation fails.
     */
    public void updateCharityInventory(CharityInventoryDTO charityInventory) throws Exception {

        charityInventoryDao.updateCharityInventory(charityInventory);
    }

    /**
     * Deletes a charity inventory by its ID.
     *
     * @param inventoryId The ID of the inventory to delete.
     */
    public void deleteCharityInventory(int inventoryId) {
        charityInventoryDao.deleteCharityInventory(inventoryId);
    }
}
