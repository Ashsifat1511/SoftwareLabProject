package org.example.Others;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactTest {
    Transact transact = new Transact(1, "2022-03-15", 101, 201, "Sifat");
    @Test
    public void testGetTrID() {
        assertEquals(Integer.valueOf(1), transact.getTrID());
    }

    @Test
    public void testSetTrID() {
        transact.setTrID(2);
        assertEquals(Integer.valueOf(2), transact.getTrID());
    }

    @Test
    public void testGetDate() {
        assertEquals("2022-03-15", transact.getDate());
    }

    @Test
    public void testSetDate() {
        transact.setDate("2022-03-16");
        assertEquals("2022-03-16", transact.getDate());
    }

    @Test
    public void testGetAccID() {
        assertEquals(Integer.valueOf(101), transact.getAccID());
    }

    @Test
    public void testSetAccID() {
        transact.setAccID(102);
        assertEquals(Integer.valueOf(102), transact.getAccID());
    }

    @Test
    public void testGetPurchaseOrRentID() {
        assertEquals(Integer.valueOf(201), transact.getPurchaseOrRentID());
    }

    @Test
    public void testSetPurchaseOrRentID() {
        transact.setPurchaseOrRentID(202);
        assertEquals(Integer.valueOf(202), transact.getPurchaseOrRentID());
    }

    @Test
    public void testGetIssuedBy() {
        assertEquals("Sifat", transact.getIssuedBy());
    }

    @Test
    public void testSetIssuedBy() {
        transact.setIssuedBy("Ashsifat1511");
        assertEquals("Ashsifat1511", transact.getIssuedBy());
    }
}