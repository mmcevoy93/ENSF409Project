package edu.ucalgary.oop;
/*
@author Max McEvoy 30005167<a href="mailto:max.mcevoy@ucalgary.ca">
max.mcevoy@ucalgary.ca</a>
@version 1.2
@since 1.0
*/

import java.util.*;

public class Animal{
    protected int animalID;
    protected String nickname;
    protected boolean feed = false;
    protected boolean clean = false;
    protected int feedStart;
    protected int feedWindow;
    protected int feedPrep;
    protected int feedTime;
    protected int cleanTime;


    public Animal(int id, String nickname, int feedPrep, int feedTime, int cleanTime, int feedStart, int feedWindow){
        this.animalID = id;
        this.nickname = nickname;
        this.feedTime = feedTime;
        this.feedWindow = feedWindow;
        this.feedPrep = feedPrep;
        this.cleanTime = cleanTime;
    }
    public int getAnimalID() {return this.animalID;}
    public String getName() {return this.nickname;}
    public int getfeedTime() {return this.feedTime;}
    public boolean getClean(){return this.clean;}
    public boolean getFeed(){return this.feed;}


    public void setClean(boolean clean){ this.clean = clean;}
    public void setFeed(boolean feed){this.feed = feed;}
    public String getSpecies(){return "void";}
    public int FeedStart(){return -1;}
    public int FeedWindow(){return -1;}
}


 





class Test{
    private static List<Animal> animals = new ArrayList<>();
    public static void main(String args[]) {
        Fox fox = new Fox(12, "eppe");
        Raccoon rac = new Raccoon(12, "eppe");
        System.out.println(fox.getfeedTime());
        animals.add(fox);
        animals.add(rac);

        System.out.println(animals.get(0).getSpecies());

    }
}