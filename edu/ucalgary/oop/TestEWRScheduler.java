package edu.ucalgary.oop;
/*
@author Max McEvoy 30005167<a href="mailto:max.mcevoy@ucalgary.ca">
max.mcevoy@ucalgary.ca</a>
@version 1.5
@since 1.0
*/
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.util.List;
import java.util.ArrayList;

/**
 * TestEWRScheduler
 * 
 * Tests all Classes that the relates to the EWR management system
 */
public class TestEWRScheduler {
    
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
    public void printTreatmentHourlyScheduleTest() throws BackUpVolunteerNeededException {

        List<Animal> animals = new ArrayList<>();
        List<DailyTasks> tasks = new ArrayList<>();
        animals.add(new Fox(1, "Robin Hood"));
        animals.add(new Fox(2, "Maid Marian"));
        animals.add(new Beaver(3, "Fryer Tuck"));
        animals.add(new Porcupine(4, "Prince John"));

        tasks.add(new DailyTasks("Robin Hood", "Remove arrow from knee", 12, 30, 1));
        tasks.add(new DailyTasks("Maid Marian", "Teeth Cleaning", 12, 5, 1));

        Schedule schedule = new Schedule(animals, tasks);
        schedule.getHourlySchedule().size();

        int actual = schedule.getHourlySchedule().size();;
        int expected = 24;

        assertEquals("Hourly Schedule does not consist of 24 hours", actual, expected);

    }

