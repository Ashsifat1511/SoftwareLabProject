package org.example.Others;

/**
 * Represents an item in the inventory.
 * Designed by Sifat
 */
public class Item {
    int id;
    String name;
    int stock;
    boolean rent, sale;
    double salePrice;
    double rentRate;
    String photo;
    String itemType;

    /**
     * Constructs an Item object with the specified attributes.
     * @param id The ID of the item.
     * @param name The name of the item.
     * @param stock The stock quantity of the item.
     * @param rent Indicates whether the item is available for rent.
     * @param sale Indicates whether the item is available for sale.
     * @param salePrice The sale price of the item.
     * @param rentRate The rental rate of the item.
     * @param photo The photo URL of the item.
     * @param itemType The type of the item.
     */
    public Item(int id, String name, int stock, boolean rent, boolean sale, double salePrice, double rentRate, String photo, String itemType) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.rent = rent;
        this.sale = sale;
        this.salePrice = salePrice;
        this.rentRate = rentRate;
        this.photo = photo;
        this.itemType = itemType;
    }

    /**
     * Retrieves the ID of the item.
     * @return The ID of the item.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the item.
     * @param id The ID to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the item.
     * @return The name of the item.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the item.
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the stock quantity of the item.
     * @return The stock quantity of the item.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets the stock quantity of the item.
     * @param stock The stock quantity to set.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Checks if the item is available for rent.
     * @return True if the item is available for rent, false otherwise.
     */
    public boolean isRent() {
        return rent;
    }

    /**
     * Sets whether the item is available for rent.
     * @param rent True if the item is available for rent, false otherwise.
     */
    public void setRent(boolean rent) {
        this.rent = rent;
    }

    /**
     * Checks if the item is available for sale.
     * @return True if the item is available for sale, false otherwise.
     */
    public boolean isSale() {
        return sale;
    }

    /**
     * Sets whether the item is available for sale.
     * @param sale True if the item is available for sale, false otherwise.
     */
    public void setSale(boolean sale) {
        this.sale = sale;
    }

    /**
     * Retrieves the sale price of the item.
     * @return The sale price of the item.
     */
    public double getSalePrice() {
        return salePrice;
    }

    /**
     * Sets the sale price of the item.
     * @param salePrice The sale price to set.
     */
    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    /**
     * Retrieves the rental rate of the item.
     * @return The rental rate of the item.
     */
    public double getRentRate() {
        return rentRate;
    }

    /**
     * Sets the rental rate of the item.
     * @param rentRate The rental rate to set.
     */
    public void setRentRate(double rentRate) {
        this.rentRate = rentRate;
    }

    /**
     * Retrieves the photo URL of the item.
     * @return The photo URL of the item.
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * Sets the photo URL of the item.
     * @param photo The photo URL to set.
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * Retrieves the type of the item.
     * @return The type of the item.
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * Sets the type of the item.
     * @param itemType The type to set.
     */
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
}
