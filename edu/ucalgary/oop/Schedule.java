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
        Collections.sort(this.treatments);//sorts by time then by max window
        List<List<Treatment>> hourlySchedule = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            hourlySchedule.add(new ArrayList<>());
        }
        //this works to sort if all the tasks are less than an hour
        // System.out.println(AnimalCounter.getAnimalNames(animals, Coyote.class));
        // System.out.println(AnimalCounter.getTotalFeedTime(animals, Coyote.class));

        for (Treatment treat : treatments) {
            int startHour = treat.getStartHour();
            int duration = treat.getDuration();
            int maxWindow = treat.getMaxWindow();
            int endHour = startHour + (duration / 60) + ((duration % 60 == 0) ? 0 : 1);
            //Not working yet but 
            //System.out.println("* " + treat.getDescription() + " (" +  treat.getAnimalName() + ")");
            for (int hour = startHour; hour < hour+maxWindow; hour++) {
                // Calculate the total duration already scheduled within the hour for this treat's max window
                int scheduledDurationWithinMaxWindow = 0;
                for (Treatment scheduledTreatment : hourlySchedule.get(hour)) {
                    scheduledDurationWithinMaxWindow += scheduledTreatment.getDuration();
                }
                //System.out.println(scheduledDurationWithinMaxWindow+duration);
                // Check if adding the treatment to the hour exceeds the max window
                if (scheduledDurationWithinMaxWindow + duration <= 60) {
                    hourlySchedule.get(hour).add(treat);
                    break;
                }
                }
                // System.out.println();
        }
        //clean cage
        //similar to above 
        for(Animal a: animals){
            String name = a.getName();
            String des = "Cage Cleaning";
            int startHour = 0;
            int duration = a.getCleanTime();
            int maxWindow = 24;
            Treatment clean = new Treatment(name,des,startHour,duration,maxWindow);
            for (int hour = startHour; hour < hour+maxWindow; hour++) {
                // Calculate the total duration already scheduled within the hour for this treat's max window
                int scheduledDurationWithinMaxWindow = 0;
                for (Treatment scheduledTreatment : hourlySchedule.get(hour)) {
                    scheduledDurationWithinMaxWindow += scheduledTreatment.getDuration();
                }
                System.out.println(scheduledDurationWithinMaxWindow+duration);
                // Check if adding the treatment to the hour exceeds the max window
                if (scheduledDurationWithinMaxWindow + duration <= 60) {
                    hourlySchedule.get(hour).add(clean);
                    break;
                }
                }

        }



        

        //print test schedule
        for (int hour = 0; hour < 24; hour++) {
            List<Treatment> treatmentsForHour = hourlySchedule.get(hour);//list of treatments at a single hour 
            if(!treatmentsForHour.isEmpty()){//check if any treatments are in that hour
                System.out.println(String.format("\n%02d:00",hour));
            }
            //print off each taks in this hour time frame
            for (Treatment treat : treatmentsForHour) {
                System.out.println("* " + treat.getDescription() + " (" +  treat.getAnimalName() + ")");
            }
            ;
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
        // for (Treatment treat : schedule.treatments){
        //     count++;
        //     System.out.println(String.format("Animal Name %d: " + treat.getAnimalName(), count));
        //     System.out.println("Treatment: " + treat.getDescription());
        //     System.out.println("Start hour: " + treat.getStartHour());
        //     System.out.println("maxWindow: " + treat.getMaxWindow());
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