/**
 * This file contains the test cases for the Person class.
 * The PersonTest class is responsible for testing the functionality of the Person class.
 * It includes test cases for object creation, getters, setters, and methods of the Person class.
 * The test cases ensure that the Person class behaves as expected and that all its methods work correctly.
 * 
 * @author Deven Powell
 * @version 1.0
 * @since 2024-03-10
 */
package edu.ucalgary.oop;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class contains unit tests for the Person class.
 */
public class PersonTest {

    Person person = new Person("John");
    Person person2 = new Person("Dave","Smith");

    /**
     * Test case for object creation.
     * Ensures that the person object is not null after creation.
     */
    @Test
    public void testObjectCreation() {
        assertNotNull("Person should not be null", person);
        assertNotNull("Person should not be null", person2);
    }

    /**
     * Test case for getFirstName and setFirstName methods.
     * Ensures that the getFirstName method returns the correct first name
     * and that the setFirstName method changes the first name correctly.
     */
    @Test
    public void testSetAndGetFirstName() {
        String newExpectedFirstName = "Jane";
        person.setFirstName(newExpectedFirstName);
        assertEquals("setFirstName should change firstName", newExpectedFirstName, person.getFirstName());
    }

    /**
     * Test case for getLastName and setLastName methods.
     * Ensures that the getLastName method returns the correct last name
     * and that the setLastName method changes the last name correctly.
     */
    @Test
    public void testSetAndGetLastName() {
        String expectedLastName = "Doe";
        person.setLastName(expectedLastName);
        assertEquals("getLastName should return the correct last name", expectedLastName, person.getLastName());
    }


}
