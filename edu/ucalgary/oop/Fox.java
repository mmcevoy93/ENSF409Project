package edu.ucalgary.oop;
/*
@author Max McEvoy 30005167<a href="mailto:max.mcevoy@ucalgary.ca">
max.mcevoy@ucalgary.ca</a>
@version 1.3
@since 1.0
*/

/**Fox
 * 
 * Species used to construct animal.
 * Has methods that return values
 * specific to Fox
 */
public class Fox extends Nocturnal{
    private final int FEED_TIME = 5;
    private final int FEED_PREP = 5;
    private final int CLEAN_TIME = 5;

    /**
     * Fox needs an ID and a Name
     * @param animalID int: EWR ID
     * @param name StringL EWR Nickname
     */
    public Fox(int animalID, String name){
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
        return "fox";
    }
}