package edu.ucalgary.oop;
/*
@author Max McEvoy 30005167<a href="mailto:max.mcevoy@ucalgary.ca">
max.mcevoy@ucalgary.ca</a>
@version 1.1
@since 1.0
*/
import java.util.*;
// todo: create a throw to an IllegalArgumentException
public class Treatment  implements Comparable<Treatment>{
    private int ANIMAL_ID;
    private int TASK_ID;
    private int START_HOUR;
    private List<Task> tasks;
    private List<Animal> animals;


    public Treatment(int animalID, int taskID, int startHour, List<Task> tasks, List<Animal> animals){
        this.ANIMAL_ID = animalID;
        this.TASK_ID = taskID;
        this.START_HOUR = startHour;
        this.tasks = tasks;
        this.animals = animals;
    }

    public String getAnimalName(){
        return this.animals.get(ANIMAL_ID-1).getName();
    }

    public String getTaskDescription(){
        return this.tasks.get(TASK_ID-1).getDescription();
    }

    public int getStart() {
        return this.START_HOUR;
    }

    public int getDur() {
        return this.tasks.get(TASK_ID-1).getDuration();
    }

    public int getWindow() {
        return this.tasks.get(TASK_ID-1).getMaxWindow();
    }
    @Override
    public int compareTo(Treatment other) {
        return Integer.compare(this.START_HOUR, other.START_HOUR);
    }
}