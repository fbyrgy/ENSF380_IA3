/**
 * The UserInterface class provides a text-based user interface for the ReliefService class.
 *
 * @author Deven Powell
 * @version 1.0
 * @since 2024-03-31
 */

package edu.ucalgary.oop;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;

@SuppressWarnings("resource")
public class UserInterface {

    // Seting up the database connection
    private static DatabaseConnection connection = new DatabaseConnection();

    /**
     * Prompts the user to enter an integer input.
     * 
     * @return the integer input
     */
    public static int getIntegerInput() {
        
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid integer");
            scanner.nextLine();
        }
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

     /**
      * Adds a disaster victim to the specified location.
      * 
      * @return the disaster victim that was added
      */
     public static DisasterVictim enterDisasterVictim(int locationID) {
         Scanner scanner = new Scanner(System.in);
         DisasterVictim victim = null;
 
         System.out.println("Please enter the first name of the disaster victim (or enter '-1' to go back):");
         String firstName = scanner.nextLine();
         if (firstName.equals("-1")) {
             throw new IllegalArgumentException("No disaster victim added");
         }
         String entryDate = "";
         boolean validDate = false;
 
         while (!validDate) {
             System.out.println("Please press 'enter' to use the current date or enter the date of entry in the format 'YYYY-MM-DD' (or enter '-1' to go back):");
             entryDate = scanner.nextLine();
 
             if (entryDate.isEmpty()) {
                 entryDate = LocalDate.now().toString();
                 System.out.println("Date successfully set");
                 validDate = true;
             } else if (entryDate.equals("-1")) {
                 throw new IllegalArgumentException("No disaster victim added");
             }
           
            try {
                victim = new DisasterVictim(firstName, entryDate);
                validDate = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + ". Please try again");
                }
                
         }
         
        Location location = null;
        while (location == null) {
            if (locationID == -1) {
                System.out.println("Please enter the locationID of the location that you would like to add the disaster victim to from the following:");
                ReliefService.displayLocation();
                locationID = getIntegerInput();
            }
            try {
                location = ReliefService.getLocationFromID(locationID);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                locationID = -1;
            }
        }
         victim.setLocation(location);

