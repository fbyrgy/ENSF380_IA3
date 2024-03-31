package edu.ucalgary.oop;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
     /**
      * Adds a disaster victim to the specified location.
      * 
      * @return the disaster victim that was added
      */
     public static DisasterVictim enterDisasterVictim(int locationID) {
         Scanner scanner = new Scanner(System.in);
 
         System.out.println("Please enter the first name of the disaster victim:");
         String firstName = scanner.nextLine();
 
         String entryDate = "";
         boolean validDate = false;
 
         while (!validDate) {
             System.out.println(
                     "Please enter the entry date of the disaster victim in the format 'YYYY-MM-DD', or type -1 to use the current date:");
             entryDate = scanner.nextLine();
 
             if (entryDate.equals("-1")) {
                 entryDate = LocalDate.now().toString();
                 validDate = true;
             } else if (ReliefService.isValidDateFormat(entryDate)) {
                 validDate = true;
             } else {
                 System.out.println("Invalid date format. Please try again.");
             }
         }
         
        Location location = null;
        while (location == null) {
            if (locationID == -1) {
                System.out.println("Please enter the locationID of the location that you would like to add the disaster victim to from the following:");
                ReliefService.displayLocation();
                locationID = scanner.nextInt();
            }
            try {
                location = ReliefService.getLocationFromID(locationID);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                locationID = -1;
            }
        }
         DisasterVictim victim = new DisasterVictim(firstName, entryDate, location);

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
                 int userInput = scanner.nextInt();
                 scanner.nextLine();
 
                 if (userInput == 1) {
                     // Entering new Disaster Victim
                     enterDisasterVictim(locationID);
                     System.out.println("Disaster Victim added successfully. You can now edit the disaster victim by pressing '2'.");
                     continue;
 
                 } else if (userInput == 2) {
                     // Editing information for a Disaster Victim
                     Location location = null;
                     while (location == null) {
                         if (locationID == -1) {
                             System.out.println("Please enter the locationID of the location that you would like to access:");
                             ReliefService.displayLocation();
                             locationID = scanner.nextInt();
                         }
                         try {
                             location = ReliefService.getLocationFromID(locationID);
                         } catch (IllegalArgumentException e) {
                             System.out.println(e.getMessage());
                             locationID = -1;
                         }
                     }
 
                     System.out.println("Please enter the social ID of the disaster victim that you would like to edit from the following:");
                     ReliefService.displayDisasterVictims(locationID);

                     int socialID = scanner.nextInt();
                     DisasterVictim victim = location.getDisasterVictimFromID(socialID);
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
                        System.out.println("Please enter '-1' to go back");
                        int choice = scanner.nextInt();
                        scanner.nextLine();
    
                        // Handling all the choices
                        
                        switch (choice) {
                            case -1:
                                sameVictim = false;
                                break;

                            case 1:
                                System.out.println("Please enter the new first name of the disaster victim:");
                                String newFirstName = scanner.nextLine();
                                victim.setFirstName(newFirstName);
                                System.out.println("The first name of: " + victim.getAssignedSocialID() + " has been updated to: " + victim.getFirstName());
                                break;
                            case 2:
                                System.out.println("Please enter the new last name of the disaster victim:");
                                String newLastName = scanner.nextLine();
                                victim.setLastName(newLastName);
                                System.out.println("The last name of: " + victim.getFirstName() + " has been updated to: " + victim.getLastName());
                                break;
                            case 3:
                                boolean validDateOfBirth = false;
                                while (!validDateOfBirth) {
                                    System.out.println("Please enter the new date of birth of the disaster victim in the format 'YYYY-MM-DD':");
                                    String newDateOfBirth = scanner.nextLine();
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
                                boolean noError = true;
                                System.out.println("Please enter the new approximate age of the disaster victim:");
                                int newAge = scanner.nextInt();
                                try{
                                    victim.setApproximateAge(newAge);
                                } catch (IllegalStateException e) {
                                    noError = false;
                                    System.out.println(e.getMessage());
                                }
                                if (noError) {
                                    System.out.println("The approximate age of: " + victim.getFirstName() + " has been updated to: " + victim.getApproximateAge());
                                }
                                break;
                            case 5:
                                System.out.println("Please enter the new gender of the disaster victim");
                                System.out.println("The valid genders are:" + DisasterVictim.getGenderOptions());
                                String newGender = scanner.nextLine();
                                boolean validGender = false;
                                while (!validGender) {
                                    try {
                                        System.out.println("Please enter the new gender of the disaster victim");
                                        System.out.println("The valid genders are:" + DisasterVictim.getGenderOptions());
                                        victim.setGender(newGender);
                                        validGender = true;
                                    } catch (IllegalArgumentException e) {
                                        System.out.println("Please try again");
                                        newGender = scanner.nextLine();
                                    }
                                }
                                System.out.println("The gender of: " + victim.getFirstName() + " has been updated to: " + victim.getGender());
                                break;
                            case 6:
                                boolean validID = false;
                            
                                while(!validID) {
                                    try{
                                        System.out.println("Please enter the new locationID of the location that you would like to assign to the disaster victim from the following:");
                                        ReliefService.displayLocation();
                                        int newLocationID = scanner.nextInt();
                                        Location newLocation = ReliefService.getLocationFromID(newLocationID);
                                        victim.setLocation(newLocation);
                                        validID = true;
                                    } catch (IllegalArgumentException e) {
                                        System.out.println(e.getMessage());
                                        System.out.println("Please try again");
                                    }

                                }
                                System.out.println("The location of: " + victim.getFirstName() + " has been updated to: " + victim.getLocation().getName());
                                break;
                            case 7:
                                System.out.println("Please enter the treatment details of the disaster victim:");
                                String treatmentDetails = scanner.nextLine();
                                System.out.println("Please enter the date of treatment in the form YYYY-MM-DD:");
                                String dateOfTreatment = scanner.nextLine();
                                boolean validDate = false;

                                while (!validDate) {
                                    try {
                                        MedicalRecord newMedicalRecords = new MedicalRecord(location, treatmentDetails, dateOfTreatment);
                                        victim.addMedicalRecord(newMedicalRecords);
                                        validDate = true;
                                    } catch (IllegalArgumentException e) {
                                        System.out.println("Please enter the date of treatment in the form YYYY-MM-DD:");
                                        dateOfTreatment = scanner.nextLine();
                                    }
                                }
                                System.out.println("Medical record added successfully");
                                break;
                            case 8:
                                DisasterVictim personOne;
                                boolean valid = false;
                                while(!valid){
                                    System.out.println("Please enter the social ID of the family member that you would like to add from the following (or enter '-1' to go back): ");
                                    ReliefService.displayLocationsAndVictims();
                                    int familyMemberID = scanner.nextInt();
                                    if (familyMemberID == -1) {
                                        break;
                                    }
                                    try {
                                        personOne = location.getDisasterVictimFromID(familyMemberID);
                                        valid = true;
                                    } catch (IllegalArgumentException e) {
                                        System.out.println(e.getMessage());
                                        continue;
                                    }
                                    System.out.println("Please enter the relationship between the disaster victim and the family member:");
                                    String relationship = scanner.nextLine();
                                    new FamilyRelation(victim, relationship, personOne);
                                }
                                if(valid){
                                    System.out.println("Family relation added successfully");
                                }
                                break;
                            default:
                                System.out.println("Invalid choice");
                                break;
                            }
                    }
 
                     continue;
 
                 } else if (userInput == -1) {
                     return;
 
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
             System.out.println("Please enter the first name of the inquirer:");
             String firstName = scanner.nextLine();
             System.out.println("Please enter the last name of the inquirer:");
             String lastName = scanner.nextLine();
             System.out.println("Please enter the phone number of the inquirer:");
             String phone = scanner.nextLine();
 
             Inquirer inquirer = new Inquirer(firstName, lastName, phone);
             ReliefService reliefService = new ReliefService(inquirer);
 
             System.out.println("Enter the name or part of the name to search for:");
             String query = scanner.nextLine();
             ArrayList<DisasterVictim> foundVictims = searchForVictim(query);
 
             if (foundVictims.isEmpty()) {
                 System.out.println("No victim found with the specified name.");
             } else {
                 System.out.println("What is the social ID of the victim you would like to inquire about?");
                 int socialID = scanner.nextInt();
                 boolean found = false;
                 for (DisasterVictim victim : foundVictims) {
                     if (victim.getAssignedSocialID() == socialID) {
                         found = true;
                         reliefService.setMissingPerson(victim);
                         break;
                     }
                 }
                 if (!found) {
                     System.out.println("No victim found with the specified social ID.");
                     scanner.close();
                     return;
                 }
             }
 
             System.out.println("Please enter any additional info that the inquirer has provided");
             String info = scanner.nextLine();
             reliefService.setInfoProvided(info);
             inquirer.addInteraction(info);
 
             System.out.println("Would you like to log another query? (Y/N)");
             String response = scanner.nextLine();
             if (!response.equals("Y")) {
                 System.out.println("Exiting...");
                 scanner.close();
                 return;
             }
         }
     }
 
     /**
      * Searches for a victim by name or part of the name in the locations.
      * 
      * @param query the name or part of the name to search for
      * @return a list of disaster victims that match the given first name.
      */
     public static ArrayList<DisasterVictim> searchForVictim(String query) {
         ArrayList<DisasterVictim> foundVictims = new ArrayList<>();
         query = query.toLowerCase();
 
         for (Location loc : ReliefService.getLocations()) {
             for (DisasterVictim victim : loc.getOccupants()) {
                 if (victim.getFirstName().toLowerCase().contains(query)) {
                     System.out.println("Found victim: " + victim.getFirstName() + " " + victim.getLastName()
                             + " in location: " + loc.getName() + " with social ID: " + victim.getAssignedSocialID());
                     foundVictims.add(victim);
                 }
             }
         }
 
         return foundVictims;
     }

     /**
      * Creates a new location with the specified name and address.

      * @return the new location
      */

     public static Location newLocation() {
         Scanner scanner = new Scanner(System.in);
         
 
         System.out.println("Please enter the name of the location:");
         String name = scanner.nextLine();
 
         System.out.println("Please enter the address of the location:");
         String address = scanner.nextLine();
 
         Location location = new Location(name, address);
         ReliefService.getLocations().add(location);

         System.out.println("Added a new location: " + location.getName() + " with address: " + location.getAddress() + " with ID: " + location.getLocationID());
        
         return location;
     }
 


     public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);
         String mode = args.length > 0 ? args[0] : "";

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
                    System.out.println("Please enter '-1' to exit the program");
    
                    int choice = scanner.nextInt();
    
                    switch (choice) {
                        case -1:
                            // Exiting
                            System.out.println("Exiting...");
                            return;

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
                            locationID = scanner.nextInt();
                            Location location = ReliefService.getLocationFromID(locationID);
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
                    System.out.println("Please enter '-1' to exit the program");
    
                    int choice = scanner.nextInt();
 
                    switch (choice) {

                        case -1:
                            // Exiting
                            System.out.println("Exiting...");
                            return;
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

                        default:
                            System.out.println("Invalid choice");
                            break;
                    }
                }
                } else {
                    System.out.println("Invalid mode. Please specify 'central' or 'location' as a command line argument (See README.md).");
                    break;
                }
         } 
         scanner.close();
     }    
}
