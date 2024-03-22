/**
 * Represents a family relation between disaster victims.
 * 
 * @author Deven Powell
 * @version 1.0
 * @since 2024-03-18
 */


package edu.ucalgary.oop;


/**
 * Represents a family relation between two disaster victims.
 */
public class FamilyRelation {
    private DisasterVictim personOne;
    private String relationshipTo;
    private DisasterVictim personTwo;

    /**
     * Constructs a FamilyRelation object with the specified disaster victims and relationship.
     *
     * @param personOne The first disaster victim.
     * @param relationshipTo The relationship between the two disaster victims.
     * @param personTwo The second disaster victim.
     */
    public FamilyRelation(DisasterVictim personOne, String relationshipTo, DisasterVictim personTwo) {
        this.personOne = personOne;
        this.relationshipTo = relationshipTo;
        this.personTwo = personTwo;
    }

    /**
     * Gets the first disaster victim.
     *
     * @return The first disaster victim.
     */
    public DisasterVictim getPersonOne() {
        return personOne;
    }

    /**
     * Sets the first disaster victim.
     *
     * @param personOne The first disaster victim.
     */
    public void setPersonOne(DisasterVictim personOne) {
        this.personOne = personOne;
    }

    /**
     * Gets the relationship between the two disaster victims.
     *
     * @return The relationship between the two disaster victims.
     */
    public String getRelationshipTo() {
        return relationshipTo;
    }

    /**
     * Sets the relationship between the two disaster victims.
     *
     * @param relationshipTo The relationship between the two disaster victims.
     */
    public void setRelationshipTo(String relationshipTo) {
        this.relationshipTo = relationshipTo;
    }

    /**
     * Gets the second disaster victim.
     *
     * @return The second disaster victim.
     */
    public DisasterVictim getPersonTwo() {
        return personTwo;
    }

    /**
     * Sets the second disaster victim.
     *
     * @param personTwo The second disaster victim.
     */
    public void setPersonTwo(DisasterVictim personTwo) {
        this.personTwo = personTwo;
    }
}
