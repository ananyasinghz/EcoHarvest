package transferobjects;

import java.util.Date;

/**
 * This class is to create a data transfer object for the retailer_inventory and item
 * tables in the database
 */
public class InventoryItemDTO {
    private int id;
    private String name;
    private String category;
    private int quantity;
    private Date expiryDate;
    private double finalPrice;
    private boolean sale;
    private boolean donation;

    public InventoryItemDTO() {
    }

    /**
     * This constructor is used to create a new InventoryItemDTO object
     * @param name the name of the item
     * @param category the category of the item
     * @param quantity the quantity of the item
     * @param expiryDate the expiry date of the item
     * @param finalPrice the final price of the item
     * @param sale the sale status of the item
     * @param donation the donation status of the item
     */
    public InventoryItemDTO(String name, String category, int quantity, Date expiryDate, double finalPrice, boolean sale, boolean donation) {
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        this.finalPrice = finalPrice;
        this.sale = sale;
        this.donation = donation;
    }

    /**
     * gets name of the item
     * @return the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * sets the name of the item
     * @param name the name of the item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets the category of the item
     * @return the category of the item
     */
    public String getCategory() {
        return category;
    }

    /**
     * sets the category of the item
     * @param category the category of the item
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * gets the quantity of the item
     * @return the quantity of the item
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * sets the quantity of the item
     * @param quantity the quantity of the item
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * gets the expiry date of the item
     * @return the expiry date of the item
     */
    public Date getExpiryDate() {
        return expiryDate;
    }

    /**
     * sets the expiry date of the item
     * @param expiryDate the expiry date of the item
     */
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * gets the final price of the item
     * @return the final price of the item
     */
    public double getFinalPrice() {
        return finalPrice;
    }

    /**
     * sets the final price of the item
     * @param finalPrice the final price of the item
     */
    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    /**
     * gets the sale status of the item
     * @return the sale status of the item
     */
    public boolean getSale() {
        return sale;
    }

    /**
     * sets the sale status of the item
     * @param sale the sale status of the item
     */
    public void setSale(boolean sale) {
        this.sale = sale;
    }

    /**
     * gets the donation status of the item
     * @return the donation status of the item
     */
    public boolean getDonation() {
        return donation;
    }

    /**
     * sets the donation status of the item
     * @param donation the donation status of the item
     */
    public void setDonation(boolean donation) {
        this.donation = donation;
    }

    /**
     * gets the id of the item
     * @return the id of the item
     */
    public int getId() {
        return id;
    }

    /**
     * sets the id of the item
     * @param id the id of the item
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * toString for the InventoryItemDTO object
     * @return the string representation of the InventoryItemDTO object
     */
    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", quantity=" + quantity +
                ", expiryDate=" + expiryDate +
                ", finalPrice=" + finalPrice +
                ", sale=" + sale +
                ", donation=" + donation;
    }

}
