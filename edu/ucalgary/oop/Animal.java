package edu.ucalgary.oop;
/*
@author Group 10
@author Max McEvoy <a href="mailto:max.mcevoy@ucalgary.ca"> max.mcevoy@ucalgary.ca</a>
@author Chukwudiebube Anachebe
@author Daniel Lee
@author Michael Attia
@version 2.1
@since 1.0
*/
import java.util.regex.*;

/** 
 * Animal class
 *  
 * Animal contains all the relevant info needed to complete all medical treatments, feedings and cleaning
 * Animal is composed of Species and time of day they are active
 */
public class Animal{
    private final int ID;
    private final String NICKNAME;
    private boolean orphan;
    
    /** 
     * Constructor
     * @param animalID The ID of the animal
     * @param nickname The nickname of the animal
     */
    public Animal(int animalID, String nickname) {
        this.ID = animalID;
        this.NICKNAME = nickname;
        this.orphan = checkOrphaned(nickname);
    }
    
    /** 
     * Checks if the nickname has comma-separated values
     * @param nickname The nickname of the animal
     * @return true if the nickname has comma-separated values, false otherwise
     */
    public boolean checkOrphaned(String nickname) {
        Pattern has_comma = Pattern.compile("\\b\\w+(,\\s+\\w+)+\\b");
        Matcher match = has_comma.matcher(nickname);
        return match.find();
    }
    
    /** 
     * Gets the ID of the animal
     * @return int: The ID of the animal
     */
    public int getID() {return ID;}
    
    /** 
     * Gets the name of the animal
     * @return String: The name of the animal
     */
    public String getName() {return NICKNAME;}
    
    /** 
     * Checks if the animal is orphaned
     * @return boolean: animal is orphaned
     */
    public boolean isOrphaned() {return orphan;}
    
    /** 
     * Gets the feed time of the animal
     * @return int: The feed min
     */
    public int getFeedTime() {return -1;}
    
    /** 
     * Gets the feed preparation time of the animal
     * @return int: The feed prep min
     */
    public int getFeedPrep() {return -1;}
    
    /** 
     * Gets the cleaning time of the animal
     * @return int: Clean time min
     */
    public int getCleanTime() {return -1;}
    
    /** 
     * Gets the feed start time of the animal
     * @return int: The feed start time (0h-24h)
     */
    public int getFeedStart() {return -1;}
    
    /** 
     * Gets the feed window of the animal
     * @return int: The feed window hours
     */
    public int getFeedWindow() {return -1;}
    
    /** 
     * Gets the species of the animal
     * @return String: The species of the animal
     */
    public String getSpecies() {return "void";}

//    /**
//     * Static function that returns all species in EWR
//     * @return String[]: species in EWR
//     */
//    public static String[] getAllSpecies(){
//        return new String[] {"beaver","coyote","fox","porcupine","raccoon"};
//    }
    
    /** 
     * Prints information about the animal
     * @return String: Information about the animal
     */
    @Override
    public String toString() {
        return String.format("| %-3s | %-24s | %-15s |\n", ID, NICKNAME, getSpecies());
    }
}
