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
    int dayHours[] = new int[24];
    String printHours[] = new String[24];

    public Schedule(List<Animal> animals, List<Treatment> treatments){
        this.animals = animals;
        this.treatments = treatments;

        Collections.sort(this.treatments);
        Arrays.fill(this.dayHours, 60);
        foo();
    }

    public void foo(){
        int time;
        String name, description = "";
        int lastTime = -1;
        for (Treatment t : this.treatments){
            time = t.getStart();
            if(lastTime!=time){System.out.println(String.format("\n%02d:00",time));}
            lastTime=time;
            name = t.getAnimalName();
            description = t.getTaskDescription();
            this.dayHours[time] -= t.getDur();
            System.out.print("* " + description + " (" + name +") - ");
            System.out.println("Time remaining in hour: " + this.dayHours[time]);
        }
    }

    

    public static void main(String[] args){
        String url = "jdbc:postgresql://localhost:5432/ewr";
        String username = "oop";
        String password = "ucalgary";
        SQLData myJDBC = new SQLData(url,username,password);
        
        Schedule schedule = new Schedule(myJDBC.selectAnimalData(), myJDBC.selectTreatmentsData());
        List<Animal> animals = myJDBC.selectAnimalData();
        
    }

}