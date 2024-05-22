package org.example.Others;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTypeTest {
    ItemType itemType = new ItemType(1, "Electronics", 20);
    @Test
    public void testGetItemTypeID() {
        assertEquals(Integer.valueOf(1), itemType.getItemTypeID());
    }

    @Test
    public void testSetItemTypeID() {
        itemType.setItemTypeID(2);
        assertEquals(Integer.valueOf(2), itemType.getItemTypeID());
    }

    @Test
    public void testGetItemTypeName() {
        assertEquals("Electronics", itemType.getItemTypeName());
    }

    @Test
    public void testSetItemTypeName() {
        itemType.setItemTypeName("Furniture");
        assertEquals("Furniture", itemType.getItemTypeName());
    }

    @Test
    public void testGetTotalItems() {
        assertEquals(Integer.valueOf(20), itemType.getTotalItems());
    }

    @Test
    public void testSetTotalItems() {
        itemType.setTotalItems(30);
        assertEquals(Integer.valueOf(30), itemType.getTotalItems());
    }
}