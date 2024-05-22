package org.example.Others;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    Account account = new Account(6, "MyAcc", 101, "Sifat", "Cash");

    @Test
    public void testSetUser() {
        account.setUser("newUser");
        assertEquals("newUser", account.getUser());
    }

    @Test
    public void testSetCusID() {
        account.setCusID(456);
        assertEquals(456, account.getCusID());
    }

    @Test
    public void testSetAccID() {
        account.setAccID(4);
        assertEquals(4, account.getAccID());
    }

    @Test
    public void testSetCusName() {
        account.setCusName("New Customer");
        assertEquals("New Customer", account.getCusName());
    }

    @Test
    public void testSetAccName() {
        account.setAccName("New Account");
        assertEquals("New Account", account.getAccName());
    }

    @Test
    public void testSetPaymethod() {
        account.setPaymethod("Credit");
        assertEquals("Credit", account.getPaymethod());
    }
    Account account1 = new Account(6, "MyAcc", 101, "Sifat", "Cash");
    @Test
    public void testGetUser() {
        assertEquals("Sifat", account1.getUser());
    }

    @Test
    public void testGetCusID() {
        assertEquals(101, account1.getCusID());
    }

    @Test
    public void testGetAccID() {
        assertEquals(6, account1.getAccID());
    }
    @Test
    public void testGetAccName() {
        assertEquals("MyAcc", account1.getAccName());
    }

    @Test
    public void testGetPaymethod() {
        assertEquals("Cash", account1.getPaymethod());
    }
}