/**
 * This file contains the test cases for the ReliefService class.
 * The ReliefServiceTest class is responsible for testing the functionality of the ReliefService class.
 * It includes test cases for object creation, getters, and methods of the ReliefService class.
 * The test cases ensure that the ReliefService class behaves as expected and that all its methods work correctly.
 * 
 * @author Deven Powell
 * @version 1.0
 * @since 2024-03-10
 */
package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class contains unit tests for the ReliefService class.
 */
public class ReliefServiceTest {
    private ReliefService reliefService;
    private Inquirer inquirer;
    private DisasterVictim missingPerson;
    private Location lastKnownLocation;
    private String validDate = "2024-02-10";
    private String invalidDate = "2024/02/10";
    private String expectedInfoProvided = "Looking for family member";
    private String expectedLogDetails = "Inquirer: John, Missing Person: Jane Alex, Date of Inquiry: 2024-02-10, Info Provided: Looking for family member, Last Known Location: University of Calgary"; 

    @Before
    public void setUp() {
        
        inquirer = new Inquirer("John", "Alex", "1234567890", "Looking for family member");
        missingPerson = new DisasterVictim("Jane Alex", "2024-01-25");
        lastKnownLocation = new Location("University of Calgary", "2500 University Dr NW");
        reliefService = new ReliefService(inquirer, missingPerson, validDate, expectedInfoProvided, lastKnownLocation);
        Location testLocation = new Location("University of Calgary", "2500 University Dr NW");
        DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20");
        DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22");
        testLocation.addOccupant(victim1);
        testLocation.addOccupant(victim2);
        
    }

    /**
     * Test for the constructor of ReliefService.
     * It checks if the ReliefService object is not null.
     */
    @Test
    public void testObjectCreation() {
        assertNotNull("ReliefService object should not be null", reliefService);
    }

    /**
     * Test for the getInquirer method of ReliefService.
     * It checks if the returned Inquirer object matches the one set in the setup.
     */
    @Test
    public void testGetInquirer() {
        assertEquals("Inquirer should match the one set in setup", inquirer, reliefService.getInquirer());
    }

    /**
     * Test for the setInquirer method of ReliefService.
     * It checks if the inquirer is set correctly.
     */
    @Test
    public void testSetInquirer() {
        Inquirer newInquirer = new Inquirer("Jane", "Doe", "9876543210", "Looking for friend");
        reliefService.setInquirer(newInquirer);
        assertEquals("Inquirer should be set correctly", newInquirer, reliefService.getInquirer());
    }

    /**
     * Test for the setInquirer method of ReliefService.
     * It checks if the inquirer is set correctly and added to the inquirerList.
     */
    @Test
    public void testSetInquirer_AddsToInquirerList() {
        Inquirer newInquirer = new Inquirer("Jane", "Doe", "9876543210", "Looking for friend");
        reliefService.setInquirer(newInquirer);
        
        // Check if the inquirer is set correctly
        assertEquals("Inquirer should be set correctly", newInquirer, reliefService.getInquirer());
        
        // Check if the inquirer is added to the inquirerList
        assertTrue("Inquirer should be added to the inquirerList", reliefService.getInquirerList().contains(newInquirer));
    }

    /**
     * Test for the getMissingPerson method of ReliefService.
     * It checks if the returned DisasterVictim object matches the one set in the setup.
     */
    @Test
    public void testGetMissingPerson() {
        assertEquals("Missing person should match the one set in setup", missingPerson, reliefService.getMissingPerson());
    }

    /**
     * Test for the setMissingPerson method of ReliefService.
     * It checks if the missing person is set correctly.
     */
    @Test
    public void testSetMissingPerson() {
        DisasterVictim newMissingPerson = new DisasterVictim("John", "Doe", "University of Calgary");
        reliefService.setMissingPerson(newMissingPerson);
        assertEquals("Missing person should be set correctly", newMissingPerson, reliefService.getMissingPerson());
    }

    /**
     * Test for the getDateOfInquiry method of ReliefService.
     * It checks if the returned date of inquiry matches the one set in the setup.
     */
    @Test
    public void testGetDateOfInquiry() {
        assertEquals("Date of inquiry should match the one set in setup", validDate, reliefService.getDateOfInquiry());
    }

    /**
     * Test for the getInfoProvided method of ReliefService.
     * It checks if the returned info provided matches the one set in the setup.
     */
    @Test
    public void testGetInfoProvided() {
        assertEquals("Info provided should match the one set in setup", expectedInfoProvided, reliefService.getInfoProvided());
    }

    /**
     * Test for the getLastKnownLocation method of ReliefService.
     * It checks if the returned last known location matches the one set in the setup.
     */
    @Test
    public void testGetLastKnownLocation() {
        assertEquals("Last known location should match the one set in setup", lastKnownLocation, reliefService.getLastKnownLocation());
    }

    /**
     * Test for the setLastKnownLocation method of ReliefService.
     * It checks if the last known location is set correctly.
     */
    @Test
    public void testSetLastKnownLocation() {
        Location newLocation = new Location("Shelter B", "4321 Shelter Blvd");
        reliefService.setLastKnownLocation(newLocation);
        assertEquals("Last known location should be set correctly", newLocation, reliefService.getLastKnownLocation());
    }

    /**
     * Test for the setDateOfInquiry method of ReliefService with a valid date.
     * It checks if setting a valid date updates the date of inquiry.
     */
    @Test
    public void testSetDateOfInquiryWithValidDate() {
        reliefService.setDateOfInquiry(validDate);
        assertEquals("Setting a valid date should update the date of inquiry", validDate, reliefService.getDateOfInquiry());
    }

    /**
     * Test for the setDateOfInquiry method of ReliefService with an invalid date.
     * It checks if setting an invalid date throws an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfInquiryWithInvalidDate() {
        reliefService.setDateOfInquiry(invalidDate); // This should throw IllegalArgumentException due to invalid format
    }

    /**
     * Test for the getLogDetails method of ReliefService.
     * It checks if the returned log details match the expected format.
     */
    @Test
    public void testGetLogDetails() {
        assertEquals("Log details should match the expected format", expectedLogDetails, reliefService.getLogDetails());
    }

    /**
     * Test for the setLogDetails method of ReliefService.
     * It checks if the log details are set correctly.
     */
    @Test
    public void testSetLogDetails() {
        String newLogDetails = "Updated log details";
        reliefService.setLogDetails(newLogDetails);
        assertEquals("Log details should be set correctly", newLogDetails, reliefService.getLogDetails());
    }

    /**
     * Test for the enterInformation method of ReliefService.
     * It checks if the note is added to the comments of the victim.
     */
    @Test
    public void testEnterInformation() {
        enterInformation(testLocation, victim1, "Recovered");
        assertEquals("Note should be added to the comments", "Recovered", victim1.getComments());
    }

    /**
     * Test for the searchName method of ReliefService.
     * It checks if the search returns true for a given search query.
     */
    @Test
    public void testSearchName() {
        String searchQuery = "Joh";
        assertTrue("Search should return true", searchName(testLocation, searchQuery));
    }
    /**
     * Test for the inquirerExists method of ReliefService.
     * It checks if the inquirer exists in the inquirerList.
     */
    @Test
    public void testInquirerExists() {
        reliefService.setInquirer(inquirer); 
        Inquirer inquirer2 = inquirer = new Inquirer("John", "Alex", "1234567890", "Looking for family member");
        assertTrue("Inquirer should exist", inquirerExists(inquirer2));
    }
}
