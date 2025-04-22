package model;

/*
@author aaronthomp
 */

import transferobjects.ItemDTO;
import transferobjects.RetailerInventoryDTO;
import transferobjects.UserDTO;

import java.util.Date;

/**
 * DTOBuilder is a model class that builds the data transfer objects for the application.
 */
public class DTOBuilder {

    /**
     * Builds a user data transfer object
     *
     * @param name takes in the name for the users
     * @param password takes in the users password
     * @param role takes in the users role
     * @param email takes in the users email
     * @param phone takes in the users phone
     * @param location takes in the users location
     * @param preference takes in the users preference
     * @param subscribeToPhone takes in the users subscribe to phone
     * @param subscribeToEmail takes in the users subscribe to email
     * @return the user data transfer object
     */
    public UserDTO userBuilder(String name, String password, String role, String email, String phone, String location, String preference, Boolean subscribeToPhone, Boolean subscribeToEmail){
        UserDTO user = new UserDTO();
        user.setName(name);
        user.setPassword(password);
        user.setRole(role);
        user.setMail(email);
        user.setPhone(phone);
        user.setLocation(location);
        user.setPreference(preference);
        user.setSubscribeToEmail(subscribeToEmail);
        user.setSubscribeToPhone(subscribeToPhone);
        return user;
    }


    /**
     * Builds an item data transfer object
     * @param itemName takes in the item name
     * @param itemCategory takes in the item category
     * @return the item data transfer object
     */
    public ItemDTO itemBuilder(String itemName, String itemCategory){
        ItemDTO item = new ItemDTO();
        item.setName(itemName);
        item.setCategory(itemCategory);
        return item;
    }

    /**
     * Builds a retailer inventory data transfer object
     * @param retailId takes in the retailer id
     * @param itemId takes in the item id
     * @param rInventoryBatchNum takes in the batch number
     * @param rInventoryQuantity takes in the quantity
     * @param rInventoryUnitPrice takes in the unit price
     * @param rInventoryFinalPrice takes in the final price
     * @param rInventoryExpDate takes in the expiry date
     * @param sale takes in the sale boolean
     * @param donation takes in the donation boolean
     * @return the retailer inventory data transfer object
     */
    public RetailerInventoryDTO retailerInventoryBuilder(int retailId, int itemId, int rInventoryBatchNum, int rInventoryQuantity, double rInventoryUnitPrice, double rInventoryFinalPrice, Date rInventoryExpDate, boolean sale, boolean donation){
        RetailerInventoryDTO retailerInventory = new RetailerInventoryDTO();
        retailerInventory.setUsersId(retailId);
        retailerInventory.setItemId(itemId);
        retailerInventory.setBatch(rInventoryBatchNum);
        retailerInventory.setQuantity(rInventoryQuantity);
        retailerInventory.setUnitPrice(rInventoryUnitPrice);
        retailerInventory.setFinalPrice(rInventoryFinalPrice);
        retailerInventory.setExpiryDate(rInventoryExpDate);
        retailerInventory.setSale(sale);
        retailerInventory.setDonation(donation);
        return retailerInventory;
    }
}