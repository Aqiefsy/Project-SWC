package Train;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReceiptFrame extends JFrame {

    private JPanel contentPane;
    private JTextArea receiptTextArea;
    private JLabel lblNewLabel_2;
    private Timer timer;
    private int x = 760;
    private JTextField UserCurrentBalance;

    public ReceiptFrame(String[] bookingInfo, double totalAmount, double newBalance) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 774, 495);
        setTitle("Receipt");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Receipt information text area
        StringBuilder receiptText = new StringBuilder();
        receiptText.append("Receipt:\n");
        for (String info : bookingInfo) {
            receiptText.append(info).append("\n");
        }
        receiptText.append("\nTotal Amount Paid: ").append(totalAmount);

        receiptTextArea = new JTextArea(receiptText.toString());
        receiptTextArea.setEditable(false);
        receiptTextArea.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 14));
        receiptTextArea.setBounds(209, 156, 356, 252);
        contentPane.add(receiptTextArea);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setIcon(new ImageIcon(ReceiptFrame.class.getResource("/img/Microsoft-Fluentui-Emoji-3d-Train-3d.64.png")));
        lblNewLabel.setBounds(190, 40, 64, 54);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Train Malaysia");
        lblNewLabel_1.setFont(new Font("Segoe UI Black", Font.BOLD, 30));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(185, 40, 398, 54);
        contentPane.add(lblNewLabel_1);

        lblNewLabel_2 = new JLabel("Selamat Hari Raya ! Maaf Zahir Dan Batin Dari Kami Train Malaysia");
        lblNewLabel_2.setFont(new Font("Rockwell", Font.BOLD, 20));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_2.setBounds(757, 117, 763, 31);
        contentPane.add(lblNewLabel_2);

        UserCurrentBalance = new JTextField(String.format("%.2f", newBalance));
        UserCurrentBalance.setBounds(27, 417, 176, 31);
        UserCurrentBalance.setEditable(false);
        contentPane.add(UserCurrentBalance);
        UserCurrentBalance.setColumns(10);

        JLabel UserBalanceLabel = new JLabel("Current Balance:");
        UserBalanceLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        UserBalanceLabel.setBounds(42, 392, 144, 16);
        contentPane.add(UserBalanceLabel);

        // Create a Timer to update the text position
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x--;
                if (x < -lblNewLabel_2.getPreferredSize().getWidth()) {
                    x = 760;
                }
                lblNewLabel_2.setLocation(x, 124);
            }
        });
        timer.start();
    }
}