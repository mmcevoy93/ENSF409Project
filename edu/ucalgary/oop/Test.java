package edu.ucalgary.oop;
/*
@author Max McEvoy 30005167<a href="mailto:max.mcevoy@ucalgary.ca">
max.mcevoy@ucalgary.ca</a>
@version 1.2
@since 1.0
*/

//Hello!

class Animal{
    protected int animalID;
    protected String nickname;
    protected boolean feed = false;
    protected boolean clean = false;
    protected int feedTime;
    public Animal(int id, String nickname, int feedtime){
        this.animalID = id;
        this.nickname = nickname;
        this.feedTime = feedtime;
    }

}


interface Nocturnal{
    default int FeedStart(){return 0;}
    default int FeedWindow(){return 3;}
} 

class Fox extends Animal implements Nocturnal{

    public Fox(int id, String name, int feedtime){
        super(id, name, feedtime);

    }

    public int getfeedTime() {
        return super.feedTime;
    }

    public int getName() {
        return super.animalID;
    }

}

public class Test{

    public static void main(String args[]) {
        Fox fox = new Fox(12, "eppe", 50);
        System.out.println(fox.getfeedTime());
    }


}