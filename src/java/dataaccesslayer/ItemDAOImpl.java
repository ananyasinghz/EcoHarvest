package dataaccesslayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import transferobjects.ItemDTO;

/**
 * ItemDAOImpl class implements ItemDAO interface
 */
public class ItemDAOImpl implements ItemDAO {

    Connection con;

    /**
     * Constructs a new {@code ItemDAOImpl} object with the specified database connection.
     * @param con the database connection
     */
    public ItemDAOImpl(Connection con) {
        this.con = con;
    }
    /**
     * Inserts a new item record into the database.
     * @param item the item DTO object to be inserted
     * @throws SQLException if a database access error occurs
     */
    @Override
    public void insertItem(ItemDTO item) throws SQLException {
        String sql = "INSERT INTO item (id, name, category) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, item.getId());
            pstmt.setString(2, item.getName());
            pstmt.setString(3, item.getCategory());
            pstmt.executeUpdate();
            System.out.println("Item inserted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to insert item");
        }
    }

    /**
     * Retrieves an item record from the database by its ID.
     * @param itemId the ID of the item record
     * @return the item DTO object if found, otherwise null
     */
    @Override
    public ItemDTO getItemById(int itemId) {
        String sql = "SELECT * FROM item WHERE id = ?";
        ItemDTO item = null;
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, itemId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    item = new ItemDTO();
                    item.setId(rs.getInt("id"));
                    item.setName(rs.getString("name"));
                    item.setCategory(rs.getString("category"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    /**
     * Retrieves all item records from the database.
     * @return a list of item DTO objects
     */
    @Override
    public List<ItemDTO> getAllItems() {
        List<ItemDTO> items = new ArrayList<>();
        String sql = "SELECT * FROM item";
        try (PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                ItemDTO item = new ItemDTO();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setCategory(rs.getString("category"));
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    /**
     * Updates an item record in the database.
     * @param item the item DTO object to be updated
     */
    @Override
    public void updateItem(ItemDTO item) {
        String sql = "UPDATE item SET name = ?, category = ? WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, item.getName());
            pstmt.setString(2, item.getCategory());
            pstmt.setInt(3, item.getId());
            pstmt.executeUpdate();
            System.out.println("Item updated successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes an item record from the database by its ID.
     * @param itemId the ID of the item record
     */
    @Override
    public void deleteItem(int itemId) {
        String sql = "DELETE FROM item WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, itemId);
            pstmt.executeUpdate();
            System.out.println("Item deleted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
