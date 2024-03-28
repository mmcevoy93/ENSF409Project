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
    private List<Animal> animals = new ArrayList<>();
    private List<Treatment> treatments = new ArrayList<>();

    public SQLData(String url, String user, String pw){
        this.DBURL = url;
        this.USERNAME = user;
        this.PASSWORD = pw;
        initializeConnection();
        selectAnimalData();
        selectTreatmentsData();
        close();
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


    public void selectAnimalData(){
        ResultSet animalResults;
        String query = "SELECT * FROM ANIMALS";
        try{
            Statement myStmt = dbConnect.createStatement();
            animalResults = myStmt.executeQuery(query);
            while (animalResults.next()){
                int animalID = animalResults.getInt("AnimalID");
                String animalNickname = animalResults.getString("AnimalNickname");
                String animalSpecies = animalResults.getString("AnimalSpecies");
                switch(animalSpecies){
                    case("coyote"):
                        this.animals.add(new Coyote(animalID, animalNickname));
                        break;
                    case("beaver"):
                        this.animals.add(new Beaver(animalID, animalNickname));
                        break;
                    case("fox"):
                        this.animals.add(new Fox(animalID, animalNickname));
                        break;
                    case("raccoon"):
                        this.animals.add(new Raccoon(animalID, animalNickname));
                        break;
                    case("porcupine"):
                        this.animals.add(new Porcupine(animalID, animalNickname));
                        break;
                    default:
                        System.out.println("Invalid Species");
                }
            }
            animalResults.close();
        } 
        catch (SQLException e) {e.printStackTrace();}
    }

    public void selectTreatmentsData(){
        ResultSet treatmentResults;
        String query = "SELECT " +
                            "ANIMALS.AnimalNickname AS AnimalName, " +
                            "TASKS.Description AS TaskDescription, " +
                            "TASKS.Duration AS Duration, " +
                            "TASKS.MaxWindow AS MaxWindow, " +
                            "TREATMENTS.StartHour AS StartHour " +
                        "FROM " +
                            "TREATMENTS " +
                        "JOIN " +
                            "ANIMALS ON TREATMENTS.AnimalID = ANIMALS.AnimalID " +
                        "JOIN " +
                            "TASKS ON TREATMENTS.TaskID = TASKS.TaskID";
        try
        {
            Statement myStmt = dbConnect.createStatement();
            treatmentResults = myStmt.executeQuery(query);
            while (treatmentResults.next()){
                String animalName = treatmentResults.getString("AnimalName");
                String taskDescription = treatmentResults.getString("TaskDescription");
                int duration = treatmentResults.getInt("Duration");
                int maxWindow = treatmentResults.getInt("MaxWindow");
                int startHour = treatmentResults.getInt("StartHour");
                this.treatments.add(new Treatment(animalName, taskDescription, startHour, duration, maxWindow));
            }
            treatmentResults.close();
        } 
        catch (SQLException e) {e.printStackTrace();}
    }

    public void close(){
        try{
            dbConnect.close();
        } 
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Animal> getAnimalList(){return this.animals;}
    public List<Treatment> getTreatmentList(){return this.treatments;}

    public static void main(String[] args){
        SQLData myJDBC = new SQLData("jdbc:postgresql://localhost:5432/ewr", "oop", "ucalgary");
        myJDBC.initializeConnection();
        myJDBC.close();
    }

}