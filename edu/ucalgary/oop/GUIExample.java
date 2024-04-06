package edu.ucalgary.oop;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GUIExample {
    private JFrame frame;
    private JButton printScheduleBtn;
    private JButton displayAnimalsBtn;
    private JButton displayTasksBtn;

    SQLData myJDBC = new SQLData("jdbc:postgresql://localhost:5432/ewr", "oop", "ucalgary");

    public GUIExample() {
        frame = new JFrame("Wildlife Rescue Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        myJDBC.initializeConnection();

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
    }

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
                    // displayTasks();
                }
            }
        });
        return button;
    }

    private void printSchedule() {
        System.out.println("Printing Schedule...");
    }

    private void displayAnimals() {
        List<Animal> animals = myJDBC.getAnimalList();
        String format = "| %-3s | %-24s | %-15s |\n";
        StringBuilder sb = new StringBuilder();
        sb.append("+-----+--------------------------+-----------------+\n");
        sb.append(String.format(format, "ID", "Name", "Species"));
        for (Animal a : animals) {
            sb.append(a);
        }
        sb.append("+-----+--------------------------+-----------------+\n");
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