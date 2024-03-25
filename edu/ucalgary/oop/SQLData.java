package edu.ucalgary.oop;
import java.sql.*;
import java.util.*;
public class SQLData {

    private Connection dbConnect;
    private ResultSet taskResults;

    public final String DBURL;
    public final String USERNAME;
    public final String PASSWORD;

    private ArrayList<Task> newTask;

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

    //Step 5: close the connection
    public void close(){
        try{
            taskResults.close();
            dbConnect.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        SQLData myJDBC = new SQLData("jdbc:postgresql://localhost/ewr", "oop", "ucalgary");

        myJDBC.initializeConnection();


        ArrayList<Task> newList = myJDBC.selectTask();
//        allData = myJDBC.selectData();
//        System.out.println(allData);

        myJDBC.close();
    }

}