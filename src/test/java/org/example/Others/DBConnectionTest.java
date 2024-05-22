package org.example.Others;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DBConnectionTest {
    DBConnection dbConnection = new DBConnection();
    @Test
    public void testGetConnection() {
        try {
            Connection connection = dbConnection.getConnection();
            assertNotNull(connection);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}