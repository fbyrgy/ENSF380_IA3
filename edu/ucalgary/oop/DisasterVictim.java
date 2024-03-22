/**
 * Represents a victim of a disaster with a first name, last name, gender, comments, among other attributes.
 * 
 * @author Deven Powell
 * @version 1.0
 * @since 2024-03-18
 */


package edu.ucalgary.oop;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.time.LocalDate;

/**
 * Represents a victim of a disaster.
 */
public class DisasterVictim {
    private static int counter = 0;

    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private final int ASSIGNED_SOCIAL_ID;
    private ArrayList<FamilyRelation> familyConnections = new ArrayList<>();
    private ArrayList<MedicalRecord> medicalRecords = new ArrayList<>();
    private Supply[] personalBelongings;
    private final String ENTRY_DATE;
    private String gender;
    private String comments;

    /**
     * Constructs a new DisasterVictim object with the given first name and entry date.
     * @param firstName the first name of the victim
     * @param ENTRY_DATE the entry date of the victim in the format "YYYY-MM-DD"
     * @throws IllegalArgumentException if the entry date has an invalid format
     */
    public DisasterVictim(String firstName, String ENTRY_DATE) {
        this.firstName = firstName;
        if (!isValidDateFormat(ENTRY_DATE)) {
            throw new IllegalArgumentException("Invalid date format for entry date. Expected format: YYYY-MM-DD");
        }
        this.ENTRY_DATE = ENTRY_DATE;
        this.ASSIGNED_SOCIAL_ID = generateSocialID();
    }

    private static int generateSocialID() {
        counter++;
        return counter;
    }

    private static boolean isValidDateFormat(String date) {
        String dateFormatPattern = "^\\d{4}-\\d{2}-\\d{2}$";
        return date.matches(dateFormatPattern);
    }

    // Getters and setters

    /**
     * Returns the first name of the victim.
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the victim.
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name of the victim.
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the victim.
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the date of birth of the victim.
     * @return the date of birth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the date of birth of the victim.
     * @param dateOfBirth the date of birth to set in the format "YYYY-MM-DD"
     * @throws IllegalArgumentException if the date of birth has an invalid format
     */
    public void setDateOfBirth(String dateOfBirth) {
        if (!isValidDateFormat(dateOfBirth)) {
            throw new IllegalArgumentException("Invalid date format for date of birth. Expected format: YYYY-MM-DD");
        }
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Returns the assigned social ID of the victim.
     * @return the assigned social ID
     */
    public int getAssignedSocialID() {
        return ASSIGNED_SOCIAL_ID;
    }

    /**
     * Returns an array of family connections of the victim.
     * @return an array of family connections
     */
    public FamilyRelation[] getFamilyConnections() {
        return familyConnections.toArray(new FamilyRelation[0]);
    }

    /**
     * Returns an array of medical records of the victim.
     * @return an array of medical records
     */
    public MedicalRecord[] getMedicalRecords() {
        return medicalRecords.toArray(new MedicalRecord[0]);
    }

    /**
     * Returns an array of personal belongings of the victim.
     * @return an array of personal belongings
     */
    public Supply[] getPersonalBelongings() {
        return this.personalBelongings;
    }

    // The add and remove methods remain correct.

    /**
     * Sets the family connections of the victim.
     * @param connections the family connections to set
     */
    public void setFamilyConnections(FamilyRelation[] connections) {
        this.familyConnections.clear();
        for (FamilyRelation newRecord : connections) {
            addFamilyConnection(newRecord);
        }
    }

    /**
     * Sets the medical records of the victim.
     * @param records the medical records to set
     */
    public void setMedicalRecords(MedicalRecord[] records) {
        this.medicalRecords.clear();
        for (MedicalRecord newRecord : records) {
            addMedicalRecord(newRecord);
        }
    }

    /**
     * Sets the personal belongings of the victim.
     * @param belongings the personal belongings to set
     */
    public void setPersonalBelongings(Supply[] belongings) {
        this.personalBelongings = belongings;
    }

    /**
     * Adds a supply to the personal belongings of the victim.
     * @param supply the supply to add
     */
    public void addPersonalBelonging(Supply supply) {
        if (this.personalBelongings == null) {
            Supply tmpSupply[] = { supply };
            this.setPersonalBelongings(tmpSupply);
            return;
        }

        // Create an array one larger than the previous array
        int newLength = this.personalBelongings.length + 1;
        Supply tmpPersonalBelongings[] = new Supply[newLength];

        // Copy all the items in the current array to the new array
        int i;
        for (i=0; i < personalBelongings.length; i++) {
            tmpPersonalBelongings[i] = this.personalBelongings[i];
        }

        // Add the new element at the end of the new array
        tmpPersonalBelongings[i] = supply;

        // Replace the original array with the new array
        this.personalBelongings = tmpPersonalBelongings;
    }

    /**
     * Removes a supply from the personal belongings of the victim.
     * @param unwantedSupply the supply to remove
     */
    public void removePersonalBelonging(Supply unwantedSupply) {
        Supply[] updatedBelongings = new Supply[personalBelongings.length-1];
        int index = 0;
        int newIndex = index;
        for (Supply supply : personalBelongings) {
            if (!supply.equals(unwantedSupply)) {
                updatedBelongings[newIndex] = supply;
                newIndex++;
            }
            index++;
        }
    }

    /**
     * Removes a family connection from the victim's family connections.
     * @param exRelation the family connection to remove
     */
    public void removeFamilyConnection(FamilyRelation exRelation) {
        familyConnections.remove(exRelation);
    }

    /**
     * Adds a family connection to the victim's family connections.
     * @param record the family connection to add
     */
    public void addFamilyConnection(FamilyRelation record) {
        familyConnections.add(record);
    }

    /**
     * Adds a medical record to the victim's medical records.
     * @param record the medical record to add
     */
    public void addMedicalRecord(MedicalRecord record) {
        medicalRecords.add(record);
    }

    /**
     * Returns the entry date of the victim.
     * @return the entry date
     */
    public String getEntryDate() {
        return ENTRY_DATE;
    }

    /**
     * Returns the comments associated with the victim.
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * Sets the comments associated with the victim.
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments =  comments;
    }

    /**
     * Returns the gender of the victim.
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the gender of the victim.
     * @param gender the gender to set, acceptable values are "male", "female", or "other"
     * @throws IllegalArgumentException if the gender is not one of the acceptable values
     */
    public void setGender(String gender) {
        if (!gender.matches("(?i)^(male|female|other)$")) {
            throw new IllegalArgumentException("Invalid gender. Acceptable values are male, female, or other.");
        }
        this.gender = gender.toLowerCase(); // Store in a consistent format
    }
}





