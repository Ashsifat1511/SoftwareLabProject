package org.example.Others;

import org.example.Controller.PromptDialogController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Manages the database connection.
 * Designed by Sifat
 */
public class DBConnection {

    /**
     * Establishes a connection to the database.
     * @return The database connection.
     */
    public static Connection getConnection(){
        try{
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Database URL
            String url = "jdbc:mysql://localhost/inventory";

            // Establish the database connection
            Connection connection = DriverManager.getConnection(url, "root", "");

            return connection;
        }
        catch(Exception e){
            // Print the stack trace if an exception occurs
            e.printStackTrace();
        }
        return null;
    }
}
