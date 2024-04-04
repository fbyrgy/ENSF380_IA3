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
     * Test constructor with invalid date
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidDate() {
        new MedicalRecord(expectedLocation, expectedTreatmentDetails, inValidDateOfTreatment);
    }

    /**
     * Test constructor with date past current
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithDatePastCurrent() {
        String pastDateOfTreatment = "2077-03-10";
        new MedicalRecord(expectedLocation, expectedTreatmentDetails, pastDateOfTreatment);
    }

    /**
     * Test constructor with date before 2020
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithDateBefore2020() {
        String before2020DateOfTreatment = "2019-03-10";
        new MedicalRecord(expectedLocation, expectedTreatmentDetails, before2020DateOfTreatment);
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
     * Test setDateOfTreatment with valid date
     */
    @Test
    public void testSetDateOfTreatment() {
        String newExpectedDateOfTreatment = "2024-02-05";
        medicalRecord.setDateOfTreatment(newExpectedDateOfTreatment);
        assertEquals("setDateOfTreatment should update date of treatment", newExpectedDateOfTreatment, medicalRecord.getDateOfTreatment());
    }


    /**
     * Testing setDateOfTreatment with invalid format. Should throw IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfTreatmentWithInvalidFormat() {
        medicalRecord.setDateOfTreatment(inValidDateOfTreatment);
    }

    /**
     * Test setDateOfTreatment with date past current
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfTreatmentWithDatePastCurrent() {
        String pastDateOfTreatment = "2077-03-10";
        medicalRecord.setDateOfTreatment(pastDateOfTreatment);
    }

    /**
     * Test setDateOfTreatment with date before 2020
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfTreatmentWithDateBefore2020() {
        String before2020DateOfTreatment = "2019-03-10";
        medicalRecord.setDateOfTreatment(before2020DateOfTreatment);
    }
}
