/**
 * This file contains the test cases for the Inquirer class.
 * The InquirerTest class is responsible for testing the functionality of the Inquirer class.
 * It includes test cases for object creation, getters, and setters of the Inquirer class.
 * The test cases ensure that the Inquirer class behaves as expected and that all its methods work correctly.
 * 
 * @author Deven Powell
 * @version 1.0
 * @since 2024-03-10
 */
package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * This class contains unit tests for the Inquirer class.
 */
public class InquirerTest {
    
    /* 
    Define the values which will be used for tests
    */
    private String expectedFirstName = "Joseph";
    private String expectedLastName = "Bouillon";
    private String expectedPhoneNumber = "+1-123-456-7890";
    private ArrayList<String> expectedInteractions = new ArrayList<>(Arrays.asList("Found family members", "Looking for friend"));
    private String expectedMessage = "looking for my family members";

    private Inquirer inquirer = new Inquirer(expectedFirstName, expectedLastName, expectedPhoneNumber, expectedMessage);

    /**
     * Test case for object creation.
     * It verifies that an "Inquirer" object is successfully created.
     * The expected result is that the Inquirer object is not null, confirming successful object creation.
     */
    @Test
    public void testObjectCreation() {
        assertNotNull(inquirer);
    }

    /**
     * Test case for the getFirstName() method.
     * It ensures that the "getFirstName()" method correctly returns the actual inquirer's first name.
     * The expected result is that "inquirer.getFirstName()" should return "Joseph".
     */
    @Test
    public void testGetFirstName() {
        assertEquals("getFirstName() should return inquirer's first name", expectedFirstName, inquirer.getFirstName());
    }
    
    /**
     * Test case for the getLastName() method.
     * It confirms that the "getLastName()" method accurately returns inquirer's last name.
     * The expected result is that "inquirer.getLastName()" should return "Bouillon".
     */
    @Test
    public void testGetLastName() {
        assertEquals("getLastName() should return inquirer's last name", expectedLastName, inquirer.getLastName());
    }
    
    /**
     * Test case for the getServicesPhoneNum() method.
     * It confirms that the "getServicesPhoneNum()" method correctly returns the services phone number.
     * The expected result is that "inquirer.getServicesPhoneNum()" should return "+1-123-456-7890".
     */
    @Test
    public void testGetServicesPhoneNum() {
        assertEquals("getServicesPhoneNum() should return the correct Services Number", expectedPhoneNumber, inquirer.getServicesPhoneNum());
    }
    
    /**
     * Test case for the getInfo() method.
     * It confirms that the "getInfo()" method retrieves the correct information string.
     * The expected result is that "inquirer.getInfo()" should return the string "looking for my family members".
     */
    @Test
    public void testGetInfo() {
        assertEquals("getInfo() should return the inquirer message", expectedMessage, inquirer.getInfo());
    }

    /**
     * Test case for the getInteractions() method.
     * It verifies that the "getInteractions()" method returns the inquirer's interactions.
     * The expected result is that "inquirer.getInteractions()" should return the expected interactions.
     */
    @Test
    public void testAddAndGetInteractions() {
        inquirer.addInteraction("Found family members");
        inquirer.addInteraction("Looking for friend");
        assertEquals("getInteractions() should return the inquirer's interactions", expectedInteractions, inquirer.getInteractions());
    }

}
