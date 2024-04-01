/**
 * Represents a victim of a disaster with a first name, last name, gender, comments, among other attributes.
 * 
 * @author Deven Powell
 * @version 1.0
 * @since 2024-03-18
 */

package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a victim of a disaster.
 */
public class DisasterVictim extends Person implements Names {
    private static int counter = 0;

    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private Integer approximateAge;
    private final int ASSIGNED_SOCIAL_ID;
    private ArrayList<FamilyRelation> familyConnections = new ArrayList<>();
    private ArrayList<MedicalRecord> medicalRecords = new ArrayList<>();
    private ArrayList<Supply> personalBelongings = new ArrayList<>();
    private final String ENTRY_DATE;
    private String gender;
    private String comments;
    private ArrayList<DietaryRestrictions> dietaryRestrictions = new ArrayList<>();
    private Location location;

    /**
     * Loads the gender options from a file and returns them as a set of strings.
     *
     * @return a set of strings containing the gender options
     */
    private static Set<String> loadGenderOptions() {
        Set<String> genderOptions = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("GenderOptions.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                genderOptions.add(line.trim().toLowerCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return genderOptions;
    }

    private static final Set<String> GENDER_OPTIONS = loadGenderOptions();

    /**
     * Constructs a new DisasterVictim object with the given first name and entry
     * date.
     * 
     * @param firstName  the first name of the victim
     * @param ENTRY_DATE the entry date of the victim in the format "YYYY-MM-DD"
     * @throws IllegalArgumentException if the entry date has an invalid format or is in the future or if the year is before 2020
     */
    public DisasterVictim(String firstName, String ENTRY_DATE) {
        super(firstName); // Add constructor call to the superclass Person with the firstName argument
        this.firstName = firstName;
        if (!isValidDateFormat(ENTRY_DATE)) {
            throw new IllegalArgumentException("Invalid date format for entry date. Expected format: YYYY-MM-DD");
        }
        LocalDate entryDate = LocalDate.parse(ENTRY_DATE, DateTimeFormatter.ISO_LOCAL_DATE);
        if (entryDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Entry date cannot be in the future");
        }
        if (entryDate.getYear() < 2020) {
            throw new IllegalArgumentException("Entry date cannot be before 2020");
        }
        this.ENTRY_DATE = ENTRY_DATE;
        this.ASSIGNED_SOCIAL_ID = generateSocialID();
    }

    /**
     * Constructs a new DisasterVictim object with the given first name and entry
     * date.
     * 
     * @param firstName  the first name of the victim
     * @param ENTRY_DATE the entry date of the victim in the format "YYYY-MM-DD"
     * @param location   the location of the victim
     * @throws IllegalArgumentException if the entry date has an invalid format or is in the future or if the year is before 2020
     */
    public DisasterVictim(String firstName, String ENTRY_DATE, Location location) {
        super(firstName); // Add constructor call to the superclass Person with the firstName argument
        this.firstName = firstName;
        if (!isValidDateFormat(ENTRY_DATE)) {
            throw new IllegalArgumentException("Invalid date format for entry date. Expected format: YYYY-MM-DD");
        }
        LocalDate entryDate = LocalDate.parse(ENTRY_DATE, DateTimeFormatter.ISO_LOCAL_DATE);
        if (entryDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Entry date cannot be in the future");
        }
        if (entryDate.getYear() < 2020) {
            throw new IllegalArgumentException("Entry date cannot be before 2020");
        }
        this.ENTRY_DATE = ENTRY_DATE;
        this.ASSIGNED_SOCIAL_ID = generateSocialID();
        this.location = location;
        location.addOccupant(this); // Add the DisasterVictim to the location's occupants
    }

    /**
     * Generates a unique social ID for a disaster victim.
     * 
     * @return the generated social ID
     */
    private static int generateSocialID() {
        counter++;
        return counter;
    }

    /**
     * Checks if the given date string is in a valid date format (yyyy-MM-dd).
     *
     * @param date the date string to be validated
     * @return true if the date string is in a valid format, false otherwise
     */
    private static boolean isValidDateFormat(String date) {

        try {
            LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns the first name of the victim.
     * 
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the victim.
     * 
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name of the victim.
     * 
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the victim.
     * 
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the date of birth of the victim.
     * 
     * @return the date of birth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the date of birth of the victim.
     * 
     * @param dateOfBirth the date of birth to set in the format "YYYY-MM-DD"
     * @throws IllegalArgumentException if the date of birth has an invalid format
     * @throws IllegalStateException    if the approximate age is already set
     */
    public void setDateOfBirth(String dateOfBirth) {
        if (this.approximateAge != null) {
            throw new IllegalStateException("Cannot set date of birth when approximate age is already set");
        }
        if (!isValidDateFormat(dateOfBirth)) {
            throw new IllegalArgumentException("Invalid date format for date of birth. Expected format: YYYY-MM-DD");
        }
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Returns the approximate age of the victim.
     * 
     * @return the approximate age
     */
    public Integer getApproximateAge() {
        return approximateAge;
    }

    /**
     * Sets the approximate age of the victim.
     * 
     * @param approximateAge the approximate age to set
     * @throws IllegalStateException if the date of birth is already set
     * @throws IllegalArgumentException if the approximate age is not between 0 and 150
     */
    public void setApproximateAge(Integer approximateAge) {
        if (this.dateOfBirth != null) {
            throw new IllegalStateException("Cannot set approximate age when date of birth is already set");
        } else if (approximateAge < 0 || approximateAge > 150) {
            throw new IllegalArgumentException("Invalid approximate age. Expected value between 0 and 150");
        }
        this.approximateAge = approximateAge;
    }

    /**
     * Returns the assigned social ID of the victim.
     * 
     * @return the assigned social ID
     */
    public int getAssignedSocialID() {
        return ASSIGNED_SOCIAL_ID;
    }

    /**
     * Returns an array of family connections of the victim.
     * 
     * @return an array of family connections
     */
    public ArrayList<FamilyRelation> getFamilyConnections() {
        return this.familyConnections;
    }

    /**
     * Returns an array of medical records of the victim.
     * 
     * @return an array of medical records
     */
    public ArrayList<MedicalRecord> getMedicalRecords() {
        return this.medicalRecords;
    }

    /**
     * Returns an array of personal belongings of the victim.
     * 
     * @return an array of personal belongings
     */
    public ArrayList<Supply> getPersonalBelongings() {
        return this.personalBelongings;
    }

    // The add and remove methods remain correct.

    /**
     * Sets the family connections of the victim.
     * 
     * @param connections the family connections to set
     */
    public void setFamilyConnections(ArrayList<FamilyRelation> connections) {
        this.familyConnections.clear();
        for (FamilyRelation newRecord : connections) {
            addFamilyConnection(newRecord);
        }
    }

    /**
     * Sets the medical records of the victim.
     * 
     * @param records the medical records to set
     */
    public void setMedicalRecords(ArrayList<MedicalRecord> records) {
        this.medicalRecords.clear();
        for (MedicalRecord newRecord : records) {
            addMedicalRecord(newRecord);
        }
    }

    /**
     * Adds a personal belonging to the disaster victim's personal belongings list.
     * The personal belonging can only be added if the location is set, the supply
     * is available at the location,
     * and there is enough quantity of the supply.
     *
     * @param supply   the supply to be added as a personal belonging
     * @param quantity the quantity of the supply to add
     * @throws IllegalStateException    if the location is not set
     * @throws IllegalArgumentException if the supply is not available at the
     *                                  location or there is not enough quantity
     */
    public void addPersonalBelonging(Supply supply) {
        if (location == null) {
            throw new IllegalStateException("Location must be set before adding personal belongings");
        }
        boolean supplyAvailable = false;
        for (Supply locationSupply : location.getSupplies()) {
            if (locationSupply.getType().toLowerCase().equals(supply.getType().toLowerCase())) {
                supplyAvailable = true;
                int quantity = supply.getQuantity();
                if (locationSupply.getQuantity() >= quantity) {
                    personalBelongings.add(supply);
                    locationSupply.setQuantity(locationSupply.getQuantity() - quantity); // Removing the quantity from location
                                                                                         
                    break;
                } else {
                    throw new IllegalArgumentException("Not enough quantity of the supply available at the location");
                }
            }
        }
        if (!supplyAvailable) {
            throw new IllegalArgumentException("Supply is not available at the location");
        }
    }

    /**
     * Removes a supply from the personal belongings of the victim.
     * 
     * @param unwantedSupply the supply to remove
     */
    public void removePersonalBelonging(Supply unwantedSupply) {
        ArrayList<Supply> updatedBelongings = new ArrayList<>();
        for (Supply supply : personalBelongings) {
            if (!supply.getType().equals(unwantedSupply.getType())) {
                updatedBelongings.add(supply);
            } else {
                if (supply.getQuantity() > unwantedSupply.getQuantity()) {
                    // The case where there is still supply left for the DisasterVictim
                    supply.setQuantity(supply.getQuantity() - unwantedSupply.getQuantity());
                    updatedBelongings.add(supply);
                }
            }
        }
        personalBelongings = updatedBelongings;
    }

    /**
     * Removes a family connection from the victim's family connections.
     * 
     * @param exRelation the family connection to remove
     */
    public void removeFamilyConnection(FamilyRelation exRelation) {
        familyConnections.remove(exRelation);
    }

    /**
     * Adds a family connection to the victim's family connections.
     * 
     * @param record the family connection to add
     */
    public void addFamilyConnection(FamilyRelation record) {
        familyConnections.add(record);
    }

    /**
     * Adds a medical record to the victim's medical records.
     * 
     * @param record the medical record to add
     */
    public void addMedicalRecord(MedicalRecord record) {
        medicalRecords.add(record);
    }

    /**
     * Returns the entry date of the victim.
     * 
     * @return the entry date
     */
    public String getEntryDate() {
        return ENTRY_DATE;
    }

    /**
     * Returns the comments associated with the victim.
     * 
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * Sets the comments associated with the victim.
     * 
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * Returns the gender of the victim.
     * 
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    public static Set<String> getGenderOptions() {
        return GENDER_OPTIONS;
    }

    /**
     * Sets the gender of the victim.
     * 
     * @param gender the gender to set, must be one of the options specified in the
     *               "GenderOptions.txt" file
     * @throws IllegalArgumentException if the gender is not one of the acceptable
     *                                  values
     */
    public void setGender(String gender) {
        if (!GENDER_OPTIONS.contains(gender.toLowerCase())) {
            throw new IllegalArgumentException("Invalid gender. Acceptable values are: " + GENDER_OPTIONS);
        }
        this.gender = gender.toLowerCase(); // Store in a consistent format
    }

    /**
     * Returns the dietary restrictions of the victim.
     * 
     * @return a set of DietaryRestrictions
     */
    public ArrayList<DietaryRestrictions> getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    /**
     * Adds a dietary restriction to the victim's set of dietary restrictions.
     * 
     * @param restriction the dietary restriction to add
     * @throws IllegalArgumentException if the restriction is not in the
     *                                  DietaryRestrictions enum
     */
    public void addDietaryRestriction(DietaryRestrictions restriction) {
        if (!Arrays.asList(DietaryRestrictions.values()).contains(restriction)) {
            throw new IllegalArgumentException("Invalid dietary restriction. Acceptable values are: "
                    + Arrays.toString(DietaryRestrictions.values()));
        }
        this.dietaryRestrictions.add(restriction);
    }

    /**
     * Removes a dietary restriction from the victim's set of dietary restrictions.
     * 
     * @param restriction the dietary restriction to remove
     */
    public void removeDietaryRestriction(DietaryRestrictions restriction) {
        this.dietaryRestrictions.remove(restriction);
    }

    /**
     * Sets the location of the disaster victim.
     * 
     * @param location the location to set
     */
    public void setLocation(Location location) {
        this.location = location;
        location.addOccupant(this); // Add the DisasterVictim to the location's occupants
    }

    /**
     * Returns the location of the disaster victim.
     * 
     * @return the location
     */
    public Location getLocation() {
        return location;
    }
}
