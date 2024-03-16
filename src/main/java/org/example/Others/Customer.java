package org.example.Others;

import java.sql.Date;

/**
 * Represents a customer entity.
 * Designed by Sifat
 */
public class Customer {
    Integer id; // Customer ID
    String firstName; // First name of the customer
    String lastName; // Last name of the customer
    String address; // Address of the customer
    String phone; // Phone number of the customer
    String email; // Email address of the customer
    String photo; // Photo of the customer
    String gender; // Gender of the customer
    Date date; // Date associated with the customer

    /**
     * Retrieves the ID of the customer.
     * @return The ID of the customer.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the customer.
     * @param id The ID of the customer.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the first name of the customer.
     * @return The first name of the customer.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the customer.
     * @param firstName The first name of the customer.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Retrieves the last name of the customer.
     * @return The last name of the customer.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the customer.
     * @param lastName The last name of the customer.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Retrieves the address of the customer.
     * @return The address of the customer.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the customer.
     * @param address The address of the customer.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Retrieves the phone number of the customer.
     * @return The phone number of the customer.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the customer.
     * @param phone The phone number of the customer.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Retrieves the email address of the customer.
     * @return The email address of the customer.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the customer.
     * @param email The email address of the customer.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the photo of the customer.
     * @return The photo of the customer.
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * Sets the photo of the customer.
     * @param photo The photo of the customer.
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * Retrieves the gender of the customer.
     * @return The gender of the customer.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the gender of the customer.
     * @param gender The gender of the customer.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Retrieves the date associated with the customer.
     * @return The date associated with the customer.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date associated with the customer.
     * @param date The date associated with the customer.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Constructs a new Customer object with the provided details.
     * @param id The ID of the customer.
     * @param firstName The first name of the customer.
     * @param lastName The last name of the customer.
     * @param address The address of the customer.
     * @param phone The phone number of the customer.
     * @param email The email address of the customer.
     * @param photo The photo of the customer.
     * @param gender The gender of the customer.
     * @param date The date associated with the customer.
     */
    public Customer(int id, String firstName, String lastName, String address, String phone, String email, String photo, String gender, Date date) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.photo = photo;
        this.gender = gender;
        this.date = date;
    }
}
