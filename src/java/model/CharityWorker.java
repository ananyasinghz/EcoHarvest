package model;

import transferobjects.InventoryItemDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * CharityWorker is a model class that handles the claiming of items by a charity.
 */
public class CharityWorker {

    /**
     * Displays the items that are available for charity claims.
     * @param connection takes in a db connection
     * @return returns the list of items to display
     */
    public List<InventoryItemDTO> displayCharityClaims(Connection connection) {
        String query = "SELECT i.name, i.category, r.expiry_date, r.quantity, r.id "
                + "FROM item i "
                + "JOIN retailer_inventory r ON i.id = r.item_id "
                + "WHERE r.quantity > 0 AND r.donation = 1";
        List<InventoryItemDTO> items = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                InventoryItemDTO item = new InventoryItemDTO();
                item.setName(rs.getString("name"));
                item.setCategory(rs.getString("category"));
                item.setExpiryDate(rs.getDate("expiry_date"));
                item.setQuantity(rs.getInt("quantity"));
                item.setId(rs.getInt("id"));
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    /**
     * Query method to claim an item
     * @param connection takes in db connection
     * @param itemId takes in the item id
     * @param claimedQuantity takes in the claimed quantity by the charity
     * @return returns true if the claim was successful, false otherwise
     * @throws SQLException if database error occurs
     */
    public boolean claimItem(Connection connection, int itemId, int claimedQuantity) throws SQLException {
        boolean claimSuccessful;
        connection.setAutoCommit(false);
        String updateQuery = "UPDATE retailer_inventory SET quantity = quantity - ? WHERE id = ? AND quantity >= ?";

        try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
            updateStmt.setInt(1, claimedQuantity);
            updateStmt.setInt(2, itemId);
            updateStmt.setInt(3, claimedQuantity);

            int affectedRows = updateStmt.executeUpdate();
            if (affectedRows > 0) {
                connection.commit();
                claimSuccessful = true;
            } else {
                connection.rollback();
                claimSuccessful = false;
            }
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
        return claimSuccessful;
    }

    /**
     * checks if the quantity of an item is zero and deletes it from the database if so.
     * @param connection takes in db connection.
     * @param itemId takes in the item id.
     * @throws SQLException if database error occurs.
     */
    public void deleteItemIfQuantityZero(Connection connection, int itemId) throws SQLException {
        String sql = "SELECT quantity FROM retailer_inventory WHERE id = ? AND quantity = 0";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, itemId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String sql2 = "DELETE FROM retailer_inventory WHERE id = ?";
                    try (PreparedStatement pstmt2 = connection.prepareStatement(sql2)) {
                        pstmt2.setInt(1, itemId);
                        pstmt2.executeUpdate();
                    }
                }
            }
        }
    }

}
