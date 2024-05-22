package org.example.Others;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    Item item = new Item(1, "Laptop", 10, true, false, 999.99, 25.0, "laptop.jpg", "Electronics");
    @Test
    public void testGetId() {
        assertEquals(1, item.getId());
    }

    @Test
    public void testSetId() {
        item.setId(2);
        assertEquals(2, item.getId());
    }

    @Test
    public void testGetName() {
        assertEquals("Laptop", item.getName());
    }

    @Test
    public void testSetName() {
        item.setName("Desktop");
        assertEquals("Desktop", item.getName());
    }

    @Test
    public void testGetStock() {
        assertEquals(10, item.getStock());
    }

    @Test
    public void testSetStock() {
        item.setStock(15);
        assertEquals(15, item.getStock());
    }

    @Test
    public void testIsRent() {
        assertEquals(true, item.isRent());
    }

    @Test
    public void testSetRent() {
        item.setRent(false);
        assertEquals(false, item.isRent());
    }

    @Test
    public void testIsSale() {
        assertEquals(false, item.isSale());
    }

    @Test
    public void testSetSale() {
        item.setSale(true);
        assertEquals(true, item.isSale());
    }

    @Test
    public void testGetSalePrice() {
        assertEquals(999.99, item.getSalePrice(), 0.001);
    }

    @Test
    public void testSetSalePrice() {
        item.setSalePrice(799.99);
        assertEquals(799.99, item.getSalePrice(), 0.001);
    }

    @Test
    public void testGetRentRate() {
        assertEquals(25.0, item.getRentRate(), 0.001);
    }

    @Test
    public void testSetRentRate() {
        item.setRentRate(20.0);
        assertEquals(20.0, item.getRentRate(), 0.001);
    }

    @Test
    public void testGetPhoto() {
        assertEquals("laptop.jpg", item.getPhoto());
    }

    @Test
    public void testSetPhoto() {
        item.setPhoto("desktop.jpg");
        assertEquals("desktop.jpg", item.getPhoto());
    }

    @Test
    public void testGetItemType() {
        assertEquals("Electronics", item.getItemType());
    }

    @Test
    public void testSetItemType() {
        item.setItemType("Furniture");
        assertEquals("Furniture", item.getItemType());
    }
}