package org.example.Others;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseTest {
    Purchase purchase = new Purchase(1, 101, 201, "2022-03-15", 5, 150.0, 50.0, "Sifat");
    @Test
    public void testGetPurID() {
        assertEquals(1, purchase.getPurID());
    }

    @Test
    public void testSetPurID() {
        purchase.setPurID(2);
        assertEquals(2, purchase.getPurID());
    }

    @Test
    public void testGetCusID() {
        assertEquals(101, purchase.getCusID());
    }

    @Test
    public void testSetCusID() {
        purchase.setCusID(102);
        assertEquals(102, purchase.getCusID());
    }

    @Test
    public void testGetItemID() {
        assertEquals(201, purchase.getItemID());
    }

    @Test
    public void testSetItemID() {
        purchase.setItemID(202);
        assertEquals(202, purchase.getItemID());
    }

    @Test
    public void testGetDate() {
        assertEquals("2022-03-15", purchase.getDate());
    }

    @Test
    public void testSetDate() {
        purchase.setDate("2022-03-16");
        assertEquals("2022-03-16", purchase.getDate());
    }

    @Test
    public void testGetQty() {
        assertEquals(5, purchase.getQty());
    }

    @Test
    public void testSetQty() {
        purchase.setQty(10);
        assertEquals(10, purchase.getQty());
    }

    @Test
    public void testGetPaid() {
        assertEquals(150.0, purchase.getPaid(), 0.001);
    }

    @Test
    public void testSetPaid() {
        purchase.setPaid(200.0);
        assertEquals(200.0, purchase.getPaid(), 0.001);
    }

    @Test
    public void testGetDue() {
        assertEquals(50.0, purchase.getDue(), 0.001);
    }

    @Test
    public void testSetDue() {
        purchase.setDue(25.0);
        assertEquals(25.0, purchase.getDue(), 0.001);
    }

    @Test
    public void testGetUser() {
        assertEquals("Sifat", purchase.getUser());
    }

    @Test
    public void testSetUser() {
        purchase.setUser("Ashsifat1511");
        assertEquals("Ashsifat1511", purchase.getUser());
    }
}