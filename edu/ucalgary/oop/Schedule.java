package edu.ucalgary.oop;
/*
@author Max McEvoy 30005167<a href="mailto:max.mcevoy@ucalgary.ca">
max.mcevoy@ucalgary.ca</a>
@version 1.1
@since 1.0
*/
import java.util.*;
import java.time.LocalDate;


public class Schedule{

    private List<Animal> animals; // Array of animals (from SQLData file)
    private List<Treatment> treatments; // Array of treatments (from SQLData file)
    private int dayHours[] = new int[24]; // Array of 24, representative of 24 hours in a day
    private String printHours[] = new String[24]; // Array of 24  -- not sure WHY THOUGH?

    public Schedule(List<Animal> animals, List<Treatment> treatments){
        this.animals = animals;                 // Creates an array list with animal information
        this.treatments = treatments;           // Creates an array with treatments information

        Arrays.fill(this.dayHours, 60);     // Fills the 24 indices "daysHour" array with 60
                                                // Which is representative of 60 minutes
        printTreatments();                      // prints Treatments
    }

    public void printTreatments(){
        /**
         * Prints the treatments of the given animal for each hour
         */

        int time;                                               // Time variable to store the StartHour
        String name, description = "";                          // Name, and Description variable to store the name and task of the animal
        int lastTime = -1;                                      // Placeholder for tracking the time
        Collections.sort(this.treatments);//sorts by time
        for (Treatment t : this.treatments){
            // For loop that goes through the treatment array
            // For each treatment, accesses information from the given index
            time = t.getStartHour();                                    // Initialize time with the StartHour from the Treatment index
            if(lastTime != time){                                       // Checks if lastTime is equal to
                System.out.println(String.format("\n%02d:00",time));    // Prints out the time of the treatment
            }
            lastTime = time;                                            // Equates the treatment time to lastTime
                                                                        // Done to ensure that the time is not printed again
            name = t.getAnimalName();                                   // Assign the animalName to the name variable
            description = t.getDescription();                           // Assign the description to the description variable
            this.dayHours[time] -= t.getDuration();                     // Subtract the duration of the task form the hour of the day
            System.out.print("* " + description + " (" + name +") - "); // Prints the description of the task and the name of the animal
            System.out.println("Time remaining in hour: " + this.dayHours[time]); // Pirnts the remaining time in the given hour for tasks
        }
        System.out.println("Number of Nocturnals: " + AnimalCounter.countNocturnals(animals)); // Prints the number of Nocturnal Animals
    }

    public static void main(String[] args){
        String url = "jdbc:postgresql://localhost:5432/ewr";            // Database url
        String username = "oop";                                        // Username for the database ewr
        String password = "ucalgary";                                   // Password for the database ewr
        SQLData myJDBC = new SQLData(url,username,password);            // Instantiates an SQLData object with the url, username and password


        Schedule schedule = new Schedule(myJDBC.getAnimalList(), myJDBC.getTreatmentList());  // Instantiates a Schedule with information form the AnimalList

        System.out.println();
        int count = 0;
        // Ebube's Test *COMMENT OUT IF NOT NEEDED*
        // 1. Print out information from treatements list
        System.out.println("EBUBE'S TEST TO VIEW - PRINT TREATMENTS");
        for (Treatment treatment : schedule.treatments){
            count++;
            System.out.println(String.format("Animal Name %d: " + treatment.getAnimalName(), count));
            System.out.println("Treatment: " + treatment.getDescription());
            System.out.println("Start hour: " + treatment.getStartHour());
            System.out.println("maxWindow: " + treatment.getMaxWindow());
            System.out.println();
            System.out.println();

        }

        // 2. Print out information from animals list *COMMENT OUT IF NOT NEEDED*
        System.out.println();
        System.out.println("EBUBE'S TEST TO VIEW - PRINT ANIMALS");
        for (Animal animal : schedule.animals){
            count++;
            System.out.println(String.format("Animal ID %d: " + animal.getAnimalID(), count));
            System.out.println("Nickname: " + animal.getName());
            System.out.println("Feed Time: " + animal.getfeedTime());
            System.out.println("Feed Window: " + animal.feedWindow());
            System.out.println("Feed Prep: ?" );
            System.out.println("Clean Time: ?");
            System.out.println();
            System.out.println();

        }

    }

}