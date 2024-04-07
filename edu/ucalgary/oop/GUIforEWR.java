package edu.ucalgary.oop;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.*;

public class GUIforEWR {
    private JFrame frame;
    private JButton printScheduleBtn;
    private JButton displayAnimalsBtn;
    private JButton displayTasksBtn;
    private List<Animal> animals = new ArrayList<>();
    private List<DailyTasks> tasks = new ArrayList<>();

    /**
     * Sets up GUI related stuff
     * listener for buttons and sets font
     * initializes the SQL stuff 
     */
    public GUIforEWR() {
        frame = new JFrame("Wildlife Rescue Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        printScheduleBtn = createButton("Print Schedule", Color.BLUE);
        displayAnimalsBtn = createButton("Display Animals", Color.GREEN);
        displayTasksBtn = createButton("Display Tasks", Color.ORANGE);
        
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

        frame.add(headerPanel, BorderLayout.NORTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        initSQL();
    }

    /**
     * Initializes SQL via SQLData class
     * Gets the needed info
     * closes connection
     */
    private void initSQL(){
        SQLData myJDBC = new SQLData("jdbc:postgresql://localhost:5432/ewr", "oop", "ucalgary");
        myJDBC.initializeConnection();
        tasks = myJDBC.getTreatmentTasks();
        animals = myJDBC.getAnimalList();
        myJDBC.close();
    }

    /**
     * 
     * @param text
     * @param color
     * @return
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
     * Prints Schedule
     * 
     */
    //TODO need to generate a txt file double check outline for specifics
    private void printSchedule() {
        System.out.println("Printing Schedule...");
        Schedule schedule = new Schedule(animals, tasks);
        System.out.println(schedule);
    }

    /**
     * Wasn't required for project
     * 
     * Displays a list the animals in EWR
     * | ID | Nickname | Species |
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
     * Wasn't required for project
     * 
     * Displays a list of medical treatments done at EWR
     * | Description | Nickname | Species |
     */
    private void displayTasks() {
        System.out.println("Treatment List");
        String format = "| %-25s | %-10s | %-10s |\n";
        String lineBreak = "+---------------------------+------------+------------+\n";
        StringBuilder sb = new StringBuilder();
        sb.append(lineBreak);
        sb.append(String.format(format,"Description", "Duration", "Max Window"));
        Set<String> uniqueDescriptions = new HashSet<>();
        for (DailyTasks t : tasks) {
            if (!uniqueDescriptions.contains(t.getDescription())) {
                sb.append(t);
                uniqueDescriptions.add(t.getDescription());
            }
        }
        sb.append(lineBreak);
        System.out.println(sb.toString());
    }

    /**
     * Runnable GUIS
     * Main to run for end of project
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUIforEWR();
            }
        });
    }
}