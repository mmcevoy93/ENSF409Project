package edu.ucalgary.oop;
import java.util.*;
public class Hour {
    //Declaring fields
    private int hour;
    private List<Treatment> treatment;
    private List<Feed> feed;

    private int volunteer = 1;

    //Constructor
    public Hour(int hour, List<Feed> feed){
        this.hour = hour;
        this.feed = new ArrayList<Feed>(feed);
    }

    public void callSecondVolunteer(){
        this.volunteer = 2;
    }
}

