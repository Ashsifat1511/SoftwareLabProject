package org.example.Others;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RentTest {
    Rent rent = new Rent(1, 101, 201, "2022-03-15", "2022-03-20", 50.0, 25.0, "Sifat");
    @Test
    public void testGetRentID() {
        assertEquals(1, rent.getRentID());
    }

    @Test
    public void testSetRentID() {
        rent.setRentID(2);
        assertEquals(2, rent.getRentID());
    }

    @Test
    public void testGetItemID() {
        assertEquals(101, rent.getItemID());
    }

    @Test
    public void testSetItemID() {
        rent.setItemID(102);
        assertEquals(102, rent.getItemID());
    }

    @Test
    public void testGetCusID() {
        assertEquals(201, rent.getCusID());
    }

    @Test
    public void testSetCusID() {
        rent.setCusID(202);
        assertEquals(202, rent.getCusID());
    }

    @Test
    public void testGetRentDate() {
        assertEquals("2022-03-15", rent.getRentDate());
    }

    @Test
    public void testSetRentDate() {
        rent.setRentDate("2022-03-16");
        assertEquals("2022-03-16", rent.getRentDate());
    }

    @Test
    public void testGetReturnDate() {
        assertEquals("2022-03-20", rent.getReturnDate());
    }

    @Test
    public void testSetReturnDate() {
        rent.setReturnDate("2022-03-25");
        assertEquals("2022-03-25", rent.getReturnDate());
    }

    @Test
    public void testGetPayAmount() {
        assertEquals(50.0, rent.getPayAmount(), 0.001);
    }

    @Test
    public void testSetPayAmount() {
        rent.setPayAmount(60.0);
        assertEquals(60.0, rent.getPayAmount(), 0.001);
    }

    @Test
    public void testGetAmountDue() {
        assertEquals(25.0, rent.getAmountDue(), 0.001);
    }

    @Test
    public void testSetAmountDue() {
        rent.setAmountDue(30.0);
        assertEquals(30.0, rent.getAmountDue(), 0.001);
    }

    @Test
    public void testGetUser() {
        assertEquals("Sifat", rent.getUser());
    }

    @Test
    public void testSetUser() {
        rent.setUser("Ashsifat1511");
        assertEquals("Ashsifat1511", rent.getUser());
    }
}