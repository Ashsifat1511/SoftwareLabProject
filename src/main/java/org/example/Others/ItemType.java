package org.example.Others;

/**
 * Represents an item type in the inventory.
 */
public class ItemType {
    Integer itemTypeID;
    String itemTypeName;
    Integer totalItems; // Total items of this type

    /**
     * Constructs an ItemType object with the specified attributes.
     * @param itemTypeID The ID of the item type.
     * @param itemTypeName The name of the item type.
     * @param totalItems The total items of this type.
     */
    public ItemType(Integer itemTypeID, String itemTypeName, Integer totalItems) {
        this.itemTypeID = itemTypeID;
        this.itemTypeName = itemTypeName;
        this.totalItems = totalItems;
    }

    /**
     * Retrieves the ID of the item type.
     * @return The ID of the item type.
     */
    public Integer getItemTypeID() {
        return itemTypeID;
    }

    /**
     * Sets the ID of the item type.
     * @param itemTypeID The ID to set.
     */
    public void setItemTypeID(Integer itemTypeID) {
        this.itemTypeID = itemTypeID;
    }

    /**
     * Retrieves the name of the item type.
     * @return The name of the item type.
     */
    public String getItemTypeName() {
        return itemTypeName;
    }

    /**
     * Sets the name of the item type.
     * @param itemTypeName The name to set.
     */
    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }

    /**
     * Retrieves the total items of this type.
     * @return The total items of this type.
     */
    public Integer getTotalItems() {
        return totalItems;
    }

    /**
     * Sets the total items of this type.
     * @param totalItems The total items to set.
     */
    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }
}
