package edu.ucalgary.oop;
import java.time.LocalDate;
/*
@author Max McEvoy 30005167<a href="mailto:max.mcevoy@ucalgary.ca">
max.mcevoy@ucalgary.ca</a>
@version 1.1
@since 1.0
*/
import java.util.*;

public class Schedule{
    private List<Animal> animals; // Array of animals (from SQLData file)
    private List<DailyTasks> tasks; // Array of treatments (from SQLData file)
    private List<List<DailyTasks>> hourlySchedule = new ArrayList<>();

    public Schedule(List<Animal> animals, List<DailyTasks> tasks){
        this.animals = animals;       // Creates an array list with animal information
        this.tasks = tasks;           // Creates an array with treatments information
        for (int i = 0; i < 24; i++) {
            this.hourlySchedule.add(new ArrayList<>());
        }
        this.buildSchedule();
    }

    /**
     * Add provided Tasks to avaliable Hour 0-24
     * given startHour, duration and maxWindow constraints
     * @param task a DailyTask that needs to be added 
     */
    public void addTasksToHours(DailyTasks task){
        int startHour = task.getStartHour();
        int duration = task.getDuration();
        int maxWindow = 24;
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
    public void addFeedingToTasks() {
        HashMap<String, String> names = new HashMap<>();
        HashMap<String, Integer> feed = new HashMap<>();
        HashMap<String, Integer> prep = new HashMap<>();
        HashMap<String, Integer> window = new HashMap<>();
        HashMap<String, Integer> startHour = new HashMap<>();
        String description = "Feed - ";
        for (Animal a : this.animals) {
            String species = a.getSpecies();
            names.put(species, names.getOrDefault(species, "") + ", " + a.getName());
            startHour.put(species, a.getFeedStart());
            window.put(species, a.getFeedWindow());
            feed.put(species, feed.getOrDefault(species, 0) + a.getFeedTime());
            prep.put(species, a.getFeedPrep());
        }
        for (String species : feed.keySet()) {
            if (!names.get(species).isEmpty()) {
                this.tasks.add(new DailyTasks(
                        names.get(species).substring(2),
                        description + species,
                        startHour.get(species),
                        feed.get(species),
                        window.get(species),
                        prep.get(species)
                ));
            }
        }
    }

    /**
     * Cleaning tasks only need to be once
     * There is no constraint for
     * start or max window.
     * This function gets all the cleaning info
     * creats a task and adds it to the Hour
     */
    public void addCleaningToTasks(){
        for(Animal a: animals){
            String name = a.getName();
            String des = "Cage Cleaning";
            int startHour = 0;
            int duration = a.getCleanTime();
            int maxWindow = 24;
            DailyTasks clean = new DailyTasks(name,des,startHour,duration,maxWindow);
            addTasksToHours(clean);
        }
    }
    /**
     * For now a simple helper function to build Schedule
     */
    public void buildSchedule(){
        addFeedingToTasks();
        Collections.sort(this.tasks);
        for (DailyTasks t : tasks) {
            addTasksToHours(t);
        }
        addCleaningToTasks();
    }
    
    @Override
    /*
     * Overrides toString will print the entire schdule
     * Formats hour hh:00
     * Formats task * {task} ({animal name(s)})
     * Return "" if null
     */
    public String toString(){
        String formatTask = " * %s (%s)\n";
        String formatHour = "\n%02d:00\n";
        StringBuilder sb = new StringBuilder();
        sb.append(LocalDate.now());
        for (int hour = 0; hour < 24; hour++) {
            List<DailyTasks> treatmentsForHour = hourlySchedule.get(hour); 
            if(!treatmentsForHour.isEmpty()){
                sb.append(String.format(formatHour,hour));
            }
            for (DailyTasks treat : treatmentsForHour) {
                sb.append(String.format(formatTask, treat.getDescription(),treat.getAnimalName()));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args){
        String url = "jdbc:postgresql://localhost:5432/ewr";            // Database url
        String username = "oop";                                        // Username for the database ewr
        String password = "ucalgary";                                   // Password for the database ewr
        SQLData myJDBC = new SQLData(url,username,password);            // Instantiates an SQLData object with the url, username and password
        Schedule schedule = new Schedule(myJDBC.getAnimalList(), myJDBC.getTreatmentTasks());  // Instantiates a Schedule with information form the AnimalList
        

        System.out.println(schedule);
    }
}