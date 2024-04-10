package edu.ucalgary.oop;
/*
@author Max McEvoy 30005167<a href="mailto:max.mcevoy@ucalgary.ca">
max.mcevoy@ucalgary.ca</a>
@version 1.1
@since 1.0
*/
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.*;

/**
 * The GUIforEWR class represents the graphical user interface for the Wildlife
 * Rescue Management System.
 * It provides functionality to display the daily schedule, animal list, and
 * treatment tasks.
 */
public class GUIforEWR {
    private SQLData myJDBC;
    private JFrame frame;
    private JButton printScheduleBtn;
    private JButton displayAnimalsBtn;
    private JButton displayTasksBtn;
    private List<Animal> animals = new ArrayList<>();
    private List<DailyTasks> treatments = new ArrayList<>();
    private Schedule schedule;
    private String url = "jdbc:postgresql://localhost:5432/ewr";
    private String user = "oop";
    private String pass = "ucalgary";

    /**
     * Constructs a new GUIforEWR object and initializes the GUI components.
     * Using one global frame for most of this app
     */
    public GUIforEWR() {
        // Create the main frame
        frame = new JFrame("Wildlife Rescue Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 20, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        // Create the buttons
        printScheduleBtn = createButton("Print Schedule", Color.BLUE);
        displayAnimalsBtn = createButton("Display Animals", Color.GREEN);
        displayTasksBtn = createButton("Move a Treatment", Color.ORANGE);
        // Add the buttons to the button panel
        buttonPanel.add(printScheduleBtn);
        buttonPanel.add(displayAnimalsBtn);
        buttonPanel.add(displayTasksBtn);
        frame.add(buttonPanel, BorderLayout.CENTER);
        // Create a header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.LIGHT_GRAY);
        JLabel headerLabel = new JLabel("Welcome to the Wildlife Rescue Management System");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 12));
        headerPanel.add(headerLabel);
        // Add the header panel to the frame
        frame.add(headerPanel, BorderLayout.NORTH);
        // Set the frame properties
        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
        while (!login()) {
        }

    }

    /**
     * Initializes the SQL data by retrieving the treatment tasks and animal list
     * from the database.
     */
    public void initSQL(String url, String user, String pw) throws SQLException {
        myJDBC = new SQLData(url, user, pw);
        myJDBC.initializeConnection();
        this.treatments = myJDBC.getTreatmentTasks();
        this.animals = myJDBC.getAnimalList();
        this.schedule = new Schedule(this.animals, this.treatments);
    }

    /**
     * Creates a button with the specified text and color.
     *
     * @param text  the text to display on the button
     * @param color the background color of the button
     * @return the created button
     */
    public JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(200, 50));
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
                    changeTreatmentTime();
                }
            }
        });
        return button;
    }

    /**
     * User prompted for login credentials.
     * If unable to connect to database this will
     * return false. If connection is successful
     * true.
     * If user cancels GUI is terminated.
     * 
     * @return boolean: connected successfully
     */
    public boolean login() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        JTextField urlField = new JTextField(url);
        JTextField usernameField = new JTextField(user);
        JTextField passwordField = new JPasswordField(pass);
        panel.add(new JLabel("URL:"));
        panel.add(urlField);
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        // Show the option dialog
        int result = JOptionPane.showConfirmDialog(null, panel, "Login", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            user = usernameField.getText();
            pass = passwordField.getText();
            url = urlField.getText();
            try {
                initSQL(url, user, pass);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Login Failed\n" + e.getMessage());
                return false;
            }
        } else {
            System.exit(0);
        }
        return true;
    }

    /**
     * Attempts to print the daily schedule.
     * If scheduling is not possible with current
     * tasks it will prompt user if it wishes to
     * add a Backup Volunteer in the Window of time
     * a conflict happened.
     * 
     * Will first schedule treatments
     * Then feedings
     * Finally cleaning
     * 
     * any exceptions caught will appear as a dialog box
     * and will end this method without printing
     */
    public void printSchedule() {
        schedule.resetHourlySchedule();
        try {
            while (!scheduleTasks("treatment")) {
            }
            while (!scheduleTasks("feeding")) {
            }
            while (!scheduleTasks("cleaning")) {
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return;
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
            JOptionPane.showMessageDialog(null,"Schedule saved to:\n" +System.getProperty("user.dir")+"'\'"+ fileName);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Error saving schedule to file:\n" + e.getMessage());
        }
    }

    /**
     * Attempts to add all Treatments to Schedule
     * If it is impossible it will ask user if they
     * want to add a backup volunteer
     * if buildSchedule throws exception it will return false
     * 
     * @return boolean: Treatments added to schedule
     */
    public boolean scheduleTasks(String type) {
        boolean scheduleMade = false;
        try {
            schedule.buildSchedule(type);
            scheduleMade = true;
        } catch (BackUpVolunteerNeededException e) {
            DailyTasks errorTask = e.getTask();
            // Display an error message if it's not possible to create a schedule
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Scheduling Error", JOptionPane.ERROR_MESSAGE);
            if (askToAddBackupVolunteer(errorTask)) {
                int selectedValue = askForBackupVolunteerHour(errorTask);
                if (confirmBackupVolunteer(selectedValue)) {
                    schedule.addBackupVolunteer(errorTask.getStartHour());
                } else {
                    throwScheduleNotGeneratedException();
                }
            } else {
                throwScheduleNotGeneratedException();
            }
        }
        return scheduleMade;
    }

    /**
     * Prompts user with task that could not be scheduled
     * User will be asked if the want to schedule a
     * backup volunteer
     * 
     * @param errorTask DailyTask: Task that could not be scheduled
     * @return boolean: Users choice
     */
    public boolean askToAddBackupVolunteer(DailyTasks errorTask) {
        String backupVolunteerOption = String.format("Do you want to add a Backup Volunteer?");
        int tryBackup = JOptionPane.showConfirmDialog(null, backupVolunteerOption, "Backup Volunteer",
                JOptionPane.YES_NO_OPTION);
        return tryBackup == JOptionPane.YES_OPTION;
    }

    /**
     * Asks the user when to add a volunteer
     * Giving them a choice from the start hour
     * of the task that could not be scheduled
     * all the way to end of the max window hour
     * 
     * @param errorTask DailyTask: Task that could not be scheduled
     * @return hour that user selected to have a volunteer
     */
    public int askForBackupVolunteerHour(DailyTasks errorTask) {
        JSlider slider = new JSlider(errorTask.getStartHour(),
                errorTask.getStartHour() + errorTask.getMaxWindow() - 1);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        int option = JOptionPane.showOptionDialog(null, slider, "Select an hour for a backup volunteer:",
                JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
        return option != JOptionPane.NO_OPTION && option != JOptionPane.CLOSED_OPTION ? slider.getValue() : -1;
    }

    /**
     * Finaly confirmation that a backup volunteer has been called and
     * can be scheduled
     * 
     * @param selectedValue hour to add a backup volunteer
     * @return boolean: Called backup volunteer to confirm
     */
    public boolean confirmBackupVolunteer(int selectedValue) {
        int confirm = JOptionPane.showConfirmDialog(null,
                "Have you called and confirmed a backup volunteer at\n: " + selectedValue + ":00", "Backup Volunteer",
                JOptionPane.YES_NO_OPTION);
        return confirm == JOptionPane.YES_OPTION;
    }

    /*
     * throws exception that schedule was not generated at
     * any point the user attempts to cancel or declines an option
     */
    public void throwScheduleNotGeneratedException() {
        throw new IllegalArgumentException("Schedule not Generated");
    }

    /**
     * Displays the list of animals.
     * Not a requirement but we tossed it in anyway.
     */
    public void displayAnimals() {
        JFrame frame = new JFrame("Animal List");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 350);
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Species");
        for (Animal a : animals) {
            tableModel.addRow(new Object[] { a.getID(), a.getName(), a.getSpecies() });
        }
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    /**
     * Prompts the user to chose an hour that the
     * selected treatment will be moved to
     */
    public int pickHour(int startHour) {
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(startHour, 0, 23, 1));
        JLabel message = new JLabel("Please select a new hour:");
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(message);
        panel.add(spinner);
        int option = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Select Hour",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            return (int) spinner.getValue();
        } else {
            return -1;
        }
    }

    /**
     * Creates a pop up table that is populated with
     * descriptive info of all the Treatments in
     * order of starting hour
     * 
     * User can select a treatment and will have
     * the option of moving the treatment to another
     * starting hour
     * 
     * if a SQLException is caught it will show
     * a dialog message box with error message
     * will stop any progress and return to
     * main frame.
     */
    public void changeTreatmentTime() {
        try {
            initSQL(url, user, pass);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to initialize database connection:\n" + e.getMessage());
            return;
        }
        Collections.sort(this.treatments);
        JFrame treatmentFrame = new JFrame("Select Treatment");
        treatmentFrame.setSize(600, 400);
        treatmentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Start Hour");
        tableModel.addColumn("Animal");
        tableModel.addColumn("Description");
        tableModel.addColumn("Duration");
        tableModel.addColumn("Max Window");
        for (DailyTasks task : treatments) {
            tableModel.addRow(new Object[] { task.getStartHour() + ":00", task.getAnimalName(), task.getDescription(),
                    task.getDuration(), task.getMaxWindow() });
        }
        JTable treatmentTable = new JTable(tableModel);
        treatmentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(treatmentTable);
        JButton selectButton = new JButton("Select");
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = treatmentTable.getSelectedRow();
                if (selectedRow != -1) {
                    // Get the selected treatment
                    DailyTasks selectedTreatment = treatments.get(selectedRow);
                    int newHour = pickHour(selectedTreatment.getStartHour());
                    // Perform further actions with the selected treatment
                    if (newHour != -1) {
                        int confirm = JOptionPane.showConfirmDialog(null,
                                "Are you sure you want to move\nTreatment: " + selectedTreatment.getDescription()
                                        + "\nFor: " + selectedTreatment.getAnimalName() + "\nfrom: "
                                        + selectedTreatment.getStartHour() + "\nto: " + newHour,
                                "Confirm Change",
                                JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            try {
                                myJDBC.updateTreatmentStartHour(newHour, selectedTreatment);
                                JOptionPane.showMessageDialog(null, "Treatment moved successfully.");
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(null,
                                        "Failed to update treatment:\n" + ex.getMessage());
                            } finally {
                                myJDBC.close();
                                treatmentFrame.dispose();
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Please select a treatment.");
                    }
                }
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(selectButton, BorderLayout.SOUTH);
        treatmentFrame.add(panel);
        treatmentFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUIforEWR();
            }
        });

    }
}