package edu.ucalgary.oop;
import java.sql.*;
public class SQLData {

    private Connection dbConnect;
    private ResultSet results;

    public final String DBURL;
    public final String USERNAME;
    public final String PASSWORD;

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
    public String selectData(){
        StringBuffer tasksAndTreatments = new StringBuffer();

        try{
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM treatments");

            while (results.next()){
                System.out.println("Print results: " + results.getString(""));
                tasksAndTreatments.append(results.getString(""));
                tasksAndTreatments.append('\n');
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasksAndTreatments.toString();
    }

    //Step 5: close the connection
    public void close(){
        try{
            results.close();
            dbConnect.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        SQLData myJDBC = new SQLData("whatever URL", "whatever username", "whatever password");

        myJDBC.initializeConnection();

        String allData = myJDBC.selectData();
        System.out.println(allData);

        myJDBC.close();
    }

}