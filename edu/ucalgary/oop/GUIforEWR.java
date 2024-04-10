package edu.ucalgary.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.*;

/**
 * The GUIforEWR class represents the graphical user interface for the Wildlife Rescue Management System.
 * It provides functionality to display the daily schedule, animal list, and treatment tasks.
 */
public class GUIforEWR {
    private JFrame frame;
    private JButton printScheduleBtn;
    private JButton displayAnimalsBtn;
    private JButton displayTasksBtn;
    private List<Animal> animals = new ArrayList<>();
    private List<DailyTasks> treatments = new ArrayList<>();
    private Schedule schedule;
    /**
     * Constructs a new GUIforEWR object and initializes the GUI components.
     */
    public GUIforEWR() {
        // Create the main frame
        frame = new JFrame("Wildlife Rescue Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create the buttons
        printScheduleBtn = createButton("Print Schedule", Color.BLUE);
        displayAnimalsBtn = createButton("Display Animals", Color.GREEN);
        displayTasksBtn = createButton("Display Tasks", Color.ORANGE);

        // Add the buttons to the button panel
        buttonPanel.add(printScheduleBtn);
        buttonPanel.add(displayAnimalsBtn);
        buttonPanel.add(displayTasksBtn);
        frame.add(buttonPanel, BorderLayout.CENTER);

        // Create a header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.LIGHT_GRAY);
        JLabel headerLabel = new JLabel("Welcome to the Wildlife Rescue Management System");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerPanel.add(headerLabel);

        // Add the header panel to the frame
        frame.add(headerPanel, BorderLayout.NORTH);

        // Set the frame properties
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Initialize the SQL data
        initSQL();
    }

    /**
     * Initializes the SQL data by retrieving the treatment tasks and animal list from the database.
     */
    private void initSQL() {
        SQLData myJDBC = new SQLData("jdbc:postgresql://localhost:5432/ewr", "oop", "ucalgary");
        myJDBC.initializeConnection();
        this.treatments = myJDBC.getTreatmentTasks();
        this.animals = myJDBC.getAnimalList();
        this.schedule = new Schedule(animals, treatments);
    }

    /**
     * Creates a button with the specified text and color.
     *
     * @param text  the text to display on the button
     * @param color the background color of the button
     * @return the created button
     */
    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 50));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == printScheduleBtn) {
                    printSchedule();
                } else if (e.getSource() == displayAnimalsBtn) {
                    displayAnimals();
                } else if (e.getSource() == displayTasksBtn) {
                    displayTasks();
                }
            }
        });
        return button;
    }

    /**
     * Prints the daily schedule.
     */
    private void printSchedule() {
        try{
            while(!scheduleTreatments()){}
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        // Display the schedule in a dialog box
        JTextArea scheduleArea = new JTextArea(schedule.toString());
        scheduleArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(scheduleArea);
        scrollPane.setPreferredSize(new Dimension(500, 400));
        JOptionPane.showMessageDialog(frame, scrollPane, "Daily Schedule", JOptionPane.PLAIN_MESSAGE);

        // Save the schedule to a file
        String fileName = "schedule_" + java.time.LocalDate.now() + ".txt";
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(schedule.toString());
            System.out.println("Schedule saved to " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving schedule to file: " + e.getMessage());
        }        
    }


    /**
     * Attempts to add all Treatments to Schedule
     * If it is impossible it will ask user if they
     * want to add a backup volunteer
     * if buildSchedule throws exception it will return false
     * @return boolean: Treatments added to schedule
     */
    private boolean scheduleTreatments() throws IllegalArgumentException{
        boolean scheduleMade = false;
        try{
            schedule.buildSchedule("treatment");
            scheduleMade = true;
        } 
        catch (BackUpVolunteerNeededException e) {
            DailyTasks errorTask = e.getTask();
            // Display an error message if it's not possible to create a schedule
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Scheduling Error", JOptionPane.ERROR_MESSAGE);
            String backupVolunterOption = String.format("Do you want to add a Backup Volunteer?");
            //Ask if we want to add a backupvolunteer
            int TryBackup = JOptionPane.showConfirmDialog(null, backupVolunterOption, "Backup Volunteer", JOptionPane.YES_NO_OPTION);
            
            if (TryBackup == JOptionPane.YES_OPTION) {
                JSlider slider = new JSlider(errorTask.getStartHour(), errorTask.getStartHour()+errorTask.getMaxWindow()-1);
                slider.setMajorTickSpacing(1);
                slider.setPaintTicks(true);
                slider.setPaintLabels(true);
                int option = JOptionPane.showOptionDialog(null, slider, "Select an hour for a backup volunteer:",
                        JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
                if(option != JOptionPane.NO_OPTION && option != JOptionPane.CLOSED_OPTION){
                    int selectedValue = slider.getValue();
                    int confirm = JOptionPane.showConfirmDialog(null,"Are you sure you want an extra volunteer at: " + selectedValue + ":00", "Backup Volunteer", JOptionPane.YES_NO_OPTION);
                    if(confirm == JOptionPane.YES_OPTION){
                        schedule.addBackupVolunteer(errorTask.getStartHour());
                    }
                    else{
                        throw new IllegalArgumentException("Schedule not Generated");
                    }
                }else{
                    throw new IllegalArgumentException("Schedule not Generated");
                }
            }
            else{
                throw new IllegalArgumentException("Schedule not Generated");
            }
        }
        return scheduleMade;
    }



    /**
     * Displays the list of animals.
     */
    private void displayAnimals() {
        System.out.println("Animal List");
        String format = "| %-3s | %-24s | %-15s |\n";
        String lineBreak = "+-----+--------------------------+-----------------+\n";
        StringBuilder sb = new StringBuilder();
        sb.append(lineBreak);
        sb.append(String.format(format, "ID", "Name", "Species"));
        for (Animal a : animals) {
            sb.append(a);
        }
        sb.append(lineBreak);
        System.out.println(sb.toString());
    }

    /**
     * Displays the list of treatment tasks.
     */
    private void displayTasks() {
        // System.out.println("Treatment List");
        // StringBuilder sb = new StringBuilder();
        // sb.append(tasks + "\n");
        // Set<String> uniqueDescriptions = new HashSet<>();
        // for (DailyTasks t : tasks) {
        //     if (!uniqueDescriptions.contains(t.getDescription())) {
        //         sb.append(t);
        //         uniqueDescriptions.add(t.getDescription());
        //     }
        // }

        // System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUIforEWR();
            }
        });
    }
}