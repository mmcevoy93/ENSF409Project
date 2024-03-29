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
        addFeedToList();
        Collections.sort(this.treatments);//sorts by time
        

        // Step 2: Create hourly schedule
        List<List<Treatment>> hourlySchedule = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            hourlySchedule.add(new ArrayList<>());
        }

        for (Treatment treatment : treatments) {
            int startHour = treatment.getStartHour();
            int duration = treatment.getDuration();
            int maxWindow = treatment.getMaxWindow();
            int endHour = startHour + (duration / 60) + ((duration % 60 == 0) ? 0 : 1);
            for (int hour = startHour; hour < endHour; hour++) {
            
                int scheduledDuration = 0;
                // Calculate total duration already scheduled within the hour
                for (Treatment scheduledTreatment : hourlySchedule.get(hour)) {
                    scheduledDuration += scheduledTreatment.getDuration();
                }
                // Check if there's enough time available in the hour for task
                if (scheduledDuration + duration <= 60) {
                    hourlySchedule.get(hour).add(treatment);
                    break; // Exit loop if treatment is scheduled in this hour
                }
            
            }
        }
        for (int hour = 0; hour < 24; hour++) {
            System.out.println(String.format("\n%02d:00",hour));
            List<Treatment> treatmentsForHour = hourlySchedule.get(hour);
            for (Treatment treatment : treatmentsForHour) {
                System.out.println("* " + treatment.getDescription() + " (" +  treatment.getAnimalName() + ")");
            }
            System.out.println();
        }


        
    }

    public void addFeedToList(Class<? extends Animal> animalClass, String description, int feedPrep, int startHour) {
        if (AnimalCounter.countAnimals(animals, animalClass) != 0) {
            String animalName = AnimalCounter.getAnimalNames(animals, animalClass);
            int duration = AnimalCounter.getTotalFeedTime(animals, animalClass) + feedPrep;
            treatments.add(new Treatment(animalName, description, startHour, duration, 3));
        }
    }
    public void addFeedToList() {
        addFeedToList(Beaver.class, "Feeding - beaver", Beaver.getFeedPrep(),Beaver.getFeedStart());
        addFeedToList(Coyote.class, "Feeding - coyote", Coyote.getFeedPrep(),Coyote.getFeedStart());
        addFeedToList(Fox.class, "Feeding - fox", Fox.getFeedPrep(),Fox.getFeedStart());
        addFeedToList(Porcupine.class, "Feeding - porcupine", Porcupine.getFeedPrep(),Porcupine.getFeedStart());
        addFeedToList(Raccoon.class, "Feeding - raccoon", Raccoon.getFeedPrep(),Raccoon.getFeedStart());
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
        // System.out.println("EBUBE'S TEST TO VIEW - PRINT TREATMENTS");
        // for (Treatment treatment : schedule.treatments){
        //     count++;
        //     System.out.println(String.format("Animal Name %d: " + treatment.getAnimalName(), count));
        //     System.out.println("Treatment: " + treatment.getDescription());
        //     System.out.println("Start hour: " + treatment.getStartHour());
        //     System.out.println("maxWindow: " + treatment.getMaxWindow());
        //     System.out.println();
        //     System.out.println();

        // }

        // // 2. Print out information from animals list *COMMENT OUT IF NOT NEEDED*
        // System.out.println();
        // System.out.println("EBUBE'S TEST TO VIEW - PRINT ANIMALS");
        // for (Animal animal : schedule.animals){
        //     count++;
        //     System.out.println(String.format("Animal ID %d: " + animal.getAnimalID(), count));
        //     System.out.println("Nickname: " + animal.getName());
        //     System.out.println("Feed Time: " + animal.getfeedTime());
        //     System.out.println("Feed Window: " + animal.feedWindow());
        //     System.out.println("Feed Prep: ?" );
        //     System.out.println("Clean Time: ?");
        //     System.out.println();
        //     System.out.println();

        //}

    }

}