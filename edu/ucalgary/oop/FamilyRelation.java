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
     * @throws IllegalArgumentException If the two disaster victims are the same person.
     */
    public FamilyRelation(DisasterVictim personOne, String relationshipTo, DisasterVictim personTwo) {
        if (personOne.getAssignedSocialID() == personTwo.getAssignedSocialID()) {
            throw new IllegalArgumentException("The two disaster victims cannot be the same person.");
        }
        this.personOne = personOne;
        this.relationshipTo = relationshipTo;
        this.personTwo = personTwo;
        personOne.addFamilyConnection(this);
        personTwo.addFamilyConnection(this);
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

    /**
     * Indicates whether some other object is "equal to" this one.
     * 
     * @param obj the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        FamilyRelation that = (FamilyRelation) obj;
        return (personOne.equals(that.personOne) && personTwo.equals(that.personTwo)) ||
           (personOne.equals(that.personTwo) && personTwo.equals(that.personOne));
    }

    /**
     * Returns the hash code value for this FamilyRelation object.
     * The hash code is calculated by adding the hash codes of the personOne and personTwo objects.
     *
     * @return the hash code value for this FamilyRelation object.
     */
    @Override
    public int hashCode() {
        return personOne.hashCode() + personTwo.hashCode();
    }
    
}
