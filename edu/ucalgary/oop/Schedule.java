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
    private List<Task> tasks;
    int dayHours[] = new int[24];

    public Schedule(List<Animal> animals, List<Treatment> treatments, List<Task> tasks){
        this.animals = animals;
        this.treatments = treatments;
        this.tasks = tasks;
        Collections.sort(this.treatments);
        Arrays.fill(this.dayHours, 60);
        foo();
    }

    public void foo(){
        int time, AnID, tID;
        String name, description = "";
        int lastTime = -1;
        LocalDate today  = LocalDate.now();
        System.out.println("Schedule for " + today.toString());
        for (Treatment t : this.treatments){
            time = t.getStartHour();
            if(lastTime!=time){System.out.println(String.format("\n%02d:00",time));}
            lastTime=time;

            AnID = t.getAnimalID()-1;
            tID = t.getTaskID()-1;
            name = animals.get(AnID).getName();
            description = tasks.get(tID).getDescription();

            this.dayHours[time] -= tasks.get(tID).getDuration();
            System.out.println("* " + description + " (" + name +")");
            System.out.println(this.dayHours[time]);            
        }
    }

    

    public static void main(String[] args){
        String url = "jdbc:postgresql://localhost:5432/ewr";
        String username = "oop";
        String password = "ucalgary";
        SQLData myJDBC = new SQLData(url,username,password);
        myJDBC.initializeConnection();

        Schedule schedule = new Schedule(myJDBC.selectAnimalData(), myJDBC.selectTreatmentsData(), myJDBC.selectTask());
        List<Animal> animals = myJDBC.selectAnimalData();
        
    }

}