package org.example.Others;

/**
 * Represents an employee in the system.
 * Designed by Sifat
 */
public class Employee {
    String username;
    String pass;
    String email;
    String access;

    /**
     * Constructs an Employee object with the specified username, password, email, and access level.
     * @param username The username of the employee.
     * @param pass The password of the employee.
     * @param email The email address of the employee.
     * @param access The access level of the employee.
     */
    public Employee(String username, String pass, String email, String access) {
        this.username = username;
        this.pass = pass;
        this.email = email;
        this.access = access;
    }

    /**
     * Retrieves the username of the employee.
     * @return The username of the employee.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the employee.
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the password of the employee.
     * @return The password of the employee.
     */
    public String getPass() {
        return pass;
    }

    /**
     * Sets the password of the employee.
     * @param pass The password to set.
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * Retrieves the email address of the employee.
     * @return The email address of the employee.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the employee.
     * @param email The email address to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the access level of the employee.
     * @return The access level of the employee.
     */
    public String getAccess() {
        return access;
    }

    /**
     * Sets the access level of the employee.
     * @param access The access level to set.
     */
    public void setAccess(String access) {
        this.access = access;
    }
}
