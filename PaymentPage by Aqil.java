package Train;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentPage extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel totalAmountLabel;
    private JLabel bookingInfoLabel;
    private JButton btnNewButton;
    private JLabel lblNewLabel;
    private JTextField currentBalanceTextField;
    private JLabel lblDeposit;
    private JTextField depositTextField;
    private JTextField totalAmountTextField;
    private JLabel lblTotalAmount;

    public PaymentPage(String[] bookingInfo, double totalAmountValue) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 774, 495);
        setTitle("Payment Page");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Booking information label
        StringBuilder bookingInfoText = new StringBuilder("<html><body>");
        bookingInfoText.append("<b>Booking Information:</b><br>");
        for (String info : bookingInfo) {
            bookingInfoText.append(info).append("<br>");
        }
        bookingInfoText.append("</body></html>");

        bookingInfoLabel = new JLabel(bookingInfoText.toString());
        bookingInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bookingInfoLabel.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 14));
        bookingInfoLabel.setBounds(20, 20, 706, 150);
        contentPane.add(bookingInfoLabel);

        totalAmountTextField = new JTextField(String.format("%.2f", totalAmountValue));
        totalAmountTextField.setEditable(false);
        totalAmountTextField.setColumns(10);
        totalAmountTextField.setBounds(283, 313, 238, 31);
        contentPane.add(totalAmountTextField);

        // Pay button
        JButton btnPay = new JButton("Pay");
        btnPay.setBounds(361, 395, 160, 31);
        btnPay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the current balance from the currentBalanceTextField
                double currentBalance = Double.parseDouble(currentBalanceTextField.getText());

                // Get the total amount from the totalAmountTextField
                double totalAmount = Double.parseDouble(totalAmountTextField.getText());

                if (currentBalance >= totalAmount) {
                    // Calculate the new balance after payment
                    double newBalance = currentBalance - totalAmount;

                    // Update the current balance in the currentBalanceTextField
                    currentBalanceTextField.setText(String.format("%.2f", newBalance));

                    // Perform payment processing here
                    // Dummy processing (displaying a message)
                    JOptionPane.showMessageDialog(PaymentPage.this, "Payment Successful!", "Payment Status", JOptionPane.INFORMATION_MESSAGE);

                    // Open the receipt frame and pass the new balance
                    ReceiptFrame receiptFrame = new ReceiptFrame(bookingInfo, totalAmount, newBalance);
                    receiptFrame.setVisible(true);

                    dispose(); // Close the payment page after successful payment
                } else {
                    JOptionPane.showMessageDialog(PaymentPage.this, "Insufficient balance!", "Payment Status", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        contentPane.add(btnPay);

        btnNewButton = new JButton("Deposit");
        btnNewButton.setBounds(179, 400, 147, 26);
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCurrentBalance();
            }
        });
        contentPane.add(btnNewButton);

        lblNewLabel = new JLabel("Current Balance:");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(125, 262, 130, 19);
        contentPane.add(lblNewLabel);

        currentBalanceTextField = new JTextField("0");
        currentBalanceTextField.setEditable(false);
        currentBalanceTextField.setBounds(283, 257, 238, 31);
        contentPane.add(currentBalanceTextField);
        currentBalanceTextField.setColumns(10);

        lblDeposit = new JLabel("Deposit:");
        lblDeposit.setHorizontalAlignment(SwingConstants.CENTER);
        lblDeposit.setFont(new Font("Arial", Font.BOLD, 14));
        lblDeposit.setBounds(125, 208, 130, 19);
        contentPane.add(lblDeposit);

        depositTextField = new JTextField();
        depositTextField.setColumns(10);
        depositTextField.setBounds(283, 203, 238, 31);
        contentPane.add(depositTextField);

        lblTotalAmount = new JLabel("Total Amount:");
        lblTotalAmount.setHorizontalAlignment(SwingConstants.CENTER);
        lblTotalAmount.setFont(new Font("Arial", Font.BOLD, 14));
        lblTotalAmount.setBounds(125, 322, 130, 19);
        contentPane.add(lblTotalAmount);
    }

    private void updateCurrentBalance() {
        String depositText = depositTextField.getText();
        if (!depositText.isEmpty()) {
            try {
                double deposit = Double.parseDouble(depositText);
                currentBalanceTextField.setText(String.valueOf(deposit));
            } catch (NumberFormatException ex) {
                // Handle invalid input
                JOptionPane.showMessageDialog(this, "Invalid deposit amount. Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}