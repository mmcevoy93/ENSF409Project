package edu.ucalgary.oop;
/*
@author Group 10
@author Max McEvoy <a href="mailto:max.mcevoy@ucalgary.ca"> max.mcevoy@ucalgary.ca</a>
@author Chukwudiebube Anachebe
@author Daniel Lee
@author Michael Attia
@version 1.1
@since 1.0
*/

/**
 * Beaver
 * 
 * Species used to construct animal.
 * Has methods that return values
 * specific to Beaver
 */
public class Beaver extends Diurnal {
    private final int FEED_PREP = 0;
    private final int FEED_TIME = 5;
    private final int CLEAN_TIME = 5;

    /**
     * Beaver needs an ID and a Name
     * @param animalID int: EWR ID
     * @param name StringL EWR Nickname
     */
    public Beaver(int animalID, String name) {
        super(animalID, name);
    }

    @Override
    public int getFeedTime() {return FEED_TIME;}

    @Override
    public int getFeedPrep() {return FEED_PREP;}

    @Override
    public int getCleanTime() {return CLEAN_TIME;}

    @Override
    public String getSpecies() {return "beaver";}
}