    @Test
    public void testVolunteerBackupException() {
        boolean correctException = false;
        try{
            List<Animal> animals = new ArrayList<>();
            List<DailyTasks> tasks = new ArrayList<>();
            animals.add(new Fox(1, "Robin Hood"));
            animals.add(new Fox(2, "Maid Marian"));
            animals.add(new Beaver(3, "Fryer Tuck"));
            animals.add(new Porcupine(4, "Prince John"));
            animals.add(new Coyote(5, "Sheriff of Nottingham"));
            animals.add(new Raccoon(6, "Kilgore Trout"));
            tasks.add(new DailyTasks("Maid Marian", "Teeth Cleaning", 12, 5, 1));
            tasks.add(new DailyTasks("Robin Hood", "Remove arrow from knee", 12, 45, 1));
            tasks.add(new DailyTasks("Prince John", "Thumb Removal", 12, 25, 1));

            Schedule schedule = new Schedule(animals, tasks);
            System.out.println(schedule);
        }
        catch(Exception e){
            correctException = true;
        }
        assertEquals("BackUpVolunteerNeededException not thrown", true, correctException);
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

    /**
     * Daily Tasks Tests
     **/

    @Test
    public void dailyTasksWithoutPrepTimeTest(){
        List<Animal> animals = new ArrayList<>();
        List<DailyTasks> tasks = new ArrayList<>();

        animals.add(new Fox(2, "Maid Marian"));
        animals.add(new Fox(1, "Robin Hood"));
        tasks.add(new DailyTasks("Maid Marian", "Teeth Cleaning", 12, 5, 1, 10));
        tasks.add(new DailyTasks("Robin Hood", "Remove arrow from knee", 12, 30, 1));

        int actual = 0;
        assertEquals("getPrepTime() does not return the correct value", actual, tasks.get(1).getPrepTime());

    }

    @Test
    public void dailyTasksWithPrepTimeTest(){
        List<Animal> animals = new ArrayList<>();
        List<DailyTasks> tasks = new ArrayList<>();

        animals.add(new Fox(2, "Maid Marian"));
        animals.add(new Fox(1, "Robin Hood"));
        tasks.add(new DailyTasks("Maid Marian", "Teeth Cleaning", 12, 5, 1, 10));
        tasks.add(new DailyTasks("Robin Hood", "Remove arrow from knee", 12, 30, 1));

        int actual = 10;
        assertEquals("getPrepTime() does not return the correct value", actual, tasks.get(0).getPrepTime());

    }

    /** The following are tests ran for a specific species to ensure inheritance
     * is followed properly: Beaver, Coyote, and Raccoon are tested.
     * This allows for Diurnal, Crepuscular, and Nocturnal to be tested as well
     * */

    /**
     * Test to see if a Beaver object is created with instantiated
     * */
    @Test
    public void beaverObjectTest(){
        String name = "beavy";
        int animalID = 16;

        Beaver beaver = new Beaver(animalID, name);
        assertTrue("'beaver' is not an object of the Beaver class ", beaver instanceof Beaver);

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
        assertTrue("beaver does not inherit from the Diurnal Class properly", beaver instanceof Diurnal);

    }

    /**
     * Test to see if Beaver object extends Animal
     **/
    @Test
    public void beaverObjectAnimalTest(){
        String name = "beavy";
        int animalID = 16;

        Beaver beaver = new Beaver(animalID, name);
        assertTrue("beaver does not extend from the Animal Class properly ", beaver instanceof Animal);

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
        assertEquals("getFeed() does not return the correct value", actual, expected);
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
        assertEquals("getPrep() does not return the correct value", actual, expected);
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
        assertEquals("getCleanTime() does not return the correct value", actual, expected);
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
        assertEquals("getSpecies() does not return the correct value", actual, expected);
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

        assertEquals("Beaver does not inherit from the Diurnal Class properly to access getFeedWindow()", actual, expected);
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

        assertEquals("Beaver does not inherit form the Diurnal Class properly to access getFeedStart ", actual, expected);

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

        assertEquals("Beaver does not inherit from the Animal Class properly to access toString() method", actual, expected);

    }

    /**
     * Test to see if a Coyote object is created when instantiated
     * */
    @Test
    public void coyoteObjectTest(){
        String name = "yote";
        int animalID = 7;

        Coyote coyote = new Coyote(animalID, name);
        assertTrue("'coyote' is not an object of the Coyote class ", coyote instanceof Coyote);

    }

    /**
     * Test to see if Coyote object extends Crepuscular
     **/
    @Test
    public void coyoteObjectCrepuscularTest(){
        String name = "yote";
        int animalID = 7;

        Coyote coyote = new Coyote(animalID, name);
        assertTrue("coyote does not inherit from the Crespuscular class properly", coyote instanceof Crepuscular);
    }

    /**
     * Test to see if Coyote object extends Animal
     **/
    @Test
    public void coyoteObjectAnimalTest(){
        String name = "yote";
        int animalID = 7;

        Coyote coyote = new Coyote(animalID, name);
        assertTrue("coyote does not extend from the Animal Class properly ", coyote instanceof Animal);

    }

    /**
     * Test to see if getFeed() method returns the correct value
     **/
    @Test
    public void coyoteGetFeedTest() {
        String name = "yote";
        int animalID = 7;

        Coyote testCoyote = new Coyote(animalID, name);
        int actual = testCoyote.getFeedTime();
        int expected = 5;

        // make new Coyote object
        // get Coyote feed time
        // assert result
        assertEquals(actual, expected, "getFeed() does not return the correct value");
    }

    /**
     * Test to see if getPrep() method returns the correct value
     **/
    @Test
    public void coyoteGetFeedPrepTest() {
        String name = "yote";
        int animalID = 7;

        Coyote testCoyote = new Coyote(animalID, name);
        int actual = testCoyote.getFeedPrep();
        int expected = 10;

        // make new coyote object
        // get coyote feed time
        // assert result
        assertEquals(actual, expected, "getPrep() does not return the correct value");
    }

    /**
     * Test to see if getCleanTime() method returns the correct value
     **/
    @Test
    public void coyoteGetCleanTimeTest() {
        String name = "yote";
        int animalID = 7;

        Coyote testCoyote = new Coyote(animalID, name);
        int actual = testCoyote.getCleanTime();
        int expected = 5;

        // make new Coyote object
        // get Coyote feed time
        // assert result
        assertEquals(actual, expected, "getCleanTime() does not return the correct value");
    }

    /**
     * Test extension of Crepuscular Class for getFeedWindow method
     * */
    @Test
    public void getFeedWindowTestForCrepuscular(){
        String name = "yote";
        int animalID = 7;
        Coyote testCoyote = new Coyote(animalID, name);

        int actual =  testCoyote.getFeedWindow();
        int expected = 3;

        assertEquals(actual, expected, "Coyote does not inherit from the Crepuscular Class properly to access getFeedWindow()");
    }

    /**
     * Test inheritance of Crepuscular Class for getFeedStart method
     * */
    @Test
    public void getFeedStartTestForCrepuscular(){
        String name = "yote";
        int animalID = 7;
        Coyote testCoyote = new Coyote(animalID, name);

        int actual = testCoyote.getFeedStart();
        int expected = 19;

        assertEquals(actual, expected, "Coyote does not inherit form the Crepuscular Class properly to access getFeedStart ");

    }

    /**
     * Test inheritance of Animal Class for Coyote
     * */
    @Test
    public void printToStringTestForCoyote(){
        String name = "yote";
        int animalID = 16;
        Coyote testCoyote = new Coyote(animalID, name);

        String actual = testCoyote.toString();
        String expected = String.format("| %-3s | %-24s | %-15s |\n", animalID, name, "coyote");;

        assertEquals(actual, expected, "Coyote does not inherit from the Animal Class properly to access toString() method");

    }

    /**
     * Test to see if a Raccoon object is created when instantiated
     * */
    @Test
    public void RaccoonObjectTest(){
        String name = "roony";
        int animalID = 7;

        Raccoon raccoon = new Raccoon(animalID, name);
        assertTrue("'raccoon' is not an object of the Raccoon class ", raccoon instanceof Raccoon);

    }

    /**
     * Test to see if Raccoon object extends Nocturnal
     **/
    @Test
    public void raccoonObjectCrepuscularTest(){
        String name = "roony";
        int animalID = 7;

        Raccoon raccoon = new Raccoon(animalID, name);
        assertTrue("raccoon does not inherit from the Nocturnal class properly", raccoon instanceof Nocturnal);
    }

    /**
     * Test to see if Raccoon object extends Animal
     **/
    @Test
    public void raccoonObjectAnimalTest(){
        String name = "roony";
        int animalID = 7;

        Raccoon raccoon = new Raccoon(animalID, name);
        assertTrue("raccoon does not extend from the Animal Class properly ", raccoon instanceof Animal);

    }

    /**
     * Test to see if getFeed() method returns the correct value
     **/
    @Test
    public void raccoonGetFeedTest() {
        String name = "roony";
        int animalID = 7;

        Raccoon testRaccoon = new Raccoon(animalID, name);
        int actual = testRaccoon.getFeedTime();
        int expected = 5;

        // make new Raccoon object
        // get Raccoon feed time
        // assert result
        assertEquals(actual, expected, "getFeed() does not return the correct value");
    }

    /**
     * Test to see if getPrep() method returns the correct value
     **/
    @Test
    public void raccoonGetFeedPrepTest() {
        String name = "roony";
        int animalID = 7;

        Raccoon testRaccoon = new Raccoon(animalID, name);
        int actual = testRaccoon.getFeedPrep();
        int expected = 0;

        // make new raccoon object
        // get raccoon feed time
        // assert result
        assertEquals(actual, expected, "getPrep() does not return the correct value");
    }

    /**
     * Test to see if getCleanTime() method returns the correct value
     **/
    @Test
    public void raccoonGetCleanTimeTest() {
        String name = "roony";
        int animalID = 7;

        Raccoon testRaccoon = new Raccoon(animalID, name);
        int actual = testRaccoon.getCleanTime();
        int expected = 5;

        // make new Raccoon object
        // get Raccoon feed time
        // assert result
        assertEquals(actual, expected, "getCleanTime() does not return the correct value");
    }

    /**
     * Test extension of Nocturnal Class for getFeedWindow method
     * */
    @Test
    public void getFeedWindowTestForNocturnal(){
        String name = "roony";
        int animalID = 7;
        Raccoon testRaccoon = new Raccoon(animalID, name);

        int actual =  testRaccoon.getFeedWindow();
        int expected = 3;

        assertEquals(actual, expected, "Raccoon does not inherit from the Nocturnal Class properly to access getFeedWindow()");
    }

    /**
     * Test inheritance of Nocturnal Class for getFeedStart method
     * */
    @Test
    public void getFeedStartTestForNocturnal(){
        String name = "roony";
        int animalID = 7;
        Raccoon testRaccoon = new Raccoon(animalID, name);

        int actual = testRaccoon.getFeedStart();
        int expected = 0;

        assertEquals(actual, expected, "Raccoon does not inherit form the Nocturnal Class properly to access getFeedStart ");

    }

    /**
     * Test inheritance of Animal Class for Raccoon
     * */
    @Test
    public void printToStringTestForRaccoon(){
        String name = "roony";
        int animalID = 16;
        Raccoon testRaccoon = new Raccoon(animalID, name);

        String actual = testRaccoon.toString();
        String expected = String.format("| %-3s | %-24s | %-15s |\n", animalID, name, "raccoon");;

        assertEquals(actual, expected, "Raccoon does not inherit from the Animal Class properly to access toString() method");

    }
}
