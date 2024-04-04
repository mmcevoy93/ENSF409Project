package edu.ucalgary.oop;
/*
@author Max McEvoy 30005167<a href="mailto:max.mcevoy@ucalgary.ca">
max.mcevoy@ucalgary.ca</a>
@version 1.2
@since 1.0
*/

public class Nocturnal extends Animal{
    private final int FEED_START = 0;
    private final int FEED_WINDOW = 3;
    
    
    Nocturnal(int animalID, String name){
        super(animalID, name);
    }

    @Override
    public int getFeedTime() {
        return -1;
    }

    @Override
    public int getFeedPrep() {
        return -1;
    }

    @Override
    public int getCleanTime() {
        return -1;
    }

    @Override
    public int getFeedStart() {
        return FEED_START;
    }

    @Override
    public int getFeedWindow() {
        return FEED_WINDOW;
    }

    @Override
    public String getSpecies() {
        return "nocturnal"; // Placeholder value, to be overridden in subclasses
    }
}