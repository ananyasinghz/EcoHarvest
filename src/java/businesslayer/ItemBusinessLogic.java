package businesslayer;

import dataaccesslayer.ItemDAO;
import dataaccesslayer.ItemDAOImpl;
import transferobjects.ItemDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Business logic for the Item table.
 */
public class ItemBusinessLogic {

    private ItemDAO itemDao;

    /**
     * Constructs an ItemBusinessLogic object, initializing the DAO with the given database connection.
     *
     * @param con The database connection to be used by the DAO.
     */
    public ItemBusinessLogic(Connection con) {
        this.itemDao = new ItemDAOImpl(con);
    }

    /**
     * Adds a new item.
     *
     * @param item The item to add.
     * @throws SQLException If an error occurs during the database operation.
     */
    public void addItem(ItemDTO item) throws SQLException {
        itemDao.insertItem(item);
    }

    /**
     * Retrieves an item by its ID.
     *
     * @param itemId The ID of the item to retrieve.
     * @return The item with the specified ID, or null if not found.
     */
    public ItemDTO getItemById(int itemId) {
        return itemDao.getItemById(itemId);
    }

    /**
     * Retrieves all items.
     *
     * @return A list of all items.
     */
    public List<ItemDTO> getAllItems() {
        return itemDao.getAllItems();
    }

    /**
     * Updates an existing item.
     *
     * @param item The item with updated information.
     * @throws SQLException If an error occurs during the database operation.
     */
    public void updateItem(ItemDTO item) throws SQLException {
        itemDao.updateItem(item);
    }

    /**
     * Deletes an item by its ID.
     *
     * @param itemId The ID of the item to delete.
     * @throws SQLException If an error occurs during the database operation.
     */
    public void deleteItem(int itemId) throws SQLException {
        itemDao.deleteItem(itemId);
    }
}
