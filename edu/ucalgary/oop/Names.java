/**
 * Represents a names interface for the first and last name of the victim.
 * 
 * @author Deven Powell
 * @version 1.0
 * @since 2024-03-18
 */

package edu.ucalgary.oop;

/**
 * Names interface for the first and last name of the victim.
 *
 */
public interface Names {
    
    /**
     * Returns the first name of the victim.
     * @return the first name
     */
    public String getFirstName();

    /**
     * Sets the first name of the victim.
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName);

    /**
     * Returns the last name of the victim.
     * @return the last name
     */
    public String getLastName();

    /**
     * Sets the last name of the victim.
     * @param lastName the last name to set
     */
    public void setLastName(String lastName);
}