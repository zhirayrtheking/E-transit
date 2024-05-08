import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WithdrawUI extends JFrame {
    private JLabel withdrawLabel;
    private JTextField amountField;
    private JButton withdrawButton;
    ImageIcon logo = new ImageIcon("LOGO.jpg");
    public WithdrawUI(Account userAccount, User user) {
        setTitle("Withdraw");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setIconImage(logo.getImage());
        // Panel for deposit components
        JPanel withdrawPanel = new JPanel();
        withdrawPanel.setLayout(new GridLayout(3, 1));

        // Label for deposit message
        withdrawLabel = new JLabel("How much money are you withdrawing?");
        withdrawPanel.add(withdrawLabel);

        // Text field for amount
        amountField = new JTextField();
        withdrawPanel.add(amountField);

        // Button for deposit
        withdrawButton = new JButton("Withdraw");
        withdrawButton.setBackground(new Color(231, 76, 60)); // Red
        withdrawButton.setForeground(Color.WHITE);
        withdrawButton.setFocusPainted(false);
        withdrawPanel.add(withdrawButton);

        // Add deposit panel to frame
        add(withdrawPanel, BorderLayout.CENTER);

        // Action listener for deposit button
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the amount from the text field
                String amountText = amountField.getText();

                // Convert amount to double (you might want to add validation here)
                double amount = Double.parseDouble(amountText);

                if (userAccount.withdraw(amount)){
                    JOptionPane.showMessageDialog(null, "Dear " + user.getUsername() + " you successfully withdraw " + amount +
                            " USD from your account. Press ok to return to your dashboard.", "E-Transit", JOptionPane.INFORMATION_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(null, "Insufficient funds in your account", "E-Transit", JOptionPane.ERROR_MESSAGE);
                }



                dispose();
                new DashboardUIRegular(user, userAccount);


            }
        });

        setVisible(true);
    }
}