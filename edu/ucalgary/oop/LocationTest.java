/**
 * This file contains the test cases for the Location class.
 * The LocationTest class is responsible for testing the functionality of the Location class.
 * It includes test cases for the constructor, setters, getters, and methods of the Location class.
 * The test cases ensure that the Location class behaves as expected and that all its methods work correctly.
 * 
 * @author Deven Powell
 * @version 1.0
 * @since 1.0 (2024-03-10)
 */
package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;


/**
 * This class contains unit tests for the Location class.
 */
public class LocationTest {
    private Location location;
    private DisasterVictim victim;
    private Supply supply;

    @Before
    public void setUp() {
        // Initializing test objects before each test method
        location = new Location("Shelter A", "1234 Shelter Ave");
        victim = new DisasterVictim("John Doe", "2024-01-01");
        supply = new Supply("Water Bottle", 10);
    }

    /**
     * Helper method to check if a supply is in the list.
     *
     * @param supplies       the list of supplies to check
     * @param supplyToCheck  the supply to check for
     * @return               true if the supply is in the list, false otherwise
     */
    private boolean containsSupply(ArrayList<Supply> supplies, Supply supplyToCheck) {
        return supplies.contains(supplyToCheck);
    }

    /**
     * Test for the constructor of the Location class.
     */
    @Test
    public void testConstructor() {
        assertNotNull("Constructor should create a non-null Location object", location);
    }

    /**
     * Test for the setName method of the Location class.
     */
    @Test
    public void testSetName() {
        String newName = "Shelter B";
        location.setName(newName);
        assertEquals("setName should update the name of the location", newName, location.getName());
    }

    /**
     * Test for the setAddress method of the Location class.
     */
    @Test
    public void testSetAddress() {
        String newAddress = "4321 Shelter Blvd";
        location.setAddress(newAddress);
        assertEquals("setAddress should update the address of the location", newAddress, location.getAddress());
    }

    /**
     * Test for the addOccupant method of the Location class.
     */
    @Test
    public void testAddOccupant() {
        location.addOccupant(victim);
        assertTrue("addOccupant should add a disaster victim to the occupants list", location.getOccupants().contains(victim));
    }

    /**
     * Test for the removeOccupant method of the Location class.
     */
    @Test
    public void testRemoveOccupant() {
        location.addOccupant(victim); // Ensure the victim is added first
        location.removeOccupant(victim);
        assertFalse("removeOccupant should remove the disaster victim from the occupants list", location.getOccupants().contains(victim));
    }

    /**
     * Test for the setOccupants and getOccupants methods of the Location class.
     */
    @Test
    public void testSetAndGetOccupants() {
        ArrayList<DisasterVictim> newOccupants = new ArrayList<>();
        newOccupants.add(victim);
        location.setOccupants(newOccupants);
        assertTrue("setOccupants should replace the occupants list with the new list", location.getOccupants().containsAll(newOccupants));
    }

    /**
     * Test for the addSupply method of the Location class.
     */
    @Test
    public void testAddSupply() {
        Location testLocation = new Location("Shelter A", "1234 Shelter Ave");
        Supply supply1 = new Supply("Water Bottle", 1);
        Supply supply2 = new Supply("Water Bottle", 2);
        testLocation.addSupply(supply1);
        testLocation.addSupply(supply2);
        // There should now be 3 water bottles in the supplies list
    
        boolean found = false;
        for (Supply supply : testLocation.getSupplies()) {
            if (supply.getType().equals("Water Bottle") && supply.getQuantity() == 3) {
                // There should be a supply with type "Water Bottle" and quantity 3 
                found = true;
                break;
            }
        }
        assertTrue("addSupply should add a supply to the supplies list", found);
    }

    /**
     * Test for the removeSupply method of the Location class.
     */
    @Test
    public void testRemoveSupply() {
        // supply has 10 water bottles
        location.addSupply(supply); // Ensure the supply is added first
        Supply supplyToRemove = new Supply("Water Bottle", 7);
        location.removeSupply(supplyToRemove);
        // There should now be 10-3 = 7 water bottles in the supplies list

        boolean found = false;
        for (Supply supply : location.getSupplies()) {
            if (supply.getType().equals("Water Bottle") && supply.getQuantity() == 3) {
                // There should be a supply with type "Water Bottle" and quantity 3
                found = true;
                break;
            }
        }
        assertTrue("removeSupply should remove the supply from the supplies list", found);
    }

    /**
     * Test for the setSupplies and getSupplies methods of the Location class.
     */
    @Test
    public void testSetAndGetSupplies() {
        ArrayList<Supply> newSupplies = new ArrayList<>();
        newSupplies.add(supply);
        location.setSupplies(newSupplies);
        assertTrue("setSupplies should replace the supplies list with the new list", containsSupply(location.getSupplies(), supply));
    }

    /**
     * Test case for the getLocationID method.
     * 
     * This test creates two Location objects and checks if the IDs are sequential.
     * The first location ID should be 1 less than the second location ID.
     */
    @Test
    public void testGetLocationID() {
        // Create two Location objects
        Location location1 = new Location("Location1", "Address1");
        Location location2 = new Location("Location2", "Address2");
    
        // Check if the IDs are sequential. 
        assertEquals("First location ID should be 1", location2.getLocationID(), location1.getLocationID() + 1);
    }

    /**
     * Test case for the getDisasterVictimFromID method in the Location class.
     * 
     * This test verifies that the getDisasterVictimFromID method returns the correct DisasterVictim object
     * when given a valid ID.
     */
    @Test
    public void testGetDisasterVictimFromId() {
    
        location.addOccupant(victim);
        System.out.println(victim.getAssignedSocialID());
        assertEquals("getDisasterVictimFromId should return the correct DisasterVictim object", victim, location.getDisasterVictimFromID(victim.getAssignedSocialID()));
    }
    
    /**
     * Test case to verify that the getDisasterVictimFromID method throws an IllegalArgumentException
     * when an invalid social ID is provided.
     *
     * This test creates a Location object and attempts to retrieve a DisasterVictim using an invalid social ID.
     * Since no DisasterVictim has been added to the Location, any social ID will be considered invalid.
     * The test expects an IllegalArgumentException to be thrown.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetDisasterVictimFromIdInvalid() {
        // Create a Location
        Location location1 = new Location("Location1", "Address1");
    
        // Check that getDisasterVictimFromID throws an IllegalArgumentException for an invalid social ID
        // Since no DisasterVictim has been added to the Location, any socialID will be invalid
        location1.getDisasterVictimFromID(99999);
    }

}