         return victim;
     }
 
     /**
      * Allows the user to add a new Disaster Victiom, or edit information about a
      * Disaster Victim.
      *
      * @param locationID the ID of the location to add or edit a disaster victim. This will be -1 if it is not known yet.
      */
     public static void editDisasterVictims(int locationID) {
         Scanner scanner = new Scanner(System.in);
 
         while (true) {
            System.out.println("Please enter '1' if you would like to enter a new Disaster Victim");
            System.out.println("Please enter '2' if you would like to edit the information for a Disaster Victim");
            System.out.println("Please enter '-1' to go back");
            {
                 int userInput = getIntegerInput();

 
                 if (userInput == 1) {
                     // Entering new Disaster Victim
                     try{
                        enterDisasterVictim(locationID);
                     } catch (IllegalArgumentException e) {
                         System.out.println(e.getMessage());
                         return;
                     }
                     System.out.println("Disaster Victim added successfully. You can now edit the disaster victim by entering '2'");
                     continue;
 
                 } else if (userInput == 2) {
                     // Editing information for a Disaster Victim
                     Location location = null;
                     while (location == null) {
                         if (locationID == -1) {
                             System.out.println("Please enter the locationID of the location that you would like to access (or enter '-1' to go back):");
                             ReliefService.displayLocation();
                             locationID = getIntegerInput();
                                if (locationID == -1) {
                                    return; // If the user wants to go back
                                }
                         }
                         try {
                             location = ReliefService.getLocationFromID(locationID);
                         } catch (IllegalArgumentException e) {
                             System.out.println(e.getMessage());
                             locationID = -1;
                         }
                     }
                     
                     DisasterVictim victim = null;
                     int socialID = 0;
                        while (victim == null) {
                            System.out.println("Please enter the social ID of the disaster victim that you would like to edit from the following (or enter '-1' to go back):");
                            ReliefService.displayDisasterVictims(locationID);
                            socialID = getIntegerInput();
                            if (socialID == -1) {
                                return; // If the user wants to go back
                            }
                            try {
                                victim = location.getDisasterVictimFromID(socialID);
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            }
                        }

                     boolean sameVictim = true;
                     while(sameVictim){
                        System.out.println("Current Disaster Victim: " + victim.getFirstName() +" with social ID: " + victim.getAssignedSocialID());
                        System.out.println("Please enter '1' if you would like to edit the first name of the disaster victim");
                        System.out.println("Please enter '2' if you would like to edit the last name of the disaster victim");
                        System.out.println("Please enter '3' if you would like to edit the date of birth of the disaster victim");
                        System.out.println("Please enter '4' if you would like to edit the approximate age of the disaster victim");
                        System.out.println("Please enter '5' if you would like to edit the gender of the disaster victim");
                        System.out.println("Please enter '6' if you would like to edit the location of the disaster victim");
                        System.out.println("Please enter '7' if you would like to edit the medical records of the disaster victim");
                        System.out.println("Please enter '8' if you would like to edit the family connections of the disaster victim");
                        System.out.println("Please enter '9' if you would like to edit the dietary restictions of the disaster victim");
                        System.out.println("Please enter '10' if you would like to edit the supplies of the disaster victim");
                        System.out.println("Please enter '11' to display all known disaster victim data");
                        System.out.println("Please enter '-1' to go back");
                        int choice = getIntegerInput();

    
                        // Handling all the choices
                        
                        switch (choice) {
                            case -1:
                                // Exiting
                                sameVictim = false;
                                break;

                            case 1:
                                // Editing first name
                                System.out.println("Please enter the new first name of the disaster victim (or enter '-1' to go back):");
                                String newFirstName = scanner.nextLine();
                                if (newFirstName.equals("-1")) {
                                    System.out.println("First name change cancelled. Going back");
                                    break;
                                }
                                victim.setFirstName(newFirstName);
                                System.out.println("The first name of: " + victim.getAssignedSocialID() + " has been updated to: " + victim.getFirstName());
                                break;
                            case 2:
                                // Editing last name
                                System.out.println("Please enter the new last name of the disaster victim (or enter '-1' to go back):");
                                String newLastName = scanner.nextLine();
                                if (newLastName.equals("-1")) {
                                    System.out.println("Last name change cancelled. Going back");
                                    break;
                                }
                                victim.setLastName(newLastName);
                                System.out.println("The last name of: " + victim.getFirstName() + " has been updated to: " + victim.getLastName());
                                break;
                            case 3:
                                // Editing date of birth
                                boolean validDateOfBirth = false;
                                while (!validDateOfBirth) {
                                    System.out.println("Please enter the new date of birth of the disaster victim in the format 'YYYY-MM-DD' (or enter '-1' to go back):");
                                    String newDateOfBirth = scanner.nextLine();
                                    if (newDateOfBirth.equals("-1")) {
                                        System.out.println("Date of birth change cancelled. Going back");
                                        break;
                                    }
                                    try {
                                        victim.setDateOfBirth(newDateOfBirth);
                                        validDateOfBirth = true;
                                    } catch (IllegalArgumentException e) {
                                        System.out.println("Please try again.");
                                    } catch (IllegalStateException e) {
                                        System.out.println(e.getMessage());
                                        break;
                                    }
                                }
                                if (validDateOfBirth) {
                                    System.out.println("The date of birth of: " + victim.getFirstName() + " has been updated to: " + victim.getDateOfBirth());
                                }
                                break;
                            case 4:
                                // Editing approximate age
                                boolean noError = true;
                                System.out.println("Please enter the new approximate age of the disaster victim (or enter '-1' to go back):");
                                int newAge = getIntegerInput();
                                if (newAge == -1) {
                                    System.out.println("Approximate age change cancelled. Going back");
                                    break;
                                }
                                try{
                                    victim.setApproximateAge(newAge);
                                } catch (IllegalStateException e) {
                                    noError = false;
                                    System.out.println(e.getMessage());

                                } catch (IllegalArgumentException e) {
                                    noError = false;
                                    System.out.println(e.getMessage());
                                    System.out.println("Please try again");
                                }
                                if (noError) {
                                    System.out.println("The approximate age of: " + victim.getFirstName() + " has been updated to: " + victim.getApproximateAge());
                                }
                                break;
                            case 5:
                                // Editing gender
                                boolean validGender = false;
                                while (!validGender) {
                                    try {
                                        System.out.println("Please enter the new gender of the disaster victim (or enter '-1' to go back)");
                                        System.out.println("The valid genders are:" + DisasterVictim.getGenderOptions());
                                        String newGender = scanner.nextLine();
                                        if (newGender.equals("-1")) {
                                            System.out.println("Going back");
                                            break;
                                        }
                                        victim.setGender(newGender);
                                        validGender = true;
                                    } catch (IllegalArgumentException e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                                if (validGender) {
                                    System.out.println("The gender of: " + victim.getFirstName() + " has been updated to: " + victim.getGender());
                                }
                                break;
                            case 6:
                                // Editing location
                                boolean validID = false;
                            
                                while(!validID) {
                                    try{
                                        System.out.println("Please enter the new locationID of the location that you would like to assign to the disaster victim from the following (or enter '-1' to go back):");
                                        ReliefService.displayLocation();
                                        int newLocationID = getIntegerInput();
                                        if (newLocationID == -1) {
                                            break;
                                        }
                                        Location newLocation = ReliefService.getLocationFromID(newLocationID);
                                        victim.setLocation(newLocation);
                                        Location oldLocation = ReliefService.getLocationFromID(locationID);
                                        oldLocation.removeOccupant(victim); // Removing the victim from the old location
                                        validID = true;
                                    } catch (IllegalArgumentException e) {
                                        System.out.println(e.getMessage());
                                        System.out.println("Please try again");
                                    }

                                }
                                if (validID) {
                                System.out.println("The location of: " + victim.getFirstName() + " has been updated to: " + victim.getLocation().getName());
                                }
                                break;
                            case 7:
                                // Editing medical records
                                System.out.println("Please enter the treatment details of the disaster victim (or enter '-1' to go back):");
                                String treatmentDetails = scanner.nextLine();
                                if (treatmentDetails.equals("-1")) {
                                    System.out.println("Medical record cancelled. Going back");
                                    break;
                                }

                                boolean validDate = false;
                                String dateOfTreatment;
                                MedicalRecord newMedicalRecords = null;
                                while (!validDate) {
                                    try {
                                        System.out.println("Please press 'enter' to use the current date or enter the date of treatment in the format 'YYYY-MM-DD' (or enter '-1' to go back):");
                                        dateOfTreatment = scanner.nextLine();
                                        if (dateOfTreatment.equals("-1")) {
                                            System.out.println("Medical record cancelled. Going back");
                                            break;
                                        } else if (dateOfTreatment.isEmpty()) {
                                            dateOfTreatment = LocalDate.now().toString();
                                            System.out.println("Date successfully set");
                                            newMedicalRecords = new MedicalRecord(location, treatmentDetails, dateOfTreatment);
                                            validDate = true;
                                        } else {
                                            newMedicalRecords = new MedicalRecord(location, treatmentDetails, dateOfTreatment);
                                            validDate = true;
                                        }
                                        
                                        victim.addMedicalRecord(newMedicalRecords);
                                        validDate = true;
                                    } catch (IllegalArgumentException e) {
                                        System.out.println(e.getMessage() + " Please try again");
                                    }
                                }
                                if (validDate) {
                                    System.out.println("Medical record added successfully");
                                }
                                break;
                            case 8:
                                // Editing family connections
                                DisasterVictim personOne = null;
                                boolean valid = false;
                                boolean validRelation = false;
                                while(!valid){
                                    System.out.println("Please enter the social ID of the family member that you would like to add from the following (or enter '-1' to go back):");
                                    ReliefService.displayLocationsAndVictims();
                                    int familyMemberID = getIntegerInput();
                                    if (familyMemberID == -1) {
                                        break;
                                    }
                                    for (Location loc : ReliefService.getLocations()) {
                                        // Trying to find the disaster victim by going through every location
                                        try {
                                            personOne = loc.getDisasterVictimFromID(familyMemberID);
                                            valid = true;
                                            break;
                                        } catch (IllegalArgumentException e) {
                                            continue;
                                        }
                                    }
                                    if (!valid) {
                                        System.out.println("Social ID does not match any disaster victim. Please try again");
                                        continue;
                                    }
                                    System.out.println("Please enter the relationship between the disaster victim and the family member (or enter '-1' to go back):");
                                    String relationship = scanner.nextLine();
                                    if (relationship.equals("-1")) {
                                        break;
                                    }
                                    try {
                                        new FamilyRelation(victim, relationship, personOne);
                                        validRelation = true;
                                    } catch (IllegalArgumentException e) {
                                        System.out.println(e.getMessage());
                                        continue;
                                    }
                                    
                                }
                                if(validRelation){
                                    victim.ensureRelationshipConsistency();
                                    System.out.println("Family relation added successfully");
                                }
                                break;
                            
                            case 9:
                                // Editing dietary restrictions
                                int dietaryChoice = 1;
                                if (!victim.getDietaryRestrictions().isEmpty()) {
                                    System.out.println("Please enter '1' if you would like to add a dietary restriction");
                                    System.out.println("Please enter '2' if you would like to remove a dietary restriction");
                                    System.out.println("Please enter '-1' to go back");
                                    dietaryChoice = getIntegerInput();

                                }

                                switch (dietaryChoice) {

                                    case 1:    
                                        // Adding dietary restriction
                                        boolean validRestriction = false;

                                        while(!validRestriction) {
                                            System.out.println("Please enter the 4 character code of the dietary restriction you would like to add for the disaster victim from the following (or enter '-1' to go back):");
                                            System.out.println("AVML - Asian Vegetarian Meal");
                                            System.out.println("DBML - Diabetic Meal");
                                            System.out.println("GFML - Gluten Intolerant Meal");
                                            System.out.println("KSML - Kosher Meal");
                                            System.out.println("LSML - Low Salt Meal");
                                            System.out.println("MOML - Muslim Meal");
                                            System.out.println("PFML - Peanut Free Meal");
                                            System.out.println("VGML - Vegan Meal");
                                            System.out.println("VJML - Vegetarian Jain Meal");
                                            String dietaryRestrictions = scanner.nextLine().toUpperCase();
                                            if (dietaryRestrictions.equals("-1")) {
                                                break;
                                            }
                                            try {
                                                DietaryRestrictions restriction = DietaryRestrictions.valueOf(dietaryRestrictions);
                                                victim.addDietaryRestriction(restriction);
                                                validRestriction = true;
                                            } catch (IllegalArgumentException e) {
                                                System.out.println("Invalid dietary restriction code. Please try again.");
                                            }
                                        }
                                        if (validRestriction) {
                                            System.out.println("Dietary restriction added successfully");
                                        }
                                        break;

                                    case 2:
                                        // Removing dietary restriction
                                        boolean validRemoval = false;

                                        while(!validRemoval) {
                                            System.out.println("Please enter the 4 character code of the dietary restriction you would like to remove for the disaster victim from the following (or enter '-1' to go back):");
                                            for (DietaryRestrictions restriction : victim.getDietaryRestrictions()) {
                                                System.out.println(restriction);
                                            }
                                            String dietaryRestrictions = scanner.nextLine().toUpperCase();
                                            if (dietaryRestrictions.equals("-1")) {
                                                break;
                                            }
                                            try {
                                                DietaryRestrictions restriction = DietaryRestrictions.valueOf(dietaryRestrictions);
                                                victim.removeDietaryRestriction(restriction);
                                                validRemoval = true;
                                            } catch (IllegalArgumentException e) {
                                                System.out.println("Invalid dietary restriction code. Please try again.");
                                            }
                                        }
                                        if (validRemoval) {
                                            System.out.println("Dietary restriction removed successfully");
                                        }

                                    
                                    case -1:
                                        break;
                                    

                                    default:
                                        System.out.println("Invalid choice");
                                        break;
                                }
                                
                                    
                                    break;

                            case 10:
                                // Editing supplies
                                int supplyChoice = 1;
                                if (!victim.getPersonalBelongings().isEmpty()) {
                                    System.out.println("Please enter '1' if you would like to add a supply");
                                    System.out.println("Please enter '2' if you would like to remove a supply");
                                    System.out.println("Please enter '-1' to go back");
                                    supplyChoice = getIntegerInput();
                                }

                                switch (supplyChoice) {

                                case 1:
                                    // Adding supply
                                    boolean validSupply = false;
                                    while(!validSupply) {
                                        System.out.println("Please enter the type of the supply that you would like to add for the disaster victim (or enter '-1' to go back):");
                                        String supplyType = scanner.nextLine();
                                        if (supplyType.equals("-1")) {
                                            break;
                                        }
                                        System.out.println("Please enter the quantity of the supply that you would like to add for the disaster victim (or enter '-1' to go back):");
                                        int supplyQuantity = getIntegerInput();
                                        if (supplyQuantity == -1) {
                                            break;
                                        }
                                        try {
                                            Supply supply = new Supply(supplyType, supplyQuantity);
                                            victim.addPersonalBelonging(supply);
                                            validSupply = true;
                                        } catch (IllegalArgumentException e) {
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                if (validSupply) {
                                    System.out.println("Supply added successfully");
                                }
                                break;
                            
                                case 2:
                                    // Removing supply
                                    boolean validRemoval = false;
                                    while(!validRemoval) {
                                        System.out.println("Here are the current supplies for the disaster victim:");
                                        for (Supply supply : victim.getPersonalBelongings()) {
                                            System.out.println("Type: " + supply.getType() + " Quantity: " + supply.getQuantity());
                                        }
                                        System.out.println("Please enter the type of the supply you would like to remove (or enter '-1' to go back):");
                                        String supplyType = scanner.nextLine();
                                        if (supplyType.equals("-1")) {
                                            break;
                                        }
                                        boolean exists = false;
                                        for (Supply s : victim.getPersonalBelongings()) {
                                            if (s.getType().toLowerCase().equals(supplyType.toLowerCase())) {
                                                exists = true;
                                                break;
                                            }
                                        }
                                        if (!exists) {
                                            System.out.println("Supply does not exist. Please try again.");
                                            continue;
                                        }
                                        System.out.println("Please enter the quantity of the supply you would like to remove (or enter '-1' to go back):");
                                        int supplyQuantity = getIntegerInput();
                                        if (supplyQuantity == -1) {
                                            break;
                                        }
                                        try {
                                            Supply supply = new Supply(supplyType, supplyQuantity);
                                            victim.removePersonalBelonging(supply);
                                            validRemoval = true;
                                        } catch (IllegalArgumentException e) {
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                    if (validRemoval) {
                                        System.out.println("Supply removed successfully");
                                    }
                                    break;

                                default:
                                    System.out.println("Going back");
                                    break;

                            }
                            break;
                            
                            case 11:
                                // Displaying disaster victim data
                                displayDisasterVictimData(locationID, socialID);
                                break;
                            
                            default:
                                System.out.println("Invalid choice");
                                break;
                            }
                    }
 
                     continue;
 
                 } else if (userInput == -1) {
                     return;
 
                 } else {
                     System.out.println("Invalid input. Please try again.");
                 }

             }
         }
     }
 
     /**
      * Prompts the user to enter information about an inquirer and their query,
      * logs the query, and allows for logging of additional queries.
      * 
      * This method continuously prompts the user to enter the first name, last name,
      * and phone number of the inquirer. It then prompts for the query and logs it
      * using the Inquirer class. The user is then asked if they want to log another
      * query. If the response is not "Y", the method exits.
      */
     public static void logInquirerQueries() {
         Scanner scanner = new Scanner(System.in);
 
         while (true) {
            System.out.println("Please enter the first name of the inquirer (or enter '-1' to cancel the inquiry and go back go back):");
            String firstName = scanner.nextLine();
            if ("-1".equals(firstName)) {
                System.out.println("No inquery logged");
                return;
            }

            System.out.println("Please enter the last name of the inquirer (or enter '-1' to cancel the inquiry and go back):");
            String lastName = scanner.nextLine();
            if ("-1".equals(lastName)) {
                System.out.println("No inquery logged");
                return;
            }

            System.out.println("Please enter the phone number of the inquirer (or enter '-1' to cancel the inquiry and go back):");
            String phone = scanner.nextLine();
            if ("-1".equals(phone)) {
                System.out.println("No inquery logged");
                return;
            }
 
            Inquirer inquirer = new Inquirer(firstName, lastName, phone); // Creating a new inquirer
            // The ReliefService constructor will ensure that multiple interactions will be logged with the same inquirer
            ReliefService reliefService = new ReliefService(inquirer); 
            inquirer = reliefService.getInquirer(); // If the inquirer is changed, we need to overwrite the old inquirer
 
            System.out.println("Enter the name or part of the name to search for (or enter '-1' to cancel the inquiry and go back):");
            String query = scanner.nextLine();
            if ("-1".equals(query)) {
                System.out.println("No inquery logged");
                return;
            }
            ArrayList<DisasterVictim> foundVictims = ReliefService.searchForVictim(query);
 
            if (foundVictims.isEmpty()) {
                System.out.println("No victim found with the specified name.");
            } else {
                System.out.println("Please enter the social ID of the victim that the inquirer would like to inquire about (or enter '-1' to cancel the inquiry and go back):");
                int socialID = getIntegerInput();
                if (socialID == -1) {
                    System.out.println("No inquery logged");
                    return;
                }
                boolean found = false;
                for (DisasterVictim victim : foundVictims) {
                    if (victim.getAssignedSocialID() == socialID) {
                        found = true;
                        reliefService.setMissingPerson(victim);
                        break;
                     }
                }
                if (!found) {
                    System.out.println("No victim found with the specified social ID. Cancelling inquiry");
                    return;
                }
             }
 
             System.out.println("Please enter any additional info that the inquirer has provided (or enter '-1' to cancel the inquiry and go back):");
             String info = scanner.nextLine();
            if ("-1".equals(info)) {
                System.out.println("No inquery logged");
                return;
            } 
             reliefService.setInfoProvided(info);
             inquirer.addInteraction(info);
             System.out.println("Inquery logged successfully");
 
             System.out.println("Would you like to log another query? (Y/N)");
             String response = scanner.nextLine();
             if (!response.equals("Y")) {
                 System.out.println("Exiting...");
                 return;
             }
         }
     }
 


     /**
      * Creates a new location with the specified name and address.

      * @return the new location
      */
      public static void newLocation() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Please enter the name of the location (or enter '-1' to go back):");
        String name = scanner.nextLine();
        if ("-1".equals(name)) { 
           System.out.println("No location added");
           return;
        }
    
        System.out.println("Please enter the address of the location (or enter '-1' to go back):");
        String address = scanner.nextLine();
        if ("-1".equals(address)) {
           System.out.println("No location added"); 
           return;
        }
    
        Location location = new Location(name, address);
        ReliefService.addLocation(location);;
    
        System.out.println("Added a new location: " + location.getName() + " with address: " + location.getAddress() + " with ID: " + location.getLocationID());
       

    }
    /**
     * Adds a supply to a specified location.
     * 
     * @param LocationID the ID of the location to add the supply to (-1 if not known yet)
     */
     public static void addSupplyToLocation(int locationID) {
            Scanner scanner = new Scanner(System.in);
            Location location = null;
            while (location == null) {
                if (locationID == -1) {
                    System.out.println("Please enter the locationID of the location that you would like to access from the following (or enter '-1' to go back):");
                    ReliefService.displayLocation();
                    locationID = getIntegerInput();

                    if (locationID == -1) {
                        return; // If the user wants to go back
                    }
                }
                try {
                    location = ReliefService.getLocationFromID(locationID);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    locationID = -1;
                }
            }
            Supply supply = null;
            while (supply == null) {
                System.out.println("Please enter the type of the supply (or enter '-1' to go back):");
                String type = scanner.nextLine();
                if (type.equals("-1")) {
                    System.out.println("No supply added");
                    return; // If the user wants to go back
                }
                System.out.println("Please enter the quantity of the supply (or enter '-1' to go back):");
                int quantity = getIntegerInput();

                if (quantity == -1) {
                    System.out.println("No supply added");
                    return; // If the user wants to go back
                }
                try {
                    supply = new Supply(type, quantity);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            location.addSupply(supply);
            System.out.println("Supply added successfully");
     }



    /**
     * Displays the supplies at a given location.
     * 
     * @param locationID the ID of the location to display supplies for
     */
     public static void displaySuppliesAtLocation(int locationID) {
        Location location = null;
        while (location == null) {
            if (locationID == -1) {
                System.out.println("Please enter the locationID of the location that you would like to access from the following (or enter '-1' to go back):");
                ReliefService.displayLocation();
                locationID = getIntegerInput();
                if (locationID == -1) {
                    return; // If the user wants to go back
                }
            }
            try {
                location = ReliefService.getLocationFromID(locationID);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                locationID = -1;
            }
        }
        if (location.getSupplies().isEmpty()) {
            System.out.println("No supplies have been added to this location yet");
            return;
        }
        for (Supply supply : location.getSupplies()) {
            System.out.println("Supply: " + supply.getType() + " Quantity: " + supply.getQuantity());
        }
    }

    /**
     * Displays the data of a disaster victim based on the given location ID and social ID.
     * 
     * @param locationID the ID of the location where the victim is located
     * @param socialID the social ID of the victim
     */
    public static void displayDisasterVictimData(int locationID, int socialID) {
        Location location = ReliefService.getLocationFromID(locationID);
        DisasterVictim victim = location.getDisasterVictimFromID(socialID);

        System.out.println("First name: " + victim.getFirstName());

        if (victim.getLastName() != null) {
            System.out.println("Last name: " + victim.getLastName());
        }

        System.out.println("Social ID: " + victim.getAssignedSocialID());

        System.out.println("Entry date: " + victim.getEntryDate());

        if (victim.getDateOfBirth() != null) {
            System.out.println("Date of birth: " + victim.getDateOfBirth());
        }

        if (victim.getApproximateAge() != null) {
            System.out.println("Approximate age: " + victim.getApproximateAge());
        }

        if (victim.getGender() != null) {
            System.out.println("Gender: " + victim.getGender());
        }

        if (victim.getLocation() != null) {
            System.out.println("Location: " + victim.getLocation().getName());
        }

        if (!victim.getMedicalRecords().isEmpty()) {
            System.out.println("Medical records:");
            for (MedicalRecord record : victim.getMedicalRecords()) {
                System.out.println("Treatment details: " + record.getTreatmentDetails());
                System.out.println("Date of treatment: " + record.getDateOfTreatment());
            }
        }

        if (!victim.getFamilyConnections().isEmpty()) {
            System.out.println("Family connections:");
            for (FamilyRelation relation : victim.getFamilyConnections()) {
                DisasterVictim otherPerson = (relation.getPersonOne() == victim) ? relation.getPersonTwo() : relation.getPersonOne();
                System.out.println("Family member: " + otherPerson.getFirstName());
                System.out.println("Relationship: " + relation.getRelationshipTo());
            }
        }

        if (!victim.getDietaryRestrictions().isEmpty()) {
            System.out.println("Dietary restrictions:");
            for (DietaryRestrictions restriction : victim.getDietaryRestrictions()) {
                System.out.println(restriction);
            }
        }

        if (!victim.getPersonalBelongings().isEmpty()) {
            System.out.println("Supplies:");
            for (Supply supply : victim.getPersonalBelongings()) {
                System.out.println("Type: " + supply.getType() + " Quantity: " + supply.getQuantity());
            }
        }

        if (victim.getComments() != null) {
            System.out.println("Comments: " + victim.getComments());
        }
    }

    /**
     * Method that handles accessing and updating the database.
     * This method requires a properly setup PostgreSQL database to work. 
     * See README.pdf for details on how to set up the database.
     */
    public static void accessDatabase() {
        Scanner scanner = new Scanner(System.in);

        try{
            connection.createConnection();
        } catch (SQLException e) {
            System.out.println("CRITICAL ERROR: Could not connect to the database. Please see README.pdf for instructions on how to set up the database. Exiting...");
            return;
        }
        boolean connected = true;
        
        while (connected) {
        
            System.out.println("Please enter '1' to display all inquirers in the database");
            System.out.println("Please enter '2' to display all interactions (inquiry logs) in the database");
            System.out.println("Please enter '3' to add a new inqurier to the database");
            System.out.println("Please enter '4' to add a new interaction to the database");
            System.out.println("Please enter '-1' to exit the database");
            int choice = getIntegerInput();

            switch(choice) {

                case 1:
                    // Displaying all inquirers in the database
                    connection.displayInquirers();
                    break;
                
                case 2:
                    // Displaying all interactions in the database
                    connection.displayInteractions();
                    break;

                case 3:
                    // Adding a new inquirer to the database
                    System.out.println("Please enter the first name of the inquirer (or enter '-1' to go back):");
                    String firstName = scanner.nextLine();
                    if (firstName.equals("-1")) {
                        break;
                    }

                    System.out.println("Please enter the last name of the inquirer (or enter '-1' to go back):");
                    String lastName = scanner.nextLine();
                    if (lastName.equals("-1")) {
                        break;
                    }

                    System.out.println("Please enter the phone number of the inquirer (or enter '-1' to go back):");
                    String phone = scanner.nextLine();
                    if (phone.equals("-1")) {
                        break;
                    }

                    connection.addInquirer(firstName, lastName, phone);
                    break;
                
                case 4:
                    // Adding an interaction
                    boolean validID = false;
                    int inquirerID = -1;
                    String date = "";
                    while (!validID) {
                        System.out.println("Please enter the id of the inquirer (or enter '-1' to go back):");
                        inquirerID = getIntegerInput();
                        if (inquirerID == -1) {
                            break;
                        }
                        validID = connection.inquirerExists(inquirerID);
                        if (!validID) {
                            System.out.println("Invalid inquirer ID. Please try again.");
                        }
                    }
                    if (!validID) {
                        break; // breaking out of the case
                    }
                    
                    boolean validDate = false;
                    while (!validDate) {
                        System.out.println("Please press 'enter' to use the current date or enter the date of the interaction in the form YYYY-MM-DD (or enter '-1' to go back):");
                        date = scanner.nextLine();
                        if (date.isEmpty()) {
                            date = LocalDate.now().toString();
                            validDate = true;
                        } else if (date.equals("-1")) {
                            break;
                        } else {
                            try {
                                LocalDate parsedDate = LocalDate.parse(date);
                                LocalDate currentDate = LocalDate.now();
                                
                                if (parsedDate.isAfter(currentDate)) {
                                    System.out.println("Invalid date. Date cannot be in the future. Please try again.");
                                } else if (parsedDate.getYear() < 2020) {
                                    System.out.println("Invalid date. Year cannot be before 2020. Please try again.");
                                } else {
                                    validDate = true;
                                }
                            } catch (DateTimeParseException e) {
                                System.out.println("Invalid date format. Please try again.");
                            }
                        }
                    }
                    if (!validDate) {
                        break; // breaking out of the case
                    }

                    System.out.println("Please enter the details of the interaction (or enter '-1' to go back):");
                    String details = scanner.nextLine();
                    if (details.equals("-1")) {
                        break;
                    }

                    connection.addInquiryLog(inquirerID, date, details);
                    break;

                case -1:
                    // Exiting
                    connection.close();
                    connected = false;
                    break;
            }
        }

    }


    /**
     * Main method that runs the application.
     * 
     * @param args the CLI arguments
     */
     public static void main(String[] args) {
        

         Scanner scanner = new Scanner(System.in);
         String mode = args.length > 0 ? args[0] : ""; // The mode is determined from CLI arguments

         while(true) {
             if (mode.equals("central")) {
                 // Run the application in central mode

                 while(mode.equals("central")){
                    System.out.println("Running in central mode");
                    System.out.println("Please enter '1' if you would like enter information about Disaster Victims");
                    System.out.println("Please enter '2' if you would like to log inquirer queries");
                    System.out.println("Please enter '3' to add a new location");
                    System.out.println("Please enter '4' to switch to location-based mode");
                    System.out.println("Please enter '5' to display all locations and victims");
                    System.out.println("Please enter '6' to add supplies to a location");
                    System.out.println("Please enter '7' to display supplies at a location");
                    System.out.println("Please enter '8' to access the database");
                    System.out.println("Please enter '9' to display all inquiries");
                    System.out.println("Please enter '-1' to exit the program");
    
                    int choice = getIntegerInput();
    
                    switch (choice) {
                        case -1:
                            // Exiting
                            System.out.println("You are about to exit the program. Are you sure you want to exit? (Y/N)");

                            String response = scanner.nextLine();
                            if (response.toUpperCase().equals("Y")) {
                                System.out.println("Exiting...");
                                return;
                            }
                            break;

                        case 1:
                            // Entering information about Disaster Victims
                            if (ReliefService.getLocations().isEmpty()) {
                                System.out.println("No locations have been added. Please add a location by pressing '3'.");
                                break;
                            }
                            editDisasterVictims(-1); // The argument is -1, since we do not yet know the location
                            break;

                        case 2:
                            // Logging inquirer queries
                            logInquirerQueries();
                            break;
                                
                        case 3:
                            // Adding a new location
                            newLocation();
                            break;
                        
                        case 4:
                            // Switching to location-based mode
                            System.out.println("Switching to location-based mode");
                            mode = "location";
                            break;
                        
                        case 5:
                            // Displaying all locations and victims
                            if(ReliefService.getLocations().isEmpty()) {
                                System.out.println("No locations have been added yet");
                                break;
                            }
                            ReliefService.displayLocationsAndVictims();
                            break;
                        case 6:
                            // Adding supplies to a location
                            addSupplyToLocation(-1);
                            break;
                        
                        case 7:
                            // Displaying supplies at a location
                            displaySuppliesAtLocation(-1);
                            break;

                        case 8:
                            // Accessing the database
                            accessDatabase();
                            break;
                        
                        case 9:
                            // Displaying all inquiries
                            ReliefService.displayInquirers();
                            break;

                        default:
                            System.out.println("Invalid choice");
                            break;
                    }
                }
 
             } else if (mode.equals("location")) {
                // Run the application in location-based mode
                int locationID = -1;
                if (ReliefService.getLocations().isEmpty()) {
                    System.out.println("No locations have been added. Please add a location in central mode");
                    System.out.println("Switching to central mode");
                    mode = "central";
                } else {
                    boolean valid = false;
                    
                    while (!valid) {
                        try {
                            System.out.println("Please enter a location ID from the following to access:");
                            ReliefService.displayLocation();
                            locationID = getIntegerInput();
                            ReliefService.getLocationFromID(locationID);
                            valid = true; // Set valid to true if no exception is thrown
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        } 
                    }
                }
                 

                 while(mode.equals("location")){
                    System.out.println("Running in location-based mode");
                    System.out.println("Please enter '1' if you would like enter information about Disaster Victims");
                    System.out.println("Please enter '2' if you would like to log inquirer queries");
                    System.out.println("Please enter '3' to display all disaster victims in this location");
                    System.out.println("Please enter '4' to switch to central mode");
                    System.out.println("Please enter '5' to add supplies to a location");
                    System.out.println("Please enter '6' to display supplies at a location");
                    System.out.println("Please enter '-1' to exit the program");
    
                    int choice = getIntegerInput();
 
                    switch (choice) {

                        case -1:
                            // Exiting
                            System.out.println("You are about to exit the program. Are you sure you want to exit? (Y/N)");

                            String response = scanner.nextLine();
                            if (response.toUpperCase().equals("Y")) {
                                System.out.println("Exiting...");
                                return;
                            }
                            break;
                        case 1:
                            // Entering information about Disaster Victims
                            editDisasterVictims(locationID);
                            break;
                        case 2:
                            // Logging inquirer queries
                            logInquirerQueries();
                            break;
                        
                        case 3:
                            // Displaying all disaster victims in this location
                        
                            ReliefService.displayDisasterVictims(locationID);
                            break;
                        
                        case 4:
                            // Switching to central mode
                            System.out.println("Switching to central mode");
                            mode = "central";
                            break;

                        case 5:
                            // Adding supplies to a location
                            addSupplyToLocation(locationID);
                            break;
                        
                        case 6:
                            // Displaying supplies at a location
                            displaySuppliesAtLocation(locationID);
                            break;

                        default:
                            System.out.println("Invalid choice");
                            break;
                    }
                }
                } else {
                    System.out.println("Invalid mode. Please specify 'central' or 'location' as a command line argument (See README.pdf)");
                    break;
                }
         } 
         scanner.close();
     }    
}
