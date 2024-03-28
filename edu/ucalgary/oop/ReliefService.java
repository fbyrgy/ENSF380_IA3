/**
 * The ReliefService class represents a relief service for disaster victims.
 * It provides methods to manage and retrieve information about the relief service.
 * @author Deven Powell
 * @version 1.0
 * @since 2024-03-18
 */

package edu.ucalgary.oop;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ReliefService {
    private Inquirer inquirer;
    private DisasterVictim missingPerson;
    private String dateOfInquiry;
    private String infoProvided;
    private Location lastKnownLocation;
    private ArrayList<Inquirer> inquirerList = new ArrayList<>();

    /**
     * Constructs a ReliefService object with the specified parameters.
     * 
     * @param inquirer          the inquirer associated with the relief service
     * @param missingPerson     the missing person associated with the relief service
     * @param dateOfInquiry     the date of the inquiry in the format "YYYY-MM-DD"
     * @param infoProvided      the information provided by the inquirer
     * @param lastKnownLocation the last known location of the missing person
     */
    public ReliefService(Inquirer inquirer, DisasterVictim missingPerson, String dateOfInquiry, String infoProvided,
            Location lastKnownLocation) {
        this.inquirer = inquirer;
        this.missingPerson = missingPerson;
        setDateOfInquiry(dateOfInquiry);
        this.infoProvided = infoProvided;
        this.lastKnownLocation = lastKnownLocation;
        inquirerList.add(inquirer);
    }

    /**
     * Returns the inquirer associated with the relief service.
     * 
     * @return the inquirer associated with the relief service
     */
    public Inquirer getInquirer() {
        return inquirer;
    }

    /**
     * Sets the inquirer associated with the relief service.
     * If the inquirer is already in the inquirerList, it sets it to that inquirer.
     * 
     * @param inquirer the inquirer to be set
     */
    public void setInquirer(Inquirer inquirer) {
        if (inquirerList.contains(inquirer)) {
            this.inquirer = inquirer;
        } else {
            inquirerList.add(inquirer);
            this.inquirer = inquirer;
        }
    }

    /**
     * Returns the missing person associated with the relief service.
     * 
     * @return the missing person associated with the relief service
     */
    public DisasterVictim getMissingPerson() {
        return missingPerson;
    }

    /**
     * Sets the missing person associated with the relief service.
     * 
     * @param missingPerson the missing person to be set
     */
    public void setMissingPerson(DisasterVictim missingPerson) {
        this.missingPerson = missingPerson;
    }

    /**
     * Returns the date of the inquiry in the format "YYYY-MM-DD".
     * 
     * @return the date of the inquiry
     */
    public String getDateOfInquiry() {
        return dateOfInquiry;
    }

    /**
     * Sets the date of the inquiry.
     * 
     * @param dateOfInquiry the date of the inquiry in the format "YYYY-MM-DD"
     * @throws IllegalArgumentException if the date format is invalid
     */
    public void setDateOfInquiry(String dateOfInquiry) {
        // Check if the dateOfInquiry string matches the expected date format
        if (!isValidDateFormat(dateOfInquiry)) {
            throw new IllegalArgumentException("Invalid date format for date of inquiry. Expected format: YYYY-MM-DD");
        }
        this.dateOfInquiry = dateOfInquiry;
    }

    /**
     * Returns the information provided by the inquirer.
     * 
     * @return the information provided by the inquirer
     */
    public String getInfoProvided() {
        return infoProvided;
    }

    /**
     * Sets the information provided by the inquirer.
     * 
     * @param infoProvided the information provided by the inquirer
     */
    public void setInfoProvided(String infoProvided) {
        this.infoProvided = infoProvided;
    }

    /**
     * Returns the last known location of the missing person.
     * 
     * @return the last known location of the missing person
     */
    public Location getLastKnownLocation() {
        return lastKnownLocation;
    }

    /**
     * Sets the last known location of the missing person.
     * 
     * @param lastKnownLocation the last known location of the missing person
     */
    public void setLastKnownLocation(Location lastKnownLocation) {
        this.lastKnownLocation = lastKnownLocation;
    }

    /**
     * Checks if a string matches the "YYYY-MM-DD" date format.
     * 
     * @param date the string to be checked
     * @return true if the string matches the date format, false otherwise
     */
    private boolean isValidDateFormat(String date) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Returns a string representation of the log details of the relief service.
     * 
     * @return a string representation of the log details
     */
    public String getLogDetails() {
        return "Inquirer: " + inquirer.getFirstName() + ", Missing Person: " + missingPerson.getFirstName()
                + ", Date of Inquiry: " + dateOfInquiry + ", Info Provided: " + infoProvided + ", Last Known Location: "
                + lastKnownLocation.getName();
    }

    /**
     * Returns a list of inquirers associated with the relief service.
     * 
     * @return a list of inquirers associated with the relief service
     */
    public ArrayList<Inquirer> getInquirerList() {
        return inquirerList;
    
    }

    /**
     * Checks if the specified inquirer exists in the inquirer list.
     * 
     * @param inquirer the inquirer to check
     * @return true if the inquirer exists in the list, false otherwise
     */
    public boolean inquirerExists(Inquirer inquirer) {
        return inquirerList.contains(inquirer);
    }
}
