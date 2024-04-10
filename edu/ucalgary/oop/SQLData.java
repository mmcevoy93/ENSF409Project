package edu.ucalgary.oop;

/*
@author Max McEvoy 30005167<a href="mailto:max.mcevoy@ucalgary.ca">
max.mcevoy@ucalgary.ca</a>
@version 1.4
@since 1.0
*/
import java.sql.*;
import java.util.*;

/**
 * SQLData
 * 
 * Handles calls to PostGres database
 * Two methods to get animals and treatments
 */
public class SQLData {
    private Connection dbConnect;
    private final String DBURL;
    private final String USERNAME;
    private final String PASSWORD;
    private List<Animal> animals = new ArrayList<>();
    private List<DailyTasks> tasks = new ArrayList<>();

    /**
     * 
     * @param url  String: database address
     * @param user String: username
     * @param pw   String: password
     */
    public SQLData(String url, String user, String pw) throws Exception {
        this.DBURL = url;
        this.USERNAME = user;
        this.PASSWORD = pw;
        try{
            initializeConnection();
        }
        catch(SQLException e){
            throw new Exception("Database connection failed!");

        }
        selectAnimalData();
        selectTreatmentsData();
        close();
    }
    /**
     * Updates the start hour of a treatment in the database
     *
     * @param startHour the new start hour for the treatment
     */
    public void updateTreatmentStartHour(int newStart, String animalNickname, String description, int oldStart) {
        String query = "UPDATE TREATMENTS " +
        "SET StartHour = %d " +
        "WHERE AnimalID = (SELECT AnimalID FROM ANIMALS WHERE AnimalNickname = '%s') " +
        "AND TaskID = (SELECT TaskID FROM TASKS WHERE Description = '%s') " +
        "AND StartHour = %d;";
        query = String.format(query, newStart, animalNickname, description, oldStart);

    
        try {
            Statement myStmt = dbConnect.createStatement();
            int rowsAffected = myStmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
   
    /**
     * Initializes connection to database
     */
    public void initializeConnection() throws SQLException {
        dbConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
    }

    /**
     * selects all from the animal table
     * Populates list of Animals with correct animal
     */
    public void selectAnimalData() {
        ResultSet animalResults;
        String query = "SELECT * FROM ANIMALS";
        try {
            Statement myStmt = dbConnect.createStatement();
            animalResults = myStmt.executeQuery(query);
            while (animalResults.next()) {
                int animalID = animalResults.getInt("AnimalID");
                String animalNickname = animalResults.getString("AnimalNickname");
                String animalSpecies = animalResults.getString("AnimalSpecies");
                switch (animalSpecies) {
                    case ("coyote"):
                        this.animals.add(new Coyote(animalID, animalNickname));
                        break;
                    case ("beaver"):
                        this.animals.add(new Beaver(animalID, animalNickname));
                        break;
                    case ("fox"):
                        this.animals.add(new Fox(animalID, animalNickname));
                        break;
                    case ("raccoon"):
                        this.animals.add(new Raccoon(animalID, animalNickname));
                        break;
                    case ("porcupine"):
                        this.animals.add(new Porcupine(animalID, animalNickname));
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid Species");
                }
            }
            animalResults.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * selects from treatments joining animal and tasks together
     * populates list of DailyTasks
     */
    public void selectTreatmentsData() {
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
        try {
            Statement myStmt = dbConnect.createStatement();
            treatmentResults = myStmt.executeQuery(query);
            while (treatmentResults.next()) {
                String animalName = treatmentResults.getString("AnimalName");
                String taskDescription = treatmentResults.getString("TaskDescription");
                int duration = treatmentResults.getInt("Duration");
                int maxWindow = treatmentResults.getInt("MaxWindow");
                int startHour = treatmentResults.getInt("StartHour");
                this.tasks.add(new DailyTasks(animalName, taskDescription, startHour, duration, maxWindow));
            }
            treatmentResults.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes database connection
     */
    public void close() {
        try {
            dbConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the List of Animals retrived from database
     * @return List of Animal
     */
    public List<Animal> getAnimalList() {return this.animals;}

    /**
     * returns the List of DailyTasks retrived from database
     * @return List of Tasks
     */
    public List<DailyTasks> getTreatmentTasks() {return this.tasks;}

    /**
     * Temporary used to test This Class and Database connection
     * @param args
     */
}