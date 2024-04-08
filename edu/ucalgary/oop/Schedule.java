package edu.ucalgary.oop;
import java.time.LocalDate;
/*
@author Max McEvoy 30005167<a href="mailto:max.mcevoy@ucalgary.ca">
max.mcevoy@ucalgary.ca</a>
@version 1.9
@since 1.0
*/
import java.util.*;

/** 
 * Schedule Class
 *  
 * This class uses SQLData to create animal and tasks lists
 * Builds Schedule for EWR volunteers:
 * Cleaning cages
 * Feeding
 * Medical Treatments
 * 
 * If a schedule can not be created it will catch it 
 */
public class Schedule{
    private List<Animal> animals; // Array of animals (from SQLData file)
    private List<DailyTasks> tasks; // Array of treatments (from SQLData file)
    private List<List<DailyTasks>> hourlySchedule = new ArrayList<>();

    public Schedule(List<Animal> animals, List<DailyTasks> tasks){
        this.animals = animals;      
        this.tasks = tasks;
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
            //TODO - Throw an argument that schedule cannot be made without backup volunteer
        }
    }

    /**
     * HashMaps used for need task info
     * Species is the key for each HashMap
     * name, feedtime, prep, window, startH are the values
     * 
     * We loop through all the animals in the EWR
     * Initially get it's species
     * for each HashMap we set the appropriate value
     * 
     * For some where we are getting a cumlitive amount
     * we use getorDefault to set a default if empty
     * 
     * then for all species we loop through and add all
     * this data to Daily tasks
     */
    //TODO - Does not consider feeding same species animals at different hours
    public void addFeedingToTasks() {
        HashMap<String, List<String>> names = new HashMap<>();
        HashMap<String, Integer> feed = new HashMap<>();
        HashMap<String, Integer> prep = new HashMap<>();
        HashMap<String, Integer> window = new HashMap<>();
        HashMap<String, Integer> startHour = new HashMap<>();
        String description = "Feed - ";
        for (Animal a : this.animals) {
            String species = a.getSpecies();
            startHour.put(species, a.getFeedStart());
            window.put(species, a.getFeedWindow());
            List<String> speciseNames = names.getOrDefault(species, new ArrayList<>());
            speciseNames.add(a.getName());
            names.put(species, speciseNames);
            prep.put(species, a.getFeedPrep());
            feed.put(species, feed.getOrDefault(species, 0) + a.getFeedTime());
        }
        for (String species : Animal.getAllSpecies()) {
            if (names.containsKey(species)) {
                this.tasks.add(new DailyTasks(
                        String.join(", ", names.get(species)),
                        description + species, 
                        startHour.get(species), feed.get(species),
                        window.get(species),    prep.get(species)
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
     * This is what the GUI could call and this could
     * throw the schedule error
     */
    public void buildSchedule(){
        addFeedingToTasks();
        Collections.sort(this.tasks);
        for (DailyTasks t : tasks) {
            addTasksToHours(t);
        }
        addCleaningToTasks();//lowest priority
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

    /**
     * to be removed just for testing right now
     * @param args
     */
    public static void main(String[] args){
        String url = "jdbc:postgresql://localhost:5432/ewr";            // Database url
        String username = "oop";                                        // Username for the database ewr
        String password = "ucalgary";                                   // Password for the database ewr
        SQLData myJDBC = new SQLData(url,username,password);            // Instantiates an SQLData object with the url, username and password
        Schedule schedule = new Schedule(myJDBC.getAnimalList(), myJDBC.getTreatmentTasks());  // Instantiates a Schedule with information form the AnimalList
        System.out.println(schedule);
    }
}