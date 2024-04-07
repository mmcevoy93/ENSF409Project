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
     * 
     */
    public void printTreatmentTest(){
        SQLData myJDBC = new SQLData(url,username,password);
        var schedule = new Schedule(myJDBC.getAnimalList(), myJDBC.getTreatmentTasks());
        System.out.println(schedule);
    }
    
    
    
    
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

}
