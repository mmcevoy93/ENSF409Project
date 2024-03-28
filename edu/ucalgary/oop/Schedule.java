package edu.ucalgary.oop;
/*
@author Max McEvoy 30005167<a href="mailto:max.mcevoy@ucalgary.ca">
max.mcevoy@ucalgary.ca</a>
@version 1.1
@since 1.0
*/
import java.util.*;
import java.time.LocalDate;


public class Schedule{

    private List<Animal> animals;
    private List<Treatment> treatments;
    private int dayHours[] = new int[24];
    private String printHours[] = new String[24];

    public Schedule(List<Animal> animals, List<Treatment> treatments){
        this.animals = animals;                 // Creates an array list with animal information
        this.treatments = treatments;           // Creates an array with treatments information

        Arrays.fill(this.dayHours, 60);     // Fills the 24 indices "daysHour" array with 60
                                                // Which is representative of 60 minutes
        printTreatments();                      // prints Treatments
    }

    public void printTreatments(){
        /**
         * Prints the treatments of the given animal for each hour
         */

        int time;                                   // Time variable to store the StartHour
        String name, description = "";              // 
        int lastTime = -1;                          // What is this negative for?
        //Collections.sort(this.treatments);//sorts by time
        for (Treatment t : this.treatments){
            // For loop that goes through the treatment array
            // For each treatment, accesses information form the given index
            time = t.getStartHour();                // Initialize time with the StartHour from the Treatment index
            if(lastTime!=time){System.out.println(String.format("\n%02d:00",time));}
            lastTime=time;
            name = t.getAnimalName();
            description = t.getDescription();
            this.dayHours[time] -= t.getDuration();
            System.out.print("* " + description + " (" + name +") - ");
            System.out.println("Time remaining in hour: " + this.dayHours[time]);
        }
    }

    public static void main(String[] args){
        String url = "jdbc:postgresql://localhost:5432/ewr";
        String username = "oop";
        String password = "ucalgary";
        SQLData myJDBC = new SQLData(url,username,password);
        
        Schedule schedule = new Schedule(myJDBC.getAnimalList(), myJDBC.getTreatmentList());        
    }

}