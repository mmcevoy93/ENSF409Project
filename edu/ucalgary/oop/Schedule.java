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
    private List<DailyTasks> tasks; // Array of treatments (from SQLData file)
    private List<List<DailyTasks>> hourlySchedule = new ArrayList<>();

    public Schedule(List<Animal> animals, List<DailyTasks> tasks){
        this.animals = animals;                 // Creates an array list with animal information
        this.tasks = tasks;           // Creates an array with treatments information

        //Initializes hourlySchedule data structure
        for (int i = 0; i < 24; i++) {
            hourlySchedule.add(new ArrayList<>());
        }
         // does the sorting operations, generates cleaning tasks and prints schdule to terminal
        printTreatments();
    }

    /**
     * This will likely be broken down and simplified
     * this will add feeding tasks to daily tasks. 
     * sort tasks by time then max window
     * assign the tasks to the hour they can fit
     * keep track of minutes used in a given hour
     * create indivdual cleaning tasks and add to hour
     * print the hour data structure in schdule format
     */
    public void printTreatments(){
        addFeedToList();
        Collections.sort(this.tasks);//sorts by time then by max window
        
        for (DailyTasks task: tasks) {
            int startHour = task.getStartHour();
            int duration = task.getDuration();
            int maxWindow = task.getMaxWindow();
            int endHour = startHour + (duration / 60) + ((duration % 60 == 0) ? 0 : 1);

            for (int hour = startHour; hour < hour+maxWindow; hour++) {
                // Calculate the total duration already scheduled within the hour for this treat's max window
                int scheduledDurationWithinMaxWindow = 0;
                for (DailyTasks scheduledTreatment : hourlySchedule.get(hour)) {
                    scheduledDurationWithinMaxWindow += scheduledTreatment.getDuration();
                }
                // Check if adding the treatment to the hour exceeds the max window
                if (scheduledDurationWithinMaxWindow + duration <= 60) {
                    hourlySchedule.get(hour).add(task);
                    break;
                }
            }
        }
        //Loops through all animals including orphaned ones.
        //creates a new cleaning task with start time at 00:00
        //and maxwindow 24hours. Anytime during the day
        //Adds this to hour using same logic above.
        //This is Cleaning is lower priority than feeding and treatments 
        //due to the maxWindow so it is arranged seperately to the rest
        for(Animal a: animals){
            String name = a.getName();
            String des = "Cage Cleaning";
            int startHour = 0;
            int duration = a.getCleanTime();
            int maxWindow = 24;
            DailyTasks clean = new DailyTasks(name,des,startHour,duration,maxWindow);
            for (int hour = startHour; hour < hour+maxWindow; hour++) {
                // Calculate the total duration already scheduled within the hour for this treat's max window
                int scheduledDurationWithinMaxWindow = 0;
                for (DailyTasks scheduledTreatment : hourlySchedule.get(hour)) {
                    scheduledDurationWithinMaxWindow += scheduledTreatment.getDuration();
                }
                // Check if adding the treatment to the hour exceeds the max window
                if (scheduledDurationWithinMaxWindow + duration <= 60) {
                    hourlySchedule.get(hour).add(clean);
                    break;
                }
            }
        }
        //print test schedule
        //loops through hours of day and prints out all the tasks in desired schedule format.
        for (int hour = 0; hour < 24; hour++) {
            List<DailyTasks> treatmentsForHour = hourlySchedule.get(hour);//list of treatments at a single hour 
            if(!treatmentsForHour.isEmpty()){//check if any treatments are in that hour
                System.out.println(String.format("\n%02d:00",hour));
            }
            //print off each taks in this hour time frame
            for (DailyTasks treat : treatmentsForHour) {
                System.out.println("* " + treat.getDescription() + " (" +  treat.getAnimalName() + ")");
            }
        }
    }

    /**
     * given an extended Animal class, feeding info will add ned Feeding tasks to
     * Daily tasks list. Will likely chnage how this works so it is less confusing.
     */
    public void addFeedToList(Class<? extends Animal> animalClass, String description, int feedPrep, int startHour) {
        if (AnimalCounter.countAnimals(animals, animalClass) != 0) {
            String animalName = AnimalCounter.getAnimalNames(animals, animalClass);
            int duration = AnimalCounter.getTotalFeedTime(animals, animalClass) + feedPrep;
            tasks.add(new DailyTasks(animalName, description, startHour, duration, 3));
        }
    }
    /**
     * like above function this one calls the same addFeedtoList method but uses overflow one instead
     * again will likely change this out
     */
    public void addFeedToList() {
        addFeedToList(Beaver.class, "Feeding - beaver", Beaver.getFeedPrep(),Beaver.getFeedStart());
        addFeedToList(Coyote.class, "Feeding - coyote", Coyote.getFeedPrep(),Coyote.getFeedStart());
        addFeedToList(Fox.class, "Feeding - fox", Fox.getFeedPrep(),Fox.getFeedStart());
        addFeedToList(Porcupine.class, "Feeding - porcupine", Porcupine.getFeedPrep(),Porcupine.getFeedStart());
        addFeedToList(Raccoon.class, "Feeding - raccoon", Raccoon.getFeedPrep(),Raccoon.getFeedStart());
    }
    
    /**
     * Ebube's Test *COMMENT OUT IF NOT NEEDED*
     */
    public void printTesting(){
        // System.out.println();
        // int count = 0;
        // //1. Print out information from treatements list
        // System.out.println("EBUBE'S TEST TO VIEW - PRINT TREATMENTS");
        // for (DailyTasks task : this.tasks){
        //     count++;
        //     System.out.println(String.format("Animal Name %d: " + task.getAnimalName(), count));
        //     System.out.println("Treatment: " + task.getDescription());
        //     System.out.println("Start hour: " + task.getStartHour());
        //     System.out.println("maxWindow: " + task.getMaxWindow());
        //     System.out.println();
        //     System.out.println();
        // }

        // // 2. Print out information from animals list *COMMENT OUT IF NOT NEEDED*
        // System.out.println();
        // System.out.println("EBUBE'S TEST TO VIEW - PRINT ANIMALS");
        // for (Animal animal : this.animals){
        //     count++;
        //     System.out.println(String.format("Animal ID %d: " + animal.getAnimalID(), count));
        //     System.out.println("Nickname: " + animal.getName());
        //     System.out.println("Feed Time: " + animal.getfeedTime());
        //     System.out.println("Feed Window: " + animal.feedWindow());
        //     System.out.println("Feed Prep: " +animal.getFeedPrep());
        //     System.out.println("Clean Time: ?" + animal.getCleanTime());
        //     System.out.println();
        //     System.out.println();
        // }
    }


    public static void main(String[] args){
        String url = "jdbc:postgresql://localhost:5432/ewr";            // Database url
        String username = "oop";                                        // Username for the database ewr
        String password = "ucalgary";                                   // Password for the database ewr
        SQLData myJDBC = new SQLData(url,username,password);            // Instantiates an SQLData object with the url, username and password
        Schedule schedule = new Schedule(myJDBC.getAnimalList(), myJDBC.getTreatmentTasks());  // Instantiates a Schedule with information form the AnimalList
        //schedule.printTesting();      //Moved Ebube's testing to it's own method
    }
}