package Train;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import com.github.lgooddatepicker.components.DateTimePicker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TicketBooking extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton confirmBookingButton;
    private JComboBox<String> trainComboBox;
    private JTextArea trainInfoTextArea;
    private JTextField textField;
    private UserDashboard userDashboard;
    private JComboBox<String> routeComboBox;
    private JComboBox<String> seatsComboBox;
    private DateTimePicker dateTimePicker;
    private JComboBox<String> travelTimeComboBox;
    

    public TicketBooking(UserDashboard userDashboard) {
        this.userDashboard = userDashboard;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Ticket Booking");
        setSize(508, 536);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblSelectTrain = new JLabel("Select Train:");
        lblSelectTrain.setBounds(20, 20, 100, 20);
        contentPane.add(lblSelectTrain);

        trainComboBox = new JComboBox<>();
        trainComboBox.setBounds(130, 20, 259, 30);
        // Add logic to populate trainComboBox with available trains
        trainComboBox.addItem("Train 1");
        trainComboBox.addItem("Train 2");
        contentPane.add(trainComboBox);

        JLabel lblChooseDateTime = new JLabel("Date and Time:");
        lblChooseDateTime.setBounds(20, 60, 150, 20);
        contentPane.add(lblChooseDateTime);

        confirmBookingButton = new JButton("Confirm Booking");
        confirmBookingButton.setBounds(165, 266, 150, 30);
        confirmBookingButton.addActionListener(this);
        contentPane.add(confirmBookingButton);

        trainInfoTextArea = new JTextArea();
        trainInfoTextArea.setEditable(false);
        trainInfoTextArea.setBounds(39, 306, 400, 150);
        contentPane.add(trainInfoTextArea);
        
        dateTimePicker = new DateTimePicker();
        dateTimePicker.setBounds(130, 60, 259, 30);
        contentPane.add(dateTimePicker);
        
        JLabel RouteLabel = new JLabel("Route:");
        RouteLabel.setBounds(27, 104, 45, 13);
        contentPane.add(RouteLabel);
        
        routeComboBox = new JComboBox<>();
        routeComboBox.setBounds(130, 98, 259, 30);
        // Add logic to populate routeComboBox with available routes
        routeComboBox.addItem("A to B");
        routeComboBox.addItem("B to C");
        contentPane.add(routeComboBox);
        
        JLabel TravelTimeLabel = new JLabel("Travel Time:");
        TravelTimeLabel.setBounds(27, 138, 90, 20);
        contentPane.add(TravelTimeLabel);
        
        JLabel PriceLabel = new JLabel("Price:");
        PriceLabel.setBounds(27, 179, 45, 13);
        contentPane.add(PriceLabel);
        
        textField = new JTextField();
        textField.setBounds(130, 171, 259, 30);
        textField.setEditable(false); // Make price field non-editable
        textField.setText("RM 20"); // Set example price
        contentPane.add(textField);
        
        JLabel SeatsLabel = new JLabel("Seats:");
        SeatsLabel.setBounds(27, 216, 45, 13);
        contentPane.add(SeatsLabel);

        seatsComboBox = new JComboBox<>();
        seatsComboBox.setBounds(130, 211, 265, 30);
        // Add logic to populate seatsComboBox with available seat options
        for (int i = 1; i <= 18; i++) {
            seatsComboBox.addItem(i + "A");
            seatsComboBox.addItem(i + "B");
        }
        contentPane.add(seatsComboBox);
        
        travelTimeComboBox = new JComboBox<>();
        travelTimeComboBox.setBounds(130, 133, 259, 30);
        // Add logic to populate travelTimeComboBox with available travel times
        travelTimeComboBox.addItem("8:00 AM - 10:00 AM");
        travelTimeComboBox.addItem("10:00 AM - 11:00 AM");
        travelTimeComboBox.addItem("12:00 PM - 2:00 PM");
        travelTimeComboBox.addItem("2:00 PM - 4:00 PM");
        travelTimeComboBox.addItem("4:00 PM - 6:30 PM");
        travelTimeComboBox.addItem("6:30 PM - 7:30PM");
        travelTimeComboBox.addItem("7:30 PM - 9:30PM");
        travelTimeComboBox.addItem("9:30 PM - 11:30PM");
        contentPane.add(travelTimeComboBox);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmBookingButton) {
            // Get the selected train, route, price, seats, and travel time
            String selectedTrain = (String) trainComboBox.getSelectedItem();
            String selectedRoute = (String) routeComboBox.getSelectedItem();
            String selectedPrice = textField.getText().replace("RM ", "");
            String selectedSeats = (String) seatsComboBox.getSelectedItem();
            String selectedTravelTime = (String) travelTimeComboBox.getSelectedItem();
            
            // Get selected date and time from DateTimePicker
            LocalDateTime dateTime = dateTimePicker.getDateTimePermissive();
            if (dateTime != null) {
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                String selectedDate = dateTime.format(dateFormatter);
                String selectedTime = dateTime.format(timeFormatter);

                // Add the booking information to the UserDashboard's table
                userDashboard.appendBookingInfo(new Object[] { selectedTrain, selectedDate, selectedTime, selectedRoute, selectedTravelTime, selectedPrice, selectedSeats });
                // Close the TicketBooking frame
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a valid date and time.", "Invalid Selection", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}