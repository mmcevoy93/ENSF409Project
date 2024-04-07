package edu.ucalgary.oop;
/*
@author Max McEvoy 30005167<a href="mailto:max.mcevoy@ucalgary.ca">
max.mcevoy@ucalgary.ca</a>
@version 1.3
@since 1.0
*/

/**Raccoon
 * 
 * Species used to construct animal.
 * Has methods that return values
 * specific to Raccoon
 */
public class Raccoon extends  Nocturnal{
    private final int FEED_TIME = 5;
    private final int FEED_PREP = 0;
    private final int CLEAN_TIME = 5;

    /**
     * Raccoon needs an ID and a Name
     * @param animalID int: EWR ID
     * @param name StringL EWR Nickname
     */
    public Raccoon(int animalID, String name){
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
        return "raccoon";
    }
}