/**
 * This class provides an implementation of the {@link CharityInventoryDAO} interface to perform CRUD operations
 * related to charity inventory in the database.
 */
package dataaccesslayer;

import transferobjects.CharityInventoryDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * CharityInventoryDAOImpl class implements CharityInventoryDAO interface
 */
public class CharityInventoryDAOImpl implements CharityInventoryDAO {

    Connection con;

    /**
     * Constructs a new {@code CharityInventoryDAOImpl} object with the specified database connection.
     *
     * @param con the database connection
     */
    public CharityInventoryDAOImpl(Connection con) {
        this.con = con;
    }

    /**
     * Inserts a new charity inventory record into the database.
     *
     * @param charityInventory the charity inventory DTO object to be inserted
     * @throws SQLException if a database access error occurs
     */
    @Override
    public void insertCharityInventory(CharityInventoryDTO charityInventory) throws SQLException {
        String sql = "INSERT INTO charity_inventory (id, users_id, quantity, userInventory_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, charityInventory.getId());
            pstmt.setInt(2, charityInventory.getUsersId());
            pstmt.setString(3, charityInventory.getQuantity());
            pstmt.setInt(4, charityInventory.getUserInventoryId());
            pstmt.executeUpdate();
        }
    }

    /**
     * Retrieves a charity inventory record from the database by its ID.
     *
     * @param inventoryId the ID of the charity inventory record
     * @return the charity inventory DTO object if found, otherwise null
     */
    @Override
    public CharityInventoryDTO getCharityInventoryById(int inventoryId) {
        String sql = "SELECT * FROM charity_inventory WHERE id = ?";
        CharityInventoryDTO inventory = null;
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, inventoryId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                inventory = new CharityInventoryDTO();
                inventory.setId(rs.getInt("id"));
                inventory.setUsersId(rs.getInt("users_id"));
                inventory.setQuantity(rs.getString("quantity"));
                inventory.setUserInventoryId(rs.getInt("userInventory_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventory;
    }

    /**
     * Retrieves all charity inventory records from the database.
     *
     * @return a list of charity inventory DTO objects
     */
    @Override
    public List<CharityInventoryDTO> getAllCharityInventories() {
        List<CharityInventoryDTO> inventories = new ArrayList<>();
        String sql = "SELECT * FROM charity_inventory";
        try (PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                CharityInventoryDTO inventory = new CharityInventoryDTO();
                inventory.setId(rs.getInt("id"));
                inventory.setUsersId(rs.getInt("users_id"));
                inventory.setQuantity(rs.getString("quantity"));
                inventory.setUserInventoryId(rs.getInt("userInventory_id"));
                inventories.add(inventory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventories;
    }

    /**
     * Updates a charity inventory record in the database.
     *
     * @param charityInventory the charity inventory DTO object containing updated information
     */
    @Override
    public void updateCharityInventory(CharityInventoryDTO charityInventory) {
        String sql = "UPDATE charity_inventory SET users_id = ?, quantity = ?, userInventory_id = ? WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, charityInventory.getUsersId());
            pstmt.setString(2, charityInventory.getQuantity());
            pstmt.setInt(3, charityInventory.getUserInventoryId());
            pstmt.setInt(4, charityInventory.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a charity inventory record from the database by its ID.
     *
     * @param inventoryId the ID of the charity inventory record to be deleted
     */
    @Override
    public void deleteCharityInventory(int inventoryId) {
        String sql = "DELETE FROM charity_inventory WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, inventoryId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
