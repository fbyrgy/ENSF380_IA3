/**
 * This file contains the test cases for the DisasterVictim class.
 * The DisasterVictimTest class is responsible for testing the functionality of the DisasterVictim class.
 * It includes test cases for object creation, getters, setters, and methods of the DisasterVictim class.
 * The test cases ensure that the DisasterVictim class behaves as expected and that all its methods work correctly.
 * 
 * @author Deven Powell
 * @version 1.0
 * @since 2024-03-10
 */
package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * This class contains unit tests for the DisasterVictim class.
 */
public class DisasterVictimTest {
    private DisasterVictim victim;
    private Location location;
    private String expectedFirstName = "Freda";
    private String EXPECTED_ENTRY_DATE = "2024-01-18";
    private String invalidDate = "15/13/2024";
    private String expectedComments = "Needs medical attention and speaks 2 languages";


    @Before
    public void setUp() {
        victim = new DisasterVictim(expectedFirstName, EXPECTED_ENTRY_DATE);
        location = new Location("building 123", "123 Main St");

    }

    /**
     * Test case for the constructor with a valid date.
     */
    @Test
    public void testConstructorWithValidEntryDate() {
        String validEntryDate = "2024-01-18";
        DisasterVictim victim = new DisasterVictim("Freda", validEntryDate);
        assertNotNull("Constructor should successfully create an instance with a valid entry date", victim);
        assertEquals("Constructor should set the entry date correctly", validEntryDate, victim.getEntryDate());
    }

    /**
     * Test the constructor with location argument 
     */
    @Test
    public void testObjectCreation() {
        DisasterVictim testVictim = new DisasterVictim("Freda", "2024-01-18", location);
        assertNotNull(testVictim);
    }

    /**
     * Test case for the sconstructor with an invalid date.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidEntryDateFormat() {
        String invalidEntryDate = "18/01/2024"; // Incorrect format according to your specifications
        new DisasterVictim("Freda", invalidEntryDate);
        // Expecting IllegalArgumentException due to invalid date format
    }

    /**
     * Test case for the setDateOfBirth method with a valid date.
     */
    @Test
    public void testSetDateOfBirth() {
        String newDateOfBirth = "1987-05-21";
        victim.setDateOfBirth(newDateOfBirth);
        assertEquals("setDateOfBirth should correctly update the date of birth", newDateOfBirth,
                victim.getDateOfBirth());
    }

    /**
     * Test case for the setDateOfBirth method with an invalid date.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfBirthWithInvalidFormat() {
        victim.setDateOfBirth(invalidDate); // This format should cause an exception
    }

    /**
     * Test case for the setFirstName and getFirstName methods.
     */
    @Test
    public void testSetAndGetFirstName() {
        String newFirstName = "Alice";
        victim.setFirstName(newFirstName);
        assertEquals("setFirstName should update and getFirstName should return the new first name", newFirstName,
                victim.getFirstName());
    }

    /**
     * Test case for the setLastName and getLastName methods.
     */
    @Test
    public void testSetAndGetLastName() {
        String newLastName = "Smith";
        victim.setLastName(newLastName);
        assertEquals("setLastName should update and getLastName should return the new last name", newLastName,
                victim.getLastName());
    }

    /**
     * Test case for the getComments method.
     */
    @Test
    public void testGetComments() {
        victim.setComments(expectedComments);
        assertEquals("getComments should return the initial correct comments", expectedComments, victim.getComments());
    }


    /**
     * Test case for the setComments method.
     */
    @Test
    public void testSetComments() {
        String newComments = "Has a minor injury on the left arm";
        victim.setComments(newComments);
        assertEquals("setComments should update the comments correctly", newComments, victim.getComments());
    }

    /**
     * Test case for the getAssignedSocialID method.
     */
    @Test
    public void testGetAssignedSocialID() {
        // The next victim should have an ID one higher than the previous victim
        // Tests can be run in any order so two victims will be created
        DisasterVictim newVictim = new DisasterVictim("Kash", "2024-01-21");
        int expectedSocialId = newVictim.getAssignedSocialID() + 1;
        DisasterVictim actualVictim = new DisasterVictim("Adeleke", "2024-01-22");

        assertEquals("getAssignedSocialID should return the expected social ID", expectedSocialId,
                actualVictim.getAssignedSocialID());
    }

    /**
     * Test case for the getEntryDate method.
     */
    @Test
    public void testGetEntryDate() {
        assertEquals("getEntryDate should return the expected entry date", EXPECTED_ENTRY_DATE, victim.getEntryDate());
    }

    /**
     * Test case for the setGender and getGender methods.
     */
    @Test
    public void testSetAndGetGender() {
        String newGender = "man";
        victim.setGender(newGender);
        assertEquals("setGender should update and getGender should return the new gender", newGender.toLowerCase(),
                victim.getGender());
    }

