package edu.ucalgary.oop;
import java.sql.*;
import java.util.*;
public class SQLData {

    private Connection dbConnect;
    private ResultSet taskResults;
    private ResultSet animalResults;
    private ResultSet treatmentResults;

    public final String DBURL;
    public final String USERNAME;
    public final String PASSWORD;

    private ArrayList<Task> newTask;
    private ArrayList<Animal> animals;
    private ArrayList<Treatment> treatments;

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
    public ArrayList<Task> selectTask(){

        try{
            Statement myStmt = dbConnect.createStatement();
            taskResults = myStmt.executeQuery("SELECT * FROM TASKS");

            while (taskResults.next()){
                int taskID = taskResults.getInt("TaskID");
                String description = taskResults.getString("Description");
                int duration = taskResults.getInt("Duration");
                int maxWindow = taskResults.getInt("MaxWindow");

                switch(description) {
                    case ("Kit feeding"):
                        newTask.add(new Task(taskID, description, duration, maxWindow));
                        break;

                    case ("Rebandage leg wound"):
                        newTask.add(new Task(taskID, description, duration, maxWindow));
                        break;

                    case ("Apply burn ointment back"):
                        newTask.add(new Task(taskID, description, duration, maxWindow));
                        break;

                    case ("Administer antibiotics"):
                        newTask.add(new Task(taskID, description, duration, maxWindow));
                        break;

                    case ("Flush neck wound"):
                        newTask.add(new Task(taskID, description, duration, maxWindow));
                        break;

                    case ("Give fluid injection"):
                        newTask.add(new Task(taskID, description, duration, maxWindow));
                        break;

                    case ("Give vitamin injection"):
                        newTask.add(new Task(taskID, description, duration, maxWindow));
                        break;

                    case ("Mange treament"):
                        newTask.add(new Task(taskID, description, duration, maxWindow));
                        break;

                    case ("Eyedrops"):
                        newTask.add(new Task(taskID, description, duration, maxWindow));
                        break;

                    case ("Inspect broken leg"):
                        newTask.add(new Task(taskID, description, duration, maxWindow));
                        break;
                    default:
                        System.out.println("Invalid task");
                        break;
                }


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newTask;
    }

    public ArrayList<Animal> selectAnimalData(){

        try{
            Statement myStmt = dbConnect.createStatement();
            animalResults = myStmt.executeQuery("SELECT * FROM ANIMALS");

            while (animalResults.next()){
                int animalID = animalResults.getInt("AnimalID");
                String animalNickname = animalResults.getString("AnimalNickname");
                String animalSpecies = animalResults.getString("AnimalSpecies");

                switch(animalSpecies){
                    case("coyote"):
                        animals.add(new Coyote(animalID, animalNickname, animalSpecies));
                        break;
                    case("beaver"):
                        animals.add(new Beaver(animalID, animalNickname, animalSpecies));
                        break;
                    case("Fox"):
                        animals.add(new Fox(animalID, animalNickname, animalSpecies));
                        break;
                    case("Raccoon"):
                        animals.add(new Raccoon(animalID, animalNickname, animalSpecies));
                        break;
                    case("porcupine"):
                        animals.add(new Porcupine(animalID, animalNickname, animalSpecies));
                        break;
                    default:
                        System.out.println("Invalid Species");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animals;
    }

    public ArrayList<Treatment> selectTreatmentsData(){

        try{
            Statement myStmt = dbConnect.createStatement();
            treatmentResults = myStmt.executeQuery("SELECT * FROM TREATMENTS");

            while (treatmentResults.next()){
                int animalID = treatmentResults.getInt("AnimalID");
                int taskID = treatmentResults.getInt("TaskID");
                int startHour = treatmentResults.getInt("StartHour");

                treatments.add(new Treatment(animalID, taskID, startHour));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return treatments;
    }

    //Step 5: close the connection
    public void close(){
        try{
            taskResults.close();
            dbConnect.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void closeAnimal(){
        try{
            treatmentResults.close();
            dbConnect.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void closeTreatment(){
        try{
            treatmentResults.close();
            dbConnect.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        SQLData myJDBC = new SQLData("jdbc:postgresql://localhost/ewr", "oop", "ucalgary");

        myJDBC.initializeConnection();


        ArrayList<Task> newList = myJDBC.selectTask();
        ArrayList<Animal> animals = myJDBC.selectAnimalData();
        ArrayList<Treatment> treatments = myJDBC.selectTreatmentsData();
//        allData = myJDBC.selectData();
//        System.out.println(allData);

        myJDBC.close();
        myJDBC.closeAnimal();
        myJDBC.closeTreatment();
    }

}