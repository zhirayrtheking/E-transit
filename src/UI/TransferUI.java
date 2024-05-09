package UI;

import banksystem.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransferUI extends JFrame {
    private JTextField destinationField;
    private JTextField amountField;
    private JTextField destinationBankField;
    String username;
    String password;
    ImageIcon logo = new ImageIcon("LOGO.jpg");

    public TransferUI(Account sourceAccount) {
        setTitle("Transfer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setResizable(false);
        Color backgroundColor = new Color(52, 152, 219);
        Color buttonColor = new Color(46, 204, 113);
        Color textColor = Color.WHITE;

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(backgroundColor); // Set background color
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel destinationLabel = new JLabel("user:");
        destinationLabel.setForeground(textColor); // Set text color
        destinationField = new JTextField(20);
        destinationField.setBorder(new EmptyBorder(0,0,0,0));
        panel.add(destinationLabel, gbc);
        gbc.gridx++;
        panel.add(destinationField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;

        JLabel destinationBankLabel = new JLabel("Bank :");
        destinationBankLabel.setForeground(textColor); // Set text color
        destinationBankField = new JTextField(20);
        destinationBankField.setBorder(new EmptyBorder(0,0,0,0));
        panel.add(destinationBankLabel, gbc);
        gbc.gridx++;
        panel.add(destinationBankField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setForeground(textColor); // Set text color
        amountField = new JTextField(20);
        amountField.setBorder(new EmptyBorder(0,0,0,0));
        panel.add(amountLabel, gbc);
        gbc.gridx++;
        panel.add(amountField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;



        JButton transferButton = new JButton("Transfer");
        transferButton.setBackground(buttonColor); // Set background color
        transferButton.setForeground(textColor); // Set text color
        transferButton.setFocusPainted(false);
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Bank destinationBank = new Bank(destinationBankField.getName());
                Account destinationAccount = new Account(destinationField.getText(), destinationBank);
                if(sourceAccount.transferTo(sourceAccount, destinationAccount, Double.parseDouble(amountField.getText()))){
                    JOptionPane.showMessageDialog(null, "Amount of " + amountField.getText() + "$ was transferred to user " + destinationField.getText(),
                            "E-Transit", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    new LoginUI();
                }else {
                    JOptionPane.showMessageDialog(null, "Insufficent Funds",
                            "E-Transit", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        gbc.gridwidth = 2;
        panel.add(transferButton, gbc);

        add(panel);
        setLocationRelativeTo(null);
        setIconImage(logo.getImage());
        setVisible(true);
    }



}
