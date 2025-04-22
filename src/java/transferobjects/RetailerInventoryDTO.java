package transferobjects;

import java.util.Date;

/**
 * This class is to create a data transfer object for the retailer_inventory table in the database
 */
public class RetailerInventoryDTO {
    private int id;
    private int usersId;
    private int itemId;
    private int batch;
    private Date expiryDate;
    private int quantity;
    private double unitPrice;
    private double finalPrice;
    private boolean sale;
    private boolean donation;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUsersId() {
        return usersId;
    }
    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }
    public int getItemId() {
        return itemId;
    }
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
    public int getBatch() {
        return batch;
    }
    public void setBatch(int batch) {
        this.batch = batch;
    }
    public Date getExpiryDate() {
        return expiryDate;
    }
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
    public double getFinalPrice() {
        return finalPrice;
    }
    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }
    public boolean getSale() {
        return sale;
    }
    public void setSale(boolean sale) {
        this.sale = sale;
    }
    public boolean getDonation() {
        return donation;
    }
    public void setDonation(boolean donation) {
        this.donation = donation;
    }
    public String toString() {
        return "RetailerInventoryDTO{" +
                "id=" + id +
                ", usersId=" + usersId +
                ", itemId=" + itemId +
                ", batch=" + batch +
                ", expiryDate=" + expiryDate +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", finalPrice=" + finalPrice +
                ", sale=" + sale +
                ", donation=" + donation +
                '}';
    }
}
