package edu.ucalgary.oop;
/*
@author Max McEvoy 30005167<a href="mailto:max.mcevoy@ucalgary.ca">
max.mcevoy@ucalgary.ca</a>
@version 1.1
@since 1.0
*/

public class Crepuscular extends Animal {
    protected static final int FEED_START = 19; 
    protected static final int FEED_WINDOW = 3;

    Crepuscular(int id, String nickname, int feedTime, int feedPrep, int cleanTime) {
        super(id, nickname, feedPrep, feedTime, cleanTime, FEED_START, FEED_WINDOW);
    }

    @Override
    public int feedStart() { return FEED_START; }
    @Override
    public int feedWindow() { return FEED_WINDOW; }
}