package edu.ucalgary.oop;
/*
@author Max McEvoy 30005167<a href="mailto:max.mcevoy@ucalgary.ca">
max.mcevoy@ucalgary.ca</a>
@version 1.5
@since 1.0
*/

public class DailyTasks implements Comparable<DailyTasks> {
    private String animalName;
    private String description;
    private int startHour;
    private int duration;
    private int maxWindow;
    private int prepTime;

    public DailyTasks(String animalName, String description, int startHour, int duration, int maxWindow) {
        this.animalName = animalName;
        this.description = description;
        this.startHour = startHour;
        this.duration = duration;
        this.maxWindow = maxWindow;
        this.prepTime = 0;
    }

    /**
     * Overflow constructor for tasks that require preptime
     */
    public DailyTasks(String animalName, String description, int startHour, int duration, int maxWindow, int prepTime) {
        this.animalName = animalName;
        this.description = description;
        this.startHour = startHour;
        this.duration = duration;
        this.maxWindow = maxWindow;
        this.prepTime = prepTime;
    }
    /**
     * gets Animal Name(s) related to task
     * @return
     */    
    public String getAnimalName() {
        return this.animalName;
    }
    /**
     * gets Description of task
     * @return Description of task
     */
    public String getDescription() {
        return this.description;
    }
    /**
     * gets the start Hour of task
     * @return 24h time task can start
     */
    public int getStartHour() {
        return this.startHour;
    }
    /**
     * gets the Duration of task
     * @return Duration in Minutes
     */
    public int getDuration() {
        return this.duration + this.prepTime;
    }
    /**
     * gets maximum window of task
     * @return Max Window in Hours
     */
    public int getMaxWindow() {
        return this.maxWindow;
    }

    @Override
    /*
     * compares each tasks first by starting hour
     * then by the length of window. small to largerst
     */
    public int compareTo(DailyTasks other) {
        // First, compare by startHour
        int startHourComparison = Integer.compare(this.startHour, other.startHour);
        if (startHourComparison != 0) {
            return startHourComparison; // If startHour differs, return the result
        }
        // If startHour is the same, compare by maxWindow (smaller maxWindow comes
        // first)
        return Integer.compare(this.maxWindow, other.maxWindow);
    }

    @Override
    /**
     * Used to print formated Task info
     * Right now used for GUI
     */
    public String toString(){
        String format = "| %-25s | %2d minutes | %d hour(s)  |\n";
        return String.format(format, this.description, this.duration, this.maxWindow);
    }
}