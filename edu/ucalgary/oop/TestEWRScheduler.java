package edu.ucalgary.oop;
/*
@author Max McEvoy 30005167<a href="mailto:max.mcevoy@ucalgary.ca">
max.mcevoy@ucalgary.ca</a>
@version 1.5
@since 1.0
*/
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;

/**
 * TestEWRScheduler
 * 
 * Tests all Classes that the relates to the EWR management system
 */
public class TestEWRScheduler {
    private String url = "jdbc:postgresql://localhost:5432/ewr";
    private String username = "oop";
    private String password = "ucalgary";
    
    @Test
    /*
     * tests whether checkOrphaned returns true when it is supposed to
     */
    public void CheckOrphanedIsTrue(){
        Animal testAnimal = new Animal(1, "Annie, Oliver and Mowgli");
        assertTrue(testAnimal.isOrphaned());
    }

    @Test
    /*
     * tests whether checkOrphaned returns false when it is supposed to
     */
    public void CheckOrphanedIsFalse(){
        Animal testAnimal = new Animal(1, "Pencil");
        assertFalse(testAnimal.isOrphaned());
    }

    
    @Test
    /**
     * tests whether correct species is returned
     */
    public void getSpeciesTest(){
        Coyote testAnimal = new Coyote(1, "Pencil");
        assertEquals("coyote", testAnimal.getSpecies());
    }

    @Test
    /**
     * Test to see if a structure for the hourly schedule is made
     * Should be 24 hours
     */
    public void printTreatmentHourlyScheduleTest(){
        SQLData myJDBC = new SQLData(url,username,password);            // Instantiates an SQLData object with the url, username and password
        Schedule schedule = new Schedule(myJDBC.getAnimalList(), myJDBC.getTreatmentTasks());
        schedule.hourlySchedule.size();
        assertTrue(" ", beaver instanceof Diurnal);

    }

//    @Test
//    public void insertDataUnitTest(){
//        // arrange our objects and test fixtures
//        db = initializeDatabase(); // initialize db object to test
//        Road road = new Road("Germany"); // create a valid Road object to insert into db
//        // act on an object to perform a test
//        Boolean result = db.insertRoadData(road);
//        // assert the result of the action is as expected
//        Assert.assertTrue(result);
//    }
    
    @Test
    /**
     * Will construct a schedule that should not through any errors
     */
    public void testValidSchedule(){
        boolean passed = true;

        List<Animal> animals = new ArrayList<>();
        List<DailyTasks> tasks = new ArrayList<>();
        animals.add(new Fox(1, "Robin Hood"));
        animals.add(new Fox(2, "Maid Marian"));
        animals.add(new Beaver(3, "Fryer Tuck"));
        animals.add(new Porcupine(4, "Prince John"));
        animals.add(new Coyote(5, "Sheriff of Nottingham"));
        animals.add(new Raccoon(6, "Kilgore Trout"));
        tasks.add(new DailyTasks("Robin Hood", "Remove arrow from knee", 12, 30, 1));
        tasks.add(new DailyTasks("Maid Marian", "Teeth Cleaning", 12, 5, 1));
        tasks.add(new DailyTasks("Prince John", "Thumb Removal", 12, 25, 1));
        try{
            Schedule testSchedule = new Schedule(animals, tasks);
            System.out.println(testSchedule);
        } catch (Exception e){
            passed = false;
        }
        assertTrue("Valid Schedule throw IllegalArgumentException when attempting to make valid schedule", passed);
    }


    /** The following are tests ran for a specific species to ensure inheritance
     * is followed properly
     * */

    /**
     * Test to see if a Beaver object is created with instantiated
     * */
    @Test
    public void beaverObjectTest(){
        String name = "beavy";
        int animalID = 16;

        Beaver beaver = new Beaver(animalID, name);
        assertTrue(" ", beaver instanceof Beaver);

    }

    // Check if Beaver Object extends Diurnal
    /**
     * Test to see if Beaver object extends Diurnal
     **/
    @Test
    public void beaverObjectDiurnalTest(){
        String name = "beavy";
        int animalID = 16;

        Beaver beaver = new Beaver(animalID, name);
        assertTrue(" ", beaver instanceof Diurnal);

    }

    /**
     * Test to see if Beaver object extends Animal
     **/
    @Test
    public void beaverObjectAnimalTest(){
        String name = "beavy";
        int animalID = 16;

        Beaver beaver = new Beaver(animalID, name);
        assertTrue(" ", beaver instanceof Animal);

    }

    /**
     * Test to see if getFeed() method returns the correct value
     **/
    @Test
    public void beaverGetFeedTest() {
        String name = "beavy";
        int animalID = 16;

        Beaver beaver = new Beaver(animalID, name);
        int actual = beaver.getFeedTime();
        int expected = 5;

        // make new beaver object
        // get beaver feed time
        // assert result
        assertEquals("place holder text", actual, expected);
    }

    /**
     * Test to see if getPrep() method returns the correct value
     **/
    @Test
    public void beaverGetFeedPrepTest() {
        String name = "beavy";
        int animalID = 16;

        Beaver beaver = new Beaver(animalID, name);
        int actual = beaver.getFeedPrep();
        int expected = 0;

        // make new beaver object
        // get beaver feed time
        // assert result
        assertEquals("place holder text", actual, expected);
    }

    /**
     * Test to see if getCleanTime() method returns the correct value
     **/
    @Test
    public void beaverGetCleanTimeTest() {
        String name = "beavy";
        int animalID = 16;

        Beaver beaver = new Beaver(animalID, name);
        int actual = beaver.getCleanTime();
        int expected = 5;

        // make new beaver object
        // get beaver feed time
        // assert result
        assertEquals("place holder text", actual, expected);
    }

    /**
     * Test to see if getSpecies() method returns the correct value
     **/
    @Test
    public void beaverGetSpeciesTest() {
        String name = "beavy";
        int animalID = 16;

        Beaver beaver = new Beaver(animalID, name);
        String actual = beaver.getSpecies();
        String expected = "beaver";

        // make new beaver object
        // get beaver feed time
        // assert result
        assertEquals("place holder text", actual, expected);
    }

    /**
     * Test extension of Diurnal Class for getFeedWindow method
     * */
    @Test
    public void getFeedWindowTest(){
        String name = "beavy";
        int animalID = 16;
        Beaver beaver = new Beaver(animalID, name);

        int actual = beaver.getFeedWindow();
        int expected = 3;

        assertEquals("place holder text", actual, expected);
    }

    /**
     * Test inheritance of Diurnal Class for getFeedStart method
     * */
    @Test
    public void getFeedStartTest(){
        String name = "beavy";
        int animalID = 16;
        Beaver beaver = new Beaver(animalID, name);

        int actual = beaver.getFeedStart();
        int expected = 8;

        assertEquals("place holder text", actual, expected);

    }

    /**
     * Test inheritance of Animal Class for Beaver
     * */
    @Test
    public void printToStringTest(){
        String name = "beavy";
        int animalID = 16;
        Beaver beaver = new Beaver(animalID, name);

        String actual = beaver.toString();
        String expected = String.format("| %-3s | %-24s | %-15s |\n", animalID, name, "beaver");;

        assertEquals("place holder text", actual, expected);

    }


}
