/**
 * The ReliefService class represents a relief service for disaster victims.
 * It provides methods to manage and retrieve information about the relief service.
 * @author Deven Powell
 * @version 1.0
 * @since 2024-03-18
 */

 package edu.ucalgary.oop;

 import java.time.LocalDate;
 import java.time.format.DateTimeFormatter;
 import java.util.ArrayList;
 import java.util.Scanner;
 
 public class ReliefService {
     private Inquirer inquirer;
     private DisasterVictim missingPerson;
     private String dateOfInquiry;
     private String infoProvided;
     private Location lastKnownLocation;
     private ArrayList<Inquirer> inquirerList = new ArrayList<>();
     private static ArrayList<Location> locations = new ArrayList<>();
 
     /**
      * Constructs a ReliefService object with the specified parameters.
      * 
      * @param inquirer          the inquirer associated with the relief service
      * @param missingPerson     the missing person associated with the relief
      *                          service
      * @param dateOfInquiry     the date of the inquiry in the format "YYYY-MM-DD"
      * @param infoProvided      the information provided by the inquirer
      * @param lastKnownLocation the last known location of the missing person
      */
     public ReliefService(Inquirer inquirer, DisasterVictim missingPerson, String dateOfInquiry, String infoProvided,
             Location lastKnownLocation) {
         this.inquirer = inquirer;
         this.missingPerson = missingPerson;
         setDateOfInquiry(dateOfInquiry);
         this.infoProvided = infoProvided;
         this.lastKnownLocation = lastKnownLocation;
         inquirerList.add(inquirer);
     }
 
     /**
      * Constructs a ReliefService object with the given parameters.
      *
      * @param inquirer the Inquirer object representing the person making the
      *                 inquiry
      */
     public ReliefService(Inquirer inquirer) {
         this.inquirer = inquirer;
         inquirerList.add(inquirer);
     }

     /**
      * Default constructor
      */
     public ReliefService() {
     }
 
     /**
      * Returns the inquirer associated with the relief service.
      * 
      * @return the inquirer associated with the relief service
      */
     public Inquirer getInquirer() {
         return inquirer;
     }
 
     /**
      * Sets the inquirer associated with the relief service.
      * If the inquirer is already in the inquirerList, it sets it to that inquirer.
      * 
      * @param inquirer the inquirer to be set
      */
     public void setInquirer(Inquirer inquirer) {
         if (inquirerExists(inquirer)) {
             this.inquirer = inquirer;
         } else {
             inquirerList.add(inquirer);
             this.inquirer = inquirer;
         }
     }
 
     /**
      * Returns the missing person associated with the relief service.
      * 
      * @return the missing person associated with the relief service
      */
     public DisasterVictim getMissingPerson() {
         return missingPerson;
     }
 
     /**
      * Sets the missing person associated with the relief service.
      * 
      * @param missingPerson the missing person to be set
      */
     public void setMissingPerson(DisasterVictim missingPerson) {
         this.missingPerson = missingPerson;
     }
 
     /**
      * Returns the date of the inquiry in the format "YYYY-MM-DD".
      * 
      * @return the date of the inquiry
      */
     public String getDateOfInquiry() {
         return dateOfInquiry;
     }
 
     /**
      * Sets the date of the inquiry.
      * 
      * @param dateOfInquiry the date of the inquiry in the format "YYYY-MM-DD"
      * @throws IllegalArgumentException if the date format is invalid
      */
     public void setDateOfInquiry(String dateOfInquiry) {
         // Check if the dateOfInquiry string matches the expected date format
         if (!isValidDateFormat(dateOfInquiry)) {
             throw new IllegalArgumentException("Invalid date format for date of inquiry. Expected format: YYYY-MM-DD");
         }
         this.dateOfInquiry = dateOfInquiry;
     }
 
     /**
      * Returns the information provided by the inquirer.
      * 
      * @return the information provided by the inquirer
      */
     public String getInfoProvided() {
         return infoProvided;
     }
 
     /**
      * Sets the information provided by the inquirer.
      * 
      * @param infoProvided the information provided by the inquirer
      */
     public void setInfoProvided(String infoProvided) {
         this.infoProvided = infoProvided;
     }
 
     /**
      * Returns the last known location of the missing person.
      * 
      * @return the last known location of the missing person
      */
     public Location getLastKnownLocation() {
         return lastKnownLocation;
     }
 
     /**
      * Sets the last known location of the missing person.
      * 
      * @param lastKnownLocation the last known location of the missing person
      */
     public void setLastKnownLocation(Location lastKnownLocation) {
         this.lastKnownLocation = lastKnownLocation;
     }
 
     /**
      * Checks if a string matches the "YYYY-MM-DD" date format.
      * 
      * @param date the string to be checked
      * @return true if the string matches the date format, false otherwise
      */
     public static boolean isValidDateFormat(String date) {
         try {
             LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
             return true;
         } catch (Exception e) {
             return false;
         }
     }
 
     /**
      * Returns a string representation of the log details of the relief service.
      * 
      * @return a string representation of the log details
      */
     public String getLogDetails() {
         return "Inquirer: " + inquirer.getFirstName() + ", Missing Person: " + missingPerson.getFirstName()
                 + ", Date of Inquiry: " + dateOfInquiry + ", Info Provided: " + infoProvided + ", Last Known Location: "
                 + lastKnownLocation.getName();
     }
 
     /**
      * Returns a list of inquirers associated with the relief service.
      * 
      * @return a list of inquirers associated with the relief service
      */
     public ArrayList<Inquirer> getInquirerList() {
         return inquirerList;
 
     }
 
     /**
      * Checks if the specified inquirer exists in the inquirer list.
      * 
      * @param inquirer the inquirer to check
      * @return true if the inquirer exists in the list, false otherwise
      */
     public boolean inquirerExists(Inquirer inquirer) {
         for (Inquirer existingInquirer : inquirerList) {
             if (existingInquirer.getFirstName().equals(inquirer.getFirstName()) &&
                     existingInquirer.getLastName().equals(inquirer.getLastName()) &&
                     existingInquirer.getServicesPhoneNum().equals(inquirer.getServicesPhoneNum())) {
                 return true;
             }
         }
         return false;
     }
 
     /**
      * Returns the list of locations associated with the relief service.
      * 
      * @return the list of locations
      */
     public static ArrayList<Location> getLocations() {
         return locations;
     }
 
     /**
      * Adds a location to the list of locations associated with the relief service.
      * 
      * @param location the location to be added
      */
     public void addLocation(Location location) {
         locations.add(location);
     }
 
     /**
      * Checks if a location exists in the list of locations, and then returns the
      * location.
      * 
      * @param locationID the ID of the location to be returned
      * @return the location with the specified locationID
      * @throws IllegalArgumentException if the location does not exist
      */
     public static Location getLocationFromID(int locationID) {
         for (Location loc : locations) {
             if (loc.getLocationID() == locationID) {
                 return loc;
             }
         }
         throw new IllegalArgumentException("Location with ID " + locationID + " does not exist.");
     }

         /**
    * Displays the locations in the locations list.
    */
    public static void displayLocation() {
        for(Location loc : locations) {
            System.out.println("Name: " + loc.getName() + ", Address: " + loc.getAddress() + ", ID: " + loc.getLocationID());
        }
    }


    /**
     * Displays the information of disaster victims at a given location.
     * 
     * @param locationID the ID of the location
     */
    public static void displayDisasterVictims(int locationID) {
        Location location = getLocationFromID(locationID);
        ArrayList<DisasterVictim> occupants = location.getOccupants();
        if (occupants.isEmpty()) {
            System.out.println("No disaster victims found at location: " + location.getName());
            return;
        }
        for (DisasterVictim victim : occupants) {
            System.out.println("Name: " + victim.getFirstName() + " Location: " + location.getName() + " Social ID: " + victim.getAssignedSocialID());
        }
    }

    /**
     * Displays the locations and their corresponding disaster victims.
     * Iterates through each location and calls the displayDisasterVictims method
     * to display the victims associated with that location.
     */
    public static void displayLocationsAndVictims() {
        for(Location loc : locations) {
            int locationID = loc.getLocationID();
            displayDisasterVictims(locationID);
        }
    }

 
    //  /**
    //   * Adds a disaster victim to the specified location.
    //   * 
    //   * @return the disaster victim that was added
    //   */
    //  public static DisasterVictim enterDisasterVictim(int locationID) {
    //      Scanner scanner = new Scanner(System.in);
 
    //      System.out.println("Please enter the first name of the disaster victim:");
    //      String firstName = scanner.nextLine();
 
    //      String entryDate = "";
    //      boolean validDate = false;
 
    //      while (!validDate) {
    //          System.out.println(
    //                  "Please enter the entry date of the disaster victim in the format 'YYYY-MM-DD', or type -1 to use the current date:");
    //          entryDate = scanner.nextLine();
 
    //          if (entryDate.equals("-1")) {
    //              entryDate = LocalDate.now().toString();
    //              validDate = true;
    //          } else if (isValidDateFormat(entryDate)) {
    //              validDate = true;
    //          } else {
    //              System.out.println("Invalid date format. Please try again.");
    //          }
    //      }
         
    //     Location location = null;
    //     while (location == null) {
    //         if (locationID == -1) {
    //             System.out.println("Please enter the locationID of the location that you would like to add the disaster victim to from the following:");
    //             displayLocation();
    //             locationID = scanner.nextInt();
    //         }
    //         try {
    //             location = getLocationFromID(locationID);
    //         } catch (IllegalArgumentException e) {
    //             System.out.println(e.getMessage());
    //             locationID = -1;
    //         }
    //     }
    //      DisasterVictim victim = new DisasterVictim(firstName, entryDate, location);

    //      return victim;
    //  }
 
    //  /**
    //   * Allows the user to add a new Disaster Victiom, or edit information about a
    //   * Disaster Victim.
    //   *
    //   * @param locationID the ID of the location to add or edit a disaster victim. This will be -1 if it is not known yet.
    //   */
    //  public static void editDisasterVictims(int locationID) {
    //      Scanner scanner = new Scanner(System.in);
 
    //      while (true) {
    //         System.out.println("Please enter '1' if you would like to enter a new Disaster Victim");
    //         System.out.println("Please enter '2' if you would like to edit the information for a Disaster Victim");
    //         System.out.println("Please enter '3' if you would like to exit");
    //         {
    //              int userInput = scanner.nextInt();
    //              scanner.nextLine();
 
    //              if (userInput == 1) {
    //                  // Entering new Disaster Victim
    //                  enterDisasterVictim(locationID);
    //                  System.out.println("Disaster Victim added successfully. You can now edit the disaster victim by pressing '2'.");
    //                  continue;
 
    //              } else if (userInput == 2) {
    //                  // Editing information for a Disaster Victim
    //                  Location location = null;
    //                  while (location == null) {
    //                      if (locationID == -1) {
    //                          System.out.println("Please enter the locationID of the location that you would like to access:");
    //                          displayLocation();
    //                          locationID = scanner.nextInt();
    //                      }
    //                      try {
    //                          location = getLocationFromID(locationID);
    //                      } catch (IllegalArgumentException e) {
    //                          System.out.println(e.getMessage());
    //                          locationID = -1;
    //                      }
    //                  }
 
    //                  System.out.println("Please enter the social ID of the disaster victim that you would like to edit from the following:");
    //                 displayDisasterVictims(locationID);

    //                  int socialID = scanner.nextInt();
    //                  DisasterVictim victim = location.getDisasterVictimFromID(socialID);
 
    //                  System.out.println(
    //                          "Please enter '1' if you would like to edit the first name of the disaster victim");
    //                  System.out
    //                          .println("Please enter '2' if you would like to edit the last name of the disaster victim");
    //                  System.out.println(
    //                          "Please enter '3' if you would like to edit the date of birth of the disaster victim");
    //                  System.out.println(
    //                          "Please enter '4' if you would like to edit the approximate age of the disaster victim");
    //                  System.out.println("Please enter '5' if you would like to edit the gender of the disaster victim");
    //                  System.out
    //                          .println("Please enter '6' if you would like to edit the location of the disaster victim");
    //                  System.out.println(
    //                          "Please enter '7' if you would like to edit the medical records of the disaster victim");
    //                  System.out.println(
    //                          "Please enter '8' if you would like to edit the family connections of the disaster victim");
    //                  int choice = scanner.nextInt();
    //                  scanner.nextLine();
 
    //                  // Handling all the choices
    //                  switch (choice) {
    //                      case 1:
    //                          System.out.println("Please enter the new first name of the disaster victim:");
    //                          String newFirstName = scanner.nextLine();
    //                          victim.setFirstName(newFirstName);
    //                          break;
    //                      case 2:
    //                          System.out.println("Please enter the new last name of the disaster victim:");
    //                          String newLastName = scanner.nextLine();
    //                          victim.setLastName(newLastName);
    //                          break;
    //                      case 3:
    //                          System.out.println("Please enter the new date of birth of the disaster victim in the format 'YYYY-MM-DD':");
    //                          String newDateOfBirth = scanner.nextLine();
    //                          victim.setDateOfBirth(newDateOfBirth);
    //                          break;
    //                      case 4:
    //                          System.out.println("Please enter the new approximate age of the disaster victim:");
    //                          int newAge = scanner.nextInt();
    //                          victim.setApproximateAge(newAge);
    //                          break;
    //                      case 5:
    //                         System.out.println("Please enter the new gender of the disaster victim");
    //                         System.out.println("The valid genders are:" + DisasterVictim.getGenderOptions());
    //                         String newGender = scanner.nextLine();
    //                         boolean validGender = false;

    //                         while (!validGender) {
    //                             try {
                                    
    //                                 victim.setGender(newGender);
    //                                 validGender = true;
    //                             } catch (IllegalArgumentException e) {
    //                                 System.out.println(e.getMessage());
    //                                 System.out.println("Please try again.");
    //                                 newGender = scanner.nextLine();
    //                             }
    //                         }
    //                         System.out.println("The gender of: " + victim.getFirstName() + " has been updated to: " + victim.getGender());
    //                         break;
    //                      case 6:
    //                          System.out.println(
    //                                  "Please enter the new locationID of the location that you would like to assign to the disaster victim:");
    //                          int newLocationID = scanner.nextInt();
    //                          Location newLocation = getLocationFromID(newLocationID);
    //                          victim.setLocation(newLocation);
    //                          break;
    //                      case 7:
    //                          System.out.println("Please enter the treatment details of the disaster victim:");
    //                          String treatmentDetails = scanner.nextLine();
    //                          System.out.println("Please enter the date of treatment in the form YYYY-MM-DD:");
    //                          String dateOfTreatment = scanner.nextLine();
    //                          MedicalRecord newMedicalRecords = new MedicalRecord(location, treatmentDetails,
    //                                  dateOfTreatment);
    //                          victim.addMedicalRecord(newMedicalRecords);
    //                          break;
    //                      case 8:
    //                          System.out.println(
    //                                  "Please enter the social ID of the family member that you would like to add:");
    //                          int familyMemberID = scanner.nextInt();
    //                          DisasterVictim personOne = location.getDisasterVictimFromID(familyMemberID);
    //                          System.out.println(
    //                                  "Please enter the relationship between the disaster victim and the family member:");
    //                          String relationship = scanner.nextLine();
    //                          new FamilyRelation(victim, relationship, personOne);
    //                          break;
    //                      default:
    //                          System.out.println("Invalid choice");
    //                          break;
    //                  }
 
    //                  continue;
 
    //              } else if (userInput == 3) {
    //                  System.out.println("Exiting...");
                     
    //                  return;
 
    //              }
    //          }
    //      }
    //  }
 
    //  /**
    //   * Prompts the user to enter information about an inquirer and their query,
    //   * logs the query, and allows for logging of additional queries.
    //   * 
    //   * This method continuously prompts the user to enter the first name, last name,
    //   * and phone number of the inquirer. It then prompts for the query and logs it
    //   * using the Inquirer class. The user is then asked if they want to log another
    //   * query. If the response is not "Y", the method exits.
    //   */
    //  public static void logInquirerQueries() {
    //      Scanner scanner = new Scanner(System.in);
 
    //      while (true) {
    //          System.out.println("Please enter the first name of the inquirer:");
    //          String firstName = scanner.nextLine();
    //          System.out.println("Please enter the last name of the inquirer:");
    //          String lastName = scanner.nextLine();
    //          System.out.println("Please enter the phone number of the inquirer:");
    //          String phone = scanner.nextLine();
 
    //          Inquirer inquirer = new Inquirer(firstName, lastName, phone);
    //          ReliefService reliefService = new ReliefService(inquirer);
 
    //          System.out.println("Enter the name or part of the name to search for:");
    //          String query = scanner.nextLine();
    //          ArrayList<DisasterVictim> foundVictims = searchForVictim(query);
 
    //          if (foundVictims.isEmpty()) {
    //              System.out.println("No victim found with the specified name.");
    //          } else {
    //              System.out.println("What is the social ID of the victim you would like to inquire about?");
    //              int socialID = scanner.nextInt();
    //              boolean found = false;
    //              for (DisasterVictim victim : foundVictims) {
    //                  if (victim.getAssignedSocialID() == socialID) {
    //                      found = true;
    //                      reliefService.setMissingPerson(victim);
    //                      break;
    //                  }
    //              }
    //              if (!found) {
    //                  System.out.println("No victim found with the specified social ID.");
    //                  scanner.close();
    //                  return;
    //              }
    //          }
 
    //          System.out.println("Please enter any additional info that the inquirer has provided");
    //          String info = scanner.nextLine();
    //          reliefService.setInfoProvided(info);
    //          inquirer.addInteraction(info);
 
    //          System.out.println("Would you like to log another query? (Y/N)");
    //          String response = scanner.nextLine();
    //          if (!response.equals("Y")) {
    //              System.out.println("Exiting...");
    //              scanner.close();
    //              return;
    //          }
    //      }
    //  }
 
    //  /**
    //   * Searches for a victim by name or part of the name in the locations.
    //   * 
    //   * @param query the name or part of the name to search for
    //   * @return a list of disaster victims that match the given first name.
    //   */
    //  public static ArrayList<DisasterVictim> searchForVictim(String query) {
    //      ArrayList<DisasterVictim> foundVictims = new ArrayList<>();
    //      query = query.toLowerCase();
 
    //      for (Location loc : locations) {
    //          for (DisasterVictim victim : loc.getOccupants()) {
    //              if (victim.getFirstName().toLowerCase().contains(query)) {
    //                  System.out.println("Found victim: " + victim.getFirstName() + " " + victim.getLastName()
    //                          + " in location: " + loc.getName() + " with social ID: " + victim.getAssignedSocialID());
    //                  foundVictims.add(victim);
    //              }
    //          }
    //      }
 
    //      return foundVictims;
    //  }

    //  /**
    //   * Creates a new location with the specified name and address.

    //   * @return the new location
    //   */

    //  public static Location newLocation() {
    //      Scanner scanner = new Scanner(System.in);
         
 
    //      System.out.println("Please enter the name of the location:");
    //      String name = scanner.nextLine();
 
    //      System.out.println("Please enter the address of the location:");
    //      String address = scanner.nextLine();
 
    //      Location location = new Location(name, address);
    //      locations.add(location);

    //      System.out.println("Added a new location: " + location.getName() + " with address: " + location.getAddress() + " with ID: " + location.getLocationID());
        
    //      return location;
    //  }
 

    //  public static void main(String[] args) {
    //      Scanner scanner = new Scanner(System.in);
    //      String mode = args.length > 0 ? args[0] : "";
    //      boolean central = false;
    //      while(true) {
    //          if (mode.equals("central")) {
    //              // Run the application in central mode
    //              central = true;
    //              while(mode.equals("central")){
    //                 System.out.println("Running in central mode");
    //                 System.out.println("Please enter '1' if you would like enter information about Disaster Victims");
    //                 System.out.println("Please enter '2' if you would like to log inquirer queries");
    //                 System.out.println("Please enter '3' to add a new location");
    //                 System.out.println("Please enter '4' to switch to location-based mode");
    //                 System.out.println("Please enter '5' to display all locations and victims");
    //                 System.out.println("Please enter '-1' to exit the program");
    
    //                 int choice = scanner.nextInt();
    
    //                 switch (choice) {
    //                     case -1:
    //                         // Exiting
    //                         System.out.println("Exiting...");
    //                         return;

    //                     case 1:
    //                         // Entering information about Disaster Victims
    //                         if (locations.isEmpty()) {
    //                             System.out.println("No locations have been added. Please add a location by pressing '3'.");
    //                             break;
    //                         }
    //                         editDisasterVictims(-1); // The argument is -1, since we do not yet know the location
    //                         break;

    //                     case 2:
    //                         // Logging inquirer queries
    //                         logInquirerQueries();
    //                         break;
                                
    //                     case 3:
    //                         // Adding a new location
    //                         newLocation();
    //                         break;
                        
    //                     case 4:
    //                         // Switching to location-based mode
    //                         System.out.println("Switching to location-based mode");
    //                         mode = "location";
    //                         break;
                        
    //                     case 5:
    //                         // Displaying all locations and victims
    //                         if(locations.isEmpty()) {
    //                             System.out.println("No locations have been added yet");
    //                             break;
    //                         }
    //                         displayLocationsAndVictims();
    //                         break;
    
    //                     default:
    //                         System.out.println("Invalid choice");
    //                         break;
    //                 }
    //             }
 
    //          } else if (mode.equals("location")) {
    //             // Run the application in location-based mode
    //             int locationID = -1;
    //             if (locations.isEmpty()) {
    //                 System.out.println("No locations have been added. Please add a location in central mode.");
    //                 System.out.println("Switching to central mode");
    //                 mode = "central";
    //             } else {
    //                 boolean valid = false;
                    
    //                 while (!valid) {
    //                     try {
    //                         System.out.println("Please enter a location ID from the following to access:");
    //                         displayLocation();
    //                         locationID = scanner.nextInt();
    //                         Location location = getLocationFromID(locationID);
    //                         valid = true; // Set valid to true if no exception is thrown
    //                     } catch (IllegalArgumentException e) {
    //                         System.out.println(e.getMessage());
    //                     } 
    //                 }
    //             }

    //              while(mode.equals("location")){
    //                 System.out.println("Running in location-based mode");
    //                 System.out.println("Please enter '1' if you would like enter information about Disaster Victims");
    //                 System.out.println("Please enter '2' if you would like to log inquirer queries");
    //                 System.out.println("Please enter '-1' to exit the program");
    
    //                 int choice = scanner.nextInt();
 
    //                 switch (choice) {

    //                     case -1:
    //                         // Exiting
    //                         System.out.println("Exiting...");
    //                         return;
    //                     case 1:
    //                         // Entering information about Disaster Victims
    //                         editDisasterVictims(locationID);
    //                         break;
    //                     case 2:
    //                         // Logging inquirer queries
    //                         logInquirerQueries();
    //                         break;
                    
    
    //                     default:
    //                         System.out.println("Invalid choice");
    //                         break;
    //                 }
    //             }
    //             } else {
    //                 System.out.println("Invalid mode. Please specify 'central' or 'location'.");
    //                 break;
    //             }
    //      } 
    //      scanner.close();
    //  }
     
 }

 