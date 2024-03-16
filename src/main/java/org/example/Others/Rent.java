package org.example.Others;

/**
 * Represents a rental transaction.
 * Designed by Sifat
 */
public class Rent {
    int rentID;
    int itemID;
    int cusID;
    String rentDate;
    String returnDate;
    double payAmount, amountDue;
    String user;

    /**
     * Constructs a Rent object with the specified attributes.
     * @param rentID The rental ID.
     * @param itemID The item ID.
     * @param cusID The customer ID.
     * @param rentDate The rental date.
     * @param returnDate The return date.
     * @param payAmount The amount paid.
     * @param amountDue The due amount.
     */
    public Rent(int rentID, int itemID, int cusID, String rentDate, String returnDate, double payAmount, double amountDue) {
        this.rentID = rentID;
        this.itemID = itemID;
        this.cusID = cusID;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.payAmount = payAmount;
        this.amountDue = amountDue;
    }

    /**
     * Constructs a Rent object with the specified attributes.
     * @param rentID The rental ID.
     * @param itemID The item ID.
     * @param cusID The customer ID.
     * @param rentDate The rental date.
     * @param returnDate The return date.
     * @param payAmount The amount paid.
     * @param amountDue The due amount.
     * @param user The user who made the rental.
     */
    public Rent(int rentID, int itemID, int cusID, String rentDate, String returnDate, double payAmount, double amountDue, String user) {
        this.rentID = rentID;
        this.itemID = itemID;
        this.cusID = cusID;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.payAmount = payAmount;
        this.amountDue = amountDue;
        this.user = user;
    }


    /**
     * Gets the user who made the rental.
     * @return The user who made the rental.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the user who made the rental.
     * @param user The user who made the rental to set.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Gets the rental ID.
     * @return The rental ID.
     */
    public int getRentID() {
        return rentID;
    }

    /**
     * Sets the rental ID.
     * @param rentID The rental ID to set.
     */
    public void setRentID(int rentID) {
        this.rentID = rentID;
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
     * Gets the rental date.
     * @return The rental date.
     */
    public String getRentDate() {
        return rentDate;
    }

    /**
     * Sets the rental date.
     * @param rentDate The rental date to set.
     */
    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    /**
     * Gets the return date.
     * @return The return date.
     */
    public String getReturnDate() {
        return returnDate;
    }

    /**
     * Sets the return date.
     * @param returnDate The return date to set.
     */
    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * Gets the amount paid.
     * @return The amount paid.
     */
    public double getPayAmount() {
        return payAmount;
    }

    /**
     * Sets the amount paid.
     * @param payAmount The amount paid to set.
     */
    public void setPayAmount(double payAmount) {
        this.payAmount = payAmount;
    }

    /**
     * Gets the due amount.
     * @return The due amount.
     */
    public double getAmountDue() {
        return amountDue;
    }

    /**
     * Sets the due amount.
     * @param amountDue The due amount to set.
     */
    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }
}
