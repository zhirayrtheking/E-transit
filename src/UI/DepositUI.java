package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import banksystem.*;

public class DepositUI extends JFrame {
    private JLabel depositLabel;
    private JTextField amountField;
    private JButton depositButton;
    ImageIcon logo = new ImageIcon("LOGO.jpg");
    public DepositUI(Account userAccount, User user) {
        setTitle("Deposit");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setIconImage(logo.getImage());
        // Panel for deposit components
        JPanel depositPanel = new JPanel();
        depositPanel.setLayout(new GridLayout(3, 1));

        // Label for deposit message
        depositLabel = new JLabel("How much money are you depositing?");
        depositPanel.add(depositLabel);

        // Text field for amount
        amountField = new JTextField();
        depositPanel.add(amountField);

        // Button for deposit
        depositButton = new JButton("Deposit");
        depositButton.setBackground(new Color(39, 174, 96));
        depositButton.setForeground(Color.WHITE);
        depositButton.setFocusPainted(false);
        depositPanel.add(depositButton);

        // Add deposit panel to frame
        add(depositPanel, BorderLayout.CENTER);

        // Action listener for deposit button
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the amount from the text field
                String amountText = amountField.getText();

                // Convert amount to double
                double amount = Double.parseDouble(amountText);

                userAccount.deposit(amount);

                JOptionPane.showMessageDialog(null, "Dear " + user.getUsername() + " you succefully deposited " + amount +
                        " USD into your account. Press ok to return to your dashboard.");

                dispose();
                new DashboardUIRegular(user, userAccount);


            }
        });

        setVisible(true);
    }
}