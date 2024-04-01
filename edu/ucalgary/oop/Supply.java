/**
 * The Supply class represents a supply item with a type and quantity.
 *
 * @author Deven Powell
 * @version 1.0
 * @since 2024-03-18
 */

package edu.ucalgary.oop;


public class Supply {
    private String type;
    private int quantity;

    /**
     * Constructs a new Supply object with the specified type and quantity.
     *
     * @param type     the type of the supply
     * @param quantity the quantity of the supply
     * @throws IllegalArgumentException if the quantity is negative
     */
    public Supply(String type, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("The quantity of a supply cannot be negative.");
        }
        this.type = capitalizeFirstChar(type);
        this.quantity = quantity;
    }

    /**
     * Capitalizes the first character of a given string and converts the rest of the characters to lowercase.
     *
     * @param str the string to be capitalized
     * @return the capitalized string
     */
    public static String capitalizeFirstChar(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1).toLowerCase();
    }

    /**
     * Sets the type of the supply.
     *
     * @param type the type of the supply
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Sets the quantity of the supply.
     *
     * @param quantity the quantity of the supply
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns the type of the supply.
     *
     * @return the type of the supply
     */
    public String getType() {
        return this.type;
    }

    /**
     * Returns the quantity of the supply.
     *
     * @return the quantity of the supply
     */
    public int getQuantity() {
        return this.quantity;
    }
}

