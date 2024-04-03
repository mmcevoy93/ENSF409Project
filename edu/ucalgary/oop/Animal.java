package edu.ucalgary.oop;

/*
@author Max McEvoy 30005167<a href="mailto:max.mcevoy@ucalgary.ca">
max.mcevoy@ucalgary.ca</a>
@version 1.4
@since 1.0
*/
import java.util.regex.*;
import java.util.*;

/**Animal class
 * Animal contains all the relavent info needed to 
 * complete all medical treatments, feedings and cleaning
 */

// todo: include a throw to the IllegalArgumentException
public class Animal {
    private int animalID;
    private String nickname;
    private boolean feed = false;
    private boolean clean = false;
    private boolean orphaned;
    private int feedStart;
    private int feedWindow;
    private int feedPrep;
    private int feedTime;
    private int cleanTime;
    private final Pattern PATTERN = Pattern.compile("\\b\\w+(,\\s+\\w+)+\\b");

    /**
     * Animal constructor
     * Needs all the info about animal
     * 
     * @param animalID ID of animal
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

        Matcher matcher = PATTERN.matcher(nickname);
        if(matcher.find()){this.orphaned = true;}
        else{this.orphaned = false;}
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
     * Checks if animal's cage has been cleaned yet
     * @return boolean of cage cleaned status
     */
    public int getCleanTime(){return this.cleanTime;}
     /**
     * Checks if animal's cage has been cleaned yet
     * @return boolean of cage cleaned status
     */


     /**
     * Checks if animal has been feed yet
     * @return boolean on animal feeding status for day
     */
    public boolean getFeed(){return this.feed;}

    public boolean getOrphaned(){return this.orphaned;}

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
    public int feedStart(){return -1;}

    /**
     * gets the feeding window time in hours
     * @return amount of hours you have from start
     */
    public int feedWindow(){return -1;}

    public String printInfo() {
        String format = "| %-3s | %-24s | %-15s |\n";
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(format, String.valueOf(this.animalID), this.nickname, "this.species"));
        return sb.toString();
    }


}