package edu.ucalgary.oop;
/*
@author Max McEvoy 30005167<a href="mailto:max.mcevoy@ucalgary.ca">
max.mcevoy@ucalgary.ca</a>
@version 1.1
@since 1.0
*/

/**
 * Diurnal
 * 
 * Extends animal class
 * there to get feeding times of animal
 */
public class Diurnal extends Animal {
    private final int FEED_START = 8;
    private final int FEED_WINDOW = 3;

    /**
     * Diurnal are Feed at 8am
     * @param animalID int: ID
     * @param name String: Name
     */
    public Diurnal(int animalID, String nickname) {
        super(animalID, nickname);
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
        return "diurnal"; // Placeholder value, to be overridden in subclasses
    }
}