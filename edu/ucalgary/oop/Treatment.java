package edu.ucalgary.oop;
public class Treatment throws IllegalArgumentException{
    private int ANIMAL_ID;
    private int TASK_ID;
    private int START_HOUR;

    public Treatment(int animalID, int taskID, int startHour){
        this.ANIMAL_ID = animalID;
        this.TASK_ID = taskID;
        this.START_HOUR = startHour;
    }

    public int getAnimalID(){
        return this.ANIMAL_ID;
    }

    public int getTaskID(){
        return this.TASK_ID;
    }

    public int getStartHour(){
        return this.START_HOUR;
    }
}
