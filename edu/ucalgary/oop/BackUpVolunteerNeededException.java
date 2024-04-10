package edu.ucalgary.oop;

public class BackUpVolunteerNeededException extends Exception {

    private DailyTasks task;

    BackUpVolunteerNeededException(String error, DailyTasks task){
        super(error);
        this.task = task;
    }
    BackUpVolunteerNeededException(String error){
        super(error);
    }
   


    public DailyTasks getTask() {
        return task;
    }
}
