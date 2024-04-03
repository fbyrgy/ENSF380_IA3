/**
 * This file contains the test cases for the User Interface class.
 * It tests the sesrch for victim method.
 * 
 * @author Deven Powell
 * @version 1.0
 * @since 2024-04-3
 */
package edu.ucalgary.oop;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;

public class UserInterfaceTest {
    ReliefService reliefService;
    Location location;
    DisasterVictim victim;
    DisasterVictim victim2;

    @Before
    public void setUp() {
        reliefService = new ReliefService();
        Location location = new Location ("location","address");
        ReliefService.addLocation(location);
        victim = new DisasterVictim("Praveen","2023-01-01",location);
        victim2 = new DisasterVictim("Oprah", "2023-01-01", location);
    }

    /**
     * Test case for the searchForVictim method in the UserInterface class.
     * It verifies that the searchForVictim method returns the expected list of victims
     * when searching for a specific query.
     */
    @Test
    public void testSearchForVictim() {
        String query = "Pra";
        ArrayList<DisasterVictim> expectedVictims = new ArrayList<>();
        expectedVictims.add(victim);
        expectedVictims.add(victim2);
        assertEquals("The expected results should contain victim and victim2", expectedVictims, UserInterface.searchForVictim(query));
    }
}