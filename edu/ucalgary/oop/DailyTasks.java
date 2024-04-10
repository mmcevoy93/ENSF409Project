package edu.ucalgary.oop;
/*
@author Max McEvoy 30005167<a href="mailto:max.mcevoy@ucalgary.ca">
max.mcevoy@ucalgary.ca</a>
@version 1.5
@since 1.0
*/

/**
 * DailyTasks
 * 
 * Class that has all the info for tasks that
 * Need to be completed that day
 * 
 * Cleaning
 * Medical Treatments
 * Feeding
 */
public class DailyTasks implements Comparable<DailyTasks>, Cloneable  {
    private String animalName;
    private String description;
    private int startHour;
    private int duration;
    private int maxWindow;
    private int prepTime;

    /**
     * Daily Tasks used for a task without any prep time
     * @param animalName String: Name of animal related
     * @param description String: Task description
     * @param startHour int: time that task can start (0h-24h)
     * @param duration int: duration of task minutes
     * @param maxWindow int: window of time to do task hours
     */
    public DailyTasks(String animalName, String description, int startHour, int duration, int maxWindow) {
        this.animalName = animalName;
        this.description = description;
        this.startHour = startHour;
        this.duration = duration;
        this.maxWindow = maxWindow;
        this.prepTime = 0;
    }

    /**
     * Task that has needed prep time. Used for Cleaning Cages
     * @param animalName String: Name of animal related
     * @param description String: Task description
     * @param startHour int: time that task can start (0h-24h)
     * @param duration int: duration of task in minutes
     * @param maxWindow int: window of time to do task in hours
     * @param prepTime int: time needed to prepare for task in minutes
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
     * @return String: Animal Nickname
     */    
    public String getAnimalName() {
        return this.animalName;
    }
    /**
     * gets Description of task
     * @return String: Task Description
     */
    public String getDescription() {
        return this.description;
    }
    /**
     * gets the start Hour of task
     * @return int: Hour task can start (0h-24h)
     */
    public int getStartHour() {
        return this.startHour;
    }
    /**
     * gets the Duration of task
     * @return int: Duration of task in minutes
     */
    public int getDuration() {
        return this.duration + this.prepTime;
    }
    /**
     * gets maximum window of task
     * @return int: window of time to complete task in hours
     */
    public int getMaxWindow() {
        return this.maxWindow;
    }

    /**
     * gets prep time of task
     * @return int: prep time for the task
     * */
    public int getPrepTime(){
        return this.prepTime;
    }

    @Override
    public DailyTasks clone() {
        try {
            return (DailyTasks) super.clone();
        } catch (CloneNotSupportedException e) {
            // This should never happen since we are Cloneable
            throw new InternalError(e);
        }
    }
    @Override
    public int compareTo(DailyTasks other) {
        // Used to sort by start hour then by max window
        int startHourComparison = Integer.compare(this.startHour, other.startHour);
        if (startHourComparison != 0) {
            return startHourComparison;
        }
        return Integer.compare(this.maxWindow, other.maxWindow);
    }

    @Override
    public String toString(){
        //Used for GUI printing
        String format = "Task: %s for %s\nDuration: %s minutes\nBetween: %s:00 - %s:00";
        return String.format(format, this.description, this.animalName,this.duration, this.startHour, this.startHour+this.maxWindow);
    }
}