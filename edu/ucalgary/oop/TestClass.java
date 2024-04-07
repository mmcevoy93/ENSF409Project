package edu.ucalgary.oop;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestClass {

    
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
        String url = "jdbc:postgresql://localhost:5432/ewr";            // Database url
        String username = "oop";                                        // Username for the database ewr
        String password = "ucalgary";                                   // Password for the database ewr
        SQLData myJDBC = new SQLData(url,username,password);
        //var schedule = new Schedule(myJDBC.getAnimalList(), myJDBC.getTreatmentTasks());
        
    }

}
