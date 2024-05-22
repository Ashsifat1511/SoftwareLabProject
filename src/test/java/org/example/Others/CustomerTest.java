package org.example.Others;

import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    Customer customer = new Customer(1, "Ashrarul", "Sifat", "Teligati", "01521582847", "sifatashrarul@gmail.com", "photo.jpg", "Male", Date.valueOf("2022-01-01"));
    @Test
    public void testSetAndGetId() {
        customer.setId(2);
        assertEquals(2, customer.getId());
    }

    @Test
    public void testSetAndGetFirstName() {
        customer.setFirstName("Sifat");
        assertEquals("Sifat", customer.getFirstName());
    }

    @Test
    public void testSetAndGetLastName() {
        customer.setLastName("Ashrarul");
        assertEquals("Ashrarul", customer.getLastName());
    }

    @Test
    public void testSetAndGetAddress() {
        customer.setAddress("Matikata");
        assertEquals("Matikata", customer.getAddress());
    }

    @Test
    public void testSetAndGetPhone() {
        customer.setPhone("01792461585");
        assertEquals("01792461585", customer.getPhone());
    }

    @Test
    public void testSetAndGetEmail() {
        customer.setEmail("ashrarulhsifat@gmail.com");
        assertEquals("ashrarulhsifat@gmail.com", customer.getEmail());
    }

    @Test
    public void testSetAndGetPhoto() {
        customer.setPhoto("new_photo.jpg");
        assertEquals("new_photo.jpg", customer.getPhoto());
    }

    @Test
    public void testSetAndGetGender() {
        customer.setGender("Female");
        assertEquals("Female", customer.getGender());
    }

    @Test
    public void testSetAndGetDate() {
        Date newDate = Date.valueOf("2023-02-15");
        customer.setDate(newDate);
        assertEquals(newDate, customer.getDate());
    }
}