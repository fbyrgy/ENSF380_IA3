/**
 * This file contains the test cases for the FamilyRelation class.
 * The FamilyRelationTest class is responsible for testing the functionality of the FamilyRelation class.
 * It includes test cases for object creation, getters, setters, and methods of the FamilyRelation class.
 * The test cases ensure that the FamilyRelation class behaves as expected and that all its methods work correctly.
 * 
 * @author Deven Powell
 * @version 1.0
 * @since 2024-03-10
 */
package edu.ucalgary.oop;

import org.junit.Test;
import static org.junit.Assert.*;


/**
 * This class contains unit tests for the FamilyRelation class.
 */
public class FamilyRelationTest {

    private DisasterVictim personOne = new DisasterVictim("John Dalan", "2024-01-19");
    private DisasterVictim personTwo = new DisasterVictim("Jane Dalan", "2024-02-20");
    private String relationshipTo = "sibling";
    private FamilyRelation testFamilyRelationObject = new FamilyRelation(personOne, relationshipTo, personTwo);
    
    /**
     * Test the constructor of FamilyRelation.
     */
    @Test
    public void testObjectCreation() {
        assertNotNull(testFamilyRelationObject);
    }
	
    /**
     * Test the setPersonOne and getPersonOne methods of FamilyRelation.
     */
    @Test
    public void testSetAndGetPersonOne() {
        DisasterVictim newPersonOne = new DisasterVictim("New Person", "2024-03-21");
        testFamilyRelationObject.setPersonOne(newPersonOne);
        assertEquals("setPersonOne does not update personOne", newPersonOne, testFamilyRelationObject.getPersonOne());
    }
    
    /**
     * Test the setPersonTwo and getPersonTwo methods of FamilyRelation.
     */
    @Test
    public void testSetAndGetPersonTwo() {
        DisasterVictim newPersonTwo = new DisasterVictim("Another Person", "2024-04-22");
        testFamilyRelationObject.setPersonTwo(newPersonTwo);
        assertEquals("setPersonTwo does not update personTwo", newPersonTwo, testFamilyRelationObject.getPersonTwo());
    }

    /**
     * Test the setRelationshipTo and getRelationshipTo methods of FamilyRelation.
     */
    @Test
    public void testSetAndGetRelationshipTo() {
        String newRelationship = "parent";
        testFamilyRelationObject.setRelationshipTo(newRelationship);
        assertEquals("setRelationshipTo does not update the relationship", newRelationship, testFamilyRelationObject.getRelationshipTo());
    }

    /**
     * Test the relationshipExists method of FamilyRelation.
     */
    @Test
    public void testRelationShipExists() {
        FamilyRelation relationshipOne = new FamilyRelation(personOne, relationshipTo, personTwo);
        FamilyRelation relationshipTwo = new FamilyRelation(personOne, relationshipTo, personTwo);

        // Assert that the objects have the same data
        assertEquals("personOne should be equal", relationshipOne.getPersonOne(), relationshipTwo.getPersonOne());
        assertEquals("relationshipTo should be equal", relationshipOne.getRelationshipTo(), relationshipTwo.getRelationshipTo());
        assertEquals("personTwo should be equal", relationshipOne.getPersonTwo(), relationshipTwo.getPersonTwo());
    }
}
