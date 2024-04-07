package edu.ucalgary.oop;
/*
@author Max McEvoy 30005167<a href="mailto:max.mcevoy@ucalgary.ca">
max.mcevoy@ucalgary.ca</a>
@version 1.1
@since 1.0
*/

/**
 * Crepuscular
 * 
 * Extends animal class
 * there to get feeding times of animal
 */
public class Crepuscular extends Animal {
    protected final int FEED_START = 19; 
    protected final int FEED_WINDOW = 3;

    /**
     * Crepuscular are Feed at 7pm
     * @param animalID int: ID
     * @param name String: Name
     */
    Crepuscular(int animalID, String name) {
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
        return "crepuscular"; 
    }
}