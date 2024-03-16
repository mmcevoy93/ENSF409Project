package edu.ucalgary.oop;
/*
@author Max McEvoy 30005167<a href="mailto:max.mcevoy@ucalgary.ca">
max.mcevoy@ucalgary.ca</a>
@version 1.1
@since 1.0
*/

public class Raccoon extends  Nocturnal{
    private final int FEED_TIME = 5;
    private final int FEED_PREP = 5;
    private final int CLEAN_TIME = 4;
    public Raccoon(int id, String name){
        super(id, name, FEED_TIME, FEED_PREP, CLEAN_TIME);   
    }

     @Override
    public String getSpecies(){
        return "raccoon";
    }

}