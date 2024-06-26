package UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import banksystem.*;

public class SignUpUI extends JFrame {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JPasswordField passwordField;
    private JTextField bankField;
    ImageIcon logo = new ImageIcon("LOGO.jpg");

    public SignUpUI() {
        setTitle("Sign Up");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 250);
        setResizable(false);
        Color backgroundColor = new Color(52, 152, 219);
        Color buttonColor = new Color(241, 196, 15);
        Color textColor = Color.WHITE;

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(backgroundColor); // Set background color
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setForeground(textColor); // Set text color
        firstNameField = new JTextField(20);
        firstNameField.setBorder(new EmptyBorder(0,0,0,0));
        panel.add(firstNameLabel, gbc);
        gbc.gridx++;
        panel.add(firstNameField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setForeground(textColor); // Set text color
        lastNameField = new JTextField(20);
        lastNameField.setBorder(new EmptyBorder(0,0,0,0));
        panel.add(lastNameLabel, gbc);
        gbc.gridx++;
        panel.add(lastNameField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(textColor); // Set text color
        passwordField = new JPasswordField(20);
        passwordField.setBorder(new EmptyBorder(0,0,0,0));
        panel.add(passwordLabel, gbc);
        gbc.gridx++;
        panel.add(passwordField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;

        JLabel bankLabel = new JLabel("Bank:");
        bankLabel.setForeground(textColor); // Set text color
        bankField = new JTextField(20);
        bankField.setBorder(new EmptyBorder(0,0,0,0));
        panel.add(bankLabel, gbc);
        gbc.gridx++;
        panel.add(bankField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;

        JButton signUpButton = new JButton("Sign-Up");
        signUpButton.setBackground(buttonColor); // Set background color
        signUpButton.setForeground(textColor); // Set text color
        signUpButton.setFocusPainted(false);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bankText = bankField.getText();
                Bank bank = new Bank(bankText);
                String databaseFilePath = "database/users.txt";
                Map<String, User> users = User.loadUsers(databaseFilePath, bank);
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String username = firstName + "_" + lastName;
                String password = new String(passwordField.getPassword());
                if (password.contains(":")){
                    JOptionPane.showMessageDialog(null, "Password contains ':' symbol. Please choose something else.",
                            "E-Transit", JOptionPane.ERROR_MESSAGE);
                } else if (users.containsKey(username)) {
                    JOptionPane.showMessageDialog(null, "Username already exists",
                            "E-Transit", JOptionPane.ERROR_MESSAGE);
                }
                AuthenticationSystem authenticationSystem = new AuthenticationSystem(users, databaseFilePath, bank);
                authenticationSystem.registerUser(firstName, lastName, password);
                JOptionPane.showMessageDialog(null, "Registeration complete. Press OK to return to Login Page.","E-Transit",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new LoginUI();

            }
        });
        gbc.gridwidth = 2;
        panel.add(signUpButton, gbc);

        add(panel);
        setLocationRelativeTo(null);
        setIconImage(logo.getImage());
        setVisible(true);
    }


}
