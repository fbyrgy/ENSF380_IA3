/**
 * This file contains the test cases for the Supply class.
 * The SupplyTest class is responsible for testing the functionality of the Supply class.
 * It includes test cases for object creation, getters, setters, and methods of the Supply class.
 * The test cases ensure that the Supply class behaves as expected and that all its methods work correctly.
 * 
 * @author Deven Powell
 * @version 1.0
 * @since 2024-03-10
 */
package edu.ucalgary.oop;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The SupplyTest class is responsible for testing the functionality of the Supply class.
 */
public class SupplyTest {
    String expectedType = "Blanket";
    int expectedQuantity = 5;
    private Supply supply = new Supply(expectedType, expectedQuantity);

    /**
     * Test case for verifying that a Supply object is successfully created.
     * 
     */
    @Test
    public void testObjectCreation() {
        assertNotNull(supply);
    }

    /**
     * Test case for verifying that a Supply object is not created with a negative quantity.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testObjectCreationWithNegativeQuantity() {
        new Supply("Water", -5);
    }

    /**
     * Test case for verifying that the getType() method correctly returns the actual Supply type.
     * 
     */
    @Test
    public void testGetType() {
        assertEquals("getType should return the correct type", expectedType, supply.getType());
    }

    /**
     * Test case for verifying that the setType() method correctly updates the Supply type.
     * 
     */
    @Test
    public void testSetType() {
        supply.setType("Food");
        assertEquals("setType should update the type", "Food", supply.getType());
    }

    /**
     * Test case for verifying that the getQuantity() method correctly returns the actual Supply quantity.
     * 
     */
    @Test
    public void testGetQuantity() {
        assertEquals("getQuantity should return the correct quantity", expectedQuantity, supply.getQuantity());
    }

    /**
     * Test case for verifying that the setQuantity() method correctly updates the Supply quantity.
     * 
     */
    @Test
    public void testSetQuantity() {
        supply.setQuantity(20);
        assertEquals("setQuantity should update the quantity", 20, supply.getQuantity());
    }
}