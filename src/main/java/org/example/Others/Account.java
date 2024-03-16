package org.example.Others;
/**
 * Represents a financial account associated with a customer.
 * Designed by Sifat
 */
public class Account {
    int accID;
    String cusName;
    String accName;
    int cusID;
    String paymethod;
    String user;

    /**
     * Constructor for initializing an account with customer details.
     * @param accID The account ID.
     * @param cusName The customer's name.
     * @param accName The account name.
     * @param paymethod The payment method.
     */
    public Account(int accID, String cusName, String accName, String paymethod) {
        this.accID = accID;
        this.cusName = cusName;
        this.accName = accName;
        this.paymethod = paymethod;
    }

    /**
     * Constructor for initializing an account with customer ID and user details.
     * @param accID The account ID.
     * @param accName The account name.
     * @param cusID The customer ID.
     * @param user The user associated with the account.
     * @param paymethod The payment method.
     */
    public Account(int accID, String accName, int cusID, String user, String paymethod) {
        this.accID = accID;
        this.accName = accName;
        this.cusID = cusID;
        this.user = user;
        this.paymethod = paymethod;
    }

    /**
     * Retrieves the user associated with the account.
     * @return The user associated with the account.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the user associated with the account.
     * @param user The user associated with the account.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Retrieves the customer ID associated with the account.
     * @return The customer ID associated with the account.
     */
    public int getCusID() {
        return cusID;
    }

    /**
     * Sets the customer ID associated with the account.
     * @param cusID The customer ID associated with the account.
     */
    public void setCusID(int cusID) {
        this.cusID = cusID;
    }

    /**
     * Retrieves the account ID.
     * @return The account ID.
     */
    public int getAccID() {
        return accID;
    }

    /**
     * Sets the account ID.
     * @param accID The account ID.
     */
    public void setAccID(int accID) {
        this.accID = accID;
    }

    /**
     * Retrieves the customer's name associated with the account.
     * @return The customer's name associated with the account.
     */
    public String getCusName() {
        return cusName;
    }

    /**
     * Sets the customer's name associated with the account.
     * @param cusName The customer's name associated with the account.
     */
    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    /**
     * Retrieves the account name.
     * @return The account name.
     */
    public String getAccName() {
        return accName;
    }

    /**
     * Sets the account name.
     * @param accName The account name.
     */
    public void setAccName(String accName) {
        this.accName = accName;
    }

    /**
     * Retrieves the payment method associated with the account.
     * @return The payment method associated with the account.
     */
    public String getPaymethod() {
        return paymethod;
    }

    /**
     * Sets the payment method associated with the account.
     * @param paymethod The payment method associated with the account.
     */
    public void setPaymethod(String paymethod) {
        this.paymethod = paymethod;
    }

}

