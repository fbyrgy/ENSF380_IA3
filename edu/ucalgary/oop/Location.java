/**
 * The Location class represents a physical location.
 * It stores information about the location, such as its name and address.
 * The class also maintains lists of occupants and supplies associated with the location.
 * @author Deven Powell
 * @version 1.0
 * @since 2024-03-18
 */

package edu.ucalgary.oop;
import java.util.ArrayList;


public class Location {
    private String name;
    private String address;
    private ArrayList<DisasterVictim> occupants = new ArrayList<>(); 
    private ArrayList<Supply> supplies = new ArrayList<>(); 
    private static int counter = 0;
    private final int LOCATION_ID;

    /**
     * Constructs a Location object with the specified name and address.
     *
     * @param name    the name of the location
     * @param address the address of the location
     */
    public Location(String name, String address) {
        this.name = name;
        this.address = address;
        LOCATION_ID = generateLocationID();
    }

    /**
     * Generates a unique location ID.
     *
     * @return The generated location ID.
     */
    private static int generateLocationID() {
        counter++;
        return counter;
    }

    /**
     * Returns the ID of the location.
     *
     * @return the ID of the location
     */
    public int getLocationID() {
        return LOCATION_ID;
    }

    /**
     * Returns the name of the location.
     *
     * @return the name of the location
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the location.
     *
     * @param name the name of the location
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the address of the location.
     *
     * @return the address of the location
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the location.
     *
     * @param address the address of the location
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns a copy of the occupants list.
     *
     * @return a copy of the occupants list
     */
    public ArrayList<DisasterVictim> getOccupants() {
        return new ArrayList<>(occupants);
    }

    /**
     * Sets the occupants list.
     *
     * @param occupants the occupants list
     */
    public void setOccupants(ArrayList<DisasterVictim> occupants) {
        this.occupants = new ArrayList<>(occupants);
    }

    /**
     * Returns a copy of the supplies list.
     *
     * @return a copy of the supplies list
     */
    public ArrayList<Supply> getSupplies() {
        return supplies;
    }

    /**
     * Sets the supplies list.
     *
     * @param supplies the supplies list
     */
    public void setSupplies(ArrayList<Supply> supplies) {
        this.supplies = new ArrayList<>(supplies);
    }

    /**
     * Adds an occupant to the occupants list.
     *
     * @param occupant the occupant to be added
     */
    public void addOccupant(DisasterVictim occupant) {
        occupants.add(occupant);
    }

    /**
     * Removes an occupant from the occupants list.
     *
     * @param occupant the occupant to be removed
     */
    public void removeOccupant(DisasterVictim occupant) {
        occupants.remove(occupant);
    }

    /**
     * Adds a supply to the supplies list.
     *
     * @param supply the supply to be added
     */
    public void addSupply(Supply supply) {
        boolean found = false;
        for (Supply s : supplies) {
            if (s.getType() == supply.getType()) {
                s.setQuantity(s.getQuantity() + supply.getQuantity());
                found = true;
                break;
            }
        }
        if (!found) {
            supplies.add(supply);
        }
    }

    /**
     * Removes a supply from the supplies list.
     *
     * @param supply the supply to be removed
     */
    public void removeSupply(Supply unwantedSupply) {
        for (Supply s : supplies) {
            if (s.getType() == unwantedSupply.getType()) {
                s.setQuantity(s.getQuantity() - unwantedSupply.getQuantity());
                if (s.getQuantity() <= 0) {
                    supplies.remove(s);
                }
                break;
            }
        }

    }

    /**
     * Returns the disaster victim with the given social ID.
     *
     * @param socialID the socialID to check for
     * @return the disaster victim with the given social ID
     * @throws IllegalArgumentException if the social ID does not match any disaster victim
     */
    public DisasterVictim getDisasterVictimFromID(int socialID) {
        for (DisasterVictim victim : occupants) {
            if (victim.getAssignedSocialID() == socialID) {
                return victim;
            }
        }
        throw new IllegalArgumentException("Social ID does not match any disaster victim");
    }
}
