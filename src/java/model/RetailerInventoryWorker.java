package model;

import transferobjects.InventoryItemDTO;
import transferobjects.ItemDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * RetailerInventoryWorker is a model class that handles the inventory of a retailer.
 */
public class RetailerInventoryWorker {

    int NOT_FOR_SALE = 0;
    int ON_SALE = 1;
    int FOR_DONATION = 2;
    double salePercent = 0.3;

    /**
     * Validates if the product is within automatic sale date
     * @param expiryDate the expiry date of the product
     * @return true if the product is within 7 days of expiry, false otherwise
     */
    public boolean saleDateValidation(Date expiryDate) {
        Date currentDate = new Date();

        long differenceInMillis = expiryDate.getTime() - currentDate.getTime();
        long daysDifference = differenceInMillis / (1000 * 60 * 60 * 24);

        return daysDifference <= 7;
    }

    /**
     * Validates if the product is within automatic donation date
     * @param expiryDate the expiry date of the product
     * @return true if the product is within 3 days of expiry, false otherwise
     */
    public boolean isDonation(Date expiryDate){
        Date currentDate = new Date();
        long differenceInMillis = expiryDate.getTime() - currentDate.getTime();
        long daysDifference = differenceInMillis / (1000 * 60 * 60 * 24);
        return daysDifference <= 3;
    }

    /**
     * Determines the status of the product
     * @param expiryDate the expiry date of the product
     * @return the status of the product
     */
    public int productStatus(Date expiryDate){
        if (isDonation(expiryDate)){
            return FOR_DONATION;
        } else if (saleDateValidation(expiryDate)){
            return ON_SALE;
        } else {
            return NOT_FOR_SALE;
        }
    }

    /**
     * Calculates the price of the product and returns either the unit price or a rounded final price
     * @param status the status of the product
     * @param unitPrice the unit price of the product
     * @return the price of the product
     */
    public double productPrice(int status, double unitPrice) {
        if (status == ON_SALE){
            return Math.round((unitPrice - (unitPrice * salePercent)) * 100.0) / 100.0;
        } else {
            return unitPrice;
        }
    }


    /**
     * Checks if the product already exists in the retailer's inventory by checking the name and
     * batch number from a specific retailer
     * @param connection the connection to the database
     * @param retailerId the ID of the retailer
     * @param itemName the name of the product
     * @param batchNum the batch number of the product
     * @return true if the product already exists, false otherwise
     */
    public boolean productAlreadyExists(Connection connection, int retailerId, String itemName, int batchNum) {
        String query = "SELECT * FROM item AS i " +
                "JOIN retailer_inventory AS ri ON i.id = ri.item_id " +
                "WHERE ri.users_id = ? AND i.name = ? AND ri.batch = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, retailerId);
            statement.setString(2, itemName);
            statement.setInt(3, batchNum);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // If a row is found, it means the product exists for the retailer
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Inserts a new product into the retailer's inventory and gets the auto incremented id
     * @param connection the connection to the database
     * @param item the item to insert
     * @return the auto incremented id of the inserted product
     * @throws SQLException if an error occurs during the insert
     */
    public int insertAndGetGeneratedId(Connection connection, ItemDTO item) throws SQLException {
        String insertQuery = "INSERT INTO item VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, item.getId());
            pstmt.setString(2, item.getName());
            pstmt.setString(3, item.getCategory());
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) { // this line was ChatGPT'd because i didnt know how to get this automatically
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1; // return -1 if no generated key is retrieved
    }

    /**
     * retrieves the inventory to display for the retailer
     * @param connection the connection to the database
     * @param retailerId the ID of the retailer
     * @return the list of inventory items to display
     */
    public List<InventoryItemDTO> retrieveInventory(Connection connection, int retailerId) {
        List<InventoryItemDTO> inventory = new ArrayList<>();
        String query = "SELECT i.name, i.category, ri.quantity, ri.expiry_date, ri.final_price, ri.sale, ri.donation " +
                "FROM item i " +
                "JOIN retailer_inventory ri ON i.id = ri.item_id " +
                "WHERE ri.users_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, retailerId); // Set the retailer ID parameter
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String category = resultSet.getString("category");
                int quantity = resultSet.getInt("quantity");
                Date expiryDate = resultSet.getDate("expiry_date");
                double finalPrice = resultSet.getDouble("final_price");
                boolean sale = resultSet.getBoolean("sale");
                boolean donation = resultSet.getBoolean("donation");
                InventoryItemDTO item = new InventoryItemDTO(name, category, quantity, expiryDate, finalPrice, sale, donation);
                inventory.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventory;
    }

    /**
     * Updates the quantity of a product in the retailer's inventory
     * @param connection the connection to the database
     * @param retailerId the ID of the retailer
     * @param itemName the name of the product
     * @param batchNum the batch number of the product
     * @param newQuantity the new quantity to set
     * @param sale the sale status of the product
     * @param donation the donation status of the product
     */
    public void updateQuantity(Connection connection, int retailerId, String itemName, int batchNum, int newQuantity, boolean sale, boolean donation) {
        String sql = "UPDATE retailer_inventory ri " +
                "JOIN item i ON ri.item_id = i.id " +
                "SET ri.quantity = ?, ri.sale = ?, ri.donation = ? " +
                "WHERE ri.users_id = ? AND i.name = ? AND ri.batch = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, newQuantity); // new quantity to update with
            pstmt.setBoolean(2, sale);
            pstmt.setBoolean(3, donation);
            pstmt.setInt(4, retailerId);
            pstmt.setString(5, itemName);
            pstmt.setInt(6, batchNum);

            pstmt.executeUpdate(); // Execute the update without storing the affected rows
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Updates the sale and donation flags of a product in the retailer's inventory
     * @param connection the connection to the database
     * @param itemId the ID of the product
     * @param sale the sale status of the product
     * @param donation the donation status of the product
     * @throws SQLException if an error occurs during the update
     */
    public void updateInventoryFlags(Connection connection, int itemId, boolean sale, boolean donation) throws SQLException {
        String sql = "UPDATE retailer_inventory SET sale = ?, donation = ? WHERE item_id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);

        pstmt.setBoolean(1, sale);
        pstmt.setBoolean(2, donation);
        pstmt.setInt(3, itemId);

        pstmt.executeUpdate();

        if (sale){
            String sql2 = "SELECT unit_price FROM retailer_inventory WHERE item_id = ?";
            PreparedStatement pstmt2 = connection.prepareStatement(sql2);
            pstmt2.setInt(1, itemId);
            ResultSet rs = pstmt2.executeQuery();
            if (rs.next()){
                double unitPrice = rs.getDouble("unit_price");
                updatePrice(connection, itemId, unitPrice);
            }
        }
    }


    /**
     * this method updates the price if the sale flag is set to true
     * @param connection the connection to the database
     * @param itemId the ID of the product
     * @param unitPrice the unit price of the product
     * @throws SQLException if an error occurs during the update
     */
    public void updatePrice(Connection connection, int itemId, double unitPrice) throws SQLException {
        String sql = "UPDATE retailer_inventory SET final_price = ? WHERE item_id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);

        pstmt.setDouble(1, productPrice(1, unitPrice));
        pstmt.setInt(2, itemId);

        pstmt.executeUpdate();
    }
}

