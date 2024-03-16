package org.example.Others;

/**
 * Represents a purchase transaction.
 * Designed by Sifat
 */
public class Purchase {
    int purID;
    int cusID;
    int itemID;
    String date;
    int qty;
    double paid, due;
    String user;

    /**
     * Constructs a Purchase object with the specified attributes.
     * @param purID The purchase ID.
     * @param cusID The customer ID.
     * @param itemID The item ID.
     * @param date The date of the purchase.
     * @param qty The quantity purchased.
     * @param paid The amount paid.
     * @param due The due amount.
     */
    public Purchase(int purID, int cusID, int itemID, String date, int qty, double paid, double due) {
        this.purID = purID;
        this.cusID = cusID;
        this.itemID = itemID;
        this.date = date;
        this.qty = qty;
        this.paid = paid;
        this.due = due;
    }

    /**
     * Constructs a Purchase object with the specified attributes.
     * @param purID The purchase ID.
     * @param cusID The customer ID.
     * @param itemID The item ID.
     * @param date The date of the purchase.
     * @param qty The quantity purchased.
     * @param paid The amount paid.
     * @param due The due amount.
     * @param user The user who made the purchase.
     */
    public Purchase(int purID, int cusID, int itemID, String date, int qty, double paid, double due, String user) {
        this.purID = purID;
        this.cusID = cusID;
        this.itemID = itemID;
        this.date = date;
        this.qty = qty;
        this.paid = paid;
        this.due = due;
        this.user = user;
    }

    /**
     * Gets the purchase ID.
     * @return The purchase ID.
     */
    public int getPurID() {
        return purID;
    }

    /**
     * Sets the purchase ID.
     * @param purID The purchase ID to set.
     */
    public void setPurID(int purID) {
        this.purID = purID;
    }

    /**
     * Gets the customer ID.
     * @return The customer ID.
     */
    public int getCusID() {
        return cusID;
    }

    /**
     * Sets the customer ID.
     * @param cusID The customer ID to set.
     */
    public void setCusID(int cusID) {
        this.cusID = cusID;
    }

    /**
     * Gets the item ID.
     * @return The item ID.
     */
    public int getItemID() {
        return itemID;
    }

    /**
     * Sets the item ID.
     * @param itemID The item ID to set.
     */
    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    /**
     * Gets the purchase date.
     * @return The purchase date.
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the purchase date.
     * @param date The purchase date to set.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets the quantity purchased.
     * @return The quantity purchased.
     */
    public int getQty() {
        return qty;
    }

    /**
     * Sets the quantity purchased.
     * @param qty The quantity to set.
     */
    public void setQty(int qty) {
        this.qty = qty;
    }

    /**
     * Gets the amount paid.
     * @return The amount paid.
     */
    public double getPaid() {
        return paid;
    }

    /**
     * Sets the amount paid.
     * @param paid The amount paid to set.
     */
    public void setPaid(double paid) {
        this.paid = paid;
    }

    /**
     * Gets the due amount.
     * @return The due amount.
     */
    public double getDue() {
        return due;
    }

    /**
     * Sets the due amount.
     * @param due The due amount to set.
     */
    public void setDue(double due) {
        this.due = due;
    }

    /**
     * Gets the user who made the purchase.
     * @return The user who made the purchase.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the user who made the purchase.
     * @param user The user who made the purchase to set.
     */
    public void setUser(String user) {
        this.user = user;
    }
}
