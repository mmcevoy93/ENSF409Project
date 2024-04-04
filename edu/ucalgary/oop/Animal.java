package edu.ucalgary.oop;

/*
@author Max McEvoy 30005167<a href="mailto:max.mcevoy@ucalgary.ca"> max.mcevoy@ucalgary.ca</a>
@version 1.4
@since 1.0
*/
import java.util.regex.*;
import java.util.*;

/** Animal class
 *  Animal contains all the relevant info needed to complete all medical treatments, feedings and cleaning
 */
// todo: include a throw to the IllegalArgumentException
public class Animal {
    private final int ID;
    private final String NICKNAME;
    private boolean orphan;
    
    /** Constructor
     * @param animalID The ID of the animal
     * @param nickname The nickname of the animal
     */
    public Animal(int animalID, String nickname) {
        this.ID = animalID;
        this.NICKNAME = nickname;
        this.orphan = checkOrphaned(nickname);
    }
    
    /** Checks if the nickname has comma-separated values
     * @param nickname The nickname of the animal
     * @return true if the nickname has comma-separated values, false otherwise
     */
    private boolean checkOrphaned(String nickname) {
        Pattern has_comma = Pattern.compile("\\b\\w+(,\\s+\\w+)+\\b");
        Matcher match = has_comma.matcher(nickname);
        return match.find();
    }
    
    /** Gets the ID of the animal
     * @return The ID of the animal
     */
    public int getID() {return ID;}
    
    /** Gets the name of the animal
     * @return The name of the animal
     */
    public String getName() {return NICKNAME;}
    
    /** Checks if the animal is orphaned
     * @return true if the animal is orphaned, false otherwise
     */
    public boolean isOrphaned() {return orphan;}
    
    /** Gets the feed time of the animal
     * @return The feed time of the animal
     */
    public int getFeedTime() {
        return -1; // Placeholder value, to be overridden in subclasses
    }
    
    /** Gets the feed preparation time of the animal
     * @return The feed preparation time of the animal
     */
    public int getFeedPrep() {
        return -1; // Placeholder value, to be overridden in subclasses
    }
    
    /** Gets the cleaning time of the animal
     * @return The cleaning time of the animal
     */
    public int getCleanTime() {
        return -1; // Placeholder value, to be overridden in subclasses
    }
    
    /** Gets the feed start time of the animal
     * @return The feed start time of the animal
     */
    public int getFeedStart() {
        return -1; // Placeholder value, to be overridden in subclasses
    }
    
    /** Gets the feed window of the animal
     * @return The feed window of the animal
     */
    public int getFeedWindow() {
        return -1; // Placeholder value, to be overridden in subclasses
    }
    
    /** Gets the species of the animal
     * @return The species of the animal
     */
    public String getSpecies() {
        return "void"; // Placeholder value, to be overridden in subclasses
    }
    
    /** Prints information about the animal
     * @return Information about the animal
     */
    public String printInfo() {
        return String.format("| %-3s | %-24s | %-15s |\n", ID, NICKNAME, getSpecies());
    }

//    public String getNickname() {
//
//    }
}
