package dataaccesslayer;

import transferobjects.ItemDTO;

import java.sql.SQLException;
import java.util.List;

/**
 * ItemDAO interface for ItemDAOImpl
 */
public interface ItemDAO {
    /**
     * Method to insert item
     * @param item ItemDTO object
     * @throws SQLException throws SQLException
     */
    void insertItem(ItemDTO item) throws SQLException;
    /**
     * Method to get item by id
     * @param itemId int object
     * @return ItemDTO object
     */
    ItemDTO getItemById(int itemId);
    /**
     * Method to get all items
     * @return List of ItemDTO objects
     */
    List<ItemDTO> getAllItems();
    /**
     * Method to update item
     * @param item ItemDTO object
     */
    void updateItem(ItemDTO item);
    /**
     * Method to delete item
     * @param itemId int object
     */
    void deleteItem(int itemId);
}