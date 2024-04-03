package edu.ucalgary.oop;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;


public class GUIExample {
    private JFrame frame;
    private JButton printScheduleBtn;
    private JButton displayAnimalsBtn;
    private JButton displayTasksBtn;

    SQLData myJDBC = new SQLData("jdbc:postgresql://localhost:5432/ewr", "oop", "ucalgary");
   

    public GUIExample() {
        frame = new JFrame("GUI Example");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        myJDBC.initializeConnection();
        
        printScheduleBtn = new JButton("Print Schedule");
        printScheduleBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                printSchedule();
            }
        });

        displayAnimalsBtn = new JButton("Display Animals");
        displayAnimalsBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayAnimals();
            }
        });

        displayTasksBtn = new JButton("Display Tasks");
        displayTasksBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayTasks();
            }
        });

        frame.add(printScheduleBtn);
        frame.add(displayAnimalsBtn);
        frame.add(displayTasksBtn);

        frame.setVisible(true);
    }

    private void printSchedule() {
        // Placeholder method for printing schedule
        System.out.println("Printing Schedule...");
    }

    private void displayAnimals() {
        // Placeholder method for displaying animals
        List<Animal> animals = myJDBC.getAnimalList();
        String format = "| %-3s | %-24s | %-15s |\n";
        StringBuilder sb = new StringBuilder();
        sb.append("+----+---------------------------+-----------------+\n");

        sb.append(String.format(format, "ID", "Name", "Species"));
        for(Animal a: animals){
            sb.append(a.printInfo());
        }
        sb.append("+----+---------------------------+-----------------+\n");
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUIExample();
            }
        });
    }
}
