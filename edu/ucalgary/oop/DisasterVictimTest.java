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
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class contains unit tests for the DisasterVictim class.
 */
public class DisasterVictimTest {
    private DisasterVictim victim;
    private ArrayList<Supply> suppliesToSet;
    private ArrayList<FamilyRelation> familyRelations;
    private String expectedFirstName = "Freda";
    private String EXPECTED_ENTRY_DATE = "2024-01-18";
    private String validDate = "2024-01-15";
    private String invalidDate = "15/13/2024";
    private String expectedGender = "female";
    private String expectedComments = "Needs medical attention and speaks 2 languages";
    private DietaryRestrictions expectedDietaryRestrictions = DietaryRestrictions.VGML;

    @Before
    public void setUp() {
        victim = new DisasterVictim(expectedFirstName, EXPECTED_ENTRY_DATE);
        suppliesToSet = new ArrayList<>();
        suppliesToSet.add(new Supply("Water Bottle", 10));
        suppliesToSet.add(new Supply("Blanket", 5));

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
        String newGender = "male";
        victim.setGender(newGender);
        assertEquals("setGender should update and getGender should return the new gender", newGender.toLowerCase(),
                victim.getGender());
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
    public void testAddPersonalBelonging() {
        Supply newSupply = new Supply("Emergency Kit", 1);
        victim.addPersonalBelonging(newSupply);
        ArrayList<Supply> testSupplies = victim.getPersonalBelongings();
        boolean correct = false;

        for (Supply supply : testSupplies) {
            if (supply == newSupply) {
                correct = true;
            }
        }
        assertTrue("addPersonalBelonging should add the supply to personal belongings", correct);
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
     * Test case for the removePersonalBelonging method.
     */
    @Test
    public void testRemovePersonalBelonging() {

        Supply supplyToRemove = suppliesToSet.get(0);
        victim.addPersonalBelonging(supplyToRemove);
        victim.removePersonalBelonging(supplyToRemove);

        ArrayList<Supply> testSupplies = victim.getPersonalBelongings();
        boolean correct = true;

        for (Supply supply : testSupplies) {
            if (supply.equals(supplyToRemove)) {
                correct = false;
            }
        }
        assertTrue("removePersonalBelonging should remove the supply from personal belongings", correct);
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
     * Test case for the setPersonalBelongings method.
     */
    @Test
    public void testSetPersonalBelongings() {
        Supply one = new Supply("Tent", 1);
        Supply two = new Supply("Jug", 3);
        ArrayList<Supply> newSupplies = new ArrayList<>(Arrays.asList(one, two)); 
        boolean correct = true;

        victim.setPersonalBelongings(newSupplies);
        ArrayList<Supply> actualSupplies = victim.getPersonalBelongings();


        if (newSupplies.size() != actualSupplies.size()) { 
            correct = false;
        } else {
            int i;
            for (Supply actualSupply : actualSupplies) {
                boolean found = false;
                for (Supply newSupply : newSupplies) {
                    if (actualSupply.equals(newSupply)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    correct = false;
                    break;
                }
            }
        }
        assertTrue("setPersonalBelongings should correctly update personal belongings", correct);
    }

    /**
     * Test case for the setDietaryRestrictions and getDietaryRestrictions methods.
     */
    @Test
    public void testSetAndGetDietaryRestrictions() {
        victim.addDietaryRestriction(expectedDietaryRestrictions);
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
        victim.setAge(34);
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
}
