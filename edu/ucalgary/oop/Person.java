/**
 * Represents a person with a first name and last name
 * 
 * @author Deven Powell
 * @version 1.0
 * @since 2024-03-18
 */

package edu.ucalgary.oop;

/**
 * Represents a person with a first name and last name
 */
public class Person {
    private String firstName;
    private String lastName;

    
    /**
     * Constructs a Person object with the specified first name
     * 
     * @param firstName the first name of the person
     */
    public Person(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the first name of the person.
     * 
     * @return the first name of the person
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the person.
     * 
     * @param firstName the first name of the person
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name of the person.
     * 
     * @return the last name of the person
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the person.
     * 
     * @param lastName the last name of the person
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    
}