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
        assertEquals("Constructor should set the name correctly", "Shelter A", location.getName());
        assertEquals("Constructor should set the address correctly", "1234 Shelter Ave", location.getAddress());
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
        location.addSupply(supply);
        assertTrue("addSupply should add a supply to the supplies list", containsSupply(location.getSupplies(), supply));
    }

    /**
     * Test for the removeSupply method of the Location class.
     */
    @Test
    public void testRemoveSupply() {
        location.addSupply(supply); // Ensure the supply is added first
        location.removeSupply(supply);
        assertFalse("removeSupply should remove the supply from the supplies list", containsSupply(location.getSupplies(), supply));
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
}
