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

import java.util.ArrayList;

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
     * Test the default constructor of ReliefService.
     */
    @Test
    public void testDefaultConstructor() {
        ReliefService defaultReliefService = new ReliefService();
        assertNotNull("Default ReliefService object should not be null", defaultReliefService);
    }

    /**
     * Test the constructor of ReliefService with only an Inquirer object.
     * 
     */
    @Test
    public void testConstructorWithInquirer() {
        ReliefService inquirerReliefService = new ReliefService(inquirer);
        assertNotNull("ReliefService object with only Inquirer should not be null", inquirerReliefService);
    }
    /**
     * Test the getLocations method. 
     * The list it returns should contain the two new added locations.
     * 
     */
    @Test
    public void testGetLocations() {

        // Create some Locations
        Location location1 = new Location("Location1", "Address1");
        Location location2 = new Location("Location2", "Address2");

        // Add the Locations to the ReliefService
        ReliefService.addLocation(location1);
        ReliefService.addLocation(location2);

        // Check that getLocations returns the correct list of Locations
        ArrayList<Location> expectedLocations = new ArrayList<>();
        expectedLocations.add(location1);
        expectedLocations.add(location2);
        // The method is static so we cannot see if it equals another list, since other areas may affect it
        // Instead we confirm that it contains both of the new locations we added.=
        assertTrue("getLocations should contain location1", ReliefService.getLocations().contains(location1)); 
        assertTrue("getLocations should contain location1", ReliefService.getLocations().contains(location2));
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
        ReliefService rs = new ReliefService();
        Inquirer newInquirer = new Inquirer("Jane", "Doe", "1", "Looking for friend");
        rs.setInquirer(newInquirer);
        assertEquals("Inquirer should be set correctly", newInquirer, rs.getInquirer());
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
        assertTrue("Inquirer should be added to the inquirerList", ReliefService.getInquirerList().contains(newInquirer));
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
        DisasterVictim newMissingPerson = new DisasterVictim("John", "2024-01-01");
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
     * Test for the findInquirer method of ReliefService.
     * When a new ReliefService is created, it should check the list of inquirers to see if the inquirer already exists.
     * If the inquirer already exists, it should use the one that already exists in the list, instead of starting a new log
     */
    @Test
    public void testFindInquirer() {
        Inquirer person = new Inquirer("John","Doe","1123");
        Inquirer person2 = new Inquirer("John","Doe","1123"); // Same data as person
        ReliefService rs = new ReliefService(person);
        ReliefService rs2 = new ReliefService(person2);
        assertEquals("rs2 should use the inquirer initialized with rs", person, rs2.getInquirer());

    }

    /**
     * Test that the findInquirer method throws IllegalArgumentException when the inquirer does not exist in the inquirers list
     * 
     */
    @Test(expected=IllegalArgumentException.class)
    public void testFindInquirerDifferentData() {
        Inquirer person = new Inquirer("John","Doe","112");
        Inquirer person2 = new Inquirer("Jane","Doe","112"); // person2 is not added to the list since we do not create a relief service for it
        ReliefService rs = new ReliefService(person);
        rs.findInquirer(person2); // Should throw IllegalArgumentException since person2 is not in the list
    }

    /**
     * Test that the findInquirer method uses the correct inquirer when inquirers have different data
     * 
     */
    @Test
    public void testFindInquirerDifferentData2() {
        Inquirer person = new Inquirer("John","Doe","1124");
        Inquirer person2 = new Inquirer("Jane","Doe","1124"); // Different name
        ReliefService rs = new ReliefService(person);
        ReliefService rs2 = new ReliefService(person2);
        assertEquals("rs2 should use the inquirer person2", person2, rs2.getInquirer()); // Here, person != person2, so rs2 should use the inquirer its initialized with
        assertEquals("rs should use the inquirer person", person, rs.getInquirer());
    }
 
    /**
     * Test for the addLocation method of ReliefService.
     * It checks if the location is added to the list of locations.
     */
    @Test
    public void testAddLocation() {


        Location location = new Location("Location1", "Address1");

        // Add the Location to the ReliefService
        ReliefService.addLocation(location);

        // Check that the Location was added to the ReliefService
        assertTrue("addLocation should add the Location to the ReliefService", ReliefService.getLocations().contains(location));
    }

    /**
     * Test for the getLocationFromID method of ReliefService.
     * It should return the Location that corresponds to the given location ID.
     */
    @Test
    public void testGetLocationFromID() {


        Location location = new Location("Location1", "Address1");

        // Add the Location to the ReliefService
        ReliefService.addLocation(location);

        // Check that getLocationFromID returns the correct Location
        assertEquals("getLocationFromID should return the correct Location", location, ReliefService.getLocationFromID(location.getLocationID()));
    }

    /**
     * Test for the getLocationFromID method of ReliefService.
     * It should throw an IllegalArgumentException when an invalid location ID is provided.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetLocationFromIDInvalid() {

        // Check that getLocationFromID throws an IllegalArgumentException for an invalid location ID
        ReliefService.getLocationFromID(99999);
    }

    /**
     * Test case for the searchForVictim method in the UserInterface class.
     * It verifies that the searchForVictim method returns the expected list of victims
     * when searching for a specific query.
     */
    @Test
    public void testSearchForVictim() {
        Location location = new Location ("location","address");
        ReliefService.addLocation(location);
        DisasterVictim victim = new DisasterVictim("Praveen","2023-01-01",location);
        DisasterVictim victim2 = new DisasterVictim("Oprah", "2023-01-01", location);
        String query = "Pra";
        ArrayList<DisasterVictim> expectedVictims = new ArrayList<>();
        expectedVictims.add(victim);
        expectedVictims.add(victim2);
        assertEquals("The expected results should contain victim and victim2", expectedVictims, ReliefService.searchForVictim(query));
    }


}
