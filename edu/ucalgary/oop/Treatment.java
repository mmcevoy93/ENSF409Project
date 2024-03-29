package edu.ucalgary.oop;
/*
@author Max McEvoy 30005167<a href="mailto:max.mcevoy@ucalgary.ca">
max.mcevoy@ucalgary.ca</a>
@version 1.1
@since 1.0
*/


import java.util.*;

// todo: create a throw to an IllegalArgumentException
public class Treatment implements Comparable<Treatment> {
    private String animalName;
    private String description;
    private int startHour;
    private int duration;
    private int maxWindow;

    public Treatment(String animalName, String description, int startHour, int duration, int maxWindow) {
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
    public int compareTo(Treatment other) {
        return Integer.compare(this.startHour, other.startHour);
    }
}