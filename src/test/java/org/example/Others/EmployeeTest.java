package org.example.Others;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    Employee employee = new Employee("Ashsifat1511", "123", "sifatashrarul@gmail.com", "admin");;
    @Test
    public void testGetUsername() {
        assertEquals("Ashsifat1511", employee.getUsername());
    }

    @Test
    public void testSetUsername() {
        employee.setUsername("Sifat");
        assertEquals("Sifat", employee.getUsername());
    }

    @Test
    public void testGetPass() {
        assertEquals("123", employee.getPass());
    }

    @Test
    public void testSetPass() {
        employee.setPass("new_password");
        assertEquals("new_password", employee.getPass());
    }

    @Test
    public void testGetEmail() {
        assertEquals("sifatashrarul@gmail.com", employee.getEmail());
    }

    @Test
    public void testSetEmail() {
        employee.setEmail("hi@gmail.com");
        assertEquals("hi@gmail.com", employee.getEmail());
    }

    @Test
    public void testGetAccess() {
        assertEquals("admin", employee.getAccess());
    }

    @Test
    public void testSetAccess() {
        employee.setAccess("employee");
        assertEquals("employee", employee.getAccess());
    }
}