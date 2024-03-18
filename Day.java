import java.util.*;
public class Day {
    //Declaring fields
    private List<Animal> animals;
    private List<Hour> hour;
    private List<Task> task;

    //Constructor
    public Day(List<Animal> sqlAnimals, List<Task> tasks, List<Hour> hour, String[] treatments){
        this.animals = new ArrayList<Animal>(sqlAnimals);
        this.hour = new ArrayList<Hour>(hour);
        this.task = new ArrayList<Task>(tasks);
    }

    public String PrintHour(String hourToPrint){
        //Format and return the hour to be printed
        return hourToPrint;
    }
}
