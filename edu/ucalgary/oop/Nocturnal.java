package edu.ucalgary.oop;
/*
@author Max McEvoy 30005167<a href="mailto:max.mcevoy@ucalgary.ca">
max.mcevoy@ucalgary.ca</a>
@version 1.2
@since 1.0
*/

public class Nocturnal extends Animal{
    private static final int FEED_START = 0;
    private static final int FEED_WINDOW = 3;
    
    
    Nocturnal(int id, String nickname, int feedTime, int feedPrep, int cleanTime){
        super(id, nickname, feedTime, feedPrep, cleanTime, FEED_START, FEED_WINDOW);
    }
    
    @Override
    public int feedStart(){return FEED_START;}
    @Override
    public int feedWindow(){return FEED_WINDOW;}
    
}