package Train;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WelcomePage extends JFrame implements ActionListener {
    JButton startButton;

    public WelcomePage() {
        setTitle("Train Ticket Booking");
        setSize(836, 573);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

      

        // Create a Panel
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(null);

        // Create Label
        JLabel label = new JLabel("", SwingConstants.CENTER);
        label.setBounds(212, 10, 408, 394);
        label.setIcon(new ImageIcon(getClass().getResource("/img/Train.png")));
        label.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(label);

        // Create Start Button
        startButton = new JButton("Start");
        startButton.setFont(new Font("Tahoma", Font.PLAIN, 21));
        startButton.setBounds(0, 481, 822, 55);
        // Register to a listener
        startButton.addActionListener(this);
        panel.add(startButton);

        JLabel Welcome = new JLabel("Welcome to Train Ticket Booking System");
        Welcome.setFont(new Font("Segoe UI Black", Font.BOLD, 25));
        Welcome.setHorizontalAlignment(SwingConstants.CENTER);
        Welcome.setBounds(126, 415, 578, 35);
        panel.add(Welcome);

        setVisible(true);
    }

    // method overriding
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            dispose(); // Close the WelcomePage window
            UserDashboard userDashboard = new UserDashboard(); // Create an instance of UserDashboard
            userDashboard.setVisible(true); // Make the UserDashboard window visible
        }
    }

    public static void main(String[] args) {
        WelcomePage WP = new WelcomePage();
    }
}
