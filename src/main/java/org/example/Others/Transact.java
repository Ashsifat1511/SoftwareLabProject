package org.example.Others;

/**
 * Represents a transaction.
 * Designed by Sifat
 */
public class Transact {
    Integer trID;
    String date;
    Integer accID;
    Integer purchaseOrRentID;
    String issuedBy;

    /**
     * Constructs a Transact object with the specified attributes.
     * @param trID The transaction ID.
     * @param date The transaction date.
     * @param accID The account ID associated with the transaction.
     * @param purchaseOrRentID The ID of the purchase or rental transaction.
     * @param issuedBy The entity that issued the transaction.
     */
    public Transact(Integer trID, String date, Integer accID, Integer purchaseOrRentID, String issuedBy) {
        this.trID = trID;
        this.date = date;
        this.accID = accID;
        this.purchaseOrRentID = purchaseOrRentID;
        this.issuedBy = issuedBy;
    }

    /**
     * Gets the transaction ID.
     * @return The transaction ID.
     */
    public Integer getTrID() {
        return trID;
    }

    /**
     * Sets the transaction ID.
     * @param trID The transaction ID to set.
     */
    public void setTrID(Integer trID) {
        this.trID = trID;
    }

    /**
     * Gets the transaction date.
     * @return The transaction date.
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the transaction date.
     * @param date The transaction date to set.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets the account ID associated with the transaction.
     * @return The account ID associated with the transaction.
     */
    public Integer getAccID() {
        return accID;
    }

    /**
     * Sets the account ID associated with the transaction.
     * @param accID The account ID to set.
     */
    public void setAccID(Integer accID) {
        this.accID = accID;
    }

    /**
     * Gets the ID of the purchase or rental transaction.
     * @return The ID of the purchase or rental transaction.
     */
    public Integer getPurchaseOrRentID() {
        return purchaseOrRentID;
    }

    /**
     * Sets the ID of the purchase or rental transaction.
     * @param purchaseOrRentID The ID of the purchase or rental transaction to set.
     */
    public void setPurchaseOrRentID(Integer purchaseOrRentID) {
        this.purchaseOrRentID = purchaseOrRentID;
    }

    /**
     * Gets the entity that issued the transaction.
     * @return The entity that issued the transaction.
     */
    public String getIssuedBy() {
        return issuedBy;
    }

    /**
     * Sets the entity that issued the transaction.
     * @param issuedBy The entity that issued the transaction to set.
     */
    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }
}
