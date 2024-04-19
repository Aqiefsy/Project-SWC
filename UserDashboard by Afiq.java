package Train;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDashboard extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private List<String[]> bookingInfoList; // List to store booking information

    public UserDashboard() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1231, 570);
        setTitle("User Dashboard");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblWelcome = new JLabel("Welcome to Train Booking System");
        lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        lblWelcome.setBounds(460, 15, 250, 20);
        contentPane.add(lblWelcome);

        JButton btnBookTicket = new JButton("Book Ticket");
        btnBookTicket.setBounds(519, 55, 150, 30);
        btnBookTicket.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // When "Book Ticket" button is clicked, show TicketBooking panel
                TicketBooking ticketBooking = new TicketBooking(UserDashboard.this);
                ticketBooking.setVisible(true);
            }
        });
        contentPane.add(btnBookTicket);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(38, 100, 1118, 367);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        model = new DefaultTableModel(
                new Object[][] {},
                new String[] { "Train", "Date", "Time", "Route", "Travel Time", "Price", "Seats" }
        );
        table.setModel(model);

        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(1038, 10, 100, 30);
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the UserDashboard frame when "Logout" button is clicked
                dispose();
            }
        });
        contentPane.add(btnLogout);

        JButton paymentButton = new JButton("Payment");
        paymentButton.setBounds(519, 493, 166, 30);
        contentPane.add(paymentButton);
        paymentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the selected row from the table
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // Retrieve the booking information from the selected row
                    String[] bookingInfo = bookingInfoList.get(selectedRow);

                    // Extract the price from the booking information
                    String priceString = bookingInfo[5].split(": ")[1];
                    double price = Double.parseDouble(priceString.replace("RM ", ""));

                    // Create an instance of the PaymentPage and pass the booking information and price
                    PaymentPage paymentPage = new PaymentPage(bookingInfo, price);
                    paymentPage.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(UserDashboard.this, "Please select a booking from the table.", "No Booking Selected", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Initialize bookingInfoList
        bookingInfoList = new ArrayList<>();
    }

    public void appendBookingInfo(Object[] bookingData) {
        String selectedTrain = (String) bookingData[0];
        String selectedDate = (String) bookingData[1];
        String selectedTime = (String) bookingData[2];
        String selectedRoute = (String) bookingData[3];
        String selectedTravelTime = (String) bookingData[4];
        String selectedPrice = (String) bookingData[5];
        String selectedSeats = (String) bookingData[6];

        String[] bookingInfo = {
            "Train: " + selectedTrain,
            "Date: " + selectedDate,
            "Time: " + selectedTime,
            "Route: " + selectedRoute,
            "Travel Time: " + selectedTravelTime,
            "Price: " + selectedPrice,
            "Seats: " + selectedSeats
        };

        // Append the booking information to the table
        model.addRow(bookingData);

        // Add the booking information to the bookingInfoList
        bookingInfoList.add(bookingInfo);
    }
}