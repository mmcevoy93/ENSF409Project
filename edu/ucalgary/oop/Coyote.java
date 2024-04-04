package edu.ucalgary.oop;
/*
@author Max McEvoy 30005167<a href="mailto:max.mcevoy@ucalgary.ca">
max.mcevoy@ucalgary.ca</a>
@version 1.1
@since 1.0
*/

public class Coyote extends Crepuscular {
    private final int FEED_TIME = 5;
    private final int FEED_PREP = 10;
    private final int CLEAN_TIME = 5;

    public Coyote(int animalID, String name) {
        super(animalID, name);
    }
    @Override
    public int getFeedTime() {
        return FEED_TIME;
    }
    @Override
    public int getFeedPrep() {
        return FEED_PREP;
    }
    @Override
    public int getCleanTime() {
        return CLEAN_TIME;
    }

    @Override
    public String getSpecies() {
        return "coyote";
    }
}