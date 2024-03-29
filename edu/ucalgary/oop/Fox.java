

package edu.ucalgary.oop;
/*
@author Max McEvoy 30005167<a href="mailto:max.mcevoy@ucalgary.ca">
max.mcevoy@ucalgary.ca</a>
@version 1.1
@since 1.0
*/

public class Fox extends Nocturnal{
    private static final int FEED_TIME = 5;
    private static final int FEED_PREP = 5;
    private static final int CLEAN_TIME = 5;

    public Fox(int id, String name){
        super(id, name, 1, 1, 1);    
    }

     @Override
    public String getSpecies(){
        return "fox";
    }
    // Getter method for FEED_TIME
    public static int getFeedTime() {
        return FEED_TIME;
    }

    // Getter method for FEED_PREP
    public static int getFeedPrep() {
        return FEED_PREP;
    }

    // Getter method for CLEAN_TIME
    public static int getCleanTime() {
        return CLEAN_TIME;
    }
    
    public static int getFeedStart(){return Nocturnal.FEED_START;}

}