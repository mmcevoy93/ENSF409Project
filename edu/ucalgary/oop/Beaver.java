package edu.ucalgary.oop;
/*
@author Max McEvoy 30005167<a href="mailto:max.mcevoy@ucalgary.ca">
max.mcevoy@ucalgary.ca</a>
@version 1.1
@since 1.0
*/

public class Beaver extends Diurnal {
    private static final int FEED_TIME = ;
    private static final int FEED_PREP = ;
    private static final int CLEAN_TIME = ;

    public Beaver(int id, String name) {
        super(id, name, FEED_TIME, FEED_PREP, CLEAN_TIME);
    }

    @Override
    public String getSpecies() { return "beaver"; }
}