    /**
     * Test case for the setGender method with an invalid gender.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetGenderWithInvalidGender() {
        String invalidGender = "pineapple";
        victim.setGender(invalidGender);
    }

    /**
     * Test case for the addFamilyConnection method.
     */
    @Test
    public void testAddFamilyConnection() {
        DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20");
        DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22");

        FamilyRelation relation = new FamilyRelation(victim2, "parent", victim1);
        ArrayList<FamilyRelation> expectedRelations = new ArrayList<>(Arrays.asList(relation));
        victim2.setFamilyConnections(expectedRelations);

        ArrayList<FamilyRelation> testFamily = victim2.getFamilyConnections();
        boolean correct = false;

        if ((testFamily != null) && (testFamily.get(0) == expectedRelations.get(0))) {
            correct = true;
        }
        assertTrue("addFamilyConnection should add a family relationship", correct);
    }

    /**
     * Test case for the addPersonalBelonging method.
     */
    @Test
    public void testAddAndGetPersonalBelonging() {
        DisasterVictim testVictim = new DisasterVictim("John", "2024-03-18", location);
        Supply newSupply = new Supply("Emergency Kit", 5);
        Supply newSupply2 = new Supply("Emergency Kit", 1);
        location.addSupply(newSupply);
        testVictim.addPersonalBelonging(newSupply2);
        
        assertTrue("addPersonalBelonging should add the supply to personal belongings", testVictim.getPersonalBelongings().contains(newSupply2));
    }

    /**
     * Testing for addPersonalBelonging should remove the supply from location
     */
    @Test
    public void testAddPersonalBelongingRemoveSupply() {
        DisasterVictim testVictim = new DisasterVictim("John", "2024-03-18", location);
        Supply newSupply = new Supply("Emergency Kit", 5);
        Supply newSupply2 = new Supply("Emergency Kit", 1);
        location.addSupply(newSupply);
        testVictim.addPersonalBelonging(newSupply2);
        // There should be 5-1 = 4 supplies left

        boolean found = false;
        for (Supply supply : location.getSupplies()) {
            if (supply.getType().equals("Emergency Kit") && supply.getQuantity() == 4) {
                // There should be a supply with type "Emergency Kit" and quantity 4
                found = true;
                break;
            }
        }
        assertTrue("removeSupply should remove the supply from the supplies list", found);
    }


    /**
     * Test case for the removePersonalBelonging method.
     */
    @Test
    public void testRemovePersonalBelonging() {
        DisasterVictim testVictim = new DisasterVictim("John", "2024-03-18", location);
        Supply newSupply = new Supply("Emergency Kit", 1);
        location.addSupply(newSupply);
        testVictim.addPersonalBelonging(newSupply);

        testVictim.removePersonalBelonging(newSupply);

        assertFalse("removePersonalBelonging should remove the supply from personal belongings", testVictim.getPersonalBelongings().contains(newSupply));
    }



    /**
     * Test case for the removeFamilyConnection method.
     */
    @Test
    public void testRemoveFamilyConnection() {
        DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20");
        DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22");
        FamilyRelation relation1 = new FamilyRelation(victim, "sibling", victim1);
        FamilyRelation relation2 = new FamilyRelation(victim, "sibling", victim2);
        ArrayList<FamilyRelation> expectedRelations = new ArrayList<>();
        expectedRelations.add(relation2);
        ArrayList<FamilyRelation> originalRelations = new ArrayList<>();
        originalRelations.add(relation1);
        originalRelations.add(relation2);
        victim.setFamilyConnections(originalRelations);

        DisasterVictim victim = new DisasterVictim("Freda", "2024-01-23");
        victim.addFamilyConnection(relation1);
        victim.addFamilyConnection(relation2);
        victim.removeFamilyConnection(relation1);

        ArrayList<FamilyRelation> testFamily = victim.getFamilyConnections();
        boolean correct = true;

        for (FamilyRelation relation : testFamily) {
            if (relation.equals(relation1)) {
                correct = false;
            }
        }
        assertTrue("removeFamilyConnection should remove the family member", correct);
    }


    /**
     * Test case for the setFamilyConnection method.
     */
    @Test
    public void testSetFamilyConnection() {
        DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20");
        DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22");

        FamilyRelation relation = new FamilyRelation(victim1, "sibling", victim2);
        ArrayList<FamilyRelation> expectedRelations = new ArrayList<>();
        expectedRelations.add(relation);
        victim1.setFamilyConnections(expectedRelations);
        boolean correct = true;


        ArrayList<FamilyRelation> actualRecords = victim1.getFamilyConnections();
        if (expectedRelations.size() != actualRecords.size()) {
            correct = false;
        } else {
            for (FamilyRelation expectedRelation : expectedRelations) {
                if (!actualRecords.contains(expectedRelation)) {
                    correct = false;
                    break;
                }
            }
        }
        assertTrue("Family relation should be set", correct);
    }

