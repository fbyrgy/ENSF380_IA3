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
    private String expectedFirstName = "John";
    private String expectedLastName = "Doe";
    private String expectedGender = "Male";
    private String expectedComments = "Regular Civilian";

    Person person = new Person("John","Doe","Male","Regular Civilian");

    /**
     * Test case for object creation.
     * Ensures that the person object is not null after creation.
     */
    @Test
    public void testObjectCreation() {
        assertNotNull("Person should not be null", person);
    }

    /**
     * Test case for getFirstName method.
     * Ensures that the getFirstName method returns the correct first name.
     */
    @Test
    public void testGetFirstName() {
        assertEquals("getFirstName should return the correct first name", expectedFirstName, person.getFirstName());
    }

    /**
     * Test case for getLastName method.
     * Ensures that the getLastName method returns the correct last name.
     */
    @Test
    public void testGetLastName() {
        assertEquals("getLastName should return the correct last name", expectedLastName, person.getLastName());
    }

    /**
     * Test case for getComments method.
     * Ensures that the getComments method returns the correct comments.
     */
    @Test
    public void testGetComments() {
        assertEquals("getComments should return the correct comments", expectedComments, person.getComments());
    }

    /**
     * Test case for getGender method.
     * Ensures that the getGender method returns the correct gender.
     */
    @Test
    public void testGetGender() {
        assertEquals("getGender should return the correct gender", expectedGender, person.getGender());
    }

    /**
     * Test case for setFirstName method.
     * Ensures that the setFirstName method changes the first name correctly.
     */
    @Test
    public void testSetFirstName() {
        String newExpectedFirstName = "Jane";
        person.setFirstName(newExpectedFirstName);
        assertEquals("setFirstName should change firstName", newExpectedFirstName, person.getFirstName());
    }

    /**
     * Test case for setLastName method.
     * Ensures that the setLastName method changes the last name correctly.
     */
    @Test
    public void testSetLastName() {
        String newExpectedLastName = "Miller";
        person.setLastName(newExpectedLastName);
        assertEquals("setLastName should change lastName", newExpectedLastName, person.getLastName());
    }

    /**
     * Test case for setGender method.
     * Ensures that the setGender method changes the gender correctly.
     */
    @Test
    public void testSetGender() {
        String newExpectedGender = "Female";
        person.setGender(newExpectedGender);
        assertEquals("setGender should change gender", newExpectedGender, person.getGender());
    }

    /**
     * Test case for setComments method.
     * Ensures that the setComments method changes the comments correctly.
     */
    @Test
    public void testSetComments() {
        String newExpectedComments = "Special Agent";
        person.setComments(newExpectedComments);
        assertEquals("setComments should change comments", newExpectedComments, person.getComments());
    }

}
