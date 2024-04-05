/**
 * Responsible for handling database connections, and associated methods.
 * 
 * @author Deven Powell
 * @version 1.0
 * @since 2024-04-02
 */

package edu.ucalgary.oop;

import java.sql.*;

public class DatabaseConnection {

    // Code to connect to the database
    private Connection dbConnect;
    private ResultSet results;

    /**
     * Default constructor to instantiate the class
     */
    public DatabaseConnection() {
    }

    /**
     * Creates a connection to the database
     * 
     * @throws SQLException when the connection to the database fails
     */
    public void createConnection() throws SQLException {

        System.out.println("Connecting to database...");
        dbConnect = DriverManager.getConnection("jdbc:postgresql://localhost/ensf380project", "oop", "ucalgary");
        System.out.println("Database connection successful");

    }

    /**
     * Close the database connection
     */
    public void close() {
        try {
            results.close();
            dbConnect.close();
            System.out.println("Database connection closed");
        } catch (SQLException e) {
            System.out.println("Error closing database connection. Full details: \n" + e);
        }
    }

    /**
     * Displays all inquirers in the database
     * 
     */
    public void displayInquirers() {
        try {
            Statement stmt = dbConnect.createStatement();
            results = stmt.executeQuery("SELECT * FROM inquirer");
            while (results.next()) {
                System.out.println("Inquirer ID: " + results.getInt("id") + " First Name: " + results.getString("firstname") + " Last Name: " + results.getString("lastname") + " Phone Number: " + results.getString("phonenumber"));
            }
            System.out.println();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }   

    /**
     * Displays all interactions in the database
     * 
     */
    public void displayInteractions() {
        try {
            Statement stmt = dbConnect.createStatement();
            results = stmt.executeQuery("SELECT * FROM inquiry_log");
            while (results.next()) {
                System.out.println("Interaction ID: " + results.getInt("id") + " Inquirer ID: " + results.getInt("inquirer") + " Interaction Date: " + results.getString("calldate") + " Details: " + results.getString("details"));
            }
            System.out.println();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new inquirer to the database.
     *
     * @param firstName the first name of the inquirer
     * @param lastName the last name of the inquirer
     * @param phone the phone number of the inquirer
     */
    public void addInquirer(String firstName, String lastName, String phone) {
        try {
            PreparedStatement stmt = dbConnect.prepareStatement("INSERT INTO inquirer (firstname, lastname, phonenumber) VALUES (?, ?, ?)"); // Using placeholders
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, phone);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error adding inquirer to database. Full details: \n" + e);
        }
    }

    /**
     * Checks if an inquirer exists in the database.
     *
     * @param id the id of the inquirer
     * @return true if the inquirer exists, false otherwise
     */
    public boolean inquirerExists(int id) {
        try {
            PreparedStatement stmt = dbConnect.prepareStatement("SELECT 1 FROM inquirer WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            boolean exists = rs.next();
            rs.close();
            stmt.close();
            return exists;
        } catch (SQLException e) {
            System.out.println("Error checking if inquirer exists in database. Full details: \n" + e);
            return false;
        }
    }

    /**
     * Adds an inquiry log to the database.
     *
     * @param inquirer the id of the inquirer
     * @param callDate the date of the call as a string in the format "YYYY-MM-DD"
     * @param details the details of the call
     */
    public void addInquiryLog(int inquirer, String callDate, String details) {
        if (!inquirerExists(inquirer)) {
            System.out.println("Inquirer with id " + inquirer + " does not exist.");
            return;
        }

        try {
            PreparedStatement stmt = dbConnect.prepareStatement("INSERT INTO INQUIRY_LOG (inquirer, callDate, details) VALUES (?, ?, ?)");
            stmt.setInt(1, inquirer);
            stmt.setDate(2, java.sql.Date.valueOf(callDate));
            stmt.setString(3, details);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error adding inquiry log to database. Full details: \n" + e);
        }
    }
}    