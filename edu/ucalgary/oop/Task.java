package edu.ucalgary.oop;

// todo: create a through to the IllegalArgumentException
public class Task {
    private int TASK_ID;
    private String DESCRIPTION;
    private int DURATION;
    private int MAX_WINDOW;

   public Task(int taskID, String description, int duration, int maxWindow){
        this.TASK_ID = taskID;
        this.DESCRIPTION = description;
        this.DURATION = duration;
        this.MAX_WINDOW = maxWindow;
    };
    public int getTaskID(){
        return this.TASK_ID;
    }

    public String getDescription(){
        return this.DESCRIPTION;
    }

    public int getDuration(){
        return this.DURATION;
    }

    public int getMaxWindow(){
        return this.MAX_WINDOW;
    }
}
