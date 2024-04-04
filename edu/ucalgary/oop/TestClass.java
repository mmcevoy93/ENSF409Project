package edu.ucalgary.oop;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;
public class TestClass {

    @Test
    void printTreatmentTest(){
        String url = "jdbc:postgresql://localhost:5432/ewr";            // Database url
        String username = "oop";                                        // Username for the database ewr
        String password = "ucalgary";                                   // Password for the database ewr
        SQLData myJDBC = new SQLData(url,username,password);
        var schedule = new Schedule(myJDBC.getAnimalList(), myJDBC.getTreatmentTasks());


        // checks that
    }



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




    @Test // -- DONE
    /*
     * Both constructors and both addVisitorReservation() should throw an IllegalArgumentException for an invalid licence as defined in the Parking class.
     */
    public void testThrowsExceptionWithInvalidLicence() {
        boolean passed = false;

        // Test data - values may be changed in actual tests
        String badLicence = "76543210";
        var aDate = LocalDate.of(2222, 9, 26);

        // Check new VisitorParking(licence)
        try {
            var vp = new VisitorParking(badLicence);
        } catch (IllegalArgumentException e) {
            passed = true;
        } catch (Exception e) {
        }
        assertTrue("VisitorParking 1-argument constructor did not throw IllegalArgumentException when given invalid licence", passed);

        // Check new VisitorParking(licence, date)
        passed = false;
        try {
            var vp = new VisitorParking(badLicence, aDate);
        } catch (IllegalArgumentException e) {
            passed = true;
        } catch (Exception e) {
        }
        assertTrue("VisitorParking 2-argument constructor did not throw IllegalArgumentException when given invalid licence", passed);

        // Check addVisitorReservation(licence)
        passed = false;
        var vp = new VisitorParking();
        try {
            vp.addVisitorReservation(badLicence);
        } catch (IllegalArgumentException e) {
            passed = true;
        } catch (Exception e) {
        }
        assertTrue("VisitorParking 1-argument addVisitorReservation did not throw IllegalArgumentException when given invalid licence", passed);

        // Check addVisitorReservation(licence, date)
        passed = false;
        try {
            vp.addVisitorReservation(badLicence, aDate);
        } catch (IllegalArgumentException e) {
            passed = true;
        } catch (Exception e) {
        }
        assertTrue("VisitorParking 2-argument addVisitorReservation did not throw IllegalArgumentException when given invalid licence", passed);
    }

}
