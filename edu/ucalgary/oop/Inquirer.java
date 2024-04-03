/**
 * The Inquirer class represents a person who is making an inquiry.
 * It stores information about the inquirer, such as their first name,
 * last name, phone number, and additional information.
 * @author Deven Powell
 * @version 1.0
 * @since 2024-03-18
 */

 package edu.ucalgary.oop;
import java.util.ArrayList;

public class Inquirer {
    private final String FIRST_NAME;
    private  String lastName;
    private final String INFO;
    private final String SERVICES_PHONE;
    private ArrayList<String> interactions = new ArrayList<>();

    /**
     * Constructs a new Inquirer object with the specified information.
     *
     * @param firstName the first name of the inquirer
     * @param lastName the last name of the inquirer
     * @param phone the phone number of the inquirer
     * @param info additional information about the inquirer
     */
    public Inquirer(String firstName, String lastName, String phone, String info) {
        this.FIRST_NAME = firstName;
        this.lastName = lastName;
        this.SERVICES_PHONE = phone;
        this.INFO = info;
        
    }

    /**
     * Constructs a new Inquirer object with the specified information.
     *
     * @param firstName the first name of the inquirer
     * @param lastName the last name of the inquirer
     * @param phone the phone number of the inquirer
     */
    public Inquirer(String firstName, String lastName, String phone) {
        this.FIRST_NAME = firstName;
        this.lastName = lastName;
        this.SERVICES_PHONE = phone;
        this.INFO = "";
    }

    /**
     * Returns the first name of the inquirer.
     *
     * @return the first name of the inquirer
     */
    public String getFirstName() { return this.FIRST_NAME; }

    /**
     * Returns the last name of the inquirer.
     *
     * @return the last name of the inquirer
     */
    public String getLastName() { return this.lastName; }

    /**
     * Returns the phone number of the inquirer.
     *
     * @return the phone number of the inquirer
     */
    public String getServicesPhoneNum() { return this.SERVICES_PHONE; }

    /**
     * Returns additional information about the inquirer.
     *
     * @return additional information about the inquirer
     */
    public String getInfo() { return this.INFO; }

    /**
     * Returns a list of interactions with the inquirer.
     *
     * @return a list of interactions with the inquirer
     */
    public ArrayList<String> getInteractions() { return this.interactions; }

    /**
     * Adds an interaction to the list of interactions with the inquirer.
     *
     * @param interaction the interaction to add
     */
    public void addInteraction(String interaction) {
        this.interactions.add(interaction);
    }
}