    /**
     * Test case for the setMedicalRecords method.
     */
    @Test
    public void testSetMedicalRecords() {
        Location testLocation = new Location("Shelter Z", "1234 Shelter Ave");
        MedicalRecord testRecord = new MedicalRecord(testLocation, "test for strep", "2024-02-09");
        boolean correct = true;

        ArrayList<MedicalRecord> newRecords = new ArrayList<>();
        newRecords.add(testRecord);
        victim.setMedicalRecords(newRecords);
        ArrayList<MedicalRecord> actualRecords = victim.getMedicalRecords();


        if (newRecords.size() != actualRecords.size()) {
            correct = false;
        } else {
            int i;
            for (i = 0; i < newRecords.size(); i++) {
                if (!actualRecords.get(i).equals(newRecords.get(i))) {
                    correct = false;
                }
            }
        }
        assertTrue("setMedicalRecords should correctly update medical records", correct);
    }


    /**
     * Test case for the addDietaryRestrictions and getDietaryRestrictions methods.
     */
    @Test
    public void testAddAndGetDietaryRestrictions() {
        // Getting the expected results
        ArrayList<DietaryRestrictions> expectedDietaryRestrictions = new ArrayList<>();
        expectedDietaryRestrictions.add(DietaryRestrictions.VGML);
        expectedDietaryRestrictions.add(DietaryRestrictions.KSML);

        // Using the DisasterVictim methods
        victim.addDietaryRestriction(DietaryRestrictions.VGML);
        victim.addDietaryRestriction(DietaryRestrictions.KSML);

        assertEquals(
                "setDietaryRestrictions should update and getDietaryRestrictions should return the new dietary restrictions",
                expectedDietaryRestrictions, victim.getDietaryRestrictions());
    }

    /**
     * Test case for the setAge method when date of birth is set.
     */
    @Test(expected = IllegalStateException.class)
    public void testSetAgeWithDateOfBirthSet() {
        victim.setDateOfBirth("1987-05-21");
        victim.setApproximateAge(34);
        // Expecting IllegalStateException due to date of birth already set
    }

    /**
     * Test case for the setAge method when date of birth is not set.
     */
    @Test
    public void testSetAgeWithoutDateOfBirthSet() {
        DisasterVictim testVictim = new DisasterVictim("Jane", "2024-01-18");
        testVictim.setApproximateAge(34);
        assertEquals("setAge should correctly set the age", 34, (int) testVictim.getApproximateAge());
    }

    /**
     * Test case for the setDateOfBirth method when age is set.
     */
    @Test(expected = IllegalStateException.class)
    public void testSetDateOfBirthWithAgeSet() {
        DisasterVictim testVictim2 = new DisasterVictim("Jane", "2024-01-18");
        testVictim2.setApproximateAge(34);
        testVictim2.setDateOfBirth("1987-05-21");
        // Expecting IllegalStateException due to age already set
    }

    /**
     * Test case for the inheritance from Person class.
     */
    @Test
    public void testInheritance() {
        assertTrue("DisasterVictim should inherit from Person", victim instanceof Person);
    }

    /**
     * Test case for the implementation of the Names interface.
     */
    @Test
    public void testNamesInterface() {
        assertTrue("DisasterVictim should implement the Names interface", victim instanceof Names);
    }

    /**
    * Test case for connecting to the file.
    */
    @Test
    public void testFileConnection() {
        String fileName = "GenderOptions.txt";
        assertTrue("Should be able to connect to the file", Files.exists(Paths.get(fileName)));
    }

    /**
     * Test case for the getLocation and setLocation methods.
     */
    @Test
    public void testGetAndSetLocation() {
        DisasterVictim testVictim = new DisasterVictim("John", "2024-03-18");
        
        // Set the location
        testVictim.setLocation(location);
        
        // Get the location and assert that it matches the expected location
        assertEquals("getLocation should return the correct location", location, testVictim.getLocation());
    }

    /**
     * Test case for addPersonalBelonging with no location set.
     */
    @Test(expected = IllegalStateException.class)
    public void testAddPersonalBelongingNoLocation() {
        Supply supply = new Supply("Water Bottle", 10);
        victim.addPersonalBelonging(supply);
        // Expecting IllegalStateException due to no location set
    }

    /**
     * Test case for addPersonalBelonging with supply not found at location.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddPersonalBelongingSupplyNotFound() {

        DisasterVictim testVictim = new DisasterVictim("John", "2024-03-18", location);
        Supply supply = new Supply("Blanket", 5);
        testVictim.addPersonalBelonging(supply);
        // Expecting IllegalArgumentException due to supply not found at location
    }

    /**
     * Test case for addPPersonalBelonging with not enough supply.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddPersonalBelongingNotEnoughSupply() {
        DisasterVictim testVictim = new DisasterVictim("John", "2024-03-18", location);
        Supply supply = new Supply("Water Bottle", 1);
        location.addSupply(supply);
        Supply supply2 = new Supply("Water Bottle", 10);
        testVictim.addPersonalBelonging(supply2);
        // Expecting IllegalArgumentException due to not enough supply
    }

}