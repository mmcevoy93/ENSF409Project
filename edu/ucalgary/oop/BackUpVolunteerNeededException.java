package edu.ucalgary.oop;

public class BackUpVolunteerNeededException extends Exception {

    private int hour;

    BackUpVolunteerNeededException(String error, int hour){
        super(error);
        this.hour = hour;
    }


    public int getHour() {
        return hour;
    }
}
