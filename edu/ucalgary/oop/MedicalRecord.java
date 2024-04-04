/**
 * Represents a medical record containing information about a patient's treatment.
 * @author Deven Powell
 * @version 1.0
 * @since 2024-03-18
 */

package edu.ucalgary.oop;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;



public class MedicalRecord {
    private Location location;
    private String treatmentDetails;
    private String dateOfTreatment;

    /**
     * Constructs a MedicalRecord object with the specified location, treatment details, and date of treatment.
     *
     * @param location         the location where the treatment took place
     * @param treatmentDetails the details of the treatment
     * @param dateOfTreatment  the date of the treatment in the format "YYYY-MM-DD"
     * @throws IllegalArgumentException if the date of treatment is not in the expected format, or does not fall between 2020 and the current date
     */
    public MedicalRecord(Location location, String treatmentDetails, String dateOfTreatment) throws IllegalArgumentException {
        setLocation(location);
        this.treatmentDetails = treatmentDetails;

        // Check if the treatmentDetails string matches the expected date format
        isValidDateFormat(dateOfTreatment);
        this.dateOfTreatment = dateOfTreatment;
    }

    /**
     * Gets the location where the treatment took place.
     *
     * @return the location of the treatment
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the location where the treatment took place.
     *
     * @param location the location of the treatment
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Gets the details of the treatment.
     *
     * @return the treatment details
     */
    public String getTreatmentDetails() {
        return treatmentDetails;
    }

    /**
     * Sets the details of the treatment.
     *
     * @param treatmentDetails the treatment details
     */
    public void setTreatmentDetails(String treatmentDetails) throws IllegalArgumentException {
        this.treatmentDetails = treatmentDetails;
    }

    /**
     * Gets the date of the treatment.
     *
     * @return the date of the treatment in the format "YYYY-MM-DD"
     */
    public String getDateOfTreatment() {
        return dateOfTreatment;
    }

    /**
     * Sets the date of the treatment.
     *
     * @param dateOfTreatment the date of the treatment in the format "YYYY-MM-DD"
     * @throws IllegalArgumentException if the date of treatment is not in the expected format
     */
    public void setDateOfTreatment(String dateOfTreatment) {
        // Check if the date of treatment string matches the expected date format
        isValidDateFormat(dateOfTreatment);
        this.dateOfTreatment = dateOfTreatment;
    }

    /**
     * Checks if a string matches the "YYYY-MM-DD" date format.
     *
     * @param date the date string to be checked
     * @return true if the date string matches the expected format, false otherwise
     */
    private static void isValidDateFormat(String date) {
        try {
            LocalDate parsedDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
            LocalDate currentDate = LocalDate.now();
            LocalDate startOf2020 = LocalDate.of(2020, 1, 1);

            if (parsedDate.isBefore(startOf2020) || parsedDate.isAfter(currentDate)) {
                throw new IllegalArgumentException("Date must be between 2020 and the current date.");
            }

        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Expected format: YYYY-MM-DD.");
        }
    }
}
