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
    private List<DailyTasks> treatments = new ArrayList<>();
    private List<DailyTasks> cleanings = new ArrayList<>();
    private List<List<DailyTasks>> hourlySchedule = new ArrayList<>();
    private List<Integer> numVolunteers = new ArrayList<>();

    public Schedule(List<Animal> animals, List<DailyTasks> treatments){
        this.animals = animals;      
        for (DailyTasks t : treatments) {
            this.treatments.add(t.clone());
        }
        resetHourlySchedule();
    }


    public List<List<DailyTasks>> getHourlySchedule(){
        return this.hourlySchedule;
    }

    public void addBackupVolunteer(int hour){
        int current = this.numVolunteers.get(hour);
        this.numVolunteers.set(hour, current+1);
        
    }
    
    /**
     * Add provided Tasks to avaliable Hour 0-24
     * given startHour, duration and maxWindow constraints
     * @param task a DailyTask that needs to be added 
     */
    public void addTaskToHours(DailyTasks task) throws BackUpVolunteerNeededException{
        int startHour = task.getStartHour();
        int duration = task.getDuration();
        int maxWindow = task.getMaxWindow();
        boolean added = false;
        for (int hour = startHour; hour < startHour+maxWindow; hour++) {
            // Calculate the total duration already scheduled within the hour for this treat's max window
            int scheduledDurationWithinMaxWindow = 0;
            for (DailyTasks scheduledTreatment : hourlySchedule.get(hour)) {
                scheduledDurationWithinMaxWindow += scheduledTreatment.getDuration();
            }
            // Check if adding the treatment to the hour exceeds the max window
            if (scheduledDurationWithinMaxWindow + duration <= 60 * this.numVolunteers.get(hour)) {
                hourlySchedule.get(hour).add(task);
                added = true;
                break;
            }
        }
        if(!added){
            String ScheduleError = "Schedule cannot be made without backup volunteer\n" + task.toString() + "\nCould not be Scheduled";
            this.hourlySchedule.clear();
            for (int i = 0; i < 24; i++) {
                this.hourlySchedule.add(new ArrayList<>());
                this.numVolunteers.add(1);
            }
            throw new BackUpVolunteerNeededException(ScheduleError, task);
        }
    }

    /**
     */
    public void addFeedingToTasks() throws BackUpVolunteerNeededException{
        HashMap<String, List<String>> namesMap = new HashMap<>();
        HashMap<String, Integer> feedMap = new HashMap<>();
        HashMap<String, Integer> prepMap = new HashMap<>();
        HashMap<String, Integer> windowMap = new HashMap<>();
        HashMap<String, Integer> startHourMap = new HashMap<>();
        
        for (Animal a : this.animals) {
            if(!a.isOrphaned()){
                String species = a.getSpecies();
                startHourMap.put(species, a.getFeedStart());
                windowMap.put(species, a.getFeedWindow());
                List<String> speciseNames = namesMap.getOrDefault(species, new ArrayList<>());
                speciseNames.add(a.getName());
                namesMap.put(species, speciseNames);
                prepMap.put(species, a.getFeedPrep());
                feedMap.put(species, a.getFeedTime());
            }
        }
        for (String species : namesMap.keySet()) {
            List<String> nicknames = namesMap.get(species);
            int startHour = startHourMap.get(species);
            int maxWindow = windowMap.get(species);
            int prep = prepMap.get(species);
            String description = "Feeding - " + species;
            boolean added = false;
            do{
                for (int hour = startHour; hour < startHour+maxWindow; hour++) {
                    int scheduledDurationWithinMaxWindow = 0;
                    int feed = feedMap.get(species);
                    for (DailyTasks scheduledTreatment : hourlySchedule.get(hour)) {
                        scheduledDurationWithinMaxWindow += scheduledTreatment.getDuration();
                    }
                    int feedTotal = feed;
                    if(scheduledDurationWithinMaxWindow + (feed+prep)<= 60 * this.numVolunteers.get(hour)) {
                        String foo = nicknames.remove(0);
                        feedTotal += feed;
                        do{
                            if(nicknames.isEmpty()){break;}
                            foo += ", " + nicknames.remove(0);
                            feedTotal += feed;
                        }while(scheduledDurationWithinMaxWindow + (feedTotal+prep)<= 60 * this.numVolunteers.get(hour));
                        DailyTasks task = new DailyTasks(foo, description, startHour, feedTotal,maxWindow,prep);
                        hourlySchedule.get(hour).add(task);
                        added = true;
                        break;
                    }
                }
            } while(!nicknames.isEmpty());
            if(!added){
                    String ScheduleError = "Schedule cannot be made without backup volunteer\n" + species + " Could not be Feed";
                    resetHourlySchedule();
                    throw new BackUpVolunteerNeededException(ScheduleError);
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
            this.cleanings.add(clean);
        }
    }

    /**
     * For now a simple helper function to build Schedule
     * This is what the GUI could call and this could
     * throw the schedule error
     */
    public void buildSchedule(String type) throws BackUpVolunteerNeededException{
        if(type.equals("treatment")){
            Collections.sort(this.treatments);
            for (DailyTasks t : this.treatments) {
                addTaskToHours(t);
            }
        }
        if(type.equals("feeding")){
            addFeedingToTasks();
        }
        if(type.equals("cleaning")){
            addCleaningToTasks();
            for (DailyTasks c : this.cleanings){
                addTaskToHours(c);
            }  
        }
    }

    private void resetHourlySchedule(){
        this.hourlySchedule.clear();
        for (int i = 0; i < 24; i++) {
            this.hourlySchedule.add(new ArrayList<>());
            this.numVolunteers.add(1);
        }
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
        String BackupNeeded = "\n%02d:00 [+ backup volunteer]\n";
        StringBuilder sb = new StringBuilder();
        sb.append(LocalDate.now());
        for (int hour = 0; hour < 24; hour++) {
            List<DailyTasks> treatmentsForHour = hourlySchedule.get(hour); 
            if(!treatmentsForHour.isEmpty()){
                if(numVolunteers.get(hour)>=2){sb.append(String.format(BackupNeeded,hour));}
                else{sb.append(String.format(formatHour,hour));}
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
    public static void main(String[] args) throws BackUpVolunteerNeededException{
        List<Animal> animals = new ArrayList<>();
        List<DailyTasks> treatments = new ArrayList<>();
        animals.add(new Fox(1, "Robin Hood"));
        animals.add(new Fox(2, "Maid Marian"));
        animals.add(new Fox(4, "Prince John"));
        treatments.add(new DailyTasks("Maid Marian", "Teeth Cleaning", 0, 45, 1));
        treatments.add(new DailyTasks("Robin Hood", "Remove arrow from knee", 12, 40, 1));

        Schedule schedule = new Schedule(animals,treatments );  // Instantiates a Schedule with information form the AnimalList
        schedule.buildSchedule("treatment");
        schedule.buildSchedule("feeding");
        schedule.buildSchedule("cleaning");
        System.out.println(schedule);
    }
}