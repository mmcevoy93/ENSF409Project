package edu.ucalgary.oop;
/*
@author Max McEvoy 30005167<a href="mailto:max.mcevoy@ucalgary.ca">
max.mcevoy@ucalgary.ca</a>
@version 1.1
@since 1.0
*/

import java.util.*;

// todo: create a throw to an IllegalArgumentException
public class DailyTasks implements Comparable<DailyTasks> {
    private String animalName;
    private String description;
    private int startHour;
    private int duration;
    private int maxWindow;

    public DailyTasks(String animalName, String description, int startHour, int duration, int maxWindow) {
        this.animalName = animalName;
        this.description = description;
        this.startHour = startHour;
        this.duration = duration;
        this.maxWindow = maxWindow;
    }

    public String getAnimalName() {
        return this.animalName;
    }

    public String getDescription() {
        return this.description;
    }

    public int getStartHour() {
        return this.startHour;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getMaxWindow() {
        return this.maxWindow;
    }

    @Override
public int compareTo(DailyTasks other) {
    // First, compare by startHour
    int startHourComparison = Integer.compare(this.startHour, other.startHour);
    if (startHourComparison != 0) {
        return startHourComparison; // If startHour differs, return the result
    }
    // If startHour is the same, compare by maxWindow (smaller maxWindow comes first)
    return Integer.compare(this.maxWindow, other.maxWindow);
}
}