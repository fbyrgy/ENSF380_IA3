/**
 * This file contains the test cases for the MedicalRecord class.
 * The MedicalRecordTest class is responsible for testing the functionality of the MedicalRecord class.
 * It includes test cases for the constructor, setters, getters, and methods of the MedicalRecord class.
 * The test cases ensure that the MedicalRecord class behaves as expected and that all its methods work correctly.
 * @author Deven Powell
 * @version 1.0
 * @since 1.0 (2024-03-10)
 */
package edu.ucalgary.oop;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class contains unit tests for the MedicalRecord class.
 */
public class MedicalRecordTest {
    Location expectedLocation = new Location("University of Calgary", "2500 University Dr NW");
    private String expectedTreatmentDetails = "Lacerations treated";
    private String expectedDateOfTreatment = "2024-02-03";
    private String validDateOfTreatment = "2024-02-04";
    private String inValidDateOfTreatment = "2024/02/04";
    MedicalRecord medicalRecord = new MedicalRecord(expectedLocation, expectedTreatmentDetails, expectedDateOfTreatment);

    /**
     * Test the constructor 
     */
    @Test
    public void testObjectCreation() {
        assertNotNull(medicalRecord);
    }

    /**
     * Test getLocation
     */
    @Test
    public void testGetLocation() {
        assertEquals("getLocation should return the correct location", expectedLocation, medicalRecord.getLocation());
    }

    /**
     * Test setLocation
     */
    @Test
    public void testSetLocation() {
        Location newExpectedLocation = new Location("SAIT", "1301 16 Ave NW");
        medicalRecord.setLocation(newExpectedLocation);
        assertEquals("setLocation should update location", newExpectedLocation.getName(), medicalRecord.getLocation().getName());
    }

    /**
     * Test getTreatmentDetails
     */
    @Test
    public void testGetTreatmentDetails() {
        assertEquals("getTreatmentDetails should return the correct treatment details", expectedTreatmentDetails, medicalRecord.getTreatmentDetails());
    }

    /**
     * Test setTreatmentDetails
     */
    @Test
    public void testSetTreatmentDetails() {
        String newExpectedTreatment = "No surgery required";
        medicalRecord.setTreatmentDetails(newExpectedTreatment);
        assertEquals("setTreatmentDetails should update the treatment details", newExpectedTreatment, medicalRecord.getTreatmentDetails());
    }

    /**
     * Test getDateOfTreatment
     */
    @Test
    public void testGetDateOfTreatment() {
        assertEquals("getDateOfTreatment should return the correct date of treatment", expectedDateOfTreatment,
                medicalRecord.getDateOfTreatment());
    }

    /**
     * Test setDateOfTreatment
     */
    @Test
    public void testSetDateOfTreatment() {
        String newExpectedDateOfTreatment = "2024-02-05";
        medicalRecord.setDateOfTreatment(newExpectedDateOfTreatment);
        assertEquals("setDateOfTreatment should update date of treatment", newExpectedDateOfTreatment, medicalRecord.getDateOfTreatment());
    }

    /**
     * Testing setDateOfTreatment with valid format
     */
    @Test
    public void testSetDateOfTreatmentWithValidFormat() {

        medicalRecord.setDateOfTreatment(validDateOfTreatment); // Should not throw an exception
    }

    /**
     * Testing setDateOfTreatment with invalid format. Should throw IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfBirthWithInvalidFormat() {
        medicalRecord.setDateOfTreatment(inValidDateOfTreatment);
    }

    /**
     * Testing setDateOfBirth with invalid date
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfBirthWithNotADate() {
        medicalRecord.setDateOfTreatment(expectedTreatmentDetails);
    }
}
