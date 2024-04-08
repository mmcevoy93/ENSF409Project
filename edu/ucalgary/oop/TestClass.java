package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;
import java.util.*;
import org.junit.Test;

public class TestClass {
//    @Test
//    public void getAnimalListIsNotNullTest() {
//        String url = "jdbc:postgresql://localhost:5432/ewr";            // Database url
//        String username = "oop";                                        // Username for the database ewr
//        String password = "ucalgary";                                   // Password for the database ewr
//        SQLData myJDBC = new SQLData(url, username, password);
//        myJDBC.initializeConnection();
//        myJDBC.close();
////        var schedule = new Schedule(myJDBC.getAnimalList(), myJDBC.getTreatmentTasks());
//        assertFalse("AnimalList is not populated", myJDBC.getAnimalList().isEmpty());
//
//    }
//
//    @Test
//    public void getTreatmentTasksIsNotNullTest(){
//        String url = "jdbc:postgresql://localhost:5432/ewr";            // Database url
//        String username = "oop";                                        // Username for the database ewr
//        String password = "ucalgary";                                   // Password for the database ewr
//        SQLData myJDBC = new SQLData(url, username, password);
////        var schedule = new Schedule(myJDBC.getAnimalList(), myJDBC.getTreatmentTasks());
//        assertFalse("Treatment Tasks list is not populated", myJDBC.getTreatmentTasks().isEmpty());
//    }

//    @Test
//    public void dailyTasksObjectWithoutPrepTimeSuccess(){
//        // makes a dailyTasks Object
//        // calls on value of preptime
//        // value should return 0
//
//    }

    // Seems like there is a methodology for writing unit tests with data bases (ie: writing code stubs etc.)
    // let's focus on writing tests for each of the different species (this should not be hard)
    // then go back to writing unit tests for Schedule and SQLData classes

//    @Test
//    public void countAnimalsMethodTest(){
//        AnimalCounter animalCount = new AnimalCounter();
//        ArrayList<Animal> animalList = new ArrayList<>();
//        animalList.add((5, 'Eraser', 'coyote'));
//        animalCount.countAnimals();

    /**
     * Test to see if a Beaver object is created when instantiated
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
    public void printInfoTest(){
        String name = "beavy";
        int animalID = 16;
        Beaver beaver = new Beaver(animalID, name);

        String actual = beaver.printInfo();
        String expected = String.format("| %-3s | %-24s | %-15s |\n", animalID, name, "beaver");;

        assertEquals("place holder text", actual, expected);

    }


}

//            (5, 'Eraser', 'coyote'),
//(6, 'Annie, Oliver and Mowgli', 'fox'),
//        (7, 'Slinky', 'fox'),
//
//
//    // check that preptime for daily tasks is 0 for no prep time
//    // check that preptime is a value for preptime input
//}
        // Check that the task array is not empty

        // getting a task should return something




//        public Schedule(List<Animal> animals, List<DailyTasks> tasks){
//        this.animals = animals;       // Creates an array list with animal information
//        this.tasks = tasks;           // Creates an array with treatments information
//
//        //Initializes hourlySchedule data structure
//        for (int i = 0; i < 24; i++) {
//            hourlySchedule.add(new ArrayList<>());
//        }
//         // does the sorting operations, generates cleaning tasks and prints schedule to terminal
//        printTreatments();
//    }




//    @Test // -- DONE
//    /*
//     * Both constructors and both addVisitorReservation() should throw an IllegalArgumentException for an invalid licence as defined in the Parking class.
//     */
//    public void testThrowsExceptionWithInvalidLicence() {
//        boolean passed = false;
//
//        // Test data - values may be changed in actual tests
//        String badLicence = "76543210";
//        var aDate = LocalDate.of(2222, 9, 26);
//
//        // Check new VisitorParking(licence)
//        try {
//            var vp = new VisitorParking(badLicence);
//        } catch (IllegalArgumentException e) {
//            passed = true;
//        } catch (Exception e) {
//        }
//        assertTrue("VisitorParking 1-argument constructor did not throw IllegalArgumentException when given invalid licence", passed);
//
//        // Check new VisitorParking(licence, date)
//        passed = false;
//        try {
//            var vp = new VisitorParking(badLicence, aDate);
//        } catch (IllegalArgumentException e) {
//            passed = true;
//        } catch (Exception e) {
//        }
//        assertTrue("VisitorParking 2-argument constructor did not throw IllegalArgumentException when given invalid licence", passed);
//
//        // Check addVisitorReservation(licence)
//        passed = false;
//        var vp = new VisitorParking();
//        try {
//            vp.addVisitorReservation(badLicence);
//        } catch (IllegalArgumentException e) {
//            passed = true;
//        } catch (Exception e) {
//        }
//        assertTrue("VisitorParking 1-argument addVisitorReservation did not throw IllegalArgumentException when given invalid licence", passed);
//
//        // Check addVisitorReservation(licence, date)
//        passed = false;
//        try {
//            vp.addVisitorReservation(badLicence, aDate);
//        } catch (IllegalArgumentException e) {
//            passed = true;
//        } catch (Exception e) {
//        }
//        assertTrue("VisitorParking 2-argument addVisitorReservation did not throw IllegalArgumentException when given invalid licence", passed);
//    }
//
//}
