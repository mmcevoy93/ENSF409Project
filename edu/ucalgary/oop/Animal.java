package edu.ucalgary.oop;

/*
@author Max McEvoy 30005167<a href="mailto:max.mcevoy@ucalgary.ca">
max.mcevoy@ucalgary.ca</a>
@version 1.4
@since 1.0
*/

import java.util.*;

/**Animal class
 * Animal contains all the relavent info needed to 
 * complete all medical treatments, feedings and cleaning
 */
public class Animal{
    protected int animalID;
    protected String nickname;
    protected boolean feed = false;
    protected boolean clean = false;
    protected int feedStart;
    protected int feedWindow;
    protected int feedPrep;
    protected int feedTime;
    protected int cleanTime;

    /**
     * Animal constructor
     * Needs all the info about animal
     * 
     * @param int ID of animal
     * @param nickname Nickname of animal
     * @param feedPrep Time in minutes to prep for animal feeding
     * @param feedTime time to feed animal in minutes
     * @param cleanTime time it takes to clean animal cage in minutes
     * @param feedStart using 24h time that feeding can start
     * @param feedWindow Window of time in hours for animal to be feed in
     */
    public Animal(  int id, String nickname, int feedPrep, int feedTime, 
                    int cleanTime, int feedStart, int feedWindow){
        this.animalID = id;
        this.nickname = nickname;
        this.feedTime = feedTime;
        this.feedWindow = feedWindow;
        this.feedPrep = feedPrep;
        this.cleanTime = cleanTime;
    }

     /**
     * Gets ID of animal
     * @return int of animal ID
     */
    public int getAnimalID() {return this.animalID;}

     /**
     * Gets the nickname of animal
     * @return String of nickname
     */
    public String getName() {return this.nickname;}

     /**
     * Gets the time it takes to feed animal
     * @return integer of time to feed in minutes
     */
    public int getfeedTime() {return this.feedTime;}

     /**
     * Checks if animal's cage has been cleaned yet
     * @return boolean of cage cleaned status
     */
    public boolean getClean(){return this.clean;}

     /**
     * Checks if animal has been feed yet
     * @return boolean on animal feeding status for day
     */
    public boolean getFeed(){return this.feed;}

    /**
     * Sets the daily cleaning status of animal cage
     * each animal needs their cage cleaned once a day
     * @param boolean of cage cleaning status
     */
    public void setClean(boolean clean){ this.clean = clean;}

    /**
     * Sets the daily feeding status of animal
     * each animal needs to be feed once a day
     * @param boolean of feed status of animal
     */
    public void setFeed(boolean feed){this.feed = feed;}

    /**
     * gets the name of the species
     * @return String of species
     */
    public String getSpecies(){return "void";}

    /**
     * gets the feed start time in 24 hours
     * @return the start hour that animal can be feed at
     */
    public int FeedStart(){return -1;}

    /**
     * gets the feeding window time in hours
     * @return amount of hours you have from start
     */
    public int FeedWindow(){return -1;}
}

class Test{
    private static List<Animal> animals = new ArrayList<>();
    public static void main(String args[]) {
        Fox fox = new Fox(12, "eppe");
        Raccoon rac = new Raccoon(12, "eppe");
        System.out.println(fox.getfeedTime());
        animals.add(fox);
        animals.add(rac);
        System.out.println(animals.get(0).getSpecies());

    }
}