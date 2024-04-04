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
     * 
     * Good OOP would be breaking this down into smaller methods
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
     * A little convoluted but I didn't want to change Animal anymore
     * Good OOP would probably be breaking this down into smaller methods
     * 
     *  I use HashMaps (Dictionary equivalent in Python) to determine the
     *  feeding info of each species
     *  Species is the key and name, feedtime, prep, window, startH are the values
     *  This way was used so that the simpler Animal class could be used.
     *  Before there was less code but was too convoluted to troubleshoot.
     * 
     * HashMaps are created with each species as the key.
     * used putAll to create a deepcopy of some similar HashMaps
     * 
     * loop through all the animals to get the info relavent to feeding.
     * Adding up the feeding time for each species of animal together.
     * start, window, prep should be the same for each species but it
     * was simple to assigning the value to the key it is a little inefficent.
     * 
     * Once the hashMaps have been populated we loop again through all the species.
     * If there are no names in names we know that that species DNE
     * else we create a new feeding task and add it to our List of DailyTasks
     */
    public void addFeedToList() {
        String allSpecies[] = {"beaver","coyote","fox","porcupine","raccoon"};
        HashMap<String, String> names = new HashMap<>();
        HashMap<String, Integer> feed = new HashMap<>();
        for (String s:allSpecies){
            feed.put(s, 0);
            names.put(s,"");
        }
        HashMap<String, Integer> prep = new HashMap<>();
        prep.putAll(feed);
        HashMap<String, Integer> window = new HashMap<>();
        window.putAll(feed);
        HashMap<String, Integer> startH = new HashMap<>();
        startH.putAll(feed);
        String dsrt = "Feed - ";
        for(Animal a : this.animals){
            String species = a.getSpecies();
            startH.put(species, a.getFeedStart());
            window.put(species, a.getFeedWindow());
            feed.put(species, feed.get(species)+ a.getFeedTime());
            prep.put(species, a.getFeedPrep());
            names.put(species, names.get(species) + ", " + a.getName());
        }
        for (String s : allSpecies){
            if(!names.get(s).equals("")){
                //too long horizontally
                tasks.add(new DailyTasks(
                    names.get(s).toString().substring(2),
                    dsrt + s,
                    startH.get(s),
                    feed.get(s),
                    window.get(s),
                    prep.get(s)
                ));
            }
        }
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