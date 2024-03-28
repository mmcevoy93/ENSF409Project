package edu.ucalgary.oop;
/*
@author <a href="mailto:">
</a>
@version 1.2
@since 1.0
*/
import java.sql.*;
import java.util.*;

public class SQLData {
    private Connection dbConnect;
    private final String DBURL;
    private final String USERNAME;
    private final String PASSWORD;

    public SQLData(String url, String user, String pw){
        this.DBURL = url;
        this.USERNAME = user;
        this.PASSWORD = pw;
    }

    public void initializeConnection() {
        try {
            dbConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        } 
        catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }
    }

    public List<Task> selectTask(){
        List<Task> newTask = new ArrayList<>();
        ResultSet taskResults;
        try{
            Statement myStmt = dbConnect.createStatement();
            taskResults = myStmt.executeQuery("SELECT * FROM TASKS");
            
            while (taskResults.next()){
                int taskID = taskResults.getInt("TaskID");
                String description = taskResults.getString("Description");
                int duration = taskResults.getInt("Duration");
                int maxWindow = taskResults.getInt("MaxWindow");
                newTask.add(new Task(taskID, description, duration, maxWindow));
            }
            taskResults.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return newTask;        
    }

    public List<Animal> selectAnimalData(){
        ResultSet animalResults;
        List<Animal> animals = new ArrayList<>();
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
        return animals;
    }

    public List<Treatment> selectTreatmentsData(){
        List<Treatment> treatments = new ArrayList<>();
        ResultSet treatmentResults;
        try{
            Statement myStmt = dbConnect.createStatement();
            treatmentResults = myStmt.executeQuery("SELECT * FROM TREATMENTS");
            while (treatmentResults.next()){
                int animalID = treatmentResults.getInt("AnimalID");
                int taskID = treatmentResults.getInt("TaskID");
                int startHour = treatmentResults.getInt("StartHour");
                treatments.add(new Treatment(animalID, taskID, startHour));
            }
            treatmentResults.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return treatments;
    }

    public void close(){
        try{
            dbConnect.close();
        } 
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        SQLData myJDBC = new SQLData("jdbc:postgresql://localhost:5432/ewr", "oop", "ucalgary");
        myJDBC.initializeConnection();
        myJDBC.close();
    }

}