package edu.ucalgary.oop;
/*
@author Group 10
@author Max McEvoy <a href="mailto:max.mcevoy@ucalgary.ca"> max.mcevoy@ucalgary.ca</a>
@author Chukwudiebube Anachebe
@author Daniel Lee
@author Michael Attia
@version 1.2
@since 1.0
*/

/**
 * Nocturnal
 * 
 * Extends animal class
 * there to get feeding times of animal
 */
public class Nocturnal extends Animal{
    private final int FEED_START = 0;
    private final int FEED_WINDOW = 3;
    
    /**
     * Nocturnal are Feed at 12am
     * @param animalID int: ID
     * @param name String: Name
     */
    Nocturnal(int animalID, String name){
        super(animalID, name);
    }

    @Override
    public int getFeedTime() {return -1;}

    @Override
    public int getFeedPrep() {return -1;}

    @Override
    public int getCleanTime() {return -1;}

    @Override
    public int getFeedStart() {return FEED_START;}

    @Override
    public int getFeedWindow() {return FEED_WINDOW;}

    @Override
    public String getSpecies() {return "nocturnal";}
}