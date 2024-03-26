package edu.ucalgary.oop;
import java.sql.*;
import java.util.*;
public class SQLData {

    private Connection dbConnect;

    public final String DBURL;
    public final String USERNAME;
    public final String PASSWORD;

    private List<Task> newTask = new ArrayList<>();
    private List<Animal> animals = new ArrayList<>();
    private List<Treatment> treatments = new ArrayList<>();

    public SQLData(String url, String user, String pw){

        // Database URL
        this.DBURL = url;

        //  Database credentials
        this.USERNAME = user;
        this.PASSWORD = pw;
    }

    //Step 1: Create a connection to database
    public void initializeConnection() {
        try {
            dbConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }
    }

    //Step 2 & 3: Make statements and execute
    public void selectTask(){
        ResultSet taskResults;
        try{
            Statement myStmt = dbConnect.createStatement();
            taskResults = myStmt.executeQuery("SELECT * FROM TASKS");

            while (taskResults.next()){
                int taskID = taskResults.getInt("TaskID");
                String description = taskResults.getString("Description");
                int duration = taskResults.getInt("Duration");
                int maxWindow = taskResults.getInt("MaxWindow");
                this.newTask.add(new Task(taskID, description, duration, maxWindow));
            }
            taskResults.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        
    }

    public void selectAnimalData(){
        ResultSet animalResults;
        try{
            Statement myStmt = dbConnect.createStatement();
            animalResults = myStmt.executeQuery("SELECT * FROM ANIMALS");

            while (animalResults.next()){
                int animalID = animalResults.getInt("AnimalID");
                String animalNickname = animalResults.getString("AnimalNickname");
                String animalSpecies = animalResults.getString("AnimalSpecies");
                switch(animalSpecies){
                    case("coyote"):
                        animals.add(new Coyote(animalID, animalNickname));
                        break;
                    case("beaver"):
                        animals.add(new Beaver(animalID, animalNickname));
                        break;
                    case("fox"):
                        animals.add(new Fox(animalID, animalNickname));
                        break;
                    case("raccoon"):
                        animals.add(new Raccoon(animalID, animalNickname));
                        break;
                    case("porcupine"):
                        animals.add(new Porcupine(animalID, animalNickname));
                        break;
                    default:
                        System.out.println("Invalid Species");
                }
            }
            animalResults.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectTreatmentsData(){

        ResultSet treatmentResults;
        try{
            Statement myStmt = dbConnect.createStatement();
            treatmentResults = myStmt.executeQuery("SELECT * FROM TREATMENTS");
            while (treatmentResults.next()){
                int animalID = treatmentResults.getInt("AnimalID");
                int taskID = treatmentResults.getInt("TaskID");
                int startHour = treatmentResults.getInt("StartHour");
                this.treatments.add(new Treatment(animalID, taskID, startHour));
            }
            treatmentResults.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    //Step 5: close the connection
    public void close(){
        try{
            dbConnect.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void testAnimalList(){
        String foo = "%s is a %s. It needs to be feed at %d:00";
        for(Animal a : animals){
            System.out.println(String.format(foo, a.getName(), a.getSpecies(), a.getfeedTime()));
        }
    }

    public static void main(String[] args){
        SQLData myJDBC = new SQLData("jdbc:postgresql://localhost:5432/ewr", "oop", "ucalgary");

        myJDBC.initializeConnection();


        myJDBC.selectTask();
        myJDBC.selectAnimalData();
        myJDBC.selectTreatmentsData();
        myJDBC.close();

        myJDBC.testAnimalList();


    }

}